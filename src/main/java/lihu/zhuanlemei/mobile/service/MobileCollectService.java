package lihu.zhuanlemei.mobile.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lihu.zhuanlemei.dao.CollectDao;
import lihu.zhuanlemei.model.Collect;

@Service("mobileCollectService")
public class MobileCollectService {
	
	@Resource
	private CollectDao collectDao;

	public Map<String, Object> queryPager(Map<String, Object> queryParam) {
		// 返回结果
		Map<String, Object> reault = new HashMap<String, Object>();
		
		// 查询条件
		// int start, int end
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("start", start);
		// params.put("end", end);
		
		// int totalCount = newsDao.queryCountByParams(queryParam); // 总记录数
		List<Collect> collects = collectDao.queryAll(); // 当前页的数据
		
		reault.put("collects", collects);
		// reault.put("totalCount", totalCount);
		
		return reault;
	}
	
}
