/**   
 * 
 * @Title: ChnanelMapper.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.mapper 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午1:29:32 
 * @version: V1.0   
 */
package com.wangming.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wangming.entity.Channel;


/** 
 * @ClassName: ChnanelMapper 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午1:29:32  
 */
public interface ChannelMapper {

	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> getChannel();
}
