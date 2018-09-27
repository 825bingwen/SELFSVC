/*
 * 文 件 名:  InvoicePrint.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: zKF69263
 * 修改时间:  2014-5-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.invoice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.InvoicePrintBean;
import com.customize.sd.selfsvc.feeService.model.CyclePO;
import com.customize.sd.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.MoneySimpleToCapitalUtil;

/**
 * 发票打印 
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-5-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InvoicePrintSDAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1661915831837155550L;

    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(InvoicePrintSDAction.class);
    
    /**
     * 设置结果
     */
    private List<List<String>> result;
    
    /**
     * 调用接口Bean
     */
    private transient InvoicePrintBean invoicePrintBean;
    
    /**
     * 结果标题
     */
    private String[] servicetitle;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 用户手机号
     */
    private String servnumber;
    
    /**
     * 发票打印PO
     */
    private InvoicePrintPO invoicePrint;
    
    //add begin kWX211786 20141/6/23  自助缴费增加月结发票打印
    /**
     * 发票打印类型
     */
    private String invoiceType;
    
    //add end kWX211786 20141/6/23  自助缴费增加月结发票打印
       
    /*-------Add by wWX217192 on 20140516 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票 start-------*/
    
    private String month = "";
	
	// 账期列表
	private List<CyclePO> cycleList = new ArrayList<CyclePO>();
	
	/*-------Add by wWX217192 on 20140516 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票 end-------*/
    
	// add begin jWX216858 2015-4-15 OR_SD_201501_1063 自助终端支撑连缴功能优化
	/**
     * 话费连缴标志
     */
    private String morePhoneFlag;
    
    /**
     * 打印月结发票标志，1：打印月结发票
     */
    private String monthInvoiceFlag;
    
    /**
     * 凭条打印标志，1：已打印
     */
    private String printPayProFlag;
    
    /**
     * 打印全部发票标志，1：已打印
     */
    private String printAllInvFlag;
    
    /**
     * 话费连缴总金额
     */
    private String totalFee;
    
    /**
     * 话费连缴用户信息字符串
     * 用户1，用户2，用户3
     * 用：手机号码~手机号码region~实缴金额~余额~应缴费~客户姓名~受理类型~缴费日志流水~受理流水~boss编号~缴费状态
     */
    private String morePhoneInfo;
    
    /**
     * 已打印发票的手机号码串
     */
    private String printInvTelnum = "";
    // add end jWX216858 2015-4-15 OR_SD_201501_1063 自助终端支撑连缴功能优化
    
    /**
     * 读取要打印的发票记录信息
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    public String invoiceList()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("invoiceList Starting...");
        }
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        servnumber = customer.getServNumber();
        
        // 查询要打印发票的记录信息
        Map mapResult = invoicePrintBean.invoiceList(terminalInfoPO, customer, curMenuId, invoicePrint.getBillCycle());
        
        if (mapResult != null && mapResult.size() > 1)
        {
            CRSet crset = (CRSet)mapResult.get("returnObj");
            
            // 判断返回报文数据是否为空
            if (null == crset)
            {
                getRequest().setAttribute("errormessage", "没有可打印的发票信息！");
                
                return "error";
            }
            else
            {
                // 定义结果标题名称
                String[] titles = {"受理编号", "客户名称", "金额", "大写金额", "缴费渠道"};
                
                // 设置标题名称
                setServicetitle(titles);
                
                result = new ArrayList();
                
                List listInner = null;
                
                if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_NOINVOICEPRINT_SD))
                {
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        // 判断打印类型=1 发票，账期=0缴费发票信息
                        if ("1".equals(crset.GetValue(i, 4)) && "0".equals(crset.GetValue(i, 7))) 
                        {
                            listInner = new ArrayList();
                            // 业务受理号
                            listInner.add(crset.GetValue(i, 1));
                            
                            //modify begin lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
                            // 客户名称
                            listInner.add(CommonUtil.getUserLastName(crset.GetValue(i, 2)));
                            
                            //modify end lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造  
                            
                            // 发票金额
                            String invoicefee = CommonUtil.fenToYuan(crset.GetValue(i, 6));
                            // 金额
                            listInner.add(invoicefee);
                            // 将金额转为大写
                            if (StringUtils.isNotEmpty(invoicefee))
                            {
                                listInner.add(MoneySimpleToCapitalUtil.getInstance().format(invoicefee));
                            }
                            // 缴费渠道
                            listInner.add(crset.GetValue(i, 15));
                            // 帐期
                            listInner.add(crset.GetValue(i, 7));
                            // 账号
                            listInner.add(crset.GetValue(i, 9));
                            // 打印类型  0 收据  1 发票
                            listInner.add(crset.GetValue(i, 4));
                            
                            result.add(listInner);
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        // 判断打印类型=1 发票，账期=0缴费发票信息
                        if ("1".equals(crset.GetValue(i, 2)) && "0".equals(crset.GetValue(i, 4))) 
                        {
                            listInner = new ArrayList();
                            // 业务受理号
                            listInner.add(crset.GetValue(i, 0));
                          
                            //modify begin lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
                            // 客户名称
                            listInner.add(CommonUtil.getUserLastName(crset.GetValue(i, 1)));                           
                            //modify end lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造  
                            
                            // 发票金额
                            String invoicefee = CommonUtil.fenToYuan(crset.GetValue(i, 3));
                            // 金额
                            listInner.add(invoicefee);
                            // 将金额转为大写
                            if (StringUtils.isNotEmpty(invoicefee))
                            {
                                listInner.add(MoneySimpleToCapitalUtil.getInstance().format(invoicefee));
                            }
                            // 缴费渠道
                            listInner.add(crset.GetValue(i, 6));
                            // 帐期
                            listInner.add(crset.GetValue(i, 4));
                            // 账号
                            listInner.add(crset.GetValue(i, 5));
                            // 打印类型  0 收据  1 发票
                            listInner.add(crset.GetValue(i, 2));
                            
                            result.add(listInner);
                        }
                    }
                }
                
            }
        }
        else if (mapResult != null)
        {
            getRequest().setAttribute("errormessage", "没有可打印的发票信息！");
            
            return "error";
        }
        else {
            
            getRequest().setAttribute("errormessage", "查询异常");
            
            return "error";
        }
        
        return "success";
    }
    
    /** 
     * 打印发票
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    public String printInvoice()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "success";
        
        HttpServletRequest request = this.getRequest();
        
        List list = new ArrayList();
        
        // 调用接口打印发票
        //modify begin kWX211786 2014/6/25 需求编号：OR_SD_201406_148_山东_营改增-个人客户发票开具规则
        //增加缴费充值打印发票
        if("recMonthInvoice".equals(curMenuId) ||  "feeCharge".equals(curMenuId))
       //modify end kWX211786 2014/6/25 需求编号：OR_SD_201406_148_山东_营改增-个人客户发票开具规则
        {
        	list = qryMonthInvoice();
        }
        else
        {
        	list = getPrintInvoiceData();
        }
        
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启,打印电子发票时，接口不返回值,所以直接跳到界面
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(terminalInfoPO.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            String errormessage = (String)getRequest().getAttribute("errormessage");
            if(CommonUtil.isNotEmpty(errormessage))
            {
                logger.info(errormessage);
                return "error";
            }
            else
            {
                // 获取客户信息
                NserCustomerSimp customer = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
                if (null == customer)
                {
                    customer = new NserCustomerSimp();
                    customer.setServNumber(servnumber);
                }
                
                request.setAttribute("servnumber", customer.getServNumber());
                request.setAttribute("isElectronInvoice", isElectronInvoice);
                logger.info("打印电子发票，用户" + customer.getServNumber() + "打印发票信息成功");
                return "success";
            }
        }
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
        if (null == list)
        {
            if (logger.isInfoEnabled())
            {
                logger.info("发票打印功能，用户" + servnumber + "查询发票信息失败");
            }
            
        	// 统一返回错误提示页面，将错误信息打印在页面上
        	forward = "error";
        }
        else
        {
            // 发票打印方式配置。1:厂商编码A,机器型号A|厂商编码C,机器型号C;2:厂商编码B,机器型号B|厂商编码D,机器型号D;3:厂商编码E,机器型号E|厂商编码F,机器型号F
            String printType = (String) PublicCache.getInstance().getCachedData("SH_INVOICE_PRINTTYPE");
            if (printType != null && !"".equals(printType.trim()))
            {
                // 终端机信息
                TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
                
                // 厂商编码B,机器型号B
                String tmpStr = termInfo.getProvidercode() + "," + termInfo.getMachinemodel();
                int index1 = printType.indexOf(tmpStr);
                if (index1 != -1)
                {
                    int index2 = printType.indexOf(";", index1);
                    if (index2 != -1)
                    {
                        // 1:厂商编码A,机器型号A|厂商编码C,机器型号C;2:厂商编码B,机器型号B|厂商编码D,机器型号D
                        printType = printType.substring(0, index2);
                    }
                    
                    index2 = printType.lastIndexOf(":");
                    
                    // 打印方式：2
                    printType = printType.substring(index2 - 1, index2);
                    
                    request.setAttribute("printType", printType);
                }
            }
            
            // 调用接口打印发票
            request.setAttribute("invoiceXML", getXmlStr(list));
            
            if (logger.isInfoEnabled())
            {
                logger.info("发票打印功能，用户" + servnumber + "查询发票信息成功");
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice End");
        }
        
        return forward;
    }
    
    /** 
     * 调用打印发票接口查询发票数据
     * 
     * @return List<Map<String, String>>
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    private List<Map<String, String>> getPrintInvoiceData()
    {
        HttpServletRequest request = this.getRequest();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 查询打印发票信息
        Map invoiceData = invoicePrintBean.invoiceData(termInfo, curMenuId, servnumber, getInvoicePrint());
        
        List<Map<String, String>> invoicesList = null;
        
        if (invoiceData != null && invoiceData.size() > 1)
        {
            Object invoiceObj = invoiceData.get("returnObj");
            
            if (invoiceObj instanceof CRSet)
            {
                invoicesList = new ArrayList<Map<String, String>>();
                
                // 取接口返回对象
                CRSet crset  = (CRSet)invoiceObj;

                // crset转成MAP
                Map<String, String> invoiceMaps = getInvoiceInfo(crset);
                
                // 加入list
                invoicesList.add(invoiceMaps);
            }
            
            // 记录查询未打印发票信息成功日志
            this.createRecLog(customer.getServNumber(), Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "0", "获取补打发票信息成功!");
        }
        else if (invoiceData != null) {
            
            getRequest().setAttribute("errormessage", invoiceData.get("returnMsg"));
            
            // 记录查询未打印发票信息失败日志
            this.createRecLog(customer.getServNumber(), Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "1", 
                "获取补打发票信息失败：" + String.valueOf(invoiceData.get("returnMsg")));
        }
        
        // 返回
        return invoicesList;
    }
    
    /**
     * 对客服返回的打印发票内容进行处理
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    public Map<String, String> getInvoiceInfo(CRSet crset)
    {
    	// 获取session信息
    	HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
    	// 从接口返回值中获取发票信息
    	Map<String, String> invoiceMaps = new HashMap<String, String>();
        
    	// 组合成可用的Map，并返回
    	Map<String, String> resultMaps = new HashMap<String, String>();
    	
        // 将发票明细信息拼接成字符串信息
        StringBuffer contentItemStr = new StringBuffer("");
        
        // 发票解析格式的标志位, 0:老发票格式，1：新发票格式
        String styleFlag = "0";
        
        // 话费账期
        StringBuffer feeTime = new StringBuffer("");
        
        // 获取ContentItemX或者ContentItemXName的值
        String key = "";
        
        // 初始化最大值信息
        int max = 0;
        
        // ContentItem数据的索引值
        int seq = 0;
        
        // 遍历发票明细信息
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	seq = 0;
        	
        	key = crset.GetValue(i, 0);
        	
        	// 若key值中出现以ContentItem开头不以Name结尾的信息，则为新发票格式，启用新发票的解析方法
        	if(key.startsWith("ContentItem") && !key.endsWith("Name"))
        	{
        		styleFlag = "1";
        	}
        	
        	max = max(key, max);
        	
        }
        
        // 定义一个数组，封装发票明细信息
        String[] contentItems = new String[max];
        
        // 定义一个获取ContentItem后数值的变量
        int arrNums = 0;
        
        // 遍历crset的数据
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	String name = crset.GetValue(i, 0);
        	String value = crset.GetValue(i, 1);
        	
        	// name中包含ContentItem的字符串，则将信息封装成字符串的格式
        	if(name.startsWith("ContentItem"))
        	{
        		arrNums = getSeq(name);
        		
        		if("1".equals(styleFlag))
        		{
        			parseInvoiceDetail(name, value, contentItems, arrNums);
        		}
        		else
        		{
        			contentItems[arrNums - 1] = value.trim().replace("  ", "|");
        		}
        	}
        	// 若name中不包含ContentItem的字符串，则将数据添加至invoiceMaps中
        	else
        	{
        		invoiceMaps.put(name, value);
        	}
        }
        
        // 将ContentItemStr字符串清空，存储发票信息
        contentItemStr = new StringBuffer("");
        
        for(int i = 0; i < contentItems.length; i++)
        {
        	if(StringUtils.isNotEmpty(contentItems[i]))
        	{
        		contentItemStr.append(contentItems[i]).append(";");
        	}
        }
        
        // 计费期间(截取前8个字符，作为值进行使用)
        if(!CommonUtil.isEmpty(invoiceMaps.get("callfeeCreateTime")))
        {
        	feeTime = feeTime.append("话费账期:|").append(invoiceMaps.get("callfeeCreateTime").substring(0, 6) + "(" + invoiceMaps.get("callfeeCreateTime") + ")").append(";");
        }
        
        // 在发票明细的顶部添加话费账期的显示项
        contentItemStr = feeTime.append(contentItemStr);
        
        // 增加显示"金额大写"
        contentItemStr.append("大写金额合计:|").append(invoiceMaps.get("totalmoneydx")).append(";");
        
        // 对发票明细进行解析，解析成可打印在发票上的字符串
        String consumeList = parseConsumeList(contentItemStr.toString(), invoiceMaps.get("note"));
        
        invoiceMaps.put("consumeList", consumeList);
        
        // 将信息封装成类似缴费接口的形式打印
        // 手机号码
        resultMaps.put("servNumber", invoiceMaps.get("telnumber"));
        
        // 流水号
        resultMaps.put("dealNum", invoiceMaps.get("formnum"));
        
        // 发票打印
        resultMaps.put("acceptType", invoiceMaps.get("rectype"));
        
        // 合同号
        resultMaps.put("acctID", 
        	StringUtils.isNotEmpty(invoiceMaps.get("paynumno")) ? invoiceMaps.get("paynumno") : "");
        
        // 客户名称
        resultMaps.put("subsName", invoiceMaps.get("username"));
        
        // 打印日期(YYYY.MM.DD)将格式修改为YYYYMMDD
        resultMaps.put("payDate", invoiceMaps.get("time").replace(".", ""));
        
        // 受理编号
        resultMaps.put("bossFormnum", invoiceMaps.get("formnum"));
        
        // 终端组织机构名称
        resultMaps.put("pOrgName", termInfo.getOrgname());
        
        // 发票明细信息
        resultMaps.put("consumeList", invoiceMaps.get("consumeList"));
        
        return resultMaps;
        
    }
    
    /**
     * 得到ContentItem串中的最大值信息
     * @param key
     * @param max
     * @return 最大值
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
     */
    private int max(String key, int max)
    {
    	// 获取ContentItem后的数值序列
    	int seq = 0;
    	
    	// 获取key值中的数字信息
    	if(key.startsWith("ContentItem") && key.endsWith("Name"))
    	{
    		seq = Integer.parseInt(key.replace("ContentItem", "").replace("Name", ""));
    	}
    	
    	// 获取索引中的最大值
    	if(seq > max)
    	{
    		max = seq;
    	}
    	
    	return max;
    }
    
    /**
     * 得到ContentItem序列中的数值信息
     * @param name
     * @return 得到ContentItem串后的数值信息
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
     */
    private int getSeq(String name)
    {
    	// 获取ContentItem后的数值
    	int arrNums = 0;
    	
    	// 字符串以Name结尾，则replace掉ContentItem及Name字符串
    	if(name.startsWith("ContentItem") && name.endsWith("Name"))
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", "").replace("Name", ""));
		}
    	// 字符串不以Name结尾，则替换掉ContentItem
		else
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", ""));
		}
    	return arrNums;
    }
    
    /**
     * 解析发票明细
     * @param name
     * @param value
     * @param contentItemStr
     * @return 发票明细字符串
     * @remark create wWX217192 2014-07-08 OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_月结发票
     */
    private String[] parseInvoiceDetail(String name, String value, String[] contentItems, int seq)
    {
		StringBuffer tempSf = new StringBuffer("");
		
		// 若ContentItems[seq - 1] 不为空，则将name替换为value值
		if(StringUtils.isNotEmpty(contentItems[seq - 1]))
		{
			contentItems[seq - 1] = contentItems[seq - 1].replace(name, value);
		}
		// ContentItems[seq - 1] 为空
		else
		{
			// name以"Name"结尾，拼接字符串存入数组中
			if(name.endsWith("Name"))
			{
				tempSf.append(value).append("|").append(name.substring(0, name.length() - 4));
				contentItems[seq - 1] = tempSf.toString();
			}
			// name不以"Name"结尾，处理后放入数组中
			else
			{
				tempSf.append(name + "Name").append("|").append(value);
				contentItems[seq - 1] = tempSf.toString();
			}
		}
		
		// modify begin wWX217192 2014-07-14 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
		// 现场要求在合计后加空行
		if(value.equals("合计"))
		{
			contentItems[seq - 1] = contentItems[seq - 1] + "\\n";
		}
		// modify end wWX217192 2014-07-14 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
		
		return contentItems;
    }
    
    
    /**
     * 将发票数据组织成xml
     * 
     * @param list List
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_")
                .append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                    .append(subItemKey)
                    .append("><![CDATA[")
                    .append(subItemValue)
                    .append("]]></")
                    .append(subItemKey)
                    .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        
        return xmlStr.toString();
    }
    
    /**
     * 查询近六个月月份
     * 
     * @return
     * @throws Exception
     * @remark add by wWX217192 on 20140516 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票 start
     */
    public String qryCurrentMonth() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();

        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        
        for(int i = 0; i < 6; i++)
        {
        	request.setAttribute("month" + i, CommonUtil.getLastMonth(pattern, i));
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth End");
        }
        
        return "qryCurrentMonth";
    }
    
    /**
     * 查询账期功能
     * <功能详细描述>
     * @param
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     * @remark add by wWX217192 on 20140516 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票 start
     */
    @SuppressWarnings("unchecked")
	public String qryBillCycle() throws Exception
    {
    	if(logger.isDebugEnabled())
    	{
    		logger.debug("qryBillCycle Starting ...");
    	}
    	
    	HttpServletRequest request = this.getRequest();
    	
    	// 获取session信息
    	HttpSession session = this.getRequest().getSession();
    	
    	// 获取客户信息
    	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    	//add begin kWX211786 20141/6/23  自助缴费增加月结发票打印
    	if (null == customer)
    	{
    	    customer = new NserCustomerSimp();
    	    customer.setServNumber(servnumber);
    	}
    	//add end kWX211786 20141/6/23  自助缴费增加月结发票打印
    	// 获取终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	//调用查询账期接口返回的数据
    	ReturnWrap rw = invoicePrintBean.qryBillCycle(customer.getServNumber(), termInfo, curMenuId, invoicePrint.getBillCycle());
    	
    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 取返回对象
            Vector vector = (Vector)(rw.getReturnObject());
            
            // 账期信息
            CRSet crset = (CRSet)(vector.get(1));
            
            CyclePO cyclePO;
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                cyclePO = new CyclePO();
                cyclePO.setCycle(crset.GetValue(i, 0));// 帐期
                cyclePO.setStartdate(crset.GetValue(i, 1));// 开始时间
                cyclePO.setEnddate(crset.GetValue(i, 2));// 结束时间
                cyclePO.setAcctid(crset.GetValue(i, 3));// 主账号
                cyclePO.setUnionacct(crset.GetValue(i, 4));// 是否合户用户。1，是；0，不是
                cycleList.add(cyclePO);
            }
            
            // 返回
            return "selectCycle";
        }
        else
        {
        	request.setAttribute("errormessage", "账期查询失败！");
        	
        	request.setAttribute("backStep", "-1");
        	
            // 返回
            return "error";
        }
    }
    
    /**
     * 查询月结发票数据
     * <功能详细描述>
     * @param
     * @return
     * @throws Exception
     * @return
     * @remark add by wWX217192 on 20140516 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票 start
     */
    @SuppressWarnings("unchecked")
	public List<Map<String, String>> qryMonthInvoice()
    {
    	if(logger.isDebugEnabled())
    	{
    		logger.debug("qryMonthInvoice Starting ...");
    	}
    	
    	// 获取session信息
    	HttpSession session = this.getRequest().getSession();
    	
    	// 获取客户信息
    	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    	
    	//add begin kWX211786 20141/6/23  自助缴费增加月结发票打印
        if (null == customer)
        {
            customer = new NserCustomerSimp();
            customer.setServNumber(servnumber);
        }
        //add end kWX211786 20141/6/23  自助缴费增加月结发票打印
    	
    	// 获取终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	// 调用月结发票数据查询接口，获取月结发票数据信息
    	ReturnWrap rw = invoicePrintBean.qryMonthInvoice(customer.getServNumber(), termInfo, invoicePrint.getBillCycle(), 
    			invoicePrint.getStartdate(), invoicePrint.getEnddate(), invoicePrint.getAcctId(), curMenuId);
        
    	List<Map<String, String>> invoicesList = null;
    	Map invoiceMap = new HashMap();
    	
    	// 接口调用成功，将数据进行封装
    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
    	{
    		// 发票流详细信息
    		CRSet crset = (CRSet)rw.getReturnObject();
    		
    		if(null != crset)
    		{
    			invoicesList = new ArrayList<Map<String, String>>();
    			invoiceMap = getInvoiceInfo(crset);
    			invoicesList.add(invoiceMap);
    		}
    		
    		// 记录查询月结发票信息成功日志
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_SD, "0", "0", "0", "获取月结发票信息成功!");
    	}
    	else if(rw != null && rw.getStatus() == SSReturnCode.ERROR)
    	{
    		getRequest().setAttribute("errormessage", rw.getReturnMsg());
    		
    		// 记录查询月结发票信息失败日志
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_SD, "0", "0", "1", rw.getReturnMsg());
    	}
    	else
    	{
    		getRequest().setAttribute("errormessage", "获取发票信息失败!");
    		
    		// 记录查询月结发票信息失败日志
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_SD, "0", "0", "1", "获取月结发票信息失败!");
    	}
    	
    	return invoicesList;
    }
    
    /**
     * 消费明细、发票注释解析
     * 
     * @param consumeList 消费明细
     * @param comments 发票注释
     * @return
     */
    private String parseConsumeList(String consumeList, String comments)
    {
        // modify begin g00140516 2012/08/14 R003C12L08n01 发票打印格式优化
        String[] consumeArr = consumeList.replace("|", ",").split(";");
        StringBuffer tmpConsumeList = new StringBuffer();
        int rowNum = 18;
        int row = 0;
        for (int i = 0; i < consumeArr.length && row < rowNum; i++)
        {
            if (consumeArr[i].trim().length() <= 3)
            {
                continue;
            }
            
            String tmpArr[] = consumeArr[i].split(",");
            if (!CommonUtil.isEmpty(tmpArr[0]))
            {
            	// modify begin wWX217192 2014-07-10 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票信息改造
            	// 为保留发票明细子项前的空格信息将trim()方法删除
            	tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[0], 13));
                
                if (tmpArr.length > 1)
                {
                	// 为保留发票明细子项前的空格信息将trim()方法删除
                    tmpConsumeList.append(CommonUtil.fillLeftBlank(tmpArr[1], 8));
                }
                else
                {
                    tmpConsumeList.append(CommonUtil.fillLeftBlank("", 8));
                }
                // modify end wWX217192 2014-07-10 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票信息改造
                tmpConsumeList.append("\\n");
                row++;
            }
        }
        
        // 发票备注，每行打印25个汉字
        if (comments != null && !"".equals(comments.trim()))
        {
            tmpConsumeList.append(comments.trim()).append("\\n");
            row++;
        }
        
        tmpConsumeList.append("\\n");
        row++;
        tmpConsumeList.append("\\n");
        row++;
        
        return tmpConsumeList.toString();
    }
    
    /**
     * @return 返回 result
     */
    public List<List<String>> getResult()
    {
        return result;
    }

    /**
     * @param 对result进行赋值
     */
    public void setResult(List<List<String>> result)
    {
        this.result = result;
    }

    /**
     * @return 返回 invoicePrintBean
     */
    public InvoicePrintBean getInvoicePrintBean()
    {
        return invoicePrintBean;
    }

    /**
     * @param 对invoicePrintBean进行赋值
     */
    public void setInvoicePrintBean(InvoicePrintBean invoicePrintBean)
    {
        this.invoicePrintBean = invoicePrintBean;
    }

    /**
     * @return 返回 servicetitle
     */
    public String[] getServicetitle()
    {
        return servicetitle;
    }

    /**
     * @param 对servicetitle进行赋值
     */
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * @return 返回 servnumber
     */
    public String getServnumber()
    {
        return servnumber;
    }

    /**
     * @param 对servnumber进行赋值
     */
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }

    /**
     * @return 返回 invoicePrint
     */
    public InvoicePrintPO getInvoicePrint()
    {
        return invoicePrint;
    }

    /**
     * @param 对invoicePrint进行赋值
     */
    public void setInvoicePrint(InvoicePrintPO invoicePrint)
    {
        this.invoicePrint = invoicePrint;
    }

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<CyclePO> getCycleList() {
		return cycleList;
	}

	public void setCycleList(List<CyclePO> cycleList) {
		this.cycleList = cycleList;
	}

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

	public String getMorePhoneFlag() {
		return morePhoneFlag;
	}

	public void setMorePhoneFlag(String morePhoneFlag) {
		this.morePhoneFlag = morePhoneFlag;
	}

	public String getMonthInvoiceFlag() {
		return monthInvoiceFlag;
	}

	public void setMonthInvoiceFlag(String monthInvoiceFlag) {
		this.monthInvoiceFlag = monthInvoiceFlag;
	}

	public String getPrintPayProFlag() {
		return printPayProFlag;
	}

	public void setPrintPayProFlag(String printPayProFlag) {
		this.printPayProFlag = printPayProFlag;
	}

	public String getPrintAllInvFlag() {
		return printAllInvFlag;
	}

	public void setPrintAllInvFlag(String printAllInvFlag) {
		this.printAllInvFlag = printAllInvFlag;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getMorePhoneInfo() {
		return morePhoneInfo;
	}

	public void setMorePhoneInfo(String morePhoneInfo) {
		this.morePhoneInfo = morePhoneInfo;
	}

	public String getPrintInvTelnum() {
		return printInvTelnum;
	}

	public void setPrintInvTelnum(String printInvTelnum) {
		this.printInvTelnum = printInvTelnum;
	}
    
}
