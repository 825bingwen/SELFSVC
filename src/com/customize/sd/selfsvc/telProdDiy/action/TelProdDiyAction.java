/*
 * 文 件 名:  TelProdDiyAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;
import com.customize.sd.selfsvc.telProdDiy.service.TelProdDiyService;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.common.ReceptionException;

/**
 * 自选套餐action
 * @author  jWX216858
 * @version  [版本号, 2014-10-10]
 * @see  
 * @since 
 */
@Controller
@Scope("prototype")
public class TelProdDiyAction extends PagedAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//public static final Logger log = Logger.getLogger(TelProdDiyAction.class);
	private static Log log = LogFactory.getLog(TelProdDiyAction.class);
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	
	/**
	 * 查询service
	 */
	@Autowired
	private transient TelProdDiyService telService;
	
	/**
	 * 可选业务列表
	 */
	private List<TelProdPO> voiceProdList;
	
	/**
	 * 可选业务列表
	 */
	private List<TelProdPO> netProdList;
	
	/**
	 * 主体产品列表
	 */
	private List<TelProdPO> telProdList;
	
	/**
	 * 自选套餐对象
	 */
	private transient TelProdPO telProdPo = new TelProdPO();
	
	/**
	 * 当前菜单
	 */
	private String curMenuId;
	
	/**
	 * 总费用
	 */
	private String totalHidden = "0.00";
	
    /**
     * 页面右侧div里的内容
     */
    private String rightDivHtml;
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 成功信息
     */
    private String successMessage;
    
    /**
     * 查询自选套餐可用业务列表
     * @return
     * @remark modify by sWX219697 2015-5-5 17:37:11 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public String qryTelProdList()
    {
        String forward = ERROR;
        
        try
        {
            //查询用户可用的主体产品
            telProdList = telService.qryUsableProdList(curMenuId);
            forward = "qryTelProdList";
        }
        catch (ReceptionException e)
        {
            log.error("查询自选套餐可用业务列表失败：", e);
            setErrormessage(e.getMessage());    
        }
    	
    	return forward;
    }
    
    /**
     * <根据用户选择的主体产品，查找对应的语音或上网流量套餐>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-5-6 09:45:30 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public String qryProdListByProdId()
    {
        String forward = ERROR;
        
        try
        {
            // 查询数据库，获取自选套餐信息,默认查询语音
            if (null == telProdPo.getQryType() || "VOICECALL".equals(telProdPo.getQryType()))
            {
                // 对获取的语音数据进行分页
                voiceProdList = getPageList(telService.qryVoiceProdList(telProdPo.getProdId()), 8);
            }
            else
            {
                // 对获取的上网流量包数据进行分页
                netProdList = getPageList(telService.qryNetProdList(telProdPo.getProdId()), 8);
            }
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            log.error("根据主体产品编码查询语音或上网套餐失败：", e);
            setErrormessage(e.getMessage());        
        }
        
        return forward;
    }
    
    /**
     * 查询自选套餐可用业务列表
     * @return
     * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public String recSubmit()
    {
        String forward = ERROR;
        
        try
        {
            //查询用户选中套餐对应的ncode，并调用流水线接口受理
            telService.recCommit(telProdPo, curMenuId);
            
            setSuccessMessage("自选套餐受理成功");
            forward = "recSubmit";
        }
        catch (ReceptionException e)
        {
            log.error("自选套餐受理失败：", e);
            setErrormessage(e.getMessage());   
        }
		
		return forward;
    }


    
    public String getTotalHidden()
    {
        return totalHidden;
    }
    
    public void setTotalHidden(String totalHidden)
    {
        this.totalHidden = totalHidden;
    }
    
    public String getRightDivHtml()
    {
        return rightDivHtml;
    }
    
    public void setRightDivHtml(String rightDivHtml)
    {
        this.rightDivHtml = rightDivHtml;
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
    
    public List<TelProdPO> getVoiceProdList()
    {
        return voiceProdList;
    }
    
    public void setVoiceProdList(List<TelProdPO> voiceProdList)
    {
        this.voiceProdList = voiceProdList;
    }
    
    public List<TelProdPO> getNetProdList()
    {
        return netProdList;
    }
    
    public void setNetProdList(List<TelProdPO> netProdList)
    {
        this.netProdList = netProdList;
    }

    public List<TelProdPO> getTelProdList()
    {
        return telProdList;
    }

    public void setTelProdList(List<TelProdPO> telProdList)
    {
        this.telProdList = telProdList;
    }

    public TelProdPO getTelProdPo()
    {
        return telProdPo;
    }

    public void setTelProdPo(TelProdPO telProdPo)
    {
        this.telProdPo = telProdPo;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
    
}
