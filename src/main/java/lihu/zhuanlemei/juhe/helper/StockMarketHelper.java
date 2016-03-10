package lihu.zhuanlemei.juhe.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lihu.zhuanlemei.juhe.Pageable;
import lihu.zhuanlemei.juhe.config.Configuration;
import lihu.zhuanlemei.juhe.model.StockMarketHK;
import lihu.zhuanlemei.juhe.model.StockMarketSH;
import lihu.zhuanlemei.juhe.model.StockMarketSZ;
import lihu.zhuanlemei.juhe.model.StockMarketUS;
import lihu.zhuanlemei.juhe.util.HttpRequestUtil;
import lihu.zhuanlemei.util.ObjectUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 聚合数据请求帮助类
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月9日 下午2:48:01
 * 
 */
@Component
public class StockMarketHelper {
	private static final Logger logger = LoggerFactory.getLogger(StockMarketHelper.class);

	@Resource
	private Configuration configuration;

	private static final String REQUEST_METHOD_GET = "GET";

	/**
	 * 请求获取沪股数据列表
	 */
	public Pageable requestSHList(String page, String currentTickDate) throws Exception {
		logger.info("请求获取沪股数据列表 page={}", page);

		Pageable pageable = new Pageable();
		Object[] stockMarketArray = getByList(pageable, page, configuration.getStockSHallUrl());

		List<StockMarketSH> stockMarkets = new ArrayList<StockMarketSH>(); // 沪股数据列表
		for (Object object : stockMarketArray) {
			JSONObject marketJson = JSONObject.fromObject(object);
			StockMarketSH stockMarket = new StockMarketSH();

			stockMarket.setSymbol(ObjectUtil.toString(marketJson.get("symbol")));
			stockMarket.setName(ObjectUtil.toString(marketJson.get("name")));
			stockMarket.setTrade(ObjectUtil.toString(marketJson.get("trade")));
			stockMarket.setPriceChange(ObjectUtil.toString(marketJson.get("pricechange")));
			stockMarket.setChangePercent(ObjectUtil.toString(marketJson.get("changepercent")));
			stockMarket.setBuy(ObjectUtil.toString(marketJson.get("buy")));
			stockMarket.setSell(ObjectUtil.toString(marketJson.get("sell")));
			stockMarket.setSettleMent(ObjectUtil.toString(marketJson.get("settlement")));
			stockMarket.setOpen(ObjectUtil.toString(marketJson.get("open")));
			stockMarket.setHigh(ObjectUtil.toString(marketJson.get("high")));
			stockMarket.setLow(ObjectUtil.toString(marketJson.get("low")));
			stockMarket.setVolume(ObjectUtil.toString(marketJson.get("volume")));
			stockMarket.setAmount(ObjectUtil.toString(marketJson.get("amount")));
			stockMarket.setCode(ObjectUtil.toString(marketJson.get("code")));
			stockMarket.setTickTime(ObjectUtil.toString(marketJson.get("ticktime")));
			stockMarket.setTickDate(currentTickDate);

			stockMarkets.add(stockMarket);
		}
		pageable.setDataSH(stockMarkets);

		logger.info("获取沪股列表成功");

		return pageable;
	}

