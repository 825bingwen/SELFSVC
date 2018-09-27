/*
 * 文 件 名:  InvoicePrint.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: YKF38827
 * 修改时间:  Mar 13, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.invoice.action;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gmcc.boss.selfsvc.common.BaseAction;
/**
 * <发票打印>
 * <功能详细描述>
 * 
 * @author  YKF38827
 * @version  [Mar 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ValiInvoicePrintHubAction extends BaseAction
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
   
    private String valiMess;
 
    /**
     * <检测发票打印机状态>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String validatePrinTerm(){
        String gotoJsp = "";
        if(null==valiMess||valiMess.length()==0){   
            //清除是否随机码验证
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
