package lihu.zhuanlemei.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 读取配置文件类
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年2月24日 下午6:58:45
 * 
 */
public class PropertyReadUtil {

	/** 资源属性文件properties */
	private ResourceBundle bundle;

	/**
	 * 初始化时读取资源属性文件
	 * 
	 * @param FileName
	 *            文件名称
	 */
	public PropertyReadUtil(String FileName) {
		bundle = ResourceBundle.getBundle(FileName);
	}

	/**
	 * 根据Key获取值
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (key == null || key.equals("") || key.equals("null")) {
			return "";
		}
		String result = "";
		try {
			result = bundle.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return result;
	}

}
