package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品实现类
 * @ClassName: ItemServiceImpl 
 * @Description: TODO(商品实现类) 
 * @author 高宇嘉 1285660360@qq.com 
 * @date 2017年10月23日 下午11:15:08 
 *
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	/*
	 * 根据itemId获取商品对象信息
	 * <p>Title: getItemById</p>  
	 * <p>Description: 根据itemId获取商品对象信息</p>  
	 * @param itemId		商品ID
	 * @return  
	 * @see com.taotao.service.ItemService#getItemById(java.lang.Long)
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		//根据主键查询
		//return itemMapper.selectByPrimaryKey(itemId);
		//用查询条件进行查询
		TbItemExample example = new TbItemExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//执行查询
		TbItemExample example = new TbItemExample();
		//分页查询
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		//获取分页数量
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item,String desc) throws Exception {
		item.setId(IDUtils.genItemId());
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TaotaoResult insertImteDesc = insertImteDesc(item.getId(), desc);
		if(insertImteDesc.getStatus() != 200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	
	public TaotaoResult insertImteDesc(Long itemId, String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

}
