package com.customize.nx.selfsvc.call.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.nx.selfsvc.call.SelfSvcCallNX;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class SelfSvcCallNXImpl implements SelfSvcCallNX
{
    private static Log logger = LogFactory.getLog(SelfSvcCallNXImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
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
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_balance_nx", menuid,
                touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("余额查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 月账单查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String month = (String)map.get("month");
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
            
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            Element eReq_contactid = eBody.addElement("contactid");
            eReq_contactid.addText(touchOID);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_detailedbill_nx", menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("月账单查询失败：", e);
            
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
        String smsContent = (String)map.get("smsContent");
        String priority = (String)map.get("priority");
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
            Element eReq_smscont = eBody.addElement("smscont");
            eReq_smscont.addText(smsContent);
            
            // 优先级
            Element eReq_priority = eBody.addElement("priority");
            eReq_priority.addText(priority);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_sendsms", menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("短信发送失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 缴费查询接口
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
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_fee_nx", menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("缴费查询接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 缴费接口
     * 
     * @param map
     * @return
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
            String touchoid = (String)map.get("touchoid");
            String terminalSeq = (String) map.get("terminalSeq");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);

            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText("1");
            
            Element eReq_contactid = eBody.addElement("formnum");
            eReq_contactid.addText(terminalSeq);
            
            // 金额
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            Element eReq_invoicetype = eBody.addElement("invoicetype");
            eReq_invoicetype.addText("0");
            
            Element eReq_chargetype = eBody.addElement("chargetype");
            eReq_chargetype.addText("0");
            
            Element eReq_bankaccount = eBody.addElement("bankaccount");
            eReq_bankaccount.addText("");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chargefee_nx", menuid,
                touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("缴费失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询发票
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map<String, String> map)
    {
        try
        {
            String operid = map.get("operid");
            String termid = map.get("termid");
            String menuid = map.get("menuid");
            String servnumber = map.get("servnumber");
            String money = map.get("money");
            String touchoid = map.get("touchoid");
            String channel = map.get("channel");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 渠道
            Element eReq_invoicechannel = eBody.addElement("invoicechannel");
            eReq_invoicechannel.addText(channel);
            
            // 缴费金额（分）
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_invoice_nx", menuid,
                touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询发票失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }

    /**
     * 宁夏套餐信息查询
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String billcycle = (String)map.get("billcycle");
        
        // 封装包体入参
        // 手机号码
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telnumber);
        
        // 当前账期
        Element eReq_billcycle = eBody.addElement("billcycle");
        eReq_billcycle.addText(billcycle);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_taocan_nx", menuid,
            touchoid, "1", telnumber, operid, atsvNum);
        return intMsgUtil.invoke(docXML);
    }
    
    /**
     * 根据手机号码查询客户信息
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getCustinfo(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cycle = (String)map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 账期
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_custinfo", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询客户信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }       
    }
    
    /**
     * 月账单查询新版
     */
    public ReturnWrap qryMonthBill_new(Map map)
    {
        try
        {
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            String telnum = (String)map.get("telnum");// 手机号码
            String acctid = (String)map.get("acctid");// 帐户ID，同客户信息查询接口返回的主账号
            String subsid = (String)map.get("subsid");// 用户ID
            String startcycle = (String)map.get("startcycle");// 开始帐期
            String starttime = (String)map.get("starttime")+"000000";// 帐期开始时间
            String endtime = (String)map.get("endtime")+"235959";// 帐期结束时间
            String isunitpayment = (String)map.get("isunitpayment");// 是否合并付费
            String region = (String)map.get("region");// 区域
            String arealist = (String)map.get("arealist");// 区域列表
            String factory = (String)map.get("factory");// 厂家编码的密文标识，不能为空
            
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 用户手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 帐户ID，同客户信息查询接口返回的主账号
            Element eReq_acctid = eBody.addElement("acctid");
            eReq_acctid.addText(acctid);
            
            // 用户ID
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(subsid);
            
            // 开始帐期
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText(startcycle);
            
            // 帐期开始时间
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(starttime);
            
            // 帐期结束时间
            Element eReq_endtime = eBody.addElement("endtime");
            eReq_endtime.addText(endtime);
            
            // 是否合并付费
            Element eReq_isunitpayment = eBody.addElement("isunitpayment");
            eReq_isunitpayment.addText(isunitpayment);
            
            // 区域
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // 区域列表
            Element eReq_arealist = eBody.addElement("arealist");
            eReq_arealist.addText(arealist);
            
            // 厂家编码的密文标识，不能为空
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText(factory);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_bill2012_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke_MonthFee_NX(docXML);
        }
        catch (Exception e)
        {
            logger.error("新版月账单查询失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 支付方式
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_chargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("用户现有支付方式查询失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 取消银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap cancelChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 支付方式
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_delchargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("删除银行划扣支付方式失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 新增银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap addChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            String bankid = (String) paramMap.get("bankid");
            String bankacct = (String) paramMap.get("bankacct");
            String drawtype = (String) paramMap.get("drawtype");
            String drawamt = (String) paramMap.get("drawamt");
            String trigamt = (String) paramMap.get("trigamt");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 支付方式
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            // 银行编码
            Element eReq_bankid = eBody.addElement("bankid");
            eReq_bankid.addText(bankid);
            
            // 银行账号
            Element eReq_bankacct = eBody.addElement("bankacct");
            eReq_bankacct.addText(bankacct);
            
            // 划扣类型
            Element eReq_drawtype = eBody.addElement("drawtype");
            eReq_drawtype.addText(drawtype);
            
            // 划扣金额
            Element eReq_drawamt = eBody.addElement("drawamt");
            eReq_drawamt.addText(drawamt);
            
            // 触发金额
            Element eReq_trigamt = eBody.addElement("trigamt");
            eReq_trigamt.addText(trigamt);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_addchargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("新增银行划扣支付方式失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询产品变更确认信息，包括用户需开通的业务、会保留的业务，需取消的业务
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // ncode
            String ncode = (String)map.get("ncode");
            
            // 操作类型
            String oprtype = (String)map.get("oprtype");
            
            // 渠道
            String accesstype = (String)map.get("accesstype");
            
            // 生效方式
            String affecttype = (String)map.get("affecttype");
            
            // 受理时间
            String intime = (String)map.get("intime");
            
            String preparebusi = (String)map.get("preparebusi");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_oprtype = eBody.addElement("oprtype");
            eReq_oprtype.addText(oprtype);
            
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText(affecttype);
            
            Element eReq_intime = eBody.addElement("intime");
            eReq_intime.addText(intime);
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText(preparebusi);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_MainIntProductRec", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询产品变更确认信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 调用接口执行主体产品转换
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductChangeSubmit(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // ncode
            String ncode = (String)map.get("ncode");
            
            // 操作类型
            String stype = (String)map.get("stype");
            
            String preparebusi = (String)map.get("preparebusi");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(stype);
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText(preparebusi);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_ChangeProductSubmit", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("产品转换出错：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  查询可变更主体产品列表
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap qryChangeMainProduct(Map<String,String> map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // 受理方式
            String rectype = (String)map.get("rectype");

            //渠道
            String channel = (String)map.get("channel");

            // 解决方案
            String solutionid = (String)map.get("solutionid");

            // 产品类型
            String prodtype = (String)map.get("prodtype");

            // 目录编码
            String catalogid = (String)map.get("catalogid");

            // 用户的主体产品ID
            String prodid = (String)map.get("prodid");

            // 地区编码
            String region = (String)map.get("region");

            // 单位编码
            String org = (String)map.get("org");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 受理方式
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);

            //渠道
            Element eReq_channel = eBody.addElement("channel");
            eReq_channel.addText(channel);
            
            // 解决方案
            Element eReq_solutionid = eBody.addElement("solutionid");
            eReq_solutionid.addText(solutionid);
            
            // 产品类型
            Element eReq_prodtype = eBody.addElement("prodtype");
            eReq_prodtype.addText(prodtype);
            
            // 目录编码
            Element eReq_catalogid = eBody.addElement("catalogid");
            eReq_catalogid.addText(catalogid);
            
            // 用户的主体产品ID
            Element eReq_prodid = eBody.addElement("prodid");
            eReq_prodid.addText(prodid);
            
            // 地区编码
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // 单位编码
            Element eReq_org = eBody.addElement("org");
            eReq_org.addText(org);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_changeMainProduct", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询可变更主体产品失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据类型查询积分值
     * 
     * @param map
     * @return returnWrap
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap)
    {
        try
        {
            // 当前菜单ID
            String menuID = (String)paramMap.get("curMenuId");
            
            // 客户接触id
            String touchOID = (String)paramMap.get("touchOID");
            
            // 操作员ID
            String operID = (String)paramMap.get("operID");
            
            // 终端机ID
            String termID = (String)paramMap.get("termID");
            
            // 用户手机号码
            String telnum = (String)paramMap.get("telnum");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 查询方式 默认传“0” 个人积分
            Element eReq_paytype = eBody.addElement("qrytype");
            eReq_paytype.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userscoreInfo", menuID,
                touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("根据类型查询积分失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 开户时证件号码校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark OR_NX_201303_280  宁夏自助终端优化需求之在线开户
     */
    public ReturnWrap chkCertSubs(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchoid");
        
        // 证件类型
        String certtype = (String)map.get("certtype");
        
        // 证件号码
        String certid = (String)map.get("certid");
        
        // 默认传1
        String choicetelnum = (String)map.get("choicetelnum");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 证件类型
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            // 证件号码
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            // 当前入网的数量，默认传1
            Element eReq_choicetelnum = eBody.addElement("choicetelnum");
            eReq_choicetelnum.addText(choicetelnum);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_chkcertsubs_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("证件号码校验失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询手机号码列表
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark OR_NX_201303_280  宁夏自助终端优化需求之在线开户
     */
    public ReturnWrap qryNumberByProdid(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchOID");

        // 机构
        String orgid = (String)map.get("orgid");
        
        // 查询类型 2：按前缀查询 3：按后缀查询
        String sele_rule = (String)map.get("sele_rule");
        
        // 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
        String tel_prefix = (String)map.get("tel_prefix");
        
        // 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
        String tel_suffix = (String)map.get("tel_suffix");
                
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 机构
            Element eReq_orgid = eBody.addElement("county_id");
            eReq_orgid.addText(orgid);
              
            // 查询类型
            Element eReq_sele_rule = eBody.addElement("sele_rule");
            eReq_sele_rule.addText(sele_rule);
             
            // 号码后缀
            Element eReq_tel_suffix = eBody.addElement("tel_suffix");
            eReq_tel_suffix.addText(tel_suffix);
            
            // 号码前缀
            Element eReq_tel_prefix = eBody.addElement("tel_prefix");
            eReq_tel_prefix.addText(tel_prefix);
                        
          
            Document docXML = intMsgUtil.createMsg(doc, "cli_qrynumberlist", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询手机号码列表失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 号码资源暂选接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchoid");
        
        // 手机号码 
        String msisdn = (String)map.get("telnum");
        
        // 资源类型 rsclTgsm
        String restype = (String)map.get("restype");
        
        // 操作标志 0为锁定，1为解锁
        String operflag = (String)map.get("operflag");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_msisdn = eBody.addElement("msisdn");
            eReq_msisdn.addText(msisdn);
            
            // 资源类型
            Element eReq_restype = eBody.addElement("restype");
            eReq_restype.addText(restype);
            
            // 操作标志
            Element eReq_operflag = eBody.addElement("operflag");
            eReq_operflag.addText(operflag);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_telnumtmppick_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("号码资源暂选失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 校验空白卡资源是否可用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchoid");
        
        // 空白卡序列号
        String blankno = (String)map.get("blankno");
        
        // 组织单位
        String orgid = (String)map.get("orgid");
        
        // 资源大类
        String res_kind_id = (String)map.get("res_kind_id");
        
        // 资源类型 传：""
        String res_type_id = (String)map.get("res_type_id");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 地区
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // 单位
            Element eReq_org_id = eBody.addElement("org_id");
            eReq_org_id.addText(orgid);
            
            // 操作员
            Element eReq_operid = eBody.addElement("oper_id");
            eReq_operid.addText(operID);
            
            // 资源大类
            Element eReq_reskindid = eBody.addElement("res_kind_id");
            eReq_reskindid.addText(res_kind_id);
            
            // 资源类型
            Element eReq_restypeid = eBody.addElement("res_type_id");
            eReq_restypeid.addText(res_type_id);
            
            // 资源大类
            Element eReq_startsn = eBody.addElement("startsn");
            eReq_startsn.addText(blankno);
            
            // 资源大类
            Element eReq_endsn = eBody.addElement("endsn");
            eReq_endsn.addText(blankno);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_chkblankno_nx", menuID, touchOID, "0", region, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("校验空白卡资源是否可用失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 空白卡资源暂选
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchOID");
        
        // 空白卡序列号
        String blankno = (String)map.get("blankno");
        
        // 服务号码
        String telnum = (String)map.get("telnum");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 服务号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 空白卡序列号
            Element eReq_blankno = eBody.addElement("blankcardnum");
            eReq_blankno.addText(blankno);            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_blankcardtmppick_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("空白卡资源暂选失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 号卡校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkTelSimcard(Map map)
    {
    	// 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchOID");
        
        // 手机号码
        String telnum = (String)map.get("telnum");
        
        // SIM卡ICCID
        String iccid = (String)map.get("iccid");
        
        // 产品编码
        String mainprodid = (String)map.get("porductid");
        
        // 业务类型（传Install）
        String rectype = (String)map.get("rectype");
        
        // 是否已经锁定  传1
        String islocked = (String)map.get("islocked");
        
        // 是否是空白卡 传0
        String isblankcard = (String)map.get("isblankcard");
        
        // 代理商ID orgid
        String agentid = (String)map.get("agentid");

        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");

            // 手机号码
            Element eReq_telnum = eBody.addElement("servnum");
            eReq_telnum.addText(telnum);
            
            // SIM卡ICCID
            Element eReq_iccid = eBody.addElement("iccid");
            eReq_iccid.addText(iccid);
            
            // 产品编码
            Element eReq_porductid = eBody.addElement("mainprodid");
            eReq_porductid.addText(mainprodid);
            
            // 业务类型（传Install）
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);
            
            // 是否已经锁定  传1
            Element eReq_islocked = eBody.addElement("islocked");
            eReq_islocked.addText(islocked);
            
            // 是否是空白卡 传0
            Element eReq_isblankcard = eBody.addElement("isblankcard");
            eReq_isblankcard.addText(isblankcard);

            //  代理商ID orgid
            Element eReq_agentid = eBody.addElement("agentid");
            eReq_agentid.addText(agentid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_chktelsimcard_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("号卡校验失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 计算费用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap reckonRecFee(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchOID");
        
        // 手机号码
        String telnum = (String)map.get("telnum");
        
        // 主体产品编码
        String mainprodid = (String)map.get("mainprodid");
        
        // 产品模板编码
        String prodtempletid = (String)map.get("prodtempletid");
        
        // iccid SIM卡号
        String simnum = (String)map.get("simnum");
        
        // 空白卡卡号
        String blankcardno= (String)map.get("blankcardno");
        
        // 是否返回 减免费用 传1
        String retdiscountfee= (String)map.get("retdiscountfee");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("installtelnum");
            eReq_telnum.addText(telnum);
            
            // 主体产品编码
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText(mainprodid);
            
            // 产品模板编码
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            // iccid SIM卡号
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            // 空白卡卡号
            Element eReq_blankcardno = eBody.addElement("blankcardno");
            eReq_blankcardno.addText(blankcardno);
            
            // 空白卡卡号
            Element eReq_retdiscountfee = eBody.addElement("retdiscountfee");
            eReq_retdiscountfee.addText(retdiscountfee);

            Document docXML = intMsgUtil.createMsg(doc, "cli_reckonrecfee_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("计算费用失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 开户
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap terminalInstall(Map map)
    {
        // 菜单
        String menuID = (String)map.get("menuID");
        
        // 操作员ID
        String operID = (String)map.get("operID");
        
        // 终端ID
        String termID = (String)map.get("termID");
        
        // 地区
        String region = (String)map.get("region");
        
        // 统一接触流水
        String touchOID = (String)map.get("touchOID");
        
        // 渠道bsacAtsv
        String accesstype = (String)map.get("accesstype");
        
        // 手机号码
        String telnum = (String)map.get("installtelnum");
        
        // 卡号 SIM
        String simnum = (String)map.get("simnum");

        // imsi
        String imsi= (String)map.get("imsi");
        
        // 主体产品编码
        String mainprodid = (String)map.get("mainprodid");
        
        // 产品模板编码
        String prodtempletid = (String)map.get("prodtempletid");
        
        // 选号费(可选)，这里0即可
        String telprice= (String)map.get("telprice");
        
        // 业务类型（传Install）
        String rectype= (String)map.get("rectype");
        
        // 证件类型
        String certtype= (String)map.get("certtype");
        
        // 证件号
        String certid= (String)map.get("certid");
 
        // 客户名称
        String custname= (String)map.get("custname");
        
        // 用户缴纳费用
        String totalfee= (String)map.get("totalfee");
        
        // 密码
        String password= (String)map.get("password");
        
        // 客户联系电话
        String linkphone= (String)map.get("linkphone");
        
        // 联系地址
        String linkaddr= (String)map.get("linkaddr");
        
        // 默认传0
        String existdetail= (String)map.get("existdetail");
        
        // 客户地址
        String custaddr= (String)map.get("custaddr");
        
        // 联系人姓名
        String linkname= (String)map.get("linkname");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 渠道
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // 开户号码
            Element eReq_telnum = eBody.addElement("installtelnum");
            eReq_telnum.addText(telnum);

            // 卡号
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            // imsi
            Element eReq_imsi = eBody.addElement("imsi");
            eReq_imsi.addText(imsi);
            
            // 主体产品编码
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText(mainprodid);
            
            // 产品模板编码
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);

            // 选号费(可选)，这里直接填空
            Element eReq_telprice = eBody.addElement("telprice");
            eReq_telprice.addText(telprice);
            
            // 业务类型
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);
            
            // 证件类型
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);

            // 证件号
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);

            // 客户名称
            Element eReq_custname = eBody.addElement("custname");
            eReq_custname.addText(custname);
            
            // 用户缴纳总费用
            Element eReq_totalfee = eBody.addElement("totalfee");
            eReq_totalfee.addText(totalfee);
            
            // 密码
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            // 客户联系电话
            Element eReq_linkphone = eBody.addElement("linkphone");
            eReq_linkphone.addText(linkphone);
            
            // 地址
            Element eReq_linkaddr = eBody.addElement("linkaddr");
            eReq_linkaddr.addText(linkaddr);
            
            // existdetail
            Element eReq_existdetail = eBody.addElement("existdetail");
            eReq_existdetail.addText(existdetail);
            
            // 客户地址
            Element eReq_custaddr = eBody.addElement("custaddr");
            eReq_custaddr.addText(custaddr);
            
            // 联系人姓名
            Element eReq_linkname = eBody.addElement("linkname");
            eReq_linkname.addText(linkname);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_terminalinstall_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("开户失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    //add begin jWX216858 2014/6/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
    /**
     * 查询账期（宁夏）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
     @Override
 	public ReturnWrap qryBillCycle(Map<String, String> paramMap) 
     {
     	String telnum = paramMap.get("servnum");
    		String operId = paramMap.get("operId");
    		String menuId = paramMap.get("menuId");
    		String touchId = paramMap.get("touchId");
    		String termId = paramMap.get("termId");
    		String cycle = paramMap.get("cycle");
    		try
    		{
    			Document doc = DocumentHelper.createDocument();
    			Element eBody = doc.addElement("message_content");
    			
    			// 手机号
    			eBody.addElement("servnum").addText(telnum);
    			
    			// 账期
    			eBody.addElement("cycle").addText(cycle);
    			
    			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billCycleCustInfo", menuId, touchId, "1", telnum, operId, termId);
    			
    			return intMsgUtil.invoke(docXML);
    		}
    		catch (Exception e)
    		{
    			logger.error("查询账期失败!", e);
         	
    			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    		}
 	}
        
    /**
     * 获取月结发票信息（宁夏）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
	@Override
	public ReturnWrap getMonInvoiceData(Map<String, String> paramMap)
	{
		String telnum = paramMap.get("telnum");
		String operId = paramMap.get("operId");
		String menuId = paramMap.get("menuId");
		String touchId = paramMap.get("touchId");
		String termId = paramMap.get("termId");
		String cycle = paramMap.get("cycle");
		String startdate = paramMap.get("startdate");
		String enddate = paramMap.get("enddate");
		String acctId = paramMap.get("acctid");
		try
		{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//手机号码
			eBody.addElement("servnum").addText(telnum);
			
			//账期
			eBody.addElement("billCycle").addText(cycle);
			
			// 开始时间
			eBody.addElement("startDate").addText(startdate);
			
			// 结束时间
			eBody.addElement("endDate").addText(enddate);
			
			// 主账号
			eBody.addElement("acctId").addText(acctId);
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_monthinvoiceinfo", menuId, touchId, "1", telnum, operId, termId);
			
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("月结发票数据查询失败!", e);
     	
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	//add end jWX216858 2014/6/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印

}
