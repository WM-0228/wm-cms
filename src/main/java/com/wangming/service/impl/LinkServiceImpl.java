/**   
 * 
 * @Title: LinkServiceImpl.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service.impl 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月24日 下午12:07:18 
 * @version: V1.0   
 */
package com.wangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.entity.Link;
import com.wangming.mapper.LinkMapper;
import com.wangming.service.LinkService;

/** 
 * @ClassName: LinkServiceImpl 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月24日 下午12:07:18  
 */
@Service
public class LinkServiceImpl implements LinkService {

	
	@Autowired
	private LinkMapper linkMapper;

	@Override
	public PageInfo<Link> getLinkList(int pageNum) {
		PageHelper.startPage(pageNum, 10);
		return new PageInfo<Link>(linkMapper.getLinkList());
	}

	@Override
	public int addLink(Link link) {
		// TODO Auto-generated method stub
		return linkMapper.addLink(link);
	}

}
