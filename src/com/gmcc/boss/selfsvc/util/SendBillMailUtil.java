package com.gmcc.boss.selfsvc.util;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.customize.sd.selfsvc.bean.MonthFeeBean;
/**
 * 
 * <账单邮件下发接口线程类>
 * <功能详细描述>
 * 
 * @author  sWX219697 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
 * @version  [版本号, May 4, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendBillMailUtil extends TimerTask
{
	private static Log logger = LogFactory.getLog(SendBillMailUtil.class);
	
	//操作员id
	private String operID;
	
	//终端机编号
	private String termID;
	
	//手机号码
	private String telnum;
	
	//账期
	private String cycle;
	
	//是否合并付费
	private String unionacct;
	
	//当前菜单id
	private String curMenuId;
	
	/**
	 * <默认构造函数>
	 */
	public SendBillMailUtil(String operID,String termID,String telnum,String cycle,String unionacct,String curMenuId)
	{
		this.operID = operID;
		this.termID = termID;
		this.telnum = telnum;
		this.cycle = cycle;
		this.unionacct = unionacct;
		this.curMenuId = curMenuId;
	}
	
	/**
	 * 定时任务
	 */
	public void run()
	{
		try 
		{
			//从spring上下文中获取Bean
			MonthFeeBean monthFeeBean = (MonthFeeBean)ApplicationContextUtil.getBean("monthFeeBean");
			
			//账单邮件下发接口
			monthFeeBean.sendBillMail(operID, termID, telnum, cycle, unionacct, curMenuId);
		} 
		catch (Exception e) 
		{
			logger.error("账单邮件下发接口调用失败：",e);
		}
	}
	
	
	
	

}
