/*
 * �� �� ��:  ConnUtil.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <���˳���������>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 14, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
 */
package com.customize.sd.selfsvc.serviceinfo.dao;
import java.util.List;

import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * <���п����ܰ�>
 * <������ϸ����>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BindBankCardDaoImpl extends BaseDaoImpl
{
    /**
     * ��ѯ��ˮ��
     * 
     * @return
     */
    public String qryOrderId()
    {
        return (String) this.sqlMapClientTemplate.queryForObject("bindBankCard.qryOrderId");
    }
    
    /**
     * <��������չʾ��Ϣ>
     * <������ϸ����>
     * @param bankCardType 0����ǿ���1�����ÿ�
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-7 11:38:30 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    @SuppressWarnings("unchecked")
	public List<BankCardInfoPO> getBankInfoList(String bankCardType)
    {
        return sqlMapClientTemplate.queryForList("bindBankCard.getBankInfoByType", bankCardType);
    }
}
