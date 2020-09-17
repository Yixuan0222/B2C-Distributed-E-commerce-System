package com.ego.order.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.redis.dao.JedisDao;
@Service
public class TbOrderServiceImpl implements TbOrderService{
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${passport.url}") 
	private String passportUrl;
	@Value("${cart.key}") 
	private String cartKey;
	@Reference 
	private TbItemDubboService tbItemDubboServiceImpl; 
	@Reference
	TbOrderDubboService tbOrderDubboServiceImpl; 
	@Override
	public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
		String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result=HttpClientUtil.doPost(passportUrl+token);
		EgoResult er=JsonUtils.jsonToPojo(result, EgoResult.class);
		String key=cartKey+((LinkedHashMap)er.getData()).get("username");
		String json=jedisDaoImpl.get(key);
		List<TbItemChild> list=JsonUtils.jsonToList(json, TbItemChild.class);
		List<TbItemChild> listNew=new ArrayList<>();
		for (TbItemChild child:list){
			for (Long id :ids){
				if ((long)child.getId()==id){
					TbItem tbItem=tbItemDubboServiceImpl.selById(id);
					if (tbItem.getNum()>=child.getNum()){
						child.setEnough(true);
					}else{
						child.setEnough(false);
					}
					listNew.add(child);
				}
			}
		}
		return listNew;
	}
	@Override
	public EgoResult create(MyOrderParam param, HttpServletRequest request) {
		//���������� 
		TbOrder order = new TbOrder(); 
		order.setPayment(param.getPayment()); 
		order.setPaymentType(param.getPaymentType()); 
		long id = IDUtils.genItemId(); 
		order.setOrderId(id+""); 
		Date date =new Date(); 
		order.setCreateTime(date); 
		order.setUpdateTime(date);
		String token =CookieUtils.getCookieValue(request, "TT_TOKEN"); 
		String result =HttpClientUtil.doPost(passportUrl+token); 
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class); 
		Map user= (LinkedHashMap)er.getData();
		order.setUserId(Long.parseLong(user.get("id").toString()));
		order.setBuyerNick(user.get("username").toString());
		order.setBuyerRate(0);
		//����-��Ʒ�� 
		for (TbOrderItem item : param.getOrderItems()) { 
			item.setId(IDUtils.genItemId()+""); 
			item.setOrderId(id+"");
		}
		//�ջ�����Ϣ 
		TbOrderShipping shipping = param.getOrderShipping();
		shipping.setOrderId(id+""); 
		shipping.setCreated(date); 
		shipping.setUpdated(date);
		EgoResult erResult = new EgoResult();
		try {
			int index=tbOrderDubboServiceImpl.insOrder(order, param.getOrderItems(),shipping);
			if (index>0){
				erResult.setStatus(200);
				//ɾ���������Ʒ
				String json=jedisDaoImpl.get(cartKey+user.get("username"));
				List<TbItemChild> listCart=JsonUtils.jsonToList(json, TbItemChild.class);
				List<TbItemChild> listNew = new ArrayList<>();
				for (TbItemChild child:listCart){
					for (TbOrderItem item: param.getOrderItems()){
						if (child.getId().longValue()==Long.parseLong(item.getItemId())){
							listNew.add(child);
						}
					}
				}
				for (TbItemChild mynew : listNew) { 
					listCart.remove(mynew); 
				}
				jedisDaoImpl.set(cartKey+user.get("username"),JsonUtils.objectToJson(listCart));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return erResult;
	}

}
