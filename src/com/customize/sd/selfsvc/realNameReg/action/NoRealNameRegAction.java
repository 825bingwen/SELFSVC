package com.customize.sd.selfsvc.realNameReg.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.NoRealNameRegBean;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 实名制与非实名制认证补卡
 * 
 * @author wWX217192
 */
public class NoRealNameRegAction extends BaseAction 
{

	private static Log logger = LogFactory.getLog(NoRealNameRegAction.class);
	
	// 序列化
	private static final long serialVersionUID = 1672689405991172671L;
	
	//当前菜单信息
	private String curMenuId = "";
	
	// 当前认证的手机号码
	private String telNumber;
	
	// 认证方式
	private int verificationAccess;
	
	// 充值日期1
	private String rechargeDate1;
	
	// 充值日期2
	private String rechargeDate2;
	
	// 充值日期3
	private String rechargeDate3;
	
	// 充值金额1
	private String rechargeValue1;
	
	// 充值金额2
	private String rechargeValue2;
	
	// 充值金额3
	private String rechargeValue3;
	
	private NoRealNameRegBean registerCardBean;
	
	// 短信随机密码的值
	private String randomPwd;
	
	// 页面成功信息
	private String successMessage;
	
	// 个人密码信息
	private String servPasswd;
	
	// SIM卡卡号
	private String simCardNo;
	
	// 交费记录
	private ChargeRecordPO chargeRecordPO = new ChargeRecordPO();
	
	// 通话记录
	private String callNums;
	
	// 验证方式
	private String accessName;
	
	// 成功标志 0成功  1失败
	private String successBz;
	
	/**
	 * 年月日期列表
	 */
	private List<String> monthList = new ArrayList<String>();
	
	// 提交验证次数
	private int commitNum = 1;
	
	/**
	 * 展示输入两次手机号码的确认页面.
	 * 
	 * @return inputNumber2：支持金属键盘的页面；inputNumber：输入两次手机号码的确认页面
	 * @remark create wWX217192 2014/06/10 OR_huawei_201406_338
 	 */
	public String inputNumber()
    {       
        if (getIsKey())
        {
           return  "inputNumber2";
        }
        
        return "inputNumber";
    }
	
	/**
     * 是否只支持金属键盘
     * @remark create wWX217192 2014/06/10 OR_huawei_201406_338
     */
    private boolean getIsKey()
    {
        TerminalInfoPO termInfo = (TerminalInfoPO) (getRequest().getSession().getAttribute(Constants.TERMINAL_INFO));
        
        return termInfo.isSuppKey();
    }
    
