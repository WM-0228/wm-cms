/**   
 * 
 * @Title: CategoryMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午7:29:50 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import com.wangming.entity.Category;

/** 
 * @ClassName: CategoryMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午7:29:50  
 */
public interface CategoryMapper {

	/** 
	 * @Title: getByChannelId 
	 * @Description: 制定的类型
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> getByChannelId(int channelId);

	
}
