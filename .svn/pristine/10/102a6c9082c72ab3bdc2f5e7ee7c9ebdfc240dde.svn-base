package com.customize.hub.selfsvc.call.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.dubbo.common.json.ParseException;
import com.customize.hub.selfsvc.call.SelfSvcCallHub;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.call.IntMsgUtilNew;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

public class SelfSvcCallHubImpl implements SelfSvcCallHub
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(SelfSvcCallHubImpl.class);
	private static Log log = LogFactory.getLog(SelfSvcCallHubImpl.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    private IntMsgUtil intMsgUtil;
    
    private IntMsgUtilNew intMsgUtilNew;
    
    public ReturnWrap qryArrear(Map paramMap)
    {
        String telNum = (String)paramMap.get("telNum");
        String region = (String)paramMap.get("region");
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        // 封装包体入参
        
        // 手机号码
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telNum);
        
        Element eReq_password = eBody.addElement("region");
        eReq_password.addText(region);
        
        String operID = (String)paramMap.get("operID");
        String termID = (String)paramMap.get("termID");
        
        Document docXML = intMsgUtil.createMsg(doc, "待定", "", "", "1", telNum, operID, termID);
        
        return intMsgUtil.invoke(docXML);
    }
    
    /**
     * 湖北账户缴费方式查询（校验是否为托收账户）
     * 
     * @param map
     * @return
     * @remark create hWX5316476 2014-05-22 V200R003C10LG0501 OR_huawei_201405_234 自助终端接入EBUS一阶段_缴费
     */
    public ReturnWrap getAccSettleTypeByPayType(Map<String, String> map)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("PTGetAccSettleTypeByPayType"))
        {
            String operid = map.get("curOper");
            String atsvNum = map.get("atsvNum");
            String acctID = map.get("acctID");
            String touchoid = map.get("touchoid");
            String menuid = map.get("menuid");
            String region = map.get("region");
            String telnumber = map.get("telnumber");
            String payType = map.get("payType");
            
            // 入参
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 账户id
            inParamMap.put("acctID", acctID);
            
            // 地区
            inParamMap.put("region", region);
            
            // 地区
            inParamMap.put("region", region);
            
            // 支付方式
            inParamMap.put("payType", payType);
            
            return intMsgUtil.invokeDubbo("PTGetAccSettleTypeByPayType",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum,
                    inParamMap);
        }
        
        return null;
    }
    
    /**
     * 湖北缴费查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String servnumber = (String)map.get("servnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_fee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北缴费接口
     * 
     * @param map
     * @return
     * @remark modify hWX5316476 2014-04-17 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String servnumber = (String)map.get("servnumber");
            String money = (String)map.get("money");
            String payType = (String)map.get("payType");
            String touchoid = (String)map.get("touchoid");
            String chargelogoid = (String)map.get("chargelogoid");
            
            // add begin sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            String presentFee = (String)map.get("presentFee");
            String presentSubject = (String)map.get("presentSubject");
            // add end sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            
            // add begin lwx439898 2017-12-13  R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            //支付流水
            String taskoid = (String)map.get("taskoid");
            // add end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            
            // 统一接口平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("atsvBusiChargeFee"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号
                inParamMap.put("telnum", servnumber);
                
                //支付流水
                inParamMap.put("TASKOID", taskoid);
                
                // 缴费金额
                inParamMap.put("amount", money);
                
                // 缴费类型
                inParamMap.put("pay_type", payType);
                
                // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
                // 缴费流水
                inParamMap.put("chargeLgId", chargelogoid);
                // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
                
                // add begin sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                // 赠送金额，单位：分
                inParamMap.put("presentFee", presentFee);
                
                // 赠送科目
                inParamMap.put("presentSubject", presentSubject);
                // add end sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                
                
                // modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造  新增出参RECOID 和PresentRecoid
                // 出参键值对 取值键名与输出的键名对应关系数组，添加presentOid：赠送话费流水号
                String[][] outParamKeyDefine = { {"dealnum", "dealtime", "presentOid", "RECOID", "PresentRecoid"},
                        {"dealNum", "dealTime", "presentOid", "RECOID", "PresentRecoid"}};
                // modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造 新增出参RECOID 和PresentRecoid
                
                return intMsgUtil.invokeDubbo("atsvBusiChargeFee",
                        menuid,
                        touchoid,
                        "1",
                        servnumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 金额
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // 受理类型
            Element eReq_payType = eBody.addElement("pay_type");
            eReq_payType.addText(payType);
            
            // add begin jWX216858 2014-07-14 DR_HUB_201405_887_自助终端-银联对账整改2014
            // 缴费日志流水
            Element eReq_chargelogoid = eBody.addElement("chargelogoid");
            eReq_chargelogoid.addText(chargelogoid);
            // add end jWX216858 2014-07-14 DR_HUB_201405_887_自助终端-银联对账整改2014
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_chargefee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("缴费接口出现异常：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 更新表 ar_bank_task 的 recoid
     * @param termInfo
     * @param currNumber
     * @param curMenuId
     * @param taskoid
     * @param recoid
     * @param status
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap updateBankRecoid(TerminalInfoPO termInfo,String currNumber,String curMenuId,String taskoid,String recoid,String status)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        try
        {
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(taskoid);
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            Element eReq_status = eBody.addElement("status");
            eReq_status.addText(status);
            //地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText("bsacAtsv");
            
            //湖北统一平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("BLARUpdateRecStatus"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("REGION", termInfo.getRegion());
                inParamMap.put("TASKOID", taskoid);
                inParamMap.put("RECOID", recoid);
                inParamMap.put("RECSTATUS", "1");
                inParamMap.put("BANKSTATUS", "1");
                
                return intMsgUtil.invokeDubbo("BLARUpdateRecStatus",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap);               
            }
                        
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_update_bankrecoid",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML); 
                    
        }
        catch (Exception e)
        {
            log.error("创建支付任务失败：", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 创建支付任务接口
     * 
     * @param customerSimp
     * @param curMenuId
     * @param termInfo
     * @param isTelNum
     * @param currNumber
     * @param selfPayLog
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    
    public ReturnWrap createPayCenterTrans(NserCustomerSimp customerSimp,String curMenuId,TerminalInfoPO termInfo, String isTelNum, String currNumber,
            Map<String, String> selfPayLog)
    {
        try
        {
            //获取操作员id
            String operid = termInfo.getOperid();
            //获取终端ID
            String termid = termInfo.getTermid();
                             
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");  
            //地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            //手机号
            Element eReq_servnumber = eBody.addElement("telnum");
            eReq_servnumber.addText(currNumber);
            //银行代码
            Element eReq_bankCode = eBody.addElement("bankcode");
            eReq_bankCode.addText(selfPayLog.get("bankCode"));
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());            
            //交易时间
            Element eReq_transtime = eBody.addElement("transtime");
            eReq_transtime.addText(payDate);            
            //相关任务号
            Element eReq_relatetaskoid = eBody.addElement("relatetaskoid");
            eReq_relatetaskoid.addText("");   
            //支付类型
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(selfPayLog.get("Paytype"));
            //交易类型
            Element eReq_exchangetype = eBody.addElement("exchangetype");
            eReq_exchangetype.addText(selfPayLog.get("exChangeType"));
            //交易金额
            Element eReq_payamount = eBody.addElement("payamount");
            eReq_payamount.addText(selfPayLog.get("Payamount"));
            //银行处理状态 0：未处理；1：处理成功； 9：处理失败
            Element eReq_bankstatus = eBody.addElement("bankstatus");
            eReq_bankstatus.addText("0");
            //业务处理状态：0未处理 1处理成功 8补充值处理中 9处理失败
            Element eReq_recstatus = eBody.addElement("recstatus");
            eReq_recstatus.addText("0");
            //对帐状态： 0 未对账 1对账成功 2银行长款 3 银行短款  4 金额不一致
            Element eReq_checkstatus = eBody.addElement("checkstatus");
            eReq_checkstatus.addText("0");
            //用户地市
            //Element eReq_usercity = eBody.addElement("usercity");
            //eReq_usercity.addText(customerSimp.getRegionName());
            //操作员工号
            Element eReq_operatorid = eBody.addElement("operatorid");
            eReq_operatorid.addText(operid);
            //当前业务类型
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(selfPayLog.get("RecType"));
            //银行交易流水号
            Element eReq_bankseqno = eBody.addElement("bankseqno");
            eReq_bankseqno.addText("");           
            //受理渠道
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(selfPayLog.get("AccessType"));
            
            //湖北统一平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("BLARPayOrderBuild"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("Telnum", currNumber);
                inParamMap.put("AccessType", selfPayLog.get("AccessType"));
                inParamMap.put("Paytype", selfPayLog.get("Paytype"));
                inParamMap.put("Payamount", selfPayLog.get("Payamount"));
                inParamMap.put("exChangeType", selfPayLog.get("exChangeType"));
                inParamMap.put("relateTaskoid", "");
                inParamMap.put("RecType", selfPayLog.get("RecType"));
                inParamMap.put("bankCode", selfPayLog.get("bankCode"));
                
                String[][] outParamKeyDefine = {{"TASKOID"},{"TASKOID"}};
                
                return intMsgUtil.invokeDubbo("BLARPayOrderBuild",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);               
            }

            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_create_payments",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML); 
                    
        }
        catch (Exception e)
        {
            log.error("创建支付任务失败：", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    } 
    
    /**
     * 查询支付交易
     * @param termInfo
     * @param taskoid
     * @param currNumber
     * @param curMenuId
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap qryPayChargeInfo(TerminalInfoPO termInfo, String taskoid, String currNumber, String curMenuId)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();
        
        try
        {
            Document doc = DocumentHelper.createDocument();           
            Element eBody = doc.addElement("message_content");
            
            //任务流水
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(taskoid);
            
            //地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            
            //出参转大写标识
            Element eReq_useupperkey = eBody.addElement("useupperkey");
            eReq_useupperkey.addText("1");
            
            //湖北统一平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("BLARQryArBankTaskByTaskOid"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("taskoid", taskoid);
                inParamMap.put("region", termInfo.getRegion());
                
                String[][] outParamKeyDefine = { {"RESULT_INTF","bankstatus","rollstatus","payType","exChangeType","relateTaskOid"}
                ,{"RESULT_INTF","BANKSTATUS","rollstatus","PAYTYPE","EXCHANGETYPE","relateTaskOid"}};
               
                return intMsgUtil.invokeDubbo("BLARQryArBankTaskByTaskOid",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);               
            }
                     
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_paytranstion",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询支付交易异常：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
 
    /**
     * 更新银行支付状态
     * @param payTransMsg
     * @param payCenterParam
     * @param termInfo
     * @param curMenuId
     * @param currNumber
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap updateBankPaymentStatus(String payCenterParamInfo,String respMsg,String payTransMsg,TerminalInfoPO termInfo,String curMenuId,String currNumber)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();

        try
        {
            Document doc = DocumentHelper.createDocument();           
            Element eBody = doc.addElement("message_content");
            //任务流水
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(payTransMsg);
            //地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            //银行状态
            Element eReq_bankstatus = eBody.addElement("bankstatus");
            eReq_bankstatus.addText("0");
            //请求报文
            Element eReq_requestinfo = eBody.addElement("requestinfo");
            eReq_requestinfo.addText(payCenterParamInfo);
            //响应报文
            Element eReq_responseinfo = eBody.addElement("responseinfo");
            eReq_responseinfo.addText(respMsg);
            
            Element eReq_isordercommit = eBody.addElement("isordercommit");
            eReq_isordercommit.addText("0");
            
            //湖北统一平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("BLARUpdateBankStatus"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("RESPONSEINFO", respMsg);
                inParamMap.put("PAYTYPEPARAM", "PayCenter");
                inParamMap.put("REQUESTINFO", payCenterParamInfo);
                inParamMap.put("TASKOID", payTransMsg);
                inParamMap.put("BANKSTATUS", "0");
                inParamMap.put("isOrderCommit", "0");
                inParamMap.put("REGION", termInfo.getRegion());
                
                return intMsgUtil.invokeDubbo("BLARUpdateBankStatus",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap);               
            }
          
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_update_bankpayments",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("更新银行支付状态异常：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
      
    /**
     * 湖北代理商自助终端现金缴费接口 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chargeCommitByAgent(Map map)
    {
        try
        {
            // 获取操作员ID
            String operid = (String)map.get("operid");
            
            // 获取终端ID
            String termid = (String)map.get("termid");
            
            // 获取当前菜单id
            String menuid = (String)map.get("menuid");
            
            // 获取手机号
            String servnumber = (String)map.get("servnumber");
            
            // 获取缴费金额
            String money = (String)map.get("money");
            
            // 获取缴费类型
            String payType = (String)map.get("payType");
            
            // 获取客户接触id
            String touchoid = (String)map.get("touchoid");
            
            // 获取业务编码
            String businessid = (String)map.get("businessid");
            
            // 获取代理商组织结构编码
            String orgid = (String)map.get("orgid");
            
            // 获取外围流水ID
            String recoid = (String)map.get("recoid");
            
            // 获取发起业务的渠道ID
            String plattype = (String)map.get("plattype");
            
            // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
            // 缴费流水号
            String chargelogoid = (String)map.get("chargelogoid");
            // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
            
            // add begin sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            String presentFee = (String)map.get("presentFee");
            String presentSubject = (String)map.get("presentSubject");
            // add end sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            
            // 统一接口平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("BLDeductAcctBalance"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 业务编码
                inParamMap.put("BUSINESSID", businessid);
                
                // 代理商编码ID
                inParamMap.put("CHANNELID", orgid);
                
                // 交易金额
                inParamMap.put("TRADEAMOUNT", money);
                
                // 外围流水
                inParamMap.put("RECOID", recoid);
                
                // 用户手机号
                inParamMap.put("SERVNUMBER", servnumber);
                
                // 发起业务的渠道ID
                inParamMap.put("PLATTYPE", plattype);
                
                // 手机号码
                inParamMap.put("telnum", servnumber);
                
                // 金额
                inParamMap.put("amount", money);
                
                // 受理类型
                inParamMap.put("pay_type", payType);
                
                // modify begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
                // 缴费流水
                inParamMap.put("chargeLgId", chargelogoid);
                // modify end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
                
                // add begin sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                // 赠送金额，单位：分
                inParamMap.put("presentFee", presentFee);
                
                // 赠送科目
                inParamMap.put("presentSubject", presentSubject);
                // add end sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                
                // 出参键值对 取值键名与输出的键名对应关系数组
                String[][] outParamKeyDefine = { {"dealnum", "dealtime", "presentOid"},
                        {"dealNum", "dealTime", "presentOid"}};
                
                return intMsgUtil.invokeDubbo("BLDeductAcctBalance",
                        menuid,
                        touchoid,
                        "1",
                        servnumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
                
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 业务编码(对应科目)
            Element eReq_businessid = eBody.addElement("businessid");
            eReq_businessid.addText(businessid);
            
            // 代理商编码ID
            Element eReq_channelid = eBody.addElement("channelid");
            eReq_channelid.addText(orgid);
            
            // 交易金额
            Element eReq_tradeamount = eBody.addElement("tradeamount");
            eReq_tradeamount.addText(money);
            
            // 外围流水
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // 用户手机号
            Element eReq_servnumber = eBody.addElement("servnumber");
            eReq_servnumber.addText(servnumber);
            
            // 发起业务的渠道ID
            Element eReq_plattype = eBody.addElement("plattype");
            eReq_plattype.addText(plattype);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 金额
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // 受理类型
            Element eReq_payType = eBody.addElement("pay_type");
            eReq_payType.addText(payType);
            
            // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
            // 缴费流水号
            eBody.addElement("chargelogoid").addText(chargelogoid);
            // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 自助终端-银联对账整改2014(切换为EBUS协议)
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_agentaccchargeback_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("湖北代理商自助终端现金缴费接口异常：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北取系统参数接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap getParamValue(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String region = (String)map.get("region");
            String paramid = (String)map.get("paramid");
            String touchoid = (String)map.get("touchoid");
            
            // 统一接口平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("PTGetParameterByID"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 参数ID
                inParamMap.put("paramID", paramid);
                
                // 出参键值对 取值键名与输出的键名对应关系数组
                String[][] outParamKeyDefine = { {"paramValue"}, {"paramvalue"}};
                
                return intMsgUtil.invokeDubbo("PTGetParameterByID",
                        menuid,
                        touchoid,
                        "0",
                        region,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 参数ID
            Element eReq_paramid = eBody.addElement("paramid");
            eReq_paramid.addText(paramid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_parametervaluebyid_hub",
                    menuid,
                    touchoid,
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSMS(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsContent = "1" + (String)map.get("smsContent");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 短信模板编号
                inParamMap.put("TEMPLATENO", "Atsv0001");
                
                // 手机号码
                inParamMap.put("TELNUM", telnum);
                
                // 参数列表
                inParamMap.put("1", (String)map.get("smsContent"));
                
                return getIntMsgUtil().invokeDubbo("PTSendSmsInfo",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 短信模板编号
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            // 参数列表
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsContent);
            
            // 是否服务间调用
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_sendsmsinfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 获取发票信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getInvoiceData(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String formnum = (String)map.get("formnum");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // 统一接口平台转EBUS开启
            if (IntMsgUtil.isTransEBUS("Atsv_Qry_InvoiceData_Hub"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("telnum", telnum);
                
                // formnum
                inParamMap.put("formnum", formnum);
                
                return intMsgUtil.invokeDubbo("Atsv_Qry_InvoiceData_Hub", msgHeader, inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 交易流水
            Element eReq_smscont = eBody.addElement("formnum");
            eReq_smscont.addText(formnum);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoice_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 月账单查询
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String month = (String)map.get("month");
        String type = (String)map.get("type");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 短信内容
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            // 优先级
            Element eReq_type = eBody.addElement("type");
            eReq_type.addText(type);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_detailedbill_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 账户余额查询
     */
    public ReturnWrap queryBalance(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");// 操作员id
            String atsvNum = (String)map.get("atsvNum");// 终端机id
            String telnumber = (String)map.get("telnumber");// 客户手机号
            String touchoid = (String)map.get("touchoid");// 客户接触id
            String menuid = (String)map.get("menuid");// 当前菜单ID
            
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("nkfqoverage139Mail"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号
                inParamMap.put("telnum", telnumber);
                
                // 0不发送短信，直接通过参数返回结果 1发送短信 2返回短信内容
                inParamMap.put("outflag", "0");
                
                // 0不发送邮件 1发送邮件
                inParamMap.put("isSendMail", "0");
                
                // 出参键值对 取值键名与输出的键名对应关系数组 receiveMoney 应缴话费
                String[][] outParamKeyDefine = {
                        {"balance", "cashBalance", "cardBalance", "monDeduction", "presentBalance",
                                "monpresentBalance", "dkBalance", "predkBalance", "left", "overdraft", "nowfee",
                                "totalowe", "receiveMoney"},
                        {"balance", "cashBalance", "cardBalance", "monDeduction", "presentBalance",
                                "monPresentBalance", "DKBalance", "preDKBalance", "availableBalance", "credit",
                                "realTimeFee", "hisArrears", "receiveMoney"}};
                
                return intMsgUtil.invokeDubbo("nkfqoverage139Mail",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        inParamMap,
                        outParamKeyDefine);
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balance_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String billcycle = (String)map.get("billcycle");
        
        
            // 入参
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // 电话号码
            paramMap.put("telNum", telnumber);
            
            // 查询账期,YYYYMM
            paramMap.put("cycle", billcycle);
            
            // 是否发送邮件，0不发送邮件；1发送邮件
            paramMap.put("isEmail", "0");
            paramMap.put("servType", "ALL");
            paramMap.put("reqCode", "1000");
            
            
            // 发送请求到Dubbo服务
            MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid, "1", telnumber);    
            ReturnWrap rw = intMsgUtil.invokeDubbo("cli_intf_qusages", msgHeaderPO, paramMap,false);    
            
            //1:语音 分钟，2:短信 条，3:彩信 条 4：WLAN MB  5:EDUWLAN MB，6国内流量 MB 7.专用型流量 MB  8 WLAN 分钟 9 EDUWLAN 分钟
            Map attrTypeMap=new HashMap();
            attrTypeMap.put("1", "语音");
            attrTypeMap.put("2", "短信");
            attrTypeMap.put("3", "彩信");
            attrTypeMap.put("4", "WLAN");
            attrTypeMap.put("5", "EDUWLAN");
            attrTypeMap.put("6", "国内流量");
            attrTypeMap.put("7", "专用型流量");
            attrTypeMap.put("8", "WLAN");
            attrTypeMap.put("9", "EDUWLAN");
            if (SSReturnCode.SUCCESS == rw.getStatus())
            {
                String json=(String)rw.getReturnObject();
                try
                {
                    com.alibaba.dubbo.common.json.JSONObject  jsonObj = (com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                    com.alibaba.dubbo.common.json.JSONArray  mealInfoLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("MealInfoLst");
                    List result = new ArrayList();
                    for(int i=0;i<mealInfoLst.length();i++){
                        com.alibaba.dubbo.common.json.JSONObject _obj=(com.alibaba.dubbo.common.json.JSONObject)mealInfoLst.get(i);
                        
                        List listInner = new ArrayList();
                        String attrType=(String)_obj.get("ATTR_TYPE");
                        String attrTypeName=(String)attrTypeMap.get(_obj.get("ATTR_TYPE"));                        
                        listInner.add(attrTypeName==null?attrType:attrTypeName);
                        listInner.add(_obj.get("PRODNAME"));
                        listInner.add(_obj.get("ATTR_NAME"));
                        listInner.add(_obj.get("FREE_VALUE"));
                        listInner.add(_obj.get("USAGE_VALUE"));
                        listInner.add(_obj.get("ATTR_UNIT"));
                        result.add(listInner);
                    }
                    rw.setReturnObject(result);
                }
                catch (ParseException e)
                {
                    rw.setStatus(SSReturnCode.ERROR);
                    rw.setReturnMsg("解析返回值异常");
                    e.printStackTrace();
                }                
            }
            
            return rw;
    }
    
    /**
     * 本机品牌资费及已开通功能（产品展示） <功能详细描述>
     * 
     * @param map
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryFavourable(MsgHeaderPO msgHeaderPO)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("kfQueryProductTree"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 电话号码
                paramMap.put("telnum", msgHeaderPO.getRouteValue());
                
                // 发送请求到Dubbo服务
                return getIntMsgUtil().invokeDubbo("kfQueryProductTree",
                        msgHeaderPO,
                        paramMap,
                        new String[] {"cdeep1", "pkgtype", "packageid", "packagename", "applydate", "enddate"});
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeaderPO.getRouteValue());
            
            return intMsgUtil.invoke("cli_qry_producttree_hub", msgHeaderPO, doc);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询需要预约号码_湖北 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap phoneNumQuery(Map map)
    {
        ReturnWrap rw = null;
        
        String newVersion = (String)map.get("newVersion");
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// 操作员
        String termid = (String)map.get("termID");// 终端机ID
        String menuid = (String)map.get("curMenuId");// 菜单ID
        String region = (String)map.get("region");// 区域
        
        String model = (String)map.get("model");// 号码模式
        
        // 新版自助选号启用
        if ("1".equals(newVersion))
        {
            String maxPrice = (String)map.get("maxPrice");
            String minPrice = (String)map.get("minPrice");
            String prodType = (String)map.get("prodType");
            String telType = (String)map.get("telType");
            String maxcount = (String)map.get("maxcount");
            String iscountry = (String)map.get("iscountry");
            String telnum = (String)map.get("telnum");
            String orgid = (String)map.get("orgid");
            
            // 封装包体入参
            
            // 号码模式
            String encodedMode = "";
            
            try
            {
                encodedMode = URLEncoder.encode(model, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                encodedMode = "model";
                
                log.error("对选号模式进行转码失败：", e);
            }
            
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSQueryAvlTelNum"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 号码符合条件,号段
                paramMap.put("telModel", encodedMode);
                
                // 特殊号码预付金上限
                paramMap.put("maxPreFee", StringUtils.isNotEmpty(maxPrice) ? maxPrice : "1000000");
                
                // 特殊号码预付金下限
                paramMap.put("minPreFee", minPrice);
                
                // 产品品牌
                paramMap.put("prodType", prodType);
                
                // 号码类型
                paramMap.put("resType", telType);
                
                // 返回纪录最大数量，上限为2000
                paramMap.put("telNumCount", maxcount);
                
                // 江苏省内一卡多号的号码全部在县市,地市是没有号码的
                paramMap.put("isCountry", iscountry);
                
                // 电话号码
                paramMap.put("servNumber", telnum);
                
                // 用户选择的地市的orgid
                paramMap.put("qryAvlTelNumOrgid", orgid);
                
                // 发送请求到Dubbo服务//lowConsumFee modify begin liutao 2016-07-30 OR_HUB_201603_1191  自助终端显示内容优化需求（张德伟）
                return getIntMsgUtil().invokeDubbo("BLCSQueryAvlTelNum",
                        menuid,
                        "",
                        "0",
                        region,
                        operid,
                        termid,
                        paramMap,
                        new String[][] { {"telInfoList"}, {"telInfoList"}},
                        new String[] {"telNum", "prepay","lowConsumFee"});
            }
            
            Element eReq_model = eBody.addElement("fitmod");
            eReq_model.addText(encodedMode);
            
            // 特殊号码预付金上限
            Element eReq_maxprice = eBody.addElement("maxprice");
            eReq_maxprice.addText(maxPrice);
            
            // 特殊号码预付金下限
            Element eReq_minprice = eBody.addElement("minprice");
            eReq_minprice.addText(minPrice);
            
            // 产品品牌
            Element eReq_prdtype = eBody.addElement("prdtype");
            eReq_prdtype.addText(prodType);
            
            // 号码类型
            Element eReq_teltype = eBody.addElement("teltype");
            eReq_teltype.addText(telType);
            
            // 返回记录最大的数量
            Element eReq_maxcount = eBody.addElement("maxcount");
            eReq_maxcount.addText(maxcount);
            
            // 特殊号码预付金下限
            Element eReq_iscountry = eBody.addElement("iscountry");
            eReq_iscountry.addText(iscountry);
            
            // 号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 地区编码
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // 用户选择的地市的orgid
            Element eReq_qryavltelnumorgid = eBody.addElement("qryavltelnumorgid");
            eReq_qryavltelnumorgid.addText(orgid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_telnum_hub_new",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid);
            rw = intMsgUtil.invoke(docXML);
        }
        else
        {
            String pur = (String)map.get("pur");// 号码用途(默认传"rsupSal")
            String pageIndex = (String)map.get("pageIndex");// 号码页数
            
            // 封装包体入参
            
            // 号码模式=网号+号段+尾数(11位下划线)
            Element eReq_model = eBody.addElement("model");
            eReq_model.addText(model);
            
            // 号码用途(默认传"rsupSal")
            Element eReq_pur = eBody.addElement("pur");
            eReq_pur.addText(pur);
            
            // 号码页数
            Element eReq_pageIndex = eBody.addElement("pageIndex");
            eReq_pageIndex.addText(pageIndex);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_telnum_hub", menuid, "", "0", region, operid, termid);
            rw = intMsgUtil.invoke(docXML);
        }
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * 预约号码_湖北 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap bespeakPhone(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");// 操作员
            String termid = (String)map.get("termid");// 终端机ID
            String menuid = (String)map.get("curMenuId");// 菜单ID
            String region = (String)map.get("region");// 区域
            
            String telnum = (String)map.get("telnum");// 被预定号码
            String seltelprepay = (String)map.get("seltelprepay");// 预存费
            String channelid = (String)map.get("channelid");// 受理渠道, 默认："bsacAtsv"
            String CredentFlag = (String)map.get("credentFlag");// 凭证类型(0 验证码；1 身份证；2 无凭证)
            String certtype = (String)map.get("certtype");// 证件类型,默认：IdCard，没有为空
            String certid = (String)map.get("certid");// 证件号码,没有为空
            
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("nkfSelNBR"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 电话号码
                paramMap.put("telnum", telnum);
                
                // 预存费
                paramMap.put("seltelprepay", seltelprepay);
                
                // 受理渠道
                paramMap.put("channelid", channelid);
                
                // 凭证类型(0 验证码；1 身份证；2 无凭证)
                paramMap.put("credentflag", CredentFlag);
                
                // 证件类型,默认：IdCard，没有为空
                paramMap.put("certtype", certtype);
                
                // 证件号码,没有为空
                paramMap.put("certid", certid);
                
                // 发送请求到Dubbo服务
                return getIntMsgUtil().invokeDubbo("nkfSelNBR", menuid, "0", "0", region, operid, termid, paramMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 预存费
            Element eReq_seltelprepay = eBody.addElement("seltelprepay");
            eReq_seltelprepay.addText(seltelprepay);
            
            // 受理渠道, 默认："bsacAtsv"
            Element eReq_channelid = eBody.addElement("channelid");
            eReq_channelid.addText(channelid);
            
            // 凭证类型(0 验证码；1 身份证；2 无凭证)
            Element eReq_CredentFlag = eBody.addElement("CredentFlag");
            eReq_CredentFlag.addText(CredentFlag);
            
            // 证件类型,默认：IdCard，没有为空
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            // 证件号码,没有为空
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_occupytel_hub",
                    menuid,
                    "0",
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询网号_湖北 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap netNbrQuery(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// 操作员
        String termid = (String)map.get("termid");// 终端机ID
        String menuid = (String)map.get("curMenuId");// 菜单ID
        String region = (String)map.get("region");// 区域
        
        String nettype = (String)map.get("netType");// 网络类型(默认传"GSM")
        String pur = (String)map.get("pur");// 号码用途(默认传"rsupSal")
        
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("atsvGetNetNbr"))
        {
            // 入参
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // 网络类型
            paramMap.put("nettype", nettype);
            
            // 资源类型
            paramMap.put("pur", pur);
            
            // 发送请求到Dubbo服务
            ReturnWrap rw = getIntMsgUtil().invokeDubbo("atsvGetNetNbr",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid,
                    paramMap,
                    new String[][] { {"retDataList"}, {"retDataList"}});
            
            if (SSReturnCode.SUCCESS == rw.getStatus() && rw.getReturnObject() == null)
            {
                
                rw.setReturnObject(new CRSet());
            }
            
            return rw;
        }
        
        // 封装包体入参
        
        // 网络类型(默认传"GSM")
        Element eReq_nettype = eBody.addElement("nettype");
        eReq_nettype.addText(nettype);
        
        // 号码用途(默认传"rsupSal")
        Element eReq_pur = eBody.addElement("pur");
        eReq_pur.addText(pur);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_netnbr_hub", menuid, "", "0", region, operid, termid);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * 查询网段_湖北 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap netValueQuery(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// 操作员
        String termid = (String)map.get("termid");// 终端机ID
        String menuid = (String)map.get("curMenuId");// 菜单ID
        String region = (String)map.get("region");// 地区
        
        String netnbr = (String)map.get("netnbr");// 网号
        String nettype = (String)map.get("nettype");// 网络类型(默认传"GSM")
        String pur = (String)map.get("pur");// 号码用途(默认传"rsupSal")
        String pageIndex = (String)map.get("pageIndex");// 号码页数
        
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("atsvGetTelSection"))
        {
            // 入参
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // 网号
            paramMap.put("netnbr", netnbr);
            
            // 网络类型
            paramMap.put("nettype", nettype);
            
            // 资源类型
            paramMap.put("pur", pur);
            
            // 发送请求到Dubbo服务
            ReturnWrap rw = getIntMsgUtil().invokeDubbo("atsvGetTelSection",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid,
                    paramMap,
                    new String[][] { {"retDataList"}, {"retDataList"}});
            
            if (SSReturnCode.SUCCESS == rw.getStatus() && rw.getReturnObject() == null)
            {
                
                rw.setReturnObject(new CRSet());
            }
            
            return rw;
        }
        
        // 封装包体入参
        
        // 网号
        Element eReq_netnbr = eBody.addElement("netnbr");
        eReq_netnbr.addText(netnbr);
        
        // 网络类型(默认传"GSM")
        Element eReq_nettype = eBody.addElement("nettype");
        eReq_nettype.addText(nettype);
        
        // 号码用途(默认传"rsupSal")
        Element eReq_pur = eBody.addElement("pur");
        eReq_pur.addText(pur);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_telsection_hub", menuid, "", "0", region, operid, termid);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * 身份证入网预约_湖北 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by hWX5316476 2014-9-17 V200R003C10LG0901 OR_huawei_201409_427 自助终端接入EBUS_身份证入网预约
     */
    public ReturnWrap idCardBook(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");// 操作员
            String termid = (String)map.get("termid");// 终端机ID
            String menuid = (String)map.get("curMenuId");// 菜单ID
            String region = (String)map.get("region");// 区域
            
            String name = (String)map.get("name");// 姓名
            String sex = (String)map.get("sex");// 性别
            String nation = (String)map.get("nation");// 民族
            String birthday = (String)map.get("birthday");// 出生
            String address = (String)map.get("address");// 地址
            String idCard = (String)map.get("idCard");// 公民身份号码
            String idiograph = (String)map.get("idiograph");// 签发机关
            String startDate = (String)map.get("startDate");// 有效期起始日期
            String endDate = (String)map.get("endDate");// 有效期截止日期
            String newAddress = (String)map.get("newAddress");// 最新住址
            
            if (IntMsgUtil.isTransEBUS("CTCSInstallByIdCard"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 证件号
                inParamMap.put("certID", idCard);
                
                // 姓名
                inParamMap.put("certName", name);
                
                // 证件地址
                inParamMap.put("certAddr", address);
                
                // 性别
                inParamMap.put("certGender", sex);
                
                // 证件开始时间
                inParamMap.put("certSrtDate", startDate);
                
                // 证件结束时间
                inParamMap.put("certEndDate", endDate);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, termid, "0", "0", region);
                
                return intMsgUtil.invokeDubbo("CTCSInstallByIdCard", msgHeaderPO, inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 身份证号码
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(idCard);
            
            // 姓名
            Element eReq_name = eBody.addElement("certname");
            eReq_name.addText(name);
            
            // 性别
            Element eReq_sex = eBody.addElement("certgender");
            eReq_sex.addText(sex);
            
            // 民族
            Element eReq_nation = eBody.addElement("nation");
            eReq_nation.addText(nation);
            
            // 出生
            Element eReq_birthday = eBody.addElement("birthday");
            eReq_birthday.addText(birthday);
            
            // 地址
            Element eReq_address = eBody.addElement("certaddr");
            eReq_address.addText(address);
            
            // 签发机关
            Element eReq_idiograph = eBody.addElement("idiograph");
            eReq_idiograph.addText(idiograph);
            
            // 有效期起始日期
            Element eReq_startDate = eBody.addElement("certstartdate");
            eReq_startDate.addText(startDate);
            
            // 有效期截止日期
            Element eReq_endDate = eBody.addElement("certenddate");
            eReq_endDate.addText(endDate);
            
            // 最新住址
            Element eReq_newAddress = eBody.addElement("newAddress");
            eReq_newAddress.addText(newAddress);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_install_bookcertinfo_hub",
                    menuid,
                    "0",
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北优惠业务查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map)
    {
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        
        String favorType = (String)map.get("favorType");
        try
        {
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // add by xkf57421 cli_qry_promotions_hub 接口出参及入参都要带上favorabletype
            Element eReq_favorType = eBody.addElement("favorabletype");
            eReq_favorType.addText(favorType);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_promotions_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    /**
     * 湖北优惠业务受理
     */
    public ReturnWrap privAcceptCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String isSubmit = (String)map.get("issubmit");
            String nCode = (String)map.get("nCode");
            String sType = (String)map.get("sType");
            // add by xkf57421 for bsacAtsvCard begin
            String payType = (String)map.get("payType");
            // add by xkf57421 for bsacAtsvCard end
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 受理优惠
            Element eReq_nCode = eBody.addElement("ncode");
            eReq_nCode.addText(nCode);
            
            // 受理类型 PCOpRec
            Element eReq_sType = eBody.addElement("stype");
            eReq_sType.addText(sType);
            
            // 受理方式 0 预受理 1直接受理
            Element eReq_issubmit = eBody.addElement("issubmit");
            eReq_issubmit.addText(isSubmit);
            
            // add by xkf57421 for bsacAtsvCard begin
            Element eReq_payType = eBody.addElement("paytype");
            eReq_payType.addText(payType);
            // add by xkf57421 for bsacAtsvCard end
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_promotions_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            // logger.error("缴费接口出现异常：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北账单分析查询
     */
    public ReturnWrap qryBillAanlysis(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 月份
            String month = (String)map.get("month");
            
            // add begin by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus 2015/4/1
            if (IntMsgUtil.isTransEBUS("CTCSQryBillAnalysis"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("TELNUM", telnumber);
                
                // 月份
                inParamMap.put("BILLCYCLE", month);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid,
                        MsgHeaderPO.ROUTETYPE_TELNUM, telnumber);
                
                return intMsgUtil.invokeDubbo("CTCSQryBillAnalysis", msgHeaderPO, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus 2015/4/1
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 所需参数
            Element eReq_telnumber = eBody.addElement("telnum");
            eReq_telnumber.addText(telnumber);
            
            Element eReq_region = eBody.addElement("month");
            eReq_region.addText(month);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_billanalysis_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 湖北资费推荐
     */
    public ReturnWrap qryChargeGuide(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String menuid = (String)map.get("curMenuId");
            
            // 用户地区
            String region = (String)map.get("region");
            // 流水编号
            String streamNo = (String)map.get("streamno");
            // 问题编号
            String questionCode = (String)map.get("questioncode");
            // 答案编号
            String answerCode = (String)map.get("answercode");
            
            // add begin by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryChargeGuide"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                inParamMap.put("region", region);
                inParamMap.put("streamno", streamNo);
                inParamMap.put("questioncode", questionCode);
                inParamMap.put("answercode", answerCode);
                
                return intMsgUtil.invokeDubbo("CTCSQryChargeGuide", msgHeader, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 所需参数
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            Element eReq_streamno = eBody.addElement("streamno");
            eReq_streamno.addText(streamNo);
            
            Element eReq_questioncode = eBody.addElement("questioncode");
            eReq_questioncode.addText(questionCode);
            
            Element eReq_answercode = eBody.addElement("answercode");
            eReq_answercode.addText(answerCode);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_chargeguide_hub",
                    menuid,
                    "",
                    "0",
                    "999",
                    operid,
                    atsvNum);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 产品变更，查询可以转换的产品
     */
    public ReturnWrap qryChangeList(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTPCEIGetProdListForChgMainProd"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 地区
                inParamMap.put("region", (String)map.get("region"));
                
                // 业务类型
                inParamMap.put("recType", (String)map.get("rectype"));
                
                // 主体产品Id
                inParamMap.put("mainProdID", (String)map.get("mainprodid"));
                
                return intMsgUtil.invokeDubbo("PTPCEIGetProdListForChgMainProd",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 所需参数
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)map.get("region"));
            
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)map.get("rectype"));
            
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)map.get("mainprodid"));
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_changelist_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("查询用户可以转换的产品失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询用户可以转换的产品失败.");
        }
    }
    
    /**
     * 获取模板明细
     */
    public ReturnWrap qryChgContent(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String menuid = (String)inMap.get("curMenuId");
            String fromProdId = (String)inMap.get("fromProdId");
            String toProdId = (String)inMap.get("toProdId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // 地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)inMap.get("region"));
            
            // 受理类型(Install)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // 新主体产品编码
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            // 模板ID
            Element eReq_templeteid = eBody.addElement("templeteid");
            eReq_templeteid.addText((String)inMap.get("templeteid"));
            
            log.info("调用业务ID[cli_package_chgcontent_hub]开始.");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcontent_hub",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            log.info("调用业务ID[cli_package_chgcontent_hub]开始.");
            
            // 这里需要调用单独的解析
            return intMsgUtil.invokeProdChg(docXML);
            
        }
        catch (Exception e)
        {
            log.error("根据产品模板ID查询模板明细失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "根据产品模板ID查询模板明细失败.");
        }
        
    }
    
    /**
     * 套餐转换的受理
     */
    public ReturnWrap prodChgCommit(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String menuid = (String)inMap.get("curMenuId");
            String sn = (String)inMap.get("sn");
            String fromProdId = (String)inMap.get("fromProdId");
            String toProdId = (String)inMap.get("toProdId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnumber = eBody.addElement("telnum");
            eReq_telnumber.addText(telnumber);
            
            // 主体产品编码
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            // 模板编码
            Element eReq_templeteid = eBody.addElement("templeteid");
            eReq_templeteid.addText((String)inMap.get("templeteid"));
            
            // 受理时间
            Element eReq_recdate = eBody.addElement("recdate");
            eReq_recdate.addText((String)inMap.get("recdate"));
            
            // 生效类型(1)
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText((String)inMap.get("affecttype"));
            
            // 开通的产品编码串
            Element eReq_productset = eBody.addElement("productset");
            eReq_productset.addText((String)inMap.get("productset"));
            
            // ?
            Element eReq_prodsprelation = eBody.addElement("prodsprelation");
            eReq_prodsprelation.addText(notNeedNull((String)inMap.get("prodsprelation")));
            
            // 删除的产品编码串
            Element eReq_delprodset = eBody.addElement("delprodset");
            eReq_delprodset.addText(notNeedNull((String)inMap.get("delprodset")));
            
            // sp编码串
            Element eReq_subsprivattrlist = eBody.addElement("subsprivattrlist");
            eReq_subsprivattrlist.addText(notNeedNull((String)inMap.get("subsprivattrlist")));
            
            // 服务编码串
            Element eReq_subsservattrlist = eBody.addElement("subsservattrlist");
            eReq_subsservattrlist.addText(notNeedNull((String)inMap.get("subsservattrlist")));
            
            Element eReq_delsprelation = eBody.addElement("delsprelation");
            eReq_delsprelation.addText(notNeedNull((String)inMap.get("delsprelation")));
            
            log.info("调用业务ID[cli_package_chgcommit_hub]开始.");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcommit_hub",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            log.info("调用业务ID[cli_package_chgcommit_hub]结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("产品转换受理失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "产品转换受理失败.");
        }
    }
    
    /**
     * 根据用户选择的新产品，查询出该产品的模板列表
     */
    public ReturnWrap getProdTmpList(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // 地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)inMap.get("region"));
            
            // 受理类型(ChangeProduct)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // 新主体产品编码
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            log.info("调用业务ID[cli_package_gettmplist_hub]查询产品的模板开始.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_gettmplist_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("调用业务ID[cli_package_gettmplist_hub]查询产品的模板结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("查询产品的模板列表失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    /**
     * 验证用户选择的新主体产品是否具备转换条件
     * 
     * @param inMap
     * @return
     */
    public ReturnWrap prodChgCheck(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // 旧的主体产品编码
            Element eReq_oldmainprodid = eBody.addElement("oldmainprodid");
            eReq_oldmainprodid.addText((String)inMap.get("oldmainprodid"));
            
            // 新的主体产品编码
            Element eReq_newmainprodid = eBody.addElement("newmainprodid");
            eReq_newmainprodid.addText((String)inMap.get("newmainprodid"));
            
            // 受理类型(ChangProductMain)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // 生效类型(EffectNextMonth)
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText((String)inMap.get("affecttype"));
            
            log.info("调用业务ID[cli_package_chgcheck_hub]验证用户是否具备转换条件开始.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcheck_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("调用业务ID[cli_package_chgcheck_hub]验证用户是否具备转换条件结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("验证用户是否具备转换条件失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    /**
     * 根据GROUPID获取字典表数据 add by LiFeng
     * 
     * @param map
     * @return
     */
    public ReturnWrap getDictItemByGroup(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String groupid = (String)map.get("groupid");
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTPUBQryDictItemsByGrpID"))
            {
                // 入参
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 字典组ID
                paramMap.put("groupID", groupid);
                
                // modify begin gwx223032 2015-05-12 OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1
                // 发送dubbo请求
                ReturnWrap rw = intMsgUtil.invokeDubbo("PTPUBQryDictItemsByGrpID",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        paramMap,
                        new String[] {"dictID", "dictName", "description"});
                // modify end gwx223032 2015-05-12 OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1
                
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                }
                return rw;
                
            }
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // group ID
            Element eReq_groupid = eBody.addElement("groupid");
            eReq_groupid.addText(groupid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_dictitem",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("获取字典表数据失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 Begin
    /**
     * 新增分月返还到账情况查询功能 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthlyReturnInfo(Map inMap)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // 返还开始时间
            Element eReq_oldmainprodid = eBody.addElement("stardate");
            eReq_oldmainprodid.addText((String)inMap.get("stardate"));
            
            // 返还结束时间
            Element eReq_newmainprodid = eBody.addElement("enddate");
            eReq_newmainprodid.addText((String)inMap.get("enddate"));
            
            log.info("调用业务ID[cli_qry_monthlyreturninfo_hub]获取分月返还信息开始.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_monthlyreturninfo_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("调用业务ID[cli_qry_monthlyreturninfo_hub]获取分月返还信息结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("获取分月返还信息失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    /**
     * 业务统一查询接口 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2014-9-11 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public ReturnWrap queryService(MsgHeaderPO msgHead)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号
                inParamMap.put("telNum", msgHead.getTelNum());
                
                // 序号
                inParamMap.put("querySN", "0");
                
                String[] outParamListDefine = new String[] {"objType", "spID", "spName", "spBizID", "spBizName",
                        "spBizType", "billingType", "price", "domain", "startDate", "endDate", "seqNum", "charge",
                        "priceDesc"};
                
                // 发送请求到Dubbo服务
                return intMsgUtil.invokeDubbo("BLCSIntQuerySubsAllServ", msgHead, inParamMap, outParamListDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHead.getTelNum());
            
            // 序号
            DocumentUtil.addSubElementToEle(eBody, "sn", "0");
            
            return intMsgUtil.invoke("cli_qry_spinfo", msgHead, doc);
        }
        catch (Exception e)
        {
            log.error("业务统一查询接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 账单寄送方式提交
     * 
     * @param msgHead 消息头
     * @param billSendType 寄送方式
     * @param mailAddr 邮寄地址
     * @return
     * @remark modify by sWX219697 2014-9-9 09:48:21 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public ReturnWrap billSendCommit(MsgHeaderPO msgHead, String billSendType, String mailAddr)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("CTCSDoSubsBillMailNew"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号码
                inParamMap.put("telNum", msgHead.getTelNum());
                
                // 邮寄类型
                inParamMap.put("mailType", billSendType);
                
                // 邮寄地址
                inParamMap.put("mailAddr", mailAddr);
                
                // 操作类型
                inParamMap.put("oprType", "1");
                
                // 发送请求到Dubbo服务
                return intMsgUtil.invokeDubbo("CTCSDoSubsBillMailNew", msgHead, inParamMap);
                
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHead.getTelNum());
            
            // 账单寄送类型
            DocumentUtil.addSubElementToEle(eBody, "mailtype", billSendType);
            
            // 操作类型，默认为1：开通
            DocumentUtil.addSubElementToEle(eBody, "oprtype", "1");
            
            // 如果邮寄类型为Email帐单，则为Email地址；如果邮寄类型是短信帐单或者彩信帐单，则为手机号码
            DocumentUtil.addSubElementToEle(eBody, "mailaddr", mailAddr);
            
            return intMsgUtil.invoke("cli_busi_sendbill", msgHead, doc);
        }
        catch (Exception e)
        {
            log.error("账单寄送业务受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 End
    
    // 新版账单查询 Add by XKF57421 Begin
    public ReturnWrap qryBillCustInfo(Map map)
    {
        String servnum = (String)map.get("SERVNUM");
        String cyclemonth = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ---湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("CTCSQryBillCycleCustInfo"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("telNum", servnum);
                
                // 月份
                inParamMap.put("cycle", cyclemonth);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", servnum);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("CTCSQryBillCycleCustInfo", msgHeaderPO, inParamMap);
                
                Vector v = new Vector();
                if (rw.getReturnObject() instanceof CTagSet)
                {
                    v.add(rw.getReturnObject());
                    rw.setReturnObject(v);
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("TELNUM");
            eReq_telnum.addText(servnum);
            
            Element eReq_month = eBody.addElement("CYCLE");
            eReq_month.addText(cyclemonth);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_BillCycleCustInfo_hub",
                    menuID,
                    touchOID,
                    "1",
                    servnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    public void addJsonArray2Xml(Element bodyElement,com.alibaba.dubbo.common.json.JSONArray  pkgLst,String[] keys,String parentKey){
        if(pkgLst!=null){
            for(int i=0;i<pkgLst.length();i++){
                Element parentElement=bodyElement.addElement(parentKey);
                com.alibaba.dubbo.common.json.JSONObject _pkgLst=(com.alibaba.dubbo.common.json.JSONObject)pkgLst.get(i);                    
                for(int j=0;j<keys.length;j++){
                    Element element =parentElement.addElement(keys[j]);
                    element.setText((String)_pkgLst.get(keys[j])); 
                }
            }
        }
    }
    public String arQryBillCommuHuBExcelEbus(Map map) throws Exception{
        String telnum = (String)map.get("telnum");
        String billcycle = (String)map.get("billcycle");
        //String isunitepayment = (String)map.get("isunitepayment");
        //String arealist = (String)map.get("arealist");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");        
        Map<String, String> inParamMap = new HashMap<String, String>();      
        // 电话号码
        inParamMap.put("telNum", telnum);
        // 月份
        inParamMap.put("cycle", billcycle);   
        inParamMap.put("printInfo", "1"); 
        MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);        
        ReturnWrap rw = intMsgUtil.invokeDubbo("arQryBillCommuHuBExcelEbus", msgHeaderPO, inParamMap,false);        
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            String json=(String)rw.getReturnObject();
            try
            {
                com.alibaba.dubbo.common.json.JSONObject jsonObj=(com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                com.alibaba.dubbo.common.json.JSONArray  pkgLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("pkgLst");
                com.alibaba.dubbo.common.json.JSONArray  overLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("overLst");
                com.alibaba.dubbo.common.json.JSONArray  bcallLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("bcallLst");
                String printInfo=(String)jsonObj.get("printInfo");
                Document doc = DocumentHelper.createDocument();
                doc.setXMLEncoding("GBK");
                Element bodyElement=doc.addElement("body");
                addJsonArray2Xml( bodyElement,  pkgLst,new String[]{"remainderDesc","includeDesc","usageDesc","className","packageName"},"pkgLst");
                addJsonArray2Xml( bodyElement,  overLst,new String[]{"className","includeDesc"},"overLst");
                addJsonArray2Xml( bodyElement,  bcallLst,new String[]{"usageDesc","className","remainderDesc","includeDesc"},"bcallLst");                
                if(printInfo!=null){
                    Element printInfoElement= bodyElement.addElement("printInfo");
                    printInfoElement.addText(printInfo);
                }                
                //System.out.println(doc.asXML());
                return doc.asXML();
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }
        }
        return null;
    }
    public ReturnWrap qryCurBillInfo(Map map)
    {
        String telnum = (String)map.get("telnum");
        String billcycle = (String)map.get("billcycle");
        String isunitepayment = (String)map.get("isunitepayment");
        String arealist = (String)map.get("arealist");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ------自助终端ebus改造------------------------
            if (IntMsgUtil.isTransEBUS("CTCSQryBillArea"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("telnum", telnum);
                
                // 月份
                inParamMap.put("billcycle", billcycle);
                
                // 
                inParamMap.put("isunitepayment", isunitepayment);
                
                // 
                inParamMap.put("arealist", arealist);
                
                inParamMap.put("operid", operID);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("CTCSQryBillArea", msgHeaderPO, inParamMap);
                
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Vector v = new Vector();
                    if (rw.getReturnObject() instanceof Vector)
                    {
                        v = (Vector)rw.getReturnObject();
                        if (v != null && v.size() > 0)
                        {
                            rw.setReturnObject((CTagSet)v.get(0));
                        }
                    }
                    if (rw.getReturnObject() instanceof CTagSet)
                    {
                        rw.setReturnObject(rw.getReturnObject());
                    }
                }
                return rw;
            }
            // ------------------------------
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_month = eBody.addElement("billcycle");
            eReq_month.addText(billcycle);
            
            Element eReq_custtype = eBody.addElement("isunitepayment");
            eReq_custtype.addText(isunitepayment);
            
            Element eReq_areaFlag = eBody.addElement("arealist");
            eReq_areaFlag.addText(arealist);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_qrybillarea_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // 新版账单查询 Add by XKF57421 End
    
    private String notNeedNull(String inStr)
    {
        return inStr == null ? "" : inStr;
    }
    
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }
    
    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
    // add begin l00190940 2011-12-7 OR_HUB_201112_16
    /**
     * 查询亲情号码
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryFamilyNumber(MsgHeaderPO msgHeader)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("CTCSQryDealDealTelNum"))
            {
                CTagSet cTagSet = new CTagSet();
                
                // 循环将空亲情号码放入CTagSet中
                for (int i = 0; i < 3; i++)
                {
                    cTagSet.SetValue("friendnum" + (i + 1), "");
                }
                
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 电话号码
                paramMap.put("telNum", msgHeader.getTelNum());
                
                // 产品编码，固定值"G8887"
                paramMap.put("prodID", "G8887");
                
                // 发送请求到Dubbo服务
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("CTCSQryDealDealTelNum",
                        msgHeader,
                        paramMap,
                        new String[] {"attrID", "attrValue"});
                
                // 取得所有亲情号码
                CRSet crSet = (CRSet)rw.getReturnObject();
                
                if (crSet != null)
                {
                    // 取前三个亲情号码
                    String[] arrays = new String[] {"FriendNum1", "FriendNum2", "FriendNum3"};
                    
                    // 循环将前三个亲情号码放入CTagSet中
                    for (int i = 0; i < crSet.GetRowCount(); i++)
                    {
                        if (ArrayUtils.contains(arrays, crSet.GetValue(i, 0)))
                        {
                            cTagSet.SetValue(crSet.GetValue(i, 0).toLowerCase(Locale.ENGLISH), crSet.GetValue(i, 1));
                        }
                    }
                }
                
                rw.setReturnObject(cTagSet);
                
                return rw;
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_qry_szxqskqqh", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("查询亲情号失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 设置、修改或取消亲情号码
     */
    public ReturnWrap setFamilyNumber(MsgHeaderPO msgHeader, String sn, String sregion, String stype)
    {
        try
        {
            // add by jWX216858 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 手机号码
                paramMap.put("TELNUM", msgHeader.getTelNum());
                
                // ncode 固定值
                paramMap.put("NCODE", "FriendNew");
                
                // 亲情号码位置。目前只有1、2、3
                paramMap.put("SN", sn);
                
                // 亲情号码，如果是取消，传“”
                paramMap.put("FRIEND", sregion);
                
                // 操作类型，固定传ADD
                paramMap.put("STYPE", stype);
                
                return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
            }
            // add end jWX216858 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeader.getTelNum());
            
            // 操作类型
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(stype);
            
            // 号码位置
            Element eReq_sn = eBody.addElement("sn");
            eReq_sn.addText(sn);
            
            // 亲情号码
            Element eReq_sregion = eBody.addElement("sregion");
            eReq_sregion.addText(sregion);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_szxqskqqh",
                    msgHeader.getMenuId(),
                    msgHeader.getUniContact(),
                    "1",
                    msgHeader.getTelNum(),
                    msgHeader.getOperId(),
                    msgHeader.getTermId());
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("设置亲情号失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end l00190940 2011-12-7 OR_HUB_201112_16
    
    /**
     * 查询用户已存在的档次_湖北
     * 
     * @param map
     * @return
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public ReturnWrap qrySubsDangcisList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSQrySubsValidPrivList"))
            {
                // 入参
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 手机号码
                paramMap.put("servNumber", telnumber);
                
                // 发送dubbo请求
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLCSQrySubsValidPrivList",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[][] { {"result", "privList"}, {"result", "privList"}},
                        new String[] {"privID", "privName"});
                
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Object obj = rw.getReturnObject();
                    
                    if (obj instanceof CTagSet)
                    {
                        rw.setReturnObject(new CRSet());
                    }
                    else if (obj instanceof Vector)
                    {
                        Vector v = (Vector)obj;
                        rw.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rw;
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_subsprivlist",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询用户已存在的档次失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询档次描述_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryDangciDesc(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            String privid = (String)map.get("privid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("privid");
            eReq_telnum.addText(privid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_privdesc",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询档次描述失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询奖品列表_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRewardList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // 活动编码
            String activityId = (String)map.get("activityId");
            
            // 档次编码
            String dangciId = (String)map.get("dangciId");
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTPCEIGetBatchRecPresentsByActId"))
            {
                // 入参
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 活动编码
                paramMap.put("actID", activityId);
                
                // 档次编码
                paramMap.put("privID", dangciId);
                
                // 发送dubbo服务
                ReturnWrap rw = intMsgUtil.invokeDubbo("PTPCEIGetBatchRecPresentsByActId",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[] {"rewardID", "rewardName", "rewardType", "rewardValue", "scoreDealType",
                                "useScore", "rewardInfo"});
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 活动编码
            Element eReq_prodid = eBody.addElement("prodid");
            eReq_prodid.addText(activityId);
            
            // 档次编码
            Element eReq_privid = eBody.addElement("privid");
            eReq_privid.addText(dangciId);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_rewardlist",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询奖品列表失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询营销方案费用和用户预存费用
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivFee(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // 营销方案受理流水
            String recoid = (String)map.get("recoid");
            
            // 用户总费用
            String totalFee = (String)map.get("totalfee");
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSGetSubsRecFeeAndPreFee"))
            {
                // 入参
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 受理编码
                paramMap.put("recOid", recoid);
                
                // 用户存入总费用
                paramMap.put("totalFee", totalFee);
                
                // 发送dubbo请求
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLCSGetSubsRecFeeAndPreFee",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap);
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                    else
                    {
                        CTagSet ctag = (CTagSet)rw.getReturnObject();
                        
                        CRSet crset = new CRSet(2);
                        crset.AddRow();
                        
                        // 受理费用
                        crset.SetValue(0, 0, ctag.GetValue("recFee"));
                        
                        // 预付费用
                        crset.SetValue(0, 1, ctag.GetValue("preFee"));
                        
                        rw.setReturnObject(crset);
                    }
                }
                return rw;
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 营销方案受理流水
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // 用户总费用
            Element eReq_totalFee = eBody.addElement("totalfee");
            eReq_totalFee.addText(totalFee);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_privFee",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询营销方案费用和用户预存费用失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 促销活动受理_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap recRewardCommit(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // 活动编码
            String actno = (String)map.get("actno");
            
            // 档次编码
            String actlevel = (String)map.get("actlevel");
            
            // 活动赠品编号串
            String actrewaed = (String)map.get("actrewaed");
            
            // 手机的IMEI号
            String imeiid = (String)map.get("imeiid");
            
            // 1是预受理；0是受理
            String onlycheck = (String)map.get("onlycheck");
            
            // 赠品数量
            String quantity = (String)map.get("quantity");
            
            // 受理渠道
            String accesstype = (String)map.get("accesstype");
            
            // 密码 只有河北和宁夏不需要校验密码
            String password = (String)map.get("password");
            
            // 用户投入的费用金额 预受理时可以不传
            String totalfee = (String)map.get("totalfee");
            
            // 支付方式cash:现金 card:银联卡
            String paytype = (String)map.get("paytype");
            
            // add begin m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            // 优惠产品的附加属性串
            String addattrstr = (String)map.get("addattrstr");
            if (addattrstr == null)
            {
                addattrstr = "";
            }
            // add end m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSRecRewardactiveLight"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 手机号码
                paramMap.put("servNumber", telnumber);
                
                // 派生类名称
                paramMap.put("invokerName", "CBLCSRecRewardCommit");
                
                // 活动编码
                Map<String, String> actnoMap = new HashMap<String, String>();
                actnoMap.put("actID", actno);
                paramMap.put("act", actnoMap);
                
                // 档次编码
                Map<String, String> levelIDMap = new HashMap<String, String>();
                levelIDMap.put("levelID", actlevel);
                paramMap.put("level", levelIDMap);
                
                // 奖品信息，包括：手机的IMEI号， 奖品编码， 奖品数量
                List<Map<String, Object>> rewardList = new ArrayList<Map<String, Object>>();
                
                String[] rewardIds = actrewaed.split("\\|");
                String[] imeiids = imeiid.split("\\|", -1);
                
                Map<String, Object> rewardAttrList = new HashMap<String, Object>();
                List<Map<String, String>> attrList = new ArrayList<Map<String, String>>();
                for (int i = 0; i < rewardIds.length; i++)
                {
                    Map<String, Object> rewardMap = new HashMap<String, Object>();
                    
                    // 奖品编码
                    rewardMap.put("rewardID", rewardIds[i]);
                    
                    // 手机的IMEI号
                    rewardMap.put("startResID", imeiids[i]);
                    rewardMap.put("endResID", imeiids[i]);
                    
                    // modify begin jWX216858 2015-5-28 BUG_HUB_201503_254 自助终端积分兑换电子卷失败 
                    if ("activitiesRec".equals(menuID))
                    {
                    	// 奖品数量
                    	rewardMap.put("quantity", quantity);
                    }
                    else
                    {
                    	// 奖品数量
                    	rewardMap.put("quantity", Integer.parseInt(quantity));
                    }
                    // modify end jWX216858 2015-5-28 BUG_HUB_201503_254 自助终端积分兑换电子卷失败 
                    
                    if (StringUtils.isNotEmpty(addattrstr))
                    {
                        String[] attrArr = addattrstr.split(",");
                        String attrType = attrArr[1];
                        String attrID = attrArr[2].substring(0, attrArr[2].indexOf("="));
                        String attrValue = attrArr[2].substring(attrArr[2].indexOf("=") + 1, attrArr[2].length());
                        Map<String, String> mapAttr = new HashMap<String, String>();
                        mapAttr.put("attrValue", attrValue);
                        mapAttr.put("attrID", attrID);
                        attrList.add(mapAttr);
                        rewardAttrList.put("attrType", attrType);
                        rewardAttrList.put("attrList", attrList);
                        
                    }
                    rewardMap.put("rewardAttrList", rewardAttrList);
                    rewardList.add(rewardMap);
                }
                paramMap.put("rewardList", rewardList);
                
                // 特殊键值
                Map<String, Object> specialMap = new HashMap<String, Object>();
                
                // 1是预受理；0是受理
                specialMap.put("isOnlyChk", onlycheck);
                
                // 用户投入的费用金额 预受理时可以不传
                specialMap.put("totalFee", totalfee);
                // specialMap.put("presentAttrList", presentList);
                
                paramMap.put("specialList", specialMap);
                
                // 密码
                Map<String, String> rewardExtMap = new HashMap<String, String>();
                
                rewardExtMap.put("password", password);
                paramMap.put("rewardExt", rewardExtMap);
                
                // 支付方式cash:现金 card:银联卡
                List<Map<String, String>> payTypeList = new ArrayList<Map<String, String>>();
                Map<String, String> payTypeMap = new HashMap<String, String>();
                
                payTypeMap.put("payType", paytype);
                payTypeList.add(payTypeMap);
                
                paramMap.put("payTypeList", payTypeList);
                
                if ("1".equals(onlycheck))
                {
                    // 发送dubbo请求
                    return intMsgUtil.invokeDubbo("BLCSRecRewardactiveLight",
                            menuID,
                            touchOID,
                            "1",
                            telnumber,
                            operID,
                            termID,
                            paramMap);
                }
                // 发送dubbo请求
                return intMsgUtil.invokeDubbo("BLCSRecRewardactiveLight",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[][] { {"recOid"}, {"recoid"}});
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 活动编码
            Element eReq_actno = eBody.addElement("actno");
            eReq_actno.addText(actno);
            
            // 档次编码
            Element eReq_actlevel = eBody.addElement("actlevel");
            eReq_actlevel.addText(actlevel);
            
            // 活动赠品编号串
            Element eReq_actreward = eBody.addElement("actrewaed");
            eReq_actreward.addText(actrewaed);
            
            // 手机的IMEI号
            Element eReq_imeiid = eBody.addElement("imeiid");
            eReq_imeiid.addText(imeiid);
            
            // 1是预受理；0是受理
            Element eReq_onlycheck = eBody.addElement("onlycheck");
            eReq_onlycheck.addText(onlycheck);
            
            // 赠品数量
            Element eReq_quantity = eBody.addElement("quantity");
            eReq_quantity.addText(quantity);
            
            // 受理渠道
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // 密码 只有河北和宁夏不需要校验密码
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            // 用户投入的费用金额 预受理时可以不传
            Element eReq_totalfee = eBody.addElement("totalfee");
            eReq_totalfee.addText(totalfee);
            
            // 支付方式cash:现金 card:银联卡
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(paytype);
            
            // add begin m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            // 优惠奖品的附加属性串
            Element eReq_addattrstr = eBody.addElement("addattrstr");
            eReq_addattrstr.addText(addattrstr);
            // add end m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            
            Document docXML = null;
            
            // 银联卡缴费，单独组织报文
            if ("Card".equals(paytype))
            {
                docXML = intMsgUtil.createMsgCard(doc,
                        "cli_busi_recrewardcommit_card",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID);
            }
            else
            {
                docXML = intMsgUtil.createMsg(doc,
                        "cli_busi_recrewardcommit",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID);
            }
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("促销活动受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 促销活动_发票打印
     * 
     * @param map
     * @return
     */
    public ReturnWrap invoiceDataByActivity(Map map)
    {
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String telnum = (String)map.get("telnumber");// 手机号码
        String formnums = (String)map.get("formnums");// 要打印发票的日志流水号，如果有多个则以“|”分割
        String recoid = (String)map.get("recoid");// 交易流水
        
        try
        {
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTSupplyPrintInvoice"))
            {
                // 入参
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 手机号码
                paramMap.put("telnum", telnum);
                
                // 业务流水串
                paramMap.put("formNums", formnums);
                
                // 业务唯一序列
                paramMap.put("recOid", recoid);
                
                // 发送dubbo请求
                ReturnWrap rw = null;
                rw = intMsgUtil.invokeDubbo("PTSupplyPrintInvoice",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        paramMap);
                
                if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
                {
                    CTagSet ctagSet = (CTagSet)rw.getReturnObject();
                    
                    // tag出参
                    CTagSet outParam = new CTagSet();
                    
                    // 组装crset出参
                    CRSet crset = new CRSet(2);
                    
                    Vector v = new Vector();
                    
                    // 发票数目
                    String invoiceCount = ctagSet.GetValue("invoiceCount");
                    outParam.SetValue("invoice_cnt", invoiceCount);
                    
                    v.add(outParam);
                    
                    // 有发票内容，取内容组装出参
                    if (("1").equals(invoiceCount))
                    {
                        // 增加一行
                        crset.AddRow();
                        
                        // 受理日期
                        crset.SetValue(0, 0, ctagSet.GetValue("payTime"));
                        
                        // 发票内容
                        crset.SetValue(0, 1, ctagSet.GetValue("printItems"));
                    }
                    v.add(crset);
                    rw.setReturnObject(v);
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 日志流水
            Element eReq_formnums = eBody.addElement("formnums");
            eReq_formnums.addText(formnums);
            
            // 交易流水
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // 受理渠道
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoiceinfoByActivity_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    // add begin ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    /**
     * 查询未打印的发票记录数据
     * 
     * @param map
     * @return
     */
    public ReturnWrap invoiceList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String accessType = "bsacAtsv";
            
            // modify begin wWX217192 2014-07-16 OR_huawei_201406_300 自助终端接入EBUS二阶段_发票打印
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARQueryUnprintInvoceList"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 手机号码
                paramMap.put("serviceNumber", telnumber);
                
                // 受理渠道
                paramMap.put("accessType", accessType);
                
                // 发送请求到Dubbo服务
                return getIntMsgUtil().invokeDubbo("BLARQueryUnprintInvoceList",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[] {"billCycle", "formnum", "itemName", "custName", "servNumber", "recFee",
                                "accessName", "recDate", "status", "statusDate", "operName", "orgName", "recType",
                                "oid", "invoiceFee", "upperFee"});
            }
            // modify end wWX217192 2014-07-16 OR_huawei_201406_300 自助终端接入EBUS二阶段_发票打印
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // 渠道
            Element eReq_accessType = eBody.addElement("accesstype");
            eReq_accessType.addText(accessType);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_noinvoiceprint_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("查询要打印的发票记录信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询要打印的发票打印项数据
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String formnum = (String)map.get("formnum");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String billCycle = (String)map.get("billCycle");
        
        try
        {
            // modify begin wWX217192 2014-07-16 OR_huawei_201406_300 自助终端接入EBUS二阶段_发票打印
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARReprintInvoice"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 业务受理号
                paramMap.put("recOid", formnum);
                
                // 受理渠道
                paramMap.put("accessType", "bsacAtsv");
                
                // 账期
                paramMap.put("billCycle", billCycle);
                
                // 发送请求到Dubbo服务
                return getIntMsgUtil().invokeDubbo("BLARReprintInvoice",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        paramMap);
            }
            // modify end wWX217192 2014-07-16 OR_huawei_201406_300 自助终端接入EBUS二阶段_发票打印
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 交易流水
            Element eReq_smscont = eBody.addElement("formnum");
            eReq_smscont.addText(formnum);
            
            // 账期
            Element eReq_billCycle = eBody.addElement("billCycle");
            eReq_billCycle.addText(billCycle);
            
            // 受理渠道
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoiceinfo_hub_new",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    // add end ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * 查询产品变更确认信息，包括用户需开通的业务、会保留的业务，需取消的业务
     * 
     * @param map
     * @return
     */
    public ReturnWrap mainProductRecInfo(MsgHeaderPO msgHeader, String ncode, String inttime)
    {
        try
        {
            // modify begin wWX217192 2014-09-20 OR_huawei_201409_433 自助终端接入EBUS_资费套餐转换
            if (IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 手机号码
                paramMap.put("servNum", msgHeader.getTelNum());
                
                // ncode
                paramMap.put("nCode", ncode);
                
                // 操作类型
                paramMap.put("operType", "PCOpRec");
                
                // 生效方式
                paramMap.put("affectType", "Type_NextCycle");
                
                // 受理时间
                paramMap.put("inTime", inttime);
                
                // 是否为产品变更预处理
                paramMap.put("prepareBusi", "PreBsacNBChgMainProd");
                
                // 展示产品列表
                paramMap.put("ebusShowProd", "1");
                
                return getIntMsgUtil().invokeDubbo("BLCSProductRec", msgHeader, paramMap, false);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ncode
            DocumentUtil.addSubElementToEle(eBody, "ncode", ncode);
            
            // 操作类型
            DocumentUtil.addSubElementToEle(eBody, "oprtype", "PCOpRec");
            
            // 渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 生效方式
            DocumentUtil.addSubElementToEle(eBody, "affecttype", "Type_NextCycle");
            
            // 受理时间
            DocumentUtil.addSubElementToEle(eBody, "intime", inttime);
            
            // 是否为产品变更预处理
            DocumentUtil.addSubElementToEle(eBody, "preparebusi", "PreBsacNBChgMainProd");
            
            return intMsgUtil.invoke("cli_busi_MainIntProductRec", msgHeader, msgBody);
            // modify end 2014-09-20 OR_huawei_201409_433 自助终端接入EBUS_资费套餐转换
        }
        catch (Exception e)
        {
            log.error("查询产品变更确认信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 调用接口执行主体产品转换
     * 
     * @param map
     * @return
     */
    public ReturnWrap mainProductChangeSubmit(MsgHeaderPO msgHeader, String ncode)
    {
        try
        {
            // add by jWX216858 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // 手机号码
                paramMap.put("TELNUM", msgHeader.getTelNum());
                
                // 操作类型ADD 增加 DEL 删除 MOD 修改 QRY 查询
                paramMap.put("STYPE", "ADD");
                
                // ncode
                paramMap.put("NCODE", ncode);
                
                // 固定BsacNBSubmit
                paramMap.put("-PREPAREBUSI", "BsacNBSubmit");
                
                return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
            }
            // add end jWX216858 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeader.getTelNum());
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText("ADD");
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText("BsacNBSubmit");
            
            return intMsgUtil.invoke("cli_busi_ChangeProductSubmit", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("产品转换出错：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    
    // add by yedengchu
    
    /**
     * 湖北返还信息查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryBackInfo(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String servnumber = (String)map.get("servnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_fee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by yedengchu
    public ReturnWrap qryAllBackInfo(Map map)
    {
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String qryflag = (String)map.get("qryflag");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus 2015/4/1
            if (IntMsgUtil.isTransEBUS("CTCSQryBalRetInfo"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, touchoid,
                        MsgHeaderPO.ROUTETYPE_TELNUM, telnumber);
                
                Map<String, String> inParamMap = new HashMap<String, String>();
                inParamMap.put("telnum", telnumber);
                inParamMap.put("startdate", startDate);
                inParamMap.put("enddate", endDate);
                inParamMap.put("qryflag", qryflag);
                
                String[] outParamKeyDefine = {"subjectname", "begindate", "enddate", "flowtime", "types", "flowamt",
                        "nowbalance", "rewardid", "rewardname", "remarks"};
                return intMsgUtil.invokeDubbo("CTCSQryBalRetInfo", msgHeader, inParamMap, outParamKeyDefine);
            }
            // add end by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus 2015/4/1
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始日期
            Element eReq_startdate = eBody.addElement("startdate");
            eReq_startdate.addText(startDate);
            
            // 结束日期
            Element eReq_endDate = eBody.addElement("enddate");
            eReq_endDate.addText(endDate);
            // 查询标志
            Element eReq_qryflag = eBody.addElement("qryflag");
            eReq_qryflag.addText(qryflag);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balancereturninfo_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("返还信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by xkf57421 for OR_HUB_201202_1192 begin
    public ReturnWrap validateTelSim(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String iccid = (String)paramMap.get("iccid");
        // String imsi = (String)paramMap.get("imsi");
        String porductid = (String)paramMap.get("porductid");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremCardCkeck"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // 手机号
                map.put("telnum", telnum);
                
                // SIM卡号
                map.put("simnum", iccid);
                
                // 主体产品ID
                map.put("mainprodiid", porductid);
                
                map.put("islocked", "1");
                
                map.put("isblankcard", "0");
                
                return intMsgUtil.invokeDubbo("CTCSTremCardCkeck", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_iccid = eBody.addElement("simnum");
            eReq_iccid.addText(iccid);
            
            /*
             * Element eReq_imsi = eBody.addElement("imsi"); eReq_imsi.addText(imsi);
             */

            Element eReq_porductid = eBody.addElement("mainprodiid");
            eReq_porductid.addText(porductid);
            
            Element eReq_lock = eBody.addElement("islocked");
            eReq_lock.addText("1");
            
            Element eReq_blank = eBody.addElement("isblankcard");
            eReq_blank.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ChkTelSimcard_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("号卡校验失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public ReturnWrap queryFeeItemList(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String mainprodid = (String)paramMap.get("mainprodid");
        String prodtempletid = (String)paramMap.get("prodtempletid");
        String simnum = (String)paramMap.get("simnum");
        String blankcardno = (String)paramMap.get("blankcardno");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSQueryCostList"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // 手机号
                map.put("telnum", telnum);
                
                map.put("mainprodiid", mainprodid);
                
                map.put("prodtempletid", prodtempletid);
                
                map.put("simnum", simnum);
                
                map.put("blankcardno", blankcardno);
                
                String[] outParamKeyDefine = {"entityname", "amount", "quantity", "expenessid"};
                return intMsgUtil.invokeDubbo("CTCSQueryCostList", msgHeader, map, outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_mainprodid = eBody.addElement("mainprodiid");
            eReq_mainprodid.addText(mainprodid);
            
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            Element eReq_blankcardno = eBody.addElement("blankcardno");
            eReq_blankcardno.addText(blankcardno);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ReckonRecFee_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("查询费用明细失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询销售条件
     * 
     * @param paramMap
     * @return author wWX191797 time 2014-04-14 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造
     */
    public ReturnWrap querySaleCond(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        System.out.println("menuID:[" + menuID + "]");
        if (menuID == null || "".equals(menuID))
        {
            menuID = "choSernumAndOrdUse";
        }
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSGetSaleTermFromNum"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // 手机号
                map.put("telnum", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSGetSaleTermFromNum", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            System.out.println("telnum:[" + telnum + "]");
            System.out.println("doc:[" + doc + "]");
            System.out.println("menuID:[" + menuID + "]");
            System.out.println("touchOID:[" + touchOID + "]");
            System.out.println("region:[" + region + "]");
            System.out.println("operID:[" + operID + "]");
            System.out.println("termID:[" + termID + "]");
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            System.out.println("0000000000000000000000000");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_LocQryTelSaleCond_CRSet",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            System.out.println("11111111111111111111111111");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("查询费用明细失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public ReturnWrap commitInstallProd(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String simnum = (String)paramMap.get("simnum");
        String mainprodiid = (String)paramMap.get("mainprodiid");
        String prodtempletid = (String)paramMap.get("prodtempletid");
        String sumprice = (String)paramMap.get("sumprice");
        String custname = (String)paramMap.get("custname");
        String certid = (String)paramMap.get("certid");
        String certtype = (String)paramMap.get("certtype");
        String linkaddr = (String)paramMap.get("linkaddr");
        String sex = (String)paramMap.get("sex");
        String passwd = (String)paramMap.get("passwd");
        String blankcard = (String)paramMap.get("blankcard");
        String imsi = (String)paramMap.get("imsi");
        String telprice = (String)paramMap.get("telprice");
        String linkphone = (String)paramMap.get("linkphone");
        String productlist = (String)paramMap.get("productlist");
        String submailaddr = (String)paramMap.get("submailaddr");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            Element eReq_mainprodid = eBody.addElement("mainprodiid");
            eReq_mainprodid.addText(mainprodiid);
            
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            Element eReq_blankcardno = eBody.addElement("sumprice");
            eReq_blankcardno.addText(sumprice);
            
            Element eReq_custname = eBody.addElement("custname");
            eReq_custname.addText(custname);
            
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            Element eReq_linkaddr = eBody.addElement("linkaddr");
            eReq_linkaddr.addText(linkaddr);
            
            Element eReq_sex = eBody.addElement("sex");
            eReq_sex.addText(sex);
            
            Element eReq_passwd = eBody.addElement("passwd");
            eReq_passwd.addText(passwd);
            
            Element eReq_blankcard = eBody.addElement("blankcard");
            eReq_blankcard.addText(blankcard);
            
            Element eReq_imsi = eBody.addElement("imsi");
            eReq_imsi.addText(imsi);
            
            Element eReq_telprice = eBody.addElement("telprice");
            eReq_telprice.addText(telprice);
            
            Element eReq_linkphone = eBody.addElement("linkphone");
            eReq_linkphone.addText(linkphone);
            
            Element eReq_productlist = eBody.addElement("productlist");
            eReq_productlist.addText(productlist);
            
            Element eReq_submailaddr = eBody.addElement("submailaddr");
            eReq_submailaddr.addText(submailaddr);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TerminalInstall_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("开户失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by xkf57421 for OR_HUB_201202_1192 end
    /**
     * 证件校验 证件校验
     * 
     * @param certType 证件类型
     * @param certId 证件号码
     * @param pesSubsNum 相关号码
     * @param region 地区
     * @param operid 操作员Id
     * @param atsvNum 终端机ID
     * @param menuid 当前菜单Id
     * @return tagset 结果集，
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-12 OR_HUB_201202_1192
     */
    public ReturnWrap certCardInfo(Map paramMap)
    {
        String certType = (String)paramMap.get("certtype");
        String certId = (String)paramMap.get("certid");
        String pesSubsNum = (String)paramMap.get("pessubsnum");
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSChkCertSubs"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        pesSubsNum);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                map.put("subsnum", pesSubsNum);
                map.put("certtype", certType);
                map.put("cretid", certId);
                
                return intMsgUtil.invokeDubbo("CTCSChkCertSubs", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 证件类型
            Element ecertType = eBody.addElement("certtype");
            ecertType.addText(certType);
            // 证件号码
            Element ecertId = eBody.addElement("certid");
            ecertId.addText(certId);
            // 相关号码
            Element epesSubsNum = eBody.addElement("pessubsnum");
            epesSubsNum.addText(pesSubsNum);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ChkCertSubs_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("自助终端证件校验接口：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 获取SIM卡信息
     * 
     * @param
     * @param iccid sim卡_iccid
     * @param region 地区
     * @param operid 操作员Id
     * @param atsvNum 终端机ID
     * @param menuid 当前菜单Id
     * @return tagset 结果集，
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
     */
    public ReturnWrap getSimInfo(Map paramMap)
    {
        String iccid = (String)paramMap.get("iccid");
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremQuerySIM"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // iccid
                map.put("iccid", iccid);
                
                return intMsgUtil.invokeDubbo("CTCSTremQuerySIM", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // sim——iccid
            Element eiccid = eBody.addElement("iccid");
            eiccid.addText(iccid);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_GetSimInfo_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("自助终端查询SIM卡接口：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 获取号码
     * 
     * @param
     * @param fitmod 号码符合条件
     * @param hlrid 归属HLR
     * @param groupid HLR分组号
     * @param istoretype 查询库标志
     * @param teltype 产品品牌
     * @param prdtype 号码类型
     * @param maxcount 返回记录的最大数量
     * @param region 地区
     * @param operid 操作员Id
     * @param atsvNum 终端机ID
     * @param menuid 当前菜单Id
     * @return crset 结果集，
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
     */
    public ReturnWrap getTelnumbs(Map paramMap)
    {
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        String fitmod = (String)paramMap.get("fitmod");
        String hlrid = (String)paramMap.get("hlrid");
        String groupid = (String)paramMap.get("groupid");
        String istoretype = (String)paramMap.get("istoretype");
        String prdtype = (String)paramMap.get("prdtype");
        String teltype = (String)paramMap.get("teltype");
        String maxcount = (String)paramMap.get("maxcount");
        String pur = (String)paramMap.get("pur");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSGetNumListFromHLR"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // fitmod
                map.put("fitmod", fitmod);
                // hlrid
                map.put("hlrid", hlrid);
                // groupid
                map.put("groupid", groupid);
                // istoretype
                map.put("istoretype", istoretype);
                // prdtype
                map.put("prdtype", prdtype);
                // teltype
                map.put("teltype", teltype);
                // maxcount
                map.put("maxcount", maxcount);
                // pur
                map.put("pur", pur);
                
                String[] outParamKeyDefine = {"telnum", "price", "region", "orgid"};
                return intMsgUtil.invokeDubbo("CTCSGetNumListFromHLR", msgHeader, map, outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // sim——iccid
            Element efitmod = eBody.addElement("fitmod");
            efitmod.addText(fitmod);
            Element ehlrid = eBody.addElement("hlrid");
            ehlrid.addText(hlrid);
            Element egroupid = eBody.addElement("groupid");
            egroupid.addText(groupid);
            Element eistoretype = eBody.addElement("istoretype");
            eistoretype.addText(istoretype);
            Element eprdtype = eBody.addElement("prdtype");
            eprdtype.addText(prdtype);
            Element eteltype = eBody.addElement("teltype");
            eteltype.addText(teltype);
            Element emaxcount = eBody.addElement("maxcount");
            emaxcount.addText(maxcount);
            Element epur = eBody.addElement("pur");
            epur.addText(pur);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TheHlrGrpNum_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("自助终端获取号码接口：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 暂选号码
     * 
     * @param telnum 电话号码
     * @param region 地区
     * @param operid 操作员Id
     * @param atsvNum 终端机ID
     * @param menuid 当前菜单Id
     * @return tagset 结果集，
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-17 OR_HUB_201202_1192
     */
    public ReturnWrap chooseTheTelnum(Map paramMap)
    {
        String telnum = (String)paramMap.get("telnum");
        
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremNumberChoice"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                map.put("telnum", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSTremNumberChoice", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 电话号码
            Element etelnum = eBody.addElement("telnum");
            etelnum.addText(telnum);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TelNumTmpPick_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("自助终端号码暂选接口：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    /**
     * 查询用户可推荐的业务列表_湖北营销推荐活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRecommendProductList(MsgHeaderPO msgHeader)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSGetRecommendProductList"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 是否需要在查询推荐列表的接口中进行冲突检验的标志 传空表示不需要检验
                inParamMap.put("isNeedChk", "");
                
                // 手机号码
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // 受理渠道
                inParamMap.put("accessType", "bsacAtsv");
                
                // 业务推荐活动编码
                inParamMap.put("actID", "");
                
                // 需要查询的业务推荐数目
                inParamMap.put("showNum", "5");
                
                // 是否记录业务推荐信息 0 否
                inParamMap.put("isRecOrd", "1");
                
                // 是否展示营销方案类业务推荐 0否
                inParamMap.put("isQueryReward", "1");
                
                // 是否记录短信需要回复的记录表
                inParamMap.put("isRecordfeedback", "1");
                
                String[] outParamDefine = {"oid", "entityName", "recmdDiction", "nCode", "prodid", "privid", "userSeq",
                        "kmsLink", "templateId", "timeType", "actid", "actName", "contactTimes", "notes", "prodName",
                        "privName", "recmdType", "isFeedBackDef", "type"};
                
                return getIntMsgUtil().invokeDubbo("BLCSGetRecommendProductList",
                        msgHeader,
                        inParamMap,
                        null,
                        outParamDefine);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // modify begin wWX217192 2014-08-26 R003C14L08n01 OR_huawei_201408_740 精准营销二期_自助终端渠道改造(切换为EBUS协议)
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 业务推荐活动编码
            DocumentUtil.addSubElementToEle(eBody, "actid", "");
            
            // add begin zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
            // 需要查询的业务推荐数目，传入5
            DocumentUtil.addSubElementToEle(eBody, "shownum", "5");
            
            // 记录推荐信息(传1)
            DocumentUtil.addSubElementToEle(eBody, "isrecord", "1");
            
            // 是否查询营销方案(1 查询 0 不查询) (传1)
            DocumentUtil.addSubElementToEle(eBody, "isqueryreward", "1");
            
            // 是否记录短信反馈表(传1)
            DocumentUtil.addSubElementToEle(eBody, "isrecordfeedback", "1");
            // add end zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
            
            return intMsgUtil.invoke("cli_qry_recommendProductList", msgHeader, msgBody);
            // modify begin wWX217192 2014-08-26 R003C14L08n01 OR_huawei_201408_740 精准营销二期_自助终端渠道改造(切换为EBUS协议)
        }
        catch (Exception e)
        {
            log.error("查询用户可推荐的业务列表失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 记录业务推荐结果_湖北营销推荐活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap recommendFeedback(MsgHeaderPO msgHeader, String userSeqs, String status, String actIds,
            String eventTypes)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSRecommendFeedback"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号码
                inParamMap.put("telNum", msgHeader.getTelNum());
                
                // 受理渠道
                inParamMap.put("accessType", "bsacAtsv");
                
                // 类型
                inParamMap.put("type", eventTypes);
                
                // 业务推荐活动编码
                inParamMap.put("actid", actIds);
                
                // 推荐流水号
                inParamMap.put("userSeq", userSeqs);
                
                // 状态:用户已接触,未响应
                inParamMap.put("status", status);
                
                // 备注
                inParamMap.put("outNotes", "");
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendFeedback", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 推荐流水号
            DocumentUtil.addSubElementToEle(eBody, "userseq", userSeqs);
            
            // 状态:用户已接触,未响应
            DocumentUtil.addSubElementToEle(eBody, "status", status);
            
            // 备注
            DocumentUtil.addSubElementToEle(eBody, "outnotes", "");
            
            // 业务推荐活动编码
            DocumentUtil.addSubElementToEle(eBody, "actid", actIds);
            
            // 类型 even：实时营销。用于区分是实时营销还是前台推荐cs_rec_commendsubs中数据
            DocumentUtil.addSubElementToEle(eBody, "type", eventTypes);
            
            return intMsgUtil.invoke("cli_busi_recommendFeedback", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("记录业务推荐结果失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 推荐业务受理_湖北营销推荐活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap recommendProduct(MsgHeaderPO msgHeader, String userSeq, String actId, String recmdType)
    {
        try
        {
            // modify begin wWX217192 2014-08-26 OR_huawei_201408_740 精准营销二期_自助终端渠道改造(切换为EBUS协议)
            // 湖北统一接口平台转EBUS开关开启 EBUS接口从BLCSRecommendProduct转换至BLCSRecommendProductByActID接口
            if (IntMsgUtil.isTransEBUS("BLCSRecommendProductByActID"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号码
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // 受理渠道
                inParamMap.put("accessType", "bsacAtsv");
                
                // 推荐流水号 从2014-08-26后的EBUS接口协议中不存在此入参 delete by wWX217192
                // inParamMap.put("userSeq", userSeq);
                
                // 活动编码
                inParamMap.put("actID", actId);
                
                // 推荐类型
                inParamMap.put("recmdType", recmdType);
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendProductByActID", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 用户序列号
            DocumentUtil.addSubElementToEle(eBody, "userseq", userSeq);
            
            // 业务推荐活动编码
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // 推荐类型 -1展示类 3营销案类 其它：产品类
            DocumentUtil.addSubElementToEle(eBody, "recmdtype", recmdType);
            
            return intMsgUtil.invoke("cli_busi_recommendProductByActID", msgHeader, msgBody);
            // modify end wWX217192 2014-08-26 OR_huawei_201408_740 精准营销二期_自助终端渠道改造(切换为EBUS协议)
        }
        catch (Exception e)
        {
            log.error("推荐业务受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 推荐业务办理成功，更新业务推荐结果_湖北营销推荐活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap setRecommendSuccess(MsgHeaderPO msgHeader, String commendOID)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSSetRecommendSuccess"))
            {
                // 入参
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 受理渠道
                inParamMap.put("accessType", "bsacAtsv");
                
                // 业务推荐唯一编码
                inParamMap.put("recommendOid", commendOID);
                
                return getIntMsgUtil().invokeDubbo("BLCSSetRecommendSuccess", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 业务推荐唯一流水号
            DocumentUtil.addSubElementToEle(eBody, "commendoid", commendOID);
            
            return intMsgUtil.invoke("cli_busi_setRecommendSuccess", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("更新业务推荐结果：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询回馈定义信息列表
     * 
     * @param msgHeader 报文头信息
     * @param actId 业务推荐活动编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap qryFeedBackDefList(MsgHeaderPO msgHeader, String actId)
    {
        try
        {
            if (IntMsgUtil.isTransEBUS("BLCSGetFeedBackDef"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("actid", actId);
                
                String[] arrAttrsKey = {"recmdName", "nCode", "actinfo", "moContent"};
                
                return getIntMsgUtil().invokeDubbo("BLCSGetFeedBackDef", msgHeader, inParamMap, null, arrAttrsKey);
            }
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 用户手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 业务推荐活动编码
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_qry_getFeedBackDefList", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("查询用户回馈定义信息列表失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 回馈定义受理
     * 
     * @param telNum 用户手机号码
     * @param operId 终端机操作员
     * @param termId 终端机编号
     * @param touchOID
     * @param menuId 菜单Id
     * @param actId 业务推荐活动编码
     * @param contents 回复内容
     * @param recmdType 推荐类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap doFeedBackDef(MsgHeaderPO msgHeader, String actId, String contents, String recmdType)
    {
        try
        {
            if (IntMsgUtil.isTransEBUS("BLCSRecommendProdByResponse"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 服务号码
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // 受理渠道
                inParamMap.put("accessType", "bsacAtsv");
                
                // 活动编码
                inParamMap.put("actID", actId);
                
                // 推荐类型
                inParamMap.put("recmdType", recmdType);
                
                // 回复内容
                inParamMap.put("contents", contents);
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendProdByResponse", msgHeader, inParamMap);
                
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // 业务推荐活动编码
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // 回复内容
            DocumentUtil.addSubElementToEle(eBody, "contents", contents);
            
            // 推荐类型 -1展示类 3营销案类 其它：产品类
            DocumentUtil.addSubElementToEle(eBody, "recmdtype", recmdType);
            
            return intMsgUtil.invoke("cli_busi_recommendProdByReply", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("用户回馈定义受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 铁通宽带交费 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2014-9-22 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
     */
    public ReturnWrap wbankpay(Map<String, String> map, MsgHeaderPO msgHead)
    {
        // 手机号码
        String telnum = map.get("telnum");
        
        // 宽带产品的ncode
        String ncode = map.get("ncode");
        
        // 操作类型
        String oprtype = map.get("oprtype");
        
        // 受理渠道
        String accesstype = map.get("accesstype");
        
        // 费用(分)
        String contrastfee = map.get("contrastfee");
        
        try
        {
            
            // EBUS改造
            if (IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("nCode", ncode);
                inParamMap.put("servNum", telnum);
                inParamMap.put("operType", oprtype);
                inParamMap.put("contrastFee", contrastfee);
                
                // 接口调用
                return intMsgUtil.invokeDubbo("BLCSProductRec", msgHead, inParamMap, new String[][] { {"formNumber"},
                        {"dealNum"}});
            }
            
            Document docXML = intMsgUtilNew.createMsg(map);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            // cli_rec_wbandpay
            Element eReq_prodlistreq = body.addElement("cli_busi_wbandrec");
            
            // tagset
            Element eReq_tagset = eReq_prodlistreq.addElement("tagset");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", telnum);
            
            // 宽带产品的ncode
            DocumentUtil.addSubElementToEle(eReq_tagset, "ncode", ncode);
            
            // 操作类型
            DocumentUtil.addSubElementToEle(eReq_tagset, "oprtype", oprtype);
            
            // 受理渠道
            DocumentUtil.addSubElementToEle(eReq_tagset, "accesstype", accesstype);
            
            // 费用(分)
            DocumentUtil.addSubElementToEle(eReq_tagset, "contrastfee", contrastfee);
            
            // 调用
            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("铁通宽带交费：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 资助终端账单协同查询之139email
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
    public ReturnWrap billColQuery139Email(Map map)
    {
        String region = (String)map.get("region");
        String telnum = (String)map.get("SERVNUM");
        String qrytype = "0";
        String sendtype = "1";
        String triggertype = "1";
        String channel = "102";
        String modelid = "he1002";
        String sendprior = "01";
        String busicode = "ngsendmail";
        String starttime = DateUtilHub._getCurrentTime();
        String statustime = DateUtilHub._getCurrentTime();
        String isqrybill = "0";
        String cycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSQrySendEmail"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, touchOID, MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> mapInParam = new HashMap<String, String>();
                
                mapInParam.put("telnum", telnum);
                mapInParam.put("region", region);
                mapInParam.put("qrytype", qrytype);
                mapInParam.put("sendtype", sendtype);
                mapInParam.put("triggertype", triggertype);
                mapInParam.put("channel", channel);
                mapInParam.put("modelid", modelid);
                mapInParam.put("sendprior", sendprior);
                mapInParam.put("busicode", busicode);
                mapInParam.put("starttime", starttime);
                mapInParam.put("statustime", statustime);
                mapInParam.put("isqrybill", isqrybill);
                mapInParam.put("cycle", cycle);
                mapInParam.put("billinfo", "0");
                
                return intMsgUtil.invokeDubbo("CTCSQrySendEmail", msgHeader, mapInParam);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(qrytype);
            Element eReq_sendtype = eBody.addElement("sendtype");
            eReq_sendtype.addText(sendtype);
            Element eReq_triggertype = eBody.addElement("triggertype");
            eReq_triggertype.addText(triggertype);
            Element eReq_channel = eBody.addElement("channel");
            eReq_channel.addText(channel);
            Element eReq_modelid = eBody.addElement("modelid");
            eReq_modelid.addText(modelid);
            Element eReq_sendprior = eBody.addElement("sendprior");
            eReq_sendprior.addText(sendprior);
            Element eReq_busicode = eBody.addElement("busicode");
            eReq_busicode.addText(busicode);
            // Element eReq_mailaddr = eBody.addElement("mailaddr");
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(starttime);
            Element eReq_statustime = eBody.addElement("statustime");
            eReq_statustime.addText(statustime);
            Element eReq_isqrybill = eBody.addElement("isqrybill");
            eReq_isqrybill.addText(isqrybill);
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            // Element eReq_billinfo = eBody.addElement("billinfo");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_sendbillby139mail_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 免费抽奖
     * 
     * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706 电子渠道-抽奖平台-抽奖接口改造
     */
    public ReturnWrap mianFeiChouJiang(Map map)
    {
        String actId = (String)map.get("ACTID");
        String telnum = (String)map.get("SERVNUM");
        String channelId = (String)map.get("CHANNELID");
        
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("TELNUM");
            eReq_telnum.addText(telnum);
            
            Element eReq_actId = eBody.addElement("ACTID");
            eReq_actId.addText(actId);
            Element eReq_chanelId = eBody.addElement("CHANNELID");
            eReq_chanelId.addText(channelId);
            // Element eReq_userret = eBody.addElement("USERRET");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_svc_busi_takeoutpraize",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 资助终端账单协同查询之短信
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
    public ReturnWrap billColQueryMessage(Map map)
    {
        String telnum = (String)map.get("SERVNUM");
        String billcycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSSmsNewBill"))
            {
                // 创建报文头信息
                // 终端机
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, touchOID, MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // 入参
                Map<String, String> mapInParam = new HashMap<String, String>();
                
                // 手机号
                mapInParam.put("telnum", telnum);
                // 账期
                mapInParam.put("billcycle", billcycle);
                
                return intMsgUtil.invokeDubbo("CTCSSmsNewBill", msgHeader, mapInParam);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_billcycle = eBody.addElement("billcycle");
            eReq_billcycle.addText(billcycle);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_smsnew_qry_bill2012",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 资助终端账单协同查询之彩信
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
    public ReturnWrap billColQueryColorMessage(Map map)
    {
        String telnum = (String)map.get("SERVNUM");
        String billcycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryMmsBillDetail"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("telNum", telnum);
                
                // 月份
                inParamMap.put("billCycle", billcycle);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSQryMmsBillDetail", msgHeaderPO, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_billcycle = eBody.addElement("billcycle");
            eReq_billcycle.addText(billcycle);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_selfTerm_qry_mmsbilldetail",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 电子券返还信息查询
     * 
     * @param [参数1] [参数1说明]
     * @param [参数2] [参数2说明]
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create yKF73963（2013-03-18） OR_HUB_201301_780 关于BOSS触发手机支付电子券的分月赠送的需求
     * 
     */
    public ReturnWrap qryEcashReturnInfo(Map map)
    {
        
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String region = (String)map.get("region");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryEcacheReturnInfo"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("telNum", telnumber);
                
                // 是否短信 0.不发短信 1.发短信
                inParamMap.put("isSendmsg", "0");
                
                // 开始日期
                inParamMap.put("billCycle", startDate);
                
                // 结束日期
                inParamMap.put("billCycleEnd", endDate);
                
                // 发短信时不能为空
                inParamMap.put("templateNo", "");
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid, "1", telnumber);
                
                String[] outParamKeyDefine = {"validCyle", "expireCyle", "procTime", "ecashValue", "leftValue",
                        "activityName", "retCycle", "totalCycles", "totalValue"};
                return intMsgUtil.invokeDubbo("CTCSQryEcacheReturnInfo", msgHeaderPO, inParamMap, outParamKeyDefine);
            }
            // add end by c00233019 [OR_HUB_201410_634]自助终端本地开发界面业务迁移到ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始日期
            Element eReq_startdate = eBody.addElement("startDate");
            eReq_startdate.addText(startDate);
            
            // 结束日期
            Element eReq_endDate = eBody.addElement("endDate");
            eReq_endDate.addText(endDate);
            // 地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            // 是否短信
            Element eReq_sms = eBody.addElement("IS_SENDMSG");
            eReq_sms.addText("0");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_self_ecashReturnInfoQuery_Hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("电子券返还信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询电子券返还返还信息出现异常！");
        }
    }
    
    /**
     * 上网流量受理
     * 
     * @param msgHeader 报文头信息
     * @param productset 开通增值产品串
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap gprsWlanRec(MsgHeaderPO msgHeader, String productset)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSChgOrChkMainProd"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 手机号码
                inParamMap.put("servNum", msgHeader.getTelNum());
                
                // 受理类型
                inParamMap.put("recType", "ChangeProduct");
                
                // 增值产品串
                inParamMap.put("productSet", productset.trim());
                
                // 调用服务接口
                return intMsgUtil.invokeDubbo("BLCSChgOrChkMainProd", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 受理类型
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // 开通增值产品串
            DocumentUtil.addSubElementToEle(eBody, "productset", productset);
            
            // 受理日期
            DocumentUtil.addSubElementToEle(eBody, "recdate", "");
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_busi_GprsWlanRec", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("上网流量受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 数据业务受理
     * 
     * @param msgHeader 报文头信息
     * @param spbizid sp业务编码
     * @param spid 企业编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
     */
    public ReturnWrap spRec(MsgHeaderPO msgHeader, String spbizid, String spid)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ"))
            {
                // 入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 业务信息
                List<Map> detailInfo = new ArrayList<Map>();
                
                // modify begin hWX5316476 2014-9-24 OR_HUB_201305_29 自助终端接入EBUS_自选套餐
                Map<String, Object> detailInfomap = new HashMap<String, Object>();
                
                // 对象ID：SP企业编码
                detailInfomap.put("spID", spid);
                
                // sp业务编码：梦网业务为SP业务编码，其他为产品ID
                detailInfomap.put("spBizID", spbizid);
                
                // 操作类型
                detailInfomap.put("operType", "Order");
                
                detailInfo.add(detailInfomap);
                // modify end hWX5316476 2014-9-24 OR_HUB_201305_29 自助终端接入EBUS_自选套餐
                
                // 手机号码
                paramMap.put("servNum", msgHeader.getTelNum());
                
                // 获取操作类型
                paramMap.put("operType", "Order");
                
                // 业务信息
                paramMap.put("detailInfo", detailInfo);
                
                // 发送请求到Dubbo服务
                return getIntMsgUtil().invokeDubbo("BLCSChangeSubsMonServ",
                        msgHeader,
                        paramMap,
                        new String[][] { {"orderResult", "formNumber"}, {"orderresult", "formnum"}});
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 企业编码
            DocumentUtil.addSubElementToEle(eBody, "spid", spid);
            
            // sp业务类型
            DocumentUtil.addSubElementToEle(eBody, "spbizid", spbizid);
            
            // 业务类型
            DocumentUtil.addSubElementToEle(eBody, "opertype", "Order");
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_busi_SpRec", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("上网流量受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 向用户发送随机密码短信 <功能详细描述>
     * 
     * @param map
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    public ReturnWrap sendSmsHub(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsparam = (String)map.get("smsparam");
        String templateno = (String)map.get("templateno");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
            // 入参
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 短信模板编号
            inParamMap.put("TEMPLATENO", templateno);
            
            // 手机号码
            inParamMap.put("TELNUM", telnum);
            
            // 参数列表
            String[] str = smsparam.split("#");
            
            for (int i = 0; i < str.length; i++)
            {
                inParamMap.put(str[i].substring(0, 1), str[i].substring(1));
            }
            
            return getIntMsgUtil().invokeDubbo("PTSendSmsInfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID,
                    inParamMap);
        }
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 短信模板编号
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText(templateno);
            
            // 参数列表
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsparam);
            
            // 是否服务间调用
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_sendsmsinfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("发送短信失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin jWX216858 2014/6/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
    /**
     * 查询账期（湖北）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
    @Override
    public ReturnWrap qryBillCycle(MsgHeaderPO msgHeader, String billCycle)
    {
        try
        {
            // add begin jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 自助终端接入EBUS_月结发票
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARBillCycleCustInfo"))
            {
                // 入参
                Map<String, String> paraMap = new HashMap<String, String>();
                
                // 手机号
                paraMap.put("servNumber", msgHeader.getTelNum());
                
                // 账期 YYYYMM格式
                paraMap.put("cycleMonth", billCycle);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLARBillCycleCustInfo", msgHeader, paraMap, new String[] {
                        "cycle", "startDate", "endDate", "acctID", "isUnion"});
                return rw;
            }
            // add end jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 自助终端接入EBUS_月结发票
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号
            eBody.addElement("servnum").addText(msgHeader.getTelNum());
            
            // 账期
            eBody.addElement("cycle").addText(billCycle);
            
            return intMsgUtil.invoke("cli_qry_billCycleCustInfo", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("查询账期失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 开户时证件号码校验
     * 
     * @param msgHeader 报文头信息
     * @param certType 证件类型
     * @param certId 证件号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 证件类型
            inParamMap.put("certType", certType);
            
            // 证件号码
            inParamMap.put("certID", certId);
            
            return intMsgUtil.invokeDubbo("PTGetMobileNumByCert", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("开户时证件号码校验接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "开户时证件号码校验接口调用异常");
        }
    }
    
    /**
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param selTelRule 选号规则
     * @param telPrefix 号码前缀
     * @param telSuffix 号码后缀
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String selTelRule, String telPrefix,
            String telSuffix, String mainProdid)
    {
        try
        {
            // 入参
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 单位
            inParamMap.put("orgid", orgId);
            
            // 查询类型 2：按前缀查询 3：按后缀查询 4：随机
            inParamMap.put("seleRule", selTelRule);
            
            // 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
            inParamMap.put("telPrefix", telPrefix);
            
            // 号码后缀
            inParamMap.put("telSuffix", telSuffix);
            
            // 产品ID
            inParamMap.put("prodid", mainProdid);
            
            // 返回号码列表：手机号码，品牌信息，选号费（单位：分），单位（预约时使用），保底费用（最低消费）（单位：分），预存费用（单位：分）
            String[] outParamKeyDefine = {"telnum", "brand", "price", "orgid", "lowConsumFee", "lowConsumPre"};
            
            return intMsgUtil.invokeDubbo("BLCSResGetTelnumsForATM", msgHeader, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("依据选号规则查询手机号码列表异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "依据选号规则查询手机号码列表异常");
        }
    }
    
    /**
     * 号码资源暂选接口
     * 
     * @param msgHeader
     * @param inParamMap 入参
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSLockOrUnLockTelNum", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("号码资源暂选接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "号码资源暂选接口调用异常");
        }
    }
    
    /**
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 报文头信息
     * @param inParamMap 入参
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSCheckSaleRealSign", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("校验空白卡资源是否可用接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验空白卡资源是否可用接口调用异常");
        }
    }
    
    /**
     * 空白卡资源暂选
     * 
     * @param msgHeader 报文头信息
     * @param blankNo 空白卡卡号
     * @param telNum 手机号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            // 入参
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 空白卡号
            inParamMap.put("blankCardNo", blankNo);
            
            // 手机号码
            inParamMap.put("telNum", telNum);
            
            return intMsgUtil.invokeDubbo("BLCSGetPersonalData", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("空白卡资源暂选异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "空白卡资源暂选调用异常");
        }
        
    }
    
    /**
     * 校验空白卡是否是预置空卡
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param telNum 手机号码
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 空白卡号
            inParamMap.put("blankCardNo", blankNo);
            
            // 手机号
            inParamMap.put("severNumber", telNum);
            
            // 是否返回预置数据（可以传0，如果传0，后面出参presetData是空的）
            inParamMap.put("retPreSetData", "0");
            
            // 预置空卡鉴权数据次序号（传空）
            inParamMap.put("dataSeq", "");
            
            // 是否是预置空卡，空白卡资源
            String[][] outParamKeyDefine = { {"isPresetBlankCard", "resTypeId"}, {"isPresetBlankCard", "resTypeId"}};
            
            return intMsgUtil.invokeDubbo("BLCSChkPreSetBlankCard", msgHeader, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("校验空白卡是否是预置空卡接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验空白卡是否是预置空卡接口调用异常");
        }
    }
    
    /**
     * 获取月结发票信息（湖北）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
    @Override
    public ReturnWrap getMonInvoiceData(MsgHeaderPO msgHeader, CyclePO cycle)
    {
        try
        {
            // add begin jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 自助终端接入EBUS_月结发票
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARIntPrintAddValueBillInv"))
            {
                // 入参
                Map<String, String> map = new HashMap<String, String>();
                
                // 手机号码
                map.put("telNum", msgHeader.getTelNum());
                
                // 账期
                map.put("billCycle", cycle.getCycle());
                
                // 开始时间
                map.put("startDate", cycle.getStartdate());
                
                // 结束时间
                map.put("endDate", cycle.getEnddate());
                
                // 主账号
                map.put("acctID", cycle.getAcctid());
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLARIntPrintAddValueBillInv", msgHeader, map, new String[] {
                        "invoiceTypeItem", "invoiceInfo"});
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (rw.getReturnObject() instanceof Vector)
                    {
                        Vector v = (Vector)rw.getReturnObject();
                        rw.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rw;
            }
            // add end jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 自助终端接入EBUS_月结发票
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            eBody.addElement("servnum").addText(msgHeader.getTelNum());
            
            // 账期
            eBody.addElement("billCycle").addText(cycle.getCycle());
            
            // 开始时间
            eBody.addElement("startDate").addText(cycle.getStartdate());
            
            // 结束时间
            eBody.addElement("endDate").addText(cycle.getEnddate());
            
            // 主账号
            eBody.addElement("acctId").addText(cycle.getAcctid());
            return intMsgUtil.invoke("cli_qry_monthinvoiceinfo", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("月结发票数据查询失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 补卡时检验手机号码和身份证信息是否一致
     * 
     * @param msgHeader
     * @param idCardNo
     * @return
     * @remark create by sWX219697 2014-10-25 13:59:39 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("servnum", msgHeader.getTelNum());
            
            // 身份证号码
            map.put("certid", idCardNo);
            
            return intMsgUtil.invokeDubbo("BLCSChkCertByServnum", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("补卡时检验手机号码和身份证信息是否一致失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 补卡业务规则校验
     * 
     * @param msgHeader
     * @return
     * @remark create by sWX219697 2014-10-25 14:12:49 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader, String subscriber)
    {
        try
        {
            // 入参
            Map<String, Object> map = new HashMap<String, Object>();
            
            // 将用户信息字符串转成map，直接传subscriber串不能正常解析
            Map<String, Object> subscriberMap = JSONObject.fromObject(subscriber);
            
            // 用户信息
            map.put("subscriber", subscriberMap);
            
            Map<String, String> params = new HashMap<String, String>();
            
            params.put("isThrowException", "0");
            
            //业务类型
            params.put("recType", "ChangeEnum");
            params.put("channelType", "ALL");
            params.put("subRecType", "");
            params.put("isRollBack", "0");
            
            //提示类型
            params.put("promptType", "before");
            
            //参数list
            map.put("params", params);
            
            return intMsgUtil.invokeDubbo("PTCheckRecValid", msgHeader, map, new String[][] { {"retInfo"}, {"retInfo"}});
        }
        catch (Exception e)
        {
            log.error("补卡业务规则校验!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    /**
     * 获取写卡信息加密数据
     * @param msgHead 公共消息头
     * @param telnum 手机号
     * @param iccid ICCID
     * @param blankno 空白卡序列号
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-28 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户) 
     */
    public ReturnWrap getEncryptedData(MsgHeaderPO msgHead, Map<String, Object> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWirteDataEncrypt", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("获取写卡信息加密数据异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "获取写卡信息加密数据异常");
        }
    }
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-28 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("servNum", msgHeader.getTelNum());
            
            //iccid
            map.put("enum", iccid);
            
            //空白卡卡号
            map.put("blankCardNo", blankCardNum);
            
            //是否校验规则 0:不校验，1：校验
            map.put("isCheckRecValid", "1");
            
            String[] str = {"fee", "feeName", "chargeType", "discount"};
            
            return intMsgUtil.invokeDubbo("BLCSQryChangeEnumFee", msgHeader, map, str);
        }
        catch (Exception e)
        {
            log.error("补卡算费：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param recFee 应缴费用
     * @param payType 支付方式
     * @param blankno 空白卡卡号     
     * @param simInfo sim卡信息
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-30 13:57:27 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, String blankno,
            SimInfoPO simInfo)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("servNumber", msgHeader.getTelNum());
            
            map.put("accessType", "bsacAtsv");
            
            //iccid
            map.put("enum", simInfo.getIccid());
            
            //imsi
            map.put("imsi", simInfo.getImsi());
            
            //总费用 分
            map.put("sumFee", recFee);
            
            //是否减免，传0
            map.put("isDerateFee", "0");
            
            //空白卡卡号
            map.put("blankCardNo", blankno);
            
            //校验规则
            map.put("isCheckRecValid", "1");
            
            //支付方式
            map.put("payType", payType);
            
            //传1
            map.put("isNeedFee", "1");
            
            return intMsgUtil.invokeDubbo("BLCSChangeEnum", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("补卡提交：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询手机号码是否有备卡
     * @param msgHeader
     * @param subsID 
     * @return
     * @remark create by c00233019 2014-10-25 13:59:39 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
     */
    public ReturnWrap qryStoreCard(MsgHeaderPO msgHeader, String subsID)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // subsid
            map.put("subsID", subsID);
            
            return intMsgUtil.invokeDubbo("BLCSQrySubsstandbyCard", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("查询手机号码是否有备卡：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <备卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
     */
    public ReturnWrap reckonrecfeeByStore(MsgHeaderPO msgHeader, String servnum, String iccid)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("telNum", servnum);
            
            //iccid
            map.put("simCardNo", iccid);
            
            return intMsgUtil.invokeDubbo("BLCSStandByGetQryRecFee", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("备卡算费：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <备卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
     */
    public ReturnWrap prepareCashCommit(MsgHeaderPO msgHeader, String servnum, String iccid, String tMoney,
            String payType)
    {
        
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("telNum", servnum);
            
            //iccid
            map.put("simCardNo", iccid);
            
            //子业务类型
            map.put("subsRecType", "StandbyGetAtsv");
            
            //总费用（用户投入金额）
            map.put("sumFee", tMoney);
            
            //支付方式
            map.put("payType", payType);
            
            return intMsgUtil.invokeDubbo("BLCSStandByGet", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("备卡提交：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public IntMsgUtilNew getIntMsgUtilNew()
    {
        return intMsgUtilNew;
    }
    
    public void setIntMsgUtilNew(IntMsgUtilNew intMsgUtilNew)
    {
        this.intMsgUtilNew = intMsgUtilNew;
    }
    
    /**
     * 空白卡开户
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSNetAgentInstall", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("预置空白卡开户异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "预置空白卡开户异常");
        }
    }
    
    /**
     * 写卡失败 作废卡接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWriteBlankCardFail", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("作废卡接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "作废卡接口调用异常");
        }
    }
    
    /**
     * 校验写卡结果接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap checkWriteCardInfo(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWirteCardConfirmResult", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("校验写卡结果接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验写卡结果接口调用异常");
        }
    }
    
    /**
     * 计算开户费用
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-31 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap reckonRecFee(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            // 费用名称，金额(分)，数量，费用项，减免费用，应收费用
            String[] outParamKeyDefine = {"feeName", "fee", "quantity", "feeID", "discount", "realfee"};
            
            return intMsgUtil.invokeDubbo("BLCSQryRecFeeForInstall", msgHead, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("计算开户费用接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "计算开户费用接口调用异常");
        }
    }
    
    /**
     * <查询用户信息>
     * <功能详细描述>
     * @param msgHeader
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-11-6 18:08:12 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader, String region)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            // 手机号码
            map.put("telNum", msgHeader.getTelNum());
            
            //地区
            map.put("region", region);
            map.put("warning", "false");
            map.put("qryUnfinish", "false");
            
            return intMsgUtil.invokeDubbo("PTGetSubscriberByTel", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("查询用户信息接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "补卡查询用户信息失败");
        }
        
    }
    
    /** 对手机号进行实名制验证
     * <功能详细描述>
     * @return String true:验证证成功 其他：验证失败
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    @Override
    public ReturnWrap isRealName(MsgHeaderPO msgHeader)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 手机号
            inParamMap.put("telNum", msgHeader.getTelNum());
            
            return intMsgUtil.invokeDubbo("BLCSUnionChkExistedCustInfo", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("检验是否是实名制用户接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "检验是否是实名制用户接口调用异常");
        }
    }
    
    /** 宽带预约提交
     * <功能详细描述>
     * @param telNum 预约手机号码
     * @param currArea 预约地点
     * @param currProd 预约产品
     * @param iDcard   身份证号
     * @param installDate 预约安装时间
     * @param band  带宽
     * @return String true:成功, 其他：失败
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     * @modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   
     */
    @Override
    public ReturnWrap broadBandAppoint(String currArea,String installDate, String cardIdNum, String currProd, String band, MsgHeaderPO msgHeader)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // 手机号
            inParamMap.put("telNum", msgHeader.getTelNum());
            
            // 装机地址
            inParamMap.put("countryName", currArea);
            
            // add begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
            // 带宽
            inParamMap.put("band", band);
            // 产品名称
            inParamMap.put("prodName", currProd);
            // 身份证号
            inParamMap.put("certId", cardIdNum);
            
            // 预约时间  格式yyyy-MM-dd
            String installDates = installDate.substring(0, 4) + "-" + installDate.substring(4, 6) + "-"
                    + installDate.substring(6, 8)+"  00:00:00";
            inParamMap.put("installDate", installDates);
            // add end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
            
            return intMsgUtil.invokeDubbo("BLCSMBandPreInstallHUB", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("宽带预约提交接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "宽带预约提交接口调用异常");
        }
    }
    
    /**
     * <查询余额明细接口>
     * <功能详细描述>
     * @param msgHeader
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2016-03-31 OR_HUB_201602_493
     */
    public ReturnWrap showBalanceDetail(MsgHeaderPO msgHeader)
    {
    	try
    	{
    		Map<String, String> inParamMap = new HashMap<String, String>();
    		
    		// 手机号码
    		inParamMap.put("telNum", msgHeader.getTelNum());
    		
    		String[] outParam = {"categories", "subject", "range", "consumption", "validity", "destroy"};
    		
    		return intMsgUtil.invokeDubbo("BLARInterQryAllSubjectInfo", msgHeader, inParamMap, outParam);
    	}
    	catch(Exception e)
    	{
    		log.error("查询余额明细接口调用失败", e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询余额明细接口调用异常");
    	}
    }

}
