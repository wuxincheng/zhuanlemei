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
	
	
	
}
