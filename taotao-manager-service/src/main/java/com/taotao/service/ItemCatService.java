package com.taotao.service;

import java.util.List;
import java.util.Map;

import com.taotao.common.pojo.TreeNode;

/**
 * 商品分类
 * @ClassName: ItemCatService  
 * @Description: TODO(商品分类)  
 * @author 高宇嘉  
 * @date 2017年10月25日  
 *
 */
public interface ItemCatService {

	//根据parentId分类列表
	List<TreeNode> getItemCatList(long parentId);
	
	List<Map> getItemCatMap(long parentId);
}
