/*
* @filename: MonthlyAmountAction.java
* @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
* @brif:  包月量信息查询
* @author: hWX5316476
* @de:  2014-05-30 
* @description: 新增包月量信息查询action
* @remark: create hWX5316476 2014-05-30 V200R003C10LG0601 OR_huawei_201405_878
*/
package com.customize.sd.selfsvc.feeService.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.ChooseTelBean;
import com.customize.sd.selfsvc.bean.ComboInfoBean;
import com.customize.sd.selfsvc.bean.MonthFeeBean;
import com.customize.sd.selfsvc.feeService.model.CyclePO;
import com.customize.sd.selfsvc.feeService.model.PkgPO;
import com.customize.sd.selfsvc.feeService.model.PrivPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;


public class MonthlyAmountAction extends BaseAction
{
	// 日志
	private static Log logger = LogFactory.getLog(MonthlyAmountAction.class);
    
	// 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId = "";
    
    // 月份
    private String month = "";
    
    // 月份导航信息
    private String[] months;
    
    // 手机号
    private String telnum;
    
    // 账期列表
    private List<CyclePO> cycleList = new ArrayList<CyclePO>();
    
    // 帐期，分散帐期多帐期格式YYYYMMDD，自然月帐期格式YYYYMM
    private String cycle;
    
    // 开始时间，格式:YYYYMMDD
    private String startdate;
    
    // 结束时间，格式:YYYYMMDD
    private String enddate;
    
    // 主账号
    private String acctid;
    
    // 是否合户用户。1：是 0：不是
    private String unionacct;
    
    // 用户ID
    private String subsid;
    
    // 短信内容
    private String shortMsg;
    
    // 套餐信息
    private List<PkgPO> obj_pkg;
    
    // 通信总量
    private List<PkgPO> obj_total;
    
    // 当前月包月量信息返回结果
    private List<PrivPO> obj_priv;
    
    // 调用查询包月信息接口bean
    private transient MonthFeeBean monthFeeBean;
   
    // 调用发短信接口bean
    private ChooseTelBean chooseTelBean;
    
    // 调用套餐使用量查询接口bean
    private ComboInfoBean qryComboInfoBean;
   
    /**
     * 包月量信息查询
     * 
     * @return String
     */
	public String qryMonthlyAmount()
    {
		logger.debug("qryMonthlyAmount start...");
		
		HttpServletRequest request = this.getRequest();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 手机号
        telnum = customerSimp.getServNumber();
        
        // 厂家编码的密文标识，不能为空
        String factory = "9A3A9B26E157B508228301EF1F7BF352";
        
        // 区域列表
        String arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,SPBILL,BILLINFO,INOUT,SCOREREMARK,ACKNOWLEDGEMENT";
        
        // 调用接口查询包月量信息
        ReturnWrap rw = monthFeeBean.qryMonthBill_new(terminalInfo, "10000100", telnum, acctid, subsid, cycle, startdate, enddate, unionacct, customerSimp.getRegionID(), arealist, factory);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	Map map = (Map)rw.getReturnObject();
            
            if (map != null)
            {
	            // 通信总量
            	if (map.get("total") != null)
	            {
	            	obj_total = (List<PkgPO>)map.get("total");
	            }
            	
            	// 如果没有通信总量，则表示没有该账期的包月量信息
            	if(null == obj_total || obj_total.size() == 0)
            	{
            		request.setAttribute("errormessage", "未查询到账期" + startdate + " - " + enddate + "的包月量信息");
            		return "error";
            	}
            	
            	// 套餐信息
	            if (map.get("pkg") != null)
	            {
	            	obj_pkg = (List<PkgPO>)map.get("pkg");
	            }
	            
	            // 成功
            	this.createRecLog(Constants.BUSITYPE_SUBSQRYMONTHLYAMOUNT, "0", "0", "0", "账期" + startdate + " - " + enddate + "的包月量信息查询成功");
            }
            else
            {
            	request.setAttribute("errormessage", "未查询到账期" + startdate + " - " + enddate + "的包月量信息");
            	
            	// 失败
            	this.createRecLog(Constants.BUSITYPE_SUBSQRYMONTHLYAMOUNT, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate + "的包月量信息");
            	
                // 返回
                return "error";
            }
        }
        else
        {
        	request.setAttribute("errormessage",  "账期" + startdate + " - " + enddate +"的包月量信息查询失败！");
        	
        	// 失败
        	this.createRecLog(Constants.BUSITYPE_SUBSQRYMONTHLYAMOUNT, "0", "0", "1", "账期" + startdate + " - " + enddate +"的包月量信息查询失败！");
        	
            // 返回
            return "error";
        }
        
