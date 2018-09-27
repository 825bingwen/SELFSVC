/*
* @filename: RealNameRegAction.java
* @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
* @brif:  实名认证办理
* @author: hWX5316476
* @de:  2014-06-10 
* @description: 新增实名认证action
* @remark: create hWX5316476 2014-05-30 V200R003C10LG0601 OR_SD_201405_849
*/
package com.customize.sd.selfsvc.realNameReg.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.RealNameRegBean;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 实名认证action
 * @author hWX5316476
 *
 */
public class RealNameRegAction extends BaseAction
{
    /**
     * 日志
     */ 
    private static Log logger = LogFactory.getLog(RealNameRegAction.class);
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 服务号码
     */
    private String servnumber;
    
    /**
     * 第一次校验校验方式
     */
    public String selectMethod;
    
    /**
     * 第二次校验校验方式
     */
    public String selectMethod2;
    
    /**
     * 短信随机码
     */
    private String randomPwd;
    
    /**
     * 服务密码
     */
    private String password;
    
    /**
     * 卡号
     */
    private String cardNum;
    
    /**
     * 被叫号码
     */
    private String calledNum;
    
    /**
     * 近三个月（包含当前月）充值记录
     */
    private ChargeRecordPO chargeRecordPO;
    
    /**
     * 提交验证次数
     */
    private int commitNum = 1;
    
    /**
     * 错误提示信息
     */
    private String errorMsg;
    
    /**
     * 实名制认证bean
     */
    private transient RealNameRegBean realNameRegBean;
    
	/**
	 * 年月日期列表
	 */
	private List<String> monthList = new ArrayList<String>();
    
    /**
     * 页面成功信息
     */
	private String successMessage;
	
	/**
	 * 5选3实名登记预受理第一步验证次数
	 */
	private int firstCommitNum = 1;
	
	/**
	 * 5选3实名登记预受理第二步验证次数
	 */
	private int secondCommitNum = 1;
	
	/**
	 * 5选3实名登记预受理第三步验证次数
	 */
	private int thirdCommitNum = 1;
	
	/**
	 * 5选2实名登记预受理第一步验证次数
	 */
	private int firCommitNum2 = 1;
	
	// add begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
	/**
	 * 实名认证方式选择标志 realNameRegFlag52\realNameRegFlag53
	 */
	private String realNameRegFlag = null;
	
	/**
	 * 通话记录串
	 */
	private String chargeRecord = "";
	// add end wWX217192 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
	
	// modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单
	private String[] reccceptNum;
	// modify end by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单
	
	/**
	 * 查询实名认证方式
	 * @return
	 */
	public String qryIdentityAuthMethods()
	{
		return "identityAuthMethods";
	}
    
    /**
     * 初次身份认证密码校验
     * @return
     */
    public String verifyPwd()
    {
    	if(isRealNameAccessTimes(firCommitNum2))
    	{
    		return ERROR;
    	}
    	// 第一次验证次数
    	firCommitNum2++;
    	
        // 随机短信验证
        if("selectRandomPwd".equals(selectMethod))
        {
            return verifyRandomPwd();
        } 
        // SIM卡验证
        else if("selectSIM".equals(selectMethod))
        {
        	return chkSIMCardNo();
        }
        
        return "faild";
    }
    
