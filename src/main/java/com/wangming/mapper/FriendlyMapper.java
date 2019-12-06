/**   
 * 
 * @Title: FriendlyMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月27日 上午8:46:42 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wangming.entity.Friendly;

/** 
 * @ClassName: FriendlyMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月27日 上午8:46:42  
 */
public interface FriendlyMapper {

	/**
	 * 
	 * @Title: addFriendly 
	 * @Description: 添加友情链接
	 * @param friendly
	 * @return
	 * @return: int
	 */
	@Insert("INSERT INTO cms_friendly_link VALUES (0,#{text},#{url},now())")
	int addFriendly(Friendly friendly);
	/**
	 * 
	 * @Title: getFriendlyList 
	 * @Description: 查询链接列表
	 * @return
	 * @return: List<Friendly>
	 */
	@Select("SELECT * FROM cms_friendly_link ORDER BY created DESC LIMIT 0,10")
	List<Friendly> getFriendlyList();
}
