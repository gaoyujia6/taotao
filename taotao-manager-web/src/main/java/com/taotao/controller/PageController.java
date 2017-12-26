package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页跳转
 * @ClassName: PageController  
 * @Description: TODO(跳转首页)  
 * @author 高宇嘉  
 * @date 2017年10月24日  
 *
 */
@Controller
public class PageController {

	/**
	 * 首页跳转
	 * @Title: showIndex  
	 * @Description: TODO(首页)  
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 其他页面跳转
	 * @Title: showPage  
	 * @Description: TODO(其他页面跳转)  
	 * @param @param page	跳转页面
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
