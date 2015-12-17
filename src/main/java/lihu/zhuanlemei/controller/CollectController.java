package lihu.zhuanlemei.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import lihu.zhuanlemei.util.Constants;
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
 * 产品集，现更名为榜单
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月1日 下午10:30:45
 * 
 */
@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CollectController.class);

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
		logger.info("显示榜单列表");

		requestMessageProcess(request);

		List<Collect> collects = collectService.queryAll();

		if (null == collects || collects.size() < 1) {
			model.addAttribute(Constants.MSG_INFO, "目前还没发布榜单");
			logger.debug("目前还没发布榜单");
		}

		request.setAttribute("collects", collects);

		return "collect/list";
	}

	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request) {
		logger.info("显示添加榜单页面");
		requestMessageProcess(request);

		// 判断用户是否有创建榜单权限
		if (!isCollectPermission(request)) {
			model.addAttribute(Constants.MSG_WARN, "您还没有该项权限");
			return "redirect:list";
		}

		request.setAttribute(Constants.CURRENT_USERID, getCurrentUserid(request));

		return "collect/edit";
	}

	@RequestMapping(value = "/create")
	public String create(Model model, HttpServletRequest request, Collect collect) {
		logger.info("添加新的榜单");

		String userid = getCurrentUserid(request);

		// 判断用户是否有创建榜单权限
		if (!isCollectPermission(request)) {
			request.setAttribute(Constants.MSG_WARN, "对不起，您没有操作权限");
			return "collect/edit";
		}

		// 图片存放路径
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "collect/coverbg/";
		logger.debug("图片存放路径 ctxPath={}", ctxPath);

		String responseMessage = collectService.createOrUpdate(collect, ctxPath, userid);
		if (StringUtils.isNotEmpty(responseMessage)) {
			request.setAttribute(Constants.MSG_WARN, "处理失败：" + responseMessage);
			return "collect/edit";
		}

		logger.info("榜单创建成功");

		request.setAttribute(Constants.MSG_SUCCESS, "榜单创建成功");

		return "collect/edit";
	}

	@RequestMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request, String collectid) {
		logger.info("显示榜单 collectionid={}", collectid);

		// 提示信息显示
		requestMessageProcess(request);

		// 判断collectid
		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			logger.debug("详细显示失败：collectid为空");
			return "redirect:list";
		}

		// 是否存在这个榜单
		Collect collect = collectService.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.debug("详细显示失败：榜单不存在 collectid={}", collectid);
			return "redirect:list";
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
		List<Product> products = productService.queryProductsByCollectid(queryMap);

		// 关联查询所有基金信息
		List<FundMarket> fundMarkets = fundMarketService.queryByProducts(products);
		request.setAttribute("fundMarkets", fundMarkets);

		// 查询这个榜单的所有评论
		List<Comment> comments = commentService.queryByCollectid(collectid);
		model.addAttribute("comments", comments);

		// 显示前5名热门榜单
		List<Collect> collects = collectService.queryTopHot(5);
		model.addAttribute("collects", collects);

		return "collect/detail";
	}

	/**
	 * 榜单收藏和取消收藏操作
	 * 
	 * @param collectid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/collect")
	public String collect(String collectid, String userid) {
		logger.info("榜单收藏和取消收藏操作 collectionid={} userid={}", collectid, userid);

		if (collectid != null && userid != null) {
			collectUserService.collect(collectid, userid);
			logger.debug("榜单收藏和取消收藏操作成功");
		} else {
			logger.debug("榜单收藏和取消收藏操作失败：collectid或userid为空");
		}

		return "redirect:/collect/detail?collectid=" + collectid;
	}

	@RequestMapping(value = "/like")
	@ResponseBody
	public Map<String, Object> like(Model model, HttpServletRequest request, String collectid, String likeState) {
		logger.info("用户点赞同和反对操作 collectid={}, likeState={}", collectid, likeState);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", false);
		result.put("collectid", collectid);

		// 用户是否登录
		String userid = getCurrentUserid(request);

		if (StringUtils.isEmpty(userid)) {
			result.put("message", "您还没有登录");
			logger.info("用户还没有登录 result={}", result);
			return result;
		}

		logger.info("调用点赞服务");
		Integer[] scores = prodLikeService.collectLikeHandle(collectid, likeState, userid);
		if (scores != null && scores.length == 2) {
			result.put("flag", true);
			result.put("likeScore", scores[0]);
			result.put("unLikeScore", scores[1]);

			logger.info("用户点赞成功 result={}", result);
		}

		return result;
	}

	/**
	 * 基金产品加入榜单
	 * 
	 * @param fundCode
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/addin")
	public String addin(Model model, String fundCode) {
		logger.info("基金产品加入榜单操作 fundCode={}", fundCode);

		// 查询出所有榜单
		List<Collect> collects = collectService.queryAll();
		model.addAttribute("collects", collects);

		FundMarket fundMarket = fundMarketService.queryDetailByFundCode(fundCode);
		model.addAttribute("fundName", fundMarket.getFundName());
		model.addAttribute("fundCode", fundCode);

		return "collect/addin";
	}

}
