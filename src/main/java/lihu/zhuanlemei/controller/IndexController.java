package lihu.zhuanlemei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zhuanlemei.model.Collect;
import lihu.zhuanlemei.model.FundMarket;
import lihu.zhuanlemei.service.CollectService;
import lihu.zhuanlemei.service.FundMarketService;
import lihu.zhuanlemei.util.Constants;

/**
 * 首页
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月24日 下午9:16:44
 * 
 */
@Controller
public class IndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CollectService collectService;

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {
		logger.info("显示首页");

		requestMessageProcess(request);

		// 显示前3名热门榜单
		List<Collect> collects = collectService.queryTopHot(6);
		model.addAttribute("collects", collects);

		// 显示前10名热门基金
		List<FundMarket> fundMarkets = fundMarketService.queryTopHot(10);
		model.addAttribute("fundMarkets", fundMarkets);

		// 显示红榜单
		List<FundMarket> topRedMarkets = fundMarketService.queryRedTopHot(Constants.DATE_TYPE_CACHE);
		model.addAttribute("topRedMarkets", topRedMarkets);

		// 显示绿榜单
		List<FundMarket> topGreenMarkets = fundMarketService.queryGreenTopHot(Constants.DATE_TYPE_CACHE);
		model.addAttribute("topGreenMarkets", topGreenMarkets);

		fundMarketService.queryFundCompanys();

		return "index";
	}

	@RequestMapping(value = "/sessionExpired")
	public String sessionExpired(Model model) {
		return "sessionExpired";
	}

}
