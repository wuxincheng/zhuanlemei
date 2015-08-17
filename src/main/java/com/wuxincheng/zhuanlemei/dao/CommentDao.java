package com.wuxincheng.zhuanlemei.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wuxincheng.zhuanlemei.model.FundComment;

@Component("commentDao")
public class CommentDao extends BaseDao {

	public Integer post(FundComment commment) {
		return (Integer)this.getSqlMapClientTemplate().update("Comment.post", commment);
	}

	@SuppressWarnings("unchecked")
	public List<FundComment> queryByProductid(String productid) {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryByProductid", productid);
	}
	
}
