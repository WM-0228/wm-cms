/**   
 * 
 * @Title: LinkController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月24日 下午12:10:36 
 * @version: V1.0   
 */
package com.wangming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.stat.TableStat.Mode;
import com.github.pagehelper.PageInfo;
import com.wangming.entity.Link;
import com.wangming.service.LinkService;

/** 
 * @ClassName: LinkController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月24日 下午12:10:36  
 */
@Controller
@RequestMapping("link")
public class LinkController {

	@Autowired
	private LinkService linkService;
	
	/**
	 * 
	 * @Title: link 
	 * @Description: 查询所有的连接
	 * @return
	 * @return: String
	 */
	@RequestMapping("link")
	public String link(@RequestParam(defaultValue="1")int pageNum,Model m){
		PageInfo<Link> linkList = linkService.getLinkList(pageNum);
		m.addAttribute("info",linkList);
		return "/admin/link/link";
	}
	
	
	@GetMapping("addLink")
	public String addLink(Model m){
		m.addAttribute("link",new Link());
		return "/admin/link/addLink";
	}
	
	
	
	@PostMapping("addLink")
	public String addLink(@Valid @ModelAttribute("link")Link link,BindingResult result,Model m){
		if(result.hasErrors()){
			return "/admin/link/addLink";
		}
		
		int addLink = linkService.addLink(link);
		return "redirect:/link/link";
	}
	
	
}
