package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.item.service.TbItemParamItemService;

@Controller
public class TbItemParamItemController {
	@Resource
	TbItemParamItemService tbItemParamItemServiceImpl;
	//显示商品规格参数
	@RequestMapping(value="item/param/{id}.html",produces="text/html;charset=utf-8") 
	@ResponseBody 
	public String param(@PathVariable long id){ 
		return tbItemParamItemServiceImpl.showParam(id); 
	} 
}
