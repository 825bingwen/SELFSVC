/*
 * @filename: DetailedRecordsSDAction.java
 * @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
 * @brif:  NG3.5帐详单改造之详单查询
 * @author: 高群/g00140516
 * @de:  2012/02/09 
 * @description: 
 * @remark: create g00140516 2012/02/09 R003C12L02n01 OR_huawei_201112_953
 */
package com.customize.sd.selfsvc.feeService.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.feeservice.model.CycleInfoPO;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * NG3.5帐详单改造之详单查询
 * 
 * @author g00140516
 * @version 1.0, 2012/02/09
 * @see
 * @since
 */
public class DetailedRecordsSDAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(DetailedRecordsSDAction.class);
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
    /**
     * 菜单
     */
    private String curMenuId = "";
    
    /**
     * 可查询月份列表
     */
    private String[] months = null;
    
    /**
     * 查询月份
     */
    private String month = "";
    
    /**
     * 详单类型
     */
    private String cdrType = "";
    
    /**
     * 合计信息
     */
    private Map<String, String> billTotal = null;
    
    /**
     * 套餐与固定费清单记录
     */
    private List<String> fixfeeRecords = null;
    
    /**
     * 客户名称
     */
    private String custName = "";
    
    /**
     * 账期
     */
    private String cycle = "";
    
    /**
     * 账期开始日期
     */
    private String startDate = "";
    
    /**
     * 账期结束日期
     */
    private String endDate = "";
    
    /**
     * 查询日期
     */
    private String queryDate = "";
    
    /**
     * 中文查询日期
     */
    private String chQueryDate = "";
    
    /**
     * 中文的账期开始日期
     */
    private String chStartDate = "";
    
    /**
     * 中文的账期结束日期
     */
    private String chEndDate = "";
    
    /**
     * 按时间段查询时，也只能查询6个月之内的
     */
    private String farthestDate = "";
    
    /**
     * 账期列表
     */
    private List<CycleInfoPO> cycles = null;
    
    /**
     * 新版通话详单、短彩信详单、上网详单
     */
    private Vector resultRecords = null;
    
    /**
     * 新版增值业务语音类扣费记录、代收费业务语音类扣费记录
     */
    private Vector voiceRecords = null;
    
    /**
     * 新版增值业务非语音类扣费记录、代收费业务非语音类扣费记录
     */
    private Vector nonVoiceRecords = null;
    
    /**
     * 旧版详单类型的英文名称
     */
    private String typeName = "";
    
    /**
     * 旧版详单类型的中文名称
     */
    private String tableHeader = "";
    
    /**
     * 旧版详单记录显示字段
     */
    private String[] tableTitle = null;
    
    /**
     * 旧版详单记录的总括
     */
    private String[] tableTail = null;
    
    /**
     * 旧版详单补充性说明
     */
    private String[] tableDescription = null;
    
    /**
     * 旧版详单展示列数
     */
    private int titleCols = 0;
    
    private transient DetailedRecordsService detailedRecordsService = null;
    
    /**
     * 详单打印标识
     */
    private String printFlag = "0";
    
    /**
     * 版本标识，0：旧版；1：新版
     */
    private String verFlag = "";
    
    /**
     * 查询标识，0：按时间段；1：按账期查
     */
    private String iscycle = "0";
    
    //add begin sWX219697 2014-6-3 15:02:07 OR_huawei_201405_877
    //详单补打密码
    private String detailPwd;
    //add end sWX219697 2014-6-3 15:02:07 OR_huawei_201405_877
    
    /**
     * 通话详单展示标识
     * add  lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
     */
    private String isshow;
    
    /**
     * 显示可查询月份
     * 
     * @return
     * @see
     */
    public String selectMonth()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth Starting ...");
        }
        
        String forward = "months";
        
        // 可查询当前月和前五个月的详单信息
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM_1, 6);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth End");
        }
        
        return forward;
    }
    
    /**
     * 按时间段查询
     * 
     * @return
     * @see
     */
    public String inputTime()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTime Starting ...");
        }
        
        String forward = "failed";
        
        // 按时间段查询
        iscycle = "0";
        
        // 查询开始、结束日期，默认为当天
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD);
        
        queryDate = sdf1.format(new Date());
        
        startDate = queryDate.substring(0, 6) + "01";
        endDate = queryDate;        
        
        // 按时间段查询也只能查询6个月之内的，允许查询的最早日期
        farthestDate = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 5) + "01";
        
        // 查询客户信息，获取custname
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();  
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息查询，成功时应返回一个CTagSet，一个CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, 
                queryDate.substring(0, 6), "1", curMenuId);
        if (null == v || 2 > v.size())
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "客户信息查询失败");
            
            logger.error("客户(" + customerSimp.getServNumber() + ")信息查询失败");
            
            request.setAttribute("errormessage", "客户信息查询失败！");
            
            request.setAttribute("backStep", "-1");
        }
        else
        {
            CTagSet tagset = (CTagSet) v.get(0);
            custName = tagset.GetValue("custname");
            
            try
            {
                custName = URLEncoder.encode(custName, "GBK");
            }
            catch (UnsupportedEncodingException e)
            {
                logger.error("使用GBK对客户名称编码时异常", e);
            }
            
            forward = "input";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTime End");
        }
        
        return forward;
    }
    
    /**
     * 选择账期
     * 
     * @return
     * @see
     */
    public String selectCycle()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectCycle Starting ...");
        }
        
        String forward = "failed";
        
        // 按账期查询
        iscycle = "1";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        request.setAttribute("backStep", "-1");
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM_1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        
        String strMonth = "";
        try 
        {
            strMonth = sdf2.format(sdf1.parse(month));
        } 
        catch (ParseException e) 
        {
            logger.error("详单查询，格式化日期时报错：", e);
            
            strMonth = month;
        }
        
        // add begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
        // 账期参数
        String thxdaccount = CommonUtil.getParamValue("SH_THXDACCOUNT_INFO");
        // 限定特性参数位数至少为6位
        if (StringUtils.isBlank(thxdaccount) || thxdaccount.length() < 6)
        {
            logger.error("系统参数配置不合法！");
            
            request.setAttribute("errormessage", "系统参数配置不合法！");
            throw new ReceptionException("系统参数配置不合法！");
        }
        else
        {
            // 截取前六位，精确到月份
            thxdaccount = thxdaccount.substring(0, 6);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        try
        {
            Date accot = sdf.parse(thxdaccount);
            Date ntime = sdf.parse(strMonth);
            // 判断参数，选择展示模式
            if (accot.getTime() >= ntime.getTime())
            {
                isshow = "false";
            }
            else
            {
                isshow = "true";
            }
        }
        catch (Exception e)
        {
            logger.error("日期格式转换错误", e);
            throw new ReceptionException("日期格式转换错误！");
        }
        getRequest().getSession().setAttribute("show", isshow);
        // end begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
        
        // 客户信息查询，成功时应返回一个CTagSet，一个CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, strMonth, "1", curMenuId);
        if (null == v || 2 > v.size())
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "客户信息查询失败");
            
            logger.error("客户(" + customerSimp.getServNumber() + ")信息查询失败");
            
            request.setAttribute("errormessage", "客户信息查询失败！");
        }
        else
        {
            CTagSet tagset = (CTagSet) v.get(0);
            custName = tagset.GetValue("custname");
            
            try
            {
                custName = URLEncoder.encode(custName, "GBK");
            }
            catch (UnsupportedEncodingException e)
            {
                logger.error("使用GBK对客户名称编码时异常", e);
            }
            
            CRSet rset = (CRSet) v.get(1);
            
            if (null == rset || 0 == rset.GetRowCount())
            {
                this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "客户信息查询失败");
                
                logger.error("客户(" + customerSimp.getServNumber() + ")信息查询失败");
                
                request.setAttribute("errormessage", "客户信息查询失败！");
            }
            else
            {
                int rowCount = rset.GetRowCount();
                
                // 自然月账期
                if (1 == rowCount)
                {
                    cycle = rset.GetValue(0, 0);
                    startDate = rset.GetValue(0, 1);
                    endDate = rset.GetValue(0, 2);
                    
                    forward = "singleCycle";
                }
                // 灵活账期多账期
                else
                {
                    cycles = new ArrayList<CycleInfoPO>();
                    CycleInfoPO cycleInfo = null;
                    for (int i = 0; i < rowCount; i++)
                    {
                        cycleInfo = new CycleInfoPO();
                        cycleInfo.setCycle(rset.GetValue(i, 0));
                        cycleInfo.setStartDate(rset.GetValue(i, 1));
                        cycleInfo.setEndDate(rset.GetValue(i, 2));
                        
                        cycles.add(cycleInfo);
                    }
                    
                    forward = "cycles";
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectCycle End");
        }
        
        return forward;
    }
    
    /**
     * 详单类型选择页面
     * 
     * @return
     * @see
     */
    public String selectType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType Starting ...");
        }
        
        String forward = "types";
        
        // 判断上线时间点，格式：yyyyMMdd。
        String effectMonth = (String) PublicCache.getInstance().getCachedData(Constants.NEWCDR_EFFECTMONTH);
        
        if (null == effectMonth || "".equals(effectMonth))
        {
            logger.error("系统未配置新详单查询功能的上线时间点，将使用新的详单类型进行查询");
        }
        // 如果上线时间点为20120320，只要查询开始时间在上线时间点之前，就按老版查询；否则，按新版查询，包含上线时间点当天
        else if (startDate.compareTo(effectMonth) <= 0)
        {
            forward = "oldTypes";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType End");
        }
        
        return forward;
    }
    
    /**
     * 查询详单记录新版
     * 
     * @return
     * @see
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String qryDetailedRecords()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        try
        {
            custName = URLDecoder.decode(custName, "GBK");
        }
        catch (UnsupportedEncodingException e1)
        {
            logger.error("使用GBK对客户名称解码时异常", e1);
        }
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD);
        SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD_CH);
        
        Date date = new Date();
        chQueryDate = sdf2.format(date);
        
        try
        {
            //modify begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
            date = sdf1.parse(startDate.replace("-", ""));
            //modify end lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
            chStartDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("格式化账期开始时间错误：", e);
            
            chStartDate = startDate;
        }
        
        // add begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
        // 时间段查询
        if ("0".equals(iscycle))
        {
            String thxdaccount = CommonUtil.getParamValue("SH_THXDACCOUNT_INFO");
            // 限定特性参数位数至少为6位
            if (StringUtils.isBlank(thxdaccount) || thxdaccount.length() < 6)
            {
                logger.error("系统参数配置不合法！");
                
                request.setAttribute("errormessage", "系统参数配置不合法！");
                throw new ReceptionException("系统参数配置不合法！");
            }
            else
            {
                // 截取前六位，精确到月份
                thxdaccount = thxdaccount.substring(0, 6);
            }           
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
            try
            {
                Date accot = sdf.parse(thxdaccount);
                String stt = startDate.replace("-", "");
                Date sttdate = sdf.parse(stt.substring(0, 6));
                // 判断参数，选择展示模式
                if (accot.getTime() >= sttdate.getTime())
                {
                    isshow = "false";
                }
                else
                {
                    isshow = "true";
                }
            }
            catch (Exception e)
            {
                logger.error("日期格式转换错误", e);
                throw new ReceptionException("日期格式转换错误！");
            }
            getRequest().getSession().setAttribute("show", isshow);
        }
        // end begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造 
 
        try
        {
            //modify begin lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
            date = sdf1.parse(endDate.replace("-", ""));
            //modify end lwx439898 2017-07-17 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造
            chEndDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("格式化账期开始时间错误：", e);
            
            chEndDate = endDate;
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // add begin lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造       
        request.setAttribute("custNames", CommonUtil.getUserLastName(customerSimp.getCustomerName()));       
        // add end lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        /*
         * 详单记录信息，包含totalfee、smstotalnum、billinfo等。 billinfo为08-03 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4|08-05
         * 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4
         */
        Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                terminalInfo,
                cdrType,
                startDate,
                endDate,
                curMenuId,
                iscycle,
                cycle);
        
        String description = "";
        
        // 按时间段查询
        if ("0".equals(iscycle))
        {
            description = "按时间段(" + startDate + "-" + endDate + ")查询，";
        }
        // 按账期查询
        else if ("1".equals(iscycle))
        {
            description = "按账期(" + cycle + ")查询，";
        }
        
        //add begin lwx439898 家里环境测试使用，特性参数控制
        //为true时表示测试数据开启
        if("true".equals(CommonUtil.getDictNameById("NGCC_XDCX", "NGCC_XDCX")))
        {
            resultMap = new HashMap<String,String>();
            resultMap.put("errorMessage", "");                      
        }
        //add end lwx439898 家里环境测试使用，特性参数控制
        
        if (null != resultMap && null != resultMap.get("errorMessage") && !"".equals(resultMap.get("errorMessage")))
        {         
            request.setAttribute("errormessage", resultMap.get("errorMessage"));
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "详单" + cdrType
                    + resultMap.get("errorMessage"));
            
            return "failed";
        }
        if (null != resultMap)
        {           
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", description + "详单" + cdrType + "查询成功");
            
            if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
            {
                // 套餐与固定费清单，计算合计费用
                parseFixfeeRecords(resultMap);
            }
            else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
            {
                // 通话详单，按日期分组
                parseSingleTypeRecords(resultMap);
            }
            else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
            {
                // 短彩信详单，按条数取短信、彩信，然后按日期分组
                parseSmsRecords(resultMap);
            }
            else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
            {
                // 上网详单，按条数取GPRS、WLAN，然后按日期分组
                parseGprsWlanRecords(resultMap);
            }
            else
            {
                // 增值业务扣费记录、代收费业务扣费记录，都区分语音和非语音
                parseMultiTypesRecords(resultMap);
            }
            
            // 判断用户本月是否还可以打印详单
            String termSpecial = terminalInfo.getTermspecial();
            String strMaxTimes = (String) PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES);
            
            if ('1' == termSpecial.charAt(0) && null != strMaxTimes && !"".equals(strMaxTimes.trim())
                    && !"0".equals(strMaxTimes.trim()))
            {
                SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
                String qryMonth = sdf3.format(new Date());
                
                DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(customerSimp.getServNumber(),
                        qryMonth,
                        cdrType);
                
                if (null == timesPO || timesPO.getTimes() < Integer.parseInt(strMaxTimes.trim()))
                {
                    printFlag = "1";
                }
            }
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "详单" + cdrType + "查询结束，未找到符合条件的记录");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords End");
        }
        
        return cdrType;
    }
    
    /**
     * <详单补打密码页面跳转>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877 详单补打密码
     */
    public String detailPrintPwd()
    {
    	return "detailPwd";
    }
    
    /**
     * <异步验证输入的补打密码是否正确>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877 详单补打密码
     */
    public void authDetailPrintPwd() throws IOException
    {
    	PrintWriter out = this.getResponse().getWriter();
    	HttpSession session = this.getRequest().getSession();
    	
    	//终端机信息类
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        //验证补打密码，若密码正确，输出1
        if(detailedRecordsService.authDetailPrintPwd(terminalInfo.getTermid(), detailPwd))
        {
        	out.write("1");
        	out.flush();
        }
        else
        {
        	out.write("0");
        	out.flush();
        }
    }
    
    /**
     * 查询详单记录
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryDetailedRecordsOld()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecordsOld Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 清单名称 "GSMCDR"
        typeName = getTypeServiceName(cdrType);
        
        // 获得清单表头信息 [[【通话详单】], [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)], [详单条数：,
        // 时长合计：, 费用合计(元)：], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(cdrType);
        
        // 清单名称中文 "[【通话详单】]"
        tableHeader = ((String[]) (tableDetail[0]))[0];
        
        // 表头中文 [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)]
        tableTitle = (String[]) (tableDetail[1]);
        titleCols = tableTitle.length;
        
        // 表头长度
        request.setAttribute("titleCols", tableTitle.length);
        
        // 合计中文 [详单条数：, 时长合计：, 费用合计(元)：]
        tableTail = (String[]) (tableDetail[2]);
        
        // 合计英文 [recordcount, totaltimes, totalfee]
        request.setAttribute("summaryTitle", tableDetail[3]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[]) tableDetail[4];
        }
        
		// add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        /**
         * 拼装内容 type：0--GSMCDR header：【通话详单】 title：序号 对方号码 话单类型 话单产生地 通话开始时间 通话时长(秒) 基本费(元) 长途费(元) 声讯信息费(元) 总话费(元)
         * tail：详单条数： 时长合计： 费用合计(元)：
         */
        pringLog();
		// add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        resultRecords = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                terminalInfo,
                startDate,
                endDate,
                "",
                cdrType,
                curMenuId,
                tableTitle.length - 1, month, iscycle);
        
        String description = "";
        
        // 按时间段查询
        if ("0".equals(iscycle))
        {
            description = "按时间段(" + startDate + "-" + endDate + ")查询，";
        }
        // 按账期查询
        else if ("1".equals(iscycle))
        {
            description = "按账期(" + month + ")查询，";
        }        
        
        // 未查询到详单记录
        if (resultRecords == null || resultRecords.size() == 0)
        {
            request.setAttribute("errormessage", description + "详单(" + typeName + ")查询结束，未找到符合条件的记录");
            
            request.setAttribute("recordCount", "0");
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "详单(" + typeName + ")查询结束，未找到符合条件的记录");
            
            return "success";
        }
        
        Map<String, String> totalMap = calDynTotalSD(resultRecords);
        request.setAttribute("totalMap", totalMap);
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", description + "详单(" + typeName + ")查询成功");
        
        // 判断用户本月是否还可以打印详单
        String termSpecial = terminalInfo.getTermspecial();
        String strMaxTimes = (String) PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES);
        
        if ('1' == termSpecial.charAt(0) && null != strMaxTimes && !"".equals(strMaxTimes.trim())
                && !"0".equals(strMaxTimes.trim()))
        {
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
            
            DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(customerSimp.getServNumber(),
                    sdf1.format(new Date()),
                    cdrType);
            
            if (null == timesPO || timesPO.getTimes() < Integer.parseInt(strMaxTimes.trim()))
            {
                printFlag = "1";
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecordsOld End");
        }
        
        return "success";
    }

    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
    /**
     * 拼装内容 type：0--GSMCDR header：【通话详单】 title：序号 对方号码 话单类型 话单产生地 通话开始时间 通话时长(秒) 基本费(元) 长途费(元) 声讯信息费(元) 总话费(元)
     * tail：详单条数： 时长合计： 费用合计(元)：
     */
    private void pringLog()
    {

        if (logger.isDebugEnabled())
        {
            StringBuffer buffer = new StringBuffer(1024);
            
            buffer.append("type：").append(cdrType).append("--").append(typeName);
            buffer.append(System.getProperty("line.separator")).append("header：").append(tableHeader);
            buffer.append(System.getProperty("line.separator")).append("title：");
            for (int i = 0; i < tableTitle.length; i++)
            {
                buffer.append(tableTitle[i]).append("  ");
            }
            buffer.append(System.getProperty("line.separator")).append("tail：");
            for (int i = 0; i < tableTail.length; i++)
            {
                buffer.append(tableTail[i]).append("  ");
            }
            if (tableDescription != null && tableDescription.length > 0)
            {
                buffer.append(System.getProperty("line.separator")).append("description：");
                for (int i = 0; i < tableDescription.length; i++)
                {
                    buffer.append(tableDescription[i]).append("  ");
                }
            }
            
            logger.debug(buffer.toString());
        }
    }
    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端     
    
    /**
     * 根据清单类型，获取清单类型英文名称
     * 
     * @param type，清单类型
     * @return
     */
    private String getTypeServiceName(String type)
    {
        String typeServiceName = ""; // 清单类型服务接口名
        
        // 选择全部清单，默认查询第一种类型的清单
        if ("0".equals(type))
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
            //typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
        	typeServiceName = Constants.getTypeServiceNameArray()[1];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        else
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
            //typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[Integer.parseInt(type)];
        	typeServiceName = Constants.getTypeServiceNameArray()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        
        return typeServiceName;
    }
    
    /**
     * 根据详单类型，获取相关显示信息
     * 
     * @param type，详单类型
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Object[] getTableDetail(String type)
    {
        Object[] typeTableDetail = null; // 表头
        
        if ("0".equals(type))
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
            //typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9月份findbugs修改
            
        }
        else
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
            //typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        
        return typeTableDetail;
    }
    
    /**
     * 山东统计
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map<String, String> calDynTotalSD(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map<String, String> totalM = new HashMap<String, String>();
        // 计算清单的费用，流量等 合计
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            totalM = totalByGsmcdr(v, totalM);            
        }
        else if ("SMSCDR".equals(typeName))
        {
            totalM = totalSmscdr(v, totalM);
        }
        else if ("IMSGCDR".equals(typeName))
        {
            totalM = totalImsgcdr(v, totalM);
        }
        
        else if ("WLANCDR".equals(typeName))
        {
            totalM = totalWlancdr(v, totalM);
        }
        else if ("GPRSCDR".equals(typeName))
        {
            totalM = totalGprscdr(v, totalM);
        }
        
        if (v != null)
        {
            totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
            totalM.put("recordcount", "0");
        }
        
        logger.debug("calDynTotalSD End");
        
        return totalM;
    }

    /**
     * GPRSCDR 合计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> totalGprscdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalGprs1 = "0";
        String totalGprs2 = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGprs1 = CurrencyUtil.addInt(totalGprs1, sAry[4]);
                totalGprs1 = CurrencyUtil.addInt(totalGprs1, sAry[7]);
                
                totalGprs2 = CurrencyUtil.addInt(totalGprs2, sAry[5]);
                totalGprs2 = CurrencyUtil.addInt(totalGprs2, sAry[8]);
            }
        }
        totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
        totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
        
        return totalM;
    }

    /**
     * WLANCDR 合计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> totalWlancdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee = CurrencyUtil.add(totalFee, (sAry.length > 6 ? sAry[7] : "0"));
                totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
            }
        }
        totalM.put("totalfee", totalFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
    }

    /**
     * IMSGCDR 合计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> totalImsgcdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalFee1 = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[2]);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[3]);
            }
        }
        totalM.put("totalfee1", totalFee1);
        
        return totalM;
    }

    /**
     * SMSCDR 合计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> totalSmscdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalSmsFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
            }
        }
        totalM.put("totalfee", totalSmsFee);
        
        return totalM;
    }

    /**
     * GSMCDR 合计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Map<String,String> totalByGsmcdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalGsmFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[9]);
                totalTimes = CurrencyUtil.add(totalTimes, sAry[5], 0);
            }
        }
        totalM.put("totalfee", totalGsmFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
    }
    
    /**
     * 解析套餐及固定费详单记录
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseFixfeeRecords(Map<String, String> resultMap)
    {
        // 存放详单记录
        fixfeeRecords = new ArrayList<String>();
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();        
        
        String allRecords = resultMap.get(cdrType);
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return;
        }
        
        // 合计费用
        String totalFee = resultMap.get("totalfee");        
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        // 单条记录
        String record = "";        
        
        for (int i = 0; i < recordsCount; i++)
        {
            record = recordsArray[i];
            
            // 空记录
            if (null == record || "".equals(record.trim()))
            {
                continue;
            }
            
            // 如果以@_@，说明最后一个字段为空，追加空格
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fixfeeRecords.add(record);
        }
        
        billTotal.put("totalFee", totalFee);
    }
    
    /**
     * 通话详单
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseSingleTypeRecords(Map<String, String> resultMap)
    {
        // 存放解析后的详单记录。格式：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
        resultRecords = new Vector();
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();       
        
        // 单条记录
        String record = "";
        
        // 单条记录的各字段
        String[] fields = null;
        
        // 单条记录的时间
        String timeField = "";
        
        // 单条记录对应的日期
        String day = "";
        
        // 存放同一天的所有记录
        List<String> dayRecords = null;
        
        String lastDay = "";
        
        String allRecords = resultMap.get(cdrType);
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return;
        }        
        
        // 合计费用
        String totalFee = resultMap.get("totalfee");
        billTotal.put("totalFee", totalFee);
        
        // GPRS/3G上网话单总条数
        String gprsTotalNum = resultMap.get("gprstotalnum");
        billTotal.put("gprsTotalNum", gprsTotalNum);
        
        // GPRS/3G上网总费用
        String gprsTotalFee = resultMap.get("gprstotalfee");
        billTotal.put("gprsTotalFee", gprsTotalFee);
        
        // WLAN话单总条数
        String wlanTotalNum = resultMap.get("wlantotalnum");
        billTotal.put("wlanTotalNum", wlanTotalNum);
        
        // WLAN话单总费用
        String wlanTotalFee = resultMap.get("wlantotalfee");
        billTotal.put("wlanTotalFee", wlanTotalFee);
        
        // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
        
        // 通信时长  (*小时*分*秒）
        String txtotaltime = resultMap.get("txtotaltime");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if (null == txtotaltime || "".equals(txtotaltime))
//        {
//            txtotaltime = "0";
//        }
        txtotaltime = (null == txtotaltime || "".equals(txtotaltime)) ? "0" : txtotaltime;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 

        billTotal.put("txtotaltime", txtotaltime);
        
        // 通话费（元）
        String thtotalfee = resultMap.get("thtotalfee");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if (null == thtotalfee || "".equals(thtotalfee))
//        {
//            thtotalfee = "0";
//        }
        thtotalfee = (null == thtotalfee || "".equals(thtotalfee)) ? "0" : thtotalfee;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        billTotal.put("thtotalfee", thtotalfee);
        
        // 长途费（元）
        String cttotalfee = resultMap.get("cttotalfee");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if (null == cttotalfee || "".equals(cttotalfee))
//        {
//            cttotalfee = "0";
//        }
        cttotalfee = (null == cttotalfee || "".equals(cttotalfee)) ? "0" : cttotalfee;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        billTotal.put("cttotalfee", cttotalfee);
        
        // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
        {
            record = recordsArray[i];
            
            // 空记录
            if (null == record || "".equals(record.trim()))
            {
                continue;
            }
            
            // 如果以@_@，说明最后一个字段为空，追加空格
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fields = record.split("@_@");
            
            timeField = fields[0];
            
            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
            int index = timeField.indexOf(" ");
            if (-1 != index)
            {
                day = timeField.substring(0, index);
            }
            else
            {
                day = timeField;
            }
            
            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
            if (!lastDay.equals(day))
            {
                lastDay = day;
                
                dayRecords = new ArrayList<String>();
                
                resultRecords.add(day);
                resultRecords.add(dayRecords);
            }
            
            dayRecords.add(record.substring(index + 1));
        }
    }
    
    /**
     * 短彩信详单
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseSmsRecords(Map<String, String> resultMap)
    {
        // 存放解析后的详单记录。格式：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
        resultRecords = new Vector();
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();       
        
        // 单条记录
        String record = "";
        
        // 单条记录的各字段
        String[] fields = null;
        
        // 单条记录的时间
        String timeField = "";
        
        // 单条记录对应的日期
        String day = "";
        
        // 存放同一天的所有记录
        List<String> dayRecords = null;
        
        String lastDay = "";
        
        String allRecords = resultMap.get(cdrType);
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return;
        }        
        
        // 合计费用
        String totalFee = resultMap.get("totalfee");
        billTotal.put("totalFee", totalFee);
        
        // 短信总条数
        String smsTotalNum = resultMap.get("smstotalnum");
        if (null == smsTotalNum || "".equals(smsTotalNum.trim()))
        {
            smsTotalNum = "0";
        }
        billTotal.put("smsTotalNum", smsTotalNum);
        
        // 彩信总条数
        String mmsTotalNum = resultMap.get("mmstotalnum");
        if (null == mmsTotalNum || "".equals(mmsTotalNum.trim()))
        {
            mmsTotalNum = "0";
        }
        billTotal.put("mmsTotalNum", mmsTotalNum);
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
        {
            // smsTotalNum条短信处理结束后，重置lastDay、day，重新解析彩信记录。避免将短信、彩信混在一起
            if (i == Integer.parseInt(smsTotalNum))
            {
                day = "";
                lastDay = "";
            }
            
            record = recordsArray[i];
            
            // 空记录
            if (null == record || "".equals(record.trim()))
            {
                continue;
            }
            
            // 如果以@_@，说明最后一个字段为空，追加空格
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fields = record.split("@_@");
            
            timeField = fields[0];
            
            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
            int index = timeField.indexOf(" ");
            if (-1 != index)
            {
                day = timeField.substring(0, index);
            }
            else
            {
                day = timeField;
            }
            
            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
            if (!lastDay.equals(day))
            {
                lastDay = day;
                
                dayRecords = new ArrayList<String>();
                
                resultRecords.add(day);
                resultRecords.add(dayRecords);
            }
            
            dayRecords.add(record.substring(index + 1));
        }
    }
    
    /**
     * 字符串为空返回 0
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
    private String chkEmptyGetZeroNoTrim(String str)
    {
    	if (null == str || "".equals(str))
        {
            str = "0";
        }
    	return str;
    }
    
    /**
     * 字符串为空返回 0
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
    private String chkEmptyGetZero(String str)
    {
    	if (null == str || "".equals(str.trim()))
        {
            str = "0";
        }
    	return str;
    }
    
    /**
     * 上网详单
     * 
     * @param allRecords
     * @return
     * @see
     * @remark modify by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
    private void parseGprsWlanRecords(Map<String, String> resultMap)
    {
        // 存放解析后的详单记录。格式：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
        resultRecords = new Vector();
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();       
        
        // 单条记录
        String record = "";
        
        // 单条记录的各字段
        String[] fields = null;
        
        // 单条记录的时间
        String timeField = "";
        
        // 单条记录对应的日期
        String day = "";
        
        // 存放同一天的所有记录
        List<String> dayRecords = null;
        
        String lastDay = "";
        
        String allRecords = resultMap.get(cdrType);
        if (CommonUtil.isEmpty(allRecords))
        {
            return;
        }        
        
        // GPRS/3G上网话单总条数
        String gprsTotalNum = resultMap.get("gprstotalnum");
        gprsTotalNum = this.chkEmptyGetZero(gprsTotalNum);
        billTotal.put("gprsTotalNum", gprsTotalNum);
        
        // GPRS/3G上网总费用
        String gprsTotalFee = resultMap.get("gprstotalfee");
        billTotal.put("gprsTotalFee", gprsTotalFee);
        
        // WLAN话单总条数
        String wlanTotalNum = resultMap.get("wlantotalnum");
        wlanTotalNum = this.chkEmptyGetZero(wlanTotalNum);
        billTotal.put("wlanTotalNum", wlanTotalNum);
        
        // WLAN话单总费用
        String wlantotalfee = resultMap.get("wlantotalfee");
        wlantotalfee = this.chkEmptyGetZeroNoTrim(wlantotalfee);
        billTotal.put("wlanTotalFee", wlantotalfee);
        
        // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
        
        // GPRS/3G上网总时长
        String gprstotaltime = resultMap.get("gprstotaltime");
        gprstotaltime = this.chkEmptyGetZeroNoTrim(gprstotaltime);
        billTotal.put("gprstotaltime", gprstotaltime);
        
        // GPRS/3G上网总流量
        String gprstotalflux = resultMap.get("gprstotalflux");
        gprstotalflux = this.chkEmptyGetZeroNoTrim(gprstotalflux);
        billTotal.put("gprstotalflux", gprstotalflux);
        
        // WLAN上网总时长
        String wlantotaltime = resultMap.get("wlantotaltime");
        wlantotaltime = this.chkEmptyGetZeroNoTrim(wlantotaltime);
        billTotal.put("wlantotaltime", wlantotaltime);
        
        // WLAN上网总流量
        String wlantotalflux = resultMap.get("wlantotalflux");
        wlantotalflux = this.chkEmptyGetZeroNoTrim(wlantotalflux);
        billTotal.put("wlantotalflux", wlantotalflux);
        
        // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
        
        // add begin qWX279398 2015-8-3 OR_SD_201506_821_山东_增加宽带详单查询方案
        // 有线宽带话单总条数
        String wiredtotalnum = resultMap.get("wiredtotalnum");
        wiredtotalnum = this.chkEmptyGetZeroNoTrim(wiredtotalnum);
        billTotal.put("wiredtotalnum", wiredtotalnum);
        
        // 有线宽带话单总费用
        String wiredtotalfee = resultMap.get("wiredtotalfee");
        wiredtotalfee = this.chkEmptyGetZeroNoTrim(wiredtotalfee);
        billTotal.put("wiredtotalfee", wiredtotalfee);
        
        // 有线宽带上网总时长
        String wiredtotaltime = resultMap.get("wiredtotaltime");
        wiredtotaltime = this.chkEmptyGetZeroNoTrim(wiredtotaltime);
        billTotal.put("wiredtotaltime", wiredtotaltime);
        
        // 有线宽带上网总流量
        String wiredtotalflux = resultMap.get("wiredtotalflux");
        wiredtotalflux = this.chkEmptyGetZeroNoTrim(wiredtotalflux);
        billTotal.put("wiredtotalflux", wiredtotalflux);
        // add end qWX279398 2015-8-3 OR_SD_201506_821_山东_增加宽带详单查询方案
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
        {
            // GPRS/3G上网话单处理结束后，重置lastDay、day，重新解析WLAN话单。避免将两种混在一起
            if (i == Integer.parseInt(gprsTotalNum))
            {
                day = "";
                lastDay = "";
            }
            
            record = recordsArray[i];
            
            // 空记录
            if (CommonUtil.isEmpty(record))
            {
                continue;
            }
            
            // 如果以@_@，说明最后一个字段为空，追加空格
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fields = record.split("@_@");
            
            timeField = fields[0];
            
            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
            int index = timeField.indexOf(" ");
            if (-1 != index)
            {
                day = timeField.substring(0, index);
            }
            else
            {
                day = timeField;
            }
            
            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
            if (!lastDay.equals(day))
            {
                lastDay = day;
                
                dayRecords = new ArrayList<String>();
                
                resultRecords.add(day);
                resultRecords.add(dayRecords);
            }
            
            dayRecords.add(record.substring(index + 1));
        }
    }
    
    /**
     * 增值业务扣费记录、代收费业务扣费记录
     * 
     * @param resultMap
     * @return
     * @see
     */
    private void parseMultiTypesRecords(Map<String, String> resultMap)
    {
        // 存放解析后的详单记录。格式：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
        voiceRecords = new Vector();
        nonVoiceRecords = new Vector();
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();
        
        // 单条记录
        String record = "";
        
        // 单条记录的各字段
        String[] fields = null;
        
        // 单条记录的时间
        String timeField = "";
        
        // 单条记录对应的日期
        String day = "";
        
        // 存放同一天的所有记录
        List<String> dayRecords = null;
        
        String lastDay = "";
        
        // 语音类扣费记录
        String allRecords = resultMap.get(cdrType + "-voice");
        if (null != allRecords && 0 < allRecords.trim().length())
        {
            // 合计费用
            String voiceTotalFee = resultMap.get("totalfee-voice");
            billTotal.put("voiceTotalFee", voiceTotalFee);
            
            // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
            
            // 语音类业务扣费记录总时长
            String zzywtotaltime = resultMap.get("zzywtotaltime");
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            zzywtotaltime = this.chkEmptyGetZeroNoTrim(zzywtotaltime);
            // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            billTotal.put("zzywtotaltime", zzywtotaltime);
            
            // 代收费业务扣费记录总时长
            String dsftotaltime = resultMap.get("dsftotaltime");
            	
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            dsftotaltime = this.chkEmptyGetZeroNoTrim(dsftotaltime);
            // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）

            billTotal.put("dsftotaltime", dsftotaltime);
            
            // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
            
            String[] recordsArray = allRecords.split("\\|");
            int recordsCount = recordsArray.length;
            
            for (int i = 0; i < recordsCount; i++)
            {
                record = recordsArray[i];
                
                // 空记录
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                if (CommonUtil.isEmpty(record))
                {
                    continue;
                }
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                
                // 如果以@_@，说明最后一个字段为空，追加空格
                if (record.endsWith("@_@"))
                {
                    record = record + " ";
                }
                
                // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                record = record.replaceAll("@@", "@ @");
                
                fields = record.split("@_@");
                
                timeField = fields[0];
                
                // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                int index = timeField.indexOf(" ");
                
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                day = (-1 != index) ? timeField.substring(0, index) : timeField;
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                
                // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                if (!lastDay.equals(day))
                {
                    lastDay = day;
                    
                    dayRecords = new ArrayList<String>();
                    
                    voiceRecords.add(day);
                    voiceRecords.add(dayRecords);
                }
                
                dayRecords.add(record.substring(index + 1));
            }           
        }
        
        // 非语音类扣费记录
        lastDay = "";
        allRecords = resultMap.get(cdrType + "-nonvoice");
        if (null != allRecords && 0 < allRecords.trim().length())
        {
            // 合计费用
            String nonVoiceTotalFee = resultMap.get("totalfee-nonvoice");
            billTotal.put("nonVoiceTotalFee", nonVoiceTotalFee);
            
            String[] recordsArray = allRecords.split("\\|");
            int recordsCount = recordsArray.length;
            
            for (int i = 0; i < recordsCount; i++)
            {
                record = recordsArray[i];
                
                // 空记录
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                if (CommonUtil.isEmpty(record))
                {
                    continue;
                }
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                
                // 如果以@_@，说明最后一个字段为空，追加空格
                if (record.endsWith("@_@"))
                {
                    record = record + " ";
                }
                
                // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                record = record.replaceAll("@@", "@ @");
                
                fields = record.split("@_@");
                
                timeField = fields[0];
                
                // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                int index = timeField.indexOf(" ");
                
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                day = (-1 != index) ? timeField.substring(0, index) : timeField;
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                
                // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                if (!lastDay.equals(day))
                {
                    lastDay = day;
                    
                    dayRecords = new ArrayList<String>();
                    
                    nonVoiceRecords.add(day);
                    nonVoiceRecords.add(dayRecords);
                }
                
                dayRecords.add(record.substring(index + 1));
            }           
        }
    }
    
    /**
     * 旧版详单查询获取打印数据
     * 
     * @param request
     * @return
     */
    public String getPrintData()
    {
        logger.debug("getPrintData Starting ...");
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();
            
            StringBuffer scripts = new StringBuffer("var printDataObject = {");
            StringBuffer records = new StringBuffer("");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            int iListType = Integer.parseInt(cdrType);
            
            String typeServiceName = getTypeServiceName(cdrType);
            typeName = typeServiceName;
            
            // 获得清单表头信息 [[【通话详单】], [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)], [详单条数：,
            // 时长合计：, 费用合计(元)：], [recordcount, totaltimes, totalfee]]
            Object[] tableDetail = getTableDetail(cdrType);
            tableTitle = (String[]) (tableDetail[1]);
            
            // 取得清单数据
            Vector dataV = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                    terminalInfo,
                    startDate,
                    endDate,
                    "",
                    cdrType,
                    curMenuId,
                    tableTitle.length - 1, month, iscycle);
            Map<String, String> totalM = calDynTotalSD(dataV);
            
            // 打印数据
            //if (dataV != null && dataV.size() > 0)
            if (isNotEmpty(dataV))
            {
            	// begin zKF66389 2015-09-10 9月份findbugs修改
                //createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
            	createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                String cstr = "";
                String[] sAry = null;
                
                int maxSize = dataV.size();
                
                if ("GSMCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2], 4) + " " // 通话类型
                                + CommonUtil.fillLeftBlank(CommonUtil.formatServnumber(sAry[1]), 11) + " " // 对端号码
                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 14) + " " // 日期 时间
                                + CommonUtil.fillLeftBlank(sAry[5].trim(), 5) + " " // 时长
                                + CommonUtil.fillLeftBlank(sAry[9].trim(), 5) + " " // 费用
                                + CommonUtil.fillLeftBlank(sAry[3].trim().replaceAll("&nbsp;", ""), 4)); // 话单产生地
                    }
                }
                else if ("SMSCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2].trim(), 11) + " "
                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 13) + " "
                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 15) + " "
                                + CommonUtil.fillLeftBlank(sAry[6].trim(), 5));
                    }
                }
                else if ("GPRSCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, ""
                                + CommonUtil.fillRightBlank(sAry[1], 8)
                                + " "
                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 8)
                                + " "
                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(CurrencyUtil.addLong(sAry[4],
                                        sAry[7])), 11)
                                + " "
                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(CurrencyUtil.addLong(sAry[5],
                                        sAry[8])), 11) + " " + CommonUtil.fillLeftBlank(sAry[10], 6));
                    }
                }
                else if ("IMSGCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
                                + CommonUtil.fillLeftBlank(sAry[6], 14) + " "
                                + CommonUtil.fillLeftBlank(sAry[2].trim(), 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 7));
                    }
                }
                else if ("WLANCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[3], 14) + " " + CommonUtil.fillLeftBlank(sAry[4], 6)
                                + " " + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[5]), 6) + " "
                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[6]), 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[7], 5));
                    }
                }
            }
            
            createLine(records);
            
            // begin zKF66389 2015-09-10 9月份findbugs修改
            //String[] typeTotalColumn = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[3];
            //String[] typeTotalColChnName = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[2];
            String[] typeTotalColumn = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[3];
            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[2];
            // end zKF66389 2015-09-10 9月份findbugs修改
            String s = "";
            StringBuffer sbuf = new StringBuffer(s);
            for (int i = 0; i < typeTotalColumn.length; i++)
            {
                sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
            }
            s= sbuf.toString();
            createRecord(records, s);
            createRecord(records, " ");
            
            scripts.append("printTail:'YES'");
            
            // begin zKF66389 2015-09-10 9月份findbugs修改
            //scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
            scripts.append(",printTypeName:'" + Constants.getTypeNameArray()[iListType] + "'");
            // end zKF66389 2015-09-10 9月份findbugs修改
            
            scripts.append("}");
            
            String strRecords = "var printRecords = [";
         // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//            if (!"".equals(records.toString().trim()))
