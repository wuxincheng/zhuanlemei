package lihu.zhuanlemei.oauth.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lihu.zhuanlemei.oauth.config.WechatConfig;
import lihu.zhuanlemei.util.Constants;
import lihu.zhuanlemei.util.JSONUtil;

/**
 * 微信Https请求
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月27日 下午10:13:00
 * 
 */
@Component
public class WechatHttpsHelper {
	private static final Logger logger = LoggerFactory.getLogger(WechatHttpsHelper.class);

	@Resource
	WechatConfig wechatConfig;

	/**
	 * 根据sessionid获取微信登录地址
	 * 
	 * @param sessionid
	 * @return
	 */
	public String getOAuthLoginURI(String sessionid, String clientType) {
		logger.info("根据sessionid获取微信登录地址 sessionid={}", sessionid);

		String redirectUrl = null, appid = null, wechatOAuthUrl = null;
		if (Constants.CLIENT_MOBILE.equals(clientType)) { // 微信公众平台
			appid = wechatConfig.getMpAppid();
			redirectUrl = wechatConfig.getMobileRedirectUrl();
			wechatOAuthUrl = wechatConfig.getWechatOAuthorize();
			logger.info("微信公众平台授权 appid={} redirectUrl={}", appid, redirectUrl);
		} else { // 微信开放平台
			redirectUrl = wechatConfig.getRedirectUrl();
			appid = wechatConfig.getOpenAppid();
			wechatOAuthUrl = wechatConfig.getWechatOAuthUrl();
			logger.info("微信开放平台授权 appid={} redirectUrl={}", appid, redirectUrl);
		}

		// 微信登录后返回的地址URLEncoder
		String redirectURLEncoder = null;
		try {
			redirectURLEncoder = URLEncoder.encode(redirectUrl, "UTF-8");
			logger.debug("URLEncoder后的返回地址 redirectURLEncoder={}", redirectURLEncoder);
		} catch (UnsupportedEncodingException e) {
			logger.error("URLEncoder返回地址异常", e);
		}

		// 微信授权登录页面
		String wechatOAuthFullUrl = wechatOAuthUrl.replaceAll("APPID", appid)
				.replaceAll("REDIRECT_URI", redirectURLEncoder).replaceAll("STATE", sessionid);
		logger.info("微信授权登录地址 wechatOAuthFullUrl={}", wechatOAuthFullUrl);

		return wechatOAuthFullUrl;
	}

	/**
	 * 根据sessionid获取微信JS登录地址
	 * 
	 * @param sessionid
	 * @return
	 */
	public String getOAuthJSLoginURI(String sessionid) {
		logger.info("根据sessionid获取微信JS登录地址 sessionid={}", sessionid);

		// 微信登录后返回的地址URLEncoder
		String redirectURLEncoder = null;
		try {
			redirectURLEncoder = URLEncoder.encode(wechatConfig.getRedirectUrl(), "UTF-8");
			logger.debug("URLEncoder后的返回地址 redirectURLEncoder={}", redirectURLEncoder);
		} catch (UnsupportedEncodingException e) {
			logger.error("URLEncoder返回地址异常", e);
		}

		// 微信授权登录页面
		String wechatOAuthUrl = wechatConfig.getWechatOAuthJSUrl().replaceAll("APPID", wechatConfig.getOpenAppid())
				.replaceAll("REDIRECT_URI", redirectURLEncoder).replaceAll("STATE", sessionid)
				.replaceAll("QCODE_STYLE", wechatConfig.getWechatQcodeStyle());
		logger.info("微信授权登录地址 wechatOAuthUrl={}", wechatOAuthUrl);

		return wechatOAuthUrl;
	}

	/**
	 * 根据code获取access_token
	 */
	public Map<String, Object> getAccessTokenByCode(String accessCode, String clientType) {
		logger.info("根据code获取access_token值 code={}", accessCode);
		if (StringUtils.isEmpty(accessCode)) {
			logger.warn("获取信息失败，参数不能为空");
			return null;
		}

		String appSecret = null, appid = null;
		if (Constants.CLIENT_MOBILE.equals(clientType)) { // 微信公众平台
			appSecret = wechatConfig.getMpAppSecret();
			appid = wechatConfig.getMpAppid();
		} else { // 微信开放平台
			appSecret = wechatConfig.getOpenAppSecret();
			appid = wechatConfig.getOpenAppid();
		}

		// 通过code获取access_token的URL
		String accessTokenUrl = wechatConfig.getAccessTokenUrl().replaceAll("APPID", appid)
				.replaceAll("APPSECRET", appSecret).replaceAll("CODE", accessCode);
		logger.info("请求的URL地址 accessTokenUrl={}", accessTokenUrl);

		String response = null;
		try {
			response = HttpsConnection.doGet(accessTokenUrl);
			logger.debug("获取access_token请求发送成功");
		} catch (Exception e) {
			logger.error("获取access_token请求出现异常", e);
		}

		// 处理返回数据
		Map<String, Object> responseMap = JSONUtil.parseResponseMap(response);
		logger.info("获取access_token请求返回 responseMap={}", responseMap);

		return responseMap;
	}

	/**
	 * 获取个人用户信息（UnionID机制）
	 */
	public Map<String, Object> getUserInfoUnionID(String accessToken, String openid) {
		logger.info("获取微信用户个人信息 accessToken={}, openid={}", accessToken, openid);
		if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openid)) {
			logger.warn("获取微信信息失败，参数不能为空");
			return null;
		}

		String userInfoUrl = wechatConfig.getUserInfoUrl().replaceAll("ACCESS_TOKEN", accessToken)
				.replaceAll("OPENID", openid);
		logger.info("请求微信的URL地址 userInfoUrl={}", userInfoUrl);

		String response = null;
		try {
			response = HttpsConnection.doGet(userInfoUrl);
			logger.debug("获取微信用户个人信息请求发送成功");
		} catch (Exception e) {
			logger.error("获取微信用户个人信息请求出现异常", e);
		}

		// 处理返回数据
		Map<String, Object> responseMap = JSONUtil.parseResponseMap(response);
		logger.info("获取微信用户个人信息请求返回 responseMap={}", responseMap);

		return responseMap;
	}

}