    /**
     * 为用户提供5种认证方式，维持页面的正常跳转
     * 
     * @return success：操作成功
     * @remark create wWX217192 2014/06/11 OR_huawei_201406_338
     */
    public String showRegisterAccess()
    {
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	// 调用查询非实名制认证标记接口
    	Map<String, String> resultMap = registerCardBean.qryRealNameType(telNumber, termInfo, curMenuId);
    	
    	// 接口调用成功，resultMap中的isRealName为空，意味着接口调用失败
    	if(!resultMap.containsKey("isRealName"))
    	{
    		// 将接口返回的错误信息返给前台
    		getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "查询非实名制认证标记失败");
    		return "failed";
    	}
    	// 接口调用成功，且返回isRealName的标记
    	else
    	{
    		// 非实名制认证用户，跳转至相应页面
    		if("0".equals(resultMap.get("isRealName")))
    		{
    			this.qryCurrentMonth();
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "查询非实名制认证标记成功");
    			return SUCCESS;
    		}
    		// 实名制认证用户，提示相应的错误信息
    		else
    		{
    			getRequest().setAttribute("errormessage", "此号码不属于非实名制认证用户，请核对您的号码!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "查询非实名制认证标记失败");
    			return "failed";
    		}
    	}
    }
    
    
    /**
     * 生成短信随机码
     * @throws IOException 
     * 
     * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
     */
    public void sendRandomPwd() throws IOException
    {
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
        // 头信息
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
    	
    	// 将信息异步传递至JSP页面上
        PrintWriter out = this.getResponse().getWriter();
        
        // 调用生成短信随机密码的接口
        Map<String, String> resultMap = registerCardBean.getRandomPwd(telNumber, termInfo, curMenuId);
        
        try
        {
        	// 接口调用成功，且随机密码的值不为空
        	if(resultMap.containsKey("randomPwd"))
        	{
        		logger.info("生成短信随机码：   " + resultMap.get("randomPwd"));
        		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名认证发送短信验证码成功");
        		out.write("1;,;" + resultMap.get("randomPwd"));
        	}
        	else
        	{
        		logger.info("短信随机密码接口调用失败!");
        		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名认证发送短信验证码失败");
        		out.write("2;,;" + resultMap.get("returnMsg"));
        	}
        }
        catch(Exception e)
        {
        	logger.error("发送短信随机码失败!", e);
        	out.write("0;,;" + "生成短信随机密码失败!");
        }
        finally
        {
        	out.flush();
        	out.close();
        }
        
    }
    
    /**
     * 短信随机密码验证
     * 
     * @return 页面跳转信息
     * @remark create wWX217192 2014-06-28 OR_huawei_201406_338
     */
    public String checkRandomPwd()
    {
    	// 非实名制认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
    	commitNum++;
    	
    	accessName = "短信验证";
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	// 调用随机密码验证接口
    	Map<String, String> resultMap = registerCardBean.checkRandomPwd(telNumber, termInfo, curMenuId, randomPwd);
    	
    	// 接口调用成功
    	if(resultMap.containsKey("authChkResult"))
    	{
    		// 随机密码验证成功
    		if("1".equals(resultMap.get("authChkResult")))
    		{
    			successBz = "0";
    			setSuccessMessage("业务办理成功,请取走您的凭条!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名认证短信随机密码验证成功");
    			return SUCCESS;
    		}
    		else 
    		{
    			this.getRequest().setAttribute("errormessage", "非实名认证短信随机密码验证失败");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名认证短信随机密码验证失败");
    			return "failed";
    		}
    	}
    	// 接口调用失败
    	else
    	{
    		logger.info("短信随机密码验证失败!");
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名认证短信随机密码验证失败");
    		
    		this.getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    		
    		this.qryCurrentMonth();
    		return "failed";
    	}
    }
    
    /**
     * 验证个人密码
     * 
     * @return 页面跳转信息
     * @remark create wWX217192 2014-06-28 OR_huawei_201406_338
     */
    public String checkUserPwd()
    {
    	// 非实名制认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
    	commitNum++;
    	accessName = "服务密码";
    	// 获取终端机信息
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	// 调用验证个人密码接口
    	Map<String, String> resultMap = registerCardBean.checkUserPwd(telNumber, termInfo, curMenuId, servPasswd);
    	
    	// 接口调用成功，且成功返回验证是否成功的标志
    	if(resultMap.containsKey("authChkResult"))
    	{
    		// 个人密码验证成功
    		if("1".equals(resultMap.get("authChkResult")))
    		{
    			successBz = "0";
    			setSuccessMessage("业务办理成功,请取走您的凭条!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名制认证个人密码验证成功");
    			return SUCCESS;
    		}
    		// 个人密码验证失败
    		else
    		{
    			getRequest().setAttribute("errormessage", "个人密码验证失败，请重新输入!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证个人密码验证失败");
    			this.qryCurrentMonth();
    			return "failed";
    		}
    	}
    	// 接口无法返回正确的验证成功与否的标志
    	else
    	{
    		logger.info("个人密码验证失败!");
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证个人密码验证失败");
    		
    		getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    			
    		this.qryCurrentMonth();
    		return "failed";
    	}
    }
    
    /**
     * SIM卡卡号验证
     * 
     * @return 页面跳转信息
     * @remark create wWX217192 2014-06-30 OR_huawei_201406_338
     */
    public String checkSIMCardNo()
    {
    	// 非实名制认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
    	commitNum++;
    	
    	accessName = "SIM卡";
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	Map<String, String> resultMap = registerCardBean.checkSIMCardNo(telNumber, termInfo, curMenuId, simCardNo);
    	
    	// 接口调用成功，且成功返回验证是否成功的标志
    	if(resultMap.containsKey("authChkResult"))
    	{
    		// SIM卡卡号验证成功
    		if("1".equals(resultMap.get("authChkResult")))
    		{
    			successBz = "0";
    			setSuccessMessage("业务办理成功,请取走您的凭条!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名制认证SIM卡卡号验证成功");
    			return SUCCESS;
    		}
    		// SIM卡卡号验证失败
    		else
    		{
    			getRequest().setAttribute("errormessage", "SIM卡卡号验证失败，请重新输入!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证SIM卡卡号验证失败");
    			
    			this.qryCurrentMonth();
    			return "failed";
    		}
    	}
    	// 接口无法返回正确的验证成功与否的标志
    	else
    	{
    		logger.info("SIM卡卡号验证失败!");
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证SIM卡卡号验证失败");
    		
    		getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    			
    		this.qryCurrentMonth();
    		return "failed";
    	}
    }
    
    /**
     * 交费记录验证
     * 
     * @return 页面跳转信息
     * @remark create wWX217192 2014-07-01 OR_huawei_201406_338
     */
    public String checkChargeRecord()
    {
    	// 非实名制认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
    	commitNum++;
    	
    	accessName = "充值记录";
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	// modify begin wWX217192 2015-02-05 OR_SD_201412_597 关于支撑实名认证受理操作流的需求 
    	String pattern = "yyyyMM";
    	String curMonth = CommonUtil.getLastMonth(pattern, 0);
    	String lastMonth = CommonUtil.getLastMonth(pattern, 1);
    	String preLastMonth = CommonUtil.getLastMonth(pattern, 2);
    	
    	// 添加充值日期的年份信息，添加当月信息
    	chargeRecordPO.setCurrMonChargeDate(curMonth.substring(0, 4) + "-" +chargeRecordPO.getCurrMonChargeDate());
    	
    	// 添加上一个月的年份信息
    	chargeRecordPO.setLastMonChargeDate(lastMonth.substring(0, 4) + "-" + chargeRecordPO.getLastMonChargeDate());
    	
    	// 添加上上个月的年份信息
    	chargeRecordPO.setPreLastMonChargeDate(preLastMonth.substring(0, 4) + "-" + chargeRecordPO.getPreLastMonChargeDate());
    	// modify end wWX217192 2015-02-05 OR_SD_201412_597 关于支撑实名认证受理操作流的需求
    	
    	Map<String, String> resultMap = registerCardBean.checkChargeRecord(telNumber, termInfo, curMenuId, chargeRecordPO);
    	
    	// 接口调用成功，且成功返回验证是否成功的标志
    	if(resultMap.containsKey("authChkResult"))
    	{
    		// 交费记录验证成功
    		if("1".equals(resultMap.get("authChkResult")))
    		{
    			successBz = "0";
    			setSuccessMessage("业务办理成功,请取走您的凭条!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名制认证交费记录验证成功");
    			return SUCCESS;
    		}
    		// 交费记录验证失败
    		else
    		{
    			getRequest().setAttribute("errormessage", "交费记录验证失败，请重新输入!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证交费记录验证失败");
    			
    			this.qryCurrentMonth();
    			return "failed";
    		}
    	}
    	// 接口无法返回正确的验证成功与否的标志
    	else
    	{
    		logger.info("交费记录验证失败!");
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证交费记录验证失败");
    		
    		getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    		
    		this.qryCurrentMonth();
    		return "failed";
    	}
    }

    /**
     * 加载页面上的充值记录的月份
     * @throws Exception
     * @remark create wWX217192 2014-07-01 OR_huawei_201406_338
     */
    private void qryCurrentMonth()
    {
    	logger.debug("qryCurrentMonth Starting ...");
        
        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyy年MM月";
        
        for(int i = 0; i < 6; i++)
        {
        	monthList.add(CommonUtil.getLastMonth(pattern, i));
        }
        
        logger.debug("qryCurrentMonth End");
    }
    
    /**
     * 判断月份天数
     * @param month
     * @return
     */
    public int monthSize(String month)
    {
    	int year = Integer.valueOf(month.substring(0, 4));
    	int mon = Integer.valueOf(month.substring(4));

    	// 判断是否为闰年
    	boolean leapYear = isLeapYear(year);
    	int monthSize = 0;
    	if (mon==1 || mon== 3 || mon== 5 || mon==7 || mon==8 || mon==10 || mon==12)
    	{
    		monthSize = 31;
    	}
    	else if (leapYear && mon==2)
    	{
    		monthSize = 29;
    	}
    	else if (!leapYear && mon==2)
    	{
    		monthSize = 28;
    	}
    	else 
    	{
    		monthSize = 30;
    	}
    	return monthSize;
    }
    
    /**
     * 判断是否为闰年
     * @param year
     * @return
     */
	private boolean isLeapYear(int year)
	{
		boolean leapYear = false;
    	if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
    	{
    	    leapYear = true;
    	}
		return leapYear;
	}
	
    /**
     * 通话记录验证接口
     * 
     * @return 页面跳转信息
     * @remark create wWX217192 2014-07-04 OR_huawei_201406_338
     */
    public String checkCallRecord()
    {
    	// 非实名制认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
    	commitNum++;
    	
    	accessName = "通话记录";
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
    	Map<String, String> resultMap = registerCardBean.checkCallRecord(telNumber, termInfo, curMenuId, callNums);
    	
    	// 接口调用成功，且成功返回验证是否成功的标志
    	if(resultMap.containsKey("authChkResult"))
    	{
    		// 通话记录验证成功
    		if("1".equals(resultMap.get("authChkResult")))
    		{
    			successBz = "0";
    			setSuccessMessage("业务办理成功,请取走您的凭条!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "0", "非实名制认证通话记录验证成功");
    			return SUCCESS;
    		}
    		// 通话记录验证失败
    		else
    		{
    			getRequest().setAttribute("errormessage", "通话记录验证失败，请重新输入!");
    			this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证通话记录验证失败");
    			this.qryCurrentMonth();
    			return "failed";
    		}
    	}
    	// 接口无法返回正确的验证成功与否的标志
    	else
    	{
    		logger.info("通话记录验证失败!");
    		this.createRecLog(Constants.BUSITYPE_NOREALNAMEREG, "0", "0", "1", "非实名制认证通话记录验证失败");
    		
    		getRequest().setAttribute("errormessage", resultMap.get("returnMsg"));
    		
    		this.qryCurrentMonth();
    		return "failed";
    	}
    }
    
    /**
     * 实名制认证的第二次认证是否超过限定次数
     * @return
     */
    public boolean isGtSecondRealNameTime()
    {
        // 提交次数达到限制
        if(Constants.SECONDREALNAMEREG_MAXTIMES < commitNum)
        {
            this.getRequest().setAttribute("errormessage","对不起，您的非实名制认证已经超过"
                    +Constants.SECONDREALNAMEREG_MAXTIMES+"次，如果您需要继续认证，可以重新进入非实名认证补卡！");
            return true;
        }
        commitNum++;
        return false;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public int getVerificationAccess() {
		return verificationAccess;
	}

	public void setVerificationAccess(int verificationAccess) {
		this.verificationAccess = verificationAccess;
	}

	public String getRechargeDate1() {
		return rechargeDate1;
	}

	public void setRechargeDate1(String rechargeDate1) {
		this.rechargeDate1 = rechargeDate1;
	}

	public String getRechargeDate2() {
		return rechargeDate2;
	}

	public void setRechargeDate2(String rechargeDate2) {
		this.rechargeDate2 = rechargeDate2;
	}

	public String getRechargeDate3() {
		return rechargeDate3;
	}

	public void setRechargeDate3(String rechargeDate3) {
		this.rechargeDate3 = rechargeDate3;
	}

	public String getRechargeValue1() {
		return rechargeValue1;
	}

	public void setRechargeValue1(String rechargeValue1) {
		this.rechargeValue1 = rechargeValue1;
	}

	public String getRechargeValue2() {
		return rechargeValue2;
	}

	public void setRechargeValue2(String rechargeValue2) {
		this.rechargeValue2 = rechargeValue2;
	}

	public String getRechargeValue3() {
		return rechargeValue3;
	}

	public void setRechargeValue3(String rechargeValue3) {
		this.rechargeValue3 = rechargeValue3;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public NoRealNameRegBean getRegisterCardBean() {
		return registerCardBean;
	}

	public void setRegisterCardBean(NoRealNameRegBean registerCardBean) {
		this.registerCardBean = registerCardBean;
	}

	public String getRandomPwd() {
		return randomPwd;
	}

	public void setRandomPwd(String randomPwd) {
		this.randomPwd = randomPwd;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getServPasswd() {
		return servPasswd;
	}

	public void setServPasswd(String servPasswd) {
		this.servPasswd = servPasswd;
	}

	public String getSimCardNo() {
		return simCardNo;
	}

	public void setSimCardNo(String simCardNo) {
		this.simCardNo = simCardNo;
	}

	public ChargeRecordPO getChargeRecordPO() {
		return chargeRecordPO;
	}

	public void setChargeRecordPO(ChargeRecordPO chargeRecordPO) {
		this.chargeRecordPO = chargeRecordPO;
	}

	public String getCallNums() {
		return callNums;
	}

	public void setCallNums(String callNums) {
		this.callNums = callNums;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getSuccessBz() {
		return successBz;
	}

	public void setSuccessBz(String successBz) {
		this.successBz = successBz;
	}

	public int getCommitNum() {
		return commitNum;
	}

	public void setCommitNum(int commitNum) {
		this.commitNum = commitNum;
	}

	public List<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}

}
