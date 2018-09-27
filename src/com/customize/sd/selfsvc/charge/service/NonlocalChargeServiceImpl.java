/*
 * �� �� ��:  NonlocalChargeServiceImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <ɽ����ؽɷ�serviceʵ����>
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  Apr 27, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.charge.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.customize.sd.selfsvc.common.service.IFeeServiceSD;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.ChargeService;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * <ɽ����ؽɷ�serviceʵ����>
 * <������ϸ����>
 * 
 * @author  jWX216858
 * @version  [�汾��, Apr 27, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾 OR_SD_201503_949_�����ն�������ʡ�ɷѹ��ܵ�֧��]
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class NonlocalChargeServiceImpl extends BaseServiceSDImpl implements INonlocalChargeService 
{
	/**
     * ��־
     */
    private static Log logger = LogFactory.getLog(NonlocalChargeServiceImpl.class);
    
    @Autowired
    @Qualifier("chargeService")
	private ChargeService chargeService;
    
    /**
     * ɽ���ɷ���־
     */
    @Autowired
    private transient IFeeServiceSD feeServiceSDImpl;
    
    /**
	 * �ͻ�Ӧ�ɷ����ܶ��ѯ
	 * 
	 * @param servNumber �ֻ�����
	 * @param curMenuId ��ǰ�˵�
	 * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
    @Override
	public CardChargeLogVO qryfeeChargeAccount(String servNumber, String curMenuId)
    {
    	CardChargeLogVO chargeLogVO = new CardChargeLogVO();
    	
    	// ��ȡ�ն˻���Ϣ
    	TerminalInfoPO termInfo = this.getTermInfo();
    	
    	// ��ֵ�ɷѷ�ʽ�б�
        List<MenuInfoPO> usableTypes = CommonUtil.qryUsablePayTypes(termInfo.getTermgrpid(), curMenuId);
        
        logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + usableTypes.size() + "��");
        
        if (usableTypes.size() == 0)
        {
            logger.error("û�п��õĳ�ֵ��ʽ");
            
            // ��¼��־
            this.insertRecLogTelnum(servNumber, Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, "û�п��õĳ�ֵ��ʽ");
            throw new ReceptionException("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
        }
    	
    	// ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_TELNUM, servNumber);

        // ���ýӿڲ�ѯ�ͻ�Ӧ���ܽ��
        ReturnWrap rw = selfSvcCall.qryPayAmount(msgHeader, termInfo.getOrgid());
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            String retMsg = rw.getReturnMsg();
            if(StringUtils.isEmpty(retMsg))
            {
                retMsg = "�Բ��𣬲�ѯ�ͻ��˻���Ϣʧ�ܣ�";
            }
            
            // ��¼������־
            this.insertRecLogTelnum(servNumber, Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, retMsg);

            throw new ReceptionException(retMsg);
        }
        
        CTagSet ctagSet = (CTagSet)rw.getReturnObject();
        
        if(StringUtils.isEmpty(ctagSet.GetValue("ProvinceCode")))
        {
        	// ��¼������־
            this.insertRecLogTelnum(servNumber, Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, "�Բ���û�в�ѯ����Ӧ���˻���Ϣ��");

            throw new ReceptionException("�Բ���û�в�ѯ����Ӧ���˻���Ϣ��");
        }
        
        // �ֻ��������ʡ�ݱ���
        String provinceCode = ctagSet.GetValue("ProvinceCode").substring(0,3);
        
        // ��ǰʡ�ݱ���
        String currProvinceCode = CommonUtil.getParamValue(Constants.SH_CURRPROVINCE_CODE);
        
        if(currProvinceCode.equals(provinceCode))
        {
        	// ��¼������־
            this.insertRecLogTelnum(servNumber, Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, "�Բ��𣬸ò˵����ڿ�ʡ��ؽ��ѣ������ֻ��������ڱ�ʡ���뵽������Ӧ�˵����н��ѡ�");

            throw new ReceptionException("�Բ��𣬸ò˵����ڿ�ʡ��ؽ��ѣ������ֻ��������ڱ�ʡ���뵽��ֵ���Ѳ˵����н��ѡ�");
        }
        
        // ʡ�ݱ���
        chargeLogVO.setProvinceCode(provinceCode);
        
        // �ͻ�����
        chargeLogVO.setCustName(ctagSet.GetValue("CustName"));
        
        // Ӧ������
        chargeLogVO.setYingjiaoFee(CommonUtil.fenToYuan(CommonUtil.liToFen(ctagSet.GetValue("PayAmount"), BigDecimal.ROUND_UP)));
        
        // Ԥ�������
        chargeLogVO.setBalance(CommonUtil.fenToYuan(CommonUtil.liToFen(ctagSet.GetValue("Balance"), BigDecimal.ROUND_DOWN)));
        
        // �ֻ�����
        chargeLogVO.setServnumber(servNumber);
        
        // ���ó�ֵ��ʽ�б�
        chargeLogVO.setUsableTypes(usableTypes);
        
		return chargeLogVO;
	}
    
    /**
	 * ɽ��ʡ����ؽɷ��ύ
	 * 
	 * @param chargeLogVO �ɷ���Ϣ
	 * @param curMenuId ��ǰ�˵�id
	 * @param unionRetCode �������ش�����
	 * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
    public CardChargeLogVO chargeCommit(CardChargeLogVO chargeLogVO, String curMenuId, String unionRetCode)
    {
    	// ���ý���״̬
        chargeLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
        
        // ��ȡ����������ѵĽ�����ˮ��
        String dealNum = chargeService.getNonlocalChargeSeq();
    	
        // ���ýɷѽ�����ˮ
        chargeLogVO.setDealnum(dealNum);
        
        // ����ʱ��
        chargeLogVO.setDealTime(DateUtil.getCurrentDateTime());
        
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, getTermInfo().getOperid(), getTermInfo().getTermid(), "",
            MsgHeaderPO.ROUTETYPE_TELNUM, chargeLogVO.getServnumber());
    
        // ���ýӿڲ�ѯ�ͻ�Ӧ���ܽ��
        ReturnWrap rw = selfSvcCall.nonlocalCharge(
        		msgHeader, 
        		dealNum, 
        		CommonUtil.fenToLi(CommonUtil.yuanToFen(chargeLogVO.gettMoney())), 
        		getTermInfo().getOrgid());
       
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            String retMsg = rw.getReturnMsg();
            if(StringUtils.isEmpty(retMsg))
            {
                retMsg = "�Բ��𣬿�ʡ��ؽɷѲ���ʧ�ܣ�";
            }
            
            // ���½ɷ���־
            feeServiceSDImpl.updateChargeLog(chargeLogVO, retMsg, unionRetCode, Constants.PAYSUCCESS_CHARGEERROR);
            
            // ��¼������־
            this.insertRecLogTelnum(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, retMsg);

            throw new ReceptionException(retMsg);
        }
        
        // ���½ɷ���־
        feeServiceSDImpl.updateChargeLog(chargeLogVO, "��ʡ��ؽɷѳɹ�", unionRetCode, Constants.CHARGE_SUCCESS);
        
        // ��¼�ɹ���־
        this.insertRecLogTelnum(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_SUCCESS, "��ʡ��ؽɷѳɹ�");
        
        return chargeLogVO;
    }
    
}