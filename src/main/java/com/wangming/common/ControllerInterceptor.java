/**   
 * 
 * @Title: ControllerInterceptor.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月19日 下午1:08:17 
 * @version: V1.0   
 */
package com.wangming.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: ControllerInterceptor 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月19日 下午1:08:17  
 */
@ControllerAdvice
public class ControllerInterceptor {

	@ExceptionHandler(CmsException.class)
	@ResponseBody
	public MsgResult interceptorException(CmsException exception){
		return new MsgResult(100,exception.getMessage(), null);
	}
	
	@ExceptionHandler(CmsExceptionHtml.class)
	public ModelAndView webpageException(CmsExceptionHtml exception){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		mav.addObject("error",exception.getMessage());
		return mav;
	}
	
	
}
