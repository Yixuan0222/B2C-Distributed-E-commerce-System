package com.ego.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

public interface TbUserService {
	EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response);
	//根据token查询用户信息
	EgoResult getUserInfoByToken(String token);
	//退出
	EgoResult logout(String token,HttpServletRequest request,HttpServletResponse response);
}
