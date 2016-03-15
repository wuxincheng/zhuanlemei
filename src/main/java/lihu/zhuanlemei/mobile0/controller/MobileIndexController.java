package lihu.zhuanlemei.mobile0.controller;

import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.service.CollectService;
import lihu.zhuanlemei.service.FundMarketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 移动端首页
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年12月21日 上午8:14:00
 * 
 */
@Controller("mobile0IndexController")
@RequestMapping("/mobile0")
public class MobileIndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MobileIndexController.class);

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CollectService collectService;

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {
		logger.info("移动端首页显示");

		// 目前移动端无设置首页, 跳转到榜单页面
		return "redirect:/mobile0/collect/list";
	}

}
