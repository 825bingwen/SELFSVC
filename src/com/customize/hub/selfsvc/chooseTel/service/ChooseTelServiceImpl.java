/*
 * �ļ�����ResDataServiceImpl.java
 * ��Ȩ��CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �����������ն���Դ���� ServiceImpl
 * �޸��ˣ�z00107005
 * �޸�ʱ�䣺2008-1-17
 * �޸����ݣ�����
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ��޸ģ�����Ӧ�����ն˵���Ҫ
 */
package com.customize.hub.selfsvc.chooseTel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.dao.ChooseTelDaoImpl;
import com.customize.hub.selfsvc.chooseTel.model.BlacklistPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * ԤԼѡ��_����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Apr 19, 2011]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ChooseTelServiceImpl implements ChooseTelService
{
    private ChooseTelDaoImpl chooseTelDao;
    
    /**
     * ȡ��ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ChooseTelNumPO> getChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        return chooseTelDao.getChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * ����ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        chooseTelDao.updateChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * ����ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        chooseTelDao.insertChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * ȡ�ú�����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<BlacklistPO> getBlacklist(BlacklistPO blacklistPO)
    {
        return chooseTelDao.getBlacklist(blacklistPO);
    }
    
    /**
     * ��ѯ����������
     * 
     * @return
     * @see 
     */
    public List<DictItemPO> qryLuckyNumRules()
    {
        return chooseTelDao.qryLuckyNumRules();
    }
    
    /**
     * ��ѯ���º���
     * 
     * @param po
     * @return
     * @see 
     */
    public List<LoverNumPO> qryLoverNum(LoverNumPO po)
    {
        return chooseTelDao.qryLoverNum(po);
    }
    
    /**
     * ��ѯ������������Ϣ
     * 
     * @return
     * @see 
     */
    public Map<String, ServerNumPO> qryLuckyNumInfo()
    {
        Map<String, ServerNumPO> info = new HashMap<String, ServerNumPO>();
        
        List<ServerNumPO> list = chooseTelDao.qryLuckyNumInfo();
        
        if (null != list && list.size() > 0)
        {
            ServerNumPO po = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                po = list.get(i);
                
                info.put(po.getTelnum(), po);
            }
        }
        
        return info;
    }
    
    /**
     * �������º�����Ϣ
     * 
     * @param po
     * @see 
     */
    public void updateLoverNumInfo(LoverNumPO po)
    {
        chooseTelDao.updateLoverNumInfo(po);
    }

    public ChooseTelDaoImpl getChooseTelDao()
    {
        return chooseTelDao;
    }

    public void setChooseTelDao(ChooseTelDaoImpl chooseTelDao)
    {
        this.chooseTelDao = chooseTelDao;
    }

    /**
     * ѡ��ԤԼ��־��¼
     * @param chooseTelLogPO
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017/01/09 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż�������
     */
	@Override
	public void createTelLog(ChooseTelLogPO chooseTelLogPO) {
		chooseTelDao.createTelLog(chooseTelLogPO);
	}
}