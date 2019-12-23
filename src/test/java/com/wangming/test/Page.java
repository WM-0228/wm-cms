/**   
 * 
 * @Title: Page.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.test 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年12月17日 下午6:57:18 
 * @version: V1.0   
 */
package com.wangming.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: Page 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年12月17日 下午6:57:18  
 */
public class Page {

	
	private int page;
	
	private int rows;

	private Class<?> clazz;
	
	
	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/** 
	 * @Title:Page
	 * @Description:TODO 
	 * @param page
	 * @param rows 
	 */
	public Page(ElasticsearchTemplate elasticsearchTemplate, Class<?> clazz, Integer page,
			Integer rows, String fieldNames[],String sortField, String value) {
		super();
		this.page = page;
		this.rows = rows;
		this.clazz = clazz;
		selectObjects(elasticsearchTemplate, clazz, page, rows, fieldNames, sortField, value);
	}
	
	/**
	 * 保存及更新方法
	 * 
	 * @param elasticsearchTemplate
	 * @param id
	 * @param object
	 */
	public static void saveObject(ElasticsearchTemplate elasticsearchTemplate, String id, Object object) {
		// 创建所以对象
		IndexQuery query = new IndexQueryBuilder().withId(id).withObject(object).build();
		// 建立索引
		elasticsearchTemplate.index(query);
	}

	/**
	 * 批量删除
	 * 
	 * @param elasticsearchTemplate
	 * @param clazz
	 * @param ids
	 */
	public static void deleteObject(ElasticsearchTemplate elasticsearchTemplate, Class<?> clazz, Integer ids[]) {
		for (Integer id : ids) {
			// 建立索引
			elasticsearchTemplate.delete(clazz, id + "");
		}
	}

	/**
	 * 
	 * @Title: selectById
	 * @Description: 根据id在es服务启中查询对象
	 * @param elasticsearchTemplate
	 * @param clazz
	 * @param id
	 * @return
	 * @return: Object
	 */
	public static Object selectById(ElasticsearchTemplate elasticsearchTemplate, Class<?> clazz, Integer id) {
		GetQuery query = new GetQuery();
		query.setId(id + "");
		return elasticsearchTemplate.queryForObject(query, clazz);
	}

