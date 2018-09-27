package com.customize.hub.selfsvc.privAccept.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.FeeChargeHubBean;
import com.customize.hub.selfsvc.bean.PrivAcceptHubBean;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.CommonUtilHub;
import com.customize.hub.selfsvc.common.ConstantsHub;
import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.customize.hub.selfsvc.privAccept.service.PrivAcceptHubService;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 优惠查询与办理
 * 
 * @author Administrator
 * 
 */
public class PrivAcceptHubAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    //优惠，对应SH_LOG_PRIV表的RECTYPE字段，优惠受理和热点强推都记这个值
    private static final String RECTYPE = "favourable";
    
    // 当前菜单
    private String curMenuId;
    
    // 页号
    private String pagecount;
    
    // 错误信息
    private String errormessage = "";
    
    // 成功信息
    private String successMessage;
    
    // 优惠业务结果集
    private Vector privService;
    
    // 接口调用
    private PrivAcceptHubBean privAcceptBean;
    
    private FeeChargeHubBean feeChargeBean;
    
    private FeeChargeHubService feeChargeService;
    
    private PrivAcceptHubService privAcceptHubServiver;
    
    private static Log logger = LogFactory.getLog(PrivAcceptHubAction.class);
    
    // 受理优惠的ID
    private String nCode;
    
    // 受理优惠的ID
    private String privId;
    
    private List usableTypes = null;
    
    private String servnumber;
    
    private String servRegion;
    
    // 优惠应缴费用
    private String privMoney;
    
    private String payType;
    
    private String errorType = "";
    
    // 终端机流水号
    private String terminalSeq;
    
    // change by Lifeng 修改优惠受理打印流程错误 20111121 Begin
    // 用户投币金额
    private String tMoney;
    
    // 是否打印凭条标志
    private String printFlag;
    
    // 交易状态
    private String payStatus;
    
    // 交易结果
    private String transResult;
    
    // 优惠名称
    private String privName;
    
    // 缴费交易流水
    private String pDealNum;
    
    // 缴费交易时间
    private String pDealTime;
    
    private String tmpMoney;
    
    // change by Lifeng 修改优惠受理打印流程错误 20111121 End
    
    // BOSS流水
    private String dealNum = "";
    
    // 交易时间
    private String dealTime = "";
    
    // 用户刷卡卡号
    private String cardnumber;
    
    // 需要退卡
    private String needReturnCard = "";
    
    // 银联为刷卡的终端分配的唯一编号
    private String unionpaycode;
    
    // 银联商户号（授卡方标识）
    private String unionpayuser;
    
    // 业务类型
    //modify by sWX219697 2015-7-20 09:56:43 busitype改为busiType findbugs修改
    private String busiType;
    
    // 终端批次号
    private String batchnum;
    
    // 授权码（山东用）
    private String authorizationcode;
    
    // 交易参考号（山东用）
    private String businessreferencenum;
    
    // 评证号（山东用）
    private String vouchernum;
    
    // 银联实际扣款金额，单位（分）
    private String unionpayfee;
    
    // 银联实际扣款时间
    private String unionpaytime;
    
    // 缴费日志对应的oid
    private String chargeLogOID = "";
    
    // 交费失败的类型
    private String errFlag = "1";
    
    // 活动受理时间
    private String priRecDate = "";
    
    // change by Lifeng 修改优惠受理打印流程错误 20111121 Begin
    // 应该退款金额
    // private String priBackMoney = "0";
    
    // change by Lifeng 修改优惠受理打印流程错误 20111121 End
    
    private String hasMultiInvoices = "false";
    
    // 记录银行卡的交费的ID
    private String chargeId;
    
    private String printcontext = "";// 银联打印信息
    
    // add by xkf57421 优惠类型
    private String favorabletype = "";
    
    //add by xkf57421 for XQ[2011]_10_082 begin
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    //add by xkf57421 for XQ[2011]_10_082 begin
    
    
    /**
     * 查询可办理的优惠
     * 
     * @return
     */
    public String queryPriv()
    {
        
        logger.debug("queryPriv start!");
        
        String forward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (pagecount != null)
        {
            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
        }
        
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if (CurMenuid == null)
//        {
//            CurMenuid = "";
//        }
        curMenuId = (curMenuId == null) ? "" : curMenuId;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 

        // 之前需判断用户地区是否与终端地区一致，
        // 判断用户的状态是否为US30
        String custRegion = customer.getRegionID().trim();
        
        // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户  Begion
        //String termRegion = customer.getRegionID().trim();
        String termRegion = terminalInfoPO.getRegion();
        
        String smallRegion = null;
        if(CommonUtilHub.isSplitRegion(termRegion))
        {
            // 获取终端机所在的小地市
            smallRegion = CommonUtilHub.getDictNameByID(terminalInfoPO.getCityOrgid(), "SMALLREGION");
        }
        
        // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户  End
        
        String custStatus = customer.getStatus().trim();
        if (!custRegion.equals(termRegion))
        {
            setErrormessage("用户地区与终端归属地不一致，不能办理优惠！");
            forward = "error";
        }
        //TODO
        // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户  Begion
        else if(null != smallRegion && null != customer.getSmallregion() && !smallRegion.equals(customer.getSmallregion()))
        {
            setErrormessage("用户地区与终端归属地不一致，不能办理优惠！");
            forward = "error";
        }
        // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户  End
        
        else if (custStatus.equals("US30"))
        {
            setErrormessage("您的号码欠费，不能办理优惠！");
            forward = "error";
        }
        else
        {
            Map result = privAcceptBean.qryPrivInfo(terminalInfoPO, customer, curMenuId, "0", favorabletype);
            // CRSet resultSet = new CRSet();
            if (result != null && result.size() > 0)
            {
                CRSet crset = (CRSet)result.get("returnObj");
                
                if (crset != null && crset.GetRowCount() > 0)
                {
                    forward = "qryPrivInfo";
                    // 将得到的结果数据放到页面显示的前提
                    Vector v = new Vector();
                    v.add(new CEntityString("业务组,业务名称,金额"));
                    v.add(crset);
                    
                    // 设置结果集
                    setPrivService(v);
                }
                else
                {
                    // 设置错误信息
                    setErrormessage("您尚无可受理优惠业务。");
                    forward = "error";
                    
                    // 创建错误日志
                    this.createRecLog("SHAcceptPriv", "0", "0", "0", "查询成功，但是用户没有可开通的优惠。");
                }
            }
            else
            {
                // 设置错误信息
                setErrormessage("暂时没有您可使用办理的优惠。");
                forward = "error";
                
                // 创建错误日志
                this.createRecLog("SHAcceptPriv", "0", "0", "0", "暂时没有您可使用办理的优惠。");
            }
        }
        logger.debug("queryPriv end!");
        return forward;
    }
    
    /**
     * 话费充值，选择充值方式
     * 
     * @return
     */
    public String privFeeChargeType()
    {
        
        // 转向投币页面之前需要进行要先进行优惠预受理
        // 成功则投币，失败则到错误页面
        
        logger.debug("privFeeChargeType start");
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        servnumber = customer.getServNumber();
        servRegion = customer.getRegionID();
        
        logger.info("当前用户号码和地区分为：" + servnumber + "-and-" + servRegion);
        
        String isSubmit = "0";
        
        //modified by xkf57421 for bsacAtsvCard begin
        //boolean flag = isPrivCommit(termInfo, customer, CurMenuid, isSubmit, nCode);
        boolean flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode, "Cash");
        //modified by xkf57421 for bsacAtsvCard end
        
        if (flag)
        {
            // 根据终端组自缓存中获取菜单列表
            String groupID = termInfo.getTermgrpid();
            
            List<MenuInfoPO> menus = null;
            
            if (groupID != null && !"".equals(groupID))
            {
                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
            }
            
            // 当页菜单列表
            usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
            
            if (usableTypes == null || usableTypes.size() == 0)
            {
                // 没有可用的充值方式
                setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
                
                // 记录日志
                this.createRecLog(servnumber, "SHAcceptPriv", "0", "0", "1", "暂时没有可用的充值方式");
                
                forward = "failed";
            }
            else
            {
                // 两种或两种以上的充值方式，需要用户选择
                forward = "selectType";
            }
            
        }
        else
        {
            forward = "failed";
        }
        
        logger.debug("privFeeChargeType end");
        
        return forward;
    }
    
    /**
     * 转向投币页面
     * 
     * @return
     */
    public String privcashCharge()
    {
        
        return "cashChargePage";
        
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
        selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
        
        if (tMoney == null || "".equals(tMoney.trim()))
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
     * 办理优惠，提交金额
     * 
     * @return
     */
    public String privCashCommit()
    {
        
        logger.debug("privCashCommit end");
        // 投入币额=优惠金额,成功受理，交费，记录日志，退费
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // change by Lifeng 修改优惠受理打印流程错误 20111121 Begin
        tmpMoney = CurrencyUtil.minus(tMoney, privMoney);
        
        String forward = privCashLog(termInfo, customer, terminalSeq, servnumber, servRegion, curMenuId, nCode,
        // tmpMoney,
                "Cash");
        // change by Lifeng 修改优惠受理打印流程错误 20111121 End
        
        logger.info("投入币额与优惠金额差为：" + tmpMoney);
        
        logger.debug("privCashCommit end");
        return forward;
    }
    
    /**
     * 受理优惠方法 isSubmit 表示提交的方式，0为预受理，1为受理
     * 
     * @return 受理是否成功
     */
    //modified by xkf57421 for add parameter payType:Cash,Card(isSubmit=0||1)
    public boolean isPrivCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuid, String isSubmit,
            String nCode,String payType)
    {
        boolean flag = false;
        //modified by xkf57421 for bsacAtsvCard begin
        //Map result = privAcceptBean.privAcceptCommit(termInfo, customer, curMenuid, isSubmit, nCode);
        Map result = privAcceptBean.privAcceptCommit(termInfo, customer, curMenuid, isSubmit, nCode,payType);
        //modified by xkf57421 for bsacAtsvCard end
        
        //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
        if (result.size() > 1)
        //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
        {
            String status = (String)result.get("status");
            if (status.equals("1"))
            {
                // 当正式受理时，记录成功信息
                if (isSubmit.equals("1"))
                {
                    this.createRecLog("SHAcceptPriv", "0", privMoney, "0", "优惠业务受理成功,费用为" + privMoney + "!");
                }
                flag = true;
            }
            else
            {
                // 当正式受理时记录失败信息
                String errorMessage = (String)result.get("returnMsg");
                setErrormessage(errorMessage);
                if (isSubmit.equals("1"))
                {
                    this.createRecLog("SHAcceptPriv", "0", privMoney, "1", "优惠业务受理失败,费用为" + privMoney + "!");
                }
                flag = false;
            }
        }
        return flag;
    }
    
    /**
     * 缴费的方法
     */
    // change by Lifeng 修改优惠受理打印流程错误 20111121 Begin
    public String privCashLog(TerminalInfoPO termInfo, NserCustomerSimp customer, String terminalNum,
            String servnumber, String region, String curMenuid, String nCode, String type)
    {
        // 发起缴费请求之前首先记录投币日志
        String forward = "error";
        String sign = "";
        String isSubmit = "1";
        boolean flag = false;
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO(); // 增加缴费日志
        PrivLogVO privLogVO = new PrivLogVO(); // 增加优惠受理日志
        
        String logOID = feeChargeService.getChargeLogOID();
        
        // System.out.println("信息为："+logOID+"-"+termInfo.getRegion()+"-"+termInfo.getTermid()+"-"+termInfo.getOperid());
        // System.out.println("信息为："+servnumber+"-"+Constants.PAYBYMONEY+"-"+cashMoney);
        
        selfCardPayLogVO.setChargeLogOID(logOID);
        
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        terminalNum = termInfo.getTermid() + terminalNum;
        if (terminalNum.length() > 20)
        {
            terminalNum = terminalNum.substring(terminalNum.length() - 20);
        }
        
        selfCardPayLogVO.setTerminalSeq(terminalNum);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(region);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("优惠交费之前记录投币日志");
        selfCardPayLogVO.setDealnum("");
        selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
        String payMoney = "0";
        // 记录投币日志
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        privLogVO.setChargeID(logOID); // 缴费的流水
        privLogVO.setRegion(region); // 地区
        privLogVO.setServnumber(servnumber);
        privLogVO.setPrivId("");
        privLogVO.setPrivNcode(nCode);
        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        privLogVO.setChargeType("4"); // 现金缴费
        
        // 投入币额等于优惠金额 直接受理优惠
        if (tmpMoney.equals("0.00"))
        {
            setErrFlag("0"); // 投入币额等于优惠金额
            privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
            privLogVO.setToFee(CommonUtil.yuanToFen(tMoney)); // 投币金额
            privLogVO.setChargeFee("0"); // 投币金额
            //modified by xkf57421 for bsacAtsvCard begin
            //flag = isPrivCommit(termInfo, customer, CurMenuid, isSubmit, nCode);
            flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode, "Cash");
            //modified by xkf57421 for bsacAtsvCard end
            if (flag)
            {
                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
                String accpetDate = temDate.format(new Date());
                selfCardPayLogVO.setRecdate(accpetDate);
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("优惠受理支付成功！");
                setErrormessage("优惠受理支付成功！");
                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                
                // Change by LiFeng
                privLogVO.setRecStatus("02"); // 受理成功
                privLogVO.setRecStatusDesc("投币金额等于优惠金额,优惠受理成功！"); // 描述
                setPrintFlag("0");
                setPayStatus("2");
                setSuccessMessage("优惠受理成功!");
                
                this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "0", "优惠受理失败!投币金额为：" + tMoney + "!");
                // End
                forward = "success";
            }
            else
            {
                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
                String accpetDate = temDate.format(new Date());
                selfCardPayLogVO.setRecdate(accpetDate);
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setDescription("优惠受理支付失败！");
                
                // Begin
                // setErrormessage("优惠支付失败,请凭交易凭条到营业厅办理退款!");
                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                
                // setPriBackMoney(CommonUtil.fenToYuan(tMoney)); // 退还金额
                privLogVO.setRecStatus("01"); // 受理失败
                privLogVO.setRecStatusDesc("投币金额等于优惠金额,优惠受理失败！"); // 描述
                setPrintFlag("1");
                setPayStatus("1");
                setTransResult("优惠受理失败!");
                setErrormessage("优惠受理失败,请凭交易凭条到营业厅办理退款!");
                
                this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "1", "优惠受理失败!投币金额为：" + tMoney + "!");
                // End
                
                forward = "error";
            }
        }
        // Begin
        // 投入币额>优惠金额,成功受理，交费，记录日志
        else if (tmpMoney.indexOf("-") < 0)
        {
            // sign = "1";
        	//modified by xkf57421 for bsacAtsvCard begin
        	//flag = isPrivCommit(termInfo, customer, CurMenuid, isSubmit, nCode);
        	flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode, "Cash");
        	//modified by xkf57421 for bsacAtsvCard end
            if (flag)
            {
                
                payMoney = CommonUtil.yuanToFen(tmpMoney);
                // 记录优惠日志
                privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
                privLogVO.setToFee(CommonUtil.yuanToFen(tMoney)); // 投币金额
                privLogVO.setChargeFee(payMoney); // 缴费金额
                
                // setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                
                // setPriBackMoney(CommonUtil.fenToYuan(payMoney)); // 退还金额
                
                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                // modify begin lwx439898 2017-12-12 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
                // 现金缴费               
                Map resultMap = feeChargeBean.chargeCommit("",termInfo, curMenuid, servnumber, payMoney, type, privLogVO.getPrivLogOID(),"0");
                // modify end lwx439898 2017-12-12 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
                // 交费成功
                // if(false){
                if (resultMap != null && resultMap.size() > 0)
                {
                    Object obj = resultMap.get("returnObj");
                    if (obj instanceof CTagSet)
                    {
                        // 对湖北缴费接口的返回值进行解析
                        CTagSet chargeInfo = (CTagSet)obj;
                        
                        // 提供发票打印功能时，获取发票信息
                        dealNum = (String)chargeInfo.GetValue("dealNum");// 受理编号
                        dealTime = (String)chargeInfo.GetValue("dealTime");// 受理时间
                    }
                    
                    selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                    selfCardPayLogVO.setStatus("11");
                    selfCardPayLogVO.setDescription("优惠受理成功,剩余款已成功转预存款！");
                    setErrormessage("优惠受理成功,剩余款已成功转预存款！");
                    // 记录受理优惠的日志
                    privLogVO.setRecStatus("04"); // 受理成功
                    privLogVO.setRecStatusDesc("投币金额大于优惠金额,优惠受理成功,剩余款已成功转预存款！"); // 描述
                    
                    setPrintFlag("0");
                    setPayStatus("4");
                    setSuccessMessage("优惠受理成功,剩余款已转预存款！");
                    
                    selfCardPayLogVO.setDealnum(dealNum);
                    // setPriBackMoney(payMoney);
                    forward = "success";
                    
                    // change by LiFeng 修改日志记录错误 20110907
                    // 记录缴费成功日志
                    this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "0", "优惠费用转话费:自助终端交费成功!费用为："
                            + payMoney + "!");
                }
                else
                {
                    // 交费失败
                    String payErrDate = sdf1.format(new Date());
                    selfCardPayLogVO.setRecdate(payErrDate);
                    selfCardPayLogVO.setStatus("10");
                    
                    selfCardPayLogVO.setDescription("优惠支付成功,剩余款转预存款失败！");
                    setErrormessage("优惠支付成功,剩余款转话费失败,请凭交易凭条到营业厅退款！");
                    // 记录受理优惠的日志
                    privLogVO.setRecStatus("03"); // 受理成功,转话费失败
                    privLogVO.setRecStatusDesc("投币金额大于优惠金额,优惠受理成功,剩余款转预存款失败！"); // 描述
                    
                    // 缴费失败，打印凭条退钱
                    setPrintFlag("1");
                    setPayStatus("3");
                    setTransResult("优惠受理成功,剩余款转预存款失败!");
                    
                    selfCardPayLogVO.setDealnum("");
                    forward = "error";
                    
                    // change by LiFeng 修改日志记录错误 20110907
                    // 记录缴费失败日志
                    this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "1", "优惠受理成功,剩余款转预存款失败!费用为："
                            + payMoney + "!");
                }
                
            }
            else
            {
                // 受理业务失败，全额退款
                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
                String accpetDate = temDate.format(new Date());
                selfCardPayLogVO.setRecdate(accpetDate);
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setFee(tMoney);
                selfCardPayLogVO.setDescription("优惠受理支付失败！");
                setErrormessage("优惠受理失败,请凭交易凭条到营业厅办理退款!");
                // 记录受理优惠的日志
                privLogVO.setRecStatus("01"); // 受理失败
                privLogVO.setRecStatusDesc("投币金额大于优惠金额,受理失败！"); // 描述
                // 打印小票保存记录
                setErrFlag("0"); // 不打印交易金额
                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                // setPriBackMoney(CommonUtil.fenToYuan(tMoney));
                
                setPrintFlag("1");
                setPayStatus("1");
                setTransResult("优惠受理失败!");
                setErrormessage("优惠受理失败,请凭交易凭条到营业厅办理退款。");
                this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "1", "优惠受理失败!投币金额为：" + tMoney + "!");
                forward = "error";
            }
        }
        // 投入币额<优惠金额,直接转入手机费,退费
        else if (tmpMoney.indexOf("-") >= 0)
        {
            // sign = "2";
            // flag = true;
            payMoney = CommonUtil.yuanToFen(tMoney);
            // 记录优惠日志
            privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
            privLogVO.setToFee(CommonUtil.yuanToFen(tMoney)); // 投币金额
            privLogVO.setChargeFee(payMoney); // 缴费金额
            // 打印小票保存记录
            // setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            
            // setPriBackMoney(CommonUtil.fenToYuan(payMoney));
            // System.out.println(payMoney);
            // if (flag)
            // {
            setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //modify begin lwx439898 2017-12-12 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            // 现金缴费
            Map resultMap = feeChargeBean.chargeCommit("",termInfo, curMenuid, servnumber, payMoney, type, privLogVO.getPrivLogOID(),"0");
            //modify begin lwx439898 2017-12-12 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            // 交费成功
            // if(false){
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // 对湖北缴费接口的返回值进行解析
                    CTagSet chargeInfo = (CTagSet)obj;
                    
                    // 提供发票打印功能时，获取发票信息
                    dealNum = (String)chargeInfo.GetValue("dealNum");// 受理编号
                    dealTime = (String)chargeInfo.GetValue("dealTime");// 受理时间
                }
                
                selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                selfCardPayLogVO.setStatus("11");
                
                selfCardPayLogVO.setDescription("优惠受理费用不足,已成功转预存款！");
                setErrormessage("优惠受理费用不足,已转入手机费！");
                // 记录受理优惠的日志
                privLogVO.setRecStatus("06"); // 受理成功
                privLogVO.setRecStatusDesc("投币金额小于优惠金额,优惠受理不足,已成功转预存款！"); // 描述
                
                selfCardPayLogVO.setDealnum(dealNum);
                
                setPrintFlag("0");
                setPayStatus("6");
                setTransResult("优惠受理费用不足,已转入预存款！");
                setSuccessMessage("优惠受理费用不足,已转入预存款！");
                
                // setPriBackMoney(payMoney);
                forward = "success";
                
                // change by LiFeng 修改日志记录错误 20110907
                // 记录缴费成功日志
                this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "0", "优惠费用转话费:自助终端交费成功!费用为："
                        + payMoney + "!");
            }
            else
            {
                // 交费失败
                String payErrDate = sdf1.format(new Date());
                selfCardPayLogVO.setRecdate(payErrDate);
                selfCardPayLogVO.setStatus("10");
                
                selfCardPayLogVO.setDescription("优惠受理费用不足，转预存款失败！");
                setErrormessage("优惠受理费用不足转话费,缴费失败,请凭交易凭条到营业厅退款！");
                // 记录受理优惠的日志
                privLogVO.setRecStatus("02"); // 受理费不足,转话费失败
                privLogVO.setRecStatusDesc("投币金额小于优惠金额,转预存款失败！"); // 描述
                
                selfCardPayLogVO.setDealnum("");
                
                setPrintFlag("1");
                setPayStatus("5");
                setTransResult("缴费失败!");
                setErrormessage("缴费失败,请凭交易凭条到营业厅退款。");
                
                forward = "error";
                
                // change by LiFeng 修改日志记录错误 20110907
                // 记录缴费失败日志
                this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "1", "优惠费用不足转话费:自助终端缴费失败!费用为："
                        + payMoney + "!");
            }
            // }
            
        }
        //ADD BY LiFeng [XQ[2011]_12_057]电子渠道-自助终端-办理促销奖励活动 
        privLogVO.setRecType(RECTYPE);
        privAcceptHubServiver.createPrivLog(privLogVO);
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        return forward;
        
    }
    
    // change by Lifeng 修改优惠受理打印流程错误 20111121 End
    
    /**
     * 转向银行卡免责声明页面
     * 
     * @return
     */
    public String cardCharge()
    {
        return "cardChargePage";
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
     * 银行卡缴费提交
     * 
     * @return
     */
    public String privCardCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("privCardCommit start");
        }
        
        String forward = "";
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String isSubmit = "1";
        
        logger.info("当前受交的费用为：" + tMoney);
        
        // modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
        tMoney = CommonUtil.formatNumberStr(tMoney);
        // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
        
        String tempMoney = CommonUtil.fenToYuan(tMoney);
        String tmpMoney = CurrencyUtil.minus(tempMoney, privMoney);
        
        logger.info("投入币额与优惠金额差为：" + tmpMoney);
        
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
        
        // String tempTermOperid = termInfo.getOperid();
        try
        {
            // 根据地市获取虚拟工号
            // String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
            
            // 设置各地市特殊工号
            // termInfo.setOperid(chargeOperID);
            
            // session.setAttribute(Constants.TERMINAL_INFO, termInfo);
            
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
            
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            // Change by LiFeng 修改优惠受理成功,但日志表状态都没更新的错误
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            PrivLogVO privLogVO = new PrivLogVO();
            privLogVO.setChargeID(getChargeId()); // 缴费的流水
            privLogVO.setRegion(customer.getRegionID()); // 地区
            privLogVO.setServnumber(servnumber);
            privLogVO.setPrivId("");
            privLogVO.setPrivNcode(nCode);
            privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            privLogVO.setChargeType("1"); // 现金缴费
            
            // 投入币额等于优惠金额 直接受理优惠
            if (tmpMoney.equals("0.00") || tmpMoney.startsWith("-"))
            {
                // 记录优惠受理日志
                privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
                privLogVO.setToFee(tMoney); // 投币金额
                privLogVO.setChargeFee("0"); // 投币金额
                // 打印小票保存记录
                setErrFlag("0"); // 不打印缴费金额
                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                
                // Change by LiFeng 优惠受理打印错误问题 20111121 Begin
                // setPriBackMoney(CommonUtil.fenToYuan(tMoney)); // 活动交易的金额
                // End
                //modified by xkf57421 for bsacAtsvCard begin
                //boolean flag = isPrivCommit(termInfo, customer, CurMenuid, isSubmit, nCode);
                boolean flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode, "Card");
                //modified by xkf57421 for bsacAtsvCard end
                if (flag)
                {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                    String payDate = sdf1.format(new Date());
                    selfCardPayLogVO.setRecdate(payDate);
                    selfCardPayLogVO.setStatus("11");
                    selfCardPayLogVO.setDescription("受理优惠刷卡缴费成功！");
                    selfCardPayLogVO.setDealnum("");
                    // 记录受理优惠的日志
                    // Begin
                    privLogVO.setRecStatus("02"); // 受理成功
                    privLogVO.setRecStatusDesc("金额等于优惠金额,受理成功！"); // 描述
                    forward = "success";
                    
                    setPrintFlag("0");
                    setPayStatus("2");
                    setSuccessMessage("优惠受理成功!");
                    // End
                }
                else
                {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                    String payDate = sdf1.format(new Date());
                    selfCardPayLogVO.setRecdate(payDate);
                    selfCardPayLogVO.setStatus("10");
                    selfCardPayLogVO.setDescription("受理优惠刷卡缴费失败！");
                    selfCardPayLogVO.setDealnum("");
                    // 记录受理优惠的日志
                    // Begin
                    privLogVO.setRecStatus("01"); // 受理失败
                    privLogVO.setRecStatusDesc("银联卡优惠受理失败！"); // 描述
                    
                    setPrintFlag("1");
                    setPayStatus("1");
                    setTransResult("优惠受理失败!");
                    setErrormessage("优惠受理失败,请凭交易凭条到营业厅办理退款!");
                    
                    // End
                    forward = "error";
                }
            }
            // Change by LiFeng 优惠受理打印错误问题 20111121 End
            
            //add by xkf57421 for XQ[2011]_10_082 begin
            selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
            //add by xkf57421 for XQ[2011]_10_082 end
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
            //ADD BY LiFeng [XQ[2011]_12_057]电子渠道-自助终端-办理促销奖励活动 
            privLogVO.setRecType(RECTYPE);
            privAcceptHubServiver.createPrivLog(privLogVO);
            
            this.payType = Constants.PAY_BYCARD;
            
        }
        catch (Exception e)
        {
            logger.error(e);
            e.printStackTrace();
            errormessage = "对不起,业务受理时发生异常,请凭小票到营业厅查询是受理成功,谢谢使用.";
            forward = "cardErrorPage";
        }
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        /*
         * finally { // 将session里的终端信息改回 termInfo.setOperid(tempTermOperid);
         * session.setAttribute(Constants.TERMINAL_INFO, termInfo); }
         */
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
        
        if (logger.isDebugEnabled())
        {
            logger.debug("privCardCommit end");
        }
        return forward;
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
        
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 begin
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
            
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
            // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907
            // String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
            /*
             * if (CommonUtilHub.strIsEmpty(chargeOperID)) { xml = "0";
             * 
             * logger.error("获取银联卡缴费工号异常,chargeOperID:[" + chargeOperID + "]."); } else {
             */
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
            
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            
            String logOID = feeChargeService.getChargeLogOID();
            cardChargeLogVO.setChargeLogOID(logOID);
            
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setTermID(termInfo.getTermid());
            
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
            // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907
            // cardChargeLogVO.setOperID(chargeOperID);
            
            cardChargeLogVO.setOperID(termInfo.getOperid());
            
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
            
            cardChargeLogVO.setServnumber(servnumber);
            cardChargeLogVO.setPayType(payType);
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            cardChargeLogVO.setUnionpayuser("");
            cardChargeLogVO.setUnionpaycode("");
            cardChargeLogVO.setBusiType("");

            //modify begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            //往数据库里存入加密后的银联卡号
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardnumber));
            //modify end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242

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
            
            cardChargeLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
            // 插入缴费日志
            feeChargeService.addChargeLog(cardChargeLogVO);
            xml = "1~~" + logOID;
            // }
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
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
        
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
        
        // Chagne by LiFeng 解决异常不能退卡问题 20110909
        String xml = "";
        try
        {
            writer = response.getWriter();
            // }
            // catch (IOException e)
            // {
            // throw new IOException("扣款之前记录日志失败");
            // }
            
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
            
            // add by xkf57421 for XQ[2011]_10_082 begin
            cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);
            // add by xkf57421 for XQ[2011]_10_082 end
            
            // Chagne by LiFeng 解决异常不能退卡问题 20110909
            // try
            // {
            // 插入缴费日志
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
        
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        
        // String tempTermOperid = termInfo.getOperid();
        
        try
        {
            /*
             * if (Constants.PAY_BYCARD.equals(payType)) { String chargeOperID =
             * CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER); if
             * (CommonUtilHub.strIsEmpty(chargeOperID)) { errormessage = errormessage + "获取不到银联卡缴费指定工号"; } else { //
             * 更改session里的终端对应的工号 termInfo.setOperid(chargeOperID); session.setAttribute(Constants.TERMINAL_INFO,
             * termInfo); } }
             */

            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
            
            this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
            
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(date);
            
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
                
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
                
                selfCardPayLogVO.setTerminalSeq("");
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("00");
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                selfCardPayLogVO.setAcceptType("");
                selfCardPayLogVO.setServRegion(servRegion);
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());
                selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
                
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
                
                //Change by LiFeng [OR_HUB_201112_1021]自助终端-银联对账BUG[BUG转需求] 20120104 Begin
                //银联终端号
                selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
                
                //银联商户号
                selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
                //Change by LiFeng [OR_HUB_201112_1021]自助终端-银联对账BUG[BUG转需求] 20120104 End
                
                // add by xkf57421 for XQ[2011]_10_082 begin
                selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
                // add by xkf57421 for XQ[2011]_10_082 end
                
                feeChargeService.updateChargeLog(selfCardPayLogVO);
            }
            
        }
        // Change by LiFeng 修改异常不能退卡问题 20110909 Begin
        catch (Exception e)
        {
            // 捕获一切异常,使页面必须走退卡页面
            logger.error(e);
            e.printStackTrace();
            errormessage = errormessage + e.getMessage();
        }
        // Change by LiFeng 修改异常不能退卡问题 20110909 End
        
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        /*
         * finally { // 将session里的终端信息改回 termInfo.setOperid(tempTermOperid);
         * session.setAttribute(Constants.TERMINAL_INFO, termInfo); }
         */
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        return "cardErrorPage";
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getPagecount()
    {
        return pagecount;
    }
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        try
        {
            this.errormessage = new String(errormessage.getBytes("ISO-8859-1"), "GKB");
        }
        catch (Exception e)
        {
            this.errormessage = errormessage;
        }
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public Vector getPrivService()
    {
        return privService;
    }
    
    public void setPrivService(Vector privService)
    {
        this.privService = privService;
    }
    
    public PrivAcceptHubBean getPrivAcceptBean()
    {
        return privAcceptBean;
    }
    
    public void setPrivAcceptBean(PrivAcceptHubBean privAcceptBean)
    {
        this.privAcceptBean = privAcceptBean;
    }
    
    public String getNCode()
    {
        return nCode;
    }
    
    public void setNCode(String code)
    {
        nCode = code;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivId()
    {
        return privId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivId(String privId)
    {
        this.privId = privId;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getServRegion()
    {
        return servRegion;
    }
    
    public void setServRegion(String servRegion)
    {
        this.servRegion = servRegion;
    }
    
    public String getPrivMoney()
    {
        return privMoney;
    }
    
    public void setPrivMoney(String privMoney)
    {
        this.privMoney = privMoney;
    }
    
    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }
    
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getTMoney()
    {
        return tMoney;
    }
    
    public void setTMoney(String money)
    {
        tMoney = money;
    }
    
    public String getDealNum()
    {
        return dealNum;
    }
    
    public void setDealNum(String dealNum)
    {
        this.dealNum = dealNum;
    }
    
    public String getDealTime()
    {
        return dealTime;
    }
    
    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }
    
    public FeeChargeHubService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }
    
    public FeeChargeHubBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeHubBean feeChargeBean)
    {
        this.feeChargeBean = feeChargeBean;
    }
    
    public PrivAcceptHubService getPrivAcceptHubServiver()
    {
        return privAcceptHubServiver;
    }
    
    public void setPrivAcceptHubServiver(PrivAcceptHubService privAcceptHubServiver)
    {
        this.privAcceptHubServiver = privAcceptHubServiver;
    }
    
    public String getCardnumber()
    {
        return cardnumber;
    }
    
    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }
    
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
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
    
    public String getChargeLogOID()
    {
        return chargeLogOID;
    }
    
    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }
    
    public String getErrFlag()
    {
        return errFlag;
    }
    
    public void setErrFlag(String errFlag)
    {
        this.errFlag = errFlag;
    }
    
    public String getPriRecDate()
    {
        return priRecDate;
    }
    
    public void setPriRecDate(String priRecDate)
    {
        this.priRecDate = priRecDate;
    }
    
    /*
     * public String getPriBackMoney() { return priBackMoney; }
     * 
     * public void setPriBackMoney(String priBackMoney) { this.priBackMoney = priBackMoney; }
     */

    public String getHasMultiInvoices()
    {
        return hasMultiInvoices;
    }
    
    public void setHasMultiInvoices(String hasMultiInvoices)
    {
        this.hasMultiInvoices = hasMultiInvoices;
    }
    
    public String getChargeId()
    {
        return chargeId;
    }
    
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }
    
    public String getPrintcontext()
    {
        return printcontext;
    }
    
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }
    
    public String getFavorabletype()
    {
        return favorabletype;
    }
    
    public void setFavorabletype(String favorabletype)
    {
        this.favorabletype = favorabletype;
    }
    
    /**
     * @return 返回 nCode
     */
    public String getnCode()
    {
        return nCode;
    }
    
    /**
     * @param 对nCode进行赋值
     */
    public void setnCode(String nCode)
    {
        this.nCode = nCode;
    }
    
    /**
     * @return 返回 tMoney
     */
    public String gettMoney()
    {
        return tMoney;
    }
    
    /**
     * @param 对tMoney进行赋值
     */
    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
    }
    
    /**
     * @return 返回 printFlag
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrintFlag()
    {
        return printFlag;
    }
    
    /**
     * @param 对printFlag进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }
    
    /**
     * @return 返回 payStatus
     */
    public String getPayStatus()
    {
        return payStatus;
    }
    
    /**
     * @param 对payStatus进行赋值
     */
    public void setPayStatus(String payStatus)
    {
        this.payStatus = payStatus;
    }
    
    /**
     * @return 返回 transResult
     */
    public String getTransResult()
    {
        return transResult;
    }
    
    /**
     * @param 对transResult进行赋值
     */
    public void setTransResult(String transResult)
    {
        this.transResult = transResult;
    }
    
    /**
     * @return 返回 privName
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivName()
    {
        return privName;
    }
    
    /**
     * @param 对privName进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivName(String privName)
    {
        this.privName = privName;
    }
    
    /**
     * @return 返回 pDealNum
     */
    public String getpDealNum()
    {
        return pDealNum;
    }
    
    /**
     * @param 对pDealNum进行赋值
     */
    public void setpDealNum(String pDealNum)
    {
        this.pDealNum = pDealNum;
    }
    
    /**
     * @return 返回 pDealTime
     */
    public String getpDealTime()
    {
        return pDealTime;
    }
    
    /**
     * @param 对pDealTime进行赋值
     */
    public void setpDealTime(String pDealTime)
    {
        this.pDealTime = pDealTime;
    }
    
    /**
     * @return 返回 tmpMoney
     */
    public String getTmpMoney()
    {
        return tmpMoney;
    }
    
    /**
     * @param 对tmpMoney进行赋值
     */
    public void setTmpMoney(String tmpMoney)
    {
        this.tmpMoney = tmpMoney;
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
}
