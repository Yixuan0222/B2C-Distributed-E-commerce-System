package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {
	//��ҳ��ѯ����
	EasyUIDataGrid showPage(int page,int rows);
	//����ɾ��
	int delByIds(String ids) throws Exception;
	//������Ŀid��ѯ����ģ��
	TbItemParam selByCatid(long catId);
	//������֧����������
	int insParam(TbItemParam param);
}
