package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	//��ҳ��ѯ
	EasyUIDataGrid selContentByPage(long categoryId,int page,int rows);
	//����
	int insContent(TbContent content);
	/**
	* ��ѯ�������ǰ n ��
	 */
	List<TbContent> selByCount(int count,boolean isSort);
}
