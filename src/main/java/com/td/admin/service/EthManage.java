package com.td.admin.service;

import com.td.admin.entity.EthereumEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public abstract interface EthManage {

	/**
	 * 获取转换后的金额(wei)
	 * @param input
	 * @return
	 * @throws Throwable
	 */
	public BigDecimal realValue(String input, String unit);


	/**
	 * 转换 为的wei金额
	 * @param input
	 * @return
	 * @throws Throwable
	 */
   public BigDecimal weiValue(String input, String unit);


   /**
    * 16进制转10进制
    * @param hex_num
    * @return
    * @throws Throwable
    */
   public BigInteger decodeHex(String hex_num) throws Throwable;

   /**
    * 10进制转16进制(加上0x)
    * @param num
    * @return
    * @throws Throwable
    */
   public String encodeDec(BigDecimal num) throws Throwable;


   	/**
   	 * 填充0
	 * @param input
	 * @return
	 * @throws Throwable
	 */
   public String fill_zero(String input) throws Throwable;

   	/**
   	 * 去除0
	 * @param input
	 * @return
	 * @throws Throwable
	 */
   public String getridof_zero(String input) throws Throwable;


   	/**
   	 * 解锁账户(交易前需要操作)
	 * @param address
	 * @param password
	 * @throws Throwable
	 */
   public String personal_unlockAccount(String ip, String port, String address, String password) throws Throwable;

   /**
  	 * 锁定账户(交易后需要操作)
	 * @param address
	 * @param
	 * @throws Throwable
	 */
   public Map<String, Object> personal_lockAccount(String ip, String port, String address) throws Throwable;

   /**
     * 返回当前以太坊协议版本(可测试接口)
	 * @throws Throwable
	 */
   public EthereumEntity eth_protocolVersion(String ip, String port) throws Throwable;


   /**
     * 进行转账交易
	 * @param address
	 * @param value
	 * @param toaddress
	 * @throws Throwable
	 */
   public EthereumEntity eth_sendTransaction(String ip, String port, String address, String value, String toaddress, String zhqb, String zcsl) throws Throwable ;



   /**
     * 根据Hash获取交易记录
	 * @param hash
	 * @throws Throwable
	 */

	public Map<String, Object> eth_getTransactionByHash(String ip, String port, String hash) throws Throwable;




	/**
	 * 获取交易数据情况(根据Hash)
	 * @param hash
	 * @return
	 * @throws Throwable
	 */
	public EthereumEntity eth_getTransactionReceipt(String ip, String port, String hash) throws Throwable;


		
}
