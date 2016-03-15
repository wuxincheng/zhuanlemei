package lihu.zhuanlemei.mobile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.Result;
import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.model.User;
import lihu.zhuanlemei.oauth.helper.WechatHttpsHelper;
import lihu.zhuanlemei.service.UserService;
import lihu.zhuanlemei.util.Constants;
import lihu.zhuanlemei.util.MD5;
import lihu.zhuanlemei.util.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录
 * 
 * @author wuxincheng(wxcking)
 * 
 * @Date 2016年1月26日 下午4:59:47
 * 
 */
@Controller
@RequestMapping("/mobile/login")
public class MobileLogin extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MobileLogin.class);

	@Resource
	private UserService userService;

	@Resource
	private WechatHttpsHelper wechatHttpsHelper;

	@RequestMapping(value = "/")
	public String login(Model model, HttpServletRequest request) {
		logger.info("显示用户登录授权页面");
		return "mobile/login";
	}

	@RequestMapping(value = "/submit")
	@ResponseBody
	public Result submit(Model model, HttpServletRequest request, User user) {
		logger.info("移动端用户登录 loginEmail={}", user.getLoginEmail());

		Result result = new Result();

		// 验证登录信息
		if (Validation.isBlank(user.getLoginEmail()) || Validation.isBlank(user.getPassword())) {
			return result.reject("邮箱和密码都不能为空");
		}

		User userFlag = userService.checkLogin(user.getLoginEmail());

		if (null == userFlag) {
			return result.reject("用户邮箱不存在");
		}

		String passwordFlag = userFlag.getPassword(); // 数据库中的密码

		// 登录密码加密
		String adminsPwdMD5Str = MD5.encryptMD5Pwd(user.getPassword());

		if (!Validation.isBlank(passwordFlag) && passwordFlag.equals(adminsPwdMD5Str)) {
			request.getSession().setAttribute(Constants.CURRENT_USER, userFlag);
		} else {
			return result.reject("用户密码不正确");
		}

		return result.redirect("/mobile/collect/list");
	}
	
	@RequestMapping(value = "/wechat")
	public String loginWechat(Model model, HttpServletRequest request) {
		logger.info("显示微信扫描二维码授权页面");
		// 获取微信登录二维码地址
		String sessionid = request.getSession().getId();
		logger.debug("获取用户浏览器Session sessionid={}", sessionid);
		
		String wechatOAuthJSURI = wechatHttpsHelper.getOAuthLoginURI(sessionid);
		logger.debug("登录二维码地址 wechatOAuthJSURI={}", wechatOAuthJSURI);
		
		model.addAttribute("wechatOAuthJSURI", wechatOAuthJSURI);
		
		return "mobile/login/wechat";
	}

}
