package lihu.zhuanlemei.mobile.oauth.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lihu.zhuanlemei.model.User;
import lihu.zhuanlemei.oauth.helper.WechatHttpsHelper;
import lihu.zhuanlemei.service.UserService;
import lihu.zhuanlemei.util.Constants;
import lihu.zhuanlemei.util.MapFormatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信登录验证
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月24日 上午10:31:37
 * 
 */
@Controller
@RequestMapping("/mobile/oauth/wechat")
public class WechatMobileLoginController {
	private static final Logger logger = LoggerFactory.getLogger(WechatMobileLoginController.class);

	@Resource
	private WechatHttpsHelper wechatHttpsHelper;

	@Resource
	private UserService userService;

	/**
	 * 跳转到网站微信授权页面
	 */
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		logger.info("跳转到网站微信授权页面");

		response.setContentType("text/html;charset=utf-8");

		try {
			String sessionid = request.getSession().getId();
			String wechatOAuthUrl = wechatHttpsHelper.getOAuthLoginURI(sessionid);
			logger.debug("微信授权页面wechatOAuthUrl={}", wechatOAuthUrl);
			response.sendRedirect(wechatOAuthUrl); // 跳转到微信登录授权页面
			logger.debug("已跳转到微信授权页面");
		} catch (Exception e) {
			logger.error("连接登录微信异常", e);
		}
	}

	/**
	 * 跳转到微信内部授权页面
	 */
	@RequestMapping(value = "/login/self")
	public void loginSelf(HttpServletRequest request, HttpServletResponse response) {
		logger.info("跳转到微信内部授权页面");

		response.setContentType("text/html;charset=utf-8");

		try {
			String sessionid = request.getSession().getId();
			String wechatOAuthUrl = wechatHttpsHelper.getOAuthInnerURI(sessionid);
			logger.debug("微信授权页面wechatOAuthUrl={}", wechatOAuthUrl);
			response.sendRedirect(wechatOAuthUrl); // 跳转到微信登录授权页面
			logger.debug("已跳转到微信授权页面");
		} catch (Exception e) {
			logger.error("连接登录微信异常", e);
		}
	}

	/**
	 * 用户授权后微信后台返回信息
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback(Model model, HttpServletRequest request) {
		logger.info("接收微信用户登录返回的信息");

		// 接收到自定义state参数和微信的Code
		String state = request.getParameter("state");
		logger.info("返回数据 state(sessionid)={}", state);

		if (StringUtils.isEmpty(state)) {
			logger.error("授权失败，Session为空");
			return "redirect:/mobile/index";
		}

		// 验证state参数
		/*
		 * if (!request.getSession().getId().equals(state)) {
		 * logger.error("授权失败，不合法的Session"); return "redirect:/index"; }
		 */

		String code = request.getParameter("code");
		logger.info("返回数据 code={}", code);

		// 若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数
		if (StringUtils.isEmpty(code)) {
			logger.error("授权失败，取消了微信登录操作");
			return "redirect:/mobile/index";
		}

		logger.debug("开始获取 access_token");
		// 通过code获取access_token
		Map<String, Object> responseMap = wechatHttpsHelper.getAccessTokenByCode(code);
		if (null == responseMap) {
			model.addAttribute(Constants.MSG_WARN, "获取微信AccessToken失败");
			return "redirect:/mobile/index";
		}

		logger.debug("开始获取用户个人信息");
		Map<String, Object> responseUserInfoMap = wechatHttpsHelper.getUserInfoUnionID(
				MapFormatUtil.getString(responseMap, "access_token"),
				MapFormatUtil.getString(responseMap, "openid"));

		logger.info("个人信息获取成功 responseUserInfoMap={}", responseUserInfoMap);

		logger.info("开始保存用户数据");
		// 保存用户数据
		User oauthUser = new User();
		oauthUser.setNickName(MapFormatUtil.getString(responseUserInfoMap, "nickname"));
		oauthUser.setSocialPicPath(MapFormatUtil.getString(responseUserInfoMap, "headimgurl"));
		oauthUser.setAccessToken(MapFormatUtil.getString(responseMap, "access_token"));
		oauthUser.setTokenExpireIn(code);
		oauthUser.setOpenid(MapFormatUtil.getString(responseUserInfoMap, "openid"));
		oauthUser.setLoginType(Constants.OAUTH_WECHAT);
		oauthUser.setUnionid(MapFormatUtil.getString(responseUserInfoMap, "unionid"));
		oauthUser.setSex(MapFormatUtil.getInt(responseUserInfoMap, "sex")+"");
		logger.info("保存数据已封装");
		checkAndProcessOAuthUser(oauthUser, request);

		logger.info("微信授权登录成功");

		return "redirect:/mobile/index";
	}

	/**
	 * 处理用户登录信息
	 */
	private void checkAndProcessOAuthUser(User oauthUser, HttpServletRequest request) {
		// 验证是否已经在库中有记录，如果有记录更新，没记录新增
		logger.info("验证用户");
		User checkUser = userService.validateOAuthUser(oauthUser);

		// 用户信息放入在Session中
		request.getSession().setAttribute(Constants.MOBILE_USER, checkUser);
		logger.info("用户授权成功");
	}

}
