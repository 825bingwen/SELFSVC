/*
 * 文件名：IntMsgUtil.java
 * 版权：Copyright 2010-2015 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：新接口平台接口调用处理类
 * 修改人：y68902
 * 修改时间：2010-08-07
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.feeService.model.BillPO;
import com.customize.sd.selfsvc.feeService.model.FeedetailPO;
import com.customize.sd.selfsvc.feeService.model.PkgPO;
import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;
import com.gmcc.boss.selfsvc.busiAcceptTime.service.BusiAcceptTimeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.AccountUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author y68902
 * 
 */
public class IntMsgUtil
{
    private static String serverURL = "";
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    static DocumentFactory df = DocumentFactory.getInstance();
    
    private static Log logger = LogFactory.getLog(IntMsgUtil.class);
    
    static int transID = 1; // 流水号
    
    //add begin zWX176560 OR_SD_201308_934 R003C13L09n01
    /**
     * 受理时长表操作service
     */
    private BusiAcceptTimeService busiAcceptTimeServiceImpl;

   //add end zWX176560 OR_SD_201308_934 R003C13L09n01
    
    /**
     * 发送请求到Dubbo服务器工具类
     */
    private transient DubboInvokeUtil dubboInvokeUtil;
    
    /**
     * 创建http连接
     * 
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static HttpURLConnection getConnection(String urlStr) throws Exception
    {
        
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setDoOutput(true);
        //modify begin fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_关于便利店配合was升级的改造需求需求分析设计说明书
        con.setRequestProperty("Pragma", "no-cache");
        //modify end fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_关于便利店配合was升级的改造需求需求分析设计说明书
      
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Content-Type", "text/xml");
        return con;
    }
    
    /**
     * 创建XML请求报文
     * 
     * @param ...
     * @return
     */
    public Document createMsg(Document docContent, String process_code, String menuid, String unicontact,
            String route_type, String route_value, String operatorid, String atsvNum)
    {
        return this.createMsg(docContent, process_code, menuid, unicontact, route_type, route_value, 
            operatorid, atsvNum, "bsacAtsv", null);
    }
    
    /**
     * 山东创建XML请求报文，使用verify_code、unicontact
     * 
     * @param docContent
     * @param process_code 业务代码
     * @param menuid
     * @param unicontact 统一接触流水，密码验证接口返回
     * @param route_type
     * @param route_value
     * @param operatorid
     * @param atsvNum
     * @param verifyCode 校验码，密码验证接口返回
     * @return
     * @remark: create g00140516 2012/02/09 R003C12L02n01 OR_huawei_201112_953
     */
    public Document createMsg(Document docContent, String process_code, String menuid, String unicontact,
            String route_type, String route_value, String operatorid, String atsvNum, String verifyCode)
    {
        return this.createMsg(docContent, process_code, menuid, unicontact, route_type, route_value, 
            operatorid, atsvNum, "bsacAtsv", verifyCode);
    }
    
    /**
     * 创建XML请求报文，使用verify_code、unicontact
     * 
     * @param docContent
     * @param process_code 业务代码
     * @param menuid
     * @param unicontact 统一接触流水，密码验证接口返回
     * @param route_type
     * @param route_value
     * @param operatorid
     * @param atsvNum
     * @param channelId 渠道编码
     * @param verifyCode 校验码，密码验证接口返回
     * @return Document
     * @remark: create zKF69263 2014/07/26 R003C14L07n01 OR_huawei_201407_369
     */
    public Document createMsg(Document docContent, String process_code, String menuid, String unicontact,
        String route_type, String route_value, String operatorid, String atsvNum, String channelId, String verifyCode)
    {
        Document doc = df.createDocument();
        doc.setXMLEncoding("GBK");
        
        // 根节点
        Element root = doc.addElement("message_request");
        // 消息头
        Element eHead = root.addElement("message_head");
        eHead.addAttribute("version", "1.0");
        
        // 菜单项
        Element eMenu = eHead.addElement("menuid");
        eMenu.addText(menuid);
        // 业务代码
        Element eProssCode = eHead.addElement("process_code");
        eProssCode.addText(process_code);
        // 验证码
        Element eVerifyCode = eHead.addElement("verify_code");
        if (null == verifyCode)
        {
            eVerifyCode.addText("");
        }
        else
        {
            eVerifyCode.addText(verifyCode);
        }
        
        // 请求时间
        Element eProcessTime = eHead.addElement("req_time");
        synchronized (sdf)
        {
            eProcessTime.addText(sdf.format(new Date()));
        }
        
        // 请求流水
        Element eSeq = eHead.addElement("req_seq");
        eSeq.addText(String.valueOf(transID++));
        
        // 统一接触
        Element eUnicontact = eHead.addElement("unicontact");
        if (null == unicontact)
        {
            eUnicontact.addText("");
        }
        else
        {
            eUnicontact.addText(unicontact);
        }
        
        // 测试标识
        Element eTestFlag = eHead.addElement("testflag");
        eTestFlag.addText("1");
        
        // 路由（父节点）
        Element eRoute = eHead.addElement("route");
        // 路由类型
        Element eRoute_type = eRoute.addElement("route_type");
        eRoute_type.addText(route_type);
        // 路由字段值
        Element eRoute_value = eRoute.addElement("route_value");
        eRoute_value.addText(route_value);
        
        // 渠道信息（父节点）
        Element eChannelinfo = eHead.addElement("channelinfo");
        // 操作员
        Element eOperatorid = eChannelinfo.addElement("operatorid");
        eOperatorid.addText(operatorid);
        // 渠道ID
        Element eChannelid = eChannelinfo.addElement("channelid");
        eChannelid.addText(channelId);
        // 子渠道id
        Element eUnitid = eChannelinfo.addElement("unitid");
        // eUnitid.addText(atsvNum);
        eUnitid.addText("HUAWEI");
        
        // 请求内容
        Element eBody = root.addElement("message_body");
        docContent.setXMLEncoding("GBK");
        eBody.addCDATA(docContent.asXML());
        
        return doc;
    }
    
    // public CTagSet parseResponseToTagset(String response) throws Exception {
    //
    // System.out.println("response xml=========" + response);
    //
    // Document doc = DocumentHelper.parseText(response);
    // Element root = doc.getRootElement();
    // Element eResponse = root.element("Response");
    // Element eHead = eResponse.element("message_head");
    //		
    // String resType = null;
    // String rspCode = null;
    // String rspDesc = null;
    // String rspSvc = null;
    //
    // CTagSet cset = new CTagSet();
    //
    // if (eResponse != null) {
    // Element eRetinfo = eHead.element("retinfo");
    //			
    // resType = eRetinfo.element("rettype").getText();
    // rspCode = eRetinfo.element("retcode").getText();
    // rspDesc = eRetinfo.element("retmsg").getText();
    //
    // //错误信息处理，如果是系统异常，屏蔽底层错误
    // if (!"0".equals(resType) && !"600".equals(resType)){
    // rspDesc = "系统错误,请稍后再试!";
    // }
    // cset.SetValue("rettype", rspCode);
    // cset.SetValue("retcode", rspCode);
    // cset.SetValue("retmsg", rspDesc);
    // }
    //
    // List bodyList = root.elements("message_body");
    //
    // if (bodyList != null && bodyList.size() > 0) {
    // Iterator it = bodyList.iterator();
    //
    // while (it.hasNext()) {
    // Element e = (Element) it.next();
    // String key = e.getName();
    // String val = e.getText().replace("^_^", "&");
    // cset.SetValue(key, val);
    // }
    // }
    //
    // return cset;
    // }
    
