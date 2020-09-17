package com.ego.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;

@Service
public class TbUserServiceImpl implements TbUserService{
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Resource 
	private JedisDao jedisDaoImpl; 
	@Override
	public EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
		EgoResult er=new EgoResult();
		TbUser userSelect=tbUserDubboServiceImpl.selByUser(user);
		if(userSelect!=null){
			er.setStatus(200);
			//���û���¼�ɹ�����û���Ϣ���뵽 redis �� 
			String key = UUID.randomUUID().toString();
			jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
			jedisDaoImpl.expire(key, 60*60*24*7);
			//���� Cookie 
			CookieUtils.setCookie(request, response,"TT_TOKEN", key, 60*60*24*7); 
		}else{
			er.setMsg("�û������������");
		}
		return er;
	}
	@Override
	public EgoResult getUserInfoByToken(String token) {
		EgoResult er = new EgoResult(); 
		String json = jedisDaoImpl.get(token); 
		if(json!=null&&!json.equals("")){ 
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
			tbUser.setPassword(null); 
			er.setStatus(200); 
			er.setMsg("OK");
			er.setData(tbUser); 
		}else{
			er.setMsg("��ȡʧ��");
		}
		return er;
	}
	@Override
	public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
		Long index = jedisDaoImpl.del(token); 
		CookieUtils.deleteCookie(request, response, "TT_TOKEN"); 
		EgoResult er = new EgoResult();
		er.setStatus(200); 
		er.setMsg("OK"); return er;
	}

}
