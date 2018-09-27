package com.customize.sd.selfsvc.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ���ѳ�ֵ�ɷ�
 * @author xkf29026
 *
 */
// modify begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
public class FeeChargeBean  extends BaseBeanSDImpl
{
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @param bankNo �ɷѷ�ʽ
     * @param payDate �ɷ�ʱ��
     * @param acceptType ��������
 	 * @param chargeType �ɷ�����
     * @return
     */
    public Map<String, Object> qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType, String chargeType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        //���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("servnumber", servnumber);
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        //���õ�ǰ�˵�id
        paramMap.put("menuid", curMenuId);
        
        //�ɷѷ�ʽ
        paramMap.put("bankNo", bankNo);
        
        //�ɷ�����
        paramMap.put("payDate", payDate);
        
        //��������
        paramMap.put("acceptType", acceptType);
        
        //�ɷѷ�ʽ
        paramMap.put("chargeType", chargeType);
        
        //��ȡ���
        ReturnWrap rw = this.getSelfSvcCallSD().qryfeeChargeAccount(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // modify begin g00140516 2012/02/29 R003C12L03n01 �ӿڿ��ܷ���CTagSet��Ҳ���ܷ���Vector
            CTagSet v = null;
            
            Object obj = rw.getReturnObject();
            if (obj instanceof Vector)
            {
               v = (CTagSet) ((Vector) obj).get(0);
            }
            else if (obj instanceof CTagSet)
            {
                v = (CTagSet) obj;
            }
            // modify end g00140516 2012/02/29 R003C12L03n01 �ӿڿ��ܷ���CTagSet��Ҳ���ܷ���Vector
            
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            // modify begin fwx439896 2017-12-25  R005C20LG1701   OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            if(null!=v&&CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_FEE))
            {
                v=CommonUtil.lowerTagKey(v);
            }    
            // modify end fwx439896 2017-12-25 R005C20LG1701   OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
             // ���÷��ؽ��
             map.put("returnObj", v);

            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
    
    // modify begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
    /**
     * ���ѳ�ֵ�ɷ�
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param servnumber �ֻ�����
     * @param bankNo �ɷѷ�ʽ
     * @param payDate �ɷ�ʱ��
     * @param terminalSeq �ն����к�
     * @param money �ɷѽ��
     * @param acceptType ��������
     * @param invoiceType ��Ʊ����
     * @param bankSite
     * @param bankOper
     * @param chargeType �ɷ�����
     * @param regionFlag 0:�������ѣ���0�����ؽ���
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, 
    		String servnumber, String bankNo,
            String payDate, String terminalSeq, 
            String money, String acceptType, 
            String invoiceType, String bankSite,
            String bankOper, String chargeType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("bankNo", bankNo);
        paramMap.put("payDate", payDate);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("money", money);
        paramMap.put("acceptType", acceptType);
        paramMap.put("invoiceType", invoiceType);
        paramMap.put("bankSite", bankSite);
        paramMap.put("bankOper", bankOper);
        paramMap.put("touchoid", "");
        paramMap.put("chargeType", chargeType);
        
        //modify begin sWX219697 2014-7-17 09:57:56 OR_huawei_201406_1125_֧�ſ����ɷ�
        //��������
        paramMap.put("region", termInfo.getRegion());
        //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_֧�ſ����ɷ�
        
        ReturnWrap rw = this.getSelfSvcCallSD().chargeCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagSet = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            //add begin fwx439896 R005C20LG1701   OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            if(null!=tagSet&&CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHARGEFEE))
            {
                tagSet=CommonUtil.lowerTagKey(tagSet);
            }
            //add end fwx439896 R005C20LG1701   OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            //���÷��ؽ��
            map.put("returnObj", tagSet);
            
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
    // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
    
    /**
     * ��У�����룬ֱ�ӻ�ȡ�û���Ϣ
     * 
     * @param servnumber���ֻ�����
     * @param password����������
     * @param termInfo���ն˻���Ϣ
     * @return �û���Ϣ���������null��˵��������֤ʧ��
     */
    public Map<String,String> getUserStatus(String servnumber, String password, TerminalInfoPO termInfo)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnum", servnumber);
        paramMap.put("password", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().getUserStatus(paramMap);
        Map<String,String> map = new HashMap<String,String>();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet) rw.getReturnObject();
            
            map.put("status", cout.GetValue("status") == null ? "" : cout.GetValue("status"));
            map.put("servRegion", cout.GetValue("region") == null ? "" : cout.GetValue("region"));
            
            //add begin sWX219697 2014-7-19 11:57:22 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
            map.put("regionName", cout.GetValue("regionname") == null ? "" : cout.GetValue("regionname"));
            //add end sWX219697 2014-7-19 11:57:22 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
            return map;
        }
        
