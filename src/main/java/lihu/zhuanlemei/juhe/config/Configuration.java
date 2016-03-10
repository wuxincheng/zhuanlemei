package lihu.zhuanlemei.juhe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 聚合数据相关配置
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月10日 上午8:54:17
 * 
 */
@Component
public class Configuration {

	@Value("#{juheSettings.appKey}")
	private String appKey;

	@Value("#{juheSettings.stockSHallUrl}")
	private String stockSHallUrl;

	@Value("#{juheSettings.stockSZallUrl}")
	private String stockSZallUrl;

	@Value("#{juheSettings.stockUSallUrl}")
	private String stockUSallUrl;

	@Value("#{juheSettings.stockHKallUrl}")
	private String stockHKallUrl;

	@Value("#{juheSettings.stockUSDetailUrl}")
	private String stockUSDetailUrl;

	@Value("#{juheSettings.stockHKDetailUrl}")
	private String stockHKDetailUrl;

	@Value("#{juheSettings.stockHSDetailUrl}")
	private String stockHSDetailUrl;

	/**
	 * 聚合数据key
	 */
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * 沪股列表请求地址
	 */
	public String getStockSHallUrl() {
		return stockSHallUrl;
	}

	public void setStockSHallUrl(String stockSHallUrl) {
		this.stockSHallUrl = stockSHallUrl;
	}

	/**
	 * 深圳股市列表请求地址
	 */
	public String getStockSZallUrl() {
		return stockSZallUrl;
	}

	public void setStockSZallUrl(String stockSZallUrl) {
		this.stockSZallUrl = stockSZallUrl;
	}

	/**
	 * 美国股市列表请求地址
	 */
	public String getStockUSallUrl() {
		return stockUSallUrl;
	}

	public void setStockUSallUrl(String stockUSallUrl) {
		this.stockUSallUrl = stockUSallUrl;
	}

	/**
	 * 香港股市列表请求地址
	 */
	public String getStockHKallUrl() {
		return stockHKallUrl;
	}

	public void setStockHKallUrl(String stockHKallUrl) {
		this.stockHKallUrl = stockHKallUrl;
	}

	/**
	 * 美国股市详细请求地址
	 */
	public String getStockUSDetailUrl() {
		return stockUSDetailUrl;
	}

	public void setStockUSDetailUrl(String stockUSDetailUrl) {
		this.stockUSDetailUrl = stockUSDetailUrl;
	}

	/**
	 * 香港股市详细请求地址
	 */
	public String getStockHKDetailUrl() {
		return stockHKDetailUrl;
	}

	public void setStockHKDetailUrl(String stockHKDetailUrl) {
		this.stockHKDetailUrl = stockHKDetailUrl;
	}

	/**
	 * 沪深股市详细请求地址
	 */
	public String getStockHSDetailUrl() {
		return stockHSDetailUrl;
	}

	public void setStockHSDetailUrl(String stockHSDetailUrl) {
		this.stockHSDetailUrl = stockHSDetailUrl;
	}

}
