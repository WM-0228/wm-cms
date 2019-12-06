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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wangming.common.CmsAssert;
import com.wangming.common.ConstantClass;
import com.wangming.entity.Article;
import com.wangming.entity.ArticleType;
import com.wangming.entity.Collect;
import com.wangming.entity.Image;
import com.wangming.entity.User;
import com.wangming.service.ArticleService;
import com.wangming.service.CommentService;

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
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 
	 * @Title: lookDetail 
	 * @Description: 根据id获取文章详情
	 * @param id
	 * @param m
	 * @return
	 * @return: String
	 * @throws ParseException 
	 */
	@RequestMapping("lookDetail")
	public String lookDetail(Integer id,Model m,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="3")int pageSize,HttpServletRequest request) throws ParseException{
		Article articleList = articleService.getArticleList(id);
		CmsAssert.AssertTrueHtml(articleList != null,"文章不存在");
		m.addAttribute("article",articleList);
		PageInfo commentList = commentService.getCommentList(id, pageNum, pageSize);
		m.addAttribute("info",commentList);
		
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		Collect collect = commentService.getUserIdOrArticleId((user == null ? null : user.getId()), id);
		m.addAttribute("collect", collect);
		
		if(articleList.getArticleType() == ArticleType.HTML){
			return "/article/articleDetail";
		}else{
			Gson gson = new Gson();
			/*List<Image> listImg = gson.fromJson(articleList.getContent(), List.class);*/
			/*JSONObject json = new JSONObject();*/
			//将字符串转换成json对象
			List<Image> listImg = (List<Image>) new JSONParser().parse(articleList.getContent());
			articleList.setImageList(listImg);
			return "/article/articleDetailImage";
		}
		
		
	}
}
