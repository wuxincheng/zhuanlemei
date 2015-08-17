package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.zhuanlemei.model.FundCollect;

@Repository("fundCollectDao")
public class FundCollectDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<FundCollect> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryAll");
	}

	public void create(FundCollect fundCollect) {
		this.getSqlMapClientTemplate().update("FundCollect.create", fundCollect);
	}

	public FundCollect queryDetailByCollectid(String collectid) {
		return (FundCollect) this.getSqlMapClientTemplate().queryForObject("Collect.queryDetailByCollectid", collectid);
	}

	public void addProductSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addFundSum", collectid);
	}

	public void addCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addFundSum", collectid);
	}

	public void cutCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.cutCollectSum", collectid);
	}

	@SuppressWarnings("unchecked")
	public List<FundCollect> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryByUserid", userid);
	}

	public void update(FundCollect fundCollect) {
		this.getSqlMapClientTemplate().update("Collect.update", fundCollect);
	}

}
