/*
 * �ļ�����ResDataDaoImpl.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.customize.hub.selfsvc.chooseTel.dao;

import java.util.List;

import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.model.BlacklistPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
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
public class ChooseTelDaoImpl extends BaseDaoImpl
{
    public ChooseTelDaoImpl()
    {
        super();
    }
    
    /**
     * ȡ��ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ChooseTelNumPO> getChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.getChooseTelNum",chooseTelNumPO);
    }
    
    /**
     * ����ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        sqlMapClientTemplate.update("chooseTel.updateChooseTelNum", chooseTelNumPO);
    }
    
    /**
     * ����ѡ�Ŵ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        sqlMapClientTemplate.insert("chooseTel.insertChooseTelNum", chooseTelNumPO);
    }
    
    /**
     * ȡ�ú�����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<BlacklistPO> getBlacklist(BlacklistPO blacklistPO)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.getBlacklist",blacklistPO);
    }

    /**
     * ��ѯ����������
     * 
     * @return
     * @see 
     */
    public List<DictItemPO> qryLuckyNumRules()
    {
        return sqlMapClientTemplate.queryForList("chooseTel.luckyNumRules");
    }
    
    /**
     * ��ѯ���º���
     * 
     * @return
     * @see 
     */
    public List<LoverNumPO> qryLoverNum(LoverNumPO po)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.loverNums", po);
    }
    
    /**
     * ��ѯ������������Ϣ
     */
    public List<ServerNumPO> qryLuckyNumInfo()
    {
        return sqlMapClientTemplate.queryForList("chooseTel.luckyNumInfo");
    }
    
    /**
     * �������º������Ϣ������״̬��ʧЧʱ��
     * 
     * @param po
     * @see 
     */
    public void updateLoverNumInfo(LoverNumPO po)
    {
        sqlMapClientTemplate.update("chooseTel.updateLoverNumInfo", po);
    }
    
    /**
     * ��¼ѡ��ԤԼ��־
     * @param chooseTelLogPO
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017/01/09 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż�������
     */
    public void createTelLog(ChooseTelLogPO chooseTelLogPO)
    {
    	sqlMapClientTemplate.insert("chooseTel.insertTelLog", chooseTelLogPO);
    }
}
