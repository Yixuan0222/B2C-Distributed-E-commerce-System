package com.ego.dubbo.service;
import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	* ���ݸ� id ��ѯ��������Ŀ
	* @param id 
	* @return */ 
	List<TbContentCategory> selByPid(long id);
	//����cate
	int insTbContentCategory(TbContentCategory cate);
	// �޸� isparent ͨ�� id 
	int updIsParentById(TbContentCategory cate);
	//ͨ�� id ��ѯ������Ŀ��ϸ��Ϣ 
	TbContentCategory selById(long id);
}
