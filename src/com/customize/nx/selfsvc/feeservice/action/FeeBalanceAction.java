package com.customize.nx.selfsvc.feeservice.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.FeeBalanceBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ��ֵ����ֵ
 * @author cKF48754
 *
 */

public class FeeBalanceAction extends BaseAction
{
    
	// ���л�
    private static final long serialVersionUID = 1L;
   
    // ��ǰ�˵�id
    private String curMenuId = "";
   
    // ����б����
    private String[] serviceTitle;
    
    // ���������Ϣ
    private String[] balanceStr;
    
    // ��־
    private static Log logger = LogFactory.getLog(FeeBalanceAction.class);
    
    // ���ýӿ�Bean
    private FeeBalanceBean feeBalanceBean;
   
    /**
     * �˻�����ѯ
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    
    public String qryAccBalance()
    {
        if (logger.isDebugEnabled())
        {
        	logger.debug("queryBalance Starting...");
        }
        
        // ��ȡsession
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // �������ҳ��ת��
        String forward = "error";
        
        // ����ѯ
		Map result = feeBalanceBean.queryBalance(terminalInfoPO,customer,curMenuId);
		 
		if (result != null && result.size() > 0)
		{
			CTagSet tagSet = (CTagSet)result.get("returnObj");
		     
		    String[] titles = {"�ϴ����", "����Ӧ��", "�������"};
		     
		    // ���ñ���
		    setServiceTitle(titles);
		     
		    String[] balances = {tagSet.GetValue("lasttimebalance"), 
		            tagSet.GetValue("shouldpay"), tagSet.GetValue("newbalance")};
		     
		    // �������
		    setBalanceStr(balances);
		     
		    forward = "qryBalance";
		     
		    // ��¼�ɹ���־
		    this.createRecLog( Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "0", "�˻�����ѯ:����ѯ�ɹ�!");
		}
		else
		{
		    if (null != result)
		    {		        
		        request.setAttribute("errormessage", result.get("returnMsg"));
		    }
		    else
		    {
		        request.setAttribute("errormessage", "����ѯʧ��");
		    }		    
		     
		    // ��¼������־
		    this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "1", "�˻�����ѯ:����ѯʧ��!");
		}
        
		if (logger.isDebugEnabled())
        {
			logger.debug("queryBalance End!");
        }
		
		return forward;
    }

    public void setFeeBalanceBean(FeeBalanceBean feeBalanceBean)
    {
        this.feeBalanceBean = feeBalanceBean;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String[] getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String[] serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public String[] getBalanceStr() {
		return balanceStr;
	}

	public void setBalanceStr(String[] balanceStr) {
		this.balanceStr = balanceStr;
	}

}
