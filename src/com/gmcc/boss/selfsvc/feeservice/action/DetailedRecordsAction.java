/*
 * 文件名：DetailedRecordsAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-16
 * 修改内容：新增，月详单查询
 */
package com.gmcc.boss.selfsvc.feeservice.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.query.common.SelfSvcCdrType;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * 
 * 月详单查询
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-16
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DetailedRecordsAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(DetailedRecordsAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
    private transient DetailedRecordsService detailedRecordsService = null;
    
    /**
     * 查询月份
     */
    private String month = "";
    
    /**
     * 菜单
     */
    private String curMenuId = "";
    
    /**
     * 当前页码
     */
    private String page = "";
    
    /**
     * 每页显示记录数
     */
    private final int pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    /**
     * 数字标识的详单类型
     */
    private String listtype = "";
    
    /**
     * 英文的详单类型名称
     */
    private String typeName = "";
    
    /**
     * 是否是查询全部的详单，如果是，提供打印功能
     */
    private String isQueryAll = "";
    
    /**
     * 中文的详单类型名称
     */
    private String tableHeader = "";
    
    /**
     * 详单记录显示字段
     */
    private String[] tableTitle = null;
    
    /**
     * 详单记录的总括
     */
    private String[] tableTail = null;
    
    /**
     * 补充性说明
     */
    private String[] tableDescription = null;
    
    /**
     * 页面显示的详单记录
     */
    private Vector resultRecords = null;
    
    private String pagecount = "";
    
    /**
     * 查询方式
     */
    private String fee_type = "";
    
    // add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    /**
     * 邮箱开通标记
     */
    private String haveMailbox = "";
    // add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    
    /**
     * 详单类型对应的url
     */
    private String menuURL = "";

    /**
     * 可查询当前月及前五个月的详单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String showMonths()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("showMonths Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (customerSimp != null)
        {
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }
        
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")) )
        {
           
            TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 权限验证
            boolean bz = detailedRecordsBean.checkQueryAuth(customerSimp, terminalInfo, customerSimp.getServNumber(), curMenuId);
            
            if (!bz)
            {
                this.getRequest().setAttribute("errormessage", "该手机号码无详单查询权限！");
                return "failed";
            }
        }
        // 可查询当前月和前五个月的详单信息
        String pattern = "yyyyMM";
        
        String[] months = CommonUtil.getLastMonths(pattern, 6);
        
        this.getRequest().setAttribute("months", months);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("showMonths End");
        }
        
        return "success";
    }
    
    /**
     * 从月份选择页面返回，之所以不走公共的返回处理逻辑，是因为此时需要清空session中保存的清单数据
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String backToFunctionList()
    {
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (customerSimp != null)
        {
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }
        
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        // 根据当前菜单获取父菜单ID
        String parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, curMenuId);
        
        // 将父菜单ID置为当前菜单
        this.getRequest().setAttribute(Constants.CUR_MENUID, parentMenuID);
        
        return "back";
    }
    
    /**
     * 显示详单查询类型
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String showTypes()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("showTypes Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("showTypes End");
        }
        
        // add begin g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        {
            return "hub_success";
        }
        // add end g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
        
        return "success";
    }
    
    /**
     * 从详单记录显示页面返回到详单类型查询页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String backToTypesPage()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("backToTypesPage Starting ...");
        }
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        // 根据当前菜单获取父菜单ID
        String parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, curMenuId);
        
        // 将父菜单ID置为当前菜单
        this.getRequest().setAttribute(Constants.CUR_MENUID, parentMenuID);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("backToTypesPage End");
        }
        
        return "success";
    }
    
    /**
     * 查询详单记录
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryDetailedRecords()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords Starting ...");
        }
        // yKF28472
        if (listtype.split(",").length > 1)
        {
            listtype = listtype.split(",")[0];
        }
            
        //湖北的详单查询需要选择查询方式(非零费用的、零费用的、全部的)
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID"))
                && (!"true".equals(isQueryAll) || "0".equals(listtype))
                && (fee_type == null || "".equals(fee_type.trim())))
        {
            //自助终端的详单查询类型除了有通话详单、短信详单等具体的类型之外，还有全部详单。
            //用户在选择了全部详单之后，系统先查询第1种详单(如通话详单，配置的)并显示，同时在页面上显示“下一详单”的链接，用户点击链接后才会查询下一种详单(如短信详单)。
            //就是说即便用户选择了全部详单，系统也是在用户的操作之下依次显示每种详单。
            //如果用户查询时选择了通话详单或者短信详单，那么进入方式选择页面；如果用户选择了全部详单，且查询的是第一种详单，也需要进入方式选择页面。
            //如果用户选择的是全部详单，但查询的不是第一种详单，说明方式已经选择过了，不需要再进入方式选择页面。
            return "fee_type";            
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 清单名称 "GSMCDR"
        typeName = getTypeServiceName(listtype);
        
        // 获得清单表头信息 [[【通话详单】], [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)], [详单条数：, 时长合计：, 费用合计(元)：], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(listtype);
        
        // 清单名称中文 "[【通话详单】]"
        tableHeader = ((String[])(tableDetail[0]))[0];
        
        // 表头中文 [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)]
        tableTitle = (String[])(tableDetail[1]);
        
        // 表头长度
        request.setAttribute("titleCols", tableTitle.length);
        
        // 合计中文 [详单条数：, 时长合计：, 费用合计(元)：]
        tableTail = (String[])(tableDetail[2]);
        
        // 合计英文 [recordcount, totaltimes, totalfee]
        request.setAttribute("summaryTitle", tableDetail[3]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[])tableDetail[4];
        }
        
        /** 拼装内容
        type：0--GSMCDR
        header：【通话详单】
        title：序号  对方号码  话单类型  话单产生地  通话开始时间  通话时长(秒)  基本费(元)  长途费(元)  声讯信息费(元)  总话费(元)  
        tail：详单条数：  时长合计：  费用合计(元)：  
        **/
        if (logger.isInfoEnabled())
        {
            StringBuffer buffer = new StringBuffer(1024);
            
            buffer.append("type：").append(listtype).append("--").append(typeName);
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
            
            logger.info(buffer.toString());
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();
        
        String currMonth = CommonUtil.getLastMonth("yyyyMM", 0);
        // 非当前月详单允许打印
        // 但是只有在选择全部详单的前提下才能打印
//        if (!currMonth.equals(month) && "true".equalsIgnoreCase(isQueryAll))
//        {
            //DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servnumber, month);
            // yKF28472
            DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servnumber, month, this.listtype);
            int times = 0;
            if (timesPO != null)
            {
                times = timesPO.getTimes();
            }
            
            // 只有打印次数小于限制值，才可以打印；否则不允许打印
            if (times < Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES)))
            {
                request.setAttribute("printFlag", "1");
            }
