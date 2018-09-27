package com.customize.sd.selfsvc.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;

/**
 * <�����ӿڵ���>
 * <������ϸ����>
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-12-27]
 * @see  [�����/����]
 * @since  [��Ʒ/OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����]
 */
public class ReissueCardBean extends BaseBeanSDImpl
{
    private static Log logger = LogFactory.getLog(ReissueCardBean.class);
    
    /**
     * �û�������֤
     */
    private UserLoginBean userLoginBean;
    
    /**
     * <У������֤��Ϣ���ֻ����Ƿ����>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
   	 * @remark create by zKF69263 2014-12-27 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public void checkReissueIdCard(String idCardNo, String telnum, String curMenuId, 
    		TerminalInfoPO termInfo)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkReissueIdCard(msgHeader, idCardNo);
    	
        // ����ʧ��
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException("У���û�����֤��Ϣ���ֻ�����ʧ��");
        }
        
        CTagSet tagSet = (CTagSet)rw.getReturnObject();
        //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTBYSERVNUM))
        {
            String[] openEbusRtn = new String[] {"resultcode"};
            String[] destRtn = new String[] {"ifvalid"};
            tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_BUSI_CHKCERTBYCERT, openEbusRtn, destRtn);
            // У������У��ͨ�� 0
            if (!"0".equals(tagSet.GetValue("ifvalid")))
            {
                throw new ReceptionException("�û�����֤��Ϣ���ֻ����벻���");
            }             
        }
        else
        {
            // У������У��ͨ�� 1����ͨ����0
            if (!"1".equals(tagSet.GetValue("ifvalid")))
            {
                throw new ReceptionException("�û�����֤��Ϣ���ֻ����벻���");
            }
        }
        // modify end lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
    }
    
    /** 
     * <У���û��ֻ�����>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-27 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public NserCustomerSimp checkSubTelPwd(String telnum, String password, String curMenuId, TerminalInfoPO termInfo)
    {
        try
        {
            // �ֻ��������3DES����
            DESedeEncrypt encrypt = new DESedeEncrypt();
            password = encrypt.encrypt(password);
        }
        catch (Exception e)
        {
            logger.error("ɽ��������֤ʱ�������������", e);
            throw new ReceptionException("�������ʧ��");
        }
        
        // ����CRMͳһ��֤�ӿڽ�����֤��������Ҫ����3DES����
        Map returnMap = userLoginBean.checkPassword(termInfo, telnum, curMenuId, password);
        
        // ������<>100ʱ����ʾ
        if (returnMap == null || !"100".equals(String.valueOf(returnMap.get("errcode"))))
        {
            logger.error("ʹ�÷����������������֤ʧ�ܣ��ֻ����룺" + telnum);
            throw new ReceptionException("ʹ�÷������������֤ʧ��");
        }
        
        // �����ֻ������ѯ�û���Ϣ
        Map customerSimpMap = userLoginBean.getUserInfo(telnum, termInfo);
        
        // ȡ��¼�û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
        
        if (customerSimp == null)
        {
            logger.error("��ѯ�û���Ϣʧ�ܣ��ֻ����룺" + telnum + " " + customerSimpMap.get("returnMsp"));
            throw new ReceptionException("��ѯ�û���Ϣʧ��");
        }
        
        return customerSimp;
    }
    
    /**
     * <��������У��>
     * <������ϸ����>
     * @param telnum
     * @param curMenuId
     * @param termInfo
     * @see [�ࡢ��#��������#��Ա]
   	 * @remark create by zKF69263 2014-12-27 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public void checkReissueNum(String telnum, String curMenuId, TerminalInfoPO termInfo)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkReissueNum(msgHeader);  
        
        // ����ʧ��
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * <�������>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
   	 * @remark create by zKF69263 2014-12-27 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public Map<String,Object> countReissueFee(String servnum, String iccid, String blankCardNum, 
    		String curMenuId, TerminalInfoPO termInfo)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        // �������
        ReturnWrap rw = this.getSelfSvcCallSD().countReissueFee(msgHeader,iccid,blankCardNum);  
        
        // ����ʧ��
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
        
        //������ϸ
        List<FeeConfirmPO> feeList = new ArrayList<FeeConfirmPO>();
        
        CRSet crSet = (CRSet)rw.getReturnObject();
        
        //�ܷ���
        BigDecimal totalFee = new BigDecimal("0");
        
        //�������ط�����ϸ����������úϼ�
        for(int i=0; i < crSet.GetRowCount(); i++)
        {
            FeeConfirmPO feePO = new FeeConfirmPO();
            
            //����������
            feePO.setFeeName(crSet.GetValue(i, 0));
            
            //Ӧ�ս��(��תԪ)
            feePO.setFee(CommonUtil.fenToYuan(crSet.GetValue(i, 1)));
            
            //�Żݷ���(��תԪ)
            feePO.setPrivFee(CommonUtil.fenToYuan(crSet.GetValue(i, 2)));
            
            //���ô���
            feePO.setFeeKind(crSet.GetValue(i, 3));
            
            //�շ����ͣ�ʵ�ա����͡����յ�
            feePO.setFeeType(crSet.GetValue(i, 4));
            
            //ʵ�շ���=Ӧ�ս��-�Żݷ���
            feePO.setRealFee(new BigDecimal(feePO.getFee()).subtract(new BigDecimal(feePO.getPrivFee())).toString());
            
            //�����ܷ���
            totalFee = totalFee.add(new BigDecimal(feePO.getRealFee()));
            
            feeList.add(feePO);
        }
        
        //�ܷ���
        FeeConfirmPO feeConfirmPO = new FeeConfirmPO();
        feeConfirmPO.setFeeName("���úϼ�");
        feeConfirmPO.setRealFee(totalFee.toString());
        feeList.add(feeConfirmPO);
        
        map.put("feeList", feeList);
        map.put("recFee", totalFee.toString());
        
        return map;
    }
    
    /**
     * <�����ύ>
     * <������ϸ����>
     * @param servnum
     * @param curMenuId
     * @param recFee
     * @param payType
     * @param simInfo
     * @param termInfo
     * @param chargeType �ɷ�����
     * @param terminalSeq ���нɷ���ˮ��
     * @return
     * @remark create by zKF69263 2014-12-27 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public String reissueCommit(String servnum, String curMenuId, String recFee, String payType, String blankno,
        SimInfoPO simInfo, TerminalInfoPO termInfo, String chargeType, String terminalSeq)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
        ReturnWrap rw = this.getSelfSvcCallSD().reissueCommit(msgHeader,
            recFee, payType, blankno, simInfo, chargeType + termInfo.getBankno(), terminalSeq);
		// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

        // ����ʧ��
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet tagSet = (CTagSet)rw.getReturnObject();
        
        // ��ˮ��
        return tagSet.GetValue("recoid");
    }

    /**
     * @return ���� userLoginBean
     */
    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    /**
     * @param ��userLoginBean���и�ֵ
     */
    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }
}