package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.zhuanlemei.model.FundCollectUser;

@Repository("fundCollectUserDao")
public class FundCollectUserDao extends BaseDao {

	public void insert(FundCollectUser fundCollectUser) {
		this.getSqlMapClientTemplate().update("FundCollectUser.insert", fundCollectUser);
	}
	
	public void delete(FundCollectUser fundCollectUser) {
		this.getSqlMapClientTemplate().delete("FundCollectUser.delete", fundCollectUser);
	}

	public FundCollectUser query(FundCollectUser fundCollectUser) {
		return (FundCollectUser)this.getSqlMapClientTemplate().queryForObject("FundCollectUser.query", fundCollectUser);
	}

	@SuppressWarnings("unchecked")
	public List<FundCollectUser> queryCollects(String userid) {
		return this.getSqlMapClientTemplate().queryForList("FundCollectUser.queryCollects", userid);
	}
	
}
