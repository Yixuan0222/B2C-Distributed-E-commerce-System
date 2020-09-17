package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		//���÷�ҳ����
		PageHelper.startPage(page,rows);
		//���ò�ѯ�� SQL ��� //XXXXExample() ������ʲô,�൱����sql �� where �Ӿ����������
		//���������һ����һ�����ϵ�����text����. ���ɵķ��� xxxxxxxxWithBlobs()
		//���ʹ��xxxxWithBlobs() ��ѯ����а�������text�����,���û��ʹ�� withblobs() ���������� text ����. 
		List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//���ݳ���Ա�Լ���д�� SQL ����Ϸ�ҳ����������ս��,��װ�� PageInfo
		PageInfo<TbItemParam> pi=new PageInfo<>(list);
		//���÷������ؽ�� 
		EasyUIDataGrid datagrid=new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}
	@Override
	public int delByIds(String ids) throws Exception {
		int index=0;
		String[] idStr=ids.split(",");
		for (String id:idStr){
			index+=tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if (index==idStr.length){
			return 1;
		}else{
			throw new Exception("ɾ��ʧ�ܡ�����ԭ�������Ѿ�������");
		}
	}
	@Override
	public TbItemParam selByCatid(long catId) {
		TbItemParamExample example=new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list!=null&&list.size()>0){
			//Ҫ��ÿ����Ŀֻ����һ��ģ��
			return list.get(0);
		}
		return null;
	}
	@Override
	public int insParam(TbItemParam param) {
		return tbItemParamMapper.insertSelective(param);
	}
}
