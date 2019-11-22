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

import com.github.pagehelper.PageInfo;
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
}