    /**
     * 二次身份认证
     * @return
     */
    public String secondAuthMethods()
    {
        // 二次认证次数超过5次,不能继续认证
        if(isGtSecondRealNameTime())
        {
            return ERROR;
        }
        
        // add begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        Map<String,String> map = new HashMap<String,String>();// 校验内容
        
        // 随机短信验证
        if("selectRandomPwd".equals(selectMethod))
        {
        	map.put("RandomPwdChk", randomPwd);
        } 
        // SIM卡验证
        else if("selectSIM".equals(selectMethod))
        {
        	map.put("SimChk", cardNum);
        }
        // and end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        
        // modify begin wWX217192 2014-08-20 OR_huawei_201408_964 自助终端新增实名登记认证（自助终端5选2认证方式)优化
        // 服务密码验证
        if("selectServerPwd".equals(selectMethod2))
        {
        	//return verifyServerPwd;
        	
        	if(SUCCESS.equals(verifyServerPwd()))
        	{
        		// 服务密码验证
        		map.put("PasswordChk", "1");
        	}
        }
        // modify end wWX217192 2014-08-20 OR_huawei_201408_964 自助终端新增实名登记认证（自助终端5选2认证方式)优化
        
        // 充值记录验证
        else if("selectRechargeRecord".equals(selectMethod2))
        {    
			//return chkChargeRecord();
        	String chargeRecodes = chkChargeRecord();//获取充值记录

        	if (StringUtils.isNotEmpty(chargeRecodes))
        	{
				//充值记录验证
	        	map.put("ChargeRecordChk",chargeRecodes);
        	}
        }
        // 通话记录验证
        else if("selectCallRecord".equals(selectMethod2))
        {
            //return chkCallRecord();
        	String callRecords = chkCallRecord();//获取通话记录
        	
        	if(StringUtils.isNotEmpty(callRecords))
        	{
        		//通话记录认证
        		map.put("CallRecordChk", callRecords);
        	}
        }
        
        if(map.size()<2)
        {
        	return "failed";
        }
        else
        {
        	// 实名制受理提交
    		return saveRealNameChkRecLog(map);
        }
		
    }
    
    /** 
     * 发送短信随机码
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     */
    public void sendRandomPwd()
    {
        logger.debug("sendRandomPwd start");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        PrintWriter out = null;
        String retMsg = "";
        try
        {
            this.getRequest().setCharacterEncoding("GBK");
            this.getResponse().setContentType("text/html;charset=GBK");
            out = this.getResponse().getWriter();
            Map<String,String> map = realNameRegBean.sendRandomPwd(this.getTerminalInfoPO(), curMenuId, servnumber);
            if(null != map.get("new_passwd"))
            {
                logger.info("向用户"+servnumber+"发送短信验证码成功！此次短信随机验证码为："+map.get("new_passwd"));
                this.createRecLog(realNameRegLog, "0", "0", "0", "实名认证发送短信验证码成功");
            }
            else
            {
                logger.error("向用户"+servnumber+"发送短信验证码失败！请稍后再试！");
                this.createRecLog(realNameRegLog, "0", "0", "1", map.get("returnMsg"));
                retMsg = "对不起，短信随机码发送失败，请稍后再试!";
            }
        }
        catch(IOException e)
        {
            logger.error("向用户"+servnumber+"发送短信验证码异常！请稍后再试！");
            retMsg = "对不起，短信随机码发送失败，请稍后再试!";
        }
        catch (Exception e)
        {
            logger.error("向用户"+servnumber+"发送短信验证码异常！请稍后再试！");
            retMsg = "对不起，短信随机码发送失败，请稍后再试!";
        }
        finally
        {
            if(null != out)
            {
                out.write(retMsg);
                out.flush();
                out.close();
            }
        }
        
        logger.debug("sendRandomPwd end");
    } 
   
    /**
     * 短信随机码验证
     * @return
     * 
     */
    public String verifyRandomPwd()
    {
        logger.debug("verifyRandomPwd Start");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        Map<String,String> map = realNameRegBean.verifyRandomPwd(this.getTerminalInfoPO(), curMenuId, servnumber, randomPwd);
        
        if(map.size() > 1)
        {
            // 验证结果 0失败 1 成功
            String authchkresult = map.get("authchkresult");
            
            // 验证结果信息
            String authchkmsg = map.get("authchkmsg");
            
            // 返回结果失败
            if(!"1".equals(authchkresult))
            {
            	this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证短信验证码验证失败！");
            	errorMsg = (StringUtils.isEmpty(authchkmsg))?"对不起，短信验证码验证失败！":authchkmsg;
            }
        }
        else
        {
        	this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证短信验证码验证失败！");
        	errorMsg = StringUtils.isEmpty(map.get("returnMsg"))?"对不起,短信验证码验证异常，请稍后再试！":map.get("returnMsg");
        }
        
        // 验证失败，弹出提示信息
        if(null != errorMsg)
        {
        	this.qryCurrentMonth();
            
            return "failed";
        }
        
        this.qryCurrentMonth();
        
        this.createRecLog(realNameRegLog, "0", "0", "0", "实名认证短信验证码验证成功！");
        
        logger.debug("verifyRandomPwd End");
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // 记录实名认证受理日志
        // return saveRealNameChkRecLog("RandomPwdChk", randomPwd);
        return SUCCESS;
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
    }
    