	/**
	 * 请求获取美股数据列表
	 */
	public Pageable requestUSList(String page, String currentDate) throws Exception {
		logger.info("请求获取美股数据列表 page={}", page);

		Pageable pageable = new Pageable();
		Object[] stockMarketArray = getByList(pageable, page, configuration.getStockUSallUrl());

		List<StockMarketUS> stockMarkets = new ArrayList<StockMarketUS>();

		for (Object object : stockMarketArray) {
			JSONObject marketJson = JSONObject.fromObject(object);
			StockMarketUS stockMarket = new StockMarketUS();

			stockMarket.setName(ObjectUtil.toString(marketJson.get("cname")));
			stockMarket.setCategory(ObjectUtil.toString(marketJson.get("category")));
			stockMarket.setSymbol(ObjectUtil.toString(marketJson.get("symbol")));
			stockMarket.setPrice(ObjectUtil.toString(marketJson.get("price")));
			stockMarket.setDiffience(ObjectUtil.toString(marketJson.get("diff")));
			stockMarket.setChangePercent(ObjectUtil.toString(marketJson.get("chg")));
			stockMarket.setPreclose(ObjectUtil.toString(marketJson.get("preclose")));
			stockMarket.setOpen(ObjectUtil.toString(marketJson.get("open")));
			stockMarket.setHigh(ObjectUtil.toString(marketJson.get("high")));
			stockMarket.setLow(ObjectUtil.toString(marketJson.get("low")));
			stockMarket.setAmplitude(ObjectUtil.toString(marketJson.get("amplitude")));
			stockMarket.setVolume(ObjectUtil.toString(marketJson.get("volume")));
			stockMarket.setMktcap(ObjectUtil.toString(marketJson.get("mktcap")));
			stockMarket.setMarket(ObjectUtil.toString(marketJson.get("market")));
			stockMarket.setMarketDate(currentDate);

			stockMarkets.add(stockMarket);
		}
		pageable.setDataUS(stockMarkets);

		logger.info("获取美股数据列表成功");

		return pageable;
	}

	/**
	 * 请求获取港股数据列表
	 */
	public Pageable requestHKList(String page, String currentDate) throws Exception {
		logger.info("请求获取港股数据列表 page={}", page);

		Pageable pageable = new Pageable();
		Object[] stockMarketArray = getByList(pageable, page, configuration.getStockHKallUrl());

		List<StockMarketHK> stockMarkets = new ArrayList<StockMarketHK>();

		for (Object object : stockMarketArray) {
			JSONObject marketJson = JSONObject.fromObject(object);
			StockMarketHK stockMarket = new StockMarketHK();

			stockMarket.setSymbol(ObjectUtil.toString(marketJson.get("symbol")));
			stockMarket.setName(ObjectUtil.toString(marketJson.get("cname")));
			stockMarket.setEngName(ObjectUtil.toString(marketJson.get("engname")));
			stockMarket.setLasttrade(ObjectUtil.toString(marketJson.get("lasttrade")));
			stockMarket.setPrevClose(ObjectUtil.toString(marketJson.get("prevclose")));
			stockMarket.setOpen(ObjectUtil.toString(marketJson.get("open")));
			stockMarket.setHigh(ObjectUtil.toString(marketJson.get("high")));
			stockMarket.setLow(ObjectUtil.toString(marketJson.get("low")));
			stockMarket.setVolume(ObjectUtil.toString(marketJson.get("volume")));
			stockMarket.setAmount(ObjectUtil.toString(marketJson.get("amount")));
			stockMarket.setTicktime(ObjectUtil.toString(marketJson.get("ticktime")));
			stockMarket.setBuy(ObjectUtil.toString(marketJson.get("buy")));
			stockMarket.setSell(ObjectUtil.toString(marketJson.get("sell")));
			stockMarket.setHigh52week(ObjectUtil.toString(marketJson.get("high_52week")));
			stockMarket.setLow52week(ObjectUtil.toString(marketJson.get("low_52week")));
			stockMarket.setStocksSum(ObjectUtil.toString(marketJson.get("stocks_sum")));
			stockMarket.setPriceChange(ObjectUtil.toString(marketJson.get("pricechange")));
			stockMarket.setChangePercent(ObjectUtil.toString(marketJson.get("changepercent")));

			stockMarkets.add(stockMarket);
		}
		pageable.setDataHK(stockMarkets);

		logger.info("获取港股数据列表成功");

		return pageable;
	}

