package com.wuxincheng.zhuanlemei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.service.FundMarketService;
import com.wuxincheng.zhuanlemei.util.Validation;

/**
 * 基金行情管理
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月14日 上午8:56:20 
 *
 */
@Controller
@RequestMapping("/fund/market")
public class FundMarketController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FundMarketController.class);

	@Autowired
	private FundMarketService fundMarketService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, String currentPage) {
		logger.info("显示基金行情列表");
		requestMessageProcess(request);
		
		List<FundMarket> fundMarkets = fundMarketService.queryAll();
		
		model.addAttribute("fundMarkets", fundMarkets);
		
		return "fund/market/list";
	}
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request, String fundCode) {
		logger.info("显示基金行情详细页面 fundCode={}", fundCode);
		
		if (StringUtils.isEmpty(fundCode) || !Validation.isIntPositive(fundCode)) {
			return "404";
		}

		// 基金行情详细
		FundMarket fundMarket = fundMarketService.queryDetailByFundCode(fundCode);
		model.addAttribute("fundMarket", fundMarket);
		
		return "fund/market/detail";
	}
	
}
