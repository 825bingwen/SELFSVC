/*
* @filename: NewSelfSvcDubboCallImpl.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  Dubbo����Э����ýӿ�
* @author: zKF69263
* @de:  2014/04/14 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_huawei_201205_740
*/
package com.gmcc.boss.selfsvc.call;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;


/**
 * Dubbo����Э����ýӿ�
 * 
 * @author  zKF69263
 * @version  1.0, 2014/04/14
 * @since  
 */
public class NewSelfSvcDubboCallImpl extends NewSelfSvcCallImpl 
{
	private static Log logger = LogFactory.getLog(NewSelfSvcDubboCallImpl.class);
	
	/**
     * ����������֤
     * 
     * @param map
     * @return  ReturnWrap
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  �����ն˽���EBUSһ�׶�_��ȡ�û���Ϣ
     */
    public ReturnWrap checkUserPassword(Map<String, String> map)
    {
    	if(IntMsgUtil.isTransEBUS("BLCSCheckPassWd"))
    	{
			try 
	    	{ 
	    		// ��Ρ�
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	
	        	// �û�����
	        	inParamMap.put("telNum", map.get("telnumber"));
	        	
	        	// ����
	        	inParamMap.put("inputPwd", map.get("password"));
	        	                    
	        	return getIntMsgUtil().invokeDubbo("BLCSCheckPassWd", "10001001", "",  "1", 
        			map.get("telnumber"), map.get("curOper"), map.get("atsvNum"), inParamMap);
			} 
	    	catch (Exception e) 
	    	{
	    		logger.error("����������֤��", e);
	    		
	    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����������֤ʧ��!");
			}
    	}
    	return super.checkUserPassword(map);
    }
    
    /**
     * ��ȡ�û���Ϣ
     * 
     * @param map
     * @return
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  �����ն˽���EBUSһ�׶�_��ȡ�û���Ϣ
     */
    public ReturnWrap getUserInfoHub(Map map)
    {
    	if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
    	{
	    	try 
	    	{
	    		// ��Ρ�
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	
	        	// �û�����
	        	inParamMap.put("telnum", (String)map.get("telnumber"));

	        	return getIntMsgUtil().invokeDubbo("sMQryUserInfoHub", "10001001", "",  "1", 
        			(String)map.get("telnumber"), (String)map.get("curOper"), (String)map.get("atsvNum"), inParamMap);
			} 
	    	catch (Exception e) 
	    	{
	    		logger.error("��ȡ�û���Ϣ��", e);
	    		
	    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ȡ�û���Ϣʧ��!");
			}
    	}
    	return super.getUserInfoHub(map);
    }
    
    
	/**
     * �����޸�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
     @Override
	public ReturnWrap recChangePassword(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSChgSubsPassWord")) 
		{
			try 
			{
				// ���
				Map<String, String> paramMap = new HashMap<String, String>();

				// �ֻ���
				paramMap.put("telNum", (String)map.get("telnumber"));

				// ������
				paramMap.put("subsOldPassword", (String)map.get("oldPasswd"));

				// ������
				paramMap.put("subsNewPassword", (String)map.get("newPasswd"));

                // ���ܶ�Ӧ��������ֵ 0:����У�� 1:������ 2: ��������(������) 3.��������(���)4:�����ʼ��
				paramMap.put("subCmd", "1");

				return getIntMsgUtil().invokeDubbo("BLCSChgSubsPassWord", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
			} 
			catch (Exception e) 
			{
				logger.error("�����޸�ʧ�ܣ�", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�����޸�ʧ��!");
			}
		}
		
		return super.recChangePassword(map);
    }
	
	/**
     * ͣ����ҵ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap stopOpenSubs(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSStopOpenSubs"))
		{
			try
	        {
				// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();

				// �ֻ���
				paramMap.put("servNumber", map.get("telnumber"));

				// ҵ������
				paramMap.put("recType", map.get("stoptype"));

				// ԭ��
				paramMap.put("changeReason", map.get("reason"));

				// ��������Dubbo����
				return getIntMsgUtil().invokeDubbo("BLCSStopOpenSubs", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("ͣ����ʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ͣ�������ýӿ�ʧ��");
	        }
		}
		
		return super.stopOpenSubs(map);
    }
    
    /**
     * ҵ��ͳһ��ѯ�ӿ� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryService(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ")) 
		{
			try
	        {
				// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();

				// �ֻ���
				paramMap.put("telNum", map.get("telnumber"));

				// �Ƿ���������ѯ
				paramMap.put("querySN", map.get("sn"));

				// ��������Dubbo����
				return getIntMsgUtil().invokeDubbo("BLCSIntQuerySubsAllServ", (String) map.get("curMenuId"), 
				    (String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap,
					new String[] { "objType", "spID", "spName", "spBizID",
						"spBizName", "spBizType", "billingType", "price",
						"domain", "startDate", "endDate", "seqNum",
						"charge", "priceDesc"});
	        }
	        catch (Exception e)
	        {
	            logger.error("ҵ��ͳһ��ѯ�ӿ�ʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "ҵ��ͳһ��ѯ�ӿڵ���ʧ��");
	        }
		}
		
		return super.queryService(map);
    }
    
    /**
     * ҵ��ͳһ�˶��ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap cancelService(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSIntServUniteCancel")) 
		{
			try
	        {
				// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// ҵ����Ϣ�б�
				List<Map<String, Object>> lstDetailInfo = new ArrayList<Map<String,Object>>();
				
				// ҵ����Ϣ
				Map<String, Object> detailInfo = new HashMap<String, Object>();
				
				// �������ͣ�����ҵ������ҵ�񡢼���ҵ��
				detailInfo.put("objType", map.get("dealType"));
				
				// ����ID��SP��ҵ����
				detailInfo.put("spID", map.get("spId"));
				
				// spҵ����룺����ҵ��ΪSPҵ����룬����Ϊ��ƷID
				detailInfo.put("spBizID", map.get("spBizCode"));
				
				// ҵ�����ͣ�����ҵ��ʱ����cancel_typeΪ4��ʱ����봫
				detailInfo.put("spBizType", map.get("bizType"));
				
				// sp����domian������ҵ����Ч
				detailInfo.put("domain", map.get("domain"));
				
				lstDetailInfo.add(detailInfo);
				
				// �ֻ���
				paramMap.put("servNum", map.get("telnumber"));
				
				// ҵ����Ϣ
				paramMap.put("detailInfo", lstDetailInfo);

				// ��������Dubbo����
				return getIntMsgUtil().invokeDubbo("BLCSIntServUniteCancel", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("ҵ��ͳһ�˶��ӿ�ʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "ҵ��ͳһ�˶��ӿڵ���ʧ��");
	        }
		}
		
		return super.cancelService(map);
    }
    
    /**
     * ����SPҵ��
     * 
     * @param map ���
     * @return �������
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public ReturnWrap orderSPService(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ")) 
		{
			try
	        {
				// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// ҵ����Ϣ
				// ҵ����Ϣ
                List<Map> detailInfo = new ArrayList<Map>();
                
                Map<String, Object> detailInfomap = new HashMap<String, Object>();
                
				// ����ID��SP��ҵ����
                detailInfomap.put("spID", map.get("spId"));
				
				// spҵ����룺����ҵ��ΪSPҵ����룬����Ϊ��ƷID
                detailInfomap.put("spBizID", map.get("spBizCode"));
				
				// spҵ���������
                detailInfomap.put("spBizType", map.get("bizType"));
				
				// sp����domian������˶���־Ϊ"5"(5 == CANCEL_FLAG������DOMAIN�����˶�
                detailInfomap.put("spDomain", map.get("domain"));
                
                // ��������
                detailInfomap.put("operType", map.get("operType"));
				
                detailInfo.add(detailInfomap);
                
				// �˶���ʶ��ֻ�в�������Ϊ�˶�ʱ����ȡ�˶���ʶ
				paramMap.put("cancelFlag", map.get("cancelFlag"));
				
				// �Ƿ���srp���Ʋ���
				paramMap.put("flag", "1");
				
				// ��ȡ��������
				paramMap.put("operType", map.get("operType"));
				
				// ҵ����Ϣ
				paramMap.put("detailInfo", detailInfo);

				// ��������Dubbo����
				return getIntMsgUtil().invokeDubbo("BLCSChangeSubsMonServ", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap, 
					new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}});
	        }
	        catch (Exception e)
	        {
	            logger.error("SPҵ�񶩹��ӿ�ʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "SPҵ�񶩹��ӿڵ���ʧ��");
	        }
		}
		
		return super.orderSPService(map);
    }
    
    
    /**
     * ��ȡ4G������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark ���� create by liutao00194861 OR_HUB_201603_1191	��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��
	 */
	@Override
    public ReturnWrap getIs4GCard(MsgHeaderPO msgHeader)
    {
		try
		{
			Map<String, String> inParamMap = new HashMap<String, String>();

			// �ֻ�����
			inParamMap.put("telnum", msgHeader.getTelNum());

			String[] outParam = {"result"};

			return getIntMsgUtil().invokeDubbo("BLCSCheckIs4GCardByTelnum", msgHeader, inParamMap, outParam);
		}
		catch(Exception e)
		{
			logger.error("��ѯ�Ƿ�4G��ʧ��", e);

			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�Ƿ�4G��ʧ��");
		}
	}

