package com.customize.sd.selfsvc.call.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.call.SelfSvcCallSD;
import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

public class SelfSvcCallSDImpl implements SelfSvcCallSD
{
    private static Log logger = LogFactory.getLog(SelfSvcCallSDImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    /**
     * 山东套餐信息查询
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
            String menuid = (String)map.get("curMenuId");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_TAOCAN, menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // 能开返回的是crset，需转为Vector以便统一处理
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_TAOCAN) && rw.getReturnObject() instanceof CRSet)
            {
                CRSet crset = (CRSet)rw.getReturnObject();
                Vector vec = new Vector();
                
                // 第一个放点东西占位
                vec.add(rw.getReturnMsg());
                
                // 第二个放返回信息
                vec.add(crset);
                
                rw.setReturnObject(vec);
            }
            
            return rw;
        }
        catch (Exception e)
        {
            logger.error("套餐信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            String verifyCode = (String) map.get("verifyCode");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_detailedbill", menuID, touchOID, "1", telnumber, operID, termID, verifyCode);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("月账单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }       
    }
    
    /**
     * 账户余额查询 <功能详细描述>
     * 
     * @param map
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    
    // modify begin cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
    
    public ReturnWrap queryBalance(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            String isoffset = (String)map.get("isoffset");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 是否模拟销账
            Element eReq_isoffset= eBody.addElement("isoffset");
            eReq_isoffset.addText(isoffset);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_balance", menuid,
                touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        } 
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "余额查询异常");
        }
        
    }
    
    // modify end cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询

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
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            String chargeType = (String)map.get("chargeType");
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            // 缴费方式
            Element eReq_chargeType = eBody.addElement("charge_type");
            eReq_chargeType.addText(chargeType);
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371，统一接口平台不能拼装两个字符串，所以在前台封装
            // 银行行号
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(chargeType + bankNo);
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371，统一接口平台不能拼装两个字符串，所以在前台封装
            
            // 缴费时间
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // 受理类型
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            //modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_FEE, menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("交费前查询账户信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 话费充值
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chargeCommit(Map<String,String> map)
    {
        try
        {
            String operid = map.get("operid");
            String termid = map.get("termid");
            String menuid = map.get("menuid");
            String servnumber = map.get("servnumber");
            String bankNo = map.get("bankNo");
            String payDate = map.get("payDate");
            String terminalSeq = map.get("terminalSeq");
            String money = map.get("money");
            String acceptType = map.get("acceptType");
            String printFlag = map.get("invoiceType");
            String bankSite = map.get("bankSite");
            String bankOper = map.get("bankOper");
            String touchoid = map.get("touchoid");
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            String chargeType = map.get("chargeType");
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            //modify begin sWX219697 2014-7-17 OR_huawei_201406_1125_跨区交费改造，采用受理地市路由
            String region = map.get("region");
            //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_跨区交费改造，采用受理地市路由
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            // 缴费方式
            Element eReq_chargeType = eBody.addElement("charge_type");
            eReq_chargeType.addText(chargeType);
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
            
            // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371，统一接口平台不能拼装两个字符串，所以在前台封装            
            // 银行行号
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(chargeType + bankNo);
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371，统一接口平台不能拼装两个字符串，所以在前台封装
            
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
 
            // modify begin sWX219697 2014-7-17 OR_huawei_201406_1125_跨区交费改造，采用受理地市路由
            String chargeSwitch = (String)PublicCache.getInstance().getCachedData("LOCAL_CHARGE_SWITCH");
            boolean isSwitchOpen = "1".equals(chargeSwitch);

            docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CHARGEFEE, menuid, touchoid, 
            		        isSwitchOpen ? "0" : "1", 
            		        isSwitchOpen ? region : servnumber, 
            				operid, termid);
            //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_跨区交费改造，采用受理地市路由

            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("交费失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            // modify begin  fwx439896 2017-8-7 OR_huawei_201704_411_【山东移动接口迁移专题】-用户信息查询接口拆分
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO))
            {
                Element accessType = eBody.addElement("ACCESSTYPE");
                accessType.addText(Constants.CHANNEL_ID);
               
                //是否模糊化 1模糊化  0不模糊化
                Element isblurry = eBody.addElement("ISBLURRY");
                isblurry.addText("0");
            }
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_USERINFO, "10001001", "", "1", telnumber, operID, termID);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // 调能开,出参转为小写
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                // 出参统一转小写
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"subsname", "prodid", "brandname","substatusname"};
                String[] destRtn = {"subname", "productid", "productname","status"};
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            }
            
            return rw;
            // modify end  fwx439896 2017-8-7 OR_huawei_201704_411_【山东移动接口迁移专题】-用户信息查询接口拆分
            
        }
        catch (Exception e)
        {
            logger.error("获取用户信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    //add begin l00190940 2011-9-27 账单寄送
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
	}
	//add end l00190940 2011-9-27 账单寄送
	
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_MAILBOX, menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("查询用户是否已开通手机邮箱失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * 账单备注查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryBillAddInfo(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnumber");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String beginTime = (String)map.get("beginTime");
            String endTime = (String)map.get("endTime");
            
            // add begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            String verifyCode = (String) map.get("verifyCode");
            // add end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // qryflag,固定传0：号码
            Element eReq_qryflag = eBody.addElement("qryflag");
            eReq_qryflag.addText("0");
            
            // 手机号码
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(telnumber);
            
            // 开始时间
            Element eReq_beginTime = eBody.addElement("begin_time");
            eReq_beginTime.addText(beginTime);
            
            // 结束时间
            Element eReq_endTime = eBody.addElement("end_time");
            eReq_endTime.addText(endTime);
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billaddinfo", menuID,
                touchOID, "1", telnumber, operID, termID, verifyCode);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953            
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询账单备注失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CUSTINFO, menuID, touchOID, "1", telnum, operID, termID);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CUSTINFO))
            {
                // 取返回对象
                Vector vector = (Vector)(rw.getReturnObject());
                
                // 客户信息
                CTagSet ctagset = (CTagSet)(vector.get(0));
                
                // 出参转小写
                ctagset = CommonUtil.lowerTagKey(ctagset);
                
                // 从字典项中获取星级，特殊处理
                ctagset.SetValue("subsCreditName", CommonUtil.getDictNameById("subsCreditId",
                        ctagset.GetValue("creditlevel")));
                
                vector.set(0, ctagset);
            }
            
            return rw;
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
            
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 begin
            // 开始帐期
            Element eReq_startcycle = null;
            
            // 帐期开始时间
            Element eReq_starttime = null;
            
            // 帐期结束时间
            Element eReq_endtime = null;
            
            // 是否合并付费
            Element eReq_isunitpayment = null;
            
            // 若调用能开，则入参key值需要转换
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_BILL2012_SD))
            {
                eReq_startcycle = eBody.addElement("BILLCYCLE");
                eReq_starttime = eBody.addElement("BEGINDATE");
                eReq_endtime = eBody.addElement("ENDDATE");
                eReq_isunitpayment = eBody.addElement("ISUNITEPAYMENT");                
            }
            else
            {
                eReq_startcycle = eBody.addElement("startcycle");
                eReq_starttime = eBody.addElement("starttime");
                eReq_endtime = eBody.addElement("endtime");
                eReq_isunitpayment = eBody.addElement("isunitpayment");
            }
            
            eReq_startcycle.addText(startcycle);
            eReq_starttime.addText(starttime);
            eReq_endtime.addText(endtime);
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_BILL2012_SD, menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 end
        }
        catch (Exception e)
        {
            logger.error("新版月账单查询失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
    /**
     * 校验用户是否实名注册
     * 
     * @param bindBankCardPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkFactNameRegist(Map paramMap)
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
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_bindBankCardUserInfo", menuID,
                touchOID, "1", telnum, operID, termID);
         
            // 记录查询用户信息报文
            logger.info("记录查询用户信息报文："+docXML.asXML());
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询用户信息失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 自动交费操作接口
     * 入参：
	 *	TELNUM 用户号码（必选）
	 *	OPRTYPE 操作类型（必选 0：查询，1：开通自动缴费,2：关闭自动缴费, 3:更新）
	 *	ISNOTIFY 是否发送短信（可选，0：不发送，1：发送，默认发送短息）
	 *	TRIGAMT最低余额值（可选 预付费支付方式新增或修改时必传，后付费可不传）
	 *	DRAWAMT划扣金额（可选 预付费支付方式新增或修改时必传，后付费可不传）
	 *	BANKID  银行编号
     * @param paramMap
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @modify yWX163692 2013年11月19日 OR_SD_201309_940 易充值二阶段，解约新增自动交费判断流程 
     * @remark modify by sWX219697 2014-12-2 15:33:07 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public ReturnWrap autoFeeSet(MsgHeaderPO msgHeader, String oprtype, String trigamt, String drawamt)
    {
    	  try
          {
              // 创建报文体
              Document doc = DocumentHelper.createDocument();
              Element eBody = doc.addElement("message_content");
              
              DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
              
              //操作类型（必选 0：查询，1：开通自动缴费,2：关闭自动缴费, 3:更新）
              DocumentUtil.addSubElementToEle(eBody, "oprtype", oprtype);
              
              // 银行编号
              DocumentUtil.addSubElementToEle(eBody, "bankid", "50");
              
              // 最低余额值（可选 预付费支付方式新增或修改时必传，后付费可不传）
              DocumentUtil.addSubElementToEle(eBody, "trigamt", trigamt);
              
              // 划扣金额（可选 预付费支付方式新增或修改时必传，后付费可不传）
              DocumentUtil.addSubElementToEle(eBody, "drawamt", drawamt);
              
              // 是否发送短信（可选，0：不发送，1：发送，默认发送短息）
              DocumentUtil.addSubElementToEle(eBody, "isnotify", "0".equals(oprtype) ? "0" : "1");
              
              //服务CCEIZLWGAutoChargeSettleType
  			  return intMsgUtil.invoke("cli_AutoChargeSettle", msgHeader, doc);
          }
          catch (Exception e)
          {
              logger.error("查询用户信息失败!", e);
              
              return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
          }
    }
    
    /**
     * 易充值签约之前调用接口检查是否满足产品开通条件
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkProCondition(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String ncode = (String)map.get("nCode");
        String type = (String)map.get("operType");
        String effectType = (String)map.get("effectType");
        String param = (String)map.get("param");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String isSubmit = (String)map.get("isSubmit");
        String executecmd = (String)map.get("executecmd");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // NCODE
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            // 操作类型
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(type);
            
            // 生效方式
            Element eReq_effect = eBody.addElement("effect_type");
            eReq_effect.addText(effectType);
            
            Element eReq_param = eBody.addElement("param");
            eReq_param.addText(param);
            
            // 提交方式 0只检验返回 空或者其他值为提交
            Element eReq_isSubmit = eBody.addElement("isSubmit");
            eReq_isSubmit.addText(isSubmit);
            
            Element eReq_executecmd = eBody.addElement("executecmd");
            eReq_executecmd.addText(executecmd);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_productreccheck", menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("产品开通条件检查失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    /**
     * 查询未打印的发票记录数据
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @Override
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
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_NOINVOICEPRINT_SD))
            {
                // 手机号码
                Element eReq_telnum = eBody.addElement("SEARCHNUMBER");
                eReq_telnum.addText(telnumber);
                
                // 渠道
                Element eReq_accessType = eBody.addElement("ACCESSTYPE");
                eReq_accessType.addText(accessType);
                
                // 开始时间
                Element eReq_startTime = eBody.addElement("STARTDATE");
                eReq_startTime.addText((String)map.get("startTime"));
                
                // 结束时间
                Element eReq_endTime = eBody.addElement("ENDDATE");
                eReq_endTime.addText((String)map.get("endTime"));
            }
            else
            {
             // 手机号码
                Element eReq_telnum = eBody.addElement("telnum");
                eReq_telnum.addText(telnumber);
                
                // 渠道
                Element eReq_accessType = eBody.addElement("accesstype");
                eReq_accessType.addText(accessType);
                
                // 开始时间
                Element eReq_startTime = eBody.addElement("starttime");
                eReq_startTime.addText((String)map.get("startTime"));
                
                // 结束时间
                Element eReq_endTime = eBody.addElement("endtime");
                eReq_endTime.addText((String)map.get("endTime"));
            }
                        
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_NOINVOICEPRINT_SD, menuID,
                touchOID, "1", telnumber, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询要打印的发票记录信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询要打印的发票打印项数据
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnumber");
            String recoid = (String)map.get("recoid");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String billCycle = (String)map.get("billCycle");
            String invType = (String)map.get("invType");
            String acctId = (String)map.get("acctId");

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            String eleinvType = (String)map.get("eleinvType");
            String pushType = (String)map.get("pushType");
            String receiveMode = (String)map.get("receiveMode");
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 业务受理号
            Element eReq_smscont = eBody.addElement("recoid");
            eReq_smscont.addText(recoid);
            
            // 账期
            Element eReq_billCycle = eBody.addElement("billCycle");
            eReq_billCycle.addText(billCycle);
            
            // 打印类型  0 收据  1 发票
            Element eReq_invType = eBody.addElement("invType");
            eReq_invType.addText(invType);
            
            // 账号
            Element eReq_acctId = eBody.addElement("acctId");
            eReq_acctId.addText(acctId);
            
            // 受理渠道
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            // 是否开具电子发票 1是 0否
            Element eReq_eleinvType = eBody.addElement("eleinvType");
            eReq_eleinvType.addText(eleinvType);
            // 推送方式 1邮箱
            Element eReq_pushType = eBody.addElement("pushType");
            eReq_pushType.addText(pushType);
            // 推送信息 电子邮件地址
            Element eReq_receiveMode = eBody.addElement("receiveMode");
            eReq_receiveMode.addText(receiveMode);
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_INVOICEINFO_SD, menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询要打印的发票打印项数据失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    // add end zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    
    /**
     * 话费充值账户应缴费用查询
     * @param paramMap
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
     * @return 
     */
    public ReturnWrap  qryRetcharge(Map paramMap)
    {
    	// 当前菜单ID
        String menuID = (String)paramMap.get("curMenuid");
        
        // 客户接触id
        String touchOID = (String)paramMap.get("touchoid");
        
        // 操作员ID
        String operID = (String)paramMap.get("operid");
        
        // 终端机ID
        String termID = (String)paramMap.get("termid");
        
        // 设置客户手机号
        String servnumber = (String)paramMap.get("servnumber");
        
        // 帐号
        String acctid = (String)paramMap.get("acctid");
        
        // 查询类型    默认1，1：个人，2：家庭，3集团
        String qrytype = (String)paramMap.get("qrytype");
        
        // 查询帐期
        String cycle = (String)paramMap.get("cycle");
        
        // 帐单状态 默认1；0：指定账期查询，1：没有结帐的月份的所有帐单（缴费时用（为1时，cycle不起作用）
        String status = (String)paramMap.get("status");
        
        // 账单类型 默认1；“1”正常，“2”冷号，“3”核销（坏账）
        String isbaddebt = (String)paramMap.get("isbaddebt");
        
        // 受理渠道
        String accesstype = (String)paramMap.get("accesstype");
        
        // 保留字段，默认为 0
        String unbilled = (String)paramMap.get("unbilled");
        
        // 业务代码 -1,减免滞纳金 0,缴费 1,退预交款 2,退网结帐 4,赠送预交款 5,坏帐回收 6,后打发票缴费 7,呆账回收
        String processcode = (String)paramMap.get("processcode");
        
        // 用户号,可为空，除江苏外的其他省市使用）
        String subsid = (String)paramMap.get("subsid");
        
        // 是否需要返回用户信息""
        String isneedsubsinfo = (String)paramMap.get("isneedsubsinfo");
        
        // 是否要返回的是最新余额
        String isneedleftmoney = (String)paramMap.get("isneedleftmoney");
        
    	try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 客户手机号
            Element eReq_telnum = eBody.addElement("servnumber");
            eReq_telnum.addText(servnumber);
            
            // 帐号
            Element eReq_acctid = eBody.addElement("acctid");
            eReq_acctid.addText(acctid);
            
            // 查询类型    默认1，1：个人，2：家庭，3集团
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(qrytype);
            
            // 查询帐期
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            
            // 帐单状态 
            Element eReq_status = eBody.addElement("status");
            eReq_status.addText(status);
            
            // 账单类型 
            Element eReq_isbaddebt = eBody.addElement("isbaddebt");
            eReq_isbaddebt.addText(isbaddebt);
            
            // 受理渠道
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // 保留字段，默认为 0
            Element eReq_unbilled = eBody.addElement("unbilled");
            eReq_unbilled.addText(unbilled);
            
            // 业务代码 
            Element eReq_processcode = eBody.addElement("processcode");
            eReq_processcode.addText(processcode);
            
            // 用户号
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(subsid);
            
            // 是否需要返回用户信息""
            Element eReq_isneedsubsinfo = eBody.addElement("isneedsubsinfo");
            eReq_isneedsubsinfo.addText(isneedsubsinfo);
            
            // 是否要返回的是最新余额
            Element eReq_isneedleftmoney = eBody.addElement("isneedleftmoney");
            eReq_isneedleftmoney.addText(isneedleftmoney);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_RETCHARGE, menuID, touchOID, "1", servnumber, operID,termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("应缴费用查询失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 账单邮件下发接口
     * @param map 
     * @remark  create sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * @return ReturnWrap
     */
    public ReturnWrap sendBillMail(Map<String,String> map)
    {
    	//当前菜单id
    	String menuID = map.get("menuID");
    	
    	//客户接触id
    	String touchOID = map.get("touchOID");
    	
    	//操作员id
    	String operID = map.get("operID");
    	
    	//终端机id
    	String termID = map.get("termID");
    	
    	//客户手机号码
    	String telnum = map.get("telnum");
    	
    	//查询账期
    	String cycle = map.get("cycle");
    	
    	//是否合并
    	String isunitepayment = map.get("isunitepayment");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//客户手机号码
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//查询账期
			Element eReq_cycle = eBody.addElement("cycle");
			eReq_cycle.addText(cycle);
			
			//偏移量，默认为空
			Element eReq_cycleoffset = eBody.addElement("cycleoffset");
			eReq_cycleoffset.addText("");
			
			//是否合并付费 1 合并，0 不合并
			Element eReq_isunitepayment = eBody.addElement("isunitepayment");
			eReq_isunitepayment.addText(isunitepayment);
			
			//从缓存中取出接口业务id
			String buisID = (String) PublicCache.getInstance().getCachedData(Constants.SEND_BILL_BUSI_ID);
			Document docXML = intMsgUtil.createMsg(doc, buisID, menuID, touchOID, "1", telnum, operID, termID);
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		
            logger.error("账单邮件下发失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * 月结发票的账期接口查询
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return
     */
    public ReturnWrap qryBillCycle(Map map)
    {
    	// 手机号码
    	String telnum = (String)map.get("servnum");
    	
    	// 账期
    	String billCycle = (String)map.get("cycle");
    	
    	String menuid = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //modify begin by cwx456134 2017-05-02 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_BILLCYCLECUSTINFO))
            {
                // 手机号码
                Element eReq_telNum = eBody.addElement("TELNUM");
                eReq_telNum.addText(telnum);
            }
            else
            {
                // 手机号码
                Element eReq_telNum = eBody.addElement("servnum");
                eReq_telNum.addText(telnum);
            }
            
            // 账期
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(billCycle);
            //modify end by cwx456134 2017-05-02 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
    	
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_BILLCYCLECUSTINFO, menuid, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch(Exception e)
        {
        	logger.error("账期查询失败!", e);
        	
        	return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 月结发票查询数据接口
     * @param paramMap
     * @remark add by wWX217192 on 20140508 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return 月结发票数据
     */
    public ReturnWrap qryMonthInvoice(Map map)
    {
    	// 手机号码
    	String servNum = (String)map.get("servnum");
    	
    	// 账期
    	String cycle = (String)map.get("billcycle");
    	
    	// 账期开始时间
    	String startDate = (String)map.get("startdate");
    	
    	// 账期结束时间
    	String endDate = (String)map.get("enddate");
    	
    	// 主账号
    	String acctid = (String)map.get("acctid");
    	
    	String menuId = (String)map.get("menuID");
    	String touchOID = (String)map.get("touchOID");
    	String operId = (String)map.get("operID");
    	String termID = (String)map.get("termID");
    	
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        String eleinvType = (String)map.get("eleinvType");
        String pushType = (String)map.get("pushType");
        String receiveMode = (String)map.get("receiveMode");
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
    	
    	try
    	{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telNum = eBody.addElement("servnum");
            eReq_telNum.addText(servNum);
            
            // 账号
            Element eReq_Acctid = eBody.addElement("acctId");
            eReq_Acctid.addText(acctid);
            
            // 账期
            Element eReq_BillCycle = eBody.addElement("billCycle");
            eReq_BillCycle.addText(cycle);
            
            // 账期开始时间
            Element eReq_StartDate = eBody.addElement("startDate");
            eReq_StartDate.addText(startDate);
            
            // 账期结束时间
            Element eReq_EndDate = eBody.addElement("endDate");
            eReq_EndDate.addText(endDate);

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            // 是否开具电子发票 1是 0否
            Element eReq_eleinvType = eBody.addElement("eleinvType");
            eReq_eleinvType.addText(eleinvType);
            // 推送方式 1邮箱
            Element eReq_pushType = eBody.addElement("pushType");
            eReq_pushType.addText(pushType);
            // 推送方式 1邮箱
            Element eReq_receiveMode = eBody.addElement("receiveMode");
            eReq_receiveMode.addText(receiveMode);
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_MONTHINVOICEINFO, menuId, touchOID, "1", servNum, operId, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch(Exception e)
    	{
    		logger.error("月结发票数据查询失败!", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    	}
    }
	
    /**
     * 查询可变更的主体产品信息
     * @param paramMap
     * @remark add by jWX216858 2014-5-7 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
     * @return ReturnWrap
     */
	public ReturnWrap qryMainProdInfo(Map<String, String> paramMap)
	{
		// 当前菜单id
		String menuId = paramMap.get("menuId");
		
		// 终端机id
		String termId = paramMap.get("termId");
		
		// 操作员id
		String operId = paramMap.get("operId");
		
		// 客户接触id
		String touchId = paramMap.get("touchId");
		
		// 区域编码
		String region = paramMap.get("region");
		
		// 产品编码
		String prodId = paramMap.get("prodId");
		
		// 渠道类型
		String accessType = paramMap.get("accessType");
		
		try
		{
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 区域编码
			Element eReq_region = ebody.addElement("REGION");
			eReq_region.addText(region);
			
			//modify begin by cwx456134 2017-05-12 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CONVERTPRODINFO))
			{
			    // 产品编码
	            Element eReq_prodId = ebody.addElement("MAINPRODID");
	            eReq_prodId.addText(prodId);
	            
	            // 是否精确匹配channel
                Element eReq_channel = ebody.addElement("ISALLCHANNEL");
                eReq_channel.addText("0");
	            
			}
			else
			{
			    // 产品编码
	            Element eReq_prodId = ebody.addElement("PRODID");
	            eReq_prodId.addText(prodId);
			}
            //modify end by cwx456134 2017-05-12 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
			
			// 渠道类型
			Element eReq_accessType = ebody.addElement("ACCESSTYPE");
			eReq_accessType.addText(accessType);
			
			Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CONVERTPRODINFO, menuId, touchId, "0", region, operId, termId);
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("查询可转换主体产品信息失败", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 查询主体产品模板信息
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
     */
	public ReturnWrap qryProdTemplateInfo(Map<String, String> paramMap)
	{
		// 当前菜单id
		String menuId = paramMap.get("menuId");
		
		// 终端机id
		String termId = paramMap.get("termId");
		
		// 操作员id
		String operId = paramMap.get("operId");
		
		// 客户接触id
		String touchId = paramMap.get("touchId");
		
		// 区域编码
		String region = paramMap.get("region");
		
		// 产品编码
		String prodId = paramMap.get("prodId");
		
		// 受理渠道
		String channel = paramMap.get("channel");
		
		// 受理类型
		String recType = paramMap.get("recType");
		
		// 查询时是否精确匹配
		String ruleType = paramMap.get("ruleType");
		
		try
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");

			// 受理类型
			Element eReq_recType = ebody.addElement("RECTYPE");
			eReq_recType.addText(recType);
			
			//modify begin lWX431760 2017-06-15 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
			// 受理渠道
			Element eReq_channel = null;
			// 产品编码
			Element eReq_operId = null;			
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PRODTEMPLATEINFO))
			{
			    eReq_channel= ebody.addElement("ACCESSTYPE");
			    eReq_channel.addText(channel);
			    eReq_operId = ebody.addElement("PRODCODE");
			    eReq_operId.addText(prodId);
			    //区域编码
	            Element eReq_region = ebody.addElement("REGION");
	            eReq_region.addText(region);
			}
			else
			{
			    eReq_channel = ebody.addElement("CHANNEL");
			    eReq_channel.addText(channel);
			    eReq_operId = ebody.addElement("MAINPRODID");
			    eReq_operId.addText(prodId);
			}			
									 					
			// 查询时是否精确匹配 
			Element eReq_ruleType = ebody.addElement("RULETYPE");
			eReq_ruleType.addText(ruleType);
			
			Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_PRODTEMPLATEINFO, menuId, touchId, "0", region, operId, termId);
			//modify end lWX431760 2017-06-15 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("查询主体产品模板失败", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
	 * 执行主体产品变更
	 * @param paramMap
     * @return ReturnWrap
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
	 */
	public ReturnWrap mainProdChangeRec(Map<String, String> paramMap) 
    {
		// 当前菜单id
		String menuId = paramMap.get("menuId");
		
		// 终端机id
		String termId = paramMap.get("termId");
		
		// 操作员id
		String operId = paramMap.get("operId");
		
		// 客户接触id
		String touchId = paramMap.get("touchId");
		
		// 手机号码
		String telnum = paramMap.get("telnum");
		
		// 是否使用NOCDE 1：不使用 0:使用
		String notexencode = paramMap.get("NOTEXENCODE");
		
		// 主体产品编码
		String mainProdId = paramMap.get("MAINPRODID");
		
		// 主体产品变更预处理 PreBsacNBChgMainProd：预处理,传空为执行主体产品变更
		String preparebusi = paramMap.get("PREPAREBUSI");
		
		try 
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 手机号
			Element eReq_telnum = ebody.addElement("TELNUM");
			eReq_telnum.addText(telnum);
			
			// 是否使用ncode
			Element eReq_ncode = ebody.addElement("NOTEXENCODE");
			eReq_ncode.addText(notexencode);
			
			// 主体产品编码
			Element eReq_mainProdid = ebody.addElement("MAINPRODID");
			eReq_mainProdid.addText(mainProdId);
		
			// 主体产品变更预处理 PreBsacNBChgMainProd：预处理,传空为执行主体产品变更
			Element eReq_prebusi = ebody.addElement("PREPAREBUSI");
			eReq_prebusi.addText(preparebusi);
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODCHANGEINFO))
			{
			    //应收费用
	            DocumentUtil.addSubElementToEle(ebody, "CONTRASTFEE", "");
			    Element eReq_recProdlist = ebody.addElement("RECPRODLIST");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRODID",mainProdId);
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "EFFECTTYPE", paramMap.get("affecttype"));
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "OPTYPE", "PCOpRec");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "OBJTYPE", "PCIntObjMain");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "TEMPLATEID", paramMap.get("templateId"));
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRIVID", ""); 
			}
			else
			{
			    Element eReq_prodid = ebody.addElement("prodid");
	            eReq_prodid.addText(paramMap.get("MAINPRODID"));
			    // 生效方式
	            Element eReq_affecttype = ebody.addElement("affecttype");
	            eReq_affecttype.addText(paramMap.get("affecttype"));
	            
	            // 操作类型
	            Element eReq_opertype = ebody.addElement("operType");
	            eReq_opertype.addText(paramMap.get("opertype"));
	            
	            // 对象类型
	            Element eReq_objtype = ebody.addElement("objtype");
	            eReq_objtype.addText(paramMap.get("objtype"));
	            
	            // 优惠名称
	            Element eReq_privname = ebody.addElement("privname");
	            eReq_privname.addText(paramMap.get("privname"));
	            
	            // 模板编码
	            Element eReq_tempid = ebody.addElement("templateId");
	            eReq_tempid.addText(paramMap.get("templateId"));
			}
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
			// MO套餐提醒键值，预处理时传固定值message, 执行主体产品变更时传空
			Element eReq_MOPrivTips = ebody.addElement("MOPrivTips");
			eReq_MOPrivTips.addText(paramMap.get("MOPrivTips"));
			
			// 渠道编码，固定:bsacAtsv
			Element eReq_AccessType = ebody.addElement("ACCESSTYPE");
			eReq_AccessType.addText("bsacAtsv");
			
			Element eReq_issubmit = ebody.addElement("ISSUBMIT");
			eReq_issubmit.addText(paramMap.get("ISSUBMIT"));
			// add end jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
			
			String openVersion = CommonUtil.getParamValue("change_confir_aopenversion");		
			
			Document docXML = null;
			
			if("".equals(preparebusi))
			{
				// 主体产品变更
				docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_PRODCHANGEINFO, menuId, touchId, "1", telnum, operId, termId);
			}
			else
			{
				// 产品变更确认
				docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_PRODCHANGECONFIR, menuId, touchId, "1", telnum, operId, termId);
			}
			
			ReturnWrap rw = intMsgUtil.invoke(docXML);
			
			// 产品变更确认,调用能开接口,tagset出参转小写
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODCHANGECONFIR) && StringUtils.isNotBlank(preparebusi))
			{
			    Vector vec = (Vector)rw.getReturnObject();
	            
	            // 出参统一转小写
	            CTagSet tagSet = CommonUtil.lowerTagKey((CTagSet)vec.get(0));
	            
	            vec.set(0, tagSet);
			}
			
