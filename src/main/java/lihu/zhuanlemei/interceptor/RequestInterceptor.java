package lihu.zhuanlemei.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lihu.zhuanlemei.util.HttpRequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * IP和路径访问记录拦截器
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月27日 下午5:49:13
 * 
 */
public class RequestInterceptor implements HandlerInterceptor {
	private static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	private String mappingURL; // 利用正则映射到需要拦截的路径

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws IOException {
		String url = request.getRequestURL().toString();
		String remoteAddress = request.getRemoteAddr();

		if (!(mappingURL == null || url.matches(mappingURL))) {
			logger.info("mappingURL={}", mappingURL);
		}

		String requestIp = url.substring(url.indexOf("//") + 2, url.length());
		String requestSystemPath = requestIp.substring(requestIp.indexOf('/'), requestIp.length());

		if ("/".equals(requestSystemPath)) {
			return true;
		}

		request.getSession();
		
		// 获取用户浏览器信息
		String userAgent = request.getHeader("User-Agent");
		// logger.info("userAgent={}", userAgent);
		
		// 控制PC端和移动端之间的跳转
		if (userAgent.indexOf("Android") > -1 || userAgent.indexOf("iPhone") > -1 || userAgent.indexOf("PlayBook") > -1
				|| userAgent.indexOf("Touch") > -1 || userAgent.indexOf("Windows Phone") > -1) {
			if (requestSystemPath.indexOf("/mobile") == -1) {
				logger.info("PC端转移动端");
				String contentPath = request.getContextPath();
				String path = requestSystemPath.substring(contentPath.length(), requestSystemPath.length());
				String redirectPatch = HttpRequestUtil.getRequestFullUrl(request).replace(path, "/mobile" + path);
				response.sendRedirect(redirectPatch);
			}
		} else {
			if (requestSystemPath.indexOf("/mobile") > -1) {
				logger.info("移动端转PC端");
				String redirectPatch = HttpRequestUtil.getRequestFullUrl(request).replace("/mobile", "");
				response.sendRedirect(redirectPatch);
			}
		}

		logger.info("访客信息记录：IP地址={}, 访问路径={}", remoteAddress, requestSystemPath);

		return true;
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

}
