/**   
 * 
 * @Title: ChannelServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午1:37:20 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangming.entity.Channel;
import com.wangming.mapper.ChannelMapper;
import com.wangming.service.ChannelService;

/** 
 * @ClassName: ChannelServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午1:37:20  
 */
@Service
public class ChannelServiceImpl implements ChannelService{

	@Autowired
	private ChannelMapper mapper;

	@Override
	public List<Channel> getList() {
		// TODO Auto-generated method stub
		return mapper.getChannel();
	}


	
}
