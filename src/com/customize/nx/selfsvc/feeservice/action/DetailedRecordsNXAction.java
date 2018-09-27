/*
* @filename: DetailedRecordsNXAction.java
* @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
* @brif:  NG3.5帐详单改造之详单查询
* @author: 高群/g00140516
* @de:  2012/03/13 
* @description: 
* @remark: create g00140516 2012/03/13 R003C12L03n01 OR_NX_201203_128
*/
package com.customize.nx.selfsvc.feeservice.action;

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
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.feeservice.model.CycleInfoPO;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * NG3.5帐详单改造之详单查询
 * 
 * @author  g00140516
 * @version  1.0, 2012/03/13
 * @see  
 * @since  
 */
public class DetailedRecordsNXAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(DetailedRecordsNXAction.class);
    
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
     * 详单类型中文名称
     */
    private String cdrTypeName = "";
    
    /**
     * 详单记录字段
     */
    private String recordFields = "";
    
    /**
     * 合计信息
     */
    private Map<String, String> billTotal = null;
    
    /**
     * 套餐与固定费清单记录
     */
    private List<String> fixfeeRecords = null;
    
    /**
     * 套餐与固定费之外的其它类型的详单记录
     */
    private Vector resultRecords = null;
    
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
     * 账期列表
     */
    private List<CycleInfoPO> cycles = null;
    
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
    
    // delete begin g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
    // delete end g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次

    private transient DetailedRecordsService detailedRecordsService = null;
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
    private String additionalInfo = "";
    
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
        
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (customerSimp != null)
        {
            // 删除session中保存的用户的详单数据。checkQueryAuth函数中也有这一步，此处再删除一下的原因是：其它省份不会校验查询权限，会直接进入此函数
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }        

        // 可查询当前月和前五个月的详单信息
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM, 6);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth End");
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
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        request.setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息查询，成功时应返回一个CTagSet，一个CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, month, "1", curMenuId);
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
        
        HttpServletRequest request = this.getRequest();
        
        // 获取session
        HttpSession session = request.getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 取菜单的温馨提示信息
        List totalMenus = (List)PublicCache.getInstance().getCachedData(terminalInfoPO.getTermgrpid());
        
        MenuInfoPO menuInfoPO = null;
        for (int i = 0; i < totalMenus.size(); i++)
        {
            menuInfoPO = (MenuInfoPO)totalMenus.get(i);
            if (this.curMenuId.equals(menuInfoPO.getMenuid()))
            {
                break;
            }
            else
            {
                menuInfoPO = null;
            }
        }
        
        if (menuInfoPO != null)
        {
            additionalInfo = menuInfoPO.getAdditionalInfo();
        }
        
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType End");
        }
        
        return forward;
    }
    
    /**
     * 老版查询详单记录
     * 
     * @return
     * @see 
     */
    public String qryDetailedRecordsOld()
    {
        logger.debug("qryDetailedRecordsOld Starting ...");
        
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
        
        /**
         * 拼装内容 type：0--GSMCDR header：【通话详单】 title：序号 对方号码 话单类型 话单产生地 通话开始时间 通话时长(秒) 基本费(元) 长途费(元) 声讯信息费(元) 总话费(元)
         * tail：详单条数： 时长合计： 费用合计(元)：
         */
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
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // 先从session中取详单记录，如果session中有，直接返回；如果session中没有，调用接口查询
        // session中保存的详单数据，属性名称：Constants.LIST_DATA_SESSION_NAME + servNumber，属性值：map
        // map中的数据，key：201012-GSMCDR，value：Vector
        // Vector中保存记录，每条记录是一个String，多个字段之间以","隔开，如
        resultRecords = null;
        
        Map detailedRecords = (Map) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);

        if (detailedRecords != null && detailedRecords.get(cycle + "-" + typeName) != null)
        {
            resultRecords = (Vector)detailedRecords.get(cycle + "-" + typeName);
            
            Map totalMap = (Map)detailedRecords.get(cycle + "-" + typeName + "-SUM");
            
            request.setAttribute("totalMap", totalMap);
        }
        else
        {
            if (detailedRecords == null)
            {
                detailedRecords = new HashMap();
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            resultRecords = detailedRecordsBean.queryCDRListNXOld(customerSimp,
                    terminalInfo,
                    "",
                    cdrType,                   
                    curMenuId,
                    tableTitle.length - 1,
                    startDate,
                    endDate);          
        }
        
        //未查询到详单记录
        if (resultRecords == null || resultRecords.size() == 0)
        {
            request.setAttribute("errormessage", "月详单(" + cycle + "-" + typeName + ")查询结束，未找到符合条件的记录");
            
            request.setAttribute("recordCount", "0");
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "月详单(" + cycle + "-" + typeName + ")查询结束，未找到符合条件的记录");

            return "success";
        }
        
        detailedRecords.put(cycle + "-" + typeName, resultRecords);
        
        Map totalMap = calDynTotalNX(resultRecords);
        
        detailedRecords.put(cycle + "-" + typeName + "-SUM", totalMap);
        
        request.setAttribute("totalMap", totalMap);
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "月详单(" + cycle + "-" + typeName + ")查询成功");
        
        // delete begin g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
        // delete end g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
        
        logger.debug("qryDetailedRecordsOld End");
        
        return "success";
    }
    
    /**
     * 新版获取详单记录
     * 
     * @return
     * @see 
     */
    public String qryDetailedRecords()
    {        
        logger.debug("qryDetailedRecords Starting ...");
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        Object[] cdrTypeInfo = Constants.CDRTYPEMAP.get(cdrType);
        
        // 根据详单类型未获取到对应的信息
        if (null == cdrTypeInfo || 2 > cdrTypeInfo.length)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "系统不支持详单类型" + cdrType);
            
            logger.error("系统不支持详单类型" + cdrType);
            
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            request.setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            request.setAttribute("errormessage", "系统不支持详单类型" + cdrType + "，请联系营业人员解决！");
            
            return "failed";
        }
        
        cdrTypeName = (String) cdrTypeInfo[0];
        
        recordFields = (String) cdrTypeInfo[1];        
        
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
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();        
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        /*
         * 某种类型的详单记录信息
         */
        Map<String, String> resultMap = null;
        
        /*
         * session中保存的用户详单记录的数据结构：
         * 使用Map存放用户的全部详单记录，键值为(cycle + "-" + cdrType)，对应的详单记录为resultMap
         */
        Map<String, Map<String, String>> detailedRecords = (Map<String, Map<String, String>>) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        if (null != detailedRecords)
        {
            resultMap = detailedRecords.get(cycle + "-" + cdrType);
        }
        else
        {
            detailedRecords = new HashMap<String, Map<String, String>>();
            
            session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
        }
        
        // 从session中未取到详单记录，则调用后台接口获取，同时存放到session中
        if (null == resultMap || 0 == resultMap.size())
        {
        	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
        	resultMap = detailedRecordsBean.queryDetailedRecordsNX2012(customerSimp, terminalInfo, cdrType, startDate, endDate, curMenuId, cycle);
        	// modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
        	
            detailedRecords.put(cycle + "-" + cdrType, resultMap);
        }
        
        if (null != resultMap)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "月详单(" + cycle + "-" + cdrType + ")查询成功");
            
            if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
            {
                // 套餐与固定费清单
                parseFixfeeRecords(resultMap);
            }
            else
            {
                // 非套餐与固定费清单
                parseSingleTypeRecords(resultMap);
            }
            
            // delete begin g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
            // delete end g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "月详单(" + cycle + "-" + cdrType + ")查询结束，未找到符合条件的记录");
        }
        
        logger.debug("qryDetailedRecords End");
        
        return cdrType;
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
        
        for (int i = 1; i < recordsCount; i++)
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
     * 非套餐及固定费详单
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
        billTotal.put("totalFee", resultMap.get("totalfee"));
        
        // 短信总条数
        billTotal.put("smsTotalNum", resultMap.get("smstotalnum"));
        
        // 彩信总条数
        billTotal.put("mmsTotalNum", resultMap.get("mmstotalnum"));
        
        // 本地通话费
        billTotal.put("localcall", resultMap.get("localcall"));
                
        // 长途通话费
        billTotal.put("longcall", resultMap.get("longcall"));   
        
        // 漫游通话费
        billTotal.put("roamcall", resultMap.get("roamcall"));
        
        // 市话费
        billTotal.put("citycall", resultMap.get("citycall"));
        
        // 其他业务费
        billTotal.put("othercall", resultMap.get("othercall"));
        
        // 国内长途
        billTotal.put("inlancall", resultMap.get("inlancall"));
        
        // 港澳台长途
        billTotal.put("colonycall", resultMap.get("colonycall"));
        
        // 国际长途
        billTotal.put("interncall", resultMap.get("interncall"));
        
        // 国内漫游
        billTotal.put("inlanroam", resultMap.get("inlanroam"));
        
        // 港澳台漫游
        billTotal.put("colonyroam", resultMap.get("colonyroam"));
        
        // 国际漫游
        billTotal.put("internroam", resultMap.get("internroam"));
        
        // GPRS话单条数
        billTotal.put("gprssum", resultMap.get("gprssum"));
        
        // 收费流量
        billTotal.put("chargeflux", resultMap.get("chargeflux"));
        
        // 免费流量
        billTotal.put("freechargeflux", resultMap.get("freechargeflux"));
        
        // 总流量
        billTotal.put("sumflux", resultMap.get("sumflux"));
        
        // wlan总时长
        billTotal.put("wlansumtime", resultMap.get("wlansumtime"));
        
        // wlan总费用
        billTotal.put("wlansumfee", resultMap.get("wlansumfee"));
        
        // wlan话单条数
        billTotal.put("wlansum", resultMap.get("wlansum"));
        
        // wlan总流量
        billTotal.put("wlansumflux", resultMap.get("wlansumflux"));
                
        // gprs总费用
        billTotal.put("gprssumfee", resultMap.get("gprssumfee"));
        
        // CMWAP条数
        billTotal.put("cmwapsum", resultMap.get("cmwapsum"));
                
        // CMNET条数
        billTotal.put("cmnetsum", resultMap.get("cmnetsum"));
        
        // CMWAP收费流量
        billTotal.put("cmwapflux", resultMap.get("cmwapflux"));
                
        // CMNET收费流量
        billTotal.put("cmnetflux", resultMap.get("cmnetflux"));
                
        // CMWAP免费流量
        billTotal.put("cmwapfreeflux", resultMap.get("cmwapfreeflux"));
        
        // CMNET免费流量
        billTotal.put("cmnetfreeflux", resultMap.get("cmnetfreeflux"));
        
        // CMWAP总流量 
        billTotal.put("cmwapsumflux", resultMap.get("cmwapsumflux"));
        
        // CMNET总流量
        billTotal.put("cmnetsumflux", resultMap.get("cmnetsumflux"));
        
        // 公众WLAN条数
        billTotal.put("pubwlansum", resultMap.get("pubwlansum"));
     
        // 校园WLAN条数
        billTotal.put("campuswlansum", resultMap.get("campuswlansum"));
     
        // 公众WLAN流量
        billTotal.put("pubwlanflux", resultMap.get("pubwlanflux"));
        
        // 校园WLAN流量
        billTotal.put("campuswlanflux", resultMap.get("campuswlanflux"));
        
        // 公众WLAN时长
        billTotal.put("pubwlantime", resultMap.get("pubwlantime"));
     
        // 校园WLAN时长
        billTotal.put("campuswlantime", resultMap.get("campuswlantime"));
     
        // 公众WLAN费用
        billTotal.put("pubwlanfee", resultMap.get("pubwlanfee"));
     
        // 校园WLAN费用
        billTotal.put("campuswlanfee", resultMap.get("campuswlanfee"));
     
        // CMWAP费用
        billTotal.put("cmwapfee", resultMap.get("cmwapfee"));
     
        // CMNET费用
        billTotal.put("cmnetfee", resultMap.get("cmnetfee"));
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 1; i < recordsCount; i++)
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
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
            typeServiceName = Constants.getTypeServiceNameArray()[1];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        else
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[Integer.parseInt(type)];
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
//            typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        else
        {
        	// begin zKF66389 2015-09-10 9月份findbugs修改
//            typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9月份findbugs修改
        }
        
        return typeTableDetail;
    }
    
    /**
     * 老版的统计信息
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
     * 获取老版打印数据
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
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            int iListType = Integer.parseInt(cdrType);
            
            String typeServiceName = getTypeServiceName(cdrType);
            
            // 取得清单数据
            Vector dataV = null;
            Map totalM = null;
            
            Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            if (detailedRecords != null && detailedRecords.get(cycle + "-" + typeServiceName) != null)
            {
                dataV = (Vector)detailedRecords.get(cycle + "-" + typeServiceName);
                
                totalM = (Map)detailedRecords.get(cycle + "-" + typeServiceName + "-SUM");
            }
            else
            {
                if (detailedRecords == null)
                {
                    detailedRecords = new HashMap();
                    session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
                }
                
                dataV = detailedRecordsBean.queryCDRListNXOld(customerSimp,
                        terminalInfo,
                        "",
                        cdrType,                   
                        curMenuId,
                        tableTitle.length - 1,
                        startDate,
                        endDate);
                
                totalM = calDynTotalNX(dataV);
                
                detailedRecords.put(cycle + "-" + typeServiceName, dataV); 
                detailedRecords.put(cycle + "-" + typeServiceName + "-SUM", totalM);
            }
            
            // 打印数据
            //if (dataV != null && dataV.size() > 0)
            if (isNotEmpty(dataV))
            {
            	// begin zKF66389 2015-09-10 9月份findbugs修改
//                createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
                createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                String cstr = "";
                String[] sAry = null;
                
                int maxSize = dataV.size();
                
                if ("GSMCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
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
                    for (int i = 0; i < maxSize; i++)
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
                    for (int i = 0; i < maxSize; i++)
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
            
            createLine(records);
            
            // begin zKF66389 2015-09-10 9月份findbugs修改
//            String[] typeTotalColumn = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[3];
//            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[2];
            String[] typeTotalColumn = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[3];
            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[2];
            // end zKF66389 2015-09-10 9月份findbugs修改
            String s = "";
            StringBuffer sbuf  = new StringBuffer();
            for (int i = 0; i < typeTotalColumn.length; i++)
            {
                sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
            }
            s = sbuf.toString();
            createRecord(records, s);
            createRecord(records, " ");
            
            scripts.append("printTail:'YES'");
            
            // begin zKF66389 2015-09-10 9月份findbugs修改
//            scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
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
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();           
            
            StringBuffer records = new StringBuffer(1024);
           
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            /*
             * 详单记录信息
             */
            Map<String, String> resultMap = null;
            
            Map<String, Map<String, String>> detailedRecords = (Map<String, Map<String, String>>) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            if (null != detailedRecords)
            {
                resultMap = detailedRecords.get(cycle + "-" + cdrType);
            }
            else
            {
                detailedRecords = new HashMap<String, Map<String, String>>();
                
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            // 从session中未取到详单记录，则调用后台接口获取，同时存放到session中
            if (null == resultMap || 0 == resultMap.size())
            {
            	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
            	resultMap = detailedRecordsBean.queryDetailedRecordsNX2012(customerSimp, terminalInfo, cdrType, startDate, endDate, curMenuId, cycle);
            	// modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
            	
                detailedRecords.put(cycle + "-" + cdrType, resultMap);
            }            
            
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
                        records.append("'扣费日期 使用周期 套餐及固定费名称 费用(元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
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
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    
                    // 本地通话费
                    String localcall = resultMap.get("localcall");
                    
                    // 长途通话费
                    String longcall = resultMap.get("longcall");
                    
                    // 漫游通话费
                    String roamcall = resultMap.get("roamcall");
                    
                    // 市话费
                    String citycall = resultMap.get("citycall");
                    
                    // 其他业务费
                    String othercall = resultMap.get("othercall");
                    
                    // 国内长途
                    String inlancall = resultMap.get("inlancall");
                    
                    // 港澳台长途
                    String colonycall = resultMap.get("colonycall");
                    
                    // 国际长途
                    String interncall = resultMap.get("interncall");
                    
                    // 国内漫游
                    String inlanroam = resultMap.get("inlanroam");
                    
                    // 港澳台漫游
                    String colonyroam = resultMap.get("colonyroam");
                    
                    // 国际漫游
                    String internroam = resultMap.get("internroam");
                    
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
                        records.append("'本地通话费：").append(CommonUtil.fillLeftBlank(localcall, 8)).append("',")
                                .append("'    市话费：").append(CommonUtil.fillLeftBlank(citycall, 8)).append("',")
                                .append("'其他业务费：").append(CommonUtil.fillLeftBlank(othercall, 8)).append("',")
                                .append("'长途通话费：").append(CommonUtil.fillLeftBlank(longcall, 8)).append("',")
                                .append("'  国内长途：").append(CommonUtil.fillLeftBlank(inlancall, 8)).append("',")
                                .append("'港澳台长途：").append(CommonUtil.fillLeftBlank(colonycall, 8)).append("',")
                                .append("'  国际长途：").append(CommonUtil.fillLeftBlank(interncall, 8)).append("',")
                                .append("'漫游通话费：").append(CommonUtil.fillLeftBlank(roamcall, 8)).append("',")
                                .append("'  国内漫游：").append(CommonUtil.fillLeftBlank(inlanroam, 8)).append("',")
                                .append("'港澳台漫游：").append(CommonUtil.fillLeftBlank(colonyroam, 8)).append("',")
                                .append("'  国际漫游：").append(CommonUtil.fillLeftBlank(internroam, 8)).append("',")
                                .append("'起始时间 通信地点 通信 对方号码    通信时长 通话费',")
                                .append("'                  方式                        (元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
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
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    String smsTotalNum = resultMap.get("smstotalnum");
                    String mmsTotalNum = resultMap.get("mmstotalnum");
                    
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
                        records.append("'短信总条数：").append(CommonUtil.fillLeftBlank(smsTotalNum, 7))
                                .append("            彩信总条数：").append(CommonUtil.fillLeftBlank(mmsTotalNum, 7)).append("',");
                        records.append("'起始时间 通信地点 对方号码     信息类型 通信费(元)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
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
                            
                            records.append(formatSmsRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    // GPRS话单条数
                    String gprssum = resultMap.get("gprssum");
                    
                    // CMWAP条数
                    String cmwapsum = resultMap.get("cmwapsum");

                    // CMNET条数
                    String cmnetsum = resultMap.get("cmnetsum");
                    
                    // 收费流量
                    String chargeflux = resultMap.get("chargeflux");
                    
                    // CMWAP收费流量
                    String cmwapflux = resultMap.get("cmwapflux");
                    
                    // CMNET收费流量
                    String cmnetflux = resultMap.get("cmnetflux");
                    
                    // 免费流量
                    String freechargeflux = resultMap.get("freechargeflux");
                    
                    // CMWAP免费流量
                    String cmwapfreeflux = resultMap.get("cmwapfreeflux");
                    
                    // CMNET免费流量
                    String cmnetfreeflux = resultMap.get("cmnetfreeflux");                    
                    
                    // 总流量
                    String sumflux = resultMap.get("sumflux");
                    
                    // CMWAP总流量
                    String cmwapsumflux = resultMap.get("cmwapsumflux");
                    
                    // CMNET总流量
                    String cmnetsumflux = resultMap.get("cmnetsumflux");
                    
                    // WLAN话单条数
                    String wlansum = resultMap.get("wlansum");
                    
                    // 公众WLAN条数
                    String pubwlansum = resultMap.get("pubwlansum");

                    // 校园WLAN条数
                    String campuswlansum = resultMap.get("campuswlansum");

                    // WLAN总流量
                    String wlansumflux = resultMap.get("wlansumflux");

                    // 公众WLAN流量
                    String pubwlanflux = resultMap.get("pubwlanflux");

                    // 校园WLAN流量
                    String campuswlanflux = resultMap.get("campuswlanflux");

                    // WLAN总时长
                    String wlansumtime = resultMap.get("wlansumtime");

                    // 公众WLAN时长
                    String pubwlantime = resultMap.get("pubwlantime");

                    // 校园WLAN时长
                    String campuswlantime = resultMap.get("campuswlantime");

                    // WLAN总费用
                    String wlansumfee = resultMap.get("wlansumfee");

                    // 公众WLAN费用
                    String pubwlanfee = resultMap.get("pubwlanfee");

                    // 校园WLAN费用
                    String campuswlanfee = resultMap.get("campuswlanfee");                    
                    
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
                        records.append("'       GPRS话单条数:").append(CommonUtil.fillLeftBlank(gprssum, 19)).append("',")
                                .append("'    其中：CMWAP条数:").append(CommonUtil.fillLeftBlank(cmwapsum, 19)).append("',")
                                .append("'    其中：CMNET条数:").append(CommonUtil.fillLeftBlank(cmnetsum, 19)).append("',");
                        
                        records.append("'           收费流量:").append(CommonUtil.fillLeftBlank(chargeflux, 19)).append("',")
                                .append("'其中：CMWAP收费流量:").append(CommonUtil.fillLeftBlank(cmwapflux, 19)).append("',")
                                .append("'其中：CMNET收费流量:").append(CommonUtil.fillLeftBlank(cmnetflux, 19)).append("',");
                        
                        records.append("'           免费流量:").append(CommonUtil.fillLeftBlank(freechargeflux, 19)).append("',")
                                .append("'其中：CMWAP免费流量:").append(CommonUtil.fillLeftBlank(cmwapfreeflux, 19)).append("',")
                                .append("'其中：CMNET免费流量:").append(CommonUtil.fillLeftBlank(cmnetfreeflux, 19)).append("',");
                
                        records.append("'             总流量:").append(CommonUtil.fillLeftBlank(sumflux, 19)).append("',")
                                .append("'  其中：CMWAP总流量:").append(CommonUtil.fillLeftBlank(cmwapsumflux, 19)).append("',")
                                .append("'  其中：CMNET总流量:").append(CommonUtil.fillLeftBlank(cmnetsumflux, 19)).append("',");
                                                
                        records.append("'       WLAN话单条数:").append(CommonUtil.fillLeftBlank(wlansum, 19)).append("',")
                                .append("' 其中：公众WLAN条数:").append(CommonUtil.fillLeftBlank(pubwlansum, 19)).append("',")
                                .append("' 其中：校园WLAN条数:").append(CommonUtil.fillLeftBlank(campuswlansum, 19)).append("',");
                        
                        records.append("'         WLAN总流量:").append(CommonUtil.fillLeftBlank(wlansumflux, 19)).append("',")
                                .append("' 其中：公众WLAN流量:").append(CommonUtil.fillLeftBlank(pubwlanflux, 19)).append("',")
                                .append("' 其中：校园WLAN流量:").append(CommonUtil.fillLeftBlank(campuswlanflux, 19)).append("',");
                        
                        records.append("'         WLAN总时长:").append(CommonUtil.fillLeftBlank(wlansumtime, 19)).append("',")
                                .append("' 其中：公众WLAN时长:").append(CommonUtil.fillLeftBlank(pubwlantime, 19)).append("',")
                                .append("' 其中：校园WLAN时长:").append(CommonUtil.fillLeftBlank(campuswlantime, 19)).append("',");
                                                
                        records.append("'         WLAN总费用:").append(CommonUtil.fillLeftBlank(wlansumfee, 19)).append("',")
                                .append("' 其中：公众WLAN费用:").append(CommonUtil.fillLeftBlank(pubwlanfee, 19)).append("',")
                                .append("' 其中：校园WLAN费用:").append(CommonUtil.fillLeftBlank(campuswlanfee, 19)).append("',");
                        
                        //records.append("'起始时间 通信地点 上网方式 时长 流量 2G/3G标识 通信费',");
                        String[] recordsArray = allRecords.split("\\|");
                        
                        // gWX294598 修改
//                        int recordsCount = recordsArray.length;
                        int recordsCount = recordsArray.length/2+1;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
//                            record = recordsArray[i];
                            
                            if(i==0) {
                        		record = recordsArray[i];
                        	}else {
                        		record = recordsArray[(i*2)-1]+recordsArray[i*2];
                        	}
                            
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
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    
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
                        records.append("'时间     使用方式   业务名称       业务端口   费用',");

                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
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
                            
                            records.append(formatIsmgRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    } 
                }
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    // 合计费用
                    String totalFee = resultMap.get("totalfee");
                    
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
                        records.append("'时间     业务 业务端口 服务提供商 费用类型费用(元)',");
                        records.append("'         名称',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
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
                            
                            records.append(formatInfofeeRecord(record));
                        }
                        
                        records.append("'合计：").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    } 
                }
            }
            
            String strRecords = "var printDataObject = {printTail:'YES'};var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records;
            }
            strRecords += "]";
            
            System.out.println(strRecords);
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
        // modify begin cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        HttpSession session = this.getRequest().getSession();

        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 检查详单打印是否超出次数限制
        Map<String, String> resultMap = detailedRecordsBean.writePrintCdrLog(customerSimp,
                terminalInfo,
                cdrType,
                curMenuId);
        
        if (null != resultMap && null != resultMap.get("success"))
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "月详单(" + cycle + "-" + cdrType + ")更新详单打印次数成功");

        }
        else if (null != resultMap && null != resultMap.get("errormsg"))
        {
            String msg = resultMap.get("errormsg");
            if ("".equals(msg))
            {
                msg = "检查详单打印是否超出次数限制失败";
            }
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "月详单(" + cycle + "-" + cdrType + ")更新详单打印次数失败：" + msg);

        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "月详单(" + cycle + "-" + cdrType + ")更新详单打印次数失败：");
        }
        // modify end cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        return "success";
    }
    
    /**
     * 打印详单之前，先判断之前是否已经打印过。如果是，提示用户不能重复打印。
     * @throws Exception
     * @remark create g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
     */
    public void checkPrintTimes() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        
        String msg = "";
        // modify begin cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        try
        {
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 检查详单打印是否超出次数限制
            Map<String, String> resultMap = detailedRecordsBean.queryPrintCdr(customerSimp,
                    terminalInfo,
                    cdrType,
                    curMenuId);
            
            if (null != resultMap && null != resultMap.get("success"))
            {
                msg = "";
            }
            else if (null != resultMap && null != resultMap.get("errormsg"))
            {
                msg = resultMap.get("errormsg");
                if ("".equals(msg))
                {
                    msg = "检查详单打印是否超出次数限制失败";
                }
            }
            else
            {
                msg = "检查详单打印是否超出次数限制失败";
            }
            
        }
        catch (Exception e)
        {
            msg = e.getMessage();
            
            logger.error("检查详单打印是否超出次数限制时发生异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(msg);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("检查详单打印是否超出次数限制时发生异常：", e);
                    
                    throw new Exception("检查详单打印是否超出次数限制时发生异常");
                }
            }
        }
        // modify end cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
    }
    
    /**
     * 打印详单之前，先判断之前是否已经打印过。如果是，提示用户不能重复打印。
     * @throws Exception
     * @remark create g00140516 2013/02/01 R003C13L01n01 每年的12个月，每月详单均只能打印一次
     */
    ////modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /*
    private String formatDate(String srcDate, String srcPattern, String destPattern)
    {
    	String destDate = srcDate;
    	
    	try
    	{
    		SimpleDateFormat srcFormat = new SimpleDateFormat(srcPattern);
        	SimpleDateFormat destFormat = new SimpleDateFormat(destPattern);
        	
        	destDate = destFormat.format(srcFormat.parse(srcDate));
    	}
    	catch (Exception e)
    	{
    		logger.error("格式化日期异常：", e);
    	}
    	
    	return destDate;
    }
    */
    /**
     * 格式化单条套餐及固定费详单记录
     * 扣费日期占10个字节，左对齐；套餐及固定费名称占34个字节，左对齐；费用(元)占6字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    /*
    private String formatFixfeeRecordbak(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
               
        String[] rows = null;
        int rowNum = 0;
        
        if (fields[2].getBytes().length <= 34)
        {
            rowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 17)
            {
                rowNum = fields[2].length() / 17;
            }
            else
            {
                rowNum = fields[2].length() / 17 + 1;
            }                              
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[0], 10);    
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 10);
            }
            
            if (m == rowNum - 1)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 17), 34);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 17, (m + 1) * 17), 34);
            }                               
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[3], 6);    
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /**
     * 格式化单条套餐及固定费详单记录
     * 扣费日期占10个字节，左对齐；套餐及固定费名称占34个字节，左对齐；费用(元)占6字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatFixfeeRecord(String record)
    {
        String[] fields = record.split("@_@");
        
        //开始
        String str = "'";
        
        // 记录
        str = str + 
        fields[0] + " " + // 扣费日期
        fields[1] + " " + // 使用周期
        fields[2] + " " + // 套餐及固定费名称
        fields[3]; // 费用(元)
        
        // 结束
        str = str + "',";
        
        logger.error("套餐及固定费详单记录:");
        logger.error(str);
        
        return str;
    }
    
    /**
     * 格式化单条通话详单记录
     * 起始时间，占8个字节，左对齐；通信地点占5个字节，左对齐；通信方式占4个字节，左对齐；对方号码占12字节，左对齐；
     * 通信时长占8个字节，左对齐；实收通话费(元)占7个字节，右对齐；实收长途费(元)占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatGsmRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 4)
            {
                tempRowNum = fields[1].length() / 4;
            }
            else
            {
                tempRowNum = fields[1].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 通信方式
        if (fields[2].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 2)
            {
                tempRowNum = fields[2].length() / 2;
            }
            else
            {
                tempRowNum = fields[2].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对方号码
        if (fields[3].getBytes().length <= 12)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 12)
            {
                tempRowNum = fields[3].length() / 12;
            }
            else
            {
                tempRowNum = fields[3].length() / 12 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 通信时长
        if (fields[4].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 4)
            {
                tempRowNum = fields[4].length() / 4;
            }
            else
            {
                tempRowNum = fields[4].length() / 4 + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= 9)
            {
            	if (m == 0)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[1].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            // 通信方式
            if (fields[2].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[2].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // 对方号码
            if (fields[3].length() > (m + 1) * 12)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 12, (m + 1) * 12), 12);
            }
            else if (fields[3].length() > m * 12)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 12), 12);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 12);
            }
            
            // 通信时长 
            if (fields[4].getBytes().length <= 9)
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[4].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条短彩信详单记录
     * 起始时间，占9个字节，左对齐；通信地点占9个字节，左对齐；对方号码占13个字节，左对齐；信息类型占9个字节，左对齐；
     * 通信费(元)占10个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatSmsRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 4)
            {
                tempRowNum = fields[1].length() / 4;
            }
            else
            {
                tempRowNum = fields[1].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对方号码
        if (fields[2].getBytes().length <= 13)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 13)
            {
                tempRowNum = fields[2].length() / 13;
            }
            else
            {
                tempRowNum = fields[2].length() / 13 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 信息类型
        if (fields[4].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 4)
            {
                tempRowNum = fields[4].length() / 4;
            }
            else
            {
                tempRowNum = fields[4].length() / 4 + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= 9)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[1].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
         
            // 对方号码
            if (fields[2].length() > (m + 1) * 13)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 13, (m + 1) * 13), 13);
            }
            else if (fields[2].length() > m * 13)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 13), 13);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 13);
            }
            
            // 信息类型
            if (fields[4].getBytes().length <= 9)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[4], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[4].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[4].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 10);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 10);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条上网详单记录
     * 起始时间，占8个字节，左对齐；通信地点5、上网方式6、时长11、流量7个字节，左对齐；2G/3G(3个字节)通信费(元)占5个字节，右对齐。
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatGprsWlanRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 2)
            {
                tempRowNum = fields[1].length() / 2;
            }
            else
            {
                tempRowNum = fields[1].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对上网方式进行特殊处理，英文占多数        
        tempRowNum = 0;
        
        int offset = 0;
        int len = 6;
        while (offset < fields[2].length())
        {
            if (offset + len > fields[2].length())
            {
                len = fields[2].length() - offset;
            }
            
            while (fields[2].substring(offset, offset + len).getBytes().length > 6)
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
        
        // 时长
        if (fields[3].getBytes().length <= 12)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 12)
            {
                tempRowNum = fields[3].length() / 12;
            }
            else
            {
                tempRowNum = fields[3].length() / 12 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 流量
        if (fields[4].getBytes().length <= 7)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 7)
            {
                tempRowNum = fields[4].length() / 7;
            }
            else
            {
                tempRowNum = fields[4].length() / 7 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        } 
        
        // 2G/3G
        String fileds6 = fields[6];
        if(fileds6.equals("2G/3G标识"))
        {
            fileds6 = "2G/3G";
        }
        if (fileds6.getBytes().length <= 3)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fileds6.length() % 2)
            {
                tempRowNum = fileds6.length() / 2;
            }
            else
            {
                tempRowNum = fileds6.length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        String txFee = fields[7];
        if(txFee.equals("通信费"))
        {
            txFee = "通信费(元)";
        }
        else
        {
            txFee = txFee.substring(0,txFee.length()-1);
        }
        // 通讯费
        if (txFee.getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == txFee.length() % 4)
            {
                tempRowNum = txFee.length() / 4;
            }
            else
            {
                tempRowNum = txFee.length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
            
        rows = new String[rowNum];
        
        // 对上网方式进行特殊处理，英文占多数
        offset = 0;
        len = 6;
        
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 8); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 8);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 8);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[1].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // 上网方式
            if (offset < fields[2].length())
            {
                if (offset + len > fields[2].length())
                {
                    len = fields[2].length() - offset;
                }
                
                while (fields[2].substring(offset, offset + len).getBytes().length > 6)
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(offset, offset + len), 6);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 6);
            }
            
            // 时长
            if (fields[3].getBytes().length <= 12)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[3], 12);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 12);
            	}
            }
            else
            {
            	if (fields[3].length() > (m + 1) * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8, (m + 1) * 8), 12);
                }
                else if (fields[3].length() > m * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8), 12);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 12);
                }
            }
            
            // 流量
            if (fields[4].length() > (m + 1) * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6, (m + 1) * 6), 7);
            }
            else if (fields[4].length() > m * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6), 7);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 7);
            }
            
            // 2G/3G
            if (fileds6.length() > (m + 1) * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2, (m + 1) * 2), 3);
            }
            else if (fileds6.length() > m * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2), 3);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 3);
            }
            
//            if (fields[6].getBytes().length <= 3)
//            {
//                if (0 == m)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6], 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
//            else
//            {
//                if (fields[6].length() > (m + 1) * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 2, (m + 1) * 2), 3);
//                }
//                else if (fields[6].length() > m * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 2), 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
            
            // 通讯费
            if (txFee.getBytes().length <= 5)
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee, 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            else
            {
                if (txFee.length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5, (m + 1) * 5), 5);
                }
                else if (txFee.length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条上网详单记录
     * 起始时间，占8个字节，左对齐；通信地点、上网方式、时长、流量各占9个字节，左对齐；通信费(元)占6个字节，右对齐。
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /*
    private String formatGprsWlanRecordBak(String record)
    {
        String[] fields = record.split("@_@");
        
        //开始
        String str = "'";
        
        // 记录
        str = str + 
        fields[0].split(" ")[1] + " " + // 起始时间
        fields[1] + " " + // 通信地点
        fields[2] + " " + // 上网方式
        fields[3] + " " + // 时长
        fields[4] + " " + // 流量
        fields[6] + " " + // 2/3G
        fields[7].replaceAll("元", "");// 通信费(元)
        
        // 结束
        str = str + "',";
        
        logger.error("上网详单记录:");
        logger.error(str);
        
        return str;
    }
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /**
     * 格式化单条增值业务扣费记录
     * 时间，占9个字节，左对齐；使用方式占11个字节，左对齐；业务名称占15个字节，左对齐；业务端口占9个字节，左对齐；费用占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String formatIsmgRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 使用方式       
        if (fields[1].getBytes().length <= 11)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 5)
            {
                tempRowNum = fields[1].length() / 5;
            }
            else
            {
                tempRowNum = fields[1].length() / 5 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务名称
        if (fields[2].getBytes().length <= 15)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 7)
            {
                tempRowNum = fields[2].length() / 7;
            }
            else
            {
                tempRowNum = fields[2].length() / 7 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务端口
        if (fields[3].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 9)
            {
                tempRowNum = fields[3].length() / 9;
            }
            else
            {
                tempRowNum = fields[3].length() / 9 + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // 使用方式
            if (fields[1].getBytes().length <= 11)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 11);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 11);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 5, (m + 1) * 5), 11);
                }
                else if (fields[1].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 5), 11);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 11);
                }
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= 15)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 15);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 15);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 7)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 7, (m + 1) * 7), 15);
                }
                else if (fields[2].length() > m * 7)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 7), 15);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 15);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9, (m + 1) * 9), 9);
            }
            else if (fields[3].length() > m * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9), 9);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[4], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * 格式化单条代收费业务扣费记录
     * 时间，占9个字节，左对齐；业务名称占5个字节，左对齐；业务端口占9个字节，左对齐；服务提供商11个字节，左对齐；费用类型占10个字节，左对齐；
     * 费用占6个字节，右对齐。
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 业务名称      
        if (fields[2].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 2)
            {
                tempRowNum = fields[2].length() / 2;
            }
            else
            {
                tempRowNum = fields[2].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 业务端口
        if (fields[3].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 9)
            {
                tempRowNum = fields[3].length() / 9;
            }
            else
            {
                tempRowNum = fields[3].length() / 9 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 服务提供商
        if (fields[4].getBytes().length <= 11)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 5)
            {
                tempRowNum = fields[4].length() / 5;
            }
            else
            {
                tempRowNum = fields[4].length() / 5 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 费用类型
        if (fields[6].getBytes().length <= 10)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[6].length() % 5)
            {
                tempRowNum = fields[6].length() / 5;
            }
            else
            {
                tempRowNum = fields[6].length() / 5 + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // 业务名称
            if (fields[2].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[2].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // 服务号码
            if (fields[3].length() > (m + 1) * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9, (m + 1) * 9), 9);
            }
            else if (fields[3].length() > m * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9), 9);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // 服务提供商
            if (fields[4].getBytes().length <= 11)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[4], 11);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 11);
            	}
            }
            else
            {
            	if (fields[4].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 5, (m + 1) * 5), 11);
                }
                else if (fields[4].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 5), 11);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 11);
                }
            }
            
            // 费用类型
            if (fields[6].getBytes().length <= 10)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[6], 10);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 10);
            	}
            }
            else
            {
            	if (fields[6].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 5, (m + 1) * 5), 10);
                }
                else if (fields[6].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 5), 10);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 10);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
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

    public String getCdrTypeName()
    {
        return cdrTypeName;
    }

    public void setCdrTypeName(String cdrTypeName)
    {
        this.cdrTypeName = cdrTypeName;
    }

    public String getRecordFields()
    {
        return recordFields;
    }

    public void setRecordFields(String recordFields)
    {
        this.recordFields = recordFields;
    }

    public Vector getResultRecords()
    {
        return resultRecords;
    }

    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
    }

    public List<String> getFixfeeRecords()
    {
        return fixfeeRecords;
    }

    public void setFixfeeRecords(List<String> fixfeeRecords)
    {
        this.fixfeeRecords = fixfeeRecords;
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

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStartDate()
    {
        return startDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getEndDate()
    {
        return endDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getQueryDate()
    {
        return queryDate;
    }

    public void setQueryDate(String queryDate)
    {
        this.queryDate = queryDate;
    }

    public String getChQueryDate()
    {
        return chQueryDate;
    }

    public void setChQueryDate(String chQueryDate)
    {
        this.chQueryDate = chQueryDate;
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

    public List<CycleInfoPO> getCycles()
    {
        return cycles;
    }

    public void setCycles(List<CycleInfoPO> cycles)
    {
        this.cycles = cycles;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
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

    public int getTitleCols()
    {
        return titleCols;
    }

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

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
    
    
}
