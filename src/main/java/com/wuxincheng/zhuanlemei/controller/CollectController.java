package com.wuxincheng.zhuanlemei.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.zhuanlemei.model.Collect;
import com.wuxincheng.zhuanlemei.model.CollectUser;
import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.model.Product;
import com.wuxincheng.zhuanlemei.service.CollectService;
import com.wuxincheng.zhuanlemei.service.CollectUserService;
import com.wuxincheng.zhuanlemei.service.FundMarketService;
import com.wuxincheng.zhuanlemei.service.ProductService;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.Validation;

/**
 * 产品集，现更名为榜单
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月1日 下午10:30:45
 * 
 */
@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CollectController.class);

	@Autowired
	private CollectService collectService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CollectUserService collectUserService;

	@Autowired
	private FundMarketService fundMarketService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示榜单列表");

		requestMessageProcess(request);

		List<Collect> collects = collectService.queryAll();

		if (null == collects || collects.size() < 1) {
			model.addAttribute(Constants.MSG_INFO, "目前还没发布榜单");
			logger.debug("目前还没发布榜单");
		}

		request.setAttribute("collects", collects);

		return "collect/list";
	}

	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request) {
		logger.info("显示添加榜单页面");
		requestMessageProcess(request);

		// 判断用户是否有创建榜单权限
		if (!isCollectPermission(request)) {
			model.addAttribute(Constants.MSG_WARN, "您还没有该项权限");
			return "redirect:list";
		}

		request.setAttribute(Constants.CURRENT_USERID, getCurrentUserid(request));

		return "collect/edit";
	}

	@RequestMapping(value = "/create")
	public String create(Model model, HttpServletRequest request, Collect collect) {
		logger.info("添加新的榜单");

		String userid = getCurrentUserid(request);

		// 判断用户是否有创建榜单权限
		if (!isCollectPermission(request)) {
			model.addAttribute(Constants.MSG_WARN, "您还没有该项权限");
			return "redirect:edit";
		}

		// 图片存放路径
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "collect/coverbg/";
		logger.debug("图片存放路径 ctxPath={}", ctxPath);

		String responseMessage = collectService.createOrUpdate(collect, ctxPath, userid);
		if (StringUtils.isNotEmpty(responseMessage)) {
			model.addAttribute(Constants.MSG_WARN, "处理失败：" + responseMessage);
			logger.debug(responseMessage);
			return "redirect:edit";
		}

		logger.info("榜单创建成功");

		model.addAttribute(Constants.MSG_SUCCESS, "榜单创建成功");

		return "redirect:list";
	}

	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, String collectid) {
		logger.info("显示榜单 collectionid={}", collectid);

		// 提示信息显示
		requestMessageProcess(request);

		// 判断collectid
		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			logger.debug("详细显示失败：collectid为空");
			return "redirect:list";
		}

		// 是否存在这个榜单
		Collect collect = collectService.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.debug("详细显示失败：榜单不存在 collectid={}", collectid);
			return "redirect:list";
		}
		request.setAttribute("collect", collect);

		String userid = null;

		// 判断用户是否已经登录
		if (getCurrentUserid(request) != null) {
			// 如果登录，查询该用户是否已经收藏该榜单
			CollectUser collectUser = collectUserService.query(collectid, getCurrentUserid(request));
			request.setAttribute("collectUser", collectUser);
			userid = getCurrentUserid(request);
		}

		// 查询这个榜单下的所有产品
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);
		queryMap.put("userid", userid);
		List<Product> products = productService.queryProductsByCollectid(queryMap);

		// 关联查询所有基金信息
		List<FundMarket> fundMarkets = fundMarketService.queryByProducts(products);
		request.setAttribute("fundMarkets", fundMarkets);

		return "collect/detail";
	}

	/**
	 * 榜单收藏和取消收藏操作
	 * 
	 * @param collectid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/collect")
	public String collect(String collectid, String userid) {
		logger.info("榜单收藏和取消收藏操作 collectionid={} userid={}", collectid, userid);

		if (collectid != null && userid != null) {
			collectUserService.collect(collectid, userid);
			logger.debug("榜单收藏和取消收藏操作成功");
		} else {
			logger.debug("榜单收藏和取消收藏操作失败：collectid或userid为空");
		}

		return "redirect:/collect/detail?collectid=" + collectid;
	}

}