    /**
     * 服务密码验证
     * @return
     */
    public String verifyServerPwd()
    {
        logger.debug("verifyServerPwd Start");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        Map<String,String> map = realNameRegBean.verifyServerPwd(this.getTerminalInfoPO(), curMenuId, servnumber, password);
        
        if(map.size() > 1)
        {
            // 验证结果 0失败 1 成功
            String authchkresult = map.get("authchkresult");
            
            // 验证结果信息
            String authchkmsg = map.get("authchkmsg");
            
            // 返回结果失败
            if(!"1".equals(authchkresult))
            {
            	this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证个人密码验证失败！");
            	errorMsg = (StringUtils.isEmpty(authchkmsg))?"对不起，个人密码验证失败！":authchkmsg;
            }
        }
        else
        {
        	this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证个人密码验证失败！");
        	errorMsg = StringUtils.isEmpty(map.get("returnMsg"))?"对不起,个人密码验证异常，请稍后再试！":map.get("returnMsg");
        }
        
        // 验证失败，弹出提示信息
        if(null != errorMsg)
        {
        	this.qryCurrentMonth();
            
            return "failed";
        }
        
        this.qryCurrentMonth();
        
        this.createRecLog(realNameRegLog, "0", "0", "0", "实名认证个人密码验证成功！");
        
        logger.debug("verifyServerPwd End");
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // 记录实名认证受理日志
        // return saveRealNameChkRecLog("PasswordChk", "1");
        return SUCCESS;
        // modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
    }

    /**
     * SIM卡认证
     * @return
     */
    public String chkSIMCardNo()
    {
        logger.debug("chkSIMCardNo start!");
        
        String realNameRegLog = this.getRealNameRegLog();

        HttpSession session = this.getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO termInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // SIM卡校验
        Map<String,String> map = realNameRegBean.chkSIMCardNo(termInfoPO, curMenuId, servnumber, cardNum);
        
        if(map.size() > 1)
        {
            // 验证结果 0失败 1 成功
            String authchkresult = map.get("authchkresult");
            
            // 验证结果信息
            String authchkmsg = map.get("authchkmsg");
            
            // 返回结果失败
            if(!"1".equals(authchkresult))
            {
                errorMsg = (StringUtils.isEmpty(authchkmsg))?"对不起，SIM卡认证失败，请检查您输入的SIM卡号！":authchkmsg;
                this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证SIM卡认证失败！");
            }
        }
        else
        {
            errorMsg =  (StringUtils.isEmpty(map.get("returnMsg")))?"对不起，SIM卡验证异常，请稍后再试！":map.get("returnMsg");
            this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证SIM卡认证异常！");
        }
        
        // 验证失败，弹出提示信息
        if(null != errorMsg)
        {
        	this.qryCurrentMonth();
            
            return "failed";
        }
        
        logger.debug("chkSIMCardNo end");
        
        this.qryCurrentMonth();
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // SIM认证成功后记录实名登记受理日志
        //return saveRealNameChkRecLog("SimChk", cardNum);
        return SUCCESS;
        // modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
    }
    
    /**
     * 充值记录验证
     * @return
     */
    public String chkChargeRecord()
    {
        logger.debug("chkChargeRecord start!");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        // 终端机信息
        TerminalInfoPO termInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);

        String currMonChargeDate = chargeRecordPO.getCurrMonChargeDate();
        String lastMonChargeDate = chargeRecordPO.getLastMonChargeDate();
        String preLastMonChargeDate = chargeRecordPO.getPreLastMonChargeDate();
        
        // 充值记录验证
        Map<String,String> map = realNameRegBean.chkChargeRecord(termInfoPO, curMenuId, servnumber, chargeRecordPO);
        