//            {
//                strRecords += records.substring(0, records.toString().length() - 1);
//            }
            strRecords = (!"".equals(records.toString().trim())) ? strRecords + records.substring(0, records.toString().length() - 1) : strRecords;
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
            strRecords += "]";
            
            request.setAttribute("printData", scripts.toString() + ";" + strRecords);
            
            logger.debug("getPrintData End");
            
            return "success";
        }
        catch (Exception e)
        {
            logger.error("组装清单打印数据失败：", e);
            
            this.getRequest().setAttribute("errormessage", "组装打印数据出错，详单打印失败");
            
            logger.debug("getPrintData End");
            
            return "failed";
        }
    }
    
    /**
     * 判断是否为空
     * <功能详细描述>
     * @param dataV
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean isNotEmpty(Vector dataV)
    {
        return dataV != null && dataV.size() > 0;
    }
    
    /**
     * 新版详单查询获取打印数据
     * 
     * @param request
     * @return
     */
    public String getPrintDataNew()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getPrintDataNew Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();           
        
        try
        {
            StringBuffer records = new StringBuffer(1024);
           
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            String referenceServNumber = request.getParameter("referenceServNumber");
            if (customerSimp == null || customerSimp.getServNumber() == null || referenceServNumber == null 
                    || "".equals(referenceServNumber.trim()) 
                    || !referenceServNumber.trim().equals(customerSimp.getServNumber()))
            {
                throw new Exception("要打印清单的手机号码与会话中保存的用户的手机号码不一致");
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("用户" + referenceServNumber + "选择打印详单：" + cdrType);
            }
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            /*
             * 详单记录信息，包含totalfee、smstotalnum、billinfo等。 billinfo为08-03 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4|08-05
             * 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4
             */
            Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                    terminalInfo,
                    cdrType,
                    startDate,
                    endDate,
                    curMenuId,
                    iscycle,
                    cycle);
            
            if (null != resultMap)
            {
                if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    
                    // 单条记录
                    String record = "";                    
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'扣费日期  套餐及固定费名称            费用(元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }
                            
                            records.append(formatFixfeeRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 40)).append("'"); 
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                    // 合计费用
                    //String totalFee = resultMap.get("totalfee");
                    
                    // yKF28472
                    
                    // 通信时长  (*小时*分*秒）
                    String txtotaltime = resultMap.get("txtotaltime");
                    if (null == txtotaltime || "".equals(txtotaltime))
                    {
                        txtotaltime = "0";
                    }
                    
                    // 通话费（元）
                    String thtotalfee = resultMap.get("thtotalfee");
                    if (null == thtotalfee || "".equals(thtotalfee))
                    {
                        thtotalfee = "0";
                    }
                    
                    // 长途费（元）
                    String cttotalfee = resultMap.get("cttotalfee");
                    if (null == cttotalfee || "".equals(cttotalfee))
                    {
                        cttotalfee = "0";
                    }
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    
                    // 单条记录
                    String record = "";                    

                    // 单条记录的各字段
                    String[] fields = null;
                    
                    // 单条记录的时间
                    String timeField = "";
                    
                    // 单条记录对应的日期
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    String noShowToll = (String)getRequest().getSession().getAttribute("show");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        if("true".equals(noShowToll))
                        {
                            records.append("'起始时间通信 通信对方号码   通信 通话费','        地点 方式           时长   (元)',");
                        }
                        else
                        {
                            records.append("'起始时间通信 通信对方号码   通信 通话费 长途费','        地点 方式           时长   (元)   (元)',");
                        }
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            }                           
                            
                            records.append(formatGsmRecord(record));
                        }

                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        //modify begin lwx439898 2017-09-05 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造的需求 
                        String gsmHjRecord = "";
                        if("true".equals(noShowToll))
                        {
                            gsmHjRecord = "合计:@_@ @_@ @_@ @_@"+txtotaltime+"@_@ @_@ @_@"+thtotalfee;
                        }
                        else
                        {
                            gsmHjRecord = "合计:@_@ @_@ @_@ @_@"+txtotaltime+"@_@ @_@ @_@"+thtotalfee+"@_@"+cttotalfee;
                        }
                        //modify end lwx439898 2017-09-05 OR_SD_201707_264__2017年长市漫一体化专题--自助终端通话详单查询改造的需求 
                        
                        records.append(formatGsmRecord(gsmHjRecord));
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                    }                    
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    String smsTotalNum = resultMap.get("smstotalnum");
                    if (null == smsTotalNum || "".equals(smsTotalNum.trim()))
                    {
                        smsTotalNum = "0";
                    }
                    
                    String mmsTotalNum = resultMap.get("mmstotalnum");
                    if (null == mmsTotalNum || "".equals(mmsTotalNum.trim()))
                    {
                        mmsTotalNum = "0";
                    }
                    
                    // 单条记录
                    String record = "";                    

                    // 单条记录的各字段
                    String[] fields = null;
                    
                    // 单条记录的时间
                    String timeField = "";
                    
                    // 单条记录对应的日期
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'短信总条数：").append(CommonUtil.fillLeftBlank(smsTotalNum, 5))
                                .append("条        彩信总条数：").append(CommonUtil.fillLeftBlank(mmsTotalNum, 5)).append("条',")
                                .append("'起始时间 通信地点 对方号码     信息类型 通信费','                                          (元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            // smsTotalNum条短信处理结束后，重置lastDay、day，重新解析彩信记录。避免将短信、彩信混在一起
                            if (i == Integer.parseInt(smsTotalNum))
                            {
                                day = "";
                                lastDay = "";
                            }
                            
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatSmsRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 40)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    // GPRS/3G上网话单总条数
                    String gprsTotalNum = resultMap.get("gprstotalnum");
                    if (null == gprsTotalNum || "".equals(gprsTotalNum.trim()))
                    {
                        gprsTotalNum = "0";
                    }
                    
                    // GPRS/3G上网总费用
                    String gprsTotalFee = resultMap.get("gprstotalfee");
                    
                    // WLAN话单总条数
                    String wlanTotalNum = resultMap.get("wlantotalnum");
                    if (null == wlanTotalNum || "".equals(wlanTotalNum.trim()))
                    {
                        wlanTotalNum = "0";
                    }
                    
                    // WLAN话单总费用
                    String wlanTotalFee = resultMap.get("wlantotalfee");
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-05 begin
                    
                    // modify begin zKF66389 2015-6-11 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
                    // GPRS/3G上网总时长
