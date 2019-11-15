/**   
 * 
 * @Title: CategoryService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午7:24:40 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import com.wangming.entity.Category;

/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午7:24:40  
 */
public interface CategoryService {

	/** 
	 * @Title: getByChannelId 
	 * @Description: 获取指定频道的类型
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> getByChannelId(int channelId);

	
}
