/**   
 * 
 * @Title: CommentController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月21日 上午11:11:25 
 * @version: V1.0   
 */
package com.wangming.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangming.common.CmsAssert;
import com.wangming.common.ConstantClass;
import com.wangming.common.MsgResult;
import com.wangming.entity.Collect;
import com.wangming.entity.Comment;
import com.wangming.entity.User;
import com.wangming.service.CommentService;
import com.wangming.service.UserService;

/** 
 * @ClassName: CommentController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月21日 上午11:11:25  
 */
@Controller
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("publish")
	@ResponseBody
	public MsgResult publishComment(Comment com){
		System.out.println("------------------------"+com);
		int publishComment = commentService.publishComment(com);
		if(publishComment > 0){
			return new MsgResult(1,"发表成功",null);
		}else{
			return new MsgResult(1,"发表失败",null);
		}
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(User user,HttpServletRequest request,Integer articleId){
		User login = userService.login(user);
		if(login != null){
			request.getSession().setAttribute(ConstantClass.USER_KEY, login);
			return "redirect:/article/lookDetail?id="+articleId;
		}else{
			request.setAttribute("user",user);
			return "/user/login";
		}
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(Model m,User user,Integer articleId){
		User existUser = userService.findByName(user.getUsername());
		CmsAssert.AssertTrue(existUser == null, "该用户名已存在");
		
		int resul = userService.register(user);
		CmsAssert.AssertTrue(resul > 0, "用戶注冊失敗,请稍后再试");
		
		return "redirect:/article/lookDetail?id="+articleId+"&flag=true";
	}
	

	@RequestMapping("commentLike")
	@ResponseBody
	public MsgResult commentLike(Integer id){
		int commentLike = commentService.commentLike(id);
		if(commentLike > 0){
			return new MsgResult(1,"发表成功",null);
		}else{
			return new MsgResult(2,"发表失败",null);
		}
	}
	
	
	@RequestMapping("collect")
	@ResponseBody
	public MsgResult collect(Integer articleId,HttpServletRequest request,Integer deleted){
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(user != null,"你还没有去登入请先登入");
		Collect collect = commentService.getUserIdOrArticleId((user == null ? null : user.getId()), articleId);
		if(deleted == 1){
			if(collect != null){
				CmsAssert.AssertTrue(collect.getDeleted() != 1,"您已经收藏");
			}
			if(collect == null){
				int collectArticle = commentService.collectArticle(user.getId(), articleId, deleted);
				if(collectArticle > 0){
					return new MsgResult(1,"收藏成功",null);
				}else{
					return new MsgResult(2,"收藏失败",null);
				}
			}else{
				int cancelCollect = commentService.cancelCollect(collect.getId(), deleted);
				if(cancelCollect > 0){
					return new MsgResult(1,"收藏成功",null);
				}else{
					return new MsgResult(2,"收藏失败",null);
				}
			}
		}else{
			CmsAssert.AssertTrue(collect.getDeleted() != 0,"您还没有收藏");
			int cancelCollect = commentService.cancelCollect(collect.getId(), deleted);
				if(cancelCollect > 0){
					return new MsgResult(1,"取消成功",null);
				}else{
					return new MsgResult(2,"取消失败",null);
				}
		}
	}
	
}
