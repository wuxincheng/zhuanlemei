package lihu.zhuanlemei.juhe;

import java.util.ArrayList;
import java.util.List;

import lihu.zhuanlemei.juhe.model.StockMarketHK;
import lihu.zhuanlemei.juhe.model.StockMarketSH;
import lihu.zhuanlemei.juhe.model.StockMarketSZ;
import lihu.zhuanlemei.juhe.model.StockMarketUS;

/**
 * 分页
 * 
 * @author wuxincheng(wxcking) 
 * @date 2016年3月9日 下午4:54:09 
 *
 */
public class Pageable {

	/** 总条数 */
	private String totalCount;

	/** 当前页数 */
	private String page;

	/** 是否为最后一页 */
	private boolean isLastPage;

	/** 每页显示条数 */
	private String pageSize;

	/** 美国股市列表 */
	private List<StockMarketUS> dataUS = new ArrayList<StockMarketUS>();

	/** 香港股市列表 */
	private List<StockMarketHK> dataHK = new ArrayList<StockMarketHK>();

	/** 深圳股市列表 */
	private List<StockMarketSZ> dataSZ = new ArrayList<StockMarketSZ>();

	/** 沪股列表 */
	private List<StockMarketSH> dataSH = new ArrayList<StockMarketSH>();

	public Pageable() {
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public List<StockMarketUS> getDataUS() {
		return dataUS;
	}

	public void setDataUS(List<StockMarketUS> dataUS) {
		this.dataUS = dataUS;
	}

	public List<StockMarketHK> getDataHK() {
		return dataHK;
	}

	public void setDataHK(List<StockMarketHK> dataHK) {
		this.dataHK = dataHK;
	}

	public List<StockMarketSZ> getDataSZ() {
		return dataSZ;
	}

	public void setDataSZ(List<StockMarketSZ> dataSZ) {
		this.dataSZ = dataSZ;
	}

	public List<StockMarketSH> getDataSH() {
		return dataSH;
	}

	public void setDataSH(List<StockMarketSH> dataSH) {
		this.dataSH = dataSH;
	}

}
