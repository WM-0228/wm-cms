/**   
 * 
 * @Title: LinkMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月24日 上午11:58:44 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wangming.entity.Link;

/** 
 * @ClassName: LinkMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月24日 上午11:58:44  
 */
public interface LinkMapper {

	/**
	 * 
	 * @Title: getLinkList 
	 * @Description: 查询所有的友情链接
	 * @return
	 * @return: List<Link>
	 */
	@Select("SELECT * FROM cms_link")
	List<Link> getLinkList();
	
	/**
	 * 
	 * @Title: addLink 
	 * @Description: 添加友情链接
	 * @param link
	 * @return
	 * @return: int
	 */
	@Insert("INSERT INTO cms_link values (0,#{url},#{name},now())")
	int addLink(Link link);
}