	/**
	 * ��ȡ����
	 * scoreinfo.SetRegion(jvInParams["region"].GetStr());
	 * scoreinfo.SetservNumber(jvInParams["servnumber"].GetStr());
	 * scoreinfo.SetEntityID(jvInParams["entityid"].GetStr());
	 * scoreinfo.Setprodtype(jvInParams["prodtype"].GetStr());
	 * scoreinfo.SetCycle(jvInParams["scorecycle"].GetStr());
	 * scoreinfo.SetEntityType(jvInParams["entitytype"].GetStr());
	 * scoreinfo.SetScoreTypeID(jvInParams["scoretypeid"].GetStr());
	 * scoreinfo.SetqryBalanceDetail(_atoi64(jvInParams["qrybalancedetail"].GetStr()));
	 *
	 * @param map
	 * @return
	 * @remark ���� create by liutao00194861 OR_HUB_201603_1191	��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��
	 * @see [�ࡢ��#��������#��Ա]
	 */
	@Override
	public ReturnWrap getAvailIntegralEbus(MsgHeaderPO msgHeader)
	{
		try
		{
			Map<String, String> inParamMap = new HashMap<String, String>();

			// �ֻ�����
			inParamMap.put("servnumber", msgHeader.getTelNum());
			inParamMap.put("region", msgHeader.getRegion());
			//inParamMap.put("entityid", msgHeader.ge);

			String[] outParam = {"result"};

			return getIntMsgUtil().invokeDubbo("GetAvailIntegralEbus", msgHeader, inParamMap, outParam);
		}
		catch(Exception e)
		{
			logger.error("��ѯ�Ƿ�4G��ʧ��", e);

			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�Ƿ�4G��ʧ��");
		}
	}
	
