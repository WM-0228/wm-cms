/**   
 * 
 * @Title: ArticleServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午4:54:20 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.common.ConstantClass;
import com.wangming.entity.Article;
import com.wangming.mapper.ArticleMapper;
import com.wangming.service.ArticleService;
import com.wangming.test.Common;

/** 
 * @ClassName: ArticleServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午4:54:20  
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Article> newList(int i) {
		// TODO Auto-generated method stub
		return articleMapper.newList(i);
	}

	@Override
	public PageInfo hotList(int pageNum) {
		PageHelper.startPage(pageNum,ConstantClass.PAGE_SIZE);
		return new PageInfo(articleMapper.hotList());
	}

	/* (non Javadoc) 
	 * @Title: getArticleList
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.wangming.service.ArticleService#getArticleList(java.lang.Integer) 
	 */
	@Override
	public Article getArticleList(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleList(id);
	}

	@Override
	public PageInfo getCategoryId(int channelId, int categoryId, int pageNum) {
		PageHelper.startPage(pageNum, ConstantClass.PAGE_SIZE);
		return new PageInfo(articleMapper.getCategoryId(channelId, categoryId));
	}


}
