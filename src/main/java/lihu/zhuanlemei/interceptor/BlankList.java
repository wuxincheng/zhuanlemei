package lihu.zhuanlemei.interceptor;

import org.apache.commons.lang.StringUtils;

import lihu.zhuanlemei.util.PropertyReadUtil;

/**
 * 黑名单
 * 
 * @author wuxincheng(wxcking) 
 * @date 2016年3月14日 下午4:32:05 
 *
 */
public class BlankList {

	/**
	 * 判断是否能访问
	 * 
	 * @param remoteIpAddress 
	 * @return true：能访问，false：不能访问
	 */
	public static boolean isAccess(String remoteIpAddress) {
		if (StringUtils.isBlank(remoteIpAddress)) {
			return true;
		}
		
		PropertyReadUtil param = new PropertyReadUtil("blanklist");
		String ips = param.getString("ips");

		if (StringUtils.isBlank(ips)) {
			return true;
		}

		String[] ipArray = ips.split("\\|");
		if (null == ipArray || ipArray.length < 1) {
			return true;
		}

		for (String ipAddress : ipArray) {
			if (StringUtils.isNotBlank(ipAddress) && !"|".equals(ipAddress)) {
				if (ipAddress.equals(remoteIpAddress)) {
					return false;
				}
			}
		}
		
		return true;
	}

}
