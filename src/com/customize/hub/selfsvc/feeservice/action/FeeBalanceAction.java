package com.customize.hub.selfsvc.feeservice.action;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.customize.hub.selfsvc.bean.FeeBalanceBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �˻�����ѯ
 * @author cKF48754
 *
 */

public class FeeBalanceAction extends BaseAction
{
    
	// ���л�
    private static final long serialVersionUID = 1L;
   
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    // ������Ϣ
    private String error;
   
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
        HttpSession session = getRequest().getSession();
        
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
		     
		    String[] titles = {"�������", "�ֽ�", "��ֵ��", "���µֿۻ���", "���ͻ���", "�������ͻ���", "������µֿ��ܶ�",
		    				   "���ͷ��µֿ��ܶ�", "�������", "���ö�", "ʵʱ����", "��ʷǷ��"};
		     
		    // ���ñ���
		    setServiceTitle(titles);
		    
		    String[] balances = {tagSet.GetValue("balance"), tagSet.GetValue("cashBalance"),
		    					 tagSet.GetValue("cardBalance"), tagSet.GetValue("monDeduction"),
		    					 tagSet.GetValue("presentBalance"), tagSet.GetValue("monPresentBalance"),
		     					 tagSet.GetValue("DKBalance"), tagSet.GetValue("preDKBalance"),
		     					 tagSet.GetValue("availableBalance"), tagSet.GetValue("credit"),
		     					 tagSet.GetValue("realTimeFee"), tagSet.GetValue("hisArrears")};
		    
		    //����ת����Ԫ
		    for(int i = 0;i < balances.length; i++)
		    {
		    	balances[i] = CommonUtil.fenToYuan(balances[i]);
		    }
		    
		    // �������
		    setBalanceStr(balances);
		     
		    forward = "qryBalance";
		     
		    // ��¼�ɹ���־
		    this.createRecLog( Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "0", "�˻�����ѯ:����ѯ�ɹ�!");
		}
		else
		{
		    String errMsg = "";
		    if (null != result)
		    {
		        errMsg = (String) result.get("returnMsg");
		    }
		    
		    if (null == errMsg || "".equals(errMsg))
		    {
		        errMsg = "�˻�����ѯʧ��";
		    }
		    
		    getRequest().setAttribute("errormessage", errMsg);
		     
		    // ��¼������־
		    this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "1", errMsg);
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

	public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String[] getServiceTitle() 
	{
		return serviceTitle;
	}
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setServiceTitle(String[] serviceTitle) 
	{
		this.serviceTitle = serviceTitle;
	}

	public String[] getBalanceStr() 
	{
		return balanceStr;
	}

	public void setBalanceStr(String[] balanceStr) 
	{
		this.balanceStr = balanceStr;
	}

}
