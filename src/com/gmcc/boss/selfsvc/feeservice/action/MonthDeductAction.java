package com.gmcc.boss.selfsvc.feeservice.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.bean.MonthDeductBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * �³��ۿ��ѯAction
 * <������ϸ����>
 * 
 * @author  user
 * @version  [�汾��, Dec 10, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MonthDeductAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(MonthDeductAction.class);
    
    private MonthDeductBean monthDeductBean;
    
    private String curMenuId = "";

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * �³��ۿ��ѯ
     * <������ϸ����>
     * @return
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryMonthDeduct() 
    {
        logger.debug("queryMonthDeduct Starting...");
        String forward = "";
        
        HttpSession session = this.getRequest().getSession();
        
        // �û���Ϣ
        NserCustomerSimp customerInfo = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        // ִ�в�ѯ
        ReturnWrap rw = monthDeductBean.queryMonthDeduct(customerInfo, termInfo, this.getCurMenuId());
        
        if (rw.getStatus() == 1)
        {
            Vector vec = (Vector)rw.getReturnObject();
            this.getRequest().setAttribute("query", vec);
            
            // �ɹ���־
            this.createRecLog(Constants.BUSITYPE_MONTHFEE, "0", "0", "0", "���Ѳ�ѯ���³��ۿ��ѯ�ɹ���");
            
            forward = "success";
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "������Ϣ��" + rw.getReturnMsg());
            
            // ʧ����־
            this.createRecLog(Constants.BUSITYPE_MONTHFEE, "0", "0", "1", "���Ѳ�ѯ���³��ۿ��ѯʧ�ܣ�");
            
            forward = "error";
        }
        logger.debug("queryMonthDeduct end!");
        return forward;
    }

    /**
     * springע��
     * <������ϸ����>
     * @param monthDeductBean
     * @see [�ࡢ��#��������#��Ա]
     */
    public void setMonthDeductBean(MonthDeductBean monthDeductBean)
    {
        this.monthDeductBean = monthDeductBean;
    }
    
    
}
