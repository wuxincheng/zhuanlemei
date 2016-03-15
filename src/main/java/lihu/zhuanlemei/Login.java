package lihu.zhuanlemei;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import lihu.zhuanlemei.util.Validation;

/**
 * 登录
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午6:15:26 
 *
 */
@Controller
@RequestMapping("/login")
public class Login extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(Login.class);
	
	@Resource
	private UserService userService;
	
	@Resource 
	private WechatHttpsHelper wechatHttpsHelper;
	
	@RequestMapping(value = "/")
	public String login(Model model, HttpServletRequest request) {
		logger.info("显示用户登录授权页面");
		
		requestMessageProcess(request);
		
		// 初始化第三方授权登录
		
		// 微信
		
		// 获取微信登录二维码地址
		String sessionid = request.getSession().getId();
		logger.debug("获取用户浏览器Session sessionid={}", sessionid);
		
		String wechatOAuthJSURI = wechatHttpsHelper.getOAuthLoginURI(sessionid, Constants.CLIENT_PC);
		logger.debug("登录二维码地址 wechatOAuthJSURI={}", wechatOAuthJSURI);
		
		model.addAttribute("wechatOAuthJSURI", wechatOAuthJSURI);
		
		// QQ（目前没有需求）
		
		// 新浪微博（目前没有需求）
		
		return "login";
	}
	
	@RequestMapping(value = "/doLogin")
	public String doLogin(Model model, HttpServletRequest request, User user) {
		logger.info("用户登录 loginEmail={}", user.getLoginEmail());
		
		// 验证登录信息
		if (Validation.isBlank(user.getLoginEmail()) || Validation.isBlank(user.getPassword())) {
			model.addAttribute(Constants.MSG_WARN, "用户邮箱和密码都不能为空");
			return "redirect:/login/";
		}
		
		User userFlag = userService.checkLogin(user.getLoginEmail());
		
		if (null == userFlag) {
			model.addAttribute(Constants.MSG_WARN, "用户邮箱不存在！");
			return "redirect:/login/";
		}
		
		String passwordFlag = userFlag.getPassword(); // 数据库中的密码
		
		// 登录密码加密
		String adminsPwdMD5Str = MD5.encryptMD5Pwd(user.getPassword());
		
		if (!Validation.isBlank(passwordFlag) && passwordFlag.equals(adminsPwdMD5Str)) {
			request.getSession().setAttribute(Constants.CURRENT_USER, userFlag);
		} else {
			model.addAttribute(Constants.MSG_WARN, "用户密码不正确");
			return "redirect:/login/";
		}
		
		model.addAttribute(Constants.MSG_SUCCESS, "登录成功");
		
		return "redirect:/index";
	}
	
}
