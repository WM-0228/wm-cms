/**   
 * 
 * @Title: UserServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月13日 下午12:41:07 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.common.ConstantClass;
import com.wangming.entity.User;
import com.wangming.mapper.UserMapper;
import com.wangming.service.UserService;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月13日 下午12:41:07  
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public PageInfo userList(String name,int pageNum) {
		PageHelper.startPage(pageNum, ConstantClass.PAGE_SIZE);
		return new PageInfo(userMapper.userList(name));
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUser(userId);
	}

	@Override
	public boolean upUserStatus(int userId, int status) {
		// TODO Auto-generated method stub
		return userMapper.upUserStatus(userId, status) > 0;
	}

	/* (non Javadoc) 
	 * @Title: findByName
	 * @Description: TODO
	 * @param username
	 * @return 
	 * @see com.wangming.service.UserService#findByName(java.lang.String) 
	 */
	@Override
	public User findByName(String username) {
		// TODO Auto-generated methousernamed stub
		return userMapper.findByName(username);
	}

	/* (non Javadoc) 
	 * @Title: register
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.wangming.service.UserService#register(com.wangming.entity.User) 
	 */
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		return userMapper.register(user);
	}

	
}
