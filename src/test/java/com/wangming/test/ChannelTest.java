/**   
 * 
 * @Title: ChannelTest.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.test 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午1:57:20 
 * @version: V1.0   
 */
package com.wangming.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wangming.entity.Channel;
import com.wangming.service.ChannelService;

/** 
 * @ClassName: ChannelTest 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午1:57:20  
 */

public class ChannelTest extends Common{
	
	@Autowired
	private ChannelService service;
	
	@Test
	public void channelTest(){
		List<Channel> list = service.getList();
		list.forEach(x -> System.out.println(x));
	}

}
