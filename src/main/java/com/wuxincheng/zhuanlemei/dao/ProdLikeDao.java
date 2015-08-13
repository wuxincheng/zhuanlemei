package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.zhuanlemei.model.ProdLike;

@Repository("prodLikeDao")
public class ProdLikeDao extends BaseDao {

	public void insert(ProdLike prodLike) {
		this.getSqlMapClientTemplate().update("ProdLike.insert", prodLike);
	}
	
	public void delete(ProdLike prodLike) {
		this.getSqlMapClientTemplate().delete("ProdLike.delete", prodLike);
	}
	
	public ProdLike query(ProdLike prodLike) {
		return (ProdLike)this.getSqlMapClientTemplate().queryForObject("ProdLike.query", prodLike);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProdLike> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("ProdLike.queryByUserid", userid);
	}

	@SuppressWarnings("unchecked")
	public List<ProdLike> queryLikeUserDetail(String prodid) {
		return this.getSqlMapClientTemplate().queryForList("ProdLike.queryLikeUserDetail", prodid);
	}
	
}
