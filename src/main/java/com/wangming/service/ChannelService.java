/**   
 * 
 * @Title: ChannelService.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.service 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午1:25:10 
 * @version: V1.0   
 */
package com.wangming.service;

import java.util.List;

import com.wangming.entity.Channel;


/** 
 * @ClassName: ChannelService 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午1:25:10  
 */
public interface ChannelService {

	/** 
	 * @Title: getList 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> getList();

}
