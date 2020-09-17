package com.ego.dubbo.service;
import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	* 根据父 id 查询所有子类目
	* @param id 
	* @return */ 
	List<TbContentCategory> selByPid(long id);
	//新增cate
	int insTbContentCategory(TbContentCategory cate);
	// 修改 isparent 通过 id 
	int updIsParentById(TbContentCategory cate);
	//通过 id 查询内容类目详细信息 
	TbContentCategory selById(long id);
}
