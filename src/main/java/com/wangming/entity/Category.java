/**   
 * 
 * @Title: Category.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.entity 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月14日 下午2:21:08 
 * @version: V1.0   
 */
package com.wangming.entity;

import java.io.Serializable;

/** 
 * @ClassName: Category 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月14日 下午2:21:08  
 */
public class Category implements Serializable{

	private Integer id;
	private String name;
	//頻道id
	private int channel_id;
	//頻道
	private Channel channel;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the channel_id
	 */
	public int getChannel_id() {
		return channel_id;
	}
	/**
	 * @param channel_id the channel_id to set
	 */
	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
}
