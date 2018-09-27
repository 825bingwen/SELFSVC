package com.customize.sd.selfsvc.chooseTel.service;

import java.util.List;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.chooseTel.dao.ChooseTelDaoImpl;
import com.customize.sd.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class ChooseTelServiceImpl extends BaseServiceSDImpl implements ChooseTelService 
{
	private ChooseTelDaoImpl chooseTelDaoImpl;
	
	/**
	 * 获取数据库时间
	 * 
	 * @return String
	 */
	public String qryEnddate()
	{
		return chooseTelDaoImpl.qryEnddate();
	}
	
	public ChooseTelDaoImpl getChooseTelDaoImpl() 
	{
		return chooseTelDaoImpl;
	}
	public void setChooseTelDaoImpl(ChooseTelDaoImpl chooseTelDaoImpl) 
	{
		this.chooseTelDaoImpl = chooseTelDaoImpl;
	}
}
