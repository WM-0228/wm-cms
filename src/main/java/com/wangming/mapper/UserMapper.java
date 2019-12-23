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

import com.wangming.entity.Article;
import com.wangming.entity.Category;
import com.wangming.entity.Collect;
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
	@Select("SELECT * FROM cms_user WHERE username = #{value} limit 1")
	User findByName(String username);

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
	 * @return: int
	 */
	int deleteArticle(int aId);
	
	/**
	 * 
	 * @Title: getChannelId 
	 * @Description: 根据频道id获取分类集合
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@Select("SELECT * FROM cms_category WHERE channel_id = ${value}")
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
	 * @Title: getArticleById 
	 * @Description: //根据文章id获取文章内容用于回显
	 * @param articleId
	 * @return
	 * @return: Article
	 */
	@Select("SELECT * FROM cms_article WHERE id = ${value}")
	Article getArticleById(Integer articleId);
	
	/**
	 * 
	 * @Title: addUserArticle 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateUserArticle(Article article);
	/**
	 * 
	 * @Title: addImage 
	 * @Description: 添加多张图片的文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int addImage(Article article);
	
	/**
	 * 
	 * @Title: getCollectList 
	 * @Description: 获取收藏
	 * @return
	 * @return: List<Collect>
	 */
	List<Collect> getCollectList(Integer userId);
	
	@Select("SELECT id FROM cms_user")
	int[] getUserId();
	
}
