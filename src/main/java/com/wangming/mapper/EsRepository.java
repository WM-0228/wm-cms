/**   
 * 
 * @Title: EsRepository.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年12月17日 下午1:45:30 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wangming.entity.Article;

/** 
 * @ClassName: EsRepository 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年12月17日 下午1:45:30  
 */
public interface EsRepository extends ElasticsearchRepository<Article, Integer>{
	
	List<Article> findByTitle(String key);
}
