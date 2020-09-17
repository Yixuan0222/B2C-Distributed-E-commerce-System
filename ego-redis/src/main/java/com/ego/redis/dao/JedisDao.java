package com.ego.redis.dao;

public interface JedisDao {
	/**
	* 判断 key 是否存在 * @param key * @return
	*/
	Boolean exists(String key);
	/**
	* 删除 * @param key * @return 
	*/ 
	Long del(String key);
	/**
	* 设置值 * @param key * @param value * @return 
	*/ 
	String set(String key,String value);
	/**
	* 取值 * @param key * @return 
	*/ 
	String get(String key);
	
	Long expire(String key, int i);
}
