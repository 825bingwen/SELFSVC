package com.gmcc.boss.selfsvc.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.DocumentException;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class ScoreBean extends BaseBeanImpl
{
    /**
     * ���ֲ�ѯ
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryScoreValue(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
    	// ɽ��������������ֲ�ѯ���ؿ���
    	String newScoreSwitch = CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH);
    	String province = CommonUtil.getParamValue(Constants.PROVINCE_ID);
    	if ("1".equals(newScoreSwitch) && province.equalsIgnoreCase(Constants.PROOPERORGID_SD))
    	{
    		return this.queryScoreValueSD(terminalInfoPO, customer, curMenuId);
    	}
        Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.queryScoreValue(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String v = ((CTagSet)rw.getReturnObject()).GetValue("scoreinfo");
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", v);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	Map map = new HashMap();
            
            //���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        	
        return null;
    }
    
    /**
     * ������ϸ��Ϣ��ѯ(������ɽ���ͺ���)
     * 
     * @param startDate ��ʼʱ��
     * @param endDate ����ʱ��
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     * @throws DocumentException
     * @throws DocumentException
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    public Map qryScoreDetailHisForSd(String startDate, String endDate, TerminalInfoPO terminalInfoPO,
            NserCustomerSimp customer, String curMenuId) throws DocumentException
    {
        
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���ÿ�ʼʱ��
        paramMap.put("startDate", startDate);
        
        // ���ÿ�ʼʱ��
        paramMap.put("endDate", endDate);
        
        // ���б��
        paramMap.put("region", customer.getRegionID());
        
        ReturnWrap rw = selfSvcCall.queryScoreDetailHis(paramMap);

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {	
        	CRSet crset=(CRSet)rw.getReturnObject();
        	
        	String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            if(crset.GetRowCount()>0){
            	
            	retList = getCRSetToMap(crset);
            	
            	map.put("SUCCESSINFO", retList);
            	map.put("IsInfoNull", "false");
            }else{
            	
            	map.put("IsInfoNull", "true");
            	map.put("returnMsg", rw.getReturnMsg());
            }
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
    
    public List<Map<String,String>> getCRSetToMap(CRSet crset){
    	
    	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    	for(int i=0,len=crset.GetRowCount();i<len;i++){
    		Map<String,String> map=new HashMap<String,String>();
    		
    		for(int l=0,lens=crset.GetColumnCount();l<lens;l++)
    		map.put("col_"+(l+1), crset.GetValue(i, l));
    		list.add(map);
    	}
    	return list;
    }
    
    /**
     * ɽ�����ֶһ���ʷ��Ϣ��ѯ
     * 
     * @param startDate ��ʼʱ��
     * @param endDate ����ʱ��
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return 
     * @throws DocumentException 
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    public Map queryScoreChangeHisForsd(String startDate, String endDate, TerminalInfoPO terminalInfoPO,
            NserCustomerSimp customer, String curMenuId) throws DocumentException
    {
        Map paramMap = new HashMap();
        
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���ÿ�ʼʱ��
        paramMap.put("startDate", startDate);
        
        // ���ÿ�ʼʱ��
        paramMap.put("endDate", endDate);
        
        // ���б��
        paramMap.put("region", customer.getRegionID());
        
        ReturnWrap rw = selfSvcCall.queryScoreChangeHis(paramMap);

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CRSet crset=(CRSet)rw.getReturnObject();

        	String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            if(crset.GetRowCount()>0){
            	retList = getCRSetToMap(crset);

            	map.put("SUCCESSINFO", retList);
            	map.put("IsInfoNull", "false");
            }else{
            	map.put("IsInfoNull", "true");
            	map.put("returnMsg", rw.getReturnMsg());
            }
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
     * �������ֶһ���ʷ��Ϣ��ѯ
     * 
     * @param startDate ��ʼʱ��
     * @param endDate ����ʱ��
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     * @throws DocumentException 
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    public Map queryScorePrizeHisForhub(String startDate, String endDate, TerminalInfoPO terminalInfoPO,
            NserCustomerSimp customer, String curMenuId) throws DocumentException
    {
        Map paramMap = new HashMap();
        
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���ÿ�ʼʱ��
        paramMap.put("startDate", startDate);
        
        // ���ÿ�ʼʱ��
        paramMap.put("endDate", endDate);
        
        // ���б��
        paramMap.put("region", customer.getRegionID());
        
        ReturnWrap rw = selfSvcCall.queryScorePrizeHis(paramMap);

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CRSet crset=(CRSet)rw.getReturnObject();

        	String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            if(crset.GetRowCount()>0){

            	retList = getCRSetToMap(crset);
            
            	map.put("SUCCESSINFO", retList);
            
            	map.put("IsInfoNull", "false");
            
            }else{
            	map.put("IsInfoNull", "true");
            	map.put("returnMsg", rw.getReturnMsg());
            }
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
     * <��ѯ�û��Ƿ�ͨ���ּƻ� ɽ��>
     * <������ϸ����>
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ�������Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return true���ѿ�ͨ��false��û�п�ͨ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create sWX219697 2014-05-12 OR_SD_201404_777_ɽ��_�����������նˡ�����__ȫ�������ֲ�ѯ���һ�����
     */
    public boolean qryIsScoreOpenForSd(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
    	Map<String,String> paramMap = new HashMap<String,String>();
    	
    	//����Ա���
    	paramMap.put("operID", terminalInfoPO.getOperid());
    	
    	//�ն˻����
    	paramMap.put("termID", terminalInfoPO.getTermid());
    	
    	//�ͻ��ֻ�����
    	paramMap.put("telnum", customer.getServNumber());
    	
    	//�û��Ӵ�id
    	paramMap.put("contactID", customer.getContactId());
    	
    	//��ǰ�˵����
    	paramMap.put("curMenuId", curMenuId);
    	
    	//��ѯ����
    	paramMap.put("region", customer.getRegionID());
    	
    	//�ӻ�����ȡ����Ʒid
    	String prodID = (String) PublicCache.getInstance().getCachedData(Constants.SCORE_PLAN_PROD_ID);
    	paramMap.put("prodID", prodID);
    	
    	ReturnWrap rw = selfSvcCall.qryIsScoreOpen(paramMap);
    	
    	//�Ƿ�ͨ���ּƻ���true����ͨ��false��û�п�ͨ
    	boolean isOpenScore = false;
    	
    	if (null != rw && rw.getStatus() == SSReturnCode.SUCCESS)
    	{
    		CTagSet tSet = (CTagSet)rw.getReturnObject();
    		
    		//�ж��û��Ƿ�ͨ���ּƻ�
    		String scoresOpen = tSet.GetValue("result");
    		
    		//0 û�ж���
    		if (!"0".equals(scoresOpen))
    		{
    			isOpenScore = true;
    		}
    	}
    	return isOpenScore;
    }
    
    /**
     * ���ַ��Ų�ѯ
     * @param curMenuId �˵�id
     * @param termInfo �ն˻���Ϣ
     * @param customer �û���Ϣ
     * @param endDate ����ʱ��
     * @param startDate ��ʼʱ��
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_ɽ���ƶ������������ֲ�ѯ����֧������
     */
    public Map<String, Object> qryScorePaymentSD(String startDate, String endDate, NserCustomerSimp customer, TerminalInfoPO termInfo, String curMenuId)
    {
    	// ��װ����ͷ��Ϣ
		MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
    	
		// ����call��ѯ���ַ�����Ϣ 
		ReturnWrap rw = this.selfSvcCall.qryPayMentScoreSD(header, startDate, endDate);
		
		if (SSReturnCode.SUCCESS == rw.getStatus())
		{
			CRSet crset = (CRSet) rw.getReturnObject();
			 
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != crset && crset.GetRowCount() > 0)
			{
			    //������ַ�����ϸ��ѯ��openEbus����ж�ת��ʱ���ʽ
			    if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PAYMENTSOCRE))
			    {
			        for (int i = 0; i < crset.GetRowCount(); i++)
	                {
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	                    
	                    try
	                    {
	                        Date payTime = sdf.parse(crset.GetValue(i, 0));
	                        crset.SetValue(i, 0, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payTime));
	                    }
	                    catch (ParseException e)
	                    {
	                        e.printStackTrace();
	                    }	                   
	                }
			    }
			    
				List<Map<String, String>> retList = getCRSetToMap(crset);
            
            	map.put("SUCCESSINFO", retList);
            
            	map.put("IsInfoNull", "false");
			}
			else
			{
				map.put("IsInfoNull", "true");
			}
			
			// ���÷�����Ϣ
			map.put("returnMsg", rw.getReturnMsg());
			return map;
		}
		else
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("returnMsg", rw.getReturnMsg());
			return map;
		}
    }
    
    /**
     * ���ֲ�ѯ��ɽ����
     * @param termInfo �ն˻���Ϣ
     * @param customer �û���Ϣ
     * @param curMenuId �˵�id
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_ɽ���ƶ������������ֲ�ѯ����֧������
     */
    public Map queryScoreValueSD(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId)
    {
    	// ��װ����ͷ��Ϣ
		MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		// ����call��ѯ�û�����
		ReturnWrap rw = this.selfSvcCall.qryScoreValueSD(header);
		
		if (SSReturnCode.SUCCESS == rw.getStatus())
		{
			Map map = new HashMap();
			
	            // modify begin fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
	        	//���ܿ�����ת��
	        	if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCOREVALUESD))
	            { 
	        		CTagSet tagSet = (CTagSet)rw.getReturnObject();
	            
	                // ��openEBUS  �� UAP ����ͳһ
	                String[] openEbusRtn = {"availscore"};
	                String[] destRtn = {"pointBalance"};
	                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_SCOREVALUESD, openEbusRtn, destRtn);
	                rw.setReturnObject(tagSet);   
	            }
	             
			// ��UAP�û����ֲ�Ϊ0ʱ
			if (rw.getReturnObject() instanceof Vector)
			{
				Vector v = (Vector) rw.getReturnObject();
				CTagSet ctagset = (CTagSet) v.get(0);
				CRSet crset = (CRSet) v.get(1);
				if (crset.GetRowCount() > 0)
				{
					List<Map<String, String>> retList = getCRSetToMap(crset);
		            
	            	map.put("SUCCESSINFO", retList);
	            	map.put("IsInfoNull", "false");
				}
				// �û����û���
				map.put("pointBalance", ctagset.get("pointBalance"));
			}
			
			// ��UAP�û�����Ϊ0ʱ����OPenEbusʱ�û����ֲ�Ϊ0
			else if (rw.getReturnObject() instanceof CTagSet)
			{
				CTagSet ctagset = (CTagSet) rw.getReturnObject();
			    //���ܿ�  ��װcrset
				if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCOREVALUESD))
				{
					List<Map<String, String>> retList=new ArrayList<Map<String,String>>();
					Map<String, String> reMap=new HashMap<String, String>();
					reMap.put("col_"+(1), " ");
					reMap.put("col_"+(2),(String) ctagset.get("pointBalance"));
					reMap.put("col_"+(3), " ");
					retList.add(reMap) ;   
		            map.put("SUCCESSINFO", retList);
					map.put("IsInfoNull", "false");
				}
				else
				{
					map.put("IsInfoNull", "true");
				}	
			    // modify end fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
	          	
				// �û����û���
				map.put("pointBalance", ctagset.get("pointBalance"));
			}
			map.put("returnMsg", rw.getReturnMsg());
			return map;
		}
		else
		{	Map map = new HashMap();
			map.put("returnMsg", rw.getReturnMsg());
			return map;
		}
    }
    /**
     * ���ָ���µĵ�һ��0��ʾ��ǰ��-1��һ����;1��ʾ��һ����
     * @param i
     * @return
     */
    public static String getLastMonthBeginDate(int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return getDate(cal);
    }
    
    /**
     * ���ָ���µ����һ��0��ʾ��ǰ�£�-1��һ����;1��һ����
     * @param i
     * @return
     */
    public static String getLastMonthEndDate(int i)
    {
    	i=i-1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return getDate(cal);
    }
    
    /**
     *  �������
     * @param cal Calendar
     * @return
     */
    private static String getDate(Calendar cal)
    {
        String v_strDate = "";
        int v_intYear = cal.get(Calendar.YEAR);
        int v_intMonth = cal.get(Calendar.MONTH) + 1;
        int v_intDAY = cal.get(Calendar.DAY_OF_MONTH);
        
        if (v_intDAY < 10)
        {
            v_strDate = v_strDate + "0" + v_intDAY;
        }
        else
        {
            v_strDate = v_strDate + v_intDAY;
        }
        if (v_intMonth < 10)
        {
            v_strDate = "0" + v_intMonth + v_strDate;
        }
        else
        {
            v_strDate = v_intMonth + v_strDate;
        }
        v_strDate = v_intYear + "" + v_strDate;
        cal = null;
        return v_strDate;
    }
}