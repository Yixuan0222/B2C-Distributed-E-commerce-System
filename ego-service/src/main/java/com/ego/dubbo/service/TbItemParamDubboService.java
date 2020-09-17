package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {
	//分页查询数据
	EasyUIDataGrid showPage(int page,int rows);
	//批量删除
	int delByIds(String ids) throws Exception;
	//根据类目id查询参数模板
	TbItemParam selByCatid(long catId);
	//新增，支持主键自增
	int insParam(TbItemParam param);
}
