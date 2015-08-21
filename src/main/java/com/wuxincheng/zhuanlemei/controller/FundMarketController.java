package com.wuxincheng.zhuanlemei.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxincheng.zhuanlemei.model.Comment;
import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.model.ProdLike;
import com.wuxincheng.zhuanlemei.service.CommentService;
import com.wuxincheng.zhuanlemei.service.FundMarketService;
import com.wuxincheng.zhuanlemei.service.ProdLikeService;
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
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ProdLikeService prodLikeService;
	
	/** 每页显示条数 */
	private final Integer pageSize = 10;
	
	/** 当前页面 */
	private String currentPage;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, String currentPage) {
		logger.info("显示基金行情列表");
		requestMessageProcess(request);
		
		if (Validation.isBlank(currentPage) || !Validation.isInt(currentPage, "0+")) {
			currentPage = "1";
		}
		
		this.currentPage = currentPage;
		
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
		
		Map<String, Object> pager = fundMarketService.queryPager(queryParam);
		
		try {
			if (pager != null && pager.size() > 0) {
				Integer totalCount = (Integer)pager.get("totalCount");
				Integer lastPage = (totalCount/pageSize);
				Integer flag = (totalCount%pageSize)>0?1:0;
				pager.put("lastPage", lastPage + flag);
				
				// 如果当前页数大于总页数, 减1处理
				if (current > (lastPage + flag)) {
					current--;
					this.currentPage = current+"";
				}
				
				logger.info(this.currentPage);
				
				pager.put("currentPage", current);
				pager.put("pageSize", pageSize);
				
				model.addAttribute("pager", pager);
			}
		} catch (Exception e) {
			logger.error("分页查询出现异常", e);
		}
		
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
		
		// 评论列表
		List<Comment> comments = commentService.queryByFundCode(fundCode);
		model.addAttribute("comments", comments);
		
		// 查询当前用户是赞这个行情还是骂
		String userid = getCurrentUserid(request);
		if (StringUtils.isNotEmpty(userid)) {
			ProdLike prodLike = prodLikeService.queryByFundCode(fundCode, userid);
			model.addAttribute("prodLike", prodLike);
		}
		
		return "fund/market/detail";
	}
	
	@RequestMapping(value = "/like")
	@ResponseBody
	public Map<String, Object> like(Model model, HttpServletRequest request, String fundCode, String likeState) {
		logger.info("用户点赞同和反对操作 fundCode={}, likeState={}", fundCode, likeState);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", false);
		result.put("fundCode", fundCode);
		
		// 用户是否登录
		String userid = getCurrentUserid(request);
		
		if (StringUtils.isEmpty(userid)) {
			result.put("message", "您还没有登录");
			logger.info("用户还没有登录 result={}", result);
			return result;
		}
		
		logger.info("调用点赞服务");
		Integer[] scores = fundMarketService.likeHandle(fundCode, likeState, userid);
		if (scores != null && scores.length == 2) {
			result.put("flag", true);
			result.put("likeScore", scores[0]);
			result.put("unLikeScore", scores[1]);

			logger.info("用户点赞成功 result={}", result);
		} 
		
		return result;
	}
	
}
