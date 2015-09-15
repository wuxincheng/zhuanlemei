package com.wuxincheng.zhuanlemei.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.CollectDao;
import com.wuxincheng.zhuanlemei.dao.ProductDao;
import com.wuxincheng.zhuanlemei.model.Collect;
import com.wuxincheng.zhuanlemei.model.Product;
import com.wuxincheng.zhuanlemei.util.ColorUtil;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.DateUtil;
import com.wuxincheng.zhuanlemei.util.ImageUtil;
import com.wuxincheng.zhuanlemei.util.Validation;

@Service("collectService")
public class CollectService {
	private static final Logger logger = LoggerFactory.getLogger(CollectService.class);

	@Resource
	private CollectDao collectDao;

	@Resource
	private ProductDao productDao;

	/**
	 * 查询所有产品集
	 */
	public List<Collect> queryAll() {
		return collectDao.queryAll();
	}

	/**
	 * 根据产品集主键查询详细
	 */
	public Collect queryDetailByCollectid(String collectid) {
		if (StringUtils.isEmpty(collectid)) {
			return null;
		}
		return collectDao.queryDetailByCollectid(collectid);
	}

	/**
	 * 查询用户发布的榜单
	 */
	public List<Collect> queryByUserid(String userid) {
		if (StringUtils.isEmpty(userid)) {
			return null;
		}
		return collectDao.queryByUserid(userid);
	}

	/**
	 * 查询榜单排名前3名
	 * 
	 * @param i
	 * @return
	 */
	public List<Collect> queryTopHot(Integer topLimit) {
		return collectDao.queryTopHot(topLimit);
	}

	/**
	 * 创建或更新产品集（现命名为榜单）
	 */
	public String createOrUpdate(Collect collect, String ctxPath, String userid) {
		logger.info("创建或更新产品集(榜单)");
		String responseMessage = null;

		// 当前时间
		String currentDate = DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:ss");

		if (collect.getCollectid() != null) { // 更新榜单信息
			logger.info("更新榜单信息");

			if (StringUtils.isEmpty(userid)) {
				responseMessage = "Session异常：用户id为空";
				logger.warn(responseMessage);
				return responseMessage;
			}

			Collect queryCollect = collectDao.queryDetailByCollectid(collect.getCollectid());
			logger.debug("查询出要更新的榜单信息");

			if (!userid.equals(queryCollect.getUserid())) {
				responseMessage = "您无权限修改非您创建的榜单";
				logger.warn(responseMessage);
				return responseMessage;
			}

			logger.debug("验证榜单更新信息");
			responseMessage = checkAndProcessCollectInfo(collect, queryCollect, ctxPath);
			if (StringUtils.isNotEmpty(responseMessage)) {
				logger.debug("榜单信息验证失败");
				logger.info(responseMessage);
				return responseMessage;
			}

			// 删除之前的封面图片

			queryCollect.setCollectName(collect.getCollectName());
			queryCollect.setCoverImgPath(collect.getCoverImgPath());
			queryCollect.setMemo(collect.getMemo());
			queryCollect.setRecommend(collect.getRecommend());
			queryCollect.setUpdateTime(currentDate);

			collectDao.update(queryCollect);
		} else { // 新增榜单信息
			logger.info("新增榜单信息");

			logger.debug("验证榜单更新信息");
			responseMessage = checkAndProcessCollectInfo(collect, null, ctxPath);
			if (StringUtils.isNotEmpty(responseMessage)) {
				logger.debug("榜单信息验证失败");
				logger.info(responseMessage);
				return responseMessage;
			}

			collect.setCollectSum(0);
			collect.setProductSum(0);
			collect.setCreateTime(currentDate);
			collect.setUpdateTime(currentDate);
			collect.setUpdateState(Constants.DEFAULT_STATE);
			collect.setCollectState(Constants.DEFAULT_STATE);
			collect.setUserid(userid);
			/* 新增评论总数和赞同反对 */
			collect.setLikeSum(0);
			collect.setLikeScore(0);
			collect.setUnLikeSum(0);
			collect.setUnLikeScore(0);
			collect.setCommentSum(0);

			logger.debug("新增榜单");
			collectDao.create(collect);
			logger.info("新增榜单成功");
		}

		return responseMessage;
	}

	/**
	 * 删除榜单
	 */
	public String delete(String collectid, String userid) {
		logger.info("删除榜单 collectid={}", collectid);
		String responseMessage = null;

		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			responseMessage = "删除失败：collectid为空";
			logger.debug(responseMessage);
			return responseMessage;
		}

		// 查询榜单信息
		Collect queryCollect = collectDao.queryDetailByCollectid(collectid);
		logger.debug("查询出要删除的榜单信息");

