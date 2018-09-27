/*
 * �� �� ��:  CardInstallBean.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  �հ׿�����Bean
 * �� �� ��:  zKF69263
 * �޸�ʱ��:  2014-12-27
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �հ׿�����Bean
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-12-27]
 * @see  [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class CardInstallBean extends BaseBeanSDImpl
{
    /** 
     * ����ʱ֤������У��
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param certType ֤������
     * @param certId ֤������
     * @see [�ࡢ��#��������#��Ա]
     */
    public void chkCertSubs(TerminalInfoPO termInfo, String curMenuId, String certType, String certId)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());

        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().chkCertSubs(msgHeader, certType, certId);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException("֤������У��ʧ��");
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        //modify begin lwx439898 2017-05-06 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTINFOFORINSTALL))
        {
            String[] openEbusRtn = new String[] {"ifvalid"};
            String[] destRtn = new String[] {"ifValid"};
            cTagSet = CommonUtil.rtnConvert(cTagSet, BusinessIdConstants.CLI_BUSI_CHKCERTINFOFORINSTALL, openEbusRtn, destRtn);
        } 
        //modify end lwx439898 2017-05-06 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2
        
        // ����ifValidУ��ͨ�� 1����ͨ����0
        String ifValid = cTagSet.GetValue("ifValid");
        
        // ������ýӿڳɹ������صĲ���ifValid У��ͨ�� 1����ͨ����0 
        if (!"1".equals(ifValid))
        {
            throw new ReceptionException("����֤�������µ���Ч�û����������û�����");
        }
    }
    
    /**
     * ��ѯ�ֻ������б�
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param selTelRule ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ 4:���
     * @param telPrefix ����ǰ׺ sele_rule = 2ʱ�����û�����ƣ�Ϊ_______�������ƣ�������7λ�����油_ sele_rule = 3ʱ��Ϊ����
     * @param telSuffix �����׺ sele_rule = 2ʱ��Ϊ���� sele_rule = 3ʱ������4λ�����油_
     * @pramam ProdTempletPO ��Ʒģ��
     * @return List<ServerNumPO>
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ServerNumPO> qryTelNumberListByRule(TerminalInfoPO termInfo, String curMenuId,
        String selTelRule, String telPrefix, String telSuffix, String mainProdid)
    {
    	String fitmod = "";
        // ����ǰ׺�����Ȳ����ں��油_
        // ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
        if ("2".equals(selTelRule))
        {
            // �ұ߲���11λ_
        	fitmod = StringUtils.rightPad((null == telPrefix) ? "" : telPrefix, 11, "_");
        }
        // �����׺,���Ȳ����ں��油_
        else if("3".equals(selTelRule))
        {
            // �ұ߲���4λ_
        	fitmod = "_______" + StringUtils.rightPad((null == telSuffix) ? "" : telSuffix, 4, "_");;
        }
        else if ("4".equals(selTelRule))
        {
        	fitmod = "";
        }
        
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().qryTelNumberListByRule(msgHeader, termInfo.getOrgid(), fitmod, mainProdid);
        
        // ���ýӿ�ʧ��
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            // ������Ϣ�ʹ����붨��
            String errMsg = "δ��ѯ�����������ļ�¼";
            
            // ��ǰ׺��ѯ
            if ("2".equals(selTelRule))
            {
                // ��ѯ���� 2����ǰ׺��ѯ
                errMsg = "δ��ѯ�����������ļ�¼(ǰ׺��" + telPrefix + ")";
            }
            // ����׺��ѯ
            else if("3".equals(selTelRule))
            {
                // ��ѯ���� 3������׺��ѯ
                errMsg = "δ��ѯ�����������ļ�¼(��׺��" + telSuffix + ")";
            }
            
            // ���ýӿ�ʧ�ܷ����쳣��Ϣ
            throw new ReceptionException(errMsg);
        }
        // ��ѯѡ���б�
    	CRSet crset = (CRSet)rw.getReturnObject();
        
        List<ServerNumPO> serverNumList = new ArrayList<ServerNumPO>();
        
        // ѭ��crset������List��
        for (int i = 1; i < crset.GetRowCount(); i++)
        {
            ServerNumPO serverNumPO = new ServerNumPO();
            
            // �ֻ���
            serverNumPO.setTelnum(crset.GetValue(i, 0));
            
            // ѡ�ŷ� ��λ����
            serverNumPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 1)));
            
            // ��λ
            serverNumPO.setOrgId(crset.GetValue(i, 3));
            
            // ���׷��ã�������ѣ� ��λ����
            serverNumPO.setLowConsumFee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
           
            // Ԥ����� ��λ����
            serverNumPO.setLowConsumPre(CommonUtil.fenToYuan(crset.GetValue(i, 5)));
            
            // ����serverNumList��
            serverNumList.add(serverNumPO);
        }
        
        // ����
        return serverNumList;
    }
    
    /**
     * ������Դ��ѡ�ӿ�
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param telnum �ֻ�����
     * @see [�ࡢ��#��������#��Ա]
     */
    public void telNumTmpPick(TerminalInfoPO termInfo, String curMenuId, String telNum)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().telNumTmpPick(msgHeader, telNum);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * У��հ׿���Դ�Ƿ����
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param blankNo �հ׿����к�
     * @param prodid �����Ʒ
     * @param recType �������ͣ�����Install������ChangeEnum
     * @see [�ࡢ��#��������#��Ա]
     */
    public void chkBlankNo(TerminalInfoPO termInfo, String curMenuId, String blankNo, String prodid, String recType)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());

        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().chkBlankNo(msgHeader, termInfo, blankNo, prodid, recType);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /** 
     * У��հ׿��Ƿ���Ԥ�ÿտ�
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param blankNo �հ׿����к�
     * @param telNum �ֻ�����
     * @see [�ࡢ��#��������#��Ա]
     */
    public void chkPreSetBlankCard(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum, String recType)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().chkPreSetBlankCard(msgHeader, blankNo, telNum, recType);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        // 1:�ǣ�0������
        String isPresetBlankCard = null;
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKISPRESETBLANKCARD))
        {
            isPresetBlankCard = cTagSet.GetValue("ISPRESETBLANKCARD");
        }
        else
        {
            isPresetBlankCard = cTagSet.GetValue("status");
        }
        // ������ýӿڳɹ������صĲ���isPresetBlankCardУ�鲻ͨ��
        if (!"1".equals(isPresetBlankCard))
        {
            throw new ReceptionException("�ÿ�����Ԥ�ÿհ׿�");
        }
        
    }
    
    /**
     * ���㿪������
     * @param termInfo
     * @param curMenuId
     * @param telnum
     * @param mainprodid �����ƷID
     * @param prodtempletid  �����Ʒģ��iD
     * @param simInfoPO SIM����Ϣ
     * @param blankcardno �հ׿���
     * @return
     */
    public CRSet reckonRecFee(TerminalInfoPO termInfo, String curMenuId, String telnum, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno)
    {
        //��װ��Ϣͷ
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallSD().reckonRecFee(msgHead, tpltTempletPO, simInfoPO, blankno);
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return (CRSet)rw.getReturnObject();
    }
    
    /**
     * ��ȡд����Ϣ��������
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId �˵���Ϣ
     * @param telNum �ֻ���
     * @return 
     * @see [�ࡢ��#��������#��Ա]
     */
    public SimInfoPO getEncryptedData(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum, String recType)
    {
        // ��װ��Ϣͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telNum);

        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().getEncryptedData(msgHeader, blankNo, recType, termInfo.getRegion());
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        SimInfoPO simInfoPO = new SimInfoPO();
        
        // iccid
        simInfoPO.setIccid(v.GetValue("ICCID"));
        
        // imsi
        simInfoPO.setImsi(v.GetValue("IMSI"));
        
        // ��������
        simInfoPO.setIssueData(v.GetValue("issueData"));
        
        // д����ˮ
        simInfoPO.setFormNum(v.GetValue("fromnum"));
        
        // �ϵ�iccid
        simInfoPO.setOldiccid(v.GetValue("OLDICCID"));
        
        return simInfoPO;
    }
    
    /**
     * �����ύ
     * <������ϸ����>
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param idCardPO ���֤��ϢPO
     * @param tpltTempletPO �����ƷPO
     * @param telnum �ֻ�����
     * @param totalfee �û����ɷ���
     * @param SimInfoPO ����Ϣ
     * @param passwd ��������
     * @param chargeType �ɷ�����
     * @param terminalSeq ���нɷ���ˮ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> terminalInstall(TerminalInfoPO termInfo, String curMenuId, IdCardPO idCardPO,
        ProdTempletPO tpltTempletPO, String telnum, String totalfee, SimInfoPO simInfoPO, String passwd,
        String chargeType, String terminalSeq)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
        // ���ÿ����ӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().terminalInstall(msgHeader,
            simInfoPO, tpltTempletPO, idCardPO, totalfee, passwd, telnum, chargeType + termInfo.getBankno(), terminalSeq);
		// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        Map<String, String> map = new HashMap<String, String>();
        
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SELFINSTALL))
        {
            
            // ������ˮ
            map.put("formnum", v.GetValue("FORMNUM"));
            
            // subsid
            map.put("subsid", v.GetValue("install_subsid"));
            
            // ������ˮ
            map.put("dealnum", v.GetValue("RECOID"));
        }
        else
        {
            // ������ˮ
            map.put("formnum", v.GetValue("install_formnum"));
            
            // subsid
            map.put("subsid", v.GetValue("install_subsid"));
            
            // ������ˮ
            map.put("dealnum", v.GetValue("install_recoid"));
            
        }
        
        return map;
    }
    
    /**
     * д���ɹ�ʧ�ܽӿ�
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId �˵�id
     * @param blankno �հ׿����к�
     * @param SimInfoPO ����Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateWriteCardResult(TerminalInfoPO termInfo, String curMenuId, String blankno, SimInfoPO simInfoPO)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		"", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // ���ýӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().updateWriteCardResult(msgHeader, blankno, simInfoPO);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /** 
     * ҵ�����ǰ��¼ҵ�������Ϣ
     * 
     * @param termInfo �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId �˵�id
     * @param servnumber �ֻ�����
     * @param chargeType ��������
     * @param terminalSeq ���нɷ���ˮ��
     * @param amount �ɷ��ܶ�
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_�����ն˻����Ԥ������
     */
    public void writeNetFeeInfo(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
        String servnumber, String chargeType, String terminalSeq, String amount, String acceptType)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);
        
        // ���ÿ����ӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().writeNetFeeInfo(msgHeader,
            chargeType + termInfo.getBankno(), acceptType, terminalSeq, amount, null);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
}
