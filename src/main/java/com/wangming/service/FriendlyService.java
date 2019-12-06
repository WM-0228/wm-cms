/**   
 * 
 * @Title: FriendlyService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月27日 上午8:49:40 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wangming.entity.Friendly;

/** 
 * @ClassName: FriendlyService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月27日 上午8:49:40  
 */
public interface FriendlyService {

	/**
	 * 
	 * @Title: addFriendly 
	 * @Description: 添加友情链接
	 * @param friendly
	 * @return
	 * @return: int
	 */
	int addFriendly(Friendly friendly);
	/**
	 * 
	 * @Title: getFriendlyList 
	 * @Description: 查询链接列表
	 * @return
	 * @return: List<Friendly>
	 */
	List<Friendly> getFriendlyList();
}
