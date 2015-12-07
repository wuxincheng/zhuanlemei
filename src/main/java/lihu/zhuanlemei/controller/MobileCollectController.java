package lihu.zhuanlemei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.mobile.service.MobileCollectService;
import lihu.zhuanlemei.util.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 移动版榜单
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月1日 下午10:30:45
 * 
 */
@Controller
@RequestMapping("/mobile/collect")
public class MobileCollectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MobileCollectController.class);
	
	/** 每页显示条数 */
	private final Integer pageSize = 10;

	@Autowired
	private MobileCollectService mobileCollectService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示手机版本榜单列表");
		
		return "mobile/collect/list";
	}
	
	@RequestMapping(value = "/loadmore")
	@ResponseBody
	public Map<String, Object> loadmore(String currentPage) {
		logger.info("点击加载更多 currentPage={}", currentPage);
		
		if (Validation.isBlank(currentPage) || !Validation.isInt(currentPage, "0+")) {
			currentPage = "1";
		}
		
		Integer current = Integer.parseInt(currentPage);
		Integer start = null;
		Integer end = null;
		if (current > 1) {
			start = (current - 1) * pageSize;
			end = pageSize;
		} else {
			start = 0;
			end = pageSize;
		}
		
		// 封装查询条件
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("start", start);
		queryParam.put("end", end);
		
		Map<String, Object> pager = mobileCollectService.queryPager(queryParam);
		
		try {
			if (pager != null && pager.size() > 0) {
				
			} else {
				logger.info("没有查询到文章信息");
			}
		} catch (Exception e) {
			logger.info("查询文章明细时出现异常", e);
		}
		
		return pager;
	}

	@RequestMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request) {
		logger.info("显示手机版本榜单详细页面");

		return "mobile/collect/detail";
	}

}
