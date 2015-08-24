package com.wuxincheng.zhuanlemei.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundMarketDao;
import com.wuxincheng.zhuanlemei.dao.ProdLikeDao;
import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.model.ProdLike;
import com.wuxincheng.zhuanlemei.util.Constants;
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

	private static final String FUNDSFILTER_CACHE_NAME = "lterm";

	private static final String FUNDSFILTER_CACHE_KEY = "FUNDSFILTER";

	@Resource
	private FundMarketDao fundMarketDao;

	@Resource
	private ProdLikeDao prodLikeDao;

	@Resource
	private CacheManager cacheManager;

	/**
	 * 分页查询行情列表信息
	 * 
	 * @param queryParam
	 *            分页参数
	 * @param dataType
	 *            获取类型
	 * @return
	 */
	public Map<String, Object> queryPager(Map<String, Object> queryParam, String dataType) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<FundMarket> fundMarkets = null; // 查询的数据
		int totalCount = 0; // 查询出总记录数

		if (Constants.DATE_TYPE_DB.equals(dataType)) { // 直接访问数据库
			fundMarkets = fundMarketDao.queryPager(queryParam);
			totalCount = fundMarketDao.queryCount(queryParam); // 总记录数
		} else { // 从缓存中获取
			fundMarkets = getCacheFundMarkets();
			totalCount = fundMarkets.size(); // 总记录数据
			if ((Integer) queryParam.get("end") < totalCount) {
				fundMarkets = fundMarkets.subList((Integer) queryParam.get("start"),
						(Integer) queryParam.get("end"));
			} else {
				fundMarkets = fundMarkets.subList((Integer) queryParam.get("start"), totalCount);
			}
		}

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
				// 减少赞同人数及赞同分数
				logger.info("减少赞同人数及赞同分数");
				Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
				reduceFundInfoMap.put("fundCode", fundCode);
				reduceFundInfoMap.put("likeSum", -1);
				reduceFundInfoMap.put("likeScore", -1);
				fundMarketDao.updateLikeInfo(reduceFundInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消赞同操作");

					// 删除这条赞同记录
					logger.info("删除这条赞同记录");
					prodLikeDao.deleteFundLike(prodLike);
					logger.debug("赞同记录删除成功");
				} else {
					// 更新赞同记录

					// 更新赞同类型为反对
					logger.info("更新赞同类型为反对");
					Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
					updateMarketLikeMap.put("fundCode", fundCode);
					updateMarketLikeMap.put("userid", userid);
					updateMarketLikeMap.put("likeState", "1");
					prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

					// 增加反对人数及赞同分数
					logger.info("增加反对人数及赞同分数");
					Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
					plusFundInfoMap.put("fundCode", fundCode);
					plusFundInfoMap.put("unLikeSum", 1);
					plusFundInfoMap.put("unLikeScore", 1);
					fundMarketDao.updateUnLikeInfo(plusFundInfoMap);
				}

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
				// 减少反对人数及赞同分数
				logger.info("减少反对人数及赞同分数");
				Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
				plusFundInfoMap.put("fundCode", fundCode);
				plusFundInfoMap.put("unLikeSum", -1);
				plusFundInfoMap.put("unLikeScore", -1);
				fundMarketDao.updateUnLikeInfo(plusFundInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消反对操作");

					// 删除这条反对记录
					logger.info("删除这条反对记录");
					prodLikeDao.deleteFundLike(prodLike);
					logger.debug("反对记录删除成功");
				} else {
					// 更新反对类型为赞同
					logger.info("更新反对类型为赞同");
					Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
					updateMarketLikeMap.put("fundCode", fundCode);
					updateMarketLikeMap.put("userid", userid);
					updateMarketLikeMap.put("likeState", "0");
					prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

					// 增加赞同人数及赞同分数
					logger.info("增加赞同人数及赞同分数");
					Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
					reduceFundInfoMap.put("fundCode", fundCode);
					reduceFundInfoMap.put("likeSum", 1);
					reduceFundInfoMap.put("likeScore", 1);
					fundMarketDao.updateLikeInfo(reduceFundInfoMap);
				}

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

	/**
	 * 查询赞同排名
	 * 
	 * @return
	 */
	public List<FundMarket> queryTopRedMarkets(String dataType) {
		// 直接查询数据库获取数据
		if (Constants.DATE_TYPE_DB.equals(dataType)) {
			return fundMarketDao.queryTopRedMarkets();
		}

		// 验证是否使用缓存数据
		if (!Constants.DATE_TYPE_CACHE.equals(dataType)) {
			return null;
		}

		// 获取缓存数据
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		List<FundMarket> sortFundMarkets = new ArrayList<FundMarket>();
		sortFundMarkets.addAll(fundMarkets);

		// 排序
		Collections.sort(sortFundMarkets, new Comparator<FundMarket>() {
			@Override
			public int compare(FundMarket fund1, FundMarket fund2) {
				return fund2.getLikeScore() == fund1.getLikeScore() ? 0
						: (fund2.getLikeScore() > fund1.getLikeScore() ? 1 : -1);
			}
		});

		return sortFundMarkets.subList(0, 5);
	}

	/**
	 * 查询反对排名
	 * 
	 * @return
	 */
	public List<FundMarket> queryTopGreenMarkets(String dataType) {
		// 直接查询数据库获取数据
		if (Constants.DATE_TYPE_DB.equals(dataType)) {
			return fundMarketDao.queryTopGreenMarkets();
		}

		// 验证是否使用缓存数据
		if (!Constants.DATE_TYPE_CACHE.equals(dataType)) {
			return null;
		}

		// 获取缓存数据
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		List<FundMarket> sortFundMarkets = new ArrayList<FundMarket>();
		sortFundMarkets.addAll(fundMarkets);

		// 排序
		Collections.sort(sortFundMarkets, new Comparator<FundMarket>() {
			@Override
			public int compare(FundMarket fund1, FundMarket fund2) {
				return fund2.getUnLikeScore() == fund1.getUnLikeScore() ? 0 : (fund2
						.getUnLikeScore() > fund1.getUnLikeScore() ? 1 : -1);
			}
		});

		return sortFundMarkets.subList(0, 5);
	}

	/**
	 * 根据关键字查询基金信息
	 * 
	 * @param keyword
	 *            关键字fundCode或fundName
	 * @return
	 */
	public List<Map<String, String>> queryFunds(String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}

		// 存储查询基金信息
		List<Map<String, String>> funds = new ArrayList<Map<String, String>>();

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();

		for (FundMarket fundMarket : fundMarkets) {
			// 只存储基金名称(fundName)和基金代码(fundCode)
			Map<String, String> fund = new HashMap<String, String>();
			// 过滤基金代码fundCode
			if (StringUtils.isNotEmpty(fundMarket.getFundCode())) {
				if (fundMarket.getFundCode().indexOf(keyword) >= 0) {
					fund.put("fundCode", fundMarket.getFundCode());
					fund.put("fundName", fundMarket.getFundName());
					funds.add(fund);
					continue;
				}
			}

			// 过滤基金名称fundName
			if (StringUtils.isNotEmpty(fundMarket.getFundName())) {
				if (fundMarket.getFundName().indexOf(keyword) >= 0) {
					fund.put("fundCode", fundMarket.getFundCode());
					fund.put("fundName", fundMarket.getFundName());
					funds.add(fund);
					continue;
				}
			}
		}

		return funds;
	}

	/**
	 * 从缓存中获取基金行情信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> getCacheFundMarkets() {
		Cache cache = cacheManager.getCache(FUNDSFILTER_CACHE_NAME);
		ValueWrapper valueWrapper = cache.get(FUNDSFILTER_CACHE_KEY);
		if (valueWrapper != null) {
			logger.debug("从缓存中读取基金行情 cacheName={} cacheKey={}", FUNDSFILTER_CACHE_NAME,
					FUNDSFILTER_CACHE_KEY);
			return (List<FundMarket>) valueWrapper.get();
		}

		List<FundMarket> fundMarkets = fundMarketDao.queryAll();

		cache.put(FUNDSFILTER_CACHE_KEY, fundMarkets);
		logger.info("读取基金行情并放入缓存 cacheName={} cacheKey={}", FUNDSFILTER_CACHE_NAME,
				FUNDSFILTER_CACHE_KEY);

		return fundMarkets;
	}

}
