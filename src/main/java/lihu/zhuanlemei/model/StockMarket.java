package lihu.zhuanlemei.model;

/**
 * 股票行情数据
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月9日 下午2:11:56
 * 
 */
public class StockMarket {

	/** 股票名称 */
	private String stockName;

	/** 股票代码 */
	private String stockCode;

	/** 行业版块 */
	private String category;

	/** 最新价 */
	private String price;

	/** 涨跌额 */
	private String diffiencePrice;

	private String change;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
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

	public String getDiffiencePrice() {
		return diffiencePrice;
	}

	public void setDiffiencePrice(String diffiencePrice) {
		this.diffiencePrice = diffiencePrice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

}
