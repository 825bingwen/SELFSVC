package com.gmcc.boss.selfsvc.serviceinfo.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.PukCodeBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * puk码查询
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, May 31, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PukCodeAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(PukCodeAction.class);
    
    // 当前菜单id
    private String curMenuId = "";
    
    //PUK码
    private String pukStr;
    
    //错误消息
    private String error;
    
    private Map result = null;
    
    private String title = null;
    
    //调用接口bean
    private PukCodeBean pukCodeBean;
    
    /**
     * 查询PUK码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryPukCode()
    {
        // 记录日志
        logger.debug("queryPukCode Starting...");
        
        // 定义错误指向页面
        String froward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // PUK码查询
        result = pukCodeBean.queryPUK(terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 0)
        {
            title = "PIN1,PIN2,PUK1,PUK2";
            
            String pukStr = (String)result.get("returnObj");

            String pin1 = pukStr.split("\r\n")[0].split("\t")[0]+" ";
            String puk1 = pukStr.split("\r\n")[0].split("\t")[1]+" ";
            String pin2 = pukStr.split("\r\n")[1].split("\t")[0]+" ";
            String puk2 = pukStr.split("\r\n")[1].split("\t")[1]+" ";
            
            result.put(pin1.split(":")[0], pin1.split(":")[1]);
            result.put(puk1.split(":")[0], puk1.split(":")[1]);
            result.put(pin2.split(":")[0], pin2.split(":")[1]);
            result.put(puk2.split(":")[0], puk2.split(":")[1]);
            setPukStr(pukStr);
            froward = "qryPukQry";
            
            // 插入成功日志
            this.createRecLog(Constants.BUSITYPE_PUKITEM, "0", "0", "0", "业务信息查询:PUK码查询成功!");
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "PUK码查询失败!");
            
            // 插入失败日志
            this.createRecLog(Constants.BUSITYPE_PUKITEM, "0", "0", "1", "业务信息查询:PUK码查询失败!");
        }
        logger.debug("queryPukCode End!");
        return froward;
    }
    
    public PukCodeBean getPukCodeBean()
    {
        return pukCodeBean;
    }
    
    public void setPukCodeBean(PukCodeBean pukCodeBean)
    {
        this.pukCodeBean = pukCodeBean;
    }
   
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPukStr()
    {
        return pukStr;
    }

    public void setPukStr(String pukStr)
    {
        this.pukStr = pukStr;
    }

    public Map getResult()
    {
        return result;
    }

    public void setResult(Map result)
    {
        this.result = result;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    
}
