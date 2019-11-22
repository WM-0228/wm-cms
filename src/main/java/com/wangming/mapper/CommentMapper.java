/**   
 * 
 * @Title: CommentMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月21日 上午10:46:47 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import com.wangming.entity.Comment;

/** 
 * @ClassName: CommentMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月21日 上午10:46:47  
 */
public interface CommentMapper {

	/**
	 * 
	 * @Title: getCommentList 
	 * @Description: 获取文章的评论
	 * @param articleId
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> getCommentList(Integer articleId);
	
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
