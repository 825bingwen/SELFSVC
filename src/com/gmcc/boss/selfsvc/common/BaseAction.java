/*
 * 文件名：BaseAction.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g69866
 * 修改时间：2009-8-5
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.common.base.CommandContext;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.common.model.UserSatfyVO;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.huawei.alert.business.IAlertMsgService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * 
 * BaseAction <功能详细描述>
 * 
 * @author 自助终端项目组
 * @version [版本号, Dec 10, 2010]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    // HttpServletRequest
    private HttpServletRequest request;
    
    // HttpServletResponse
    private HttpServletResponse response;
    
    // selfSvcService
    private transient SelfSvcService selfSvcService;
    
    // add begin g00140516 2012/08/07 R003C12L08n01 OR_HUB_201203_367
    private IAlertMsgService alertMsgService = null;
    // add end g00140516 2012/08/07 R003C12L08n01 OR_HUB_201203_367
    
    // add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 
    //湖北用户满意度调查之用户手机号
    private String servnumber = "";
    
    //湖北用户满意度调查之整体评分
	private String totScore = "";
	
	//湖北用户满意度调查之单项评分，字符串
	private String speciSatfyScore = "";
	
	//湖北用户满意度调查之最常用电子渠道
	private String favorEC = "";
	// add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
	
	// add begin qWX279398 2015-5-13 OR_SD_201503_942_山东_自助终端提示换USIM
	/**
	 * USIM卡提示标志
	 */
	private String uSIMflag = "0";
	// add end qWX279398 2015-5-13 OR_SD_201503_942_山东_自助终端提示换USIM

	// add begin jWX216858 2015-5-28 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑
	/**
     * 当前菜单
     */
    private String curMenuId;
    // add end jWX216858 2015-5-28 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑
    
	// add begin jWX216858 2015-5-13
    // js版本号，例：20150513 解决IE客户端缓存未清理导致JS未更新
	public String getJsVersion()
	{
		return CommonUtil.getParamValue(Constants.SH_JSVERSION);
	}
	// add end jWX216858 2015-5-13
	
    /**
     * 获取field error标识 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getFieldErrFlag()
    {
        if (hasFieldErrors() || hasActionErrors())
        {
            return "true";
        }
        else
        {
            return "";
        }
    }
    
    /**
     * IoC方式注入HttpServletRequest
     * 
     * @param request
     */
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    /**
     * IoC方式注入HttpServletResponse
     * 
     * @param response
     */
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    /**
     * 获取HttpServletRequest <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public HttpServletRequest getRequest()
    {
        return this.request;
    }
    
    /**
     * 获取HttpServletResponse <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public HttpServletResponse getResponse()
    {
        return this.response;
    }
    
    /**
     * 业务日志处理 <功能详细描述>
     * 
     * @param busiType 业务类型
     * @param recFormnum 业务流水号
     * @param recFee 业务受理金额
     * @param recStatus 业务受理状态 0：成功，1：失败
     * @param recStatusDesc 业务受理描述
     * @see [类、类#方法、类#成员]
     */
    protected void createRecLog(String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        // 业务推荐营业员的手机号码
        String rectel = "";
        
        // 客户信息
        NserCustomerSimp customer = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// 区域
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }
        }
        else
        {
            selfSvcLogVO.setRegion("");// 区域
        }
       
        if (customer != null)
        {
            selfSvcLogVO.setServnumber(customer.getServNumber());// 手机号码
        }
        else
        {
            selfSvcLogVO.setServnumber("");// 手机号码
        }
        
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// 终端编号
        selfSvcLogVO.setTourchoid(customer == null ? "" : customer.getContactId());// 统一接触流水
        
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// 业务受理单位编码
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// 操作员工号
        
        selfSvcLogVO.setBusitype(busiType);// 业务类型
        selfSvcLogVO.setRecformnum(recFormnum);// 业务流水号
        selfSvcLogVO.setRecfee(recFee);// 业务受理金额
        selfSvcLogVO.setRecstatus(recStatus);// 业务受理状态
        if(recStatusDesc.length()>256)
        {
            recStatusDesc = recStatusDesc.substring(0,256);
        }
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// 业务受理描述
        selfSvcLogVO.setBrandID(customer == null ? "" : customer.getBrandID());  //用户品牌
        selfSvcLogVO.setRectel(rectel);
        //modify end g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        
        selfSvcService.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <记录业务日志>
     * <功能详细描述>
     * @param servNumber 手机号码
     * @param busiType 业务类型
     * @param recFormnum 业务流水号
     * @param recFee 业务受理费用，无费用的为0
     * @param recStatus 0:成功。1:失败
     * @param recStatusDesc 描述信息
     * @see [类、类#方法、类#成员]
     */
    protected void createRecLog(String servNumber, String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        // 业务推荐营业员的手机号码
        String rectel = "";
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// 区域
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }            
        }
        else
        {
            selfSvcLogVO.setRegion("");// 区域
        }
        
        selfSvcLogVO.setServnumber(servNumber);// 手机号码
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// 终端编号
        selfSvcLogVO.setTourchoid("");// 统一接触流水
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// 业务受理单位编码
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// 操作员工号
        
        selfSvcLogVO.setBusitype(busiType);// 业务类型
        selfSvcLogVO.setRecformnum(recFormnum);// 业务流水号
        selfSvcLogVO.setRecfee(recFee);// 业务受理金额
        selfSvcLogVO.setRecstatus(recStatus);// 业务受理状态
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// 业务受理描述
        selfSvcLogVO.setRectel(rectel);
        //modify end g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        
        selfSvcService.createRecLog(selfSvcLogVO);
    }
    
    /**
     * 生成短信随机密码
     * <功能详细描述>
     * @param servNumber 手机号
     * @param sendTime 随机短信密码
     * @see [类、类#方法、类#成员]
     */
    protected String createRandomPassword(String servNumber,String sendTime)
    {
        // 随机密码长度
        int len = 0;
        String strLen = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_LEN);
        if (strLen == null || "".equals(strLen.trim()))
        {
            len = 6;
        }
        else
        {
            len = Integer.parseInt(strLen);
        }
        
        // 生成随机密码
        String randomPwd = CommonUtil.getRandomNum(len);
        
        // 写入session
        this.getRequest().getSession().setAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD, 
                randomPwd + "_" + sendTime);// randomPass随机短信密码
        
        // 返回
        return randomPwd;
    }
    
    /**
     * 随机密码认证
     * 
     * @param servNumber 服务号码
     * @param randomPwd 随机密码
     * @return 1，成功；0，密码错误；-1，超时
     * @see 
     */
    protected String loginWithRandomPwd(String servNumber, String randomPwd)
    {
        // 检查session中是否存在 手机号+Constants.AUTHTYPE_RANDOMPWD key 
        Object obj = this.getRequest().getSession().getAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
        if (obj == null)
        {
            return "0";
        }
        
        String[] info = ((String) obj).split("_");
        
        //随机密码输入错误
        if (!info[0].equalsIgnoreCase(randomPwd))
        {
            return "0";
        }
        
        // modify begin g00140516 2013/02/07 R003C13L01n01 OR_NX_201302_600
        //随机密码输入正确，进行有效期验证
        if (info.length > 1)
        {         
            int periodOfValidity = 0;
            
            // 宁夏：将随机密码有效期单位改为秒
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
            	String strValidity = (String) PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_VALIDITY);
                if (strValidity == null || "".equals(strValidity.trim()))
                {
                    periodOfValidity = 30;
                }
                else
                {
                    periodOfValidity = Integer.parseInt(strValidity);
                }
            }
            // 其它省份:仍为分钟
            else
            {
            	String strValidity = (String) PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_VALIDITY);
                if (strValidity == null || "".equals(strValidity.trim()))
                {
                    periodOfValidity = 5 * 60;
                }
                else
                {
                    periodOfValidity = Integer.parseInt(strValidity) * 60;
                }
            }
            
            try
            {               
                String limitTime = CommonUtil.addDate(info[1], periodOfValidity);
                
                String currTime = CommonUtil.getCurrentTime();// 取得当前时间
                
                if (limitTime.compareTo(currTime) >= 0)
                {                   
                    //验证通过时，删除                    
                    this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
                    
                    return "1";
                }
                else
                {
                    //验证超时时，删除
                    this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
                    
                    return "-1";
                }
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                
                return "0";
            }
        }
        // modify end g00140516 2013/02/07 R003C13L01n01 OR_NX_201302_600
        
        //验证通过时，删除 
        this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
        
        return "1";     
    }
    
    /**
     * 提示信息转换
     * 
     * @param resultMsg 后台接口返回提示
     * @param orimsgid 原提示信息编码
     * @param errcode 后台返回的错误编码
     * @param params 参数
     * @return
     * @see 
     */
    protected String getConvertMsg(String resultMsg, String orimsgid, String errcode, String[] params)
    {
        // modify begin g00140516 2012/11/29 R003C12L11n01 CRM返回的错误码不进行转换
        // CRM返回的错误码不进行转换
        if (errcode != null && !"".equals(errcode.trim()))
        {
            return resultMsg;
        }
        
        // 提示信息转换。1，转；其它，不转
        String convertFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ALERTMSG_CONVERTFLAG);
        if (!"1".equals(convertFlag))
        {
            return resultMsg;
        }
        
        // 用户信息
        NserCustomerSimp customerInfor = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 渠道
        String channel = "bsacAtsv";
        
        // 地区
        String region = termInfo.getRegion();
        
        // 品牌
        String brand = customerInfor == null ? "" : customerInfor.getBrandID();
        
        // 默认提示信息
        String defaultInfo = (String) PublicCache.getInstance().getCachedData(Constants.SH_ALERTMSG_DEFAULTINFO);
        
        // 针对自助终端内部的错误码进行转换。若转换后的提示信息是默认提示信息，仍使用原有的提示信息
        String newMsg = getAlertMsgService().getAlertMsg(orimsgid, channel, region, brand, params).getMsgText();
        if (defaultInfo.equalsIgnoreCase(newMsg))
        {
            newMsg = resultMsg;
        }
        // modify end g00140516 2012/11/29 R003C12L11n01 CRM返回的错误码不进行转换      
        
        return newMsg;
    }
    
    /**
     * 通过groupid获取字典表数据
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid)
    {
    	return selfSvcService.getDictItemByGrp(groupid);
    }
    
    // add begion yKF28472 OR_huawei_201305_474
    /**
     * 湖北取小地市REGION
     * <功能详细描述>
     * @param orgId
     * @param region
     * @return 小地市REGION
     * @see [类、类#方法、类#成员]
     */
    public String getSmallregion(String cityOrgId, String region)
    {
        if (cityOrgId != null && !"".equals(cityOrgId))
        {
            List<DictItemPO> smallregionList = getDictItemByGrp("SMALLREGION");
            for(DictItemPO po : smallregionList)
            {
                if (po.getDictid().equals(cityOrgId))
                {
                    // 返回小地市region
                    return po.getDictname();
                }
            }
        }
        
        // 未从字典表中查询到，表示非728地市的
        return region;
    }
    // add end yKF28472 OR_huawei_201305_474
    
	/**
	 * 用户满意度调查存入数据库
	 * 从jsp页面表单回传的数据，被存入数据库
	 * @see
	 * @return 无
	 * @remark create m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
	 */
	public void surveyUserSatfy()
	{
        // 获取session
        HttpSession session = getRequest().getSession();
        
		// 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取客户手机号
        servnumber = customer.getServNumber();
        
        UserSatfyVO userSatfyVO = new UserSatfyVO();
        userSatfyVO.setUserNo(servnumber);
        userSatfyVO.setUserTotScore(totScore);
        userSatfyVO.setUserSpeciScore(speciSatfyScore);
        userSatfyVO.setUserFavorEC(favorEC);
        
        if (servnumber != null && totScore != null && speciSatfyScore != null && favorEC != null)
        {    //存入数据库
            selfSvcService.insertUserSatfy(userSatfyVO);
        }
	}
	
	/**
	 * 获取终端机信息
	 * @return
	 */
	public  TerminalInfoPO getTerminalInfoPO()
	{
	    // 从缓存中取出终端机信息
	    TerminalInfoPO termInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
	    return termInfoPO;
	}
	
    /** 
     * 取得当前登录用户
     * 
     * @return NserCustomerSimp
     * @see [类、类#方法、类#成员]
     */
    public NserCustomerSimp getCustomerSimp()
    {
        return (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
    }
    
    /**
     * 校验当前时间银联卡充值是否可用。1，可用；0，不可用
     * 
     * @throws Exception
     * @see 
     * @see [类、类#方法、类#成员]
     */
    public void checkTime() throws Exception
    {
        String xml = "0";
        
        // 2320-0025
        String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
        
        if (time != null && !"".equals(time.trim()))
        {
            // 当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");        
            String currTime = sdf.format(new Date());
            
            // 当前时间在0025至2320之间时可用
            String[] times = time.split("-");
            if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
            {
                xml = "1";
            }
        }
        
        // 打印信息
        writeXmlMsg(xml);
    }
    
    /**
     * AJAX调用返回消息
     * 
     * @return void [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    protected void writeXmlMsg(String msg)
    {
        PrintWriter writer = null;
        
        try
        {
            this.getResponse().setContentType("text/xml;charset=GBK");
            writer = this.getResponse().getWriter();
            
            if (null != writer)
            {
                writer.write(msg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
	
    public void setSelfSvcService(SelfSvcService selfSvcService)
    {
        this.selfSvcService = selfSvcService;
    }

    public IAlertMsgService getAlertMsgService()
    {
        return alertMsgService;
    }

    public void setAlertMsgService(IAlertMsgService alertMsgService)
    {
        this.alertMsgService = alertMsgService;
    }
    
	public String getTotScore() {
		return totScore;
	}

	public void setTotScore(String totScore) {
		this.totScore = totScore;
	}

	public String getSpeciSatfyScore() {
		return speciSatfyScore;
	}

	public void setSpeciSatfyScore(String speciSatfyScore) {
		this.speciSatfyScore = speciSatfyScore;
	}

	public String getFavorEC() {
		return favorEC;
	}

	public void setFavorEC(String favorEC) {
		this.favorEC = favorEC;
	}
	
	// add begin qWX279398 2015-5-13 OR_SD_201503_942_山东_自助终端提示换USIM
	/**
	 * @return the uSIMflag
	 */
    public String getuSIMflag()
    {
        return uSIMflag;
    }

    public void setuSIMflag(String uSIMflag)
    {
        this.uSIMflag = uSIMflag;
    }

	// add end qWX279398 2015-5-13 OR_SD_201503_942_山东_自助终端提示换USIM
	
	/**
	 * 生成图片验证码
	 * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 自助终端用户登录验证方式
	 */
	public void createRandomCodeImage()
	{
		String randomCode = CommonUtil.createRandomCode(4);
		
		CommandContext.getLog().debug("图片验证码：" + randomCode);
		this.getRequest().getSession().setAttribute("randomCode", randomCode);
		
		try
		{
			 OutputStream out = this.getResponse().getOutputStream();
	         JPEGImageEncoder encode = JPEGCodec.createJPEGEncoder(out);
	         BufferedImage bi = CommonUtil.createBufferedImage(randomCode);
	         encode.encode(bi);
	         out.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断是否可以发送短信验证码
	 * 返回值errorMsg为空则可以发送，不为空不允许发送
	 * @param servNum 手机号
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	 */
	public String canSmsCode(String servNum)
	{
	    HttpSession session = this.getRequest().getSession();
	    String errorMsg = "";
	    // 获取短信发送频率，单位：秒
        String sendCodeInterval = CommonUtil.getParamValue(ConstantsBase.SH_SENDCODEINTERVAL, "60");
        // 从缓存中取上一次发送短信的时间
        Long sendCodeTime = (Long)session.getAttribute(servNum);
        // 取当前时间
        long currentTime = new Date().getTime();
        // 如果缓存中的时间不为空，判断发送短信间隔与次数
        if (null != sendCodeTime)
        {
            // 当前时间 - 上一次发送时间 <= 短信发送频率时间 则获取短信验证码过于频繁
            if (currentTime - sendCodeTime <= Long.parseLong(sendCodeInterval) * 1000)
            {
                errorMsg = "您获取短信验证码过于频繁，请稍后再试！";
                return errorMsg;
            }
            
            // 获取多长时间内只允许下发多少次的时间,单位：小时
            String smsNumTime = CommonUtil.getParamValue(ConstantsBase.SH_SMSNUMTIME);
            // 获取多长时间内只允许下发多少次的次数
            String smsNum = CommonUtil.getParamValue(ConstantsBase.SH_SMSNUM);
            // 组装查询条件
            RecordSmsCodePO recordSmsCodePO = new RecordSmsCodePO();
            // 一段时间
            recordSmsCodePO.setSmsNumTime(Double.parseDouble(smsNumTime) / 24);
            // 手机号
            recordSmsCodePO.setServNum(servNum);
            // 执行查询语句，获取一段时间内此手机号下发短信记录的次数
            int sendNum = selfSvcService.qrySmsCodeNum(recordSmsCodePO);
            // 如果数据库里配置的次数大于等于从SH_SMS_HISTORY获取的数据数目，则获取频繁
            if (sendNum >= Integer.parseInt(smsNum))
            {
                errorMsg = "您获取短信验证码过于频繁，请稍后再试！";
                return errorMsg;
            }
        }
        return errorMsg;
	}
	
	/**
	 * 短信验证码发送成功后记录到SH_SMS_HISTORY表中
	 * <功能详细描述>
	 * @param servNum 手机号
	 * @param termInfo 终端信息
	 * @param randomPwd 验证码
	 * @see [类、类#方法、类#成员]
	 * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	 */
	public void insertSmsCode(String servNum, TerminalInfoPO termInfo, String randomPwd)
	{
	    // 组装插入信息
        RecordSmsCodePO recordSmsCode = new RecordSmsCodePO();
        // id为年月日时分秒毫秒
        recordSmsCode.setId(DateUtil.curOnlyTime());
        // 手机号
        recordSmsCode.setServNum(servNum);
        // 终端信息
        recordSmsCode.setTermId(termInfo.getTermid());
        // 短信内容
        recordSmsCode.setSmsContent("向用户" + servNum + "发送随机密码短信成功，随机码" + randomPwd);
        // 执行插入语句将信息记录到SH_SMS_HISTORY表中
        selfSvcService.insertSmsCode(recordSmsCode);
	}

	/**
	 * <获取省份id>
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create by hWX5316476 2015-6-15 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
	 */
    public String getProvinceId()
    {
        return CommonUtil.getParamValue("ProvinceID");
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
}
