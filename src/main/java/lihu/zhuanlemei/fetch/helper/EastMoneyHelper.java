package lihu.zhuanlemei.fetch.helper;

import java.util.List;
import java.util.Map;

import lihu.zhuanlemei.util.HttpClientHelper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 东方财富
 * 
 * @author wuxincheng(wxcking) 
 * @date 2016年5月27日 下午6:36:03 
 *
 */
@Component
public class EastMoneyHelper {
	private static final Logger logger = LoggerFactory.getLogger(EastMoneyHelper.class);

	// http://group.eastmoney.com/rank.html
	
	public static void main(String[] args) {
		fectRankInfos("http://group.eastmoney.com/rank.html", null);
	}
	
	public static List<Map<String, String>> fectRankInfos(String fetchURL, String rankType) {
		logger.info("抓取东方财富排行榜信息 fetchURL={}", fetchURL);
		HttpClientHelper hp = new HttpClientHelper();

		String fetchData = null;
		try {
			logger.info("开始抓取");
			fetchData = hp.getData(fetchURL);
			logger.info("抓取成功");
		} catch (Exception e) {
			logger.error("抓取出现异常", e);
			return null;
		}

		if (StringUtils.isEmpty(fetchData)) {
			logger.info("抓取数据为空");
			return null;
		}
		
		logger.info("开始解析原始数据");
		StringBuffer fetchbf = new StringBuffer(fetchData);
		
		logger.info(fetchbf.toString());
		
		return null;
	}
	
}
