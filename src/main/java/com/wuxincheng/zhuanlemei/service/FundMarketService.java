package com.wuxincheng.zhuanlemei.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.FundMarketDao;
import com.wuxincheng.zhuanlemei.model.FundMarket;

@Service("fundMarketService")
public class FundMarketService {

	@Resource
	private FundMarketDao fundMarketDao;
	
	public Map<String, Object> queryPager(Map<String, Object> queryParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<FundMarket> fundMarkets = fundMarketDao.queryPager(queryParam);
		
		int totalCount = fundMarketDao.queryCount(queryParam); // 总记录数
		
		result.put("fundMarkets", fundMarkets);
		result.put("totalCount", totalCount);
		
		return result;
	}

	public FundMarket queryDetailByFundCode(String fundCode) {
		return fundMarketDao.queryDetail(fundCode);
	}

}
