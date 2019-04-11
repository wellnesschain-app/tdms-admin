package com.td.admin.util;

public enum CoinType {
	ETH("以太坊系列"), BTC("比特币系列"), SWT("井通系列"),SAN("瑞波系列"),MOAC("墨客系列"),ETF("以太雾系列"),ETC("以太经典系列"),DCAR("笛卡尔系列");

	protected final String name;

	private CoinType(String name) {
		this.name = name;
	}

	/**
	 * 获取中文名称.
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
}
