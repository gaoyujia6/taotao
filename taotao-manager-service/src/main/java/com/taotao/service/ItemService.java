package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 商品相关接口
 * @ClassName: ItemService  
 * @Description: TODO(商品相关接口)  
 * @author 高宇嘉  
 * @date 2017年10月25日  
 *
 */
public interface ItemService {

	//根据itemId进行查询
	TbItem getItemById(Long itemId);
	
	//商品列表
	EUDataGridResult getItemList(int page, int rows);
	
	TaotaoResult createItem(TbItem item,String desc) throws Exception;
}
