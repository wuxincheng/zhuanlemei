package com.wuxincheng.hotfund.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.zhuanlemei.service.CollectUserService;
import com.wuxincheng.zhuanlemei.service.CommentService;
import com.wuxincheng.zhuanlemei.service.FundMarketService;
import com.wuxincheng.zhuanlemei.service.ProdLikeService;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.Validation;

@Controller
@RequestMapping("/hotfund")
public class HotFundController {

	private static final Logger logger = LoggerFactory.getLogger(HotFundController.class);
	
	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProdLikeService prodLikeService;
	
	@Autowired
	private CollectUserService collectUserService;

	/** 每页显示条数 */
	private final Integer pageSize = 10;

	/** 当前页面 */
	private String currentPage;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, String currentPage, String keyword) {
		logger.info("显示基金行情列表，当前页面 page={}，keyword={}", this.currentPage, keyword);

		if (Validation.isBlank(currentPage) || !Validation.isInt(currentPage, "0+")) {
			currentPage = "1";
		}

		this.currentPage = currentPage;

		Integer current = Integer.parseInt(currentPage);
		Integer start = null;
		Integer end = null;
		if (current > 1) {
			start = (current - 1) * pageSize;
			end = pageSize * current;
		} else {
			start = 0;
			end = pageSize;
		}

		if (StringUtils.isEmpty(keyword)) {
			keyword = "";
		}

		// 封装查询条件
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("start", start);
		queryParam.put("end", end);
		queryParam.put("keyword", keyword);

		Map<String, Object> pager = fundMarketService.queryPager(queryParam, Constants.DATE_TYPE_CACHE);

		try {
			if (pager != null && pager.size() > 0) {
				Integer totalCount = (Integer) pager.get("totalCount");
				Integer lastPage = (totalCount / pageSize);
				Integer flag = (totalCount % pageSize) > 0 ? 1 : 0;
				pager.put("lastPage", lastPage + flag);

				// 如果当前页数大于总页数, 减1处理
				if (current > (lastPage + flag)) {
					current--;
					this.currentPage = current + "";
				}

				pager.put("currentPage", current);
				pager.put("pageSize", pageSize);

				model.addAttribute("pager", pager);
			}
		} catch (Exception e) {
			logger.error("分页查询出现异常", e);
		}

		model.addAttribute("keyword", keyword);

		return "hotfund/fund/market/list";
	}
	
}
