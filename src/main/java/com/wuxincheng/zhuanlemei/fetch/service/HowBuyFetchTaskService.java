package com.wuxincheng.zhuanlemei.fetch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundMarketDao;
import com.wuxincheng.zhuanlemei.dao.TaskDao;
import com.wuxincheng.zhuanlemei.fetch.helper.HowBuyHelper;
import com.wuxincheng.zhuanlemei.model.FundMarket;
import com.wuxincheng.zhuanlemei.model.Task;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.DateUtil;

/**
 * 好买网信息抓取服务
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月14日 下午6:42:06
 * 
 */
@Service("howBuyFetchTaskService")
public class HowBuyFetchTaskService {
	private static final Logger logger = LoggerFactory.getLogger(HowBuyFetchTaskService.class);

	@Resource
	private HowBuyHelper howBuyHelper;

	@Resource
	private FundMarketDao fundMarketDao;

	@Resource
	private TaskDao taskDao;

	/**
	 * 任务1：抓取基金行情列表数据（只抓取当天的数据）
	 */
	public void fundMarketInfoTask() {
		// 当天日期
		String currentDate = DateUtil.getCurrentDate(new Date(), "yyyy-MM-dd");
		logger.debug("当天日期 currentDate={}", currentDate);
		
		Task fundMarketInfoTask = taskDao.query("fundMarketInfo", currentDate);
		if (fundMarketInfoTask == null) {
			logger.debug("执行失败: 没有查询到抓取基金行情列表数据任务");
			return;
		}
		
		if (!"0".equals(fundMarketInfoTask.getTaskFlag())) {
			logger.debug("执行失败: 抓取基金行情列表数据任务已经执行");
			return;
		}
		
		logger.info("开始抓取基金行情信息");

		List<Map<String, String>> fundMarkets = getFundMarket();
		logger.debug("已经抓取到基金行情信息列表，准备入库");
		
		String currentDateTime = DateUtil.getCurrentDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		// 循环存储抓取到的数据到TFundMarketCurrent表中
		for (Map<String, String> fundMarketMap : fundMarkets) {

			try {
				// 根据基金净值日期和基金代码查询当天净值数据是否存在
				FundMarket fundMarketFlag = fundMarketDao.query(fundMarketMap.get("fundCode"),
						fundMarketMap.get("fundNavDate"));
				if (fundMarketFlag != null) { // 如果存在, 更新相关信息
					logger.debug("更新行情信息 fundCode={}, fundNavDate={}", fundMarketMap.get("fundCode"),
							fundMarketMap.get("fundNavDate"));

					logger.debug("封装更新基金行情信息");

					fundMarketFlag.setMarketDate(currentDate);
					
					// 基本信息不用更新
					// fundMarketFlag.setFundName(fundMarketMap.get("fundName"));
					// fundMarketFlag.setFundType(fundMarketMap.get("fundType"));
					// fundMarketFlag.setFundRiskLevel(fundMarketMap.get("fundRiskLevel"));
					// fundMarketFlag.setCurrentState(Constants.DEFAULT_STATE);
					
					// 更新依据
					fundMarketFlag.setFundCode(fundMarketMap.get("fundCode"));
					
					// 更新以下每天实时的基金行情数据
					fundMarketFlag.setCurrentNav(fundMarketMap.get("fundNav"));
					fundMarketFlag.setNavDate(fundMarketMap.get("fundNavDate"));
					fundMarketFlag.setFundRiseWeek(fundMarketMap.get("fundRiseWeek"));
					fundMarketFlag.setFundRiseMonth(fundMarketMap.get("fundRiseMonth"));
					fundMarketFlag.setFundRiseThreeMonth(fundMarketMap.get("fundRiseThreeMonth"));
					fundMarketFlag.setFundRiseHalfYear(fundMarketMap.get("fundRiseHalfYear"));
					fundMarketFlag.setFundRiseYear(fundMarketMap.get("fundRiseYear"));
					fundMarketFlag.setFundRiseThisYear(fundMarketMap.get("fundRiseThisYear"));
					fundMarketFlag.setUpdateTime(currentDateTime);

					fundMarketDao.update(fundMarketFlag);

					logger.debug("成功更新一条基金行情信息");
				} else { // 如果不存在, 则添加这条行情信息
					logger.debug("新增行情信息 fundCode={}, fundNavDate={}", fundMarketMap.get("fundCode"),
							fundMarketMap.get("fundNavDate"));

					FundMarket fundMarket = new FundMarket();

					logger.debug("封装基金行情信息");

					fundMarket.setMarketDate(currentDate);
					fundMarket.setFundName(fundMarketMap.get("fundName"));
					fundMarket.setFundCode(fundMarketMap.get("fundCode"));
					fundMarket.setFundType(fundMarketMap.get("fundType"));
					fundMarket.setFundRiskLevel(fundMarketMap.get("fundRiskLevel"));
					fundMarket.setCurrentNav(fundMarketMap.get("fundNav"));
					fundMarket.setCurrentState(Constants.DEFAULT_STATE);
					fundMarket.setNavDate(fundMarketMap.get("fundNavDate"));
					fundMarket.setFundRiseWeek(fundMarketMap.get("fundRiseWeek"));
					fundMarket.setFundRiseMonth(fundMarketMap.get("fundRiseMonth"));
					fundMarket.setFundRiseThreeMonth(fundMarketMap.get("fundRiseThreeMonth"));
					fundMarket.setFundRiseHalfYear(fundMarketMap.get("fundRiseHalfYear"));
					fundMarket.setFundRiseYear(fundMarketMap.get("fundRiseYear"));
					fundMarket.setFundRiseThisYear(fundMarketMap.get("fundRiseThisYear"));
					fundMarket.setCreateTime(currentDateTime);
					fundMarket.setUpdateTime(currentDateTime);

					fundMarketDao.insert(fundMarket);

					logger.debug("成功插入一条基金行情信息");
				}
			} catch (Exception e) {
				logger.info("基金行情异常数据 fundCode={}, fundNavDate={}", fundMarketMap.get("fundCode"),
						fundMarketMap.get("fundNavDate"));
				logger.error("基金行情处理异常", e);
			}
			
		}

		logger.info("基金行情信息抓取完成");
		
		// 更新任务进度
		Task task = new Task();
		task.setTaskName("fundMarketInfo");
		task.setTaskFlag("1"); // 1-已经执行
		task.setTaskDate(currentDate);
		task.setUpdateTime(currentDateTime);
		taskDao.update(task);
		logger.info("任务进度已经更新");
	}

	/**
	 * TODO 任务2：补充基金行情详细信息（只补充当天抓取的数据）
	 */
	public void fundDetailInfoTask() {
		// 查询当天所有的基金行情信息

	}

	/**
	 * TODO 任务3：从基金行情信息表中提取信息到基金信息表（只提取当天的数据）
	 */
	public void fundInfoTask() {

	}
	
	/**
	 * TODO 任务4：把当天的行情信息转移到历史表中
	 */
	public void fundMarketHistoryTask() {

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
