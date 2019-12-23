/**   
 * 
 * @Title: MsgListener.java 
 * @Prject: wangming-cms
 * @Package: com.wangming 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年12月11日 下午1:43:44 
 * @version: V1.0   
 */
package com.wangming.kafka;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.wangming.entity.Article;
import com.wangming.mapper.ArticleMapper;
import com.wangming.mapper.EsRepository;
import com.wangming.mapper.UserMapper;
import com.wangming.service.ArticleService;
import com.wangming.service.UserService;

/** 
 * @ClassName: MsgListener 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年12月11日 下午1:43:44  
 */
public class MsgListener implements MessageListener<String, String>{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private EsRepository esRepository;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String value = data.value();
		String condition = value.split("=")[1];
		if(value.startsWith("add")){
			//删除redis数据缓存   让数据从mysql查到后在存入redis
			//这样就可以将数据同步到redis数据库了
			redisTemplate.delete("ArticleList");
			//将json字符串转换成article对象
			Article article = JSON.parseObject(condition, Article.class);
			//添加到es索引库
			/*System.err.println("================="+article.getId());*/
			esRepository.save(article);
		}else if(value.startsWith("update")){
			//删除redis数据库key是articleList的数据
			redisTemplate.delete("ArticleList");
			Article article = JSON.parseObject(condition, Article.class);
			esRepository.save(article);
		}else if(value.startsWith("delete")){
			redisTemplate.delete("ArticleList");
			Integer id = (Integer) JSON.parse(condition);
			//将es索引库数据删除
			esRepository.deleteById(id);
		}else if(value.startsWith("article")){
			Article article = JSON.parseObject(condition, Article.class);
			userMapper.addUserArticle(article);
		}else{
			System.err.println("获取了消息.....................");
			Article article = JSON.parseObject(value, Article.class);
			userMapper.addUserArticle(article);
		}
		
	}

}
