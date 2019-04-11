package com.td.admin.util;

import java.io.Serializable;

public class EthereumEntity implements Serializable {
	

	//rpc版本
	public String jsonrpc;
	//id号
	public String id;
	//返回参数
	public Object result;
	//错误参数
	public Object error;
	
	
}
