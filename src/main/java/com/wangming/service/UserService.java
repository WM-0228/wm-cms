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
import com.wangming.entity.Article;
import com.wangming.entity.Category;
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
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登入
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);
	/**
	 * 
	 * @Title: deleteArticle 
	 * @Description: 删除文章
	 * @param userId
	 * @return
	 * @return: boolean
	 */
	boolean deleteArticle(int aId);
	
	/**
	 * 
	 * @Title: getChannelId 
	 * @Description: 根据频道id获取分类集合
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> getChannelId(Integer channelId);

	/**
	 * 
	 * @Title: addUserArticle 
	 * @Description: 用戶添加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int addUserArticle(Article article);

	/**
	 * 
	 * @Title: addUserArticle 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateUserArticle(Article article);
}
