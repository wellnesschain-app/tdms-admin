package com.td.admin.service.impl;

import com.td.admin.common.Constants;
import com.td.admin.entity.EthereumEntity;
import com.td.admin.service.NodeService;
import com.td.admin.util.*;
import com.td.admin.util.*;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class NodeServiceImpl implements NodeService {
    private static final SecureRandom secureRandom = MySecureRandomUtils.secureRandom();
    private static final String URL = "http://47.244.135.69:9901";
    private static final String KEYSTOREPATH = "/data/svo/keystore/";
    private static final String PASSWORD = "yx2018";
    private static final String unitStr = "1000000000000000000";// svo单位


    /**
     * 获取svo余额和ETH余额
     * @return
     */
    @Override
    public Map<String, Object> getBalance() {
        Map<String, Object> map = new HashMap<>();
        String msg="ok";
        String svo="";
        String eth="";
        try {
            //获取svo代币余额
            BigDecimal svoBalance = getsvoBalance(Constants.SVO_IP, Constants.SVO_PORT, Constants.SVO_COTARCT_ADDR,Constants.WALLET_ADDR, null);
            if(svoBalance.compareTo(new BigDecimal(0))==0){
                svo="0.00";
            }else{
                svo=svoBalance.toString();
            }

            BigDecimal ethBalance = getETHBalance("0x665f1cad7d13527592f2a3d68b066918bd822cd0", Constants.SVO_IP + ":" + Constants.SVO_PORT);
            if(ethBalance.compareTo(new BigDecimal(0))==0){
                eth="0.00";
            }else{
                eth=ethBalance.toString();
            }
            map.put("svo",svo);
            map.put("eth",eth);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    //16进制转10进制
    public static BigDecimal realValue(String input, String unit) {
        BigDecimal bigDecimal = new BigDecimal(input);
        if (StringHelper.isEmpty(unit)) {
            unit = unitStr;
        }
        bigDecimal = bigDecimal.divide(new BigDecimal(unit), 8, BigDecimal.ROUND_HALF_DOWN);
        return bigDecimal;
    }


    public static BigInteger decodeHex(String hex_num) throws Throwable {
        hex_num = hex_num.replace("0x", "");
        if ("".equals(hex_num)) {
            return new BigInteger("0");
        }
        BigInteger bigInterger = new BigInteger(hex_num, 16);
        return bigInterger;
    }

    //填充0
    public static String fill_zero(String input) throws Throwable {
        String str = input.replace("0x", "");
        int strLen = input.length();
        StringBuffer sb = null;
        while (strLen < 64) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }


    public static String encodeDec(BigDecimal num) throws Throwable {
        String strPart = StringHelper.toUnsignedString(num, 16);
        return "0x" + strPart;
    }

    //以太钱包地址规范
    public static boolean checkWalletAddress(String address) {
        address = address.replace(" ", "");
        // 判断地址
        if (StringHelper.isEmpty(address)) {
            return false;
        }
        if (address.length() != 42) {
            return false;
        }
        if (!address.startsWith("0x")) {
            return false;
        }
        return true;
    }



    /**
     * 获取ETH余额
     * @param addr
     * @param ipAddrAndPort
     * @return
     */
    public static BigDecimal getETHBalance(String addr,String ipAddrAndPort){
        Web3j web3j=Web3j.build(new HttpService(ipAddrAndPort));
        //返回为wei为单位的余额
        BigDecimal balance = null;
        try {
            BigInteger getBalanc = web3j.ethGetBalance(addr, DefaultBlockParameterName.LATEST).send().getBalance();
            //转成GETHER为单位
            balance = Convert.fromWei(String.valueOf(getBalanc), Convert.Unit.ETHER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balance;
    }



    //获取以太坊代币
    public static BigDecimal getsvoBalance(String ip, String port, String contractAddress, String address, String unit)
            throws Throwable {

        StringBuffer url = new StringBuffer(ip).append(":" + port);
        String method = "0x70a08231";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc", "2.0");
        map.put("method", "eth_call");
        map.put("id", "1");

        HashMap<String, Object> parmas = new HashMap<String, Object>();
        parmas.put("to", contractAddress);
        parmas.put("data", method + fill_zero(address));

        map.put("params", new Object[] { parmas, "latest" });
        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
        EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
        if (json.error != null) {
            throw new Exception("代币钱包链接失败1015：" + json.error);
        } else {
            return  realValue(decodeHex((String) json.result).toString(), unit);
        }
    }

    /**
     * 从公共钱包提出svo到其他平台
     * @param  //申请地址(不是转出地址，转出地址是水链APP公共钱包,这个只做给用户存记录)
     * @param to
     * @param value
     * @return
     */
    @Test
    @Override
    public Map<String, Object> transactionsForConstract(String to, String value) {
        Map<String, Object> map = new HashMap<>();
        EthManageImpl too=new EthManageImpl();
        String msg="ok";
            try {
                //连接节点
                Admin web3j= Admin.build(new HttpService(Constants.SVO_IP + ":" + Constants.SVO_PORT));
                Geth geth= Geth.build(new HttpService(Constants.SVO_IP + ":" + Constants.SVO_PORT));

                //获取eth手续费余额
                BigDecimal ethBalance = getETHBalance("0x665f1cad7d13527592f2a3d68b066918bd822cd0",
                        Constants.SVO_IP + ":" + Constants.SVO_PORT);

                //获取svo代币余额
                BigDecimal svoBalance = getsvoBalance(Constants.SVO_IP, Constants.SVO_PORT, Constants.SVO_COTARCT_ADDR,
                        Constants.WALLET_ADDR, null);


                //获取当前手续费
                BigDecimal gas=new BigDecimal("60000");
                BigInteger gP = web3j.ethGasPrice().send().getGasPrice();
                //System.out.println("gP:"+gP);
                BigDecimal gasPrice = Convert.fromWei(String.valueOf(gP), Convert.Unit.ETHER);
                //System.out.println("gasPrice:"+gasPrice);
                BigDecimal fee = gasPrice.multiply(gas);
                //System.out.println("手续费:"+fee);
                if(svoBalance.compareTo(new BigDecimal(value))==-1){
                    msg="提出失败！钱包余额不足。";
                }else if(ethBalance.compareTo(fee)==-1){
                    msg="提出失败！钱包手续费不足。";
                }else{
                    //进行解锁操作
                    boolean isUnlock = web3j.personalUnlockAccount("0x665f1cad7d13527592f2a3d68b066918bd822cd0",
                            "Bzx10241024.").send().accountUnlocked().booleanValue();

                    if(isUnlock){//解锁成功则进行转账
                        EthereumEntity transactionResult = too.eth_sendTransaction(Constants.SVO_IP, Constants.SVO_PORT,
                                "0x665f1cad7d13527592f2a3d68b066918bd822cd0", "0", Constants.SVO_COTARCT_ADDR, to, value);
                        if(transactionResult.result==null){//返回结果为null说明这笔交易没有发起
                            msg="转出失败！";
                        }else{
                            String txHash = transactionResult.result.toString();//发起交易会返回交易哈希
                            map.put("txHash",txHash);//返回交易哈希
                            boolean success = geth.personalLockAccount("0x665f1cad7d13527592f2a3d68b066918bd822cd0").send().success();
                            if(!success){
                                msg="钱包上锁失败!";
                            }
                        }

                    }else{
                        msg="钱包解锁失败！";
                    }

                }

            }catch (Exception e){
                msg="系统繁忙";
                e.printStackTrace();
            } catch (Throwable throwable) {
                msg=throwable.getMessage();
                throwable.printStackTrace();
            }
        map.put("msg",msg);
        return map;
    }


    public static void main(String[] args) {
       /* EthManageImpl too=new EthManageImpl();
        try {
            String s = too.personal_unlockAccount("http://47.244.135.69", "9901", "0x658043e8d09640914f5304f9f68ab27f369ac82c", "e5x96z5y0");
            System.out.println("解锁完成"+s+"进行交易");

            EthereumEntity transactionResult=too.eth_sendTransaction("http://47.244.135.69", "9901",
                    "0x658043e8d09640914f5304f9f68ab27f369ac82c","0",
                    "0xa5d9ac5ffef9c72e4cf91ebb6c8a672da044fb31","0x665f1cad7d13527592f2a3d68b066918bd822cd0","100");

            System.out.println(transactionResult.result.toString());
            Map<String, Object> stringObjectMap = too.personal_lockAccount("http://47.244.135.69", "9901", "0x658043e8d09640914f5304f9f68ab27f369ac82c");
        }catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
*/
        Admin web3j= Admin.build(new HttpService(Constants.SVO_IP + ":" + Constants.SVO_PORT));
        Geth geth= Geth.build(new HttpService(Constants.SVO_IP + ":" + Constants.SVO_PORT));
        try {
            boolean isUnlock = web3j.personalUnlockAccount("0x665f1cad7d13527592f2a3d68b066918bd822cd0",
                    "Bzx10241024.").send().accountUnlocked().booleanValue();
            if(isUnlock){
                System.out.println("钱包解锁成功!");
            }else{
                System.out.println("钱包解锁失败!");
            }


            boolean success = geth.personalLockAccount("0x665f1cad7d13527592f2a3d68b066918bd822cd0").send().success();
            if(success){
                System.out.println("钱包上锁成功!");
            }else{
                System.out.println("钱包上锁失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*Web3j web3j=Web3j.build(new HttpService(Constants.svo_IP + ":" + Constants.svo_PORT));
        try {
            BigDecimal gas=new BigDecimal("60000");
            BigInteger gP = web3j.ethGasPrice().send().getGasPrice();
            System.out.println("gP:"+gP);
            BigDecimal gasPrice = Convert.fromWei(String.valueOf(gP), Convert.Unit.ETHER);
            System.out.println("gasPrice:"+gasPrice);
            BigDecimal fee = gasPrice.multiply(gas);
            System.out.println("手续费:"+fee);

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*BigDecimal ethBalance = getETHBalance("0x658043e8d09640914f5304f9f68ab27f369ac82c", Constants.svo_IP + ":" + Constants.svo_PORT);
        System.out.println(ethBalance);*/
    }

}
