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
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	 * @Description: 根据id获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getArticleList(Integer id);

	/** 
	 * @Title: getCategoryId 
	 * @Description: 获取某一个分类下的某个频道
	 * @param channelId
	 * @param categoryId
	 * @param pageNum
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getCategoryId(@Param("channelId")int channelId, @Param("categoryId")int categoryId);
	/**
	 * 
	 * @Title: getWritings 
	 * @Description: 获取所有的文章  用于上一篇下一篇
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getWritings();
	
	/**
	 * 
	 * @Title: getUserById 
	 * @Description: 获取当前用户的所有文章
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getUserById(Integer userId);
	/**
	 * 
	 * @Title: getByUserId 
	 * @Description: 根据文章id获取文章对象  看他是否存在  不用判断他是否通过
	 * @param userId
	 * @return
	 * @return: Article
	 */
	@Select("SELECT id,title,user_id userId,hot,status from cms_article where id = ${value}")
	@ResultType(Article.class)
	Article getById(Integer id);
	/**
	 * 
	 * @Title: getArticleList 
	 * @Description: 管理员用于文章管理
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getArticle(int status);
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
	int updateStatus(@Param("status")Integer status,@Param("id")Integer id);
	/**
	 * 
	 * @Title: getImageList 
	 * @Description: 获取最新图片标题
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getImageList(int pageNum);

	/** 
	 * @Title: test 
	 * @Description: 修改点击量
	 * @param articleList
	 * @return: void
	 */
	@Update("UPDATE cms_article SET hits = ${hits} WHERE id = #{id}")
	void test(Article articleList);
	
	/**
	 * 
	 * @Title: findByCategoryId 
	 * @Description: 获取和频道对应的分类id
	 * @param channelId
	 * @return
	 * @return: int[]
	 */
	@Select("SELECT id FROM cms_category WHERE channel_id = #{value}")
	int[] findByCategoryId(int channelId);
}
