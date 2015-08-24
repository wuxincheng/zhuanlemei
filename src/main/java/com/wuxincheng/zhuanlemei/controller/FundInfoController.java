package com.wuxincheng.zhuanlemei.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxincheng.zhuanlemei.service.FundMarketService;

/**
 * 基金信息管理
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月23日 下午10:24:18
 * 
 */
@Controller
@RequestMapping("/fund/info")
public class FundInfoController {
	private static final Logger logger = LoggerFactory.getLogger(FundInfoController.class);

	@Autowired
	private FundMarketService fundMarketService;

	@RequestMapping(value = "/query")
	@ResponseBody
	public List<Map<String, String>> query(String keyword) {
		logger.info("根据关键字异步查询基金信息 keyword={}", keyword);
		return fundMarketService.queryFunds(keyword);
	}
	
	@RequestMapping(value = "/search")
	public String search(String keyword) {
		logger.info("根据关键字同步查询基金信息 keyword={}", keyword);
		
		// 
		
		return "/fund/market/list";
	}

}
