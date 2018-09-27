package com.gmcc.boss.selfsvc.baseService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.FeeMessageBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ShMenuNode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 基础业务办理费用提示信息页面
 * @author xkf29026
 *
 */
public class FeeMessageAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(FeeMessageAction.class);

    //序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    //受理业务类型
    private String sType;
    
    private String isInput;
    
    private String nCode;
    
    //操作类型
    private String operType;

    //提示信息
    private String confirmMsg;
    
    //错误信息
    private String errormessage;

    //调用接口
    private FeeMessageBean feeMessageBean;

    /**
     * 业务受理与网厅保持一致，先提示费用信息，等用户确认后，再提交业务
     * @return
     */
    public String displayFeeMessage() throws IOException
    {
        //开始记录日志
        logger.debug("displayFeeMessage start");
        
        this.getRequest().setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        PrintWriter out = this.getResponse().getWriter();
        
        //获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //转向费用提示信息页面
        String forward = "feemessage";
        
        //判断业务类型是否为停开机
        if ("SHstopOpen".equalsIgnoreCase(sType))
        {
            if ("0".equals(operType))
            {
                setOperType("停机");
                
                // modify begin by wWX217192 on 20140528 OR_huawei_201405_876 营业厅全业务流程优化-业务分流(自助终端)_停开机业务细节优化 
                String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
                
                if(Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
                {
                    // 山东停机弹出框的提示信息
                    setConfirmMsg("尊敬的客户，您好！停机当月套餐费正常收取，次账期开始收取停机保号费，" +
                            "5元/月或0.16元/天，停机期间原有协议消费正常收取，请确保余额充足。" +
                            "如果您开通了欢乐家庭业务，停机期间该业务功能费正常收取。详情咨询10086。您确定要办理" + operType
                            + "业务吗？点击\"<strong>确认</strong>\"按钮办理，点击\"<strong>取消</strong>\"取消本次操作。");
                }
                else
                {
                    // 其它省份停机弹出框提示信息
                    setConfirmMsg("您确定要办理" + operType
                            + "业务吗？点击\"<strong>确认</strong>\"按钮办理，点击\"<strong>取消</strong>\"取消本次操作。");
                }
            }
            else if ("1".equals(operType))
            {
                setOperType("开机");
                
                // 开机弹出框的提示信息
                setConfirmMsg("您确定要办理" + operType
                		+ "业务吗？点击\"<strong>确认</strong>\"按钮办理，点击\"<strong>取消</strong>\"取消本次操作。");
                // modify end by wWX217192 on 20140528 OR_huawei_201405_876 营业厅全业务流程优化-业务分流(自助终端)_停开机业务细节优化
            }
            
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
            out.flush();
            return null;
        }
        
        //判断业务类型是否为余额提醒
        if ("SHBalanceUD".equalsIgnoreCase(sType))
        {
            if ("0".equals(operType))
            {
                setOperType("取消");
            }
            else if ("1".equals(operType))
            {
                setOperType("开通");
            }
            
            setConfirmMsg("您确定要" + operType
                    + "余额提醒业务吗？点击\"<strong>确认</strong>\"按钮开通，点击\"<strong>取消</strong>\"取消本次操作。");
            
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
        }
        
        //判断业务类型是否为呼叫转移
        if ("SHCallTransfer".equalsIgnoreCase(sType))
        {
            if ("0".equals(operType))
            {
                setOperType("取消");
            }
            else if ("1".equals(operType))
            {
                setOperType("开通");
            }
            
            setConfirmMsg("您确定要" + operType
                    + "呼叫转移业务吗？点击\"<strong>确认</strong>\"按钮开通，点击\"<strong>取消</strong>\"取消本次操作。");
            
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
        }
        
        //判断业务类型是否为短信互转
        if ("SHMsgTransfer".equalsIgnoreCase(sType))
        {
            if ("0".equals(operType))
            {
                setOperType("取消");
            }
            else if ("1".equals(operType))
            {
                setOperType("开通");
            }
            
            setConfirmMsg("您确定要" + operType
                    + "短信呼转业务吗？点击\"<strong>确认</strong>\"按钮开通，点击\"<strong>取消</strong>\"取消本次操作。");
            
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
        }
        
        //判断业务类型是否为号簿管家
        if ("SHBookManager".equalsIgnoreCase(sType))
        {
            if ("0".equals(operType))
            {
                setOperType("取消");
            }
            else if ("1".equals(operType))
            {
                setOperType("开通");
            }
            
            setConfirmMsg("您确定要" + operType
                    + "号簿管家业务吗？点击\"<strong>确认</strong>\"按钮开通，点击\"<strong>取消</strong>\"取消本次操作。");
            
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
        }
        
        //当前菜单不存在则设为空
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        //调用接口查询费用信息
        Map result = feeMessageBean.queryFeeMessage(terminalInfoPO,customer,curMenuId,nCode);
        if (result == null || result.size() <= 0 )
        {
            setErrormessage("业务受理失败，请稍后再试。带来不便，敬请原谅。");
            forward = "error";
            
            this.createRecLog(sType, "0", "0", "1","办理业务时，获取确认信息失败");
        }
        else
        {
            if ("0".equals(operType))
            {
                setOperType("退订");
            }
            else if ("1".equals(operType))
            {
                setOperType("开通");
            }
            
            // 菜单名称
            String curMenuId = (String)getRequest().getAttribute(Constants.CUR_MENUID);
            Map menuMap = (Map)session.getAttribute(Constants.CUR_MENUMAP);
            ShMenuNode curMenunode = (ShMenuNode)menuMap.get(curMenuId);
            String menuName = curMenunode.menuItem.m_MenuName;
            
            // 后台返回的费用信息，可能为空
            CTagSet tagset = (CTagSet)result.get("returnObj");
            String msg = "";
            if (tagset != null)
            {
                msg = tagset.GetValue("DESC");
            }
            
            if (msg == null || msg.trim().length() == 0)
            {
                msg = "";
            }
            else
            {
                msg += "，";
            }
            
            setConfirmMsg("您即将" + operType + menuName + "业务，" + msg + "点击\"<strong>确认</strong>\"按钮确定" + operType
                    + "，点击\"<strong>取消</strong>\"按钮取消" + operType + "。中国移动");
            out.write(confirmMsg+","+sType+","+operType+","+isInput);
            
//            createSHRecLog(cOperator, servnumber, sType, "0", "0", "0", termid, touchOid, "1", "办理业务时，获取确认信息成功");
        }
        logger.debug("displayFeeMessage end!");
        
        return forward;
    }
    
    public String getOperType()
    {
        return operType;
    }

    public void setOperType(String operType)
    {
        this.operType = operType;
    }

    public String getConfirmMsg()
    {
        return confirmMsg;
    }

    public void setConfirmMsg(String confirmMsg)
    {
        this.confirmMsg = confirmMsg;
    }

    public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getSType()
    {
        return sType;
    }

    public void setSType(String type)
    {
        sType = type;
    }
    
    public FeeMessageBean getFeeMessageBean()
    {
        return feeMessageBean;
    }

    public void setFeeMessageBean(FeeMessageBean feeMessageBean)
    {
        this.feeMessageBean = feeMessageBean;
    }

    public String getNCode()
    {
        return nCode;
    }

    public void setNCode(String code)
    {
        nCode = code;
    }

    public String getIsInput()
    {
        return isInput;
    }

    public void setIsInput(String isInput)
    {
        this.isInput = isInput;
    }
}
