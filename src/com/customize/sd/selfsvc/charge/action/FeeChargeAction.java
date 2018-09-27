package com.customize.sd.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.FeeChargeBean;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.customize.sd.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * 话费充值缴费
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志 
    // modify begin by xkf29026  2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(FeeChargeAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
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
    private FeeChargeBean feeChargeBean;
    
    private String servRegion = "";
    
    private String acceptType = "";
    
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
    private String busitype;
    
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
    private FeeChargeService feeChargeService;
    
    // BOSS流水
    private String dealNum = "";
    
    private String hasMultiInvoices;
    
    private String payType;
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
    // 缴费日志对应的oid
    private String chargeLogOID = "";
    
    private String errorType = "";
    
    private String needReturnCard = "";
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    private String chargeType = "";
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
    // 凭条交易时间
    private String pDealTime = "";
    // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
    
    // add begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
    /**
     * 客户名称
     */
    private String custName = "";
    // add end g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
    
    // add begin g00140516 2012/12/07 R003C12L11n01 OR_SD_201211_692
    /**
     * 银联扣款接口的返回码
     */
    private String unionRetCode = "";
    
    /**
     * 银联打印信息
     */
    private String printcontext = "";
    // add end g00140516 2012/12/07 R003C12L11n01 OR_SD_201211_692
    
    /**
     * 发票打印PO
     */
    private InvoicePrintPO invoicePrint;
    
    private String recoid;
    
    //add begin sWX219697 2014-7-17 OR_huawei_201406_1125_山东_支撑跨区缴费
    /**
     * 异地缴费，1：本地，0：异地缴费
     */
    private String regionFlag;
    
    /**
     * 地区名称
     */
    private String regionName;
    //add end sWX219697 2014-7-17 OR_huawei_201406_1125_山东_支撑跨区缴费
    
    // add begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    /**
     * 现金明细
     */
    private String cashDetail;
    // add end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    
    // add begin jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
    /**
     * 话费连缴标志
     */
    private String morePhoneFlag; 
    
    /**
     * 话费连缴list
     */
    private transient List<CardChargeLogVO> morePhones = null;
    
    /**
     * 话费连缴手机号数组
     */
    private String[] servnumbers;
    
    /**
     * 用户实缴金额数组
     */
    private String morePhoneFee;

    /**
     * 话费连缴总费用
     */
    private String totalFee;
    
    /**
     * 发票内容
     */
    private transient List<String> invoices = null;
    
    /**
     * 打印月结发票标志，1：打印月结发票
     */
    private String monthInvoiceFlag;
    
    /**
     * 银联卡缴费po
     */
    private transient CardChargeLogVO cardChargeVO;
    
    /**
     * 凭条打印标志，1：已打印
     */
    private String printPayProFlag;
    
    /**
     * 打印全部发票标志，1：已打印
     */
    private String printAllInvFlag;
    
    /**
     * 手机号码字符串，以","分隔
     */
    private String telnums;
    
    /**
     * 话费连缴用户信息字符串
     * 用户1，用户2，用户3
     * 用：手机号码~手机号码region~实缴金额~余额~应缴费~客户姓名~受理类型~缴费日志流水~受理流水~boss编号~缴费状态
     */
    private String morePhoneInfo;
    
    /**
     * 已打印发票的手机号码串
     */
    private String printInvTelnum = "";
    // add end jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
    
    
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    /**
     * 发票打印时密码
     */
    private String password = "";
    
    // 月结标识
    private String printFlag = "";
    
    private transient UserLoginBean userLoginBean = null;
    
    private transient LoginService loginService = null;
    
    // 打印全部发票标识
    private String printAllFlag = "";
    
    // 已打印号码个数
    private int printInvTelnumLen = 0;
    
    // 多人缴费号码总个数
    private int morePhonesLen = 0;
    
    // 多人缴费标识
    private String morePhonesFlag = "";
    // modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    
    /**
     * 话费充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
     * 
     * @return
     */
    public String inputNumber()
    {      
    	// 初始化客户信息标志
    	morePhoneInfo = "";
    	
    	// 初始化话费连缴标志
    	morePhoneFlag = "";
    	
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
           return  "inputNumber2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "inputNumber";
    }
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    /**
     * 是否只支持金属键盘
     * @remark create YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818 
     */
    private boolean getIsKey()
    {
        TerminalInfoPO termInfo = (TerminalInfoPO) (getRequest().getSession().getAttribute(Constants.TERMINAL_INFO));
        
        return termInfo.isSuppKey();
    }
        
    /**
     * 取缴费类型
     * 
     * @param payType(Card或者Cash)
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getChargeType(String payType)
    {
        String chargeType = "";
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance()
                .getCachedData(Constants.ChargeType);
        // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
        if (chargeTypeList != null)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
        
        return chargeType;
    }
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371

    /**
     * 话费充值账户信息查询
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {        
        logger.debug("qryfeeChargeAccount start");
        
        String forward = "failed";

        // 判断用户状态是否可以缴费，返回true可以缴费
        String checkResult = qryUserStatus();
        
        if (!"".equals(checkResult))
        {       	
        	return forward;
        }
        
        HttpServletRequest request = this.getRequest();
            
        try
        {
        	TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
                
        	// 现金缴费
        	// modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
            String bankNo = termInfo.getBankno();
            String chargeType = getChargeType("Cash");
            
            if (StringUtils.isEmpty(bankNo) || StringUtils.isEmpty(chargeType))
            {
            	logger.error("交费渠道或者行号获取失败！");
                
                setErrormessage("交费渠道或者行号信息获取失败");
                
                // 记录异常日志
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费渠道或者行号信息获取失败");
                
                return forward;
            }
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
             
            // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            Map<String, Object> result = feeChargeBean.qryfeeChargeAccount(termInfo,
                    servnumber,
                    curMenuId,
                    bankNo,
                    CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"),
                    "ALL",
                    chargeType);
            // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
            if (result != null && result.size() > 0)
            {
                CTagSet tagSet = (CTagSet)result.get("returnObj");
                
                // modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
                String sumFee = "";
                String balance = "";
                
                servRegion = tagSet.GetValue("servregion");
                
                // modify begin sWX219697 2014-7-19 09:47:35 OR_huawei_201406_1125_山东_支撑跨区缴费
                // 受理方式，regionFlag为0(跨区交费)时，受理类型为ZZYD（自助异地）
                // modify begin zKF28472 2014-10-28 OR_SD_201410_324 关于自助终端跨区缴费对账异常的需求
                // acceptType = "0".equals(regionFlag) ? "ZZYD" : tagSet.GetValue("accept_type");
                acceptType = "0".equals(regionFlag) ? "Z" + tagSet.GetValue("accept_type")
                        : tagSet.GetValue("accept_type");
                // modify end zKF28472 2014-10-28 OR_SD_201410_324 关于自助终端跨区缴费对账异常的需求
                // modify end sWX219697 2014-7-19 09:47:41 OR_huawei_201406_1125_山东_支撑跨区缴费
                // add begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                custName = tagSet.GetValue("cust_name");
                // add begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                
                sumFee = tagSet.GetValue("sum_fee");
                balance = tagSet.GetValue("balance");
                
                // modify end by cwx456134 2017-05-13 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
                
                if (StringUtils.isEmpty(acceptType))
                {
                    logger.error("查询受理类型失败！");
                    
                    setErrormessage("查询用户信息失败");
                    
                    // 记录异常日志
                    this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费时查询受理类型失败");
                    
                    return forward;
                }
                
                // modify begin hWX5316476 2014-03-14 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
                // 应缴费用
                if (StringUtils.isEmpty(sumFee) || "0.00".equals(sumFee))
                {
                    this.setYingjiaoFee(null);
                    
                    // 设置话费余额
                    setBalance(balance);
                }
                else
                {
                	// 调用接口查询应缴话费金额
                    Map resultfee = feeChargeBean.qryRetcharge(termInfo, servnumber, curMenuId);
                    
                    if(null == resultfee || null == resultfee.get("retcharge"))
                    {
                    	logger.error("查询查询应缴话费金额失败！");
                        
                        setErrormessage("查询应缴话费金额失败");
                        
                        // 记录异常日志
                        this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费时查询应缴话费金额失败");
                        
                        return forward;
                    }
                    String retcharge = (String) resultfee.get("retcharge");
                	String shouldpay = CommonUtil.fenToYuan(retcharge);
                	
                	this.setYingjiaoFee(shouldpay);
                }
	            // modify end hWX5316476 2014-03-14 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
                // ----------------------------充值方式-------------------------------------------------
                    
                // 根据终端组自缓存中获取菜单列表
                String groupID = termInfo.getTermgrpid();
                    
                List<MenuInfoPO> menus = null;
                    
                if (!StringUtils.isEmpty(groupID))
                {
                	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
                }
                    
                // 当页菜单列表
                usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
                
                // findbugs修改 2015-09-02 zKF66389
                //logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
                // findbugs修改 2015-09-02 zKF66389    
                // findbugs修改 2015-09-02 zKF66389 
                //if (usableTypes == null || usableTypes.size() == 0)
                if (usableTypes.size() == 0)
                // findbugs修改 2015-09-02 zKF66389 
                {
                    // 没有可用的充值方式
                    setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
                        
                    // 记录日志
                    this.createRecLog(servnumber, "feeCharge", "0", "0", "1", "暂时没有可用的充值方式");
                }
                else
                {
                    // add begin YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
                    if (getIsKey())
                    {
                       return  "success2";
                    }
                    // add end YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
                    
                    forward = "success";
                }
            }
            else
            {
                logger.error("查询受理类型失败！");
                    
                setErrormessage("查询受理类型失败");
                    
                // 记录错误日志
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费时查询受理类型失败");
            }
        }
        catch (Exception e)
        {
            logger.error("查询受理类型失败！");
                
            setErrormessage("查询受理类型失败");
                
            // 记录异常日志
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费时查询受理类型失败");
        }
        
        return forward;
    }
    
    /**
     * 手工输入缴费金额
     * 
     * @return
     */
    public String toInputMoney()
    {
        return "toInputMoney";
    }
    
    /**
     * 转向投币页面
     * 
     * @return
     */
    public String cashCharge()
    {
        this.getRequest().setAttribute("sdCashFlag", "1");
        
        // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        chargeType = getChargeType("Cash");
        // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // add begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        // 终端信息
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 取得缴费日志oid
        chargeLogOID = feeChargeService.getChargeLogOID();
        
        // 封装缴费日志对象数据
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        selfCardPayLogVO.setRecdate(DateUtil._getCurrentTime());
        selfCardPayLogVO.setAcceptType(acceptType);
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
        selfCardPayLogVO.setDescription("交费之前记录投币日志");
        selfCardPayLogVO.setRecType("phone"); // 业务类型（phone：话费缴费  favourable：优惠缴费）
        selfCardPayLogVO.setBankno(chargeType + termInfo.getBankno());
        
        // 新增缴费日志
        feeChargeService.addChargeLog(selfCardPayLogVO);
        // add end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
           return  "cashChargePage2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "cashChargePage";
    }
    
    /**
     * 话费充值，现金缴费
     * 
     * @return
     */
    public String cashChargeCommit()
    {
        logger.debug("cashChargeCommit start");
        logger.warn("提交交费请求，号码：" + servnumber + "；金额：" + tMoney + "；流水：" + terminalSeq);
          	
        // add begin g00140516 2012/11/09 R003C12L11n01 防止用户不投币，直接从浏览器中模拟交费请求
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            
            return "error";
        }    
        // add end g00140516 2012/11/09 R003C12L11n01 防止用户不投币，直接从浏览器中模拟交费请求        
        
        String forward = null;
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        
        // 现金缴费
        // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        String bankNo = termInfo.getBankno();
        // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // modify begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        // 封装缴费日志对象数据
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        // modify end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        
        // 山东接口 -- 发票打印标志，0＝不打印 1＝打印 2=不打印发票可以补打
        String invoiceType = "2";
        
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        // 现金缴费
        Map resultMap = feeChargeBean.chargeCommit(termInfo,
        		curMenuId,servnumber,bankNo,payDate,terminalSeq,tMoney,acceptType,invoiceType,"","",chargeType);
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        if (resultMap != null && resultMap.size() > 1)
        {
            CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            if (null != tagSet && !tagSet.isEmpty())
            {
                dealNum = (String)tagSet.GetValue("bill_nbr");// 受理编号
                
                this.getRequest().setAttribute("dealNum", dealNum);
                
                // modify begin wWX217192 OR_huawei_201407_1042 自助终端（山东）充值发票打印优化 
                // 受理流水号，打印发票时使用
                recoid = (String)tagSet.GetValue("recoid");
                // modify end wWX217192 OR_huawei_201407_1042 自助终端（山东）充值发票打印优化 
                
                // add begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                custName = (String)tagSet.GetValue("cust_name");
                // add end g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                
            }
            
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("话费充值现金交费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
/*            forward = "success";
            
            // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
            if (getIsKey())
            {
            	 forward = "success2";
            }
            // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
*/            
            forward = getIsKey() ? "success2" : "success";
            
            // 记录缴费成功日志
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "0", "交费:自助终端交费成功!");
            
        }
        else
        {
            String errMsg = "";
            if (resultMap != null)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            
            if (errMsg == null || "".equals(errMsg.trim()))
            {
                errMsg = "话费充值现金交费失败！";
            }
            
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            selfCardPayLogVO.setDealnum("");
            
            //forward = "error";
            
            // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
//            if (getIsKey())
//            {
//            	 forward = "error2";
//            }
            // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818
            forward = getIsKey() ? "error2" : "error";
            
            setErrormessage("话费充值现金交费失败！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);

        logger.debug("cashChargeCommit end");
   
        return forward;
    }
    
    /** 
     * 现金投币后，更新交费日志
     * 
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2015/04/16 OR_SD_201502_169_SD_自助终端实现现金稽核功能
     */
    public void updateCashChargeLog()
    {
        logger.debug("updateCashChargeLog Starting...");
        
        try
        {
            // 取得终端机信息
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            
            cardChargeLogVO.setChargeLogOID(chargeLogOID);
            cardChargeLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            cardChargeLogVO.setDescription("话费充值现金交费记录投币明细");
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setRecdate(DateUtil._getCurrentTime());
            cardChargeLogVO.setCashDetail(cashDetail);
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            cardChargeLogVO.setTerminalSeq(terminalSeq);
            
            // 插入缴费日志
            feeChargeService.updateCashDetail(cardChargeLogVO);
            
            // 更新缴费日志成功
            writeXmlMsg("1");
        }
        catch (Exception e)
        {
            // 更新缴费日志失败
            writeXmlMsg("0");
            
            logger.error("现金投币之后更新投币明细日志异常：", e);
        }
        
        logger.debug("updateCashChargeLog end!");
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
        
        // modify begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        // 终端信息
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // 更新记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 封装缴费日志对象数据
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRecdate(DateUtil._getCurrentTime());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        // 终端流水号
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
        
        if (tMoney == null || "".equals(tMoney.trim()) || "0".equals(tMoney.trim()))
        {
            selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        else
        {
            selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        
        // 更新缴费日志
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        // modify end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
            return "cashErrorPage2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "cashErrorPage";
    }
    
    /**
     * 校验当前时间银联卡充值是否可用。1，可用；0，不可用
     * 
     * @throws Exception
     * @see 
     * @remark create g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692
     */
    public void checkTime() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("判断银联卡交费是否可用失败");
        }
        
        String xml = "0";
        
        try
        {
            // 2320-0025
            String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
            
            if (time != null && !"".equals(time.trim()))
            {
                // 当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("HHmm");        
                String currTime = sdf.format(new Date());
                
                // 当前时间在0025至2320之间时可用
                String[] times = time.split("-");
                if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
                {
                    xml = "1";
                }
            }
        }
        catch (Exception e)
        {
            logger.error("判断银联卡交费是否可用失败：", e);
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
                    
                    throw new Exception("判断银联卡交费是否可用失败");
                }
            }
        }        
    }
    
    /**
     * 转向银行卡缴费金额选择页面
     * 
     * @return
     */
    public String cardCharge()
    {
    	// add begin jWX216858 2015-4-9 OR_SD_201501_1063 自助终端支撑连缴功能优化
    	// 如果为多人缴费，直接进入协议页面
    	if ("1".equals(morePhoneFlag))
    	{
            // add begin sWX219697 2015-8-6 14:52:50
            //校验前台总金额与后台总金额是否一致
            int userTotalFee = 0;
            
            //计算用户信息字符串中的总金额
            for(CardChargeLogVO vo : this.getMorePhoneList())
            {
                userTotalFee = userTotalFee + Integer.parseInt(vo.gettMoney());
            }
            
            //隐藏域totalFee与计算的总金额不一致，跳转至错误页面，结束多人缴费
            if (userTotalFee != Integer.parseInt(totalFee))
            {
                logger.error("多人缴费总金额校验失败！");
           
                this.createRecLog(Constants.SH_MOREPHONE, "", "0", Constants.RECSTATUS_FALID, "多人缴费总金额校验失败！");
           
                setErrormessage("多人缴费总金额校验失败！");
           
                return ERROR;
            }
            // add end sWX219697 2015-8-6 14:52:50
    	    
    		return this.dutyInfo();
    	}
    	// add end jWX216858 2015-4-9 OR_SD_201501_1063 自助终端支撑连缴功能优化
    	
		chargeType = getChargeType("Card");
		
		if (getIsKey())
		{
			return "otherFee2";
		}
		
		return "otherFee";
    }
    
    /**
     * 转向银行卡缴费免责声明页面
     * 
     * @return
     */
    public String dutyInfo()
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
        String forward = "toReadCard";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            // 组装日志对象
            chargeLogOID = feeChargeService.getChargeLogOID();
            
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);        
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setTermID(termInfo.getTermid());
            cardChargeLogVO.setOperID(termInfo.getOperid());
            cardChargeLogVO.setServnumber(servnumber);
            cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            cardChargeLogVO.setUnionpayuser("");
            cardChargeLogVO.setUnionpaycode("");
            cardChargeLogVO.setBusiType("");
            cardChargeLogVO.setCardnumber("");
            cardChargeLogVO.setBatchnum("");
            cardChargeLogVO.setAuthorizationcode("");
            cardChargeLogVO.setBusinessreferencenum("");
            cardChargeLogVO.setUnionpaytime("");
            cardChargeLogVO.setVouchernum("");
            cardChargeLogVO.setUnionpayfee("");
            cardChargeLogVO.setTerminalSeq("");
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            
            cardChargeLogVO.setStatus("00");
            cardChargeLogVO.setDescription("扣款之前记录日志");
            cardChargeLogVO.setDealnum("");
            cardChargeLogVO.setAcceptType(acceptType);
            cardChargeLogVO.setServRegion(servRegion);
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setPosNum("");
            cardChargeLogVO.setBatchNumBeforeKoukuan("");
            cardChargeLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
            cardChargeLogVO.setBankno(chargeType + termInfo.getBankno());
           
            // 插入缴费日志
            feeChargeService.addChargeLog(cardChargeLogVO);
            
            // 屏蔽超时返回首页的功能
            request.setAttribute("sdCashFlag", "1");
        }
        catch (Exception e)
        {
            logger.error("扣款之前记录日志失败：", e);
            
            this.createRecLog(servnumber, payType, "0", "0", "1", "扣款之前记录日志失败，无法使用银联卡进行充值");
            
            setErrormessage("扣款之前记录日志失败，无法使用银联卡进行充值");
            
            forward = "error";
        }
        
        return forward;
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
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("扣款之前记录日志失败");
        }
        
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        cardChargeLogVO.setCardnumber(cardnumber);
        cardChargeLogVO.setBatchnum(batchnum);
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        cardChargeLogVO.setVouchernum(vouchernum);
        
        if (unionpayfee != null)
        {
            while (unionpayfee.startsWith("0"))
            {
                unionpayfee = unionpayfee.substring(1);
            }
        }
        else
        {
            unionpayfee = "";
        }
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        cardChargeLogVO.setRecdate(payDate);
        
        String xml = "";
        try
        {
            // 插入缴费日志
            feeChargeService.updateCardChargeLog(cardChargeLogVO);
            
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之后更新日志异常：", e);
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
                    
                    throw new Exception("扣款之后更新日志异常");
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
        logger.debug("cardChargeCommit start");

        String forward = null;
        
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        String referer = getRequest().getHeader("Referer");
        if (null == referer)
        {
            setErrormessage("无效请求");
            
            return "error";
        }
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        
        // 银行卡缴费
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        String bankNo = termInfo.getBankno();
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // 山东接口 -- 发票打印标志，0＝不打印 1＝打印 2=不打印发票可以补打
        String invoiceType = "2";
        
        while (tMoney.startsWith("0"))
        {
            tMoney = tMoney.substring(1);
        }
        tMoney = CommonUtil.fenToYuan(tMoney);
        
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        // 银行卡缴费
        Map resultMap = feeChargeBean.chargeCommit(termInfo,
        		curMenuId,servnumber,bankNo,payDate,
                terminalSeq,tMoney,acceptType,invoiceType,"","",chargeType);
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // 更新日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        if (resultMap != null && resultMap.size() > 1)
        {
        	CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            
        	if (null != tagSet && !tagSet.isEmpty())
            {
                dealNum = (String)tagSet.GetValue("bill_nbr");// 受理编号
                
                this.getRequest().setAttribute("dealNum", dealNum);
                
                // modify begin wWX217192 OR_huawei_201407_1042 自助终端（山东）充值发票打印优化
                // 受理流水，预存发票打印使用
                recoid = (String)tagSet.GetValue("recoid");
            	// modify begin wWX217192 OR_huawei_201407_1042 自助终端（山东）充值发票打印优化 
                
                // add begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                custName = (String)tagSet.GetValue("cust_name");
                // add end g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
                
            }
                
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("话费充值银联卡交费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
            forward = "success";
            
            if (getIsKey())
            {
               return  "success2";
            }
            
            // 记录缴费成功日志
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "0", "交费:自助终端交费成功!");
        }
        else
        {
            String errMsg = "";
            if (resultMap != null)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//            if (errMsg == null || "".equals(errMsg.trim()))
//            {
//                errMsg = "银联卡扣款成功，但是交费失败！";
//            }
            errMsg = (errMsg == null || "".equals(errMsg.trim())) ? "银联卡扣款成功，但是交费失败！" : errMsg;
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
            
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            
            setErrormessage("银联卡扣款成功，但是交费失败！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
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
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
        {
            selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
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
            
            if (tMoney == null || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            }
            else
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            }
            
            selfCardPayLogVO.setTerminalSeq("");
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
            selfCardPayLogVO.setAcceptType(acceptType);
            selfCardPayLogVO.setServRegion(servRegion);
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
            // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            selfCardPayLogVO.setBankno(chargeType + termInfo.getBankno());
            // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            feeChargeService.addChargeLog(selfCardPayLogVO);
        }
        // 更新日志
        else
        {
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            
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
            selfCardPayLogVO.setPosResCode(unionRetCode);
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        return "cardErrorPage";
    }
    
    /**
     * 记录发票打印日志
     * 
     * @see
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String cycle = request.getParameter("cycle");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintRecord record = new InvoicePrintRecord();
        record.setServNumber(servNumber);
        record.setCycle(cycle);
        record.setTermID(termInfo.getTermid());
        
        feeChargeService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
    /**
     * 消费明细、发票注释解析
     * 
     * @param consumeList 消费明细
     * @param comments 发票注释
     * @return
     */
    private String parseConsumeList(String consumeList, String comments)
    {
    	// modify begin g00140516 2012/08/14 R003C12L08n01 发票打印格式优化
        String[] consumeArr = consumeList.replace("|", ",").split(";");
        StringBuffer tmpConsumeList = new StringBuffer();
        int rowNum = 18;
        int row = 0;
        for (int i = 0; i < consumeArr.length && row < rowNum; i++)
        {
            if (consumeArr[i].trim().length() <= 3)
            {
                continue;
            }
            
            String tmpArr[] = consumeArr[i].split(",");
            if (!CommonUtil.isEmpty(tmpArr[0]))
            {
            	// modify begin wWX217192 2014-07-10 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票信息改造
            	// 为保留发票明细子项前的空格信息将trim()方法删除
            	tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[0], 13));
                
                if (tmpArr.length > 1)
                {
                	// 为保留发票明细子项前的空格信息将trim()方法删除
                    tmpConsumeList.append(CommonUtil.fillLeftBlank(tmpArr[1], 8));
                }
                else
                {
                    tmpConsumeList.append(CommonUtil.fillLeftBlank("", 8));
                }
                // modify end wWX217192 2014-07-10 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票信息改造
                tmpConsumeList.append("\\n");
                row++;
            }
        }
        
        // 发票备注，每行打印25个汉字
        if (comments != null && !"".equals(comments.trim()))
        {
            tmpConsumeList.append(comments.trim()).append("\\n");
            
            if (comments.trim().length() % 25 == 0)
            {
                row += (comments.trim().length() / 25);
            }
            else
            {
                row = row + (comments.trim().length() / 25) + 1;
            }
        }
        
        tmpConsumeList.append("\\n");
        row++;
        tmpConsumeList.append("\\n");
        row++;
        
        return tmpConsumeList.toString();
        
    }
    
    /**
     * 将发票数据组织成xml
     * 
     * @param list 发票数据
     * @return
     * @see
     */
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                        .append(subItemKey)
                        .append("><![CDATA[")
                        .append(subItemValue)
                        .append("]]></")
                        .append(subItemKey)
                        .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        
        return xmlStr.toString();
    }
    
    /**
     * 随机密码验证，验证通过后，打印发票
     * 
     * @return
     * @see
     */
    public String printInvoice()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            
            if (logger.isInfoEnabled())
            {
                logger.info("发票打印功能，用户" + servnumber + "使用随机密码进行认证成功");
            }
        }
        else
        {
            forward = "error";
            
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                errorMsg = "随机密码输入错误，请重新输入。";
            }
            
            logger.error("发票打印功能，用户" + servnumber + "输入的随机密码错误或超时，认证失败");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
    	list = getPrintInvoice(servnumber, recoid);
    	
    	//add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启,打印电子发票时，接口不返回值,所以直接跳到界面
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            String errormessage = (String)getRequest().getAttribute("telnum");
            if(CommonUtil.isNotEmpty(errormessage))
            {
                logger.info(errormessage);
                return "error";
            }
            else
            {
                logger.info("打印电子发票，用户" + servnumber + "打印发票信息成功");
                request.setAttribute("isElectronInvoice", isElectronInvoice);
                request.setAttribute("servnumber", servnumber);
                return "success";
            }
        }
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
        if(list.isEmpty())
        {
        	logger.info("查询发票信息失败!");
        	
        	forward = "error";
        }
        // String invoiceXML = (String)request.getParameter("invoiceXML");
        // String invoiceType = (String)request.getParameter("invoiceType");
        
        // 封装XML信息
        request.setAttribute("invoiceXML", getXmlStr(list));
    	
        // 设置打印类型
    	request.setAttribute("invoiceType", "Last");
        
        return forward;
    }
    
    /**
     * 无需随机密码验证，直接打印发票
     * 
     * @return
     * @see
     */
    public String printInvoiceWithoutPwd()
    {
    	// 页面跳转标志信息
    	String forward = "success";
        // modify begin g00140516 2012/08/14 R003C12L08n01 发票打印格式优化
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
        list = getPrintInvoice(servnumber, recoid);

        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启,打印电子发票时，接口不返回值,所以直接跳到界面
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            String errormessage = (String)getRequest().getAttribute("telnum");
            if(CommonUtil.isNotEmpty(errormessage))
            {
                logger.info(errormessage);
                return "error";
            }
            else
            {
                logger.info("打印电子发票，用户" + servnumber + "打印发票信息成功");
                request.setAttribute("isElectronInvoice", isElectronInvoice);
                request.setAttribute("servnumber", servnumber);
                if (getIsKey())
                {
                    return  "print2";
                }
                else
                {
                    return "print";
                }
            }
        }
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
        if(list.isEmpty())
        {
        	logger.info("查询发票信息失败!");
        	
        	forward = "error";
        }
        else
        {
        	String printType = setPrintType();
			getRequest().setAttribute("printType", printType);

        	// modify end g00140516 2012/08/14 R003C12L08n01 发票打印格式优化
        	
        	// String invoiceXML= (String) request.getParameter("invoiceXML");
        	// String invoiceType = (String) request.getParameter("invoiceType");
        	
        	request.setAttribute("invoiceXML", getXmlStr(list));
        	
        	request.setAttribute("invoiceType", "Last");
        	
        	// add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        	if (getIsKey())
        	{
        		return  "print2";
        	}
        	// add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        }
        
        return "print";
    }
    
    /**
     *  * 调用打印发票接口查询发票数据
     * 
     * @return List<Map<String, String>>
     * @see [类、类#方法、类#成员]
     * @remark add by wWX217192 
     */
    private List<Map<String, String>> getPrintInvoice(String telnum, String oid)
    {
    	HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 查询打印发票信息
        Map<String, Object> invoiceData = feeChargeBean.invoiceData(termInfo, curMenuId, telnum, oid);
        
        List<Map<String, String>> invoicesList = null;
        
        if (invoiceData != null && invoiceData.size() > 1)
        {
            Object invoiceObj = invoiceData.get("returnObj");
            
            if (invoiceObj instanceof CRSet)
            {
                invoicesList = new ArrayList<Map<String, String>>();
                
                // 取接口返回对象
                CRSet crset  = (CRSet)invoiceObj;

                // crset转成MAP
                Map<String, String> invoiceMaps = getInvoiceInfo(crset);
                
                // 加入list
                invoicesList.add(invoiceMaps);
            }
            printInvTelnum = printInvTelnum + "," + telnum;
            
            // 记录查询未打印发票信息成功日志
            this.createRecLog(telnum, Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "0", "获取补打发票信息成功!");
        }
        else if (invoiceData != null) {
            
            getRequest().setAttribute("telnum", invoiceData.get("returnMsg"));
            
            // 记录查询未打印发票信息失败日志
            this.createRecLog(telnum, Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "1", 
                "获取补打发票信息失败：" + String.valueOf(invoiceData.get("returnMsg")));
        }
        
        // 返回
        return invoicesList;
    }
    
    
    /**
     * 对客服返回的打印发票内容进行处理
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    public Map<String, String> getInvoiceInfo(CRSet crset)
    {
    	// 获取session信息
    	HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
    	// 从接口返回值中获取发票信息
    	Map<String, String> invoiceMaps = new HashMap<String, String>();
        
    	// 组合成可用的Map，并返回
    	Map<String, String> resultMaps = new HashMap<String, String>();
    	
        // 将发票明细信息拼接成字符串信息
        StringBuffer contentItemStr = new StringBuffer("");
        
        // 发票解析格式的标志位, 0:老发票格式，1：新发票格式
        String styleFlag = "0";
        
        // 话费账期
        StringBuffer feeTime = new StringBuffer("");
        
        // 获取ContentItemX或者ContentItemXName的值
        String key = "";
        
        // 初始化最大值信息
        int max = 0;
        
        // ContentItem数据的索引值
        int seq = 0;
        
        // 遍历发票明细信息
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	seq = 0;
        	
        	key = crset.GetValue(i, 0);
        	
        	// 若key值中出现以ContentItem开头不以Name结尾的信息，则为新发票格式，启用新发票的解析方法
        	if(key.startsWith("ContentItem") && !key.endsWith("Name"))
        	{
        		styleFlag = "1";
        	}
        	
        	max = max(key, max);
        	
        }
        
        // 定义一个数组，封装发票明细信息
        String[] contentItems = new String[max];
        
        // 定义一个获取ContentItem后数值的变量
        int arrNums = 0;
        
        // 遍历crset的数据
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	String name = crset.GetValue(i, 0);
        	String value = crset.GetValue(i, 1);
        	
        	// name中包含ContentItem的字符串，则将信息封装成字符串的格式
        	if(name.startsWith("ContentItem"))
        	{
        		arrNums = getSeq(name);
        		
        		if("1".equals(styleFlag))
        		{
        			parseInvoiceDetail(name, value, contentItems, arrNums);
        		}
        		else
        		{
        			contentItems[arrNums - 1] = value.trim().replace("  ", "|");
        		}
        	}
        	// 若name中不包含ContentItem的字符串，则将数据添加至invoiceMaps中
        	else
        	{
        		invoiceMaps.put(name, value);
        	}
        }
        
        // 将ContentItemStr字符串清空，存储发票信息
        contentItemStr = new StringBuffer("");
        
        for(int i = 0; i < contentItems.length; i++)
        {
        	if(StringUtils.isNotEmpty(contentItems[i]))
        	{
        		contentItemStr.append(contentItems[i]).append(";");
        	}
        }
        
        // 计费期间(截取前8个字符，作为值进行使用)
        if(!CommonUtil.isEmpty(invoiceMaps.get("callfeeCreateTime")))
        {
        	feeTime = feeTime.append("话费账期:|").append(invoiceMaps.get("callfeeCreateTime").substring(0, 6) + "(" + invoiceMaps.get("callfeeCreateTime") + ")").append(";");
        }
        
        // 在发票明细的顶部添加话费账期的显示项
        contentItemStr = feeTime.append(contentItemStr);
        
        // 增加显示"金额大写"
        contentItemStr.append("大写金额合计:|").append(invoiceMaps.get("totalmoneydx")).append(";");
        
        // 对发票明细进行解析，解析成可打印在发票上的字符串
        String consumeList = parseConsumeList(contentItemStr.toString(), invoiceMaps.get("note"));
        
        invoiceMaps.put("consumeList", consumeList);
        
        // 将信息封装成类似缴费接口的形式打印
        // 手机号码
        resultMaps.put("servNumber", invoiceMaps.get("telnumber"));
        
        // 流水号
        resultMaps.put("dealNum", invoiceMaps.get("formnum"));
        
        // 发票打印
        resultMaps.put("acceptType", invoiceMaps.get("rectype"));
        
        // 合同号
        resultMaps.put("acctID", 
        	StringUtils.isNotEmpty(invoiceMaps.get("paynumno")) ? invoiceMaps.get("paynumno") : "");
        
        // 客户名称
        resultMaps.put("subsName", invoiceMaps.get("username"));
        
        // 打印日期(YYYY.MM.DD)将格式修改为YYYYMMDD
        resultMaps.put("payDate", invoiceMaps.get("time").replace(".", ""));
        
        // 受理编号
        resultMaps.put("bossFormnum", invoiceMaps.get("formnum"));
        
        // 终端组织机构名称
        resultMaps.put("pOrgName", termInfo.getOrgname());
        
        // 发票明细信息
        resultMaps.put("consumeList", invoiceMaps.get("consumeList"));
        
        return resultMaps;
        
    }
    
    /**
     * 得到ContentItem串中的最大值信息
     * @param key
     * @param max
     * @return 最大值
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
     */
    private int max(String key, int max)
    {
    	// 获取ContentItem后的数值序列
    	int seq = 0;
    	
    	// 获取key值中的数字信息
    	if(key.startsWith("ContentItem") && key.endsWith("Name"))
    	{
    		seq = Integer.parseInt(key.replace("ContentItem", "").replace("Name", ""));
    	}
    	
    	// 获取索引中的最大值
    	if(seq > max)
    	{
    		max = seq;
    	}
    	
    	return max;
    }
    
    /**
     * 得到ContentItem序列中的数值信息
     * @param name
     * @return 得到ContentItem串后的数值信息
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
     */
    private int getSeq(String name)
    {
    	// 获取ContentItem后的数值
    	int arrNums = 0;
    	
    	// 字符串以Name结尾，则replace掉ContentItem及Name字符串
    	if(name.startsWith("ContentItem") && name.endsWith("Name"))
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", "").replace("Name", ""));
		}
    	// 字符串不以Name结尾，则替换掉ContentItem
		else
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", ""));
		}
    	return arrNums;
    }
    
    /**
     * 解析发票明细
     * @param name
     * @param value
     * @param contentItemStr
     * @return 发票明细字符串
     * @remark create wWX217192 2014-07-08 OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_月结发票
     */
    private String[] parseInvoiceDetail(String name, String value, String[] contentItems, int seq)
    {
		StringBuffer tempSf = new StringBuffer("");
		
		// 若ContentItems[seq - 1] 不为空，则将name替换为value值
		if(StringUtils.isNotEmpty(contentItems[seq - 1]))
		{
			contentItems[seq - 1] = contentItems[seq - 1].replace(name, value);
		}
		// ContentItems[seq - 1] 为空
		else
		{
			// name以"Name"结尾，拼接字符串存入数组中
			if(name.endsWith("Name"))
			{
				tempSf.append(value).append("|").append(name.substring(0, name.length() - 4));
				contentItems[seq - 1] = tempSf.toString();
			}
			// name不以"Name"结尾，处理后放入数组中
			else
			{
				tempSf.append(name + "Name").append("|").append(value);
				contentItems[seq - 1] = tempSf.toString();
			}
		}
		
		// modify begin wWX217192 2014-07-14 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
		// 现场要求在合计后加空行
		if(value.equals("合计"))
		{
			contentItems[seq - 1] = contentItems[seq - 1] + "\\n";
		}
		// modify end wWX217192 2014-07-14 OR_huawei_201407_304 营业厅全业务流程优化-业务分流(自助终端)_营改增发票改造
		
		return contentItems;
    }
    
    /**
     * 不校验密码，直接获取用户信息
     * 
     * @return
     * @remark modify by jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    private String qryUserStatus()
    {
        logger.debug("getUserStatus Starting ...");
        
        HttpServletRequest request = this.getRequest();
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        HashMap<String,String> map = (HashMap<String,String>) feeChargeBean.getUserStatus(servnumber, "", termInfo);
        
        if (map == null)
        {
            logger.error("交费前验证号码" + servnumber + "的状态失败");
            
            setErrormessage("验证号码" + servnumber + "状态失败");
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "交费前验证号码状态失败");
            
            logger.debug("getUserStatus End!");
            
            return "验证号码状态失败";
        }
        
        String status = map.get("status");
        
        Pattern p = Pattern.compile("US2[A-Z0-9]*");
        Matcher m = p.matcher(status);
        if (m.matches() && !"US28".equals(status))
        {
            logger.error("号码" + servnumber + "已销户，不能交费");
            
            setErrormessage("号码" + servnumber + "已销户，不能交费");
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "状态：" + status + "，已销户，不能交费");
            
            logger.debug("getUserStatus End!");
            
            return "号码" + servnumber + "已销户，不能交费";
        }
        
        String region = map.get("servRegion");
        
        // add begin jWX216858 2015-4-23 OR_SD_201501_1063 自助终端支撑连缴功能优化
        if ("1".equals(morePhoneFlag))
        {
        	if (!region.equals(termInfo.getRegion()))
        	{
        		logger.error("号码" + servnumber + "是异地用户，请在缴费充值菜单进行缴费！");
                
            	setErrormessage("号码" + servnumber + "是异地用户，请在缴费充值菜单进行缴费！");
            	
            	return "号码" + servnumber + "是异地用户，请在缴费充值菜单进行缴费！";
        	}
        	return "";
        }
        // add begin jWX216858 2015-4-23 OR_SD_201501_1063 自助终端支撑连缴功能优化
        
        //是否异地缴费
        if (!region.equals(termInfo.getRegion()))
        {
        	//modify begin sWX219697 2014-7-19 09:42:45 OR_huawei_201406_1125_山东_支撑跨区缴费
        	//地区名称
        	regionName = map.get("regionName");
        	
        	//异地缴费开关，1：允许异地缴费，非1：不允许异地缴费
        	//modify begin sWX219697 2014-9-3 08:40:20 增加异地缴费开关
        	String chargeSwitch = CommonUtil.getParamValue("SH_YD_CHARGE_SWITCH");
        	//modify begin sWX219697 2014-9-3 08:40:20 增加异地缴费开关
        	
        	//跨区缴费开关关闭，或该号码为省外号码
        	if (!"1".equals(chargeSwitch) || feeChargeService.anthSdTelnum(region) <= 0)
        	{
            	logger.error("号码" + servnumber + "是异地用户，不能交费");
                
            	setErrormessage("号码" + servnumber + "是异地用户，不能交费");
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "号码" + servnumber + "是异地用户，不能交费");
                
                logger.debug("getUserStatus End!");
                
                return "号码" + servnumber + "是异地用户，不能交费";
        	}

        	//跨区交费
        	regionFlag = "0";
        	//modify end sWX219697 2014-7-19 09:43:17 OR_huawei_201406_1125_山东_支撑跨区缴费
        }
        
        logger.debug("getUserStatus End!");
        
        return "";
    }
    
    /**
     * 话费连缴开关 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-10 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String getMorePhoneSwitch()
    {
		return CommonUtil.getParamValue(Constants.SH_MOREPHONE_SWITCH);
	}
    
    /**
     * 话费连缴开关 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-21 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public List<DictItemPO> getAmountList()
    {
    	return this.getDictItemByGrp("MorePhoneAmount");
    }
    
    /**
     * 话费连缴输入手机号码 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String morePhone()
    {
    	morePhones = this.getMorePhoneList();
    	return "inputNumber";
    }
    
    /**
     * 话费连缴获取用户信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public void qryUserStatusMorePhone()
    {
    	logger.debug("qryUserStatusMorePhone start");
        
    	String xml = "";
    	try
    	{
    		// 判断用户状态是否可以缴费，返回true可以缴费
    		String checkResult = qryUserStatus();
    		
    		if (!"".equals(checkResult))
    		{       
    			xml = "0;,;" + getErrormessage();
    		}
    		else
    		{
    			String bankNo = getTerminalInfoPO().getBankno(); // 行号
    			chargeType = getChargeType("Card"); // 缴费类型
    			
    			if (StringUtils.isEmpty(bankNo) || StringUtils.isEmpty(chargeType))
    			{
    				logger.error("交费渠道或者行号获取失败！");
    				
    				setErrormessage("交费渠道或者行号信息获取失败");
    				xml = "0;,;"  + getErrormessage();
    			}
    			else
    			{
    				String money = getRequest().getParameter("money");
    				CardChargeLogVO morePhone = feeChargeBean.qryAcountMorePhone(
    						getTerminalInfoPO(),
    						servnumber,
    						curMenuId,bankNo,
    						CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"),
    						"ALL",chargeType,
    						feeChargeService.getChargeLogOID(),
    						money);
    				xml = "1;,;" + morePhone.getMorePhoneStr();
    			}
    		}
    	}
    	catch (ReceptionException e)
    	{
    		logger.error(e.getMessage());
    		xml = "0;,;" + e.getMessage();
    	}
    	logger.debug("qryUserStatusMorePhone end");
    	writeXmlMsg(xml);
    }
    
    /**
     * 话费连缴获取用户信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public List<CardChargeLogVO> getMorePhoneList()
    {
    	morePhones =  new ArrayList<CardChargeLogVO>();
    	
    	if (CommonUtil.isEmpty(morePhoneInfo))
    	{	
    		return morePhones;
    	}
    	String[] morePhoneInfos = morePhoneInfo.split(",");
    	
    	for (int i = 0; i < morePhoneInfos.length; i++)
    	{
    		String[] infos = morePhoneInfos[i].split("~",-1);
    		CardChargeLogVO cardCharge = new CardChargeLogVO();
    		cardCharge.setServnumber(infos[0]);
    		cardCharge.setServRegion(infos[1]);
    		cardCharge.settMoney(infos[2]);
    		cardCharge.setBalance(infos[3]);
    		cardCharge.setYingjiaoFee(infos[4]);
    		cardCharge.setCustName(infos[5]);
    		cardCharge.setAcceptType(infos[6]);
    		cardCharge.setChargeLogOID(infos[7]);
    		cardCharge.setRecoid(infos[8]);
    		cardCharge.setDealnum(infos[9]);
    		cardCharge.setChargeStatus(infos[10]);
    		morePhones.add(cardCharge);
    	}
    	return morePhones;
    }
    
    /**
     * 话费连缴充值账户信息查询 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String qryAcountMorePhone()
    {
    	logger.debug("qryAcountMorePhone begin");
    	chargeType = getChargeType("Card"); // 缴费类型
    	
    	String forward = "qryAcountMorePhone";
    	usableTypes = getPayMenu();
        
    	// findbugs修改 2015-09-02 zKF66389
    	//logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
    	// findbugs修改 2015-09-02 zKF66389
        
    	// findbugs修改 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
    	if (usableTypes.size() == 0)
        // findbugs修改 2015-09-02 zKF66389
        {
            // 没有可用的充值方式
            setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
            
            forward = "failed";
            // 记录日志
            this.createRecLog(Constants.SH_MOREPHONE, "", "0", "1", "暂时没有可用的充值方式");
        }
        morePhones = this.getMorePhoneList();
        
        if (morePhones.size() < 1)
        {
        	setErrormessage("没有获取到用户信息");
        	forward = "failed";
        }
    	logger.debug("qryAcountMorePhone end");
    	return forward;
    }

    /**
     * 获取充值方式菜单 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	private List getPayMenu() {
		// 获取终端机id
        String groupID = getTerminalInfoPO().getTermgrpid();
        
        List<MenuInfoPO> menus = null;
        
        if (!StringUtils.isEmpty(groupID))
        {
        	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
        }
        
        // 获取支付方式菜单
        return CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
	}
    
    /**
     * 转到银联卡读卡页面 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String readCardMorePhone()
    {
    	String forward = "readCardMorePhone";
        
    	try
    	{
        	morePhones = this.getMorePhoneList();
        	
            // 获取话费连缴流水
            chargeLogOID = feeChargeService.getChargeLogOID();
            
            feeChargeService.addCardLog(chargeLogOID, morePhones, getTerminalInfoPO(), chargeType);
            
            // 屏蔽超时返回首页的功能
            getRequest().setAttribute("sdCashFlag", "1");
        }
        catch (Exception e)
        {
            logger.error("扣款之前记录日志失败：", e);
            
            this.createRecLog(Constants.SH_MOREPHONE, "", "0", "1", "扣款之前记录日志失败，无法使用银联卡进行充值");
            
            setErrormessage("扣款之前记录日志失败，无法使用银联卡进行充值");
            
            forward = "error";
        }
        
        return forward;
    }
    
    /**
     * 更新话费连缴日志 <功能详细描述>
     * 
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public void updateMoreChargeLog() throws Exception
    {
        logger.debug("updateMoreChargeLog Starting...");
        
        getResponse().setContentType("text/xml;charset=GBK");
        getRequest().setCharacterEncoding("UTF-8");
        morePhoneInfo = java.net.URLDecoder.decode(getRequest().getParameter("morePhoneInfo"),"UTF-8");
        
    	morePhones = this.getMorePhoneList();
    	
    	String xml = "1";
    	for (int i = 0; i < morePhones.size(); i++)
    	{
    		try
    		{
    			// 更新缴费日志
	    		feeChargeService.updateMorePhoneLog(cardChargeVO, morePhones.get(i), getTerminalInfoPO());
    		}
    		catch (Exception e)
    		{
    			xml = "0";
    			logger.error(morePhones.get(i).getServnumber() + " 用户更新缴费日志异常");
    			logger.error("扣款之后更新日志异常：", e);
    		}
    	}
        
        logger.debug("updateCardChargeLog end!");
        
        writeXmlMsg(xml);
    }
    
    /**
     * 话费连缴提交 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String morePhoneCommit()
    {
    	logger.debug("morePhoneCommit start");

    	morePhones = this.getMorePhoneList();
        String forward = "success";
        
        String referer = getRequest().getHeader("Referer");
        if (null == referer)
        {
            setErrormessage("无效请求");
            
            return "failed";
        }
        
        CardChargeLogVO morePhone = null;
        StringBuffer sb = new StringBuffer();
        
        // 循环缴费
        for (int i = 0; i < morePhones.size(); i++)
        {
        	// 银行卡缴费
        	morePhone = feeChargeBean.chargeCommitMorePhone(getTerminalInfoPO(),
                     morePhones.get(i), curMenuId, chargeType);
           
        	// 受理流水
            morePhones.get(i).setRecoid(morePhone.getRecoid());
            
            // boss流水
            morePhones.get(i).setDealnum(morePhone.getDealnum());
            
            // 客户名称
            morePhones.get(i).setCustName(morePhone.getCustName());
            
            // 缴费状态,0:成功
            morePhones.get(i).setChargeStatus(morePhone.getChargeStatus());
            
            // 交易时间，打印凭条用
            pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            sb.append(morePhones.get(i).getMorePhoneStr()).append(",");
            // 更新缴费日志
            feeChargeService.updateChargeLog(morePhone);
            
            // 记录业务日志
            this.createRecLog(morePhones.get(i).getServnumber(), Constants.SH_MOREPHONE, "0", "0", 
            		morePhone.getChargeStatus(), morePhone.getDescription());
        }
        morePhoneInfo = sb.toString();
        morePhoneInfo = morePhoneInfo.substring(0, morePhoneInfo.length() - 1);
        logger.debug("morePhoneCommit end");
        
        return forward;
    }
    
    /**
     * 话费连缴结束页面 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-15 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String finish()
    {
    	morePhones = this.getMorePhoneList();
    	return "finish";
    }
    
    /**
     * 话费连缴打印全部发票 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public String printAllInvoice()
    {
    	logger.debug("printAllInvoice begin");
    	String forward = "success";
    	morePhones = this.getMorePhoneList();
    	invoices = new ArrayList<String>();
    	CardChargeLogVO morePhone = null;
    	List<Map<String, String>> list = null;
    	StringBuffer telnum = new StringBuffer(); 
    	
    	int index = 0;

        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
    	//电子发票时，返回结果列表，电话号码，邮箱，成功失败
        List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
        //读取开关配置，是否启用电子发票,true为开启,打印电子发票时，接口只返回错误信息
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
    	
    	// 将发票内容放入集合
    	for (int i = 0; i < morePhones.size(); i++)
    	{
    		index += i;
    		morePhone = morePhones.get(i);
    		
    		// 拼接手机号
    		telnum = telnum.append(morePhone.getServnumber()).append(",");
    		
    		// 缴费成功，才可打印发票
    		if ("0".equals(morePhone.getChargeStatus()))
    		{
    			// 获取发票信息
    			list = getPrintInvoice(morePhone.getServnumber(), morePhone.getRecoid());
    			
    	        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
    	        if("true".equals(isElectronInvoice))
    	        {
    	            Map<String,String> map = new HashMap<String,String>();
                    map.put("telNum", morePhone.getServnumber());
                    map.put("email", morePhone.getServnumber()+"@139.com");
    	            String errormessage = (String)getRequest().getAttribute("telnum");
    	            if(CommonUtil.isNotEmpty(errormessage))
    	            {
    	                map.put("flag", "失败");
    	                logger.info(errormessage);
    	            }
    	            else
    	            {
                        map.put("flag", "成功");
    	                logger.info("打印电子发票，用户" + morePhone.getServnumber() + "打印发票信息成功");
    	            }
    	            messages.add(map);
    	        }
    	        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
    			
    			// begin zKF66389 2015-09-18 9月份findbugs修改
    			//if(null == list || list.isEmpty())
    			if(list.isEmpty())
    			// end zKF66389 2015-09-18 9月份findbugs修改
    			{
    				logger.info("查询发票信息失败!");
    				
    				invoices.add("");
    			}
    			else
    			{
    				// 发票打印方式配置只配置一次就行
    				if (0 == index)
    				{
    					String printType = setPrintType();
    					getRequest().setAttribute("printType", printType);
    				}
    			}
    			invoices.add(getXmlStr(list));
    		}
    	}
    	
    	//add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        getRequest().setAttribute("isElectronInvoice", isElectronInvoice);
        getRequest().setAttribute("messages", messages);
    	//add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
    	getRequest().setAttribute("invoice", invoices);
    	telnums = telnum.toString();
    	logger.debug("printAllInvoice end");
    	return forward;
    }

    /**
     * 话费连缴发票打印方式配置 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	private String setPrintType() 
	{
		// 发票打印方式配置。1:厂商编码A,机器型号A|厂商编码C,机器型号C;2:厂商编码B,机器型号B|厂商编码D,机器型号D;3:厂商编码E,机器型号E|厂商编码F,机器型号F
		String printType = (String) PublicCache.getInstance().getCachedData("SH_INVOICE_PRINTTYPE");
		if (printType != null && !"".equals(printType.trim()))
		{
			// 厂商编码B,机器型号B
			String tmpStr = getTerminalInfoPO().getProvidercode() + "," + getTerminalInfoPO().getMachinemodel();
			int index1 = printType.indexOf(tmpStr);
			if (index1 != -1)
			{
				int index2 = printType.indexOf(";", index1);
				if (index2 != -1)
				{
					// 1:厂商编码A,机器型号A|厂商编码C,机器型号C;2:厂商编码B,机器型号B|厂商编码D,机器型号D
					printType = printType.substring(0, index2);
				}
				
				index2 = printType.lastIndexOf(":");
				
				// 打印方式：2
				printType = printType.substring(index2 - 1, index2);
				
			}
		}
		return printType;
	}
    
	/**
     * 话费连缴错误处理 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	public String goMorePhoneError()
	{
		morePhones = this.getMorePhoneList();
		
		// 循环插入信息
		for (int i = 0; i < morePhones.size(); i++)
		{
			// 设置错误信息
			morePhones.get(i).setDescription(errormessage);
			
			// 银联返回码
			morePhones.get(i).setPosResCode(unionRetCode);
			
			if (CommonUtil.isEmpty(tMoney))
			{	
				// 00状态
				morePhones.get(i).setStatus(Constants.CHARGE_ERROR);
			}
			else
			{
				// 10状态，扣款成功，缴费失败
				morePhones.get(i).setStatus(Constants.PAYSUCCESS_CHARGEERROR);
			}
			
			// 组织机构编码
			morePhones.get(i).setOrgID(getTerminalInfoPO().getOrgid());
			
			// region
			morePhones.get(i).setRegion(getTerminalInfoPO().getRegion());
			
            feeChargeService.updateChargeLog(morePhones.get(i));
            
            this.createRecLog(morePhones.get(i).getServnumber(), Constants.SH_MOREPHONE, "0", "0", "1", errormessage);
		}
		return "goMorePhoneError";
	}
	
	/** 
     * 转到错误页面
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String goToError()
    {
        return ERROR;
    }
    
    /** 获取全部打印信息
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
     */
    public void getAllPrintInfo()
    {
        // 全部打印
        if ("all".equals(this.printAllFlag))
        {
            // 已打印号码
            String printInvTelnumFormat = this.printInvTelnum;
            
            // 非空且第一个字符为逗号
            if ( !"".equals(printInvTelnumFormat) && printInvTelnumFormat.charAt(0) == ',')
            {
                printInvTelnumFormat = printInvTelnumFormat.substring(1,printInvTelnumFormat.length());
            }
            
            // 已打印号码个数
            String[] printInvTelnumStr = printInvTelnumFormat.split(",");
            
            // 非第一次打印
            if (!"".equals(printInvTelnumFormat))
            {
                printInvTelnumLen = printInvTelnumStr.length;
            }
            
            // 号码信息总个数
            morePhonesLen = this.getMorePhoneList().size();
            servnumber = morePhones.get(printInvTelnumLen).getServnumber();
            recoid = morePhones.get(printInvTelnumLen).getRecoid();
            tMoney = morePhones.get(printInvTelnumLen).gettMoney();
        }
    }
    
    /** 打印前服务密码登陆
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
     */
    public String goServicePwdPage()
    {
        getAllPrintInfo();
        return "goServicePwdPage";
    }
    
    /** 打印前随机密码登陆
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
     */
    public String goRandomPwdPage()
    {
        getAllPrintInfo();
        return "goRandomPwdPage";
    }
    
    /**
     * 通过手机号码、服务密码进行身份认证
     * 
     * @return
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
     */
    public String loginWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd Starting ...");
        }
        
        String forward = "failed";
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        // 服务密码加密标志(宁夏)，1:加密
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
                
        LoginErrorPO loginErrorPO = null;
        if (!Constants.PROOPERORGID_SD.equals(province))
        {
            loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
            if (loginErrorPO != null && isLocked(loginErrorPO))
            {
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                // 被锁定
                logger.error("由于服务密码连续输入错误次数达到了系统限制，号码" + servnumber + "暂时被锁定");
                
                if (Constants.PROOPERORGID_NX.equals(province))
                {
                    this.getRequest().setAttribute("errormessage", "由于服务密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试");
                }
                else
                {
                    this.getRequest().setAttribute("errormessage", "由于服务密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试");
                }
                
                return forward;
            }
        }

        if (Constants.PROOPERORGID_SD.equals(province))
        {
            //modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
            String newpwd = password;
            
            try
            {
                DESedeEncrypt encrypt = new DESedeEncrypt();
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("山东密码验证时，加密密码错误：", e);
            }
            
            // 调用CRM统一认证接口进行认证
            Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
            //modify end g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密

            // 100:成功 102:缺省密码 119:初始密码
            if (returnMap != null && "100".equals(String.valueOf(returnMap.get("errcode"))))// 100:成功 
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("用户" + servnumber + "使用服务密码进行身份认证成功");
                }
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                
                if (customerSimpMap.get("customerSimp") != null)
                {
                    // 取登录用户信息
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                          
                    // 处理session信息
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //老手机号码及其验证方式
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //清除详单数据
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                        //清除账单数据
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中

                        //清除用户信息
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //将新的用户信息存放在session中
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //设置用户的loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
                    
                    forward = "success";
                }
            }
            // 初始密码或缺省密码-->随机密码验证-->密码修改页面
            else if (returnMap != null && ("102".equals(String.valueOf(returnMap.get("errcode"))) || "119".equals(String.valueOf(returnMap.get("errcode")))))
            {
                // 生成随机密码
                String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
                
                // 发送随机密码短信
                String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMessage = shortMessage.replace("[%1]", randomPwd);
                
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                customerSimp.setServNumber(servnumber);
                
                if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
                {
                    logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
                    
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
                    
                    this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
                    
                    forward = "failed";
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
                    }
                    
                    forward = "randomcode_editPwd";
                }
            }
            // 登录失败
            else
            {
                loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
                
                logger.error("使用服务密码进行身份认证失败，手机号码：" + servnumber);
                
                if (returnMap != null)
                {
                    this.getRequest().setAttribute("errormessage", returnMap.get("returnMsg"));
                }
                else
                {
                    this.getRequest().setAttribute("errormessage", "使用服务密码进行认证失败。");
                }
                this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用服务密码进行认证失败。"); 
                
                forward = "failed";
            }
        }
        else
        {
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            Map customerSimpMap = null;
            if (Constants.PROOPERORGID_NX.equals(province) && "1".equals(encryptFlag))
            {
                String newpwd = password;
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                }
                catch (Exception e)
                {
                    logger.error("服务密码认证时，加密密码错误：", e);
                }
                
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, newpwd, termInfo);

            }
            else
            {   
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            if (customerSimpMap.get("customerSimp") == null)
            {
                // 登录失败
                loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
                
                logger.error("使用服务密码进行身份认证失败，手机号码：" + servnumber);
                
                // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
                String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
                        "zz_04_01_000001", String.valueOf(customerSimpMap.get("errcode")), 
                        null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // add begin cKF76106 2013/02/05  宁夏用户登录失败根据错误转换提示信息
                if (Constants.PROOPERORGID_NX.equals(province) && "713".equals(String.valueOf(customerSimpMap.get("errcode"))))
                {
                    // [CDATA[BUSINESSID:BegChargeFee_ZZZD,号码[15138053999]查询归属地失败]]
                    this.getRequest().setAttribute("errormessage", "此用户非宁夏移动用户，不允许进行操作");
                }
                // add end cKF76106 2013/02/05  宁夏用户登录失败根据错误转换提示信息
                else if (Constants.PROOPERORGID_NX.equals(province))
                {
                    int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                    String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                    
                    if ((loginErrorPO == null && 1 < maxTimes) 
                            || (loginErrorPO != null && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                    {
                        this.getRequest().setAttribute("errormessage", "服务密码认证失败，请退出或者重新认证");
                    }
                    else
                    {
                        //this.getRequest().setAttribute("errormessage", "服务密码认证失败，由于连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试");
                        this.getRequest().setAttribute("errormessage", "服务密码认证失败，由于连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试");
                    }
                }
                
                this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
                // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            }
            else
            {   
                //add begin ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
                if (Constants.PROOPERORGID_NX.equals(province)
                        && userLoginBean.valiIsfirstpwd(termInfo, curMenuId, servnumber))
                {
                    // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    forward = "initPwdLogin";
                    // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    
                }
                //add end ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
             
                else
                { 
                    if (logger.isInfoEnabled())
                    {
                        logger.info("用户" + servnumber + "使用服务密码进行身份认证成功");
                    }
                    
                    
                    // add begin lWX5316086 

                    // 如果同一号码同一渠道多次登录
                    if(Constants.PROOPERORGID_NX.equals(province) && 
                            "1".equals((String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_SWITCH")) && 
                              !loginService.checkLoginByServNumber(servnumber,termInfo.getTermid()))
                    {
                        // 返回错误提示
                        this.getRequest().setAttribute("errormessage", (String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_TIPMESSAGE"));
                        
                        return "failed";
                    }
               
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                    
                    // 登录成功，删除记录
                    loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                    
                    //modify begin g00140516 2011/10/20 R003C11L10n01 重构            
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //老手机号码及其验证方式
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //清除详单数据
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                        //清除账单数据
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                        
                        //清除用户信息
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //将新的用户信息存放在session中
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //设置用户的loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    //modify end g00140516 2011/10/20 R003C11L10n01 重构 
                    
                    // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    // 清除已向用户推荐活动标志
                    session.removeAttribute(Constants.ALREADY_REC_FLAG);
                    // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
                    
                    forward = "success";  
                    
                    // add end lWX5316086 
                    
                }
           }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd End");
        }
        
        return forward;
    }
    
    /** 检测号码是否锁定
     * <功能详细描述>
     * @param loginErrorPO
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
     */
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // 错误次数达到了系统限制
        if (loginErrorPO.getErrorTimes() >= maxTimes)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String lastTime = loginErrorPO.getLastTime();
            
            try
            {
                // 锁定时长
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // 当前时间
                String currTime = sdf.format(c.getTime());
                
                // 解锁时间
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // 解锁时间要晚于当前时间，即服务号码在锁定期间内
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("判断号码是否被锁定时发生异常：", e);
            }
        }
        
        // 错误次数尚未达到系统限制
        return false;
    }
    
    /**
     * 使用随机密码进行认证
     * 
     * @return
     * @see 
     */
    public String loginWithRandomPwdNew()
    {
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, password);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用随机密码进行身份认证成功");
            }
            
            // 认证成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 用户信息
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // 取客户信息
            if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                if (customerSimpMap.get("customerSimp") != null)
                {
                    customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                }
            }
            
            customerSimp.setServNumber(servnumber);
            customerSimp.setLoginMark("0100");
            
            // session中存放的用户信息
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                // session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                //老手机号码及其验证方式
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                session.removeAttribute(oldServNumber + "_billhalfyeartrend");
                session.removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中

                if (!servnumber.equals(oldCustomerSimp.getServNumber()))
                {
                    //清除用户信息
                    session.removeAttribute(Constants.USER_INFO);
                    
                    //将新的用户信息存放在session中
                    // session.setAttribute(Constants.USER_INFO, customerSimp);
                }
                else
                {
                    oldCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0100"));   
                }
            }
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "随机密码验证成功。");
            
            forward = "success";
        }
        // 认证失败
        else 
        {
            // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            // 获取认证失败后的错误信息
            String errorMsg = getErrMsg(loginErrorPO, result);
            // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，身份认证失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        return forward;
    }
    
    /**
     * 获取认证失败后的错误信息
     * @param loginErrorPO
     * @param result
     * @return 错误信息
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
    private String getErrMsg(LoginErrorPO loginErrorPO, String result)
    {
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        String errorMsg = "";
        
        if ("-1".equals(result))
        {
            errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                if ((null == loginErrorPO && 1 < maxTimes) 
                        || (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                {
                    errorMsg = "您输入的随机密码已经超时，请退出或者重新认证";
                }
                else
                {
                    errorMsg = "您输入的随机密码已经超时，由于随机密码认证失败次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试";
                }
            }
        }
        else
        {
            errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
            
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                if ((null == loginErrorPO  && 1 < maxTimes) 
                        || (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                {
                    errorMsg = "随机密码输入错误，请退出或者重新认证";
                }
                else
                {
                    errorMsg = "随机密码输入错误，由于随机密码认证失败次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试";
                }
            }
        }
        return errorMsg;
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
    // add end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    
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
    
    public String getDealNum()
    {
        return dealNum;
    }
    
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
    
    public FeeChargeBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeBean feeChargeBean)
    {
        this.feeChargeBean = feeChargeBean;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String gettMoney()
    {
        return tMoney;
    }

    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
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
    
    public String getBusitype()
    {
        return busitype;
    }
    
    public void setBusitype(String busitype)
    {
        this.busitype = busitype;
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
    
    public FeeChargeService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
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
    
    public String getAcceptType()
    {
        return acceptType;
    }
    
    public void setAcceptType(String acceptType)
    {
        this.acceptType = acceptType;
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
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    public String getChargeType()
    {
        return chargeType;
    }
    
    public void setChargeType(String chargeType)
    {
        this.chargeType = chargeType;
    }
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
	// add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371   
    public String getpDealTime()
    {
        return pDealTime;
    }
    
    public void setpDealTime(String pDealTime)
    {
        this.pDealTime = pDealTime;
    }
    // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getUnionRetCode()
    {
        return unionRetCode;
    }

    public void setUnionRetCode(String unionRetCode)
    {
        this.unionRetCode = unionRetCode;
    }
        
    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public InvoicePrintPO getInvoicePrint()
    {
        return invoicePrint;
    }

    public void setInvoicePrint(InvoicePrintPO invoicePrint)
    {
        this.invoicePrint = invoicePrint;
    }

	public String getRegionFlag() {
		return regionFlag;
	}

	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public List<CardChargeLogVO> getMorePhones() {
		return morePhones;
	}

	public void setMorePhones(List<CardChargeLogVO> morePhones) {
		this.morePhones = morePhones;
	}

	public String[] getServnumbers() {
		return servnumbers;
	}

	public void setServnumbers(String[] servnumbers) {
		this.servnumbers = servnumbers;
	}

	public String getMorePhoneFee() {
		return morePhoneFee;
	}

	public void setMorePhoneFee(String morePhoneFee) {
		this.morePhoneFee = morePhoneFee;
	}

	public List<String> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<String> invoices) {
		this.invoices = invoices;
	}

	public void setMorePhoneFlag(String morePhoneFlag) {
		this.morePhoneFlag = morePhoneFlag;
	}

	public String getMorePhoneFlag() {
		return morePhoneFlag;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getMonthInvoiceFlag() {
		return monthInvoiceFlag;
	}

	public void setMonthInvoiceFlag(String monthInvoiceFlag) {
		this.monthInvoiceFlag = monthInvoiceFlag;
	}

    /**
     * @return 返回 cashDetail
     */
    public String getCashDetail()
    {
        return cashDetail;
    }

    /**
     * @param 对cashDetail进行赋值
     */
    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
    }

	public CardChargeLogVO getCardChargeVO() {
		return cardChargeVO;
	}

	public void setCardChargeVO(CardChargeLogVO cardChargeVO) {
		this.cardChargeVO = cardChargeVO;
	}

	public String getPrintPayProFlag() {
		return printPayProFlag;
	}

	public void setPrintPayProFlag(String printPayProFlag) {
		this.printPayProFlag = printPayProFlag;
	}

	public String getPrintAllInvFlag() {
		return printAllInvFlag;
	}

	public void setPrintAllInvFlag(String printAllInvFlag) {
		this.printAllInvFlag = printAllInvFlag;
	}

	public String getTelnums() {
		return telnums;
	}

	public void setTelnums(String telnums) {
		this.telnums = telnums;
	}

	public String getMorePhoneInfo() {
		return morePhoneInfo;
	}

	public void setMorePhoneInfo(String morePhoneInfo) {
		this.morePhoneInfo = morePhoneInfo;
	}

	public String getPrintInvTelnum() {
		return printInvTelnum;
	}

	public void setPrintInvTelnum(String printInvTelnum) {
		this.printInvTelnum = printInvTelnum;
	}
	
	// add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrintFlag()
    {
        return printFlag;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }
    public String getPrintAllFlag()
    {
        return printAllFlag;
    }
    
    public void setPrintAllFlag(String printAllFlag)
    {
        this.printAllFlag = printAllFlag;
    }
	// add end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化

    public int getPrintInvTelnumLen()
    {
        return printInvTelnumLen;
    }

    public void setPrintInvTelnumLen(int printInvTelnumLen)
    {
        this.printInvTelnumLen = printInvTelnumLen;
    }

    public int getMorePhonesLen()
    {
        return morePhonesLen;
    }

    public void setMorePhonesLen(int morePhonesLen)
    {
        this.morePhonesLen = morePhonesLen;
    }

    public String getMorePhonesFlag()
    {
        return morePhonesFlag;
    }

    public void setMorePhonesFlag(String morePhonesFlag)
    {
        this.morePhonesFlag = morePhonesFlag;
    }


}