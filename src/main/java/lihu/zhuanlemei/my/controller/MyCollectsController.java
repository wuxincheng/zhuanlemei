package lihu.zhuanlemei.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.model.Collect;
import lihu.zhuanlemei.service.CollectService;
import lihu.zhuanlemei.service.ProductService;
import lihu.zhuanlemei.util.Constants;
import lihu.zhuanlemei.util.Validation;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		if (null == collects || collects.size() < 1) {
			model.addAttribute(Constants.MSG_INFO, "您还没有创建过榜单");
		}
		
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
		
		return "collect/edit";
	}
	
	@RequestMapping(value = "/modify")
	@Deprecated
	public String modify(Model model, HttpServletRequest request, Collect collect) {
		logger.info("处理榜单数据");

		try {
			String ctxPath = request.getSession().getServletContext().getRealPath("/") + "imgbase/coverbg/"; 
			String userid = getCurrentUserid(request);
			
			String response = collectService.createOrUpdate(collect, ctxPath, userid);
			
			if (!StringUtils.isEmpty(response)) {
				logger.warn("数据处理失败：{}", response);
				model.addAttribute(Constants.MSG_WARN, "数据处理失败："+response);
				return "redirect:list";
			}
			
			logger.info("榜单数据修改成功");
			model.addAttribute(Constants.MSG_SUCCESS, "榜单修改成功");
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
		
		String responseMessage = collectService.delete(collectid, userid);
		
		if (!StringUtils.isEmpty(responseMessage)) {
			logger.debug("删除失败：{}", responseMessage);
			return "redirect:list";
		}
		
		logger.info("删除成功");
		
		return "redirect:list";
	}
	
}
