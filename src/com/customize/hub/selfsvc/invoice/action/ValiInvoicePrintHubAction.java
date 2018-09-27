/*
 * �� �� ��:  InvoicePrint.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: YKF38827
 * �޸�ʱ��:  Mar 13, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.invoice.action;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gmcc.boss.selfsvc.common.BaseAction;
/**
 * <��Ʊ��ӡ>
 * <������ϸ����>
 * 
 * @author  YKF38827
 * @version  [Mar 13, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ValiInvoicePrintHubAction extends BaseAction
{

    /**
     * ע������
     */
    private static final long serialVersionUID = 1L;
   
    private String valiMess;
 
    /**
     * <��ⷢƱ��ӡ��״̬>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String validatePrinTerm(){
        String gotoJsp = "";
        if(null==valiMess||valiMess.length()==0){   
            //����Ƿ��������֤
            HttpSession session = this.getRequest().getSession();
            session.removeAttribute("ifSendRrandPwd");
            gotoJsp = "success";
        }else{
            this.getRequest().setAttribute("errorMessage", valiMess);
            gotoJsp = "error";
        }
        return gotoJsp;
    }
    
   
 
    public String getValiMess()
    {
        return valiMess;
    }
    public void setValiMess(String valiMess)
    {
        this.valiMess = valiMess;
    }

   
}
