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
	PageInfo hotList(int pageNum);

	/**
	 * 
	 * @Title: getArticleList 
	 * @Description: 获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getArticleList(Integer id);
	
	public PageInfo getCategoryId(int channelId, int categoryId, int pageNum);
}
