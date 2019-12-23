package com.wangming.util;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;

import com.github.pagehelper.PageInfo;


/**
 * 分页工具类
 *  
 */
public final class PageUtil {
	
	
	
	/**
	 * 分页面显示
	 * @param request 
	 * @param url      请求的mapping路径
	 * @param pageSize 每页显示的数据条数
	 * @param list     显示的list集合
	 * @param listCount 一共有多少条数据
	 * @param page     当前页码
	 */
	public static void page(HttpServletRequest request, String url, Integer pageSize,Integer count, Integer listCount, Integer pageNum,Class clazz,Object obj) {
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
		
		//上一页
		String prev = "";
		//下一页
		String next = "";
		
		
		int prePage = 0;
		int nextPage = 0;
		
		prePage = (pageNum == 1 ? pageNum : pageNum - 1);
		nextPage = (pageNum == pageCount ? pageNum : pageNum + 1);
		
		
		/*PageInfo page = new PageInfo();
		page.setPages(pageCount);
		page.setPrePage(prePage);
		page.setNextPage(nextPage);*/
		
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
		for (int i = pageNum - 2  > 1 ? pageNum - 2 : 1; i < (pageNum + 2 > pageCount ? pageCount : pageNum + 2); i++) {
			if(pageNum != i){
				pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+i+"'>${index.index}</a></li>";
			}else{
				pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+i+"'>${index.index}</a></li>";
			}
		}
		pages += "<li><a href='"+request.getContextPath()+url+flag+"pageNum="+nextPage+"'>&raquo;</a></li></ul>";
		
		request.setAttribute("page",pages);
	
	}
}