	/**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     */
	@Override
    public ReturnWrap sendSmsHub(Map map)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	try 
        	{
        		// ��Ρ�
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// ����ģ����
            	inParamMap.put("TEMPLATENO",(String)map.get("templateno"));
            	
            	// �ֻ�����
            	inParamMap.put("TELNUM", (String)map.get("telnumber"));
            	
            	// �����б�
            	String smsparam =(String)map.get("smsparam");
            	String[] str = smsparam.split("#");
            	
            	for(int i = 0;i < str.length;i++)
            	{
            		inParamMap.put(str[i].substring(0,1),str[i].substring(1));
            	}
            	
            	return getIntMsgUtil().invokeDubbo("PTSendSmsInfo", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
			}
        	catch (Exception e) 
        	{
        		logger.error("���û���������������ʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���û���������������ʧ��!");
        	}
        }
        return super.sendSmsHub(map);
    }
    
    /**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     */
    @Override
    public ReturnWrap sendSMS(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	try
        	{
	        	// ��Ρ�
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	        	
	        	// ����ģ����
	        	inParamMap.put("TEMPLATENO","Atsv0001");
	        	
	        	// �ֻ�����
	        	inParamMap.put("TELNUM", (String)map.get("telnumber"));
	        	
	        	// �����б�
	        	inParamMap.put("1",(String)map.get("smsContent"));
	        	
	        	return getIntMsgUtil().invokeDubbo("PTSendSmsInfo", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
        	}
        	catch(Exception e)
        	{
        		logger.error("���û���������������ʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���û���������������ʧ��!");
        	}
        }
        return super.sendSMS(map);
    }
    
	/**
     * ��������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_374 �����ն�ȫ���̽���EBUS����_�������롢�޸�����
     */
	@Override
    public ReturnWrap resetPassword(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("BLCSChgSubsPassWord"))
        {
        	try
        	{
	        	// ��Ρ�
	        	Map<String,String> inParamMap = new HashMap<String,String> ();
	        	
	        	// �ֻ���
	        	inParamMap.put("telNum", (String)map.get("telnumber"));
	        	
	        	// ������
	        	inParamMap.put("subsOldPassword", "");
	        	
	        	// ������
	        	inParamMap.put("subsNewPassword", "");
	        	
	        	// ���ܶ�Ӧ��������ֵ 0:����У�� 1:������ 2: ��������(������) 3.��������(���)4:�����ʼ��
	        	inParamMap.put("subCmd", "4");
	        	
	        	return getIntMsgUtil().invokeDubbo("BLCSChgSubsPassWord", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
        	}
        	catch(Exception e)
        	{
        		logger.error("��������ʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��������ʧ��!");
        	}
        }
        return super.resetPassword(map);
    }
	
    /**
     * ����״̬��ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/19
     */
	@Override
    public ReturnWrap queryCurrentStatus(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
        {
            try
            { 
            	// ��Ρ�
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// �ֻ���
            	inParamMap.put("telnum", (String)map.get("telnum"));
            	
            	// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
            	String[][] outParamKeyDefine = {{"statusName"},{"state"}};
            	
            	return getIntMsgUtil().invokeDubbo("sMQryUserInfoHub", (String)map.get("menuid"), 
            		(String)map.get("touchoid"), "1", (String)map.get("telnum"), (String)map.get("operid"), 
            		(String)map.get("operid"), inParamMap, outParamKeyDefine);
            }
            catch (Exception e)
            {
                logger.error("��ǰ״̬��ѯʧ�ܣ�", e);
                
	              return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ǰ״̬��ѯʧ��!");
            }
        }
        return super.queryCurrentStatus(map);
    }
	
    /**
     * ����ת������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/19 
     */
	@Override
    public ReturnWrap commitCallTransferNo(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if(IntMsgUtil.isTransEBUS("BLCSEIDealCallTransfer"))
		{
	        try
	        {
	        	// ��Ρ�
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// �ֻ���
            	inParamMap.put("telNum", (String)map.get("servNumber"));
            	
	            // ��������
            	inParamMap.put("dealType", (String)map.get("dealType"));

            	// ��ת����
            	inParamMap.put("callType", (String)map.get("callType"));
	            
	            // ����ת����
            	inParamMap.put("calledNum", (String)map.get("transferNumber"));
            	
            	return getIntMsgUtil().invokeDubbo("BLCSEIDealCallTransfer", (String)map.get("menuID"), 
            		(String)map.get("contactID"), "1", (String)map.get("servNumber"), 
            		(String)map.get("operID"), (String)map.get("termID"), inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("����ת������ʧ�ܣ�", e);
	
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ת���������쳣��");
	        }
		}
        return super.commitCallTransferNo(map);
    }
	
    /**
     * ʹ���ֻ����롢����������������֤
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_373 �����ն�ȫ���̽���EBUS����_����������֤�����֤��֤
     */
	/*@Override
    public ReturnWrap getUserInfoWithPwd(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
    	{
    		try 
    		{
    			// ���
    			Map<String,String> inParamMap = new HashMap<String,String>();
    			
    			// �ֻ�����
    			inParamMap.put("telNum", (String)map.get("telnum"));
    			
    			// �Ƿ�У������
    			inParamMap.put("isCheckPass", "1");
    			
    			// ����
    			inParamMap.put("passWord", (String)map.get("password"));

    			// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
    			String[][] outParamKeyDefine = {{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype", "nettype", "contacted", "status", "subage", "subscore", "smallregion"},
    											{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

    			return getIntMsgUtil().invokeDubbo("Atsv_Qry_UserInfo_Hub", "10001001", "", "1", 
    				(String)map.get("telnum"), (String)map.get("operID"), (String)map.get("termID"), 
    				inParamMap, outParamKeyDefine);
			} 
    		catch (Exception e) 
    		{
    			logger.error("����������֤ʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
    	}
        return super.getUserInfoWithPwd(map);
    }*/
	
    /**
     * �������¼
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_373 �����ն�ȫ���̽���EBUS����_����������֤�����֤��֤
     */
/*	@Override
    public ReturnWrap getUserInfo(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
		{
			try 
    		{
				// ���
    			Map<String,String> inParamMap = new HashMap<String,String>();

    			// �ֻ�����
    			inParamMap.put("telnum", (String)map.get("telnum"));
    			
    			// �Ƿ�У������
    			inParamMap.put("ischeckpass", "0");
    			
    			// ����
    			inParamMap.put("password", (String)map.get("password"));

    			// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
    			String[][] outParamKeyDefine = {{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype", "nettype", "contacted", "status", "subage", "subscore", "smallregion"},
    											{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

    			return getIntMsgUtil().invokeDubbo("Atsv_Qry_UserInfo_Hub", "10001001", "", "1", 
    				(String)map.get("telnum"), (String)map.get("operID"), (String)map.get("termID"), 
    				inParamMap, outParamKeyDefine);
			} 
    		catch (Exception e) 
    		{
    			logger.error("�������¼��ȡ�û���Ϣʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
		}
        return super.getUserInfo(map);
    }*/
	
	/** 
     * �굥��ѯȨ����֤_����
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/22 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     * @remark modify by zKF69263 2014/09/15 OR_huawei_201409_425 �����ն˽���EBUS_�굥��ѯ
     */
    @Override
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("CTCSQueryBillDetailChk"))
		{
			try
	        {
				// ���
				Map<String,String> inParamMap = new HashMap<String,String>();
				
				// �ֻ�����
				inParamMap.put("telNum", msgHeader.getTelNum());
				
				// ����ebus����
	            return getIntMsgUtil().invokeDubbo("CTCSQueryBillDetailChk", msgHeader, inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("������֤�굥��ѯȨ��ʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
	        }
		}
		
		return super.checkQueryAuth(msgHeader);
    }
	
    /** 
     * NG3.5���굥����֮�굥��ѯ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param month ��ѯ�·�
     * @param cdrType �굥����
     * @param feeType ��������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     * * @remark create hWX5316476 2014/04/21 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     */
    @Override
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if (IntMsgUtil.isTransEBUS("qryClearList"))
    	{
    		try 
    		{
				Map<String,String> inParamMap = new HashMap<String,String>();

	            // �ֻ�����
				inParamMap.put("telnum", msgHeader.getTelNum());
	            
	            // �û�����
				inParamMap.put("userpwd", "");
	            
	            // ����
				inParamMap.put("billcycle", month);
				
	            // ��ʼ����
				inParamMap.put("startdate", "");
	            
	            // ��������
				inParamMap.put("enddate", "");
	            
	            // �굥����
				inParamMap.put("flag", cdrType);
	            
	            // У������
				inParamMap.put("ckkey", "");
	            
	            // ѡ������
				inParamMap.put("selecttype", feeType);
	            
	            // ��������
				inParamMap.put("accesstype", "bsacAtsv");
				
				// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
    			String[][] outParamKeyDefine = {{"billcount","billitem"},{"billsummary","billitem"}};
    			
    			// ����ebus����
                return getIntMsgUtil().invokeDubbo("qryClearList", msgHeader, inParamMap, outParamKeyDefine);
			}
    		catch (Exception e) 
    		{
    			logger.error("���굥��ѯʧ�ܣ�", e);
                
    			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
    	}
    	
    	return super.queryDetailedRecords2012(msgHeader, month, cdrType, feeType);
    }

	/**
     * �ɷ���ʷ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
	public ReturnWrap qryChargeHistory(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQueryReceptionHis"))
        {
        	try
        	{
	        	// ���
	        	Map<String, Object> paramMap = new HashMap<String, Object>();
	
	        	// �ֻ�����
	        	paramMap.put("telNum", (String)map.get("servNumber"));
	        	
	        	// ��ʼʱ��
	        	paramMap.put("startDate", (String)map.get("startDate"));
	        	
	        	// ����ʱ��
	        	paramMap.put("endDate", (String)map.get("endDate"));
	        	
	        	// ҵ�����ͣ��ӿ��Ǳ�˵�ȴ�����
	        	paramMap.put("receptionType", "Charge");
	        	
	        	// ����ʹ���д���0��,�����õ�
	        	paramMap.put("qryRecOrdbyAcctID", "0");
	        	
	        	// ��������Dubbo����
	        	return getIntMsgUtil().invokeDubbo("BLCSQueryReceptionHis", (String)map.get("curMenuId"), 
        			(String)map.get("contactID"), "1", (String)map.get("servNumber"),
        			(String)map.get("operID"), (String)map.get("termID"), paramMap, 
        			new String[]{"formNum","itemName","recDate","cycle","fee","status","contactType"});
        	}
        	catch (Exception e)
        	{
        		logger.error("�ɷ���ʷ��ѯʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�ɷ���ʷ��ѯʧ��!");
        	}
        }
		return super.qryChargeHistory(map);
	}
	
	 /**
     * ���ֲ�ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by hWX5316476 2014-05-13 OR_huawei_201405_233  �����ն˽���EBUSһ�׶�_��ѯ����
     */
	@Override
	public ReturnWrap queryScoreValue(Map map) 
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("qrySubsScoreSimple139Mail"))
        {
        	try 
        	{
	        	// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// �ֻ�����
				paramMap.put("telnum", (String)map.get("telnumber"));
				
				// 0�������ʼ� 1�����ʼ�
				paramMap.put("issendmail", "0");
				
				// ��������Dubbo����
				ReturnWrap rw =  getIntMsgUtil().invokeDubbo("qrySubsScoreSimple139Mail", (String)map.get("curMenuId"), 
					(String)map.get("touchoid"), "1", (String)map.get("telnumber"),
					(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap);
				
				// �ɹ����ػ�����Ϣ 
				if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
				{
					CTagSet cs = (CTagSet)rw.getReturnObject();
					String sbrand = cs.GetValue("sbrand");
					
					String brandName = "";
					if("M".equals(sbrand))
					{
						brandName = "���еش�";
					}
					else if("G".equals(sbrand))
					{
						brandName = "ȫ��ͨ";
					}
					else
					{
						brandName = "������";
					}
					// ƴװ�ɴ���ʽ��Ʒ������~���û���~������������~�������ѻ���~���½�������~�����Ѷһ�����~����ĩʣ�����~����������
					StringBuffer sb = new StringBuffer();
					sb.append(brandName).append("~");
					sb.append(cs.GetValue("lleftscore")).append("~");
	                sb.append(cs.GetValue("curincscorestr")).append("~");
	                sb.append(cs.GetValue("curconsumescorestr")).append("~");
	                sb.append(cs.GetValue("curawardscorestr")).append("~");
	                sb.append(cs.GetValue("curconvertscorestr")).append("~");
	                sb.append(cs.GetValue("curlastscorestr")).append("~");
	                sb.append(cs.GetValue("yearzeroscorestr"));
					CTagSet cts = new CTagSet();
					cts.SetValue("scoreinfo", sb.toString());
					rw.setReturnObject(cts);
				}
				return rw;
        	}
        	catch (Exception e)
        	{
        		logger.error("���ֲ�ѯʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ֲ�ѯʧ��!");
        	}
        }
		return super.queryScoreValue(map);
	}
	
	/**
     * ������ϸ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
	public ReturnWrap queryScoreDetailHis(Map map) 
	{ 
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQryScoreDetailHis"))
        {							
        	try
        	{
	        	// ���
	        	Map<String, Object> paramMap = new HashMap<String, Object>();
	        	
	        	// �ֻ�����
	        	paramMap.put("servNumber", (String)map.get("telnumber"));
	        	
	        	// ����
	        	paramMap.put("region", (String)map.get("region"));
	        	
	        	// ��ѯ���� :0 ����;1 Mֵ;2 �ֱ� ��Ϊ��
	        	paramMap.put("qryType", "0");
	        	
	        	// ��ʼʱ��
	        	paramMap.put("startDate", (String)map.get("startDate") + "000000");
	        	
	        	// ����ʱ��
	        	paramMap.put("endDate", (String)map.get("endDate") + "235959");
	        	
	        	// ��������Dubbo����
	        	ReturnWrap rw =  getIntMsgUtil().invokeDubbo("BLCSQryScoreDetailHis", (String)map.get("curMenuId"), 
        			(String)map.get("touchoid"),"1", (String)map.get("telnumber"), 
        			(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap, 
        			new String[]{"subsID","recOid","scoreType",
        				"chgScore","accessType","null1","null2",
        				"updateTime","reason","chgScore","null3",
        				"null4","null5","null6","null7","cycle",
        				"servNumber","subsName"});
	        	
	        	// ����ӿڵ��óɹ��������س��ε����鲿����ӵ�returnObject
	        	if(rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
	        	{
	        		Vector vector = (Vector)rw.getReturnObject();
	        		
	        		if (vector != null && vector.size() > 1)
	                {
	                    CRSet crset = (CRSet) vector.get(1);
	                    rw.setReturnObject(crset);
	                }
	        		else
	        		{
	        			CRSet crset = new CRSet();
	        			rw.setReturnObject(crset);
	        		}
	        	}
	        	return rw;
        	}
        	catch (Exception e)
        	{
        		logger.error("������ϸ��ѯʧ�ܣ�", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������ϸ��ѯʧ��!");
        	}
        }
		return super.queryScoreDetailHis(map);
	}

	/**
	 * ������Ļ��ֶһ���ʷ��ѯ
	 * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
	@Override
	public ReturnWrap queryScorePrizeHis(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("fmGetScorePrize"))
		{
			try
			{
				// ���
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// �ֻ�����
				paramMap.put("telnum", (String)map.get("telnumber"));
				
				// ��ʼʱ�� 
				paramMap.put("startDate", (String)map.get("startDate"));
				
				//����ʱ��
				paramMap.put("endDate", (String)map.get("endDate"));
				
				//��������Dubbo����
				return getIntMsgUtil().invokeDubbo("fmGetScorePrize", (String)map.get("curMenuId"),
					(String)map.get("touchoid"), "1", (String)map.get("telnumber"), 
					(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap,
					new String[]{"statusdate","prodname","orgname","opername"});
			}
			catch (Exception e)
			{
				logger.error("������Ļ��ֶһ���ʷ��ѯʧ�ܣ�", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������Ļ��ֶһ���ʷ��ѯʧ��!");
			}
		}
		return super.queryScorePrizeHis(map);
	}

    /**
     * PUK���ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 2014-07-09 OR_huawei_201406_303 �����ն˽���EBUS���׶�_PUK���ѯ 
     */
    public ReturnWrap queryPUK(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if(IntMsgUtil.isTransEBUS("BLCSQrySimPuk"))
    	{
    		try
    		{
    			// ��װ���
    			Map<String, Object> paramMap = new HashMap<String, Object>();
    			
    			// �ֻ�����
    			paramMap.put("telNum", (String) map.get("telnumber"));
    			
    			// �˴����봫servNumber
    			paramMap.put("servNumber", (String) map.get("telnumber"));
    			
    			// ��ѯ��ʽ
    			paramMap.put("qryType", "");
    			
    			//�Ƿ���������ѯ
    			paramMap.put("isNetCard", "");
    			
    			//SIM����
    			paramMap.put("enum", "");
    			
    			// �հ׿���
    			paramMap.put("blankNum", "");
    			
    			// �Ƿ���ҪMSI��Ϣ
    			paramMap.put("isLinkMsiInfo", "");
    			
    			String[] arrAttrsKey = {"iCol0", "iCol1", "iCol2", "iCol3"};
    			
    			return getIntMsgUtil().invokeDubbo("BLCSQrySimPuk", (String) map.get("curMenuId"), 
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), 
					(String) map.get("curOper"), (String) map.get("atsvNum"), paramMap, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    		    return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    		}
    		
    	}
    	return super.queryPUK(map);
    }

	/**
	 * �����ز�ѯ��������
	 * @param map
	 * @return
	 * @remark create by sWX219697 2014-7-8 OR_huawei_201407_35_�����ն˽���EBUS���׶�_�����ز�ѯ 
	 */
    public ReturnWrap queryUserRegionReq(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("PTQueryLocation"))
		{
			try
			{
				// ���
				Map<String, Object> inMap = new HashMap<String, Object>();
	            
				// �ֻ�����
				inMap.put("servNum", (String)map.get("qryServnuber"));
				
				//�Ƿ����ʡ����Ϣ 1���������1�������
				inMap.put("linkProvInfo", "1");
				
				//��������Dubbo����
				return getIntMsgUtil().invokeDubbo("PTQueryLocation", (String)map.get("curMenuId"),
					(String)map.get("touchoid"), "0", "999", (String)map.get("curOper"), 
					(String)map.get("atsvNum"), inMap,
					new String[][]{{"regionName","provName"},{"cityname","provname"}});
			}
			catch (Exception e)
			{
				logger.error("����������ز�ѯʧ�ܣ�", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����������ز�ѯʧ��!");
			}
		}
			
		return super.queryUserRegionReq(map);
	}

	/**
	 * ����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ
	 * 
	 * @param map �ӿ����
	 * @return ReturnWrap ����ֵ
	 * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 �����ն˽���EBUS���׶�_����
	 */
	@Override
    public ReturnWrap queryFeeMessage(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQryRatePlane"))
        {
	        try
	        {
                Map<String, Object> inParamMap = new HashMap<String, Object>();
	            
                // NCODE
                inParamMap.put("nCode", map.get("nCode"));
                
                // ��������
                inParamMap.put("opType", "PCOpRec");
                
	            // �ֻ�����
	            inParamMap.put("telNum", map.get("servNumber"));
	            
	            // ����dubbo����
	            return getIntMsgUtil().invokeDubbo("BLCSQryRatePlane", (String)map.get("menuID"), 
	            	(String)map.get("contactID"), "1", (String)map.get("servNumber"),
            		(String)map.get("operID"), (String)map.get("termID"), inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("ҵ������ʱ����Ʒ�ʷ���Ϣ��ѯʧ�ܣ�", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
	        }
        }
        
        return super.queryFeeMessage(map);
    }
    
    /**
     * ����ncode��ѯ�ر���ʾ��Ϣ
     * 
     * @param paramMap �ӿ����
     * @return ReturnWrap ����ֵ
     * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 �����ն˽���EBUS���׶�_����
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSGetObjectTipsByNCode"))
        {
            try
            {
                // ���
                Map<String, Object> param = new HashMap<String, Object>();
                
                // ��ѯ����
                List<Map<String, String>> dataList = new  ArrayList<Map<String,String>>();
                
                // crset���
                Map<String, String> prodParam = new HashMap<String, String>();
                
                // NCODE����
                prodParam.put("NCODE", paramMap.get("ncode"));
                
                // ��ʾ����
                prodParam.put("TIPTYPE", paramMap.get("tipType"));
                
                // ��������
                prodParam.put("OPERATORTYPE", paramMap.get("operType"));
                
                // ��Ӳ�ѯ����
                dataList.add(prodParam);
                
                // ��ѯ����
                param.put("dataList", dataList);
                
                // �ֻ�����
                param.put("servNumber", paramMap.get("telnum"));
                
                // ҵ������
                param.put("recType", paramMap.get("rectype"));
                
                // ����dubbo����
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("BLCSGetObjectTipsByNCode", paramMap.get("menuid"), 
                    paramMap.get("unicontact"), "1", paramMap.get("telnum"), 
                    paramMap.get("operatorid"), paramMap.get("termid"),
                    param, new String[]{"NCODE","TIPTYPE","OPERATORTYPE","TIPTEXT"});
                
                // ��װvector����rw
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Vector<CPEntity> v = new Vector<CPEntity>();
                    v.add(0, null);
                    v.add(1, (CRSet)rw.getReturnObject());
                    rw.setReturnObject(v);
                }
                
                return rw;
            }
            catch (Exception e)
            {
                logger.error("����NCode��ѯ��Ʒ��ʾ��Ϣʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����NCode��ѯ��Ʒ��ʾ��Ϣʧ��!");
            }
        }
        
        return super.qryNcodeTips(paramMap);
    }
	
	/**
     * ��ѯ��Ʒ��ʾ��Ϣ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by jWX216858 2014-07-03 OR_huawei_201407_33 �����ն˽���EBUS���׶�_�����
     */
	@Override
	public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods) 
	{
		// ����תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSGetObjectTips"))
		{
			// ���
			Map<String, Object> param = new HashMap<String, Object>();
			
			// �ֻ�����
			param.put("servNumber", paramMap.get("telnum"));
			
			// ҵ������
			param.put("recType", paramMap.get("recType"));
			
			Map<String, String> prodMap = null;
			
			// crset���
			Map<String, String> prodParam = new HashMap<String, String>();
			List<Map<String, String>> prodList = new  ArrayList<Map<String,String>>();
			
			// �������ϣ����ת��
			for (int i = 0; i < prods.size(); i++)
			{
				prodMap = prods.get(i);
				
				// ��������
				prodParam.put("OBJTYPE", prodMap.get("objectType"));
				
				// ��ʾ����
				prodParam.put("TIPTYPE", prodMap.get("tipType"));
				
				// ��������
				prodParam.put("OPERTYPE", prodMap.get("operType"));
				
				// �������
				prodParam.put("OBJID", prodMap.get("objectID"));
				
				prodList.add(prodParam);
			}
			
			param.put("dataList", prodList);
			
			// ����dubbo����
			ReturnWrap rw = getIntMsgUtil().invokeDubbo("BLCSGetObjectTips", paramMap.get("menuid"), 
				paramMap.get("unicontact"), "1", paramMap.get("telnum"), 
				paramMap.get("operatorid"), paramMap.get("termid"),
				param, new String[]{"OBJID","OBJTYPE","TIPTYPE","OPERTYPE","TIPTEXT"});
		
			// ��װvector����rw
			if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
			{
				CRSet crset = (CRSet) rw.getReturnObject();
				Vector<CPEntity> v = new Vector<CPEntity>();
				if (null != crset)
				{
					v.add(0, new CTagSet());
					v.add(1, crset);
				}
				rw.setReturnObject(v);
			}
			return rw;
		}
		return super.qryObjectTips(paramMap, prods);
	}
	
	/** 
     * ��Ʒ���ٷ���-��ѯ��Ʒ�����Ӳ�Ʒ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param nCode ��Ʒ�����롢ģ��ID
     * @param type ���ͣ�2 ��Ʒ�� 3 ģ��
     * @param optType ��������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: add zKF69263 2014/09/15 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("PTGetPackageOrTempletItems"))
        {
            try
            {
            	// ��װ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �������
                paramMap.put("nodeID", nCode);
                
                // ��type����ת����2 ��Ʒ��PCIntObjPack 3 ģ��PCIntObjTplt
                if ("2".equals(type))
                {
                	type = "PCIntObjPack";
                }
                else if ("3".equals(type))
                {
                	type = "PCIntObjTplt";
                }
                
                // ��������
                paramMap.put("objType", type);
                
                // ��������
                paramMap.put("recType", "ChangeProduct");
                
                // ��������
                paramMap.put("channel", msgHeader.getChannelId());
                
                // ��������
                paramMap.put("opType", optType);
                
                // ����ת�� :��ѡ��Ʒ��С��,��ѡ��Ʒ�����,
                String[][] objStr = {{"MIN","MAX","DATALIST"},{"minprod","maxprod","DATALIST"}};
                
                /*
                 * ����CRSet����˳��
                 * ��Ʒ������,��Ʒ����,�Żݱ���,��Ʒ����,ѡ������ SeleType_Choice����ѡ SeleType_Must����ѡ
                 * �Ƿ��и������� 0���� 1����,�ӿ�ҵ������ PCIntRelaNormal����ͨҵ�� PCIntRelaRadio��ҵ���л�
                 */
                String[] attrKey = {"C0","C1","C2","C3","C4","C5","C6"};
                
                // ����ebus����ӿ�
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("PTGetPackageOrTempletItems", msgHeader, paramMap,
                		objStr, attrKey);
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                	Vector v = new Vector();
                	if (rw.getReturnObject() instanceof CTagSet)
                	{
                		v.add(rw.getReturnObject());
                		v.add(new CRSet());
                		rw.setReturnObject(v);
                	}
                }
                return rw;
            }
            catch (Exception e)
            {
                logger.error("��ѯ��Ʒ�����Ӳ�Ʒ��", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qrySubProds(msgHeader, nCode, type, optType);
    }
    
    /** 
     * ��Ʒ���ٷ���-�û��Ѷ�����Ʒ��Ϣ��ѯ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/16 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("PTQrySubsOrderAndIntMultiProduct"))
        {
            try
            {
                // ��װ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �Ƿ���ԭBLCSEIIntMultiProductQry�߼���ʶ
                paramMap.put("isIntMultiProdQry", "1");
                
                // ��ֵ��Ʒ�������
                paramMap.put("prodClass", "");
                
                // ��ѯ��ʽ,�̶���5
                paramMap.put("qryType", "5");
                
                // �ײʹ��ࣨ�ײͲ�ѯʱ�ã�
                paramMap.put("pkgType", "");
                
                // �Ƿ������Ʒ��
                paramMap.put("isSolution", "");
                
                // �����û����
                paramMap.put("grpSubsOid", "0");
                
                // ���ͷ��û���ʶ
                paramMap.put("donorSubsId", "");
                
                // �Ƿ��ѯ����û�������ֵ��Ʒ��Ϣ����""
                paramMap.put("isHaveBandProdID", "");
                
                // �ͷ�IVR�ײ�ʹ�ò�ѯ�á���""
                paramMap.put("queryFlag", "");
                
                // �ͷ�IVR�ײ�ʹ�ò�ѯ�á���""
                paramMap.put("pkgInfo", "");
                
                // �ͷ�IVR�ײ�ʹ�ò�ѯ�á���""
                paramMap.put("exceptClass", "");
                
                // �ͷ�IVR�ײ�ʹ�ò�ѯ�á���""
                paramMap.put("prodList", "");
                
                // ����ebus����ӿ�
                return getIntMsgUtil().invokeDubbo("PTQrySubsOrderAndIntMultiProduct", msgHeader, paramMap, 
                    new String[]{"prodID", "prodName", "attr", "serviceRes", "applyDate", "createDate", "endDate", 
                        "status", "formNum", "productClass", "description", "donorSubsID", "rltRefID", 
                        "productClassName", "cancelDate", "pkgProdID", "outNcodeType", "privID", "NcodeID"});
            }
            catch (Exception e)
            {
                logger.error("��ѯ�û��Ѷ�����Ʒʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qryHasProds(msgHeader);
    }
    
    /** 
     * ��Ʒ���ٷ���-��ѯ��Ʒ��������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param qryType ��ѯ���� 0��NCODE 1����Ʒ���²�Ʒ
     * @param nCode nCode
     * @param operType PCOpRec:��ͨ  PCOpMod:�޸� PCOpDel:�ر� 
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: add zKF69263 2014/09/15 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("PTGetAppendAttrByID"))
        {
            try
            {   
                // ��װ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �û�����
                paramMap.put("telnum", msgHeader.getTelNum());
                
                // ��ѯ����
                paramMap.put("qryType", qryType);
                
                // �û����
                paramMap.put("entityID", nCode);
                
                // ��������
                paramMap.put("opType", operType);
                
                /**
                 * ����CRSet����˳��
                 * ������룬�������Ա��룬�����������ƣ������������ͣ�ֵ���� NUMBER:���� STRING:�ַ�����
                 * ��������ֵ��С���ȣ���������ֵ��󳤶ȣ��Ƿ���� 0������ 1���ǣ��Ƿ����չ�� 0������ 1���ǣ�
                 * ����ֵ�������������Էָ������û�����ֵ���ֵ���Ϣ����������:��Ʒ/�Ż�/����
                 */ 
                String[] attrParam = {"objected","attrID","entityName","attrType","valueType","minLen",
                		"maxLen","isNeed","isShowInRec","valCount","sepSign","defaultValue","dictVal","objType"};
                
                // ����ebus����ӿ�
                return getIntMsgUtil().invokeDubbo("PTGetAppendAttrByID", msgHeader, paramMap, attrParam);
                
            }
            catch (Exception e)
            {
                logger.error("��ѯ��Ʒ��������ʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qryAddAttr(msgHeader, qryType, nCode, operType);
    }
    
    /** 
     * ��Ʒ���ٷ���-��Ʒ����
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/24 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSMultiProductRec"))
        {
            try
            {   
                // ��װ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �������
                paramMap.put("servNum", multiProdCommitPO.getTelnum());
                
                // �Ƿ�У����Դ
                paramMap.put("isChkRes", multiProdCommitPO.getIscheck());
                
                // ���ͺ���
                paramMap.put("doneeNum", multiProdCommitPO.getDoneenum());
                
                // ��ѱ�ʶ��Ԥ���ֶΣ��ݲ�ʹ�á�������
                paramMap.put("isCalcFee", multiProdCommitPO.getIscalcfee());
                
                // �Ƿ��Ͷ��ţ�0:������;1����
                paramMap.put("isSendSms", multiProdCommitPO.getIssendsms());
                
                // ������
                paramMap.put("operSource", multiProdCommitPO.getOpersource());
                
                // ��Ʒ�б�
                List<ProdCommitPO> prodCommitPOList = multiProdCommitPO.getProdCommitList();
                
                // ��װ��Ʒ�б�
                List<Map<String, Object>> recProdList = new ArrayList<Map<String, Object>>();
                
                // ѭ����Ʒ�б�
                for(ProdCommitPO prodCommitPO : prodCommitPOList)
                {
                    Map<String, Object> recProdMap = new HashMap<String, Object>();
                    
                    // ��ֵ��Ʒ���룬�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ������Ʒ���룻������NCODE
                    recProdMap.put("prodID", prodCommitPO.getNcode());
                    
                    // ��Ч��ʽ��2:������4:���գ�3:����
                    recProdMap.put("effectType", prodCommitPO.getEffecttype());
                    
                    // �������ͣ�PCOpRec:��ͨ PCOpMod:�޸� PCOpDel:�ر� PCOpPau:��ͣ PCOpRes:�ָ�
                    recProdMap.put("opType", prodCommitPO.getOpertype());
                    
                    // ��������(attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2����)
                    recProdMap.put("attrPara", prodCommitPO.getAttrparam());
                    
                    // ԭ��ֵ��Ʒ����(ֻ�в�������Ϊ�޸�ʱ����Ч)
                    recProdMap.put("oldProdID", prodCommitPO.getOldprodid());
                    
                    // ������Դ(resid1=restype1=optype1#resid2=restype2=optype2����)
                    recProdMap.put("resPara", prodCommitPO.getServiceres());

                    // �ӿڶ�Ӧ���ͣ��Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ�á� PCIntRelaNormal����ͨҵ�� PCIntRelaRadio��ҵ���л�
                    recProdMap.put("relaType", prodCommitPO.getInftype());
                    
                    // �Ƿ�ģ�崦��, Ĭ��Ϊ0��0������ 1����
                    recProdMap.put("isTemplate", prodCommitPO.getTempletflag());
                    
                    // ָ����Чʱ��
                    recProdMap.put("startDate", prodCommitPO.getStartdate());
                    
                    // ָ��ʧЧʱ��
                    recProdMap.put("endDate", prodCommitPO.getEnddate());
                    
                    // ��Ʒ������
                    recProdMap.put("packageID", prodCommitPO.getPkgid());
                    
                    // ��������
                    recProdMap.put("objType", prodCommitPO.getObjtype());
                    
                    // �Żݱ���
                    recProdMap.put("privID", prodCommitPO.getPrivid());
                    
                    // ģ�����
                    recProdMap.put("templateID", prodCommitPO.getTempletid());
                    
                    // ������װ��Ʒ�б���
                    recProdList.add(recProdMap);
                }
                
                // ��Ʒ�б�
                paramMap.put("recProdList", recProdList);
                
                // ����ebus����ӿ�
                return getIntMsgUtil().invokeDubbo("BLCSMultiProductRec", msgHeader, paramMap,
                    new String[][]{{"recOid", "orderID"}, {"recOid", "orderID"}});
                
            }
            catch (Exception e)
            {
                logger.error("��Ʒ����ʧ�ܣ�", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.prodRec(msgHeader, multiProdCommitPO);
    }
    
    /**
     * ������ʷ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 OR_huawei_201407_39 �����ն˽���EBUS���׶�_������ʷ��ѯ 
     */
    public ReturnWrap qryAllServiceHistory(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if(IntMsgUtil.isTransEBUS("BLCSQryReceptionSimple"))
    	{
    		try
    		{
    			// ��װ���
	    		Map<String, Object> paramMap = new HashMap<String, Object>();
	    		
	    		// �ֻ�����
	    		paramMap.put("telNum", map.get("servnumber"));
	    		
	    		paramMap.put("outOperid", "1");
	    		
	    		// ��ѯ��ʼʱ��
	    		paramMap.put("startDate", map.get("startDate"));
	    		
	    		// ��ѯ����ʱ��
	    		paramMap.put("endDate", map.get("endDate"));
	    		
	    		paramMap.put("operType", "1");
	    		 
	    		// ��װ�������ֵ������CRSet���ص�ǰ̨
	    		String[] arrAttrsKey = {"recdate", "contacttype", "recopid", "recdefname"};
	    		
	    		// ���й��ڽӿ����qryAllFlag���������ն˲಻��ֵ������£���̨Ĭ��Ϊ0����ѭͳһ�ӿ�ƽ̨�Ĵ����˴�ʡȥqryAllFlag��ֵ
	    		return getIntMsgUtil().invokeDubbo("BLCSQryReceptionSimple", 
	    				(String) map.get("curMenuId"), 
	    				(String) map.get("touchoid"), "1",
	    				(String) map.get("servnumber"), 
	    				(String) map.get("curOper"), 
	    				(String) map.get("atsvNum"), paramMap, null, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
                rw.setReturnMsg("");
                
                return rw;
    		}
    	}
    	
    	return super.qryAllServiceHistory(map);
    }

    /**
     * ����ר�ã���ѯҵ������״̬
     * 
     * @param msgHeader ��Ϣͷ
     * @param nCode
     * @param operType���������ͣ���ͨ����ȡ��
     * @return
     * @se
     * @remark create by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_������֪����ˮ�߲���EBUS����
     */
	@Override
	public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType) 
	{
		try
		{
			// ����תEBUS���ؿ���
			if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        	{
        		Map<String, String> paramMap = new HashMap<String, String>();
        		
        		// �ֻ�����
        		paramMap.put("TELNUM", msgHeader.getTelNum());
        		
        		// ��������ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
        		paramMap.put("STYPE", "QRY");
        		
        		// �̶���SUBSNCODEEXIST
        		paramMap.put("NCODE", "SUBSNCODEEXIST");
        		
        		// ��Ʒ��Ӧ��ncode
        		paramMap.put("NCODELIST", nCode);
        		
        		// ����dubbo����
        		return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
        	}
		}
        catch (Exception e)
        {
            logger.error("��ѯҵ������״̬ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
		return super.qryRecStatusHub(msgHeader, nCode, operType);
	}

	/**
     * ��Ʒ����ͨ�ýӿ�
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ��Ϣ
     * @param nCode ncode
     * @param operType ��������ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
     * @param effectType ��Ч��ʽ
     * @param param �������Դ� ��action�ഫ���Ķ��ǿգ�
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_������֪����ˮ�߲���EBUS����
     */
	@Override
	public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
	{
		try
		{
			// ����תEBUS���ؿ���
			if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        	{
        		Map<String, String> paramMap = new HashMap<String, String>();
        		
        		// �ֻ�����
        		paramMap.put("TELNUM", msgHeader.getTelNum());
        		
        		// ��������ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
        		paramMap.put("STYPE", operType);
        		
        		// �̶���SUBSNCODEEXIST
        		paramMap.put("NCODE", nCode);
        		
        		// ��Ч��ʽ
        		paramMap.put("EFFECT_TYPE", effectType);
        		
        		// �������Դ�
        		paramMap.put("PARM", param);
        		
        		// ����dubbo����
        		ReturnWrap rw = getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);

        		// ����������Ϣ�������ٴη�װ
        		if (SSReturnCode.SUCCESS == rw.getStatus())
        		{
        			CTagSet ctagSet = new CTagSet();
        			if (rw.getReturnObject() instanceof Vector)
        			{
        				Vector v = (Vector) rw.getReturnObject();
        				CTagSet ctag = (CTagSet) v.get(0);
        				ctagSet.SetValue("formnum", ctag.GetValue("FORMNUMBER")); // ҵ��������ˮ
        			}
        			if (rw.getReturnObject() instanceof CTagSet)
        			{
        				CTagSet ctag = (CTagSet)rw.getReturnObject();
        				ctagSet.SetValue("formnum", ctag.GetValue("FORMNUMBER")); // ҵ��������ˮ
        			}
        			rw.setReturnObject(ctagSet);
        		}
        		return rw;
        	}
		}
        catch (Exception e)
        {
            logger.error("��ѯҵ������״̬ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
		return super.recCommonServ(msgHeader, nCode, operType, effectType, param);
	}
	
	/**
     * ��ѯ�ͻ�Ӧ���ܽ�� ������ֱ�ӵ���һ��boss�ӿ�
     * @param msgHeader
     * @param orgid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-3-27 18:01:22 OR_NX_201501_1030_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
        	//����������
        	Document doc = DocumentHelper.createDocument();
        	
        	//�����ĸ��ڵ�
        	Element eleRoot = doc.addElement("QryPayReq");
        	
        	//�ӿ�ҵ��id
        	DocumentUtil.addSubElementToEle(eleRoot, "BIZID", "KQFWPayFeeQry");
        	
        	//����Ա���
        	DocumentUtil.addSubElementToEle(eleRoot, "OperatorId", msgHeader.getOperId());
        	
        	//��֯�ṹ���
        	DocumentUtil.addSubElementToEle(eleRoot, "OrgID", orgid);
        	
        	//��ʾ���ͣ�01�ֻ�����
        	DocumentUtil.addSubElementToEle(eleRoot, "IDType", "01");
        	
        	//�ֻ�����
        	DocumentUtil.addSubElementToEle(eleRoot, "IDValue", msgHeader.getTelNum());
        	
        	//һ��boss�ӿڵ���url
        	String interBOSSURL = CommonUtil.getParamValue(Constants.NONLOCALCHARGE_BOSSURL);
        	
            //ֱ�ӵ���һ��boss�ӿ� ��ѯ�ͻ�Ӧ�ɽ��
        	String resXML = getIntMsgUtil().httpInvoke(interBOSSURL, doc, "UTF-8");
        	
        	//��װ���ض���
        	ReturnWrap retData = new ReturnWrap();
        	
        	//�������ر���
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element root = resDoc.getRootElement();
    		
    		//��������Ϊ0000�����ʾ�ӿڵ��óɹ�
    		if(SSReturnCode.INTERBOSS_SUCCESS.equals(root.element("BizOrderResult").getTextTrim()))
    		{
    			//����״̬
    			retData.setStatus(SSReturnCode.SUCCESS);
    			
    			//��װCTagSet
    			CTagSet tagSet = new CTagSet();
    			
    			//ʡ�ݱ���
    			tagSet.SetValue("ProvinceCode", root.element("HSNDUNS").getTextTrim());
    			
        		//������װӦ�ɷ��õ���Ϣ
    			Element qryPayInfo = root.element("QryPayInfo");
    			
    			//�ͻ�����
    			tagSet.SetValue("CustName", qryPayInfo.element("CustName").getTextTrim());
    			
    			//Ӧ�ɷ���
    			tagSet.SetValue("PayAmount", qryPayInfo.element("PayAmount").getTextTrim());
    			
    			//�ͻ�Ԥ����
    			tagSet.SetValue("Balance", qryPayInfo.element("Balance").getTextTrim());

    			retData.setReturnObject(tagSet);
    		}
    		else
    		{
    			retData.setStatus(SSReturnCode.ERROR);
    			
        		//������Ϣ
        		retData.setReturnMsg(root.element("ResultDesc").getTextTrim());
    		}

    		return retData;
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ͻ�Ӧ���ܽ���쳣!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ѯ�ͻ�Ӧ���ܽ���쳣��");
        }
        
    }
    
    /**
     * ��ʡ�ɷ��ύ
     * <������ϸ����>
     * @param msgHeader
     * @param seq ���������ˮ��
     * @param actualPayAmount ʵ�ʽɷѽ��壩
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-4-2 09:58:49 OR_NX_201501_1030_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eleRoot = msgBody.addElement("PaymentReq");

            // ��ʶ���� 01:�ֻ���
            DocumentUtil.addSubElementToEle(eleRoot, "IDType", "01");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(eleRoot, "IDValue", msgHeader.getTelNum());
            
            // ʵ�ʽɷѽ���
            DocumentUtil.addSubElementToEle(eleRoot, "PayAmount", actualPayAmount);
            
            // ������
            DocumentUtil.addSubElementToEle(eleRoot, "HandCharge", "0");
            
            // ���������ˮ��
            DocumentUtil.addSubElementToEle(eleRoot, "Seq", seq);
            
        	//��֯�ṹ���
        	DocumentUtil.addSubElementToEle(eleRoot, "OrgID", orgid);
        	
        	//����Ա���
        	DocumentUtil.addSubElementToEle(eleRoot, "OperatorId", msgHeader.getOperId());
            
            // BIZID �ӿ�ID(����opcode)
            DocumentUtil.addSubElementToEle(eleRoot, "BIZID", "KQFWPayFee");
            
        	//һ��boss�ӿڵ���url
        	String interBOSSURL = CommonUtil.getParamValue(Constants.NONLOCALCHARGE_BOSSURL);
            
            // ����һ��boss�ӿڣ����н���
            String resXML = getIntMsgUtil().httpInvoke(interBOSSURL, msgBody, "UTF-8");
            
        	//��װ���ض���
        	ReturnWrap retData = new ReturnWrap();
        	
        	//�������ر���
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element resRoot = resDoc.getRootElement();
        	
    		//�ӿڵ��óɹ�
        	if(SSReturnCode.INTERBOSS_SUCCESS.equals(resRoot.element("BizOrderResult").getTextTrim()))
        	{
        		retData.setStatus(SSReturnCode.SUCCESS);
        		retData.setReturnMsg("���ѳɹ�");
        	}
        	
        	//�ӿڵ���ʧ��
        	else
        	{
        		retData.setStatus(SSReturnCode.ERROR);
        		retData.setReturnMsg(resRoot.element("ResultDesc").getTextTrim());
        	}
        	
        	return retData;
        }
        catch (Exception e)
        {
            logger.error("��ؽɷ��쳣!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ؽɷ��쳣��");
        }
    }
    
    /**
	 * �����мۿ�����
	 * @param valueCardVO
	 * @param paramMap
	 * @return
	 * @remark create by wWX217192 2015-05-13 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
	 */
	public ReturnWrap getValueCards(MsgHeaderPO header, Map<String, String> inParam)
	{
		try
		{
			return getIntMsgUtil().invokeDubbo("BLCSElecCardSale", header, inParam, null, 
            		new String[]{"cardNo", "cardPwd", "elecCardCntTotal", "cardDate", "CardType", "CardBusiPro"});
		}
		catch(Exception e)
		{
			logger.error("�мۿ�����ʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�мۿ�����ʧ�ܣ�");
		}
	}
    
    /**
     * <�мۿ�У��>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param paramMap ��Ϣ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        try
        {
            return getIntMsgUtil().invokeDubbo("BLCSChkIfEvcCard", msgHeader, paramMap);
        }
        catch (Exception e)
        {
            logger.error("�мۿ�����У��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"�мۿ�����У��ʧ��");
        }
    }
    
    /**
     * <�мۿ���ֵ>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param valueCardPwd ��ֵ������
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
     * @remark modify by hWX5316476 2015-6-10 OR_SD_201505_1022_ɽ�����ӳ�ֵ����������_�����ն˸���
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, String valueCardPwd)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            //�����мۿ���ֵ����
            map.put("channelType", msgHeader.getChannelId());
            
            //����ֵ����
            map.put("calledIdValue", msgHeader.getRouteValue());
            
            //���ӳ�ֵ������
            map.put("cardPassWord", valueCardPwd);
            
            //��ֵ����ʡ���룬������270
            map.put("calllingProv", Constants.VALUECARD_HUB_PROVCODE);
            
            //�����ֻ����� �ɲ���
            map.put("callingIdValue", "");
            
            //Ӫҵ�����룬�ɲ���
            map.put("actionID", "");
            
            //����Ա����
            map.put("actionUserID", msgHeader.getOperId());
            
            return getIntMsgUtil().invokeDubbo("BLCSElecCardCharge", msgHeader, map);
        }
        catch (Exception e)
        {
            logger.error("�мۿ���ֵʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"�мۿ���ֵʧ��");
        }
    }
	/**
	 * ��������ǰУ�����֤��
	 * <������ϸ����>
	 * @param map
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by hWX5316476 2014/04/20 OR_huawei_201404_374 �����ն�ȫ���̽���EBUS����_�������롢�޸�����
	 */
	@Override
	public ReturnWrap checkIDCard(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if(IntMsgUtil.isTransEBUS("PTIDCardCheck"))
		{
			try
			{
				// ��Ρ�
				Map<String,String> inParamMap = new HashMap<String,String>();

				// ֤��id
				inParamMap.put("certID", (String)map.get("IDCard"));

				// ֤������
				inParamMap.put("certType","IdCard");

				// �û�����(�ֻ�����)
				inParamMap.put("callNum", (String)map.get("telnum"));

				return getIntMsgUtil().invokeDubbo("PTIDCardCheck", (String)map.get("menuid"),
						(String)map.get("touchoid"), "1", (String)map.get("telnum"), (String)map.get("operid"),
						(String)map.get("termid"), inParamMap);
			}
			catch (Exception e)
			{
				logger.error("��������ǰУ�����֤��ʧ�ܣ�", e);

				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�����޸�ʧ��!");
			}
		}

		return super.checkIDCard(map);
	}

}