			return rw;
		}
		catch (Exception e)
		{
			logger.error("执行主体产品变更失败", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
     * <查询代理商账户信息>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-6-6 09:17:25 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap qryAgentInfo(Map<String,String> map)
    {
    	//操作员id
    	String operid = map.get("operid");
    	
    	//终端机id
    	String atsvNum = map.get("atsvNum");
    	
    	//代理商手机号码
    	String telnum = map.get("telnum");
    	
    	//接触id
    	String touchoid = map.get("touchoid");
    	
    	//当前菜单id
    	String curMenuId = map.get("curMenuid");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//代理商手机号码
			eBody.addElement("telnum").addText(telnum);
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_agentinfo", curMenuId, touchoid, "1", telnum, 
					operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("代理商账户信息查询失败：", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * 代理商缴费前记录
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap beforeAgentCharge(Map<String,String> map)
    {
    	//操作员id
    	String operid = map.get("operid");
    	
    	//终端机id
    	String atsvNum = map.get("atsvNum");
    	
    	//接触id
    	String touchoid = map.get("touchoid");
    	
    	//当前菜单id
    	String curMenuId = map.get("curMenuid");
    	
    	//代理商组织机构编码
    	String orgId = map.get("orgId");
    	
    	//代理商账户
    	String agentAccount = map.get("fundacctid");
    	
    	//充值金额
    	String tMoney = map.get("amount");
    	
    	//受理类型编码
    	String accept_type = map.get("accept_type");
    	
    	//银行号
    	String bank_no = map.get("bank_no");
    	
    	//客户手机号码
    	String msisdn = map.get("msisdn");
    	
    	//科目编码
    	String subjectid = map.get("subjectid");
    	
    	//交费日期
    	String pay_date = map.get("pay_date");
    	
    	//受理渠道
    	String channel = map.get("channel");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//代理商组织机构id
			eBody.addElement("orgid").addText(orgId);
			
			//代理商账户编码
			eBody.addElement("fundacctid").addText(agentAccount);
			
			//充值金额
			eBody.addElement("amount").addText(tMoney);
			
			//受理类型编码
			eBody.addElement("accept_type").addText(accept_type);
			
			//银行号
			eBody.addElement("bank_no").addText(bank_no);
			
			//代理商手机号码
			eBody.addElement("msisdn").addText(msisdn);
			
			//科目编码
			eBody.addElement("subjectid").addText(subjectid);
			
			//交费日期
			eBody.addElement("pay_date").addText(pay_date);
			
			//操作员工号
			eBody.addElement("operid").addText(operid);
			
			//受理渠道
			eBody.addElement("channel").addText(channel);
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_beforeagentcharge", curMenuId, touchoid, 
					"1", msisdn, operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (RuntimeException e) 
    	{
    		logger.error("代理商交费前记录调用失败：", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * 代理商充值
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap agentCharge(Map<String,String> map)
    {
    	
    	//操作员id
    	String operid = map.get("operid");
    	
    	//终端机id
    	String atsvNum = map.get("atsvNum");
    	
    	//接触id
    	String touchoid = map.get("touchoid");
    	
    	//代理商手机号码
    	String telnum = map.get("telnum");
    	
    	//当前菜单id
    	String curMenuId = map.get("curMenuid");
    	
    	//充值金额，单位：元
    	String amount = map.get("amount");
    	
    	//代理商缴费前流水号
    	String bank_nbr = map.get("bank_nbr");
    	
    	//银行号
    	String bank_no = map.get("bank_no");
    	
    	//交费日期
    	String pay_date = map.get("pay_date");
    	
    	//受理渠道编码
    	String channel = map.get("channel");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//充值金额
			eBody.addElement("amount").addText(amount);
			
			//缴费前流水号
			eBody.addElement("bank_nbr").addText(bank_nbr);
			
			//银行号
			eBody.addElement("bank_no").addText(bank_no);
			
			//交费日期
			eBody.addElement("pay_date").addText(pay_date);
			
			//受理渠道
			eBody.addElement("channel").addText(channel);
			
			Document docXML =  intMsgUtil.createMsg(doc, "cli_busi_agentcharge", curMenuId, touchoid, "1", telnum, 
					operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
            logger.error("代理商交费失败：", e);
        	
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
    }

	/**
	 * 查询用户实名制登记标志
	 * @param map
	 * @return 接口调用成功与否的标志位及接口返回信息
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
	public ReturnWrap qryRealNameType(Map<String, String> map) 
	{
		try
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 手机号码
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_custrealnametype_sd", (String) map.get("menuID"), 
					(String) map.get("touchOID"), "1", (String) map.get("telnum"), (String) map.get("operID"), (String) map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
			
		}
		catch(Exception e)
		{
			logger.error("查询用户实名制登记标志失败!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
    
    /**
     * 验证SIM卡
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-23 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkSIMCardNo(Map<String,String> paramMap)
    {
        // 当前菜单id
        String menuId = paramMap.get("menuId");
        
        // 终端机id
        String termId = paramMap.get("termId");
        
        // 操作员id
        String operId = paramMap.get("operId");
        
        // 客户接触id
        String touchId = paramMap.get("touchId");
        
        // 手机号码
        String telnum = paramMap.get("telnum");
        
        // SIM卡卡号
        String cardno = paramMap.get("cardno");
        
        try 
        {
            // 组装报文
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // SIM卡卡号
            DocumentUtil.addSubElementToEle(ebody, "cardno", cardno);
            
            // SIM卡验证
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chksimcardno", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("SIM卡验证失败", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "SIM卡验证异常");
        }
    }
    
    /**
     * 充值记录认证
     * @param paramMap
     * @return 
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkChargeRecord(Map<String, Object> paramMap)
    {
        // 当前菜单id
        String menuId = (String)paramMap.get("menuId");
        
        // 终端机id
        String termId = (String)paramMap.get("termId");
        
        // 操作员id
        String operId = (String)paramMap.get("operId");
        
        // 客户接触id
        String touchId = (String)paramMap.get("touchId");
        
        // 手机号码
        String telnum = (String)paramMap.get("telnum");
        
        // 缴费记录 
        ChargeRecordPO chargeRecordPO = (ChargeRecordPO)paramMap.get("chargeRecordPO");
        
        try 
        {
            // 组装报文
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // 充值记录
            Element chargerecord1 = ebody.addElement("chargerecord");
            Element chargerecord2 = ebody.addElement("chargerecord");
            Element chargerecord3 = ebody.addElement("chargerecord");
            
            // 缴费日期
            DocumentUtil.addSubElementToEle(chargerecord1, "chargedate", chargeRecordPO.getCurrMonChargeDate());
            DocumentUtil.addSubElementToEle(chargerecord2, "chargedate", chargeRecordPO.getLastMonChargeDate());
            DocumentUtil.addSubElementToEle(chargerecord3, "chargedate", chargeRecordPO.getPreLastMonChargeDate());
            
            // 缴费金额
            DocumentUtil.addSubElementToEle(chargerecord1, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getCurrMonChargeAmount()));
            DocumentUtil.addSubElementToEle(chargerecord2, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getLastMonChargeAmount()));
            DocumentUtil.addSubElementToEle(chargerecord3, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getPreLastMonChargeAmount()));
            
            // 记录实名制认证受理日志
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkchargerecord", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("充值记录验证异常：",  e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "充值记录验证异常，请稍后重试！");
        }
    }
    
    /**
     * 通话记录验证
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkCallRecord(Map<String, String> paramMap)
    {
        // 当前菜单id
        String menuId = paramMap.get("menuId");
        
        // 终端机id
        String termId = paramMap.get("termId");
        
        // 操作员id
        String operId = paramMap.get("operId");
        
        // 客户接触id
        String touchId = paramMap.get("touchId");
        
        // 手机号码
        String telnum = paramMap.get("telnum");
        
        // 通话记录 
        String[] calledNums = paramMap.get("calledNum").split(",");
        
        try 
        {
            // 组装报文
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            for(int i = 0; i < calledNums.length;i++)
            {
                Element calledNum = ebody.addElement("callednum");
                DocumentUtil.addSubElementToEle(calledNum, "calledtelnum", calledNums[i]);
            }
            
            // 记录实名制认证受理日志
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkcallrecord", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("通话记录验证异常：",  e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "通话记录验证异常，请稍后重试！");
        }
    }
    
    /**
     * 记录实名制认证受理日志
     * @param paramMap
     * @param map
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-24 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
     */
    public ReturnWrap saveRealNameChkRecLog(Map<String,String> paramMap,Map<String,String> map)
    {
        // 当前菜单id
        String menuId = paramMap.get("menuId");
        
        // 终端机id
        String termId = paramMap.get("termId");
        
        // 操作员id
        String operId = paramMap.get("operId");
        
        // 客户接触id
        String touchId = paramMap.get("touchId");
        
        // 手机号码
        String telnum = paramMap.get("telnum");
        
//        // 校验方式 编码 
//        String attrid = paramMap.get("attrid");
//        
//        // 校验内容
//        String newattrvalue = paramMap.get("newattrvalue");
        
        try 
        {
            // 组装报文
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // 验证明细
            Element recdetail = ebody.addElement("recdetail");
            
            for(Entry<String, String> obj : map.entrySet())
            {
	            // 校验方式 编码
	            DocumentUtil.addSubElementToEle(recdetail, "attrid", obj.getKey());
	            
	            // 校验内容
	            DocumentUtil.addSubElementToEle(recdetail, "newattrvalue", obj.getValue());
            }
            
            // 记录实名制认证受理日志
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_saverealnamechkreclog", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("记录实名制认证受理日志异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "记录实名制认证受理日志异常");
        }
    }
    
    /**
	 * 生成短信验证随机码
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
	 */
	public ReturnWrap getRandomPwd(Map<String, String> map)
	{
		try
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 手机号码
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// 业务类型
			ebody.addElement("dorectype").addText(map.get("dorectype"));
			
			// 默认为4则是随机短信密码
			ebody.addElement("subcmdid").addText(map.get("subcmdid"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_randompwd", (String) map.get("menuID"), 
					(String) map.get("touchOID"), "1", (String) map.get("telnum"), (String) map.get("operID"), (String) map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("生成短信验证码失败!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 短信随机密码验证
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkRandomPwd(Map<String, String> map)
	{
		try
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 手机号码
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// 随机密码
			ebody.addElement("randompasswd").addText(map.get("randompwd"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkrandompwd", map.get("menuID"), 
					map.get("touchOID"), "1", map.get("telnum"), map.get("operID"), map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("短信随机密码验证失败!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 个人密码验证接口
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkUserPwd(Map<String, String> map)
	{
		try
		{
			// 组装报文
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// 手机号码
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// 个人密码
			ebody.addElement("passwd").addText(map.get("passwd"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkserverpwd", map.get("menuID"), 
					map.get("touchOID"), "1", map.get("telnum"), map.get("operID"), map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("短信随机密码验证失败!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
    /**
     * 代理商充值金额校验
     * @param msgHeader 消息头
     * @param orgId 组织机构编码
     * @param agentAccount 资金账户编码
     * @param subjectId 科目编码
     * @param tMoney 充值金额 分
     * @return
     * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_自助终端代理商资金账户充值功能优化
     */
	public ReturnWrap checkBeforeAgentCharge(MsgHeaderPO msgHeader, String orgId, 
			String agentAccount, String subjectId, String tMoney)
	{
        try 
        {
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 渠道编码
			DocumentUtil.addSubElementToEle(eBody, "channelid", orgId);
			
			//代理商账户
			DocumentUtil.addSubElementToEle(eBody, "acctid", agentAccount);
			
			//科目编码
			DocumentUtil.addSubElementToEle(eBody, "subjectid", subjectId);
			
			//充值金额 分
			DocumentUtil.addSubElementToEle(eBody, "adjustamount", tMoney);
			
			// 调用后台接口
			return intMsgUtil.invoke("cli_qry_CheckBeforeAgentCharge", msgHeader, msgBody);
		} 
        catch (Exception e) 
        {
            logger.error("校验代理商充值金额失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
        
	}

	/**
	 * <查询用户的付费类型>
	 * <功能详细描述>
	 * @param msgHeader 消息头部
	 * @return
	 * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-7 14:28:55 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap qrySubsPrepayType(MsgHeaderPO msgHeader) 
	{
        try 
        {
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 手机号码
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			// 调用后台接口
			return intMsgUtil.invoke("cli_qry_SubsPrepayType", msgHeader, msgBody);
		} 
        catch (Exception e) 
        {
            logger.error("查询用户付费类型失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询用户付费类型失败");
		}
	}

	/**
	 * 语音通话受理
	 * @param msgHeader 报文头信息
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	@Override
	public ReturnWrap voiceCallRec(MsgHeaderPO msgHeader, String nCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// 手机号码
			DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
			
			// nCode
			DocumentUtil.addSubElementToEle(ebody, "ncode", nCode);
			
			// 操作类型 固定传ADD
			DocumentUtil.addSubElementToEle(ebody, "stype", "ADD");
			
			// 预受理 固定传BsacNBSubmit
			DocumentUtil.addSubElementToEle(ebody, "preparebusi", "BsacNBSubmit");
			
			// 将互斥或依赖的进行关联删除 固定传NEEDPREMUTEX
			DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", "NEEDPREMUTEX");
			
			// 调用接口
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD, msgHeader, msgBody); 
		}
		catch (Exception e)
		{
			logger.error("语音通话受理失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
	 * 上网流量受理
	 * @param header 消息头
	 * @param productset 增值产品串(产品包,增值产品,优惠;产品包,增值产品,优惠;)
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	@Override
	public ReturnWrap gprsWlanRec(MsgHeaderPO header, String productset) 
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// 受理类型
			DocumentUtil.addSubElementToEle(ebody, "rectype", "ChangeProduct");
			
			// 手机号码
			DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
			
			// 增值产品串 (产品包,增值产品,优惠;产品包,增值产品,优惠;)
			DocumentUtil.addSubElementToEle(ebody, "productset", productset);
			
			// 受理日期
			DocumentUtil.addSubElementToEle(ebody, "recdate", "");
			
			// 将互斥或依赖的进行关联删除 固定传NEEDPREMUTEX
			DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", "NEEDPREMUTEX");
			
			return intMsgUtil.invoke("cli_busi_GprsWlanRecSD", header, msgBody);
			
		}
		catch (Exception e)
		{
			logger.error("上网流量受理失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * <查询易充值用户副号码列表>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param ncode
	 * @param stype
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-2 19:36:41 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberQry(MsgHeaderPO msgHeader, String ncode, String stype)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			//手机号码
			DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
			
			//ncode
			DocumentUtil.addSubElementToEle(ebody, "ncode", ncode);
			
			//操作类型 查询：QRY
			DocumentUtil.addSubElementToEle(ebody, "stype", stype);
			
			//调用流水线查询
			return intMsgUtil.invoke("cli_qry_vicenum", msgHeader, msgBody);
		} 
		catch (Exception e) 
		{
			logger.error("查询易充值用户副号码列表失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询易充值用户副号码列表失败");
		}
	}

	/**
	 * <新增、删除易充值副号码>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param viceArray 副号码数组
	 * @param opertype 操作类型，1：新增，2：删除
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-4 12:00:03 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberSet(MsgHeaderPO msgHeader, String[] viceArray, String opertype)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element eBody = msgBody.addElement("message_content");
			
			//手机号码
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			//操作类型，1：新增，2：删除
			DocumentUtil.addSubElementToEle(eBody, "opertype", opertype);
			
			//CRSet 副号码列表
			for(String viceNum : viceArray)
			{
				Element crsetNode = eBody.addElement("slavenumlist");
				DocumentUtil.addSubElementToEle(crsetNode, "slavenum", viceNum);
			}
			
			//opcode:CCEIAddEasyVoucherSlaveNum
			return intMsgUtil.invoke("cli_busi_EasyVoucherSlaveNum", msgHeader, msgBody);
		} 
		catch (Exception e) 
		{
			logger.error("新增、删除易充值副号码失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "新增、删除易充值副号码接口操作失败");
		}
	}
	
	/**
	 * 查询当前用户是否签约和包易充值
	 * @param headerPo 消息头
	 * @return 用户签约信息
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap checkHeBao(MsgHeaderPO headerPo, String bankCardNum)
	{
		/*try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// 待查询的手机号码
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// 银行卡号信息
			DocumentUtil.addSubElementToEle(ebody, "cardNo", bankCardNum);
			
			//调用查询和包易充值签约关系
			return intMsgUtil.invoke("cli_busi_SDHBTelQryRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("查询当前用户签约信息失败!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}*/
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_SDHBTelQryRegist</process_code><verify_code></verify_code>" +
            		"<resp_time>20150407095636</resp_time>" +
            		"<sequence><req_seq>231</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><AGRNO></AGRNO><BANKABBR></BANKABBR><cardType></cardType>" +
            		"<cardNo>6225885415787779</cardNo><status>2</status>" +
            		"<signDate></signDate><signTime></signTime></message_content>]]>" +
            		"</message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
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
	 * 和包易充值平台发送短信随机密码
	 * @param headerPo 请求报文头
	 * @param smsType 验证码类型
	 * @param AGRNO 协议号
	 * @return 和包易充值平台的返回报文
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap sendHeBaoRandom(MsgHeaderPO headerPo, String smsType, BindBankCardPO cardPo)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// 待操作的手机号码
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// 验证码类型
			DocumentUtil.addSubElementToEle(ebody, "smsType", smsType);
			
			// 证件类型 00-身份证
			DocumentUtil.addSubElementToEle(ebody, "IdType", cardPo.getIdCardType());
			
			// 证件号码
			DocumentUtil.addSubElementToEle(ebody, "IdNo", cardPo.getIdCardNum());
			
			// 协议号
			DocumentUtil.addSubElementToEle(ebody, "AGRNO", cardPo.getAGRNO());
			
			// 订单金额 以分为单位
			DocumentUtil.addSubElementToEle(ebody, "amount", cardPo.getAmount());
			
			// 银行代码
			DocumentUtil.addSubElementToEle(ebody, "BANKABBR", cardPo.getBankAbbr());
			
			// 银行卡类型 0-借记卡 1-信用卡
			DocumentUtil.addSubElementToEle(ebody, "cardType", cardPo.getBankCardType());
			
			// 银行卡卡号 需要加密
			DocumentUtil.addSubElementToEle(ebody, "cardNo", cardPo.getBankCardNum());
			
			// 银行卡户名 需要加密 GBK编码
			DocumentUtil.addSubElementToEle(ebody, "cardName", cardPo.getUserFactName());
			
			// 信用卡CVV2 需要加密
			DocumentUtil.addSubElementToEle(ebody, "cardCvv2", cardPo.getCvn2());
			
			// 信用卡有效期 需要加密
			DocumentUtil.addSubElementToEle(ebody, "cardExpDate", cardPo.getExpire());
			
			return intMsgUtil.invoke("cli_busi_SDHBTelGetSmsChkCode", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("和包易充值平台发送短信随机码失败!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 和包易充值签约
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap signHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// 待操作的手机号码
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// 银行代码
			DocumentUtil.addSubElementToEle(ebody, "BANKABBR", cardPo.getBankAbbr());
			
			// 银行卡类型
			DocumentUtil.addSubElementToEle(ebody, "cardType", cardPo.getBankCardType());
			
			// 银行卡卡号
			DocumentUtil.addSubElementToEle(ebody, "cardNo", cardPo.getBankCardNum());
			
			// 银行卡用户名
			DocumentUtil.addSubElementToEle(ebody, "cardName", cardPo.getUserFactName());
			
			// 证件类型
			DocumentUtil.addSubElementToEle(ebody, "IdType", cardPo.getIdCardType());
			
			// 证件号码
			DocumentUtil.addSubElementToEle(ebody, "IdNo", cardPo.getIdCardNum());
			
			// 交易流水
			DocumentUtil.addSubElementToEle(ebody, "tradeNo", cardPo.getAppFlowCode());
			
			// 短信验证码
			DocumentUtil.addSubElementToEle(ebody, "smsCode", smsCode);
			
			// 短信通知 0-需要下发成功短信 1-不需要下发成功短信(默认)
			DocumentUtil.addSubElementToEle(ebody, "smsNotify", "0");
			
			// 信用卡CVV2
			DocumentUtil.addSubElementToEle(ebody, "cardCvv2", cardPo.getCvn2());
			
			// 信用卡有效期
			DocumentUtil.addSubElementToEle(ebody, "cardExpDate", cardPo.getExpire());
			
			// 最低触发余额 以分为单位
			DocumentUtil.addSubElementToEle(ebody, "trigAmt", 
					(String) PublicCache.getInstance().getCachedData(Constants.SH_HEBAO_TRIGAMT));
			
			// 自动交费金额 以元为单位
			DocumentUtil.addSubElementToEle(ebody, "drawAmt",
					(String) PublicCache.getInstance().getCachedData(Constants.SH_HEBAO_DRAWAMT));
			
			return intMsgUtil.invoke("cli_busi_SDHBTelRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("和包易充值平台签约失败!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 和包易充值解约接口
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-29 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap unsignHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// 待操作的手机号码
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// 快捷支付协议号
			DocumentUtil.addSubElementToEle(ebody, "AGRNO", cardPo.getAGRNO());
			
			// 交易流水号
			DocumentUtil.addSubElementToEle(ebody, "tradeNo", cardPo.getAppFlowCode());
			
			// 短信验证码
			DocumentUtil.addSubElementToEle(ebody, "smsCode", smsCode);
			
			// 短信通知
			DocumentUtil.addSubElementToEle(ebody, "smsNotify", "0");
			
			//调用和包易充值解约接口 
			return intMsgUtil.invoke("cli_busi_SDHBTelUnRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("和包易充值平台解约约失败!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
	}
	
	/**
	 * 和包易充值自动交费功能设置
	 * @param headerPo
	 * @param oprType
	 * @param trigAmt
	 * @param drawAmt
	 * @param bankId
	 * @return
	 * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap setHeBaoAutoValue(MsgHeaderPO headerPo, String oprType, BankCardInfoPO bankCardInfoPO)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// 手机号码
			DocumentUtil.addSubElementToEle(ebody, "TELNUM", headerPo.getTelNum());
			
			// IFPOST
			DocumentUtil.addSubElementToEle(ebody, "IFPOST", "");
			
			// OPRFORMNUM
			DocumentUtil.addSubElementToEle(ebody, "OPRFORMNUM", "");
			
			// 查询类型 查询0,新增：1,删除：2,修改:3
			DocumentUtil.addSubElementToEle(ebody, "OPRTYPE", oprType);
			
			// 最低余额值
			DocumentUtil.addSubElementToEle(ebody, "TRIGAMT", bankCardInfoPO.getTrigamt());
			
			// 划扣金额
			DocumentUtil.addSubElementToEle(ebody, "DRAWAMT", bankCardInfoPO.getDrawamt());
			
			// 银行代码
			DocumentUtil.addSubElementToEle(ebody, "BANKID", bankCardInfoPO.getBankId());
			
			return intMsgUtil.invoke("cli_busi_ChgMobilePaySettleType", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("和包易充值设置自动交费金额失败!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
	}
	
	/**
     * 补卡时检验手机号码和身份证信息是否一致
     * @param msgHeader
     * @param idCardNo
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            //modify begin lwx439898 2017-05-13 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTBYSERVNUM))
            {
                // 手机号码
                DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            }
            else
            {
                // 手机号码
                DocumentUtil.addSubElementToEle(ebody, "servnum", msgHeader.getTelNum()); 
            }
            //modify end lwx439898 2017-05-13 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            
            // 身份证号码
            DocumentUtil.addSubElementToEle(ebody, "certid", idCardNo);
            
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTBYSERVNUM))
            {
                ReturnWrap rw = intMsgUtil.invoke("cli_busi_ChkCertByServnum", msgHeader, msgBody);
                
                if (rw.getStatus() == SSReturnCode.ERROR)
                {
                    throw new ReceptionException("实名认证异常，认证失败！");
                }
                
                CTagSet tagSet = (CTagSet)rw.getReturnObject();
                if ("1".equals(tagSet.GetValue("CHKRESULT")))
                {
                    // 证件类型
                    DocumentUtil.addSubElementToEle(ebody, "certtype", "IdCard");
                    
                    return intMsgUtil.invoke("cli_busi_ChkCertByCert", msgHeader, msgBody);
                }
                else
                {
                    throw new ReceptionException("实名认证失败！");
                }
            }
            else
            {
                return intMsgUtil.invoke("cli_busi_ChkCertByServnum", msgHeader, msgBody);
            }
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        } 
        catch (Exception e) 
        {
            logger.error("补卡时检验手机号码和身份证信息是否一致失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 补卡业务规则校验
     * @param msgHeader
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // 电话号码
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // 渠道类型
            DocumentUtil.addSubElementToEle(ebody, "accesstype", "bsacAtsv");
            
            // 业务类型
            DocumentUtil.addSubElementToEle(ebody, "rectype", "ChangeEnum");
            
            // 是否抛异常(填“0”,不抛异常)
            DocumentUtil.addSubElementToEle(ebody, "isthrowexception", "0");
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_busi_CheckBusiRecValid", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("补卡业务规则校验!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // 电话号码
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // 新ICCID
            DocumentUtil.addSubElementToEle(ebody, "iccid", iccid);
            
            // 空白卡卡号
            DocumentUtil.addSubElementToEle(ebody, "blankcardno", blankCardNum);
            
            // 是否进行卡号校验(0不需要;1需要),默认不需要
            DocumentUtil.addSubElementToEle(ebody, "ischeck", "1");
            
            // 是否进行规则校验
            DocumentUtil.addSubElementToEle(ebody, "ischeckrecvalid", "1");
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_busi_CalChangeEnumFee", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("补卡算费：", e);
            
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
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, 
        String blankno, SimInfoPO simInfo, String bankNo, String bankNbr)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // 渠道
            DocumentUtil.addSubElementToEle(ebody, "accesstype", "bsacAtsv");
            
            // 新ICCID
            DocumentUtil.addSubElementToEle(ebody, "iccid", simInfo.getIccid());
            
            // 新IMSI
            DocumentUtil.addSubElementToEle(ebody, "imsi", simInfo.getImsi());
            
            // 传1
            DocumentUtil.addSubElementToEle(ebody, "isneedfee", "1");
            
            // 终端收取费用
            DocumentUtil.addSubElementToEle(ebody, "sumfee", recFee);
            
            // 支付方式
            DocumentUtil.addSubElementToEle(ebody, "paytype", payType);
            
            // 是否减免
            DocumentUtil.addSubElementToEle(ebody, "isderatefee", "0");
            
            // 空白卡
            DocumentUtil.addSubElementToEle(ebody, "blankcardno", blankno);
            
            // 是否进行业务规则校验
            DocumentUtil.addSubElementToEle(ebody, "ischeckrecvalid", "1");
            
            // add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
            // 银行号
            DocumentUtil.addSubElementToEle(ebody, "agentunit", bankNo);
            
            // 唯一流水，终端机返回的termseq
            DocumentUtil.addSubElementToEle(ebody, "agentformnum", bankNbr);
            
            // 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
            DocumentUtil.addSubElementToEle(ebody, "accepttype", Constants.ACCEPTTYPE_REISSUECARD);
            
            // 缴费日期，格式： YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(ebody, "paydate", DateUtil._getCurrentTime());
            // add end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_busi_ChangeEnumRec", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("补卡提交：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <查询用户信息>
     * <功能详细描述>
     * @param msgHeader
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region)
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
            logger.error("查询用户信息接口调用异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"补卡查询用户信息失败");
        }

    }
	
	/**
	 * 业务有效性校验
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 客户信息
	 * @param menuId 菜单id
	 * @return true：可以继续办理业务，false：终止办理业务
	 * @see [类、类#方法、类#成员]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap checkRecValid(MsgHeaderPO msgHeader)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 手机号
			DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
			
			// 渠道类型
			DocumentUtil.addSubElementToEle(eBody, "accessType", "bsacAtsv");
			
			// 受理类型 固定传RewardActivity
			DocumentUtil.addSubElementToEle(eBody, "recType", "RewardActivity");
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHECKRECVALID, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("业务有效性校验失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 查询用户已经存在的档次
	 * @param msgHeader 信息头
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qrySubsActLevelList(MsgHeaderPO msgHeader) 
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 手机号
			DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SUBSPRIVLISTSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("查询用户已经存在的档次:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 查询奖品列表
	 * @param msgHeader 消息头
	 * @param actLevelId 档次编码
	 * @param activityId 活动编码
	 * @return
	 * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRewardList(MsgHeaderPO msgHeader, String actLevelId, String activityId)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 活动编码
			DocumentUtil.addSubElementToEle(eBody, "prodid", activityId);
	        
	        // 档次编码
			DocumentUtil.addSubElementToEle(eBody, "privid", actLevelId);
	        
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_REWARDLISTSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("查询奖品列表:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 查询活动费用
	 * @param msgHeader 消息头
	 * @param actid 活动编码
	 * @param levelid 档次编码
	 * @param rewardId 奖品编码
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryActivityFee(MsgHeaderPO msgHeader, String actid, String levelid, String rewardId)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			//modify begin by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHKPRIVANDCALCFEE))
			{
	            // 手机号码
	            DocumentUtil.addSubElementToEle(eBody, "TELNUM", msgHeader.getTelNum());
	            
	            // 活动编码
	            DocumentUtil.addSubElementToEle(eBody, "ACTNO", actid);
	            
	            // 档次编码
	            DocumentUtil.addSubElementToEle(eBody, "ACTLEVEL", levelid);
	            	            
	            // 拆分奖品  
	            String[] rewardIds = rewardId.split("\\|");
	            StringBuffer actreward = new StringBuffer();
	            for (int i = 0; i < rewardIds.length; i++)
	            {
	                //格式：奖品编码1$奖品数量|奖品编码2$奖品数量
	                if(StringUtils.isEmpty(actreward.toString()))
	                {
	                    actreward.append(rewardIds[i]).append("$").append("1");
	                }
	                else
	                {
                        actreward.append("|").append(rewardIds[i]).append("$").append("1");
	                }
	            }

                // 奖品串列表
                DocumentUtil.addSubElementToEle(eBody, "ACTREWARD", actreward.toString());
			}
			else
			{
	            // 手机号码
	            DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
	            
	            // 活动编码
	            DocumentUtil.addSubElementToEle(eBody, "actid", actid);
	            
	            // 档次编码
	            DocumentUtil.addSubElementToEle(eBody, "levelid", levelid);
	            
	            // 拆分奖品  
	            String[] rewardIds = rewardId.split("\\|");
	            
	            for (int i = 0; i < rewardIds.length; i++)
	            {
	                // 奖品信息
	                Element rwdInfo = eBody.addElement("rwdInfo");
	                
	                // 奖品编码
	                DocumentUtil.addSubElementToEle(rwdInfo, "rewardId", rewardIds[i]);
	            
	                // 奖品串号 imeiid
	                DocumentUtil.addSubElementToEle(rwdInfo, "imeiid", "");
	                
	                // 数量
	                DocumentUtil.addSubElementToEle(rwdInfo, "quantity", "1");
	            } 
			}
            //modify e by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_CHKPRIVANDCALCFEE, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("查出活动费用失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * 预存有礼受理
	 * 
     * @param msgHeader 消息头
     * @param prestoredRewardPO 入参 
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
    public ReturnWrap recRewardCommit(MsgHeaderPO msgHeader, PrestoredRewardPO prestoredRewardPO, String bankNo,
        String bankNbr)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 手机号码
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			// 活动编码
			DocumentUtil.addSubElementToEle(eBody, "actno", prestoredRewardPO.getActivityId());
			
			// 档次编码
			DocumentUtil.addSubElementToEle(eBody, "actlevel", prestoredRewardPO.getActLevelId());
			
			// 奖品串
			DocumentUtil.addSubElementToEle(eBody, "actreward", prestoredRewardPO.getActreward());
			
			// 手机imeiid
			DocumentUtil.addSubElementToEle(eBody, "imeiid", "");
			
			// 是否预受理
			DocumentUtil.addSubElementToEle(eBody, "onlycheck", prestoredRewardPO.getOnlycheck());
			
			// 奖品数量
			DocumentUtil.addSubElementToEle(eBody, "quantity", "1");
			
			// 受理渠道
			DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
			
			// 密码
			DocumentUtil.addSubElementToEle(eBody, "password", "");
			
			// 用户投入的费用金额 预受理时可以不传
			DocumentUtil.addSubElementToEle(eBody, "totalfee", prestoredRewardPO.getTotalFee());
			
			// 支付方式cash:现金  card:银联卡
			DocumentUtil.addSubElementToEle(eBody, "paytype", StringUtils.capitalize(prestoredRewardPO.getPaytype()));
			
			// add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
			// 银行号
            DocumentUtil.addSubElementToEle(eBody, "agentunit", bankNo);
            
            // 唯一流水，终端机返回的termseq
            DocumentUtil.addSubElementToEle(eBody, "agentformnum", bankNbr);
            
            // 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", Constants.ACCEPTTYPE_PRESTORED_REWARD);
            
            // 缴费日期，格式： YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
			// add end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送            

			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_RECREWARDCOMMITSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("预存有礼受理失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
    /** 
     * 业务办理前记录业务费用信息
     * 
     * @param msgHeader 报文请求头
     * @param bankNo 银行号
     * @param payDate 缴费日期,格式：YYYYMMDDHH24MISS
     * @param acceptType 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
     * @param bankNbr 唯一流水(和agentcharge表的AGENTFORMNUM字段保持一致) ，终端机返回的termseq
     * @param amount 订单金额
     * @param prestoredRewardPO 活动信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
     */
    public ReturnWrap writeNetFeeInfo(MsgHeaderPO msgHeader, String bankNo, String acceptType,
        String bankNbr, String amount, PrestoredRewardPO prestoredRewardPO)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 银行号
            DocumentUtil.addSubElementToEle(eBody, "bankno", bankNo);
            
            // 缴费日期,格式：YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "msisdn", msgHeader.getTelNum());
            
            // 订单金额
            DocumentUtil.addSubElementToEle(eBody, "amount", CommonUtil.fenToYuan(amount));
            
            // 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", acceptType);
            
            // 唯一流水(和agentcharge表的AGENTFORMNUM字段保持一致) ，终端机返回的termseq
            DocumentUtil.addSubElementToEle(eBody, "banknbr", bankNbr);
            
            // 预存赠活动办理时传入以下参数
            if (null != prestoredRewardPO)
            {
                // 活动编码，开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "actid", prestoredRewardPO.getActivityId());
                
                // 活动档次编码，开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "levelid", prestoredRewardPO.getActLevelId());
                
                // 奖品编码,活动赠品编号串，多个赠品中间用|分割  如：3333333333|3322322333|4444444|5555555,开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "rewardid", prestoredRewardPO.getActreward());
                
                // 支付方式Cash:现金  Card:银联卡， 开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "paytype", StringUtils.capitalize(prestoredRewardPO.getPaytype()));
            }
            else
            {
                // 活动编码，开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "actid", "");
                
                // 活动档次编码，开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "levelid", "");
                
                // 奖品编码,活动赠品编号串，多个赠品中间用|分割  如：3333333333|3322322333|4444444|5555555,开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "rewardid", "");
                
                // 支付方式Cash:现金  Card:银联卡， 开户和补卡传空值
                DocumentUtil.addSubElementToEle(eBody, "paytype", "");
            }
            
            // 渠道编码
            DocumentUtil.addSubElementToEle(eBody, "accesstype", Constants.CHANNEL_ID);

            // 调用接口
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_WRITENETFEEINFO, msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("业务办理前记录失败:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

	/**
	 * 查询营销方案费用和用户预存费用
	 * @param msgHeader 消息头
	 * @param recoid 受理流水
	 * @param totalFee 用户存入的费用
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRecFeeAndPreFee(MsgHeaderPO msgHeader, String recoid, String totalFee)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 受理流水
			DocumentUtil.addSubElementToEle(eBody, "recoid", recoid);
			
			// 用户存入的费用
			DocumentUtil.addSubElementToEle(eBody, "totalFee", totalFee);
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_PRIVFEESD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("查询营销方案费用和用户预存费用失败:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 办理特惠业务包
     * @param msgHeader
     * @param privServPackPO
     * @return
     * @remark create by hWX5316476 2014-12-24 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
    public ReturnWrap privServPackRec(MsgHeaderPO msgHeader,PrivServPackPO privServPackPO)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // nCode
            DocumentUtil.addSubElementToEle(ebody, "ncode", privServPackPO.getNcode());
            
            // 操作类型 固定传ADD
            DocumentUtil.addSubElementToEle(ebody, "stype", "ADD");
            
            // 生效类型，（0默认  2立即  3下周期 4指定时间生效）
            DocumentUtil.addSubElementToEle(ebody, "efftype", privServPackPO.getEffType());
            
            // 生效时间 格式 YYYYMMDD
            DocumentUtil.addSubElementToEle(ebody, "ncode_effdate", privServPackPO.getEffDate().replace("-", ""));
                
            // 失效时间 格式YYYYMMDD
            DocumentUtil.addSubElementToEle(ebody, "ncode_expdate", privServPackPO.getEndDate().replace("-", ""));
            
            // 调用接口
            return intMsgUtil.invoke("cli_busi_privServPackCommit", msgHeader, msgBody); 
        }
        catch (Exception e)
        {
            logger.error("特惠业务包受理失败:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据主体产品Id获取主体产品信息
     * 
     * @param msgHeader
     * @param prodid 产品编码
     * @param type  对象类型 如果为产品传PRODTYPE；如果为优惠传PRIVTYPE
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public ReturnWrap qryProdInfoById(MsgHeaderPO msgHeader,String prodid,String type)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            //modify by lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
            // 主体产品编码
            DocumentUtil.addSubElementToEle(ebody, "objid", prodid);
                
            // 对象类型
            DocumentUtil.addSubElementToEle(ebody, "objtype", type);
            //modify by lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
                        
            // 调用接口
            return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_GETPRODINFOBYID, msgHeader, doc); 
        }
        catch (Exception e)
        {
            logger.error("根据主体产品Id获取主体产品信息异常:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "根据主体产品Id获取主体产品信息异常");
        }
    }
    
    /**
     * 组内档次转换
     * @param msgHeader
     * @param ncode NCODE
     * @param stype 操作类型 ADD 增加 DEL 删除 MOD 修改 QRY 查询
     * @param preparebusi 预受理 固定传BsacNBSubmit
     * @param premutex 是否将互斥或依赖的进行关联删除，不删除传空
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     * @remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
     */
    public ReturnWrap chgLevelInGroup(MsgHeaderPO msgHeader, String ncode, String stype, String preparebusi, String premutex)
    {
        try
        {         
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD))
            {
                Element eReq_recProdlist = ebody.addElement("RECPRODLIST");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRODID",ncode);
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "EFFECTTYPE", "");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "OPTYPE", "PCOpRec");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "OBJTYPE", "NCODE");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "TEMPLATEID", "");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRIVID", ""); 
            }
            else
            {
                // nCode
                DocumentUtil.addSubElementToEle(ebody, "ncode", ncode);
                
                // 操作类型 固定传ADD
                DocumentUtil.addSubElementToEle(ebody, "stype", stype);
                
                // 预受理 固定传BsacNBSubmit
                DocumentUtil.addSubElementToEle(ebody, "preparebusi", preparebusi);
                
                // 将互斥或依赖的进行关联删除 固定传NEEDPREMUTEX
                DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", premutex);
            }
            
            
            // 调用接口             
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD, msgHeader, msgBody); 
        }
        catch (Exception e)
        {
            logger.error("组内档次转换失败:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"组内档次转换异常");
        }
    }
    
    /** 
     * 开户时证件号码校验
     * 
     * @param msgHeader 消息头
     * @param curMenuid 当前菜单ID
     * @param certType 证件类型
     * @param certId 证件号码
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
    	try
    	{
    		Document msgBody = DocumentHelper.createDocument();
    		Element eBody = msgBody.addElement("message_content");
    		
    		// 证件号码
    		DocumentUtil.addSubElementToEle(eBody, "certID", certId);
    		
    		// 证件类型
    		DocumentUtil.addSubElementToEle(eBody, "certType", certType);
    		
    		// 调用接口
    		return intMsgUtil.invoke("cli_qry_chkCertInfoForInstall", msgHeader, msgBody);
    	}
    	catch (Exception e)
    	{
    		logger.error("校验证件下开户数量失败：",e);
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验证件下开户数量异常");
    	}
    }
    
    /** 
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param fitmod 选号规则
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-4 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String fitmod, String mainProdid)
    {
        try
        {
        	Document msgBody = DocumentHelper.createDocument();
        	Element eBody = msgBody.addElement("message_content");
        	
        	// 组织机构id
        	DocumentUtil.addSubElementToEle(eBody, "qryOrgId", orgId);
        	
        	// 号码符合条件
        	DocumentUtil.addSubElementToEle(eBody, "fitmod", fitmod);
        	
        	String maxCount = "100";
        	if (CommonUtil.isNotEmpty(Constants.SH_TELNUM_MAXCOUNT))
        	{
        		maxCount = CommonUtil.getParamValue(Constants.SH_TELNUM_MAXCOUNT);
        	}
        	
        	// 获取选号的最大数量(1-2000)，上限2000
        	DocumentUtil.addSubElementToEle(eBody, "maxCount", maxCount);
        	
        	// 是否查询绑定实体卡的手机号
        	DocumentUtil.addSubElementToEle(eBody, "bundleimsi", "0");
        	
        	// 手机号资源类型
        	DocumentUtil.addSubElementToEle(eBody, "telType", "rsclTgsm");
        	
        	return intMsgUtil.invoke("cli_qry_qryAvlTelNum", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("依据选号规则查询手机号码列表异常", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"依据选号规则查询手机号码列表异常");
        }
    }
    
    /**
     * 号码资源暂选接口
     * 
     * @param msgHeader 消息头
     * @param telnum 手机号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, String telnum)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			//modify begin by cwx456134 2017-05-08 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2 
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_LOCKORUNLOCKTELNUM))
			{
			    // 手机号
                DocumentUtil.addSubElementToEle(eBody, "TELNUM", telnum);
                
                // 操作标志 0为锁定，1为解锁
                DocumentUtil.addSubElementToEle(eBody, "OPERFLAG", "0");
			}
			else
			{
	            // 手机号
	            DocumentUtil.addSubElementToEle(eBody, "msisdn", telnum);
	            
	            // 资源类型，reclTgsm
	            DocumentUtil.addSubElementToEle(eBody, "resType", "rsclTgsm");
	            
	            // 操作标志 0为锁定，1为解锁
	            DocumentUtil.addSubElementToEle(eBody, "operFlag", "0");
			}
            //modify end by cwx456134 2017-05-08 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
			
			// 调用接口
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_LOCKORUNLOCKTELNUM, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("手机号暂选失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "手机号暂选异常");
		}
	}
	
	/**
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 消息头
     * @param curMenuid 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param prodid 主体产品
     * @param recType 受理类型，开户Install，补卡ChangeEnum	
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, TerminalInfoPO termInfo, String blankNo, String prodid, String recType)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 地区
            DocumentUtil.addSubElementToEle(eBody, "region", termInfo.getRegion());
            
            // 组织机构
            DocumentUtil.addSubElementToEle(eBody, "orgid", termInfo.getOrgid());
            
            // 空白卡号
            DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankNo);
            
            // 变更类型
            DocumentUtil.addSubElementToEle(eBody, "changeType", recType);
            
            // 受理类型
            DocumentUtil.addSubElementToEle(eBody, "recType", recType);
            
            // 主体产品
            DocumentUtil.addSubElementToEle(eBody, "prodid", prodid);
            
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHECKBLANKCARD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("校验空白卡资源是否可用失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验空白卡资源是否可用异常");
		}
	}
	
	/**
     * 校验空白卡是否是预置空卡
     * 
     * @param msgHeader 消息头
     * @param blankNo 空白卡序列号
     * @param telNum 手机号
     * @param recType 受理类型，开户Install，补卡ChangeEnum	
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum, String recType)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 空白卡序列号
			DocumentUtil.addSubElementToEle(eBody, "blankCardSN", blankNo);
			
			// 空卡序列号
			DocumentUtil.addSubElementToEle(eBody, "blankCardSeq", blankNo);
			
			// 受理类型
			DocumentUtil.addSubElementToEle(eBody, "recType", recType);
			
			// 手机号码
			DocumentUtil.addSubElementToEle(eBody, "serverNum", telNum);
			
			// 地区
			DocumentUtil.addSubElementToEle(eBody, "region", msgHeader.getRegion());
			
			// 是否获取鉴权数据 传空
			DocumentUtil.addSubElementToEle(eBody, "retPresetData", "");
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHKISPRESETBLANKCARD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("校验空白卡是否是预置空卡失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "校验空白卡是否是预置空卡异常");
		}
	}
	
	/**
     * 开户算费
     * 
     * @param msgHeader 消息头
     * @param telnum 手机号
     * @param tpltTempletPO 模板po
     * @param simInfoPO sim卡po
     * @param blankno 空白卡序列号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap reckonRecFee(MsgHeaderPO msgHeader, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 开户号码
			DocumentUtil.addSubElementToEle(eBody, "installTelnum", msgHeader.getTelNum());
			
			// 主体产品
			DocumentUtil.addSubElementToEle(eBody, "mainProdid", tpltTempletPO.getMainProdId());
		
			// 模板编码
			DocumentUtil.addSubElementToEle(eBody, "prodTempletid", tpltTempletPO.getTempletId());
			
			// sim卡号(ICCID)
			DocumentUtil.addSubElementToEle(eBody, "simNum", simInfoPO.getOldiccid());
			
			// 空白卡号
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankno);
			
			// 是否返回减免费用 传1
			DocumentUtil.addSubElementToEle(eBody, "retDiscountFee", "1");
			//add by lWX298507 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1 2017-5-8 begin
			if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL))
            {
                DocumentUtil.addSubElementToEle(eBody, "REGION", tpltTempletPO.getRegion());
            }
			
			ReturnWrap rw = intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL, msgHeader, msgBody);
			if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL))
			{
			    Vector vector = (Vector)rw.getReturnObject();
		        CRSet crSet =(CRSet) vector.elementAt(1);
		        rw.setReturnObject(crSet);
		        return rw;
			}
			//add by lWX298507 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1 2017-5-8 end
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("开户算费失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "开户算费异常");
		}
	}
	
	/**
     * 申请写卡，包括空白卡资源暂选和获取加密数据
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap getEncryptedData(MsgHeaderPO msgHeader, String blankNo, String recType, String region)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 预置空卡序列号
			DocumentUtil.addSubElementToEle(eBody, "blankCardSN", blankNo);
			
			// 手机号
			DocumentUtil.addSubElementToEle(eBody, "serverNum", msgHeader.getTelNum());
			
			// 地区
			DocumentUtil.addSubElementToEle(eBody, "region", region);
			
			// 受理类型
			DocumentUtil.addSubElementToEle(eBody, "recType", recType);
			
			// 操作员 
			DocumentUtil.addSubElementToEle(eBody, "operId", msgHeader.getOperId());
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_GENWRITECARDDATAENCRYPT, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("申请写卡失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "申请写卡异常");
		}
	}
	
	/**
     * 写卡成功失败接口
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param simInfoPO sim卡信息
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHeader, String blankno, SimInfoPO simInfoPO)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 空白卡序列号
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankno);
			
			// iccid
			DocumentUtil.addSubElementToEle(eBody, "iccID", simInfoPO.getIccid());
			
			// imsi
			DocumentUtil.addSubElementToEle(eBody, "imsi", simInfoPO.getImsi());
			
			// 传空或imsi都行
			DocumentUtil.addSubElementToEle(eBody, "secImsi", simInfoPO.getImsi());
			
			// 成功传“写卡成功”，失败写错误信息
			DocumentUtil.addSubElementToEle(eBody, "errMsg", simInfoPO.getErrMsg());

			// 成功传0，失败写失败错误吗
			DocumentUtil.addSubElementToEle(eBody, "errCode", simInfoPO.getErrCode());
		
			// 是否写卡成功1 成功  -1 失败
			DocumentUtil.addSubElementToEle(eBody, "iswSus", simInfoPO.getWriteResult());
			
			// 操作员
			DocumentUtil.addSubElementToEle(eBody, "operId", msgHeader.getOperId());
			
			return intMsgUtil.invoke("cli_qry_writeBlankCardFail", msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("作废卡失败：", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "作废卡异常");
		}
	}
	
	/**
     * 开户提交
     * 
     * @param msgHeader 消息头
     * @param simInfoPO sim卡信息
     * @param tpltTempletPO 模板信息
     * @param idCardPO 用户身份信息
     * @param totalfee 总费用
     * @param passwd 服务密码
     * @param telnum 手机号码
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-15 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHeader, SimInfoPO simInfoPO, ProdTempletPO tpltTempletPO,
        IdCardPO idCardPO, String totalfee, String passwd, String telnum, String bankNo, String bankNbr)
    {
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// 渠道
			DocumentUtil.addSubElementToEle(eBody, "ACCESSTYPE", Constants.CHANNEL_ID);
			
			// 产品模板
			DocumentUtil.addSubElementToEle(eBody, "templateId", tpltTempletPO.getTempletId());
			
			// 主体产品
			DocumentUtil.addSubElementToEle(eBody, "mainProd", tpltTempletPO.getMainProdId());
			
			// 新开户号码
			DocumentUtil.addSubElementToEle(eBody, "telnum", telnum);
			
			// 子业务类型
			DocumentUtil.addSubElementToEle(eBody, "subRecType", "SELFCVSINSTALL");
			
			// 空白卡号
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", simInfoPO.getBlankno());
			
			// imsi
			DocumentUtil.addSubElementToEle(eBody, "newImsi", simInfoPO.getImsi());
			
			// iccid
			DocumentUtil.addSubElementToEle(eBody, "newIccid", simInfoPO.getIccid());
			
			// 总费用
			DocumentUtil.addSubElementToEle(eBody, "totalPrice", totalfee);
			
			// 不传写卡验证码 标志(传1)
			DocumentUtil.addSubElementToEle(eBody, "noCardCheckFlag", "1");
			
			// 证件ID
			DocumentUtil.addSubElementToEle(eBody, "certid", idCardPO.getIdCardNo());
			
			// 证件类型
			DocumentUtil.addSubElementToEle(eBody, "certtype", "IdCard");
			
			// 客户名称
			DocumentUtil.addSubElementToEle(eBody, "custname", idCardPO.getIdCardName());
			
			// 联系电话
			DocumentUtil.addSubElementToEle(eBody, "linkphone", "");
			
			// 联系地址
			DocumentUtil.addSubElementToEle(eBody, "linkaddr", idCardPO.getIdCardAddr());
			
			// 证件地址
			DocumentUtil.addSubElementToEle(eBody, "certaddr", idCardPO.getIdCardAddr());
			
			// 用户密码
			DocumentUtil.addSubElementToEle(eBody, "passwd", passwd);
			
			// add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
			// 银行号
            DocumentUtil.addSubElementToEle(eBody, "agentunit", bankNo);
            
            // 唯一流水，终端机返回的termseq
            DocumentUtil.addSubElementToEle(eBody, "agentformnum", bankNbr);
            
            // 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", Constants.ACCEPTTYPE_CARDINSTALL);
            
            // 缴费日期，格式： YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
			// add end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
			
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SELFINSTALL))
            {
                ReturnWrap rw = intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SELFINSTALL, msgHeader, msgBody);
                if (rw.getStatus() == SSReturnCode.ERROR)
                {
                    throw new ReceptionException(rw.getReturnMsg());
                }
                
                CTagSet v = (CTagSet)rw.getReturnObject();
                
                
                DocumentUtil.addSubElementToEle(eBody, "INSTALL_FORMNUM", v.GetValue("FORMNUM"));
                DocumentUtil.addSubElementToEle(eBody, "SERVNUM", telnum);
                DocumentUtil.addSubElementToEle(eBody, "PAY_DATE", DateUtil._getCurrentTime());
                intMsgUtil.invoke(BusinessIdConstants.WRITEAGENTCHARGEZZZD, msgHeader, msgBody);
                return rw;
                
            }
            else
            {
                // 调用接口
                return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SELFINSTALL, msgHeader, msgBody);
            }
            
		}
		catch (Exception e)
		{
			logger.error("开户提交失败", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "开户提交异常");
		}
	}
	

    
    /**
     * <查询用户已预约的号码列表>
     * <功能详细描述>
     * @param msgHeader
     * @param Document doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
     */
    public ReturnWrap qryBookedTelnum(MsgHeaderPO msgHeader, Document doc)
    {
        try
        {
            return intMsgUtil.invoke("cli_qry_bookedTelnum", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("查询已预约的手机号码失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    

    /**
	 * 山东有价卡购买接口
	 * @param header
	 * @param doc
	 * @return
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
	 */
	public ReturnWrap buyValueCard(MsgHeaderPO header, Document doc)
	{
		try
		{
			return getIntMsgUtil().invoke("cli_qry_elecCardSale", header, doc);
		}
		 catch (Exception e)
        {
            logger.error("异地缴费异常!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"异地缴费异常！");
        }
	}
}
