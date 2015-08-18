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
import com.wuxincheng.zhuanlemei.model.Product;
import com.wuxincheng.zhuanlemei.service.CommentService;
import com.wuxincheng.zhuanlemei.service.ProductService;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.StringUtil;
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
	
	@RequestMapping(value = "/post")
	public String post(Model model, HttpServletRequest request, Comment commment) {
		logger.info("保存评论数据 commment={}", StringUtil.toStringMultiLine(commment));
		
		// 判断是否有用户登录
		String userid = getCurrentUserid(request);
		Product product = productService.queryDetailByProdid(commment.getProductid(), userid);
		
		if (null == product) {
			model.addAttribute(Constants.MSG_WARN, "评论失败：产品信息不存在！");
			return "redirect:/product/list";
		}
		
		model.addAttribute("prodid", product.getProdid());
		
		if (StringUtils.isEmpty(commment.getContent().trim())) {
			model.addAttribute(Constants.MSG_WARN, "评论失败：评论内容不能为空");
			return "redirect:/product/detail";
		}

		commentService.post(commment, getCurrentUserid(request));
		
		return "redirect:/product/detail";
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
