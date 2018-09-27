/*
 * �� �� ��:  BindBankCardAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <���ܰ����п�>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 13, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
 */
package com.customize.sd.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.BindBankCardBean;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.customize.sd.selfsvc.serviceinfo.service.BindBankCardService;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;

/**
 * <���ܰ����п�>
 * <������ϸ����>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66 
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BindBankCardAction extends BaseAction 
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��־
     */
    private static Log logger = LogFactory.getLog(BindBankCardAction.class);
    
    /**
     * ���ܰ����п�bean
     */
    private BindBankCardBean bindBankCardBean;
    
    /**
     * �û���¼Bean
     */
    private UserLoginBean userLoginBean;
    
    /**
     * ���п�����ϢPO
     */
    private BindBankCardPO bindBankCardPO = new BindBankCardPO();
    
    /**
     * ��
     */
    private String expire1 = "";
    
    /**
     * ��
     */
    private String expire2 = "";
    
    /**
     * ��ǰ�˵�����
     */
    private String curMenuId = "";
    
    /**
     * �쳣��Ϣ
     */
    private String errorMessage = "";
    
    /**
     * 
     */
    private String confirmUnbind = "";
    
    /**
     * ���������
     */
    private String randomPwd;
    
    /**
     * �������������Ĵ������
     */
    private String randomPwdErrTimes;
    
    // Add begin wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
    /**
     * 0: �󸶷� 1: Ԥ���� 9: ��ѯ�󶨵��û�������
     */
    private String payTypeFlag = "";
    
    /**
     * ��֤������
     */
    private String smsType;
    
    /**
     * ������֤��
     */
    private String smsCode;
    
    /**
     * �Ͱ��ύ��־ 1��ǩԼ�ύ��2����Լ�ύ
     */
    private String heBaoCommitFlag;
    
    /**
     * ҵ�����ɹ������ʾ��Ϣ
     */
    private String successMessage;
    
    /**
     * ��ѯ���п���ϢService
     */
    private transient BindBankCardService bindBankCardService;
    
    /**
     * ���п���Ϣ�б�
     */
    private List<BankCardInfoPO> cardInfoList = new ArrayList<BankCardInfoPO>();
    
    /**
     * �û���Ϣչʾҳ����ܰ��ʾ
     */
    private String userInfoTips = "";
    
    /**
     * �Զ����ѷ�ֵ��Ϣ
     */
    private List<DictItemPO> balanceList;
    
    /**
     * �Զ����ѳ�ֵ���
     */
    private List<DictItemPO> chargeList;
    
    /**
     * ѡ�������п���Ϣ
     */
    private BankCardInfoPO bankCardInfoPO = new BankCardInfoPO();
    
    /**
     * �����Զ����ѵ�����
     */
    private String oprType;
    
    /**
     * ������ֵ
     */
    private String trigAmt;
    
    /**
     * ���۽��
     */
    private String drawAmt;
    
    /**
     * ����ҳ����ܰ��ʾ��Ϣ
     */
    private String HBManageTips;
    
    /**
     * ��¼ҳ������п�����
     */
    private String cardNo;
    
    /**
     * ��¼ҳ����ֻ�����
     */
    private String servnumber;
    
    /**
     * ��¼ҳ���������
     */
    private String password;
    // Add end wWX217192 2014-11-28  R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
    
    //add begin sWX219697 2014-12-5 09:44:10 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
    /**
     * �������б�
     */
    private List<BankCardInfoPO> viceNumList;
    
    /**
     * �û��Ѱ󶨵ĸ����봮����ʽ��13911111111~13811111111
     */
    private String oldViceNumber;
    
    /**
     * �׳�ֵ���������ܰ��ʾ�ֵ���
     */
    private List<DictItemPO> easyPayTipList;
    //add end sWX219697 2014-12-5 09:44:10 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
    
    /**
     * <У���û��Ƿ�Ϊ��ʵ��Ϣ����>
     * <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String checkLoginUserIsFactUser() throws Exception
    {
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
        // ���򵽴���ҳ��
        String forward = "checkError";
        
        userInfoTips = (String)PublicCache.getInstance().getCachedData(Constants.SH_HBUSERINFO_TIPS);
        
        // ����ҳ���ֻ������ǿͻ���¼���ֻ�����
        bindBankCardPO.setTelNum(customer.getServNumber());
        
        // ��ѯ�û������п���Ϣ
        Map<String, String> bindCardMap = null;
        
        // modify begin wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
        // ��ѯ�Ͱ�����Ϣ
        Map<String, Object> heBaoMap = new HashMap<String, Object>();
        
        // У���û��Ƿ�����ʵ��ݵ�¼
        Map retMap = bindBankCardBean.checkIsFactNameRegist(terminalInfoPO, customer, curMenuId);
        
        try
        {
        	// �׳�ֵǩԼ��ϵ��ѯ
            bindCardMap = bindBankCardBean.queryBindInfo(bindBankCardPO);
        }
        catch(Exception e)
        {
            // ����������־
        	this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", "�׳�ֵǩԼ��ϵ��ѯʧ�ܣ����Ժ�����!");
            this.getRequest().setAttribute("errormessage", "�׳�ֵǩԼ��ϵ��ѯʧ�ܣ����Ժ�����!");
            return forward;
        }
        
        // �׳�ֵ�Ѿ��󶨣�����ת������Ϣ��ʾҳ��
        // 1:��ǩԼ 0:�ѽ�Լ ��δǩԼ
        if ("1".equals(bindCardMap.get("STATUS")))
        {
        	//modify by sWX219697 2014-12-15 11:24:11 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
        	//�׳�ֵ����ҳ�濪�أ�1����������ת���׳�ֵ����ҳ�档��1���رգ���ת�����ҳ��
            if ("1".equals(CommonUtil.getParamValue(Constants.SH_EASYPAYMNG_SWITCH)))
            {
            	String bankCardNum = bindCardMap.get("CARDNO");
            	
            	//չʾ���п��ź���λ
            	bankCardInfoPO.setBankCardNum(bankCardNum.substring(bankCardNum.length()-4));
            	
                forward = easyPayMng();
            }
            
            //�׳�ֵ����ҳ��رգ���ת�����ҳ��
            else
            {
                //���������Ϣ
                errorMessage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_BINDTIP);
                this.getRequest().setAttribute("errormessage", errorMessage);
                
                // ת�����ҳ��
                forward = "unBindCard";
            }
            //modify end sWX219697 2014-12-15 11:29:26 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������

            return forward;
        }
        
    	// �Ͱ��׳�ֵǩԼ��ϵ��ѯ
        heBaoMap = bindBankCardBean.checkHeBao(terminalInfoPO, customer, curMenuId, cardNo);
        
        // �Ͱ��׳�ֵ�Ѱ�
        // 0: ��ǩԼ   ��0��δǩԼ����ʧЧ �˴������߼���Bean��
        if(heBaoMap.containsKey("returnObj"))
        {
        	// �û�Ϊ��ʵ��ݵ�¼
            if(null != retMap)
            {
                CTagSet ctagset = (CTagSet)retMap.get("returnObj");
                
                // ���֤����
                bindBankCardPO.setIdCardNum((String)ctagset.get("certid"));
                
                // ����
                bindBankCardPO.setUserFactName((String)ctagset.get("custname"));	
            }
        	return manageHBEasyPay(heBaoMap);
        }
        // �Ͱ��׳�ֵ��ѯǩԼ��ϵ�ӿڵ���ʱ�쳣����
        else if(SSReturnCode.ERROR == (Integer)heBaoMap.get("status"))
        {
        	this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", (String)heBaoMap.get("errMsg"));
        	this.getRequest().setAttribute("errormessage", heBaoMap.get("errMsg"));
        	return forward;
        }
        // modify end wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
        
        //�û�û�а�
        // �����б�
        List<DictItemPO> dictItemList = getDictItemByGrp("easyPayChange");   
        
        // �û�Ϊ��ʵ��ݵ�¼
        if(null != retMap)
        {
            CTagSet ctagset = (CTagSet)retMap.get("returnObj");
            
            // ֤������
            bindBankCardPO.setIdCardType((String)ctagset.get("certtypeid"));
            
            // ���������б�
            for(DictItemPO dictItemPO : dictItemList)
            {
                if (dictItemPO.getDictid().equals(bindBankCardPO.getIdCardType()))
                {
                    // ֤������_�˳���
                    bindBankCardPO.setId_type(dictItemPO.getDictname().split(":")[0]);
                    
                    // ֤������_��ʾ��
                    bindBankCardPO.setIdCardTypeText(dictItemPO.getDictname().split(":")[1]);
                    
                    break;
                }
            }
            
            // ֤������
            bindBankCardPO.setIdCardNum((String)ctagset.get("certid"));
            
            // ����
            bindBankCardPO.setUserFactName((String)ctagset.get("custname"));
        }
//            bindBankCardPO.setUserFactName("����");
//            bindBankCardPO.setIdCardNum("111111");
//            bindBankCardPO.setIdCardType("���֤");
        forward = "checkSuccess";
        return forward;
    }
    
    /**
     * �Ͱ��׳�ֵ����
     * @param heBaoMap
     * @return
     * @remark create by wWX217192 2014-12-12 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    private String manageHBEasyPay(Map<String, Object> heBaoMap)
    {
    	// ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
    	// ����ҳ����ܰ��ʾ��Ϣ
    	HBManageTips = (String)PublicCache.getInstance().getCachedData(Constants.SH_HBUSERINFO_TIPS);
    	
    	CTagSet set = (CTagSet) heBaoMap.get("returnObj");
    	
    	// ���п����ź�4λ
    	bindBankCardPO.setBankCardNum(set.GetValue("cardNo"));
    	
    	// ���֧��Э���
    	bindBankCardPO.setAGRNO(set.GetValue("AGRNO"));
    	
    	// ���п�����
    	bindBankCardPO.setBankCardType(set.GetValue("cardType"));
    	
    	// ���д��� Ϊ�Զ��������ù���׼������
    	bankCardInfoPO.setBankId(set.GetValue("BANKABBR"));
    	
    	// ���д��룬Ϊ���Ͷ��������ʹ��
    	bindBankCardPO.setBankAbbr(set.GetValue("BANKABBR"));
    	
		try
		{
			Map<String, String> payTypeMap = new HashMap<String, String>();
			
			// �������Ͳ�ѯ�ӿڵ���
			payTypeMap = bindBankCardBean.qrySubsPrepayType(customer, terminalInfoPO, curMenuId);
			
			// �󸶷�
			if("0".equals(payTypeMap.get("payType")))
			{
				payTypeFlag = "0";
			}
			// Ԥ����
			else if("1".equals(payTypeMap.get("payType")))
			{
				payTypeFlag = "1";
				
				// ��ʹ�������б�
				balanceList = getDictItemByGrp("EasyPayAutoBalance");
				
				// ��ͳ�ֵ����б�
				chargeList = getDictItemByGrp("EasyPayAutoChargeValue");
			}
		}
		catch(Exception e)
		{
			// ����������־
			this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", e.getMessage());
			this.getRequest().setAttribute("errormessage", e.getMessage());
			return "checkError";
		}
		
    	return "heBaoManage";
    }
    
    /**
     * ת����Ϣ�Ǽǽ���
     * 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create xKF69944 Aug 21, 2013[��������]
     */
    public String writeBankCardInfo()
    {
        //"0"Ϊ���п���"1"Ϊ���ÿ�
        String bankCardType = bindBankCardPO.getBankCardType();
        
        // ��ȡ�����ն�ϵͳ���õ����п���Ϣ
        cardInfoList = bindBankCardService.getBankInfoList(bankCardType);
        
        if("0".equals(bankCardType))
        {
            return "debitCard";
        }
        else
        {
            return "creditCard";
        }
    }
    
    /**
     *���ܰ�ģʽ�ύ
     * 
     * @return
     * @throws Exception 
     * @see [�ࡢ��#��������#��Ա]
     */
    public String noEncryptBindComit() throws Exception
    {
        // modify begin by zKF69263 2014-6-5 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // У������Ķ��������
        String resultMsg = this.loginWithRandomPwd(customer.getServNumber(), getRandomPwd());
        
        // ���ز���"1"��ʾ���Ͷ��������ʧ��
        if (!"1".equals(resultMsg))
        {
            // У�������������������
            return checkInputTimes(customer.getServNumber(), resultMsg);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("�û�" + customer.getServNumber() + "ʹ�ö������������������֤�ɹ�");
        }
        // modify end by zKF69263 2014-6-5 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
        
        // ��Ч��
        bindBankCardPO.setExpire(expire2 + expire1);
        
        // �ƶ��ֻ���
        bindBankCardPO.setTelNum(customer.getServNumber());
        
        // modify begin by hWX5316476 2014-2-11  OR_SD_201402_389�׳�ֵ�����ն˰�ʱ��ֹҵ���ж��������Ż�����
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �����׳�ֵǩԼ֮ǰ���ýӿڲ鿴���û��Ƿ���Կ�ͨ�ò�Ʒ
        ReturnWrap result = bindBankCardBean.checkProCondition(customer, termInfo, "ZLWGQY", "ADD", "", "","0","EXECUTECMD", curMenuId);
       
        // ���ݷ��ر��ķ����Ƿ���Կ�ͨ�ò�Ʒ��������óɹ������ʾ���Կ�ͨ�ò�Ʒ�����򲻷��Ͽ�ͨ�ò�Ʒ������
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if(result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if(result.getStatus() == SSReturnCode.SUCCESS)
        {
            // ���ܰ�ģʽ�ύ
            Map<String, String> retMap = null;
            
            try
            {
                retMap = bindBankCardBean.noEncrptyBindComit(bindBankCardPO);
            }
            catch(Exception e)
            {
                logger.info(e.getMessage());
                // ����������־
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", "�׳�ֵǩԼʧ�ܣ���·�������Ժ�����!");
                this.getRequest().setAttribute("errormessage", "�׳�ֵǩԼʧ�ܣ���·�������Ժ�����!");
                return "bindError";
            }
            
            //add begin sWX219697 2014-12-22 14:44:56 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
            //���������󣬺󸶷��û��Զ���ͨ�Զ����ѹ���
            try 
            {
				Map<String, String> returnMap = null;
				
				//��ѯ�û���������
				returnMap = bindBankCardBean.qrySubsPrepayType(customer, termInfo, curMenuId);
				
				//�󸶷��û��Զ���ͨ�Զ�����
				if("0".equals(returnMap.get("payType")))
				{
					//��ͨ�Զ����ѹ��ܣ��������ͣ�1
					bindBankCardBean.bindAutoFeeSet(customer, termInfo, curMenuId, "1", "", "");
				}
			} 
            
            //����ͨ�Զ�����ʧ�ܣ�ֻ��Ҫ��¼������Ϣ������Ҫ��ת����ҳ�棬�û��ɺ����ٿ�ͨ
            catch (ReceptionException e) 
            {
				logger.error("�����������Զ���ͨ�Զ�����ʧ��", e);
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
			}
            //add end sWX219697 2014-12-22 14:55:53 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
            
            //�󶨳ɹ�
            if("000".equals(retMap.get("rtn_code")))
            {
                bindBankCardPO.setAppFlowCode(retMap.get("pg_order_code"));
                
                // �����ɹ���־
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "�׳�ֵǩԼ�ɹ�!");
                this.getRequest().setAttribute("errormessage", "�׳�ֵǩԼ�ɹ�!");
                return "bindSuccess";
            }
            //��ʧ��
            else
            {
                String rtn_msg = "�׳�ֵǩԼʧ��!";
                
                // ��ʾǩԼʧ�ܵľ���ԭ��
                if (retMap.get("rtn_msg") != null && !"".equals(retMap.get("rtn_msg")))
                {
                    rtn_msg = retMap.get("rtn_msg");
                }
                
                // modify begin by zKF69263 2014-6-6 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
                // ��Ϣת���б�
                List<DictItemPO> dictItemList = getDictItemByGrp("easyPayMsgChg");
                
                if (dictItemList != null)
                {
                    // �����б�
                    for(DictItemPO dictItemPO : dictItemList)
                    {
                        if (dictItemPO.getDictid().equals(retMap.get("rtn_code")))
                        {
                            rtn_msg = dictItemPO.getDescription();
                            
                            break;
                        }
                    }
                }
                // modify end by zKF69263 2014-6-6 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
                
                // ����������־
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
                this.getRequest().setAttribute("errormessage", rtn_msg);
                return "bindError";
            }
        }
        else
        {
            // ����������־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", result.getReturnMsg());
            
            // �ڴ���ҳ����ʾ����ԭ��
            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
            
            return "bindError";
        }
        // modify end by hWX5316476 2014-2-11 OR_SD_201402_389�׳�ֵ�����ն˰�ʱ��ֹҵ���ж��������Ż�����
    }

    /**
     * ���п����
     * 
     * @return
     * @throws Exception
     * @remark
     * @modify yWX163692 2013��11��19�� OR_SD_201309_940 �׳�ֵ���׶Σ���Լ�����Զ������ж�����  
     */
    public String unBindCommit()
    {
        // ������Ϣ
        Map<String, String> retMap = null;
       
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomerSimp();
       
	    // δ���û�ȷ�ϣ����ж��û����Զ��ɷ�����
        if("0".equals(confirmUnbind))
	    {
	        try
	        {
	        	//modify by sWX219697 2014-12-2 15:25:04 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	        	//��ѯ�û��Ƿ�ͨ�Զ����ѹ��� �������ͣ�0
	        	retMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
	        	
	        	//�ѿ�ͨ�Զ����ѹ���
	        	if("0".equals(retMap.get("autoStatus")))
	        	{
		        	 //���������Ϣ
		            errorMessage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_UNBINDTIP);
		            this.getRequest().setAttribute("errormessage", errorMessage);
		            
		            // ת�����ҳ��
		            return "unBindTip";
	        	}
	        }
	        
	        //�ӿڲ�ѯʧ��
	        catch(ReceptionException e)
	        {
	        	// ����������־
	            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
	        	logger.error("��ѯ�û��Ƿ�ͨ�Զ����ѹ��ܣ�", e);
	        	
	            //ת�����ҳ��
	            return "unBindError";
	        }
	    }
        else
        {
        	//modify by sWX219697 2014-12-2 15:25:51 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
		    // ȷ�Ϲر��Զ�����
	        try
	        {
	        	//�ر��Զ����ѹ��ܣ��������ͣ�2
	        	bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "2", "", "");
	        }
	        // ��¼��־
	        catch(ReceptionException ex)
	        {
		       	// ����������־
		        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", ex.getMessage());
		           
		        //���ô�����Ϣ��ʾ
		        this.getRequest().setAttribute("errormessage", ex.getMessage());
		           
		        //ת�����ҳ��
		        return "unBindError";
	        }
        }
        
        try
        {
            // �����˳���󶨽ӿ�
            retMap = bindBankCardBean.unBindBankCard(bindBankCardPO);
        }
        catch (Exception e)
        {
            // ������ʾ��Ϣ
            String rtn_msg = "���п����ʧ�ܣ�����ԭ�򣺵��������ӿ�ʧ�ܣ�";
            
            // ����������־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
            
            //���ô�����Ϣ��ʾ
            this.getRequest().setAttribute("errormessage", rtn_msg);
            
            //ת�����ҳ��
            return "unBindError";
        }
        
        if ("0".equals(retMap.get("STATUS")))
        {
            String rtn_msg = "�Բ��𣬽�����п�ʧ��ʧ��ԭ��" + retMap.get("RETMSG");
            
            // ����������־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
            
            //���ô�����Ϣ��ʾ
            this.getRequest().setAttribute("errormessage", rtn_msg);
            
            //ת�����ҳ��
            return "unBindError";
        }
        else
        {
            // �����ɹ���־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "�𾴵�" + bindBankCardPO.getTelNum() + "�û�,�����п���󶨳ɹ���");
            
            // ����ҳ�淵����Ϣ
            this.getRequest().setAttribute("errormessage", "�𾴵�" + bindBankCardPO.getTelNum() + "�û�,�����п���󶨳ɹ���");
            
            //����
            return "unBindSuccess";
        }
    }
    
    /** 
     * ���Ͷ��������
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
     * @remark modify by wWX217192 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     * @reson �û������׳�ֵ��ʱ�������ն�ϵͳ���ձ��������׳�ֵ��Ͱ��׳�ֵ�ķ��䣬�ʽ�������public��Ϊprivate����
     */
    private String sendRandomPwd() 
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd Starting ...");
        }
        
        String forward = "error";
        
        // ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �����׳�ֵǩԼ֮ǰ���ýӿڲ鿴���û��Ƿ���Կ�ͨ�ò�Ʒ
        ReturnWrap result = bindBankCardBean.checkProCondition(customerSimp, termInfo, "ZLWGQY", "ADD", "", "","0","EXECUTECMD", curMenuId);
       
        // ���ݷ��ر��ķ����Ƿ���Կ�ͨ�ò�Ʒ��������óɹ������ʾ���Կ�ͨ�ò�Ʒ�����򲻷��Ͽ�ͨ�ò�Ʒ������
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            // �����������
            String randomPwd = createRandomPassword(customerSimp.getServNumber(), CommonUtil.getCurrentTime());
            
            // ��ȡ���������������
            String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            
            // �滻������������
            if (StringUtils.isNotEmpty(shortMessage))
            {
                shortMessage = shortMessage.replace("[%1]", randomPwd);
            }
            
            // ���Ͷ��������
            if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
            {
                logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
                
                // ����������־
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
                
                this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("���û�" + customerSimp.getServNumber() + "�������������ųɹ��������" + randomPwd);
                    logger.info("�������ݣ�" + shortMessage);
                }
                
                forward = "success";
            }
        }
        else
        {
            // ����������־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", result.getReturnMsg());
            
            // �ڴ���ҳ����ʾ����ԭ��
            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd End");
        }
        
        return forward;
    } 
    
    /** 
     * �������֤��
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
     */
    public String inputCertid()
    {
        return "inputCertid";
    }
    
    /** 
     * ��ʾ�û���Ϣ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
     */
    public String showUserInfo()
    {
        return "userInfo";
    }
    
    /** 
     * У�������������������
     * 
     * @param servNumber
     * @param resultMsg
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_SD_201404_563 ���ڶ��׳�ֵ��ȫ��֤��ҳ������Ż�����������
     */
    private String checkInputTimes(String servNumber, String resultMsg)
    {
        // �Ӳ���������������������������������
        if (StringUtils.isEmpty(randomPwdErrTimes))
        {
            randomPwdErrTimes = (String)PublicCache.getInstance().getCachedData(Constants.SH_SENDRANDOMERR_MAXTIMES);
        }
        
        // Ĭ�Ͽ��������������
        if (StringUtils.isEmpty(randomPwdErrTimes))
        {
            randomPwdErrTimes = "5";
        }
        
        // ���������1
        randomPwdErrTimes = String.valueOf(Integer.parseInt(randomPwdErrTimes) - 1);
        
        String errorMsg = "";
        String forward = "error";
        
        if ("-1".equals(resultMsg))
        {
            errorMsg = "������Ķ��������Ѿ���ʱ���뷵�����Ի��߽�������������";
        }
        else if (Integer.parseInt(randomPwdErrTimes) == 0)
        {
            errorMsg = "����������������Ѵﵽ���������������Ժ����ԡ�";
        }
        else 
        {
            errorMsg = "��������������󣬻�ʣ"+Integer.parseInt(randomPwdErrTimes)+"�λ��ᣬ���������롣";
            forward = "validRandomError";
        }
        
        logger.error("�׳�ֵ���п�ǩԼ�󶨣��û�" + servNumber + "����������������ʱ����֤ʧ��");
        
        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", errorMsg);
        
        this.getRequest().setAttribute("errormessage", errorMsg);
        
        return forward;
    }

    
    /**
     * �Ͱ��׳�ֵ���Ͷ�����֤��ӿ�
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String sendHeBaoRandom()
    {
    	// ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        bindBankCardPO.setUserFactName(customer.getCustomerName());
        
        // ���ö�����֤�뷢�ͽӿ�
        Map<String, String> resultMap = new HashMap<String, String>();
        
        if(null == bindBankCardPO.getBankCardNum() || "".equals(bindBankCardPO.getBankCardNum()) )
        {
        	bindBankCardPO.setBankCardNum(cardNo);
        }
        
        resultMap =	bindBankCardBean.sendHeBaoRandom(termInfo, customer, curMenuId, smsType, bindBankCardPO);
        
        // ���ӿڵ��óɹ����ҷ��ؽ�����ˮ�ţ�ϵͳǨ�������������֤��ҳ��
        if(resultMap.containsKey("tradeNo"))
        {
        	bindBankCardPO.setAppFlowCode(resultMap.get("tradeNo"));
        	
        	return "randomPwd";
        }
        // ���ӿڵ���ʧ�ܣ���¼������־����ϵͳ����
        else
        {
        	// ����������־
        	this.createRecLog(Constants.SH_HEBAO_SENDRANDOMPWD, "0", "0", "1", resultMap.get("retMsg"));
        	this.getRequest().setAttribute("errormessage", resultMap.get("retMsg"));
        	return "error";
        }
    }
    
    /**
     * �Ͱ��׳�ֵǩԼ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String signHeBaoCommit()
    {
    	// ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        Map<String, String> resultMap = bindBankCardBean.signHeBao(termInfo, customer, curMenuId, smsCode, bindBankCardPO);
        
        if(resultMap.containsKey("AGRNO"))
        {
        	bindBankCardPO.setAGRNO(resultMap.get("AGRNO"));
        	
        	setSuccessMessage("�Ͱ��׳�ֵǩԼ�ɹ�!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "�Ͱ��׳�ֵǩԼ�ɹ�!");
        	
        	return "signSuccess";
        }
        else
        {
        	// ����������־
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", resultMap.get("retMsg"));
        	this.getRequest().setAttribute("errormessage", resultMap.get("retMsg"));
        	return "signError";
        }
    }
    
    /**
     * ͨ�������ѡ��ǰ�׳�ֵ����ʽΪ����ֱ�����ǺͰ��׳�ֵ
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String getBindBankType()
    {
    	int random = 0;
    	Random r = new Random();
    	random = r.nextInt(100) + 1;
    	 
    	// ��ȡ�׳�ֵ���Ͱ��׳�ֵ�ı���
    	String percentage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASTPAY_PERCENTAGE);
    	 
    	// �����׳�ֵ��ռ����������ֵ
    	int easyPayNum = Integer.valueOf(percentage.split(":")[0]) * 10;
    	
    	// �������С��easyPayNumʱ�������׳�ֵ����ʽ
    	if(random < easyPayNum)
    	{
    		return sendRandomPwd();
    	}
    	// �����������easyPayNumʱ�����úͰ��׳�ֵ�ķ�ʽ
    	else
    	{
    		return sendHeBaoRandom();
    	}
    }
    
    /**
     * �Ͱ��׳�ֵ��Լ
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String unsignHeBao()
    {
    	// ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
        	bindBankCardBean.unsignHeBao(termInfo, customer, curMenuId, smsCode, bindBankCardPO);
        	
        	setSuccessMessage("�Ͱ��׳�ֵ��Լ�ɹ�!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "�Ͱ��׳�ֵ��Լ�ɹ�!");
        	return SUCCESS;
        }
        catch(ReceptionException e)
        {
        	// ����������־
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", "�Ͱ��׳�ֵ��Լʧ��!");
        	this.getRequest().setAttribute("errormessage", e.getMessage());
        	
        	return "error";
        }
    }
    
    /**
     * �Ͱ��׳�ֵ�Զ���������
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String setHeBaoAutoValue()
    {
    	// ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
        	bindBankCardBean.setHeBaoAutoValue(termInfo, customer, curMenuId, oprType, bankCardInfoPO);
        	
        	setSuccessMessage("�Զ��������óɹ�!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "�Զ��������óɹ�!");
        	return SUCCESS;
        }
        catch(Exception e)
        {
        	// ����������־
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", "�Զ��������óɹ�!");
        	this.getRequest().setAttribute("errormessage", e.getMessage());
        	
        	return "error";
        }
    }
    
    /**
     * ��¼ҳ����ת
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String inputNumAndCardNo()
    {
    	// �����ǰ��¼session
		if (getCustomerSimp() != null)
		{
			//���session
            this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
		}
		
    	return SUCCESS;
    }
    
    /**
     * �׳�ֵǩԼ��¼��д
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public String loginWithEasyPay()
    {
    	// ȡ�õ�ǰSession
        HttpSession session = this.getRequest().getSession();
        
    	// ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String newpwd = password;
        
        try
        {
            DESedeEncrypt encrypt = new DESedeEncrypt();
            newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
        }
        catch (Exception e)
        {
            logger.error("ɽ��������֤ʱ�������������", e);
        }
    	
        Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
    	
    	// 100:�ɹ� 102:ȱʡ���� 119:��ʼ����
    	if (returnMap != null && "100".equals(String.valueOf(returnMap.get("errcode"))))// 100:�ɹ� 
    	{
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ�÷���������������֤�ɹ�");
            }
            Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
            
            if (customerSimpMap.get("customerSimp") != null)
	        {
            	// ȡ��¼�û���Ϣ
	            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
	                  
            	// ����session��Ϣ
	            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	            if (oldCustomerSimp == null)
	            {
	                session.setAttribute(Constants.USER_INFO, customerSimp);
	            }
	            else
	            {
	                //����û���Ϣ
	                session.removeAttribute(Constants.USER_INFO);
	                
	                //���µ��û���Ϣ�����session��
	                session.setAttribute(Constants.USER_INFO, customerSimp);
	            }
	            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷���������������֤�ɹ�");
        	}
            
            return SUCCESS;
    	}
    	else
    	{
    		this.getRequest().setAttribute("errormessage", "ʹ�÷������������֤ʧ�ܣ�����������!");
    		this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "1", "ʹ�÷���������������֤ʧ��!");
    		return "failed";
    	}
    }
    
    /**
     * <�׳�ֵǩԼ����>
     * <1����ѯ�û��������͡�2����ѯ�û��Ƿ�ͨ�Զ���ֵ���ܡ�3����ѯ�û��ĸ������б�>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-12-5 09:43:20 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż������� 
     */
    private String easyPayMng()
    {
    	String result = ERROR;
    	
    	Map<String,String> resMap = null;
    	
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomerSimp();
        
        //��������ֵ���
        balanceList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAY_FZ);
        
        //���۽���ֵ���
        chargeList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAY_HK);
        
        //��ܰ��ʾ
        easyPayTipList = getDictItemByGrp(Constants.SH_EASYPAY_MNG_TIP);
    	
    	try 
    	{
            //��ѯ�û��������� 0���󸶷� 1��Ԥ���� 9����ѯ�󶨵��û�������
        	resMap = bindBankCardBean.qrySubsPrepayType(customer, termPO, curMenuId);
        	
        	//�û���������
        	bankCardInfoPO.setPayType(resMap.get("payType"));
        	
			//��ѯ�û��Ƿ�ͨ�Զ���ֵ���ܣ��������� 0
			resMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
			
			//�Զ���ֵ
			bankCardInfoPO.setAutoStatus(resMap.get("autoStatus"));
			
			//���û�ΪԤ�����û������ѿ�ͨ�Զ���ֵ
			if ("1".equals(bankCardInfoPO.getPayType()) && "0".equals(bankCardInfoPO.getAutoStatus()))
			{
				//�������
				bankCardInfoPO.setTrigamt(resMap.get("trigamt"));
				
				//��ֵ���
				bankCardInfoPO.setDrawamt(resMap.get("drawamt"));
			}
			
			//��ѯ�û��ĸ������б���ˮ��
			Map<String, Object> retMap = bindBankCardBean.viceNumberQry(customer, termPO, curMenuId, "ZLWGQY", "QRY");
			
			//�û��Ѱ󶨵ĸ�����list
			viceNumList = (List<BankCardInfoPO>)retMap.get("viceNumList");
			
			//�û��Ѱ󶨵ĸ����봮
			oldViceNumber = (String)retMap.get("oldViceNumber");
			
			result = "easyPayMng";
		} 
    	catch (ReceptionException e) 
    	{
            this.getRequest().setAttribute("errormessage", e.getMessage());
            
	       	// ����������־
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
    		logger.error(e);
		}
    	
    	return result;
    }
    
    /**
     * <�����û����Զ��ۿ���>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-12-5 15:36:42 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż������� 
     */
    public String setAutoFee()
    {
    	String forward = ERROR;
    	
    	Map<String,String> resMap = null;
    	
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomerSimp();
        
        //�������ͣ����ѿ�ͨ�Զ�������Ϊ3:���£�����Ϊ1����ͨ
        String oprtype = "0".equals(bankCardInfoPO.getAutoStatus()) ? "3" : "1";
        
        //Ԥ�����û��Ĵ������
        String trigamt = "1".equals(bankCardInfoPO.getPayType()) ? bankCardInfoPO.getTrigamt() : "";
    	
        //Ԥ�����û��Ŀۿ���
        String drawamt = "1".equals(bankCardInfoPO.getPayType()) ? bankCardInfoPO.getDrawamt() : "";
        
    	try 
    	{
    		//�Զ���������
			resMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, oprtype, trigamt, drawamt);
			
			//Ԥ�����û�
			if("1".equals(bankCardInfoPO.getPayType()))
			{
				//���ú�Ĵ������
				bankCardInfoPO.setTrigamt(resMap.get("trigamt"));
				
				//���ú���Զ����ѽ��
				bankCardInfoPO.setDrawamt(resMap.get("drawamt"));
			}
			
			//��ʾ��Ϣ
			String successMsg = "0".equals(bankCardInfoPO.getAutoStatus()) ? "�Զ����ѹ����޸ĳɹ�" : "�Զ����ѹ��ܿ�ͨ�ɹ�";
            this.getRequest().setAttribute("successMsg", successMsg);
			
			//��ת������ҳ��
			forward = easyPayMng();
		} 
    	catch (ReceptionException e) 
    	{
			logger.error("�׳�ֵ�Զ����ѹ�������ʧ�ܣ�", e);
			
	       	// ����������־
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
		}
    	
    	return forward;
    }
    
    /**
     * <�׳�ֵ�û�����������>
     * <��������(�������ͣ�1)��ɾ��(�������ͣ�2)����������>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-12-12 16:49:39 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż������� 
     */
    public String viceNumberMng()
    {
    	String forward = ERROR;
    	
        //�û��Ѱ󶨵ĸ������б�
        String[] viceNumArray = bankCardInfoPO.getViceNumber().split("~");
    	
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomerSimp();
        
        try 
        {
			//����������
			bindBankCardBean.viceNumberSet(customer, termPO, curMenuId, viceNumArray, bankCardInfoPO.getOperType());
			
			//��ʾ��Ϣ
			String successMsg = "1".equals(bankCardInfoPO.getOperType()) ? "��������ӳɹ�" : "������ɾ���ɹ�";
            this.getRequest().setAttribute("successMsg", successMsg);
            
			//��ת������ҳ��
			forward = easyPayMng();
		} 
        catch (ReceptionException e) 
        {
			logger.error("�׳�ֵ�û�����������ʧ�ܣ�", e);
			
	       	// ����������־
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
		}
    	
    	return forward;
    }
    
    /**
     * <ȡ�����п��󶨲���>
     * <���û��ѿ�ͨ�Զ����ѣ����ȹر��Զ����ѹ��ܣ��ٽ�����п���>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-12-9 11:15:50 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż������� 
     */
    public String cancelBind()
    {
    	String forward = ERROR;
    	
    	Map<String,String> retMap = null;
    	
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomerSimp();
	        	
        try 
        {
			//��ѯ�û��Ƿ�ͨ�Զ����ѹ��� �������ͣ�0
			retMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
			
			//�ѿ�ͨ
			if("0".equals(retMap.get("autoStatus")))
			{
				//�ر��Զ�����
				bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "2", "", "");
			}
			
			retMap.clear();
			
        	bindBankCardPO.setTelNum(customer.getServNumber());
        	
            // �����˳���󶨽ӿ�
        	retMap = bindBankCardBean.unBindBankCard(bindBankCardPO);
        	
            if ("0".equals(retMap.get("STATUS")))
            {
                String msg = "�Բ��𣬽�����п�ʧ��ʧ��ԭ��" + retMap.get("RETMSG");
                
                // ����������־
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", msg);
                
                //���ô�����Ϣ��ʾ
                this.getRequest().setAttribute("errormessage", msg);
            }
            else
            {
                // �����ɹ���־
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "���п���󶨳ɹ�");
                
                // ����ҳ�淵����Ϣ
                this.getRequest().setAttribute("errormessage", "�𾴵�" + customer.getServNumber() + "�û��������п���󶨳ɹ���");
                
                forward = SUCCESS;
            }
		} 
        catch (ReceptionException e) 
        {
	       	// ����������־
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
	           
	        //���ô�����Ϣ��ʾ
            this.getRequest().setAttribute("errormessage", e.getMessage());
            
            return forward;
		}
        catch (Exception ex)
        {
            // ������ʾ��Ϣ
            String msg = "���п����ʧ�ܣ�����ԭ�򣺵��������ӿ�ʧ�ܣ�";
            
            // ����������־
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", msg);
            
            logger.error("���п����ʧ��:", ex);
            
            //���ô�����Ϣ��ʾ
            this.getRequest().setAttribute("errormessage", msg);
        }
        
    	return forward;
    }
    
    /**
     * <��ת������������ҳ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputViceNum()
    {
    	return "inputViceNum";
    }

    
    public BindBankCardPO getBindBankCardPO()
    {
        return bindBankCardPO;
    }
    public void setBindBankCardPO(BindBankCardPO bindBankCardPO)
    {
        this.bindBankCardPO = bindBankCardPO;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public BindBankCardBean getBindBankCardBean()
    {
        return bindBankCardBean;
    }

    public void setBindBankCardBean(BindBankCardBean bindBankCardBean)
    {
        this.bindBankCardBean = bindBankCardBean;
    }

    public String getExpire1()
    {
        return expire1;
    }

    public void setExpire1(String expire1)
    {
        this.expire1 = expire1;
    }

    public String getExpire2()
    {
        return expire2;
    }

    public void setExpire2(String expire2)
    {
        this.expire2 = expire2;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getConfirmUnbind() {
		return confirmUnbind;
	}

	public void setConfirmUnbind(String confirmUnbind) {
		this.confirmUnbind = confirmUnbind;
	}

    /**
     * @return ���� userLoginBean
     */
    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    /**
     * @param ��userLoginBean���и�ֵ
     */
    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    /**
     * @return ���� randomPwd
     */
    public String getRandomPwd()
    {
        return randomPwd;
    }

    /**
     * @param ��randomPwd���и�ֵ
     */
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }

    /**
     * @return ���� randomPwdErrTimes
     */
    public String getRandomPwdErrTimes()
    {
        return randomPwdErrTimes;
    }

    /**
     * @param ��randomPwdErrTimes���и�ֵ
     */
    public void setRandomPwdErrTimes(String randomPwdErrTimes)
    {
        this.randomPwdErrTimes = randomPwdErrTimes;
    }

	public String getPayTypeFlag() {
		return payTypeFlag;
	}

	public void setPayTypeFlag(String payTypeFlag) {
		this.payTypeFlag = payTypeFlag;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getHeBaoCommitFlag() {
		return heBaoCommitFlag;
	}

	public void setHeBaoCommitFlag(String heBaoCommitFlag) {
		this.heBaoCommitFlag = heBaoCommitFlag;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public BindBankCardService getBindBankCardService() {
		return bindBankCardService;
	}

	public void setBindBankCardService(BindBankCardService bindBankCardService) {
		this.bindBankCardService = bindBankCardService;
	}

	public List<BankCardInfoPO> getCardInfoList() {
		return cardInfoList;
	}

	public void setCardInfoList(List<BankCardInfoPO> cardInfoList) {
		this.cardInfoList = cardInfoList;
	}

	public String getUserInfoTips() {
		return userInfoTips;
	}

	public void setUserInfoTips(String userInfoTips) {
		this.userInfoTips = userInfoTips;
	}

	public List<DictItemPO> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(List<DictItemPO> balanceList) {
		this.balanceList = balanceList;
	}

	public List<DictItemPO> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<DictItemPO> chargeList) {
		this.chargeList = chargeList;
	}

	public BankCardInfoPO getBankCardInfoPO() {
		return bankCardInfoPO;
	}

	public void setBankCardInfoPO(BankCardInfoPO bankCardInfoPO) {
		this.bankCardInfoPO = bankCardInfoPO;
	}

	public String getOprType() {
		return oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getTrigAmt() {
		return trigAmt;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setTrigAmt(String trigAmt) {
		this.trigAmt = trigAmt;
	}

	public String getDrawAmt() {
		return drawAmt;
	}

	public void setDrawAmt(String drawAmt) {
		this.drawAmt = drawAmt;
	}

	public String getHBManageTips() {
		return HBManageTips;
	}

	public void setHBManageTips(String manageTips) {
		HBManageTips = manageTips;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public List<BankCardInfoPO> getViceNumList() {
		return viceNumList;
	}

	public void setViceNumList(List<BankCardInfoPO> viceNumList) {
		this.viceNumList = viceNumList;
	}

	public List<DictItemPO> getEasyPayTipList() {
		return easyPayTipList;
	}

	public void setEasyPayTipList(List<DictItemPO> easyPayTipList) {
		this.easyPayTipList = easyPayTipList;
	}
	
	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldViceNumber() {
		return oldViceNumber;
	}

	public void setOldViceNumber(String oldViceNumber) {
		this.oldViceNumber = oldViceNumber;
	}
	
}
