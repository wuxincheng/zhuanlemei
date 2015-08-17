package com.wuxincheng.zhuanlemei.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundCollectDao;
import com.wuxincheng.zhuanlemei.dao.FundCollectUserDao;
import com.wuxincheng.zhuanlemei.model.FundCollectUser;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.DateUtil;

@Service("collectUserService")
public class CollectUserService {

	@Resource
	private FundCollectUserDao collectUserDao;
	
	@Resource
	private FundCollectDao collectDao;

	public void insert(FundCollectUser collectUser) {
		collectUserDao.insert(collectUser);
	}

	public void delete(FundCollectUser collectUser) {
		collectUserDao.delete(collectUser);
	}
	
	public FundCollectUser query(String collectid, String userid) {
		FundCollectUser collectUser = new FundCollectUser();
		collectUser.setCollectid(collectid);
		collectUser.setUserid(userid);
		return collectUser = collectUserDao.query(collectUser);
	}

	/**
	 * 收藏
	 * 
	 * @param collectid
	 * @param userid
	 */
	public void collect(String collectid, String userid) {
		FundCollectUser deleteOrQueryCollectUser = new FundCollectUser();
		deleteOrQueryCollectUser.setCollectid(collectid);
		deleteOrQueryCollectUser.setUserid(userid);
		
		FundCollectUser querycu = collectUserDao.query(deleteOrQueryCollectUser);
		if (null == querycu) { // 收藏
			FundCollectUser collectUser = new FundCollectUser();
			collectUser.setCollectid(collectid);
			collectUser.setUserid(userid);
			collectUser.setCreateTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:ss"));
			collectUser.setCollectState(Constants.DEFAULT_STATE);
			
			collectUserDao.insert(collectUser);
			
			// 产品集收藏+1
			collectDao.addCollectSum(collectid);
		} else { // 取消收藏
			collectUserDao.delete(deleteOrQueryCollectUser);
			
			// 产品集收藏-1
			collectDao.cutCollectSum(collectid);
		}
	}

	/**
	 * 根据用户主键查询产品集主键
	 * 
	 * @param userid
	 * @return
	 */
	public List<FundCollectUser> queryCollects(String userid) {
		if (null == userid) {
			return null;
		}
		return collectUserDao.queryCollects(userid);
	}

}
