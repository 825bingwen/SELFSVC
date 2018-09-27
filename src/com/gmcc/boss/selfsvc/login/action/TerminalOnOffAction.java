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


/** �ն˿��ػ���Ϣ����
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
    
    TerminalOnOffPO terminalOnOffPO = new TerminalOnOffPO();//�ն˿��ػ�״̬PO
    
    /**
     * ���� ��¼��Ϣ
     * @throws IOException 
     */
    public void termOnOff() throws IOException
    {
        // ����ն˻���Ϣ
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        terminalOnOffPO.setTermId(terminalInfo.getTermid());
        
        // ���ÿ�������
        String status = (String)PublicCache.getInstance().getCachedData(Constants.TERMON);
        terminalOnOffPO.setStatus(status);
        
        // ����״̬�����ѯ״̬����
        String detail = (String)PublicCache.getInstance().getCachedData(Constants.SH_TERMON);
        terminalOnOffPO.setDetail(detail);
        
        // modify begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
        // ʡ���ж�
        if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            int region = terminalOnOffService.getRegionByTermId(terminalInfo.getTermid());
            terminalOnOffPO.setRegion(region);
        }
        // modify begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
        
        // ��¼����
        terminalOnOffService.termOnOff(terminalOnOffPO);
    }
    
    /**
     * ÿ15������ SH_TERMSTATUS_ONOFF���������
     */
    public void updateTermHeart()
    {
        // ����ն˻���Ϣ
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��������
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
