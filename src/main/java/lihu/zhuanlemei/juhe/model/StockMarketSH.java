package lihu.zhuanlemei.juhe.model;

import java.io.Serializable;

/**
 * 沪股
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月9日 下午4:56:49
 * 
 */
public class StockMarketSH implements Serializable {

	private static final long serialVersionUID = 7373165015714049377L;

	private String symbol;

	private String name;

	private String trade;

	private String priceChange;

	private String changePercent;

	private String buy;

	private String sell;

	private String settleMent;

	private String open;

	private String high;

	private String low;

	private String volume;

	private String amount;

	private String code;

	private String tickTime;

	private String tickDate;

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

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
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

	public String getSettleMent() {
		return settleMent;
	}

	public void setSettleMent(String settleMent) {
		this.settleMent = settleMent;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTickTime() {
		return tickTime;
	}

	public void setTickTime(String tickTime) {
		this.tickTime = tickTime;
	}

	public String getTickDate() {
		return tickDate;
	}

	public void setTickDate(String tickDate) {
		this.tickDate = tickDate;
	}

}
