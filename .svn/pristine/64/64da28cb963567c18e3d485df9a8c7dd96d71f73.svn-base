package com.gmcc.boss.selfsvc.baseService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.RecOpenAndStopSubsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 停开机处理类
 * 
 * @author xkf29026
 * 
 */
public class RecOpenAndStopSubsAction extends BaseAction
{
    // modify begin by xkf29026 2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(RecOpenAndStopSubsAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 请求方法名
    private String actionCase;
    
    // 页面转向
    private String forward;
    
    // 操作类型
    private String operType;
    
    // 错误信息
    private String errormessage;
    
    // 成功信息
    private String successMessage;
    
    // 调用停开机接口
    private RecOpenAndStopSubsBean recOpenAndStopSubsBean;
    
    private String isInput;
    
    private String selectReason;
    
    // 默认执行方法
    public String execute() throws Exception
    {
        if (Constants.BUSITYPE_STOPOPEN.concat("Page").equals(actionCase)
                || Constants.BUSITYPE_STOPOPEN.equals(actionCase))
        {
            recOpenAndStopSubs();
        }
        else if("toValidate".equalsIgnoreCase(actionCase))
        {            
//            String selectReasonFlag = (String) PublicCache.getInstance().getCachedData("SH_SELECTREASON_FLAG");
//
//            if("1".equals(selectReasonFlag) )
//            {
//                return "toValidateNX";
//            }
            // modify begin hWX5316476 2014-07-02 OR_huawei_201407_85_山东_营业厅全业务流程优化-业务分流(自助终端)_停开机去掉短信验证码
            // 是否关闭停开机短信随机码验证 
            String closeSMSChk = (String) PublicCache.getInstance().getCachedData(Constants.CLOSESMSCHK_STOPOPEN_SWITCH);
            
            // 查看停机业务是否关闭短信验证(1:关闭短信验证 0：保留短信验证)
            if("1".equalsIgnoreCase(closeSMSChk))
            {
                recOpenAndStopSubs();
            }
            else
            {
                return "toValidate";
            }
            // modify end hWX5316476 2014-07-02 OR_huawei_201407_85_山东_营业厅全业务流程优化-业务分流(自助终端)_停开机去掉短信验证码
        }
        return forward;
    }
    
    /**
     * 停开机业务处理函数
     * 
     * @return
     */
    private void recOpenAndStopSubs()
    {
        logger.debug("recOpenAndStopSubs Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 判断当前菜单是否为空
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // 设置停开机类型
        String stoptype = "StopSubs";
        
        if ("1".equals(operType))
        {
            stoptype = "OpenSubs";
        }
        
        String reason = "";
        
        String selectReasonFlag = (String) PublicCache.getInstance().getCachedData("SH_SELECTREASON_FLAG");

        if ("1".equals(selectReasonFlag))
        {
            String group = "StopReason";
            
            if("1".equals(operType))
            {
                group = "OpenReason";
            }
            
            reason = getReasonName(group, selectReason);
        }
 
        Map result = recOpenAndStopSubsBean.stopOpenSubs(terminalInfoPO, customer, curMenuId, stoptype, reason);
        if (result != null && result.size() > 1)
        {
            // 成功信息
            String msg = (SSReturnCode.ADD_REC.equals(operType) ? "开机申请成功!" : "停机申请成功!");
            
            // 设置成功信息
            setSuccessMessage(msg);
            
            // 转向成功页面
            setForward("success");
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_STOPOPEN, "0", "0", "0", msg);
        }
        else
        {
            // 设置错误信息
            setErrormessage((String)result.get("returnMsg"));
            
            // 转向错误页面
            setForward("error");
            
            // 记录错误信息
            this.createRecLog(Constants.BUSITYPE_STOPOPEN, "0", "0", "0", (String)result.get("returnMsg"));
        }
        logger.debug("recOpenAndStopSubs End!");
    }    
    
    /**
     * 取原因名称
     * 
     * @param group 停机原因或者开机原因
     * @param reasonID 原因ID
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getReasonName(String group, String reasonID)
    {
        String reasonName = "";
        List<DictItemPO> reasonList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(group);
        if (reasonList != null)
        {
            for (int i = 0; i < reasonList.size(); i++)
            {
                DictItemPO dictItemPO = reasonList.get(i);
                if (reasonID.equals(dictItemPO.getDictid()))
                {
                    reasonName = dictItemPO.getDictname();
                    break;
                }
            }
        }
        
        return reasonName;
    }
    
    public String selectReason()
    {
        return "selectReason";
    }
    
    public String getActionCase()
    {
        return actionCase;
    }
    
    public void setActionCase(String actionCase)
    {
        this.actionCase = actionCase;
    }
    
    public String getForward()
    {
        return forward;
    }
    
    public void setForward(String forward)
    {
        this.forward = forward;
    }
    
    public String getOperType()
    {
        return operType;
    }
    
    public void setOperType(String operType)
    {
        this.operType = operType;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public RecOpenAndStopSubsBean getRecOpenAndStopSubsBean()
    {
        return recOpenAndStopSubsBean;
    }
    
    public void setRecOpenAndStopSubsBean(RecOpenAndStopSubsBean recOpenAndStopSubsBean)
    {
        this.recOpenAndStopSubsBean = recOpenAndStopSubsBean;
    }

    public String getIsInput()
    {
        return isInput;
    }

    public void setIsInput(String isInput)
    {
        this.isInput = isInput;
    }

    public String getSelectReason()
    {
        return selectReason;
    }

    public void setSelectReason(String selectReason)
    {
        this.selectReason = selectReason;
    }
}