//                    String gprstotaltime = resultMap.get("gprstotaltime");
//                    if (null == gprstotaltime || "".equals(gprstotaltime))
//                    {
//                        gprstotaltime = "0";
//                    }
                    // modify end zKF66389 2015-6-11 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
                    
                    // GPRS/3G上网总流量
                    String gprstotalflux = resultMap.get("gprstotalflux");
                    if (null == gprstotalflux || "".equals(gprstotalflux))
                    {
                        gprstotalflux = "0";
                    }
                    
                    // WLAN上网总时长
                    String wlantotaltime = resultMap.get("wlantotaltime");
                    if (null == wlantotaltime || "".equals(wlantotaltime))
                    {
                        wlantotaltime = "0";
                    }
                    
                    // WLAN上网总流量
                    String wlantotalflux = resultMap.get("wlantotalflux");
                    if (null == wlantotalflux || "".equals(wlantotalflux))
                    {
                        wlantotalflux = "0";
                    }
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-05 end
                    
                    // 单条记录
                    String record = "";                    

                    // 单条记录的各字段
                    String[] fields = null;
                    
                    // 单条记录的时间
                    String timeField = "";
                    
                    // 单条记录对应的日期
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'移动数据流量上网话单总条数:").append(CommonUtil.fillLeftBlank(gprsTotalNum, 6)).append("条',")
                               .append("'移动数据流量上网总费用:    ").append(CommonUtil.fillLeftBlank(gprsTotalFee, 6)).append("元',");
                        records.append("'WLAN话单总条数:       ").append(CommonUtil.fillLeftBlank(wlanTotalNum, 6)).append("条',")
                               .append("'WLAN话单总费用:       ").append(CommonUtil.fillLeftBlank(wlanTotalFee, 6)).append("元',");
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-05 start
                        // modify begin zKF66389 2015-6-13 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
