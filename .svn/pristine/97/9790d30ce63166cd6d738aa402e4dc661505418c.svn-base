/*
 * 文 件 名:  ValueCardChargeAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  sWX219697
 * 修改时间:  Apr 27, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.valueCard.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.valueCard.service.ValueCardService;

/**
 * <有价卡充值>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Apr 27, 2015]
 * @see  [相关类/方法]
 * @since  [OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造]
 */
public class ValueCardChargeAction extends BaseAction
{

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //public static final Logger log = Logger.getLogger(ValueCardChargeAction.class);
    public static final Log log = LogFactory.getLog(ValueCardChargeAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    /**
     * 手机号码
     */
    private String servnumber;
    
    /**
     * 有价卡密码
     */
    private String valueCardPwd;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 成功信息
     */
    private String successMessage;
    
    /**
     * 有价卡service
     */
    private transient ValueCardService valueCardService;
    
    /**
     * <跳转至充值页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String chargePage()
    {
        return SUCCESS;
    }
    
    /**
     * <有价卡充值>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String chargeCommit()
    {
        String forward = ERROR;
        
        try
        {
            //有价卡充值：首先校验是否电子有价卡，然后充值
            valueCardService.valueCardCharge(servnumber, curMenuId, valueCardPwd);
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            //有价卡校验失败，在原页面提示，不跳转错误页面，方便用户在此输入
            if("authError".equals(e.getCode()))
            {
                forward = "authError";
            }
            
            log.error("有价卡充值失败：", e);
            setErrormessage(e.getMessage());
        }
        
        return forward;
    }

    public String getServnumber()
    {
        return servnumber;
    }

    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }

    public String getValueCardPwd()
    {
        return valueCardPwd;
    }

    public void setValueCardPwd(String valueCardPwd)
    {
        this.valueCardPwd = valueCardPwd;
    }

    public void setValueCardService(ValueCardService valueCardService)
    {
        this.valueCardService = valueCardService;
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

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