		logger.debug("qryMonthlyAmount end");
    	return "monthlyAmountDetail";
    }
	
	/**
	 * 根据月份查询账期
	 * 
	 * @return
	 */
	public String qryMonthCycle()
	{
		logger.debug("qryMonthCycle start...");
		
		HttpServletRequest request = this.getRequest();
		
		// 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        telnum = customerSimp.getServNumber();
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 当前月份
        String currMonth = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 0);
        
        // 页面没有传月份则默认当前月份 格式“yyyyMM”
		if(month == null || "".equals(month))
		{
			month = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 0);
		}
		
		// 如果页面传入月份为当前月
		if(currMonth.equals(month))
		{
			return qryCurrMonthlyAmount();
		}
		
		// 根据手机号码以及月份查询账期信息
        ReturnWrap rw = monthFeeBean.getCustinfo(terminalInfo, curMenuId, customerSimp.getServNumber(), month);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 取返回对象
            Vector vector = (Vector)(rw.getReturnObject());
            
            // 客户信息
            CTagSet ctagset = null;
            if(vector.size() > 0)
            {
            	// 客户信息
            	ctagset = (CTagSet)(vector.get(0));
            	
            	// 用户ID
                subsid = ctagset.GetValue("subsid");
            }
           
            CRSet crset = new CRSet();
            if(vector.size() > 1)
            {
            	// 账期信息
            	crset = (CRSet)(vector.get(1));
            }
            
            CyclePO cyclePO;
            
            // 多账期
            if (crset.GetRowCount() > 1)
            {
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    cyclePO = new CyclePO();
                    
                    // 帐期
                    cyclePO.setCycle(crset.GetValue(i, 0));
                    
                    // modify begin lWX431760 2018-02-11 R005C20LG2601 DTS2018021101902
                    String startTime = crset.GetValue(i, 1);
                    if (startTime.contains("-"))
                    {
                        startTime = startTime.replace("-", "");
                    }
                    // 开始时间
                    cyclePO.setStartdate(startTime);
                    
                    String endTime = crset.GetValue(i, 2);
                    if (endTime.contains("-"))
                    {
                        endTime = startTime.replace("-", "");
                    }
                    // 结束时间
                    cyclePO.setEnddate(endTime);
                    // modify end lWX431760 2018-02-11 R005C20LG2601 DTS2018021101902
                    
                    // 主账号
                    cyclePO.setAcctid(crset.GetValue(i, 3));
                    
                    // 是否合户用户。1，是；0，不是
                    cyclePO.setUnionacct(crset.GetValue(i, 4));
                    
                    cycleList.add(cyclePO);
                }
                
                // 多账期跳转到账期选择页面
                return "selectCycle";
            }
            // 单账期
            else
            {
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    cyclePO = new CyclePO();
                    
                    // 帐期
                    cyclePO.setCycle(crset.GetValue(i, 0));
                    cycle = crset.GetValue(i, 0);
                    
                    // modify begin lWX431760 2018-02-11 R005C20LG2601 DTS2018021101902
                    String startTime = crset.GetValue(i, 1);
                    if (startTime.contains("-"))
                    {
                        startTime = startTime.replace("-", "");
                    }
                    // 开始时间
                    cyclePO.setStartdate(startTime);
                    startdate = startTime;
                    
                    String endTime = crset.GetValue(i, 2);
                    if (endTime.contains("-"))
                    {
                        endTime = startTime.replace("-", "");
                    }
                    // 结束时间
                    cyclePO.setEnddate(endTime);
                    enddate = endTime;
                    // modify end lWX431760 2018-02-11 R005C20LG2601 DTS2018021101902
                    
                    // 主账号
                    cyclePO.setAcctid(crset.GetValue(i, 3));
                    acctid = crset.GetValue(i, 3);
                    
                    // 是否合户用户。1，是；0，不是
                    cyclePO.setUnionacct(crset.GetValue(i, 4));
                    unionacct = crset.GetValue(i, 4);
                    
                    cycleList.add(cyclePO);
                }
                
                // 单账期直接转到查询结果页面
                return qryMonthlyAmount();
            }
        }
        else
        {
        	request.setAttribute("errormessage", "账期查询失败！请稍后再试，给您带来的不便，敬请原谅！");
        	request.setAttribute("backStep", "-1");
        	return "error";
        }
       
	}
	/**
	 * 查询当前月包月量详情
	 * @return String
	 */
	public String qryCurrMonthlyAmount()
    {
        // 开始记录日志
        logger.debug("qryCurrMonthlyAmount start...");
        
        // 获取request
        HttpServletRequest request = getRequest();
        
        // 获取session
        HttpSession session = request.getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
        // 查询套餐信息
        Map mapResult = qryComboInfoBean.qryComboInfo(terminalInfoPO, customer, curMenuId);
        
        if (mapResult != null && mapResult.size() > 0)
        {
            Vector retData = (Vector)(mapResult.get("returnObj"));
            
            // 套餐信息
            obj_priv = new ArrayList<PrivPO>();
            
            CRSet cr = new CRSet();
            if(retData.size() > 0)
            {
            	cr = (CRSet)(retData.get(1));
            }
            
            // 拼装数据
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
            	PrivPO privPO = new PrivPO();
            	
            	// 名称privSet
            	privPO.setPrivset(cr.GetValue(i, 0));
            	
            	// 通讯类型
        		privPO.setFreetype(cr.GetValue(i, 1));
        		
        		// 总量
        		privPO.setTotal(cr.GetValue(i, 2));
        		
        		// 剩余量
        		privPO.setSurplusamount(cr.GetValue(i, 3));
        		
        		// 单位
        		privPO.setAttrtypeunit(cr.GetValue(i, 4));
            	
        		obj_priv.add(privPO);
            }
              
            // 如果没有通信总量，则表示没有该账期的包月量信息
        	if(obj_priv.size() == 0)
        	{
        		request.setAttribute("errormessage", "未查询到月份" + month + "的包月量信息");
        		return "error";
        	}
                
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMONTHLYAMOUNT, "0", "0", "0", "月份" + month +"的包月量信息查询成功!");
        }
        else
        {               
        	request.setAttribute("errormessage",  "月份" + month +"的包月量信息查询失败！");
        	
        	// 失败
        	this.createRecLog(Constants.BUSITYPE_SUBSQRYMONTHLYAMOUNT, "0", "0", "1", "月份" + month +"的包月量信息查询失败！");
        	
            // 返回
            return "error";
        }
       
        logger.debug("qryCurrMonthlyAmount End ...");
        return "currMonthlyAmountDetail";
    }
	
	/**
	 * 包月量信息以短信形式发送到用户手机
	 * 
	 * @return
	 */
	public void sendMonthlyMsg()
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("sendMonthlyMsg start...");
		}
		
		// 终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        PrintWriter writer = null;
        
        // 发送短信标识 1：成功 2:失败
        String xml="";
        try
        {
        	// 为中文入参解码
        	shortMsg = URLDecoder.decode(shortMsg,"utf-8");
        	
        	writer = this.getResponse().getWriter();
        	
        	// 发送短信
        	boolean isSuccess = this.chooseTelBean.sendMsg(telnum, terminalInfoPO, shortMsg, "");
        	
        	xml = Boolean.toString(isSuccess);
        }
        catch(UnsupportedEncodingException e)
        {
        	logger.error("包月量信息短信内容解析失败：", e);
        }
        catch(IOException e)
        {
        	logger.error("包月量信息短信发送失败：", e);
        }
        catch (Exception e)
        {
            logger.error("包月量信息短信发送失败：", e);
        }
        finally
        {
            // 输出到client，并关闭流
            if (writer != null)
            {
            	writer.write(xml);
                writer.flush();
                writer.close();
            }
        }
        
        if(logger.isDebugEnabled())
        {
        	logger.debug("短信发送"+(Boolean.valueOf(xml)?"成功!":"失败!"));
        	logger.debug("短信内容："+shortMsg);
        	logger.debug("sendMonthlyMsg end!");
        }
        
        
	}
	
	public String[] getMonths()
	{
		// 可查询当前月和前五个月的详单信息
        String[] monthsBak = new String[6];
        
        // 获取当前及前五个月的月份 格式“yyyyMM”
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM, 6);
        
        // 给六个月按从前往后排序
        for (int i = 0; i <= 5; i ++)
        {
            monthsBak[5-i] = months[i];
        }
        months = monthsBak;
        return months;
	}

	public void setMonths(String[] months) 
	{
		this.months = months;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getMonth() 
	{
		return month;
	}
	public void setMonth(String month) 
	{
		this.month = month;
	}

	public List<CyclePO> getCycleList() 
	{
		return cycleList;
	}
	public void setCycleList(List<CyclePO> cycleList) 
	{
		this.cycleList = cycleList;
	}
	public String getCycle()
	{
		return cycle;
	}
	public void setCycle(String cycle) 
	{
		this.cycle = cycle;
	}
	public String getStartdate() 
	{
		return startdate;
	}
	public void setStartdate(String startdate) 
	{
		this.startdate = startdate;
	}
	public String getEnddate()
	{
		return enddate;
	}
	public void setEnddate(String enddate)
	{
		this.enddate = enddate;
	}
	public String getAcctid() 
	{
		return acctid;
	}
	public void setAcctid(String acctid)
	{
		this.acctid = acctid;
	}
	public String getUnionacct()
	{
		return unionacct;
	}
	public void setUnionacct(String unionacct)
	{
		this.unionacct = unionacct;
	}

	public String getSubsid() 
	{
		return subsid;
	}
	public void setSubsid(String subsid) 
	{
		this.subsid = subsid;
	}

	public MonthFeeBean getMonthFeeBean()
	{
		return monthFeeBean;
	}

	public void setMonthFeeBean(MonthFeeBean monthFeeBean)
	{
		this.monthFeeBean = monthFeeBean;
	}

	public List<PkgPO> getObj_pkg()
	{
		return obj_pkg;
	}

	public void setObj_pkg(List<PkgPO> obj_pkg) 
	{
		this.obj_pkg = obj_pkg;
	}

	public List<PkgPO> getObj_total() 
	{
		return obj_total;
	}

	public void setObj_total(List<PkgPO> obj_total) 
	{
		this.obj_total = obj_total;
	}

	public ChooseTelBean getChooseTelBean()
	{
		return chooseTelBean;
	}

	public void setChooseTelBean(ChooseTelBean chooseTelBean)
	{
		this.chooseTelBean = chooseTelBean;
	}

	public String getTelnum() 
	{
		return telnum;
	}

	public void setTelnum(String telnum) 
	{
		this.telnum = telnum;
	}

	public String getShortMsg()
	{
		return shortMsg;
	}

	public void setShortMsg(String shortMsg) 
	{
		this.shortMsg = shortMsg;
	}

	public ComboInfoBean getQryComboInfoBean() {
		return qryComboInfoBean;
	}

	public void setQryComboInfoBean(ComboInfoBean qryComboInfoBean) {
		this.qryComboInfoBean = qryComboInfoBean;
	}

	public List<PrivPO> getObj_priv() {
		return obj_priv;
	}

	public void setObj_priv(List<PrivPO> obj_priv) {
		this.obj_priv = obj_priv;
	}
}