    /**
     * 解析响应报文
     * 
     * @param response
     * @return ResponseBean
     * 
     * 将响应报文解析成ResponseBean对象
     */
    // public ResponseBean parseResponseToBean(String response) throws Exception {
    //
    // System.out.println("response xml=========" + response);
    //
    // Document doc = DocumentHelper.parseText(response);
    // Element root = doc.getRootElement();
    // Element eResponse = root.element("message_response");
    // Element eHead = root.element("message_head");
    //
    // String resType = null;
    // String rspCode = null;
    // String rspDesc = null;
    // String rspSvc = null;
    //
    // ResponseBean rspBean = new ResponseBean();
    // CTagSet tagSet = new CTagSet();
    // CRSet rSet = new CRSet();
    //		
    // if (eResponse != null) {
    //
    // Element eRetinfo = eHead.element("retinfo");
    //			
    // resType = eRetinfo.element("rettype").getText();
    // rspCode = eRetinfo.element("retcode").getText();
    // rspDesc = eRetinfo.element("retmsg").getText();
    //
    // if (!"0".equals(rspCode)) {
    // rspDesc = getErrorMessage(resType, rspCode, rspDesc);
    // }
    //			
    // rspBean.setRettype(resType);
    // rspBean.setRetcode(rspCode);
    // rspBean.setRetmsg(rspDesc);
    // }
    //
    // List bodyList = root.elements("message_body");
    // if (bodyList != null && bodyList.size() > 0) {
    //
    // Iterator it = bodyList.iterator();
    //
    // int i = 0;
    // int j = 0;
    // while (it.hasNext()) {
    // Element e = (Element) it.next();
    // Iterator itChild = e.elementIterator();
    // if (!itChild.hasNext()) {
    // String key = e.getName();
    // String val = e.getText().replace("^_^", "&");
    // tagSet.SetValue(key, val);
    // } else {
    // if (i == 0) {
    // rSet = new CRSet(e.elements().size());
    // }
    //
    // rSet.AddRow();
    // Element eChild = null;
    // while (itChild.hasNext()) {
    // eChild = (Element) itChild.next();
    // rSet
    // .SetValue(i, j, eChild.getText().replace("^_^",
    // "&"));
    // j++;
    // }
    // i++;
    // j = 0;
    // }
    // }
    // }
    //
    // rspBean.setTagset(tagSet);
    // rspBean.setRset(rSet);
    //
    // return rspBean;
    //		
    //		
    // }
    /**
     * 解析响应报文
     * 
     * @param response
     * @return ReturnWrap
     * 
     * 将响应报文解析成ReturnWrap对象
     */
    public ReturnWrap parseResponse(String response) throws Exception
    {
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        Element eHead = root.element("message_head");
        
        // String resType = null;
        String rspCode = null;
        String rspDesc = null;
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        String verifyCode = "";
        String contactid = "";
        
        ReturnWrap rtData = new ReturnWrap();
        CTagSet tagSet = new CTagSet();
        CRSet rSet = new CRSet();
        
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        // 解析信息头，保存错误码和错误信息
        if (eHead != null)
        {
            Element eVerifyCode = eHead.element("verify_code");
            if (null != eVerifyCode)
            {
                verifyCode = eVerifyCode.getText();
            }            
            
            Element eUnicontact = eHead.element("unicontact");
            if (null != eUnicontact)
            {
                contactid = eUnicontact.getText();    
            }                    
            
            Element eRetinfo = eHead.element("retinfo");
            
            // resType = eRetinfo.element("rettype").getText();
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            // 状态
            rtData.setStatus(1);
            // 如果错误码不是0，状态设为0
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                if (logger.isErrorEnabled() && !logger.isInfoEnabled())
                {
                    logger.error("responseMessage: " + response);
                }
                
                // rspDesc = getErrorMessage(resType, rspCode, rspDesc);
                rtData.setStatus(0);
                rtData.setErrcode(new Integer(rspCode).intValue());
            }
            else
            {
                rtData.setErrcode(100);
            }
            // 错误信息
            rtData.setReturnMsg(rspDesc);
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        Element ebody = root.element("message_body");
        if (ebody == null)
        {
            return rtData;
        }
        String rspCont = ebody.getText();
        if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
        {
            rspCont = rspCont.replaceAll("&", "_");
        }
        Document body = DocumentHelper.parseText(rspCont);
        Iterator itBody = body.getRootElement().elementIterator();
        
        // 解析消息体，将信息封装为Tagset或Rset
        int i = 0;
        int j = 0;
        while (itBody.hasNext())
        {
            Element e = (Element)itBody.next();
            Iterator itChild = e.elementIterator();
            if (!itChild.hasNext())
            {
                String key = e.getName();
                String val = e.getText().replace("^_^", "&");
                tagSet.SetValue(key, val);
            }
            else
            {
                if (i == 0)
                {
                    rSet = new CRSet(e.elements().size());
                }
                
                rSet.AddRow();
                Element eChild = null;
                while (itChild.hasNext())
                {
                    eChild = (Element)itChild.next();
                    rSet.SetValue(i, j, eChild.getText().replace("^_^", "&"));
                    j++;
                }
                i++;
                j = 0;
            }
        }
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        // 返回值同时存在tagset和Rset
        if (!tagSet.isEmpty() && rSet.GetRowCount() > 0)
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                tagSet.SetValue("verifyCode", verifyCode);
                tagSet.SetValue("contactid", contactid);
            }
            
