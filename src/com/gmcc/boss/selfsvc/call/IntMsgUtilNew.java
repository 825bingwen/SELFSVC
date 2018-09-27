/*
* @filename: IntMsgUtilNew.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  新版协议，报文创建、解析
* @author: g00140516
* @de:  2012/06/30 
* @description: 
* @remark: create g00140516 2012/06/30 R003C12L07n01 OR_huawei_201205_740
*/
package com.gmcc.boss.selfsvc.call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;
import com.gmcc.boss.selfsvc.busiAcceptTime.service.BusiAcceptTimeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 新版协议，报文创建、解析
 * 
 * @author  g00140516
 * @version  1.0, 2012/06/30
 * @see  
 * @since  
 */
public class IntMsgUtilNew
{
    private static Log logger = LogFactory.getLog(IntMsgUtilNew.class);
    
    /**
     * 格式化日期
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * 流水号
     */
    private static int transID = 1;
    
    //add begin zWX176560 OR_SD_201308_934 R003C13L09n01
    /**
     * 受理时长表操作service
     */
    private BusiAcceptTimeService busiAcceptTimeServiceImpl;

   //add end zWX176560 OR_SD_201308_934 R003C13L09n01
    
    /**
     * 创建http连接
     * 
     * @param urlStr 统一接入平台地址
     * @return
     * @throws Exception
     */
    public static HttpURLConnection getConnection(String urlStr) throws Exception
    {        
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        //modify begin fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_关于便利店配合was升级的改造需求需求分析设计说明书
        con.setRequestProperty("Pragma", "no-cache");
        //modify end fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_关于便利店配合was升级的改造需求需求分析设计说明书
       con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Content-Type", "text/xml");
        return con;
    }
    
