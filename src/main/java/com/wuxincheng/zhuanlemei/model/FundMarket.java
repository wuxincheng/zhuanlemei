package com.wuxincheng.zhuanlemei.model;

import java.math.BigDecimal;

/**
 * 基金行情
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月13日 上午10:40:40
 * 
 */
public class FundMarket {

	/** 行情日期 */
	private String marketDate;

	/** 基金名称 */
	private String fundName;

	/** 基金代码 */
	private String fundCode;

	/** 基金TA代码 */
	private String fundTACode;

	/** 基金昨日单位净值 */
	private BigDecimal lastNav;

	/** 基金昨日总份额 */
	private BigDecimal lastTotalShare;

	/** 基金当日状态 */
	private String currentState;

	/** 基金收益 */
	private BigDecimal fundIncome;

	/** 基金收益率 */
	private BigDecimal fundIncomeRate;

	/** 净值日期 */
	private String navDate;

	/** 累计单位净值 */
	private BigDecimal totalNav;

	public String getMarketDate() {
		return marketDate;
	}

	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundTACode() {
		return fundTACode;
	}

	public void setFundTACode(String fundTACode) {
		this.fundTACode = fundTACode;
	}

	public BigDecimal getLastNav() {
		return lastNav;
	}

	public void setLastNav(BigDecimal lastNav) {
		this.lastNav = lastNav;
	}

	public BigDecimal getLastTotalShare() {
		return lastTotalShare;
	}

	public void setLastTotalShare(BigDecimal lastTotalShare) {
		this.lastTotalShare = lastTotalShare;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public BigDecimal getFundIncome() {
		return fundIncome;
	}

	public void setFundIncome(BigDecimal fundIncome) {
		this.fundIncome = fundIncome;
	}

	public BigDecimal getFundIncomeRate() {
		return fundIncomeRate;
	}

	public void setFundIncomeRate(BigDecimal fundIncomeRate) {
		this.fundIncomeRate = fundIncomeRate;
	}

	public String getNavDate() {
		return navDate;
	}

	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}

	public BigDecimal getTotalNav() {
		return totalNav;
	}

	public void setTotalNav(BigDecimal totalNav) {
		this.totalNav = totalNav;
	}

}
