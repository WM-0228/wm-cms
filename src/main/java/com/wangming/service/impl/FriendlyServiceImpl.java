/**   
 * 
 * @Title: FriendlyServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月27日 上午8:51:59 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangming.entity.Friendly;
import com.wangming.mapper.FriendlyMapper;
import com.wangming.service.FriendlyService;

/** 
 * @ClassName: FriendlyServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月27日 上午8:51:59  
 */
@Service
public class FriendlyServiceImpl implements FriendlyService{

	@Autowired
	private FriendlyMapper friendlyMapper;
	
	@Override
	public int addFriendly(Friendly friendly) {
		// TODO Auto-generated method stub
		return friendlyMapper.addFriendly(friendly);
	}

	@Override
	public List<Friendly> getFriendlyList() {
		// TODO Auto-generated method stub
		return friendlyMapper.getFriendlyList();
	}

}
