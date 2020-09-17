package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

public interface TbOrderDubboService {
	//´´½¨¶©µ¥
	int insOrder(TbOrder order, List<TbOrderItem> list,TbOrderShipping shipping) throws Exception; 
}
