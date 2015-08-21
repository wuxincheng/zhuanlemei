package com.wuxincheng.zhuanlemei.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundMarketDao;
import com.wuxincheng.zhuanlemei.dao.ProdLikeDao;
import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.model.ProdLike;
import com.wuxincheng.zhuanlemei.util.DateUtil;

/**
 * 基金行情信息服务类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月20日 上午8:53:38
 * 
 */
@Service("fundMarketService")
public class FundMarketService {
	private static final Logger logger = LoggerFactory.getLogger(FundMarketService.class);

	@Resource
	private FundMarketDao fundMarketDao;

	@Resource
	private ProdLikeDao prodLikeDao;

	/**
	 * 分页查询行情列表信息
	 * 
	 * @param queryParam
	 * @return
	 */
	public Map<String, Object> queryPager(Map<String, Object> queryParam) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<FundMarket> fundMarkets = fundMarketDao.queryPager(queryParam);

		int totalCount = fundMarketDao.queryCount(queryParam); // 总记录数

		result.put("fundMarkets", fundMarkets);
		result.put("totalCount", totalCount);

		return result;
	}

	/**
	 * 根据基金代码查询行情详细信息
	 * 
	 * @param fundCode
	 * @return
	 */
	public FundMarket queryDetailByFundCode(String fundCode) {
		return fundMarketDao.queryDetail(fundCode);
	}

	/**
	 * 基金行情的赞同与反对操作
	 * 
	 * @param fundCode
	 *            基金代码
	 * @param likeType
	 *            点赞或反对: 0-赞同, 1-反对
	 * @param userid
	 *            登录用户主键
	 * 
	 * @return 这条行情赞同和反对分数
	 */
	public Integer[] likeHandle(String fundCode, String likeState, String userid) {
		logger.info("基金行情的赞同与反对操作:/nfundCode={}", fundCode);
		logger.info("likeState={}, userid={}", likeState, userid);

		// 判断用户是否已经登录
		if (StringUtils.isEmpty(userid)) {
			logger.warn("用户登录无效");
			return null;
		}

		// 验证两个参数是否为空
		if (StringUtils.isEmpty(fundCode) || StringUtils.isEmpty(likeState)) {
			logger.warn("参数都不能为空 fundCode={}, likeType={}", fundCode, likeState);
			return null;
		}

		// 查询该行情信息是否存在
		FundMarket fundMarket = fundMarketDao.queryDetail(fundCode);
		if (null == fundMarket) {
			logger.warn("基金行情信息不存在 fundCode={}", fundCode);
			return null;
		}

		// 一条行情一个用户的赞同和反对记录只有一条, 赞同和反对是单选的关系

		// 查询这个赞同或反对记录是否已经存在
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("fundCode", fundCode);
		queryMap.put("userid", userid);
		queryMap.put("likeType", "fundMarket");

		ProdLike prodLike = prodLikeDao.queryDetailByFundCode(queryMap);

		if (null == prodLike) { // 如果不存在赞同或反对信息, 则新增
			logger.info("新增赞同或反对信息");

			prodLike = new ProdLike();
			prodLike.setFundCode(fundCode);
			prodLike.setUserid(userid);
			prodLike.setLikeTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:Ss"));
			prodLike.setLikeState(likeState); // 点赞或反对: 0-赞同, 1-反对
			prodLike.setLikeType("fundMarket"); // 赞类型: /product
			prodLikeDao.insert(prodLike);
			logger.info("成功添加一条点赞记录");

			if ("0".equals(likeState)) { // 点赞
				// 更新赞同的人数和分数
				logger.info("更新赞同的人数和分数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("fundCode", fundCode);
				updateMap.put("likeSum", 1);
				updateMap.put("likeScore", 1);
				fundMarketDao.updateLikeInfo(updateMap);
				logger.debug("更新赞同的人数和分数成功");

				// 重新查询行情赞同的分数
				logger.info("重新查询行情赞同的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}

			if ("1".equals(likeState)) { // 反对
				// 更新反对的人数
				logger.info("更新反对的人数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("fundCode", fundCode);
				updateMap.put("unLikeSum", 1);
				updateMap.put("unLikeScore", 1);
				fundMarketDao.updateUnLikeInfo(updateMap);
				logger.debug("更新反对的人数和分数成功");

				// 重新查询行情反对的分数
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}
		} else { // 如果存在更新like记录
			logger.info("更新like记录");
			// 如果是赞同
			if ("0".equals(prodLike.getLikeState())) {
				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("用户重复操作");
					return null;
				}
				// 更新赞同类型为反对
				logger.info("更新赞同类型为反对");
				Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
				updateMarketLikeMap.put("fundCode", fundCode);
				updateMarketLikeMap.put("userid", userid);
				updateMarketLikeMap.put("likeState", "1");
				prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

				// 减少赞同人数及赞同分数
				logger.info("减少赞同人数及赞同分数");
				Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
				reduceFundInfoMap.put("fundCode", fundCode);
				reduceFundInfoMap.put("likeSum", -1);
				reduceFundInfoMap.put("likeScore", -1);
				fundMarketDao.updateLikeInfo(reduceFundInfoMap);

				// 增加反对人数及赞同分数
				logger.info("增加反对人数及赞同分数");
				Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
				plusFundInfoMap.put("fundCode", fundCode);
				plusFundInfoMap.put("unLikeSum", 1);
				plusFundInfoMap.put("unLikeScore", 1);
				fundMarketDao.updateUnLikeInfo(plusFundInfoMap);

				// 重新查询行情反对的分数
				logger.info("重新查询行情反对的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}

			// 如果是反对
			if ("1".equals(prodLike.getLikeState())) {
				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("用户重复操作");
					return null;
				}
				// 更新反对类型为赞同
				logger.info("更新反对类型为赞同");
				Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
				updateMarketLikeMap.put("fundCode", fundCode);
				updateMarketLikeMap.put("userid", userid);
				updateMarketLikeMap.put("likeState", "0");
				prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

				// 减少反对人数及赞同分数
				logger.info("减少反对人数及赞同分数");
				Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
				plusFundInfoMap.put("fundCode", fundCode);
				plusFundInfoMap.put("unLikeSum", -1);
				plusFundInfoMap.put("unLikeScore", -1);
				fundMarketDao.updateUnLikeInfo(plusFundInfoMap);

				// 增加赞同人数及赞同分数
				logger.info("增加赞同人数及赞同分数");
				Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
				reduceFundInfoMap.put("fundCode", fundCode);
				reduceFundInfoMap.put("likeSum", 1);
				reduceFundInfoMap.put("likeScore", 1);
				fundMarketDao.updateLikeInfo(reduceFundInfoMap);

				// 重新查询行情反对的分数
				logger.info("重新查询行情反对的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}
		}

		return null;
	}

}
