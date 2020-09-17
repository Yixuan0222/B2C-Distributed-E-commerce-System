package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	/**
	 * 商品分页查询
	 * */
	EasyUIDataGrid show(int page,int rows);
	/**
	 * 根据id修改状态
	 * */
	int updItemStatus(TbItem tbItem);
	/**
	* 新增包含商品表和商品描述表 
	* @param tbItem 
	* @param desc 
	* @return */
	int insTbItemDesc(TbItem tbItem,TbItemDesc desc,TbItemParamItem paramItem) throws Exception;
	/**
	* 通过状态查询全部可用数据 * @return 
	*/ 
	List<TbItem> selAllByStatus(byte status);
	//根据id查询商品
	TbItem selById(long id);
}
