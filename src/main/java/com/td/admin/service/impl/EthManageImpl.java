package com.td.admin.service.impl;

import com.td.admin.entity.EthereumEntity;
import com.td.admin.service.EthManage;
import com.td.admin.util.CoinType;
import com.td.admin.util.HttpRequest;
import com.td.admin.util.JsonUtil;
import com.td.admin.util.StringHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class EthManageImpl implements EthManage {
	
	
	private static final String unitStr = "1000000000000000000";// 默认以太坊

	// 同步钱包数据
	public void rechargeEthereum() throws Throwable {


	}
	// 申请手续费
	public void applyEthereum() throws Throwable {


	}


	// 获取转换后的金额(wei)
	@Override
	public BigDecimal realValue(String input, String unit) {
		BigDecimal bigDecimal = new BigDecimal(input);
		if (StringHelper.isEmpty(unit)) {
			unit = unitStr;
		}
		bigDecimal = bigDecimal.divide(new BigDecimal(unit), 8, BigDecimal.ROUND_HALF_DOWN);
		return bigDecimal;
	}

	
	 // 转换 为的wei金额
	@Override
	public BigDecimal weiValue(String input, String unit) {
		BigDecimal bigDecimal = new BigDecimal(input);
		if (StringHelper.isEmpty(unit)) {
			unit = unitStr;
		}
		bigDecimal = bigDecimal.multiply(new BigDecimal(unit));
		return bigDecimal;
	}

	
	//16进制转10进制
	   
	@Override
	public BigInteger decodeHex(String hex_num) throws Throwable {
		hex_num = hex_num.replace("0x", "");
		if ("".equals(hex_num)) {
			return new BigInteger("0");
		}
		BigInteger bigInterger = new BigInteger(hex_num, 16);
		return bigInterger;
	}

	
	//10进制转16进制(加上0x)
	   
	@Override
	public String encodeDec(BigDecimal num) throws Throwable {

		String strPart = StringHelper.toUnsignedString(num, 16);
		return  "0x"+ strPart;
	}


	
	
   	 //填充0
	 
	@Override
	public String fill_zero(String input) throws Throwable {
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

	//去除0
	@Override
	public String getridof_zero(String input) throws Throwable {
		
		return null;
	}
	


	
	//解锁账户(交易前需要操作)
	@Override
	public String  personal_unlockAccount(String ip, String port, String address, String password)
			throws Throwable {
		
		StringBuffer url = new StringBuffer(ip).append(":" + port);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("jsonrpc", "2.0");
		map.put("method", "personal_unlockAccount");
		map.put("id", "1");
		map.put("params", new Object[] { address, password});
		System.out.println(JsonUtil.GsonString(map));
		String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map));
		Map<String, Object> json = JsonUtil.GsonToMaps(result);
		return result;
	
	}
	
	//锁定账户(交易后需要操作)
	@Override
	public Map<String, Object> personal_lockAccount(String ip, String port, String address) throws Throwable {
		
		return null;
	}

	//返回当前以太坊协议版本(可测试接口) 
	@Override
	public EthereumEntity eth_protocolVersion(String ip, String port) throws Throwable {
		
		return null;
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

	//进行转账交易 
	@Override
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
		// 如果为以太坊
		String gas =  "60000";
		parmas.put("gas", encodeDec(new BigDecimal(gas)));
        //System.out.println("GasPrice:"+getGasPrice(ip,port));
		parmas.put("gasPrice",getGasPrice(ip,port));
        //System.out.println("Nonce:"+getNonce(from,ip,port));
		parmas.put("nonce", getNonce(from,ip,port));
		parmas.put("data", data);
		map.put("params", new Object[] { parmas });

		String result = HttpRequest.sendPost(url.toString(), JsonUtil.GsonString(map), CoinType.ETH.getName());
		System.out.println(result);
		EthereumEntity json = JsonUtil.GsonToBean(result, EthereumEntity.class);
		return json;
	}




	//根据Hash获取交易记录
	@Override
	public Map<String, Object> eth_getTransactionByHash(String ip, String port, String hash) throws Throwable {
		
		return null;
	}

	@Override
	public EthereumEntity eth_getTransactionReceipt(String ip, String port, String hash) throws Throwable {
		return null;
	}




	public static void main(String[] args) throws Throwable {
		EthManageImpl too=new EthManageImpl();
		
//		Map<String, Object> personal_newAccount = too.personal_newAccount("http://47.244.135.69", "9801", "123456");
		
		//String personal_unlockAccount = too.personal_unlockAccount("http://47.244.135.69", "9801","0x22e18de4677da8be5178286279ef926f9f196507","123456");
		
		//Map<String, Object> eth_accounts = too.eth_accounts("http://47.244.135.69", "9801");
		
//		String eth_blockNumber = too.eth_blockNumber("http://47.75.147.173", "19913");
//		
//		System.out.println(eth_blockNumber);
		
		//too.eth_getTransactionByBlockNumberAndIndex("http://47.244.135.69", "http://47.244.135.69",0,0);
	
		//System.out.println(too.contract_getBalance("http://47.75.58.109", "19910","0xb31B7cb40fd15b87c8a222caa5fd7d5192b5eb81","0x665f1cad7d13527592f2a3d68b066918bd822cd0",null));

//		BigDecimal a=too.weiValue("10000",null);
//		System.out.println(a);
//		System.out.println(too.encodeDec(a));

		/*String s = too.personal_unlockAccount("http://47.244.135.69", "9901", "0x658043e8d09640914f5304f9f68ab27f369ac82c", "e5x96z5y0");
		System.out.println("解锁完成"+s+"进行交易");

		Thread.sleep(8000);
		EthereumEntity transactionResult=too.eth_sendTransaction("http://47.244.135.69", "9901",
                "0x658043e8d09640914f5304f9f68ab27f369ac82c","0",
                "0xa5d9ac5ffef9c72e4cf91ebb6c8a672da044fb31","0x665f1cad7d13527592f2a3d68b066918bd822cd0","100");

		System.out.println(transactionResult.result.toString());
		Map<String, Object> stringObjectMap = too.personal_lockAccount("http://47.244.135.69", "9901", "0x658043e8d09640914f5304f9f68ab27f369ac82c");
*/
    }


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
