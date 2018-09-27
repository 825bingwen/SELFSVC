/*
* @filename: IntMsgUtilNew.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  �°�Э�飬���Ĵ���������
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
 * �°�Э�飬���Ĵ���������
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
     * ��ʽ������
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * ��ˮ��
     */
    private static int transID = 1;
    
    //add begin zWX176560 OR_SD_201308_934 R003C13L09n01
    /**
     * ����ʱ�������service
     */
    private BusiAcceptTimeService busiAcceptTimeServiceImpl;

   //add end zWX176560 OR_SD_201308_934 R003C13L09n01
    
    /**
     * ����http����
     * 
     * @param urlStr ͳһ����ƽ̨��ַ
     * @return
     * @throws Exception
     */
    public static HttpURLConnection getConnection(String urlStr) throws Exception
    {        
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        //modify begin fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_���ڱ��������was�����ĸ�����������������˵����
        con.setRequestProperty("Pragma", "no-cache");
        //modify end fwx439896 2017-10-12 V200R005C20LG2101 OR_SD_201710_102_���ڱ��������was�����ĸ�����������������˵����
       con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Content-Type", "text/xml");
        return con;
    }
    
    /**
     * ����XML��ʽ��������
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
            // ������ͷ
            is = IntMsgUtilNew.class.getClassLoader().getResourceAsStream("reqpkg.xml");
            
            document = saxReader.read(is);
            
            // ȡ���ڵ�
            Element root = document.getRootElement();
            
            // ��paramMap�е���Ϣ��֯����
            handleElement(root, paramMap);
            
            return document;
        }
        catch (DocumentException e)
        {
            logger.error("����������ͷʧ�ܣ�", e);
        }
        catch (Exception e)
        {
            logger.error("����������ͷʧ�ܣ�", e);
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
                    logger.error("�ر���ʧ�ܣ�", e);
                }
                
                is = null;
            }
        }
        
        return null;
    }
    
    /** 
     * ����XML��ʽ��������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return Document
     * @see [�ࡢ��#��������#��Ա]
     * @remark: create zKF69263 2014/09/09 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public Document createMsg(MsgHeaderPO msgHeader)
    {
        // ����ͷ����ֵ
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // ���õ�ǰ�˵�
        paramMap.put("menuid", msgHeader.getMenuId());
        
        // ҵ��Ψһ��ʶ
        paramMap.put("process_code", msgHeader.getProcessCode());
        
        // ��֤��
        paramMap.put("verify_code", msgHeader.getVerifyCode());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("unicontact", msgHeader.getUniContact());
        
        // ���Ա�� ����Ϊ�գ�0��ʾ����Ϊ�����ã�1��ʾ��ʽ����
        paramMap.put("testflag", msgHeader.getTestFlag());
        
        // ·�� 0������region 1�������ֻ�����
        paramMap.put("route_type", msgHeader.getRouteType());
        
        // ·��ֵ�����ֻ���·�ɴ��ֻ����룬������·�ɴ�region
        paramMap.put("route_value", msgHeader.getRouteValue());
        
        // ���ò���Աid
        paramMap.put("operatorid", msgHeader.getOperId());
        
        // �����¼���λ��Ϣ
        paramMap.put("unitid", msgHeader.getUnitId());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnum", msgHeader.getTelNum());
        
        // �����ն˻�id
        paramMap.put("termid", msgHeader.getTermId());
        
        // ����XML��ʽ��������
        return this.createMsg(paramMap);
    }
    
    /**
     * ͳһ����ƽ̨�ӿڵ���
     * 
     * @param docXML
     * @return
     * @see 
     */
    public ReturnWrap invoke(Document docXML)
    {
        
        //add begin zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
        //�������ͱ��Ļ�ȡ����Աid��businessId��·�ɷ�ʽ�����룬region
        BusiAcceptTimePO busiAcceptTimePO = new BusiAcceptTimePO();
        try
        {
            busiAcceptTimePO = this.parseRequest(docXML.asXML());
        }
        catch (Exception e1)
        {
            logger.error("�������ͱ����쳣��", e1);
        }
        
        // ���ķ���ʱ��
        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        String startTime = sdf.format(new Date());

        // ��ȡ�ն˻���Ϣ
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
        	// ��ȡ����ʵ��
            con = IntMsgUtilNew.getConnection(getServerURL());
            
            // ȡ��ʱʱ�䣬Ĭ��5��
            String invokeTime = (String) PublicCache.getInstance().getCachedData("SH_INVOKE_TIME");
            if (null == invokeTime || "".equals(invokeTime.trim()))
            {
                invokeTime = "50";
            }
            
            // ��������������ʱʱ��
            con.setConnectTimeout(Integer.parseInt(invokeTime) * 1000);
            
            // ���ô�������ȡ���ݳ�ʱʱ��
            con.setReadTimeout(Integer.parseInt(invokeTime) * 1000);
            
            String xmlInfo = docXML.asXML();
            
            if (logger.isWarnEnabled())
            {
                logger.warn("request message: " + xmlInfo);
            }
            
            // �ӿڵ���
            out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("GBK")));
            out.flush();
            out.close();
            
            // ��ȡӦ��
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
            //���ķ���ʱ��
            String endTime = sdf.format(new Date());
            
            //����ʱ��
            int acceptTime = (int)(sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime());
            
            busiAcceptTimePO.setStartTime(startTime.substring(0, 19));
            busiAcceptTimePO.setEndTime(endTime.substring(0,19));
            busiAcceptTimePO.setAcceptTime(acceptTime);
            
            ReturnWrap rw = parseResponse(rspMsg);
            
            //�ж�ҵ���Ƿ�����ɹ�
            if(rw.getStatus()==SSReturnCode.SUCCESS)
            {
                busiAcceptTimePO.setStatus(1);
            }
            else
            {
                busiAcceptTimePO.setStatus(0);
            }
            
            //������ʱ���������ݿ�
            this.busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
              
            // �������ر���
            return rw;
            
            //add end zWX176560 OR_SD_201308_934 R003C13L09n01          
        }
        catch (MalformedURLException e)
        {
            logger.error("ͳһ����ƽ̨�ӿڵ���ʧ�ܣ�", e);
        }
        catch (IOException e)
        {
            logger.error("ͳһ����ƽ̨�ӿڵ���ʧ�ܣ�", e);
        }
        catch (Exception e)
        {
            logger.error("ͳһ����ƽ̨�ӿڵ���ʧ�ܣ�", e);
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
//                logger.error("�ر���ʧ�ܣ�", e);
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
//                logger.error("�ر���ʧ�ܣ�", e);
//            }
//            
//            if (con != null)
//            {
//                con.disconnect();
//            }
            this.closeStream(con, br, out);
        }
        
        return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "ϵͳ�����쳣�����Ժ����ԡ����������Ĳ��㣬����ԭ�¡�");
    }
    
    /**
     * <һ�仰���ܼ���>
     * <������ϸ����>
     * @param con
     * @param br
     * @param out
     * @see [�ࡢ��#��������#��Ա]
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
            logger.error("�ر���ʧ�ܣ�", e);
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
            logger.error("�ر���ʧ�ܣ�", e);
        }
        
        if (con != null)
        {
            con.disconnect();
        }
    }
    
    /**
     * ����Ӧ����
     * 
     * @param response XML��ʽ��Ӧ����
     * @return returnObjectʼ����Vector����һ��Ԫ��ΪCTagSet(��Ӧ��������CTagSet����ֵΪnull)���ӵڶ���Ԫ�ؿ�ʼ��ΪCRSet(��Ӧ��������CRSet)��
     * @throws Exception
     * @see 
     */
    public ReturnWrap parseResponse(String response) throws Exception
    {
        ReturnWrap rtData = new ReturnWrap();
        
        // ��ʽ��CTagSet, CRSet, CRSet, ...
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        
        // ��֤��
        String verifyCode = "";
        
        // ͳһ�Ӵ�
        String contactid = "";
        
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        
        // Ӧ����ͷ
        Element eHead = root.element("Header");
        if (eHead != null)
        {
            List list = eHead.elements();
            
            if (null == list || list.size() == 0)
            {
                return null;
            }
            
            // Header��ֱϵ�ӽڵ㣬��cli_qry_custinforeq
            Element reqElement = (Element) list.get(0);
            
            // ������֤ʱ��CRM���ɲ�����
            Element eVerifyCode = reqElement.element("verify_code");
            if (null != eVerifyCode)
            {
                verifyCode = eVerifyCode.getText();
            }            
            
            // ������֤ʱ��CRM���ɲ�����
            Element eUnicontact = reqElement.element("unicontact");
            if (null != eUnicontact)
            {
                contactid = eUnicontact.getText();    
            }                
            
            // Ӧ���롢Ӧ����Ϣ
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
            
            // �ɹ�
            rtData.setStatus(1);
            
            // ���Ӧ���벻��0����100����ʧ��
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                // ��¼��־����Ҫ��Ϊ�˶�λ���⡣�����INFO������invoke���Ѿ����ˣ����Դ˴�����Ҫ�ظ���¼��
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
        // ����ͷ�Ǳ���ģ�û�б���ͷ����Ϊ�ӿڵ���ʧ��
        else
        {
            rtData.setStatus(0);
        }
        
        // ��Ӧ������
        Element ebody = root.element("Body");
        if (ebody == null)
        {
            return rtData;
        }
        
        List list = ebody.elements();
        
        // ��Ӧ������
        if (null == list || list.size() == 0)
        {
            return rtData;
        }
        
        /*
         * ����������
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
        
        // Body��ֱϵ�ӽڵ㣬��cli_qry_custinforesp���ýڵ��¿��ܰ���һ��tagset�Ͷ��crset
        Element rspElement = (Element) list.get(0);
        
        // rspElement��ֱϵ�ӽڵ�
        Element subElement = null;
        String subElementName = "";

        // ����crsetʱ��
        List crsetList = null;
        Element crsetElement = null;
        
        // ����tagset��crsetʱ��
        List leafList = null;
        Element leafElement = null;
        
        // ��¼�ѽ������rspElement���ӽڵ�����
        List<String> parsedElements = new ArrayList<String>();
        
        CTagSet tagSet = null;
        CRSet rSet = null;        
        
        // ����Ӧ�����壬����Ϣ��װΪCTagSet��CRSet
        Iterator itBody = rspElement.elementIterator();
        while (itBody.hasNext())
        {
            subElement = (Element) itBody.next();            
            subElementName = subElement.getName();
            
            // ����ΪCTagSet
            if ("tagset".equalsIgnoreCase(subElementName))
            {
                tagSet = new CTagSet();
                
                // ȡҶ�ӽڵ��б�
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
            // ����ΪCRSet��ֻ����һ��CRSet�Ļ����ڵ�����Ϊcrset��������ض��CRSet����ڵ���������Ϊcrset1��crset2...
            else if (subElementName.indexOf("crset") != -1 && !parsedElements.contains(subElementName))
            {
                // ȡͬ�������нڵ�
                crsetList = rspElement.elements(subElementName);
                
                for (int n = 0; n < crsetList.size(); n++)
                {
                    // ������crset�ڵ�
                    crsetElement = (Element) crsetList.get(n);
                     
                    // ȡҶ�ӽڵ��б�
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
                
                // ����ΪsubElementName�Ľڵ���ѽ�������
                parsedElements.add(subElementName);
                
                retVector.add(rSet);
            }           
        }
        
        // ���tagSet��Ϊ�գ�������֤���ͳһ�Ӵ�
        if (null != tagSet && !tagSet.isEmpty())
        {
            tagSet.SetValue("verifyCode", verifyCode);
            tagSet.SetValue("contactid", contactid);               
        }
        
        // ��һ��Ԫ�ؿ϶���CTagSet
        retVector.add(0, tagSet);
        
        rtData.setReturnObject(retVector);
        
        return rtData;    
    }
    
    //add begin zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
    /**
     * ����������
     * 
     * @param docXML ������
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
        
        //����������ͷ
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
        
        //��װ����ʱ��
        busiAcceptTimePO.setBusinessId(businessId);
        busiAcceptTimePO.setMenuId(menuId);
        busiAcceptTimePO.setRegion(region);
        busiAcceptTimePO.setRouteType(routeType);
        busiAcceptTimePO.setServnum(servnum);
        busiAcceptTimePO.setOperId(operId);
        
        //����
        return busiAcceptTimePO;
    }
    //add end zWX176560 2013/09/05 OR_SD_201308_934 R003C13L09n01 
    
    /**
     * ��paramMap�е���Ϣ��֯����
     * 
     * @param rootElement �����ĸ��ڵ�
     * @param paramMap �����Ľڵ��Ӧ��ֵ
     * @see 
     */
    private static void handleElement(Element rootElement, Map<String, String> paramMap)
    {
        // ȡ�ӽڵ��б�
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
                
                // ȡ�ӽڵ��б�
                subList = subElement.elements();
                
                // ����ʱ��
                if ("req_time".equalsIgnoreCase(elementName))
                {
                    synchronized (sdf)
                    {
                        subElement.setText(sdf.format(new Date()));
                    }
                }
                // ������ˮ
                else if ("req_seq".equalsIgnoreCase(elementName))
                {
                    subElement.setText(String.valueOf(transID++));
                }
                // ��������
                else if ("channelid".equalsIgnoreCase(elementName))
                {
                    subElement.setText("bsacAtsv");
                }
                // ������ӽڵ㣬��ݹ鴦��
                else if (null != subList && subList.size() > 0)
                {
                    handleElement(subElement, paramMap);
                }
                // ���û���ӽڵ㣬��ʹ��paramMap�е�ֵ��䱨��
                else if (paramMap.containsKey(elementName))
                {                    
                    subElement.setText(paramMap.get(elementName));
                }
            }
        }        
    }
        
    /**
     * ��ȡͳһ����ƽ̨��ַ
     * 
     * @return
     * @see 
     */
    private String getServerURL()
    {
        // ͳһ����ƽ̨��ַ
        String fcgiUrl = "";
        
        // ����region��ȡURL
        TerminalInfoPO termInfo = (TerminalInfoPO) ServletActionContext.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        if (termInfo != null)
        {
            fcgiUrl = (String) PublicCache.getInstance().getCachedData("SH_FCGIURL_NEW_" + termInfo.getRegion());
        }
        
        if (fcgiUrl == null || "".equals(fcgiUrl))
        {
            logger.error("δ�ܻ�ȡ��ͳһ�ӿ�ƽ̨��ַ���ն˻�region��" + (termInfo == null ? "" : termInfo.getRegion()));
            
            return "";
        }
        else if (logger.isInfoEnabled())
        {
            logger.info("ͳһ�ӿ�ƽ̨��ַ��" + fcgiUrl);
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
