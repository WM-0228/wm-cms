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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangming.common.ConstantClass;
import com.wangming.entity.Article;
import com.wangming.entity.Category;
import com.wangming.entity.Channel;
import com.wangming.entity.Friendly;
import com.wangming.entity.Link;
import com.wangming.entity.User;
import com.wangming.mapper.EsRepository;
import com.wangming.service.ArticleService;
import com.wangming.service.CategoryService;
import com.wangming.service.ChannelService;
import com.wangming.service.CommentService;
import com.wangming.service.FriendlyService;
import com.wangming.service.LinkService;
import com.wangming.utils.HLUtils;
import com.wangming.utils.PageUtil;

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
	//评论
	@Autowired
	private CommentService commentService;
	
	//友情链接
	@Autowired
	private FriendlyService friendlyService;
	
	//调用es
	@Autowired
	private EsRepository esRepository;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	//注入spring线程池
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 文章列表
	 * @param m
	 * @param pageNum
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"index","/"})
	public String index(Model m,@RequestParam(defaultValue="1")int pageNum,String key,HttpServletRequest request){
		
		
		//获取所有的频道
		List<Channel> list = channelService.getList();
		//获取最新的标题
		List<Article> newList = articleService.newList(5);
		//获取热门文章
		PageInfo hotList = null;
		//根据key有没有只判断用户是否查询
		if(null != key && key != ""){
			AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class,pageNum,
					3, new String[] { "title" }, "id", key);
			List<Article> content = (List<Article>) selectObjects.getContent();
			hotList = PageUtil.page(request,"/?key="+key,3,content,(int)selectObjects.getTotalElements(),pageNum, null, null);
			System.err.println("===========================");
		}else{
			//没有使用搜索
			Long size = redisTemplate.opsForList().size("goods_list");
			int totalPage = (int) (size % 3 == 0 ? size / 3 : (size / 3) + 1);
			PageInfo page = articleService.hotList(pageNum,3);
			List list2 = page.getList();
			
			hotList = PageUtil.page(request,"/", 3,page.getList(),totalPage, pageNum, null, null);
		}
		
		//获取最新的图片
		PageInfo imageList = articleService.getImageList(5);
		
		List<Friendly> friendlyList = friendlyService.getFriendlyList();
		
		m.addAttribute("friendlys", friendlyList);
		m.addAttribute("images", imageList);
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
	
	/**
	 * 
	 * @Title: chapter 
	 * @Description: 上一篇下一篇
	 * @param value
	 * @param aid
	 * @param m
	 * @return
	 * @return: String
	 */
	@RequestMapping("chapter")
	public String chapter(String value,Integer aid,Model m,HttpServletRequest request){
		List<Article> articleList = articleService.getArticleList();
		List<Article> list2 = new ArrayList<Article>();
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
			int i = 0;
			int j = 0;
			for (Article article : articleList) {
				if(value.equals("next")){
					i++;
					if(article.getId() == aid){
						j = i + 1;
					}
					list2.add(article);
					if(i == j){
						break;
					}
				}else if(value.equals("pre")){
					if(article.getId() == aid){
						break;
					}
					list2.add(article);
				}
			}
			if(list2.size() == 0){
				m.addAttribute("article",articleService.getArticleList(aid));
			}else{
				m.addAttribute("article",list2.get(list2.size()-1));
			}
			PageInfo commentList = commentService.getCommentList(aid, 1, 3);
			m.addAttribute("info",commentList);
		return "/article/articleDetail";
	}
}
