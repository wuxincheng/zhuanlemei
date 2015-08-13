package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.zhuanlemei.model.Collect;

@Repository("collectDao")
public class CollectDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Collect> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryAll");
	}

	public void create(Collect collect) {
		this.getSqlMapClientTemplate().update("Collect.create", collect);
	}

	public Collect queryDetailByCollectid(String collectid) {
		return (Collect) this.getSqlMapClientTemplate().queryForObject("Collect.queryDetailByCollectid", collectid);
	}

	public void addProductSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addProductSum", collectid);
	}

	public void addCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addCollectSum", collectid);
	}

	public void cutCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.cutCollectSum", collectid);
	}

	@SuppressWarnings("unchecked")
	public List<Collect> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryByUserid", userid);
	}

	public void delete(String collectid) {
		this.getSqlMapClientTemplate().delete("Collect.delete", collectid);
	}

	public void update(Collect collect) {
		this.getSqlMapClientTemplate().update("Collect.update", collect);
	}

}
