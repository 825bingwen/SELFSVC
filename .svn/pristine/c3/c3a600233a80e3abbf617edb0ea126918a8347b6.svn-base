package com.customize.cq.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.FeeChargeBean;
import com.customize.cq.selfsvc.charge.service.FeeChargeService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 话费充值缴费
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeAction extends BaseAction
{
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // 日志
//    private static Log logger = LogFactory.getLog(FeeChargeAction.class);
//    
//    // 当前菜单
//    private String curMenuId = "";
//    
//    // begin zKF66389 findbus清零
//    // 用户手机号
//    private String servNumber;
//    // end zKF66389 findbus清零
//    
//    // 交费金额
//    private String tMoney;
//    
//    // 终端机流水号
//    private String terminalSeq;
//    
//    // begin zKF66389 findbus清零
//    // 错误信息
//    private String errorMessage;
//    // end zKF66389 findbus清零
//    
//    // 调用接口Bean
//    private FeeChargeBean feeChargeBean;
//    
//    private String servRegion = "";
//    
//    // begin zKF66389 findbus清零
//    private String acceptType = "";
//    // end zKF66389 findbus清零
//    
//    // 应缴费用
//    private String yingjiaoFee;
//    
//    // 话费余额
//    private String balance;
//    
//    // 用户刷卡卡号
//    private String cardnumber;
//    
//    // 银联为刷卡的终端分配的唯一编号
//    private String unionpaycode;
//    
//    // 银联商户号（授卡方标识）
//    private String unionpayuser;
//    
//    // begin zKF66389 findbus清零
//    // 业务类型
//    private String busiType;
//    // end zKF66389 findbus清零
//    
//    // 终端批次号
//    private String batchnum;
//    
//    // 银联实际扣款金额，单位（分）
//    private String unionpayfee;
//    
//    // 银联实际扣款时间
//    private String unionpaytime;
//    
//    // 授权码（山东用）
//    private String authorizationcode;
//    
//    // 交易参考号（山东用）
//    private String businessreferencenum;
//    
//    // 评证号（山东用）
//    private String vouchernum;
//    
//    // 调用数据库
//    private FeeChargeService feeChargeService;
//    
//    // BOSS流水
//    private String dealNum = "";
//    
//    private String hasMultiInvoices;
//    
//    private String payType;
//    
//    private boolean canPayWithCash = true;
//    
//    private List usableTypes = null;
//    
//    // 缴费日志对应的oid
//    private String chargeLogOID = "";
//    
//    private String errorType = "";
//    
//    private String needReturnCard = "";
//    
//    /**
//     * 话费充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
//     * 
//     * @return
//     */
//    public String inputNumber()
//    {
//        return "inputNumber";
//    }
//    
//    /**
//     * 话费充值账户信息查询
//     * 
//     * @return
//     */
//    public String qryfeeChargeAccount()
//    {        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("qryfeeChargeAccount start");
//        }
//        
//        String forward = "failed";
//        
//        // 判断用户状态是否可以缴费，返回true可以缴费
//        String checkResult = getUserStatus();
//        if (!"".equals(checkResult))
//        {       	
//        	return forward;
//        }
//        
//        HttpServletRequest request = this.getRequest();
//            
//        try
//        {
//        	TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
//                
//            // 现金缴费
//            String bankNo = "20";
//                
//            Date date = new Date();
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//            String payDate = sdf1.format(date);
//                
//            // begin zKF66389 findbus清零
//            Map result = feeChargeBean.qryfeeChargeAccount(termInfo, servNumber, curMenuId, bankNo, payDate, "ALL");
//            // end zKF66389 findbus清零
//            if (result != null && result.size() > 0)
//            {
//            	CTagSet tagSet = (CTagSet)result.get("returnObj");
//                    
//                servRegion = tagSet.GetValue("servregion");
//                    
//                acceptType = tagSet.GetValue("accept_type");
//                    
//                if (acceptType == null || "".equals(acceptType))
//                {
//                	logger.error("查询受理类型失败！");
//                    
//                    setErrorMessage("查询用户信息失败");
//                    
//                    // 记录异常日志
//                    this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "缴费时查询受理类型失败");
//                    
//                    return forward;
//                }
//                    
//                // 应缴费用
//                if (tagSet.GetValue("sum_fee") == null || "".equals(tagSet.GetValue("sum_fee")))
//                {
//                	this.setYingjiaoFee(null);
//                        
//                    // 设置话费余额
//                    setBalance(tagSet.GetValue("balance"));
//                }
//                else
//                {
//                    this.setYingjiaoFee(tagSet.GetValue("sum_fee"));
//                }
//                
//                // ----------------------------充值方式-------------------------------------------------
//                    
//                // 根据终端组自缓存中获取菜单列表
//                String groupID = termInfo.getTermgrpid();
//                    
//                List<MenuInfoPO> menus = null;
//                    
//                if (groupID != null && !"".equals(groupID))
//                {
//                	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
//                }
//                    
//                // 当页菜单列表
//                usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
//                    
//                if (logger.isInfoEnabled())
//                {
//                    logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//                }
//                    
//                if (usableTypes == null || usableTypes.size() == 0)
//                {
//                    // 没有可用的充值方式
//                    setErrorMessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
//                        
//                    // 记录日志
//                    this.createRecLog(servNumber, "feeCharge", "0", "0", "1", "暂时没有可用的充值方式");
//                }
//                else
//                {
//                    forward = "success";
//                }
//            }
//            else
//            {
//                logger.error("查询受理类型失败！");
//                    
//                setErrorMessage("查询用户信息失败");                    
//                    
//                // 记录错误日志
//                this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "缴费时查询受理类型失败");
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("查询受理类型失败！");
//                
//            setErrorMessage("查询用户信息失败");
//                
//            // 记录异常日志
//            this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "缴费时查询受理类型失败");
//        }
//        
//        return forward;
//    }
//    
//    /**
//     * 选择交费金额
//     * 
//     * @return
//     * @see
//     */
//    public String selectOtherFee()
//    {
//        return "success";
//    }
//    
//    /**
//     * 手工输入缴费金额
//     * 
//     * @return
//     */
//    public String toInputMoney()
//    {
//        return "toInputMoney";
//    }
//    
//    /**
//     * 转向投币页面
//     * 
//     * @return
//     */
//    public String cashCharge()
//    {
//        return "cashChargePage";
//    }
//    
//    /**
//     * 话费充值，现金缴费
//     * 
//     * @return
//     */
//    public String cashChargeCommit()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("cashChargeCommit start");
//        }
//        
//        String forward = null;
//        
//        HttpSession session = getRequest().getSession();
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        
//        // 发起缴费请求之前首先记录投币日志
//        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//        
//        String logOID = feeChargeService.getChargeLogOID();
//        selfCardPayLogVO.setChargeLogOID(logOID);
//        
//        selfCardPayLogVO.setRegion(termInfo.getRegion());
//        selfCardPayLogVO.setTermID(termInfo.getTermid());
//        selfCardPayLogVO.setOperID(termInfo.getOperid());
//        selfCardPayLogVO.setServnumber(servNumber);
//        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
//        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//        
//        terminalSeq = termInfo.getTermid() + terminalSeq;
//        if (terminalSeq.length() > 20)
//        {
//            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
//        }
//        selfCardPayLogVO.setTerminalSeq(terminalSeq);
//        
//        selfCardPayLogVO.setRecdate(payDate);
//        selfCardPayLogVO.setAcceptType(acceptType);
//        selfCardPayLogVO.setServRegion(servRegion);
//        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//        selfCardPayLogVO.setStatus("01");
//        selfCardPayLogVO.setDescription("缴费之前记录投币日志");
//        selfCardPayLogVO.setDealnum("");
//        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
//        
//        feeChargeService.addChargeLog(selfCardPayLogVO);
//        
//        // 现金缴费
//        String bankNo = "20";
//        
//        // 山东接口 -- 发票打印标志，0＝不打印 1＝打印
//        String invoiceType = "1";
//        
//        // 现金缴费
//        Map resultMap = feeChargeBean.chargeCommit(termInfo,
//                curMenuId,
//                servNumber,
//                bankNo,
//                payDate,
//                terminalSeq,
//                tMoney,
//                acceptType,
//                invoiceType,
//                "",
//                "");
//        
//        if (resultMap != null && resultMap.size() > 0)
//        {
//            Object obj = resultMap.get("returnObj");
//            if (obj instanceof Vector)
//            {
//                // 对山东缴费接口的返回值进行解析
//                Vector chargeInfo = (Vector)obj;
//                CTagSet tagSet = (CTagSet)chargeInfo.get(0);
//                CRSet crset = (CRSet)chargeInfo.get(1);
//                
//                dealNum = (String)tagSet.GetValue("bill_nbr");// 受理编号
//                String cycleCount = (String)tagSet.GetValue("bcycle_count");
//                int rowNum = Integer.parseInt(cycleCount);
//                
//                if (rowNum > 1)
//                {
//                    hasMultiInvoices = "true";
//                }
//                else
//                {
//                    hasMultiInvoices = "false";
//                }
//                
//                // 提供发票打印功能
//                String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
//                if ("1".equals(canPrintInvoice))
//                {
//                    List invoicesList = new ArrayList();
//                    Map invoiceMap = null;
//                    
//                    for (int i = 0; i < rowNum; i++)
//                    {
//                        invoiceMap = new HashMap();
//                        
//                        invoiceMap.put("servNumber", servNumber);
//                        invoiceMap.put("dealNum", dealNum);
//                        invoiceMap.put("acceptType", (String)tagSet.GetValue("accept_name"));
//                        invoiceMap.put("acctID", (String)tagSet.GetValue("acct_id"));
//                        invoiceMap.put("subsName", (String)tagSet.GetValue("cust_name"));
//                        invoiceMap.put("payDate", (String)tagSet.GetValue("pay_date"));
//                        invoiceMap.put("bossFormnum", dealNum);
//                        
//                        invoiceMap.put("pOrgName", termInfo.getOrgname());
//                        
//                        invoiceMap.put("feeTime", (String)crset.GetValue(i, 0));
//                        invoiceMap.put("ysFee", (String)crset.GetValue(i, 2));
//                        invoiceMap.put("capitalFee", (String)crset.GetValue(i, 3));
//                        
//                        String consumeList = parseConsumeList((String)crset.GetValue(i, 7),
//                                (String)tagSet.GetValue("comment"));
//                        invoiceMap.put("consumeList", consumeList);
//                        
//                        invoicesList.add(invoiceMap);
//                    }
//                    
//                    this.getRequest().setAttribute("invoiceXml", getXmlStr(invoicesList));
//                }
//            }
//            
//            selfCardPayLogVO.setStatus("11");
//            selfCardPayLogVO.setDescription("话费充值现金缴费成功！");
//            selfCardPayLogVO.setDealnum(dealNum);
//            
//            forward = "success";
//            
//            // 记录缴费成功日志
//            this.createRecLog(servNumber, Constants.PAY_BYCASH, "0", "0", "0", "缴费:自助终端缴费成功!");
//        }
//        else
//        {
//            selfCardPayLogVO.setStatus("10");
//            selfCardPayLogVO.setDescription("话费充值现金缴费失败！");
//            selfCardPayLogVO.setDealnum("");
//            
//            forward = "error";
//            setErrorMessage("话费充值现金缴费失败！");
//            
//            // 记录缴费失败日志
//            this.createRecLog(servNumber, Constants.PAY_BYCASH, "0", "0", "1", "缴费:自助终端缴费失败!");
//        }
//        
//        feeChargeService.updateChargeLog(selfCardPayLogVO);
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("cashChargeCommit end");
//        }
//        
//        return forward;
//    }
//    
//    /**
//     * 现金交费异常处理
//     * 
//     * @return
//     * @see
//     */
//    public String goCashError()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("goCashError Starting ...");
//        }
//        
//        this.createRecLog(servNumber, payType, "0", "0", "1", errorMessage);
//        
//        HttpSession session = getRequest().getSession();
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//        selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
//        selfCardPayLogVO.setRegion(termInfo.getRegion());
//        selfCardPayLogVO.setTermID(termInfo.getTermid());
//        selfCardPayLogVO.setOperID(termInfo.getOperid());
//        selfCardPayLogVO.setServnumber(servNumber);
//        if (Constants.PAY_BYCASH.equals(payType))
//        {
//            selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
//        }
//        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//        if (terminalSeq == null || "".equals(terminalSeq.trim()))
//        {
//            selfCardPayLogVO.setTerminalSeq("");
//        }
//        else
//        {
//            terminalSeq = termInfo.getTermid() + terminalSeq;
//            if (terminalSeq.length() > 20)
//            {
//                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
//            }
//            
//            selfCardPayLogVO.setTerminalSeq(terminalSeq);
//        }
//        selfCardPayLogVO.setRecdate(payDate);
//        selfCardPayLogVO.setAcceptType(acceptType);
//        selfCardPayLogVO.setServRegion(servRegion);
//        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
//        
//        if (tMoney == null || "".equals(tMoney.trim()) || "0".equals(tMoney.trim()))
//        {
//            selfCardPayLogVO.setStatus("00");
//            selfCardPayLogVO.setDescription(errorMessage);
//            selfCardPayLogVO.setDealnum("");
//        }
//        else
//        {
//            selfCardPayLogVO.setStatus("10");
//            selfCardPayLogVO.setDescription(errorMessage);
//            selfCardPayLogVO.setDealnum("");
//        }
//        
//        feeChargeService.addChargeLog(selfCardPayLogVO);
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("goCashError End");
//        }
//        
//        return "cashErrorPage";
//    }
//    
//    /**
//     * 转向银行卡缴费金额选择页面
//     * 
//     * @return
//     */
//    public String cardCharge()
//    {
//        return "otherFee";
//    }
//    
//    /**
//     * 转向读银行卡页面
//     * 
//     * @return
//     */
//    public String toReadCard()
//    {
//        return "toReadCard";
//    }
//    
//    /**
//     * 转向银行卡缴费免责声明页面
//     * 
//     * @return
//     */
//    public String dutyInfo()
//    {
//        return "dutyInfo";
//    }
//    
//    /**
//     * 进入银联卡密码输入页面
//     * 
//     * @return
//     * @see
//     */
//    public String inputCardPwd()
//    {
//        return "inputPwd";
//    }
//    
//    /**
//     * 转向确认银行卡缴费金额页面
//     * 
//     * @return
//     */
//    public String toMakeSure()
//    {
//        return "makeSure";
//    }
//    
//    /**
//     * 扣款之前增加银联卡缴费日志
//     * 
//     * @throws Exception
//     */
//    public void addChargeLog() throws Exception
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("addCardPayLog Starting...");
//        }
//        
//        HttpServletRequest request = this.getRequest();
//        HttpServletResponse response = this.getResponse();
//        
//        response.setContentType("text/xml;charset=GBK");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter writer = null;
//        try
//        {
//            writer = response.getWriter();
//        }
//        catch (IOException e)
//        {
//            throw new IOException("扣款之前记录日志失败");
//        }
//        
//        HttpSession session = request.getSession();
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        String payType = (String)request.getParameter("paytype");
//        String status = (String)request.getParameter("status");
//        String description = (String)request.getParameter("description");
//        description = java.net.URLDecoder.decode(description, "UTF-8");
//        
//        // 组装日志对象
//        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
//        
//        String logOID = feeChargeService.getChargeLogOID();
//        cardChargeLogVO.setChargeLogOID(logOID);
//        
//        cardChargeLogVO.setRegion(termInfo.getRegion());
//        cardChargeLogVO.setTermID(termInfo.getTermid());
//        cardChargeLogVO.setOperID(termInfo.getOperid());
//        cardChargeLogVO.setServnumber(servNumber);
//        cardChargeLogVO.setPayType(payType);
//        cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//        cardChargeLogVO.setUnionpayuser("");
//        cardChargeLogVO.setUnionpaycode("");
//        cardChargeLogVO.setBusiType("");
//        cardChargeLogVO.setCardnumber("");
//        cardChargeLogVO.setBatchnum("");
//        cardChargeLogVO.setAuthorizationcode("");
//        cardChargeLogVO.setBusinessreferencenum("");
//        cardChargeLogVO.setUnionpaytime("");
//        cardChargeLogVO.setVouchernum("");
//        cardChargeLogVO.setUnionpayfee("");
//        cardChargeLogVO.setTerminalSeq("");
//        
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(new Date());
//        cardChargeLogVO.setRecdate(payDate);
//        
//        cardChargeLogVO.setStatus(status);
//        cardChargeLogVO.setDescription(description);
//        cardChargeLogVO.setDealnum("");
//        cardChargeLogVO.setAcceptType(acceptType);
//        cardChargeLogVO.setServRegion(servRegion);
//        cardChargeLogVO.setOrgID(termInfo.getOrgid());
//        cardChargeLogVO.setPosNum("");
//        cardChargeLogVO.setBatchNumBeforeKoukuan("");
//        cardChargeLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
//        
//        String xml = "";
//        try
//        {
//            // 插入缴费日志
//            feeChargeService.addChargeLog(cardChargeLogVO);
//            xml = "1~~" + logOID;
//        }
//        catch (Exception e)
//        {
//            xml = "0";
//            
//            logger.error("扣款之前记录日志异常：", e);
//        }
//        finally
//        {
//            // 输出到client
//            if (writer != null)
//            {
//                writer.print(xml);
//                
//                try
//                {
//                    writer.close();
//                    writer = null;
//                }
//                catch (Exception e)
//                {
//                    logger.error("关闭writer异常：", e);
//                    
//                    throw new Exception("扣款之前记录日志失败");
//                }
//            }
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("addCardPayLog end!");
//        }
//        
//        logger.debug("addCardPayLog End!");
//    }
//    
//    /**
//     * 扣款成功之后，更新交费日志
//     * 
//     * @throws Exception
//     * @see
//     */
//    public void updateCardChargeLog() throws Exception
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("updateCardChargeLog Starting...");
//        }
//        
//        HttpServletRequest request = this.getRequest();
//        HttpServletResponse response = this.getResponse();
//        
//        response.setContentType("text/xml;charset=GBK");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter writer = null;
//        try
//        {
//            writer = response.getWriter();
//        }
//        catch (IOException e)
//        {
//            throw new IOException("扣款之前记录日志失败");
//        }
//        
//        String status = (String)request.getParameter("status");
//        String description = (String)request.getParameter("description");
//        description = java.net.URLDecoder.decode(description, "UTF-8");
//        
//        // 组装日志对象
//        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
//        cardChargeLogVO.setChargeLogOID(chargeLogOID);
//        cardChargeLogVO.setUnionpayuser(unionpayuser);
//        cardChargeLogVO.setUnionpaycode(unionpaycode);
//        
//        busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
//        cardChargeLogVO.setBusiType(busiType);
//        
//        cardChargeLogVO.setCardnumber(cardnumber);
//        cardChargeLogVO.setBatchnum(batchnum);
//        cardChargeLogVO.setAuthorizationcode(authorizationcode);
//        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
//        cardChargeLogVO.setUnionpaytime(unionpaytime);
//        cardChargeLogVO.setVouchernum(vouchernum);
//        
//        if (unionpayfee != null)
//        {
//            while (unionpayfee.startsWith("0"))
//            {
//                unionpayfee = unionpayfee.substring(1);
//            }
//        }
//        else
//        {
//            unionpayfee = "";
//        }
//        cardChargeLogVO.setUnionpayfee(unionpayfee);
//        cardChargeLogVO.setTerminalSeq(terminalSeq);
//        
//        cardChargeLogVO.setStatus(status);
//        cardChargeLogVO.setDescription(description);
//        
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(new Date());
//        cardChargeLogVO.setRecdate(payDate);
//        
//        String xml = "";
//        try
//        {
//            // 插入缴费日志
//            feeChargeService.updateCardChargeLog(cardChargeLogVO);
//            xml = "1";
//        }
//        catch (Exception e)
//        {
//            xml = "0";
//            
//            logger.error("扣款之前记录日志异常：", e);
//        }
//        finally
//        {
//            // 输出到client
//            if (writer != null)
//            {
//                writer.println(xml);
//                
//                try
//                {
//                    writer.close();
//                    writer = null;
//                }
//                catch (Exception e)
//                {
//                    logger.error("关闭writer异常：", e);
//                    
//                    throw new Exception("扣款之前记录日志失败");
//                }
//            }
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("updateCardChargeLog end!");
//        }
//    }
//    
//    /**
//     * 银行卡缴费提交
//     * 
//     * @return
//     */
//    public String cardChargeCommit()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("cardChargeCommit start");
//        }
//        
//        String forward = null;
//        
//        HttpSession session = getRequest().getSession();
//        
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        
//        // 银行卡缴费
//        String bankNo = "220";
//        
//        // 山东接口 -- 发票打印标志，0＝不打印 1＝打印
//        String invoiceType = "1";
//        
//        tMoney = CommonUtil.fenToYuan(tMoney);
//        
//        // begin zKF66389 findbus清零
//        // 银行卡缴费
//        Map resultMap = feeChargeBean.chargeCommit(termInfo,
//                curMenuId,
//                servNumber,
//                bankNo,
//                payDate,
//                terminalSeq,
//                tMoney,
//                acceptType,
//                invoiceType,
//                "",
//                "");
//        // end zKF66389 findbus清零
//        
//        // 更新日志
//        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
//        
//        if (resultMap != null && resultMap.size() > 0)
//        {
//            Object obj = resultMap.get("returnObj");
//            if (obj instanceof Vector)
//            {
//                // 对山东缴费接口的返回值进行解析
//                Vector chargeInfo = (Vector)obj;
//                CTagSet tagSet = (CTagSet)chargeInfo.get(0);
//                CRSet crset = (CRSet)chargeInfo.get(1);
//                
//                dealNum = (String)tagSet.GetValue("bill_nbr");// 受理编号
//                String cycleCount = (String)tagSet.GetValue("bcycle_count");
//                int rowNum = Integer.parseInt(cycleCount);
//                
//                if (rowNum > 1)
//                {
//                    hasMultiInvoices = "true";
//                }
//                else
//                {
//                    hasMultiInvoices = "false";
//                }
//                
//                // 提供发票打印功能
//                String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
//                if ("1".equals(canPrintInvoice))
//                {
//                    List invoicesList = new ArrayList();
//                    Map invoiceMap = null;
//                    
//                    for (int i = 0; i < rowNum; i++)
//                    {
//                        invoiceMap = new HashMap();
//                        
//                        invoiceMap.put("servNumber", servNumber);
//                        invoiceMap.put("dealNum", dealNum);
//                        invoiceMap.put("acceptType", (String)tagSet.GetValue("accept_name"));
//                        invoiceMap.put("acctID", (String)tagSet.GetValue("acct_id"));
//                        invoiceMap.put("subsName", (String)tagSet.GetValue("cust_name"));
//                        invoiceMap.put("payDate", (String)tagSet.GetValue("pay_date"));
//                        invoiceMap.put("bossFormnum", dealNum);
//                        
//                        invoiceMap.put("pOrgName", termInfo.getOrgname());
//                        
//                        invoiceMap.put("feeTime", (String)crset.GetValue(i, 0));
//                        invoiceMap.put("ysFee", (String)crset.GetValue(i, 2));
//                        invoiceMap.put("capitalFee", (String)crset.GetValue(i, 3));
//                        
//                        String consumeList = parseConsumeList((String)crset.GetValue(i, 7),
//                                (String)tagSet.GetValue("comment"));
//                        invoiceMap.put("consumeList", consumeList);
//                        
//                        invoicesList.add(invoiceMap);
//                    }
//                    
//                    this.getRequest().setAttribute("invoiceXml", getXmlStr(invoicesList));
//                }
//            }
//            
//            selfCardPayLogVO.setRecdate(payDate);
//            selfCardPayLogVO.setStatus("11");
//            selfCardPayLogVO.setDescription("话费充值银联卡交费成功！");
//            selfCardPayLogVO.setDealnum(dealNum);
//            
//            forward = "success";
//            
//            // 记录缴费成功日志
//            this.createRecLog(servNumber, Constants.PAY_BYCARD, "0", "0", "0", "缴费:自助终端缴费成功!");
//        }
//        else
//        {
//            selfCardPayLogVO.setRecdate(payDate);
//            selfCardPayLogVO.setStatus("10");
//            selfCardPayLogVO.setDescription("银联卡扣款成功，但是交费失败！");
//            selfCardPayLogVO.setDealnum("");
//            
//            forward = "error";
//            
//            setErrorMessage("银联卡扣款成功，但是交费失败！");
//            
//            // 记录缴费失败日志
//            this.createRecLog(servNumber, Constants.PAY_BYCARD, "0", "0", "1", "缴费:自助终端缴费失败!");
//        }
//        
//        feeChargeService.updateChargeLog(selfCardPayLogVO);
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("cardChargeCommit end");
//        }
//        
//        return forward;
//    }
//    
//    /**
//     * 取消银行卡缴费
//     * 
//     * @return
//     */
//    public String cancelCardCharge()
//    {
//        return "cancelCardCharge";
//    }
//    
//    // /**
//    // * 更新银联卡缴费日志
//    // *
//    // * @throws Exception
//    // */
//    // public void updateChargeLog() throws Exception
//    // {
//    // if (logger.isDebugEnabled())
//    // {
//    // logger.debug("updateChargeLog Starting...");
//    // }
//    //        
//    // HttpServletRequest request = this.getRequest();
//    // HttpServletResponse response = this.getResponse();
//    //        
//    // response.setContentType("text/xml;charset=GBK");
//    // request.setCharacterEncoding("UTF-8");
//    // PrintWriter writer = null;
//    // try
//    // {
//    // writer = response.getWriter();
//    // }
//    // catch (IOException e)
//    // {
//    // throw new IOException("扣款之前记录日志失败");
//    // }
//    //        
//    // HttpSession session = request.getSession();
//    // TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
//    //        
//    // String payType = (String) request.getParameter("paytype");
//    // String status = (String) request.getParameter("status");
//    // String description = (String) request.getParameter("description");
//    // description = java.net.URLDecoder.decode(description, "GBK");
//    // String posNum = (String) request.getParameter("posnum");
//    // String batchNumBeforeKoukuan = (String) request.getParameter("batchnumbeforekoukuan");
//    //        
//    // // 组装日志对象
//    // CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
//    //        
//    // String logOID = feeChargeService.getChargeLogOID();
//    // cardChargeLogVO.setChargeLogOID(logOID);
//    //        
//    // cardChargeLogVO.setRegion(termInfo.getRegion());
//    // cardChargeLogVO.setTermID(termInfo.getTermid());
//    // cardChargeLogVO.setOperID(termInfo.getOperid());
//    // cardChargeLogVO.setServnumber(servnumber);
//    // cardChargeLogVO.setPayType(payType);
//    // cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//    // cardChargeLogVO.setUnionpayuser("");
//    // cardChargeLogVO.setUnionpaycode("");
//    // cardChargeLogVO.setBusiType("");
//    // cardChargeLogVO.setCardnumber(cardnumber);
//    // cardChargeLogVO.setBatchnum("");
//    // cardChargeLogVO.setAuthorizationcode("");
//    // cardChargeLogVO.setBusinessreferencenum("");
//    // cardChargeLogVO.setUnionpaytime("");
//    // cardChargeLogVO.setVouchernum("");
//    // cardChargeLogVO.setUnionpayfee("");
//    // cardChargeLogVO.setTerminalSeq("");
//    //        
//    // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//    // String payDate = sdf1.format(new Date());
//    // cardChargeLogVO.setRecdate(payDate);
//    //
//    // cardChargeLogVO.setStatus(status);
//    // cardChargeLogVO.setDescription(description);
//    // cardChargeLogVO.setDealnum("");
//    // cardChargeLogVO.setAcceptType(acceptType);
//    // cardChargeLogVO.setServRegion(servRegion);
//    // cardChargeLogVO.setOrgID(termInfo.getOrgid());
//    // cardChargeLogVO.setPosNum(posNum);
//    // cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
//    //        
//    // String xml = "";
//    // try
//    // {
//    // // 插入缴费日志
//    // feeChargeService.addChargeLog(cardChargeLogVO);
//    // xml = "1~~" + logOID;
//    // }
//    // catch (Exception e)
//    // {
//    // xml = "0";
//    //            
//    // logger.error("扣款之前记录日志异常：", e);
//    // }
//    // finally
//    // {
//    // // 输出到client
//    // if (writer != null)
//    // {
//    // writer.println(xml);
//    //                
//    // try
//    // {
//    // writer.close();
//    // writer = null;
//    // }
//    // catch (Exception e)
//    // {
//    // logger.error("关闭writer异常：", e);
//    //                    
//    // throw new Exception("扣款之前记录日志失败");
//    // }
//    // }
//    // }
//    //        
//    // if (logger.isDebugEnabled())
//    // {
//    // logger.debug("addCardPayLog end!");
//    // }
//    //        
//    // logger.debug("updateChargeLog End!");
//    // }
//    
//    // /**
//    // * 扣款失败，更新缴费日志
//    // *
//    // * @return
//    // * @throws Exception
//    // * @see
//    // */
//    // public String failToDeduct() throws Exception
//    // {
//    // //发起缴费请求之前首先记录投币日志
//    // CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//    //        
//    // selfCardPayLogVO.setChargeLogOID(chargeLogOID);
//    // selfCardPayLogVO.setStatus("00");
//    // selfCardPayLogVO.setDescription("银联扣款失败。" + errormessage);
//    // selfCardPayLogVO.setDealnum("");
//    //        
//    // feeChargeService.updateChargeLog(selfCardPayLogVO);
//    //        
//    // return "failed";
//    // }
//    
//    // /**
//    // * 缴费之前向SH_LOG_CASHRETURN表中增加一条数据
//    // * @param request
//    // * @param response
//    // * @throws Exception
//    // */
//    // public void addToubiLog() throws Exception
//    // {
//    // if (logger.isDebugEnabled())
//    // {
//    // logger.debug("addToubiLog Starting ...");
//    // }
//    //        
//    // HttpServletRequest request = this.getRequest();
//    // HttpServletResponse response = this.getResponse();
//    //        
//    // String result = "";
//    //        
//    // response.setContentType("text/xml;charset=GBK");
//    // request.setCharacterEncoding("UTF-8");
//    // PrintWriter writer = null;
//    // try
//    // {
//    // writer = response.getWriter();
//    //            
//    // HttpSession session = getRequest().getSession();
//    //            
//    // TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
//    //            
//    // CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//    //            
//    // String logOID = feeChargeService.getChargeLogOID();
//    // selfCardPayLogVO.setChargeLogOID(logOID);
//    //            
//    // selfCardPayLogVO.setRegion(termInfo.getRegion());
//    // selfCardPayLogVO.setTermID(termInfo.getTermid());
//    // selfCardPayLogVO.setOperID(termInfo.getOperid());
//    // selfCardPayLogVO.setServnumber((String) request.getParameter("servnumber"));
//    // selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);//现金投币日志
//    // selfCardPayLogVO.setFee(CommonUtil.yuanToFen((String) request.getParameter("tMoney")));
//    //            
//    // String sequence = (String) request.getParameter("terminalSeq");
//    // sequence = termInfo.getTermid() + sequence;
//    // if (sequence.length() > 20)
//    // {
//    // sequence = sequence.substring(sequence.length() - 20);
//    // }
//    // selfCardPayLogVO.setTerminalSeq(sequence);
//    //            
//    // Date date = new Date();
//    // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//    // String payDate = sdf1.format(date);
//    // selfCardPayLogVO.setRecdate(payDate);
//    //            
//    // selfCardPayLogVO.setAcceptType("");
//    // selfCardPayLogVO.setServRegion((String) request.getParameter("servRegion"));
//    // selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//    // selfCardPayLogVO.setStatus("01");
//    // selfCardPayLogVO.setDescription("缴费之前记录投币日志");
//    // selfCardPayLogVO.setDealnum("");
//    //            
//    // feeChargeService.addChargeLog(selfCardPayLogVO);
//    //            
//    // result = "1~~" + logOID + "~~" + sequence;
//    // }
//    // catch (IOException e)
//    // {
//    // result = "0~~";
//    //            
//    // logger.error("记录投币日志异常：", e);
//    // }
//    // catch (Exception e)
//    // {
//    // result = "0~~";
//    //            
//    // logger.error("记录投币日志异常：", e);
//    // }
//    // finally
//    // {
//    // // 输出到client
//    // if (writer != null)
//    // {
//    // writer.println(result);
//    //                
//    // try
//    // {
//    // writer.close();
//    // writer = null;
//    // }
//    // catch (Exception e)
//    // {
//    // logger.error("关闭writer异常：", e);
//    // }
//    // }
//    // }
//    //        
//    // if (logger.isDebugEnabled())
//    // {
//    // logger.debug("addToubiLog End");
//    // }
//    // }
//    
//    /**
//     * 银联卡交费异常处理
//     * 
//     * @return
//     * @see
//     */
//    public String goCardError()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("goCardError Starting ...");
//        }
//        
//        this.createRecLog(servNumber, payType, "0", "0", "1", errorMessage);
//        
//        HttpSession session = getRequest().getSession();
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//        
//        if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
//        {
//            selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
//            selfCardPayLogVO.setRegion(termInfo.getRegion());
//            selfCardPayLogVO.setTermID(termInfo.getTermid());
//            selfCardPayLogVO.setOperID(termInfo.getOperid());
//            selfCardPayLogVO.setServnumber(servNumber);
//            if (Constants.PAY_BYCASH.equals(payType))
//            {
//                selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
//            }
//            else if (Constants.PAY_BYCARD.equals(payType))
//            {
//                selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
//            }
//            
//            if (tMoney == null || "".equals(tMoney.trim()))
//            {
//                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
//            }
//            else
//            {
//                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//            }
//            
//            selfCardPayLogVO.setTerminalSeq("");
//            selfCardPayLogVO.setRecdate(payDate);
//            selfCardPayLogVO.setStatus("00");
//            selfCardPayLogVO.setDescription(errorMessage);
//            selfCardPayLogVO.setDealnum("");
//            selfCardPayLogVO.setAcceptType(acceptType);
//            selfCardPayLogVO.setServRegion(servRegion);
//            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//            selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
//            
//            feeChargeService.addChargeLog(selfCardPayLogVO);
//        }
//        // 更新日志
//        else
//        {
//            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
//            
//            if (tMoney == null || "".equals(tMoney.trim()))
//            {
//                selfCardPayLogVO.setStatus("00");
//            }
//            else
//            {
//                selfCardPayLogVO.setStatus("10");
//            }
//            selfCardPayLogVO.setDescription(errorMessage);
//            selfCardPayLogVO.setDealnum("");
//            
//            feeChargeService.updateChargeLog(selfCardPayLogVO);
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("goCardError End");
//        }
//        
//        return "cardErrorPage";
//    }
//    
//    /**
//     * 记录发票打印日志
//     * 
//     * @see
//     */
//    public void insertInvoiceLog()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("insertInvoiceLog Starting ...");
//        }
//        
//        HttpServletRequest request = this.getRequest();
//        
//        String servNumber = request.getParameter("servnumber");
//        String cycle = request.getParameter("cycle");
//        
//        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
//        
//        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
//        
//        InvoicePrintRecord record = new InvoicePrintRecord();
//        record.setServNumber(servNumber);
//        record.setCycle(cycle);
//        record.setTermID(termInfo.getTermid());
//        
//        feeChargeService.insertInvoiceLog(record);
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("insertInvoiceLog End");
//        }
//    }
//    
//    /**
//     * 消费明细、发票注释解析
//     * 
//     * @param consumeList 消费明细
//     * @param comments 发票注释
//     * @return
//     */
//    private String parseConsumeList(String consumeList, String comments)
//    {
//        String[] consumeArr = consumeList.replace("|", ",").split(";");
//        StringBuffer tmpConsumeList = new StringBuffer();
//        int rowNum = 13;
//        int row = 0;
//        for (int i = 0; i < consumeArr.length && row < rowNum; i++)
//        {
//            if (consumeArr[i].trim().length() <= 3)
//            {
//                continue;
//            }
//            
//            String tmpArr[] = consumeArr[i].split(",");
//            if (!CommonUtil.isEmpty(tmpArr[0]))
//            {
//                tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[0].trim(), 15));
//                
//                if (i % 2 == 0)
//                {
//                    if (tmpArr.length > 1)
//                    {
//                        tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[1].trim(), 8));
//                    }
//                    else
//                    {
//                        tmpConsumeList.append(CommonUtil.fillRightBlank("", 8));
//                    }
//                }
//                else
//                {
//                    if (tmpArr.length > 1)
//                    {
//                        tmpConsumeList.append(tmpArr[1].trim()).append("\\n");
//                    }
//                    else
//                    {
//                        tmpConsumeList.append("\\n");
//                    }
//                }
//                
//                if (i % 2 == 0)
//                {
//                    row++;
//                }
//            }
//        }
//        
//        if (consumeArr.length % 2 != 0)
//        {
//            tmpConsumeList.append("\\n");
//        }
//        
//        if (comments != null && !"".equals(comments.trim()))
//        {
//            tmpConsumeList.append(comments.trim()).append("\\n");
//            
//            if (comments.trim().length() % 26 == 0)
//            {
//                row += (comments.trim().length() / 26);
//            }
//            else
//            {
//                row = row + (comments.trim().length() / 26) + 1;
//            }
//        }
//        
//        while (row < rowNum)
//        {
//            tmpConsumeList.append("\\n");
//            row++;
//        }
//        
//        return tmpConsumeList.toString();
//    }
//    
//    /**
//     * 将发票数据组织成xml
//     * 
//     * @param list 发票数据
//     * @return
//     * @see
//     */
//    private String getXmlStr(List list)
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("getXmlStr Starting...");
//        }
//        
//        StringBuffer xmlStr = new StringBuffer(1024);
//        
//        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
//        
//        Iterator it = null;
//        for (int i = 0; i < list.size(); i++)
//        {
//            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
//            
//            it = ((HashMap)list.get(i)).entrySet().iterator();
//            while (it.hasNext())
//            {
//                Map.Entry entry = (Map.Entry)it.next();
//                String subItemKey = (String)entry.getKey();
//                String subItemValue = (String)entry.getValue();
//                
//                xmlStr.append("<")
//                        .append(subItemKey)
//                        .append("><![CDATA[")
//                        .append(subItemValue)
//                        .append("]]></")
//                        .append(subItemKey)
//                        .append(">");
//            }
//            
//            xmlStr.append("</entry>");
//        }
//        
//        xmlStr.append("</root></xml>");
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("getXmlStr End!");
//        }
//        
//        return xmlStr.toString();
//    }
//    
//    /**
//     * 湖北打印发票需要短信随机码认证
//     * 
//     * @return
//     * @see
//     */
//    public String validateByRandomPwd()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("validateByRandomPwd Starting ...");
//        }
//        
//        String forward = "error";
//        
//        HttpServletRequest request = this.getRequest();
//        HttpSession session = request.getSession();
//        
//        String invoiceXML = (String)request.getParameter("invoiceXML");
//        String invoiceType = (String)request.getParameter("invoiceType");
//        
//        request.setAttribute("invoiceXML", invoiceXML);
//        request.setAttribute("invoiceType", invoiceType);
//        
//        // 终端机信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 随机密码长度
//        int len = 0;
//        String strLen = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_LEN);
//        if (strLen == null || "".equals(strLen.trim()))
//        {
//            len = 6;
//        }
//        else
//        {
//            len = Integer.parseInt(strLen);
//        }
//        
//        // 生成随机密码
//        String randomPwd = createRandomPassword(servNumber, CommonUtil.getCurrentTime());
//        
//        // 发送随机密码短信
//        String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD_CONTENT");
//        shortMessage = shortMessage.replace("[%1]", randomPwd);
//        
//        // begin zKF66389 findbus清零
//        if (!feeChargeBean.sendRandomPwd(servNumber, termInfo, shortMessage, curMenuId))
//        // end zKF66389 findbus清零
//        {
//            logger.error("向用户" + servNumber + "发送随机密码短信失败");
//            
//            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", "发票打印功能，随机密码短信发送失败。");
//            
//            this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，不能打印发票。");
//        }
//        else
//        {
//            if (logger.isInfoEnabled())
//            {
//                logger.info("向用户" + servNumber + "发送随机密码短信成功，随机码" + randomPwd);
//            }
//            
//            forward = "success";
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("validateByRandomPwd End");
//        }
//        
//        return forward;
//    }
//    
//    /**
//     * 随机密码验证，验证通过后，打印发票
//     * 
//     * @return
//     * @see
//     */
//    public String printInvoice()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("printInvoice Starting ...");
//        }
//        
//        String forward = "";
//        
//        HttpServletRequest request = this.getRequest();
//        
//        String randomPwd = (String)request.getParameter("randomPwd");
//        String invoiceXML = (String)request.getParameter("invoiceXML");
//        String invoiceType = (String)request.getParameter("invoiceType");
//        
//        request.setAttribute("invoiceXML", invoiceXML);
//        request.setAttribute("invoiceType", invoiceType);
//        
//        String result = this.loginWithRandomPwd(servNumber, randomPwd);
//        if ("1".equals(result))
//        {
//            forward = "success";
//            
//            if (logger.isInfoEnabled())
//            {
//                logger.info("发票打印功能，用户" + servNumber + "使用随机密码进行认证成功");
//            }
//        }
//        else
//        {
//            forward = "error";
//            
//            String errorMsg = "";
//            
//            if ("-1".equals(result))
//            {
//                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
//            }
//            else
//            {
//                errorMsg = "随机密码输入错误，请重新输入。";
//            }
//            
//            logger.error("发票打印功能，用户" + servNumber + "输入的随机密码错误或超时，认证失败");
//            
//            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
//            
//            this.getRequest().setAttribute("errormessage", errorMsg);
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("loginWithRandomPwd End");
//        }
//        
//        return forward;
//    }
//    
//    /**
//     * 无需随机密码验证，直接打印发票
//     * 
//     * @return
//     * @see
//     */
//    public String printInvoiceWithoutPwd()
//    {
//        HttpServletRequest request = this.getRequest();
//        
//        String invoiceXML= (String) request.getParameter("invoiceXML");
//        String invoiceType = (String) request.getParameter("invoiceType");
//        
//        request.setAttribute("invoiceXML", invoiceXML);
//        request.setAttribute("invoiceType", invoiceType);
//        
//        return "print";
//    }
//    
//    /**
//     * 不校验密码，直接获取用户信息
//     * 
//     * @return
//     */
//    public String getUserStatus()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("getUserStatus Starting ...");
//        }
//        
//        HttpServletRequest request = this.getRequest();
//        
//        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
//        
//        HashMap map = (HashMap) feeChargeBean.getUserStatus(servNumber, "", termInfo);
//        
//        if (map == null)
//        {
//            logger.error("缴费前验证号码" + servNumber + "的状态失败");
//            
//            request.setAttribute("errormessage", "验证号码状态失败");
//            
//            this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "缴费前验证号码状态失败");
//            
//            if (logger.isDebugEnabled())
//            {
//                logger.debug("getUserStatus End!");
//            }
//            
//            return "验证号码状态失败";
//        }
//        
//        String status = (String) map.get("status");
//        
//        Pattern p = Pattern.compile("US2[A-Z0-9]*");
//        Matcher m = p.matcher(status);
//        if (m.matches() && !"US28".equals(status))
//        {
//            logger.error("号码" + servNumber + "已销户，不能缴费");
//            
//            request.setAttribute("errormessage", "号码" + servNumber + "已销户，不能缴费");
//            
//            this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "状态：" + status + "，已销户，不能缴费");
//            
//            if (logger.isDebugEnabled())
//            {
//                logger.debug("getUserStatus End!");
//            }
//            
//            return "号码" + servNumber + "已销户，不能缴费";
//        }
//        
//        String region = (String) map.get("servRegion");
//        if (!region.equals(termInfo.getRegion()))
//        {
//        	logger.error("号码" + servNumber + "是异地用户，不能缴费");
//            
//            request.setAttribute("errormessage", "号码" + servNumber + "是异地用户，不能缴费");
//            
//            this.createRecLog(servNumber, "feeCharge", "0", "0", "0", "号码" + servNumber + "是异地用户，不能缴费");
//            
//            if (logger.isDebugEnabled())
//            {
//                logger.debug("getUserStatus End!");
//            }
//            
//            return "号码" + servNumber + "是异地用户，不能缴费";	
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("getUserStatus End!");
//        }
//        
//        return "";
//    }
//    
//    public String getNeedReturnCard()
//    {
//        return needReturnCard;
//    }
//    
//    public void setNeedReturnCard(String needReturnCard)
//    {
//        this.needReturnCard = needReturnCard;
//    }
//    
//    public String getErrorType()
//    {
//        return errorType;
//    }
//    
//    public void setErrorType(String errorType)
//    {
//        this.errorType = errorType;
//    }
//    
//    public List getUsableTypes()
//    {
//        return usableTypes;
//    }
//    
//    public void setUsableTypes(List usableTypes)
//    {
//        this.usableTypes = usableTypes;
//    }
//    
//    public boolean isCanPayWithCash()
//    {
//        return canPayWithCash;
//    }
//    
//    public void setCanPayWithCash(boolean canPayWithCash)
//    {
//        this.canPayWithCash = canPayWithCash;
//    }
//    
//    public String getHasMultiInvoices()
//    {
//        return hasMultiInvoices;
//    }
//    
//    public void setHasMultiInvoices(String hasMultiInvoices)
//    {
//        this.hasMultiInvoices = hasMultiInvoices;
//    }
//    
//    public String getDealNum()
//    {
//        return dealNum;
//    }
//    
//    public void setDealNum(String dealNum)
//    {
//        this.dealNum = dealNum;
//    }
//    
//    // begin zKF66389 findbus清零    
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus清零
//    
//    public FeeChargeBean getFeeChargeBean()
//    {
//        return feeChargeBean;
//    }
//    
//    public void setFeeChargeBean(FeeChargeBean feeChargeBean)
//    {
//        this.feeChargeBean = feeChargeBean;
//    }
//    
//    public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//	public String getTMoney()
//    {
//        return tMoney;
//    }
//    
//    public void setTMoney(String money)
//    {
//        tMoney = money;
//    }
//    
//    public String getTerminalSeq()
//    {
//        return terminalSeq;
//    }
//    
//    public void setTerminalSeq(String terminalSeq)
//    {
//        this.terminalSeq = terminalSeq;
//    }
//    
//    public String getBalance()
//    {
//        return balance;
//    }
//    
//    public void setBalance(String balance)
//    {
//        this.balance = balance;
//    }
//    
//    public String getCardnumber()
//    {
//        return cardnumber;
//    }
//    
//    public void setCardnumber(String cardnumber)
//    {
//        this.cardnumber = cardnumber;
//    }
//    
//    public String getUnionpaycode()
//    {
//        return unionpaycode;
//    }
//    
//    public void setUnionpaycode(String unionpaycode)
//    {
//        this.unionpaycode = unionpaycode;
//    }
//    
//    public String getUnionpayuser()
//    {
//        return unionpayuser;
//    }
//    
//    public void setUnionpayuser(String unionpayuser)
//    {
//        this.unionpayuser = unionpayuser;
//    }
//    
//    public String getBusiType() {
//		return busiType;
//	}
//
//	public void setBusiType(String busiType) {
//		this.busiType = busiType;
//	}
//
//	public String getBatchnum()
//    {
//        return batchnum;
//    }
//    
//    public void setBatchnum(String batchnum)
//    {
//        this.batchnum = batchnum;
//    }
//    
//    public String getUnionpayfee()
//    {
//        return unionpayfee;
//    }
//    
//    public void setUnionpayfee(String unionpayfee)
//    {
//        this.unionpayfee = unionpayfee;
//    }
//    
//    public String getUnionpaytime()
//    {
//        return unionpaytime;
//    }
//    
//    public void setUnionpaytime(String unionpaytime)
//    {
//        this.unionpaytime = unionpaytime;
//    }
//    
//    public String getAuthorizationcode()
//    {
//        return authorizationcode;
//    }
//    
//    public void setAuthorizationcode(String authorizationcode)
//    {
//        this.authorizationcode = authorizationcode;
//    }
//    
//    public String getBusinessreferencenum()
//    {
//        return businessreferencenum;
//    }
//    
//    public void setBusinessreferencenum(String businessreferencenum)
//    {
//        this.businessreferencenum = businessreferencenum;
//    }
//    
//    public String getVouchernum()
//    {
//        return vouchernum;
//    }
//    
//    public void setVouchernum(String vouchernum)
//    {
//        this.vouchernum = vouchernum;
//    }
//    
//    public FeeChargeService getFeeChargeService()
//    {
//        return feeChargeService;
//    }
//    
//    public void setFeeChargeService(FeeChargeService feeChargeService)
//    {
//        this.feeChargeService = feeChargeService;
//    }
//    
//    public String getYingjiaoFee()
//    {
//        return yingjiaoFee;
//    }
//    
//    public void setYingjiaoFee(String yingjiaoFee)
//    {
//        this.yingjiaoFee = yingjiaoFee;
//    }
//    
//    public String getServRegion()
//    {
//        return servRegion;
//    }
//    
//    public void setServRegion(String servRegion)
//    {
//        this.servRegion = servRegion;
//    }
//
//	public String getAcceptType() {
//		return acceptType;
//	}
//
//	public void setAcceptType(String acceptType) {
//		this.acceptType = acceptType;
//	}
//
//	public String getPayType()
//    {
//        return payType;
//    }
//    
//    public void setPayType(String payType)
//    {
//        this.payType = payType;
//    }
//    
//    public String getChargeLogOID()
//    {
//        return chargeLogOID;
//    }
//    
//    public void setChargeLogOID(String chargeLogOID)
//    {
//        this.chargeLogOID = chargeLogOID;
//    }
//
//	public String getServNumber() {
//		return servNumber;
//	}
//
//	public void setServNumber(String servNumber) {
//		this.servNumber = servNumber;
//	}
    
}