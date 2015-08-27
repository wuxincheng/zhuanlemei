package com.wuxincheng.zhuanlemei.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("基金公司列表");

		return "company/list";
	}

}
