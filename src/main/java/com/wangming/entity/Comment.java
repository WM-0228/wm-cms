/**   
 * 
 * @Title: Common.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.entity 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月21日 上午10:28:56 
 * @version: V1.0   
 */
package com.wangming.entity;

import java.io.Serializable;
import java.sql.Date;

/** 
 * @ClassName: Common 
 * @Description: 评论表
 * @author:WM 
 * @date: 2019年11月21日 上午10:28:56  
 */
public class Comment implements Serializable{

	private static final long serialVersionUID = -7098157108251607509L;

	private Integer id;
	
	private Integer articleId;
	
	private Article article;
	
	private Integer userId;
	
	private Integer commentLike;
	
	private User user;
	
	private String content;
	
	private Date created;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	
	/**
	 * @return the commentLike
	 */
	public Integer getCommentLike() {
		return commentLike;
	}


	/**
	 * @param commentLike the commentLike to set
	 */
	public void setCommentLike(Integer commentLike) {
		this.commentLike = commentLike;
	}


	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}


	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the articleId
	 */
	public Integer getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/* (non Javadoc) 
	 * @Title: toString
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", userId="
				+ userId + ", user=" + user + ", content=" + content
				+ ", created=" + created + "]";
	}

	/* (non Javadoc) 
	 * @Title: hashCode
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#hashCode() 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non Javadoc) 
	 * @Title: equals
	 * @Description: TODO
	 * @param obj
	 * @return 
	 * @see java.lang.Object#equals(java.lang.Object) 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/** 
	 * @Title:Comment
	 * @Description:TODO  
	 */
	public Comment() {
		super();
	}

	/** 
	 * @Title:Comment
	 * @Description:TODO 
	 * @param id
	 * @param articleId
	 * @param userId
	 * @param user
	 * @param content
	 * @param created 
	 */
	public Comment(Integer id, Integer articleId, Integer userId, User user,
			String content, Date created) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.userId = userId;
		this.user = user;
		this.content = content;
		this.created = created;
	}
	
	
			

}
