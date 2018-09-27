package com.gmcc.boss.selfsvc.baseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.bean.PasswordResetBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ConstantsBase;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * ��������
 * 
 * @author xkf29026
 * 
 */
public class PasswordResetAction extends BaseAction
{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    // ��־��¼
    private static Log logger = LogFactory.getLog(PasswordResetAction.class);
    
    // �ֻ�����
    private String servnumber;
    
    // �������
    private String randomPwd;
    
    // ������
    private String newPasswd;
    
    // ���ýӿ�bean
    private PasswordResetBean passwordResetBean;
    
    private transient LoginService loginService = null;
    
    // �ɹ���Ϣ
    private String successMessage;
    
    // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    // ��ʼ�����¼��־
    private String initPwdLoginFlag = "";
    // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    
    /**
     * �����������Э���飨���ģ�
     * 
     * @return
     */
    public String resetPwdProtocol()
    {
        return "resetPwdProtocol";
    }
    
    /**
     * ��֤���֤�Ų������������
     * 
     * @return
     */
    public String sendRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        setServnumber(customer.getServNumber());
        
        // ��ǰ�˵�����������Ϊ��
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // �����ֻ����롢��֤��ʽ��ѯ�����¼��¼
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        // ������
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // modify begin g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
            logger.error("�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ����ʹ���������ù���");
                        
            String errorMsg = "�������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ���������ù��ܣ���" + lockedTime + "���Ӻ�����";
            
