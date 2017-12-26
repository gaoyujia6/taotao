package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 
 * @ClassName: ItemCatServiceImpl  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author 高宇嘉  
 * @date 2017年10月25日  
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//分类列表转换成TreeNode的列表
		List<TreeNode> resultList = new ArrayList<>();
		for(TbItemCat tbItemCat : list){
			//创建一个TreeNode对象
			TreeNode treeNode = new TreeNode(tbItemCat.getId(),tbItemCat.getName(),
					tbItemCat.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}
		return resultList;
	}

	@Override
	public List<Map> getItemCatMap(long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//分类列表转换成TreeNode的列表
		List<Map> maps = new ArrayList<Map>();
		for(TbItemCat tbItemCat : list){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", String.valueOf(tbItemCat.getId()));
			map.put("text", tbItemCat.getName());
			map.put("state", tbItemCat.getIsParent()?"closed":"open");
			maps.add(map);
		}
		return maps;
	}
}
