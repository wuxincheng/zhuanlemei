package com.wuxincheng.zhuanlemei.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.CommentDao;
import com.wuxincheng.zhuanlemei.dao.ProductDao;
import com.wuxincheng.zhuanlemei.model.Comment;
import com.wuxincheng.zhuanlemei.util.Constants;

/**
 * 评论
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年6月29日 下午3:17:13 
 *
 */
@Service("commentService")
public class CommentService {

	@Resource 
	private CommentDao commentDao;
	
	@Resource 
	private ProductDao productDao;
	
	/**
	 * 发表回复
	 */
	public Integer post(Comment comment, String userid) {
		// 发表评论
		comment.setUserid(userid);
		comment.setCommentState(Constants.DEFAULT_STATE);
		comment.setLikeSum(0);
		comment.setReplySum(0);
		commentDao.post(comment);
		
		// 更新产品的评论数
		productDao.plusCommentSum(comment.getProductid());
		
		// 更新产品的关注度
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("prodid", comment.getProductid());
		updateMap.put("score", 3);
		productDao.score(updateMap);
		
		return 1;
	}

	/**
	 * 根据产品ID查询评论列表
	 */
	public List<Comment> queryByProductid(String productid) {
		return commentDao.queryByProductid(productid);
	}

}