        if(map.size() > 1)
        {
            // 验证结果 0失败 1 成功
            String authchkresult = map.get("authchkresult");
            
            // 验证结果信息
            String authchkmsg = map.get("authchkmsg");
            
            // 返回结果失败
            if(!"1".equals(authchkresult))
            {
                errorMsg = (StringUtils.isEmpty(authchkmsg))?"对不起，充值记录验证失败，请检查您输入的充值记录！":authchkmsg;
                this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证充值记录验证失败！");
            }
        }
        else
        {
            errorMsg = (StringUtils.isEmpty(map.get("returnMsg")))?"对不起，充值记录验证异常，请稍后再试！":map.get("returnMsg");
            this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证充值记录验证异常！");
        }
        
        // 验证失败，弹出提示信息
        if(null != errorMsg)
        {
            /*chargeRecordPO = new ChargeRecordPO(currMon, lastMon, preLastMon);*/
        	this.qryCurrentMonth();
        	
        	// modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
            //return "failed";
        	return "";// 失败后返回空字符串
        	// modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        }
        
        logger.debug("chkChargeRecord end");
        
        StringBuffer chargeRecord = new StringBuffer();
        chargeRecord.append(currMonChargeDate).append(",").append(chargeRecordPO.getCurrMonChargeAmount());
        chargeRecord.append(lastMonChargeDate).append(",").append(chargeRecordPO.getLastMonChargeAmount());
        chargeRecord.append(preLastMonChargeDate).append(",").append(chargeRecordPO.getPreLastMonChargeAmount());
        
        this.qryCurrentMonth();
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // 充值记录验证成功后记录实名登记受理日志
        //return saveRealNameChkRecLog("ChargeRecordChk", chargeRecord.toString());
        return chargeRecord.toString();// 成功返回充值记录串
        // modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
    }
    
    /**
     * 通话记录验证
     * @return
     */
    public String chkCallRecord()
    {
        logger.debug("chkCallRecord start");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        // 终端机信息
        TerminalInfoPO termInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 通话记录验证
        Map<String,String> map = realNameRegBean.chkCallRecord(termInfoPO, curMenuId, servnumber, calledNum.replace(" ", ""));
        
        // 接口调用成功
        if(map.size() > 1)
        {
            // 验证结果 0失败 1 成功
            String authchkresult = map.get("authchkresult");
            
            // 验证结果信息
            String authchkmsg = map.get("authchkmsg");
            
            // 验证失败
            // modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单 
            if(!"1".equals(authchkresult))
            {
                reccceptNum = this.calledNum.split(", ");
                errorMsg = (StringUtils.isEmpty(authchkmsg))?"对不起，通话记录验证失败，请检查您输入的呼叫号码！":authchkmsg;
                this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证通话记录验证失败！");
            }
            // modify end by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单 
            
        }
        // 接口调用异常
        else
        {
            errorMsg = (StringUtils.isEmpty(map.get("returnMsg")))?"对不起，通话记录验证异常，请稍后再试！":map.get("returnMsg");
            this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证通话记录验证异常！");
        }
        
        // 验证失败，弹出提示信息
        if(null != errorMsg)
        {
            /*String currMon = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 0);
            String lastMon = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 1);
            String preLastMon = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 2);
            chargeRecordPO = new ChargeRecordPO(currMon, lastMon, preLastMon);*/
        	
        	this.qryCurrentMonth();
            
            calledNum = null;
            // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
            return "";  //如果失败返回空字符串
            // modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        }
        
        this.createRecLog(realNameRegLog, "0", "0", "0", "实名认证通话记录验证成功");
        logger.debug("chkCallRecord end");
        
        this.qryCurrentMonth();
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // 通话记录验证成功记录实名验证日志
        //return saveRealNameChkRecLog("CallRecordChk", calledNum.replace(" ", "").replaceAll(",", ";"));
        return calledNum.replace(" ", "").replaceAll(",", ";"); //如果返回成功返回通话记录字符串
        // modify end gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
    }
    
    /**
     * 记录实名登记受理日志
     * @return String
     * 
     */
    public String saveRealNameChkRecLog(Map<String,String> map)
    {
        logger.debug("saveRealNameChkRecLog start");
        
        String realNameRegLog = this.getRealNameRegLog();
        
        // 终端机信息
        TerminalInfoPO termInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        // 记录实名登记受理日志
         //boolean isSave= realNameRegBean.saveRealNameChkRecLog(termInfoPO, curMenuId, servnumber ,chkMethod, chkValue);
         ReturnWrap rw= realNameRegBean.saveRealNameChkRecLog(termInfoPO, curMenuId, servnumber ,map);
        
        // 日志记录成功
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            this.createRecLog(realNameRegLog, "0", "0", "0", "实名认证记录实名登记受理日志成功！");
            setSuccessMessage("业务办理成功,请取走您的凭条!");
            return SUCCESS;
        }
        // modify begin gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
        
        this.getRequest().setAttribute("errormessage",rw.getReturnMsg());
        this.createRecLog(realNameRegLog, "0", "0", "1", "实名认证记录实名登记受理日志失败");
        logger.debug("saveRealNameChkRecLog end");
        return ERROR;
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
            this.getRequest().setAttribute("errormessage","对不起，您的实名认证已经超过"
                    +Constants.SECONDREALNAMEREG_MAXTIMES+"次，如果您需要继续认证，可以退出重新进入实名认证！");
            return true;
        }
        commitNum++;
        return false;
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
	
