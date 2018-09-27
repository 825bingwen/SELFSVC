/**
 * @author lWX5316086
 */
package com.gmcc.boss.selfsvc.login.dao;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;

/**
 * 终端开关机操作
 * 
 * @author lWX5316086 [Sep 9, 2013]
 */
public class TerminalOnOffDaoImpl extends BaseDaoImpl {

	/**
	 * 根据termid查询记录
	 * 
	 * @param termId
	 * @return
	 */
	public String qryTermOnOff(String termId) {

		String tid = (String) sqlMapClientTemplate.queryForObject("login.qryTermOnOff", termId);
		return tid;
	}

	/**
	 * 根据termid查询开关机历史表记录
	 * 
	 * @param termId
	 * @return
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
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
	 * 根据termid查询终端心跳表
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
	 * 更新终端开关机信息表
	 * 
	 * @param terminalOnOffPO
	 */
	public void updateTermOnOff(TerminalOnOffPO terminalOnOffPO){
		
		sqlMapClientTemplate.update("login.updateTermOnOff" , terminalOnOffPO);
	}
	
	/**
	 * 更新终端开关机历史表
	 * 
	 * @param id
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
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
	 * 定时更新终端开关机心跳表
	 * 
	 * @param termId
	 */
	public void updateTermHeart(String termId){
		
		sqlMapClientTemplate.update("login.updateTermHeart", termId);
	}
	
	/**
	 * 增加终端开关机信息表记录
	 * 
	 * @param terminalOnOffPO
	 */
	public void insertTermOnOff(TerminalOnOffPO terminalOnOffPO){
		
		sqlMapClientTemplate.update("login.insertTermOnOff" , terminalOnOffPO);
	}
	
	/**
	 * 增加终端开关机历史表记录
	 * 
	 * @param id
	 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
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
	 * 增加更新终端开关机心跳表记录
	 * 
	 * @param termId
	 */
	public void insertTermHeart(String termId){
		
		sqlMapClientTemplate.update("login.insertTermHeart", termId);
	}
	
	/** 根据termid获取region
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
     */
    public int qryRegionByTermId (String termId)
    {
        return (Integer)sqlMapClientTemplate.queryForObject("login.qryRegionByTermId",termId);
    }
}
