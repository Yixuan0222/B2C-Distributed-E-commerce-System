package com.ego.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.order.pojo.MyOrderParam;

public interface TbOrderService {
	//ȷ�϶�����Ϣ��������Ʒ
	List<TbItemChild> showOrderCart(List<Long> id,HttpServletRequest request);
	EgoResult create(MyOrderParam param,HttpServletRequest request);
}
