package lihu.zhuanlemei.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lihu.zhuanlemei.Result;
import lihu.zhuanlemei.controller.BaseController;
import lihu.zhuanlemei.mobile.service.MobileCollectService;
import lihu.zhuanlemei.model.Collect;
import lihu.zhuanlemei.model.CollectUser;
import lihu.zhuanlemei.model.Comment;
import lihu.zhuanlemei.model.FundMarket;
import lihu.zhuanlemei.model.Product;
import lihu.zhuanlemei.model.User;
import lihu.zhuanlemei.service.CollectService;
import lihu.zhuanlemei.service.CollectUserService;
import lihu.zhuanlemei.service.CommentService;
import lihu.zhuanlemei.service.FundMarketService;
import lihu.zhuanlemei.service.ProdLikeService;
import lihu.zhuanlemei.service.ProductService;
import lihu.zhuanlemei.service.UserService;
import lihu.zhuanlemei.util.Validation;

import org.apache.commons.lang.StringUtils;
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

	@Autowired
	private CollectService collectService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CollectUserService collectUserService;

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProdLikeService prodLikeService;

	@Resource
	private UserService userService;

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
	public String detail(Model model, String collectid, HttpServletRequest request) {
		logger.info("显示手机版本榜单详细页面 collectid={}", collectid);

		// 判断collectid
		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			logger.debug("详细显示失败：collectid为空");
			return "redirect:mobile/list";
		}

		// 是否存在这个榜单
		Collect collect = collectService.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.debug("详细显示失败：榜单不存在 collectid={}", collectid);
			return "redirect:mobile/list";
		}
		request.setAttribute("collect", collect);

		// 查询用户
		User userQuery = userService.queryByUserid(collect.getUserid());
		request.setAttribute("userQuery", userQuery);

		String userid = null;

		// 判断用户是否已经登录
		if (getCurrentUserid(request) != null) {
			// 如果登录，查询该用户是否已经收藏该榜单
			CollectUser collectUser = collectUserService.query(collectid, getCurrentUserid(request));
			request.setAttribute("collectUser", collectUser);
			userid = getCurrentUserid(request);
		}

		// 查询这个榜单下的所有产品
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);
		queryMap.put("userid", userid);
		
		List<Product> products = productService.queryCollectProductUser(queryMap);

		// 关联查询所有基金信息
		List<FundMarket> fundMarkets = fundMarketService.queryByProducts(products);
		request.setAttribute("fundMarkets", fundMarkets);

		// 查询这个榜单的所有评论
		List<Comment> comments = commentService.queryByCollectid(collectid);
		model.addAttribute("comments", comments);

		return "mobile/collect/detail";
	}

	@RequestMapping(value = "/focus")
	@ResponseBody
	public Result focus(String fundCode, HttpServletRequest request) {
		logger.info("基金收藏和取消收藏操作 fundCode={}", fundCode);

		Result result = new Result();

		String userid = getCurrentUserid(request);
		if (StringUtils.isEmpty(userid)) {
			return result.redirect("/mobile/login/");
		}

		if (fundCode != null && userid != null) {
			String msg = collectUserService.focusFund(fundCode, userid);
			result.setSuccess(true);
			result.setSuccessMsg(msg);
			return result;
		} else {
			logger.debug("基金收藏和取消收藏操作失败：collectid或userid为空");
			return result.reject("操作失败");
		}
	}

}