//        }
        
        // 先从session中取详单记录，如果session中有，直接返回；如果session中没有，调用接口查询
        // session中保存的详单数据，属性名称：Constants.LIST_DATA_SESSION_NAME + servNumber，属性值：map
        // map中的数据，key：201012-GSMCDR，value：Vector
        // Vector中保存记录，每条记录是一个String，多个字段之间以","隔开，如
        Vector records = null;
        
        Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        
        // 湖北
        String feeType = "";
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
		{
        	feeType = "-" + fee_type;
		}

        if (detailedRecords != null && detailedRecords.get(month + "-" + typeName + feeType) != null)
        {
            records = (Vector)detailedRecords.get(month + "-" + typeName + feeType);
            
            Map totalMap = (Map)detailedRecords.get(month + "-" + typeName + feeType + "-SUM");
            
            request.setAttribute("totalMap", totalMap);
        }
        else
        {
            if (detailedRecords == null)
            {
                detailedRecords = new HashMap();
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            records = qryCDRList(listtype, month,fee_type,servnumber, tableTitle.length - 1);
            
            //未查询到详单记录
            if (records == null || records.size() == 0)
            {
                String resultMsg = getConvertMsg("未查询到详单记录", "zz_04_17_000001", null, formatParams(month, typeName, fee_type));
                
                request.setAttribute("errormessage", resultMsg);
                
                request.setAttribute("recordCount", "0");
                
                this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", resultMsg);
                
                //Change by LiFeng 修改 "清单查询全部清单时，某一清单查询不到，哪后面的清单都不能看" 的BUG
                //return "failed";
                return "success";
            }
            
            detailedRecords.put(month + "-" + typeName + feeType, records);
            
            Map totalMap = null;
            
            if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
    		{
            	totalMap = calDynTotalHUB(records);
    		}
            else if (Constants.PROOPERORGID_SD.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
            {
            	totalMap = calDynTotalSD(records);
            }
            else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                totalMap = calDynTotalNX(records);
            }
            else if (Constants.PROOPERORGID_CQ.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                totalMap = calDynTotalCQ(records);
            }
            
            detailedRecords.put(month + "-" + typeName + feeType + "-SUM", totalMap);
            
            request.setAttribute("totalMap", totalMap);
        }
        
        request.setAttribute("recordCount", "" + records.size());
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "月详单(" + month + "-" + typeName + feeType + ")查询成功");
        
        resultRecords = getQueryPageData(records);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords End");
        }
        
        return "success";
    }
    
    /**
     * 获取打印数据
     * 
     * @param request
     * @return
     */
    public String getPrintData()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getPrintData Starting ...");
        }
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();
            
            StringBuffer scripts = new StringBuffer("var printDataObject = {");
            StringBuffer records = new StringBuffer("");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            String listType = request.getParameter("listtype");
            if (listType == null || listType.trim().length() < 1 || Integer.parseInt(listType) < 0)
            {
                // 如果没有取到清单类型，则返回空
                scripts.append("printTail:'YES', errmsg:'清单类型参数错误:" + listType + "'}");
                
                request.setAttribute("printData", scripts.toString());
                
                if (logger.isDebugEnabled())
                {
                    logger.debug("getPrintData End");
                }
                
                return "success";
            }
            
            // yKF28472 20111126 修改湖北全部打印功能
            if ("0".equals(listType))
            {
                // 如果是打印全部清单，则默认先打印第一个话单
                listType = "1";
            }
            else
            {
                listType = this.listtype;
            }
            
            int iListType = Integer.parseInt(listType);
            
            String typeServiceName = getTypeServiceName(listType);
            
            String page = request.getParameter("page");
            if (page == null || page.trim().length() < 1)
            {
                // 如果打印页不存在则返回空
                page = "1";
            }
            int iPage = Integer.parseInt(page);
            
            String qryMonth = request.getParameter("month");
            
            // 取得清单数据
            Vector dataV = null;
            Map totalM = null;
            
            Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);

            if (detailedRecords != null && detailedRecords.get(qryMonth + "-" + typeServiceName) != null)
            {
                dataV = (Vector)detailedRecords.get(qryMonth + "-" + typeServiceName);
                
                totalM = (Map)detailedRecords.get(qryMonth + "-" + typeServiceName + "-SUM");
            }
            else
            {
                if (detailedRecords == null)
                {
                    detailedRecords = new HashMap();
                    session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
                }
                
                Object[] tableDetail = getTableDetail(listType);
                
                String[] tableTitle1 = (String[])(tableDetail[1]);
                
                // 湖北特有_0：全部清单；1：零费用清单；2：非零费用清单
                String fee_type = "";
                if (request.getParameter("fee_type") != null)
                {
                    fee_type = request.getParameter("fee_type");
                }
                else
                {
                    fee_type = "0";
                }
                
                dataV = qryCDRList(listType, qryMonth,fee_type, servnumber, tableTitle1.length - 1);
                detailedRecords.put(qryMonth + "-" + typeServiceName, dataV);
                
                // 湖北
                if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        		{
                	this.typeName = typeServiceName;
                	totalM = calDynTotalHUB(dataV);
        		}
                else if (Constants.PROOPERORGID_SD.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
                {
                	this.typeName = typeServiceName;
                	totalM = calDynTotalSD(dataV);
                }
                else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
                {
                	this.typeName = typeServiceName;
                    totalM = calDynTotalNX(dataV);
                }
                detailedRecords.put(qryMonth + "-" + typeServiceName + "-SUM", totalM);
            }
            // 打印数据
            if (dataV != null && dataV.size() > 0)
            {
                int maxSize = iPage * Constants.DEFAULT_PRINT_PAGE_SIZE;
                maxSize = (maxSize <= dataV.size()) ? maxSize : dataV.size();
                
                String cstr = "";
                String[] sAry = null;
                if (iPage == 1)
                {
                	// begin zKF66389 2015-09-10 9月份findbugs修改
//                    createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
                    createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                    // end zKF66389 2015-09-10 9月份findbugs修改
                }
                
                // 湖北
                if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        		{
                	//1：通话清单
    				if ("GSMCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[3] + " "//话单类型
    								+ CommonUtil.formatServnumber(sAry[4]) + " "//对方号码
    								+ CommonUtil.subDateTime(sAry[1], 6) + " "//起始时间
    								+ CommonUtil.fillBlank(sAry[2]) + "  "//时长(秒)
    								+ sAry[11].trim() + " " //话费
    								+ sAry[8]);//话单产生地
    					}
    				} 
    				//2：短信清单
    				else if ("SMSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[3]) + "  "//对方号码
    								+ CommonUtil.subDateTime(sAry[4], 3) + "   "//通话起始时间
    								+ (sAry.length > 6 ? sAry[6].trim() : ""));//短信费
    					}
    				} 
    				//3：梦网清单
    				else if ("IMSGCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[3]+"  "//服务代码
    								+ CommonUtil.fillBlank(sAry[1], 51, 10) + "  "//业务名称
    								+ CommonUtil.subDateTime(sAry[6], 3) + "   "//起始时间
    								+ (sAry.length > 8 ? sAry[8].trim() : "0.00")+"  "//包月费
    								+ sAry[7].trim());//信息费
    					}
    				}
    				//4：GPRS清单
    				else if ("GPRSCDR".equals(typeServiceName) || "G3GPRSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[0] + " " //网络
    								+ CommonUtil.subDateTime(sAry[2], 4) + " "//起始时间
    								+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[4]), 8) + " " //计费上行流量
    								+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[5]), 8) + " " //计费上行流量
    								+ sAry[7] + " "//业务名称
    								+ sAry[9]);//通话归属
    					}
    				}
    				//5：WLAN清单
    				else if ("WLANCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.subDateTime(sAry[3], 3) + "   "//通话起始时间
    								+ CommonUtil.fillBlank(sAry[4]) + "  "//时长
    								+ sAry[1] + "   "//通话地
    								+ (sAry.length > 5 ? sAry[5].trim() : ""));//通话费
    					}
    				}
    				//6：彩信清单
    				else if ("MMSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[7]) + " "//对方号码
    								+ CommonUtil.subDateTime(sAry[8], 3) + "  "//发送时间
    								+ CommonUtil.fillBlank(sAry[10].trim(), 6) + "  "//通信费
    								+ ((sAry.length > 11 ? sAry[11].trim() : "0.00") + "  "//信息费
    								+ String.valueOf(Float.parseFloat(sAry[10].trim())+Float.parseFloat((sAry.length > 11 ? sAry[11].trim() : "0.00")))));//总费用
    					}
    				}
    				//7：代收信息费清单
    				else if ("INFOFEECDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[4]) + "  "//对方号码
    								+ (sAry.length > 5 ? sAry[5] : "") + "  "//话单地
    								+ CommonUtil.subDateTime(sAry[1], 6) + "   "//通话起始时间
    								+ CommonUtil.fillBlank(sAry[2]) + "  "//时长
    								+ sAry[3].trim());//费用
    					}
    				} 
    				//8：VPMN清单
    				else if ("VPMNCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
//    						createRecord(records, ""
//    								+ CommonUtil.formatServnumber(sAry[2]) + "  "//对方号码
//    								+ CommonUtil.subDateTime(sAry[1], 6) + "  "//通话起始时间
//    								+ CommonUtil.fillBlank(sAry[2]) + "  "//时长
//    								+ sAry[8] + "  " //通话地点
//    								+ sAry[9].trim());//通信费
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[2]) + "  "//对方号码
    								+ CommonUtil.subDateTime(sAry[3], 6) + "  "//通话起始时间
    								+ CommonUtil.fillBlank(sAry[4]) + "  "//时长
    								+ sAry[5] + "  " //通话地点
    								+ sAry[6].trim());//通信费
    					}
    				} 
    				//9：PIM清单
    				else if ("PIMMCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[2]) + " "//业务名称
    								+ CommonUtil.subDateTime(sAry[4], 8) + "   "//话单产生时间
    								+ CommonUtil.fillBlank(sAry[5].trim(), 6)+ "  "//按条
    								+ (sAry.length > 6 ? sAry[6].trim() : ""));//包月费
    					}
    				}           
    				//10：手机动画清单
    			    else if ("FLASHCDRKF1".equals(typeServiceName)) {
	    				for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	    					createRecord(records, ""
	    							+ CommonUtil.subDateTime(sAry[4], 8) + "   "//URL请求时间
	    							+ sAry[2] + "  "//SP业务代码
	    							+ sAry[1]+ "  "//SP企业代码
	    							+ sAry[7]+ "  "//信息费
	    							+ sAry[8]);//包月费
	    				}
    			    }
    				//11：G3清单
    			    else if ("G3GPRSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
									+ sAry[0] + " " //网络
									+ CommonUtil.subDateTime(sAry[2], 4) + " "//起始时间
									+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[4]), 8) + " " //计费上行流量
									+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[5]), 8) + " " //计费上行流量
									+ sAry[7] + " "//业务名称
									+ sAry[9]);//通话归属
    					}
    			    }
    				//12：游戏点卡清单
    				else if ("GAMECDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, "  "
    								+ sAry[1] + "  " //业务类别
    								+ (String)sAry[6].substring(0,11) + "   "//产生时间
    								+ CommonUtil.fillBlank(sAry[7].trim(), 6)+ "  "//按次点数
    								+ CommonUtil.fillBlank(sAry[8].trim(), 6));//包月点数
    					}
    				}
        		}
                //宁夏
                else if (Constants.PROOPERORGID_NX.equalsIgnoreCase((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
                {
                    if ("GSMCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 8) // 通话类型
                                    + CommonUtil.fillLeftBlank(CommonUtil.formatServnumber(sAry[2]), 11) + " " // 对端号码
                                    + CommonUtil.fillLeftBlank(sAry[3].substring(2), 17) + " " // 日期 时间
                                    + CommonUtil.fillLeftBlank(sAry[4].trim(), 6) + " " // 时长
                                    + CommonUtil.fillLeftBlank(sAry[6].trim(), 5)); // 费用
                        }
                    }
                    else if ("IMSGCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
                                    + CommonUtil.fillLeftBlank(sAry[4].substring(2), 17) + " "
                                    + CommonUtil.fillLeftBlank(sAry[5].trim(), 5));
                        }
                    }
                    else if ("GPRSCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillLeftBlank(sAry[2].substring(2), 17) + " "
                                    + CommonUtil.fillLeftBlank(sAry[4], 8) + " "
                                    + CommonUtil.fillLeftBlank(sAry[6], 8) + " " 
                                    + CommonUtil.fillLeftBlank(sAry[7], 5));
                        }
                    }
                }
                // 通版
                else
                {
	                if ("GSMCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
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
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2].trim(), 11) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 13) + " "
	                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 15) + " "
	                                + CommonUtil.fillLeftBlank(sAry[6].trim(), 5));
	                    }
	                }
	                else if ("MMSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + sAry[1].trim() + "  " + CommonUtil.subDateTime(sAry[6], 3) + "   "
	                                + CommonUtil.fillBlank(sAry[2].trim(), 6) + "   "
	                                + CommonUtil.fillBlank(sAry[3].trim(), 6));
	                    }
	                }
	                else if ("GPRSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
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
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
	                                + CommonUtil.fillLeftBlank(sAry[6], 14) + " "
	                                + CommonUtil.fillLeftBlank(sAry[2].trim(), 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 7));
	                    }
	                }
	                else if ("WLANCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3], 14) + " " + CommonUtil.fillLeftBlank(sAry[4], 6)
	                                + " " + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[5]), 6) + " "
	                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[6]), 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[7], 5));
	                    }
	                }
	                else if ("INFOFEECDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + CommonUtil.formatServnumber(sAry[3]) + "  "
	                                + (sAry.length > 4 ? sAry[4] : "") + "  " + CommonUtil.subDateTime(sAry[0], 6) + "   "
	                                + CommonUtil.fillBlank(sAry[1]) + "  " + sAry[2].trim());
	                    }
	                }
	                else if ("VPMNCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + CommonUtil.formatServnumber(sAry[3]) + "  "
	                                + CommonUtil.subDateTime(sAry[0], 6) + "  " + CommonUtil.fillBlank(sAry[1]) + "  "
	                                + sAry[7] + "  " + sAry[8].trim());
	                    }
	                }
	                else if ("LBSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + sAry[1].trim() + "  " + CommonUtil.formatServnumber(sAry[3])
	                                + "  " + CommonUtil.subDateTime(sAry[9], 6) + "  "
	                                + CommonUtil.fillBlank(sAry[11].trim(), 6));
	                    }
	                }
                }
            }
            
            // 计算记录总数
            int recordCount = dataV != null ? dataV.size() : 0;
            int nextPage = iPage + 1;
            int pageCount = (recordCount % Constants.DEFAULT_PRINT_PAGE_SIZE == 0) ? (recordCount / Constants.DEFAULT_PRINT_PAGE_SIZE)
                    : (recordCount / Constants.DEFAULT_PRINT_PAGE_SIZE + 1);
            scripts.append("pageCount:" + pageCount);
            
            // 如果是当前打印清单的最后一页，则加上
            if (iPage == pageCount)
            {
                createLine(records);
                
                // begin zKF66389 2015-09-10 9月份findbugs修改
//                String[] typeTotalColumn = (String[])((Object[])Constants.TYPE_TABLE_DETAIL[iListType])[3];
//                String[] typeTotalColChnName = (String[])((Object[])Constants.TYPE_TABLE_DETAIL[iListType])[2];
                String[] typeTotalColumn = (String[])((Object[])Constants.getTypeTableDetail()[iListType])[3];
                String[] typeTotalColChnName = (String[])((Object[])Constants.getTypeTableDetail()[iListType])[2];
                // end zKF66389 2015-09-10 9月份findbugs修改
                String s = "";
                StringBuffer sbuf = new StringBuffer(s);
                for (int i = 0; i < typeTotalColumn.length; i++)
                {
                    sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
                }
                s = sbuf.toString();
                createRecord(records, s);
                createRecord(records, " ");
            }
            int tempType = SelfSvcCdrType.supportType(iListType);
            
            // int iNextListType = iListType + 1;// 清单数字标志是按从1到9的顺序，加1表示下一清单
            int iNextListType = SelfSvcCdrType.nextFactType(tempType + 1);
            
            String isPrintAll = request.getParameter("isPrintAll");
            // 取下一打印页和打印清单
            if (!"YES".equals(isPrintAll) && nextPage <= pageCount)
            {
                scripts.append(",currentListType:'" + listType + "', page:" + nextPage);
            }
            if ("YES".equals(isPrintAll))
            {
                if (nextPage <= pageCount)
                { // 当前清单未打印完
                    scripts.append(",currentListType:'" + listType + "', page:" + nextPage);
                }
                // begin zKF66389 2015-09-10 9月份findbugs修改
                //else if (nextPage > pageCount && iNextListType <= (Constants.TYPE_SERVICE_NAME_ARRAY.length - 1))
                else if (nextPage > pageCount && iNextListType <= (Constants.getTypeServiceNameArray().length - 1))
                // end zKF66389 2015-09-10 9月份findbugs修改
                { // 当前清单已打印完，并且下一要打印清单存在
                    scripts.append(",currentListType:'" + iNextListType + "', page:1");
                }
            }
            
            // 判断是否打印尾部信息，结束打印
            // begin zKF66389 2015-09-10 9月份findbugs修改
