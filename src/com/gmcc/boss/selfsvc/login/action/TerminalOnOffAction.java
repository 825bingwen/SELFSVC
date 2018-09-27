/**
 * @author lWX5316086
 */
package com.gmcc.boss.selfsvc.login.action;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;
import com.gmcc.boss.selfsvc.login.service.TerminalOnOffService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


/** 终端开关机信息操作
 * 
 * @author lWX5316086
 * @version 
 * @see 
 * @since 
 */
public class TerminalOnOffAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private TerminalOnOffService terminalOnOffService = null;
    
    TerminalOnOffPO terminalOnOffPO = new TerminalOnOffPO();//终端开关机状态PO
    
    /**
     * 开机 记录信息
     * @throws IOException 
     */
    public void termOnOff() throws IOException
    {
        // 获得终端机信息
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        terminalOnOffPO.setTermId(terminalInfo.getTermid());
        
        // 设置开机编码
        String status = (String)PublicCache.getInstance().getCachedData(Constants.TERMON);
        terminalOnOffPO.setStatus(status);
        
        // 根据状态编码查询状态描述
        String detail = (String)PublicCache.getInstance().getCachedData(Constants.SH_TERMON);
        terminalOnOffPO.setDetail(detail);
        
        // modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
        // 省份判断
        if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            int region = terminalOnOffService.getRegionByTermId(terminalInfo.getTermid());
            terminalOnOffPO.setRegion(region);
        }
        // modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
        
        // 记录开机
        terminalOnOffService.termOnOff(terminalOnOffPO);
    }
    
    /**
     * 每15分钟向 SH_TERMSTATUS_ONOFF表更新数据
     */
    public void updateTermHeart()
    {
        // 获得终端机信息
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 更新数据
        terminalOnOffService.updateTermHeart(terminalInfo.getTermid());
    }
    
    public TerminalOnOffService getTerminalOnOffService()
    {
        return terminalOnOffService;
    }

    public void setTerminalOnOffService(TerminalOnOffService terminalOnOffService)
    {
        this.terminalOnOffService = terminalOnOffService;
    }

    public TerminalOnOffPO getTerminalOnOffPO()
    {
        return terminalOnOffPO;
    }

    public void setTerminalOnOffPO(TerminalOnOffPO terminalOnOffPO)
    {
        this.terminalOnOffPO = terminalOnOffPO;
    }

}
