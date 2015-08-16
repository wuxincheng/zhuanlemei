package com.wuxincheng.zhuanlemei.dao;

import org.springframework.stereotype.Component;

import com.wuxincheng.zhuanlemei.model.FundMarket;

@Component("fundMarketDao")
public class FundMarketDao extends BaseDao {

	/**
	 * 根据基金代码和基金净值日期查询基金行情信息
	 * 
	 * @param fundCode
	 *            基金代码
	 * @param fundNavDate
	 *            基金净值日期(MM-dd)
	 * @param currentDate
	 *            基金净值日期(yyyy-MM-dd)
	 * @return
	 */
	public FundMarket query(String fundCode, String fundNavDate, String currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 插入一条基金行情记录
	 * 
	 * @param fundMarket
	 *            基金行情
	 */
	public void insert(FundMarket fundMarket) {
		// TODO Auto-generated method stub
	}

}
