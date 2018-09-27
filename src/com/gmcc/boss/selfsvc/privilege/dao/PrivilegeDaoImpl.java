/*
 * �� �� ��:  PrivilegeDaoImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  hWX5316476
 * �޸�ʱ��:  Apr 29, 2015
 * ���ٵ���:  OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.gmcc.boss.selfsvc.privilege.dao;

import java.util.List;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.privilege.model.GroupNcodePO;

/**
 * ҵ���ѯdao
 * 
 * @author  hWX5316476
 * @version  [�汾��, Apr 29, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PrivilegeDaoImpl extends BaseDaoImpl
{
    /**
     * ������ID��ѯҵ�����Ncode
     * <������ϸ����>
     * @param groupId
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    public List<GroupNcodePO> qryGroupNcodeInfo(GroupNcodePO groupNcodePO)
    {
        return (List<GroupNcodePO>)sqlMapClientTemplate.queryForList("privilege.qryGroupNcodeInfo", groupNcodePO);
    }
    
    /**
     * ����ncode��ѯ��Ʒ���Ż���Ϣ
     * <������ϸ����>
     * @param ncodePo
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @Remark create by lWX431760 2017-07-19 OR_huawei_201706_780_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ���������
     */
    public NcodePO qryNcodeMessage(NcodePO ncodePo)
    {
        // ʹ����ʱ����,��ֹ��ֵ��ԭ���ݲ���Ӱ��
        NcodePO tmpPo = null;
        
        try
        {
            tmpPo = (NcodePO)ncodePo.clone();
            
            // ��ѯʱ���������͹̶�Ϊ����
            if("PCOpQry".equals(tmpPo.getOptype()))
            {
                tmpPo.setOptype("PCOpRec");
            }
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        
        return (NcodePO) sqlMapClientTemplate.queryForObject("ncode.getObjType", tmpPo);
    }
}
