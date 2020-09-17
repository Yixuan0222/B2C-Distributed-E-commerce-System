package com.ego.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ego.commons.pojo.EgoResult;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;

@Controller
public class OrderController {
	@Resource 
	private TbOrderService tbOrderServiceImpl; 
	//ȷ�϶���
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam("id") List<Long> ids,HttpServletRequest request){
		model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request)); 
		return "order-cart";
	}
	//��������
	@RequestMapping("order/create.html") 
	public String createOrder(MyOrderParam param,HttpServletRequest request){
		EgoResult er=tbOrderServiceImpl.create(param, request);
		if (er.getStatus()==200){
			return "my-orders"; 
		}else{
			request.setAttribute("message", "��������ʧ��");
			return "error/exception";
		}
	}
}
