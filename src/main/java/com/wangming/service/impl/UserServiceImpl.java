/**   
 * 
 * @Title: UserServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月13日 下午12:41:07 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.common.ConstantClass;
import com.wangming.common.Md5;
import com.wangming.entity.Article;
import com.wangming.entity.Category;
import com.wangming.entity.User;
import com.wangming.mapper.UserMapper;
import com.wangming.service.UserService;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月13日 下午12:41:07  
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public PageInfo userList(String name,int pageNum) {
		PageHelper.startPage(pageNum, ConstantClass.PAGE_SIZE);
		return new PageInfo(userMapper.userList(name));
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUser(userId);
	}

	@Override
	public boolean upUserStatus(int userId, int status) {
		// TODO Auto-generated method stub
		return userMapper.upUserStatus(userId, status) > 0;
	}

	/* (non Javadoc) 
	 * @Title: findByName
	 * @Description: TODO
	 * @param username
	 * @return 
	 * @see com.wangming.service.UserService#findByName(java.lang.String) 
	 */
	@Override
	public User findByName(String username) {
		// TODO Auto-generated methousernamed stub
		return userMapper.findByName(username);
	}

	/* (non Javadoc) 
	 * @Title: register
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.wangming.service.UserService#register(com.wangming.entity.User) 
	 */
	@Override
	public int register(User user) {
		//加盐
		user.setPassword(Md5.password(user.getPassword(),user.getUsername().substring(0,2)));
		return userMapper.register(user);
	}

	/* (non Javadoc) 
	 * @Title: login
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.wangming.service.UserService#login(com.wangming.entity.User) 
	 */
	@Override
	public User login(User user) {
		
		User findByName = userMapper.findByName(user.getUsername());
		if(findByName == null){
			return null;
		}
		String password = Md5.password(user.getPassword(),user.getUsername().substring(0, 2));
		user.setPassword(password);
		if(password.equals(findByName.getPassword())){
			return userMapper.login(user); 
		}else{
			return null;
		}
	}

	/* (non Javadoc) 
	 * @Title: deleteArticle
	 * @Description: TODO
	 * @param aId
	 * @return 
	 * @see com.wangming.service.UserService#deleteArticle(int) 
	 */
	@Override
	public boolean deleteArticle(int aId) {
		// TODO Auto-generated method stub
		return userMapper.deleteArticle(aId) > 0;
	}

	/* (non Javadoc) 
	 * @Title: getChannelId
	 * @Description: TODO
	 * @param channelId
	 * @return 
	 * @see com.wangming.service.UserService#getChannelId(java.lang.Integer) 
	 */
	@Override
	public List<Category> getChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return userMapper.getChannelId(channelId);
	}

	/* (non Javadoc) 
	 * @Title: addUserArticle
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.wangming.service.UserService#addUserArticle(com.wangming.entity.Article) 
	 */
	@Override
	public int addUserArticle(Article article) {
		// TODO Auto-generated method stub
		return userMapper.addUserArticle(article);
	}

	/* (non Javadoc) 
	 * @Title: updateUserArticle
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.wangming.service.UserService#updateUserArticle(com.wangming.entity.Article) 
	 */
	@Override
	public int updateUserArticle(Article article) {
		// TODO Auto-generated method stub
		return userMapper.updateUserArticle(article);
	}

	/* (non Javadoc) 
	 * @Title: addImage
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.wangming.service.UserService#addImage(com.wangming.entity.Article) 
	 */
	@Override
	public int addImage(Article article) {
		// TODO Auto-generated method stub
		return userMapper.addImage(article);
	}

	
}
