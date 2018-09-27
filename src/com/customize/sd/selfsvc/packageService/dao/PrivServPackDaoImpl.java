package com.customize.sd.selfsvc.packageService.dao;

import java.util.List;

import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * �ػ�ҵ���
 * @author hWX5316476
 *
 */
public class PrivServPackDaoImpl  extends BaseDaoImpl
{
    /**
     * ��ѯ�ػ�ҵ����б�
     * @param privServPackPO
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PrivServPackPO> qryPrivServPackList(PrivServPackPO privServPackPO)
    {
        return sqlMapClientTemplate.queryForList("packageservice.qryPrivServPackList", privServPackPO);
    }
    
    /**
     * ��ѯ�ػ�ҵ�������
     * @param privServPackPO
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PrivServPackPO> qryPrivServPackDetail(PrivServPackPO privServPackPO)
    {
        return sqlMapClientTemplate.queryForList("packageservice.qryPrivServPackDetail", privServPackPO);
    }
}
