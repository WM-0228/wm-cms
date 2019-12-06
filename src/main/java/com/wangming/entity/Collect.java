/**   
 * 
 * @Title: Collect.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.entity 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月22日 下午1:28:11 
 * @version: V1.0   
 */
package com.wangming.entity;

import java.io.Serializable;
import java.sql.Date;

/** 
 * @ClassName: Collect 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月22日 下午1:28:11  
 */
public class Collect implements Serializable{
	
	private static final long serialVersionUID = -2633889414048493002L;
	
	//收藏id
	private Integer id;
	//用户id
	private Integer userId;
	//文章id
	private Integer articleId;
	//收藏时间
	private Date created;
	//是否删除
	private int deleted;
	
	private Article article;
	
	
	
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the deleted
	 */
	public int getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
		Collect other = (Collect) obj;
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
	
	

}