	/*------------------------------------------ 实名登记预受理5选3方式 --------------------------------------------*/
	
	/**
	 * 实名登记预受理5选3第一种页面方式显示
	 * @return 页面跳转信息
	 * @remark create by wWX217192 2014-08-05 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
	 */
	public String showRealNameRegAccess()
	{
		return "realNameRegAccess";
	}
	
	/**
     * 实名制第一次认证选择
     * @return
     * @remark create by wWX217192 2014-08-05 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
     */
    public String regRealNameFirstAccess()
    {
    	if(isRealNameAccessTimes(firstCommitNum))
    	{
    		return ERROR;
    	}
    	// 第一次验证次数
    	firstCommitNum++;
    	
        // 随机短信验证
        if("selectRandomPwd".equals(selectMethod))
        {
            return verifyRandomPwd();
        } 
        // 服务密码验证
        else if("selectServerPwd".equals(selectMethod))
        {
            return verifyServerPwd();
        }
        // SIM卡验证
        else if("selectSimNo".equals(selectMethod))
        {
        	return chkSIMCardNo();
        }
        
        return "failed";
    }
    
    
    
    /**
     * 实名制认证5选3的第一次认证是否超过限定次数
     * @return
     * @remark create by wWX217192 2014-08-05 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
     */
    public boolean isRealNameAccessTimes(int commitTimes)
    {
        // 提交次数达到限制
        if(Constants.SECONDREALNAMEREG_MAXTIMES < commitTimes)
        {
            this.getRequest().setAttribute("errormessage","对不起，您的实名认证已经超过"
                    +Constants.SECONDREALNAMEREG_MAXTIMES+"次，如果您需要继续认证，可以退出重新进入实名认证！");
            return true;
        }
        return false;
    }
    
    /**
     * 实名制5选3第二步认证
     * @return
     * @remark create by wWX217192 2014-08-05 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
     */
    public String regRealNameSecondAccess()
    {
    	if(isRealNameAccessTimes(secondCommitNum))
    	{
    		return ERROR;
    	}
    	
    	// 第二次验证次数统计
    	secondCommitNum++;
    	
    	// 充值记录传入第三个页面
    	this.chargeRecord = chkChargeRecord();
    	if(StringUtils.isNotEmpty(this.chargeRecord))
    	{
    		return SUCCESS;
    	}
    	else
    	{
    		return "failed";
    	}
    }
    
