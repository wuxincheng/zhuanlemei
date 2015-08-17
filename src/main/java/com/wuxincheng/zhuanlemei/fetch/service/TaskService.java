package com.wuxincheng.zhuanlemei.fetch.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.zhuanlemei.dao.TaskDao;
import com.wuxincheng.zhuanlemei.model.Task;
import com.wuxincheng.zhuanlemei.util.Constants;
import com.wuxincheng.zhuanlemei.util.DateUtil;

/**
 * 任务Service
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月17日 上午9:54:16
 * 
 */
@Service("taskService")
public class TaskService {
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Resource
	private TaskDao taskDao;

	/**
	 * 创建任务列表
	 */
	public void createFetchTask() {
		String currentDate = DateUtil.getCurrentDate(new Date(), "yyyy-MM-dd");
		String currentDateTime = DateUtil.getCurrentDate(new Date(), "yyyy-MM-dd HH:mm:ss");

		// 生成任务1: 生成抓取基金行情列表数据任务
		Task fundMarketInfoTask = taskDao.query("fundMarketInfo", currentDate);
		if (fundMarketInfoTask == null) { // 任务不存在
			logger.info("抓取基金行情列表数据任务不存在");
			// 创建任务
			fundMarketInfoTask = new Task();
			fundMarketInfoTask.setTaskName("fundMarketInfo");
			fundMarketInfoTask.setTaskDate(currentDate);
			fundMarketInfoTask.setTaskFlag("0"); // 未执行的
			fundMarketInfoTask.setTaskState(Constants.DEFAULT_STATE);
			fundMarketInfoTask.setUpdateTime(currentDateTime);
			fundMarketInfoTask.setTaskMemo("抓取基金行情列表数据任务");
			taskDao.insert(fundMarketInfoTask);
			
			logger.info("抓取基金行情列表数据任务已创建");
		} else {
			logger.debug("抓取基金行情列表数据任务已经存在");
		}

		// 生成任务2: 
		// 生成任务3: 
		// 生成任务4: 
	}

}
