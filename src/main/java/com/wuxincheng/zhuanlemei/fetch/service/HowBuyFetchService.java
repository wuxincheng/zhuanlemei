package com.wuxincheng.zhuanlemei.fetch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.fetch.helper.HowBuyHelper;

/**
 * 好买网信息抓取服务
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月14日 下午6:42:06
 * 
 */
@Service("howBuyFetchService")
public class HowBuyFetchService {
	private static final Logger logger = LoggerFactory.getLogger(HowBuyFetchService.class);

	@Resource
	private HowBuyHelper howBuyHelper;

	/**
	 * TODO 处理基金行情数据
	 */
	public void fundMarketInfoTask() {
		getFundMarket();
	}

	/**
	 * TODO 处理基金详细信息
	 */
	public void fundDetailInfoTask() {

	}

	/**
	 * 获取当天所有基金行情信息
	 * 
	 * @return
	 */
	private List<Map<String, String>> getFundMarket() {
		List<Map<String, String>> allFundMarketInfo = new ArrayList<Map<String, String>>();
		
		// logger.info("获取全部基金信息");
		// String fetchURL0 = "http://www.howbuy.com/fund/fundranking/";
		// howBuyHelper.fectFundInfos(fetchURL0, null);

		logger.info("获取股票型基金信息");
		String fetchURL1 = "http://www.howbuy.com/fund/fundranking/gupiao.htm";
		List<Map<String, String>> gupiao = howBuyHelper.fectFundInfos(fetchURL1, "1");
		allFundMarketInfo.addAll(gupiao);

		logger.info("获取债券型基金信息");
		String fetchURL2 = "http://www.howbuy.com/fund/fundranking/zhaiquan.htm";
		List<Map<String, String>> zhaiquan = howBuyHelper.fectFundInfos(fetchURL2, "2");
		allFundMarketInfo.addAll(zhaiquan);

		logger.info("获取混合型基金信息");
		String fetchURL3 = "http://www.howbuy.com/fund/fundranking/hunhe.htm";
		List<Map<String, String>> hunhe = howBuyHelper.fectFundInfos(fetchURL3, "3");
		allFundMarketInfo.addAll(hunhe);

		logger.info("获取理财型基金信息");
		String fetchURL4 = "http://www.howbuy.com/fund/fundranking/licai.htm";
		List<Map<String, String>> licai = howBuyHelper.fectFundInfos(fetchURL4, "4");
		allFundMarketInfo.addAll(licai);

		logger.info("获取货币型基金信息");
		String fetchURL5 = "http://www.howbuy.com/fund/fundranking/huobi.htm";
		List<Map<String, String>> huobi = howBuyHelper.fectFundInfos(fetchURL5, "5");
		allFundMarketInfo.addAll(huobi);

		logger.info("获取指数型基金信息");
		String fetchURL6 = "http://www.howbuy.com/fund/fundranking/zhishu.htm";
		List<Map<String, String>> zhishu = howBuyHelper.fectFundInfos(fetchURL6, "6");
		allFundMarketInfo.addAll(zhishu);

		logger.info("获取结构型基金信息");
		String fetchURL7 = "http://www.howbuy.com/fund/fundranking/jiegou.htm";
		List<Map<String, String>> jiegou = howBuyHelper.fectFundInfos(fetchURL7, "7");
		allFundMarketInfo.addAll(jiegou);

		logger.info("获取QDII基金信息");
		String fetchURL8 = "http://www.howbuy.com/fund/fundranking/qdii.htm";
		List<Map<String, String>> qdii = howBuyHelper.fectFundInfos(fetchURL8, "8");
		allFundMarketInfo.addAll(qdii);
		
		return allFundMarketInfo;
	}

}
