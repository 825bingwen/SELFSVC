package com.customize.sd.selfsvc.charge.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface FeeChargeService
{
    // �������ɷ���־
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record);
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID();
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * <��֤���Ѻ����Ƿ�ʡ���û�>
     * <������ϸ����>
     * @param telnum
     * @return true ʡ���û���false ʡ���û�
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-7-19 11:12:09 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
     */
    public int anthSdTelnum(String region);
    
    /**
     * �����������ӽɷ���־
     * 
     * @param chargeLogOID �ɷ���ˮ
     * @param morePhones ��������
     * @param termInfo �ն˻�
     * @param chargeType �ɷ�����
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	public void addCardLog(String chargeLogOID, List<CardChargeLogVO> morePhones,
			TerminalInfoPO termInfo, String chargeType);

	/**
     * <�������ɸ�����־>
     * <������ϸ����>
     * @param cardChargeVO
	 * @throws UnsupportedEncodingException 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	public void updateMorePhoneLog(CardChargeLogVO cardChargeVO,
			CardChargeLogVO cardChargeLogVO, TerminalInfoPO termInfo) throws UnsupportedEncodingException;
	
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     * @remark create by sWX219697 2015-7-1 �޸�bug93196 ����Ͷ����ϸʱ��ֻ����CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO);
}
