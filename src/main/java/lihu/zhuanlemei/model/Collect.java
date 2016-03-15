package lihu.zhuanlemei.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 产品集
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月3日 下午2:47:08
 * 
 */
public class Collect implements Serializable {

	private static final long serialVersionUID = -2048622308852729384L;

	/** 产品集主键 */
	private String collectid;

	/** 产品集名称 */
	private String collectName;

	/** 集封面路径 */
	private String coverImgPath;

	/** 隐藏封面路径 */
	private String coverImgPathHidden;

	/** 封面图片文件 */
	private MultipartFile coverImgFile;

	/** 背景颜色 */
	private String bgColor;

	/** 用户创建ID */
	private String userid;

	/** 产品总数 */
	private Integer productSum;

	/** 被收藏次数 */
	private Integer collectSum;

	/** 说明 */
	private String memo;

	/** 更新标志 */
	private String updateState;

	/** 更新时间 */
	private String updateTime;

	/** 创建时间 */
	private String createTime;

	/** 产品集状态 */
	private String collectState;

	private String recommend;

	/** 产品集赞数 */
	private Integer likeSum;

	/** 产品集赞度 */
	private Integer likeScore;

	/** 产品集反对数 */
	private Integer unLikeSum;

	/** 产品集反对度 */
	private Integer unLikeScore;

	/** 产品集评论数 */
	private Integer commentSum;

	/** 详细分析 */
	private String detailContent;

	private String nickName;

	// ====== 新增字段 start ======
	/** 手动录入 */
	private String manulFlag;
	/** 日收益 */
	private String dayIncome;
	/** 净值 */
	private String nav;
	/** 总收益 */
	private String totalIncome;
	/** 周收益 */
	private String weekIncome;
	/** 月收益 */
	private String monthIncome;
	/** 年收益 */
	private String yearIncome;
	/** 盈亏比 */
	private String profitLossRate;
	/** 胜率 */
	private String winRate;
	/** 排名比 */
	private String rankRate;
	// ====== 新增字段 end ======

	public Collect() {
	}

	public String getCoverImgPath() {
		return coverImgPath;
	}

	public void setCoverImgPath(String coverImgPath) {
		this.coverImgPath = coverImgPath;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getProductSum() {
		return productSum;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}

	public Integer getCollectSum() {
		return collectSum;
	}

	public void setCollectSum(Integer collectSum) {
		this.collectSum = collectSum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUpdateState() {
		return updateState;
	}

	public void setUpdateState(String updateState) {
		this.updateState = updateState;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}

	public String getCollectName() {
		return collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName;
	}

	public String getCollectState() {
		return collectState;
	}

	public void setCollectState(String collectState) {
		this.collectState = collectState;
	}

	public MultipartFile getCoverImgFile() {
		return coverImgFile;
	}

	public void setCoverImgFile(MultipartFile coverImgFile) {
		this.coverImgFile = coverImgFile;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCoverImgPathHidden() {
		return coverImgPathHidden;
	}

	public void setCoverImgPathHidden(String coverImgPathHidden) {
		this.coverImgPathHidden = coverImgPathHidden;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Integer likeSum) {
		this.likeSum = likeSum;
	}

	public Integer getLikeScore() {
		return likeScore;
	}

	public void setLikeScore(Integer likeScore) {
		this.likeScore = likeScore;
	}

	public Integer getUnLikeSum() {
		return unLikeSum;
	}

	public void setUnLikeSum(Integer unLikeSum) {
		this.unLikeSum = unLikeSum;
	}

	public Integer getUnLikeScore() {
		return unLikeScore;
	}

	public void setUnLikeScore(Integer unLikeScore) {
		this.unLikeScore = unLikeScore;
	}

	public Integer getCommentSum() {
		return commentSum;
	}

	public void setCommentSum(Integer commentSum) {
		this.commentSum = commentSum;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public String getManulFlag() {
		return manulFlag;
	}

	public void setManulFlag(String manulFlag) {
		this.manulFlag = manulFlag;
	}

	public String getDayIncome() {
		return dayIncome;
	}

	public void setDayIncome(String dayIncome) {
		this.dayIncome = dayIncome;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getWeekIncome() {
		return weekIncome;
	}

	public void setWeekIncome(String weekIncome) {
		this.weekIncome = weekIncome;
	}

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getProfitLossRate() {
		return profitLossRate;
	}

	public void setProfitLossRate(String profitLossRate) {
		this.profitLossRate = profitLossRate;
	}

	public String getWinRate() {
		return winRate;
	}

	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}

	public String getRankRate() {
		return rankRate;
	}

	public void setRankRate(String rankRate) {
		this.rankRate = rankRate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
