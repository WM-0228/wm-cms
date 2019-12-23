/**   
 * 
 * @Title: ESArticle.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.test 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年12月17日 下午1:41:34 
 * @version: V1.0   
 */
package com.wangming.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hutool.core.date.DateUtil;

import com.alibaba.fastjson.JSON;
import com.sun.tools.extcheck.Main;
import com.wangming.entity.Article;
import com.wangming.mapper.ArticleMapper;
import com.wangming.mapper.EsRepository;
import com.wangming.mapper.UserMapper;
import com.wangming.utils.IoStreamUtils;
import com.wangming.utils.RandomLetter;
import com.wangming.utils.UserUtils;

/** 
 * @ClassName: ESArticle 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年12月17日 下午1:41:34  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ESArticle {

	//查询所有的文章
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private EsRepository esRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Test
	public void sendESArticle(){
		List<Article> writings = articleMapper.getWritings();
		writings.forEach(System.err::println);
		esRepository.saveAll(writings);
		Iterable<Article> findAll = esRepository.findAll();
		findAll.forEach(x -> System.err.println(x.getTitle()));
		List<Article> findByTitle = esRepository.findByTitle("祖国");
		for (Article article : findByTitle) {
			System.out.println("============"+article.getTitle());
		}
	}
	
	@Test
	public void readFloder() throws Exception{
		File file = new File("D://data");
		HashMap<String, String> hashMap = IoStreamUtils.readFolder(file);
		File[] listFiles = file.listFiles();
		
		for (int i = 0; i < 50; i++) {
			String name = listFiles[i].getName();
			String string = hashMap.get(name);
			if(string == null){
				continue;
			}
			Article article = new Article();
			String title = name.replace(".txt","");
			String content = string.trim().length() > 140 ? string.substring(0,140) : string;
			article.setTitle(title);
			//存入内容
			article.setContent(content);
			//随机热门
			article.setHot(RandomLetter.randomNum(1,0));
			//是否删除
			article.setDeleted(0);
			//当前状态已审核
			article.setStatus(1);
			//获取随机的频道id
			int channelId = RandomLetter.randomNum(7,1);
			//存入频道id
			article.setChannelId(channelId);
			//获取频道id下的分类
			int[] findByCategoryId = articleMapper.findByCategoryId(channelId);
			//获取随机分类
			article.setCategoryId(RandomLetter.randomArray(findByCategoryId));
			//获取日期
			article.setCreated(DateUtil.parse(UserUtils.getBirthday(),"yyyy-MM-dd"));
			//从数据库查询用户id
			int[] userId = userMapper.getUserId();
			article.setUserId(RandomLetter.randomArray(userId));
			//转换成json对象
			String jsonString = JSON.toJSONString(article);
			kafkaTemplate.send("article","article="+jsonString);
		}
		
		
	}
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
	}
}