//            if ("YES".equals(isPrintAll) && nextPage > pageCount && iNextListType > (Constants.TYPE_SERVICE_NAME_ARRAY.length - 1))
            if ("YES".equals(isPrintAll) && nextPage > pageCount && iNextListType > (Constants.getTypeServiceNameArray().length - 1))
            // end zKF66389 2015-09-10 9月份findbugs修改
            { // 如果打印全部清单，并且清单为最后一个清单且是最后一页
                scripts.append(",printTail:'YES'");
            }
            if (!"YES".equals(isPrintAll) && nextPage > pageCount)
            { // 如果是打印单个清单，并且已经打印到最后一页
                scripts.append(",printTail:'YES'");
            }
            
            // 判断是否打印清单名称信息
            if ("YES".equals(isPrintAll) && iPage == 1 && recordCount > 0)
            {
            	// begin zKF66389 2015-09-10 9月份findbugs修改
//                scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
                scripts.append(",printTypeName:'" + Constants.getTypeNameArray()[iListType] + "'");
                // end zKF66389 2015-09-10 9月份findbugs修改
            }
            
            // 返回打印进度提示
            if (iPage <= pageCount)
            {
                int startRecord = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE;
                int endRecord = iPage * Constants.DEFAULT_PRINT_PAGE_SIZE;
                endRecord = endRecord <= recordCount ? endRecord : recordCount;
                // begin zKF66389 2015-09-10 9月份findbugs修改
//                String s = "正在打印【" + Constants.TYPE_NAME_ARRAY[iListType] + "】第" + startRecord + "～" + endRecord + "间的记录,请稍候...";
                String s = "正在打印【" + Constants.getTypeNameArray()[iListType] + "】第" + startRecord + "～" + endRecord + "间的记录,请稍候...";
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                scripts.append(",process:'" + s + "'");
            }
            
            scripts.append("}");
            
            String strRecords = "var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records.substring(0, records.toString().length() - 1);
            }
            strRecords += "]";
            
            request.setAttribute("printData", scripts.toString() + ";" + strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "success";
        }
        catch (Exception e)
        {
            logger.error("组装清单打印数据失败：", e);
            
            this.getRequest().setAttribute("errormessage", "组装打印数据出错，详单打印失败");
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "failed";
        }
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
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        String servNumber = customerSimp.getServNumber();
        
        //DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, month);
        // yKF28472
        DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, month, this.listtype);
        if (timesPO == null)
        {
            detailedRecordsService.addPrintTimes(servNumber, month, 1, this.listtype);
        }
        else
        {
            detailedRecordsService.updatePrintTimes(servNumber, month, timesPO.getTimes() + 1, this.listtype);
        }
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953 
        
        return "success";
        
    }
    
    private void createRecord(StringBuffer sb, String text)
    {
        sb.append("'" + text + "',");
    }
    
    private void createLine(StringBuffer sb)
    {
        sb.append("' ---------------------------------------------',");
    }
    
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
//        	 begin zKF66389 2015-09-10 9月份findbugs修改
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
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
//            typeTableDetail = (Object[])Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[])Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9月份findbugs修改
            
        }
        else
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
//            typeTableDetail = (Object[])Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[])Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        
        return typeTableDetail;
    }
    
    private Vector qryCDRList(String type, String qryMonth, String fee_type, String servnumber, int colNum)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCDRList Starting ...");
        }
        
        /**
         * cdrType：调用接口时传递的详单类型 1：通话；2：短信；3：梦网；4：GPRS；5：WLAN type：页面传递的详单类型 0/1：通话；2：短信；4：梦网；5：GPRS；6：WLAN
         */
        String cdrType = "";
        if ("0".equals(type) || "1".equals(type))
        {
            cdrType = "1";
        }
        else
        {
        	cdrType = type;
        }