//                        records.append("'移动数据流量上网总时长:    ").append(CommonUtil.fillLeftBlank(gprstotaltime, 6)).append("',")
//                               .append("'移动数据流量上网总流量:    ").append(CommonUtil.fillLeftBlank(gprstotalflux, 6)).append("',");
//                        records.append("'WLAN上网总时长:       ").append(CommonUtil.fillLeftBlank(wlantotaltime, 6)).append("',")
//                               .append("'WLAN上网总流量:       ").append(CommonUtil.fillLeftBlank(wlantotalflux, 6)).append("',");
                        records.append("'移动数据流量上网总流量:    ").append(CommonUtil.fillLeftBlank(gprstotalflux, 6)).append("',")
                        	.append("'WLAN上网总流量:    ").append(CommonUtil.fillLeftBlank(wlantotalflux, 6)).append("',");
                        records.append("'WLAN上网总时长:       ").append(CommonUtil.fillLeftBlank(wlantotaltime, 6)).append("',");

                        // modify end zKF66389 2015-6-13 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-05 end
                        
                        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
                        // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
                        String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
                        if ("1".equals(showGprsWlanFlux))
                        {
                            records.append("'起始时间通信地点上网方 时长 正常时 优惠时 通信费','                式          段流量 段流量  (元)',");
                        }
                        else
                        {
                            records.append("'起始时间通信地点上网方 时长    流量     通信费','                式                        (元)',");
                        }
                        // add end zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            // GPRS/3G上网话单处理结束后，重置lastDay、day，重新解析WLAN话单。避免将两种混在一起
                            if (i == Integer.parseInt(gprsTotalNum))
                            {
                                day = "";
                                lastDay = "";
                            }
                            
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }                            

                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatGprsWlanRecord(record));
                        }
                    }                    
                }
                // 增值业务扣费记录
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String voiceTotalFee = resultMap.get("totalfee-voice");
                    String nonvoiceTotalFee = resultMap.get("totalfee-nonvoice");
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                    // 语音类业务扣费记录总时长
                    String zzywtotaltime = resultMap.get("zzywtotaltime");
                    if (null == zzywtotaltime || "".equals(zzywtotaltime))
                    {
                        zzywtotaltime = "0";
                    }
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    
                    // 单条记录
                    String record = "";                    

                    // 单条记录的各字段
                    String[] fields = null;
                    
                    // 单条记录的时间
                    String timeField = "";
                    
                    // 单条记录对应的日期
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType + "-voice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'语音类业务扣费记录',");
                        records.append("'时间    使用方 业务名称服务号码时长       费用','        式                                (元)',");
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatIsmgVoiceRecord(record));
                        }
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        
                        String hjRecord = "合计:@_@ @_@ @_@ @_@"+zzywtotaltime+"@_@"+voiceTotalFee;
                        records.append(formatIsmgVoiceRecord(hjRecord));
                        //records.append("'合计：").append(CommonUtil.fillLeftBlank(voiceTotalFee, 44)).append("',");
                        
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    } 
                    
                    allRecords = resultMap.get(cdrType + "-nonvoice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'',");
                        records.append("'非语音类业务扣费记录',");
                        records.append("'时间    使用方式   业务名称      业务端   费用','                                 口',");

                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatIsmgNonVoiceRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(nonvoiceTotalFee, 40)).append("'"); 
                    } 
                }
                // 代收费业务扣费记录
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String voiceTotalFee = resultMap.get("totalfee-voice");
                    String nonvoiceTotalFee = resultMap.get("totalfee-nonvoice");
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-05 begin
                    
                    String dsftotaltime = resultMap.get("dsftotaltime");
                    if (null == dsftotaltime || "".equals(dsftotaltime))
                    {
                        dsftotaltime = "0";
                    }
                    
                    // 详单优化，添加各种合计  add by yKF28472 2012-07-05 end
                    
                    // 单条记录
                    String record = "";                    

                    // 单条记录的各字段
                    String[] fields = null;
                    
                    // 单条记录的时间
                    String timeField = "";
                    
                    // 单条记录对应的日期
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType + "-voice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'语音类业务扣费记录',");
                        records.append("'时间    业务服务号码 服务提供商 时长      费用','        名称                              (元)',");
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatInfofeeVoiceRecord(record));
                        }
                        
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        
                        String hjRecord = "合计:@_@ @_@ @_@ @_@ @_@"+dsftotaltime+"@_@"+voiceTotalFee;
                        records.append(formatInfofeeVoiceRecord(hjRecord));
                        //records.append("'合计：").append(CommonUtil.fillLeftBlank(voiceTotalFee, 44)).append("',"); 
                        
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    } 
                    
                    allRecords = resultMap.get(cdrType + "-nonvoice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'',");
                        records.append("'非语音类业务扣费记录',");
                        records.append("'时间    业务服务号码 服务提供商 费用类型  费用',");
                        records.append("'        名称                              (元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // 如果以@_@结尾，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatInfofeeNonVoiceRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(nonvoiceTotalFee, 40)).append("'"); 
                    } 
                }
            }
            
            String strRecords = "var printDataObject = {printTail:'YES'};var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records;
            }
            strRecords += "]";
            
            if (logger.isInfoEnabled())
            {
                logger.info(strRecords);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn(strRecords.length() > 512 ? strRecords.substring(0, 512) : strRecords);
            }
            
            request.setAttribute("printData", strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintDataNew End");
            }
            
            return "success";
        }
        catch (Exception e)
        {
            logger.error("组装清单打印数据失败：", e);
            
            String strRecords = "var printDataObject = {printTail:'YES'};";
            
            request.setAttribute("printData", strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "failed";
        }
    }
    
    /**
     * 格式化单条套餐及固定费详单记录
     * 扣费日期占10个字节，左对齐；套餐及固定费名称占28个字节，左对齐；费用(元)占8字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatFixfeeRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{10, 28, 8};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{10, 14, 8};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
               
        String[] rows = null;
        int rowNum = 0;
        
        // 套餐及固定费名称
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            rowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                rowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                rowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);    
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            if (m == rowNum - 1)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
            }                               
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[3], fieldLength[2]);    
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[2]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条通话详单记录
     * 起始时间，占8个字节，左对齐；通信地点占5个字节，左对齐；通信方式占4个字节，左对齐；对方号码占11字节，左对齐；
     * 通信时长占5个字节，左对齐；通话费(元)占6个字节，右对齐；长途费(元)占7个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatGsmRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[]=null;
        // 各字段每行打印字数，每个汉字占两个字节。通信时长特殊处理，既有汉字也有数字，数字占多数
        int fieldSize[] =null;
        String noShowToll = (String)getRequest().getSession().getAttribute("show");
        if("true".equals(noShowToll))
        {
            fieldLength = new int[]{8, 5, 4, 11, 5, 6};
            fieldSize= new int[]{8, 2, 2, 11, 5, 6};
        }
        else
        {
            fieldLength = new int[]{8, 5, 4, 11, 5, 6, 7};
            fieldSize= new int[]{8, 2, 2, 11, 5, 6, 7};
        }

        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 通信方式
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对方号码
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对通信时长进行特殊处理，数字占多数。tempRowNum之前已赋值，所以此处需重置为0
        int offset = 0;
        int len = 0;
        tempRowNum = 0;
        while (offset < fields[4].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[4].length())
            {
                len = fields[4].length() - offset;
            }
            
            while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
            {
                len -= 1;
            }
            
            offset += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }       
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 通信方式
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // 对方号码
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }

            // 通信时长
            len = fieldLength[4];
            if (offset < fields[4].length())
            {
                if (offset + len > fields[4].length())
                {
                    len = fields[4].length() - offset;
                }
                
                while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[5]);
                if(!"true".equals(noShowToll))
                {
                    rows[m] += CommonUtil.fillLeftBlank(fields[8], fieldLength[6]);
                }
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
                if(!"true".equals(noShowToll))
                {
                   rows[m] += CommonUtil.fillLeftBlank("", fieldLength[6]);
                }
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条短彩信详单记录
     * 起始时间，占9个字节，左对齐；通信地点占9个字节，左对齐；对方号码占13个字节，左对齐；信息类型占9个字节，左对齐；
     * 通信费(元)占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatSmsRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{9, 9, 13, 9, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{9, 4, 13, 4, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对方号码
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 信息类型
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
         
            // 对方号码
            if (fields[2].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[2].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // 信息类型
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[4]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条上网详单记录
     * 起始时间，占8个字节，左对齐；通信地点占8个字节、上网方式占7个字节、时长占8个字节、流量占9个字节，左对齐；通信费(元)占6个字节，右对齐。
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatGprsWlanRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{8, 8, 7, 8, 9, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{8, 4, 7, 8, 9, 6};
        
        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
        // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
        String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
        if ("1".equals(showGprsWlanFlux))
        {
            fieldLength = new int[]{8, 8, 7, 5, 7, 7, 6};
            fieldSize = new int[]{8, 4, 7, 5, 6, 6, 6};
        }
        // add end zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对上网方式进行特殊处理，英文占多数        
        tempRowNum = 0;
        
        int offset1 = 0;
        int len = 0;
        while (offset1 < fields[2].length())
        {
            len = fieldLength[2];
            
            if (offset1 + len > fields[2].length())
            {
                len = fields[2].length() - offset1;
            }
            
            while (fields[2].substring(offset1, offset1 + len).getBytes().length > fieldLength[2])
            {
                len -= 1;
            }
            
            offset1 += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对时长进行特殊处理，数字占多数
        tempRowNum = 0;
        
        int offset2 = 0;
        
        while (offset2 < fields[3].length())
        {
            len = fieldLength[3];
            
            if (offset2 + len > fields[3].length())
            {
                len = fields[3].length() - offset2;
            }
            
            while (fields[3].substring(offset2, offset2 + len).getBytes().length > fieldLength[3])
            {
                len -= 1;
            }
            
            offset2 += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 流量或正常时段流量
        if (fields[4].getBytes().length <= fieldLength[4])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[4])
            {
                tempRowNum = fields[4].length() / fieldSize[4];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[4] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
        // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
        if ("1".equals(showGprsWlanFlux))
        {
        	// 优惠时段流量
            if (fields[5].getBytes().length <= fieldLength[5])
            {
                tempRowNum = 1;
            }
            else
            {
                if (0 == fields[5].length() % fieldSize[5])
                {
                    tempRowNum = fields[5].length() / fieldSize[5];
                }
                else
                {
                    tempRowNum = fields[5].length() / fieldSize[5] + 1;
                }                              
            }
            
            if (rowNum < tempRowNum)
            {
                rowNum = tempRowNum;
            }
        }   
        // add end zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清 
            
        rows = new String[rowNum];
        
        // 对上网方式、时长进行特殊处理，英文、数字占多数
        offset1 = 0;
        offset2 = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 上网方式
            len = fieldLength[2];
            if (offset1 < fields[2].length())
            {
                if (offset1 + len > fields[2].length())
                {
                    len = fields[2].length() - offset1;
                }
                
                while (fields[2].substring(offset1, offset1 + len).getBytes().length > fieldLength[2])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(offset1, offset1 + len), fieldLength[2]);
                offset1 += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // 时长
            len = fieldLength[3];
            if (offset2 < fields[3].length())
            {
                if (offset2 + len > fields[3].length())
                {
                    len = fields[3].length() - offset2;
                }
                
                while (fields[3].substring(offset2, offset2 + len).getBytes().length > fieldLength[3])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(offset2, offset2 + len), fieldLength[3]);
                offset2 += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            // 流量或正常时段流量
            if (fields[4].length() > (m + 1) * fieldSize[4])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[4], (m + 1) * fieldSize[4]), fieldLength[4]);
            }
            else if (fields[4].length() > m * fieldSize[4])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[4]), fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            // add begin zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
            // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
            if ("1".equals(showGprsWlanFlux))
            {
                // 优惠时段流量
                if (fields[5].length() > (m + 1) * fieldSize[5])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * fieldSize[5], (m + 1) * fieldSize[5]), fieldLength[5]);
                }
                else if (fields[5].length() > m * fieldSize[5])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * fieldSize[5]), fieldLength[5]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[5]);
                }
                
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillLeftBlank(fields[8], fieldLength[6]);
                }
                else
                {
                    rows[m] += CommonUtil.fillLeftBlank("", fieldLength[6]);
                }
            }
            else
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillLeftBlank(" "+fields[6], fieldLength[5]);
                }
                else
                {
                    rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
                }
            }
            // add end zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }    
    
    /**
     * 格式化单条增值业务语音类扣费记录
     * 时间不打印，占8个字节，左对齐；使用方式占7个字节、业务名称占8个字节、服务号码占8个字节、时长占9个字节，左对齐；费用(元)占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatIsmgVoiceRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{8, 7, 8, 8, 9, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{8, 3, 4, 8, 9, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;

        // 使用方式       
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务名称
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务号码
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 时长
        tempRowNum = 0;
        
        int offset = 0;
        int len = 0;
        while (offset < fields[4].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[4].length())
            {
                len = fields[4].length() - offset;
            }
            
            while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
            {
                len -= 1;
            }
            
            offset += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 使用方式
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            // 时长
            len = fieldLength[4];
            if (offset < fields[4].length())
            {
                if (offset + len > fields[4].length())
                {
                    len = fields[4].length() - offset;
                }
                
                while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[5], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条增值业务非语音类扣费记录
     * 时间，占8个字节，左对齐；使用方式占10个字节，左对齐；业务名称占14个字节，左对齐；业务端口占8个字节，左对齐；费用占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatIsmgNonVoiceRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{8, 10, 14, 8, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{8, 5, 7, 8, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 使用方式       
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务名称
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务端口
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 使用方式
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[4], fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[4]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条代收费业务语音类扣费记录
     * 时间，占8个字节，左对齐；业务名称占4个字节，左对齐；服务号码占9个字节，左对齐；服务提供商11个字节，左对齐；时长占8个字节，左对齐；
     * 费用占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeVoiceRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{8, 4, 9, 11, 8, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{8, 2, 9, 5, 8, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 业务名称      
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                tempRowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务号码
        if (fields[3].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[2])
            {
                tempRowNum = fields[3].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务提供商
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 时长
        tempRowNum = 0;
        
        int offset = 0;
        int len = 0;
        while (offset < fields[5].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[5].length())
            {
                len = fields[5].length() - offset;
            }
            
            while (fields[5].substring(offset, offset + len).getBytes().length > fieldLength[4])
            {
                len -= 1;
            }
            
            offset += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[2].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[3].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // 服务提供商
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            // 时长
            len = fieldLength[4];
            if (offset < fields[5].length())
            {
                if (offset + len > fields[5].length())
                {
                    len = fields[5].length() - offset;
                }
                
                while (fields[5].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[5].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[6], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条代收费业务非语音类扣费记录
     * 时间，占8个字节，左对齐；业务名称占4个字节，左对齐；服务号码占9个字节，左对齐；服务提供商11个字节，左对齐；费用类型占8个字节，左对齐；
     * 费用占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeNonVoiceRecord(String record)
    {
        // 各字段所占字节数
        int fieldLength[] = new int[]{8, 4, 9, 11, 8, 6};
        
        // 各字段每行打印字数，一个汉字占两个字节
        int fieldSize[] = new int[]{8, 2, 9, 5, 4, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 业务名称      
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                tempRowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务号码
        if (fields[3].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[2])
            {
                tempRowNum = fields[3].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务提供商
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 费用类型
        if (fields[6].getBytes().length <= fieldLength[4])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[6].length() % fieldSize[4])
            {
                tempRowNum = fields[6].length() / fieldSize[4];
            }
            else
            {
                tempRowNum = fields[6].length() / fieldSize[4] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[2].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[3].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // 服务提供商
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            // 费用类型
            if (fields[6].getBytes().length <= fieldLength[4])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6], fieldLength[4]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
                }
            }
            else
            {
                if (fields[6].length() > (m + 1) * fieldSize[4])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * fieldSize[4], (m + 1) * fieldSize[4]), fieldLength[4]);
                }
                else if (fields[6].length() > m * fieldSize[4])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * fieldSize[4]), fieldLength[4]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 更新详单打印次数
     * 
     * @see [类、类#方法、类#成员]
     */
    public String updatePrintTimes()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servNumber = customerSimp.getServNumber();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        String qryMonth = sdf1.format(new Date());
        
        DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, qryMonth, cdrType);
        if (timesPO == null)
        {
            detailedRecordsService.addPrintTimes(servNumber, qryMonth, 1, cdrType);
        }
        else
        {
            detailedRecordsService.updatePrintTimes(servNumber, qryMonth, timesPO.getTimes() + 1, cdrType);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        return "success";
    }
    
    public void qryMailbox() throws IOException
    {        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        this.getResponse().setContentType("text/html;charset=GBK");
        
        PrintWriter out = this.getResponse().getWriter();
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 查询用户是否已开通手机邮箱
        Map result = detailedRecordsBean.qryMailbox(customerSimp, terminalInfoPO, curMenuId);
        
        if (null != result && 0 < result.size())
        {
            CTagSet tagSet = (CTagSet)result.get("returnObj");
            
            // 0：未开通 1：已开通
            String haveMailbox = tagSet.GetValue("havemailbox");
            
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 begin
            if(StringUtils.isBlank(haveMailbox))
            {
                haveMailbox = tagSet.GetValue("ISHAVEMAILBOX");
            }
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 end
            
            if (null != haveMailbox && "1".equals(haveMailbox))
            {                
                out.write("1");
                out.flush();
                
                //modify begin sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
                //详单邮件下发开关（1：开启，0：关闭）
                String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH);

                if("1".equals(sendRecords))
                {
                	//山东新版详单邮件下发接口
                	sendRecordsMail_new("0");
                }
                else
                {
                	sendMail("0");
                }
                //modify end sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
                
            }
            else
            {
                out.write("0");
                out.flush();
            }
        }
        else
        {
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "查询用户是否已开通手机邮箱失败!");
            
            logger.error("查询用户" + customerSimp.getServNumber() + "是否已开通手机邮箱失败!");
            
            out.write("2");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox End!");
        }
    }
    
    /**
     * <详单邮件下发-开通139邮箱>
     * <功能详细描述>
     * @throws IOException
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     */
    public void add139Mail() throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail Starting ...");
        }
        
        PrintWriter out = this.getResponse().getWriter();
        
        // 获取session
        HttpSession session = this.getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用接口开通139邮箱
        Map result = detailedRecordsBean.add139Mail(customer, terminalInfoPO, curMenuId);
        
        if (null != result && 1 < result.size())
        {
            out.write("1");
            out.flush();
            
            // 延迟时间（分钟）
            String delayTime = (String) PublicCache.getInstance().getCachedData("SH_MAIL_DELAY");
            
            if (null == delayTime || "".equals(delayTime.trim()))
            {
                delayTime = "3";
            }
            
            //modify begin sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
            //是否开启新版详单邮件下发接口（1：开启，0：关闭）
            String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH);

            if("1".equals(sendRecords))
            {
            	//山东新版详单邮件下发接口
            	sendRecordsMail_new(delayTime);
            }
            else
            {
            	
                // 139邮箱开通成功，发送邮件
                sendMail(delayTime);
            }
            //modify begin sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函

        }
        else
        {
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "139邮箱开通失败");
            
            logger.error("为用户" + customer.getServNumber() + "开通139邮箱失败!");
            
            out.write("0");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail End");
        }
    }
    
    /**
     * <详单邮件发送方法>
     * <功能详细描述>
     * @param delayTime 延迟时间
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     */
    private void sendRecordsMail_new(String delayTime)
    {
        HttpSession session = this.getRequest().getSession();
               
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        //是否发送短信提醒，0 发送，1 不发送，默认为0。
        String nosms = "0";
        
        //短信提醒开关判断
        String sendSms = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_NOSMS);
        
        //1：不发送短信提醒
        if("1".equals(sendSms))
        {
        	nosms="1";
        }
