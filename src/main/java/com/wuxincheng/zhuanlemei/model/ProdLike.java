package com.wuxincheng.zhuanlemei.model;

import java.io.Serializable;

/**
 * 用户产品点赞
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月23日 下午5:31:03
 * 
 */
public class ProdLike implements Serializable {

	private static final long serialVersionUID = 5101762374272334624L;

	private String userid;

	private String prodid;

	private String likeTime;

	/** 点赞是否有效: 0-有效, 1-无效 */
	private String likeState;

	// ======= 用户信息
	private String nickName;
	private String socialPicPath;
	private String picPath;
	private String memo;
	private String userGroup;
	private String position;
	private String sex;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
	}

	public String getLikeState() {
		return likeState;
	}

	public void setLikeState(String likeState) {
		this.likeState = likeState;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSocialPicPath() {
		return socialPicPath;
	}

	public void setSocialPicPath(String socialPicPath) {
		this.socialPicPath = socialPicPath;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
