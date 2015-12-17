package lihu.zhuanlemei;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.model.User;
import lihu.zhuanlemei.oauth.helper.WechatHttpsHelper;
import lihu.zhuanlemei.service.UserService;
import lihu.zhuanlemei.util.Constants;
import lihu.zhuanlemei.util.MD5;
import lihu.zhuanlemei.util.StringUtil;
import lihu.zhuanlemei.util.Validation;

/**
 * 用户注册
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午7:30:49 
 *
 */
@Controller
@RequestMapping("/register")
public class Register extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(Register.class);
	
	@Resource
	private UserService userService;
	
	@Resource 
	private WechatHttpsHelper wechatHttpsHelper;
	
	@RequestMapping(value = "/")
	public String login(Model model, HttpServletRequest request) {
		logger.info("显示用户注册授权页面");
		
		requestMessageProcess(request);
		
		// 获取微信登录二维码地址
		String sessionid = request.getSession().getId();
		logger.debug("获取用户浏览器Session sessionid={}", sessionid);
		
		String wechatOAuthJSURI = wechatHttpsHelper.getOAuthLoginURI(sessionid);
		logger.debug("登录二维码地址 wechatOAuthJSURI={}", wechatOAuthJSURI);
		
		model.addAttribute("wechatOAuthJSURI", wechatOAuthJSURI);
		
		return "register";
	}
	
	@RequestMapping(value = "/doRegister")
	public String doRegister(Model model, HttpServletRequest request, User user) {
		logger.info("用户注册 user={}", StringUtil.toStringMultiLine(user));
		
		// 验证用户注册信息
		String responseValidateMsg = validateUserInfo(user);
		if (StringUtils.isNotEmpty(responseValidateMsg)) {
			model.addAttribute(Constants.MSG_WARN, responseValidateMsg);
			return "redirect:/register/";
		}
		
		// 登录密码加密
		user.setPassword(MD5.encryptMD5Pwd(user.getPassword()));
		userService.register(user);
		
		model.addAttribute(Constants.MSG_SUCCESS, "注册成功");
		
		user = userService.checkLogin(user.getLoginEmail());
		request.getSession().setAttribute(Constants.CURRENT_USER, user);
		
		return "redirect:/index";
	}

	/**
	 * 验证用户注册信息
	 */
	private String validateUserInfo(User user) {
		String responseValidateMsg = null;
		// 验证空
		if (Validation.isBlank(user.getLoginEmail()) || Validation.isBlank(user.getPassword())
				|| Validation.isBlank(user.getPassword2()) || Validation.isBlank(user.getNickName())) {
			responseValidateMsg = "所有内容都不能为空";
			return responseValidateMsg;
		}
		
		// 验证邮箱的格式
		if (!Validation.checkEmail(user.getLoginEmail())) {
			responseValidateMsg = "邮箱格式不正确";
			return responseValidateMsg;
		}
		
		// 昵称长度(数据库中可重复)
		if (user.getNickName().length() < 4 || user.getNickName().length() > 10) {
			responseValidateMsg = "昵称长度不符合（4至10位）";
			return responseValidateMsg;
		}
		
		// 验证两次密码输入是否一样
		if (!user.getPassword().equals(user.getPassword2())) {
			responseValidateMsg = "两次密码输入的不一样";
			return responseValidateMsg;
		}
		
		// 验证邮箱长度
		if (user.getLoginEmail().length() < 10) {
			responseValidateMsg = "请输入有效的邮箱";
			return responseValidateMsg;
		}
		
		// 验证密码长度
		if (user.getPassword().length() < 6 || user.getPassword().length() > 24) {
			responseValidateMsg = "密码长度不符合（6至24位）";
			return responseValidateMsg;
		}
		
		// 验证邮箱的唯一性
		User userFlag = userService.checkLogin(user.getLoginEmail());
		if (userFlag != null) {
			responseValidateMsg = "用户邮箱已经存在！";
			return responseValidateMsg;
		}
		
		return responseValidateMsg;
	}
	
}
