package com.customize.cq.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.ComboInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 套餐信息查询
 * 
 * @author xkf29026
 * 
 */
public class ComboInfoAction extends BaseAction
{
    
//    /**
//     * 序列化
//     */
//    private static final long serialVersionUID = 1L;
//    
//    private static Log logger = LogFactory.getLog(ComboInfoAction.class);
//    
//    /**
//     * 当前菜单id
//     */
//    // begin zKF66389 findbus清零
//    private String curMenuId = "";
//    // end zKF66389 findbus清零
//    
//    // 调用接口bean
//    private ComboInfoBean qryComboInfoBean;
//    
//    // begin zKF66389 findbus清零
//    // 结果标题
//    private String[] serviceTitle;
//    // end zKF66389 findbus清零
//    
//    // 设置结果
//    private List result;
//    
//    // 错误信息
//    private String successMessage;
//    
//    public String qryComboInfo()
//    {
//        // 开始记录日志
//        logger.debug("qryComboInfo start...");
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//          
//        //modify begin l00190940 2011-11-16 套餐信息查询（重庆）
//        // 当前月份
//        String month = CommonUtil.getLastMonth("yyyyMM", 0);
//        
//        // 定向到错误页面
//        String forward = "error";
//  
//        // 查询套餐信息
//        Map mapResult = qryComboInfoBean.qryComboInfo(terminalInfoPO, customer, curMenuId, month);
//        
//        if (mapResult != null && mapResult.size() > 1)
//        {
//        	CRSet cr = (CRSet)(mapResult.get("returnObj"));
//                
//            // 定义结果标题名称
//            String[] titles = {"优惠编码", "累计量名称", "业务类型", "赠送总量", "已经使用", "剩余量", "优惠名称"};
//                
//            // 设置标题名称
//            setServiceTitle(titles);
//            
//            List listOuter = new ArrayList();
//            // 拼装数据
//            for (int i = 0; i < cr.GetRowCount(); i++)
//            {
//                String s = cr.GetValue(i, 0) + "," + cr.GetValue(i, 1) + "," + cr.GetValue(i, 2) + ","
//                        + cr.GetValue(i, 3) + "," +cr.GetValue(i, 4) + "," +cr.GetValue(i, 5) + "," +cr.GetValue(i, 6);
//                    
//                listOuter.add(s);
//            }
//        //modify end l00190940 2011-11-16 套餐信息查询（重庆）
//                
//            if (listOuter != null && listOuter.size() > 0)
//            {
//                result = new ArrayList();
//                List listInner = null;
//                for (int i = 0; i < listOuter.size(); i++)
//                {
//                    listInner = new ArrayList();
//                    
//                    String[] content = listOuter.get(i).toString().split(",");
//                    for (int j = 0; j < content.length; j++)
//                    {
//                        listInner.add(content[j]);
//                    }
//                    
//                    result.add(listInner);
//                }
//                
//            }
//
//            forward = "queryTcService";
//                
//            // 创建成功日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
//        }
//        //add begin l00190940 2011-11-17 套餐信息查询（重庆）
//        else if (mapResult != null)
//        {
//        	String returnMsg = (String)mapResult.get("returnMsg");
//        	
//        	setSuccessMessage(returnMsg);
//        	
//        	// 创建日志
//        	this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", returnMsg);
//        	
//        	return "noTcYet";
//        }
//        //add end l00190940 2011-11-17 套餐信息查询（重庆）
//        else
//        {               
//            this.getRequest().setAttribute("errormessage", "套餐信息查询失败!");
//               
//            // 创建错误日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", "业务信息查询:套餐信息查询失败!");
//        }
//       
//        logger.debug("qryComboInfo End ...");
//        return forward;
//    }
//    
//    
////    public String getError()
////    {
////        return error;
////    }
////    
////    public void setError(String error)
////    {
////        this.error = error;
////    }
//    
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//
//	public ComboInfoBean getQryComboInfoBean()
//    {
//        return qryComboInfoBean;
//    }
//    
//    public void setQryComboInfoBean(ComboInfoBean qryComboInfoBean)
//    {
//        this.qryComboInfoBean = qryComboInfoBean;
//    }
//    
//    public String[] getServiceTitle() {
//		return serviceTitle;
//	}
//
//	public void setServiceTitle(String[] serviceTitle) {
//		this.serviceTitle = serviceTitle;
//	}
//
//	public List getResult()
//    {
//        return result;
//    }
//    
//    public void setResult(List result)
//    {
//        this.result = result;
//    }
//
//	public String getSuccessMessage() 
//	{
//		return successMessage;
//	}
//
//	public void setSuccessMessage(String successMessage) 
//	{
//		this.successMessage = successMessage;
//	}
}
