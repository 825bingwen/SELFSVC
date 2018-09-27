/**
 * @author lWX5316086
 */
package com.gmcc.boss.selfsvc.login.dao;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;

/**
 * �ն˿��ػ�����
 * 
 * @author lWX5316086 [Sep 9, 2013]
 */
public class TerminalOnOffDaoImpl extends BaseDaoImpl {

	/**
	 * ����termid��ѯ��¼
	 * 
	 * @param termId
	 * @return
	 */
	public String qryTermOnOff(String termId) {

		String tid = (String) sqlMapClientTemplate.queryForObject("login.qryTermOnOff", termId);
		return tid;
	}

	/**
	 * ����termid��ѯ���ػ���ʷ���¼
	 * 
	 * @param termId
	 * @return
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
	 */
	public String qryTermOnOffHis(TerminalOnOffPO terminalOnOffPO) {
	    String tid = "";
	    if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
	    {
	        tid = (String) sqlMapClientTemplate.queryForObject("login.qryTermOnOffHisSD", terminalOnOffPO);
	    }
	    else
	    {
	        tid = (String) sqlMapClientTemplate.queryForObject("login.qryTermOnOffHis", terminalOnOffPO.getTermId());
	    }
		return tid;
	}

	
	/**
	 * ����termid��ѯ�ն�������
	 * 
	 * @param termId
	 * @return
	 */
	public String qryTermHeart(String termId){
		
		String tid = (String) sqlMapClientTemplate.queryForObject(
				"login.qryTermHeart", termId);
		return tid;
	}

	/**
	 * �����ն˿��ػ���Ϣ��
	 * 
	 * @param terminalOnOffPO
	 */
	public void updateTermOnOff(TerminalOnOffPO terminalOnOffPO){
		
		sqlMapClientTemplate.update("login.updateTermOnOff" , terminalOnOffPO);
	}
	
	/**
	 * �����ն˿��ػ���ʷ��
	 * 
	 * @param id
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
	 */
	public void updateTermOnOffHis(TerminalOnOffPO terminalOnOffPO){
	    if (((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)).equals(Constants.PROOPERORGID_SD))
	    {
	        sqlMapClientTemplate.update("login.updateTermOnOffHisSD", terminalOnOffPO);
	    }
	    else
	    {
	        sqlMapClientTemplate.update("login.updateTermOnOffHis", terminalOnOffPO.getId());
	    }
	}
	
	/**
	 * ��ʱ�����ն˿��ػ�������
	 * 
	 * @param termId
	 */
	public void updateTermHeart(String termId){
		
		sqlMapClientTemplate.update("login.updateTermHeart", termId);
	}
	
	/**
	 * �����ն˿��ػ���Ϣ���¼
	 * 
	 * @param terminalOnOffPO
	 */
	public void insertTermOnOff(TerminalOnOffPO terminalOnOffPO){
		
		sqlMapClientTemplate.update("login.insertTermOnOff" , terminalOnOffPO);
	}
	
	/**
	 * �����ն˿��ػ���ʷ���¼
	 * 
	 * @param id
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
	 */
	public void insertTermOnOffHis(TerminalOnOffPO terminalOnOffPO){
	    if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
		{
	        sqlMapClientTemplate.update("login.insertTermOnOffHisSD", terminalOnOffPO);
		}
	    else
	    {
	        sqlMapClientTemplate.update("login.insertTermOnOffHis", terminalOnOffPO);
	    }
	}
	
	/**
	 * ���Ӹ����ն˿��ػ��������¼
	 * 
	 * @param termId
	 */
	public void insertTermHeart(String termId){
		
		sqlMapClientTemplate.update("login.insertTermHeart", termId);
	}
	
	/** ����termid��ȡregion
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 ���ػ���ʷ������region
     */
    public int qryRegionByTermId (String termId)
    {
        return (Integer)sqlMapClientTemplate.queryForObject("login.qryRegionByTermId",termId);
    }
}