//        else if ("2".equals(type))
//        {
//            cdrType = "2";
//        }
//        else if ("4".equals(type))
//        {
//            cdrType = "3";
//        }
//        else if ("5".equals(type))
//        {
//            cdrType = "4";
//        }
//        else if ("6".equals(type))
//        {
//            cdrType = "5";
//        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCDRList End");
        }
        
        return detailedRecordsBean.queryCDRList(customerSimp, terminalInfo, qryMonth, fee_type,cdrType, curMenuId, colNum);
    }
    
    /**
     * 山东统计
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalSD(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map totalM = new HashMap();
        // 计算清单的费用，流量等 合计
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            totalM = totalGsmCdr(v, totalM);
            
        }
        else if ("SMSCDR".equals(typeName))
        {
            totalM = totalSmsCdr(v, totalM);
        }
        else if ("IMSGCDR".equals(typeName))
        {
            totalM = totalImsgCdr(v, totalM);
        }
        
        else if ("WLANCDR".equals(typeName))
        {
            totalM = totalWlanCdr(v, totalM);
        }
        else if ("GPRSCDR".equals(typeName))
        {
            totalM = totalGprsCdr(v, totalM);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD End");
        }
        
        return totalM;
    }

    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
    /**
     * GPRSCDR 统计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map totalGprsCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalGprs1 = "0";
        String totalGprs2 = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
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
     * WLANCDR 统计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map totalWlanCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
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
     * IMSGCDR 统计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map totalImsgCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalFee1 = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[2]);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[3]);
            }
        }
        totalM.put("totalfee1", totalFee1);
        
        return totalM;
    }

    /**
     * SMSCDR 统计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map totalSmsCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalSmsFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
            }
        }
        totalM.put("totalfee", totalSmsFee);
        
        return totalM;
    }

    /**
     * GSMCDR 统计
     * <功能详细描述>
     * @param v
     * @param totalM
     * @see [类、类#方法、类#成员]
     */
    private Map totalGsmCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalGsmFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[9]);
                totalTimes = CurrencyUtil.add(totalTimes, sAry[5], 0);
            }
        }
        totalM.put("totalfee", totalGsmFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
    }
    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
    
    /**
     * 重庆统计
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalCQ(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalCQ Starting ...");
        }
        
        Map totalM = new HashMap();
        // 计算清单的费用，流量等 合计
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName)||"SGSMCDR".equals(typeName)
        		||"LGSMCDR".equals(typeName)||"RGSMCDR".equals(typeName))
        {
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGsmFee = CurrencyUtil.add(totalGsmFee, (sAry.length > 5 ? sAry[5] : "0"));
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            
        }
        else if ("SMSCDR".equals(typeName))
        {
            String totalSmsFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 5 ? sAry[5] : "0"));
	            }
            }
            totalM.put("totalfee", totalSmsFee);
        }
        else if ("GPRSCDR".equals(typeName))
        {
        	String totalGprsFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGprsFee = CurrencyUtil.add(totalGprsFee, (sAry.length > 4 ? sAry[4] : "0"));
	            }
            }
            totalM.put("totalfee", totalGprsFee);
        }
        else if ("MMSCDR".equals(typeName))
        {
        	String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, (sAry.length > 5 ? sAry[5] : "0"));
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 6 ? sAry[6] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
        }
        else if ("MMRCDR".equals(typeName))
        {
        	String totalMmrFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalMmrFee = CurrencyUtil.add(totalMmrFee, (sAry.length > 4 ? sAry[4] : "0"));
	            }
            }
            totalM.put("totalfee", totalMmrFee);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalCQ End");
        }
        
        return totalM;
    }
    
    /**
     * 湖北统计
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalHUB(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map totalM = new HashMap();
        // 计算清单的费用，流量等 合计
        String cstr = null;
        String[] sAry = null;
        // 通话(cdrtype=1)
        if ("GSMCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[11]);
					totalTimes = CurrencyUtil.add(totalTimes, sAry[2], 0);
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
            
        }
        // 短信(cdrtype=2)
        else if ("SMSCDR".equals(typeName))
        {
            String totalSmsFee = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
	            }
            }
            totalM.put("totalfee", totalSmsFee);
        }
        // 梦网(cdrtype=3)
        else if ("IMSGCDR".equals(typeName))
        {
            String totalFee1 = "0";
            String totalFee2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 8 ? sAry[8] : "0"));
	            }
            }
            totalM.put("totalfee1", totalFee1);
            totalM.put("totalfee2", totalFee2);
        }
        // GRPS详单(cdrtype=4)
        else if ("GPRSCDR".equals(typeName))
        {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGprs1 = CurrencyUtil.addInt(totalGprs1, CurrencyUtil.calByteToK(sAry[4])+"");
					totalGprs2 = CurrencyUtil.addInt(totalGprs2, CurrencyUtil.calByteToK(sAry[5])+"");
	            }
            }
            totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
            totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
        }
        // WLAN详单(cdrtype=5)
        else if ("WLANCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalFee = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee = CurrencyUtil.add(totalFee,(sAry.length > 5 ? sAry[5] : "0"));
					totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
	            }
            }
            totalM.put("totalfee", totalFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        }
        // 彩信(cdrtype=6)
        else if ("MMSCDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[10]);
					//totalFee2 = CurrencyUtil.add(totalFee2, CurrencyUtil.add(sAry[8],(sAry.length > 9 ? sAry[9] : "0")));
					totalFee2 = CurrencyUtil.add(totalFee2, sAry[11]);
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // 代收信息费(cdrtype=7)
        else if ("INFOFEECDR".equals(typeName)) {
			String totalTimes = "0";
			String totalFee = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee = CurrencyUtil.add(totalFee, sAry[3]);
					totalTimes = CurrencyUtil.add(totalTimes, sAry[2], 0);
				}
			}
			totalM.put("totalfee", totalFee);
			totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
		}
        // VPMN清单(cdrtype=8)
        else if ("VPMNCDR".equals(typeName)) {
			String totalFee = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);  
	                //totalFee = CurrencyUtil.add(totalFee, sAry[9]);
	                totalFee = CurrencyUtil.add(totalFee, sAry[6]);
				}
			}
			totalM.put("totalfee", totalFee);
		}
        // PIM清单查询(cdrtype=9)
        else if ("PIMMCDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[5]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 6 ? sAry[6] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // 手机动画清单(cdrtype=10)
		else if ("FLASHCDRKF1".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 9 ? sAry[8] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // G3清单(cdrtype=11)
		else if ("G3GPRSCDR".equals(typeName)) {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGprs1 = CurrencyUtil.addInt(totalGprs1, CurrencyUtil.calByteToK(sAry[4])+"");
					totalGprs2 = CurrencyUtil.addInt(totalGprs2, CurrencyUtil.calByteToK(sAry[5])+"");
	            }
            }
            totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
            totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
		}
        // 游戏点卡清单(cdrtype=12)
		else if ("GAMECDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, sAry[8]);
				}
			}
			totalM.put("totalgame1", totalFee1);
			totalM.put("totalgame2", totalFee2);
		}
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD End");
        }
        
        return totalM;
    }
    
    /**
     * 宁夏统计
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalNX(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalNX Starting ...");
        }
        
        Map totalM = new HashMap();
        // 计算清单的费用，流量等 合计
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[6]);
	                totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
            
        }
        else if ("IMSGCDR".equals(typeName))
        {
            String totalFee1 = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalFee1 = CurrencyUtil.add(totalFee1, sAry[5]);
	            }
            }
            totalM.put("totalfee1", totalFee1);
        }        
        else if ("GPRSCDR".equals(typeName))
        {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            String totalfee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGprs1 = CurrencyUtil.add(totalGprs1, sAry[4]);                
	                totalGprs2 = CurrencyUtil.add(totalGprs2, sAry[6]);
	                totalfee = CurrencyUtil.add(totalfee, sAry[7]);
	            }
            }
            totalM.put("totalgprs1", totalGprs1);
            totalM.put("totalgprs2", totalGprs2);
            totalM.put("totalfee", totalfee);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalNX End");
        }
        
        return totalM;
    }
    
    /**
     * 从缓存的Map中获取查询单页数据
     * 
     * @param request
     * @param dataM
     * @param listType
     * @return
     */
    private Vector getQueryPageData(Vector dataV)
    {
        Vector result = new Vector();
        if (dataV != null && dataV.size() > 0)
        {
            if (page == null || page.trim().length() < 1)
            {
                page = "1";
            }
            int iPage = Integer.parseInt(page);
            int maxSize = iPage * pageSize;
            maxSize = maxSize <= dataV.size() ? maxSize : dataV.size();
            for (int i = (iPage - 1) * pageSize; i < maxSize; i++)
            {
                result.add(dataV.get(i));
            }
        }
        
        return result;
    }
    
    /**
     * 查询是否开通139邮箱
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
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
        
        if (result != null && result.size() > 0)
        {
            CTagSet tagSet = (CTagSet)result.get("returnObj");
            
            // 0：未开通 1：已开通
            haveMailbox = tagSet.GetValue("havemailbox");
            
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 begin
            if(StringUtils.isBlank(haveMailbox))
            {
                haveMailbox = tagSet.GetValue("ISHAVEMAILBOX");
            }
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 end
            
            // 记录成功日志
            this.createRecLog("qryMailbox", "0", "0", "0", "查询用户是否已开通手机邮箱成功!");
            
            if (haveMailbox != null && haveMailbox.equals("1"))
            {
                
                out.write("1");
                out.flush();
                
                sendMail("0");
            }
            
            if (haveMailbox != null && haveMailbox.equals("0"))
            {
                out.write("0");
                out.flush();
            }
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "查询用户是否已开通手机邮箱失败!");
            
            // 记录错误日志
            this.createRecLog("qryMailbox", "0", "0", "1", "查询用户是否已开通手机邮箱失败!");
            
            out.write("2");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox End!");
        }
    }
    
    /**
     * 发送邮件
     * 
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void sendMail(String delayTime) throws IOException
    {
        logger.debug("sendMail Starting ...");
        
        StringBuffer mailContent = new StringBuffer(1024);
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        HttpSession session= request.getSession();
                
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 手机号
        String servnumber = customerSimp.getServNumber();
        
        // 清单名称 "GSMCDR"
        typeName = getTypeServiceName(listtype);        
        
        String feeType = "";
        
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        if (CommonUtil.isNotEmpty(fee_type))
		{
        	feeType = "-" + fee_type;
		}
        // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 从session中取详单记录
        Vector records = null;        
        Map totalMap = null;
        
        Map detailedRecords = (Map) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        if (null != detailedRecords && null != detailedRecords.get(month + "-" + typeName + feeType))
        {
            records = (Vector)detailedRecords.get(month + "-" + typeName + feeType);
            
            totalMap = (Map)detailedRecords.get(month + "-" + typeName + feeType + "-SUM");            
        }
        
        if (null == records || 0 == records.size())
        {
            logger.debug("sendMail End");
        	return;
        }        
        
        // 获得清单表头信息 [[【通话详单】], [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)], [详单条数：, 时长合计：, 费用合计(元)：], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(listtype);
        
        // 清单名称中文 "[【通话详单】]"
        tableHeader = ((String[])(tableDetail[0]))[0];
        
        // 表头中文 [序号, 对方号码, 话单类型, 话单产生地, 通话开始时间, 通话时长(秒), 基本费(元), 长途费(元), 声讯信息费(元), 总话费(元)]
        tableTitle = (String[])(tableDetail[1]);
        
        // 拼装邮件内容:表头
    	mailContent.append("<html><head><style type='text/css'>")
    			.append(".tb_blue02,.tb_blue02 td ,.tb_blue02 th,.tb_num{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; color:#fff; line-height:30px; height:30px; text-align:center;background:#056e99;}")
    			.append(".tit_info{ line-height:30px; height:30px; padding-left:40px; font-size:22px; position:relative;}")
    			.append("</style>" + "</head>" + "<body>" + "<p class='tit_info' align='left'><span class='bg'></span>")
    			.append(month).append("月").append(tableHeader)
    			.append("</p><table class='tb_blue02' align='center' width='100%'><tr class='tr_color'>");
        		
        for (int i = 0; i < tableTitle.length; i++)
        {
            mailContent.append("<th class='tr_color' align='center'>").append(tableTitle[i]).append("<br/></th>");
        }
        
        mailContent.append("</tr>");
        
        // 合计中文 [详单条数：, 时长合计：, 费用合计(元)：]
        tableTail = (String[])(tableDetail[2]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[])tableDetail[4];
        }
        
        // 拼装邮件内容：查询结果
        for (int i = 0; i < records.size(); i++)
        {
            String recordStr = (String)records.get(i);
            
            String[] recordArray = recordStr.split(",");
            
            mailContent.append("<tr>");
            
            for (int j = 0; j < recordArray.length; j++)
            {
                mailContent.append("<td align='center'>").append(recordArray[j]).append("</td>");
            }
            
            mailContent.append("</tr>");
        }        
        
        // 拼装邮件内容：详单条数：, 时长合计：, 费用合计(元)：
        mailContent.append("<tr><td colspan='").append(tableTitle.length).append("' align='left'>");
        
        // 合计英文 [recordcount, totaltimes, totalfee]
        String[] summaryTitle = (String[])tableDetail[3];       
        for (int i = 0; i < summaryTitle.length; i++)
        {
            mailContent.append("<strong>").append(tableTail[i]).append("</strong>").append(totalMap.get(summaryTitle[i])).append("     ");
        }
        
        // 拼装邮件内容：补充性说明
        if (tableDescription != null && tableDescription.length > 0)
        {
            mailContent.append("<tr><td colspan='").append(tableTitle.length).append("' align='left'>");
            
            for (int i = 0; i < tableDescription.length; i++)
            {
                mailContent.append(tableDescription[i]).append("<br/>");
            }
            
            mailContent.append("</td></tr>");
        }
        
        mailContent.append("</table></body></html>");
        
        // 收件人邮箱
        String to = servnumber + "@139.com";
        
        // 邮件标题
        String subject = "您" + month + "月份的详单已到，请查收。";
        
        // 延迟时间,单位：分钟
        int delay = Integer.parseInt(delayTime);
        
        // 发送邮件
        Timer timer = new Timer();
        timer.schedule(new SendMailUtil(to, subject, mailContent.toString()), new Date(
                System.currentTimeMillis() + delay * 60L * 1000));
        
        logger.debug("sendMail End");

    }
    
    /**
     * 详单寄送邮箱开通
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void add139Mail() throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        PrintWriter out = this.getResponse().getWriter();
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用接口开通139邮箱
        Map result = detailedRecordsBean.add139Mail(customer, terminalInfoPO, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // 记录成功日志
            this.createRecLog(Constants.ADD_BILLMAIL, "0", "0", "0", "139邮箱开通成功");
            
            // 延迟时间（分钟）
            String delayTime = (String) PublicCache.getInstance().getCachedData("SH_MAIL_DELAY");
            if (null == delayTime || "".equals(delayTime.trim()))
            {
            	delayTime = "3";
            }
            
            out.write("1");
            out.flush();
            
            // 139邮箱开通成功，发送邮件
            sendMail(delayTime);
        }
        else
        {
            // 记录错误日志
            this.createRecLog(Constants.ADD_BILLMAIL, "0", "0", "1", "139邮箱开通失败");
            
            out.write("0");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail End");
        }
    }
    
    /**
     * 提示信息改造
     * 
     * @param strMonth 查询月份
     * @param strCDRType 详单类型
     * @param strFeeType 费用类型
     * @return
     * @see 
     * @remark: create g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
     */
    private String[] formatParams(String strMonth, String strCDRType, String strFeeType)
    {
        String[] params = new String[]{"", "", "", ""};
        
        if (strMonth != null && strMonth.length() == 6)
        {
            params[0] = strMonth.substring(0, 4);
            params[1] = strMonth.substring(4);
        }
        
        if ("1".equals(strFeeType))
        {
            params[2] = "非零费用";
        }
        else if ("2".equals(strFeeType))
        {
            params[2] = "费用为零";
        }
        
        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        params[3] = getCdrTypeName(strCDRType);
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        
        return params;
    }

    /**
     * 类型名称
     * <功能详细描述>
     * @param strCDRType
     * @param params
     * @see [类、类#方法、类#成员]
     */
    private String getCdrTypeName(String strCDRType)
    {
        String cdrTypeName = "";
        if ("GSMCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "通话详单";
        }
        else if ("SMSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "短信详单";
        }
        else if ("IMSGCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "移动梦网详单";
        }
        else if ("GPRSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "GPRS详单";
        }
        else if ("WLANCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "WLAN详单";
        }
        else if ("MMSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "彩信详单";
        }
        else if ("INFOFEECDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "代收信息费详单";
        }
        else if ("VPMNCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "VPMN详单";
        }
        else if ("PIMMCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "PIM详单";
        }
        else if ("FLASHCDRKF1".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "手机动画详单";
        }
        else if ("G3GPRSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "G3详单";
        }
        else if ("GAMECDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "游戏点卡详单";
        }
        return cdrTypeName;
    }
    
    public DetailedRecordsService getDetailedRecordsService()
    {
        return detailedRecordsService;
    }
    
    public void setDetailedRecordsService(DetailedRecordsService detailedRecordsService)
    {
        this.detailedRecordsService = detailedRecordsService;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
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

	public String getTypeName()
    {
        return typeName;
    }
    
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
    
    public Vector getResultRecords()
    {
        return resultRecords;
    }
    
    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
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
        return tableTitle.clone();
    }
    
    public void setTableTitle(String[] tableTitle)
    {
        this.tableTitle = tableTitle.clone();
    }
    
    public String[] getTableTail()
    {
        return tableTail.clone();
    }
    
    public void setTableTail(String[] tableTail)
    {
        this.tableTail = tableTail.clone();
    }
    
    public String[] getTableDescription()
    {
        if (tableDescription == null)
        {
            return tableDescription;
        }
        else
        {
            return tableDescription.clone();
        }
        
    }
    
    public void setTableDescription(String[] tableDescription)
    {
        this.tableDescription = tableDescription.clone();
    }
    
    public String getListtype()
    {
        return listtype;
    }
    
    public void setListtype(String listtype)
    {
        this.listtype = listtype;
    }
    
    public String getPage()
    {
        return page;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
    public String getIsQueryAll()
    {
        return isQueryAll;
    }
    
    public void setIsQueryAll(String isQueryAll)
    {
        this.isQueryAll = isQueryAll;
    }
    
    public String getPagecount()
    {
        return pagecount;
    }
    
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }

    public String getFee_type()
    {
        return fee_type;
    }

    public void setFee_type(String fee_type)
    {
        this.fee_type = fee_type;
    }
    
    // add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    public String getHaveMailbox()
    {
        return haveMailbox;
    }
    
    public void setHaveMailbox(String haveMailbox)
    {
        this.haveMailbox = haveMailbox;
    }
    // add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652

    public String getMenuURL()
    {
        return menuURL;
    }

    public void setMenuURL(String menuURL)
    {
        this.menuURL = menuURL;
    }

    public int getPageSize()
    {
        return pageSize;
    }
}
