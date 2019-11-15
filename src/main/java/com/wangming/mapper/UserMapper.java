/**   
 * 
 * @Title: UserMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月13日 上午11:29:51 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangming.entity.User;


/** 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月13日 上午11:29:51  
 */
public interface UserMapper {

	/**
	 * 
	 * @Title: userList 
	 * @Description: 用户的列表展示
	 * @param name
	 * @return
	 * @return: List<User>
	 */
	List<User> userList(String name);
	
	/**
	 * 
	 * @Title: getUser 
	 * @Description: 根据用户id查找用户是否存在
	 * @param userId
	 * @return
	 * @return: User
	 */
	/*@Select("SELECT * FROM cms_user WHERE id = ${value}")*/
	User getUser(Integer userId);
	
	/**
	 * 
	 * @Title: upUserStatus 
	 * @Description: 修改状态操作
	 * @param userId
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update("UPDATE cms_user SET locked = ${status} where id = ${userId}")
	int upUserStatus(@Param("userId")int userId,@Param("status")int status);

	/** 
	 * @Title: findByName 
	 * @Description: TODO
	 * @param username
	 * @return
	 * @return: User
	 */
	@Select("SELECT * FROM cms_user WHERE username = #{value}")
	User findByName(String username);

	int register(User user);
}