/*      // 延迟时间,单位：分钟
        int delay = Integer.parseInt(delayTime);
        
        // 发送邮件
        Timer timer = new Timer();
        
        //调用定时任务发送邮件
        timer.schedule(new SendRecordsMailUtil(customerSimp, terminalInfo, curMenuId, startDate, endDate, 
        		month, iscycle, cdrType, nosms), new Date(System.currentTimeMillis() + delay * 60L * 1000));
		//详单邮件下发接口调用
*/		
        //modify by sWX219697 2014-8-13
        //修改接口调用方式，用Timer延时调用接口时，底层获取不到Action的上下文，调用失败
        detailedRecordsBean.sendRecordsMail(customerSimp, terminalInfo, curMenuId, startDate, 
				endDate, month, iscycle, cdrType, nosms);
    }
    
    /**
     * 向用户的139邮箱发送详单信息
     * 
     * @param delayTime
     * @see 
     */
    private void sendMail(String delayTime)
    {
        StringBuffer mailContent = new StringBuffer(1024);
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
               
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        if ("0".equals(verFlag))
        {           
            // 清单名称 "GSMCDR"
            typeName = getTypeServiceName(cdrType);
            
            // 获得清单表头信息 [[【通话详单】], [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)], [详单条数：,
            // 时长合计：, 费用合计(元)：], [recordcount, totaltimes, totalfee]]
            Object[] tableDetail = getTableDetail(cdrType);
            
            // 清单名称中文 "[【通话详单】]"
            tableHeader = ((String[]) (tableDetail[0]))[0];
            
            // 表头中文 [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)]
            tableTitle = (String[]) (tableDetail[1]);
            titleCols = tableTitle.length;
            
            // 表头长度
            request.setAttribute("titleCols", tableTitle.length);
            
            // 合计中文 [详单条数：, 时长合计：, 费用合计(元)：]
            tableTail = (String[]) (tableDetail[2]);
            
            // 合计英文 [recordcount, totaltimes, totalfee]
            request.setAttribute("summaryTitle", tableDetail[3]);
            
            if (tableDetail.length >= 5)
            {
                tableDescription = (String[]) tableDetail[4];
            }           
            
            // Vector中保存记录，每条记录是一个String，多个字段之间以","隔开
            resultRecords = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                    terminalInfo,
                    startDate,
                    endDate,
                    "",
                    cdrType,
                    curMenuId,
                    tableTitle.length - 1, month, iscycle);
            
            // 查询到详单记录
            if (null != resultRecords && 0 < resultRecords.size())
            {
                Map<String, String> totalMap = calDynTotalSD(resultRecords);
                
                // 拼装邮件内容:表头
                mailContent.append("<html>")
                        .append("<head>")
                        .append("<style type='text/css'>")
                        .append(".tb_blue02, .tb_blue02 td, .tb_blue02 th{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; line-height:30px; height:30px; text-align:center;}")
                        .append(".tit_info{line-height:30px; height:30px; font-size:22px; position:relative;}")
                        .append("</style>")
                        .append("</head>")
                        .append("<body>")
                        .append("<p class='tit_info' align='left'>")
                        .append(tableHeader)
                        .append("</p>")
                        .append("<table class='tb_blue02' width='100%'>")
                        .append("<tr>");
                        
                for (int i = 0; i < tableTitle.length; i++)
                {
                    mailContent.append("<th>").append(tableTitle[i]).append("</th>");
                }
                
                mailContent.append("</tr>");
                
                // 合计中文 [详单条数：, 时长合计：, 费用合计(元)：]
                tableTail = (String[])(tableDetail[2]);
                
                if (tableDetail.length >= 5)
                {
                    tableDescription = (String[])tableDetail[4];
                }
                
                // 拼装邮件内容：查询结果
                String recordStr = "";        
                String[] recordArray = null;
                for (int i = 0; i < resultRecords.size(); i++)
                {
                    recordStr = (String) resultRecords.get(i);            
                    recordArray = recordStr.split(",");
                    
                    mailContent.append("<tr>");
                    
                    for (int j = 0; j < recordArray.length; j++)
                    {
                        mailContent.append("<td>").append(recordArray[j]).append("</td>");
                    }
                    
                    mailContent.append("</tr>");
                }        
                
                // 拼装邮件内容：详单条数：, 时长合计：, 费用合计(元)：
                mailContent.append("<tr><td colspan='").append(tableTitle.length).append("'>");
                
                // 合计英文 [recordcount, totaltimes, totalfee]
                String[] summaryTitle = (String[]) tableDetail[3];       
                for (int i = 0; i < summaryTitle.length; i++)
                {
                    if (0 == i)
                    {
                        mailContent.append("<strong>");
                    }
                    
                    mailContent.append(tableTail[i]).append(totalMap.get(summaryTitle[i])).append("     ");
                    
                    if (summaryTitle.length - 1 == i)
                    {
                        mailContent.append("</strong>");
                    }            
                }

                mailContent.append("</td></tr>");
                
                // 拼装邮件内容：补充性说明
                if (tableDescription != null && tableDescription.length > 0)
                {
                    mailContent.append("<tr><td colspan='").append(tableTitle.length).append("'>");
                    
                    for (int i = 0; i < tableDescription.length; i++)
                    {
                        mailContent.append(tableDescription[i]).append("<br/>");
                    }
                    
                    mailContent.append("</td></tr>");
                }
                
                mailContent.append("</table></body></html>");
                
                if (logger.isDebugEnabled())
                {
                    logger.debug(mailContent.toString());
                }
            }
            else
            {
                logger.error("向用户" + customerSimp.getServNumber() + "的139邮箱中发送详单信息时，查询失败");
            }            
        }
        else if ("1".equals(verFlag))
        {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
            
            Date date = new Date();
            chQueryDate = sdf2.format(date);
            
            try
            {
                date = sdf1.parse(startDate);
                chStartDate = sdf2.format(date);
            }
            catch (ParseException e)
            {
                logger.error("格式化账期开始时间错误：", e);
                
                chStartDate = startDate;
            }
            
            try
            {
                date = sdf1.parse(endDate);
                chEndDate = sdf2.format(date);
            }
            catch (ParseException e)
            {
                logger.error("格式化账期开始时间错误：", e);
                
                chEndDate = endDate;
            }
            
            /*
             * 详单记录信息，包含totalfee、smstotalnum、billinfo等。 billinfo为08-03 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4|08-05
             * 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4
             */
            Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                    terminalInfo,
                    cdrType,
                    startDate,
                    endDate,
                    curMenuId,
                    iscycle,
                    cycle);
            
            if (null != resultMap)
            {
                // 拼装邮件内容:表头
                mailContent.append("<html>")
                        .append("<head>")
                        .append("<style type='text/css'>")
                        .append(".tb_blue02, .tb_blue02 td, .tb_blue02 th{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; line-height:30px; height:30px; text-align:center;}")
                        .append(".tit_info{line-height:30px; height:30px; font-size:22px; position:relative; font-weight:bold;}")
                        .append("body{font-family:Arial,'微软雅黑' }")
                        .append(".bold{ font-weight:bold;}")
                        .append(".tb_blue02 td.tr, .tb_blue02 th.tr{text-align:right;}")
                        .append(".tb_blue02 td.tl, .tb_blue02 th.tl{text-align:left;}")
                        .append("</style>")
                        .append("</head>")
                        .append("<body>")
                        .append("<p class='tit_info' align='center'>中国移动通信 客户详单</p>")
                        .append("<table class='tb_blue02' width='60%' align='center'>")              
                        .append("<tr>")
                        .append("<th class='tl'>客户名称</th><td class='tl'>")
                        .append(customerSimp.getCustomerName())
                        .append("</td><th class='tl'>手机号码</th><td class='tl'>")
                        .append(customerSimp.getServNumber())
                        .append("</td></tr>")
                        .append("<tr>")
                        .append("<th class='tl'>查询时段</th><td class='tl'>")
                        .append(chStartDate).append("-").append(chEndDate)
                        .append("</td><th class='tl'>查询日期</th><td class='tl'>")
                        .append(chQueryDate)
                        .append("</td></tr>")
                        .append("</table>")
                        .append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'>");
                
                // 套餐及固定费详单
                if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
                {
                    mailContent.append("<tr>")
                            .append("<th colspan='4' class='tl'>套餐及固定费详单</th>")
                            .append("</tr>")
                            .append("<tr><th>扣费日期</th><th>使用周期</th><th>套餐及固定费名称</th><th>费用(元)</th></tr>");
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        // 合计费用
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        // 单条记录
                        String record = "";                        
                        String[] fields = null;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // 空记录
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // 如果以@_@，说明最后一个字段为空，追加空格
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }
                            
                            mailContent.append("<tr>");
                            
                            fields = record.split("@_@");                            
                            for (int m = 0; m < fields.length; m++)
                            {                               
                                if (m == fields.length - 1)
                                {
                                    mailContent.append("<td class='tr'>").append(fields[m]).append("</td>");
                                }
                                else
                                {
                                    mailContent.append("<td>").append(fields[m]).append("</td>");
                                }
                            }
                            
                            mailContent.append("</tr>");
                        }
                        
                        String totalfee = resultMap.get("totalfee");
                        if (null == totalfee || "".equals(totalfee))
                        {
                            totalfee = "0";
                        }
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td class='tr'>"+totalfee+"</td></tr>");
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {                    
                    parseSingleTypeRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='9' class='tl'>通话详单</th>")
                            .append("</tr>")
                            .append("<tr><th>起始时间(hh:mm:ss)</th><th>通信地点</th><th>通信方式</th><th>对方号码</th>")
                            .append("<th>通信时长</th><th>通信类型</th><th>套餐优惠</th><th>通话费(元)</th><th>长途费(元)</th></tr>");
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='9' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1 || p == fields.length - 2)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td>"+resultMap.get("txtotaltime")+"</td><td></td><td></td><td>"+resultMap.get("thtotalfee")+"</td><td>"+resultMap.get("cttotalfee")+"</td></tr>");
                        //mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td></td><td></td><td colspan='2' class='tr'>").append(resultMap.get("totalfee")).append("</td></tr>");
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    }           
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    parseSmsRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='8' class='tl'>短/彩信详单</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th>短信总条数：</th><th>").append(resultMap.get("smstotalnum")).append("条</th><th>彩信总条数：</th><th>").append(resultMap.get("mmstotalnum")).append("条</th>")
                            .append("</tr>")
                            .append("<tr><th>起始时间(hh:mm:ss)</th><th>通信地点</th><th>对方号码</th>")
                            .append("<th>通信方式</th><th>信息类型</th><th>业务名称</th><th>套餐优惠</th><th>通信费(元)</th></tr>");
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='8' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee")).append("</td></tr>");
                    }  
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    parseGprsWlanRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='7' class='tl'>上网详单</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>移动数据流量上网话单总条数</th><th>")
                            .append(resultMap.get("gprstotalnum"))
                            .append("条</th>")
                            .append("<th colspan='2'>移动数据流量上网总费用</th><th></th><th>")
                            .append(resultMap.get("gprstotalfee"))
                            .append("元</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>WLAN话单总条数</th><th>")
                            .append(resultMap.get("wlantotalnum"))
                            .append("条</th>")
                            .append("<th colspan='2'>WLAN话单总费用</th><th></th><th>")
                            .append(resultMap.get("wlantotalfee"))
                            .append("元</th>")
                            .append("</tr>")
                            
                            // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                            // modify begin zKF66389 2015-6-13 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
