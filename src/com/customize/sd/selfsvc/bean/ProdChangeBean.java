/*
 * �ļ�����ProdChangeBean.java
 * �������ײ��ʷѱ��bean��
 * �����ˣ�jWX216858
 */
package com.customize.sd.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �ײ��ʷѱ��bean
 * @author jWX216858
 * ����ʱ�䣺2014-5-6
 */
public class ProdChangeBean extends BaseBeanSDImpl
{
	/**
	 * ��ѯ��ת�������Ʒ��Ϣ
	 * @param termInfoPO
	 * @param customer
	 * @param curMenuId
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
	 */
	public List<ProdChangePO> qryMainProdInfo(TerminalInfoPO termInfoPO, NserCustomerSimp customer, String curMenuId)
	{
		// ����
		Map<String,String> paramMap = new HashMap<String, String>();
		
		// ����Աid
		paramMap.put("operId", termInfoPO.getOperid());
		
		// �ն˻�id
		paramMap.put("termId", termInfoPO.getTermid());
		
		// ��ǰ�˵�
		paramMap.put("menuId", curMenuId);
		
		// �ͻ��Ӵ�id
		paramMap.put("touchId", customer.getContactId());
		
		// �������
		paramMap.put("region", customer.getRegionID());
		
		// ��Ʒ����
		paramMap.put("prodId", customer.getProductID());
		
		// ��������
		paramMap.put("accessType", "bsacAtsv");
		
		ReturnWrap rw = getSelfSvcCallSD().qryMainProdInfo(paramMap);
		
		if(rw.getStatus() == SSReturnCode.ERROR)
		{
		    throw new ReceptionException(rw.getReturnMsg());
		}
		
		CRSet crset = (CRSet) rw.getReturnObject();
			
		List<ProdChangePO> prodChangeList = new ArrayList<ProdChangePO>();
			
        // ����crset��ȡ�÷�����Ϣ
		//modify begin lWX431760 2017-06-14 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
		if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CONVERTPRODINFO))
		{
		    for (int i = 0; i < crset.GetRowCount(); i++)
	        {
	            ProdChangePO prodChangePO = new ProdChangePO();
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdId(crset.GetValue(i, 0));
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdName(crset.GetValue(i, 1));
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdDescr(crset.GetValue(i, 3));
	            
	            prodChangeList.add(prodChangePO);
	        }
		}
		else
		{
		    for (int i = 0; i < crset.GetRowCount(); i++)
	        {
	            ProdChangePO prodChangePO = new ProdChangePO();
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdId(crset.GetValue(i, 1));
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdName(crset.GetValue(i, 3));
	            
	            // ��Ʒ����
	            prodChangePO.setNewProdDescr(crset.GetValue(i, 5));
	            
	            prodChangeList.add(prodChangePO);
	        }
		}
		//modify end lWX431760 2017-06-14 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
        
		
		return prodChangeList;
	}
	
	/**
	 * ��ѯ�����Ʒģ����Ϣ
	 * @param termInfoPO
	 * @param customer
	 * @param curMenuId
	 * @param newProdId
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
	 */
	public List<ProdTemplatePO> mainProdTemplateInfo(TerminalInfoPO termInfoPO, NserCustomerSimp customer, String curMenuId, String newProdId)
	{
		// ����
		Map<String, String> paramMap = new HashMap<String, String>();
		
		// �ն�id
		paramMap.put("termId", termInfoPO.getTermid());
		
		// ����Աid
		paramMap.put("operId", termInfoPO.getOperid());
		
		// �˵�id
		paramMap.put("menuId", curMenuId);
		
		// �ͻ��Ӵ�id
		paramMap.put("touchId", customer.getContactId());
		
		// �������
		paramMap.put("region", customer.getRegionID());
		
		// ��Ʒ����
		paramMap.put("prodId", newProdId);
		
		// ��������
		paramMap.put("recType", "ChangeProduct");
		
		// ��������
		paramMap.put("channel", "bsacAtsv");
		
		// ��ѯʱ�Ƿ�ȷƥ��
		paramMap.put("ruleType", "PRECISEQRY");
		
		ReturnWrap rw = getSelfSvcCallSD().qryProdTemplateInfo(paramMap);
		
		if(rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
		
		CRSet crset = (CRSet) rw.getReturnObject();
		
		List<ProdTemplatePO> prodTemplateList = new ArrayList<ProdTemplatePO>();
		
		// ����crset��ȡ�÷�����Ϣ
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            ProdTemplatePO prodTemplatePO = new ProdTemplatePO();

            // ģ�����
            prodTemplatePO.setTemplateId(crset.GetValue(i, 0));
            
            // ģ������
            prodTemplatePO.setTemplateName(crset.GetValue(i, 1));
            
            // ģ������
            prodTemplatePO.setTemplateDescr(crset.GetValue(i, 2));

            // ��Ʒ����
            prodTemplatePO.setProdid(crset.GetValue(i, 3));
            
            // ��Ʒ����
            prodTemplatePO.setProdname(crset.GetValue(i, 4));

            prodTemplateList.add(prodTemplatePO);
        }
		
		return prodTemplateList;
	}
	
	/**
	 * ִ�������Ʒ���
	 * @param termInfoPO
	 * @param customer
	 * @param curMenuId
	 * @param pretreatment
	 * @param newProdId
	 * @param templateId
	 * @return map
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
	 */
	public Map<String, Object> mainProdChangeRec(TerminalInfoPO termInfoPO, NserCustomerSimp customer, String curMenuId, boolean pretreatment, String templateId,String newProdId)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
		// �ն�id
		paramMap.put("termId", termInfoPO.getTermid());
		
		// ����Աid
		paramMap.put("operId", termInfoPO.getOperid());
		
		// �˵�id
		paramMap.put("menuId", curMenuId);
		
		// �ͻ��Ӵ�id
		paramMap.put("touchId", customer.getContactId());
		 
		// �ֻ���
		paramMap.put("telnum", customer.getServNumber());
		
		// �Ƿ�ʹ��NOCDE 1����ʹ�� 0:ʹ��
		paramMap.put("NOTEXENCODE", "1");
		
		// �����Ʒ����
		paramMap.put("MAINPRODID", newProdId);
		
		if (pretreatment)
		{
			// �����Ʒ���Ԥ����  PreBsacNBChgMainProd��Ԥ����
			paramMap.put("PREPAREBUSI", "PreBsacNBChgMainProd");
			
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ����� 
			// MO�ײ����Ѽ�ֵ��Ԥ����ʱ���̶�ֵmessage
			paramMap.put("MOPrivTips", "message");
			
			// �Ƿ��ύ,0���ύ
			paramMap.put("ISSUBMIT", "0");
			// add end jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ����� 
		}
		else
		{
			// ����Ϊִ�������Ʒ���
			paramMap.put("PREPAREBUSI", "");
			
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ����� 
			// MO�ײ����Ѽ�ֵ��ִ�������Ʒ���ʱ����
			paramMap.put("MOPrivTips", "");
			
			// �Ƿ��ύ,1�ύ
			paramMap.put("ISSUBMIT", "1");
			// add end jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
		}
		
		// ��Ч��ʽ:Type_Immediate ���� Type_NextCycle ���� EffectType_Time ָ����Чʱ��
		paramMap.put("affecttype", "Type_NextCycle");
		
		// ��������:PCOpRec ��ͨ  PCOpDel �˶�
		paramMap.put("opertype", "PCOpRec");
		
		// ��������:�̶�ֵPCIntObjMain�������Ʒ��
		paramMap.put("objtype", "PCIntObjMain");
		
		// �Ż�����:û�д������
		paramMap.put("privname", "");
		
		// ģ�����
		paramMap.put("templateId", templateId);
		
		ReturnWrap rw = getSelfSvcCallSD().mainProdChangeRec(paramMap);
		Map<String, Object> map = new HashMap<String, Object>();
		// ����rw�е���Ϣ���÷�����Ϣ
		if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
			Vector<Object> v = null;
			if (pretreatment)
			{
				v = (Vector<Object>)rw.getReturnObject();
			}
				
			//���÷��ؽ��
			map.put("returnObj", v);
            
            //���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
		else if (null != rw)
		{
			// ���÷�����Ϣ
			map.put("returnMsg", rw.getReturnMsg());
			
			return map;
		}
		return null;
	}
	
	
	/**
	 * ���������ƷID��ѯ�����Ʒ��Ϣ
	 * @param termInfoPO �ն˻���Ϣ
	 * @param customer �û���Ϣ
	 * @param curMenuId �˵�ID
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by hWX5316476 2015-1-5  OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
	 */
	public ProdChangePO qryProdInfoById(TerminalInfoPO termInfoPO, NserCustomerSimp customer, String curMenuId)
    {
	    // ��װ������ͷ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfoPO.getOperid(), termInfoPO.getTermid(),
                customer.getContactId(), "1", customer.getServNumber());

        //modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        String type = "PRODTYPE";
        
        // ���ýӿڲ�ѯ�����Ʒ��Ϣ
        ReturnWrap rw = getSelfSvcCallSD().qryProdInfoById(msgHeader, customer.getProductID(),type);
        //modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
        
        // ����rw�е���Ϣ���÷�����Ϣ
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            CRSet crset = (CRSet) rw.getReturnObject();
            
            // ��ȡcrset��һ����Ϣ
            if(0 < crset.GetRowCount())
            {
                ProdChangePO prodChangePO = new ProdChangePO();
                
                // ��Ʒ����
                prodChangePO.setNewProdId(crset.GetValue(0, 0));
                
                // ��Ʒ����
                prodChangePO.setNewProdName(crset.GetValue(0, 1));
                
                // ��Ʒ����
                prodChangePO.setNewProdDescr(crset.GetValue(0, 2));
                
                return prodChangePO;
            }
        }
        else
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return null;
    }
	
	/**
	 * ���ڵ���ת��
	 * @param termInfoPO
	 * @param customer
	 * @param ncode ��Ӧ��Ʒ��ģ��
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
	 */
	public void chgLevelInGroup(TerminalInfoPO termInfoPO, NserCustomerSimp customer,String curMenuId, String ncode)
	{
	    // ��װ����ͷ��Ϣ
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfoPO.getOperid(), termInfoPO.getTermid(),
                customer.getContactId(), "1", customer.getServNumber());
        
        // �������� ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
        String stype = "ADD";
        
        // Ԥ���� �̶���BsacNBSubmit
        String preparebusi = "BsacNBSubmit";
        
        // �Ƿ񽫻���������Ľ��й���ɾ��,NEEDPREMUTEXɾ�������ղ�ɾ��
        String premutex = "";
        
        // ���ýӿڲ�ѯ�����Ʒ��Ϣ
        ReturnWrap rw = getSelfSvcCallSD().chgLevelInGroup(header, ncode, stype, preparebusi, premutex);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
	}
}