package lihu.zhuanlemei.mobile0;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 退出系统
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午7:29:43 
 *
 */
@Controller("mobile0Logout")
@RequestMapping("/mobile0/logout")
public class Mobile0Logout {

	private static final Logger logger = LoggerFactory.getLogger(Mobile0Login.class);
	
	@RequestMapping(value = "/")
	public String logout(Model model, HttpServletRequest request) {
		logger.info("退出系统");
		
		request.getSession().removeAttribute("user"); // 这一步可以不需要
		request.getSession().invalidate(); // 完全使用整个Session失效
		
		return "redirect:/mobile/collect/list";
	}
	
}
