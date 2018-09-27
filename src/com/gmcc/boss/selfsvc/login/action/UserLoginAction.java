/*
 * �ļ�����UserLoginAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ConstantsBase;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.huawei.boss.common.security.RSAUtil;

/**
 * �û���¼
 * 
 * @author g00140516
 * 
 */
public class UserLoginAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(UserLoginAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient UserLoginBean userLoginBean = null;
    
    private transient LoginService loginService = null;
    
    private String servnumber = "";
    
    private String password = "";
    
    private String curMenuId = "";
    
    private String randomPwd = "";
    
    private String IDCard = "";
    
    private String oldPasswd = "";
    
    private String newPasswd = "";
    
    //add begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597    
    // ��Ʒ����
    private String typeID= "";
    
    // ���Ų�Ʒ������־
    private String hotRecFlag = "";
    
    // ���ٷ�����ʶ
    private String quickPubFlag = "";
    
    //add end CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597 
    
    //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
    // ��־���ɷ�ҳ���¼�����Ӫ���
    private String commitProductFlag = "";
    
    private String userSeq = "";
    
    // add begin create zWX176560 OR_HUB_201303_200_����_�����ն�һ�廯Ӫ�������Ż�
    // ҵ���Ƽ�Ψһ��ˮ��
    private String commendOID = "";
    // add end create zWX176560 OR_HUB_201303_200_����_�����ն�һ�廯Ӫ�������Ż�
    
    private String feeChargeFlag = "";
    //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
    
    // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    // ��ʼ�����¼��־
    private String initPwdLoginFlag = "";
    // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    
    // ͣ����ԭ��
    private String selectReason = "";
    
    /**
     * �˵����������͡�2��Ʒ�ƣ�3������ĸ
     */
    private String searchType = "";
    
    // add begin m00227318 2013/02/16 R003C13L01n01 ���ģ��޸ģ����ӿ�ѡ��֤��ʽ
    // ��֤��ʽ���ͣ�optional����ѡ;��������ѡ
    private String authCodeType = "";
    
    // ��ѡ��֤��ʽ
    private String resultAvaiCode = "";
    // add end m00227318 2013/02/16 R003C13L01n01 ���ģ��޸ģ����ӿ�ѡ��֤��ʽ
    
    // add begin lWX431760 2017/01/03 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸ģ�����ͼƬ��֤��
    private String randomCodeImage = "";
    // add end lWX431760 2017/01/03 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸ģ�����ͼƬ��֤��
    
    public String getRandomPwd()
    {
        return randomPwd;
    }
    
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String card) {
        IDCard = card;
    }
        
    // add begin lWX431760 2017/01/03 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸ģ�����ͼƬ��֤��
    public String getRandomCodeImage() {
		return randomCodeImage;
	}

	public void setRandomCodeImage(String randomCodeImage) {
		this.randomCodeImage = randomCodeImage;
	}
	 // add end lWX431760 2017/01/03 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸ģ�����ͼƬ��֤��

	/**
     * �����ն�IDɾ����¼��Ϣ
     * 
     * @author lWX5316086
     * @see [�ࡢ��#��������#��Ա]
     */
    public void deleteLoginCheckByTermId()
    {
        // ����ն˻���Ϣ
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ɾ���ն�ID��Ӧ�ĵ�¼��Ϣ
        loginService.deleteLoginCheckByTermId(terminalInfo.getTermid());
    }
    
    /**
     * ͨ���ֻ����롢�����������������֤
     * 
     * @return
     */
    public String loginWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd Starting ...");
        }
        
        String forward = "failed";
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        // ����������ܱ�־(����)��1:����
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

        //add begin CKF76106 2012/09/29 R003C12L08n01 OR_HUB_201207_875
        // ��������������֤
        String loginFlag = getRequest().getParameter("loginflag");
        if(Constants.PROOPERORGID_HUB.equals(province))
        {
            if("hub".equals(loginFlag))
            {
                forward = "failed_hub";
            } 
            password=RSAUtil.decrypt(this.getRequest().getSession(), password);
        }
        //add end CKF76106 2012/09/29 R003C12L08n01 OR_HUB_201207_875
        
        //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
        // �ɷ�֧��Ӫ���Ƽ��������������֤ҳ��
        if("1".equals(commitProductFlag))
        {
            forward = "goCheckPassword";
        }
        //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
                
        LoginErrorPO loginErrorPO = null;
        if (!Constants.PROOPERORGID_SD.equals(province))
        {
	        loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
	        if (loginErrorPO != null && isLocked(loginErrorPO))
	        {
	            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
	            
	            // ������
	            logger.error("���ڷ����������������������ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ������");
	            
	            if (Constants.PROOPERORGID_NX.equals(province))
	            {
	                this.getRequest().setAttribute("errormessage", "���ڷ����������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������");
	            }
	            else
	            {
	                this.getRequest().setAttribute("errormessage", "���ڷ����������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����");
	            }
	            
	            return forward;
	        }
        }

        if (Constants.PROOPERORGID_SD.equals(province))
        {
            //modify begin g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����
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
            
            // ����CRMͳһ��֤�ӿڽ�����֤
            Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
            //modify end g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����

        	// 100:�ɹ� 102:ȱʡ���� 119:��ʼ����
        	if (returnMap != null && "100".equals(String.valueOf(returnMap.get("errcode"))))// 100:�ɹ� 
        	{
	            if (logger.isInfoEnabled())
	            {
	                logger.info("�û�" + servnumber + "ʹ�÷����������������֤�ɹ�");
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
		                //���ֻ����뼰����֤��ʽ
		                String oldServNumber = oldCustomerSimp.getServNumber();
		                String oldLoginMark = oldCustomerSimp.getLoginMark();
		
		                //����굥����
		                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
		                
		                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
		                //����˵�����
		                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
		                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
		                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��

		                //����û���Ϣ
		                session.removeAttribute(Constants.USER_INFO);
		                
		                //���µ��û���Ϣ�����session��
		                session.setAttribute(Constants.USER_INFO, customerSimp);
		                
		                //�����û���loginMark
		                if (servnumber.equals(oldServNumber))
		                {
		                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
		                }
		            }
		            
		            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷����������������֤�ɹ�");
		            
		            forward = "success";
	        	}
        	}
        	// ��ʼ�����ȱʡ����-->���������֤-->�����޸�ҳ��
        	else if (returnMap != null && ("102".equals(String.valueOf(returnMap.get("errcode"))) || "119".equals(String.valueOf(returnMap.get("errcode")))))
        	{
                // �����������
                String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
                
                // ��������������
                String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMessage = shortMessage.replace("[%1]", randomPwd);
                
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                customerSimp.setServNumber(servnumber);
                
                if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
                {
                    logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
                    
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
                    
                    this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
                    
                    forward = "failed";
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("���û�" + servnumber + "�������������ųɹ��������" + randomPwd);
                    }
                    
                    forward = "randomcode_editPwd";
                }
        	}
        	// ��¼ʧ��
        	else
        	{
	            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
	            
	            logger.error("ʹ�÷����������������֤ʧ�ܣ��ֻ����룺" + servnumber);
	            
	            if (returnMap != null)
	            {
	            	this.getRequest().setAttribute("errormessage", StringUtils.isBlank((String)returnMap.get("returnMsg")) ? "ʹ�÷������������֤ʧ�ܡ�" : returnMap.get("returnMsg"));
	            }
	            else
	            {
	            	this.getRequest().setAttribute("errormessage", "ʹ�÷������������֤ʧ�ܡ�");
	            }
	            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ�÷������������֤ʧ�ܡ�"); 
	            
	            forward = "failed";
        	}
        }
        else
        {
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            Map customerSimpMap = null;
            if (Constants.PROOPERORGID_NX.equals(province) && "1".equals(encryptFlag))
            {
                String newpwd = password;
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                }
                catch (Exception e)
                {
                    logger.error("����������֤ʱ�������������", e);
                }
                
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, newpwd, termInfo);

            }
            else
            {   
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
	        if (customerSimpMap.get("customerSimp") == null)
	        {
	            // ��¼ʧ��
	            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
	            
	            logger.error("ʹ�÷����������������֤ʧ�ܣ��ֻ����룺" + servnumber);
	            
	            // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
	            String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
	                    "zz_04_01_000001", String.valueOf(customerSimpMap.get("errcode")), 
	                    null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // add begin cKF76106 2013/02/05  �����û���¼ʧ�ܸ��ݴ���ת����ʾ��Ϣ
                if (Constants.PROOPERORGID_NX.equals(province) && "713".equals(String.valueOf(customerSimpMap.get("errcode"))))
                {
                	// [CDATA[BUSINESSID:BegChargeFee_ZZZD,����[15138053999]��ѯ������ʧ��]]
                	this.getRequest().setAttribute("errormessage", "���û��������ƶ��û������������в���");
                }
                // add end cKF76106 2013/02/05  �����û���¼ʧ�ܸ��ݴ���ת����ʾ��Ϣ
                else if (Constants.PROOPERORGID_NX.equals(province))
                {
                	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
                	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
	            	
                	if ((loginErrorPO == null && 1 < maxTimes) 
                			|| (loginErrorPO != null && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                	{
                		this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ����˳�����������֤");
                	}
                	else
                	{
                		//this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ��������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����");
                	    this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ��������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������");
                	}
                }
                
	            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
	            // modify end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
	        }
	        else
	        {   
	            //add begin ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
                if (Constants.PROOPERORGID_NX.equals(province)
                        && userLoginBean.valiIsfirstpwd(termInfo, curMenuId, servnumber))
                {
                    // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    forward = "initPwdLogin";
                    // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    
                }
                //add end ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
	         
	            else
	            { 
	                if (logger.isInfoEnabled())
	                {
	                    logger.info("�û�" + servnumber + "ʹ�÷����������������֤�ɹ�");
	                }
	                
	                
	                // add begin lWX5316086 

	                // ���ͬһ����ͬһ������ε�¼
	                if(Constants.PROOPERORGID_NX.equals(province) && 
	                        "1".equals((String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_SWITCH")) && 
	                          !loginService.checkLoginByServNumber(servnumber,termInfo.getTermid()))
	                {
	                    // ���ش�����ʾ
	                    this.getRequest().setAttribute("errormessage", (String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_TIPMESSAGE"));
	                    
	                    return "failed";
	                }
               
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                    
                    // ��¼�ɹ���ɾ����¼
                    loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                    
                    //modify begin g00140516 2011/10/20 R003C11L10n01 �ع�            
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //���ֻ����뼰����֤��ʽ
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //����굥����
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                        //����˵�����
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                        
                        //����û���Ϣ
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //���µ��û���Ϣ�����session��
                        session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //�����û���loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    //modify end g00140516 2011/10/20 R003C11L10n01 �ع� 
                    
                    // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    // ��������û��Ƽ����־
                    session.removeAttribute(Constants.ALREADY_REC_FLAG);
                    // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷����������������֤�ɹ�");
                    
                    //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                    if("1".equals(commitProductFlag))
                    {
                        session.setAttribute(Constants.ALREADY_REC_FLAG, "1");
                        return "continue";
                    }
                    //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                    forward = "success";  
	                
	                // add end lWX5316086 
	                
	            }
	       }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd End");
        }
        return forward;
    }
    
    /**
     * ���ɲ����û���������������
     * 
     * @return
     * @see
     */
    public String sendRandomPwd()
    {
        logger.debug("sendRandomPwd Starting ...");
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(customerSimp.getServNumber(),
                Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����           
            // ������
            logger.error("�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����" + customerSimp.getServNumber() + "��ʱ����ʹ�����������֤");
            
            String errorMsg = "";
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                errorMsg = "�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�����������֤����" + lockedTime + "���Ӻ�����";
            }
            
            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
            else if (Constants.PROOPERORGID_SD.equals(province))
            {
                errorMsg = "�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�����������֤����" + Integer.parseInt(lockedTime) + "���Ӻ�����";
            }
            else
            {
                errorMsg = "�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�����������֤����" + Integer.parseInt(lockedTime) + "Сʱ������";
            }
            // modify end by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
            
            errorMsg = getConvertMsg(errorMsg, "zz_02_01_000004", null, new String[]{"���������֤", lockedTime});
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            
            return forward;
        }
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �����������
        String randomPwd = createRandomPassword(customerSimp.getServNumber(), CommonUtil.getCurrentTime());
        
        // add begin cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        if(Constants.PROOPERORGID_HUB.equals(province) && ("qryMuster".equals(curMenuId)||"recStopOpen".equals(curMenuId)))
        {
            // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
            {
                // �ж��Ƿ�����������·����ŵ�����
                String errorMsg = canSmsCode(customerSimp.getServNumber());
                
                // ���errorMsg��Ϊ���򲻷�������
                if (StringUtils.isNotBlank(errorMsg))
                {
                    logger.error(errorMsg);
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
                    
                    this.getRequest().setAttribute("errormessage", errorMsg);
                    return forward;
                }
            }
            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
            
            // �����б�:12013-07-03 12:01:01#2123456
            // modify begin lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 ҵ�����������Ż�����
            String smsparam = "";
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_SMSINFO_NEWMOD, "false")))
            {
                smsparam = "1" + new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            else
            {
                smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            // modify end lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 ҵ�����������Ż�����
            
            // ����ģ��
            String templateno = "";
            
            if ("qryMuster".equals(curMenuId))
            {
                templateno = "Atsv_qryMuster";
            }
            if ("recStopOpen".equals(curMenuId))
            {
                templateno = "Atsv_recStopOpen";
            }
            
            if (!userLoginBean.sendRandomPwdHub(termInfo, smsparam, customerSimp.getServNumber(), templateno, curMenuId))
            {
                logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
                
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
                
                this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
            }
            else
            {
                logger.info("���û�" + customerSimp.getServNumber() + "�������������ųɹ��������" + randomPwd);
                
                // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
                if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
                {
                    // ������ʱ���¼��session��
                    session.setAttribute(customerSimp.getServNumber(), new Date().getTime());
                    
                    // ִ�в�����佫��Ϣ��¼��SH_SMS_HISTORY����
                    insertSmsCode(customerSimp.getServNumber(), termInfo, randomPwd);
                }
                // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
                
                forward = "success";
                
                return forward;
            }
        }
        // add end cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        else
        {
			//add begin ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
			//��ȡ���������������
			String shortMessage = getSMTemplate(randomPwd); 
			//add end ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
	        
	        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
	        {
	            logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
	            
	            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
	            
	            this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
	        }
	        else
	        {
	            logger.info("���û�" + customerSimp.getServNumber() + "�������������ųɹ��������" + randomPwd);
	            logger.info("�������ݣ�" + shortMessage);
	            
	            forward = "success";
	        }
        }
        
        logger.debug("sendRandomPwd End");
        
        return forward;
    }
    
    /**
     * ���ݲ˵�ID��ѯ���õĶ���ģ��
     * <������ϸ����>
     * @param randomPwd �������
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create YKF38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
     */
    public String getSMTemplate(String randomPwd)
    {
        String  shortMsg = "";
        
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.CDR_SMTEMPLATE);
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        //�����굥��ѯ��ȡģ��
        if (null != itemList && Constants.PROOPERORGID_HUB.equals(province) && itemList.size() > 0)
        {                     
            for (int i=0; i<itemList.size(); i++)
            {
                DictItemPO cip = (DictItemPO)itemList.get(i);
                //����������õĶ���ģ�壬��ȡ�ö���ģ��
                if (cip.getDictid().equals(curMenuId))
                {
                    shortMsg = (String)PublicCache.getInstance().getCachedData(cip.getDictname());
                    shortMsg = shortMsg.replace("%TIME", DateUtilHub.getStringDate());  
                    shortMsg = shortMsg.replace("%RANDOMPWD", randomPwd);
                    break;
                }
            }
            if ("".equals(shortMsg) || 0 == shortMsg.length())
            {
            	shortMsg = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMsg = shortMsg.replace("[%1]", randomPwd); 
            }
        }
        else //��ȡĬ�ϵĶ���ģ��s
        {
            shortMsg = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            shortMsg = shortMsg.replace("[%1]", randomPwd); 
        }
        
        return shortMsg;
    }
    /**
     * ʹ��������������֤
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String loginWithRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(customerSimp.getServNumber(),
                Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(customerSimp.getServNumber(), randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + customerSimp.getServNumber() + "ʹ������������������֤�ɹ�");
            }
            
            // ��֤�ɹ���ɾ����¼
            loginService.deleteErrorRecords(customerSimp.getServNumber(), Constants.AUTHTYPE_RANDOMPWD);
            
            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(customerSimp.getLoginMark(), "0100"));
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "���������֤�ɹ���");
            
            forward = "success";
            
            //�ж��Ƿ�Ϊͣ����ҵ�������ͣ����ҵ��������ɹ���Ӧת��ҵ������ɹ�ҳ��
            if (getRequest().getAttribute(Constants.CUR_MENUID).equals("recStopOpen"))
            {
                  try
                {
                    this.getResponse().sendRedirect(this.getRequest().getContextPath()+"/baseService/recOpenAndStopSubs.action?actionCase=SHstopOpen&curMenuId=recStopOpen" + "&selectReason=" + selectReason);
                }
                catch (IOException e)
                {
                    return "error";
                }
            }           
        }
        else 
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
            }
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO,customerSimp.getServNumber(),Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + customerSimp.getServNumber() + "����������������ʱ��������֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * ͨ���ֻ����롢����֤�Ž�����֤
     * 
     * @return
     */
    public String loginWithID()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithID Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2011/10/20 R003C11L10n01 �ع� 
        Map customerSimpMap = userLoginBean.checkIDCard(termInfo, IDCard, servnumber, curMenuId);
        
        NserCustomerSimp newCustomerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
        if (newCustomerSimp != null)
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ������֤�Ž�����֤�ɹ�");
            }            
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);            
            if (customerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, newCustomerSimp);
            }
            else
            {
                //���ֻ����뼰����֤��ʽ
                String oldServNumber = customerSimp.getServNumber();
                String oldLoginMark = customerSimp.getLoginMark();
                
                //����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                //����˵�����
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                
                //����û���Ϣ
                session.removeAttribute(Constants.USER_INFO);
                
                session.setAttribute(Constants.USER_INFO, newCustomerSimp);
                
                //ͬһ������
                if (servnumber.equals(oldServNumber))
                {
                    newCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0010"));
                }
            }            
            
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "0", "ʹ������֤�Ž�����֤�ɹ�");
            
            forward = "success";          
        }
        else
        {
            logger.error("ʹ������֤�Ž�����֤ʧ�ܣ��ֻ����룺" + servnumber + ";����֤�ţ�" + IDCard);
            
            // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
                    "zz_04_01_000002", String.valueOf(customerSimpMap.get("errcode")), null);
            
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
            	resultMsg = "����֤����֤ʧ�ܣ����˳���������֤";
            }
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        }
        //modify end g00140516 2011/10/20 R003C11L10n01 �ع� 
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithID End");
        }
        
        return forward;
    }
    
    /**
     * ���ɲ����û���������������_����
     * 
     * @return
     * @see
     */
    public String randomcode_cq()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("randomcode_cq Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �����������
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // ��������������
        String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        // δ��¼����·��Ͷ����漴����_��������ֻ�����
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        // ��������������ʧ��
        Map resultMap = userLoginBean.sendRandomPwdByMap(customerSimp, termInfo, shortMessage, curMenuId);
        if (!"".equals((String)resultMap.get("returnMsp")))
        {
            logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��,ԭ��"+(String)resultMap.get("returnMsp"));
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
            
            this.getRequest().setAttribute("errormessage", (String)resultMap.get("returnMsp"));
            
        }
        // �������������ųɹ�
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("���û�" + customerSimp.getServNumber() + "�������������ųɹ��������" + randomPwd);
            }
            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("randomcode_cq End");
        }
        
        return forward;
    }
    
    /**
     * ͨ��������������֤_���죨�������¼��
     * 
     * @return
     */
    public String loginWithRandom_cq()
    {
        logger.debug("loginWithPwd Starting ...");
        
        String forward = "failed";
        
        // ��ѯ�����Ƿ�����
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // ������
            logger.error("��������������������������ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ������");
            
            this.getRequest().setAttribute("errormessage", "��������������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����");
            
            return forward;
        }
        
        // У���������
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        
        // �������У��ʧ��
        if (!"1".equals(result))
        {
        	String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                errorMsg = "�����������������������롣";
            }
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO,servnumber,Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����������������ʱ��������֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        logger.info("�û�" + servnumber + "ʹ������������������֤�ɹ�");

        // ���е��ýӿڵ�¼
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        if (customerSimpMap.get("customerSimp") == null)
        {
            // ��¼ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("ʹ��ʹ������������������֤ʧ�ܣ��ֻ����룺" + servnumber + ";�������룺" + password);
            
            this.getRequest().setAttribute("errormessage", customerSimpMap.get("returnMsp"));
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ�÷������������֤ʧ�ܡ�");
        }
        else
        {
            logger.info("�û�" + servnumber + "ʹ�÷����������������֤�ɹ�");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
            
            // ��¼�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                       
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                //���ֻ����뼰����֤��ʽ
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                //����˵�����
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                
                //����û���Ϣ
                session.removeAttribute(Constants.USER_INFO);
                
                //���µ��û���Ϣ�����session��
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                //�����û���loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�����������������֤�ɹ�");
            
            forward = "success";
        }
        
        logger.debug("loginWithPwd End");
        
        return "success";
    }
    
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // ��������ﵽ��ϵͳ����
        if (loginErrorPO.getErrorTimes() >= maxTimes)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String lastTime = loginErrorPO.getLastTime();
            
            try
            {
                // ����ʱ��
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // ��ǰʱ��
                String currTime = sdf.format(c.getTime());
                
                // ����ʱ��
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // ����ʱ��Ҫ���ڵ�ǰʱ�䣬����������������ڼ���
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("�жϺ����Ƿ�����ʱ�����쳣��", e);
            }
        }
        
        // ���������δ�ﵽϵͳ����
        return false;
    }
    
    /**
     * 
     * ��ʼ�������������¼֮�����������֤
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String editPasswordByRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("editPasswordByRandomPwd Starting ...");
        }
        
        String forward = "failed";
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ�����������֤�ɹ�");
            }
            forward = "success";
        }
        else 
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
            }
            
            logger.error("�û�" + servnumber + "����������������ʱ��������֤ʧ��");
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("editPasswordByRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 
     * ��ʼ�������������¼֮���޸�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String editPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("editPassword Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

    	//modify begin g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����
    	// �޸�����        
    	Map returnMap = null;
    	
    	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    	
    	 // ����������ܱ�־(����)��1:����
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);
        
        if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
        {
            String oldpwd = oldPasswd;
            String newpwd = newPasswd;
            
            try
            {
                DESedeEncrypt encrypt = new DESedeEncrypt();
                oldpwd = encrypt.encrypt(null == oldpwd ? "" : oldpwd);
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("��ʼ�����¼���޸ķ�������ʱ�������������", e);
            }
            
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldpwd, newpwd);
        }
        // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        else if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) && "1".equals(encryptFlag))
        {
            String oldpwd = oldPasswd;
            String newpwd = newPasswd;
            
            try
            {
                DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                oldpwd = encrypt.encrypt(null == oldpwd ? "" : oldpwd);
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("��ʼ�����¼���޸ķ�������ʱ�������������", e);
            }
            
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldpwd, newpwd);
        }
        // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        else
        {
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldPasswd, newPasswd);
        }
        //modify end g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����

    	// �ɹ� 
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
    	//if (returnMap != null && "1".equals(String.valueOf(returnMap.get("status"))))
    	if ("1".equals(String.valueOf(returnMap.get("status"))))
    	{
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "�޸�����ɹ�!");
            }
            
            this.getRequest().setAttribute("errormessage", "�𾴵Ŀͻ������ķ����������޸ĳɹ��������µ�¼!");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "�޸�����ɹ���"); 
            
            forward = "success";
            
    	}
    	// ʧ��
    	else 
    	{
            logger.error("�ֻ����룺" + servnumber + "�޸�����ʧ�ܣ�ԭ��" + returnMap.get("returnMsg"));
            
            this.getRequest().setAttribute("errormessage", returnMap.get("returnMsg"));
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "�޸�����ʧ�ܡ�"); 
            
            forward = "failed";
    	}
        
        if (logger.isDebugEnabled())
        {
            logger.debug("editPassword End");
        }
        
        return forward;
    }
    
    /**
     * ���ɲ����û����Ͷ�����֤��(����)
     * 
     * @return
     * @see
     */
    public void sendRandomPwd_hub() throws IOException
    {
        // ͷ��Ϣ
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        PrintWriter out = this.getResponse().getWriter();
        
        HttpSession session = this.getRequest().getSession();
        
        // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
        if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
        {
            // �ж��Ƿ�����������·����ŵ�����
            String errorMsg = canSmsCode(servnumber);
            
            // ���errorMsg��Ϊ���򲻷�������
            if (StringUtils.isNotBlank(errorMsg))
            {
                logger.error(errorMsg);
                
                out.write(errorMsg);
                out.flush();
                return;
            }
        }
        // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // ������
            logger.error("���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ����ʹ�ö�����֤����֤");
            
            String errorMsg = "���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�ö�����֤����֤����" + lockedTime + "���Ӻ�����";
            
            // ��ʾ��Ϣת��
            String[] params = new String[] {"������֤����֤", lockedTime};
            errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000004", "", params);
            
            out.write(errorMsg);
            out.flush();
            return;
        }
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ���ɶ�����֤��
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // ��ȡ������֤���������
        String shortMessage = getSMTemplate(randomPwd);
        
        // δ��¼����·��Ͷ�����֤��
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        //��ȡ��ǰʡ��
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_HUB.equals(province))
        {
            // modify begin lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 ҵ�����������Ż�����
            String smsparam = "";
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_SMSINFO_NEWMOD, "false")))
            {
                smsparam = "1" + new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            else
            {
                smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            // modify end lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 ҵ�����������Ż�����
            
            String templateno = "Atsv_RandomPwd";
            if (!userLoginBean.sendRandomPwdHub(termInfo, smsparam, customerSimp.getServNumber(), templateno, curMenuId))
            {
                logger.error("���û�" + servnumber + "���Ͷ�����֤��ʧ��");
                
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "������֤�뷢��ʧ�ܡ�");
                
                out.write("������֤�뷢��ʧ�ܣ����Ժ����ԡ�");
                out.flush();
	            return;
	        }
	        else
	        {
	            if (logger.isInfoEnabled())
	            {
	                logger.info("���û�" + customerSimp.getServNumber() + "���Ͷ�����֤��ɹ���������֤��" + randomPwd);
	                logger.info("�������ݣ�" + shortMessage);
	            }
	            
	            // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
	            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
	            {
	                // ������ʱ���¼��session��
	                session.setAttribute(servnumber, new Date().getTime());
	                
	                // ִ�в�����佫��Ϣ��¼��SH_SMS_HISTORY����
                    insertSmsCode(servnumber, termInfo, randomPwd);
	            }
	            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
	        }
        }
        else
        {
	        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
	        {
	            logger.error("���û�" + servnumber + "���Ͷ�����֤��ʧ��");
	            
	            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "������֤�뷢��ʧ�ܡ�");
	            
	            out.write("������֤�뷢��ʧ�ܣ����Ժ����ԡ�");
	            out.flush();
	            return;
	        }
	        else
	        {
	            if (logger.isInfoEnabled())
	            {
	                logger.info("���û�" + customerSimp.getServNumber() + "���Ͷ�����֤��ɹ���������֤��" + randomPwd);
	                logger.info("�������ݣ�" + shortMessage);
	            }
	        }
        }
        out.write("");
        out.flush();
        
    }
    
    /**
     * ʹ�ö�����֤���¼(����)
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String loginWithRandomPwd_hub()
    {
        logger.debug("loginWithRandomPwd Starting ...");
        
        String forward = "failed";
        
        // ��ѯ�����Ƿ�����
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // ������
            logger.error("���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ����ʹ�ö�����֤����֤");
            
            String errorMsg = "���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�ö�����֤����֤����" + lockedTime + "���Ӻ�����";
            
            // ��ʾ��Ϣת��
            String[] params = new String[] {"������֤����֤", lockedTime};
            errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000004", "", params);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        // У�������֤��
        String result = this.loginWithRandomPwd(servnumber, password);
        
        // ������֤��У��ʧ��
        if (!"1".equals(result))
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "������Ķ�����֤���Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                errorMsg = "������֤������������������롣";
                // ��ʾ��Ϣת��
                errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000003", "", null);
            }
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����Ķ�����֤������ʱ��������֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        logger.info("�û�" + servnumber + "ʹ�ö�����֤�����������֤�ɹ�");
        
        // ���е��ýӿڵ�¼
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        if (customerSimpMap.get("customerSimp") == null)
        {
            // ��¼ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("ʹ�����������������֤ʧ�ܣ��ֻ����룺" + servnumber);
            
            String errorMsg = (String)customerSimpMap.get("returnMsp");
            
            // ��ʾ��Ϣת��
            String errCode = String.valueOf(customerSimpMap.get("errcode"));
            
            errorMsg = super.getConvertMsg(errorMsg, "zz_04_01_000005", errCode, null);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ�����������������֤ʧ�ܡ�");
        }
        else
        {
            logger.info("�û�" + servnumber + "ʹ�����������������֤�ɹ�");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)customerSimpMap.get("customerSimp");
            
            // ��¼�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                // ���ֻ����뼰����֤��ʽ
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();
                
                // ����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                //����˵�����
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                
                // ����û���Ϣ
                session.removeAttribute(Constants.USER_INFO);
                
                // ���µ��û���Ϣ�����session��
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                // �����û���loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�����������������֤�ɹ�");
            
            forward = "success";
        }
        
        logger.debug("loginWithRandomPwd End");
        
        return forward;
    }
    
    /**
     * ��ʼ�����¼��ѡ�������޸ģ����Ͷ�������
     * 
     * @return
     * @see 
	 * @remark create cKF76106 2012/12/15  V200R003C12L12 OR_NX_201211_746
     */ 
    public String sendRandomCode()
    {
        String forward = "failed";
        
        // �����������
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        // ��������������
        String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
        {
            logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
            
            this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
        }
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("���û�" + servnumber + "�������������ųɹ��������" + randomPwd);
            }
            
            forward = "randomcode_editPwd";
        }
        
        return forward;
    }
    
    /**
     * ��ʼ�����¼��ѡ���������ã�ת������֤��֤ҳ��
     * 
     * @return
     * @see
	 * @remark create cKF76106 2012/12/15  V200R003C12L12 OR_NX_201211_746
     */ 
    public String goIdCardPage()
    {
        this.getRequest().setAttribute("initPwdLoginFlag", "1");
        
        return "idCardPage";
    }
    
    public String goRamdomPage_hub()
    {
        return "goRamdomPage";
    }
    
    public String goServicePwdPage_hub()
    {
        return "goServicePwdPage";
    }
    
    /**
     * ��ѡ��֤��ʽ��ʹ��������������֤
     * 
     * @return
     * @see 
     */
    public String loginWithRandomPwdNew()
    {
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, password);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ������������������֤�ɹ�");
            }
            
            // ��֤�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // �û���Ϣ
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // ȡ�ͻ���Ϣ
            if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                if (customerSimpMap.get("customerSimp") != null)
                {
                    customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                }
            }
            
            customerSimp.setServNumber(servnumber);
            customerSimp.setLoginMark("0100");
            
            // session�д�ŵ��û���Ϣ
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
            	//���ֻ����뼰����֤��ʽ
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                //����˵�����
                session.removeAttribute(oldServNumber + "_billhalfyeartrend");
                session.removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��

                if (!servnumber.equals(oldCustomerSimp.getServNumber()))
                {
                	//����û���Ϣ
                    session.removeAttribute(Constants.USER_INFO);
                    
                    //���µ��û���Ϣ�����session��
                    session.setAttribute(Constants.USER_INFO, customerSimp);
                }
                else
                {
                	oldCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0100"));   
                }
            }
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "���������֤�ɹ���");
            
            forward = "success";
        }
        // ��֤ʧ��
        else 
        {
        	// modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        	// ��ȡ��֤ʧ�ܺ�Ĵ�����Ϣ
        	String errorMsg = getErrMsg(loginErrorPO, result);
        	// modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����������������ʱ��������֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        return forward;
    }
    
    /**
     * ��ȡ��֤ʧ�ܺ�Ĵ�����Ϣ
     * @param loginErrorPO
     * @param result
     * @return ������Ϣ
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	private String getErrMsg(LoginErrorPO loginErrorPO, String result)
	{
		String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
		String errorMsg = "";
		
		if ("-1".equals(result))
		{
		    errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
		    
		    if (Constants.PROOPERORGID_NX.equals(province))
		    {
		    	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
		    	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
		    	
		    	if ((null == loginErrorPO && 1 < maxTimes) 
		    			|| (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
		    	{
		    		errorMsg = "���������������Ѿ���ʱ�����˳�����������֤";
		    	}
		    	else
		    	{
		    		errorMsg = "���������������Ѿ���ʱ���������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������";
		    	}
		    }
		}
		else
		{
		    errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
		    
		    if (Constants.PROOPERORGID_NX.equals(province))
		    {
		    	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
		    	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
		    	
		    	if ((null == loginErrorPO  && 1 < maxTimes) 
		    			|| (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
		    	{
		    		errorMsg = "�����������������˳�����������֤";
		    	}
		    	else
		    	{
		    		errorMsg = "���������������������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������";
		    	}
		    }
		}
		return errorMsg;
	}
    
    /**
     * ��ѡ��֤��ʽ��������֤����֤ҳ��
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goRandomPwdPage()
    {
    	return "randomPwdPage";
    }
    
    /**
     * ��ѡ��֤��ʽ������������֤ҳ��
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goServicePwdPage()
    {
    	return "servicePwdPage";
    }
    
    /**
     * ��ѡ��֤��ʽ��������֤ҳ��
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goIDPage()
    {
    	return "idCardPage";
    }
    
    
    /**
     * �����û��Ƿ��ں�������(����)
     * @create zKF77391
     * @throws Exception
     */
    public void checkBlackList() throws Exception
    {
        logger.debug("��ʼ��ѯ�û�[" + servnumber + "]�Ƿ��������");
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try
        {
            boolean isBlack = loginService.checkBlackListByServNumber(servnumber);
            if (isBlack)
            {
                logger.debug("�û�[" + servnumber + "]�ں�������");
                out.write("yes");
            }
            else
            {
                logger.debug("�û�[" + servnumber + "]���ں�������");
                out.write("no");
            }
            
            createRecLog(servnumber, "SHCheckBlackList", "0", "0", "0", "��ѯ�û�[" + servnumber + "]�Ƿ��ں������гɹ�");
        }
        catch (Exception e)
        {
            logger.error("��ѯ��������������:" + e);
            createRecLog(servnumber, "SHCheckBlackList", "0", "0", "1", "��ѯ�û�[" + servnumber + "]�Ƿ��ں�������ʧ��");
            out.write("yes");
        }
        finally
        {
            out.flush();
            out.close();
        }
        logger.debug("������ѯ�û�[" + servnumber + "]�Ƿ��������");
        
    }
	
	    
    /**
     * �Ƿ�ʵ������Ϣ�Ǽ�(����)
     * @create zKF77391
     * @throws Exception
     */
    public void realNameCheck() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try
        {
            // �Ƿ�ʵ������Ϣ�Ǽ�
            
            // 1 ---��ʵ���ƵǼ� 0 ---δ�Ǽ�
            String prompt = "";
            String ret = this.userLoginBean.realNameCheck(servnumber, (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO));
            if ("0".equals(ret))
            {
                prompt = (String)PublicCache.getInstance().getCachedData("SH_REALNAME_PROMPT");
                if (prompt == null)
                {
                    prompt = "�˺�����з�ʵ��������Ϊȷ���ͻ�Ȩ�棬�й��ƶ����Ĺ�˾��������רҵӪҵ������ʵ���Ǽǡ�";
                }
            }
            
            out.write(ret + "^" + prompt);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�Ƿ�ʵ������Ϣ�Ǽǳ���:" + e);
            out.write("2");
        }
        finally
        {
            out.flush();
            out.close();
        }
    }
    
    /**
     * (����)�Ƿ������������
     * 
     * @author hWX5316476
     * @remark create by hWX5316476 2014-2-19  OR_NX_201402_306 ���������ն��Ż�����_�������������
     */
    public void weakPwdCheckLogin()throws IOException
    {
    	HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();

        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
            String prompt = "";
            
            // ���ýӿڼ����Ƿ���������
            Map retmap = this.userLoginBean.weakPwdCheck(termInfo,servnumber,password,curMenuId);

			if(null != retmap && retmap.size() > 2 )
			{
				if("2".equals((String)retmap.get("remindflag")))
				{
					prompt = "�𾴵Ŀͻ��������õ�����Ƚϼ򵥣�Ϊ�˱�����������Ϣ�İ�ȫ�������������޸�Ϊ���Ӹ��ӵ����룡";
	            	
	            	// ��ȡ��������ʾ��Ϣ(����)
	            	String pro = (String)PublicCache.getInstance().getCachedData(Constants.SH_WEAKPWD_PROMPT);
	
	            	// �����������������ʾ��Ϣ����չʾ���õ���������ʾ��Ϣ
	            	if(null != pro && !"".equals(pro) )
	            	{
	            		prompt = pro;
	            	} 
	            	out.write("2^" + prompt);
				}
				else
				{
					out.write("1^" + prompt);
				}
			}
            else if (null != retmap && retmap.size() == 2)
            {
            	// �����ýӿڵĴ��󷵻���Ϣ����ҳ��
            	prompt = (String)retmap.get("returnMsg");
            	
            	out.write("0^" + prompt);
            }
            else
            {
            	out.write("0^��ѯ�Ƿ������������!���Ժ�����!");
            }
        }
        catch (Exception e)
        {
            logger.error("��ѯ�Ƿ����������:" + e);
            out.write("1");
        }
        finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * ��ѯ����ҳ��
     * @remark create by hWX5316476 2014-12-5 OR_HUB_201408_628_����_���������ն���������ҳ��
     */
    public void qrySysUpdateTipPage()
    {
        // �Ƿ���תϵͳ������ʾҳ 0������ת 1����ת
        String sysUpdatePage = "0";
        
        // ��ȡϵͳ�������� ��0���ر�  1��������
        String sysUpdateSwitch = loginService.qryParamValueById("SH_SYS_UPDATE_SWITCH");
        
        // û�����ã�Ĭ�Ϲر�
        if(StringUtils.isEmpty(sysUpdateSwitch))
        {
            sysUpdateSwitch = "0";
        }

        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // ��ϵͳ�������ؿ���������û���û���¼��ʱ�򣬿���תϵͳ������ʾҳ
        if("1".equals(sysUpdateSwitch) && null == customerSimp)
        {
            sysUpdatePage = "1";
        }
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        try
        {
            response.getWriter().write(sysUpdatePage);
        }
        catch (Exception e)
        {
            logger.error("��ѯϵͳ��������ʧ��",e);
        }
    }
    
    /**
     * ��ѯϵͳ��������
     * @remark create by hWX5316476 2014-12-5 OR_HUB_201408_628_����_���������ն���������ҳ��
     */
    public void qrySysUpdateSwitch()
    {
        // ��ȡϵͳ�������� ��0���ر�  1��������
        String sysUpdateSwitch = loginService.qryParamValueById("SH_SYS_UPDATE_SWITCH");
        
        // û�����ã�Ĭ�Ϲر�
        if(StringUtils.isEmpty(sysUpdateSwitch))
        {
            sysUpdateSwitch = "0";
        }
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        try
        {
            response.getWriter().write(sysUpdateSwitch);
        }
        catch (Exception e)
        {
            logger.error("��ѯϵͳ��������ʧ��",e);
        }
    }
    

	/**
	 * У��ͼƬ��֤��(����)
	 * @return 
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by lWX431760 2017-1-5 OR_HUB_201609_640 �����ն��û���¼��֤��ʽ
	 */
	public void randomCodeSecurity()
	{
		try
		{
			String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
			if (Constants.PROOPERORGID_HUB.equals(province)) 
			{
				HttpSession session = this.getRequest().getSession();
								
				String validateResult = this.userLoginBean.validteRandomCode(randomCodeImage,
						(String)session.getAttribute("randomCode"));
				
				session.removeAttribute("randomCode");
				
				writeXmlMsg(validateResult);	
			}
		}
		catch(Exception e)
		{
			writeXmlMsg("");
		}
	}
	
    /**
     * ��ת��ʾҳ��
     * @return
     * @remark create by hWX5316476 2014-12-10 OR_HUB_201408_628_����_���������ն���������ҳ��
     */
    public String goSysUpdateTipPage()
    {
        return "sysUpdateTipPage";
    }
    
    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }
    
    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }
    
    public LoginService getLoginService()
    {
        return loginService;
    }
    
    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

    public String getCommitProductFlag()
    {
        return commitProductFlag;
    }

    public String getUserSeq()
    {
        return userSeq;
    }

    public void setCommitProductFlag(String commitProductFlag)
    {
        this.commitProductFlag = commitProductFlag;
    }

    public void setUserSeq(String userSeq)
    {
        this.userSeq = userSeq;
    }

    public String getFeeChargeFlag()
    {
        return feeChargeFlag;
    }

    public void setFeeChargeFlag(String feeChargeFlag)
    {
        this.feeChargeFlag = feeChargeFlag;
    }

    public String getTypeID()
    {
        return typeID;
    }

    public void setTypeID(String typeID)
    {
        this.typeID = typeID;
    }

    public String getHotRecFlag()
    {
        return hotRecFlag;
    }

    public void setHotRecFlag(String hotRecFlag)
    {
        this.hotRecFlag = hotRecFlag;
    }

    public String getQuickPubFlag()
    {
        return quickPubFlag;
    }

    public void setQuickPubFlag(String quickPubFlag)
    {
        this.quickPubFlag = quickPubFlag;
    }
    
    public String getSearchType()
    {
        return searchType;
    }

    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }

    public String getInitPwdLoginFlag()
    {
        return initPwdLoginFlag;
    }

    public void setInitPwdLoginFlag(String initPwdLoginFlag)
    {
        this.initPwdLoginFlag = initPwdLoginFlag;
    }

    public String getSelectReason()
    {
        return selectReason;
    }

    public void setSelectReason(String selectReason)
    {
        this.selectReason = selectReason;
    }

	public String getAuthCodeType()
	{
		return authCodeType;
	}

	public void setAuthCodeType(String authCodeType)
	{
		this.authCodeType = authCodeType;
	}

	public String getResultAvaiCode()
	{
		return resultAvaiCode;
	}

	public void setResultAvaiCode(String resultAvaiCode)
	{
		this.resultAvaiCode = resultAvaiCode;
	}

    public String getCommendOID()
    {
        return commendOID;
    }

    public void setCommendOID(String commendOID)
    {
        this.commendOID = commendOID;
    }
}