            errorMsg = getConvertMsg(errorMsg, "zz_02_01_000004", null, new String[]{"�������ù���", lockedTime});
            // modify end g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            this.createRecLog(servnumber,
                    Constants.BUSITYPE_RESETPWD,
                    "0",
                    "0",
                    "1",
                    errorMsg);
            
            return "error";
        }
        
        // �������
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // ��ȡʡ��
        String provinceID = (String)PublicCache.getInstance().getCachedData("ProvinceID");
        
        // add begin cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
        {
         // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
            {
                // �ж��Ƿ�����������·����ŵ�����
                String errorMsg = canSmsCode(servnumber);
                
                // ���errorMsg��Ϊ���򲻷�������
                if (StringUtils.isNotBlank(errorMsg))
                {
                    logger.error(errorMsg);
                    
                    this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
                    
                    this.getRequest().setAttribute("errormessage", errorMsg);
                    
                    return "error";
                }
            }
            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
            // �����б�:12013-07-03 12:01:01#2123456
            String smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            if (!passwordResetBean.sendRandomPwdHub(terminalInfoPO,
                    smsparam,
                    servnumber,
                    "Atsv_recPwdReset",
                    curMenuId))
            {
                logger.error("�������ù��ܣ����û�" + servnumber + "��������������ʧ��");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "�������ù��ܣ����������ŷ���ʧ�ܡ�");
                
                this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
                
                return "error";
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("�������ù��ܣ����û�" + servnumber + "�������������ųɹ��������" + randomPwd);
                    // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
                    if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
                    {
                        // ������ʱ���¼��session��
                        session.setAttribute(servnumber, new Date().getTime());
                        // ִ�в�����佫��Ϣ��¼��SH_SMS_HISTORY����
                        insertSmsCode(servnumber, terminalInfoPO, randomPwd);
                    }
                    // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
                }
            }
        }
        // add end cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20

        else
        {
            // ��������������
            String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            shortMessage = shortMessage.replace("[%1]", randomPwd);
            
            if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
            {
                logger.error("�������ù��ܣ����û�" + servnumber + "��������������ʧ��");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "�������ù��ܣ����������ŷ���ʧ�ܡ�");
                
                this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
                
                return "error";
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("�������ù��ܣ����û�" + servnumber + "�������������ųɹ��������" + randomPwd);
                }
            }
            
        }
        
        String forward = "randomPwdPage";
        
        // �ж�ʡ��ת��ͬ�������������ҳ�棨��������Ҫ���������룩
        if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
        {
            forward = "randomPwdPageHub";
        }
        
        return forward;
    }
    
    /**
     * ʹ��������������������
     * 
     * @return
     */
    public String resetPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("resetPassword Starting...");
        }
        
        // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
        HttpSession session = this.getRequest().getSession();
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        setServnumber(customer.getServNumber());
        
        // �����ֻ����롢��֤��ʽ��ѯ�����¼��¼
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ�����������������֤�ɹ�");
            }
            
            // ��֤�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
                        
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if (newPasswd == null)
            {
                newPasswd = "";
            }
            
            //modify begin g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����
            // ���ýӿڽ�����������
            boolean resetStatus = false;
            
            // ɽ������Ҫ���������3DES����
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            
            // ����������ܱ�־�����ģ���1:����
            String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);
            
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                String newpwd = newPasswd;
                
                try
                {
                    DESedeEncrypt encrypt = new DESedeEncrypt();
                    // findbugs�޸� 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs�޸� 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("������������ʱ�������������", e);
                }
                
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newpwd);
            }
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            else if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) && "1".equals(encryptFlag))
            {
                String newpwd = newPasswd;
                
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    // findbugs�޸� 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs�޸� 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("������������ʱ�������������", e);
                }
                
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newpwd);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            else
            {
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newPasswd);
            }
			//modify begin g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����
            
            if (resetStatus)
            {
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "0", "�������óɹ���");
                successMessage = "�������óɹ�";
                
                // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                if ("1".equals(initPwdLoginFlag))
                {
                    successMessage = " �𾴵Ŀͻ������ķ������������óɹ��������µ�¼!";
                }
                // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                
                // add begin cKF76106 2013/02/17 NG3.3&3.5 �淶���϶Ȳ�����Ŀ����������ⱨ��-֧�ֽ��������Զ��ŷ�ʽ֪ͨ�ͻ�
                if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
                {
                    // ��������������
                    String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PWDRESET_SUCCESS");
                    shortMessage = shortMessage.replace("[%1]", newPasswd);
                    
                    if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
                    {
                        logger.error("�������óɹ������û�" + servnumber + "�����������óɹ�����ʧ��");
                        
                        this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "�������óɹ��������������óɹ�����ʧ�ܡ�");
                        
                        this.getRequest().setAttribute("errormessage", "�������óɹ��������������óɹ�����ʧ�ܡ�");
                        
                        return "error";
                    }
                    else
                    {
                        if (logger.isInfoEnabled())
                        {
                            logger.info("�������óɹ������û�" + servnumber + "�����������óɹ����ųɹ�");
                        }
                    }
                }
                // add end cKF76106 2013/02/17 NG3.3&3.5 �淶���϶Ȳ�����Ŀ����������ⱨ��-֧�ֽ��������Զ��ŷ�ʽ֪ͨ�ͻ�
                
                return "success";
            }
            else
            {
                logger.error("�û�" + servnumber + "������������ʧ��");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "��������ʧ�ܡ�");
                
                this.getRequest().setAttribute("errormessage", "��������ʧ��");
                
                return "error";
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
                // modify begin g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
                errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
            }
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����������������ʱ����������ʧ��");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            // ��ȡʡ��
            String provinceID = (String)PublicCache.getInstance().getCachedData("ProvinceID");

            if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
            {
               return "randomPwdErrorHub";
            }
            
            return "randomPwdError";
        }        
    }
    
    /**
     * ʹ��������������������(���ĵ��õ��½ӿ�)
     * 
     * @return String
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 ���������ն��Ż�����_�������������
     */
    public String resetPasswordNewNX()
    {
        logger.debug("resetPasswordNewNX Starting...");

        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // �����ֻ�����
        setServnumber(customer.getServNumber());
        
        // �����ֻ����롢��֤��ʽ��ѯ�����¼��¼
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            logger.info("�û�" + servnumber + "ʹ�����������������֤�ɹ�");
            
            // ��֤�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
                        
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if (newPasswd == null)
            {
                newPasswd = "";
            }
            
            // ����������ܱ�־�����ģ���1:����
            String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

            String newpwd = newPasswd;
                
            // ��������������
            if("1".equals(encryptFlag))
            {
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    // findbugs�޸� 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs�޸� 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("������������ʱ�������������", e);
                }
            }
            Map returnMap = this.passwordResetBean.resetPasswordNew(terminalInfoPO, servnumber, curMenuId, newpwd);    
            
            if (null != returnMap && returnMap.size() > 2)
            {
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "0", "�������óɹ�!");
                successMessage = "�������óɹ�!";
                
                if ("1".equals(initPwdLoginFlag))
                {
                    successMessage = " �𾴵Ŀͻ������ķ������������óɹ��������µ�¼!";
                }
               
                // ��������������
                String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PWDRESET_SUCCESS");
                shortMessage = shortMessage.replace("[%1]", newPasswd);
                
                if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
                {
                    logger.error("�������óɹ������û�" + servnumber + "�����������óɹ�����ʧ��");
                    
                    this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "�������óɹ��������������óɹ�����ʧ�ܡ�");
                    
                    this.getRequest().setAttribute("errormessage", "�������óɹ��������������óɹ�����ʧ�ܡ�");
                    
                    return "error";
                }
                else
                {
                    logger.info("�������óɹ������û�" + servnumber + "�����������óɹ����ųɹ�");
                }
                
                return "success";
            }
            else if (null != returnMap && returnMap.size() == 2)
            {
                logger.error("�û�" + servnumber +""+(String)returnMap.get("returnMsg"));
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "��������ʧ��ԭ��"+(String)returnMap.get("returnMsg"));
                
                this.getRequest().setAttribute("errormessage", (String)returnMap.get("returnMsg"));
                
                return "error";
            }
            else
            {
            	logger.error("�û�" + servnumber +"�����������ýӿ�ʧ��");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "�������ýӿڵ���ʧ��");
                
                this.getRequest().setAttribute("errormessage", "�������ýӿڵ���ʧ�ܣ����Ժ�����!");
                
            	return "error";
            }
        }
        else 
        {
            String errorMsg = "";
            
         // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//            if ("-1".equals(result))
//            {
//                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
//            }
//            else
//            {
//                errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
//            }
            errorMsg = "-1".equals(result) ? "���������������Ѿ���ʱ���뷵�����Ի��߽�������������" : getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����������������ʱ����������ʧ��");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return "randomPwdError";
        }        
    }
    
    /**
     * �ж��û������Ƿ�����
     * 
     * @param loginErrorPO
     * @return
     */
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
    
    public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
    
    public PasswordResetBean getPasswordResetBean()
    {
        return passwordResetBean;
    }
    
    public void setPasswordResetBean(PasswordResetBean passwordResetBean)
    {
        this.passwordResetBean = passwordResetBean;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public LoginService getLoginService()
    {
        return loginService;
    }
    
    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }
    
    public String getRandomPwd()
    {
        return randomPwd;
    }
    
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public String getNewPasswd()
    {
        return newPasswd;
    }
    
    public void setNewPasswd(String newPasswd)
    {
        this.newPasswd = newPasswd;
    }
    
    public String getInitPwdLoginFlag()
    {
        return initPwdLoginFlag;
    }
    
    public void setInitPwdLoginFlag(String initPwdLoginFlag)
    {
        this.initPwdLoginFlag = initPwdLoginFlag;
    }
}
