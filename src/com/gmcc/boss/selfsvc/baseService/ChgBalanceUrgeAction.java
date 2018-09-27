package com.gmcc.boss.selfsvc.baseService;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ChgBalanceUrgeBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 余额提醒选择提醒值和对提醒值进行设置
 * @author xkf29026
 *
 */
public class ChgBalanceUrgeAction extends BaseAction
{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin by xkf29026 2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(ChgBalanceUrgeAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
    // 当前菜单id
    private String curMenuId = "";
    
    //当前页数
    private String pagecount;
    
    // 错误信息
    private String errormessage;
    
    //成功信息
    private String successMessage;
    
    // 余额提醒类型
    private String balanceAwake;
    
    //操作类型
    private String operType;
    
    //余额提醒值容器
    private Vector balanceVector;
    
    // 调用接口Bean
    private ChgBalanceUrgeBean chgBalanceUrgeBean;
    
    /**
     * 设置余额提醒值页面
     * 
     * @return
     */
    public String balanceUrgePage()
    {
        // 记录日志
        logger.debug("balanceUrgePage starting");
        
        if(pagecount != null)
        {
            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
        }
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用接口获取余额提醒信息
        Map result = chgBalanceUrgeBean.qryBalanceNotice(terminalInfoPO, customer, curMenuId);        
        
        if (result == null || result.size() <= 0)
        {
            // 设置错误提示信息
            setErrormessage("查询当前余额提醒值失败，请稍后再试。带来不便，敬请原谅。");
            
            String forward = "error";
            
            // 创建失败日志
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "1", "查询当前余额提醒值失败，请稍后再试。带来不便，敬请原谅。");
            
            return forward;
        }
        else
        {
            CTagSet tagset = (CTagSet)result.get("returnObj");
            String prepayType = tagset.GetValue("prepay_type");
            String credit = tagset.GetValue("credit");/////////////////////////modified by l00190940 2011-08-15
            
            //modify begin fwx439896 2017-05-22  OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书 
            //能开返回的付费方式为汉字  后付费 和先付费
            if ("0".equals(prepayType)||"后付费".equals(prepayType))
            {
            //modify begin fwx439896 2017-05-22  OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书 
              
            	// 设置错误提示信息
            	setErrormessage("后付费用户不能设置余额提醒值。");
                
                String forward = "error";
                
                // 创建失败日志
                this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "0", "后付费用户不能设置余额提醒值。");
                
                return forward;
            }
            else
            {
            	if ("0".equals(credit) || "".equals(credit))
            	{
            		// 设置错误提示信息
                	setErrormessage("您尚未开通余额提醒服务。");
                	
                	String forward = "noBalanceUrge";
                	return forward;
            	}
            	else
            	{
            		setSuccessMessage(CommonUtil.fenToYuan(credit));
            		String forward = "changeOrCancel";
            		return forward;
            	}
            }

        }
    }
    
    /**
     * 进入余额提醒值选择界面
     */
    public String balanceUrgeSelect()
    {
    	logger.debug("balanceUrgeSelect Starting...");
    	
    	// 获取session
        HttpSession session = getRequest().getSession();
        
        // add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        /**
         * 此处getDictItem_chgBalanceUrge中的getDictItem为根据groupid获取字典信息的公用方法，因使用的是测试数据，所以后面加上了_chgBalanceUrge，
         * 当使用真正的接口获得数据时可使用getDictItem
         */
        Map result = chgBalanceUrgeBean.getDictItem_chgBalanceUrge(terminalInfoPO,
                customer,
                curMenuId,
                Constants.LEFT_MONEY_NOTIFS);
        
        if (result != null && result.size() > 0)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            if (crset != null)
            {
                Vector balanceVector = new Vector();
                DictItemVO dictitemVO = null;
                
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    dictitemVO = new DictItemVO(crset.GetValue(i, 0), crset.GetValue(i, 1), Constants.LEFT_MONEY_NOTIFS);
                    
                    balanceVector.add(dictitemVO);
                }
                //设置余额提醒值
                setBalanceVector(balanceVector);
            }
        }
        else
        {
            // 设置错误提示信息
        	setErrormessage("查询可选余额提醒值失败!");
        	
            String forward = "error";
            
            // 创建失败日志
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "1", "查询可选余额提醒值失败!");
            
            return forward;
        }
    	logger.debug("balanceUrgeSelect end!");
    	
    	// 返回选择余额提醒值页面
        return "selectBalancePage";
    }
    
    /**
     * 余额提醒和设置余额提醒值
     */
    public String balanceUrgeDef()
    {
        logger.debug("balanceUrgeDef Starting...");
        
        String forward = null;
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 取消余额提醒
        if ("0".equals(operType))
        {
            balanceAwake = "0";
        }
        
        // 调用接口设置余额提醒值        
        String credit = balanceAwake;
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equals(province))
        {
        	credit = CommonUtil.yuanToFen(credit);
        }
        
        Map result = chgBalanceUrgeBean.setBalanceNotice(terminalInfoPO, customer, curMenuId, credit, operType);
        if (result != null && result.size() > 0)
        {
            String msg = "";
            if ("0".equals(operType))
            {
                msg = "取消余额提醒成功!";
            }
            else if ("1".equals(operType))
            {
                msg = "开通余额提醒成功!";
            }
            else if ("2".equals(operType))
            {
                msg = "变更余额提醒值成功!";
            }
            
            // 设置成功信息
            setSuccessMessage(msg);
            
            forward = "success";
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE,
                    "0",
                    "0",
                    "0",
                    msg);
        }
        else
        {
            String msg = "";
            if ("0".equals(operType))
            {
                msg = "取消余额提醒失败!";
            }
            else if ("1".equals(operType))
            {
                msg = "开通余额提醒失败!";
            }
            else if ("2".equals(operType))
            {
                msg = "变更余额提醒值失败!";
            }
            
            // 设置错误消息
            setErrormessage(msg);
            
            // modify begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            getRequest().setAttribute("backStep", "-1");
            
            forward = "error";
            
            // 创建失败日志
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE,
                    "0",
                    "0",
                    "1",
                    msg);
            // modify end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        }
        logger.debug("balanceUrgeDef End!");
        return forward;
    }
   
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public ChgBalanceUrgeBean getChgBalanceUrgeBean()
    {
        return chgBalanceUrgeBean;
    }
    
    public void setChgBalanceUrgeBean(ChgBalanceUrgeBean chgBalanceUrgeBean)
    {
        this.chgBalanceUrgeBean = chgBalanceUrgeBean;
    }
    
    public String getBalanceAwake()
    {
        return balanceAwake;
    }
    
    public void setBalanceAwake(String balanceAwake)
    {
        this.balanceAwake = balanceAwake;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public Vector getBalanceVector()
    {
        return balanceVector;
    }

    public void setBalanceVector(Vector balanceVector)
    {
        this.balanceVector = balanceVector;
    }

    public String getPagecount()
    {
        return pagecount;
    }

    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getOperType() {
		return operType;
	}

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setOperType(String operType) {
		this.operType = operType;
	}
}
