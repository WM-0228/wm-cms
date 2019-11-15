/**   
 * 
 * @Title: ArticleMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午2:27:43 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangming.entity.Article;

/** 
 * @ClassName: ArticleMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午2:27:43  
 */
public interface ArticleMapper {

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
	 * @return: List<Article>
	 */
	List<Article> hotList();
	
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
	 * @Title: getCategoryId 
	 * @Description: TODO
	 * @param channelId
	 * @param categoryId
	 * @param pageNum
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getCategoryId(@Param("channelId")int channelId, @Param("categoryId")int categoryId);
}
