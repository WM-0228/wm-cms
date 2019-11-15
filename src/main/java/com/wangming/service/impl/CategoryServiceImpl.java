/**   
 * 
 * @Title: CategoryServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午7:27:17 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangming.entity.Category;
import com.wangming.mapper.CategoryMapper;
import com.wangming.service.CategoryService;

/** 
 * @ClassName: CategoryServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午7:27:17  
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getByChannelId(int channelId) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------"+channelId);
		return categoryMapper.getByChannelId(channelId);
	}

}
