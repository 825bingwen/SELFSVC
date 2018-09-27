/*
 * �ļ�����ReceptionBean.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������ҵ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-21
 * �޸����ݣ�����
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.privilege.dao.PrivilegeDaoImpl;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * ҵ�������������κθ�������
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-21
 * @see
 * @since
 */
public class ReceptionBean extends BaseBeanImpl
{
    protected PrivilegeDaoImpl privilegeDaoImpl; 
    
    public void setPrivilegeDaoImpl(PrivilegeDaoImpl privilegeDaoImpl)
    {
        this.privilegeDaoImpl = privilegeDaoImpl;
    }

    /**
     * ���ú�̨�ӿڣ���ѯ��ҵ���Ӧ���ʷ���Ϣ
     * 
     * @param customerSimp���û���Ϣ
     * @param termInfo���ն���Ϣ
     * @param nCode
     * @param menuID
     * @return
     * @see
     */
    public String qryFeeMessage(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String nCode, String menuID, String operType)
    {
        String message = "";
        
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("nCode", nCode);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        //modify begin lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        ReturnWrap rw = selfSvcCall.queryFeeMessage(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {          
            CTagSet tagset = (CTagSet)rw.getReturnObject();
            message = tagset.GetValue("desc");            
        }
        //modify end lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        
        return message;
    }
    
    /**
     * ҵ������
     * 
     * @param customerSimp���û���Ϣ
     * @param termInfo���ն���Ϣ
     * @param nCode
     * @param operType���������ͣ���ͨ����ȡ��
     * @param effectType
     * @param param
     * @param menuID
     * @return
     * @see
     * @Remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
     */
    public ReturnWrap recCommonServ(NserCustomerSimp customer, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String menuID)
    {
        // ��װ��Ϣͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, termInfo.getOperid(), 
        		termInfo.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
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
            return selfSvcCall.recCommonServNK(msgHeader, nCode, operType, effectType, param);
        }
        else
        {
            return selfSvcCall.recCommonServ(msgHeader, nCode, operType, effectType, param);
        }
        
    }
    
    /**
     * ����ר�ã���ѯҵ������״̬
     * 
     * @param customerSimp���û���Ϣ
     * @param termInfo���ն���Ϣ
     * @param nCode
     * @param operType���������ͣ���ͨ����ȡ��
     * @param effectType
     * @param param
     * @param menuID
     * @return
     * @se
     * @remark modify by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_������֪����ˮ�߲���EBUS����
     */
    public ReturnWrap qryRecStatusHub(NserCustomerSimp customer, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String menuID)
    {
        // ��װ��Ϣͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, termInfo.getOperid(), 
        		termInfo.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = selfSvcCall.qryRecStatusHub(msgHeader, nCode, operType);
        
        // ����תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
    	{
			// �����ӿڷ��ص��������·�װ
			if (SSReturnCode.SUCCESS == rw.getStatus())
			{
				CRSet crset = new CRSet(2);
    			crset.AddRow();
    			if (rw.getReturnObject() instanceof Vector)
    			{
    				Vector v = (Vector) rw.getReturnObject();
    				CTagSet ctag = (CTagSet) v.get(0);
    				
    				// ncode
    				crset.SetValue(0, 0, ctag.GetValue("BOSSCODE"));
    				
    				// 1����ͨ��������δ��ͨ
    				crset.SetValue(0, 1, ctag.GetValue("CURSTATUS"));
    				
    			}
    			if (rw.getReturnObject() instanceof CTagSet)
    			{
    				CTagSet ctag = (CTagSet)rw.getReturnObject();
    				
    				// ncode
    				crset.SetValue(0, 0, ctag.GetValue("BOSSCODE"));
    				
    				// 1����ͨ��������δ��ͨ
    				crset.SetValue(0, 1, ctag.GetValue("CURSTATUS"));
    				
    			}
    			rw.setReturnObject(crset);
			}
    	}
        return rw;
    }
    
    /**
     * ����ncode��ѯ�ر���ʾ��Ϣ
     * 
     * @param customerSimp
     * @param termInfo
     * @param ncode
     * @param operType
     * @param tipType
     * @param menuID
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public String qryNcodeTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String ncode, 
            String operType, String tipType, String menuID)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // �˵�
        paramMap.put("menuid", menuID);
        
        // ҵ��Ψһ��ʶ
        paramMap.put("process_code", "cli_qry_ncodetips");
        
        //��֤��
        paramMap.put("verify_code", "");
        
        // ���Ա�� ����Ϊ�գ�0��ʾ����Ϊ�����ã�1��ʾ��ʽ����
        paramMap.put("testflag", "");
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // ·�� 0������region 1�������ֻ�����
        paramMap.put("route_type", "1");
        
        // ·��ֵ�����ֻ���·�ɴ��ֻ����룬������·�ɴ�region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // ���ò���Աid
        paramMap.put("operatorid", termInfo.getOperid());
        
        // �����¼���λ��Ϣ
        paramMap.put("unitid", "");
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        // �ֻ�����
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // ��������
        paramMap.put("rectype", "ChangeProduct");
        
        // ncode
        paramMap.put("ncode", ncode);
        
        // ��������
        paramMap.put("operType", operType);
        
        // ��ʾ����
        paramMap.put("tipType", tipType);
        
        ReturnWrap rw = selfSvcCall.qryNcodeTips(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    return crset.GetValue(0, 3).trim();
                }                
            }
        }
        
        return "";
    }
        
    /**
     * ���ݶ�������ѯ�ر���ʾ��Ϣ
     * 
     * @param customerSimp
     * @param termInfo
     * @param recType
     * @param objectID
     * @param objectType
     * @param operType
     * @param tipType
     * @param menuID
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public String qryObjectTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String recType, 
            String objectID, String objectType, String operType, String tipType, String menuID)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // �˵�
        paramMap.put("menuid", menuID);
        
        // ҵ��Ψһ��ʶ
        paramMap.put("process_code", "cli_qry_objecttips");
        
        //��֤��
        paramMap.put("verify_code", "");
        
        // ���Ա�� ����Ϊ�գ�0��ʾ����Ϊ�����ã�1��ʾ��ʽ����
        paramMap.put("testflag", "");
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // ·�� 0������region 1�������ֻ�����
        paramMap.put("route_type", "1");
        
        // ·��ֵ�����ֻ���·�ɴ��ֻ����룬������·�ɴ�region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // ���ò���Աid
        paramMap.put("operatorid", termInfo.getOperid());
        
        // �����¼���λ��Ϣ
        paramMap.put("unitid", "");
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        // �ֻ�����
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // ��������
        paramMap.put("recType", recType);
        
        List<Map<String, String>> prods = new ArrayList<Map<String, String>>();
        
        Map<String, String> prodMap = new HashMap<String, String>();
        
        // �������
        prodMap.put("objectID", objectID);
        
        // ��������
        prodMap.put("objectType", objectType);
        
        // ��ʾ����
        prodMap.put("tipType", tipType);
        
        // ��������
        prodMap.put("operType", operType);
        
        prods.add(prodMap);
        
        ReturnWrap rw = selfSvcCall.qryObjectTips(paramMap, prods);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    return crset.GetValue(0, 4).trim();
                }                
            }
        }
        
        return "";
    }
    
    /**
     * ���ݶ�������ѯ�ر���ʾ��Ϣ
     * 
     * @param customerSimp
     * @param termInfo
     * @param recType
     * @param menuID
     * @param prods ��Ʒ�б�
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public Map<String, String> qryObjectTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String recType, 
            String menuID, List<Map<String, String>> prods)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // �˵�
        paramMap.put("menuid", menuID);
        
        // ҵ��Ψһ��ʶ
        paramMap.put("process_code", "cli_qry_objecttips");
        
        //��֤��
        paramMap.put("verify_code", "");
        
        // ���Ա�� ����Ϊ�գ�0��ʾ����Ϊ�����ã�1��ʾ��ʽ����
        paramMap.put("testflag", "");
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // ·�� 0������region 1�������ֻ�����
        paramMap.put("route_type", "1");
        
        // ·��ֵ�����ֻ���·�ɴ��ֻ����룬������·�ɴ�region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // ���ò���Աid
        paramMap.put("operatorid", termInfo.getOperid());
        
        // �����¼���λ��Ϣ
        paramMap.put("unitid", "");
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        // �ֻ�����
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // ��������
        paramMap.put("recType", recType);
        
        ReturnWrap rw = selfSvcCall.qryObjectTips(paramMap, prods);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    Map<String, String> tipMap = new HashMap<String, String>();
                    
                    // ��ʾ��Ϣ
                    String tip = "";
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        tip = crset.GetValue(i, 4);
                        
                        if (tip != null && !"".equals(tip.trim()))
                        {
                            tipMap.put(crset.GetValue(i, 0).trim() + "_" + crset.GetValue(i, 1).trim(), tip.trim());
                        }                        
                    }
                    
                    return tipMap;
                }                
            }
        }
        
        return null;
    }
    
}