            retVector.add(tagSet);
            retVector.add(rSet);
            rtData.setReturnObject(retVector);
        }
        else if (!tagSet.isEmpty())
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                tagSet.SetValue("verifyCode", verifyCode);
                tagSet.SetValue("contactid", contactid);
            }
            
            // 返回值只有Tagset
            rtData.setReturnObject(tagSet);
            
        }
        else if (rSet.GetRowCount() > 0)
        {
            // 返回值只有Rset
            rtData.setReturnObject(rSet);
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        return rtData;
        
    }
    
    /**
     * 错误信息处理
     * 
     * @param resType
     * @param errorCode
     * @param errorInfo
     * @return ReturnWrap
     * 
     * 根据请求报文调用新接口平台
     */
    public String getErrorMessage(String resType, String errorCode, String errorInfo)
    {
        
        if (!"600".equals(resType))
        {
            return "系统错误，请稍后再试！";
        }
        
        return errorInfo;
    }
    
    //add begin zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
    /**
     * 解析请求报文
     * 
     * @param docXML 请求报文
     * @return map
     * @throws Exception 
     */
    public BusiAcceptTimePO parseRequest(String request) throws Exception
    {
        BusiAcceptTimePO busiAcceptTimePO = new BusiAcceptTimePO();
        Document doc = DocumentHelper.parseText(request);
        Element root = doc.getRootElement();
        Element eHead = root.element("message_head");
        String menuId = "";
        String businessId = "";
        String routeType = "";
        String servnum = "";
        String region = "";
        String operId = "";
        
        //解析请求报文头
        if(null != eHead)
        {
            Element eMenuId = eHead.element("menuid");
            if(null != eMenuId)
            {
                menuId = eMenuId.getText();
            }
            Element eBusinessId = eHead.element("process_code");
            if(null != eBusinessId)
            {
                businessId = eBusinessId.getText();
            }
            Element route = eHead.element("route");
            if(null !=route)
            {
                Element eRouteType = route.element("route_type");
                if(null != eRouteType)
                {
                    routeType = eRouteType.getText();
                    if(routeType.equals("0"))
                    {
                        Element eRouteValue = route.element("route_value");
                        if(null != eRouteValue)
                        {
                            region = eRouteValue.getText();
                        }
                    }
                    if(routeType.equals("1"))
                    {
                        Element eRouteValue = route.element("route_value");
                        if(null != eRouteValue)
                        {
                            servnum = eRouteValue.getText();
                        }
                    }
                }
            }
            Element channelinfo = eHead.element("channelinfo");
            if(null != channelinfo)
            {
                Element operatorid = channelinfo.element("operatorid");
                if(null != operatorid)
                {
                    operId = operatorid.getText();
                }
            }
            
        }
        
        //组装受理时间
        busiAcceptTimePO.setBusinessId(businessId);
        busiAcceptTimePO.setMenuId(menuId);
        busiAcceptTimePO.setRegion(region);
        busiAcceptTimePO.setRouteType(routeType);
        busiAcceptTimePO.setServnum(servnum);
        busiAcceptTimePO.setOperId(operId);
        
        //返回
        return busiAcceptTimePO;
    }
    //add end zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
    
    /**
     * 新接口平台调用
     * 
     * @param docXML 请求报文
     * @return ReturnWrap
     * 
     * 根据请求报文调用新接口平台
     */
    public ReturnWrap invoke(Document docXML)
    {
        //add begin zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
        //解析发送报文获取操作员id，businessId，路由方式，号码，region
        BusiAcceptTimePO busiAcceptTimePO = new BusiAcceptTimePO();
        try
        {
            busiAcceptTimePO = this.parseRequest(docXML.asXML());
        }
        catch (Exception e1)
        {
            logger.error("解析发送报文异常：", e1);
        }
        
        // 报文发送时间
        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        String startTime = sdf.format(new Date());

        // 获取终端机信息
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
        HttpSession session = request.getSession();
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        busiAcceptTimePO.setTermId(terminalInfoPO.getTermid());
        //add end zWX176560 OR_SD_201308_934 R003C13L09n01
        //add begin hWX5316476  R003C13L09n01
        busiAcceptTimePO.setRegion(terminalInfoPO.getRegion());
        //add end hWX5316476  R003C13L09n01
        HttpURLConnection con = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try
        {
        	// modify begin lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
            // 根据processcode判断是否调openEbus，若配置了相应的服务则认为是要走openEbus
            String businessId = busiAcceptTimePO.getBusinessId();
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                // openEbus地址
                serverURL = CommonUtil.getDictNameById("CMCVS_OpenEbusAddress", "openEbusConf");
                
                // 字典项中dictname值为对应的服务和method，下划线隔开
                DictItemPO dict = CommonUtil.getDictItemById(businessId, "openEbusInvokeList");
                
                // 追加服务
                if (dict != null)
                {
                    serverURL += dict.getDictname().split("_", -1)[0] + "?access_token=" + getToken() + "&method="
                            + dict.getDictname().split("_", -1)[1] + "&format=json";
                }
            }
            else
            {
                String url = getServerURL();
                if (!"".equals(url))
                {
                    serverURL = url;
                }
            }
        	
            logger.info("serverURL: " + serverURL);
            // modify end lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
            
            // 获取连接实例
            con = IntMsgUtil.getConnection(serverURL);
            
            if (Constants.PROOPERORGID_SD.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                String invokeTime = (String)PublicCache.getInstance().getCachedData("SH_INVOKE_TIME");
                if (invokeTime != null)
                {
                    // 设置连接主机超时（单位：毫秒）
                    con.setConnectTimeout(Integer.parseInt(invokeTime)*1000);
                    
                    // 设置从主机读取数据超时（单位：毫秒）
                    con.setReadTimeout(Integer.parseInt(invokeTime)*1000);
                }
            }
            
            String xmlInfo = docXML.asXML();
            
            logger.info("原始请求报文：" + xmlInfo);
            
            // modify begin lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
            // 根据processcode判断是否调openEbus，若配置了相应的服务则认为是要走openEbus
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                // openEbus使用json格式
                con.setRequestProperty("Content-Type", "application/json; charset=" + getEncodingForEbusContentType());
                
                // 转换报文格式，创建openEbus所用的报文
                Document doc = getDocXmlForOpenEbus(docXML, busiAcceptTimePO);
                
                // xml转为json格式
                xmlInfo = CommonUtil.convertXmlToJson(doc.asXML());
            }
            // modify end lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
            
            if (logger.isWarnEnabled())
            {
                logger.warn("request message: " + xmlInfo);
            }
            
            out = new OutputStreamWriter(con.getOutputStream());
            
            // 接口调用
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
            	out.write(xmlInfo);
            }
            else
            {
            	out.write(new String(xmlInfo.getBytes("GBK")));
            }
            out.flush();
            out.close();
            
            // 获取接口响应
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
            	br = new BufferedReader(new InputStreamReader(con.getInputStream(), getEncodingForEbus()));
            }
            else
            {
            	br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String line = "";
            StringBuffer rspXML = new StringBuffer();
            for (line = br.readLine(); line != null; line = br.readLine())
            {
                rspXML.append(line);
            }
            
            String rspMsg = rspXML.toString();
            
            if (logger.isInfoEnabled())
            {
                logger.info("response message: " + rspMsg);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("response message: " + (rspMsg.length() > 1024 ? rspMsg.substring(0, 1024) : rspMsg));
            }
            
          //add begin zWX176560 OR_SD_201308_934 R003C13L09n01          
          //报文返回时间
          String endTime = sdf.format(new Date());
          
          //受理时长
          int acceptTime = (int)(sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime());
          
          busiAcceptTimePO.setStartTime(startTime.substring(0, 19));
          busiAcceptTimePO.setEndTime(endTime.substring(0,19));
          busiAcceptTimePO.setAcceptTime(acceptTime);
          
          // modify begin lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
          // openEbus返回的是json格式，这里再转成xml，同时重新组装报文格式，便于后续统一处理
          if(CommonUtil.isInvokeOpenEbus(businessId))
          {
              rspMsg = parseRespForOpenEbus(rspMsg, businessId);
              logger.info("转换后最终的响应报文:" + rspMsg);
          }
          // modify end lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
          
          ReturnWrap rw = null;
          
          // 针对账单查询单独处理返回报文
          if(BusinessIdConstants.CLI_QRY_BILL2012_SD.equals(businessId))
          {
              rw = parseResponse_MonthFee(rspMsg);
          }
          else
          {
              rw = parseResponse(rspMsg);
          }
          
          //判断业务是否受理成功
          if(rw.getStatus()==SSReturnCode.SUCCESS)
          {
              busiAcceptTimePO.setStatus(1);
          }
          else
          {
              busiAcceptTimePO.setStatus(0);
          }

          try
          {
              //将受理时长插入数据库
              // modify begin qWX279398 2015-12-24 OR_SD_201511_596 线程插入受理时长表日志
              //this.busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
              // 省份判断
              if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
              {
                  CommonUtil.insertBusiAcceptTime(busiAcceptTimePO);
              }
              else
              {
                  this.busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
              }
              // modify end qWX279398 2015-12-24 OR_SD_201511_596 线程插入受理时长表日志
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }

          // 解析返回报文
          return rw;
          
          //add end zWX176560 OR_SD_201308_934 R003C13L09n01
            
        }
        catch (MalformedURLException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
            e.printStackTrace();
            
            // 上面的e.printStackTrace()有时不管用，那我们自己再打印一个
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error(sw.toString());
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        finally
        {
            closeStream(con, br, out);
        }
        
        return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "系统发生异常，请稍后再试。带来不便，敬请原谅。");
    }

    /** 
     * openEbus返回的是json格式，这里再转成xml，同时重新组装报文格式，便于后续统一处理
     * 
     * @param rspMsg
     * @param businessId
     * @return
     * @throws DocumentException
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    private String parseRespForOpenEbus(String rspMsg, String businessId) throws DocumentException
    {
        // json转成xml
        String rspXml = CommonUtil.convertJsonToXml(rspMsg, businessId);
        
        Document responseDoc = DocumentHelper.parseText(rspXml);
        Element responseRoot = responseDoc.getRootElement();
        
        // 组装通用格式的返回报文
        Document doc = DocumentHelper.createDocument();
        
        // 根节点
        Element message_response = doc.addElement("message_response");
        
        // 报文头
        Element message_head = message_response.addElement("message_head");
        Element retinfo = message_head.addElement("retinfo");
        
        // 返回码
        Element retcode = retinfo.addElement("retcode");
        retcode.setText(responseRoot.elementTextTrim("retcode"));
        
        // 返回信息
        Element retmsg = retinfo.addElement("retmsg");
        retmsg.setText(responseRoot.elementTextTrim("retmsg"));
        
        // 报文体
        if(null != responseRoot.element("message_content"))
        {
            Element message_body = message_response.addElement("message_body");
            message_body.addCDATA(responseRoot.element("message_content").asXML());
        }
        
        // 组装结束
        return doc.asXML();
    }
    
    /** 
     * 接口平台调用
     * 
     * @param processCode 业务代码
     * @param msgHeader 信息头部
     * @param msgBody 信息体部
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invoke(String processCode, MsgHeaderPO msgHeader, Document msgBody)
    {
        Document docXML = this.createMsg(msgBody, processCode, msgHeader.getMenuId(), msgHeader.getUniContact(), 
            msgHeader.getRouteType(), msgHeader.getRouteValue(), msgHeader.getOperId(), msgHeader.getTermId(), 
            msgHeader.getChannelId(), msgHeader.getVerifyCode());
        
        return this.invoke(docXML);
    }
    
    /**
     * 关闭流
     * <功能详细描述>
     * @param con
     * @param br
     * @param out
     * @see [类、类#方法、类#成员]
     */
    public void closeStream(HttpURLConnection con, BufferedReader br, OutputStreamWriter out)
    {
        try
        {
            if (out != null)
            {
                out.close();
            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        
        try
        {
            if (br != null)
            {
                br.close();
            }
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        
        if (con != null)
        {
            con.disconnect();
        }
    }
    
    /**
     * 自助终端全流程接入EBUS改造_湖北统一接口平台转EBUS开关
     * 
     * @param curMenuId 当前菜单id
     * @return boolean
     * @remark create by hWX5316476 2014/04/14 OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
    public static boolean isTransEBUS(String interfaceId)
    {
    	// 湖北统一接口平台转EBUS开关（1：开启  0：关闭）
        String transEBUSSwitch = (String) PublicCache.getInstance().getCachedData(Constants.SH_TRANS_EBUS_SWITCH);
    	
    	// 统一接口平台转EBUS总开关不开启
    	if(!"1".equals(transEBUSSwitch))
    	{
    		return false;
    	}
    	
    	// 使用统一接口平台转EBUS接口区分开关（1：开启  0：关闭）
    	String ebusInterfaceSwitch = (String) PublicCache.getInstance().getCachedData(Constants.SH_EBUS_INTERFACE_SWITCH);
    	
    	// 使用统一接口平台转EBUS菜单区分开关开启
    	if("1".equals(ebusInterfaceSwitch))
    	{
    		// 支持统一接口平台转接口列表
	    	List<DictItemPO> actInterfaceList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.EBUS_INTERFACE_LIST);
	    	
	    	if (actInterfaceList != null)
	    	{
	    		for (int i = 0; i < actInterfaceList.size(); i++)
	    	    {
	    	        DictItemPO dictItemPO = actInterfaceList.get(i);
	    	        if (interfaceId.equals(dictItemPO.getDictid()))
	    	        {
	    	            return true;
	    	        }
	    	    }
	    	}
	    	return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    /** 
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 
     * @param msgHeader 报文头部
     * @param paramMap 参数
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invokeDubbo(String interfaceId, MsgHeaderPO msgHeader, Map paramMap)
    {
        return this.invokeDubbo(interfaceId, msgHeader, paramMap, null, null);
    }
    
    /** 
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 
     * @param msgHeader 报文头部
     * @param paramMap 参数
     * @param ifParseResponseMsg 是否解析响应报文
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invokeDubbo(String interfaceId, MsgHeaderPO msgHeader, Map paramMap, boolean ifParseResponseMsg)
    {
        return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, msgHeader.getMenuId(),
            msgHeader.getUniContact(), msgHeader.getRouteType(), msgHeader.getRouteValue(), msgHeader.getOperId(), 
            msgHeader.getTermId(), sdf, transID), null, null, ifParseResponseMsg);
    }
    
    /** 
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 
     * @param msgHeader 报文头部
     * @param paramMap 参数
     * @param arrAttrsKey 数组中对象属性Key列表
     *      取得列表中的键值，例如：new String[] {"description", "dictID", "dictName"}，依据此顺序放入crset中
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invokeDubbo(String interfaceId, MsgHeaderPO msgHeader, Map paramMap, String[] arrAttrsKey)
    {
        return this.invokeDubbo(interfaceId, msgHeader, paramMap, null, arrAttrsKey);
    }
    
    /** 
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 
     * @param msgHeader 报文头部
     * @param paramMap 参数
     * @param objAttrsKey 对象属性Key列表
     *      取得属性的键值，例如new String[][] {{"retMsg", "retCode"}, {"retMsg2", "retCode2"}}，依据数组0中的键值取值，然后依据数组1中的键值放入TagSet中
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invokeDubbo(String interfaceId, MsgHeaderPO msgHeader, Map paramMap,String[][] objAttrsKey)
    {
        return this.invokeDubbo(interfaceId, msgHeader, paramMap, objAttrsKey, null);
    }
    
    /** 
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 
     * @param msgHeader 报文头部
     * @param paramMap 参数
     * @param objAttrsKey 对象属性Key列表
     *      取得属性的键值，例如new String[][] {{"retMsg", "retCode"}, {"retMsg2", "retCode2"}}，依据数组0中的键值取值，然后依据数组1中的键值放入TagSet中
     * @param arrAttrsKey 数组中对象属性Key列表
     *      取得列表中的键值，例如：new String[] {"description", "dictID", "dictName"}，依据此顺序放入crset中
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/08/21 R003C14L08n01 OR_huawei_201408_149 代码重复率_自助终端（二阶段）
     */
    public ReturnWrap invokeDubbo(String interfaceId, MsgHeaderPO msgHeader, Map paramMap, String[][] objAttrsKey, String[] arrAttrsKey)
    {
        return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, msgHeader.getMenuId(),
            msgHeader.getUniContact(), msgHeader.getRouteType(), msgHeader.getRouteValue(), msgHeader.getOperId(), 
            msgHeader.getTermId(), sdf, transID), objAttrsKey, arrAttrsKey, true);
    }
    
    /**
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识  
     * @param menuid  菜单ID
     * @param unicontact 统一接触流水
     * @param route_type  路由类型
     * @param route_value  路由值
     * @param operatorid 操作员id
     * @param atsvNum  终端机
     * @param paramMap 入参map
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(String interfaceId, String menuid,
		String unicontact, String route_type, String route_value,
		String operatorid, String atsvNum, Map paramMap)
	{
		return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, menuid,
			unicontact, route_type, route_value, operatorid, atsvNum, sdf,
			transID));
	}
    
    /**
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识  
     * @param menuid  菜单ID
     * @param unicontact 统一接触流水
     * @param route_type  路由类型
     * @param route_value  路由值
     * @param operatorid 操作员id
     * @param atsvNum  终端机
     * @param paramMap 入参map
     * @param objAttrsKey 对象属性Key列表
     * 		取得属性的键值，例如new String[][] {{"retMsg", "retCode"}, {"retMsg2", "retCode2"}}，依据数组0中的键值取值，然后依据数组1中的键值放入TagSet中
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(String interfaceId, String menuid,
		String unicontact, String route_type, String route_value,
		String operatorid, String atsvNum, Map paramMap,
		String[][] objAttrsKey)
	{
		return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, menuid,
			unicontact, route_type, route_value, operatorid, atsvNum, sdf,
			transID), objAttrsKey, null, true);
	}
	
	/**
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识  
     * @param menuid  菜单ID
     * @param unicontact 统一接触流水
     * @param route_type  路由类型
     * @param route_value  路由值
     * @param operatorid 操作员id
     * @param atsvNum  终端机
     * @param paramMap 入参map
     * @param arrAttrsKey 数组中对象属性Key列表
     * 		取得列表中的键值，例如：new String[] {"description", "dictID", "dictName"}，依据此顺序放入crset中
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(String interfaceId, String menuid,
		String unicontact, String route_type, String route_value,
		String operatorid, String atsvNum, Map paramMap,
		String[] arrAttrsKey)
	{
		return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, menuid,
			unicontact, route_type, route_value, operatorid, atsvNum, sdf,
			transID), null, arrAttrsKey, true);
	}
	
	/**
     * 发送请求到Dubbo服务
     * 
     * @param interfaceId 接口标识 接口名与OPCODE有区别
     * @param menuid  菜单ID
     * @param unicontact 统一接触流水
     * @param route_type  路由类型
     * @param route_value  路由值
     * @param operatorid 操作员id
     * @param atsvNum  终端机
     * @param paramMap 入参map
     * @param objAttrsKey 对象属性Key列表
     * 		取得属性的键值，例如new String[][] {{"retMsg", "retCode"}, {"retMsg2", "retCode2"}}，依据数组0中的键值取值，然后依据数组1中的键值放入TagSet中
     * @param arrAttrsKey 数组中对象属性Key列表
     * 		取得列表中的键值，例如：new String[] {"description", "dictID", "dictName"}，依据此顺序放入crset中
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(String interfaceId, String menuid,
		String unicontact, String route_type, String route_value,
		String operatorid, String atsvNum, Map paramMap,
		String[][] objAttrsKey, String[] arrAttrsKey)
	{
		return dubboInvokeUtil.invokeDubbo(dubboInvokeUtil.createDubboInParamBean(paramMap, interfaceId, menuid,
			unicontact, route_type, route_value, operatorid, atsvNum, sdf,
			transID), objAttrsKey, arrAttrsKey, true);
	}
	
	/**
     * 解析应答报文
     * 
     * @param outJsonParam Json格式的应答报文
     * @param objAttrsKey 对象属性Key列表
     * @param arrAttrsKey 数组中对象属性Key列表
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap parseJsonResponse(String outJsonParam,
		String[][] objAttrsKey, String[] arrAttrsKey) 
	{
		// 返回值ReturnWrap
    	ReturnWrap rtData = dubboInvokeUtil.parseJsonResponse(outJsonParam, objAttrsKey, arrAttrsKey);
    	
    	// 设置返回信息
    	rtData.setStatus(SSReturnCode.SUCCESS);
    	rtData.setReturnMsg("Processing the request succeeded!");
    	
    	return rtData;
	}
    
    /**
     * 主体产品变更获取模板明细时需要获取单独处理
     * @param docXML
     * @return
     */
    public ReturnWrap invokeProdChg(Document docXML)
    {
        HttpURLConnection con = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try
        {
            String url = getServerURL();
            if (!"".equals(url))
            {
                serverURL = url;
            }
            
            // 获取连接实例
            con = IntMsgUtil.getConnection(serverURL);
            
            String xmlInfo = docXML.asXML();
            
            if (logger.isWarnEnabled())
            {
                logger.warn("request message: " + xmlInfo);
            }
            
            out = new OutputStreamWriter(con.getOutputStream());
            
            // 接口调用
            out.write(new String(xmlInfo.getBytes("GBK")));
            out.flush();
            out.close();
            
            // 获取接口响应
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            StringBuffer rspXML = new StringBuffer();
            for (line = br.readLine(); line != null; line = br.readLine())
            {
                rspXML.append(line.trim());
            }
            
            String retXml = rspXML.toString();
            if (logger.isInfoEnabled())
            {
                logger.info("response message: " + retXml);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("response message: " + (retXml.length() > 1024 ? retXml.substring(0, 1024) : retXml));
            }
            
            //if (retXml.indexOf("<detail>") > 0)
            if (retXml.contains("<detail>"))  
            {
                retXml = retXml.substring(retXml.indexOf("<detail>"), retXml.indexOf("</detail>") + 9); 
                
                ReturnWrap rw = new ReturnWrap();
                rw.setReturnObject(retXml);
                return rw;
            }
            
            // 解析返回报文
            return parseResponse(retXml);
            
        }
        catch (MalformedURLException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        finally
        {
//            try
//            {
//                if (out != null)
//                {
//                    out.close();
//                }
//            }
//            catch (IOException e)
//            {
//                logger.error(e);
//            }
//            
//            try
//            {
//                if (br != null)
//                {
//                    br.close();
//                }
//            }
//            catch (IOException e)
//            {
//                logger.error(e);
//            }
//            
//            if (con != null)
//            {
//                con.disconnect();
//            }
            this.closeStream(con, br, out);
        }
        
        return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "系统发生异常，请稍后再试。带来不便，敬请原谅。");
    }
    
    /**
     * 新接口平台调用
     * 
     * @return String
     * 
     * 根据region取URL地址
     */
    public String getServerURL()
    {
        // 接口平台地址
        String fcgiUrl = "";
        
    	// 终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO) ServletActionContext.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    	if (termInfo != null)
    	{
    	    fcgiUrl = (String) PublicCache.getInstance().getCachedData("SH_FCGIURL_" + termInfo.getRegion());
    	}
    	
    	// 校验
    	if (fcgiUrl == null || "".equals(fcgiUrl))
    	{
    	    logger.error("未能获取到统一接口平台地址，终端机region：" + (termInfo == null ? "" : termInfo.getRegion()));
    		
    	    return "";
    	}
    	else if (logger.isInfoEnabled())
    	{
    	    logger.info("统一接口平台地址：" + fcgiUrl);
    	}
    	
    	return fcgiUrl;
    }
    
    /**
     * 解析响应报文
     * 
     * @param response
     * @return ReturnWrap
     * 
     * 将响应报文解析成ReturnWrap对象
     */
    public ReturnWrap parseResponse_MonthFee(String response) throws Exception
    {
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        Element eHead = root.element("message_head");
        
        // String resType = null;
        String rspCode = null;
        String rspDesc = null;
        
        ReturnWrap rtData = new ReturnWrap();
        CTagSet tagSet = new CTagSet();
        CRSet rSet = new CRSet();
        
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        // 解析信息头，保存错误码和错误信息
        if (eHead != null)
        {
            
            Element eRetinfo = eHead.element("retinfo");
            
            // resType = eRetinfo.element("rettype").getText();
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            // 状态
            rtData.setStatus(1);
            // 如果错误码不是0，状态设为0
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                if (logger.isErrorEnabled() && !logger.isInfoEnabled())
                {
                    logger.error("responseMessage: " + response);
                }
                
                // rspDesc = getErrorMessage(resType, rspCode, rspDesc);
                rtData.setStatus(0);
                rtData.setErrcode(new Integer(rspCode).intValue());
            }
            else
            {
                rtData.setErrcode(100);
            }
            // 错误信息
            rtData.setReturnMsg(rspDesc);
        }
        
        Element ebody = root.element("message_body");
        if (ebody == null)
        {
            return rtData;
        }

        String rspCont = ebody.getText();
        Document body = DocumentHelper.parseText(rspCont);
        Iterator itBody = body.getRootElement().elementIterator();
        
        // 返回对象
        Map<String,Object> map = new HashMap<String,Object>();
        
        while (itBody.hasNext())
        {
            Element ecustbill = (Element)itBody.next();
            
            // 费用详细信息
            Element ebillinfo = ecustbill.element("billinfo");
            if (ebillinfo != null)
            {
                // 费用信息
                Element ebillfixed = ebillinfo.element("billfixed");
                
                // 返回 list<FeePO/FeeGroupPO>
                List<Object> obj_billfixed = AccountUtil.parseBillfixed(ebillfixed);
                map.put("billfixed", obj_billfixed);
                
                // 本月费用合计
                String parseBillfixed_hj = AccountUtil.parseBillfixed_hj(ebillfixed);
                map.put("parseBillfixed_hj", parseBillfixed_hj);
                
                // 自有业务
                Element efeedetails = ebillinfo.element("feedetails");
                
                // 双排数据
                List<FeedetailPO> obj_feedetails = AccountUtil.parseFeedetails(efeedetails);
                map.put("feedetails", obj_feedetails);
                
                // 单排数据
                List<FeedetailPO> obj_feedetails_ = AccountUtil.parseFeedetails_(efeedetails);
                map.put("feedetails_", obj_feedetails_);
            }
            
            // 账户概要信息
            Element eacctbalance = ecustbill.element("acctbalance");
            if (eacctbalance != null)
            {
                // 账户概要信息
                Element eacct = eacctbalance.element("acct");
                // Map<String,String>
                Map<String,String> obj = AccountUtil.parseMap(eacct);
                map.put("acctbalance", obj);
                
            }
            
            // 资费推荐
            Element erecommend = ecustbill.element("recommend");
            if (erecommend != null)
            {
                String obj = erecommend.getText();
                map.put("recommend", obj);
            }
            
            // 积分备注
            Element scoreremark = ecustbill.element("scoreremark");
            if (scoreremark != null)
            {
                String obj = scoreremark.getText();
                map.put("scoreremark", obj);
            }
            
            // 感谢语备注
            Element acknowledgement = ecustbill.element("acknowledgement");
            if (acknowledgement != null)
            {
            	String obj = acknowledgement.getText();
            	map.put("acknowledgement", obj);
            }
            
            // 半年消费趋势图
            Element ebillhalfyeartrend = ecustbill.element("billhalfyeartrend");
            if (eacctbalance != null)
            {
                List<BillPO> obj = AccountUtil.parseBillhalfyeartrend(ebillhalfyeartrend);
                map.put("billhalfyeartrend", obj);
            }
            
            // 积分信息
            Element escoreinfo = ecustbill.element("scoreinfo");
            if (escoreinfo != null)
            {
                // 账户概要信息
                Element scoreinfoElement = escoreinfo.element("score");
                if (scoreinfoElement != null)
                {
	                Map<String,String> obj = AccountUtil.parseMap(scoreinfoElement);
	                map.put("scoreinfo", obj);
                }
            }
            
            // 通讯量使用明细
            Element epkginfo = ecustbill.element("pkginfo");
            if (epkginfo != null)
            {
                // 套餐信息
                //Element pkgElement = epkginfo.element("pkg");
                List<PkgPO> obj_pkg = AccountUtil.parsePkg(epkginfo);
                map.put("pkg", obj_pkg);
                
                // 汇总信息
                List<PkgPO> obj_total = AccountUtil.parseTotal(epkginfo);
                map.put("total", obj_total);
            }
            
            // 协议款信息
            Element eagreementinfo = ecustbill.element("agreementinfo");
            if (eagreementinfo != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(eagreementinfo,"agreement");
                map.put("agreementinfo", obj);
            }
            
            // 赠送款信息
            Element epresentinfo = ecustbill.element("presentinfo");
            if (epresentinfo != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(epresentinfo,"present");
                map.put("presentinfo", obj);
            }
            
            // 他人代付信息
            Element epayedbyother = ecustbill.element("payedbyother");
            if (epayedbyother != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(epayedbyother,"payinfo");
                map.put("payedbyother", obj);
            }
            
            // 代他人付信息
            Element epayedforother = ecustbill.element("payedforother");
            if (epayedforother != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(epayedforother,"payinfo");
                map.put("payedforother", obj);
            }
            
            // 代收费业务
            Element espbill = ecustbill.element("spbill");
            if (espbill != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(espbill,"sp");
                map.put("spbill", obj);
            }
            
            // 账本入账明细
            Element einoutdetail = ecustbill.element("inoutdetail");
            if (einoutdetail != null)
            {
                List<Map> obj = AccountUtil.parseListMap(einoutdetail,"inout");
                map.put("inoutdetail", obj);
                
            }
        }
        
        if (map.size() > 0)
        {
            rtData.setReturnObject(map);
        }
        
        // 返回
        return rtData;
        
    }
    
    /**
     * 新接口平台调用
     * 
     * @param docXML 请求报文
     * @return ReturnWrap
     * 
     * 根据请求报文调用新接口平台
     */
    public ReturnWrap invoke_MonthFee_NX(Document docXML)
    {
        HttpURLConnection con = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try
        {
            String url = getServerURL();
            if (!"".equals(url))
            {
                serverURL = url;
            }
            
            // 获取连接实例
            con = IntMsgUtil.getConnection(serverURL);
            
            String xmlInfo = docXML.asXML();
            
            if (logger.isWarnEnabled())
            {
                logger.warn("request message: " + xmlInfo);
            }
            
            out = new OutputStreamWriter(con.getOutputStream());
            
            // 接口调用
            out.write(new String(xmlInfo.getBytes("GBK")));
            out.flush();
            out.close();
            
            // 获取接口响应
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            StringBuffer rspXML = new StringBuffer();
            for (line = br.readLine(); line != null; line = br.readLine())
            {
                rspXML.append(line);
            }
            
            String rspMsg = rspXML.toString();
            if (logger.isInfoEnabled())
            {
                logger.info("response message: " + rspMsg);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("response message: " + (rspMsg.length() > 1024 ? rspMsg.substring(0, 1024) : rspMsg));
            }
            
            // 解析返回报文
            return parseResponse_MonthFee_NX(rspMsg);
        }
        catch (MalformedURLException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            logger.error(e);
        }
        finally
        {
//            try
//            {
//                out.close();
//                br.close();
//                con.disconnect();
//            }
//            catch (Exception ex)
//            {
//                logger.error(ex.getMessage());
//            }
            this.closeStream(con, br, out);
        }
        
        return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "系统发生异常，请稍后再试。带来不便，敬请原谅。");
    }
    
    /**
     * 解析响应报文
     * 
     * @param response
     * @return ReturnWrap
     * 
     * 将响应报文解析成ReturnWrap对象
     */
    public ReturnWrap parseResponse_MonthFee_NX(String response) throws Exception
    {
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        Element eHead = root.element("message_head");
        
        // String resType = null;
        String rspCode = null;
        String rspDesc = null;
        
        ReturnWrap rtData = new ReturnWrap();
        CTagSet tagSet = new CTagSet();
        CRSet rSet = new CRSet();
        
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        // 解析信息头，保存错误码和错误信息
        if (eHead != null)
        {
            
            Element eRetinfo = eHead.element("retinfo");
            
            // resType = eRetinfo.element("rettype").getText();
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            // 状态
            rtData.setStatus(1);
            // 如果错误码不是0，状态设为0
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                // rspDesc = getErrorMessage(resType, rspCode, rspDesc);
                rtData.setStatus(0);
                rtData.setErrcode(new Integer(rspCode).intValue());
            }
            else
            {
                rtData.setErrcode(100);
            }
            // 错误信息
            rtData.setReturnMsg(rspDesc);
        }
        
        Element ebody = root.element("message_body");
        if (ebody == null)
        {
            return rtData;
        }

        String rspCont = ebody.getText();
        Document body = DocumentHelper.parseText(rspCont);
        Iterator itBody = body.getRootElement().elementIterator();
        
        // 返回对象
        Map<String,Object> map = new HashMap<String,Object>();
        
        while (itBody.hasNext())
        {
            Element ecustbill = (Element)itBody.next();
            
            // 费用详细信息
            Element ebillinfo = ecustbill.element("billinfo");
            if (ebillinfo != null)
            {
                // 费用信息
                Element ebillfixed = ebillinfo.element("billfixed");
                List<Object> obj_billfixed = AccountUtil.parseBillfixed(ebillfixed);
                map.put("billfixed", obj_billfixed);
                
                // 自有业务
                Element efeedetails = ebillinfo.element("feedetails");
                
                // 双排数据
                List<FeedetailPO> obj_feedetails = AccountUtil.parseFeedetails(efeedetails);
                map.put("feedetails", obj_feedetails);
                
                // 单排数据
                List<FeedetailPO> obj_feedetails_ = AccountUtil.parseFeedetails_(efeedetails);
                map.put("feedetails_", obj_feedetails_);
            }
            
            // 账户概要信息
            Element eacctbalance = ecustbill.element("acctbalance");
            if (eacctbalance != null)
            {
                Map obj = AccountUtil.parseAcctbalance_NX(eacctbalance);
                map.put("acctbalance", obj);
                
            }
            
            // 资费推荐
            Element erecommend = ecustbill.element("recommend");
            if (erecommend != null)
            {
                String obj = erecommend.getText();
                map.put("recommend", obj);
            }
            
            // 半年消费趋势图
            Element ebillhalfyeartrend = ecustbill.element("billhalfyeartrend");
            if (eacctbalance != null)
            {
                List<BillPO> obj = AccountUtil.parseBillhalfyeartrend(ebillhalfyeartrend);
                map.put("billhalfyeartrend", obj);
            }
            
            // 积分信息
            Element escoreinfo = ecustbill.element("scoreinfo");
            if (escoreinfo != null)
            {
                Element scoreinfoElement = escoreinfo.element("score");
                if (scoreinfoElement != null)
                {
                    Map<String,String> obj = AccountUtil.parseMap(scoreinfoElement);
                    map.put("scoreinfo", obj);
                }
            }
            
            // 通讯量使用明细
            Element epkginfo = ecustbill.element("pkginfo");
            if (epkginfo != null)
            {
                // 套餐信息
                List<PkgPO> obj_pkg = AccountUtil.parsePkg(epkginfo);
                map.put("pkg", obj_pkg);
            }
            
            // 代收费业务
            Element espbill = ecustbill.element("spbill");
            if (espbill != null)
            {
                List<Map> obj = (List<Map>)AccountUtil.parseListMap(espbill,"sp");
                map.put("spbill", obj);
            }
            
            // 账本入账明细
            Element einoutdetail = ecustbill.element("inoutdetail");
            if (einoutdetail != null)
            {
                List<Map> obj = AccountUtil.parseListMap(einoutdetail,"inout");
                map.put("inoutdetail", obj);
            }
        }
        
        if (map.size() > 0)
        {
            rtData.setReturnObject(map);
        }
        
        // 返回
        return rtData;
        
    }
    
    /**
     * 创建XML请求报文
     * 营销活动银联卡缴费，channelid特殊，值为：bsacAtsvCard
     * @param ...
     * @return
     */
    public Document createMsgCard(Document docContent, String process_code, String menuid, String unicontact,
            String route_type, String route_value, String operatorid, String atsvNum)
    {
        return this.createMsg(docContent, process_code, menuid, unicontact, route_type, route_value, 
            operatorid, atsvNum, "bsacAtsvCard", null);
    }
    
    /**
     * <湖北自助终端直接调用一级BOSS接口>
     * <湖北EBUS编码格式为GBK，而一级boss接口为UTF-8,故不能通过ebus调用一级boss接口，只能直接调用，添加此http post调用方法>
     * @param bossUrl 一级BOSS调用url，
     * @param reqBody 请求报文
     * @param charsetName 编码格式,比如：UTF-8
     * @return 接口返回报文
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-21 14:05:27 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
     */
    public String httpInvoke(String bossUrl, Document reqBody, String charsetName) throws Exception
    {
    	//请求url
    	//logger.info("request url: " + bossUrl);
    	//请求报文
    	logger.info("request message: " + reqBody.asXML());
    	
    	//创建请求连接
        HttpURLConnection con = IntMsgUtil.getConnection(bossUrl);
        con.connect();

        //输出流，发送请求报文
		OutputStreamWriter out = null;
		out = new OutputStreamWriter(con.getOutputStream());
		out.write(new String(reqBody.asXML().getBytes(charsetName)));
		out.flush();
		
		//输入流
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(con.getInputStream(),charsetName));
		
        String line = "";
        StringBuffer rspXML = new StringBuffer();
        
        //组装请求报文
        while ((line = br.readLine()) != null)
        {
            rspXML.append(line);
        }
        
        //返回报文
    	logger.info("reponse message: " + rspXML.toString());
    	
    	//断开连接
    	closeStream(con, br, out);
        
    	return rspXML.toString();
    }
    
    /**
     * 与能开对接的时候content_type的字符集编码
     * 
     * @return
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static String getEncodingForEbusContentType()
    {
        return CommonUtil.getParamValue("ENCODING_FOREBUS_CONTENTTYPE", "GBK");
    }
    
    /**
     * 获取调用能开的字符集编码
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getEncodingForEbus()
    {
        return CommonUtil.getParamValue("ENCODING_FOREBUS", "GBK");
    }
    /**
     * 调用openEbus获取token
     * 
     * @return
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    private String getToken()
    {
        // 上次将token放入缓存的时间，用于判断是否已过期，过期的话需要重新调用接口查询
        String tokenDate = CommonUtil.getParamValue("openEbusTokenDate");
        
        // 获取缓存中token数据
        String token = CommonUtil.getParamValue("openEbusToken");
        
        // 配置的token有效期，单位分钟。默认59分钟。
        String tokenDelay = CommonUtil.getParamValue("openEbusTokenDelay", "59");
        
        try
        {
            // 若缓存中没有token数据，或者已超期，则重新调接口查询
            if(StringUtils.isBlank(tokenDate) || StringUtils.isBlank(token) || Integer.parseInt(DateUtil.dateC(tokenDate)) >= Integer.parseInt(tokenDelay))
            {
                token = CommonUtil.getOpenEbusToken();
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return token;
    }
    
    /**
     * 组装调用openEbus的请求报文
     * 
     * @param docXML
     * @param busiAcceptTimePO
     * @return
     * @throws DocumentException 
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    private Document getDocXmlForOpenEbus(Document docXML, BusiAcceptTimePO busiAcceptTimePO) throws DocumentException
    {
        // 获取content节点
        Element message_body = docXML.getRootElement().element("message_body");
        String message_content = message_body.getData().toString();
        Document contentDoc = DocumentHelper.parseText(message_content);
        Element content = contentDoc.getRootElement();
        
        // 不要改变原请求报文格式,创建一份拷贝
        Element contentCopy = content.createCopy();
        
        // 字典项中dictname值为对应的服务和method，下划线隔开
        DictItemPO dict = CommonUtil.getDictItemById(busiAcceptTimePO.getBusinessId(), "openEbusInvokeList");
        
        if(null == dict)
        {
           throw new ReceptionException("获取字典配置失败");   
        }
        
        // 能开规范请求报文入参全部大写。遍历,转为大写
        Iterator<Element> iterator = contentCopy.elementIterator();
        while (iterator.hasNext())
        {
            Element element = iterator.next();
            element.setName(element.getName().toUpperCase(Locale.ENGLISH));
        }
        
        // 统一添加OPERID、REGION、TELNUM、CONTACTTYPE节点，若原来已有则不再重复添加
        if(null == contentCopy.element("OPERID"))
        {
            // 从请求报文头中获取OPERID之后添加
            DocumentUtil.addSubElementToEle(contentCopy, "OPERID", busiAcceptTimePO.getOperId());
        }
        if(null == contentCopy.element("TELNUM") && StringUtils.isNotBlank(busiAcceptTimePO.getServnum()))
        {
            DocumentUtil.addSubElementToEle(contentCopy, "TELNUM", busiAcceptTimePO.getServnum());
        }
        // 手机号码为空时才添加region，防止直接使用region路由，导致无法跨地市路由
        if(StringUtils.isBlank(busiAcceptTimePO.getServnum()) && null == contentCopy.element("REGION"))
        {
            DocumentUtil.addSubElementToEle(contentCopy, "REGION", busiAcceptTimePO.getRegion());
        }
        // 受理渠道
        if(null == contentCopy.element("CONTACTTYPE"))
        {
            DocumentUtil.addSubElementToEle(contentCopy, "CONTACTTYPE", Constants.CHANNEL_ID);
        }
        
        //add begin lWX431760 2017-10-17 OR_huawei_201708_806_【山东移动接口迁移专题】-客户身份认证获取随机密码 
        //版本号
        if (null == contentCopy.element("VERSION"))
        {
            //根据process_code和openEbusVersion取字典项，字典项中的值配置为版本号，取不到不传值
            String dictVersion = CommonUtil.getDictNameById(busiAcceptTimePO.getBusinessId(), "openEbusVersion");
            
            if(StringUtils.isNotBlank(dictVersion))
            {
                DocumentUtil.addSubElementToEle(contentCopy, "VERSION", dictVersion);
            }
        }
        //add end lWX431760 2017-10-17 OR_huawei_201708_806_【山东移动接口迁移专题】-客户身份认证获取随机密码 
        
        // 创建新的doc，并将content放进去
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("GBK");
        doc.add(contentCopy);
        
        return doc;
    }

    public BusiAcceptTimeService getBusiAcceptTimeServiceImpl()
    {
        return busiAcceptTimeServiceImpl;
    }

    public void setBusiAcceptTimeServiceImpl(BusiAcceptTimeService busiAcceptTimeServiceImpl)
    {
        this.busiAcceptTimeServiceImpl = busiAcceptTimeServiceImpl;
    }

	/**
	 * @return the dubboInvokeUtil
	 */
	public DubboInvokeUtil getDubboInvokeUtil() {
		return dubboInvokeUtil;
	}

	/**
	 * @param dubboInvokeUtil the dubboInvokeUtil to set
	 */
	public void setDubboInvokeUtil(DubboInvokeUtil dubboInvokeUtil) {
		this.dubboInvokeUtil = dubboInvokeUtil;
	}
}
