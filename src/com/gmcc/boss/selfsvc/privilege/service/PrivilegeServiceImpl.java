package com.gmcc.boss.selfsvc.privilege.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.common.service.BaseServiceImpl;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.privilege.dao.PrivilegeDaoImpl;
import com.gmcc.boss.selfsvc.privilege.model.CustNcodeInfoPO;
import com.gmcc.boss.selfsvc.privilege.model.GroupNcodePO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class PrivilegeServiceImpl extends BaseServiceImpl implements PrivilegeService
{
    public PrivilegeDaoImpl privilegeDaoImpl;
	
    /**
     * ɽ���ƶ�����ҵ�񶩹�
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param telnum
     * @param spid
     * @param bizid
     * @return
     * @remark create by wWX217192 2014-04-02 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
     */
    public String addSPReception(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String telnum, String spid, String bizid)
    {
    	String flag = "";
    	// ��װ������ͷ
    	MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
    	
    	// ��������ҵ�񶩹�
    	ReturnWrap rw = selfSvcCall.orderSP(header, spid, bizid);
    	
    	// �ӿڵ��óɹ�
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
    		flag = rw.getStatus() + "";
    		this.insertRecLog(curMenuId, "0", "0", "0", "��������ҵ��ɹ�!");
    	}
    	else
    	{
    		// ������Ϣ
    		String errorMsg = "";
    		
    		// ��������ϢΪ�գ�����ʾĬ�Ϸ�����Ϣ
    		if(StringUtils.isEmpty(rw.getReturnMsg()))
    		{
    			errorMsg = "ҵ�����ʧ�ܣ����Ժ����ԣ�";
    		}
    		else
    		{
    			errorMsg = rw.getReturnMsg();
    		}
    		
    		this.insertRecLog(curMenuId, "0", "0", "1", errorMsg);
    		throw new ReceptionException(errorMsg);
    	}
    	
    	return flag;
    }
    
    /**
     * ������ID��ѯͬ��ҵ���б�
     * @param groupId ��ID
     * @param menuid �˵�id
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-4-29 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    public CustNcodeInfoPO qryCustNcodeInfo(String groupId,String menuid)
    {
        // ������id��ѯͬ��ҵ���б�
        List<GroupNcodePO> ncodeList = privilegeDaoImpl.qryGroupNcodeInfo(new GroupNcodePO(groupId));
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // �û���Ϣ
        NserCustomerSimp customer = this.getCustomer(); 
        
        CustNcodeInfoPO custNcodeInfo = new CustNcodeInfoPO();
        
        // ��װ��Ϣͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, termInfo.getOperid(), 
                termInfo.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // ����ͬ��ҵ��
        if(CollectionUtils.isEmpty(ncodeList))
        {
            throw new ReceptionException("�Բ���û���ҵ���Ӧ��ͬ��ҵ��");
        }

        //modify begin lWX431760 2017-09-28 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        ReturnWrap result = null;
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODUCTQRY))
        {
            result = selfSvcCall.recCommonProductQry(msgHeader, ncodeList.get(0).getNcode()); 
        }
        else
        {
            // ���ýӿڲ�ѯ�û��Ѱ���ҵ����Ϣ
            result = selfSvcCall.recCommonServ(msgHeader, ncodeList.get(0).getNcode(), "QRY", "", "");
        }
        //modify end lWX431760 2017-09-28 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
                
        if(SSReturnCode.ERROR == result.getStatus())
        {
            // ҵ����־
            this.insertRecLog(menuid, "0", "0", Constants.RECSTATUS_FALID, "��ѯͬ��ҵ���û�����״̬ʧ�ܣ�");
      
            if(StringUtils.isBlank(result.getReturnMsg()))
            {
                result.setReturnMsg("�Բ��𣬲�ѯͬ��ҵ���û�����״̬ʧ�ܣ����Ժ����ԣ�");
            }
            throw new ReceptionException(result.getReturnMsg());
        }
        
        CTagSet tagSet = (CTagSet) result.getReturnObject();
        
        //add begin lWX431760 2017-09-28 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODUCTQRY))
        {
            tagSet = CommonUtil.lowerTagKey(tagSet);
        }
        //add begin lWX431760 2017-09-28 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        
        if (null != tagSet)
        {            
            // ��ǰ�ײ�ncode
            custNcodeInfo.setCurrNcode(tagSet.GetValue("curncode"));
                
            // �����ײ�ncode
            custNcodeInfo.setNextNcode(tagSet.GetValue("nextncode"));
        }
        
        // �����û��Ѱ���ҵ�����ƺͷ���
        if(StringUtils.isNotBlank(custNcodeInfo.getCurrNcode()) || StringUtils.isNotBlank(custNcodeInfo.getNextNcode()))
        {
            for(GroupNcodePO ncodePO:ncodeList)
            {
                // �����ײ�
                if(ncodePO.getNcode().equals(custNcodeInfo.getCurrNcode()))
                {
                    custNcodeInfo.setCurrNcodeName(ncodePO.getNcodeName());
                    custNcodeInfo.setCurrNcodeFee(ncodePO.getFee());
                }
                
                // �����ײ�
                if(ncodePO.getNcode().equals(custNcodeInfo.getNextNcode()))
                {
                    custNcodeInfo.setNextNcodeName(ncodePO.getNcodeName());
                    custNcodeInfo.setNextNcodeFee(ncodePO.getFee());
                }
                
                // �����ײͺ������ײͶ��������ƺ�����ѭ��
                if(StringUtils.isNotBlank(custNcodeInfo.getCurrNcodeName()) 
                        && StringUtils.isNotBlank(custNcodeInfo.getNextNcodeName()))
                {
                    break;
                }
            }
        }
        
        // ����ͬ��ҵ���б�
        custNcodeInfo.setGroupNcodeList(ncodeList);
        
        return custNcodeInfo;
    }
    
    /**
     * ������ID��ncode��ѯҵ����Ϣ
     * @param groupId ��ID
     * @param ncode 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-5-26 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    public GroupNcodePO qryGroupNcodeInfo(String groupId,String ncode)
    {
        // ��ѯҵ����Ϣ
        List<GroupNcodePO> ncodeList = privilegeDaoImpl.qryGroupNcodeInfo(new GroupNcodePO(groupId, ncode));
        
        // ����ҵ����Ϣ
        if(CollectionUtils.isEmpty(ncodeList))
        {
            throw new ReceptionException("�Բ���û�в�ѯ����Ӧ���ײ���Ϣ��");
        }
        else
        {
            return ncodeList.get(0);
        }
    }
    
    /**
     * <����ͬ��ҵ���Ʒ>
     * @param custNcodeInfoPO �û�����ҵ����Ϣ
     * @param groupNcodePO Ҫ��������ҵ����Ϣ
     * @param operType �������� ADD��ͨ  DELȡ��
     * @param effectType ��Ч��ʽ 1:������Ч  3��������Ч
     * @param curMenuId �˵�id
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-5-28 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    public String commitPrivNcode(CustNcodeInfoPO custNcodeInfo, GroupNcodePO groupNcodePO, String operType, 
            String effectType, String curMenuId)
    {
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // �û���Ϣ
        NserCustomerSimp customer = this.getCustomer();
        
        // ��װ��Ϣͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //modify begin lWX431760 2017-07-19 OR_huawei_201706_780_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ���������
        ReturnWrap result = null;
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODUCTREC))
        {         
            if ("ADD".equals(operType))
            {
                operType = "PCOpRec";
            }
            else if ("DEL".equals(operType))
            {
                operType = "PCOpDel";
            }
            else if ("QRY".equals(operType))
            {
                operType = "PCOpQry";
            }
            else if ("ALL".equals(operType))
            {
                operType = "PCOpALL";
            }          
            
            result = selfSvcCall.recCommonServNK(msgHeader, groupNcodePO.getNcode(), operType, effectType, "");
        }
        else
        {
            // ҵ�����
            result = selfSvcCall.recCommonServ(msgHeader, groupNcodePO.getNcode(), operType, effectType, "");
        }
        //modify end lWX431760 2017-07-19 OR_huawei_201706_780_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ���������
        
        // ҵ��������ˮ��
        String recFormnum = "0";
        
        // ��ͨҵ��Ncode
        String addncode = "";
        
        // �˶�ҵ��Ncode
        String delncode = "";
        
        // ����ʧ��
        if (SSReturnCode.ERROR == result.getStatus())
        {
            // ҵ����־
            this.insertRecLog(curMenuId, recFormnum, "0", Constants.RECSTATUS_FALID, "ҵ������ʧ�ܣ�");
            
            if(StringUtils.isBlank(result.getReturnMsg()))
            {
                result.setReturnMsg("�Բ���" + groupNcodePO.getNcodeName() + "ҵ�����ʧ�ܣ�");
            }
            throw new ReceptionException(result.getReturnMsg());
        }
        
        // ��ȡҵ����ˮ
        CTagSet tagSet = (CTagSet)result.getReturnObject();
        
        if(null != tagSet)
        {
            addncode = tagSet.GetValue("addncode");
            
            delncode = tagSet.GetValue("delncode");
        }
        
        // ��ͨ�ɹ���ʾ
        StringBuffer successMsg = new StringBuffer();
        
        // �����û��Ѱ���ҵ�����ƺͷ���
        if(StringUtils.isNotBlank(addncode) || StringUtils.isNotBlank(delncode))
        {
            // ������id��ѯͬ��ҵ���б�
            List<GroupNcodePO> ncodeList = privilegeDaoImpl.qryGroupNcodeInfo(new GroupNcodePO(groupNcodePO.getGroupId()));
            
            for(GroupNcodePO ncodePO:ncodeList)
            {
                // ��ͨ�ɹ���ʾ
                if(addncode.equals(ncodePO.getNcode()))
                {
                    successMsg.append(ncodePO.getNcodeName() + "��ͨ�ɹ�!");
                }
                
                // �˶��ɹ���ʾ
                if(delncode.equals(ncodePO.getNcode()))
                {
                    successMsg.append(ncodePO.getNcodeName() + "�˶��ɹ�!");
                }
            }
        }
        
        if(StringUtils.isBlank(successMsg.toString()))
        {
            successMsg.append("ҵ������ɹ���");
        }
            
        // ��¼ҵ����־
        this.insertRecLog(curMenuId, recFormnum, "0", Constants.RECSTATUS_SUCCESS, "ҵ�������ɹ�");
        
        return successMsg.toString();
    }

    public PrivilegeDaoImpl getPrivilegeDaoImpl()
    {
        return privilegeDaoImpl;
    }

    public void setPrivilegeDaoImpl(PrivilegeDaoImpl privilegeDaoImpl)
    {
        this.privilegeDaoImpl = privilegeDaoImpl;
    }
    
}