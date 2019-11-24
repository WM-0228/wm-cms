/**   
 * 
 * @Title: LinkService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月24日 下午12:06:54 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.PageInfo;
import com.wangming.entity.Link;

/** 
 * @ClassName: LinkService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月24日 下午12:06:54  
 */
public interface LinkService {

	/**
	 * 
	 * @Title: getLinkList 
	 * @Description: 查询所有的友情链接
	 * @return
	 * @return: PageInfo<Link>
	 */
	PageInfo<Link> getLinkList(int pageNum);
	
	/**
	 * 
	 * @Title: addLink 
	 * @Description: 添加友情链接
	 * @param link
	 * @return
	 * @return: int
	 */
	int addLink(Link link);
}
