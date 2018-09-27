/*
* @filename: RectelInfoAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  ҵ���Ƽ�ӪҵԱ���ֻ�����ά����
* @author: g00140516
* @de:  2012/07/04 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
*/
package com.customize.nx.selfsvc.rectelinfo.action;

import javax.servlet.http.HttpServletRequest;

import com.customize.nx.selfsvc.rectelinfo.service.RectelInfoService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.managerOperation.service.ManagerOperationService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * ҵ���Ƽ�ӪҵԱ���ֻ�����ά����
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/04
 * @see  
 * @since  
 */
public class RectelInfoAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    /**
     * ҵ���Ƽ�ӪҵԱ���ֻ�����
     */
    private String rectel = ""; 
    
    /**
     * ����Ա����
     */
    private String mgtpwd = "";
    
    /**
     * ���й���Ա����У��
     */
    private ManagerOperationService managerOperationService;
    
    /**
     * ����ҵ���Ƽ�ӪҵԱ���ֻ�����ά��
     */
    private RectelInfoService rectelInfoService;
    
    /**
     * �ɹ���ʾ��Ϣ
     */
    private String successMessage = "";

    /**
     * �������Ա��������ҳ��
     * 
     * @return
     * @see 
     */
    public String inputMgtPwd()
    {
        return "input";
    }
    
    /**
     * У�����Ա�����Ƿ���ȷ������ȷ���ж��ն˻��Ƿ���ά��ҵ���Ƽ�ӪҵԱ���ֻ�����
     * 
     * @return
     * @see 
     */
    public String checkMgtpwd()
    {
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        
        //��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //�ն˻�id
        String termid = termInfo.getTermid();
        
        //��װ����
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        managerOperationPO.setAuditPsw(CommonUtil.MD5Encode(mgtpwd));
        
        //У�����롣1���ɹ���������ʧ��
        String checkStatus = managerOperationService.checkAuditPassword(managerOperationPO);
        if ("1".equals(checkStatus))
        {
            rectel = rectelInfoService.getRectelInfoWithTermid(termid);
            
            // δά��ҵ���Ƽ�ӪҵԱ���ֻ�����
            if (null == rectel || "".equals(rectel.trim()))
            {
                forward = "registerPage";
            }
            // ��ά��ҵ���Ƽ�ӪҵԱ���ֻ�����
            else
            {
                forward = "logoutPage";
            }
        }        
        else
        {
            //���ô�����Ϣ
            request.setAttribute("errormessage", "����Ա������֤ʧ�ܣ�����������");
            
            request.setAttribute("backStep", "-1");
        }
        
        return forward;
    }

    /**
     * ע��ҵ���Ƽ�ӪҵԱ���ֻ�����
     * 
     * @return
     * @see 
     */
    public String logout()
    {
        HttpServletRequest request = this.getRequest();
        
        //��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //�ն˻�id
        String termid = termInfo.getTermid();
        
        rectelInfoService.deleteRectelWithTermid(termid);
        
        successMessage = "ҵ���Ƽ�ӪҵԱ���ֻ�����ע���ɹ�";
        
        this.createRecLog("", Constants.SH_RECTEL_MGT, "0", "0", "0", "ҵ���Ƽ�ӪҵԱ���ֻ�����ע���ɹ�(�ն˻���" + termid + ")");
        
        return "success";
    }
    
    /**
     * ע��ҵ���Ƽ�ӪҵԱ���ֻ�����
     * 
     * @return
     * @see 
     */
    public String registerRectel()
    {
        HttpServletRequest request = this.getRequest();
        
        //��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //�ն˻�id
        String termid = termInfo.getTermid();
        
        rectelInfoService.insertRectelWithTermid(termid, rectel);
        
        successMessage = "ҵ���Ƽ�ӪҵԱ���ֻ�����ά���ɹ�";
        
        this.createRecLog("", Constants.SH_RECTEL_MGT, "0", "0", "0", "ҵ���Ƽ�ӪҵԱ���ֻ�����ά���ɹ�(�ն˻���" + termid + "; �ֻ����룺" + rectel + ")");
        
        return "success";
    }

    public String getMgtpwd()
    {
        return mgtpwd;
    }

    public void setMgtpwd(String mgtpwd)
    {
        this.mgtpwd = mgtpwd;
    }

    public ManagerOperationService getManagerOperationService()
    {
        return managerOperationService;
    }

    public void setManagerOperationService(ManagerOperationService managerOperationService)
    {
        this.managerOperationService = managerOperationService;
    }

    public RectelInfoService getRectelInfoService()
    {
        return rectelInfoService;
    }

    public void setRectelInfoService(RectelInfoService rectelInfoService)
    {
        this.rectelInfoService = rectelInfoService;
    }
    
    public String getRectel()
    {
        return rectel;
    }

    public void setRectel(String rectel)
    {
        this.rectel = rectel;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
