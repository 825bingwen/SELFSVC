package com.customize.hub.selfsvc.backInfo.action;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.BackInfoBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class BackInfoAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(BackInfoAction.class);
    private static Log log = LogFactory.getLog(BackInfoAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    // 开始日期
    private String startDate;
    
    // 当前菜单
    private String telnum;
    
    // 结束日期
    private String endDate;
    
    // 当前菜单
    private String curMenuId;
    
    // 错误信息
    private String errormessage = "";
    
    // 接口调用
    private BackInfoBean backInfoBean;
    
    // 查询结果
    private List resList;
    
    // 查询类型标志 0返回信息，1查询返回中止信息
    private String flag;
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String QueryAllBackInfo()
    {
        // 记录开始日志
        log.debug("QueryAllBackInfo...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 客户手机号
        String servnumber = customer.getServNumber();
        
        // 定位到错误页面
        String forward = "error";
        
        // 返回信息查询
        flag = "0";
        if(startDate==null||"".equals(startDate)){
        GregorianCalendar gc=new GregorianCalendar();
        startDate=Integer.valueOf(gc.get(gc.YEAR)).toString()+(gc.get(gc.MONTH)<10?"0"+gc.get(gc.MONTH):gc.get(gc.MONTH));
        endDate=Integer.valueOf(gc.get(gc.YEAR)).toString()+((gc.get(gc.MONTH)+1)<10?"0"+(gc.get(gc.MONTH)+1):(gc.get(gc.MONTH)+1));
        }
        Map mapResult = backInfoBean.queryBackInfo(startDate+"01",
                backInfoBean.getDateContainsLastDay(endDate),
                terminalInfoPO,
                customer,
                curMenuId,
                flag);
        
        resList = (List)mapResult.get("returnObj");
        if (resList.size() > 0)
        {
            forward = "QueryAll";
            
            // 创建成功日志记录
            // this.createRecLog(Constants.BUSITYPE_BackInfoQry, "0", "0", "0", "返还信息查询:返还信息查询成功!");
        }
        else
        {
            
            // 从session中移除清单数据
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            
            // 记录错误消息
            setErrormessage("错误消息：" + mapResult.get("returnMsg"));
            
            // 判断错误消息是否是提示信息
            if (errormessage != null && errormessage.length() > 0)
            {
                if (errormessage.indexOf("information") >= 0)
                {
                    setErrormessage("无记录，请返回继续查询");
                }
            }
            forward = "QueryAll";
            // 创建错误日志记录
            // this.createRecLog(Constants.BUSITYPE_BackInfoQry, "0", "0", "1", "返还信息查询:返还信息查询失败!");
        }
        
        return forward;
    }
    
    public String backInfoDateInput()
    {
        log.debug("backInfoDateInput...");
        return "backInfoDateInput";
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String QueryAllBackInfoByDate()
    {
        log.debug("QueryAllBackInfoByDate...");
        QueryAllBackInfo();
        
        return "QueryAllByDate";
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String QueryAbortBackInfo()
    {
        // 记录开始日志
        log.debug("QueryAbortBackInfo...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 客户手机号
        String servnumber = customer.getServNumber();
        
        // 定位到错误页面
        String forward = "error";
        
        // 返回信息查询
        flag = "1";
        if(startDate==null||"".equals(startDate)){
            GregorianCalendar gc=new GregorianCalendar();
            startDate=(gc.get(gc.YEAR)-1)+((gc.get(gc.MONTH)+1)<10?"0"+(gc.get(gc.MONTH)+1):Integer.valueOf((gc.get(gc.MONTH)+1)).toString());
            endDate=(gc.get(gc.YEAR))+((gc.get(gc.MONTH)+1)<10?"0"+(gc.get(gc.MONTH)+1):Integer.valueOf((gc.get(gc.MONTH)+1)).toString());
            
        }
        Map mapResult = backInfoBean.queryBackInfo(startDate+"01",
                backInfoBean.getDateContainsLastDay(endDate),
                terminalInfoPO,
                customer,
                curMenuId,
                flag);
        
        resList = (List)mapResult.get("returnObj");
        if (resList.size() > 0)
        {
            forward = "QueryAll";
            
            // 创建成功日志记录
            // this.createRecLog(Constants.BUSITYPE_BackInfoQry, "0", "0", "0", "返还信息查询:返还信息查询成功!");
        }
        else
        {
            
            // 从session中移除清单数据
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            
            // 记录错误消息
            setErrormessage("错误消息：" + mapResult.get("returnMsg"));
            
            // 判断错误消息是否是提示信息
            if (errormessage != null && errormessage.length() > 0)
            {
                if (errormessage.indexOf("information") >= 0)
                {
                    setErrormessage("无记录，请返回继续查询");
                }
            }
            forward = "QueryAll";
            // 创建错误日志记录
            // this.createRecLog(Constants.BUSITYPE_BackInfoQry, "0", "0", "1", "返还信息查询:返还信息查询失败!");
        }
        
        return "QueryAbort";
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String QueryAbortBackInfoByDate()
    {
        log.debug("QueryAbortBackInfoByDate...");
        
        QueryAbortBackInfo();
        return "QueryAbortByDate";
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStartDate()
    {
        return startDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTelnum()
    {
        return telnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getEndDate()
    {
        return endDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public BackInfoBean getBackInfoBean()
    {
        return backInfoBean;
    }
    
    public void setBackInfoBean(BackInfoBean backInfoBean)
    {
        this.backInfoBean = backInfoBean;
    }
    
    public List getResList()
    {
        return resList;
    }
    
    public void setResList(List resList)
    {
        this.resList = resList;
    }
    
    public String getFlag()
    {
        return flag;
    }
    
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
    
}
