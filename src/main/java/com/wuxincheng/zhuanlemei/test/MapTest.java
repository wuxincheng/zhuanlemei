package com.wuxincheng.zhuanlemei.test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("111", "1aaa");
		map.put("222", "1a2a");
		map.put("333", null);
		map.put(null, "456");
		map.put("", "789");
		System.out.println(map);
	}
	
}
