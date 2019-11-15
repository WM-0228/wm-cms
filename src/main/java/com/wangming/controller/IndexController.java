/**   
 * 
 * @Title: ChannelController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午1:19:08 
 * @version: V1.0   
 */
package com.wangming.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.wangming.entity.Article;
import com.wangming.entity.Category;
import com.wangming.entity.Channel;
import com.wangming.service.ArticleService;
import com.wangming.service.CategoryService;
import com.wangming.service.ChannelService;

/** 
 * @ClassName: ChannelController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午1:19:08  
 */
@Controller
public class IndexController {

	//频道
	@Autowired
	private ChannelService channelService;
	//文章
	@Autowired
	private ArticleService articleService;
	//种类
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = {"index","/"})
	public String index(Model m,@RequestParam(defaultValue="1")int pageNum){
		//获取所有的频道
		List<Channel> list = channelService.getList();
		//获取最新的标题
		List<Article> newList = articleService.newList(5);
		//获取热门文章
		PageInfo hotList = articleService.hotList(pageNum);
		
		m.addAttribute("articleList", newList);
		m.addAttribute("channels",list);
		m.addAttribute("info", hotList);
		return "index";
	}
	
	/**
	 * 
	 * @Title: channel 
	 * @Description: 查看某一个频道
	 * @param m
	 * @param pageNum
	 * @return
	 * @return: String
	 */
	@RequestMapping("channel")
	public String channel(Model m,@RequestParam(defaultValue="1")int pageNum,
				@RequestParam(defaultValue="1")int channelId,
				@RequestParam(defaultValue="0")int categoryId){
		
		//获取所有的频道
		List<Channel> list = channelService.getList();
		m.addAttribute("channels",list);
		
		//获取指定频道下的所有类型
		List<Category> categoryList = categoryService.getByChannelId(channelId);
		m.addAttribute("categorys", categoryList);
		
		PageInfo info = articleService.getCategoryId(channelId,categoryId,pageNum);
		m.addAttribute("info", info);
		
		List<Article> newList = articleService.newList(5);
		m.addAttribute("articleList", newList);
		
		m.addAttribute("channelId", channelId);
		m.addAttribute("categoryId", categoryId);
		
		return "/user/channelindex";
	}
}
