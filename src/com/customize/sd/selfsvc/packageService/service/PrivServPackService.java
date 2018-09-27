package com.customize.sd.selfsvc.packageService.service;

import java.util.List;

import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;

/**
 * �ػ�ҵ���Service
 * @author  hWX5316476
 * @version  [�汾��, Dec 22, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface PrivServPackService
{
    /**
     * ��ѯ�ػ�ҵ����б�
     * @param privServPackPO
     * @return
     */
    public List<PrivServPackPO> qryPrivServPackList(PrivServPackPO privServPackPO);
    
    /**
     * ��ѯ�ػ�ҵ�������
     * @param privServPackPO
     * @return
     */
    public PrivServPackPO qryPrivServPackDetail(PrivServPackPO privServPackPO);
}
