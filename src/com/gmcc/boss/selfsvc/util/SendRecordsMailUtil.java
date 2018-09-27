package com.gmcc.boss.selfsvc.util;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * <详单邮件下发定时类>
 * <功能详细描述>
 * 
 * @author  sWX219697 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
 * @version  [版本号, May 4, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendRecordsMailUtil extends TimerTask
{
	private static Log logger = LogFactory.getLog(SendRecordsMailUtil.class);
	
	private NserCustomerSimp customerSimp;
	private TerminalInfoPO terminalInfo;
	
	//当前菜单id
	private String curMenuId;
	
	//开始时间
	private String startDate;
	
	//查询结束时间
	private String endDate;
	
	//账期
	private String month;
	
	//是否按账期查询 1：按账期，0：按起止时间，默认为0
	private String iscycle;
	
	//模板业务类别
	private String cdrType;
	
	//是否发送短信提醒 0：发送，1：不发送，默认为0
	private String nosms;
	
	/**
	 * <默认构造函数>
	 */
	public SendRecordsMailUtil(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String curMenuId,
			String startDate,String endDate,String month,String iscycle,String cdrType,String nosms)
	{
		this.customerSimp = customerSimp;
		this.terminalInfo = terminalInfo;
		this.curMenuId = curMenuId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.month = month;
		this.iscycle = iscycle;
		this.cdrType = cdrType;
		this.nosms = nosms;
	}
	
	/**
	 * 定时任务
	 */
	public void run()
	{
		try 
		{
			//从spring上下文中获取Bean
			DetailedRecordsBean detailedRecordsBean = (DetailedRecordsBean)ApplicationContextUtil.getBean("detailedRecordsBean");
			
			//详单邮件下发接口调用
			detailedRecordsBean.sendRecordsMail(customerSimp, terminalInfo, curMenuId, startDate, 
					endDate, month, iscycle, cdrType, nosms);
		} 
		catch (Exception e) 
		{
			logger.error("详单邮件下发接口调用失败：", e);
		}
	}

	

}
