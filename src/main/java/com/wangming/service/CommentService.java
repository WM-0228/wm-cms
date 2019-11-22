/**   
 * 
 * @Title: CommentService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月21日 上午11:07:01 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.PageInfo;
import com.wangming.entity.Collect;
import com.wangming.entity.Comment;

/** 
 * @ClassName: CommentService 
 * @Description: 评论类
 * @author:WM 
 * @date: 2019年11月21日 上午11:07:01  
 */
public interface CommentService {

	/**
	 * 
	 * @Title: getCommentList 
	 * @Description: 获取文章的评论
	 * @param articleId
	 * @return
	 * @return: PageInfo
	 */
	PageInfo getCommentList(Integer articleId,int pageNum,int pageSize);

	/**
	 * 
	 * @Title: publishComment 
	 * @Description: 发表评论
	 * @param com
	 * @return
	 * @return: int
	 */
	int publishComment(Comment com);
	/**
	 * 
	 * @Title: commentLike 
	 * @Description: 用户点赞
	 * @return
	 * @return: int
	 */
	int commentLike(Integer id);
	/**
	 * 
	 * @Title: getUserIdOrArticleId 
	 * @Description: 根据用户id和文章id查询  判断用户是否已经收藏或是取消收藏
	 * @return
	 * @return: Collect
	 */
	Collect getUserIdOrArticleId(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
	
	/**
	 * 
	 * @Title: collectArticle 
	 * @Description: 添加收藏
	 * @param userId
	 * @param articleId
	 * @param deleted
	 * @return
	 * @return: int
	 */
	int collectArticle(Integer userId,Integer articleId,Integer deleted);
	
	/**
	 * 
	 * @Title: cancelCollect 
	 * @Description: 取消收藏
	 * @param id
	 * @param deleted
	 * @return
	 * @return: int
	 */
	int cancelCollect(Integer id,Integer deleted);
}
