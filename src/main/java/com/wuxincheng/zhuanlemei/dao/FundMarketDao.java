package com.wuxincheng.zhuanlemei.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wuxincheng.zhuanlemei.model.FundMarket;

/**
 * 当天基金行情信息
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月16日 下午5:13:09
 * 
 */
@Component("fundMarketDao")
public class FundMarketDao extends BaseDao {

	/**
	 * 根据基金代码和基金净值日期查询基金行情信息
	 * 
	 * @param fundCode
	 *            基金代码
	 * @param navDate
	 *            基金净值日期(MM-dd)
	 * @return
	 */
	public FundMarket query(String fundCode, String navDate) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("fundCode", fundCode);
		queryMap.put("navDate", navDate);
		return (FundMarket) this.getSqlMapClientTemplate().queryForObject("FundMarket.query",
				queryMap);
	}

	/**
	 * 插入一条基金行情记录
	 * 
	 * @param fundMarket
	 *            基金行情
	 */
	public void insert(FundMarket fundMarket) {
		this.getSqlMapClientTemplate().update("FundMarket.insert", fundMarket);
	}

	/**
	 * 更新一条基金行情信息
	 * 
	 * @param fundMarketFlag
	 *            基金行情
	 */
	public void update(FundMarket fundMarket) {
		this.getSqlMapClientTemplate().update("FundMarket.update", fundMarket);
	}

	/**
	 * 查询所有基金行情信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryAll");
	}

	public FundMarket queryDetail(String fundCode) {
		return (FundMarket) this.getSqlMapClientTemplate().queryForObject("FundMarket.queryDetail", fundCode);
	}

	/**
	 * 更新产品的评论数
	 */
	public void plusCommentSum(String fundCode) {
		this.getSqlMapClientTemplate().update("FundMarket.plusCommentSum", fundCode);
	}

	public void score(Map<String, Object> like) {
		this.getSqlMapClientTemplate().update("FundMarket.score", like);
	}
	
	public void postLikeScore(Map<String, Object> likeScore) {
		this.getSqlMapClientTemplate().update("FundMarket.postLikeScore", likeScore);
	}
	
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryLikeByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryLikeByUserid", userid);
	}

	@SuppressWarnings("unchecked")
	public List<FundMarket> queryUserMain(Map<String, String> queryMap) {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryUserMain", queryMap);
	}

}