	/**
	 * 请求获取深圳股市数据列表
	 */
	public Pageable requestSZList(String page, String currentDate) throws Exception {
		logger.info("请求获取深圳股市数据列表 page={}", page);

		Pageable pageable = new Pageable();
		Object[] stockMarketArray = getByList(pageable, page, configuration.getStockSZallUrl());

		List<StockMarketSZ> stockMarkets = new ArrayList<StockMarketSZ>();

		for (Object object : stockMarketArray) {
			JSONObject marketJson = JSONObject.fromObject(object);
			StockMarketSZ stockMarket = new StockMarketSZ();

			stockMarket.setSymbol(ObjectUtil.toString(marketJson.get("symbol")));
			stockMarket.setName(ObjectUtil.toString(marketJson.get("name")));
			stockMarket.setTrade(ObjectUtil.toString(marketJson.get("trade")));
			stockMarket.setPriceChange(ObjectUtil.toString(marketJson.get("pricechange")));
			stockMarket.setChangePercent(ObjectUtil.toString(marketJson.get("changepercent")));
			stockMarket.setBuy(ObjectUtil.toString(marketJson.get("buy")));
			stockMarket.setSell(ObjectUtil.toString(marketJson.get("sell")));
			stockMarket.setSettlement(ObjectUtil.toString(marketJson.get("settlement")));
			stockMarket.setOpen(ObjectUtil.toString(marketJson.get("open")));
			stockMarket.setHigh(ObjectUtil.toString(marketJson.get("high")));
			stockMarket.setLow(ObjectUtil.toString(marketJson.get("low")));
			stockMarket.setVolume(ObjectUtil.toString(marketJson.get("volume")));
			stockMarket.setAmount(ObjectUtil.toString(marketJson.get("amount")));
			stockMarket.setTickTime(ObjectUtil.toString(marketJson.get("ticktime")));

			stockMarkets.add(stockMarket);
		}
		pageable.setDataSZ(stockMarkets);

		logger.info("获取深圳股市数据列表成功");

		return pageable;
	}

	/**
	 * 根据股票代码获取美股详细信息
	 */
	public StockMarketUS requestUSDetailBySymbol(String symbol) throws Exception {
		logger.info("根据股票代码获取美股详细信息 symbol={}", symbol);
		StockMarketUS stockMarket = null;

		Map<String, Object> params = new HashMap<String, Object>(); // 请求参数
		params.put("gid", symbol);
		params.put("key", configuration.getAppKey());
		logger.debug("请求数据已经封装 params={}", params);

		String response = HttpRequestUtil.sendData(configuration.getStockUSDetailUrl(), params, REQUEST_METHOD_GET);
		JSONObject responseJson = JSONObject.fromObject(response);
		if (responseJson.getInt("resultcode") == 200) {
			stockMarket = new StockMarketUS();

			JSONObject resultJson = JSONObject.fromObject(responseJson.get("result"));
			logger.debug("成功请求到数据 resultJson={}", resultJson);
			JSONArray jsonArray = JSONArray.fromObject(resultJson);
			Object[] stockMarketArray = jsonArray.toArray();

			JSONObject jsonArrayElement1 = JSONObject.fromObject(stockMarketArray[0]);
			JSONObject dataJson = JSONObject.fromObject(jsonArrayElement1.get("data"));

			stockMarket.setLastestPrice(ObjectUtil.toString(dataJson.get("lastestpri")));
			stockMarket.setOpenPrice(ObjectUtil.toString(dataJson.get("openpri")));
			stockMarket.setFormPrice(ObjectUtil.toString(dataJson.get("formpri")));
			stockMarket.setMaxPrice(ObjectUtil.toString(dataJson.get("maxpri")));
			stockMarket.setMinPrice(ObjectUtil.toString(dataJson.get("minpri")));
			stockMarket.setUppic(ObjectUtil.toString(dataJson.get("uppic")));
			stockMarket.setLimit(ObjectUtil.toString(dataJson.get("limit")));
			stockMarket.setTradeAmount(ObjectUtil.toString(dataJson.get("traAmount")));
			stockMarket.setAvgTradeNumber(ObjectUtil.toString(dataJson.get("avgTraNumber")));
			stockMarket.setMarkValue(ObjectUtil.toString(dataJson.get("markValue")));
			stockMarket.setMax52(ObjectUtil.toString(dataJson.get("max52")));
			stockMarket.setMin52(ObjectUtil.toString(dataJson.get("min52")));
			stockMarket.setEps(ObjectUtil.toString(dataJson.get("eps")));
			stockMarket.setPriearn(ObjectUtil.toString(dataJson.get("priearn")));
			stockMarket.setBeta(ObjectUtil.toString(dataJson.get("bete")));
			stockMarket.setDivident(ObjectUtil.toString(dataJson.get("divident")));
			stockMarket.setRor(ObjectUtil.toString(dataJson.get("ror")));
			stockMarket.setCapital(ObjectUtil.toString(dataJson.get("capital")));
			stockMarket.setAfterPicce(ObjectUtil.toString(dataJson.get("afterpic")));
			stockMarket.setAfterLimit(ObjectUtil.toString(dataJson.get("afterlimit")));
			stockMarket.setAfterUppic(ObjectUtil.toString(dataJson.get("afteruppic")));
			stockMarket.setAfterTime(ObjectUtil.toString(dataJson.get("aftertime")));
			stockMarket.setUsaTime(ObjectUtil.toString(dataJson.get("ustime")));
			stockMarket.setChinaTime(ObjectUtil.toString(dataJson.get("chtime")));

			JSONObject jsonArrayElement2 = JSONObject.fromObject(stockMarketArray[1]);
			JSONObject pictureJson = JSONObject.fromObject(jsonArrayElement2.get("gopicture"));
			stockMarket.setPictureMinUrl(ObjectUtil.toString(pictureJson.get("minurl")));
			stockMarket.setPictureMinWeek(ObjectUtil.toString(pictureJson.get("min_weekpic")));
			stockMarket.setPictureDayUrl(ObjectUtil.toString(pictureJson.get("dayurl")));
			stockMarket.setPictureWeekUrl(ObjectUtil.toString(pictureJson.get("weekurl")));
			stockMarket.setPictureMonthUrl(ObjectUtil.toString(pictureJson.get("monthurl")));
		} else {
			logger.warn("获取美股详细数据失败 {}-{}", responseJson.get("resultcode"), responseJson.get("reason"));
			throw new Exception("获取美股详细数据失败");
		}

		return stockMarket;
	}

