package lihu.zhuanlemei.juhe.model;

import java.io.Serializable;

/**
 * 港股
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月10日 上午9:51:32
 * 
 */
public class StockMarketHK implements Serializable {

	private static final long serialVersionUID = 8463861222121751582L;

	private String symbol;

	private String name;

	private String engName;

	private String lasttrade;

	private String prevClose;

	private String open;

	private String high;

	private String low;

	private String volume;

	private String amount;

	private String ticktime;

	private String buy;

	private String sell;

	private String high52week;

	private String low52week;

	private String stocksSum;

	private String priceChange;

	private String changePercent;

	// --------------------------

	private String openPrice;

	private String formPrice;

	private String maxPrice;

	private String minPrice;

	private String lastestPrice;

	private String uppic;

	private String limit;

	private String inprice;

	private String outprice;

	private String tradeAmount;

	private String tradeNumber;

	private String priearn;

	private String max52;

	private String min52;

	private String marketDate;

	private String marketTime;

	private String pictureMinUrl;

	private String pictureDayUrl;

	private String pictureWeekUrl;

	private String pictureMonthUrl;

	private String hengShengDate;

	private String hengShengFormPrice;

	private String hengShengLastestPrice;

	private String hengShengLimit;

	private String hengShengMax52;

	private String hengShengMin52;

	private String hengShengMaxPrice;

	private String hengShengMinPrice;

	private String hengShengOpenPrice;

	private String hengShengTime;

	private String hengShengTradeAmount;

	private String hengShengUppic;

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

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getLasttrade() {
		return lasttrade;
	}

	public void setLasttrade(String lasttrade) {
		this.lasttrade = lasttrade;
	}

	public String getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(String prevClose) {
		this.prevClose = prevClose;
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTicktime() {
		return ticktime;
	}

	public void setTicktime(String ticktime) {
		this.ticktime = ticktime;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getHigh52week() {
		return high52week;
	}

	public void setHigh52week(String high52week) {
		this.high52week = high52week;
	}

	public String getLow52week() {
		return low52week;
	}

	public void setLow52week(String low52week) {
		this.low52week = low52week;
	}

	public String getStocksSum() {
		return stocksSum;
	}

	public void setStocksSum(String stocksSum) {
		this.stocksSum = stocksSum;
	}

	public String getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}

	public String getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
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

	public String getLastestPrice() {
		return lastestPrice;
	}

	public void setLastestPrice(String lastestPrice) {
		this.lastestPrice = lastestPrice;
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

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public String getOutprice() {
		return outprice;
	}

	public void setOutprice(String outprice) {
		this.outprice = outprice;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getPriearn() {
		return priearn;
	}

	public void setPriearn(String priearn) {
		this.priearn = priearn;
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

	public String getMarketDate() {
		return marketDate;
	}

	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}

	public String getMarketTime() {
		return marketTime;
	}

	public void setMarketTime(String marketTime) {
		this.marketTime = marketTime;
	}

	public String getPictureMinUrl() {
		return pictureMinUrl;
	}

	public void setPictureMinUrl(String pictureMinUrl) {
		this.pictureMinUrl = pictureMinUrl;
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

	public String getHengShengDate() {
		return hengShengDate;
	}

	public void setHengShengDate(String hengShengDate) {
		this.hengShengDate = hengShengDate;
	}

	public String getHengShengFormPrice() {
		return hengShengFormPrice;
	}

	public void setHengShengFormPrice(String hengShengFormPrice) {
		this.hengShengFormPrice = hengShengFormPrice;
	}

	public String getHengShengLastestPrice() {
		return hengShengLastestPrice;
	}

	public void setHengShengLastestPrice(String hengShengLastestPrice) {
		this.hengShengLastestPrice = hengShengLastestPrice;
	}

	public String getHengShengLimit() {
		return hengShengLimit;
	}

	public void setHengShengLimit(String hengShengLimit) {
		this.hengShengLimit = hengShengLimit;
	}

	public String getHengShengMax52() {
		return hengShengMax52;
	}

	public void setHengShengMax52(String hengShengMax52) {
		this.hengShengMax52 = hengShengMax52;
	}

	public String getHengShengMin52() {
		return hengShengMin52;
	}

	public void setHengShengMin52(String hengShengMin52) {
		this.hengShengMin52 = hengShengMin52;
	}

	public String getHengShengMaxPrice() {
		return hengShengMaxPrice;
	}

	public void setHengShengMaxPrice(String hengShengMaxPrice) {
		this.hengShengMaxPrice = hengShengMaxPrice;
	}

	public String getHengShengMinPrice() {
		return hengShengMinPrice;
	}

	public void setHengShengMinPrice(String hengShengMinPrice) {
		this.hengShengMinPrice = hengShengMinPrice;
	}

	public String getHengShengOpenPrice() {
		return hengShengOpenPrice;
	}

	public void setHengShengOpenPrice(String hengShengOpenPrice) {
		this.hengShengOpenPrice = hengShengOpenPrice;
	}

	public String getHengShengTime() {
		return hengShengTime;
	}

	public void setHengShengTime(String hengShengTime) {
		this.hengShengTime = hengShengTime;
	}

	public String getHengShengTradeAmount() {
		return hengShengTradeAmount;
	}

	public void setHengShengTradeAmount(String hengShengTradeAmount) {
		this.hengShengTradeAmount = hengShengTradeAmount;
	}

	public String getHengShengUppic() {
		return hengShengUppic;
	}

	public void setHengShengUppic(String hengShengUppic) {
		this.hengShengUppic = hengShengUppic;
	}

}
