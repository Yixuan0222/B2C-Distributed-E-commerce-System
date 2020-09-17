package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	//分页查询
	EasyUIDataGrid selContentByPage(long categoryId,int page,int rows);
	//新增
	int insContent(TbContent content);
	/**
	* 查询出最近的前 n 个
	 */
	List<TbContent> selByCount(int count,boolean isSort);
}
