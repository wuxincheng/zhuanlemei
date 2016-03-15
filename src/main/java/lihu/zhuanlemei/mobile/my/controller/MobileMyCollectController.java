package lihu.zhuanlemei.mobile.my.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.model.Collect;
import lihu.zhuanlemei.model.CollectUser;
import lihu.zhuanlemei.service.CollectService;
import lihu.zhuanlemei.service.CollectUserService;
import lihu.zhuanlemei.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户中心：个人收藏（收藏的榜单）
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月3日 下午5:34:03
 * 
 */
@Controller
@RequestMapping("/mobile/my/collect")
public class MobileMyCollectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MobileMyCollectController.class);

	@Autowired
	private CollectUserService collectUserService;

	@Autowired
	private CollectService collectService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示个人收藏");
		model.addAttribute("menu_name", "mycollect");

		// 获取当前用户
		String userid = getCurrentMobileUserid(request);
		if (StringUtils.isEmpty(userid)) {
			model.addAttribute(Constants.MSG_WARN, "用户登录信息失效");
			return "redirect:/mobile/login/";
		}

		// 查询用户已经收藏的产品集
		List<CollectUser> collectUsers = collectUserService.queryCollects(userid);

		// 如果没有收藏，返回空
		if (null == collectUsers || collectUsers.size() < 1) {
			model.addAttribute(Constants.MSG_WARN, "您还没有收藏任何榜单");
			return "mobile/collect/list";
		}

		// 查询产品集信息
		List<Collect> collects = new ArrayList<Collect>();
		for (CollectUser collectUser : collectUsers) {
			collects.add(collectService.queryDetailByCollectid(collectUser.getCollectid() + ""));
		}

		request.setAttribute("collects", collects);

		return "mobile/collect/list";
	}

}
