/**
 * 
 */
package com.gmcc.boss.selfsvc.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSONObject;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.valueCard.model.ValueCardVO;

/**
 * @author zKF69263
 *
 */
public class NewSelfSvcDubboCallTest extends NewSelfSvcCallTest {

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
	        ReturnWrap rw = new ReturnWrap();
	        rw.setStatus(1);
	        rw.setReturnMsg("����������֤�ɹ�!");
	        return rw;
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
    public ReturnWrap getUserInfoHub(Map<String, String> map)
    {
    	if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
    	{
	    	// ����Ϊ��ֵ�ԣ�����JSONObj
	    	Map<String,Object> outParamMap = new HashMap<String,Object>();
	
	    	// ƴװ����
	    	// Ʒ��
	    	outParamMap.put("region", "711");
	    	outParamMap.put("brand", "BrandSzx");
	    	outParamMap.put("subsid", "7110000000814");
	    	outParamMap.put("prodid", "pp.gt.st");
	    	outParamMap.put("status", "US10");
	    	outParamMap.put("flag", "2");
	    	outParamMap.put("starlevel", "1");
	    	outParamMap.put("regionname", "����");
	    	outParamMap.put("nettype", "GSM");
	    	outParamMap.put("stopname", "");
	    	outParamMap.put("vipType", "VC160B");
	    	outParamMap.put("sexname", "��");
	    	outParamMap.put("createdate", "2013-05-20");
	    	outParamMap.put("regname", "������������԰·Ӫҵ��");
	    	outParamMap.put("subname", "���");
	    	outParamMap.put("prodname", "pp.gt��");
	    	outParamMap.put("subage", "1997-09-20");
	    	outParamMap.put("smallregion", "711");
//	    	outParamMap.put("acctID", "7110000000197");//�������û���13607232776
	    	outParamMap.put("acctID", "7115057586341");// ���������û��Ķ�13451008857
	    	
	          // add begin hWX5316476 2014-8-18 OR_huawei_201408_680 [Я��ת��]-�����ն��Ż�����(�л�ΪEBUSЭ��)
            // ������������sbsnTransTelOut������Ӫ��Я����sbsnTransTelIn������Ӫ��Я��
            outParamMap.put("signType", "sbsnTransTelIn");
            // add end hWX5316476 2014-8-18 OR_huawei_201408_680 [Я��ת��]-�����ն��Ż�����(�л�ΪEBUSЭ��)
	    	
	    	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
	    	System.out.println(jsonObj.toString());
	    	ReturnWrap rw = null;
	    	rw =  getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
	    	rw.setReturnMsg("��Ϣ��ѯ�ɹ�");
	    	return rw;
    	}
    	return super.getUserInfoHub(map);
    }
    
	/**
     * ͣ����ҵ����
     */
    public ReturnWrap stopOpenSubs(Map map) 
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSStopOpenSubs")) 
		{
			return getIntMsgUtil().parseJsonResponse("{recFormNum:'20140115000001'}", null, null);
		}
		
