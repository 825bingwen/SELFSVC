/*
 * �ļ�����ResDataDaoImpl.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.resdata.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;

/**
 * ��Դ�����ݲ���
 * 
 * @author g00140516
 * 
 */
public class ResDataDaoImpl extends BaseDaoImpl
{
    public ResDataDaoImpl()
    {
        super();
    }
    
    /**
     * ��SH_PARAM���ȡ���е�������Ϣ
     * 
     */
    public List getAllResDataList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllResDataList", null);
    }
    
    /**
     * ��ȡ�˵�����Ϣ
     * 
     * @return
     */
    public List getAllMenuInfoList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllMenuInfo", null);
    }
    
    /**
     * ��ȡregion��Ϣ
     * 
     * @return
     */
    public List getAllRegionInfoList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllRegionInfo", null);
    }
    
    /**
     * ��ȡ�ֵ�����
     * 
     * @return
     * @see
     */
    public List<DictItemPO> getDictItems()
    {
        return sqlMapClientTemplate.queryForList("ResData.getDictItems");
    }
    
    /**
     * ȥsh_info_rectel�������
     * 
     * @return Map������key���ն˻�ID��value��ҵ���Ƽ�ӪҵԱ����
     * @see 
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    public Map<String, String> getRectelInfo()
    {
        Map<String, String> resultMap = new HashMap<String, String>();
                
        List list = sqlMapClientTemplate.queryForList("ResData.getRectelInfo");
        if (list != null && list.size() > 0)
        {
            Map recordMap = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                recordMap = (Map) list.get(i);
                
                // key���ն˻�ID��value��ҵ���Ƽ�ӪҵԱ����
                resultMap.put((String) recordMap.get("TERMID"), (String) recordMap.get("TELNUMBER"));
            }
        }

        return resultMap;
    }
    
    /**
     * ��ȡ�ն��豸�������� add lwx439898 2017-10-12 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public List<TermInfosPO> getAllTermInfos()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllTermInfos");
    }
}
