/**   
 * 
 * @Title: ConmentServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月21日 上午11:07:34 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.common.ConstantClass;
import com.wangming.entity.Collect;
import com.wangming.entity.Comment;
import com.wangming.mapper.CommentMapper;
import com.wangming.service.CommentService;

/** 
 * @ClassName: ConmentServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月21日 上午11:07:34  
 */
@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public PageInfo getCommentList(Integer articleId,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return new PageInfo(commentMapper.getCommentList(articleId));
	}

	@Override
	public int publishComment(Comment com) {
		// TODO Auto-generated method stub
		return commentMapper.publishComment(com);
	}

	@Override
	public int commentLike(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.commentLike(id);
	}

	@Override
	public Collect getUserIdOrArticleId(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return commentMapper.getUserIdOrArticleId(userId, articleId);
	}

	@Override
	public int collectArticle(Integer userId, Integer articleId, Integer deleted) {
		// TODO Auto-generated method stub
		return commentMapper.collectArticle(userId, articleId, deleted);
	}

	@Override
	public int cancelCollect(Integer id, Integer deleted) {
		// TODO Auto-generated method stub
		return commentMapper.cancelCollect(id, deleted);
	}

	
}
