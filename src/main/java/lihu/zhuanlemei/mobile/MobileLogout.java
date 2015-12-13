package lihu.zhuanlemei.mobile;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zhuanlemei.util.Constants;

/**
 * 退出系统
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午7:29:43 
 *
 */
@Controller
@RequestMapping("/mobile/logout")
public class MobileLogout {

	private static final Logger logger = LoggerFactory.getLogger(MobileLogin.class);
	
	@RequestMapping(value = "/")
	public String logout(Model model, HttpServletRequest request) {
		logger.info("退出系统");
		
		request.getSession().removeAttribute("user"); // 这一步可以不需要
		
		request.getSession().invalidate(); // 完全使用整个Session失效
		
		model.addAttribute(Constants.MSG_SUCCESS, "退出成功");
		
		return "redirect:/index";
	}
	
}