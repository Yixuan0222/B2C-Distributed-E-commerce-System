package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	/**
	 * ��Ʒ��ҳ��ѯ
	 * */
	EasyUIDataGrid show(int page,int rows);
	/**
	 * ����id�޸�״̬
	 * */
	int updItemStatus(TbItem tbItem);
	/**
	* ����������Ʒ�����Ʒ������ 
	* @param tbItem 
	* @param desc 
	* @return */
	int insTbItemDesc(TbItem tbItem,TbItemDesc desc,TbItemParamItem paramItem) throws Exception;
	/**
	* ͨ��״̬��ѯȫ���������� * @return 
	*/ 
	List<TbItem> selAllByStatus(byte status);
	//����id��ѯ��Ʒ
	TbItem selById(long id);
}
