package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.zhuanlemei.model.FundLike;

@Repository("fundLikeDao")
public class FundLikeDao extends BaseDao {

	public void insert(FundLike fundLike) {
		this.getSqlMapClientTemplate().update("FundLike.insert", fundLike);
	}
	
	public void delete(FundLike fundLike) {
		this.getSqlMapClientTemplate().delete("FundLike.delete", fundLike);
	}
	
	public FundLike query(FundLike fundLike) {
		return (FundLike)this.getSqlMapClientTemplate().queryForObject("FundLike.query", fundLike);
	}
	
	@SuppressWarnings("unchecked")
	public List<FundLike> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("FundLike.queryByUserid", userid);
	}

	@SuppressWarnings("unchecked")
	public List<FundLike> queryLikeUserDetail(String fundCode) {
		return this.getSqlMapClientTemplate().queryForList("FundLike.queryLikeUserDetail", fundCode);
	}
	
}