        return null;
    }
    
    /**
     * ���ѳ�ֵ�˻�Ӧ�ɷ��ò�ѯ
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @author hWX5316476
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_ɽ��_�����ն˽���Ӧ��������ʾ���Ż�����
     * @return
     */
    public Map<String, Object> qryRetcharge(TerminalInfoPO termInfo, String servnumber, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // ���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        // ���õ�ǰ�˵�id
        paramMap.put("curMenuid", curMenuId);
        
        // ���ÿͻ��ֻ���
        paramMap.put("servnumber", servnumber);
        
        // �ʺ�
        paramMap.put("acctid", "");
        
        // ��ѯ����    Ĭ��1��1�����ˣ�2����ͥ��3����
        paramMap.put("qrytype", "1");
        
        // ��ѯ����
        paramMap.put("cycle", "");
        
        // �ʵ�״̬ Ĭ��1��0��ָ�����ڲ�ѯ��1��û�н��ʵ��·ݵ������ʵ����ɷ�ʱ�ã�Ϊ1ʱ��cycle�������ã�
        paramMap.put("status", "1");
        
        // �˵����� Ĭ��1����1����������2����ţ���3�����������ˣ�
        paramMap.put("isbaddebt", "1");
        
        // ��������
        paramMap.put("accesstype", "bsacAtsv");
        
        // �����ֶΣ�Ĭ��Ϊ 0
        paramMap.put("unbilled", "");
        
        // ҵ����� -1,�������ɽ� 0,�ɷ� 1,��Ԥ���� 2,�������� 4,����Ԥ���� 5,���ʻ��� 6,���Ʊ�ɷ� 7,���˻���
        paramMap.put("processcode", "0");
        
        // �û���,��Ϊ�գ��������������ʡ��ʹ�ã�
        paramMap.put("subsid", "");
        
        // �Ƿ���Ҫ�����û���Ϣ""
        paramMap.put("isneedsubsinfo", "");
        
        // �Ƿ�Ҫ���ص����������
        paramMap.put("isneedleftmoney", "");
        
        //��ȡ���
        ReturnWrap rw = this.getSelfSvcCallSD().qryRetcharge(paramMap);
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet tagset = (CTagSet) rw.getReturnObject();
        	
        	// add begin lWX431760 2017-12-27 R005C20LG2401 DTS2017122704697
        	if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_RETCHARGE))
        	{
        	   tagset = CommonUtil.lowerTagKey(tagset);
        	}
        	// add end lWX431760 2017-12-27 R005C20LG2401 DTS2017122704697
        	
            String returnMsg = rw.getReturnMsg();
            String retcharge = tagset.GetValue("retcharge");
            //���÷��ؽ��
            map.put("returnObj", tagset);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // ����Ӧ������
            map.put("retcharge", retcharge);
            
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            String returnMsg = rw.getReturnMsg();
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    
    /**
     * ��ȡ��Ʊ��Ϣ
     * 
     * @param termInfo
     * @param curMenuId
     * @param servnumber
     * @param recoid
     * @return Ԥ�淢Ʊ��Ϣ
     * @remark create wWX217192 2014-07-22 
     */
    public Map<String, Object> invoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, String recoid)
    {
    	Map<String, String> paramMap = new HashMap<String, String>();
    	
    	paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        
        //������ˮ�� 
        paramMap.put("recoid", recoid);
        
        // ����Ϊ0����̨�ӿ�Э���ṩ
        paramMap.put("billCycle", "0");
        
        // �˺ţ�Ӫ����ȥ��ѯ�������ն˴�ֵΪ��
        paramMap.put("acctId", "");
        
        // ��ӡ���ͣ�1����Ʊ 0���վ�
        paramMap.put("invType", "1");
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
            Map<String, Object> map = new HashMap<String, Object>();
            
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
     * �������ɻ�ȡ�û���Ϣ
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @param bankNo �ɷѷ�ʽ
     * @param payDate �ɷ�ʱ��
     * @param acceptType ��������
 	 * @param chargeType �ɷ�����
     * @param chargelogoid ���ѳ�ֵ�ɷ���ˮ
     * @param regionFlag �Ƿ������1�����أ�0����ؽɷ�
     * @param tMoney �û�ʵ�ɽ��
     * @return 
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public CardChargeLogVO qryAcountMorePhone(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType, String chargeType, String chargelogoid, String tMoney)
    {
    	Map<String, Object> result = this.qryfeeChargeAccount(termInfo, servnumber, curMenuId, bankNo, payDate, acceptType, chargeType);
    	
    	CardChargeLogVO morePhone = new CardChargeLogVO();
        String region = ""; // �ֻ���������
        String accepttype = ""; // ��������
        String custname = ""; // �ͻ�����
        String shouldpay = ""; // Ӧ�ɷ���
        String bal = ""; // ���
        
        // �����ӿڷ�����Ϣ
    	if (result != null && result.size() > 0)
        {
    		CTagSet tagSet = (CTagSet)result.get("returnObj");
            
    		region = tagSet.GetValue("servregion");// ���������
            
            //������ʽ��regionFlagΪ0(��������)ʱ����������ΪZZYD��������أ�
    		accepttype = tagSet.GetValue("accept_type");
            
    		custname = tagSet.GetValue("cust_name"); // ƾ����ӡ�ͻ�����
            
            if (StringUtils.isEmpty(accepttype))
            {
                throw new ReceptionException("��ѯ�û�:"+ servnumber +"��������ʧ��");
            }
            
            // Ӧ�ɷ���
            if (StringUtils.isEmpty(tagSet.GetValue("sum_fee")))
            {
                // ���û������
                bal = tagSet.GetValue("balance");
            }
            else
            {
            	// ���ýӿڲ�ѯӦ�ɻ��ѽ��
                Map<String, Object> resultfee = this.qryRetcharge(termInfo, servnumber, curMenuId);
                
                if(null == resultfee || null == resultfee.get("retcharge"))
                {
                    throw new ReceptionException("��ѯ�û�:"+ servnumber +"Ӧ�ɻ��ѽ��ʧ��");
                }
                String retcharge = (String) resultfee.get("retcharge");
            	shouldpay = CommonUtil.fenToYuan(retcharge);
            }
            
            // �洢�û���Ϣ
            morePhone.setServRegion(region); // ��������
            morePhone.settMoney(tMoney); // �ɷѽ��
            morePhone.setBalance(bal); // ���
            morePhone.setServnumber(servnumber); // �ֻ�����
            morePhone.setYingjiaoFee(shouldpay); // Ӧ�ɷ���
            morePhone.setCustName(custname); // �ͻ�����
            morePhone.setAcceptType(accepttype); // ��������
            morePhone.setChargeLogOID(chargelogoid);// �ɷ���־��ˮ
            return morePhone;
        }
    	else
    	{
    		throw new ReceptionException("��ȡ�û�:" + servnumber + "��Ϣʧ�ܣ�");
    	}
    }
    
    /**
     * ���������ύ
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param morePhone ��������po
     * @param chargeType �ɷ�����
     * @return
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public CardChargeLogVO chargeCommitMorePhone(TerminalInfoPO termInfo, CardChargeLogVO morePhone, String curMenuId, String chargeType)
    {
    	CardChargeLogVO cardCharge = new CardChargeLogVO();
    	
    	// ɽ���ӿ� -- ��Ʊ��ӡ��־��0������ӡ 1����ӡ 2=����ӡ��Ʊ���Բ���
        String invoiceType = "2";
        Map resultMap = this.chargeCommit(termInfo, 
        		curMenuId, 
        		morePhone.getServnumber(), 
        		termInfo.getBankno(), 
        		CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"), 
        		morePhone.getChargeLogOID(), 
        		morePhone.gettMoney(), 
        		morePhone.getAcceptType(), 
        		invoiceType, "", "", 
        		chargeType);
        
        // ����������Ϣ
        if (resultMap != null && resultMap.size() > 1)
        {
        	CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            
        	if (null != tagSet && !tagSet.isEmpty())
            {
                String dealNum = (String)tagSet.GetValue("bill_nbr");// �������
                
                // ������ˮ��Ԥ�淢Ʊ��ӡʹ��
                String recoid = (String)tagSet.GetValue("recoid");
                
                cardCharge.setRecoid(recoid); 
                cardCharge.setDealnum(dealNum);
                cardCharge.setCustName((String)tagSet.GetValue("cust_name"));
            }
                
        	cardCharge.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        	cardCharge.setStatus(Constants.CHARGE_SUCCESS);
        	cardCharge.setDescription("���������ѳɹ���");
            
            // �ѽɷ�״̬��Ϊ�ɹ�
            cardCharge.setChargeStatus("0");
        }
        else
        {
        	String errorMsg = "";
            if (resultMap != null)
            {
            	errorMsg = (String) resultMap.get("returnMsg");
            }
            errorMsg = CommonUtil.isEmpty(errorMsg) ? "�������ۿ�ɹ������ǽ���ʧ�ܣ�" : errorMsg;
            
            cardCharge.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            cardCharge.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            cardCharge.setDescription(errorMsg);
            cardCharge.setDealnum("");
            
            // �ѽɷ�״̬��Ϊʧ��
            cardCharge.setChargeStatus("1");
        }
        
        // �ɷ���־��ˮ
        cardCharge.setChargeLogOID(morePhone.getChargeLogOID());
        
        // region
        cardCharge.setRegion(termInfo.getRegion());
        
        // ��֯����
        cardCharge.setOrgID(termInfo.getOrgid());
        return cardCharge;
    }
}