package com.customize.sd.selfsvc.charge.service;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.customize.sd.selfsvc.charge.dao.FeeChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class FeeChargeServiceImpl implements FeeChargeService
{
    
    private FeeChargeDaoImpl feeChargeDaoImpl;
    
    
    /**
     * �������ɷ���־
     * 
     * @param cardChargeLogVO �������ɷ���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        feeChargeDaoImpl.insertInvoiceLog(record);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID()
    {
        return feeChargeDaoImpl.getChargeLogOID();
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateChargeLog(cardChargeLogVO);
    }
    
    /**
     * ��֤���Ѻ����Ƿ�Ϊʡ���û�
     * @param telnum
     * @return
     * @remark create by sWX219697 2014-7-19 11:14:24 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
     */
    public int anthSdTelnum(String region)
    {
    	return feeChargeDaoImpl.anthSdTelnum(region);
    }
    
    /**
     * �����������ӽɷ���־
     * 
     * @param chargeLogOID �ɷ���ˮ
     * @param morePhone ��������
     * @param termInfo �ն˻�
     * @param chargeType �ɷ�����
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    @Override
	public void addCardLog(String chargeLogOID, List<CardChargeLogVO> morePhones,
			TerminalInfoPO termInfo, String chargeType)
    {
    	for (CardChargeLogVO morePhone : morePhones)
    	{
    		morePhone.setMoreChargeOID(chargeLogOID); // ��������Ψһ��ˮ
    		morePhone.setRegion(termInfo.getRegion()); // ��������
    		morePhone.setTermID(termInfo.getTermid()); // �ն˻�id
    		morePhone.setOperID(termInfo.getOperid()); // ����Աid
    		morePhone.setPayType(Constants.PAYBYUNIONCARD); // ֧����ʽ
    		morePhone.setFee(CommonUtil.yuanToFen(morePhone.gettMoney())); // �û�ʵ�ɷ���
    		
    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    		String payDate = sdf1.format(new Date());
    		morePhone.setRecdate(payDate); // ����ʱ��
    		
    		morePhone.setStatus(Constants.CHARGE_ERROR); // 
    		morePhone.setDescription("�ۿ�֮ǰ��¼��־");
    		morePhone.setOrgID(termInfo.getOrgid());
    		morePhone.setRecType("morePhone");// ҵ�����ͣ�morePhone���������ɣ�
    		morePhone.setBankno(chargeType + termInfo.getBankno());
    		
    		feeChargeDaoImpl.addChargeLog(morePhone);
    	}
	} 
    
    /**
     * <�黰�����ɸ�����־>
     * <������ϸ����>
     * @param cardChargeVO
     * @throws UnsupportedEncodingException 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	@Override
	public void updateMorePhoneLog(CardChargeLogVO cardChargeVO,
			CardChargeLogVO morePhone, TerminalInfoPO termInfo) throws UnsupportedEncodingException
	{

		// �ն���ˮ�ţ������������ֵ���ҵ��
		cardChargeVO.setTerminalSeq(morePhone.getChargeLogOID());
		
		// �ɷ���־��ˮ
		cardChargeVO.setChargeLogOID(morePhone.getChargeLogOID());
		
		// �ɷѽ��
		cardChargeVO.setUnionpayfee(CommonUtil.yuanToFen(morePhone.gettMoney()));
		
		// ���ý�������
		cardChargeVO.setBusiType(java.net.URLDecoder.decode(cardChargeVO.getBusiType(), "UTF-8"));
		
		// ����
		cardChargeVO.setDescription(java.net.URLDecoder.decode(cardChargeVO.getDescription(), "UTF-8"));
		
		// ��֯����
		cardChargeVO.setOrgID(termInfo.getOrgid());
		
		// ��������
		cardChargeVO.setRegion(termInfo.getRegion());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        // ����ʱ��
        cardChargeVO.setRecdate(payDate);
		
        feeChargeDaoImpl.updateCardChargeLog(cardChargeVO);
	}
	
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     * @remark create by sWX219697 2015-7-1 �޸�bug93196 ����Ͷ����ϸʱ��ֻ����CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateCashDetail(cardChargeLogVO);
    }
    
    public FeeChargeDaoImpl getFeeChargeDaoImpl()
    {
        return feeChargeDaoImpl;
    }
    
    public void setFeeChargeDaoImpl(FeeChargeDaoImpl feeChargeDaoImpl)
    {
        this.feeChargeDaoImpl = feeChargeDaoImpl;
    }

}
