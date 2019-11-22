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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangming.entity.Collect;
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
	/**
	 * 
	 * @Title: getUserIdOrArticleId 
	 * @Description: 根据用户id和文章id查询  判断用户是否已经收藏或是取消收藏
	 * @return
	 * @return: Collect
	 */
	@Select("SELECT * FROM cms_collect WHERE userId = #{userId} AND articleId = #{articleId}")
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
	@Insert("INSERT INTO cms_collect values (0,#{userId},#{articleId},now(),#{deleted})")
	int collectArticle(@Param("userId")Integer userId,@Param("articleId")Integer articleId,@Param("deleted")Integer deleted);
	/**
	 * 
	 * @Title: cancelCollect 
	 * @Description: 取消收藏
	 * @param id
	 * @param deleted
	 * @return
	 * @return: int
	 */
	@Update("UPDATE cms_collect SET deleted = #{deleted} WHERE id = #{id}")
	int cancelCollect(@Param("id")Integer id,@Param("deleted")Integer deleted);
}
