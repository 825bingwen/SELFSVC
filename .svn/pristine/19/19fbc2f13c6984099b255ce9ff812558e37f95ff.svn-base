package com.customize.cq.selfsvc.call.impl;

import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.cq.selfsvc.call.SelfSvcCallCQ;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class SelfSvcCallCQImpl implements SelfSvcCallCQ
{
    private static Log logger = LogFactory.getLog(SelfSvcCallCQImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    /**
     * 重庆套餐信息查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String cycle = (String)map.get("cycle");
            String menuid = (String)map.get("curMenuId");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //add begin l00190940 2011-11-16 套餐信息查询
            // 账期
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            //add end l00190940 2011-11-16 套餐信息查询
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_taocan_cq", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("套餐信息查询失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 山东月账单查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnumber");
            String billCycle = (String)map.get("billCycle");
            
            IntMsgUtil imu = new IntMsgUtil();
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 账单模板类型，山东固定传23
            Element eReq_type = eBody.addElement("type");
            eReq_type.addText("23");
            
            // 查询月份
            Element eReq_cycleoffset = eBody.addElement("month");
            eReq_cycleoffset.addText(billCycle);
            
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = imu.createMsg(doc, "cli_qry_detailedbill", menuID, touchOID, "1", telnumber, operID, termID);
            
            return imu.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("月账单查询失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }       
    }
    
    /**
     * 账户余额查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryBalance(Map map)
    {
        try
        {                
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balance_cq",
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
            logger.error("余额查询失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("余额查询异常");
            
            return rw;
        }    
    }
    

    /**
     * 话费充值账户信息查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
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
            String bankNo = (String)map.get("bankNo");
            String payDate = (String)map.get("payDate");
            String acceptType = (String)map.get("acceptType");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 银行行号
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(bankNo);
            
            // 缴费时间
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 受理类型
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_fee", menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("缴费前查询账户信息失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 话费充值
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String servnumber = (String)map.get("servnumber");
            String bankNo = (String)map.get("bankNo");
            String payDate = (String)map.get("payDate");
            String terminalSeq = (String)map.get("terminalSeq");
            String money = (String)map.get("money");
            String acceptType = (String)map.get("acceptType");
            String printFlag = (String)map.get("invoiceType");
            String bankSite = (String)map.get("bankSite");
            String bankOper = (String)map.get("bankOper");
            String touchoid = (String)map.get("touchoid");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 银行行号
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(bankNo);
            
            // 缴费时间
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 银行交易流水号
            Element eReq_bankSeq = eBody.addElement("bank_nbr");
            eReq_bankSeq.addText(terminalSeq);
            
            // 金额
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // 受理类型
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            
            // 打印标志
            Element eReq_printFlag = eBody.addElement("print_flag");
            eReq_printFlag.addText(printFlag);
            
            // 银行营业网点编号
            Element eReq_bankSite = eBody.addElement("bsite");
            eReq_bankSite.addText(bankSite);
            
            // 银行操作员编号
            Element eReq_bankOper = eBody.addElement("boperid");
            eReq_bankOper.addText(bankOper);
            
            // 接入渠道
            Element eReq_system = eBody.addElement("systemid");
            eReq_system.addText("1");// 自助终端
            
            docXML = intMsgUtil.createMsg(doc, "cli_busi_chargefee", menuid, touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("缴费失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            return rw;
        }
    }
    
    /**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap sendSMS(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsContent = "1" + (String)map.get("smsContent");
        String priority = (String)map.get("priority");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            IntMsgUtil imu = new IntMsgUtil();
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //短信模板编号
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            //参数列表
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsContent);
            
            //是否服务间调用
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            docXML = imu.createMsg(doc, "cli_busi_sendsmsinfo", menuID, touchOID, "1", telnum, operID, termID);
            return imu.invoke(docXML);

        }
        catch (Exception e)
        {
            logger.error("发送短信失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 不校验密码，直接获取用户信息
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getUserStatus(Map map)
    {
        try
        {            
            String telnumber = (String)map.get("telnum");
            String password = (String)map.get("password");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // 是否校验密码,0不校验密码
            Element eReq_isCheck = eBody.addElement("ischeckpass");
            eReq_isCheck.addText("0");
            
            // 密码
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userinfo", "", "", "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("获取用户信息失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    
    /**
     * 获取账单寄送查询的返回信息
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getMailBillSendInfo(Map map)
    {
        try
        {          
            String telnumber = (String)map.get("telnum");
            String billType = (String)map.get("billtype");
            String mailType = (String)map.get("mailtype");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 账单类型
            Element eReq_billType = eBody.addElement("billtype");
            eReq_billType.addText(billType);
            
            // 邮寄类型
            Element eReq_mailType = eBody.addElement("mailtype");
            eReq_mailType.addText(mailType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("获取账单寄送信息失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * 撤销原邮箱寄送
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
	public ReturnWrap getCancelCaseInfo(Map map) 
	{
    	try
        {          
            String telnumber = (String)map.get("telnum");
            String oid = (String)map.get("oid");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 账单类型
            Element eReq_billType = eBody.addElement("oid");
            eReq_billType.addText(oid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_cancelbillmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("撤销原邮箱寄送失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	
	/**
     * 开通邮箱寄送
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
	public ReturnWrap getOpenBillMailInfo(Map map) 
	{
		try
        {          
            String telnumber = (String)map.get("telnum");
            String billType = (String)map.get("billtype");
            String langType = (String)map.get("langtype");
            String mailType = (String)map.get("mailtype");
            String mailAddr = (String)map.get("mailaddr");
            String postCode = (String)map.get("postcode");
            String linkPhone = (String)map.get("linkphone");
            String linkMan = (String)map.get("linkman");
            String email = (String)map.get("email");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 账单类型
            Element eReq_billType = eBody.addElement("billtype");
            eReq_billType.addText(billType);
            
            // 语言类型
            Element eReq_langType = eBody.addElement("langtype");
            eReq_langType.addText(langType);
            
            // 邮寄类型
            Element eReq_mailType = eBody.addElement("mailtype");
            eReq_mailType.addText(mailType);
            
            // 邮寄地址
            Element eReq_mailAddr = eBody.addElement("mailaddr");
            eReq_mailAddr.addText(mailAddr);
            
            // 邮政编码
            Element eReq_postCode = eBody.addElement("postcode");
            eReq_postCode.addText(postCode);
            
            // 联系电话
            Element eReq_linkPhone = eBody.addElement("linkphone");
            eReq_linkPhone.addText(linkPhone);
            
            // 联系人
            Element eReq_linkMan = eBody.addElement("linkman");
            eReq_linkMan.addText(linkMan);
            
            // 邮箱地址
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_addbillmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("开通139邮箱寄送失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	
    /**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map)
    {
    	try
    	{
    		String telnum = (String)map.get("telnum");
    		String email = (String)map.get("email");
    		String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 手机邮箱地址
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_mailbox", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("查询用户是否已开通手机邮箱失败!", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * 开通139免费邮箱
     */
    public ReturnWrap add139Mail(Map map)
    {
    	try
    	{
    		String telnum = (String)map.get("telnum");
    		String email = (String)map.get("email");
    		String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 用户手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 手机邮箱地址
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_add139mail", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("开通139免费邮箱失败!", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * 业务统一查询接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryService(Map map)
    {
        try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String sn = (String)map.get("sn");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 序号
            Element eReq_sn = eBody.addElement("sn");
            eReq_sn.addText(sn);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_spinfo", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            //log.error("业务统一查询接口失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 账单寄送方式提交
     */
    public ReturnWrap billSendCommit(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("operID");// 操作员
            String termid = (String)map.get("termID");//终端号
            String telnum = (String)map.get("telnum");//手机号
            String touchoid = (String)map.get("contactID");//接触号
            String menuid = (String)map.get("menuID");//菜单id
            String mailtype = (String)map.get("mailtype");//账单寄送类型
            String oprtype = (String)map.get("oprtype");// 操作类型
            String mailaddr = (String)map.get("mailAddr");//邮箱地址
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 账单寄送类型
            Element eReq_mailtype = eBody.addElement("mailtype");
            eReq_mailtype.addText(mailtype);
            
            // 操作类型，默认为1：开通
            Element eReq_oprtype = eBody.addElement("oprtype");
            eReq_oprtype.addText(oprtype);
            
            // 如果邮寄类型为Email帐单，则为Email地址；如果邮寄类型是短信帐单或者彩信帐单，则为手机号码
            Element eReq_mailaddr = eBody.addElement("mailaddr");
            eReq_mailaddr.addText(mailaddr);
            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_sendbill", menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            //log.error("账单寄送业务受理失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    public ReturnWrap getBillSendInfo(Map map)
    {
        try
        {          
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //是否查询历史 1为查询 其他为不查询
            Element eReq_qryhis = eBody.addElement("qryhis");
            eReq_qryhis.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billsend_cq", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("获取账单寄送信息失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * 本机品牌资费及已开通功能（产品展示） <功能详细描述>
     * 
     * @param map
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryFavourable(Map map)
    {
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        
        try
        {
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_producttree_cq",
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
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 重庆优惠业务查询
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
        try
        {
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_promotions_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            return rw;
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
        
    }
    
    /**
     * 重庆优惠业务受理
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
            Document docXML = null;
            
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
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_promotions_cq",
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
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 重庆产品变更，查询可以转换的产品
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
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 所需参数
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)map.get("region"));
            
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)map.get("rectype"));
            
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)map.get("mainprodid"));
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_changelist_cq",
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
            //log.error("查询用户可以转换的产品失败", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("查询用户可以转换的产品失败.");
            return rw;
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
            
            Document docXML = null;
            
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
            
            //log.info("调用业务ID[cli_package_chgcheck_hub]验证用户是否具备转换条件开始.");
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcheck_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            //log.info("调用业务ID[cli_package_chgcheck_hub]验证用户是否具备转换条件结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("验证用户是否具备转换条件失败", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg(e.getMessage());
            return rw;
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
            
            Document docXML = null;
            
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
            
            //log.info("调用业务ID[cli_package_gettmplist_hub]查询产品的模板开始.");
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_gettmplist_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            //log.info("调用业务ID[cli_package_gettmplist_hub]查询产品的模板结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("查询产品的模板列表失败", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg(e.getMessage());
            return rw;
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
            
            Document docXML = null;
            
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
            
            //log.info("调用业务ID[cli_package_chgcontent_hub]开始.");
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcontent_cq",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            //log.info("调用业务ID[cli_package_chgcontent_hub]开始.");
            
            // 这里需要调用单独的解析
            return intMsgUtil.invokeProdChg(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("根据产品模板ID查询模板明细失败", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("根据产品模板ID查询模板明细失败.");
            return rw;
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
            
            Document docXML = null;
            
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
            
            //log.info("调用业务ID[cli_package_chgcommit_hub]开始.");
            docXML = intMsgUtil.createMsg(doc, "cli_package_chgcommit_cq", menuid, "", "1", telnumber, operid, atsvNum);
            //log.info("调用业务ID[cli_package_chgcommit_hub]结束.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("产品转换受理失败", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("产品转换受理失败.");
            return rw;
        }
    }
    
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
    
  //add start l00190940 2011-11-03 积分兑换中的积分查询
    /**
     * 积分查询
     * 
     * @param map
     * @return
     */
	public ReturnWrap queryScoreValue(Map map)
	{
		try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_scorevalue", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("积分查询失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("积分查询接口出现异常");
            
            return rw;
        }
	}
	//add end l00190940 2011-11-03 积分兑换中的积分查询

	//add start l00190940 2011-11-04 积分兑换中的积分兑换信息查询
	/**
     * 积分兑换信息查询
     * 
     * @param map
     * @return
     */
	public ReturnWrap queryScoreExchangeInfo(Map map) {
		try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String acttype = (String)map.get("acttype");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 活动类型
            Element eReq_acttype = eBody.addElement("acttype");
            eReq_acttype.addText(acttype);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_scoreexchange_cq", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("积分兑换信息查询失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("积分兑换信息查询接口出现异常");
            
            return rw;
        }
	}
	//add end l00190940 2011-11-04 积分兑换中的积分兑换信息查询

	//add start l00190940 2011-11-04 积分兑换中的积分兑换话费
	/**
     * 积分兑换话费
     * 
     * @param map
     * @return
     */
	public ReturnWrap exchangeBalance(Map map) 
	{
		try
        {          
            String telnumber = (String)map.get("telnumber");
            String activeno = (String)map.get("activeno");
            String nlevel = (String)map.get("nlevel");
            String serviceid = (String)map.get("serviceid");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("curOper");
            String termID = (String)map.get("atsvNum");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 活动编号
            Element eReq_activeno = eBody.addElement("activeno");
            eReq_activeno.addText(activeno);
            
            // 活动级别
            Element eReq_nlevel = eBody.addElement("nlevel");
            eReq_nlevel.addText(nlevel);
            
            // 奖品编码
            Element eReq_serviceid = eBody.addElement("serviceid");
            eReq_serviceid.addText(serviceid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_scoreexchange_cq", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("积分兑换话费失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	//add end l00190940 2011-11-04 积分兑换中的积分兑换话费
}
