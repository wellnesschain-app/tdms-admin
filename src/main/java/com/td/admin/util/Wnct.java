package com.td.admin.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Wnct {

//    private static final String unitStr = "1000000000000000000";// 默认以太坊
    private static final String unitStr = "10000";// 默认以太坊
    //填充0
    public  String fill_zero(String input) throws Throwable {
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

    //10进制转16进制(加上0x)
    public String encodeDec(BigDecimal num) throws Throwable {
        String strPart = StringHelper.toUnsignedString(num, 16);
        return  "0x"+ strPart;
    }




    // 转换 为的wei金额
    public BigDecimal weiValue(String input, String unit) {
        BigDecimal bigDecimal = new BigDecimal(input);
        if (StringHelper.isEmpty(unit)) {
            unit = unitStr;
        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(unit));
        return bigDecimal;
    }


    // 获取转换后的金额(wei)
    public  BigDecimal realValue(String input, String unit) {
        BigDecimal bigDecimal = new BigDecimal(input);
        if (StringHelper.isEmpty(unit)) {
            unit = unitStr;
        }
        bigDecimal = bigDecimal.divide(new BigDecimal(unit), 8, BigDecimal.ROUND_HALF_DOWN);
        return bigDecimal;
    }

    //16进制转10进制
    public BigInteger decodeHex(String hex_num) throws Throwable {
        hex_num = hex_num.replace("0x", "");
        if ("".equals(hex_num)) {
            return new BigInteger("0");
        }
        BigInteger bigInterger = new BigInteger(hex_num, 16);
        return bigInterger;
    }

    //获取GasPrice
    public static String getGasPrice(String ip,String port) throws Throwable {
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc", "2.0");
        map.put("method", "eth_gasPrice");
        map.put("id", "66");
        map.put("params", new Object[] {});

        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
        EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
        if (json.error != null) {
            throw new Exception("获取GasPrice失败：" + json.error);
        }
        String result2 = (String) json.result;
        return result2;
    }



    //获取Nonce
    public static String getNonce(String from,String ip,String port) throws Throwable {
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc", "2.0");
        map.put("method", "eth_getTransactionCount");
        map.put("id", "6");
        map.put("params", new Object[] { from, "latest" });

        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
        EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
        if (json.error != null) {
            throw new Exception("获取Nonce失败：" + json.error);
        }
        String result2 = (String) json.result;
        return result2;
    }


    //计算手续费
    public  static BigDecimal eth_sxf(String gas, String gasPrice) throws Throwable {
        BigDecimal sxf = new BigDecimal(gas).multiply(new BigDecimal(gasPrice));
        BigDecimal sxf_ = sxf.divide(new BigDecimal("1000000000000000000"), 6, BigDecimal.ROUND_UP);
        System.out.println("gas:" + gas);
        System.out.println("gasPrice:" + gasPrice);
        System.out.println("ETH手续费" + sxf_);
        return sxf_;
    }


    //解锁钱包
    public static Map<String, Object> personal_unlockAccount(String ip , String port, String address, String password) throws Throwable {
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc","2.0");
        map.put("method","personal_unlockAccount");
        map.put("id","1");
        map.put("params", new Object[]{address,password,60});
        String result = HttpRequest.sendPost( url.toString() , JsonUtil.GsonString(map));
        System.out.println("解锁返回信息："+result);
        Map<String, Object> json = JsonUtil.GsonToMaps(result);
        return json;
    }

    //钱包上锁操作
    public Map<String, Object> personal_lockAccount(String ip, String port, String address) throws Throwable {
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc", "2.0");
        map.put("method", "personal_lockAccount");
        map.put("id", "1");
        map.put("params", new String[] { address });
        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map));
        Map<String, Object> json = JsonUtil.GsonToMaps(result);
        return json;
    }


    //查询以太坊余额
    public BigDecimal eth_getBalance(String ip, String port, String address) throws Throwable {
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jsonrpc", "2.0");
        map.put("method", "eth_getBalance");
        map.put("id", "1");
        map.put("params", new String[] { address, "latest" });
        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
        EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
        if (json.error != null) {
            throw new Exception("代币钱包链接失败814：" + json.error);
        } else {
            BigInteger decodeStr = decodeHex((String) json.result);
            return realValue(decodeStr.toString(), "1000000000000000000");
        }
    }

    //获取以太坊代币余额
    public  BigDecimal getWnctBalance(String ip, String port, String contractAddress, String address, String unit)
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


    //进行代币转账交易
    public EthereumEntity eth_sendTransaction(String ip, String port, String from, String value, String constract,
                                              String to,String zcsl) throws Throwable {
        if (!checkWalletAddress(from)) {
            throw new Exception("钱包地址错误:" + from);
        }
        ;
        if (!checkWalletAddress(constract)) {
            throw new Exception("转出地址错误:" + constract);
        }
        ;
        StringBuffer url = new StringBuffer(ip).append(":" + port);
        HashMap<String, Object> map = new HashMap<String, Object>();
        String method = "0xa9059cbb";
        map.put("jsonrpc", "2.0");
        map.put("method", "eth_sendTransaction");
        map.put("id", "666");
        HashMap<String, Object> parmas = new HashMap<String, Object>();
        parmas.put("from", from);
        parmas.put("value", "0x0");
        parmas.put("to", constract);
        String data= method +fill_zero(to)+ fill_zero(encodeDec(weiValue(zcsl,null)));
        System.out.println(data);
        // 如果为以太坊
        String gas =  "60000";
        parmas.put("gas", encodeDec(new BigDecimal(gas)));
        //System.out.println("GasPrice:"+getGasPrice(ip,port));
        parmas.put("gasPrice",getGasPrice(ip,port));//获取手续费
        //System.out.println("Nonce:"+getNonce(from,ip,port));
        parmas.put("nonce", getNonce(from,ip,port));
        parmas.put("data", data);
        map.put("params", new Object[] { parmas });
        String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
        System.out.println(result);
        EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
        return json;
    }





    //判断钱包地址是否正确
    public boolean checkWalletAddress(String address) {
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




}
