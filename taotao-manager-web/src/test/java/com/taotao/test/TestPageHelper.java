package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	
	@Test
	public void testPageHelper(){
		//加载spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//获取mapper代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//执行查询
		TbItemExample example = new TbItemExample();
		//分页查询
		PageHelper.startPage(1, 10);
		List<TbItem> list = itemMapper.selectByExample(example);
		for(TbItem tbItem : list){
			System.out.println(tbItem.getTitle());
		}
		//获取分页数量
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		System.out.println(pageInfo.getTotal());
		
	}

}
