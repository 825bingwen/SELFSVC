package com.customize.nx.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.nx.selfsvc.bean.FeeChargeNXBean;
import com.customize.nx.selfsvc.cache.RefreshCacheNX;
import com.customize.nx.selfsvc.charge.service.FeeChargeNXService;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 话费充值缴费
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeNXAction extends BaseAction
{    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    private static Log logger = LogFactory.getLog(FeeChargeNXAction.class);
    
    // 当前菜单
    private String curMenuId = "";
    
    // 用户手机号
    private String servnumber = "";
    
    // 交费金额
    private String tMoney;
    
    // 终端机流水号
    private String terminalSeq;
    
    // 错误信息
    private String errormessage = "";
    
    // 调用接口Bean
    private transient FeeChargeNXBean feeChargeBean;
    
    private String servRegion = "";
    
    private String acceptType = "";
    
    //应缴费用
    private String yingjiaoFee;
    
    private String yingjiaoFeeFen;
    
    // 话费余额
    private String balance;
    
    private String balanceFen;
    
    // 用户刷卡卡号
    private String cardnumber;
    
    // 银联为刷卡的终端分配的唯一编号
    private String unionpaycode;
    
    // 银联商户号（授卡方标识）
    private String unionpayuser;
    
    //modify by sWX219697 2015-7-20 10:31:38 busitype改为busiType,findbugs修改
    // 业务类型
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
    private transient FeeChargeNXService feeChargeService;
    
    //BOSS流水
    private String dealNum = "";
    
    //交易时间
    private String dealTime = "";
    
    private String hasMultiInvoices = "true";
    
    private String payType;
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
    //缴费日志对应的oid
    private String chargeLogOID = "";
    
    private String errorType = "";
    
    private String needReturnCard = "";
    
    // 凭条交易时间
    private String pDealTime = "";
    
    private String chargeFlag = "";
    
    private String subsname = "";
    
    /**
     * 用户投币面额信息
     */
    private String cashDetail = "";
    
    /**
     * 客户名称
     */
    private String custName = "";    
    
    /**
     * 银行行号
     */
    private String bankno = "";
    
    // add begin g00140516 2013/02/02 R003C13L01n01 银联卡交费，确认金额界面，用户可主动退出
    /**
     * 主动退出标识
     */
    private String quitFlag = "";
    // add end g00140516 2013/02/02 R003C13L01n01 银联卡交费，确认金额界面，用户可主动退出
    
    // add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    /**
     * 大写金额
     */
    private String upperTMoney;
    // add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    
    // add begin jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    /**
     * 交易时间中文格式，如2014年8月26日
     */
    private String chnDealTime;
    // add end jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    
    /**
     * 话费充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
     * 
     * @return
     */
    public String inputNumber()
    {
        return "inputNumber";
    }
    
    /**
     * 话费充值账户信息查询
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {
        logger.debug("qryfeeChargeAccount start");
        
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
            
            // 查询充值账户信息
            Map result1 = feeChargeBean.qryfeeChargeAccount(termInfo, servnumber, curMenuId);
            if (result1 != null && result1.size() == 2)
            {
                CTagSet tagSet1 = (CTagSet) result1.get("returnObj");
                
                // modify begin hWX5316476  hWX5316476 2014-8-25 V200R003C10LG0801 OR_NX_201407_722_宁夏_关于客户信息模糊化的补充需求
                subsname = tagSet1.GetValue("subsname");
                
                subsname = CommonUtil.blurName(subsname);
                // modify end hWX5316476  hWX5316476 2014-8-25 V200R003C10LG0801 OR_NX_201407_722_宁夏_关于客户信息模糊化的补充需求

                chargeFlag = tagSet1.GetValue("chargeflag");
                logger.info("chargeFlag=" + chargeFlag);
   
                //可以缴费
                if ("00".equals(chargeFlag) || "04".equals(chargeFlag))
                {              
                    yingjiaoFee = "0";
                    yingjiaoFeeFen = "0";
                    
                    //根据终端组自缓存中获取菜单列表
                    String groupID = termInfo.getTermgrpid();
                    
                    List<MenuInfoPO> menus = null;
                    
                    // add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                    if (StringUtils.isNotEmpty(groupID))
                    {                    
                        menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(groupID);
                    }
                    // add end jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                    
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
                        logger.error("没有可用的充值方式");
                        
                        // 没有可用的充值方式
                        setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
                        
                        // 记录日志
                        this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", "没有可用的充值方式");
                    } 
                    else
                    {
                        Map result = feeChargeBean.queryBalance(termInfo, servnumber, curMenuId);
                        if (result != null && result.size() > 0)
                        { 
                            CTagSet tagSet = (CTagSet) result.get("returnObj");
                            
                            setBalance(tagSet.GetValue("newbalance"));
                            balanceFen = CommonUtil.yuanToFen(balance);
                                
                            logger.info("balance=" + balance);
                            
                            forward = "success";
                        }
                        else
                        {
                            logger.error("缴费时，查询用户余额失败");
                            
                            setErrormessage("查询用户余额失败");
                            
                            // 记录错误日志
                            this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", "查询用户余额失败");
                        }
                    }
                }
                else
                {
                    String errorMsg = setErrMsgByChaFlag();
                    
                    logger.error(errorMsg);
                    
                    setErrormessage(errorMsg);
                    
                    // 记录成功日志
                    this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", errorMsg);
                }              
            }
            else if (result1 != null && result1.size() == 1)
            {
            	// add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                String errorMsg = this.setErrMsgByErrCode(result1);
                // add end jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                logger.error(errorMsg);
                
                setErrormessage(errorMsg);
                
                // 记录成功日志
                this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", errorMsg);
            }
            else
            {
                logger.error("判断用户是否可缴费失败");
                
                setErrormessage("判断用户是否可缴费失败");
                
                // 记录错误日志
                this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", "判断用户是否可缴费失败");
            }
        }
        catch (Exception e)
        {
            logger.error("判断用户是否可缴费失败", e);
            
            setErrormessage("判断用户是否可缴费失败，请联系系统管理员。给您带来的不便，敬请原谅。");
            
            // 记录异常日志
            this.createRecLog(servnumber, Constants.FEE_CHARGE, "0", "0", "1", "缴费时查询受理类型失败");
        }
        
        return forward;
    }

    /**
     * 根据chargeFlag设置错误信息
     * @return 错误信息
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String setErrMsgByChaFlag()
	{
		String errorMsg = "";
		if ("01".equals(chargeFlag))
		{
		    errorMsg = "标准神州行用户不允许缴费";
		}
		else if ("02".equals(chargeFlag))
		{
		    //errorMsg = "销户退网用户不允许缴费";
		    errorMsg = "此用户已销户，不允许进行操作";
		}
		else if ("03".equals(chargeFlag))
		{
		    //errorMsg = "该号码不存在（包括外省用户）";
		    errorMsg = "此用户非宁夏移动用户，不允许进行操作";
		}
		else
		{
		    errorMsg = "其他原因不允许缴费";
		}
		return errorMsg;
	}

    /**
     * 根据错误码设置错误信息
     * @param result1
     * @return 错误信息
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	public String setErrMsgByErrCode(Map result1) 
	{
		String errorMsg = "";
		errorMsg = (String)result1.get("returnMsg");
		// result1.get("errcode") != null && !"".equals(result1.get("errcode"))
		if (result1.get("errcode") != null && !"".equals(result1.get("errcode")))
		{
		    String errorCode = String.valueOf(result1.get("errcode"));
		    if ("713".equals(errorCode))// [CDATA[BUSINESSID:BegChargeFee_ZZZD,号码[15138053999]查询归属地失败]]
		    {
		        errorMsg = "此用户非宁夏移动用户，不允许进行操作";
		    }
		    else if ("-1".equals(errorCode))// 必传参数未传入
		    {
		        errorMsg = "系统异常，请联系系统管理员";
		    }
		    else if ("-3".equals(errorCode))// 无该终端对应的操作员
		    {
		        errorMsg = "终端机对应工号异常，请联系系统管理员";
		    }
		    else if ("-4".equals(errorCode))// 无效用户
		    {
		        errorMsg = "此用户已销户，不允许进行操作";
		    }
		    else if ("-5".equals(errorCode))// 该号码不存在（包括外省用户）
		    {
		        errorMsg = "此用户非宁夏移动用户，不允许进行操作";
		    }
		    else if ("-6".equals(errorCode))// 销户退网用户不允许缴费
		    {
		        errorMsg = "此用户已销户，不允许进行操作";
		    }
		    else if ("-7".equals(errorCode))// 标准神州行,贺岁卡,神州行新长话卡用户不允许做缴费业务
		    {
		        errorMsg = "标准神州行,贺岁卡,神州行新长话卡用户不允许做缴费业务";
		    }
		    else if ("-8".equals(errorCode))// 用户状态为退网不允许做缴费业务
		    {
		        errorMsg = "此用户已退网，不允许进行操作";
		    }
		    else if ("-9".equals(errorCode))// 调用AccCollectManager.GetShouldPay出错
		    {
		        errorMsg = "获取用户应缴信息失败，不允许进行操作";
		    }
		    else if ("-10".equals(errorCode))// 无此号码
		    {
		        errorMsg = "此用户非宁夏移动用户，不允许进行操作";
		    }
		    else if ("987168".equals(errorCode))
		    {
		        errorMsg = "终端机对应工号异常，请联系系统管理员";
		    }
		    else
		    {
		        errorMsg = "系统异常，请选择其他方式进行缴费";
		    }
		    
		}
		return errorMsg;
	}
    
    /**
     * 选择交费金额
     * 
     * @return
     * @see 
     */
    public String selectOtherFee()
    {
        return "success";
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
        // 投币页面标志，投币页面钱箱打开不跳转到现金稽核
        this.getRequest().setAttribute("isCashCharge", "1");

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
        
        // modify begin g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
        logger.info("用户" + servnumber + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney + "；流水：" + terminalSeq);
        
        String forward = null;
        
        // add begin g00140516 2012/11/09 R003C12L11n01 防止用户不投币，直接从浏览器中模拟交费请求
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            
            return "error";
        }    
        // add end g00140516 2012/11/09 R003C12L11n01 防止用户不投币，直接从浏览器中模拟交费请求
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/08/24 R003C12L08n01 解决宁夏重复交费问题
        if (!checkCashFee(termInfo))
        {
            forward = "repeatError";
            
            return forward;
        }
        // add end g00140516 2012/08/24 R003C12L08n01 解决宁夏重复交费问题
        
        // 终端流水号
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        
        // 记录用户交费日志之前先记录用户的投币情况
        Map<String, String> params = new HashMap<String, String>();
        params.put("termID", termInfo.getTermid());
        params.put("servNumber", servnumber);
        params.put("terminalSeq", terminalSeq);
        params.put("cashDetail", cashDetail);
        feeChargeService.insertCashDetailInfo(params);
        
        //发起交费请求之前首先记录日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        String logOID = feeChargeService.getChargeLogOID();
        selfCardPayLogVO.setChargeLogOID(logOID);
        
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);           
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);//现金投币日志                   
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));                   
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String payDate = sdf1.format(date);
        dealTime = sdf2.format(date);
        
        // add begin jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
        // 获取交易时间中文格式，如2014年8月26日
        chnDealTime = DateUtil.getChnDateStr(dealTime);
        // add end jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 
        selfCardPayLogVO.setRecdate(payDate);        
        selfCardPayLogVO.setAcceptType("");        
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("缴费之前记录投币日志");
        selfCardPayLogVO.setDealnum("");
        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费）
        
        feeChargeService.addChargeLog(selfCardPayLogVO);
        // modify end g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
        
        // 现金缴费
        Map resultMap = feeChargeBean.chargeCommit(termInfo,
        		curMenuId,
                servnumber,              
                CommonUtil.yuanToFen(tMoney),
                terminalSeq);
        
        // add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
        // 大写金额
        upperTMoney = CurrencyUtil.upperMoney(tMoney);
        // add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求

        //缴费成功
        if (resultMap != null && resultMap.size() > 0)
        {
            //查询余额
            Map result = feeChargeBean.queryBalance(termInfo, servnumber, curMenuId);
            if (result != null && result.size() > 0)
            {                
                CTagSet tagSet = (CTagSet) result.get("returnObj");
                
                balance = tagSet.GetValue("newbalance");
            }
            
            Object obj = resultMap.get("returnObj");
            if (obj instanceof CTagSet)
            {
                //对湖北缴费接口的返回值进行解析
                CTagSet chargeInfo = (CTagSet) obj;
                
                dealNum = (String) chargeInfo.GetValue("dealNum");//受理编号
                
                List invoicesList = new ArrayList();
                
                Map invoiceMap = new HashMap();                    
                invoiceMap.put("servNumber", servnumber);
                invoiceMap.put("dealNum", dealNum);
                invoiceMap.put("chargeAmount", CommonUtil.fenToYuan((String) chargeInfo.GetValue("chargeamount")));
                
                invoiceMap.put("subsName", (String) chargeInfo.GetValue("subsname"));
                this.getRequest().setAttribute("subsName", (String) chargeInfo.GetValue("subsname"));
                
                invoiceMap.put("payDate", dealTime);
                invoiceMap.put("bossFormnum", dealNum);
                    
                invoiceMap.put("pOrgName", termInfo.getTermid());
                
                String totalAmount = (String) chargeInfo.GetValue("chargeamount");
                invoiceMap.put("totalLower", CommonUtil.fenToYuan(totalAmount));                    
                invoiceMap.put("totalUpper", CurrencyUtil.upperMoney(CommonUtil.fenToYuan(totalAmount)));
                
                String cycle = (String) chargeInfo.GetValue("cycle");
                if (cycle == null || "".equals(cycle))
                {
                    invoiceMap.put("feeTime", payDate.subSequence(0, 6));
                    invoiceMap.put("cycle", payDate.subSequence(0, 6));
                }
                else
                {
                    invoiceMap.put("feeTime", cycle);
                    invoiceMap.put("cycle", cycle);
                }               
                
                invoiceMap.put("consumedAmount", "");
                invoiceMap.put("latestBalance", balance);                
                    
                String consumeList = parseConsumeList((String) chargeInfo.GetValue("invoicecontent"));
                invoiceMap.put("consumeList", consumeList);
                    
                invoicesList.add(invoiceMap);                
                
                this.getRequest().setAttribute("invoiceXml", getXmlStr(invoicesList));
            }     
            
            selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("话费充值现金缴费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
            forward = "success";
            
            // 记录缴费成功日志
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "0", "缴费:自助终端缴费成功!");
        }        
        //缴费失败
        else
        {           
            selfCardPayLogVO.setRecdate(payDate);            
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("话费充值现金缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            setErrormessage("尊敬的客户：抱歉，目前系统忙，请稍侯再试！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "1", "缴费:自助终端缴费失败!");
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cashChargeCommit end");
        
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
        
        // modify begin g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312        
        if (logger.isInfoEnabled())
        {
            logger.info("用户" + servnumber + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney);
        }
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        if (null == terminalSeq)
        {
            terminalSeq = "";
        }
        else if (!"".equals(terminalSeq.trim()))
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
        }
        
        // 记录用户交费日志之前先记录用户的投币情况
        Map<String, String> params = new HashMap<String, String>();
        params.put("termID", termInfo.getTermid());
        params.put("servNumber", servnumber);
        params.put("terminalSeq", terminalSeq);
        params.put("cashDetail", cashDetail);
        feeChargeService.insertCashDetailInfo(params);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        if (Constants.PAY_BYCASH.equals(payType))
        {
            selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
        }        
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        // modify end g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
        
        selfCardPayLogVO.setRecdate(payDate);        
        selfCardPayLogVO.setAcceptType("");        
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费）
             
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
        
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    /**
     * 转向银行卡缴费金额选择页面
     * 
     * @return
     */
    public String cardCharge()
    {
        return "otherFee";
    }
    
    /**
     * 转向读银行卡页面
     * 
     * @return
     */
    public String toReadCard()
    {
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        return "toReadCard";
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
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        // 是否启动银联密码框 (0：银联密码框   1：华为密码框)
        int pwdBz = PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ") == null ? 0 : Integer.parseInt((String) PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ"));
        if (pwdBz == 0)
        {
            return "makeSure_ylpwd";
        }
        else
        {
            return "makeSure";
        }
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
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("扣款之前记录日志失败");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String payType = (String)request.getParameter("paytype");
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        String logOID = feeChargeService.getChargeLogOID();
        cardChargeLogVO.setChargeLogOID(logOID);
        
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setTermID(termInfo.getTermid());
        cardChargeLogVO.setOperID(termInfo.getOperid());
        cardChargeLogVO.setServnumber(servnumber);
        cardChargeLogVO.setPayType(payType);
        cardChargeLogVO.setFee(tMoney);
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
        
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        cardChargeLogVO.setDealnum("");
        cardChargeLogVO.setAcceptType(acceptType);
        cardChargeLogVO.setServRegion(servRegion);
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setPosNum("");
        cardChargeLogVO.setBatchNumBeforeKoukuan("");
        cardChargeLogVO.setRecType("phone");// 业务类型（phone：话费缴费  favourable：优惠缴费）
        cardChargeLogVO.setBankno("");
        
        String xml = "";
        try
        {
            // 插入缴费日志
            feeChargeService.addChargeLog(cardChargeLogVO);
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
                writer.print(xml);
                
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
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("扣款并缴费之后记录日志失败");
        }
        
        // 状态
        String status = (String)request.getParameter("status");
        
        // 描述
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        cardChargeLogVO.setChargeLogOID(chargeLogOID);// id
        cardChargeLogVO.setUnionpayuser(unionpayuser);// 商户编号
        cardChargeLogVO.setUnionpaycode(unionpaycode);// 终端编号
        cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(busiType, "UTF-8"));// 交易类型

        //modify begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
        //往数据库里存入加密后的银联卡号
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardnumber));
        //modify end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242

        cardChargeLogVO.setBatchnum(batchnum);// 批次号
        cardChargeLogVO.setAuthorizationcode(authorizationcode);// 授权码
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);// 交易参考号
        cardChargeLogVO.setUnionpaytime(new SimpleDateFormat("yyyy").format(new Date()) + unionpaytime);// 银联扣款时间
        cardChargeLogVO.setVouchernum(vouchernum);// 凭证号
        
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
        cardChargeLogVO.setUnionpayfee(unionpayfee);// 扣款金额
        cardChargeLogVO.setTerminalSeq(terminalSeq);// 终端流水
        cardChargeLogVO.setStatus(status);// 状态
        cardChargeLogVO.setDescription(description);// 描述
        cardChargeLogVO.setBankno(bankno);// 银行行号
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        cardChargeLogVO.setRecdate(payDate);// 交易时间
        
        String xml = "";
        try
        {
            // 更新缴费日志
            feeChargeService.updateCardChargeLog(cardChargeLogVO);
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
     * 银行卡缴费完成之后取发票信息
     * 
     * @return
     */
    public String cardChargeCommit()
    {
        logger.debug("cardChargeCommit start");
        
        String forward = null;
        
        HttpSession session = getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        
        // 处理显示
        /*if (tMoney != null)
        {
            while (tMoney.startsWith("0"))
            {
                tMoney = tMoney.substring(1);
            }
        }
        else
        {
            tMoney = "";
        }
        tMoney = Integer.parseInt(tMoney) / 100 + "";*/
        tMoney = CommonUtil.fenToYuan(tMoney);
        
        // add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
        // 大写金额
        upperTMoney = CurrencyUtil.upperMoney(tMoney);
        // add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
        
        // 发票打印标志，0＝不打印 1＝打印
        payType = Constants.PAY_BYCARD;
        
        // 更新日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        
        //缴费成功
        if (true)
        {
            //查询余额
            Map result = feeChargeBean.queryBalance(termInfo, servnumber, curMenuId);
            if (result != null && result.size() > 0)
            {                
                CTagSet tagSet = (CTagSet) result.get("returnObj");
                
                balance = tagSet.GetValue("newbalance");
            }
            
            // modify begin cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
            // 发票查询渠道
            String invoiceChannel = (String) PublicCache.getInstance().getCachedData("SH_INVOICE_CHANNEL");
            
            // 调用接口查询发票信息
            Map resultMap = feeChargeBean.queryInvoice(termInfo,
            		curMenuId,
                    servnumber,              
                    CommonUtil.yuanToFen(tMoney),
                    invoiceChannel
                    );
            
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    //对发票查询接口的返回值进行解析
                    CTagSet chargeInfo = (CTagSet) obj;
                    
                    dealNum = (String) chargeInfo.GetValue("dealNum");//受理编号
                    
                    List invoicesList = new ArrayList();
                    
                    Map invoiceMap = new HashMap();                    
                    invoiceMap.put("servNumber", servnumber);
                    invoiceMap.put("dealNum", dealNum);
                    invoiceMap.put("chargeAmount", CommonUtil.fenToYuan((String) chargeInfo.GetValue("chargeamount")));
                    
                    invoiceMap.put("subsName", (String) chargeInfo.GetValue("subsname"));
                    this.getRequest().setAttribute("subsName", (String) chargeInfo.GetValue("subsname"));
                    
                    
                    invoiceMap.put("payDate", pDealTime);
                    invoiceMap.put("bossFormnum", dealNum);
                        
                    invoiceMap.put("pOrgName", termInfo.getTermid());
                    
                    String totalAmount = (String) chargeInfo.GetValue("chargeamount");
                    invoiceMap.put("totalLower", CommonUtil.fenToYuan(totalAmount));                    
                    invoiceMap.put("totalUpper", CurrencyUtil.upperMoney(CommonUtil.fenToYuan(totalAmount)));
                    
                    String cycle = (String) chargeInfo.GetValue("cycle");
                    if (cycle == null || "".equals(cycle))
                    {
                        invoiceMap.put("feeTime", payDate.subSequence(0, 6));
                        invoiceMap.put("cycle", payDate.subSequence(0, 6));
                    }
                    else
                    {
                        invoiceMap.put("feeTime", cycle);
                        invoiceMap.put("cycle", cycle);
                    }               
                    
                    invoiceMap.put("consumedAmount", "");
                    invoiceMap.put("latestBalance", balance);                
                        
                    String consumeList = parseConsumeList((String) chargeInfo.GetValue("invoicecontent"));
                    invoiceMap.put("consumeList", consumeList);
                        
                    invoicesList.add(invoiceMap);                
                    
                    this.getRequest().setAttribute("invoiceXml", getXmlStr(invoicesList));
                }     
            }
            // modify end cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
            
            dealTime = pDealTime;// 交易时间
            
            // add begin jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
            // 获取交易时间中文格式，如2014年8月26日
            chnDealTime = DateUtil.getChnDateStr(dealTime);
            // add end jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 

            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("话费充值银联缴费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
            forward = "success";
            
            // 记录缴费成功日志
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "0", "缴费:自助终端缴费成功!");
        }
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("银联卡扣款成功，但是交费失败！");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            
            setErrormessage("尊敬的客户：抱歉，目前系统忙，请稍侯再试！");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "1", "交费:自助终端交费失败!");
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
    }
    
    /**
     * 取缴费类型
     * 
     * @param payType(Card或者Cash)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getChargeType(String payType)
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
            selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
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
            selfCardPayLogVO.setRecType("phone");
            selfCardPayLogVO.setBankno("");
            feeChargeService.addChargeLog(selfCardPayLogVO);
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
        
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
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
     * @param consumeList 消费明细 费用名称|金额||费用名称|金额+换行符+费用名称|金额||费用名称|金额
     *
     * @return
     */
    private String parseConsumeList(String consumeList)
    {
        if (consumeList == null)
        {
            consumeList = "";
        }
        
        String[] consumeArr = consumeList.split("\n");
        StringBuffer tmpConsumeList = new StringBuffer();
        int rowNum = 7;
        int row = 0;
        
        String[] items = null; 
        for (int i = 0; i < consumeArr.length; i++ )
        {
            //费用名称|金额||费用名称|金额
            items = consumeArr[i].split("\\|\\|");
            
            //费用名称|金额
            if (items != null)
            {
                for (int j = 0; j < items.length; j++)
                {
                    String[] leaf = items[j].split("\\|");
                    if (leaf.length > 1)
                    {
                        tmpConsumeList.append(CommonUtil.fillRightBlank(leaf[0], 15));
                        tmpConsumeList.append(CommonUtil.fillRightBlank(leaf[1], 7));
                    }
                    else if (leaf.length == 1)
                    {
                        tmpConsumeList.append(CommonUtil.fillRightBlank(leaf[0], 15));
                        tmpConsumeList.append(CommonUtil.fillRightBlank("", 7));
                    }
                }
            }
            
            tmpConsumeList.append("\\n");
            
            row++;
        }
        
        while (row < rowNum)
        {
            tmpConsumeList.append("\\n");
            row++;
        }
        
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

            it = ((HashMap) list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry) it.next();
                String subItemKey = (String) entry.getKey();
                String subItemValue = (String) entry.getValue();
                
                xmlStr.append("<").append(subItemKey).append("><![CDATA[").append(subItemValue).append("]]></").append(subItemKey).append(">");
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
     * 打印发票
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
        
        String randomPwd = (String) request.getParameter("randomPwd");
        String invoiceXML= (String) request.getParameter("invoiceXML");
        String invoiceType = (String) request.getParameter("invoiceType");
        
        request.setAttribute("invoiceXML", invoiceXML);
        request.setAttribute("invoiceType", invoiceType);
        
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
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
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
        HttpServletRequest request = this.getRequest();
        
        String invoiceXML= (String) request.getParameter("invoiceXML");
        String invoiceType = (String) request.getParameter("invoiceType");
        String subsName = (String) request.getParameter("subsName");
        
        request.setAttribute("invoiceXML", invoiceXML);
        request.setAttribute("invoiceType", invoiceType);
        request.setAttribute("subsName", subsName);
        
        return "print";
    }
    
    /**
     * 重复交费判断
     * 
     * @param termInfo 终端机信息
     * @return true，未重复；false：重复
     * @since 
     * @remark create g00140516 2012/08/24 R003C12L08n01 解决宁夏重复交费问题
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {        
        String cashChargeFlag = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_FLAG");
        if ("1".equalsIgnoreCase(cashChargeFlag))
        {
            String seq = termInfo.getTermid().concat("_").concat(terminalSeq).concat("_").concat(servnumber).concat("_").concat(tMoney);
                        
            synchronized(RefreshCacheNX.cashChargeRecords)
            {
                if (RefreshCacheNX.cashChargeRecords.containsKey(seq))
                {
                    String tmpErrorMsg = "重复缴费:手机号[".concat(servnumber).concat("], 投币金额[").concat(tMoney)
                            .concat("]元, 操作员[").concat(termInfo.getOperid()).concat("], 流水号[").concat(terminalSeq).concat("]");
                    
                    logger.error(tmpErrorMsg);

                    return false;
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("向缓存中插入缴费信息：" + seq);
                    }
                    
                    RefreshCacheNX.cashChargeRecords.put(seq, DateUtilHub.curOnlyTime());
                }
            }
        }
        
        return true;
    }
    
    /**
     * 校验当前时间现金缴费是否可用。1，可用；0，不可用
     * 
     * @throws Exception
     * @see 
     * @remark create cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443
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
            throw new IOException("判断现金交费是否可用失败");
        }
        
        String xml = "0";
        
        try
        {
            // 2355-0005
            String time = (String) PublicCache.getInstance().getCachedData("SH_CHARGE_CASH_LIMIT");
            
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
            logger.error("判断现金交费是否可用失败：", e);
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
                    
                    throw new Exception("判断现金交费是否可用失败");
                }
            }
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
    
    public FeeChargeNXBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeNXBean feeChargeBean)
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
    
    public String getTMoney()
    {
        return tMoney;
    }
    
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
    
    public FeeChargeNXService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeNXService feeChargeService)
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

    public String getYingjiaoFeeFen()
    {
        return yingjiaoFeeFen;
    }

    public void setYingjiaoFeeFen(String yingjiaoFeeFen)
    {
        this.yingjiaoFeeFen = yingjiaoFeeFen;
    }

    public String getBalanceFen()
    {
        return balanceFen;
    }

    public void setBalanceFen(String balanceFen)
    {
        this.balanceFen = balanceFen;
    }
    
    public String getSubsname()
    {
        return subsname;
    }

    public void setSubsname(String subsname)
    {
        this.subsname = subsname;
    }    

    public String getChargeFlag()
    {
        return chargeFlag;
    }

    public void setChargeFlag(String chargeFlag)
    {
        this.chargeFlag = chargeFlag;
    }

    public String getCashDetail()
    {
        return cashDetail;
    }

    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getAcceptType()
    {
        return acceptType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setAcceptType(String acceptType)
    {
        this.acceptType = acceptType;
    }

    public String getPDealTime()
    {
        return pDealTime;
    }

    public void setPDealTime(String dealTime)
    {
        pDealTime = dealTime;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCustName()
    {
        return custName;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getBankno()
    {
        return bankno;
    }

    public void setBankno(String bankno)
    {
        this.bankno = bankno;
    }

	public String getQuitFlag()
	{
		return quitFlag;
	}

	public void setQuitFlag(String quitFlag)
	{
		this.quitFlag = quitFlag;
	}

	public String getUpperTMoney() {
		return upperTMoney;
	}

	public void setUpperTMoney(String upperTMoney) {
		this.upperTMoney = upperTMoney;
	}

	public String getChnDealTime() {
		return chnDealTime;
	}

	public void setChnDealTime(String chnDealTime) {
		this.chnDealTime = chnDealTime;
	}
    
}