		return super.stopOpenSubs(map);
    }
    
    /**
     * ҵ���ѯͳһ�ӿ� ����ҵ���ѯ
     */
    public ReturnWrap queryService(Map map) 
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ")) 
		{
			String response = "[{objType:'22',spID:'000001',spName:'�й��ƶ�',spBizID:'FetionBase',spBizName:'���Ź���',spBizType:'',billingType:'',price:'',domain:'',startDate:'2010-06-08 08:22:18',endDate:'',seqNum:'',charge:'',priceDesc:'���'}"
				+ ",{objType:'21',spID:'801174',spName:'�й��ƶ�',spBizID:'125820',spBizName:'�����',spBizType:'52',billingType:'���',price:'0',domain:'12580',startDate:'2010-09-20 13:52:13',endDate:'',seqNum:'',charge:'',priceDesc:'0.00���'}"
				+ ",{objType:'22',spID:'000001',spName:'�й��ƶ�',spBizID:'MMAIL_F',spBizName:'139������Ѱ�',spBizType:'',billingType:'',price:'',domain:'',startDate:'2010-09-14 17:46:47',endDate:'',seqNum:'',charge:'',priceDesc:'���'}]";
			
			return getIntMsgUtil().parseJsonResponse(response, null, null);
		}
		
		return super.queryService(map);
    }
    
    /**
     * ҵ��ͳһ�˶��ӿ�
     */
    public ReturnWrap cancelService(Map map) 
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSIntServUniteCancel")) 
		{
			return getIntMsgUtil().parseJsonResponse("{recOid:'20140115000002',formNum:'20140115000003'}", null, null);
		}
		
		return super.cancelService(map);
    }
    
    /**
     * SPҵ�񶩹�
     */
    public ReturnWrap orderSPService(Map map) {
        
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ")) 
		{
			return getIntMsgUtil().parseJsonResponse("{orderResult:'0',effetiTime:'2014-04-15',orderFlag:'1',formNumber:'20140115000001'}", 
				new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}}, null);
		}
		
		return super.cancelService(map);
    }
    
    /**
     * �����޸�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/18 OR_huawei_201404_374 �����ն�ȫ���̽���EBUS����_�������롢�޸�����
     */
	@Override
	public ReturnWrap recChangePassword(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsPassWord")) 
		{
			ReturnWrap rw = new ReturnWrap();
	        rw.setStatus(1);
	        return rw;
		}
		return super.recChangePassword(map);
    }
	
	/**
     * ��������У�����֤��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
    public ReturnWrap checkIDCard(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTIDCardCheck"))
        {
    		return getIntMsgUtil().parseJsonResponse(null, null, null);
        }
        
        return super.checkIDCard(map);
    }
	

	
	/**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark 
     */
	@Override
    public ReturnWrap sendSmsHub(Map map)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	 ReturnWrap rw = new ReturnWrap();
             rw.setStatus(SSReturnCode.SUCCESS);

             return rw;
        }
        
        return super.sendSmsHub(map);
    }
    
    /**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    @Override
    public ReturnWrap sendSMS(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
        }
        
        return super.sendSMS(map);
    }
    
	/**
     * ��������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
    public ReturnWrap resetPassword(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("BLCSSetInitPwd"))
        {
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
            try{ 
            	// ����Ϊ��ֵ�ԣ�����JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// ƴװ����
            	outParamMap.put("statusName", "����ʹ��");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		
            	// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
            	String[][] outParamKeyDefine = {{"statusName"},{"state"}};
            	
            	return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            catch (Exception e)
            {
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("��ǰ״̬��ѯʧ��!");
                
                return rw;
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
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
    	if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
    	{
    		try 
    		{
    			// ����Ϊ��ֵ�ԣ�����JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// ƴװ����
            	outParamMap.put("subName", "��Ⱥ");
            	outParamMap.put("region", "711");
            	outParamMap.put("regionName", "");
            	outParamMap.put("productID", "pp.gt.ygtch.634");
            	outParamMap.put("productName", "ȫ��ͨ");
            	outParamMap.put("productGroup", "BrandMzone");
            	outParamMap.put("vipType", "VC0000");
            	outParamMap.put("netType", "");
            	outParamMap.put("contactID", "1");
            	outParamMap.put("status", "US22");
            	outParamMap.put("subAge", "2");
            	outParamMap.put("subScore", "600");
            	outParamMap.put("smallRegion", "7");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		System.out.println(jsonObj.toString());
        		
        		// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
        		String[][] outParamKeyDefine = {{"subName", "region", "regionName", "productID", "productName", "productGroup", "vipType", "netType", "contactID", "status", "subAge", "subScore", "smallRegion"},
						{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

        		return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
			} 
    		catch (Exception e) 
    		{
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("ʹ���ֻ����롢����������������֤�쳣��");
                return rw;
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
     */
	/*@Override
    public ReturnWrap getUserInfo(Map map)
    {
		if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
		{
			try 
    		{
    			// ����Ϊ��ֵ�ԣ�����JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// ƴװ����
            	outParamMap.put("subName", "��Ⱥ");
            	outParamMap.put("region", "711");
            	outParamMap.put("regionName", "");
            	outParamMap.put("productID", "pp.gt.ygtch.634");
            	outParamMap.put("productName", "ȫ��ͨ");
            	outParamMap.put("productGroup", "BrandMzone");
            	outParamMap.put("vipType", "VC0000");
            	outParamMap.put("netType", "");
            	outParamMap.put("contactID", "1");
            	outParamMap.put("status", "US22");
            	outParamMap.put("subAge", "2011-05-08");
            	outParamMap.put("subScore", "600");
            	outParamMap.put("smallRegion", "7");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		System.out.println(jsonObj.toString());
        		
        		// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
        		String[][] outParamKeyDefine = {{"subName", "region", "regionName", "productID", "productName", "productGroup", "vipType", "netType", "contactID", "status", "subAge", "subScore", "smallRegion"},
						{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

        		return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
			} 
    		catch (Exception e) 
    		{
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("ʹ���ֻ����롢����������������֤�쳣��");
                return rw;
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
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
     * @remark create hWX5316476 2014/04/21 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     */
    @Override
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if (IntMsgUtil.isTransEBUS("qryClearList"))
    	{
	        // ����Ϊ��ֵ�ԣ�����JSONObj
        	Map<String,Object> outParamMap = new HashMap<String,Object>();
        	String billaccount = "";
        	String billitem = "";
	        // ȫ������
	        if ("ALL".equals(feeType)) 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|"
                    + "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|201401@_@01-01 -- 01-31@_@98�������ײ�@_@98";
	            	
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2016-08-01 21:56:04@_@�人@_@����@_@66174709@_@60@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@�人@_@����@_@05387763965@_@610@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@132@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@45@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@390@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@88@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-26 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@34.40|"
	                        + "2011-08-27 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@5.07";
	            }
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@��̳�ܱ�@_@@_@0.30|"
                        + "2011-08-03 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@**@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@�ڵ�@_@139********@_@����@_@����@_@@_@�人���Ű�@_@0.00|"
                        + "2011-08-04 21:56:04@_@�۰�̨@_@139********@_@����@_@����@_@@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@����ATAT@_@139********@_@����@_@����@_@@_@@_@0.70";
	            }
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) 
	            {
	                billaccount = "4";
	                billitem = "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-03 21:56:04@_@�����Ż�@_@-76.00|"
	                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-01 21:56:04@_@�����Ż�@_@-76.00";
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem = "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
	                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
	                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType))
	            {
	                billaccount = "5";
	                billitem = "2011-08-01 21:56:04@_@WAP@_@����DIY@_@10658899@_@2|"
	                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@0|"
	                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@����DIY@_@10658899@_@2";
	            } 
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType))
	            {
	                billaccount = "";
	                billitem = "2011-08-05 21:56:04@_@ΥԼ��@_@20.00|2011-08-05 21:56:04@_@Э�鲹�շ�@_@15.00|"
	                        + "2011-08-05 21:56:04@_@�����@_@25.00";
	            }
	        }
	        // �������
	        else if ("1".equals(feeType)) 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|"
	                        + "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|201205@_@01-01 -- 01-31@_@98�������ײ�@_@98";
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-26 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@34.40|"
	                        + "2011-08-27 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@5.07";
	            } 
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@��̳�ܱ�@_@@_@0.30|"
	                        + "2011-08-03 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@**@_@@_@0.10|"
	                        + "2011-08-04 21:56:04@_@�۰�̨@_@139********@_@����@_@����@_@@_@@_@0.10|"
	                        + "2011-08-04 21:56:04@_@����ATAT@_@139********@_@����@_@����@_@@_@@_@0.70";
	            } 
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-03 21:56:04@_@�����Ż�@_@-76.00|"
	                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-01 21:56:04@_@�����Ż�@_@-76.00";
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
	                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
	                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@WAP@_@����DIY@_@10658899@_@2|"
	                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|"
	                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@����DIY@_@10658899@_@2";
	                
	            } 
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) 
	            {
	                ReturnWrap rw = new ReturnWrap();
	                rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            }
	        }
	        // �����
	        else 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	            	ReturnWrap rw = new ReturnWrap();
	            	rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType))
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@�人@_@����@_@66174709@_@60@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@89@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@132@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@45@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@390@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@88@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "3��@_@2��<";
	                billitem = "2011-08-04 21:56:04@_@�ڵ�@_@139********@_@����@_@����@_@@_@�人���Ű�@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType))
	            {
	            	ReturnWrap rw = new ReturnWrap();
	            	rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem =  "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType))
	            {
	                billaccount = "5";
	                billitem = "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@0|";
	            }
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem = "2011-08-05 21:56:04@_@ΥԼ��@_@20.00|2011-08-05 21:56:04@_@Э�鲹�շ�@_@15.00|"
	                        + "2011-08-05 21:56:04@_@�����@_@25.00";
	            }
	        }
	        outParamMap.put("billcount", billaccount);
	        outParamMap.put("billitem", billitem);
	        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
    		System.out.println(jsonObj.toString());
    		
	        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    	}
        
    	return super.queryDetailedRecords2012(msgHeader, month, cdrType, feeType);
    }
	
	 /**
     * �ɷ���ʷ��ѯ
     * 
     * @param map
     * @return
     */
    @Override
	public ReturnWrap qryChargeHistory(Map map)
    {
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQueryReceptionHis"))
        {
        	String response = "[{'formNum':'531100504168436860','itemName':'�շ�','recDate':'20100504090015',"
        		+ "'cycle':'201004','fee':'5000','status':'����','contactType':'����Ӫҵ��'},"
	        	+ "{'formNum':'531100731726732692','itemName':'�շ�','recDate':'20100731112459',"
	    		+ "'cycle':'201006','fee':'5000','status':'����','contactType':'�����ն�'}]";
	    	
	    	// �������͵Ĳ���
	    	String[] arrAttrsKey = {"formNum","itemName","recDate",
	    			"cycle","fee","status","contactType"};
	    	
	    	return getIntMsgUtil().parseJsonResponse(response, null, arrAttrsKey);
        }
		return super.qryChargeHistory(map);
	}

    /**
     * ���ֲ�ѯ
     */
    @Override
	public ReturnWrap queryScoreValue(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("qrySubsScoreSimple139Mail"))
        {
        	return getIntMsgUtil().parseJsonResponse("{'scoreinfo':'�����У��ҿ��У�~300~50~20~20~20~280~10'}", null, null);
        }
		return super.queryScoreValue(map);
	}
    
    /**
     * ������ϸ��ѯ
     */
	@Override
	public ReturnWrap queryScoreDetailHis(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQryScoreDetailHis"))
        {
        	String response = "[{'subsID':'6340963456288','recOid':'88009891730539','scoreType':'���ָ���'," 
        		+ "'chgScore':'-20','accessType':'CRMӪҵ��','updateTime':'2012-04-16 16:54:14'," 
        		+ "'reason':'��������','cycle':'201204','servNumber':'13963456288','subsName':'����13963456288'}]";

        	// �������͵Ĳ���,����CRSet
        	String[] arrAttrsKey = {"subsID","recOid","scoreType","chgScore","accessType",
        			"null1","null2","updateTime","reason","chgScore","null3","null4",
        			"null5","null6","null7","cycle","servNumber","subsName",};
        	
        	return getIntMsgUtil().parseJsonResponse(response, null, arrAttrsKey);
        }
		return super.queryScoreDetailHis(map);
	}

	/**
     * ������Ļ��ֶһ���ʷ��ѯ
     */
	@Override
	public ReturnWrap queryScorePrizeHis(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("fmGetScorePrize"))
        {
        	String response = "{'retDataList':[{'statusdate':'2012-03-01 09:50:50'," 
        		+ "'prodname':'��̬��Ӫ���','orgname':'����','opername':'ϵͳ����Ա'}]}";
        	
        	return getIntMsgUtil().parseJsonResponse(response, null, 
        			new String[]{"statusdate","prodname","startdate","opername"});
        }
		return super.queryScorePrizeHis(map);
	}

	/**
	 * �����ز�ѯ��������
	 * @param map
	 * @return
	 * @remark create by sWX219697 2014-7-8 16:35:45 OR_huawei_201407_35_�����ն˽���EBUS���׶�_�����ز�ѯ 
	 */
    public ReturnWrap queryUserRegionReq(Map map)
	{
		// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
		if (IntMsgUtil.isTransEBUS("PTQueryLocation"))
		{
        	// ����Ϊ��ֵ�ԣ�����JSONObj
        	Map<String,Object> outParamMap = new HashMap<String,Object>();

        	// ƴװ����
        	outParamMap.put("regionName", "����");
        	outParamMap.put("provName", "ɽ��");
        	
        	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
    		
    		// ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
        	String[][] outParamKeyDefine = {{"regionName","provName"},{"cityname","provname"}};
        	
        	return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), outParamKeyDefine, null);
		}
		return super.queryUserRegionReq(map);
	}

    /**
     * PUK���ѯ
     * @param map
	 * @return
     * @remark create by wWX217192 2014-07-09 OR_huawei_201406_303 �����ն˽���EBUS���׶�_PUK���ѯ
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap queryPUK(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if(IntMsgUtil.isTransEBUS("BLCSQrySimPuk"))
    	{
    		try
    		{
    			String[] arrAttrsKey = new String[]{"iCol0", "iCol1", "iCol2", "iCol3"};
    			
    			return getIntMsgUtil().parseJsonResponse("[{\"iCol0\":\"PIN1\",\"iCol1\":\"PIN2\",\"iCol2\":\"PUK1\",\"iCol3\":\"PUK2\",\"iCol4\":\"ICCID\"},{\"iCol0\":\"1234\",\"iCol1\":\"\",\"iCol2\":\"01631028\",\"iCol3\":\"\",\"iCol4\":\"89860090179806837096\",\"iCol5\":\"460008689006290\",\"iCol6\":\"89860090179806837096\",\"iCol7\":\"13908686291\",\"iCol8\":\"\"}]", null, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
                rw.setReturnMsg("PUK���ѯ�쳣��");
                
                return rw;
    		}
    	}
    	
    	return super.queryPUK(map);
    }
    
    /**
     * ����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ
     * 
     * @param map �ӿ����
     * @return ReturnWrap ����ֵ
     * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 �����ն˽���EBUS���׶�_����
     */
    public ReturnWrap queryFeeMessage(Map map) 
    {
        // ����תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSQryRatePlane"))
        {
            String response = "{'desc':'���к�����ʾebus'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
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
    @Override
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        // ����תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSGetObjectTipsByNCode"))
        {
            String response = "[{'NCODE':'H01','TIPTYPE':'PCTIPNORMAL','OPERATORTYPE':'PCOpRec','TIPTEXT':'ebus������123'}]";
            
            ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, 
                new String[]{"NCODE","TIPTYPE","OPERATORTYPE","TIPTEXT"});
            
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
			String response = "{'abc':[{'OBJID':'Christmasmain','OBJTYPE':'Product','TIPTYPE':'PCTIPNORMAL','OPERTYPE':'PCOpRec','TIPTEXT':''}," +
					"{'OBJID':'Christ_bag1','OBJTYPE':'Product','TIPTYPE':'PCTIPNORMAL','OPERTYPE':'PCOpRec','TIPTEXT':''}]}";
			
			ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, new String[]{"OBJID","OBJTYPE","TIPTYPE","OPERTYPE","TIPTEXT"});
			if (SSReturnCode.SUCCESS == rw.getStatus())
			{
				Vector v = new Vector();
				v.add(new CTagSet());
				v.add(rw.getReturnObject());
				rw.setReturnObject(v);
			}
			return rw;
		}
		return super.qryObjectTips(paramMap, prods);
	}
	
	/**
     * ������ʷ��ѯ
     * @param map
	 * @return
	 * @remark create by wWX217192 OR_huawei_201407_39 �����ն˽���EBUS���׶�_������ʷ��ѯ 
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap qryAllServiceHistory(Map map)
    {
    	// ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
    	if(IntMsgUtil.isTransEBUS("BLCSQryReceptionSimple"))
    	{
    		try
    		{
    			String response = "[{\"accessType\":\"bsacHal\",\"accessTypeName\":\"CRMӪҵ��\",\"formNum\":\"999140108405842653\",\"messageInfo\":\"\",\"recDate\":\"2014-01-08 17:56:48\",\"recFee\":\"0\",\"recName\":\"������\",\"recNote\":\"\",\"recOpID\":\"10000000\",\"recOpName\":\"mxq\",\"recOrgID\":\"HB\",\"recOrgName\":\"\"},"
    				+ "{\"accessType\":\"bsacHal\",\"accessTypeName\":\"CRMӪҵ��\",\"formNum\":\"711140121405850883\",\"messageInfo\":\"\",\"recDate\":\"2014-01-21 10:09:36\",\"recFee\":\"0\",\"recName\":\"������\",\"recNote\":\"\",\"recOpID\":\"101\",\"recOpName\":\"ϵͳ����Ա\",\"recOrgID\":\"HB.EZ\",\"recOrgName\":\"����\"}]";
    		
    			return getIntMsgUtil().parseJsonResponse(response, null, 
    					new String[]{"recDate", "recOrgName", "recOpID", "recName"});
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
    			rw.setReturnMsg("������ʷ��ѯʧ�ܣ�");
    			
    			return rw;
    		}
    	}
    	return super.qryAllServiceHistory(map);
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
            String res = "{'DATALIST':[{'C0':'pkg.gt.lte.choose','C1':'B221546','C2':'',"
            	+ "'C3':'4G����88Ԫ�ײ�','C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221563','C2':'','C3':'4G����58Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221562','C2':'','C3':'4G����48Ԫ�ײ�'," 
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221565','C2':'','C3':'4G����408Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221564','C2':'','C3':'4G����328Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221566','C2':'','C3':'4G����28Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221548','C2':'','C3':'4G����238Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221547','C2':'','C3':'4G����168Ԫ�ײ�',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}]," 
            	+ "'MAX':'1','MIN':'1'}";
            return getIntMsgUtil().parseJsonResponse(res, new String[][]{{"MIN","MAX","DATALIST"},{"minprod","maxprod","DATALIST"}}, 
            		new String[]{"C0","C1","C2","C3","C4","C5","C6"});
        }
        return super.qrySubProds(msgHeader, nCode, type, optType); 
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
            String res = "[{'attrID':'Prod:MINCONSUMEFEE','attrType':'EDIT','defValue':'3',"
            	+ "'dicts':'','entityName':'�������','ifNeeds':'1','isShowInRec':'1',"
            	+ "'maxLen':'10','minLen':'0','objType':'Product','sepSign':'',"
            	+ "'serviceID':'pkg.prod.newbusi','valCount':'1','valueType':'INT32'}]";
            return getIntMsgUtil().parseJsonResponse(res, null, new String[]{"objected","attrID","entityName","attrType","valueType","minLen",
                		"maxLen","isNeed","isShowInRec","valCount","sepSign","defaultValue","dictVal",
                		"objType"});
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
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("BLCSMultiProductRec"))
        {
            String response = "{'recOid':'20140624162701','orderID':'20140624162701'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
        }
        
        return super.prodRec(msgHeader, multiProdCommitPO);
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
            String res = "{'subsID':'7110000001263','subsProdList':[{'NcodeID':'','applyDate':'2008-07-01 00:00:00',"
            	+ "'attr':'','cancelDate':'','createDate':'2008-07-01 00:00:00','description':'','donorSubsID':''," 
            	+ "'endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':'',"
            	+ "'prodID':'MP7110101095776','prodName':'����ȫ��ͨ�����ײ�68Ԫ','productClass':'class.pp.gt.st',"
            	+ "'productClassName':'��׼ȫ��ͨ����','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2008-07-01 00:00:00','attr':'','cancelDate':'','createDate':'2008-07-01 00:00:00',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'pkg.gt.st','privID':'','prodID':'B711110','prodName':'����ȫ��ͨ�����ײ�68Ԫ��ѡ��Ʒ'," 
            	+ "'productClass':'BASE','productClassName':'���ʷѲ�Ʒ','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2001-07-01 00:00:00','attr':'','cancelDate':'','createDate':'2001-07-01 00:00:00',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'prk.prod.cxyh','privID':'','prodID':'G17','prodName':'WAP����ҵ��'," 
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'�Żݰ�','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2005-11-30 15:12:27','attr':'','cancelDate':'','createDate':'2005-11-30 15:12:27',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1',"
            	+ "'pkgProdID':'pkg.prod.serv','privID':'','prodID':'G28','prodName':'�ƶ�������������(���ʷ�,��ͣ��) '," 
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'�Żݰ�','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2013-11-11 00:00:00'," 
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0',"
            	+ "'outNcodeType':'1','pkgProdID':'','privID':'','prodID':'150','prodName':'�ƶ���������ҵ��',"
            	+ "'productClass':'ProdClass_NewServ_Base','productClassName':'��������','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-03-01 00:00:00'," 
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0'," 
            	+ "'outNcodeType':'1','pkgProdID':'','privID':'','prodID':'pkg.prod.newbusi','prodName':'��ҵ�����Żݰ�',"
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'�Żݰ�','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-03-01 00:00:00','description':'',"
            	+ "'donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'',"
            	+ "'privID':'','prodID':'pkg.prod.zshf','prodName':'[��ѡ�Żݰ�27]�������ۿ۷�������','productClass':'ProdClass_Act_Zs',"
            	+ "'productClassName':'����ҵ��','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-09-15 19:28:45',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'','privID':'','prodID':'EZ_JFQL_001','prodName':'���ݻ��ֻ����','productClass':'rtScoreReward',"
            	+ "'productClassName':'','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-09-15 19:29:05','description':'',"
            	+ "'donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':''," 
            	+ "'prodID':'EZ_JFQL_001','prodName':'���ݻ��ֻ����','productClass':'rtScoreReward','productClassName':''," 
            	+ "'rltRefID':'','serviceRes':'','status':'1'},{'NcodeID':'','applyDate':'','attr':'','cancelDate':'',"
            	+ "'createDate':'2009-09-15 19:29:21','description':'','donorSubsID':'','endDate':'','formNum':''," 
            	+ "'ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':'','prodID':'EZ_JFQL_001',"
            	+ "'prodName':'���ݻ��ֻ����','productClass':'rtScoreReward','productClassName':'','rltRefID':'','serviceRes':'','status':'1'}]}";
            return getIntMsgUtil().parseJsonResponse(res, null, new String[]{"prodID", "prodName", "attr", "serviceRes", "applyDate", "createDate", "endDate", 
                        "status", "formNum", "productClass", "description", "donorSubsID", "rltRefID", 
                        "productClassName", "cancelDate", "pkgProdID", "outNcodeType", "privID", "NcodeID"});
        }
        return super.qryHasProds(msgHeader);
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
				String response = "{'-ncode':'SUBSNCODEEXIST','ACCESSTYPE':'INNER','BOSSCODE':'KJTX','BRAND':'BrandSzx'," 
        			+ "'BUSITYPE':'0','CALL_NUMBER':'15271716697','CURID':'KJTX','CURNAME':'��������','CUROUTPARAM':''," 
        			+ "'CUROUTPARAMALL':'','CUROUTPARAMNOREP':'','CURSTATUS':'1','EFFTYPE':'Type_Default','ERRNO':'0'," 
        			+ "'EXECUTECMD':'TRUE','HASPARAM':'0','INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISWRITEFILELOG':'0'," 
        			+ "'ISWRITETABLOG':'1','JOBDESC':'��������','JOBNAME':'��������','LINKNODE':'','LINKTYPE':'','MAINPRODID':'pp.eo.st'," 
        			+ "'MSISDN':'15271716697','NCODE':'KJTX','NEXTID':'KJTX','NEXTNAME':'��������','NEXTOUTPARAM':'','NEXTOUTPARAMALL':''," 
        			+ "'NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','OUTOPERID':'','OUTPARAMFORMAT':''," 
        			+ "'OUTPARAMSPLIT':'','PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1'," 
        			+ "'RETMSG':'�ѿ�ͨ','RETURN':'0','SENDORNOT':'0','STEP':'50','STYPE':'QRY','SUBSCREATEDATE':'2014-11-08 16:19:12'," 
        			+ "'SUBSID':'7119001087427','TELNUM':'15271716697','UNITID':'INNER','VNCODE':'vsmasingle','accessType':'bsacAtsv'," 
        			+ "'formnum':'','interfaceID':'IncProductSrv2','menuID':'recTestFunc2','operatorID':'101','region':'','reqSeq':'1'," 
        			+ "'reqTime':'20141118192306','routeType':'1','telNum':'15271716697','uniContact':'','uniContactFlag':''," 
        			+ "'unitID':'HUAWEI','virtualActorFlag':'1'}";
        		
        		// ����dubbo����
        		return getIntMsgUtil().parseJsonResponse(response, null, null);
        	}
			
		}
        catch (Exception e)
        {
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
        		String response = "{'-PARM':'','-effect_type':'','ACCESSTYPE':'INNER','ADDNCODE':'','ADD_ENDDATE':''," 
        			+ "'ADD_STARTDATE':'','BOSSCODE':'KJTX','BRAND':'BrandSzx','BUSITYPE':'0','CALL_NUMBER':'15271716697'," 
        			+ "'CURID':'KJTX','CURNAME':'��������','CURNCODE':'KJTX','DELNCODE':'KJTX','DEL_ENDDATE':'2014-12-01 00:00:00'," 
        			+ "'EFFTYPE':'Type_Default','EFF_DATE':'2014-12-01','EFF_DATETIME':'2014-12-01 00:00:00','END_DATE':'ZERO'," 
        			+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141118406525069','HASPARAM':'0','IMSI':'460022722015257'," 
        			+ "'INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISNEEDTHIRDCONF':'0','ISWRITEFILELOG':'0','ISWRITETABLOG':'1'," 
        			+ "'JOBDESC':'��������','JOBNAME':'��������','LINKNODE':'','LINKTYPE':'','MAINPRODID':'pp.eo.st','MSISDN':'15271716697'," 
        			+ "'NCODE':'KJTX','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','ORDERRESULT':'9900','ORI_NEXTATTRS':''," 
        			+ "'OUTOPERID':'','OUTPARAMFORMAT':'','OUTPARAMSPLIT':'','PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711'," 
        			+ "'RETCODE':'0','RETCONVERT':'1','RETMSG':'ȡ���������ѳɹ�','RETURN':'','SENDORNOT':'0','STEP':'70','STYPE':'DEL'," 
        			+ "'SUBORDERRESULT':'','SUBSCREATEDATE':'2014-11-08 16:19:12','SUBSID':'7119001087427','TELNUM':'15271716697'," 
        			+ "'TEMPLATENO':'P00200','TEMPLATEPARA':'1$��������&2$2014-12-01&3$ZERO','UNITID':'INNER','VNCODE':'vsmasingle'," 
        			+ "'YYWWFORMNUM':'10005406525067','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
        			+ "'menuID':'recTestFunc2','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141118192345','routeType':'1'," 
        			+ "'telNum':'15271716697','uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
        		
        		// ����dubbo����
        		ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, null);

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
     * @remark create by sWX219697 2015-3-27 18:01:22 
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
    		String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<QryPayRsp>" +
			"<HSNDUNS>754</HSNDUNS>" +
			"<BizOrderResult>0000</BizOrderResult>" +
			"<ResultDesc>��������</ResultDesc>" +
			"<QryPayInfo>" +
			"<CustName>����</CustName>" +
			"<PayAmount>1000000</PayAmount>" +
			"<Balance>100000</Balance>" +
			"</QryPayInfo>" +
			"</QryPayRsp>";
        	
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
     * @remark create by sWX219697 2015-4-2 09:58:49 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            // ����һ��boss�ӿڣ����н���
            String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            		"<PaymentRsp>" +
            		"<HSNDUNS>5310</HSNDUNS>" +
            		"<BizOrderResult>0000</BizOrderResult>" +
            		"</PaymentRsp>";
            
        	//��װ���ض���
        	ReturnWrap rw = new ReturnWrap();
        	
        	//�������ر���
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element resRoot = resDoc.getRootElement();
        	
    		//�ӿڵ��óɹ�
        	if(SSReturnCode.INTERBOSS_SUCCESS.equals(resRoot.element("BizOrderResult").getTextTrim()))
        	{
        		rw.setStatus(SSReturnCode.SUCCESS);
        		rw.setReturnMsg("���ѳɹ�");
        	}
        	else
        	{
        		rw.setStatus(SSReturnCode.ERROR);
        		rw.setReturnMsg(resRoot.element("ResultDesc").getTextTrim());
        	}
        	
        	return rw;
        }
        catch (Exception e)
        {
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ؽɷ��쳣��");
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
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //У����
        outParamMap.put("isElecCard", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * <�мۿ���ֵ>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param paramMap ��Ϣ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //У����
        outParamMap.put("chargeResult", "1");
        
        //��ֵ���
        outParamMap.put("countTotal", "100");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
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
		String response = "{transActionID:'1234567890978',crmFormNum:'54321678907',cardList:[" +
				"{cardNo:'111111111111',cardPwd:'1111111111111',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}," +
				"{cardNo:'222222222222',cardPwd:'2222222222222',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}," +
				"{cardNo:'333333333333',cardPwd:'3333333333333',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}]}";
        
        return getIntMsgUtil().parseJsonResponse(response, null, null);
	}
}
