package com.wuxincheng.zhuanlemei.fetch.service;

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
@Service("HowBuyFetchService")
public class HowBuyFetchService {
	private static final Logger logger = LoggerFactory.getLogger(HowBuyFetchService.class);
	
	@Resource
	private HowBuyHelper howBuyHelper;

	public void main(String[] args) {
		String index = "table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\"";
		String end = "div id=\"dbDivfloat\"";

		logger.info("获取全部基金信息");
		String fetchURL0 = "http://www.howbuy.com/fund/fundranking/";
		howBuyHelper.fectFundInfos(fetchURL0, index, end, null);

		logger.info("获取股票型基金信息");
		String fetchURL1 = "http://www.howbuy.com/fund/fundranking/gupiao.htm";
		howBuyHelper.fectFundInfos(fetchURL1, index, end, "1");

		logger.info("获取债券型基金信息");
		String fetchURL2 = "http://www.howbuy.com/fund/fundranking/zhaiquan.htm";
		howBuyHelper.fectFundInfos(fetchURL2, index, end, "2");

		logger.info("获取混合型基金信息");
		String fetchURL3 = "http://www.howbuy.com/fund/fundranking/hunhe.htm";
		howBuyHelper.fectFundInfos(fetchURL3, index, end, "3");

		logger.info("获取理财型基金信息");
		String fetchURL4 = "http://www.howbuy.com/fund/fundranking/licai.htm";
		howBuyHelper.fectFundInfos(fetchURL4, index, end, "4");

		logger.info("获取货币型基金信息");
		String fetchURL5 = "http://www.howbuy.com/fund/fundranking/huobi.htm";
		howBuyHelper.fectFundInfos(fetchURL5, index, end, "5");

		logger.info("获取指数型基金信息");
		String fetchURL6 = "http://www.howbuy.com/fund/fundranking/zhishu.htm";
		howBuyHelper.fectFundInfos(fetchURL6, index, end, "6");

		logger.info("获取结构型基金信息");
		String fetchURL7 = "http://www.howbuy.com/fund/fundranking/jiegou.htm";
		howBuyHelper.fectFundInfos(fetchURL7, index, end, "7");

		logger.info("获取QDII基金信息");
		String fetchURL8 = "http://www.howbuy.com/fund/fundranking/qdii.htm";
		howBuyHelper.fectFundInfos(fetchURL8, index, end, "8");
	}
	
}
