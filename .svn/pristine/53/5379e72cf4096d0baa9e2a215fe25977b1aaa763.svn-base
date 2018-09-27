package com.customize.sd.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.ComboInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 套餐信息查询
 * 
 * @author xkf29026
 * 
 */
public class ComboInfoAction extends BaseAction
{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin by xkf29026 2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(ComboInfoAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
    /**
     * 当前菜单id
     */
    private String curMenuId = "";
    
    // 调用接口bean
    private ComboInfoBean qryComboInfoBean;
    
    // 结果标题
    private String[] servicetitle;
    
    // 设置结果
    private List result;
    
    // 错误信息
//    private String error;
    
    public String qryComboInfo()
    {
        // 开始记录日志
        logger.debug("qryComboInfo start...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
        // 定向到错误页面
        String forward = "error";
  
        // 查询套餐信息
        Map mapResult = qryComboInfoBean.qryComboInfo(terminalInfoPO, customer, curMenuId);
        
        if (mapResult != null && mapResult.size() > 0)
        {
            Vector retData = (Vector)(mapResult.get("returnObj"));
                
            // 定义结果标题名称
            String[] titles = {"套餐名称", "类型", "包月量", "剩余量"};
                
            // 设置标题名称
            setServicetitle(titles);
                
            CRSet cr = (CRSet)(retData.get(1));
                
            List listOuter = new ArrayList();
            // 拼装数据
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                String s = cr.GetValue(i, 0) + "," + cr.GetValue(i, 1) + "," + cr.GetValue(i, 2) + ","
                        + cr.GetValue(i, 3);
                    
                listOuter.add(s);
            }
                
            if (listOuter != null && listOuter.size() > 0)
            {
                result = new ArrayList();
                List listInner = null;
                for (int i = 0; i < listOuter.size(); i++)
                {
                    listInner = new ArrayList();
                    
                    String[] content = listOuter.get(i).toString().split(",");
                    for (int j = 0; j < content.length; j++)
                    {
                        listInner.add(content[j]);
                    }
                    
                    result.add(listInner);
                }
                
            }

            forward = "queryTcService";
                
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
        }
        else
        {               
            this.getRequest().setAttribute("errormessage", "套餐信息查询失败!");
               
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", "业务信息查询:套餐信息查询失败!");
        }
       
        logger.debug("qryComboInfo End ...");
        return forward;
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
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public ComboInfoBean getQryComboInfoBean()
    {
        return qryComboInfoBean;
    }
    
    public void setQryComboInfoBean(ComboInfoBean qryComboInfoBean)
    {
        this.qryComboInfoBean = qryComboInfoBean;
    }
    
    public String[] getServicetitle()
    {
        return servicetitle;
    }
    
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }
    
    public List getResult()
    {
        return result;
    }
    
    public void setResult(List result)
    {
        this.result = result;
    }
}
