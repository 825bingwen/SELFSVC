/*
* @filename: DetailedRecordsHubAction.java
* @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
* @brif:  NG3.5帐详单改造之详单查询
* @author: 高群/g00140516
* @de:  2012/02/09 
* @description: 
* @remark: create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
*/
package com.customize.hub.selfsvc.feeservice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * NG3.5帐详单改造之详单查询
 * 
 * @author  g00140516
 * @version  1.0, 2012/02/09
 * @see  
 * @since  
 */
public class DetailedRecordsHubAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(DetailedRecordsHubAction.class);
    
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
     * 第一个月份
     */
    private String months_first = "";
    
    /**
     * 查询月份
     */
    private String month = "";
    
    /**
     * 详单类型
     */
    private String cdrType = "";
    
    /**
     * 上网详单的类型
     */
    private String gprsWlanType = "";
    
    /**
     * 费用类型
     */
    private String feeType = "";
    
    /**
     * 详单类型中文名称
     */
    private String cdrTypeName = "";
    
    /**
     * 详单记录字段
     */
    private String recordFields = "";
    
    /**
     * 统计信息
     */
    private String billSummary = "";
    
    /**
     * 合计信息
     */
    private Map<String, String> billTotal = null;
    
    /**
     * 套餐与固定费清单记录
     */
    private List<String[]> fixfeeRecords = null;
    
    /**
     * 套餐与固定费之外的其它类型的详单记录
     */
    private Vector resultRecords = null;
    
    private String resultMsg = "";

    /**
     * 校验用户是否有权限查询详单
     * 
     * @return
     * @see 
     */
    public String checkQueryAuth()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("checkQueryAuth Starting ...");
        }       
        
        String forward = "success";
        
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        boolean bz = false;
        
        if (null != customerSimp)
        {
            // 删除session中保存的用户的详单数据
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
            
            // 校验用户是否有权限查询详单
            bz = detailedRecordsBean.checkQueryAuth(customerSimp, terminalInfo, customerSimp.getServNumber(), curMenuId);
        }
        
        if (!bz)
        {
            logger.error("用户" + customerSimp.getServNumber() + "无权查询详单");
            
            String resultMsg = getConvertMsg("您没有详单查询权限！", "zz_00_17_000002", null, null);
            
            request.setAttribute("errormessage", resultMsg);
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", resultMsg);         
            
            forward = "failed";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("checkQueryAuth End");
        } 
        
        return forward;
    }
    
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
        
        // 页面直接点击用
        months_first = months[0];
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth End");
        }
        
        return forward;
    }
    
    /**
     * 详单类型选择页面
     * 
     * @return
     * @see 
     */
    //modified by xkf57421 for OR_HUB_201203_299
    public String selectType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType Starting ...");
        }
        
        String forward = "types";
        
        String effectMonth = (String) PublicCache.getInstance().getCachedData(Constants.NEWCDR_EFFECTMONTH);
        
        //第二个配置：分用户群依次配置
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        String custBrand = customerSimp.getBrandID();
        
        String brandGotone = "";
        brandGotone = (String) PublicCache.getInstance().getCachedData("SH_BRANDGOTONE_BRAND_CFG");
        
        String brandMzone = "";
        brandMzone = (String) PublicCache.getInstance().getCachedData("SH_BRANDMZONE_BRAND_CFG");
        
        String brandSzx = "";
        brandSzx = (String) PublicCache.getInstance().getCachedData("SH_BRANDSZX_BRAND_CFG");
        
        String brandOther = "";
        brandOther = (String) PublicCache.getInstance().getCachedData("SH_BRANDOTHER_BRAND_CFG");
        
        String brandMonCfg = "BrandGotone".equals(custBrand) ? brandGotone 
       		 			  : ("BrandMzone".equals(custBrand) ? brandMzone 
       		 			  : ("BrandSzx".equals(custBrand) ? brandSzx : brandOther));
        
        String curDateStr = CommonUtil.getLastMonth("yyyyMMdd", 0);
       
        
        if (null == effectMonth || "".equals(effectMonth))
        {
            logger.error("系统未配置新详单查询功能的割接月份，将使用新的详单类型进行查询");
        }
        else if (month.compareTo(effectMonth) < 0)
        {
            // 湖北用户查询割接月份之前的详单，使用旧的详单类型
            return "oldTypes";
        }
        
        //如果品牌月份没有配置则显示old
        if(null == brandMonCfg || "".equals(brandMonCfg))
        {
        	return "oldTypes";
        }
        //如果配置了品牌月份但是大于当前日期则显示old
        else if(brandMonCfg.compareTo(curDateStr) > 0)
   	 	{
        	return "oldTypes";
   	 	}
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType End");
        }
        
        return forward;
    }
    
    /**
     * 选择上网详单的类型
     * 
     * @return
     * @see 
     */
    public String selectGprsWlanType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectGprsWlanType Starting ...");
        }
        
        String forward = "grpsWlanTypes";
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectGprsWlanType End");
        }
        
        return forward;
    }
    
    /**
     * 费用类型选择页面
     * 
     * @return
     * @see 
     */
    public String selectFeeType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectFeeType Starting ...");
        }
        
        String forward = "feeTypes";
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectFeeType End");
        }
        
        return forward;
    }
    
    /**
     * 获取详单记录
     * 
     * @return
     * @see 
     */
    public String qryDetailedRecords()
    {        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 可查询当前月和前五个月的详单信息
        String[] monthsBak = new String[6];
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM, 6);
        for (int i = 0; i <= 5; i ++)
        {
            monthsBak[5-i] = months[i];
        }
        months = monthsBak;
        
        Object[] cdrTypeInfo = Constants.CDRTYPEMAP.get(cdrType);
        
        // 根据详单类型未获取到对应的信息
        if (null == cdrTypeInfo || 2 > cdrTypeInfo.length)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "系统不支持详单类型" + cdrType);
            
            logger.error("系统不支持详单类型" + cdrType);
            
            request.setAttribute("errormessage", "系统不支持详单类型" + cdrType + "，请联系营业人员解决！");
            
            return "failed";
        }
        
        cdrTypeName = (String) cdrTypeInfo[0];
        
        recordFields = (String) cdrTypeInfo[1];
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String servnumber = customerSimp.getServNumber();
        
        /*
         * 某种类型的详单记录信息，包含billcount和billitem。以通话详单为例，如billcount为2，
         * billitem为08-03 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4|08-05 21:56:04@_@北京@_@主叫@_@66174709@_@19秒@_@0.4
         */
        Map<String, String> resultMap = null;
        
        /*
         * session中保存的用户详单记录的数据结构：
         * 使用Map存放用户的全部详单记录，键值为(month + "-" + cdrType + "-" + feeType)，对应的详单记录为resultMap
         */
        Map<String, Map<String, String>> detailedRecords = (Map<String, Map<String, String>>) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        if (null != detailedRecords)
        {
            resultMap = detailedRecords.get(month + "-" + cdrType + "-" + feeType);
        }
        else
        {
            detailedRecords = new HashMap<String, Map<String, String>>();
            
            session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
        }
        
        // 从session中未取到详单记录，则调用后台接口获取，同时存放到session中
        if (null == resultMap || 0 == resultMap.size())
        {
            resultMap = detailedRecordsBean.queryDetailedRecords2012(customerSimp, terminalInfo, month, cdrType, feeType, curMenuId);
             
            detailedRecords.put(month + "-" + cdrType + "-" + feeType, resultMap);
        }
        
        // 取详单记录
        String allRecords = null;
        
        if (null != resultMap)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "月详单(" + month + "-" + cdrType + "-" + feeType + ")查询成功");
            
            billSummary = resultMap.get("billsummary");         
            
            allRecords = resultMap.get("billitem");
        }
        else
        {
            resultMsg = getConvertMsg("无对应的详单记录", "zz_04_17_000001", null, formatParams(month, cdrType, feeType));
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", resultMsg);
        }
            
        if (Constants.CDRTYPE_FIXFEE.equals(cdrType))
        {
            // 套餐与固定费清单，计算合计费用           
            fixfeeRecords = parseFixfeeRecords(allRecords);
        }
        else
        {
            // 非套餐与固定费清单，按日期分组，同时计算合计信息
            resultRecords = parseNonFixfeeRecords(allRecords);
        }            
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords End");
        }
        
        return cdrType;
    }
    
    /**
     * 解析套餐及固定费详单记录
     * 
     * @param allRecords
     * @return
     * @see 
     */
    private List<String[]> parseFixfeeRecords(String allRecords)
    {
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return null;
        }
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();
        
        // 存放解析后的详单记录
        List<String[]> result = new ArrayList<String[]>();
        
        // 合计费用
        String totalFee = "0";
        
        // 单条记录
        String record = "";
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;        
        
        String[] fileds = null;
        
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
            
            // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替
            record = record.replaceAll("@@", "@ @");
            
            fileds = record.split("@_@");

            totalFee = CurrencyUtil.add(totalFee, fileds[3]);
            
            result.add(fileds);           
        }
        
        billTotal.put("totalFee", totalFee);
        
        return result;
    }
    
    /**
     * 解析非套餐及固定费详单记录
     * 
     * @param allRecords
     * @return
     * @see 
     */
    private Vector parseNonFixfeeRecords(String allRecords)
    {
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return null;
        }
        
        // 存放合计信息
        billTotal = new HashMap<String, String>();
        
        // 存放解析后的详单记录
        Vector result = new Vector();
        
        // 合计费用
        String totalFee = "0";
        
        // 合计时长
        String totalSeconds = "0";
        
        // 合计流量
        String totalNetwork = "0";
        
        // 单条记录
        String record = "";
        
        // 单条记录的各字段
        String[] fields = null;
        
        // 单条记录的时间
        String timeField = "";
        
        // 单条记录对应的日期
        String day = "";
        
        // 存放同一天的所有记录
        List<String[]> dayRecords = null;
        
        String lastDay = "";
        
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
            if (10 > timeField.length())
            {
                continue;
            }
            
            // 通话详单，需格式化时长，计算合计费用
            if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
            {
                for (int j = 0; j < fields.length; j++)
                {
                    if (0 == j)
                    {
                        fields[j] = timeField.substring(10);
                    }
                    else if (4 == j)
                    {
                        fields[j] = CommonUtil.formatSeconds(fields[j]);
                    }
                }
                
                totalFee = CurrencyUtil.add(totalFee, fields[7]);
            }
            // 短/彩信详单或者代收费业务记录，只需计算合计费用
            else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType) 
                    || Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
            {
                for (int j = 0; j < fields.length; j++)
                {
                    if (0 == j)
                    {
                        fields[j] = timeField.substring(10);
                    }
                }
                
                totalFee = CurrencyUtil.add(totalFee, fields[7]);
            }
            // 上网详单，需区分WLAN上网详单和GPRS上网详单，还需格式化时长、流量，计算合计时长、合计流量
            else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
            {
                // 上网方式中含有WLAN，则为WLAN上网记录
                if ("WLAN".equalsIgnoreCase(gprsWlanType) && -1 == fields[2].indexOf("WLAN"))
                {
                    continue;
                }
                // 上网方式中含有GPRS，则为GPRS上网记录
                else if ("GPRS".equalsIgnoreCase(gprsWlanType) && -1 != fields[2].indexOf("WLAN"))
                {
                    continue;
                }

                totalSeconds = CurrencyUtil.addInt(totalSeconds, fields[3]);
                totalNetwork = CurrencyUtil.addInt(totalNetwork, fields[4]);
                
                for (int j = 0; j < fields.length; j++)
                {
                    if (0 == j)
                    {
                        fields[j] = timeField.substring(10);
                    }
                    else if (3 == j)
                    {
                        fields[j] = CommonUtil.formatSeconds(fields[j]);
                    }
                    else if (4 == j)
                    {
                        fields[j] = CommonUtil.calKToM(fields[j]);
                    }
                }
            }
            // 增值业务扣费记录，需计算合计费用
            else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
            {
                for (int j = 0; j < fields.length; j++)
                {
                    if (0 == j)
                    {
                        fields[j] = timeField.substring(10);
                    }
                }
                
                totalFee = CurrencyUtil.add(totalFee, fields[4]);
            }
            // 其他扣费记录和账单减免记录，需计算合计费用
            else if (Constants.CDRTYPE_OTHERFEE.equalsIgnoreCase(cdrType)
                    || Constants.CDRTYPE_DISCOUNT.equalsIgnoreCase(cdrType))
            {
                for (int j = 0; j < fields.length; j++)
                {
                    if (0 == j)
                    {
                        fields[j] = timeField.substring(10);
                    }
                }
                
                totalFee = CurrencyUtil.add(totalFee, fields[2]);
            }
            
            day = timeField.substring(0, 10);
            
            // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
            // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
            if (!lastDay.equals(day))
            {
                lastDay = day;
                
                dayRecords = new ArrayList<String[]>();
                
                result.add(day);
                result.add(dayRecords);                
            }
            
            dayRecords.add(fields);           
        }
                
        billTotal.put("totalSeconds", CommonUtil.formatSeconds(totalSeconds));
        billTotal.put("totalNetwork", CommonUtil.calKToM(totalNetwork));
        billTotal.put("totalFee", totalFee);
        
        return result;
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
        
        if ("0".equals(strFeeType))
        {
            params[2] = "非零费用";
        }
        else if ("1".equals(strFeeType))
        {
            params[2] = "费用为零";
        }
        
        if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(strCDRType))
        {
            params[3] = "套餐及固定费详单";
        }
        else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(strCDRType))
        {
            params[3] = "通话详单";
        }
        else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(strCDRType))
        {
            params[3] = "短/彩信详单";
        }
        else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(strCDRType))
        {
            params[3] = "上网详单";
        }
        else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(strCDRType))
        {
            params[3] = "增值业务扣费";
        }
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(strCDRType))
        {
            params[3] = "代收费业务";
        }
        else if (Constants.CDRTYPE_OTHERFEE.equalsIgnoreCase(strCDRType))
        {
            params[3] = "其他扣费";
        }
        else if (Constants.CDRTYPE_DISCOUNT.equalsIgnoreCase(strCDRType))
        {
            params[3] = "账单减免";
        }
        
        return params;
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

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
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

    public List<String[]> getFixfeeRecords()
    {
        return fixfeeRecords;
    }

    public void setFixfeeRecords(List<String[]> fixfeeRecords)
    {
        this.fixfeeRecords = fixfeeRecords;
    }

    public String getGprsWlanType()
    {
        return gprsWlanType;
    }

    public void setGprsWlanType(String gprsWlanType)
    {
        this.gprsWlanType = gprsWlanType;
    }

    public String getBillSummary()
    {
        return billSummary;
    }

    public void setBillSummary(String billSummary)
    {
        this.billSummary = billSummary;
    }

    public Map<String, String> getBillTotal()
    {
        return billTotal;
    }

    public void setBillTotal(Map<String, String> billTotal)
    {
        this.billTotal = billTotal;
    }

    public String getMonths_first()
    {
        return months_first;
    }

    public void setMonths_first(String months_first)
    {
        this.months_first = months_first;
    }    
        
    public String getResultMsg()
    {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }
}
