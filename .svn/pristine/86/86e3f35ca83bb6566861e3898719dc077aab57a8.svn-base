/*
* @filename: RectelInfoAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  同组业务受理，不带有附加属性
* @author: g00140516
* @de:  2012/07/10 
* @description: 
* @remark: create g00140516 2012/07/10 R003C12L07n01 OR_NX_201205_649
*/
package com.gmcc.boss.selfsvc.privilege.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 同组业务受理，不带有附加属性
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/10
 * @see  
 * @since  
 */
public class PrivilegeGroupAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static final Log logger = LogFactory.getLog(PrivilegeGroupAction.class);
    
    /**
     * 菜单ID
     */
    private String curMenuId = "";
    
    /**
     * 套餐对应的字典表中的groupid
     */  
    private String groupid = "";
    
    /**
     * 本月NCode
     */
    private String currNCode = "";
    
    /**
     * 下月NCode
     */
    private String nextNCode = "";
    
    /**
     * 本月套餐名称
     */
    private String currDesp = "未开通";
    
    /**
     * 下月套餐名称
     */
    private String nextDesp = "未开通";
    
    /**
     * 用户选择的ncode
     */
    private String newNCode = "";
    
    /**
     * 用户选择的套餐名称
     */
    private String newPrivName = "";
    
    /**
     * 用户选择的套餐描述
     */
    private String newPrivDesp = "";
    
    /**
     * 生效方式
     */
    private String effectType = "";
    
    /**
     * 成功提示信息
     */
    private String successMessage = "";
    
    /**
     * 套餐列表
     */
    private List<DictItemPO> privilegeList = null;
    
    /**
     * 生效方式
     */
    private List<DictItemPO> effectTypeList = null;
    
    private ReceptionBean receptionBean = null;
    
    /**
     * 展示同组业务列表
     * 
     * @return
     * @see 
     */
    public String qryPrivilegeGroup()
    {
        String forward = "error";
        
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 根据groupid查询套餐列表
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "没有查询到相关的套餐信息");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "没有查询到相关的套餐信息");
            
            logger.error("没有查询到相关的套餐信息");
            
            return forward;
        }
        
        // 查询用户当前办理情况
        DictItemPO dictItemPO = privilegeList.get(0);
        String nCode = dictItemPO.getDictid();
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, nCode, "QRY", "", "", curMenuId);
        
        // 正常
        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        if (checkResult(result))
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        {
            CTagSet tagSet = (CTagSet) result.getReturnObject();
            
            if (null != tagSet)
            {
                // 当月ncode
                currNCode = tagSet.GetValue("curncode");
                
                // 下月ncode
                nextNCode = tagSet.GetValue("nextncode");               
            }
            
            if ((null != currNCode && !"".equals(currNCode.trim())) 
                    || (null != nextNCode && !"".equals(nextNCode.trim())))
            {
                for (int i = 0; i < privilegeList.size(); i++)
                {
                    dictItemPO = privilegeList.get(i);
                    
                    if (dictItemPO.getDictid().equalsIgnoreCase(currNCode))
                    {
                        // 当月业务名称
                        currDesp = dictItemPO.getDictname();
                    }
                    
                    if (dictItemPO.getDictid().equalsIgnoreCase(nextNCode))
                    {
                        // 下月业务名称
                        nextDesp = dictItemPO.getDictname();
                    }
                }
            }
            
            forward = "qrySuccess";
        }
        else
        {
            request.setAttribute("errormessage", "查询套餐当前办理情况失败");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "查询套餐当前办理情况失败");
            
            logger.error("查询套餐当前办理情况失败");
        }
        
        return forward;
    }
    
    /**
     * 校验返回值
     * <功能详细描述>
     * @param result
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean checkResult(ReturnWrap result)
    {
        return (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        || (result != null && result.getErrcode() >= 104 && result.getErrcode() <= 107);
    }
    
    /**
     * 二次确认，同时选择生效方式
     * 
     * @return
     * @see 
     */
    public String confirmPrivilege()
    {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        // 发生错误时，返回上一页
        request.setAttribute("backStep", "-1");
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 根据groupid查询同组业务列表
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "没有查询到相关的套餐信息");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "没有查询到相关的套餐信息");
            
            logger.error("没有查询到相关的套餐信息");
            
            return "error";
        }
        
        // 根据用户选择ncode获取业务名称、业务描述
        DictItemPO dictItemPO = null;
        for (int i = 0; i < privilegeList.size(); i++)
        {
            dictItemPO = privilegeList.get(i);
            
            if (dictItemPO.getDictid().equalsIgnoreCase(newNCode))
            {
                newPrivName = dictItemPO.getDictname();
                newPrivDesp = dictItemPO.getDescription();
                
                newPrivDesp = newPrivDesp.replaceAll("<br/>", "");

                break;
            }            
        }
        
        // 业务对应的生效方式。开通,取消,变更
        String menuEffectType = "";
        
        List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        if (titleTotalMenus != null && titleTotalMenus.size() > 0)
        {
            MenuInfoPO menu = null;
            
            for (int i = 0; i < titleTotalMenus.size(); i++)
            {
                menu = (MenuInfoPO) titleTotalMenus.get(i);                
                
                if (curMenuId.equalsIgnoreCase(menu.getMenuid()))
                {
                    menuEffectType = menu.getEffectType();
                    break;
                }
            }
        }
        
        if (null != menuEffectType && !"".equals(menuEffectType.trim()))
        {
            String[] effectTypeArray = menuEffectType.split(",");
            
            // 变更时的生效方式
            if (null != currNCode && !"".equals(currNCode.trim()) && effectTypeArray.length >= 3)
            {
                menuEffectType = effectTypeArray[2];
            }
            // 开通时的生效方式
            else if (null == currNCode || "".equals(currNCode.trim()))
            {
                menuEffectType = effectTypeArray[0];
            }           
        }
        
        // 生效方式不为空(多种生效方式以|分隔)。先从字典表中获取生效方式列表，再从中根据生效方式进行过滤
        if (null != menuEffectType && !"".equals(menuEffectType.trim()))
        {
            String[] effectTypeArray = menuEffectType.split("\\|");

            effectTypeList = this.getDictItemByGrp(Constants.EFFECT_TYPE);
            
            if (null != effectTypeList && effectTypeList.size() > 0)
            {                
                for (int i = effectTypeList.size() - 1; i >= 0; i--)
                {
                    dictItemPO = effectTypeList.get(i);
                    
                    boolean flag = false;
                    
                    for (int j = 0; j < effectTypeArray.length; j++)
                    {
                        if (dictItemPO.getDictid().equals(effectTypeArray[j]))
                        {
                            flag = true;
                            
                            break;
                        }
                    }
                    
                    if (!flag)
                    {
                        effectTypeList.remove(i);
                    }
                }
            }
        }
        
        return "confirmPage";
    }
    
    /**
     * 同组业务办理
     * 
     * @return
     * @see 
     */
    public String commitPrivilege()
    {
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        String sOperType = "ADD";
        
        // 获取业务名称
        String menuName = CommonUtil.getMenuName(curMenuId);
        
        // 根据groupid查询同组业务列表
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "没有查询到相关的套餐信息");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "没有查询到相关的套餐信息");
            
            logger.error("没有查询到相关的套餐信息");
            
            return "error";
        }
        
        // 根据用户选择ncode获取业务名称、业务描述
        DictItemPO dictItemPO = null;
        for (int i = 0; i < privilegeList.size(); i++)
        {
            dictItemPO = privilegeList.get(i);
            
            if (dictItemPO.getDictid().equalsIgnoreCase(newNCode))
            {
                newPrivName = dictItemPO.getDictname();
                
                break;
            }            
        }        
        
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, newNCode, sOperType, effectType, "", curMenuId);
        if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        {
            forward = "success";
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.getReturnObject();
            // 业务受理流水号
            String recFormnum = "0";
            
            if(null != tagSet && null != tagSet.get("formnum"))
            {
                recFormnum = (String)tagSet.get("formnum");
            }
            
            this.createRecLog(curMenuId, recFormnum, "0", "0", "业务受理成功");
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            
            successMessage = menuName + "：" + newPrivName + "受理成功";
        }
        else
        {
            String resultMsg = menuName + "：" + newPrivName + "受理失败";
            
            // modify begin yKF28472 宁夏取CRM返回的错误信息
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                if (result != null && result.getReturnMsg() != null && !"".equals(result.getReturnMsg()))
                {
                    resultMsg = result.getReturnMsg();
                }
            }
            // modify end yKF28472 宁夏取CRM返回的错误信息
            
            this.createRecLog(curMenuId, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            logger.error("业务(" + newNCode + ")受理失败");
        }
        
        return forward;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getGroupid()
    {
        return groupid;
    }

    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCurrNCode()
    {
        return currNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCurrNCode(String currNCode)
    {
        this.currNCode = currNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getNextNCode()
    {
        return nextNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setNextNCode(String nextNCode)
    {
        this.nextNCode = nextNCode;
    }

    public String getCurrDesp()
    {
        return currDesp;
    }

    public void setCurrDesp(String currDesp)
    {
        this.currDesp = currDesp;
    }

    public String getNextDesp()
    {
        return nextDesp;
    }

    public void setNextDesp(String nextDesp)
    {
        this.nextDesp = nextDesp;
    }

    public List<DictItemPO> getPrivilegeList()
    {
        return privilegeList;
    }

    public void setPrivilegeList(List<DictItemPO> privilegeList)
    {
        this.privilegeList = privilegeList;
    }

    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }

    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }

    public String getNewNCode()
    {
        return newNCode;
    }

    public void setNewNCode(String newNCode)
    {
        this.newNCode = newNCode;
    }

    public String getNewPrivName()
    {
        return newPrivName;
    }

    public void setNewPrivName(String newPrivName)
    {
        this.newPrivName = newPrivName;
    }

    public String getNewPrivDesp()
    {
        return newPrivDesp;
    }

    public void setNewPrivDesp(String newPrivDesp)
    {
        this.newPrivDesp = newPrivDesp;
    }

    public List<DictItemPO> getEffectTypeList()
    {
        return effectTypeList;
    }

    public void setEffectTypeList(List<DictItemPO> effectTypeList)
    {
        this.effectTypeList = effectTypeList;
    }

    public String getEffectType()
    {
        return effectType;
    }

    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