    /**
     * 创建XML格式的请求报文
     * 
     * @param ...
     * @return
     */
    public Document createMsg(Map<String, String> paramMap)
    {        
        SAXReader saxReader = new SAXReader();
        Document document = null;
        InputStream is = null;
        
        try
        {
            // 读报文头
            is = IntMsgUtilNew.class.getClassLoader().getResourceAsStream("reqpkg.xml");
            
            document = saxReader.read(is);
            
            // 取根节点
            Element root = document.getRootElement();
            
            // 用paramMap中的信息组织报文
            handleElement(root, paramMap);
            
            return document;
        }
        catch (DocumentException e)
        {
            logger.error("创建请求报文头失败：", e);
        }
        catch (Exception e)
        {
            logger.error("创建请求报文头失败：", e);
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    logger.error("关闭流失败：", e);
                }
                
                is = null;
            }
        }
        
        return null;
    }
    
    /** 
     * 创建XML格式的请求报文
     * 
     * @param msgHeader 报文头信息
     * @return Document
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2014/09/09 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public Document createMsg(MsgHeaderPO msgHeader)
    {
        // 报文头参数值
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置当前菜单
        paramMap.put("menuid", msgHeader.getMenuId());
        
        // 业务唯一标识
        paramMap.put("process_code", msgHeader.getProcessCode());
        
        // 认证码
        paramMap.put("verify_code", msgHeader.getVerifyCode());
        
        // 设置客户接触id
        paramMap.put("unicontact", msgHeader.getUniContact());
        
        // 测试标记 可以为空；0表示交易为测试用；1表示正式交易
        paramMap.put("testflag", msgHeader.getTestFlag());
        
        // 路由 0：按照region 1：按照手机号码
        paramMap.put("route_type", msgHeader.getRouteType());
        
        // 路由值，按手机号路由传手机号码，按地区路由传region
        paramMap.put("route_value", msgHeader.getRouteValue());
        
        // 设置操作员id
        paramMap.put("operatorid", msgHeader.getOperId());
        
        // 渠道下级单位信息
        paramMap.put("unitid", msgHeader.getUnitId());
        
        // 设置客户手机号
        paramMap.put("telnum", msgHeader.getTelNum());
        
        // 设置终端机id
        paramMap.put("termid", msgHeader.getTermId());
        
        // 创建XML格式的请求报文
        return this.createMsg(paramMap);
    }
    
    /**
     * 统一接入平台接口调用
     * 
     * @param docXML
     * @return
     * @see 
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
        	// 获取连接实例
            con = IntMsgUtilNew.getConnection(getServerURL());
            
            // 取超时时间，默认5秒
            String invokeTime = (String) PublicCache.getInstance().getCachedData("SH_INVOKE_TIME");
            if (null == invokeTime || "".equals(invokeTime.trim()))
            {
                invokeTime = "50";
            }
            
            // 设置连接主机超时时间
            con.setConnectTimeout(Integer.parseInt(invokeTime) * 1000);
            
            // 设置从主机读取数据超时时间
            con.setReadTimeout(Integer.parseInt(invokeTime) * 1000);
            
            String xmlInfo = docXML.asXML();
            
            if (logger.isWarnEnabled())
            {
                logger.warn("request message: " + xmlInfo);
            }
            
            // 接口调用
            out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("GBK")));
            out.flush();
            out.close();
            
            // 获取应答
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
            
            //add begin zWX176560 OR_SD_201308_934 R003C13L09n01          
            //报文返回时间
            String endTime = sdf.format(new Date());
            
            //受理时长
            int acceptTime = (int)(sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime());
            
            busiAcceptTimePO.setStartTime(startTime.substring(0, 19));
            busiAcceptTimePO.setEndTime(endTime.substring(0,19));
            busiAcceptTimePO.setAcceptTime(acceptTime);
            
            ReturnWrap rw = parseResponse(rspMsg);
            
            //判断业务是否受理成功
            if(rw.getStatus()==SSReturnCode.SUCCESS)
            {
                busiAcceptTimePO.setStatus(1);
            }
            else
            {
                busiAcceptTimePO.setStatus(0);
            }
            
            //将受理时长插入数据库
            this.busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
              
            // 解析返回报文
            return rw;
            
            //add end zWX176560 OR_SD_201308_934 R003C13L09n01          
        }
        catch (MalformedURLException e)
        {
            logger.error("统一接入平台接口调用失败：", e);
        }
        catch (IOException e)
        {
            logger.error("统一接入平台接口调用失败：", e);
        }
        catch (Exception e)
        {
            logger.error("统一接入平台接口调用失败：", e);
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
//            catch (Exception e)
//            {
//                logger.error("关闭流失败：", e);
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
//                logger.error("关闭流失败：", e);
//            }
//            
//            if (con != null)
//            {
//                con.disconnect();
//            }
            this.closeStream(con, br, out);
        }
        
        return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "系统发生异常，请稍后再试。给您带来的不便，敬请原谅。");
    }
    
    /**
     * <一句话功能简述>
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
            logger.error("关闭流失败：", e);
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
            logger.error("关闭流失败：", e);
        }
        
        if (con != null)
        {
            con.disconnect();
        }
    }
    
    /**
     * 解析应答报文
     * 
     * @param response XML格式的应答报文
     * @return returnObject始终是Vector。第一个元素为CTagSet(如应答报文中无CTagSet，该值为null)；从第二个元素开始均为CRSet(如应答报文中有CRSet)。
     * @throws Exception
     * @see 
     */
    public ReturnWrap parseResponse(String response) throws Exception
    {
        ReturnWrap rtData = new ReturnWrap();
        
        // 格式：CTagSet, CRSet, CRSet, ...
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        
        // 验证码
        String verifyCode = "";
        
        // 统一接触
        String contactid = "";
        
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        
        // 应答报文头
        Element eHead = root.element("Header");
        if (eHead != null)
        {
            List list = eHead.elements();
            
            if (null == list || list.size() == 0)
            {
                return null;
            }
            
            // Header的直系子节点，如cli_qry_custinforeq
            Element reqElement = (Element) list.get(0);
            
            // 密码验证时由CRM生成并返回
            Element eVerifyCode = reqElement.element("verify_code");
            if (null != eVerifyCode)
            {
                verifyCode = eVerifyCode.getText();
            }            
            
            // 密码验证时由CRM生成并返回
            Element eUnicontact = reqElement.element("unicontact");
            if (null != eUnicontact)
            {
                contactid = eUnicontact.getText();    
            }                
            
            // 应答码、应答信息
            String rspCode = null;
            String rspDesc = null;           
            
            Element eRetinfo = reqElement.element("retinfo");            
            if (null != eRetinfo)
            {
                Element eRetcode = eRetinfo.element("retcode");
                if (null != eRetcode)
                {
                    rspCode = eRetcode.getText();
                }            
                
                Element eRetmsg = eRetinfo.element("retmsg");
                if (null != eRetmsg)
                {
                    rspDesc = eRetmsg.getText();    
                }  
            }
            
            // 成功
            rtData.setStatus(1);
            
            // 如果应答码不是0或者100，则失败
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                // 记录日志，主要是为了定位问题。如果是INFO级别，在invoke中已经记了，所以此处不需要重复记录。
                if (logger.isErrorEnabled() && !logger.isInfoEnabled())
                {
                    logger.error("responseMessage: " + response);
                }
                
                rtData.setStatus(0);
                rtData.setErrcode(new Integer(rspCode).intValue());
            }
            else
            {
                rtData.setErrcode(100);
            }
            
            rtData.setReturnMsg(rspDesc);
        }
        // 报文头是必须的，没有报文头，认为接口调用失败
        else
        {
            rtData.setStatus(0);
        }
        
        // 无应答报文体
        Element ebody = root.element("Body");
        if (ebody == null)
        {
            return rtData;
        }
        
        List list = ebody.elements();
        
        // 无应答报文体
        if (null == list || list.size() == 0)
        {
            return rtData;
        }
        
        /*
         * 报文样例：
         * <cli_qry_custinforesp>
         *  <tagset>
         *      <custname></custname>
         *  </tagset>
         *  <crset1>
         *      <col0></col0>
         *      <col1></col1>
         *  </crset1>
         *  <crset2>
         *      <col0></col0>
         *      <col1></col1>
         *  </crset2>
         * </cli_qry_custinforesp>
         */
        
        // Body的直系子节点，如cli_qry_custinforesp。该节点下可能包含一个tagset和多个crset
        Element rspElement = (Element) list.get(0);
        
        // rspElement的直系子节点
        Element subElement = null;
        String subElementName = "";

        // 解析crset时用
        List crsetList = null;
        Element crsetElement = null;
        
        // 解析tagset或crset时用
        List leafList = null;
        Element leafElement = null;
        
        // 记录已解析完的rspElement的子节点名称
        List<String> parsedElements = new ArrayList<String>();
        
        CTagSet tagSet = null;
        CRSet rSet = null;        
        
        // 解析应答报文体，将信息封装为CTagSet或CRSet
        Iterator itBody = rspElement.elementIterator();
        while (itBody.hasNext())
        {
            subElement = (Element) itBody.next();            
            subElementName = subElement.getName();
            
            // 解析为CTagSet
            if ("tagset".equalsIgnoreCase(subElementName))
            {
                tagSet = new CTagSet();
                
                // 取叶子节点列表
                leafList = subElement.elements();
                
                if (null != leafList && leafList.size() > 0)
                {
                    for (int m = 0; m < leafList.size(); m++)
                    {
                        leafElement = (Element) leafList.get(m);
                        
                        tagSet.SetValue(leafElement.getName(), leafElement.getText());
                    }
                }
            }
            // 解析为CRSet。只返回一个CRSet的话，节点名称为crset；如果返回多个CRSet，则节点名称依次为crset1、crset2...
            else if (subElementName.indexOf("crset") != -1 && !parsedElements.contains(subElementName))
            {
                // 取同名的所有节点
                crsetList = rspElement.elements(subElementName);
                
                for (int n = 0; n < crsetList.size(); n++)
                {
                    // 单个的crset节点
                    crsetElement = (Element) crsetList.get(n);
                     
                    // 取叶子节点列表
                    leafList = crsetElement.elements();
                    
                    if (null != leafList && leafList.size() > 0)
                    {
                        if (n == 0)
                        {
                            rSet = new CRSet(leafList.size());                            
                        }
                        
                        rSet.AddRow();
                        
                        for (int m = 0; m < leafList.size(); m++)
                        {
                            leafElement = (Element) leafList.get(m);
                            
                            rSet.SetValue(n, m, leafElement.getText());
                        }
                    }
                }
                
                // 名称为subElementName的节点均已解析结束
                parsedElements.add(subElementName);
                
                retVector.add(rSet);
            }           
        }
        
        // 如果tagSet不为空，保存验证码和统一接触
        if (null != tagSet && !tagSet.isEmpty())
        {
            tagSet.SetValue("verifyCode", verifyCode);
            tagSet.SetValue("contactid", contactid);               
        }
        
        // 第一个元素肯定是CTagSet
        retVector.add(0, tagSet);
        
        rtData.setReturnObject(retVector);
        
        return rtData;    
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
        Element eHead = root.element("Header").element("request_head");
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
     * 用paramMap中的信息组织报文
     * 
     * @param rootElement 请求报文根节点
     * @param paramMap 各报文节点对应的值
     * @see 
     */
    private static void handleElement(Element rootElement, Map<String, String> paramMap)
    {
        // 取子节点列表
        List<Element> list = (List<Element>) rootElement.elements();
        
        if (null != list && list.size() > 0)
        {
            Element subElement = null;
            String elementName = "";
            List<Element> subList = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                subElement = list.get(i);
                elementName = subElement.getName();
                
                // 取子节点列表
                subList = subElement.elements();
                
                // 请求时间
                if ("req_time".equalsIgnoreCase(elementName))
                {
                    synchronized (sdf)
                    {
                        subElement.setText(sdf.format(new Date()));
                    }
                }
                // 请求流水
                else if ("req_seq".equalsIgnoreCase(elementName))
                {
                    subElement.setText(String.valueOf(transID++));
                }
                // 渠道编码
                else if ("channelid".equalsIgnoreCase(elementName))
                {
                    subElement.setText("bsacAtsv");
                }
                // 如果有子节点，则递归处理
                else if (null != subList && subList.size() > 0)
                {
                    handleElement(subElement, paramMap);
                }
                // 如果没有子节点，则使用paramMap中的值填充报文
                else if (paramMap.containsKey(elementName))
                {                    
                    subElement.setText(paramMap.get(elementName));
                }
            }
        }        
    }
        
    /**
     * 获取统一接入平台地址
     * 
     * @return
     * @see 
     */
    private String getServerURL()
    {
        // 统一接入平台地址
        String fcgiUrl = "";
        
        // 根据region获取URL
        TerminalInfoPO termInfo = (TerminalInfoPO) ServletActionContext.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        if (termInfo != null)
        {
            fcgiUrl = (String) PublicCache.getInstance().getCachedData("SH_FCGIURL_NEW_" + termInfo.getRegion());
        }
        
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

    public BusiAcceptTimeService getBusiAcceptTimeServiceImpl()
    {
        return busiAcceptTimeServiceImpl;
    }

    public void setBusiAcceptTimeServiceImpl(BusiAcceptTimeService busiAcceptTimeServiceImpl)
    {
        this.busiAcceptTimeServiceImpl = busiAcceptTimeServiceImpl;
    }
    
    
}
