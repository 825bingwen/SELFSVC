/*
 * �� �� ��:  InvoicePrintBean.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  ��Ʊ��ӡBean
 * �� �� ��: zKF69263
 * �޸�ʱ��:  2014-5-9
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ��Ʊ��ӡBean
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-5-9]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class InvoicePrintBean extends BaseBeanSDImpl
{   
    /** 
     * ��ѯҪ��ӡ��Ʊ�ļ�¼��Ϣ
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param month ��ѯ�·�
     * @return Map
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_huawei_201404_1109 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_����ɷѷ�Ʊ
     */
    @SuppressWarnings("unchecked")
    public Map invoiceList(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String month)
    {
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnum", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("menuID", curMenuId);
        
        // ���ò�ѯ��ʼʱ��YYYYMMDDHH24MISS
        paramMap.put("startTime", month + "01000000");
        
        // ����ѡ���µ����һ��
        Calendar cal = new GregorianCalendar(Integer.parseInt(month.substring(0, 4)), 
            Integer.parseInt(month.substring(4)) - 1, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        // ���ò�ѯ����ʱ��YYYYMMDDHH24MISS
        paramMap.put("endTime", DateFormatUtils.format(cal.getTime(), "yyyyMMdd") + "235959");
        
        ReturnWrap rw = getSelfSvcCallSD().invoiceList(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", rw.getReturnObject());
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if (rw != null)
        {
            Map map = new HashMap();
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
        return null;
    }
    
    /** 
     * ��ӡ��Ʊ��Ϣ
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�Id
     * @param servnumber ����ҵ���ֻ�����
     * @param invoicePrint 
     * @return Map
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_huawei_201404_1109 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_����ɷѷ�Ʊ
     */
    @SuppressWarnings("unchecked")
    public Map invoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, InvoicePrintPO invoicePrint)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        paramMap.put("recoid", invoicePrint.getRecoid());
        paramMap.put("billCycle", invoicePrint.getBillCycle());
        paramMap.put("acctId", invoicePrint.getAcctId());
        paramMap.put("invType", invoicePrint.getInvType());
        paramMap.put("touchOID", "");
        
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        //��ȡ�������ã��Ƿ����õ��ӷ�Ʊ,trueΪ����
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            //�Ƿ񿪾ߵ��ӷ�Ʊ1�� 0��
            paramMap.put("eleinvType", "1");
        }
        else
        {
            //�Ƿ񿪾ߵ��ӷ�Ʊ1�� 0��
            paramMap.put("eleinvType", "0");
        }
        //���ͷ�ʽ 1����
        paramMap.put("pushType", "1");
        //������Ϣ �����ʼ���ַ
        paramMap.put("receiveMode", servnumber+"@139.com");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        
        ReturnWrap rw = this.getSelfSvcCallSD().invoiceData(paramMap);
        
        if (rw != null)
        {
            Map map = new HashMap();
            
            if (rw.getStatus() == SSReturnCode.SUCCESS) {
                
                // ���÷��ؽ��
                map.put("returnObj", rw.getReturnObject());
            }
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
        return null;
    }
    
    /**
     * �½ᷢƱ�����ڽӿڲ�ѯ
     * 
     * @param customerSimp���û���Ϣ
     * @param terminalInfo���ն˻���Ϣ
     * @param month����ѯ�·�
     * @param CurMenuid����ǰ�˵�
     * @param billcycle ����
     * 
     * @return ������Ϣ
     * @see
     * @remark add wWX217192 2014/05/04 OR_huawei_201404_1108
     */
	@SuppressWarnings("unchecked")
	public ReturnWrap qryBillCycle(String telNum, TerminalInfoPO terminalInfo, String curMenuId, String billCycle)
	{
		Map map = new HashMap();
		map.put("servnum", telNum);
		map.put("cycle", billCycle);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().qryBillCycle(map);
		
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        
        return null;
	}
	
	/**
	 * �½ᷢƱ��ӡ���ݵĲ�ѯ�ӿ�
	 * @param telNum���ֻ�����
	 * @param terminalInfo���ն˻���Ϣ
	 * @param cycle������
	 * @param startdate�����ڿ�ʼʱ��
	 * @param enddate�����ڽ���ʱ��
	 * @param acctid�����˺�
	 * @param curMenuId����ǰ�˵�
	 * 
	 * @return �½ᷢƱ����
	 * @see
	 * @remark add wWX217192 2014/05/04 OR_huawei_201404_1108
	 */
	@SuppressWarnings("unchecked")
	public ReturnWrap qryMonthInvoice(String telNum, TerminalInfoPO terminalInfo, String cycle, String startdate, String enddate, String acctid, String curMenuId)
	{
		Map map = new HashMap();
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", terminalInfo.getOperid());
		map.put("termID", terminalInfo.getTermid());
		map.put("servnum", telNum);
		map.put("acctid", acctid);
		map.put("billcycle", cycle);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        //��ȡ�������ã��Ƿ����õ��ӷ�Ʊ,trueΪ����
        String isElectronInvoice = CommonUtil.getDictNameById(terminalInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            //�Ƿ񿪾ߵ��ӷ�Ʊ1�� 0��
            map.put("eleinvType", "1");
        }
        else
        {
            //�Ƿ񿪾ߵ��ӷ�Ʊ1�� 0��
            map.put("eleinvType", "0");
        }
        //���ͷ�ʽ 1����
        map.put("pushType", "1");
        //������Ϣ �����ʼ���ַ
        map.put("receiveMode", telNum+"@139.com");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
		
		ReturnWrap rw = this.getSelfSvcCallSD().qryMonthInvoice(map);
		
		// ���ýӿڳɹ������۽ӿڷ��ص���Ϣ�Ƿ�Ϊ��ȷ���½ᷢƱ��Ϣ�������䷵��ǰ̨
		if(rw != null)
		{
			return rw;
		}
		
		return null;
	}
}
