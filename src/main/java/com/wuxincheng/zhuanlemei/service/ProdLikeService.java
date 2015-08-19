package com.wuxincheng.zhuanlemei.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.ProdLikeDao;
import com.wuxincheng.zhuanlemei.dao.ProductDao;
import com.wuxincheng.zhuanlemei.model.ProdLike;
import com.wuxincheng.zhuanlemei.model.Product;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.DateUtil;

@Service("prodLikeService")
public class ProdLikeService {

	@Resource
	private ProdLikeDao prodLikeDao;

	@Resource
	private ProductDao productDao;

	public void insert(ProdLike prodLike) {
		prodLikeDao.insert(prodLike);
	}

	public void delete(ProdLike prodLike) {
		prodLikeDao.delete(prodLike);
	}

	/**
	 * 用户点赞
	 * 
	 * @param prodid
	 *            产品主键
	 * @param userid
	 *            用户主键
	 * @return
	 */
	public Map<String, String> like(String prodid, String userid) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("prodid", prodid);

		ProdLike queryProdLike = new ProdLike();
		queryProdLike.setUserid(userid);
		queryProdLike.setProdid(prodid);
		// 查询
		ProdLike prodLike = prodLikeDao.query(queryProdLike);

		Map<String, Object> like = new HashMap<String, Object>();
		if (prodLike != null) { // 已经点赞
			// 取消点赞
			prodLikeDao.delete(queryProdLike);

			result.put("flag", "0"); // 点赞(1)或取消点赞(0)

			like.put("prodid", prodid);
			like.put("score", -1); // 关注度变化
			like.put("likeSum", -1); // 点赞数量变化
			productDao.postLikeScore(like);
		} else { // 还未点赞
			// 点赞
			queryProdLike.setLikeState(Constants.DEFAULT_STATE);
			queryProdLike.setLikeTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:Ss"));
			prodLikeDao.insert(queryProdLike);

			result.put("flag", "1"); // 点赞(1)或取消点赞(0)

			like.put("prodid", prodid);
			like.put("score", 1);
			like.put("likeSum", 1);
			productDao.postLikeScore(like);
		}

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("prodid", prodid);
		queryMap.put("userid", userid);

		Product product = productDao.queryDetailByProdid(queryMap);
		result.put("score", product.getScore()); // 点赞后产品关注分数

		return result;
	}

	/**
	 * 查询点赞用户详细信息列表
	 * 
	 * @param prodid
	 * @return
	 */
	public List<ProdLike> queryLikeUserDetail(String prodid) {
		return prodLikeDao.queryLikeUserDetail(prodid);
	}

	/**
	 * 根据基金代码和用户主键查询赞信息
	 * 
	 * @param fundCode
	 * @param userid
	 * @return
	 */
	public ProdLike queryByFundCode(String fundCode, String userid) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("fundCode", fundCode);
		queryMap.put("userid", userid);
		return prodLikeDao.queryDetailByFundCode(queryMap);
	}

}
