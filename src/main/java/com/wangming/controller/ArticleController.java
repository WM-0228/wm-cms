/**   
 * 
 * @Title: ArticleController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午4:55:39 
 * @version: V1.0   
 */
package com.wangming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangming.service.ArticleService;

/** 
 * @ClassName: ArticleController 
 * @Description: 文章内容
 * @author:WM 
 * @date: 2019年11月14日 下午4:55:39  
 */
@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("lookDetail")
	public String lookDetail(Integer id,Model m){
		m.addAttribute("article", articleService.getArticleList(id));
		return "/article/articleDetail";
	}
}