	// 查询操作
	public static AggregatedPage<?> selectObjects(ElasticsearchTemplate elasticsearchTemplate, Class<?> clazz, Integer page,
			Integer rows, String fieldNames[],String sortField, String value) {
		AggregatedPage<?> pageInfo = null;
		// 创建Pageable对象														主键的实体类属性名
		final Pageable pageable = PageRequest.of(page - 1, rows, Sort.by(Sort.Direction.ASC, sortField));
		//查询对象
		SearchQuery query = null;
		//查询条件高亮的构建对象
		QueryBuilder queryBuilder = null;
		
		if (value != null && !"".equals(value)) {
			// 高亮拼接的前缀与后缀
			String preTags = "<font color=\"red\">";
			String postTags = "</font>";

			// 定义创建高亮的构建集合对象
			HighlightBuilder.Field highlightFields[] = new HighlightBuilder.Field[fieldNames.length];

			for (int i = 0; i < fieldNames.length; i++) {
				// 这个代码有问题
				highlightFields[i] = new HighlightBuilder.Field(fieldNames[i]).preTags(preTags).postTags(postTags);
			}

			// 创建queryBuilder对象
			queryBuilder = QueryBuilders.multiMatchQuery(value, fieldNames);
			query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withHighlightFields(highlightFields)
					.withPageable(pageable).build();

			pageInfo = elasticsearchTemplate.queryForPage(query, clazz, new SearchResultMapper() {

				public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable1) {

					List<T> content = new ArrayList<T>();
					long total = 0l;

					try {
						// 查询结果
						SearchHits hits = response.getHits();
						if (hits != null) {
							//获取总记录数
							total = hits.getTotalHits();
							// 获取结果数组
							SearchHit[] searchHits = hits.getHits();
							// 判断结果
							if (searchHits != null && searchHits.length > 0) {
								// 遍历结果
								for (int i = 0; i < searchHits.length; i++) {
									// 对象值
									T entity = clazz.newInstance();

									// 获取具体的结果
									SearchHit searchHit = searchHits[i]; 

									// 获取对象的所有的字段
									Field[] fields = clazz.getDeclaredFields();

									// 遍历字段对象
									for (int k = 0; k < fields.length; k++) {
										// 获取字段对象
										Field field = fields[k];
										// 暴力反射
										field.setAccessible(true);
										// 字段名称
										String fieldName = field.getName();
										if (!fieldName.equals("serialVersionUID")&&!fieldName.equals("user")) {
											HighlightField highlightField = searchHit.getHighlightFields()
													.get(fieldName);
											if (highlightField != null) {
												// 高亮 处理 拿到 被<font color='red'> </font>结束所包围的内容部分
												String value = highlightField.getFragments()[0].toString();
												// 注意一下他是否是 string类型
												field.set(entity, value);
											} else {
												//获取某个字段对应的 value值
												Object value = searchHit.getSourceAsMap().get(fieldName);
												// 获取字段的类型
												Class<?> type = field.getType();
												if (type == Date.class) {
													// bug
													if(value!=null) {
														field.set(entity, new Date(Long.valueOf(value + "")));
													}
												} else {
													field.set(entity, value);
												}
											}
										}
									}

									content.add(entity);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return new AggregatedPageImpl<T>(content, pageable, total);
				}
			});

		} else {
			// 没有查询条件的的时候，获取es中的全部数据 分页获取
			query = new NativeSearchQueryBuilder().withPageable(pageable).build();
			pageInfo = elasticsearchTemplate.queryForPage(query, clazz);
		}
		return pageInfo;
	}
	
	
	public static PageInfo page(HttpServletRequest request, String url, Integer pageSize,List<?> list, Integer listCount, Integer pageNum,Class clazz,Object obj) {
		// 通过符合要求的总条数和页面显示数来计算总页数
		int pageCount = listCount/pageSize + (listCount%pageSize == 0 ? 0 : 1);
		String endstring = "";
		//获取类属性进行路径拼接
		if(clazz != null && obj != null){
			String str = "";
			try {
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					Object object = field.get(obj);
					str += field.getName()+"="+(field.get(obj) == null ? "" : field.get(obj))+"&";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endstring = str.substring(0, str.length() - 1);
			
		}
			
		int prePage = 0;
		int nextPage = 0;
		
		prePage = (pageNum == 1 ? pageNum : pageNum - 1);
		nextPage = (pageNum == pageCount ? pageNum : pageNum + 1);
		
		
		PageInfo page = new PageInfo(list);
		page.setPages(pageCount);
		page.setPrePage(prePage);
		page.setNextPage(nextPage);
		page.setTotal(listCount);
		page.setPageSize(pageSize);
		
		//本来想法是写一个flag判断这个循环的对象只要他不为空在进行一个判断但是没有想出来只能等出现新的错误再继续更新
		
		//这个方法是不行的   因为是类属性的字符串拼接  所以肯定长度大于0
//		String flag = substring.length() == 0 ? "?" : "&";
		
		//System.out.println("pageCount=="+pageCount);
		// 判断url上是否有?号，如果有，后面通过&符号进行连接，否则通过?进行连接
		String flag = url.indexOf("?") != -1 ? "&" : "?";
		
		url += endstring;
		
		
		String pages = "";
		
		pages += "<ul class='pagination'>";
		pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+prePage+"'>&laquo;</a></li>";
		for (int i = pageNum - 2  > 1 ? pageNum - 2 : 1; i <= (pageNum + 2 > pageCount ? pageCount : pageNum + 2); i++) {
			if(pageNum != i){
				pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+i+"'>"+i+"</a></li>";
			}else{
				pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+i+"'>"+i+"</a></li>";
			}
		}
		pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+nextPage+"'>&raquo;</a></li></ul>";
		
		request.setAttribute("page",pages);
		return page;
	}
			
}
