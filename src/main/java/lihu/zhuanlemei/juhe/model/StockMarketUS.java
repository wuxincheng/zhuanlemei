package lihu.zhuanlemei.juhe.model;

import java.io.Serializable;

/**
 * 美股
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月10日 上午9:23:47
 * 
 */
public class StockMarketUS implements Serializable {

	private static final long serialVersionUID = -8806924771542147607L;

	private String symbol;

	private String name;

	private String category;

	private String price;

	private String diffience;

	private String changePercent;

	private String preclose;

	private String open;

	private String high;

	private String low;

	private String amplitude;

	private String volume;

	private String mktcap;

	private String market;

	private String marketDate;

	// ------------------------

	private String lastestPrice;

	private String openPrice;

	private String formPrice;

	private String maxPrice;

	private String minPrice;

	private String uppic;

	private String limit;

	private String tradeAmount;

	private String avgTradeNumber;

	private String markValue;

	private String max52;

	private String min52;

	private String eps;

	private String priearn;

	private String beta;

	private String divident;

	private String ror;

	private String capital;

	private String afterPicce;

	private String afterLimit;

	private String afterUppic;

	private String afterTime;

	private String usaTime;

	private String chinaTime;

	private String pictureMinUrl;

	private String pictureMinWeek;

	private String pictureDayUrl;

	private String pictureWeekUrl;

	private String pictureMonthUrl;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiffience() {
		return diffience;
	}

	public void setDiffience(String diffience) {
		this.diffience = diffience;
	}

	public String getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}

	public String getPreclose() {
		return preclose;
	}

	public void setPreclose(String preclose) {
		this.preclose = preclose;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getMktcap() {
		return mktcap;
	}

	public void setMktcap(String mktcap) {
		this.mktcap = mktcap;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getMarketDate() {
		return marketDate;
	}

	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}

	public String getLastestPrice() {
		return lastestPrice;
	}

	public void setLastestPrice(String lastestPrice) {
		this.lastestPrice = lastestPrice;
	}

	public String getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(String openPrice) {
		this.openPrice = openPrice;
	}

	public String getFormPrice() {
		return formPrice;
	}

	public void setFormPrice(String formPrice) {
		this.formPrice = formPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getUppic() {
		return uppic;
	}

	public void setUppic(String uppic) {
		this.uppic = uppic;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getAvgTradeNumber() {
		return avgTradeNumber;
	}

	public void setAvgTradeNumber(String avgTradeNumber) {
		this.avgTradeNumber = avgTradeNumber;
	}

	public String getMarkValue() {
		return markValue;
	}

	public void setMarkValue(String markValue) {
		this.markValue = markValue;
	}

	public String getMax52() {
		return max52;
	}

	public void setMax52(String max52) {
		this.max52 = max52;
	}

	public String getMin52() {
		return min52;
	}

	public void setMin52(String min52) {
		this.min52 = min52;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getPriearn() {
		return priearn;
	}

	public void setPriearn(String priearn) {
		this.priearn = priearn;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String getDivident() {
		return divident;
	}

	public void setDivident(String divident) {
		this.divident = divident;
	}

	public String getRor() {
		return ror;
	}

	public void setRor(String ror) {
		this.ror = ror;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getAfterPicce() {
		return afterPicce;
	}

	public void setAfterPicce(String afterPicce) {
		this.afterPicce = afterPicce;
	}

	public String getAfterLimit() {
		return afterLimit;
	}

	public void setAfterLimit(String afterLimit) {
		this.afterLimit = afterLimit;
	}

	public String getAfterUppic() {
		return afterUppic;
	}

	public void setAfterUppic(String afterUppic) {
		this.afterUppic = afterUppic;
	}

	public String getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}

	public String getUsaTime() {
		return usaTime;
	}

	public void setUsaTime(String usaTime) {
		this.usaTime = usaTime;
	}

	public String getChinaTime() {
		return chinaTime;
	}

	public void setChinaTime(String chinaTime) {
		this.chinaTime = chinaTime;
	}

	public String getPictureMinUrl() {
		return pictureMinUrl;
	}

	public void setPictureMinUrl(String pictureMinUrl) {
		this.pictureMinUrl = pictureMinUrl;
	}

	public String getPictureMinWeek() {
		return pictureMinWeek;
	}

	public void setPictureMinWeek(String pictureMinWeek) {
		this.pictureMinWeek = pictureMinWeek;
	}

	public String getPictureDayUrl() {
		return pictureDayUrl;
	}

	public void setPictureDayUrl(String pictureDayUrl) {
		this.pictureDayUrl = pictureDayUrl;
	}

	public String getPictureWeekUrl() {
		return pictureWeekUrl;
	}

	public void setPictureWeekUrl(String pictureWeekUrl) {
		this.pictureWeekUrl = pictureWeekUrl;
	}

	public String getPictureMonthUrl() {
		return pictureMonthUrl;
	}

	public void setPictureMonthUrl(String pictureMonthUrl) {
		this.pictureMonthUrl = pictureMonthUrl;
	}

}
