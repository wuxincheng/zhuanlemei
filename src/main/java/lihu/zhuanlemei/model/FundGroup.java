package lihu.zhuanlemei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 基金组合
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年3月9日 下午1:30:58
 * 
 */
public class FundGroup implements Serializable {

	private static final long serialVersionUID = 4563823058562590473L;

	private Integer groupId;

	private String name;

	private String type;

	private BigDecimal nav;

	private Date navDate;

	private BigDecimal rise;

	private Date foundDate;

	private BigDecimal weekIncome;

	private BigDecimal monthIncome;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getNav() {
		return nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}

	public Date getNavDate() {
		return navDate;
	}

	public void setNavDate(Date navDate) {
		this.navDate = navDate;
	}

	public BigDecimal getRise() {
		return rise;
	}

	public void setRise(BigDecimal rise) {
		this.rise = rise;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public BigDecimal getWeekIncome() {
		return weekIncome;
	}

	public void setWeekIncome(BigDecimal weekIncome) {
		this.weekIncome = weekIncome;
	}

	public BigDecimal getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

}
