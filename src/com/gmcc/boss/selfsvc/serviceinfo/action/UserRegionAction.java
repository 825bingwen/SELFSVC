package com.gmcc.boss.selfsvc.serviceinfo.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.UserRegionBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ��������ز�ѯ
 * 
 * @author xkf29026
 * 
 */
public class UserRegionAction extends BaseAction
{
    
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(UserRegionAction.class);
    
    /**
     * ��ǰ�˵�id
     */
    private String curMenuId = "";
    
    // Ҫ��ѯ���ֻ���
    private String qryServnumber;
    
    //��������
    private String regionName;
    
    //������Ϣ
//    private String error;
    
    //���ýӿ�bean
    private UserRegionBean userRegionBean;
    
    /**
     * �����¼ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String telNumInput()
    {
        return "telNumInput";
    }
    
    /**
     * ��������ز�ѯ
     * 
     * @param request
     * @return
     */
    public String queryUserRegionReq()
    {
        logger.debug("queryUserRegionReq Starting...");
        
        String qryServnuber = getQryServnumber();
        
        //�������ָ��ҳ��
        String froward = "error";
        
        //��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //���ýӿڲ�ѯ���������
        Map result = userRegionBean.queryUserRegionReq(terminalInfoPO, customer, qryServnuber, curMenuId);
        
        if (result != null && result.size() > 0)
        {
            String regionName = (String)result.get("returnObj");
            setRegionName(regionName);
            setQryServnumber(qryServnuber);
            froward = "userRegionList";
            
            //����ɹ���־
            this.createRecLog(Constants.BUSITYPE_USERREGION, "0", "0", "0", "ҵ����Ϣ��ѯ:" + qryServnuber + "��������ز�ѯ�ɹ�!");
        }
        else
        {
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            
            this.getRequest().setAttribute("errormessage", "��������ز�ѯʧ�ܣ�");
            
            //���������־
            this.createRecLog(Constants.BUSITYPE_USERREGION, "0", "0", "1", "ҵ����Ϣ��ѯ:" + qryServnuber + "��������ز�ѯʧ��!");
        }
        logger.debug("queryUserRegionReq End!");
        return froward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getQryServnumber()
    {
        return qryServnumber;
    }
    
    public void setQryServnumber(String qryServnumber)
    {
        this.qryServnumber = qryServnumber;
    }
    
    public UserRegionBean getUserRegionBean()
    {
        return userRegionBean;
    }

    public void setUserRegionBean(UserRegionBean userRegionBean)
    {
        this.userRegionBean = userRegionBean;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

//    public String getError()
//    {
//        return error;
//    }
//
//    public void setError(String error)
//    {
//        this.error = error;
//    }
}
