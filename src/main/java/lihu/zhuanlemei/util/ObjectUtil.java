package lihu.zhuanlemei.util;

/**
 * Object工具类
 * 
 * @author wuxincheng(wxcking) 
 * @date 2016年3月9日 下午5:11:22 
 *
 */
public class ObjectUtil {

	public static String toString(Object object) {
		if (null == object) {
			return null;
		}
		return String.valueOf(object);
	}

}
