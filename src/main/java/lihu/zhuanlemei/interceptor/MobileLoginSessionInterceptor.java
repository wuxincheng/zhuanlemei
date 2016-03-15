package lihu.zhuanlemei.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lihu.zhuanlemei.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 移动端登录Session拦截
 * 
 * @author wuxincheng(wxcking) 
 * @date 2016年3月15日 上午11:04:40 
 *
 */
public class MobileLoginSessionInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(LoginSessionInterceptor.class);

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURL().toString();

		if (mappingURL == null || url.matches(mappingURL)) {
			User user = (User) request.getSession().getAttribute("user");

			if (null == user) {
				logger.info("用户登录Session失效，跳转到登录页面");

				// 重定向
				response.sendRedirect(request.getContextPath() + "/mobile/login/");

				return false;
			}
		}

		return true;
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}
	
}
