package com.customize.hub.selfsvc.broadBandPay.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.BroadBandPayBean;
import com.customize.hub.selfsvc.broadBandPay.model.WBandVO;
import com.customize.hub.selfsvc.broadBandPay.service.BroadBandPayService;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 宽带交费
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Sep 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BroadBandPayAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    private static Log logger = LogFactory.getLog(BroadBandPayAction.class);
    
    // 当前菜单
    private String curMenuId = "";
    
    // 用户手机号
    private String servnumber;
    
    // 交费金额 
    private String tMoney;
    
    // 终端机流水号
    private String terminalSeq;
    
    // 错误信息
    private String errormessage;
    
    // 调用接口Bean
    private transient BroadBandPayBean broadBandPayBean;
    
    private UserLoginBean userLoginBean;
    
    private String servRegion = "";
    
    // 应缴费用
    private String yingjiaoFee;
    
    // 话费余额
    private String balance;
    
    // 用户刷卡卡号
    private String cardnumber;
    
    // 银联为刷卡的终端分配的唯一编号
    private String unionpaycode;
    
    // 银联商户号（授卡方标识）
    private String unionpayuser;
    
    // 业务类型
    //modify by sWX219697 2015-7-18 由busitype改为busiType，findbugs修改
    private String busiType;
    
    // 终端批次号
    private String batchnum;
    
    // 银联实际扣款金额，单位（分）
    private String unionpayfee;
    
    // 银联实际扣款时间
    private String unionpaytime;
    
    // 授权码（山东用）
    private String authorizationcode;
    
    // 交易参考号（山东用）
    private String businessreferencenum;
    
    // 评证号（山东用）
    private String vouchernum;
    
    // 调用数据库
    private transient BroadBandPayService broadBandPayService;
    
    // BOSS流水
    private String dealNum = "";
    
    // 交易时间
    private String dealTime = "";
    
    private String hasMultiInvoices = "true";
    
    private String payType;
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
    // 缴费日志对应的oid
    private String chargeLogOID = "";
    
    private String errorType = "";
    
    private String needReturnCard = "";
    
    private String needRandomPwd = "";// 取BOSS系统参数_是否发送短信验证码 1:发送 0:不发送
    
    private String printcontext = "";// 银联打印信息
    
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    
    /**
     * 宽带产品_单页记录
     */
    private List<WBandVO> wbankList = new ArrayList<WBandVO>();
    
    /**
     * 宽带产品_所有记录
     */
    private List<WBandVO> wbankAllList = new ArrayList<WBandVO>();
    
    /**
     * 宽带产品NCODE
     */
    private String ncode;
    
    /**
     * 宽带产品名称
     */
    private String prodName;
    
    /**
     * 宽带产品描述
     */
    private String detailDesc;
    
    /**
     * 宽带产品费用
     */
    private String fee;

    /**
     * 话费充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
     * 
     * @return
     */
    public String inputNumber()
    {
        this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
        return "inputNumber";
    }
    
    /**
     * 查询可以办理的宽带产品
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryWBankList()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryfeeChargeAccount start");
        }
        
        // 如果账户信息查询失败转到缴费登录页面
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        Map result = userLoginBean.getUserInfo(servnumber, termInfo);
        
        if (result != null && result.get("customerSimp") != null)
        {
            NserCustomerSimp customerSimp = (NserCustomerSimp) result.get("customerSimp");
            
            // 网络类型
            if (!("WBAND".equals(customerSimp.getNetType()) || "独立宽带".equals(customerSimp.getNetType())))
            {
                setErrormessage("该功能不支持非宽带用户缴费，如需缴纳手机话费，请使用充值缴费功能。");
                
                return "failed";
            }
            
            // ----------------------------当前用户信息-------------------------------------------------
            
            // 区域
            servRegion = customerSimp.getRegionID();
            
            // 放入SESSION
            request.getSession().setAttribute(Constants.USER_INFO, customerSimp);
            
            // ----------------------------查询宽带产品-------------------------------------------------
            
            wbankAllList = broadBandPayService.qryWBankList(customerSimp.getProductID());
            
            this.displayPage();
            
            forward = "success";
            
            // 记录成功日志
            this.createRecLog(servnumber, "wbandPay", "0", "0", "0", "宽带缴费时查询账号信息成功");
        }
        //else if (null != result && null != result.get("returnMsp") && !"".equals(result.get("returnMsp")))
        else if (null != result.get("returnMsp") && !"".equals(result.get("returnMsp")))
        {
            String resultMsg = (String)result.get("returnMsp");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_10_01_000001", String.valueOf(result.get("errcode")), null);
            
            this.createRecLog("wbandPay", "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
        }
        else
        {
            String resultMsg = "宽带缴费时查询账号信息失败！";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_10_01_000001", "", null);
            
            this.createRecLog("wbandPay", "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
        }
        /**
        else
        {
            logger.debug("宽带缴费时查询账号信息失败！");
            
            if (result != null && result.get("returnMsg") != null)
            {
                this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            }
            else
            {
                this.getRequest().setAttribute("errormessage", "宽带缴费时查询账号信息失败");
            }
            
            // 记录错误日志
            this.createRecLog(servnumber, "wbandPay", "0", "0", "0", "宽带缴费时查询账号信息失败");
        }
        **/
        
        // 返回
        return forward;
    }
    
    /**
     * 支付方式
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryPayType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryfeeChargeAccount start");
        }
        
        // 如果账户信息查询失败转到缴费登录页面
        String forward = "inputNumber";
        
        HttpServletRequest request = this.getRequest();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
            
                
            // ----------------------------充值方式-------------------------------------------------
            
            // 根据终端组自缓存中获取菜单列表
            String groupID = termInfo.getTermgrpid();
            
            List<MenuInfoPO> menus = null;
            
            if (groupID != null && !"".equals(groupID))
            {
                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
            }
            
            // 当页菜单列表
            usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
            
            // findbugs修改 2015-09-02 zKF66389
//            if (logger.isInfoEnabled())
//            {
//                logger.info("当前，宽带缴费的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//            }
            // findbugs修改 2015-09-02 zKF66389
            
            if (usableTypes == null || usableTypes.size() == 0)
            {
                // 没有可用的充值方式
                setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
                
                // 记录日志
                this.createRecLog(servnumber, "wbandPay", "0", "0", "1", "暂时没有可用的充值方式");
                
                forward = "failed";
            }
            
            forward = "success";
            
            // 记录成功日志
            this.createRecLog(servnumber, "wbandPay", "0", "0", "0", "缴费时查询受理类型成功");
            
        }
        catch (Exception e)
        {
            logger.error("查询受理类型失败！");
            setErrormessage("查询受理类型出现异常，请联系系统管理员。给您带来的不便，敬请原谅。");
            
            // 记录异常日志
            this.createRecLog(servnumber, "wbandPay", "0", "0", "0", "缴费时查询受理类型失败");
        }
        
        // 返回
        return forward;
    }
    
    /**
     * 转向投币页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashbroadBand()
    {
        return "cashChargePage";
    }
    
    /**
     * 话费充值执行现金缴费
     * 
     * @return
     */
    public String cashChargeCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cashChargeCommit start");
        }
        
        String forward = null;
        
        // 设定缴费方式
        this.payType = Constants.PAY_BYCASH;
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 登录用户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 校验重复缴费
        if (!checkCashFee(termInfo))
        {
            forward = "repeatError";
            return forward;
        }
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 生成投币日志
        String logOID = broadBandPayService.getChargeLogOID();
        
        // 组装数据
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        // 终端流水(终端id+现金缴费流水 并取后20位)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");// 受理类型 缴费成功后返回
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("缴费之前记录投币日志");
        selfCardPayLogVO.setDealnum(""); // boss缴费流水 缴费成功后返回
        selfCardPayLogVO.setRecType("wband");// 业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值 activity：活动受理 wband: 宽带缴费）
        
        broadBandPayService.addChargeLog(selfCardPayLogVO);
        
        // 比对实缴金额与应缴金额
        if (Integer.parseInt(fee) != Integer.parseInt(tMoney))
        {
            logger.info("实缴金额与应缴金额不一致！");
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("宽带(现金)缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            setErrormessage("宽带缴费失败，实缴金额与应缴金额不一致，请持小票到营业前台取钱！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber,
                    Constants.PAY_BYCASH,
                    logOID,
                    CommonUtil.yuanToFen(tMoney),
                    "1",
                    "宽带缴费:自助终端缴费失败!");
            
            broadBandPayService.updateChargeLog(selfCardPayLogVO);
            
            // 返回
            return forward;
        }
        
        // 调用接口执行现金缴费
        ReturnWrap rw = broadBandPayBean.wbandpay(termInfo, customer, curMenuId, ncode, CommonUtil.yuanToFen(tMoney));
        
        // 缴费成功
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 返回值进行解析
            CTagSet ctagSet = new CTagSet();
            
            //modify begin sWX219697 2014-9-23 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
            if (rw.getReturnObject() instanceof CTagSet)
            {
            	ctagSet = (CTagSet)rw.getReturnObject();
            	
            }
            else
            {
                Vector vector = (Vector)rw.getReturnObject();
                
                if(vector.size() > 0)
                {
                    // 设置返回结果
                    ctagSet = (CTagSet)vector.get(0);
                }
            }
            //modify end sWX219697 2014-9-23 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
            
            dealNum = (String)ctagSet.GetValue("dealNum");// 交易流水

            dealTime = payDate;// 交易时间
            
            selfCardPayLogVO.setRecdate(dealTime);
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("宽带(现金)缴费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
            forward = "success";
            
            // 记录缴费成功日志
            this.createRecLog(servnumber,
                    Constants.PAY_BYCASH,
                    logOID,
                    CommonUtil.yuanToFen(tMoney),
                    "0",
                    "宽带缴费:自助终端缴费成功!");
        }
        // 缴费失败
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("宽带(现金)缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            setErrormessage("宽带(现金)缴费失败！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber,
                    Constants.PAY_BYCASH,
                    logOID,
                    CommonUtil.yuanToFen(tMoney),
                    "1",
                    "宽带缴费:自助终端缴费失败!");
        }
        
        broadBandPayService.updateChargeLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cashChargeCommit end");
        }
        
        return forward;
    }
    
    /**
     * 现金交费异常处理
     * 
     * @return
     * @see
     */
    public String goCashError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError Starting ...");
        }
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(broadBandPayService.getChargeLogOID());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        if (Constants.PAY_BYCASH.equals(payType))
        {
            selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
        }
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("wband");// 业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值 activity：活动受理     wband: 宽带缴费）
        
        if (tMoney == null || "".equals(tMoney.trim()) || "0".equals(tMoney.trim()))
        {
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        else
        {
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        
        broadBandPayService.addChargeLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    /**
     * 转向银行卡缴费免责声明页面
     * 
     * @return
     */
    public String cardbroadBand()
    {
        return "dutyInfo";
    }
    
    /**
     * 转向读银行卡页面
     * 
     * @return
     */
    public String toReadCard()
    {
        return "toReadCard";
    }
    
    /**
     * 进入银联卡密码输入页面
     * 
     * @return
     * @see
     */
    public String inputCardPwd()
    {
        return "inputPwd";
    }
    
    /**
     * 转向确认银行卡缴费金额页面
     * 
     * @return
     */
    public String toMakeSure()
    {
        return "makeSure";
    }
    
    /**
     * 扣款之前增加银联卡缴费日志
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        String xml = "";
        
        try
        {
            writer = response.getWriter();
            
            HttpSession session = request.getSession();
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            String payType = (String)request.getParameter("paytype");
            String status = (String)request.getParameter("status");
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            String posNum = (String)request.getParameter("posnum");
            String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
                        
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            String logOID = broadBandPayService.getChargeLogOID();
            cardChargeLogVO.setChargeLogOID(logOID);
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setTermID(termInfo.getTermid());
            cardChargeLogVO.setOperID(termInfo.getOperid());            
            cardChargeLogVO.setServnumber(servnumber);
            cardChargeLogVO.setPayType(payType);
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(fee));
            cardChargeLogVO.setUnionpayuser("");
            cardChargeLogVO.setUnionpaycode("");
            cardChargeLogVO.setBusiType("");
            cardChargeLogVO.setCardnumber(cardnumber);
            cardChargeLogVO.setBatchnum("");
            cardChargeLogVO.setAuthorizationcode("");
            cardChargeLogVO.setBusinessreferencenum("");
            cardChargeLogVO.setUnionpaytime("");
            cardChargeLogVO.setVouchernum("");
            cardChargeLogVO.setUnionpayfee("");
            cardChargeLogVO.setTerminalSeq(terminalSeq);
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            
            cardChargeLogVO.setStatus(status);
            cardChargeLogVO.setDescription(description);
            cardChargeLogVO.setDealnum("");
            cardChargeLogVO.setAcceptType("");
            cardChargeLogVO.setServRegion(servRegion);
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setPosNum(posNum);
            cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
            
            cardChargeLogVO.setRecType("wband");// 业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值 activity：活动受理     wband: 宽带缴费）

            
            // 插入缴费日志
            broadBandPayService.addChargeLog(cardChargeLogVO);
            
            
            xml = "1~~" + logOID;
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog end!");
        }
        
        logger.debug("addCardPayLog End!");
    }
    
    /**
     * 扣款成功之后，更新交费日志
     * 
     * @throws Exception
     * @see
     */
    public void updateCardChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        // Chagne by LiFeng 解决异常不能退卡问题 20110909 Begin
        String xml = "";
        try
        {
            writer = response.getWriter();
            
            String status = (String)request.getParameter("status");
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);
            cardChargeLogVO.setUnionpayuser(unionpayuser);
            cardChargeLogVO.setUnionpaycode(unionpaycode);
            
            busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
            cardChargeLogVO.setBusiType(busiType);
            
            cardChargeLogVO.setBatchnum(batchnum);
            cardChargeLogVO.setAuthorizationcode(authorizationcode);
            cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
            cardChargeLogVO.setUnionpaytime(unionpaytime);
            cardChargeLogVO.setVouchernum(vouchernum);
            
            // modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
            unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
            // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
            
            cardChargeLogVO.setUnionpayfee(unionpayfee);
            cardChargeLogVO.setStatus(status);
            cardChargeLogVO.setDescription(description);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);
            
            // 插入缴费日志
            broadBandPayService.updateCardChargeLog(cardChargeLogVO);
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog end!");
        }
    }
    
    /**
     * 银行卡缴费提交
     * 
     * @return
     */
    public String cardChargeCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit start");
        }
        
        String forward = null;
        
        // 设定缴费方式
        this.payType = Constants.PAY_BYCARD;
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 登录用户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        tMoney = Integer.parseInt(fee) + "";
        

        try
        {
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            
            // 调用接口执行现金缴费
            ReturnWrap rw = broadBandPayBean.wbandpay(termInfo, customer, curMenuId, ncode, CommonUtil.yuanToFen(tMoney));
            
            // 缴费成功
            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
            {
                // 返回值进行解析
                CTagSet ctagSet = new CTagSet();
                
                //modify begin sWX219697 2014-9-23 16:34:55 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
                if(rw.getReturnObject() instanceof CTagSet)
                {
                	ctagSet = (CTagSet)rw.getReturnObject();
                }
                else
                {
                    Vector vector = (Vector)rw.getReturnObject();
                    
                    if(vector.size() > 0)
                    {
                        // 设置返回结果
                        ctagSet = (CTagSet)vector.get(0);
                    }
                }
                //modify end sWX219697 2014-9-23 16:34:55 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
                
                dealNum = (String)ctagSet.GetValue("dealNum");// 交易流水
                dealTime = payDate;// 交易时间
                
                
                selfCardPayLogVO.setRecdate(dealTime);
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("宽带(银联卡)缴费成功！");
                selfCardPayLogVO.setDealnum(dealNum);
                
                tMoney = CommonUtil.fenToYuan(tMoney);
                
                forward = "success";
                
                // 记录缴费成功日志
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "0",
                        "宽带缴费:自助终端银联卡缴费成功!");
            }
            // 缴费失败
            else
            {
                tMoney = CommonUtil.fenToYuan(Integer.parseInt(tMoney) + "");
                
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setDescription("宽带(银联卡)缴费失败！");
                selfCardPayLogVO.setDealnum("");
                
                forward = "error";
                setErrormessage("宽带(银联卡)缴费失败！");
                
                
                // 记录缴费失败日志
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "1",
                        "宽带缴费:自助终端银联卡缴费失败!");
            }
            
            selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
            
            broadBandPayService.updateChargeLog(selfCardPayLogVO);
        }
        catch (Exception e)
        {
            logger.error(e);
            e.printStackTrace();
            errormessage = "对不起,提交缴费时发生异常,请凭小票到营业厅查询是否缴费成功,谢谢使用.";
            forward = "cardErrorPage";
        }
        
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit end");
        }
        
        return forward;
    }
    
    /**
     * 取消银行卡缴费
     * 
     * @return
     */
    public String cancelCardCharge()
    {
        return "cancelCardCharge";
    }
    
    /**
     * 银联卡交费异常处理
     * 
     * @return
     * @see
     */
    public String goCardError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError Starting ...");
        }
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {            
            this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
            
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(date);
            
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            
            if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
            {
                selfCardPayLogVO.setChargeLogOID(broadBandPayService.getChargeLogOID());
                selfCardPayLogVO.setRegion(termInfo.getRegion());
                selfCardPayLogVO.setTermID(termInfo.getTermid());
                selfCardPayLogVO.setOperID(termInfo.getOperid());
                selfCardPayLogVO.setServnumber(servnumber);
                if (Constants.PAY_BYCASH.equals(payType))
                {
                    selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
                }
                else if (Constants.PAY_BYCARD.equals(payType))
                {
                    selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
                }
                
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
                
                selfCardPayLogVO.setTerminalSeq("");
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("00");
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                selfCardPayLogVO.setAcceptType("");
                selfCardPayLogVO.setServRegion(servRegion);
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());
                selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费 favourable：优惠缴费）
                
                broadBandPayService.addChargeLog(selfCardPayLogVO);
            }
            // 更新日志
            else
            {
                selfCardPayLogVO.setChargeLogOID(chargeLogOID);
                
                if (tMoney == null || "".equals(tMoney.trim()))
                {
                    selfCardPayLogVO.setStatus("00");
                }
                else
                {
                    selfCardPayLogVO.setStatus("10");
                }
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                
                //银联终端号
                selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
                
                //银联商户号
                selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
                
                selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
                
                broadBandPayService.updateChargeLog(selfCardPayLogVO);
            }
        }
        catch (Exception e)
        {
            // 捕获一切异常,使页面必须走退卡页面
            logger.error(e);
            e.printStackTrace();
            errormessage = errormessage + e.getMessage();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        return "cardErrorPage";
    }
    
    /**
     * 验证流水号
     * 
     * @param termInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {
        
        String seq = termInfo.getTermid().concat(terminalSeq);
        
        String tmpSeq = seq.concat(servnumber);
        
        // 如果有相同的串，则是重复缴费
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            String recDate = DateUtilHub.getCurrentDateTime();
            
            dealTime = recDate;
            
            String tmpErrorMsg = "重复缴费:宽带账号[".concat(servnumber)
                    .concat("],投币金额[")
                    .concat(tMoney)
                    .concat("]元,归属营业厅[")
                    .concat(termInfo.getOrgname())
                    .concat("],流水号[")
                    .concat(seq)
                    .concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(servnumber);
            // 现金投币
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            // 现金缴费流水,终端id+厂商生成流水
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(recDate);
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // 记录重复缴费日志
            broadBandPayService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // 记录到SH_REC_LOG,交易流水号这里记录现金缴费流水号
            this.createRecLog(servnumber, Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
        
    }
    
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }
    
    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public boolean isCanPayWithCash()
    {
        return canPayWithCash;
    }
    
    public void setCanPayWithCash(boolean canPayWithCash)
    {
        this.canPayWithCash = canPayWithCash;
    }
    
    public String getHasMultiInvoices()
    {
        return hasMultiInvoices;
    }
    
    public void setHasMultiInvoices(String hasMultiInvoices)
    {
        this.hasMultiInvoices = hasMultiInvoices;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getDealNum()
    {
        return dealNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setDealNum(String dealNum)
    {
        this.dealNum = dealNum;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public BroadBandPayBean getBroadBandPayBean()
    {
        return broadBandPayBean;
    }

    public void setBroadBandPayBean(BroadBandPayBean broadBandPayBean)
    {
        this.broadBandPayBean = broadBandPayBean;
    }

    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTMoney()
    {
        return tMoney;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTMoney(String money)
    {
        tMoney = money;
    }
    
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getBalance()
    {
        return balance;
    }
    
    public void setBalance(String balance)
    {
        this.balance = balance;
    }
    
    public String getCardnumber()
    {
        return cardnumber;
    }
    
    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }
    
    public String getUnionpaycode()
    {
        return unionpaycode;
    }
    
    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    
    public String getUnionpayuser()
    {
        return unionpayuser;
    }
    
    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }

    
    public String getBusiType()
    {
        return busiType;
    }

    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }

    public String getBatchnum()
    {
        return batchnum;
    }
    
    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
    }
    
    public String getUnionpayfee()
    {
        return unionpayfee;
    }
    
    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
    }
    
    public String getUnionpaytime()
    {
        return unionpaytime;
    }
    
    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }
    
    public String getAuthorizationcode()
    {
        return authorizationcode;
    }
    
    public void setAuthorizationcode(String authorizationcode)
    {
        this.authorizationcode = authorizationcode;
    }
    
    public String getBusinessreferencenum()
    {
        return businessreferencenum;
    }
    
    public void setBusinessreferencenum(String businessreferencenum)
    {
        this.businessreferencenum = businessreferencenum;
    }
    
    public String getVouchernum()
    {
        return vouchernum;
    }
    
    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }

    public BroadBandPayService getBroadBandPayService()
    {
        return broadBandPayService;
    }

    public void setBroadBandPayService(BroadBandPayService broadBandPayService)
    {
        this.broadBandPayService = broadBandPayService;
    }

    public String getYingjiaoFee()
    {
        return yingjiaoFee;
    }
    
    public void setYingjiaoFee(String yingjiaoFee)
    {
        this.yingjiaoFee = yingjiaoFee;
    }
    
    public String getServRegion()
    {
        return servRegion;
    }
    
    public void setServRegion(String servRegion)
    {
        this.servRegion = servRegion;
    }
    
    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public String getChargeLogOID()
    {
        return chargeLogOID;
    }
    
    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }
    
    public String getDealTime()
    {
        return dealTime;
    }
    
    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }
    
    public String getNeedRandomPwd()
    {
        return needRandomPwd;
    }
    
    public void setNeedRandomPwd(String needRandomPwd)
    {
        this.needRandomPwd = needRandomPwd;
    }
    
    public String getPrintcontext()
    {
        return printcontext;
    }
    
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

	public String getInitPosResCode()
	{
		return initPosResCode;
	}

	public void setInitPosResCode(String initPosResCode)
	{
		this.initPosResCode = initPosResCode;
	}

	public String getCmtPosResCode()
	{
		return cmtPosResCode;
	}

	public void setCmtPosResCode(String cmtPosResCode)
	{
		this.cmtPosResCode = cmtPosResCode;
	}

	public String getErrPosResCode()
	{
		return errPosResCode;
	}

	public void setErrPosResCode(String errPosResCode)
	{
		this.errPosResCode = errPosResCode;
	}

    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }
    
    
    
    //------------------宽带产品分页-------------------------------------------------------------------------------------------
    /**
     * 当前页
     */
    private int page = 1;
    
    /**
     * 总条数
     */
    private int countNum = 0;
    
    /**
     * 每页条数
     */
    private int pageNum = 6;
    
    /**
     * 总页数
     */
    private int countPageNum = 0;
    
    /**
     * 取当前页面数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void displayPage()
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // 清空数据
        wbankList.clear();
        
        // 计算总条数
        countNum = wbankAllList.size();
        
        // 计算总页数
        if (countNum % pageNum > 0)
        {
            countPageNum = countNum / pageNum + 1;
        }
        else
        {
            countPageNum = countNum / pageNum;
        }
        
        // 开始条数
        int startNum = pageNum * (page - 1) + 1;
        
        // 结束条数
        int endNum = pageNum * page;
        
        // 开始条数
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum)
            {
                this.wbankList.add(wbankAllList.get(i-1));
            }
        }
        
    }

    public List<WBandVO> getWbankList()
    {
        return wbankList;
    }

    public void setWbankList(List<WBandVO> wbankList)
    {
        this.wbankList = wbankList;
    }

    public List<WBandVO> getWbankAllList()
    {
        return wbankAllList;
    }

    public void setWbankAllList(List<WBandVO> wbankAllList)
    {
        this.wbankAllList = wbankAllList;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getCountNum()
    {
        return countNum;
    }

    public void setCountNum(int countNum)
    {
        this.countNum = countNum;
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getCountPageNum()
    {
        return countPageNum;
    }

    public void setCountPageNum(int countPageNum)
    {
        this.countPageNum = countPageNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getNcode()
    {
        return ncode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdName()
    {
        return prodName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }

    public String getDetailDesc()
    {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc)
    {
        this.detailDesc = detailDesc;
    }

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
}