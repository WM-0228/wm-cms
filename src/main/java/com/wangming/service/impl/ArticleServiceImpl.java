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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.wangming.common.ConstantClass;
import com.wangming.entity.Article;
import com.wangming.mapper.ArticleMapper;
import com.wangming.service.ArticleService;

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
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public List<Article> newList(int i) {
		// TODO Auto-generated method stub
		return articleMapper.newList(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo hotList(int pageNum,int pageSize) {
		
		List<Article> range = redisTemplate.opsForList().range("ArticleList", (pageNum - 1) * pageSize,(pageNum - 1) * pageSize + pageSize - 1);
		if(!range.isEmpty() && range.size() > 0){
			return new PageInfo(range);
		}else{
			PageHelper.startPage(pageNum,ConstantClass.PAGE_SIZE);
			System.err.println("=======================使用mysql数据库");
			List<Article> hotList = articleMapper.hotList();
			redisTemplate.opsForList().rightPushAll("ArticleList", hotList.toArray());
			return new PageInfo(hotList);
		}
	}

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

	@Override
	public List<Article> getArticleList() {
		// TODO Auto-generated method stub
		return articleMapper.getWritings();
	}

	@Override
	public PageInfo getUserById(Integer userId,int pageNum) {
		PageHelper.startPage(pageNum, 3);
		return new PageInfo(articleMapper.getUserById(userId));
	}

	@Override
	public Article getById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
	}

	@Override
	public PageInfo getArticleList(int status,int pageNum) {
		PageHelper.startPage(pageNum, ConstantClass.PAGE_SIZE);
		return new PageInfo(articleMapper.getArticle(status));
	}

	@Override
	public Article getDetailArticle(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getDetailArticle(id);
	}

	@Override
	public boolean updateStatus(Integer status, Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.updateStatus(status, id) > 0;
	}

	@Override
	public PageInfo getImageList(int pageNum) {
		// TODO Auto-generated method stub
		return new PageInfo(articleMapper.getImageList(pageNum));
	}

	@Override
	public void test(Article articleList) {
		// TODO Auto-generated method stub
		articleMapper.test(articleList);
	}

}
