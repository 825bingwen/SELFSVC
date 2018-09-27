/*
 * 文 件 名:  BindBankCardBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <银行卡无密绑定>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 13, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.customize.sd.selfsvc.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.serviceinfo.client.ConnUtil;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.inspur.pgtools.SignEncException2;
import com.inspur.pgtools.SignUtil2;

/**
 * <银行卡无密绑定>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BindBankCardBean extends BaseBeanSDImpl
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(BindBankCardBean.class);
    
    /**
     * <校验用户绑定银行卡信息>
     * <功能详细描述>
     *  
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> queryBindInfo(BindBankCardPO bindBankCardPO) throws Exception
    {
        // 流水号
        String orderID = this.getRandomNumber();
        bindBankCardPO.setContactId(orderID);
        
        // 创建报文头
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("mobile",bindBankCardPO.getTelNum() );
        
        // 创建报文
        Document docContent = creatQueryBindXml(inParam, "processquerydrpreq");
        Document xmlDoc = this.createMsg(docContent,
                "processquerydrp",
                "recBindBankCard",
                bindBankCardPO.getContactId(),
                "1",
                bindBankCardPO.getTelNum(),
                "");
        
        // 记录查询绑定关系报文
        logger.info("查询绑定关系报文："+xmlDoc.asXML());
        
        // 调用银行网关查询绑定信息
        ConnUtil connUtil = new ConnUtil();
        
        // 调用浪潮查询绑定关系url
        String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_QRYBIND_URL);
        String xml = xmlDoc.asXML();
        
        // 编码集
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        String retMes = connUtil.sendXMLReq(url, xml, charset);
        
        // 解析返回报文获取参数
        Map<String, String> bindInfo = this.parseResponse(retMes);
         
    	/*Map<String, String> bindInfo = new HashMap<String, String>();
		bindInfo.put("STATUS", "1");
		bindInfo.put("USERNAME", "陈云暖");
		bindInfo.put("CARDNO", "5222545633255");
		bindInfo.put("BINDDATE", "20120908");*/
        
        return bindInfo;
    
    }
    
    /**
     * 创建报文体
     * 
     * @param inParam
     * @param process_code
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Document creatQueryBindXml(Map<String, String> inParam, String process_code)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement(process_code);
        Element tagSet = eBody.addElement("tagset");
        for (Entry<String, String> entry : inParam.entrySet())
        {
            tagSet.addElement(entry.getKey()).addText(entry.getValue());
        }
        return doc;
    }
    
    /**
     * 创建XML请求报文
     * 
     * @param ...
     * @return
     */
    public Document createMsg(Document docContent, String process_code, String menuid, String unicontact,
            String route_type, String route_value, String operatorid)
    {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        DocumentFactory df = DocumentFactory.getInstance();
        Document doc = df.createDocument();
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        doc.setXMLEncoding(charset);
        
        // 根节点
        Element root = doc.addElement("Envelope");
        
        // 菜单项
        Element head = root.addElement("Header");
        
        // 消息头
        Element eHead = head.addElement("request_head");
        
        // 业务代码
        Element eProssCode = eHead.addElement("process_code");
        eProssCode.addText(process_code);
        
        // 验证码
        Element eVerifyCode = eHead.addElement("verify_code");
        eVerifyCode.addText("");
        
        // 菜单项
        Element eMenu = eHead.addElement("menuid");
        if (null == menuid)
        {
            menuid = "";
        }
        eMenu.addText(menuid);
        
        // 请求时间
        Element eProcessTime = eHead.addElement("req_time");
        eProcessTime.addText(sdf.format(new Date()));
        
        // 请求流水
        Element eSeq = eHead.addElement("req_seq");
        eSeq.addText("");
        
        // 路由（父节点）
        Element eRoute = eHead.addElement("route");
        
        // 路由类型
        Element eRoute_type = eRoute.addElement("route_type");
        eRoute_type.addText(route_type);
        
        // 路由字段值
        Element eRoute_value = eRoute.addElement("route_value");
        eRoute_value.addText(route_value);
        
        // 统一接触
        Element eUnicontact = eHead.addElement("unicontact");
        eUnicontact.addText(unicontact == null ? "":unicontact);
        
        // 测试标识
        Element eTestFlag = eHead.addElement("testflag");
        eTestFlag.addText("0");
        
        // 渠道信息（父节点）
        Element eChannelinfo = eHead.addElement("channelinfo");
        
        // 渠道ID
        Element eChannelid = eChannelinfo.addElement("channelid");
        eChannelid.addText("bsacNB");
        
        // 子渠道id
        Element eUnitid = eChannelinfo.addElement("unitid");
        eUnitid.addText("HUAWEI");
        
        // 操作员
        Element eOperatorid = eChannelinfo.addElement("operatorid");
        eOperatorid.addText(operatorid);
        
        // 请求内容
        Element eBody = root.addElement("Body");
        eBody.appendContent(docContent);
        
        return doc;
    }
    
    /**
     * 解析返回报文信息
     * 
     * @param response 返回的报文
     * @return Map存放返回信息key为参数名 value为参数值 其中有两个参数 status 0：失败 1：成功 RETMSG为错误信息
     * @throws Exception 
     * @throws Exception 
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> parseResponse(String response) throws Exception 
    {
        Map<String, String> returnMap = new HashMap<String, String>();
        Document doc = null;
        try
        {
            doc = DocumentHelper.parseText(response);
            
            // 记录查询绑定关系返回报文
            logger.info("返回报文："+doc.asXML());
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
            throw new Exception("对不起，系统错误，操作失败，请稍后再试。");
            
        }
        Element root = doc.getRootElement();
        Element eHead = root.element("Header").element("response_head");
        
        String rspCode = null;
        String rspDesc = null;
        String pross_Code = null;
        
        // 解析返回码 得到业务办理状态
        if (eHead != null)
        {
            pross_Code = eHead.element("process_code").getText();
            Element eRetinfo = eHead.element("retinfo");
            
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            returnMap.put("RETCODE", rspCode);
            returnMap.put("RETMSG", rspDesc);
            
            // 状态 0已签约 1已解约 2未签约
            returnMap.put("STATUS", "1");
            
            // 如果错误码不是0，状态设为0
            if (!"0".equals(rspCode))
            {
                returnMap.put("STATUS", "0");
            }
        }
        
        // 获取并解析报文体
        Element body = root.element("Body");
        if (null != body)
        {
            Element ebody = body.element(pross_Code + "resp");
            if (null != ebody)
            {
                // 获取tagset节点
                Element tagSetElement = ebody.element("tagset");
                
                // 遍历tagset节点获取参数值
                if (null != tagSetElement)
                {
                    Iterator itChild = tagSetElement.elementIterator();
                    Element eChild = null;
                    while (itChild.hasNext())
                    {
                        eChild = (Element)itChild.next();
                        String key = eChild.getName().toUpperCase(Locale.getDefault());
                        String val = eChild.getText().replace("^_^", "&");
                        returnMap.put(key, val);
                    }
                }
            }
        }
        
        // 返回
        return returnMap;
    }

    /**
     * 校验用户是否实名注册如果是返回注册信息
     * 
     * @param bindBankCardPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> checkIsFactNameRegist(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {      
        // 封装参数
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkFactNameRegist(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 获取用户信息成功
            CTagSet tagset = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", tagset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }

    /**
     * 无密绑定模式提交
     * 
     * @param bindBankCardPO 
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     * @remark create xKF69944 Aug 20, 2013[需求名称]
     */
    public Map<String, String> noEncrptyBindComit(BindBankCardPO bindBankCardPO) throws Exception
    {
        logger.info("noEncrptyBindComit...");
        
        // 签名数据
        StringBuffer signBuffer = new StringBuffer();
        
        // 参数处理
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("pantype", bindBankCardPO.getBankCardType());// 卡片种类 0:借记卡 1:贷记卡
        paramMap.put("customer_mobile", bindBankCardPO.getTelNum());// 有效的山东移动手机号
        paramMap.put("bank_acc", bindBankCardPO.getBankCardNum());// 银行卡号
        paramMap.put("user_name", bindBankCardPO.getUserFactName());// 姓名
        paramMap.put("id_type", bindBankCardPO.getId_type());// 证件类型
        paramMap.put("id_num", bindBankCardPO.getIdCardNum());// 身份证号
        paramMap.put("cvn2", bindBankCardPO.getCvn2());// 对于信用卡必须填写此字段
        paramMap.put("expire", bindBankCardPO.getExpire());// 有效期
        
        // 创建请求报文
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("root");
        document.setRootElement(rootElement);
        this.createNoEncrptyHead(rootElement.addElement("head"), signBuffer);
        this.createNoEncrptyBody(rootElement.addElement("body"), paramMap, signBuffer);
        
        // 记录绑定银行卡发送报文
        logger.info("记录绑定银行卡发送报文："+document.asXML());
        
        // 浪潮提供的无密绑定提交URL
        String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_NOENCRPTY_URL);
        
        // 编码集
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        
        // 发送报文
        ConnUtil connUtil = new ConnUtil();
        String xml = document.asXML();
        String retMes = connUtil.sendXMLReq(url, xml, charset);
        
        // 处理返回结果
        Document doc = DocumentHelper.parseText(retMes);
        
        // 记录绑定银行卡返回报文
        logger.info("记录绑定银行卡返回报文："+doc.asXML());
        Map<String, String> retMap = new HashMap<String, String>();
        this.parseXml(doc.getRootElement(), retMap);
        
        // 校验签名
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("appcode=" + retMap.get("appcode") + "&");
        sBuffer.append("funcode=" + retMap.get("funcode") + "&");
        sBuffer.append("time=" + retMap.get("time") + "&");
        sBuffer.append("rtn_code=" + retMap.get("rtn_code") + "&");
        sBuffer.append("pg_order_code=" + retMap.get("pg_order_code") + "&");
        sBuffer.append("app_flow_code=" + retMap.get("app_flow_code"));
        if (!SignUtil2.verify(sBuffer.toString(), retMap.get("pg_sign")))
        {
            retMap.clear();
        }
        
        // 返回
        return retMap;
    }
    
    /**
     * 创建无密绑定模式提交_头信息
     * 
     * @param element
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create xKF69944 Aug 20, 2013[需求名称]
     */
    public Element createNoEncrptyHead(Element element, StringBuffer sign)
    {
        // 易充值系统编码
        element.addElement("appcode").setText("07");
        sign.append("appcode=07&");
        
        // 功能码
        element.addElement("funcode").setText("0006");
        sign.append("funcode=0006&");
        
        // 请求时间
        String date = CommonUtil.getCurrentTime();
        element.addElement("time").setText(date);
        sign.append("time="+date+"&");
        
        // 返回
        return element;
    }
    
    /**
     * 创建无密绑定模式提交_包体
     * 
     * @param element
     * @param paramMap 参数数据
     * @param sign 签名
     * @return
     * @throws SignEncException2 
     * @see [类、类#方法、类#成员]
     * @remark create xKF69944 Aug 20, 2013[需求名称]
     */
    public Element createNoEncrptyBody(Element element, Map<String, String> paramMap, StringBuffer sign) throws SignEncException2
    {   
        // 交易流水号或订单号
        String orderID = this.getRandomNumber();
        element.addElement("app_flow_code").setText(orderID);
        sign.append("app_flow_code="+orderID+"&");
        
        // 卡片种类 0：借记卡 1：贷记卡
        element.addElement("pantype").setText(paramMap.get("pantype"));
        sign.append("pantype=" + paramMap.get("pantype") + "&");
        
        // 客户手机号码
        element.addElement("customer_mobile").setText(paramMap.get("customer_mobile"));
        
        // 银行卡号
        element.addElement("bank_acc").setText(paramMap.get("bank_acc"));
        sign.append("bank_acc=" + paramMap.get("bank_acc") + "&");
        
        // 姓名
        element.addElement("user_name").setText(paramMap.get("user_name"));
        sign.append("user_name=" + paramMap.get("user_name") + "&");
        
        // 证件类型
        element.addElement("id_type").setText(paramMap.get("id_type"));
        sign.append("id_type=" + paramMap.get("id_type") + "&");
        
        // 身份证号
        element.addElement("id_num").setText(paramMap.get("id_num"));
        sign.append("id_num=" + paramMap.get("id_num"));
        
        // CVN2
        element.addElement("cvn2").setText(paramMap.get("cvn2"));
        element.addElement("expire").setText(paramMap.get("expire"));
        element.addElement("app_sign").setText(SignUtil2.sign(sign.toString()));
        //element.addElement("app_sign").setText("******");
        
        return element;
    }
    
    /**
     * 解析对应的xml问题
     * 
     * @param element
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  Map<String, String> parseXml(Element element, Map<String, String> map)
    {
        List<Element> list = element.elements();
        while (list.size() > 0)
        {
            for (Element element2 : list)
            {
                this.parseXml(element2, map);
            }
            break;
        }
        if (list.size() == 0)
        {
            map.put(element.getName(), element.getText().trim());
        }
        return map;
    }
    
    /**
     * 求绝对值
     * 
     * @param a int值
     * @return a的绝对值
     */
    public String abs(int a)
    {
        int b = (a < 0) ? -a : a;
        return Integer.toString(b);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getRandomNumber()
    {
        //return System.currentTimeMillis() + "" + ((int)(Math.random()*9000) + 1000);
        return System.currentTimeMillis() + "" + (new Random().nextInt(8999) + 1000);
    }

    /**
     * 银联卡解绑定
     * 
     * @param bindBankCardPO 用户信息
     * @return
     * @throws Exception
     * @remark 
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> unBindBankCard(BindBankCardPO bindBankCardPO) throws Exception
    {
        // 流水号
        String orderID = this.getRandomNumber();
        bindBankCardPO.setContactId(orderID);
        
        // 创建报文体
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("mobile", bindBankCardPO.getTelNum());
        Document docContent = creatQueryBindXml(inParam, "processopendrpreq");
        
        // 创建报文
        Document xmlDoc = this.createMsg(docContent,
                "processopendrp",
                "recBindBankCard",
                bindBankCardPO.getContactId(),
                "1",
                bindBankCardPO.getTelNum(),
                "");
         // 记录查询绑定关系报文
         logger.info("解绑定关系报文："+xmlDoc.asXML());
          
         // 调用银行网关查询绑定信息
         ConnUtil connUtil = new ConnUtil();
          
         // 调用浪潮查询绑定关系url
         String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_UNBIND_URL);
         String xml = xmlDoc.asXML();
          
         // 编码集
         String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
         String retMes = connUtil.sendXMLReq(url, xml, charset);
         
         // 解析返回报文获取参数
         Map<String, String> bindInfo = this.parseResponse(retMes);
         
         return bindInfo;
    }
    
    /**
     * 绑定银行卡的手机号码自动交费操作
     * 
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @param oprtype 操作类型（必选 0：查询，1：开通自动缴费，2：关闭自动缴费，3:更新）
     * @param trigamt 预付费用户触发金额 分
     * @param drawamt 预付费用户自动充值金额 分
     * @return
     * @see [类、类#方法、类#成员]
     * @modify yWX163692 2013年11月19日 OR_SD_201309_940 易充值二阶段，解约新增自动交费判断流程  
     * @remark modify by sWX219697 2014-10-7 14:21:15 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public Map<String,String> bindAutoFeeSet(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, 
    		String curMenuId, String oprtype, String trigamt, String drawamt)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        
    	// 调用接口进行自动缴费操作（查询，开通，关闭）
        ReturnWrap rw = this.getSelfSvcCallSD().autoFeeSet(msgHeader, oprtype, trigamt, drawamt);

        Map<String, String> retMap = new HashMap<String, String>();
        
        //调用成功，查询或开通成功
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			if(rw.getReturnObject() instanceof CTagSet)
			{
				CTagSet tagSet = (CTagSet)rw.getReturnObject();

				//触发金额 分
				retMap.put("trigamt", tagSet.GetValue("trigamt"));
				
				//自动充值金额 分
				retMap.put("drawamt", tagSet.GetValue("drawamt"));
			}
			
			//用户已开通自动充值
			retMap.put("autoStatus", "0");
		}
		
		//查询时，用户未开通自动交费功能
		else if(1 == rw.getErrcode() && "0".equals(oprtype))
		{
			
			//用户未开通自动充值
			retMap.put("autoStatus", "1");
		}
		else
		{
			throw new ReceptionException("用户自动交费设置失败：" + rw.getReturnMsg());
		}
		
		return retMap;
    }

    /**
     *  易充值签约之前检查是否满足产品开通条件
     *  
     * @param customerSimp，用户信息
     * @param termInfo，终端信息
     * @param nCode
     * @param operType，操作类型，开通或者取消
     * @param effectType
     * @param param
     * @param menuID
     * @param isSubmit 提交方式 0只检验返回  为空或者其他值时提交
     * @param executecmd 是否提交自动提交 空自动提交 不为空非自动提交
     * @return ReturnWrap
     * @see
     */
    public ReturnWrap checkProCondition(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String isSubmit, String executecmd, String menuID)
    {
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("nCode", nCode);
        map.put("operType", operType);
        map.put("effectType", effectType);
        map.put("param", param);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        map.put("isSubmit", isSubmit);
        map.put("executecmd", executecmd);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkProCondition(map);
        
        return rw;
    }
    
    /**
     * <查询用户的付费类型>
     * <功能详细描述>
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @return PREPAYTYPE 0：后付费 1：预付费 9：查询绑定的用户不存在
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-7 14:21:15 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public Map<String, String> qrySubsPrepayType(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, 
    		String curMenuId)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        
        //查询用户类型
        ReturnWrap rw = this.getSelfSvcCallSD().qrySubsPrepayType(msgHeader);
		
        Map<String, String> retMap = new HashMap<String, String>();
        
        //调用成功，返回付费类型
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet)rw.getReturnObject();
			
			String payType = tagSet.GetValue("prepaytype");
			
			//付费类型
			if ("1".equals(payType) || "0".equals(payType))
			{
				retMap.put("payType", payType);
				return retMap;
			}
			else
			{
				throw new ReceptionException("用户付费类型查询失败：用户不存在");
			}
		}
		
		throw new ReceptionException("用户付费类型查询失败："+rw.getReturnMsg());		
    }
    
    /**
     * <查询用户副号码列表，流水线>
     * <功能详细描述>
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @param ncode ZLWGQY
     * @param stype QRY
     * @see [类、类#方法、类#成员]
     * @remark sWX219697 2014-12-2 18:30:04 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public Map<String, Object> viceNumberQry(NserCustomerSimp customer, TerminalInfoPO termInfo, String curMenuId, 
    		String ncode, String stype)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //调用流水线查询接口
        ReturnWrap rw = this.getSelfSvcCallSD().viceNumberQry(msgHeader, ncode, stype);
        
        //查询成功
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            //副号码列表
            List<BankCardInfoPO> viceNumList = new ArrayList<BankCardInfoPO>();
            Map<String, Object> retMap = new HashMap<String, Object>();
            
            //解析CTagset
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//副号码分隔符 ~
        	String outparamsplit = tagSet.GetValue("outparamsplit");
        	
        	//13963478598~18660165052~15966640687~FriendNum3~FriendNum4~FriendNum5~FriendNum6~FriendNum7~FriendNum8~FriendNum9
        	String outparam = tagSet.GetValue("outparam");
        	
        	//副号码数组
        	String[] viceArray = outparam.split(outparamsplit);
        	
        	//用户已绑定的副号码串，用于防止用户新增重复副号码
        	StringBuffer oldViceNumber = new StringBuffer("");
        	
        	//遍历解析出副号码
        	if(StringUtils.isNotBlank(outparam) && (null != viceArray && viceArray.length > 0))
        	{
        		for (String viceNum : viceArray)
        		{
        			if (viceNum.indexOf("FriendNum") == -1)
        			{
        				BankCardInfoPO bankCardInfoPO = new BankCardInfoPO();
        				bankCardInfoPO.setViceNumber(viceNum);
        				viceNumList.add(bankCardInfoPO);
        				
        				//副号码字符串 格式：13963478598~18660165052~15966640687
        				if("".equals(oldViceNumber.toString()))
        				{
        					oldViceNumber.append(viceNum);
        				}
        				else
        				{
        					oldViceNumber.append("~").append(viceNum);
        				}
        			}
        		}
        	}
        	
        	//封装返回值
        	retMap.put("viceNumList", viceNumList);
        	retMap.put("oldViceNumber", oldViceNumber.toString());
        	
        	return retMap;
        }
		
        throw new ReceptionException("副号码查询失败：" + rw.getReturnMsg());
    }
    
    /**
     * <新增、删除副号码>
     * <功能详细描述>
     * @param customer
     * @param termInfo
     * @param curMenuId
     * @param viceArray 副号码列表
     * @param stype 操作类型：1：新增，2：删除
     * @see [类、类#方法、类#成员]
     * @remark sWX219697 2014-12-4 11:44:40 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public void viceNumberSet(NserCustomerSimp customer, TerminalInfoPO termInfo, String curMenuId, 
    		String[] viceArray, String opertype)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //调用流水线查询接口
        ReturnWrap rw = this.getSelfSvcCallSD().viceNumberSet(msgHeader, viceArray, opertype);
        
        //设置失败，则抛出异常
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	throw new ReceptionException("1".equals(opertype) ? "添加副号码失败：" : "删除副号码失败："+rw.getReturnMsg());
        }
    }
    
    /**
     * 查询当前用户是否签约和包易充值
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @return
     * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public Map<String, Object> checkHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String bankCardNum)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().checkHeBao(headerPo, bankCardNum);
    	
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
			resultMap.put("status", rw.getStatus());
			
			// 将接口返回结果进行设置
			CTagSet set = (CTagSet) rw.getReturnObject();
			
			if("0".equals(set.GetValue("status")))
			{
				resultMap.put("returnObj", set);
			}
    	}
    	else
    	{
    		// 接口调用失败，系统页面迁移至错误页面
    		resultMap.put("status", rw.getStatus());
    		resultMap.put("errMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * 和包易充值发送短信随机验证码
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsType
     * @param cardPo
     * @return
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public Map<String, String> sendHeBaoRandom(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsType, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	// 调用和包易充值发送短信随机码接口
    	ReturnWrap rw = getSelfSvcCallSD().sendHeBaoRandom(headerPo, smsType, cardPo);
    	
    	// 封装接口返回信息
    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	// 接口调用成功，且返回交易流水号
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
    		CTagSet set = (CTagSet)rw.getReturnObject();
    		
    		// 发送短息随机码后生成的交易流水号
    		resultMap.put("tradeNo", set.GetValue("tradeNo"));
    	}
    	// 接口调用失败
    	else
    	{
    		resultMap.put("retMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * 和包易充值签约接口
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsCode
     * @param cardPo
     * @return
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public Map<String, String> signHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsCode, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().signHeBao(headerPo, cardPo, smsCode);
    	
    	// 封装接口返回信息
    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	// 接口调用成功，且返回交易流水号
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
    		CTagSet set = (CTagSet) rw.getReturnObject();
    		
    		// 签约成功后返回快捷支付协议号
    		resultMap.put("AGRNO", set.GetValue("AGRNO"));
    	}
    	// 接口调用失败，返回接口报错信息
    	else
    	{
    		resultMap.put("retMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * 和包易充值解约接口
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsCode
     * @param cardPo
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public void unsignHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsCode, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().unsignHeBao(headerPo, cardPo, smsCode);
    	
    	if(SSReturnCode.ERROR == rw.getStatus())
    	{
    		throw new ReceptionException(rw.getReturnMsg()); 
    	}
    }
    
    /**
     * 和包易充值自动设置交费接口
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param oprType
     * @param trigAmt
     * @param drawAmt
     * @param bankId
     * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public void setHeBaoAutoValue(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, 
    		String oprType, BankCardInfoPO bankCardInfoPO)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().setHeBaoAutoValue(headerPo, oprType, bankCardInfoPO);
    	
    	if(SSReturnCode.ERROR == rw.getStatus())
    	{
    		throw new ReceptionException(rw.getReturnMsg());
    	}
    }

}