		if (!userid.equals(queryCollect.getUserid())) {
			responseMessage = "您无权限删除非您创建的榜单";
			logger.warn(responseMessage);
			return responseMessage;
		}

		// 查询这个榜单中是否有发布的产品
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);

		logger.debug("查询该榜单是否存在产品信息");
		List<Product> products = productDao.queryProductsByCollectid(queryMap);
		if (products != null && products.size() > 0) {
			responseMessage = "删除失败：该榜单产品不为空";
			logger.debug(responseMessage);
			return responseMessage;
		}

		logger.debug("该榜单中的产品为空，可以删除");

		collectDao.delete(collectid);
		logger.debug("已删除");

		return responseMessage;
	}

	/**
	 * 验证参数并处理图片
	 */
	private String checkAndProcessCollectInfo(Collect collect, Collect queryCollect, String ctxPath) {
		String responseMessage = null;

		if (StringUtils.isEmpty(ctxPath)) {
			responseMessage = "榜单封面图片保存路径不能为空";
			logger.warn(responseMessage);
			return responseMessage;
		}

		if (null == collect) {
			responseMessage = "榜单数据不能为空";
			logger.warn(responseMessage);
			return responseMessage;
		}

		// 验证榜单名称和说明是否为空
		if (StringUtils.isEmpty(collect.getCollectName())) {
			responseMessage = "榜单名称不能为空";
			return responseMessage;
		}
		if (StringUtils.isEmpty(collect.getMemo())) {
			responseMessage = "榜单说明不能为空";
			return responseMessage;
		}
		if (collect.getCollectName().length() > 15 || collect.getCollectName().length() < 4) {
			responseMessage = "榜单名称长度不合法，应在4到15位之间";
			return responseMessage;
		}
		if (collect.getMemo().length() > 36 || collect.getMemo().length() < 5) {
			responseMessage = "一句话介绍长度不合法，应在5到36位之间";
			return responseMessage;
		}
		if (StringUtils.isNotEmpty(collect.getRecommend())) {
			if (collect.getRecommend().length() > 250) {
				responseMessage = "内容介绍长度过长，不能超过250个字";
				return responseMessage;
			}
		}

		logger.debug("开始验证榜单封面图片");

		// 验证是否上传了图片
		if (null == collect.getCoverImgFile() || collect.getCoverImgFile().getSize() < 1) {
			// 如果没有上传图片, 则随机添加一个背景色
			collect.setBgColor(ColorUtil.getBgColorRandom());
			return null;
		}

		// 控制图片大小不能大于3M
		if (collect.getCoverImgFile().getSize() > 5 * 1024 * 1024) {
			logger.info("榜单背景图片不能超过3M size={}", collect.getCoverImgFile().getSize());
			responseMessage = "榜单背景图片不能超过3M";
			return responseMessage;
		}

		// 判断是否更新了图片：隐藏域图片的名称和显示图片的名称进行比较
		if (StringUtils.isNotEmpty(collect.getCoverImgPathHidden())) { // 有值说明是更新图片
			if (collect.getCoverImgPathHidden().equals(collect.getCoverImgPath())) {
				// 不更新图片
				logger.debug("没有更新榜单封面图片");
				return responseMessage;
			}
		}

		// 删除原有的封面图片
		if (queryCollect != null) {
			File resourceCoverImgFile = new File(ctxPath + queryCollect.getCoverImgPath());
			if (resourceCoverImgFile.delete()) {
				logger.debug("原封面图片删除成功");
			} else {
				logger.warn("原封面图片删除失败");
				logger.debug("原封面图片名称 coverImgPath={}", queryCollect.getCoverImgPath());
			}
		}

		// 验证图片的格式(只支持png、jpg格式)
		String checkFileName = collect.getCoverImgFile().getOriginalFilename();
		String lastFix = checkFileName.substring(checkFileName.lastIndexOf("."), checkFileName.length());
		if (!".png|.jpg".contains(lastFix)) {
			logger.info("榜单背景图片仅支持png、jpg格式 lastFix={}", lastFix);
			responseMessage = "榜单背景图片仅支持png、jpg格式";
			return responseMessage;
		}

		// 生成图片名称
		logger.debug("图片存放路径 ctxPath={}", ctxPath);
		String coverImgPath = System.currentTimeMillis() + lastFix;
		logger.info("封面图片 coverImgPath={}", coverImgPath);

		logger.debug("开始保存图片");
		// 保存图片到服务器
		ImageUtil.saveFile(ctxPath, coverImgPath, collect.getCoverImgFile());
		// 设置Collect对中图片存储的路径
		collect.setCoverImgPath(coverImgPath);
		logger.info("封面图片存储成功");

		return responseMessage;
	}

}
