/**   
 * 
 * @Title: UtilsTest.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.test 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月25日 下午8:00:57 
 * @version: V1.0   
 */
package com.wangming.test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wangming.entity.Channel;
import com.wangming.service.ChannelService;
import com.wangming.utils.StringUtils;

/** 
 * @ClassName: UtilsTest 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月25日 下午8:00:57  
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class UtilsTest {

	@Autowired
	private ChannelService service;
	
	@Test
	public void myTest(){
		/*boolean httpUrl = StringUtils.isHttpUrl("123");
		System.out.println(httpUrl);
		System.out.println(1);*/
		List<Channel> list = service.getList();
		list.forEach(x -> System.out.println(x));
	}
	
	
	@Test
	public void masd(){
		int a = 3;
		int b = 2;
		switch (a) {
		case 1:
			if(b == 1){
				System.out.println(1);
			}
			break;
		case 2:
			if(b == 0){
				System.out.println(0);
			}
			break;
		case 3:
			if(b == 2){
				System.out.println(2);
			}
			break;
		default:
			break;
		}
	}
	
	
	public static void main(String[] args) {
		/*Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		Map<String,Object> map3 = new HashMap<String,Object>();
		map1.put("birthday","2001-02-14");
		map2.put("birthday","2001-02-14");
		map3.put("birthday","2001-02-14");
		List<Map> list = new ArrayList();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		for (Map map : list) {
			Object object = map.get(map);
			System.out.println(object);
			Collection values = map.values();
			for (Object object : values) {
				System.out.println(object);
			}
		}
		*/
	}
}
