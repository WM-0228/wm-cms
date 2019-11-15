/**   
 * 
 * @Title: UserService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月13日 下午12:40:50 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.wangming.entity.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月13日 下午12:40:50  
 */
public interface UserService {

	/**
	 * 
	 * @Title: userList 
	 * @Description: 用户的列表展示
	 * @param name
	 * @return
	 * @return: PageInfo
	 */
	PageInfo userList(String name,int pageNum);
	
	User getUser(Integer userId);
	
	boolean upUserStatus(@Param("userId")int userId,@Param("status")int status);

	/** 
	 * @Title: findByName 
	 * @Description: 根据姓名查找用户是否存在
	 * @param username
	 * @return
	 * @return: User
	 */
	User findByName(String username);

	/** 
	 * @Title: register 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int register(User user);
}
