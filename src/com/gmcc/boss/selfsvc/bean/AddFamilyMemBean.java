package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.baseService.familymem.model.FamilyMemPO;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ��ͥ����Ա���bean
 * 
 * @author kWX211786
 * @version [�汾��, May 24, 2014]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class AddFamilyMemBean extends BaseBeanImpl
{
    
    /**
     * ��ѯ��ͥ����Ա
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer ������Ϣ
     * @param curMenuId �˵�
     * @return [��������˵��]
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create kWX211786 May 24, 2014 �汾�� ����/BUG��ţ�OR_huawei_201404_1115_ɽ��_��ͥ����Ա��ӹ���
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryFamilyMem(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.queryFamilyMemService(paramMap);
        Map map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
            CRSet cs = new CRSet();
            if (v.size() > 1)
            {
                cs = (CRSet)v.get(1);
            }
            // ���÷��ؽ��
            map.put("returnObj", cs);
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if(rw != null)
        {
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
         
            return map;
        }
        
        return null;
    }
    /**
     * ��Ӽ�ͥ����Ա
     * 
     * @return [��������˵��]
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create kWX211786 May 24, 2014 �汾�� ����/BUG��ţ�OR_huawei_201404_1115_ɽ��_��ͥ����Ա��ӹ���
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> addFamilyMem(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, FamilyMemPO familyMemPO)
    {
        Map<String, String> paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        
        // ����ҵ������룬������
        paramMap.put("servNumber", customer.getServNumber());

        // Ҫ��ӳ�Ա�绰����
        paramMap.put("memTelnum", familyMemPO.getTelNum());
        
        // �̺�
        paramMap.put("shortNum", familyMemPO.getShortNum());
        
        ReturnWrap rw = selfSvcCall.addFamilyMem(paramMap);
        
        Map<String, Object> map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet ts = (CTagSet)rw.getReturnObject();
            
            //�����Ƿ����ɹ���־
            map.put("result", ts.GetValue("result"));
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if(rw != null)
        {
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        return null;
    }
    
    /**
     * ��ͥ��ȡ���ӿ�
     * 
     * @param terminfo���ն˻���Ϣ
     * @param customer���û���Ϣ
     * @param curMenuId����ǰ�˵���Ϣ
     * @return �ӿڴ�����
     * @see
     * @remark add begin wWX217192 on 2014/06/03 for OR_huawei_201405_875
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap deleteFamilyMen(TerminalInfoPO terminfo, NserCustomerSimp customer, String curMenuId)
    {
    	Map map = new HashMap();
    	map.put("servnum", customer.getServNumber());
    	map.put("menuID", curMenuId);
    	map.put("touchOID", "");
    	map.put("operID", terminfo.getOperid());
    	map.put("termID", terminfo.getTermid());
    	
    	ReturnWrap rw = selfSvcCall.deleteFamilyMem(map);
    	
    	// ���ýӿڳɹ������۽ӿڷ��ص���Ϣ�Ƿ�Ϊ��ȷ�ļ�ͥ��ȡ����Ϣ�������䷵����action����н�һ���Ĵ���
    	if(rw != null)
    	{
    		return rw;
    	}
    	
    	return null;
    }
    
    /**
     * <ɾ����ͥ����Ա>
     * <������ϸ����>
     * @param terminalInfo �ն���Ϣ
     * @param customer  �û���Ϣ
     * @param curMenuId �˵�ID
     * @param memTelnum Ҫɾ���ĳ�Ա�ֻ���
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by sWX219697 2015-2-4 10:45:04 OR_SD_201412_777 �����ն˷ſ���ͥ����Աɾ���Ĺ���
     */
    public void delMemByTelNum(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String memTelnum)
    {
        // ��װ������ͷ
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // ����ɾ����ͥ����Ա�ӿ�
        ReturnWrap rw = selfSvcCall.delMemByTelnum(header, memTelnum);
        
        // ���ýӿڳɹ�
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet ts = (CTagSet)rw.getReturnObject();
            
            // ɾ��ʧ��
            if(!"1".equals(ts.GetValue("result")))
            {
                throw new ReceptionException("�Բ��𣬼�ͥ����Ա:"+memTelnum+"ɾ��ʧ�ܣ�");
            }
        }
        // ʧ��
        else
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
}
