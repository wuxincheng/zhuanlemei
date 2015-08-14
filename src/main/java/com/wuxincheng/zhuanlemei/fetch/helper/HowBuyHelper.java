package com.wuxincheng.zhuanlemei.fetch.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wuxincheng.zhuanlemei.util.HttpClientHelper;

/**
 * 好买基金网信息抓取帮助类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月14日 下午6:08:04
 * 
 */
@Component
public class HowBuyHelper {
	private static final Logger logger = LoggerFactory.getLogger(HowBuyHelper.class);

	/**
	 * 抓取基金行情列表信息
	 * 
	 * @param fetchURL
	 *            抓取地址
	 * @param index
	 *            开始读取的位置
	 * @param end
	 *            结束读取的位置
	 * @param fundType
	 *            基金类型
	 * @return
	 */
	public List<Map<String, String>> fectFundInfos(String fetchURL, String index,
			String end, String fundType) {
		logger.info("抓取好买网基金行情信息 fetchURL={}", fetchURL);
		HttpClientHelper hp = new HttpClientHelper();

		String fetchData = null;
		try {
			logger.info("开始抓取");
			fetchData = hp.getData(fetchURL);
			logger.info("抓取成功");
		} catch (Exception e) {
			logger.error("抓取出现异常", e);
		}

		logger.info("开始解析原始数据");
		StringBuffer fetchbf = new StringBuffer(fetchData);
		int indexflag = fetchData.indexOf(index);
		fetchbf.delete(0, indexflag);
		fetchbf.delete(fetchbf.indexOf(end), fetchbf.length() - 1);
		logger.debug("初步截取处理");

		// 每个表格行
		String[] fundsInfoRes = fetchbf.toString().split("<tr>");
		logger.debug("转换成数组处理");

		// 存放基金信息的列表
		List<Map<String, String>> fundInfos = new ArrayList<Map<String, String>>();

		for (int i = 3; i < fundsInfoRes.length; i++) {
			// 每一行的数据
			String fundInfoRes = fundsInfoRes[i];
			logger.debug("第{}行的数据 fundInfoRes={}", i, fundInfoRes);

			// 每一行中每个单元格的数据
			String[] temps = fundInfoRes.split("td width=");
			logger.debug("第{}行中每个单元格的数据 temps={}", i, temps);

			if (temps.length > 15 || temps.length < 15) {
				logger.debug("无效数据行 i={}", i);
				continue;
			}

			logger.info("数组temps长度 length={}", temps.length);

			// 基金信息
			Map<String, String> fundInfoMap = new HashMap<String, String>();

			// 基金代码
			fundInfoMap.put("fundCode", temps[3].substring(45, temps[3].length() - 10));
			logger.debug("fundCode={}", temps[3]);

			// 基金名称
			fundInfoMap.put("fundName", temps[4].substring(58, temps[4].length() - 12));
			logger.debug("fundName={}", temps[4]);

			// 基金净值日期
			fundInfoMap.put("fundNavDate", temps[5].substring(5, temps[5].length() - 6));
			logger.debug("fundNavDate={}", temps[5]);

			// 基金净值
			fundInfoMap.put("fundNav", temps[6].substring(17, temps[6].length() - 8));
			logger.debug("fundNav={}", temps[6]);

			/* 下面这几个字段要特殊处理下, 因为有--, 红色, 绿色三种显示风格 */

			// 近一周
			String fundRiseWeek = temps[7].substring(17, temps[7].length() - 6); // 无样式处理
			if (fundRiseWeek.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseWeek = fundRiseWeek.replaceAll("<span class=\"cRed\">", "").replaceAll(
						"</span>", "");
			}
			if (fundRiseWeek.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseWeek = fundRiseWeek.replaceAll("<span class=\"cGreen\">", "").replaceAll(
						"</span>", "");
			}
			fundInfoMap.put("fundRiseWeek", fundRiseWeek);
			logger.debug("fundRiseWeek={}", temps[7]);

			// 近一个月
			String fundRiseMonth = temps[8].substring(17, temps[8].length() - 6);
			if (fundRiseMonth.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseMonth = fundRiseMonth.replaceAll("<span class=\"cRed\">", "").replaceAll(
						"</span>", "");
			}
			if (fundRiseMonth.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseMonth = fundRiseMonth.replaceAll("<span class=\"cGreen\">", "").replaceAll(
						"</span>", "");
			}
			fundInfoMap.put("fundRiseMonth", fundRiseMonth);
			logger.debug("fundRiseMonth={}", temps[8]);

			// 近三个月
			String fundRiseThreeMonth = temps[9].substring(17, temps[9].length() - 6);
			if (fundRiseThreeMonth.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseThreeMonth = fundRiseThreeMonth.replaceAll("<span class=\"cRed\">", "")
						.replaceAll("</span>", "");
			}
			if (fundRiseThreeMonth.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseThreeMonth = fundRiseThreeMonth.replaceAll("<span class=\"cGreen\">", "")
						.replaceAll("</span>", "");
			}
			fundInfoMap.put("fundRiseThreeMonth", fundRiseThreeMonth);
			logger.debug("fundRiseThreeMonth={}", temps[9]);

			// 近半年
			String fundRiseHalfYear = temps[10].substring(17, temps[10].length() - 6);
			if (fundRiseHalfYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseHalfYear = fundRiseHalfYear.replaceAll("<span class=\"cRed\">", "")
						.replaceAll("</span>", "");
			}
			if (fundRiseHalfYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseHalfYear = fundRiseHalfYear.replaceAll("<span class=\"cGreen\">", "")
						.replaceAll("</span>", "");
			}
			fundInfoMap.put("fundRiseHalfYear", fundRiseHalfYear);
			logger.debug("fundRiseHalfYear={}", temps[10]);

			// 近一年
			String fundRiseYear = temps[11].substring(17, temps[11].length() - 6);
			if (fundRiseYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseYear = fundRiseYear.replaceAll("<span class=\"cRed\">", "").replaceAll(
						"</span>", "");
			}
			if (fundRiseYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseYear = fundRiseYear.replaceAll("<span class=\"cGreen\">", "").replaceAll(
						"</span>", "");
			}
			fundInfoMap.put("fundRiseYear", fundRiseYear);
			logger.debug("fundRiseYear={}", temps[11]);

			// 今年
			String fundRiseThisYear = temps[12].substring(17, temps[12].length() - 6);
			if (fundRiseThisYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
				fundRiseThisYear = fundRiseThisYear.replaceAll("<span class=\"cRed\">", "")
						.replaceAll("</span>", "");
			}
			if (fundRiseThisYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
				fundRiseThisYear = fundRiseThisYear.replaceAll("<span class=\"cGreen\">", "")
						.replaceAll("</span>", "");
			}
			fundInfoMap.put("fundRiseThisYear", fundRiseThisYear);
			logger.debug("fundRiseThisYear={}", temps[12]);

			// 基金类型
			fundInfoMap.put("fundType", fundType);

			logger.info("fundInfoMap={}", fundInfoMap);

			fundInfos.add(fundInfoMap);
		}

		logger.info("size={}", fundInfos.size());

		return fundInfos;
	}

	/**
	 * 根据基金代码抓取基金详细信息
	 * 
	 * @param fundCode
	 *            基金代码
	 * @return
	 */
	public Map<String, String> fectFundInfo(String fundCode) {
		logger.info("根据基金代码抓取基金详细信息 fundCode={}", fundCode);
		
		
		
		Map<String, String> fundInfo = new HashMap<String, String>();

		return fundInfo;
	}

	

}
