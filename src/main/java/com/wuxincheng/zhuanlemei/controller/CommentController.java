package com.wuxincheng.zhuanlemei.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.zhuanlemei.model.Comment;
import com.wuxincheng.zhuanlemei.service.CommentService;
import com.wuxincheng.zhuanlemei.service.ProductService;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.Validation;

/**
 * 评论管理
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月29日 下午3:04:31
 * 
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Resource
	private CommentService commentService;

	@Resource
	private ProductService productService;

	/**
	 * 保存评论信息(产品评论和基金行情评论)
	 * 
	 * @param model
	 * @param request
	 * @param commment
	 * @return
	 */
	@RequestMapping(value = "/post")
	public String post(Model model, HttpServletRequest request, Comment comment) {
		logger.info("保存评论数据");

		// 基金代码, 用于判断是产品评论还是行情评论的标识
		String fundCodeFlag = comment.getFundCode();
		
		model.addAttribute("prodid", comment.getProductid());
		model.addAttribute("fundCode", comment.getFundCode());
		
		// 保存处理
		String responseMessage = commentService.post(comment, getCurrentUserid(request));
		if (StringUtils.isNotEmpty(responseMessage)) { // 返回异常信息处理
			model.addAttribute(Constants.MSG_WARN, "" + responseMessage);
			return fundCodeFlag==null?"redirect:/product/detail":"redirect:/fund/market/detail";
		}

		return fundCodeFlag==null?"redirect:/product/detail":"redirect:/fund/market/detail";
	}

	@RequestMapping(value = "/list")
	public String list(String productid) {
		logger.info("查询所有评论 productid={}", productid);

		if (StringUtils.isEmpty(productid) || !Validation.isIntPositive(productid)) {
			return "redirect:product/detail";
		}

		commentService.queryByProductid(productid);

		return "redirect:product/detail";
	}

}
