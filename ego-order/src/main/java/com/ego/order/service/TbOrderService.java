package com.ego.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.order.pojo.MyOrderParam;

public interface TbOrderService {
	//确认订单信息包含的商品
	List<TbItemChild> showOrderCart(List<Long> id,HttpServletRequest request);
	EgoResult create(MyOrderParam param,HttpServletRequest request);
}
