package com.wuxincheng.zhuanlemei.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundMarketDao;
import com.wuxincheng.zhuanlemei.model.FundMarket;

@Service("fundMarketService")
public class FundMarketService {

	@Resource
	private FundMarketDao fundMarketDao;
	
	public List<FundMarket> queryAll() {
		return fundMarketDao.queryAll();
	}

	public FundMarket queryDetailByFundCode(String fundCode) {
		return fundMarketDao.queryDetail(fundCode);
	}

}