	public StockMarketSH requestSHDetailBySymbol(String symbol) throws Exception {
		return null;
	}

	public StockMarketSZ requestSZDetailBySymbol(String symbol) throws Exception {
		return null;
	}

	/**
	 * 根据股票代码获取港股详细信息
	 */
	public StockMarketHK requestHKDetailBySymbol(String symbol) throws Exception {
		logger.info("根据股票代码获取港股详细信息 symbol={}", symbol);
		StockMarketHK stockMarket = null;

		Map<String, Object> params = new HashMap<String, Object>(); // 请求参数
		params.put("num", symbol);
		params.put("key", configuration.getAppKey());
		logger.debug("请求数据已经封装 params={}", params);

		String response = HttpRequestUtil.sendData(configuration.getStockHKDetailUrl(), params, REQUEST_METHOD_GET);
		JSONObject responseJson = JSONObject.fromObject(response);
		if (responseJson.getInt("resultcode") == 200) {
			stockMarket = new StockMarketHK();

			JSONObject resultJson = JSONObject.fromObject(responseJson.get("result"));
			logger.debug("成功请求到数据 resultJson={}", resultJson);
			JSONArray jsonArray = JSONArray.fromObject(resultJson);
			Object[] stockMarketArray = jsonArray.toArray();

			JSONObject jsonArrayElement1 = JSONObject.fromObject(stockMarketArray[0]);
			JSONObject dataJson = JSONObject.fromObject(jsonArrayElement1.get("data"));

			stockMarket.setOpenPrice(ObjectUtil.toString(dataJson.get("openpri")));
			stockMarket.setFormPrice(ObjectUtil.toString(dataJson.get("formpri")));
			stockMarket.setMaxPrice(ObjectUtil.toString(dataJson.get("maxpri")));
			stockMarket.setMinPrice(ObjectUtil.toString(dataJson.get("minpri")));
			stockMarket.setLastestPrice(ObjectUtil.toString(dataJson.get("lastestpri")));
			stockMarket.setUppic(ObjectUtil.toString(dataJson.get("uppic")));
			stockMarket.setLimit(ObjectUtil.toString(dataJson.get("limit")));
			stockMarket.setInprice(ObjectUtil.toString(dataJson.get("inpic")));
			stockMarket.setOutprice(ObjectUtil.toString(dataJson.get("outpic")));
			stockMarket.setTradeAmount(ObjectUtil.toString(dataJson.get("traAmount")));
			stockMarket.setTradeNumber(ObjectUtil.toString(dataJson.get("traNumber")));
			stockMarket.setPriearn(ObjectUtil.toString(dataJson.get("priearn")));
			stockMarket.setMax52(ObjectUtil.toString(dataJson.get("max52")));
			stockMarket.setMin52(ObjectUtil.toString(dataJson.get("min52")));
			stockMarket.setMarketDate(ObjectUtil.toString(dataJson.get("date")));
			stockMarket.setMarketTime(ObjectUtil.toString(dataJson.get("time")));

			JSONObject jsonArrayElement2 = JSONObject.fromObject(stockMarketArray[1]);
			JSONObject pictureJson = JSONObject.fromObject(jsonArrayElement2.get("gopicture"));
			stockMarket.setPictureMinUrl(ObjectUtil.toString(pictureJson.get("minurl")));
			stockMarket.setPictureDayUrl(ObjectUtil.toString(pictureJson.get("dayurl")));
			stockMarket.setPictureWeekUrl(ObjectUtil.toString(pictureJson.get("weekurl")));
			stockMarket.setPictureMonthUrl(ObjectUtil.toString(pictureJson.get("monthurl")));

			JSONObject jsonArrayElement3 = JSONObject.fromObject(stockMarketArray[2]);
			JSONObject hengshengJson = JSONObject.fromObject(jsonArrayElement3.get("hengsheng_data"));
			stockMarket.setHengShengDate(ObjectUtil.toString(hengshengJson.get("date")));
			stockMarket.setHengShengFormPrice(ObjectUtil.toString(hengshengJson.get("formpri")));
			stockMarket.setHengShengLastestPrice(ObjectUtil.toString(hengshengJson.get("lastestpri")));
			stockMarket.setHengShengLimit(ObjectUtil.toString(hengshengJson.get("limit")));
			stockMarket.setHengShengMax52(ObjectUtil.toString(hengshengJson.get("max52")));
			stockMarket.setHengShengMaxPrice(ObjectUtil.toString(hengshengJson.get("maxpri")));
			stockMarket.setHengShengMin52(ObjectUtil.toString(hengshengJson.get("min52")));
			stockMarket.setHengShengMinPrice(ObjectUtil.toString(hengshengJson.get("minpri")));
			stockMarket.setHengShengOpenPrice(ObjectUtil.toString(hengshengJson.get("openpri")));
			stockMarket.setHengShengTime(ObjectUtil.toString(hengshengJson.get("time")));
			stockMarket.setHengShengTradeAmount(ObjectUtil.toString(hengshengJson.get("traAmount")));
			stockMarket.setHengShengUppic(ObjectUtil.toString(hengshengJson.get("uppic")));
		} else {
			logger.warn("获取美股详细数据失败 {}-{}", responseJson.get("resultcode"), responseJson.get("reason"));
			throw new Exception("获取美股详细数据失败");
		}

		return stockMarket;
	}

	private Object[] getByList(Pageable pageable, String page, String url) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(); // 请求参数
		params.put("key", configuration.getAppKey());
		params.put("page", page); // 第几页,每页20条数据,默认第1页
		params.put("type", 80); // 每次返回80条数据
		logger.debug("请求数据已经封装 params={}", params);

		String response = HttpRequestUtil.sendData(url, params, REQUEST_METHOD_GET);
		JSONObject responseJson = JSONObject.fromObject(response);
		Object[] stockMarketArray = null;
		if (responseJson.getInt("error_code") == 0) {
			JSONObject resultJson = JSONObject.fromObject(responseJson.get("result"));
			logger.debug("成功请求到数据 resultJson={}", resultJson);

			pageable.setPageSize(resultJson.get("num").toString());
			pageable.setPage(resultJson.get("page").toString());
			pageable.setTotalCount(resultJson.get("totalCount").toString());

			JSONArray jsonArray = JSONArray.fromObject(resultJson.get("data"));
			stockMarketArray = jsonArray.toArray();
		} else {
			logger.warn("获取股票列表数据失败 {}-{}", responseJson.get("error_code"), responseJson.get("reason"));
			throw new Exception("获取股票列表数据失败");
		}

		return stockMarketArray;
	}

}
