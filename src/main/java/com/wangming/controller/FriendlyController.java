/**   
 * 
 * @Title: FriendlyController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月27日 上午8:53:20 
 * @version: V1.0   
 */
package com.wangming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangming.entity.Friendly;
import com.wangming.service.FriendlyService;

/** 
 * @ClassName: FriendlyController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月27日 上午8:53:20  
 */
@Controller
@RequestMapping("friendly")
public class FriendlyController {

	@Autowired
	private FriendlyService friendlyService;
	
	@GetMapping("add")
	public String add(Model m){
		m.addAttribute("friendly",new Friendly());
		return "admin/friendly/add";
	}
	
	@PostMapping("add")
	public String add(Model m,@Valid @ModelAttribute("friendly")Friendly friendly,BindingResult result){
		System.out.println("=================================");
		if(result.hasErrors()){
			return "admin/friendly/add";
		}
		friendlyService.addFriendly(friendly);
		return "admin/index";
	}
}