    /**
     * 实名制5选3第三步认证
     * @return
     * @remark create by wWX217192 2014-08-05 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
     */
    public String regRealNameThirdAccess()
    {
    	if(isRealNameAccessTimes(thirdCommitNum))
    	{
    		return ERROR;
    	}
    	
    	// 第三次验证次数统计
    	thirdCommitNum++;
    	
    	Map<String,String> map = new HashMap<String,String>();// 校验内容
    	
    	// 第一步
    	
        // 随机短信验证
        if("selectRandomPwd".equals(selectMethod))
        {
        	map.put("RandomPwdChk", randomPwd);
        } 
        // 服务密码验证
        else if("selectServerPwd".equals(selectMethod))
        {
        	map.put("PasswordChk", "1");
        }
        // SIM卡验证
        else if("selectSimNo".equals(selectMethod))
        {
        	map.put("SimChk", cardNum);
        }
        
        // 第二步
        
        // 充值记录
        map.put("ChargeRecordChk", chargeRecord);
        
        // 第三步
        
        // 通话记录
        String callRecord = chkCallRecord();
        if(StringUtils.isNotEmpty(callRecord))
        {
        	map.put("CallRecordChk", callRecord);
        }
        
        if(map.size()<3)
        {
        	return "failed";
        }
          
    	// 实名制受理提交
    	return saveRealNameChkRecLog(map);
        
    	//return chkCallRecord();
    }
    
    /**
     * 判断业务日志类型
     * @return
     * @remark create by wWX217192 2014-08-13 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
     */
    private String getRealNameRegLog()
    {
    	String realNameRegLog = "";
    	
    	if("realNameReg53".equals(curMenuId))
    	{
    		realNameRegLog = Constants.BUSITYPE_REALNAMEREG1;
    	}
    	else
    	{
    		realNameRegLog = Constants.BUSITYPE_REALNAMEREG2;
    	}
    	
    	return realNameRegLog;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getSelectMethod()
    {
        return selectMethod;
    }
    
    public void setSelectMethod(String selectMethod)
    {
        this.selectMethod = selectMethod;
    }
    
    public String getRandomPwd()
    {
        return randomPwd;
    }


    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getCardNum()
    {
        return cardNum;
    }
    
    public void setCardNum(String cardNum)
    {
        this.cardNum = cardNum;
    }
    
    public RealNameRegBean getRealNameRegBean()
    {
        return realNameRegBean;
    }
    
    public void setRealNameRegBean(RealNameRegBean realNameRegBean)
    {
        this.realNameRegBean = realNameRegBean;
    }

    public ChargeRecordPO getChargeRecordPO()
    {
        return chargeRecordPO;
    }

    public void setChargeRecordPO(ChargeRecordPO chargeRecordPO)
    {
        this.chargeRecordPO = chargeRecordPO;
    }

    public String getCalledNum()
    {
        return calledNum;
    }

    public void setCalledNum(String calledNum)
    {
        this.calledNum = calledNum;
    }

    public int getCommitNum()
    {
        return commitNum;
    }

    public void setCommitNum(int commitNum)
    {
        this.commitNum = commitNum;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

	public String getSelectMethod2() {
		return selectMethod2;
	}

	public void setSelectMethod2(String selectMethod2) {
		this.selectMethod2 = selectMethod2;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public int getFirstCommitNum() {
		return firstCommitNum;
	}

	public void setFirstCommitNum(int firstCommitNum) {
		this.firstCommitNum = firstCommitNum;
	}

	public int getSecondCommitNum() {
		return secondCommitNum;
	}

	public void setSecondCommitNum(int secondCommitNum) {
		this.secondCommitNum = secondCommitNum;
	}

	public int getThirdCommitNum() {
		return thirdCommitNum;
	}

	public void setThirdCommitNum(int thirdCommitNum) {
		this.thirdCommitNum = thirdCommitNum;
	}

	public int getFirCommitNum2() {
		return firCommitNum2;
	}

	public void setFirCommitNum2(int firCommitNum2) {
		this.firCommitNum2 = firCommitNum2;
	}

	public List<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}

	public String getRealNameRegFlag() {
		return realNameRegFlag;
	}

	public void setRealNameRegFlag(String realNameRegFlag) {
		this.realNameRegFlag = realNameRegFlag;
	}

	public String getChargeRecord() {
		return chargeRecord;
	}

	public void setChargeRecord(String chargeRecord) {
		this.chargeRecord = chargeRecord;
	}

	// modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单
    public String[] getReccceptNum()
    {
        return reccceptNum;
    }

    public void setReccceptNum(String[] reccceptNum)
    {
        this.reccceptNum = reccceptNum;
    }
    // modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单

}