//                            .append("<tr>")
//                            .append("<th colspan='2'>移动数据流量上网总时长</th><th>")
//                            .append(resultMap.get("gprstotaltime"))
//                            .append("</th>")
//                            .append("<th colspan='2'>移动数据流量上网总流量</th><th></th><th>")
//                            .append(resultMap.get("gprstotalflux"))
//                            .append("</th>")
//                            .append("</tr>")
//                            
//                            .append("<tr>")
//                            .append("<th colspan='2'>WLAN上网总时长</th><th>")
//                            .append(resultMap.get("wlantotaltime"))
//                            .append("</th>")
//                            .append("<th colspan='2'>WLAN上网总流量</th><th></th><th>")
//                            .append(resultMap.get("wlantotalflux"))
//                            .append("</th>")
//                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>移动数据流量上网总流量</th><th>")
                            .append(resultMap.get("gprstotalflux"))
                            .append("</th>")
                            .append("<th colspan='2'>WLAN上网总流量</th><th></th><th>")
                            .append(resultMap.get("wlantotalflux"))
                            .append("</th>")
                            .append("</tr>")
                            
                            .append("<tr>")
                            .append("<th colspan='2'>WLAN上网总时长</th><th>")
                            .append(resultMap.get("wlantotaltime"))
                            .append("</th>")
                            .append("<th colspan='2'></th><th></th><th>")
                            .append("")
                            .append("</th>")
                            .append("</tr>")
                            // modify end zKF66389 2015-6-13 OR_SD_201412_479_SD_关于修改上网详单查询的汇总信息中时长字段显示的需求
                            // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                            
                            .append("<tr><th>起始时间(hh:mm:ss)</th><th>通信地点</th><th>上网方式</th>")
                            .append("<th>时长</th><th>流量</th><th>套餐优惠</th><th>通信费(元)</th></tr>");
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {     
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                    }
                }
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    parseMultiTypesRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='6' class='tl'>增值业务扣费记录</th>")
                            .append("</tr>");                            
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != voiceRecords || 0 < voiceRecords.size())
                    if (0 < voiceRecords.size())
                    {
                        mailContent.append("<tr>")
                                .append("<th colspan='6' class='tl'>语音类业务扣费记录</th>")
                                .append("</tr>")
                                .append("<tr><th>时间(hh:mm:ss)</th><th>使用方式</th><th>业务名称</th>")
                                .append("<th>服务号码</th><th>时长</th><th>费用(元)</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < voiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='6' class='tl'>").append((String) voiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) voiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td>"+resultMap.get("zzywtotaltime")+"</td><td class='tr'>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        //mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    }
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != nonVoiceRecords || 0 < nonVoiceRecords.size())
                    if (0 < nonVoiceRecords.size())
                    {
                        mailContent.append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'><tr>")
                                .append("<th colspan='5' class='tl'>非语音类业务扣费记录</th>")
                                .append("</tr>")
                                .append("<tr><th>时间(hh:mm:ss)</th><th>使用方式</th><th>业务名称</th>")
                                .append("<th>业务端口</th><th>费用</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < nonVoiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='5' class='tl'>").append((String) nonVoiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) nonVoiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-nonvoice")).append("</td></tr>");
                    }
                }
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    parseMultiTypesRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='7' class='tl'>代收费业务扣费记录</th>")
                            .append("</tr>");                            
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != voiceRecords || 0 < voiceRecords.size())
                    if (0 < voiceRecords.size())
                    {
                        mailContent.append("<tr>")
                                .append("<th colspan='7' class='tl'>语音类业务扣费记录</th>")
                                .append("</tr>")
                                .append("<tr><th>时间(hh:mm:ss)</th><th>使用方式</th><th>业务名称</th>")
                                .append("<th>服务号码</th><th>服务提供商</th><th>时长</th><th>费用(元)</th></tr>");;
                
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < voiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) voiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) voiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td>"+resultMap.get("dsftotaltime")+"</td><td class='tr'>"+resultMap.get("totalfee-voice")+"</td></tr></table>");
                        //mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td></td><td>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        // 详单优化，添加各种合计  add by yKF28472 2012-07-09 end
                    }
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    //if (null != nonVoiceRecords || 0 < nonVoiceRecords.size())
                    if (0 < nonVoiceRecords.size())
                    {
                        mailContent.append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'><tr>")
                                .append("<th colspan='7' class='tl'>非语音类业务扣费记录</th>")
                                .append("</tr>")
                                .append("<tr><th>时间(hh:mm:ss)</th><th>使用方式</th><th>业务名称</th>")
                                .append("<th>服务号码</th><th>服务提供商</th><th>企业代码</th><th>费用类型</th><th>费用(元)</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < nonVoiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) nonVoiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) nonVoiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // 单条记录
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // 如果以@_@，说明最后一个字段为空，追加空格
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>合计：</td><td></td><td></td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-nonvoice")).append("</td></tr>");
                    }
                }
                
                mailContent.append("</table></body></html>");

                if (logger.isDebugEnabled())
                {
                    logger.debug(mailContent.toString());
                }
            }
        }
        else
        {
            logger.error("向用户" + customerSimp.getServNumber() + "的139邮箱中发送详单信息失败，不支持的查询版本" + verFlag);
        }
        
        if (null != mailContent && 0 < mailContent.length())
        {
            // 收件人邮箱
            String to = customerSimp.getServNumber() + "@139.com";
            
            // 邮件标题
            String subject = "您" + chStartDate + "至" + chEndDate + "的详单信息已到，请查收。";
            
            // 延迟时间,单位：分钟
            int delay = Integer.parseInt(delayTime);
            
            // 发送邮件
            Timer timer = new Timer();
            timer.schedule(new SendMailUtil(to, subject, mailContent.toString()), new Date(
                    System.currentTimeMillis() + delay * 60L * 1000));
        }
    }
    
    /** 
     * 取得是否显示GprsWlan正常和优惠时段流量开关参数
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-11-20 OR_SD_201311_1126 关于上网详单查询展示分正常和优惠时段的修改需求澄清
     */
    public String getShowGprsWlanFlux()
    {
        // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
        return CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
    }
    
    private void createRecord(StringBuffer sb, String text)
    {
        sb.append("'" + text + "',");
    }
    
    private void createLine(StringBuffer sb)
    {
        sb.append("' ---------------------------------------------',");
    }
    
    public DetailedRecordsBean getDetailedRecordsBean()
    {
        return detailedRecordsBean;
    }
    
    public void setDetailedRecordsBean(DetailedRecordsBean detailedRecordsBean)
    {
        this.detailedRecordsBean = detailedRecordsBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String[] getMonths()
    {
        return months;
    }
    
    public void setMonths(String[] months)
    {
        this.months = months;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public String getCdrType()
    {
        return cdrType;
    }
    
    public void setCdrType(String cdrType)
    {
        this.cdrType = cdrType;
    }
    
    public Map<String, String> getBillTotal()
    {
        return billTotal;
    }
    
    public void setBillTotal(Map<String, String> billTotal)
    {
        this.billTotal = billTotal;
    }
    
    public String getCycle()
    {
        return cycle;
    }
    
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public List<CycleInfoPO> getCycles()
    {
        return cycles;
    }
    
    public void setCycles(List<CycleInfoPO> cycles)
    {
        this.cycles = cycles;
    }
    
    public String getChStartDate()
    {
        return chStartDate;
    }
    
    public void setChStartDate(String chStartDate)
    {
        this.chStartDate = chStartDate;
    }
    
    public String getChEndDate()
    {
        return chEndDate;
    }
    
    public void setChEndDate(String chEndDate)
    {
        this.chEndDate = chEndDate;
    }
    
    public String getQueryDate()
    {
        return queryDate;
    }
    
    public void setQueryDate(String queryDate)
    {
        this.queryDate = queryDate;
    }
    
    public List<String> getFixfeeRecords()
    {
        return fixfeeRecords;
    }
    
    public void setFixfeeRecords(List<String> fixfeeRecords)
    {
        this.fixfeeRecords = fixfeeRecords;
    }
    
    public Vector getResultRecords()
    {
        return resultRecords;
    }
    
    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
    }
    
    public Vector getVoiceRecords()
    {
        return voiceRecords;
    }
    
    public void setVoiceRecords(Vector voiceRecords)
    {
        this.voiceRecords = voiceRecords;
    }
    
    public Vector getNonVoiceRecords()
    {
        return nonVoiceRecords;
    }
    
    public void setNonVoiceRecords(Vector nonVoiceRecords)
    {
        this.nonVoiceRecords = nonVoiceRecords;
    }
    
    public String getChQueryDate()
    {
        return chQueryDate;
    }
    
    public void setChQueryDate(String chQueryDate)
    {
        this.chQueryDate = chQueryDate;
    }
    
    public String getFarthestDate()
    {
        return farthestDate;
    }
    
    public void setFarthestDate(String farthestDate)
    {
        this.farthestDate = farthestDate;
    }
    
    public String getTypeName()
    {
        return typeName;
    }
    
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
    
    public String getTableHeader()
    {
        return tableHeader;
    }
    
    public void setTableHeader(String tableHeader)
    {
        this.tableHeader = tableHeader;
    }
    
    public String[] getTableTitle()
    {
        return tableTitle;
    }
    
    public void setTableTitle(String[] tableTitle)
    {
        this.tableTitle = tableTitle;
    }
    
    public String[] getTableTail()
    {
        return tableTail;
    }
    
    public void setTableTail(String[] tableTail)
    {
        this.tableTail = tableTail;
    }
    
    public String[] getTableDescription()
    {
        return tableDescription;
    }
    
    public void setTableDescription(String[] tableDescription)
    {
        this.tableDescription = tableDescription;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public int getTitleCols()
    {
        return titleCols;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTitleCols(int titleCols)
    {
        this.titleCols = titleCols;
    }
    
    public DetailedRecordsService getDetailedRecordsService()
    {
        return detailedRecordsService;
    }
    
    public void setDetailedRecordsService(DetailedRecordsService detailedRecordsService)
    {
        this.detailedRecordsService = detailedRecordsService;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrintFlag()
    {
        return printFlag;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }

    public String getVerFlag()
    {
        return verFlag;
    }

    public void setVerFlag(String verFlag)
    {
        this.verFlag = verFlag;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getIscycle()
    {
        return iscycle;
    }

    public void setIscycle(String iscycle)
    {
        this.iscycle = iscycle;
    }

	public String getDetailPwd() {
		return detailPwd;
	}

	public void setDetailPwd(String detailPwd) {
		this.detailPwd = detailPwd;
	}
}
