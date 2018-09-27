package com.customize.cq.selfsvc.privAccept.action;

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

import com.customize.cq.selfsvc.bean.FeeChargeBean;
import com.customize.cq.selfsvc.bean.PrivAcceptCQBean;
import com.customize.cq.selfsvc.charge.service.FeeChargeService;
import com.customize.cq.selfsvc.common.CommonUtilCQ;
import com.customize.cq.selfsvc.common.ConstantsCQ;
import com.customize.cq.selfsvc.privAccept.model.PrivLogVO;
import com.customize.cq.selfsvc.privAccept.service.PrivAcceptCQService;
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

/**
 * 优惠查询与办理
 * 
 * @author Administrator
 * 
 */
public class PrivAcceptCQAction extends BaseAction
{
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单
//    private String curMenuId;
//    // end zKF66389 findbus清零
//    
//    // begin zKF66389 findbus清零
//    // 页号
//    private String pageCount;
//    // end zKF66389 findbus清零
//    
//    // begin zKF66389 findbus清零
//    // 错误信息
//    private String errorMessage = "";
//    // end zKF66389 findbus清零
//    
//    // 成功信息
//    private String successMessage;
//    
//    // 优惠业务结果集
//    private Vector privService;
//    
//    // 接口调用
//    private PrivAcceptCQBean privAcceptBean;
//    
//    private FeeChargeBean feeChargeBean;
//    
//    private FeeChargeService feeChargeService;
//    
//    private PrivAcceptCQService privAcceptCQServiver;
//    
//    private static Log logger = LogFactory.getLog(PrivAcceptCQAction.class);
//    
//    // 受理优惠的ID
//    private String nCode;
//    
//    // 受理优惠的ID
//    private String privId;
//    
//    private List usableTypes = null;
//    
//    // begin zKF66389 findbus清零
//    private String servNumber;
//    // end zKF66389 findbus清零
//    
//    private String servRegion;
//    
//    // 优惠应缴费用
//    private String privMoney;
//    
//    private String payType;
//    
//    private String errorType = "";
//    
//    // 终端机流水号
//    private String terminalSeq;
//    
//    // 用户实缴费用
//    private String tMoney;
//    
//    // BOSS流水
//    private String dealNum = "";
//    
//    // 交易时间
//    private String dealTime = "";
//    
//    // 用户刷卡卡号
//    private String cardnumber;
//    
//    // 需要退卡
//    private String needReturnCard = "";
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
//    // begin zKF66389 findbus清零
//    
//    // 终端批次号
//    private String batchnum;
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
//    // 银联实际扣款金额，单位（分）
//    private String unionpayfee;
//    
//    // 银联实际扣款时间
//    private String unionpaytime;
//    
//    // 缴费日志对应的oid
//    private String chargeLogOID = "";
//    
//    // 交费失败的类型
//    private String errFlag = "1";
//    
//    // 活动受理时间
//    private String priRecDate = "";
//    
//    // 应该退款金额
//    private String priBackMoney = "0";
//    
//    private String hasMultiInvoices = "false";
//    
//    // 记录银行卡的交费的ID
//    private String chargeId;
//    
//    private String printcontext = "";// 银联打印信息
//    
//    /**
//     * 查询可办理的优惠
//     * 
//     * @return
//     */
//    public String queryPriv()
//    {
//        
//        logger.debug("queryPriv start!");
//        
//        String forward = "error";
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        if (pageCount != null)
//        {
//            this.getRequest().setAttribute("pagecount", pageCount.split(",")[0]);
//        }
//        
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        // 之前需判断用户地区是否与终端地区一致，
//        // 判断用户的状态是否为US30
//        String custRegion = customer.getRegionID().trim();
//        String termRegion = customer.getRegionID().trim();
//        String custStatus = customer.getStatus().trim();
//        if (!custRegion.equals(termRegion))
//        {
//        	setErrorMessage("用户地区与终端归属地不一致，不能办理优惠！");
//            forward = "error";
//        }
//        else if (custStatus.equals("US30"))
//        {
//        	setErrorMessage("您的号码欠费，不能办理优惠！");
//            forward = "error";
//        }
//        else
//        {
//            Map result = privAcceptBean.qryPrivInfo(terminalInfoPO, customer, curMenuId, "0");
//            // CRSet resultSet = new CRSet();
//            if (result != null && result.size() > 0)
//            {
//                CRSet crset = (CRSet)result.get("returnObj");
//                
//                if (crset != null && crset.GetRowCount() > 0)
//                {
//                    forward = "qryPrivInfo";
//                    // 将得到的结果数据放到页面显示的前提
//                    Vector v = new Vector();
//                    v.add(new CEntityString("业务组,业务名称,金额"));
//                    v.add(crset);
//                    
//                    // 设置结果集
//                    setPrivService(v);
//                }
//                else
//                {
//                    // 设置错误信息
//                	setErrorMessage("您尚无可受理优惠业务。");
//                    forward = "error";
//                    
//                    // 创建错误日志
//                    this.createRecLog("SHAcceptPriv", "0", "0", "0", "查询成功，但是用户没有可开通的优惠。");
//                }
//            }
//            else
//            {
//                // 设置错误信息
//            	setErrorMessage("暂时没有您可使用办理的优惠。");
//                forward = "error";
//                
//                // 创建错误日志
//                this.createRecLog("SHAcceptPriv", "0", "0", "0", "暂时没有您可使用办理的优惠。");
//            }
//        }
//        logger.debug("queryPriv end!");
//        return forward;
//    }
//    
//    /**
//     * 话费充值，选择充值方式
//     * 
//     * @return
//     */
//    public String privFeeChargeType()
//    {
//        
//        // 转向投币页面之前需要进行要先进行优惠预受理
//        // 成功则投币，失败则到错误页面
//        
//        logger.debug("privFeeChargeType start");
//        String forward = "";
//        
//        HttpServletRequest request = this.getRequest();
//        
//        HttpSession session = request.getSession();
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        servNumber = customer.getServNumber();
//        servRegion = customer.getRegionID();
//        
//        logger.info("当前用户号码和地区分为：" + servNumber + "-and-" + servRegion);
//        
//        String isSubmit = "0";
//        
//        boolean flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode);
//        
//        if (flag)
//        {
//            // 根据终端组自缓存中获取菜单列表
//            String groupID = termInfo.getTermgrpid();
//            
//            List<MenuInfoPO> menus = null;
//            
//            if (groupID != null && !"".equals(groupID))
//            {
//                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
//            }
//            
//            // 当页菜单列表
//            usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
//            
//            if (usableTypes == null || usableTypes.size() == 0)
//            {
//                // 没有可用的充值方式
//            	setErrorMessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
//                
//                // 记录日志
//                this.createRecLog(servNumber, "SHAcceptPriv", "0", "0", "1", "暂时没有可用的充值方式");
//                
//                forward = "failed";
//            }
//            else
//            {
//                // 两种或两种以上的充值方式，需要用户选择
//                forward = "selectType";
//            }
//            
//        }
//        else
//        {
//            forward = "failed";
//        }
//        
//        logger.debug("privFeeChargeType end");
//        
//        return forward;
//    }
//    
//    /**
//     * 转向投币页面
//     * 
//     * @return
//     */
//    public String privcashCharge()
//    {
//        
//        return "cashChargePage";
//        
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
//        selfCardPayLogVO.setAcceptType("");
//        selfCardPayLogVO.setServRegion(servRegion);
//        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//        selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
//        
//        if (tMoney == null || "".equals(tMoney.trim()))
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
//     * 办理优惠，提交金额
//     * 
//     * @return
//     */
//    public String privCashCommit()
//    {
//        
//        logger.debug("privCashCommit end");
//        // 投入币额=优惠金额,成功受理，交费，记录日志，退费
//        
//        HttpSession session = getRequest().getSession();
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        String tmpMoney = CurrencyUtil.minus(tMoney, privMoney);
//        
//        String forward = privCashLog(termInfo,
//                customer,
//                terminalSeq,
//                servNumber,
//                servRegion,
//                curMenuId,
//                nCode,
//                tmpMoney,
//                "Cash");
//        
//        logger.info("投入币额与优惠金额差为：" + tmpMoney);
//        
//        logger.debug("privCashCommit end");
//        return forward;
//    }
//    
//    /**
//     * 受理优惠方法 isSubmit 表示提交的方式，0为预受理，1为受理
//     * 
//     * @return 受理是否成功
//     */
//    public boolean isPrivCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String isSubmit,
//            String nCode)
//    {
//        boolean flag = false;
//        Map result = privAcceptBean.privAcceptCommit(termInfo, customer, curMenuId, isSubmit, nCode);
//        
//        //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
//        if (result.size() > 1)
//        //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
//        {
//            String status = (String)result.get("status");
//            if (status.equals("1"))
//            {
//                // 当正式受理时，记录成功信息
//                if (isSubmit.equals("1"))
//                {
//                    this.createRecLog("SHAcceptPriv", "0", privMoney, "0", "优惠业务受理成功,费用为" + privMoney + "!");
//                }
//                flag = true;
//            }
//            else
//            {
//                // 当正式受理时记录失败信息
//                String errorMessage = (String)result.get("returnMsg");
//                setErrorMessage(errorMessage);
//                if (isSubmit.equals("1"))
//                {
//                    this.createRecLog("SHAcceptPriv", "0", privMoney, "1", "优惠业务受理失败,费用为" + privMoney + "!");
//                }
//                flag = false;
//            }
//        }
//        return flag;
//    }
//    
//    /**
//     * 缴费的方法
//     */
//    public String privCashLog(TerminalInfoPO termInfo, NserCustomerSimp customer, String terminalNum,
//            String servnumber, String region, String curMenuId, String nCode, String cashMoney, String type)
//    {
//        // 发起缴费请求之前首先记录投币日志
//        String forward = "error";
//        String sign = "";
//        String isSubmit = "1";
//        boolean flag = false;
//        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO(); // 增加缴费日志
//        PrivLogVO privLogVO = new PrivLogVO(); // 增加优惠受理日志
//        
//        String logOID = feeChargeService.getChargeLogOID();
//        
//        // System.out.println("信息为："+logOID+"-"+termInfo.getRegion()+"-"+termInfo.getTermid()+"-"+termInfo.getOperid());
//        // System.out.println("信息为："+servnumber+"-"+Constants.PAYBYMONEY+"-"+cashMoney);
//        
//        selfCardPayLogVO.setChargeLogOID(logOID);
//        
//        selfCardPayLogVO.setRegion(termInfo.getRegion());
//        selfCardPayLogVO.setTermID(termInfo.getTermid());
//        selfCardPayLogVO.setOperID(termInfo.getOperid());
//        selfCardPayLogVO.setServnumber(servnumber);
//        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
//        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//        
//        terminalNum = termInfo.getTermid() + terminalNum;
//        if (terminalNum.length() > 20)
//        {
//            terminalNum = terminalNum.substring(terminalNum.length() - 20);
//        }
//        
//        selfCardPayLogVO.setTerminalSeq(terminalNum);
//        
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(new Date());
//        
//        selfCardPayLogVO.setRecdate(payDate);
//        selfCardPayLogVO.setAcceptType("");
//        selfCardPayLogVO.setServRegion(region);
//        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//        selfCardPayLogVO.setStatus("01");
//        selfCardPayLogVO.setDescription("优惠交费之前记录投币日志");
//        selfCardPayLogVO.setDealnum("");
//        selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
//        String payMoney = "0";
//        // 记录投币日志
//        feeChargeService.addChargeLog(selfCardPayLogVO);
//        
//        privLogVO.setChargeID(logOID); // 缴费的流水
//        privLogVO.setRegion(region); // 地区
//        privLogVO.setServNumber(servnumber);
//        privLogVO.setPrivId("");
//        privLogVO.setPrivNcode(nCode);
//        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        privLogVO.setChargeType("4"); // 现金缴费
//        
//        // 投入币额等于优惠金额 直接受理优惠
//        if (cashMoney.equals("0.00"))
//        {
//            setErrFlag("0"); // 投入币额等于优惠金额
//            privLogVO.setPrivFee(CommonUtil.yuanToFen(cashMoney));// 受理优惠
//            privLogVO.setToFee(CommonUtil.yuanToFen(cashMoney)); // 投币金额
//            privLogVO.setChargeFee("0"); // 投币金额
//            flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode);
//            if (flag)
//            {
//                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
//                String accpetDate = temDate.format(new Date());
//                selfCardPayLogVO.setRecdate(accpetDate);
//                selfCardPayLogVO.setStatus("11");
//                selfCardPayLogVO.setDescription("优惠受理支付成功！");
//                setErrorMessage("优惠受理支付成功！");
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                privLogVO.setRecStatus("05"); // 受理成功
//                privLogVO.setRecStatusDesc("投币金额等于优惠金额,优惠受理成功！"); // 描述
//                forward = "success";
//            }
//            else
//            {
//                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
//                String accpetDate = temDate.format(new Date());
//                selfCardPayLogVO.setRecdate(accpetDate);
//                selfCardPayLogVO.setStatus("10");
//                selfCardPayLogVO.setDescription("优惠受理支付失败！");
//                setErrorMessage("优惠支付失败,请凭交易凭条到营业厅办理退款!");
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                setPriBackMoney(CommonUtil.fenToYuan(tMoney)); // 退还金额
//                privLogVO.setRecStatus("01"); // 受理失败
//                privLogVO.setRecStatusDesc("投币金额等于优惠金额,优惠受理失败！"); // 描述
//                forward = "error";
//            }
//        }
//        else
//        {
//            // 投入币额>优惠金额,成功受理，交费，记录日志
//            if (cashMoney.indexOf("-") < 0)
//            {
//                sign = "1";
//                flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode);
//                payMoney = CommonUtil.yuanToFen(cashMoney);
//                // 记录优惠日志
//                privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
//                privLogVO.setToFee(CommonUtil.yuanToFen(tMoney)); // 投币金额
//                privLogVO.setChargeFee(payMoney); // 投币金额
//                // 打印小票保存记录
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                setPriBackMoney(CommonUtil.fenToYuan(payMoney)); // 退还金额
//            }
//            // 投入币额<优惠金额,直接转入手机费,退费
//            if (cashMoney.indexOf("-") >= 0)
//            {
//                sign = "2";
//                flag = true;
//                payMoney = CommonUtil.yuanToFen(tMoney);
//                // 记录优惠日志
//                privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
//                privLogVO.setToFee(CommonUtil.yuanToFen(tMoney)); // 投币金额
//                privLogVO.setChargeFee(payMoney); // 投币金额
//                // 打印小票保存记录
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                setPriBackMoney(CommonUtil.fenToYuan(payMoney));
//            }
//            // System.out.println(payMoney);
//            if (flag)
//            {
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                // 现金缴费
//                String bankNo = "20";
//                // 山东接口 -- 发票打印标志，0＝不打印 1＝打印
//                String invoiceType = "1";
//                // 现金缴费
//                Map resultMap = feeChargeBean.chargeCommit(termInfo,
//                		curMenuId,
//                        servnumber,
//                        bankNo,
//                        payDate,
//                        terminalSeq,
//                        tMoney,
//                        "",
//                        invoiceType,
//                        "",
//                        "");
//                // 交费成功
//                // if(false){
//                if (resultMap != null && resultMap.size() > 0)
//                {
//                    Object obj = resultMap.get("returnObj");
//                    if (obj instanceof CTagSet)
//                    {
//                        // 对湖北缴费接口的返回值进行解析
//                        CTagSet chargeInfo = (CTagSet)obj;
//                        
//                        // 提供发票打印功能时，获取发票信息
//                        dealNum = (String)chargeInfo.GetValue("dealNum");// 受理编号
//                        dealTime = (String)chargeInfo.GetValue("dealTime");// 受理时间
//                    }
//                    
//                    selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
//                    selfCardPayLogVO.setStatus("11");
//                    if (sign.equals("1"))
//                    {
//                        selfCardPayLogVO.setDescription("优惠受理成功,剩余款已成功转预存款！");
//                        setErrorMessage("优惠受理成功,剩余款已成功转预存款！");
//                        // 记录受理优惠的日志
//                        privLogVO.setRecStatus("04"); // 受理成功
//                        privLogVO.setRecStatusDesc("投币金额大于优惠金额,优惠受理成功,剩余款已成功转预存款！"); // 描述
//                    }
//                    else
//                    {
//                        selfCardPayLogVO.setDescription("优惠受理费用不足,已成功转预存款！");
//                        setErrorMessage("优惠受理费用不足,已转入手机费！");
//                        // 记录受理优惠的日志
//                        privLogVO.setRecStatus("06"); // 受理成功
//                        privLogVO.setRecStatusDesc("投币金额小于优惠金额,优惠受理不足,已成功转预存款！"); // 描述
//                    }
//                    selfCardPayLogVO.setDealnum(dealNum);
//                    // setPriBackMoney(payMoney);
//                    forward = "success";
//                    
//                    // change by LiFeng 修改日志记录错误 20110907
//                    // 记录缴费成功日志
//                    this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "0", "优惠费用转话费:自助终端交费成功!费用为："
//                            + payMoney + "!");
//                }
//                else
//                {
//                    // 交费失败
//                    String payErrDate = sdf1.format(new Date());
//                    selfCardPayLogVO.setRecdate(payErrDate);
//                    selfCardPayLogVO.setStatus("10");
//                    
//                    if (sign.equals("1"))
//                    {
//                        selfCardPayLogVO.setDescription("优惠支付成功,剩余款转预存款失败！");
//                        setErrorMessage("优惠支付成功,剩余款转话费失败,请凭交易凭条到营业厅退款！");
//                        // 记录受理优惠的日志
//                        privLogVO.setRecStatus("03"); // 受理成功,转话费失败
//                        privLogVO.setRecStatusDesc("投币金额大于优惠金额,优惠受理成功,剩余款转预存款失败！"); // 描述
//                    }
//                    else
//                    {
//                        selfCardPayLogVO.setDescription("优惠受理费用不足，转预存款失败！");
//                        setErrorMessage("优惠受理费用不足转话费,缴费失败,请凭交易凭条到营业厅退款！");
//                        // 记录受理优惠的日志
//                        privLogVO.setRecStatus("02"); // 受理费不足,转话费失败
//                        privLogVO.setRecStatusDesc("投币金额小于优惠金额,转预存款失败！"); // 描述
//                    }
//                    selfCardPayLogVO.setDealnum("");
//                    forward = "error";
//                    
//                    // change by LiFeng 修改日志记录错误 20110907
//                    // 记录缴费失败日志
//                    this.createRecLog(servnumber, "PRIV_PAYTYPE_CASH", payMoney, "0", "1", "优惠费用不足转话费:自助终端缴费失败!费用为："
//                            + payMoney + "!");
//                }
//            }
//            else
//            {
//                // 受理业务失败，全额退款
//                SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
//                String accpetDate = temDate.format(new Date());
//                selfCardPayLogVO.setRecdate(accpetDate);
//                selfCardPayLogVO.setStatus("10");
//                selfCardPayLogVO.setFee(tMoney);
//                selfCardPayLogVO.setDescription("优惠受理支付失败！");
//                setErrorMessage("优惠受理失败,请凭交易凭条到营业厅办理退款!");
//                // 记录受理优惠的日志
//                privLogVO.setRecStatus("01"); // 受理失败
//                privLogVO.setRecStatusDesc("投币金额大于优惠金额,受理失败！"); // 描述
//                // 打印小票保存记录
//                setErrFlag("0"); // 不打印交易金额
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                setPriBackMoney(CommonUtil.fenToYuan(tMoney));
//                forward = "error";
//            }
//        }
//        privAcceptCQServiver.createPrivLog(privLogVO);
//        feeChargeService.updateChargeLog(selfCardPayLogVO);
//        return forward;
//        
//    }
//    
//    /**
//     * 转向银行卡免责声明页面
//     * 
//     * @return
//     */
//    public String cardCharge()
//    {
//        return "cardChargePage";
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
//     * 银行卡缴费提交
//     * 
//     * @return
//     */
//    public String privCardCommit()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("privCardCommit start");
//        }
//        
//        String forward = "";
//        
//        HttpSession session = getRequest().getSession();
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        String isSubmit = "1";
//        
//        logger.info("当前受交的费用为：" + tMoney);
//        
//        int index = tMoney.lastIndexOf("0");
//        if (index != -1)
//        {
//            tMoney = tMoney.substring(index + 1);
//        }
//        String tempMoney = CommonUtil.fenToYuan(tMoney);
//        String tmpMoney = CurrencyUtil.minus(tempMoney, privMoney);
//        
//        logger.info("投入币额与优惠金额差为：" + tmpMoney);
//        
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
//        
//        String tempTermOperid = termInfo.getOperid();
//        try
//        {
//            // 根据地市获取虚拟工号
//            String chargeOperID = CommonUtilCQ.getDictNameByID(termInfo.getRegion(), ConstantsCQ.CARD_CHARGE_OPER);
//            
//            // 设置各地市特殊工号
//            termInfo.setOperid(chargeOperID);
//            
//            session.setAttribute(Constants.TERMINAL_INFO, termInfo);
//            
//            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//            //Change by LiFeng 修改优惠受理成功,但日志表状态都没更新的错误
//            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
//            PrivLogVO privLogVO = new PrivLogVO();
//            privLogVO.setChargeID(getChargeId()); // 缴费的流水
//            privLogVO.setRegion(customer.getRegionID()); // 地区
//            privLogVO.setServNumber(servNumber);
//            privLogVO.setPrivId("");
//            privLogVO.setPrivNcode(nCode);
//            privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            privLogVO.setChargeType("1"); // 现金缴费
//            
//            // 投入币额等于优惠金额 直接受理优惠
//            if (tmpMoney.equals("0.00") || tmpMoney.startsWith("-"))
//            {
//                // 记录优惠受理日志
//                privLogVO.setPrivFee(CommonUtil.yuanToFen(privMoney));// 受理优惠
//                privLogVO.setToFee(tMoney); // 投币金额
//                privLogVO.setChargeFee("0"); // 投币金额
//                // 打印小票保存记录
//                setErrFlag("0"); // 不打印缴费金额
//                setPriRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                setPriBackMoney(CommonUtil.fenToYuan(tMoney)); // 活动交易的金额
//                boolean flag = isPrivCommit(termInfo, customer, curMenuId, isSubmit, nCode);
//                if (flag)
//                {
//                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//                    String payDate = sdf1.format(new Date());
//                    selfCardPayLogVO.setRecdate(payDate);
//                    selfCardPayLogVO.setStatus("11");
//                    selfCardPayLogVO.setDescription("受理优惠刷卡缴费成功！");
//                    selfCardPayLogVO.setDealnum("");
//                    // 记录受理优惠的日志
//                    privLogVO.setRecStatus("05"); // 受理成功
//                    privLogVO.setRecStatusDesc("金额等于优惠金额,受理成功！"); // 描述
//                    forward = "success";
//                }
//                else
//                {
//                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//                    String payDate = sdf1.format(new Date());
//                    selfCardPayLogVO.setRecdate(payDate);
//                    selfCardPayLogVO.setStatus("10");
//                    selfCardPayLogVO.setDescription("受理优惠刷卡缴费失败！");
//                    selfCardPayLogVO.setDealnum("");
//                    // 记录受理优惠的日志
//                    privLogVO.setRecStatus("01"); // 受理失败
//                    privLogVO.setRecStatusDesc("投币金额等于优惠金额,受理失败！"); // 描述
//                    forward = "error";
//                }
//            }
//            feeChargeService.updateChargeLog(selfCardPayLogVO);
//            privAcceptCQServiver.createPrivLog(privLogVO);
//            
//            this.payType = Constants.PAY_BYCARD;
//            
//        }
//        catch (Exception e)
//        {
//            logger.error(e);
//            e.printStackTrace();
//            errorMessage = "对不起,业务受理时发生异常,请凭小票到营业厅查询是受理成功,谢谢使用.";
//            forward = "cardErrorPage";
//        }
//        finally
//        {
//            // 将session里的终端信息改回
//            termInfo.setOperid(tempTermOperid);
//            session.setAttribute(Constants.TERMINAL_INFO, termInfo);
//        }
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("privCardCommit end");
//        }
//        return forward;
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
//        String xml = "";
//        
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 begin
//        try
//        {
//            writer = response.getWriter();
//            
//            HttpSession session = request.getSession();
//            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//            
//            String payType = (String)request.getParameter("paytype");
//            String status = (String)request.getParameter("status");
//            String description = (String)request.getParameter("description");
//            description = java.net.URLDecoder.decode(description, "UTF-8");
//            String posNum = (String)request.getParameter("posnum");
//            String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
//            
//            // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907
//            String chargeOperID = CommonUtilCQ.getDictNameByID(termInfo.getRegion(), ConstantsCQ.CARD_CHARGE_OPER);
//            if (CommonUtilCQ.strIsEmpty(chargeOperID))
//            {
//                xml = "0";
//                
//                logger.error("获取银联卡缴费工号异常,chargeOperID:[" + chargeOperID + "].");
//            }
//            else
//            {
//                
//                // 组装日志对象
//                CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
//                
//                String logOID = feeChargeService.getChargeLogOID();
//                cardChargeLogVO.setChargeLogOID(logOID);
//                
//                cardChargeLogVO.setRegion(termInfo.getRegion());
//                cardChargeLogVO.setTermID(termInfo.getTermid());
//                
//                // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907
//                cardChargeLogVO.setOperID(chargeOperID);
//                
//                cardChargeLogVO.setServnumber(servNumber);
//                cardChargeLogVO.setPayType(payType);
//                cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//                cardChargeLogVO.setUnionpayuser("");
//                cardChargeLogVO.setUnionpaycode("");
//                cardChargeLogVO.setBusiType("");
//                cardChargeLogVO.setCardnumber(cardnumber);
//                cardChargeLogVO.setBatchnum("");
//                cardChargeLogVO.setAuthorizationcode("");
//                cardChargeLogVO.setBusinessreferencenum("");
//                cardChargeLogVO.setUnionpaytime("");
//                cardChargeLogVO.setVouchernum("");
//                cardChargeLogVO.setUnionpayfee("");
//                cardChargeLogVO.setTerminalSeq(terminalSeq);
//                
//                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//                String payDate = sdf1.format(new Date());
//                cardChargeLogVO.setRecdate(payDate);
//                
//                cardChargeLogVO.setStatus(status);
//                cardChargeLogVO.setDescription(description);
//                cardChargeLogVO.setDealnum("");
//                cardChargeLogVO.setAcceptType("");
//                cardChargeLogVO.setServRegion(servRegion);
//                cardChargeLogVO.setOrgID(termInfo.getOrgid());
//                cardChargeLogVO.setPosNum(posNum);
//                cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
//                
//                cardChargeLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
//                // 插入缴费日志
//                feeChargeService.addChargeLog(cardChargeLogVO);
//                xml = "1~~" + logOID;
//            }
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
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
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
//        
//        // Chagne by LiFeng 解决异常不能退卡问题 20110909
//        String xml = "";
//        try
//        {
//            writer = response.getWriter();
//            // }
//            // catch (IOException e)
//            // {
//            // throw new IOException("扣款之前记录日志失败");
//            // }
//            
//            String status = (String)request.getParameter("status");
//            String description = (String)request.getParameter("description");
//            description = java.net.URLDecoder.decode(description, "UTF-8");
//            
//            // 组装日志对象
//            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
//            cardChargeLogVO.setChargeLogOID(chargeLogOID);
//            cardChargeLogVO.setUnionpayuser(unionpayuser);
//            cardChargeLogVO.setUnionpaycode(unionpaycode);
//            
//            busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
//            cardChargeLogVO.setBusiType(busiType);
//            
//            cardChargeLogVO.setBatchnum(batchnum);
//            cardChargeLogVO.setAuthorizationcode(authorizationcode);
//            cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
//            cardChargeLogVO.setUnionpaytime(unionpaytime);
//            cardChargeLogVO.setVouchernum(vouchernum);
//            
//            int index = unionpayfee.lastIndexOf("0");
//            if (index != -1)
//            {
//                unionpayfee = unionpayfee.substring(index + 1);
//            }
//            cardChargeLogVO.setUnionpayfee(unionpayfee);
//            
//            cardChargeLogVO.setStatus(status);
//            cardChargeLogVO.setDescription(description);
//            
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//            String payDate = sdf1.format(new Date());
//            cardChargeLogVO.setRecdate(payDate);
//            
//            // Chagne by LiFeng 解决异常不能退卡问题 20110909
//            // try
//            // {
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
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
//        
//        HttpSession session = getRequest().getSession();
//        
//        // 终端信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        String tempTermOperid = termInfo.getOperid();
//        
//        try
//        {
//            if (Constants.PAY_BYCARD.equals(payType))
//            {
//                String chargeOperID = CommonUtilCQ.getDictNameByID(termInfo.getRegion(), ConstantsCQ.CARD_CHARGE_OPER);
//                if (CommonUtilCQ.strIsEmpty(chargeOperID))
//                {
//                    errorMessage = errorMessage + "获取不到银联卡缴费指定工号";
//                }
//                else
//                {
//                    // 更改session里的终端对应的工号
//                    termInfo.setOperid(chargeOperID);
//                    session.setAttribute(Constants.TERMINAL_INFO, termInfo);
//                }
//            }
//            
//            this.createRecLog(servNumber, payType, "0", "0", "1", errorMessage);
//            
//            Date date = new Date();
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//            String payDate = sdf1.format(date);
//            
//            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
//            
//            if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
//            {
//                selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
//                selfCardPayLogVO.setRegion(termInfo.getRegion());
//                selfCardPayLogVO.setTermID(termInfo.getTermid());
//                selfCardPayLogVO.setOperID(termInfo.getOperid());
//                selfCardPayLogVO.setServnumber(servNumber);
//                if (Constants.PAY_BYCASH.equals(payType))
//                {
//                    selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
//                }
//                else if (Constants.PAY_BYCARD.equals(payType))
//                {
//                    selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
//                }
//                
//                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
//                
//                selfCardPayLogVO.setTerminalSeq("");
//                selfCardPayLogVO.setRecdate(payDate);
//                selfCardPayLogVO.setStatus("00");
//                selfCardPayLogVO.setDescription(errorMessage);
//                selfCardPayLogVO.setDealnum("");
//                selfCardPayLogVO.setAcceptType("");
//                selfCardPayLogVO.setServRegion(servRegion);
//                selfCardPayLogVO.setOrgID(termInfo.getOrgid());
//                selfCardPayLogVO.setRecType("favourable");// 业务类型（phone：话费缴费 favourable：优惠缴费）
//                
//                feeChargeService.addChargeLog(selfCardPayLogVO);
//            }
//            // 更新日志
//            else
//            {
//                selfCardPayLogVO.setChargeLogOID(chargeLogOID);
//                
//                if (tMoney == null || "".equals(tMoney.trim()))
//                {
//                    selfCardPayLogVO.setStatus("00");
//                }
//                else
//                {
//                    selfCardPayLogVO.setStatus("10");
//                }
//                selfCardPayLogVO.setDescription(errorMessage);
//                selfCardPayLogVO.setDealnum("");
//                
//                feeChargeService.updateChargeLog(selfCardPayLogVO);
//            }
//            
//        }
//        // Change by LiFeng 修改异常不能退卡问题 20110909 Begin
//        catch (Exception e)
//        {
//            // 捕获一切异常,使页面必须走退卡页面
//            logger.error(e);
//            e.printStackTrace();
//            errorMessage = errorMessage + e.getMessage();
//        }
//        // Change by LiFeng 修改异常不能退卡问题 20110909 End
//        finally
//        {
//            // 将session里的终端信息改回
//            termInfo.setOperid(tempTermOperid);
//            session.setAttribute(Constants.TERMINAL_INFO, termInfo);
//        }
//        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("goCardError End");
//        }
//        
//        return "cardErrorPage";
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
//	
//    
//    public String getErrorMessage()
//    {
//        return errorMessage;
//    }
//    
//    public void setErrorMessage(String errormessage)
//    {
//        try
//        {
//            this.errorMessage = new String(errormessage.getBytes("ISO-8859-1"), "GKB");
//        }
//        catch (Exception e)
//        {
//            this.errorMessage = errormessage;
//        }
//    }
//    
//    public String getSuccessMessage()
//    {
//        return successMessage;
//    }
//    
//    public void setSuccessMessage(String successMessage)
//    {
//        this.successMessage = successMessage;
//    }
//    
//    public Vector getPrivService()
//    {
//        return privService;
//    }
//    
//    public void setPrivService(Vector privService)
//    {
//        this.privService = privService;
//    }
//    
//    public PrivAcceptCQBean getPrivAcceptBean()
//    {
//        return privAcceptBean;
//    }
//    
//    public void setPrivAcceptBean(PrivAcceptCQBean privAcceptBean)
//    {
//        this.privAcceptBean = privAcceptBean;
//    }
//    
//    public String getNCode()
//    {
//        return nCode;
//    }
//    
//    public void setNCode(String code)
//    {
//        nCode = code;
//    }
//    
//    public String getPrivId()
//    {
//        return privId;
//    }
//    
//    public void setPrivId(String privId)
//    {
//        this.privId = privId;
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
//    public String getServNumber() {
//		return servNumber;
//	}
//
//	public void setServNumber(String servNumber) {
//		this.servNumber = servNumber;
//	}
//
//	public String getServRegion()
//    {
//        return servRegion;
//    }
//    
//    public void setServRegion(String servRegion)
//    {
//        this.servRegion = servRegion;
//    }
//    
//    public String getPrivMoney()
//    {
//        return privMoney;
//    }
//    
//    public void setPrivMoney(String privMoney)
//    {
//        this.privMoney = privMoney;
//    }
//    
//    public String getPayType()
//    {
//        return payType;
//    }
//    
//    public void setPayType(String payType)
//    {
//        this.payType = payType;
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
//    public String getTMoney()
//    {
//        return tMoney;
//    }
//    
//    public void setTMoney(String money)
//    {
//        tMoney = money;
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
//    public String getDealTime()
//    {
//        return dealTime;
//    }
//    
//    public void setDealTime(String dealTime)
//    {
//        this.dealTime = dealTime;
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
//    public PrivAcceptCQService getPrivAcceptCQServiver()
//    {
//        return privAcceptCQServiver;
//    }
//    
//    public void setPrivAcceptCQServiver(PrivAcceptCQService privAcceptCQServiver)
//    {
//        this.privAcceptCQServiver = privAcceptCQServiver;
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
//    public String getErrFlag()
//    {
//        return errFlag;
//    }
//    
//    public void setErrFlag(String errFlag)
//    {
//        this.errFlag = errFlag;
//    }
//    
//    public String getPriRecDate()
//    {
//        return priRecDate;
//    }
//    
//    public void setPriRecDate(String priRecDate)
//    {
//        this.priRecDate = priRecDate;
//    }
//    
//    public String getPriBackMoney()
//    {
//        return priBackMoney;
//    }
//    
//    public void setPriBackMoney(String priBackMoney)
//    {
//        this.priBackMoney = priBackMoney;
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
//    public String getChargeId()
//    {
//        return chargeId;
//    }
//    
//    public void setChargeId(String chargeId)
//    {
//        this.chargeId = chargeId;
//    }
//    
//    public String getPrintcontext()
//    {
//        return printcontext;
//    }
//    
//    public void setPrintcontext(String printcontext)
//    {
//        this.printcontext = printcontext;
//    }
//
//	public String getPageCount() {
//		return pageCount;
//	}
//
//	public void setPageCount(String pageCount) {
//		this.pageCount = pageCount;
//	}
//    
}
