package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 预约选号
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 19, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChooseTelBean extends BaseBeanImpl
{
    /**
     * 查询号码
     * <一句话功能简述>
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息 
     * @param curMenuId 当前菜单
     * @param county_id 地区，如SD.LA
     * @param sele_rule 查询类型 2：按前缀查询 3：按后缀查询
     * @param tel_prefix 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
     * @param tel_suffix 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map qryChooseTel(TerminalInfoPO terminalInfoPO,String curMenuId,String county_id,String sele_rule,
    		String tel_prefix,String tel_suffix, String region)
    {
    	// getQueryNumReq(region, "100", tel_prefix, tel_suffix, "100");
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 地区
        paramMap.put("county_id", county_id);
        
        // sele_rule 查询类型 2：按前缀查询 3：按后缀查询
        paramMap.put("sele_rule", sele_rule);
        
        // tel_prefix 号码前缀
        paramMap.put("tel_prefix", tel_prefix);
        
        // tel_suffix 号码后缀
        paramMap.put("tel_suffix", tel_suffix);
        
        paramMap.put("region", region);
        
        // modify begin cKF76106 2013/03/25 R003C12L12n01 OR_SD_201301_279
        // OR_SD_201301_296 上线标志
        String upFlag = (String)PublicCache.getInstance().getCachedData("SH_TELCHOOSE_UPFLAG");
        ReturnWrap rw = null;
        if ("1".equals(upFlag))
        {
            paramMap.put("selcountryid", county_id + ".ES");
            
            rw = selfSvcCall.qryChooseTelSD(paramMap);
        }
        else
        {
            rw = selfSvcCall.qryChooseTel(paramMap);
        }
        // modify end cKF76106 2013/03/25 R003C12L12n01 OR_SD_201301_279

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 预约号码
     * <一句话功能简述>
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param curMenuId 当前菜单
     * @param telnum 被预定号码
     * @param region 地市（查询时返回的信息）
     * @param org_id 单位（查询时返回的信息）
     * @param certtype 默认：IdCard
     * @param certid 身份证号
     * @param name 用户姓名
     * @param contacttel 联系号码，可为“”
     * @param randomEnddate 到期释放时间
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map chooseTel(TerminalInfoPO terminalInfoPO,String curMenuId,String telnum,String region,String org_id,String certtype,String certid,String name,String contacttel,String enddateRandom)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //被预定号码
        paramMap.put("telnum", telnum);
        
        //地市（查询时返回的信息）
        paramMap.put("region", region);
        
        //单位（查询时返回的信息）
        paramMap.put("org_id", org_id);
        
        //默认：IdCard
        paramMap.put("certtype", certtype);
        
        //身份证号
        paramMap.put("certid", certid);
        
        //用户姓名
        paramMap.put("name", name);
        
        //联系号码，可为“”
        paramMap.put("contacttel", contacttel);
        
        // add begin hWX5316476 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化
        // 到期释放时间
        paramMap.put("enddateRandom", enddateRandom);
        // add end hWX5316476 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化
        
        //预约时长 单位天
        String validday = (String)PublicCache.getInstance().getCachedData("SH_VALIDDAY");
        if (validday == null)
        {
            paramMap.put("validday", "");
        }
        else
        {
            paramMap.put("validday", validday);
        }
        
        ReturnWrap rw = selfSvcCall.chooseTel(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagset = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", tagset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    
    /**
     * 向用户发送订购凭证短信
     * 
     * @param servNumber，手机号码
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param curMenuId，当前菜单
     * @return
     * @see
     */
    public boolean sendMsg(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * webservice 查询可预订号码接口
     * @param region
     * @param preFee
     * @param beginNum
     * @param endNum
     * @param rowNum
     * @return webService请求报文
     * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String getQueryNumReq(String region, String preFee, String beginNum, String endNum, String rowNum, String condition)
    {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        
        // 创建命名空间
        OMNamespace serNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMSERNS), "");
        OMNamespace xsdNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMXSDNS), "");
        
        // 组装调用的方法
        OMElement requestMsg = fac.createOMElement("queryNum", serNameSpace);
        
        // 声明document变量，创建XML文档
        Document doc = DocumentHelper.createDocument();
        
        // 声明root变量
        Element root = doc.addElement("message");
        
        // 创建message节点
        Element body = root.addElement("message_body");
        
        // 设置调用方法的参数
        // 地区
        Element reqRegion = body.addElement("region");
        reqRegion.addText(region);
        
        // 预存款 preFee
        Element reqPreFee = body.addElement("preFee");
        reqPreFee.addText(preFee);
        
        // 前N位号码 beginNum
        Element reqBeginNum = body.addElement("beginNum");
        reqBeginNum.addText(beginNum);
        
        // 后N位号码 endNum
        Element reqEndNum = body.addElement("endNum");
        reqEndNum.addText(endNum);
        
        // 返回行数 rowNum
        Element reqRowNum = body.addElement("rowNum");
        reqRowNum.addText(rowNum);
        
        // 自定义查询号码
        Element reqSelfDefNum = body.addElement("selfDefNum");
        reqSelfDefNum.addText(condition);
        
        // 自定义查询标识
        Element reqSefDefQry = body.addElement("sefDefQry");
        
        if(StringUtils.isNotEmpty(condition))
        {
        	reqSefDefQry.addText("sefDefQry");
        }
        else
        {
        	reqSefDefQry.addText("");
        }
        
        OMElement xmlFile = fac.createOMElement("xmlFile", xsdNameSpace);
        
        xmlFile.setText(doc.asXML());
        
        requestMsg.addChild(xmlFile);
        
        requestMsg.build();
        
        String respMsg = selfSvcCall.queryNumResp(requestMsg);
        
        return respMsg;
    }
    
    /**
     * webservice 号码预约接口
     * @param telNum
     * @return
     * @remark create by wWX217192 2015-03-12 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String getPickNumReq(String telNum)
    {
    	OMFactory fac = OMAbstractFactory.getOMFactory();
    	
    	// 创建命名空间
        OMNamespace serNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMSERNS), "");
        OMNamespace xsdNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMXSDNS), "");
    	
    	// 组装调用方法
    	OMElement requestMsg = fac.createOMElement("pickNum", serNameSpace);
    	
    	// 声明document变量，创建XML文档
    	Document doc = DocumentHelper.createDocument();
    	
    	// root变量
    	Element root = doc.addElement("message");
    	
    	// message_body变量
    	Element body = root.addElement("message_body");
    	
    	// 为方法添加参数
    	// 要暂选的号码
    	Element reqTelNum = body.addElement("telnum");
    	reqTelNum.addText(telNum);
    	
    	// 渠道 channel
    	Element reqChannel = body.addElement("channel");
    	reqChannel.addText("bsacAtsv");
    	
    	OMElement xmlFile = fac.createOMElement("xmlFile", xsdNameSpace);
        
        xmlFile.setText(doc.asXML());
        
        requestMsg.addChild(xmlFile);
    	
    	// 封装请求报文
    	requestMsg.build();
    	
    	String respMsg = selfSvcCall.pickNumResp(requestMsg);
    	
    	return respMsg;
    }
    
    /**
     * 预订号码
     * @param name
     * @param telnum
     * @param certId
     * @param contactPhone
     * @param vprodId
     * @return
     * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String getBookNumReq(String name, String telnum, String certId, String contactPhone, String vprodId)
    {
    	OMFactory fac = OMAbstractFactory.getOMFactory();
    	
    	// 创建命名空间
    	OMNamespace serNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMSERNS), "");
        OMNamespace xsdNameSpace = fac.createOMNamespace((String) PublicCache.getInstance().getCachedData(Constants.SH_CHOOSETEL_WEBSERVICE_OMXSDNS), "");
    	
    	// 组装调用的方法
    	OMElement requestMsg = fac.createOMElement("bookNum", serNameSpace);
    	
    	// 声明document变量，创建XML文件
    	Document doc = DocumentHelper.createDocument();
    	
    	// root变量
    	Element root = doc.addElement("message");
    	
    	// message_body变量
    	Element body = root.addElement("message_body");
    	
    	// 为方法设置参数
    	// 预约人姓名 name
    	Element reqName = body.addElement("name");
    	reqName.addText(name);
    	
    	// 要预约的号码
    	Element reqTelNum = body.addElement("telnum");
    	reqTelNum.addText(telnum);
    	
    	// 身份证号码 certId
    	Element reqCertId = body.addElement("certId");
    	reqCertId.addText(certId);
    	
    	// 联系电话 contactPhone
    	Element reqContactPhone = body.addElement("contactPhone");
    	reqContactPhone.addText(contactPhone);
    	
    	// 渠道 channel
    	Element reqChannel = body.addElement("channel");
    	reqChannel.addText("bsacAtsv");
    	
    	// 虚拟产品编码 vprodId
    	Element reqVprodId = body.addElement("vprodId");
    	reqVprodId.addText(vprodId);
    	
    	// 添加XMLFile属性
    	OMElement xmlFile = fac.createOMElement("xmlFile", xsdNameSpace);
        
        xmlFile.setText(doc.asXML());
        
        requestMsg.addChild(xmlFile);
    	
    	requestMsg.build();
    	
    	String respMsg = selfSvcCall.bookNumResp(requestMsg);
    	
    	return respMsg;
    }
}
