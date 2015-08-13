package com.wuxincheng.zhuanlemei.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.zhuanlemei.controller.BaseController;
import com.wuxincheng.zhuanlemei.model.Collect;
import com.wuxincheng.zhuanlemei.service.CollectService;
import com.wuxincheng.zhuanlemei.service.ProductService;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.Validation;

/**
 * 我的榜单（相当于榜单的后台管理）
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月10日 下午10:33:22 
 *
 */
@Controller
@RequestMapping("/my/collects")
public class MyCollectsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MyCollectsController.class);
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示我的榜单列表");
		
		requestMessageProcess(request);
		
		String userid = getCurrentUserid(request);
		
		List<Collect> collects = collectService.queryByUserid(userid);
		
		// List<Collect> collects = collectService.queryAll();
		
		model.addAttribute("collects", collects);
		
		return "my/collects/list";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request, String collectid) {
		logger.info("显示我的榜单修改页面 collectid={}", collectid);

		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			logger.debug("修改页面显示失败：collectid为空");
			model.addAttribute(Constants.MSG_WARN, "修改页面显示失败：collectid为空");
			return "redirect:list";
		}
		
		Collect collect = collectService.queryDetailByCollectid(collectid);
		
		model.addAttribute("collect", collect);
		
		return "my/collects/edit";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(Model model, HttpServletRequest request, Collect collect) {
		logger.info("处理榜单数据");

		try {
			String ctxPath = request.getSession().getServletContext().getRealPath("/") + "collect/coverbg/"; 
			String userid = getCurrentUserid(request);
			
			String response = collectService.createOrUpdate(collect, ctxPath, userid);
			
			if (!StringUtils.isEmpty(response)) {
				logger.warn("数据处理失败：{}", response);
				model.addAttribute(Constants.MSG_WARN, "数据处理失败："+response);
				return "redirect:list";
			}
			
			logger.info("榜单数据处理成功");
			model.addAttribute(Constants.MSG_SUCCESS, "榜单数据处理成功");
		} catch (Exception e) {
			logger.error("榜单数据处理出现异常", e);
			model.addAttribute(Constants.MSG_ERROR, "榜单数据处理出现异常，请联系管理员");
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, HttpServletRequest request, String collectid) {
		logger.info("删除榜单 collectid={}", collectid);
		
		String userid = getCurrentUserid(request);
		if (StringUtils.isEmpty(userid)) {
			model.addAttribute(Constants.MSG_WARN, "用户登录失效，请重新登录");
			return "redirect:list";
		}
		
		String responseMessage = collectService.delete(collectid, userid);
		
		if (!StringUtils.isEmpty(responseMessage)) {
			model.addAttribute(Constants.MSG_WARN, "删除失败："+responseMessage);
			logger.debug("删除失败：{}", responseMessage);
			return "redirect:list";
		}
		
		logger.info("删除成功");
		
		model.addAttribute(Constants.MSG_INFO, "删除成功");
		
		return "redirect:list";
	}
	
}
