package com.wuxincheng.zhuanlemei.dao;

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

	/**
	 * 查询所有基金经理为空的行情列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryAllNull() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryAllNull");
	}

	/**
	 * 分页查询基金行情列表
	 * 
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryPager(Map<String, Object> queryParam) {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryPager", queryParam);
	}

	/**
	 * 统计所有基金行情记录数
	 * 
	 * @param queryParam
	 * @return
	 */
	public int queryCount(Map<String, Object> queryParam) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("FundMarket.queryCount",
				queryParam);
	}

	/**
	 * 根据基金代码查询基金行情详细信息
	 * 
	 * @param fundCode
	 * @return
	 */
	public FundMarket queryDetail(String fundCode) {
		return (FundMarket) this.getSqlMapClientTemplate().queryForObject("FundMarket.queryDetail",
				fundCode);
	}

	/**
	 * 更新基金行情的评论数
	 */
	public void plusCommentSum(String fundCode) {
		this.getSqlMapClientTemplate().update("FundMarket.plusCommentSum", fundCode);
	}

	/**
	 * 更新基金行情关注度
	 * 
	 * @param scoreMap
	 */
	public void likeScore(Map<String, Object> scoreMap) {
		this.getSqlMapClientTemplate().update("FundMarket.likeScore", scoreMap);
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
