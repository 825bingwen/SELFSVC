package com.gmcc.boss.selfsvc.login.service;

import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;

/**
 * ���û����ػ���Ϣ���м�¼
 * @author lWX5316086
 * @version 
 * @see 
 * @since
 */
public interface TerminalOnOffService
{
    
    /**
     * ��������
     * 
     * @param terminalOnOffPO
     */
    public void termOnOff(TerminalOnOffPO terminalOnOffPO);
    
    /**
     * ���¿��ػ�������
     * 
     * @param termId
     */
    public void updateTermHeart(String termId);
    
    /** ����termid��ȡregion
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
     */
    public int getRegionByTermId (String termId);

}
