/*
 * �� �� ��:  PrestoredRewardServiceImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  Ԥ������bean
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-11-29
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.bean;

import java.math.BigDecimal;
import java.util.Vector;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * Ԥ������bean
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-11-29]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PrestoredRewardBean extends BaseBeanSDImpl 
{	
	/**
	 * ҵ����Ч��У��
	 * 
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @return true�����Լ�������ҵ��false����ֹ����ҵ��
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public void checkRecValid(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId)
	{
		// ��װ����ͷ��Ϣ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ���ýӿ�
		ReturnWrap rw = getSelfSvcCallSD().checkRecValid(msgHeader);
		
		//����ʧ�ܣ����׳��쳣
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
	}
	
	/**
	 * ��ѯ�û��Ѿ����ڵĵ���
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public String qrySubsActLevelList(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId)
	{
		// ��װ����ͷ��Ϣ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ���ýӿڲ�ѯ�û��Ѵ��ڵ���
		ReturnWrap rw = getSelfSvcCallSD().qrySubsActLevelList(msgHeader);

		StringBuffer sbuf = new StringBuffer(1024);
		
		// ������������
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			// ��װ�Ѵ��ڵĵ��α����б�
	        
			CRSet crset = (CRSet) rw.getReturnObject();
			if (null != crset)
			{
				for (int i = 0; i < crset.GetRowCount(); i++)
				{
					if ((i + 1) == crset.GetRowCount())
					{
						sbuf.append("'").append(crset.GetValue(i, 0)).append("'");
					}
					else
					{
						sbuf.append("'").append(crset.GetValue(i, 0)).append("',");
					}
				}
			}
			return sbuf.toString();
		}
    	throw new ReceptionException(rw.getReturnMsg());
	}
	
	/**
	 * ��ѯ��Ʒ���
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @param actLevelId ���α���
	 * @param activityId �����
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public PrestoredRewardPO qryRewardList(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId, String actLevelId, String activityId)
	{
		// ��װ��Ϣͷ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ���ýӿڲ�ѯ��Ʒ�б�
		ReturnWrap rw = getSelfSvcCallSD().qryRewardList(msgHeader, actLevelId, activityId);
		
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		
		// ����������Ϣ
		if (SSReturnCode.SUCCESS == rw.getStatus())
		{
			StringBuffer sbuf = new StringBuffer(1024);
			CRSet crset = (CRSet) rw.getReturnObject();
			if (null != crset)
			{
				for (int i = 0; i < crset.GetRowCount(); i++)
				{
					String rewardType = crset.GetValue(i, 2);
					
					// ��Ʒ����Ϊ��Ʒ��������ƷΪʵ��
					if ("RwdGift_Good".equals(rewardType))
					{
						prestoredRewardPO.setIsGoods("1");
					}
					if ((i+1) == crset.GetRowCount())
	                {
	                    // ��Ʒ���봮
						sbuf.append(crset.GetValue(i, 0));
	                }
	                else
	                {
	                    // ��Ʒ���봮
	                	sbuf.append(crset.GetValue(i, 0)+"|");
	                }
				}
				prestoredRewardPO.setActreward(sbuf.toString());
				
				return prestoredRewardPO;
			}
			else
			{
				throw new ReceptionException("û�в�ѯ����Ӧ�Ľ�Ʒ��Ϣ");
			}
		}
		throw new ReceptionException("��ѯ��Ʒ�б�ʧ�ܣ�" + rw.getReturnMsg());
	}
	
	/**
	 * ��ѯ�����
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @param actid �����
	 * @param levelid ���α���
	 * @param rewardId ��Ʒ����
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public String qryActivityFee(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId, String actid, String levelid, String rewardId)
	{
		// ��װ��Ϣͷ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ���ýӿ�
		ReturnWrap rw = getSelfSvcCallSD().qryActivityFee(msgHeader, actid, levelid, rewardId);
		
		String recFee = "0";
		
		// ����������Ϣ
		if (SSReturnCode.SUCCESS == rw.getStatus())
		{
		    //modify begin by cwx46134 2017-05-20 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
		    if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHKPRIVANDCALCFEE))
		    {
		        Vector v =(Vector)rw.getReturnObject();
		        CTagSet tagSet = (CTagSet)v.get(0);
		        if (null != tagSet)
                {
	                recFee = String.valueOf(new BigDecimal(recFee).add(new BigDecimal(tagSet.GetValue("TOTALCONSUME"))));
                    return recFee;
                }
                else
                {
                    throw new ReceptionException("û�в�ѯ����Ӧ�ķ�����Ϣ");
                }
		    }else
		    {
		        CRSet crset = (CRSet) rw.getReturnObject();
		        
		        if (null != crset)
	            {
	                for (int i = 0; i < crset.GetRowCount(); i++)
	                {
	                    recFee = String.valueOf(new BigDecimal(recFee).add(new BigDecimal(crset.GetValue(i, 1))));
	                }
	                return recFee;
	            }
	            else
	            {
	                throw new ReceptionException("û�в�ѯ����Ӧ�ķ�����Ϣ");
	            }
		    }
            //modify end by cwx46134 2017-05-20 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
		}
		throw new ReceptionException(rw.getReturnMsg());
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
     * @param prestoredRewardPO
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_�����ն˻����Ԥ������
     */
    public void writeNetFeeInfo(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
        String servnumber, String chargeType, String terminalSeq, PrestoredRewardPO prestoredRewardPO)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);
        
        // ���ÿ����ӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().writeNetFeeInfo(msgHeader,
            chargeType + termInfo.getBankno(), Constants.ACCEPTTYPE_PRESTORED_REWARD, terminalSeq, 
            prestoredRewardPO.getTotalFee(), prestoredRewardPO);
        
        // ���ýӿ�ʧ�ܷ��ش�����Ϣ
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
	/**
	 * Ԥ����������
	 * 
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @param prestoredRewardPO �����Ϣ
	 * @param chargeType �ɷ�����
	 * @param terminalSeq ���нɷ���ˮ��
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����	
	 */
    public String recRewardCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId,
        PrestoredRewardPO prestoredRewardPO, String chargeType, String terminalSeq)
    {
		// ��װ��Ϣͷ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
		// ���ýӿ�
        ReturnWrap rw = getSelfSvcCallSD().recRewardCommit(msgHeader,
            prestoredRewardPO, chargeType + termInfo.getBankno(), terminalSeq);
		// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet ctagset = (CTagSet) rw.getReturnObject();
			
			//add begin lWX431760 2017-05-20 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
			//��дתСд
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_RECREWARDCOMMITSD))
			{
			    ctagset = CommonUtil.lowerTagKey(ctagset);
			}
			//add end lWX431760 2017-05-20 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
			// ������ˮ
			String recoid = ctagset.GetValue("recoid");
			
			return recoid;
		}
		throw new ReceptionException(rw.getReturnMsg());
	}
	
	/**
	 * ��ѯӪ���������ú��û�Ԥ�����
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @param recoid ������ˮ��
	 * @param totalFee �û�����ķ���
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����	
	 */
	public PrestoredRewardPO qryRecFeeAndPreFee(TerminalInfoPO termInfo, NserCustomerSimp customer, String menuId,
			String recoid, String totalFee)
	{
		// ��װ��Ϣͷ
		MsgHeaderPO msgHeader = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ���ýӿ�
		ReturnWrap rw = getSelfSvcCallSD().qryRecFeeAndPreFee(msgHeader, recoid, CommonUtil.yuanToFen(totalFee));
	
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		
		 // ����������Ϣ
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PRIVFEESD))
            {
                CTagSet ctagset = (CTagSet) rw.getReturnObject();
                prestoredRewardPO.setRecFee(ctagset.GetValue("RECFEE"));// Ӫ����������
                prestoredRewardPO.setPreFee(ctagset.GetValue("PREFEE")); // �û�Ԥ�����
            }
            else
            {
                CRSet crset = (CRSet) rw.getReturnObject();

                // ����crset���ݣ���ȡӪ���������ú��û�Ԥ�����
                prestoredRewardPO.setRecFee(crset.GetValue(0, 0));// Ӫ����������
                prestoredRewardPO.setPreFee(crset.GetValue(0, 1)); // �û�Ԥ�����
            }
       	
        	return prestoredRewardPO;
        }
        return null;
	}
}