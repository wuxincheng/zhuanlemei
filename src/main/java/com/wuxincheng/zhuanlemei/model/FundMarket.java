package com.wuxincheng.zhuanlemei.model;

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

	/** 基金公司 */
	private String fundCompany;

	/** 基金类型 */
	private String fundType;

	/** 基金成立日期 */
	private String foundedDate;

	/** 基金风险级别 */
	private String fundRiskLevel;

	/** 基金经理 */
	private String fundManager;

	/** 基金昨日单位净值 */
	private String lastNav;

	/** 基金净值(如果是货币基金, 则为7日年化收益率) */
	private String currentNav;

	/** 基金昨日总份额 */
	private String lastTotalShare;

	/** 基金当日状态 */
	private String currentState;

	/** 基金收益 */
	private String fundIncome;

	/** 基金收益率 */
	private String fundIncomeRate;

	/** 净值日期 */
	private String navDate;

	/** 累计单位净值 */
	private String totalNav;

	/** 近一周 */
	private String fundRiseWeek;

	/** 近一个月 */
	private String fundRiseMonth;

	/** 近三个月 */
	private String fundRiseThreeMonth;

	/** 近半年 */
	private String fundRiseHalfYear;

	/** 近一年 */
	private String fundRiseYear;

	/** 今年 */
	private String fundRiseThisYear;

	/** 基金最新规模 */
	private String newScale;

	/** 基金涨跌幅 */
	private String rateChange;

	/** 基金近3月排名 */
	private String fundSortThreeMonth;
	private String fundTotalThreeMonth;

	/** 创建时间 */
	private String createTime;

	/** 更新时间 */
	private String updateTime;

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

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(String foundedDate) {
		this.foundedDate = foundedDate;
	}

	public String getFundRiskLevel() {
		return fundRiskLevel;
	}

	public void setFundRiskLevel(String fundRiskLevel) {
		this.fundRiskLevel = fundRiskLevel;
	}

	public String getFundManager() {
		return fundManager;
	}

	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}

	public String getLastNav() {
		return lastNav;
	}

	public void setLastNav(String lastNav) {
		this.lastNav = lastNav;
	}

	public String getCurrentNav() {
		return currentNav;
	}

	public void setCurrentNav(String currentNav) {
		this.currentNav = currentNav;
	}

	public String getLastTotalShare() {
		return lastTotalShare;
	}

	public void setLastTotalShare(String lastTotalShare) {
		this.lastTotalShare = lastTotalShare;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getFundIncome() {
		return fundIncome;
	}

	public void setFundIncome(String fundIncome) {
		this.fundIncome = fundIncome;
	}

	public String getFundIncomeRate() {
		return fundIncomeRate;
	}

	public void setFundIncomeRate(String fundIncomeRate) {
		this.fundIncomeRate = fundIncomeRate;
	}

	public String getNavDate() {
		return navDate;
	}

	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}

	public String getTotalNav() {
		return totalNav;
	}

	public void setTotalNav(String totalNav) {
		this.totalNav = totalNav;
	}

	public String getFundRiseWeek() {
		return fundRiseWeek;
	}

	public void setFundRiseWeek(String fundRiseWeek) {
		this.fundRiseWeek = fundRiseWeek;
	}

	public String getFundRiseMonth() {
		return fundRiseMonth;
	}

	public void setFundRiseMonth(String fundRiseMonth) {
		this.fundRiseMonth = fundRiseMonth;
	}

	public String getFundRiseThreeMonth() {
		return fundRiseThreeMonth;
	}

	public void setFundRiseThreeMonth(String fundRiseThreeMonth) {
		this.fundRiseThreeMonth = fundRiseThreeMonth;
	}

	public String getFundRiseHalfYear() {
		return fundRiseHalfYear;
	}

	public void setFundRiseHalfYear(String fundRiseHalfYear) {
		this.fundRiseHalfYear = fundRiseHalfYear;
	}

	public String getFundRiseYear() {
		return fundRiseYear;
	}

	public void setFundRiseYear(String fundRiseYear) {
		this.fundRiseYear = fundRiseYear;
	}

	public String getFundRiseThisYear() {
		return fundRiseThisYear;
	}

	public void setFundRiseThisYear(String fundRiseThisYear) {
		this.fundRiseThisYear = fundRiseThisYear;
	}

	public String getNewScale() {
		return newScale;
	}

	public void setNewScale(String newScale) {
		this.newScale = newScale;
	}

	public String getRateChange() {
		return rateChange;
	}

	public void setRateChange(String rateChange) {
		this.rateChange = rateChange;
	}

	public String getFundSortThreeMonth() {
		return fundSortThreeMonth;
	}

	public void setFundSortThreeMonth(String fundSortThreeMonth) {
		this.fundSortThreeMonth = fundSortThreeMonth;
	}

	public String getFundTotalThreeMonth() {
		return fundTotalThreeMonth;
	}

	public void setFundTotalThreeMonth(String fundTotalThreeMonth) {
		this.fundTotalThreeMonth = fundTotalThreeMonth;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFundCompany() {
		return fundCompany;
	}

	public void setFundCompany(String fundCompany) {
		this.fundCompany = fundCompany;
	}

}
