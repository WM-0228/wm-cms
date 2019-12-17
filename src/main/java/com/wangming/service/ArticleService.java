/**   
 * 
 * @Title: ArticleService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午4:53:45 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.wangming.entity.Article;

/** 
 * @ClassName: ArticleService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午4:53:45  
 */
public interface ArticleService {

	/**
	 * 
	 * @Title: newList 
	 * @Description: 获取最新的文章
	 * @param i 获取的个数
	 * @return
	 * @return: List<Article>
	 */
	List<Article> newList(int i);

	/**
	 * 
	 * @Title: hotList 
	 * @Description: 获取热门文章
	 * @return
	 * @return: PageInfo
	 */
	PageInfo hotList(int pageNum,int pageSize);

	/**
	 * 
	 * @Title: getArticleList 
	 * @Description: 获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getArticleList(Integer id);
	
	/**
	 * 
	 * @Title: getCategoryId 
	 * @Description: 获取当前文章或者类型的小类型
	 * @param channelId
	 * @param categoryId
	 * @param pageNum
	 * @return
	 * @return: PageInfo
	 */
	public PageInfo getCategoryId(int channelId, int categoryId, int pageNum);
	
	/**
	 * 
	 * @Title: getArticleList 
	 * @Description: 获取所有的文章
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getArticleList();
	/**
	 * 
	 * @Title: getUserById 
	 * @Description: 获取当前用户的所有文章
	 * @return
	 * @return: List<Article>
	 */
	PageInfo getUserById(Integer userId,int pageNum);
	/**
	 * 
	 * @Title: getByUserId 
	 * @Description: 根据文章id获取文章对象  看他是否存在  不用判断他是否通过
	 * @param userId
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);
	/**
	 * 
	 * @Title: getArticleList 
	 * @Description: 管理员用于文章管理
	 * @return
	 * @return: PageInfo
	 */
	PageInfo getArticleList(int status,int pageNum);
	/**
	 * 
	 * @Title: getDetailArticle 
	 * @Description: 根据id获取文章,只要是没有被删除  都要查到
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getDetailArticle(Integer id);
	/**
	 * 
	 * @Title: updateStatus 
	 * @Description: 管理员审核
	 * @param status
	 * @return
	 * @return: int
	 */
	boolean updateStatus(Integer status,Integer id);
	/**
	 * 
	 * @Title: getImageList 
	 * @Description: 获取最新图片标题
	 * @return
	 * @return: PageInfo
	 */
	PageInfo getImageList(int pageNum);
}
