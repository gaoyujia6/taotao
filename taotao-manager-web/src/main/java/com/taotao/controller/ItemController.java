package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理controller
 * @ClassName: ItemController 
 * @Description: TODO(商品管理) 
 * @author 高宇嘉 1285660360@qq.com 
 * @date 2017年10月23日 下午11:36:50 
 *
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemSerivce;
	
	/**
	 * 根据itemId获取对象信息
	 * @Title: getItemById  
	 * @Description: TODO(根据itemId获取对象信息)  
	 * @param @param itemId		商品id
	 * @param @return    参数  
	 * @return TbItem    返回类型  
	 * @throws
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable("itemId") Long itemId) {
		TbItem tbItem = itemSerivce.getItemById(itemId);
		return tbItem;
	}
	
	/**
	 * 商品列表
	 * @Title: getItemList  
	 * @Description: TODO(商品列表带分页)  
	 * @param @param page	第几页
	 * @param @param rows	一页多少条
	 * @param @return    参数  
	 * @return EUDataGridResult    返回类型  
	 * @throws
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows){
		return itemSerivce.getItemList(page, rows);
	}

	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc) throws Exception{
		return itemSerivce.createItem(item,desc);
		
	}
}
