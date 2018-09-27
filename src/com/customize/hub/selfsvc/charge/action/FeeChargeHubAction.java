package com.customize.hub.selfsvc.charge.action;

import com.customize.hub.selfsvc.bean.FeeChargeHubBean;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ScoreBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.terminfo.service.TerminalInfoService;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 话费充值缴费
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeHubAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    private static Log logger = LogFactory.getLog(FeeChargeHubAction.class);
    
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
    private transient FeeChargeHubBean feeChargeBean;
    
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
    private transient FeeChargeHubService feeChargeService;
    
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
    
    //add by xkf57421 for XQ[2011]_10_082 begin
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    //add by xkf57421 for XQ[2011]_10_082 begin
    // 抽奖活动编码
    private String actId =null;
    
    //add begin sWX219697 2014-12-23 15:44:49 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
    /**
     * 赠送金额 单位，分
     */
    private String presentFee;
    //add end sWX219697	2014-12-23 15:46:51 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动

    //add begin liutao00194861 2016-8-16 OR_HUB_201603_1191 【BOSS常规需求】自助终端显示内容优化需求（张德伟）
    //剩余积分
    private String availIntegral="";
    //是否4G标示
    private String isNot4GCard="";
    // 调用接口bean
    private ScoreBean qryScoreBean;
    //add end liutao00194861 2016-8-16  【BOSS常规需求】自助终端显示内容优化需求（张德伟）
    
    //add begin lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
    //代理商自助终端判断参数 
    private boolean agentParam = false;
    //支付中心支付方式展示参数
    private String termParam; 
    //支付中心支付标识
    private String payCerntWay;
    
    //缴费金额
    private String chargeMoney;
    
    // 重定向url
    private String redirectUrl;
    
    //支付中心支付失败信息
    private String errorPayCenterMsg;
    
    //充值成功回显手机号吗
    private String cuNumbers = "";
    /**
     * 成功标志
     */
    public static final String G_SUCCESS_FLAG = "000000";
    //add ebnd lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
    

    /**
     * 话费充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
     * 
     * @return
     */
    public String inputNumber()
    {
        // Change by LiFeng 进入inputNumber页面需清除缓存中的用户信息 20110907
        this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
        return "inputNumber";
    }
    
    /**
     * 话费充值账户信息查询（账户信息+支付方式）
     * 
     * @return
     */
    public String qryfeeChargeAccount()
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
            
            Map result = feeChargeBean.qryfeeChargeAccount(termInfo, servnumber, curMenuId);
            if (result != null && result.size() > 1)
            {
                CTagSet tagSet = (CTagSet)result.get("returnObj");
                
                // ----------------------------账户信息数据-------------------------------------------------
                
                // 区域
                servRegion = tagSet.GetValue("region");
                
                // 设置话费余额
                setBalance(CommonUtil.fenToYuan(tagSet.GetValue("balance")));
                
                // ----------------------------当前用户信息-------------------------------------------------
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                
                // 手机号码
                customerSimp.setServNumber(servnumber);
                
                // 产品ID
                customerSimp.setBrandID(tagSet.GetValue("productid"));
                
                // 产品名称
                customerSimp.setBrandName(tagSet.GetValue("productname"));
                
                // 客户名称
                customerSimp.setCustomerName(tagSet.GetValue("subname"));
                
                // 放入SESSION
                request.getSession().setAttribute(Constants.USER_INFO, customerSimp);
                
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
//                if (logger.isInfoEnabled())
//                {
//                    logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//                }
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
                    
                    forward = "failed";
                }
                
                forward = "success";
                
                // 记录成功日志
                this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "缴费时查询受理类型成功");
            }
            else
            {
                logger.debug("查询受理类型失败！");
                
                this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
                
                // 记录错误日志
                this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "缴费时查询受理类型失败");
            }
        }
        catch (Exception e)
        {
            logger.error("查询受理类型失败！");
            setErrormessage("查询受理类型出现异常，请联系系统管理员。给您带来的不便，敬请原谅。");
            
            // 记录异常日志
            this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "缴费时查询受理类型失败");
        }
        
        //add begin lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
        if("true".equals(CommonUtil.getParamValue("onlinePay_Switch", "false")))
        {           
            HttpSession session = getRequest().getSession();
            // 终端信息
            TerminalInfoPO termInfos = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            //获取终端设备号
            String termids = termInfos.getTermgrpid();       
            //终端属性集合       
            List<TermInfosPO> termInfoList = (List<TermInfosPO>)PublicCache.getInstance().getCachedData(Constants.TERM_INFO);
            if(null != termInfoList)
            {              
                for(TermInfosPO terminal : termInfoList)
                {
                    //遍历查询终端设备属性表中是否存在该终端号
                    if(termids.equals(terminal.getTerminalid()))
                    {
                        if("PayCenter".equals(terminal.getAttributeid()))
                        {
                            //参数1表示满足展示支付中心支付的方式
                            if( "1".equals(terminal.getAttributeval()))
                            {
                                termParam = "true";
                                break;
                            }
                        }
                    }
                }       
                //判定自助终端是否属于代理商的orgtyAgent值
                String orgtyAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_ORGTYPE_AGENT);
                //获取TerminalInfoService对象
                TerminalInfoService infoService = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");  
                logger.info("orgtyAgent="+orgtyAgent+" and infoService="+infoService+" and termInfos="+infoService.getOrgtype(termInfos));
                //判定自助终端是否属于代理商                
                if(null != orgtyAgent && orgtyAgent.equals(infoService.getOrgtype(termInfos)))
                {
                    agentParam = true;
                }      
            }
        }      
        //add end lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
        
        return forward;
    }
    
    /**
     * 转向投币页面
     * 
     * @return
     */
    public String cashCharge()
    {
        return "cashChargePage";
    }
    
    /**
     * 支付中心支付方式
     * 
     * create by lwx439898 2017-10-12 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public String intPayCetCharge()
    {
        String forward = null;
        logger.debug("payCenterCharge start");
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        // 获取当前用户 信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        // 手机号码
        String currNumber = customerSimp.getServNumber();
        // 支付费用 元转分
        String totalChargeMoney = CommonUtil.yuanToFen(chargeMoney);
        // 当前业务类型
        String recType = CommonUtil.getParamValue("self_recType", "Voucher");
        // 交易类型 默认 pay冲值
        String transType = CommonUtil.getParamValue("self_transType", "pay");
       
        String respMsg = "";
        String payCenterParamInfo = "";
        try
        {
            // 创建支付任务并返回任务号
            String payTransMsg = createPayCenterTrans(customerSimp,
                    termInfo,
                    currNumber,
                    totalChargeMoney,
                    recType,
                    transType);
            if (StringUtils.isBlank(payTransMsg))
            {
                throw new Exception("创建支付任务失败！");
            }
            
            // 支付测试参数
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {
                respMsg = "{a:'response1',b:'response2'}";
                payCenterParamInfo = "{a:'aa',b:'bb',c:'cc'}";
                redirectUrl = CommonUtil.getParamValue("SH_URL_TEST"); 
            }
            else
            {
                // 组装请求参数和响应参数报文
                payCenterParamInfo = assembledPackets(termInfo, payTransMsg, totalChargeMoney, currNumber);
                
                // 获取支付中心请求url参数配置
                String reqUrl = CommonUtil.getParamValue("SH_PayCenterSendMsgUrl");
                
                if (StringUtils.isBlank(reqUrl))
                {
                    throw new Exception("调用支付中心请求支付的URL为空！");
                }
                
                respMsg = sendPayCenterMessage(payCenterParamInfo, reqUrl);
                if (StringUtils.isBlank(respMsg))
                {
                    throw new Exception("调用支付网关异常");
                }
                
                org.json.JSONObject respObject = new org.json.JSONObject(respMsg);
                
                String respCode = respObject.getString("retCode");
                // 成功标识
                if (G_SUCCESS_FLAG.equals(respCode))
                {
                    org.json.JSONObject resqData = respObject.getJSONObject("data");
                    if (null != resqData)
                    {
                        redirectUrl = resqData.getString("redirectUrl");
                    }
                }
                else
                {
                    throw new Exception(respObject.getString("retMsg"));
                }
            }
            // 更新银行支付状态
            feeChargeBean.updateBankPaymentStatus(payCenterParamInfo,
                    respMsg,
                    payTransMsg,
                    termInfo,
                    curMenuId,
                    currNumber);
            if (StringUtils.isBlank(redirectUrl))
            {
                throw new Exception("调用支付中心获取重定向URL失败！");
            }
            //测试使用
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {              
                getRequest().getSession().setAttribute("taskoid",payTransMsg);
            }
            
            getRequest().setAttribute("taskoid", payTransMsg);           
            forward = "initPayCenter";
        }
        catch (Exception e)
        {
            logger.error("支付中心支付异常：" + e); 
            errorPayCenterMsg = "支付中心支付失败："+e;
            forward = "error";
        }
        return forward;
    }
    
    /**
     * 支付中心支付成功(支付中心回调)
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * create by lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public String payCetChargeCommit()
    {  
        String forward = "success";
        logger.error("PayCetChargeCommit begin  ");
        try
        {
            // 终端信息
            TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
            // 获取当前用户 信息
            NserCustomerSimp customerSimp = (NserCustomerSimp)getRequest().getSession()
                    .getAttribute(Constants.USER_INFO);
            String currNumber = customerSimp.getServNumber();
            String taskoid = "";
            String payId = "";
            HttpServletRequest request = this.getRequest();
            //测试使用
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {
                payId = request.getParameter("payId");
                taskoid = (String)getRequest().getSession().getAttribute("taskoid");
                chargeMoney = CommonUtil.yuanToFen(chargeMoney);
            }
            else
            {                
                payId = request.getParameter("payId");
                taskoid =  request.getParameter("partnerPayId");
                chargeMoney = request.getParameter("payFee");               
            }
            // 支付中心支付模式
            String payModeId = "";
            // 支付状态
            String bankStatus = "";
            // 查询支付交易
            Map chargeinfos = feeChargeBean.qryPayChargeInfo(termInfo, taskoid, currNumber, curMenuId);
            logger.error("查询支付交易返回值："+chargeinfos);
            if (chargeinfos != null && chargeinfos.size() > 1)
            {
                Object obj = chargeinfos.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // 对返回值进行解析
                    CTagSet chargeInfo = (CTagSet)obj;
                    payModeId = (String)chargeInfo.GetValue("PAYTYPE");
                    bankStatus = (String)chargeInfo.GetValue("BANKSTATUS");
                }
            }
            logger.error("bankStatus："+bankStatus);
            // 判断支付状态查验是否支付成功,不是1则表示支付失败
            if (!"1".equals(bankStatus))
            {
                forward = "error";
                errorPayCenterMsg = "支付失败，请查验支付中心扣费是否正常";
                return forward;
            }
            
            // 新支付类型
            String newPayType = payModeId;
            String recoid = "";
            String status = "";
            // modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            // 缴费提交
            Map resultMap = feeChargeBean.chargeCommit(taskoid,termInfo,
                    curMenuId,
                    currNumber,
                    chargeMoney + "",
                    newPayType,
                    payId,
                    "0");
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            logger.error("FeechargeResponse 11："+resultMap);
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // 对湖北缴费接口的返回值进行解析
                    CTagSet chargeInfo = (CTagSet)obj;
                    
                    dealNum = (String)chargeInfo.GetValue("dealNum");// 交易流水
                    dealTime = (String)chargeInfo.GetValue("dealTime");// 交易时间
                    recoid = (String)chargeInfo.GetValue("RECOID");
                    
                }
                status = "success";
            }
            else
            {
                status = "fail";
            }
            logger.error("updateBankRecoid beign   ");
            // 更新表 ar_bank_task 的 recoid
            feeChargeBean.updateBankRecoid(termInfo, currNumber, curMenuId, taskoid, recoid, status);
            logger.error("updateBankRecoid end    "+resultMap);
            if (resultMap == null)
            {
                throw new Exception("调用缴费接口失败！");
            }
            cuNumbers = currNumber;
        }
        catch (Exception e)
        {
            forward = "error";
            errorPayCenterMsg = "PayCetChargeCommit exception"+e;
            logger.error("PayCetChargeCommit end error  ");
        }
     
        return forward;
    }

    /**
     * 连接支付中心发送消息
     * <功能详细描述>
     * @param sendMsg
     * @param reqUrl
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    private String sendPayCenterMessage(String sendMsg, String reqUrl)
    {
        StringBuffer respMsg = new StringBuffer();
        BufferedReader in = null;
        try
        {
            // 获取操作员信息
            
            logger.debug("PayCenterAction.sendPayCenterMessage reqUrl:" + reqUrl);
            // 1、初始化POST方法：
            PostMethod httppost = new PostMethod(reqUrl);
            // 设置POST属性
            httppost.setRequestBody(sendMsg);
            logger.debug("PayCenterAction.sendPayCenterMessage sendMsg:" + sendMsg);
            // 设置报文头信息                                              
            httppost.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            // 链接超时
            String connectTimeout = CommonUtil.getParamValue("PayCenterConnectTimeout");
            connectTimeout = StringUtils.defaultIfEmpty(connectTimeout, "5000");
            // 等待超时
            String waitTimeout = CommonUtil.getParamValue("PayCenterWaitTimeout");
            waitTimeout = StringUtils.defaultIfEmpty(waitTimeout, "5000");

            // 2、调用接口：
            HttpClient client = new HttpClient();
            // 设置连接时间
            client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt(connectTimeout));
            // 设置等待时间
            client.getHttpConnectionManager().getParams().setSoTimeout(Integer.parseInt(waitTimeout));
            client.executeMethod(httppost);
            
            // 3、获取接口调用结果
            // 接收报文
            in = new BufferedReader(new InputStreamReader(httppost.getResponseBodyAsStream(), "UTF-8"));
            char[] buffer = new char[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1)
            {
                respMsg.append(buffer, 0, len);
            }
          
            // 4、释放连接
            httppost.releaseConnection();

        }
        catch (Exception e)
        {
            logger.error("PayCenterAction.sendPayCenterMessage Exception:", e);
        }
        finally
        {
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch (IOException e)
            {
                logger.error("PayCenterAction.sendPayCenterMessage IOException:", e);
            }
            
        }
        logger.debug("PayCenterAction.sendPayCenterMessage respMsg:" + respMsg.toString());
        return respMsg.toString();
    }
    
    /**
     * 拼装支付网关入参json报文
     * <功能详细描述>
     * @param termInfo
     * @param payTransMsg
     * @param totalChargeMoney
     * @param currNumber
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by lwx439898 2017-10-16 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    private String assembledPackets(TerminalInfoPO termInfo,String payTransMsg, String totalChargeMoney, String currNumber)
    {
        JSONObject payCenterParam = new JSONObject();
        JSONObject payCenterData = new JSONObject();       
        try
        {
            // 客户表示
            payCenterData.put("customerId", currNumber);
            // 商户标识
            payCenterData.put("partnerId", CommonUtil.getParamValue("self_selfMerchant", "SHSELF"));
            // 支付单位标识
            payCenterData.put("partnerPayId", payTransMsg);
            //指定收银台code
            payCenterData.put("cashierCode", CommonUtil.getParamValue("self_cashierCode", ""));
            //订单描述
            payCenterData.put("orderDesc",  CommonUtil.getParamValue("self_orderDesc", "selfsvc_feecharge_"+totalChargeMoney));
            // 渠道编码
            payCenterData.put("groupId", termInfo.getOrgid());
            // 币种
            payCenterData.put("currency", "RMB");
            // 支付总金额(单位：分)
            payCenterData.put("totalFee", totalChargeMoney);
            // 接入模式
            payCenterData.put("accessMode","PC");
            // 前台通知
            String retUrl = CommonUtil.getParamValue("SH_PayCenterRetUrl");
            if (StringUtils.isBlank(retUrl))
            {
                logger.error("获取前台通知url异常");
            }
            payCenterData.put("retUrl", retUrl);
            // 后台通知
            String notifyUrl = CommonUtil.getParamValue("SH_PayCenterNotifyPayUrl");
            if (StringUtils.isBlank(notifyUrl))
            {
                logger.error("获取后台通知url异常");
            }
            payCenterData.put("notifyUrl", notifyUrl);
            // 用户IP
            payCenterData.put("clientIp", termInfo.getIpaddr());
            // 是否需要登录
            payCenterData.put("needLoginFlag", CommonUtil.getParamValue("self_needLoginFlag", "N"));
            // 超时时间
            payCenterData.put("timeout", CommonUtil.getParamValue("self_timeout", ""));
            // 商品类型 非必填，暂时为空
            List<String> list = new ArrayList<String>();
            payCenterData.put("goodsInfo", list);
            payCenterParam.put("data", payCenterData);
            payCenterParam.put("signType", "MD5");
            // 签名
            String eleSignKey = CommonUtil.getParamValue("SH_PayCenterElectronicSignKey");
            if (StringUtils.isBlank(eleSignKey))
            {
                logger.error("调用支付中心的秘钥异常");
            }
            
            payCenterParam.put("sign", md5HexSignStr(JSONObject.toJSONString(payCenterData, SerializerFeature.SortField), eleSignKey));
        }
        catch (Exception e)
        {
            logger.error("拼装入参json报文异常", e);
        }      
        return payCenterParam.toString();
    }
  
    /**
     * 新建支付任务
     * 
     * @param currNumber
     * @param totalChargeMoney
     * @param recType
     * @param transType
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    private String createPayCenterTrans(NserCustomerSimp customerSimp,TerminalInfoPO termInfo,String currNumber,String totalChargeMoney,String recType,String transType)
    {
        String taskOid = "";
        String queryType = CommonUtil.getParamValue("self_qryChargeType", "servNumber");
        Map<String,String> selfPayLog = new HashMap<String,String>();
        selfPayLog.put("RecType", recType);
        selfPayLog.put("exChangeType", transType);
        selfPayLog.put("Payamount", totalChargeMoney);
        selfPayLog.put("AccessType","bsacAtsv");
        selfPayLog.put("Paytype", CommonUtil.getParamValue("self_payType", "PayCenter"));
        selfPayLog.put("Telnum", currNumber);
        selfPayLog.put("bankCode",CommonUtil.getParamValue("self_bankCode", "PayCenter"));
               
        //创建支付任务，记录ar_bank_task表
        Map payTransMsg = feeChargeBean.createPayCenterTrans(customerSimp,curMenuId,termInfo,queryType,currNumber,selfPayLog);
        if (payTransMsg != null && payTransMsg.size() > 1)
        {
            Object obj = payTransMsg.get("returnObj");
            if (obj instanceof CTagSet)
            {
                // 对返回值进行解析
                CTagSet chargeInfo = (CTagSet)obj;
                taskOid = (String)chargeInfo.GetValue("TASKOID");
            }
        }
        
        return taskOid;
    }
    
    /**
     * MD5加密
     * @param content
     * @param signKey
     * @return
     */
    private String md5HexSignStr(String content, String signKey)
    {
        String str = content + "&key=" + signKey;     
       
        return DigestUtils.md5Hex(str);
    }
    
      
    /**
     * 话费充值执行现金缴费
     * 
     * @return
     */
    public String cashChargeCommit()
    {
        logger.debug("cashChargeCommit start");
        
        String forward = null;
        
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        //计算赠送金额，单位：元
        presentFee = calculateFee(tMoney);
        
        //赠送话费流水号
        String presentOid = "";
        //add end sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        
        // 设定缴费方式
        this.payType = Constants.PAY_BYCASH;
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // add by LiFeng [XQ[2011]_11_062]自助终端-重复缴费控制 20111125 Begin
        //测试可以屏蔽
	    if (!checkCashFee(termInfo))
	    {
	         forward = "repeatError";
	         return forward;
	    }
        //Add by LiFeng [XQ[2011]_11_062]自助终端-重复缴费控制 20111125 End
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 生成投币日志
        String logOID = feeChargeService.getChargeLogOID();
        
        // 组装数据
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        //赠送金额
        selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(presentFee));
        
        //赠送状态0：初始状态，赠送失败
        selfCardPayLogVO.setPresentStatus("0");
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        
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
        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费 favourable：优惠缴费）
        
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        // Change by hWX5316476  OR_huawei_201305_1248 20131011 NG4.0社会渠道风险管控个性需求_社会渠道自助终端现金缴费资金鉴权 Begin
        
        // 代理商自助终端businessid对应的科目
        String businessid = (String)PublicCache.getInstance().getCachedData(Constants.SH_SUBJECT_AGENT);
        
        // 现金缴费代理商扣款开关
        String cashChageAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_CASHCHARGE_AGENT);
        
        // 判定自助终端是否属于代理商的orgtype值
        String orgtypeAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_ORGTYPE_AGENT);
        
        Map resultMap = null;
        
        // 获取TerminalInfoService对象
        TerminalInfoService service = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");

        // 如果代理商自助终端的现金缴费开关开启，并且该自助终端属于代理商管理的，调用代理商扣款接口执行现金缴费；否则调用现金缴费接口
        if("1".equals(cashChageAgent) && null != orgtypeAgent && orgtypeAgent.equals(service.getOrgtype(termInfo)))
        {
        	 resultMap = feeChargeBean.chargeCommitByAgent(termInfo,
        			 curMenuId,
                  servnumber,
                  CommonUtil.yuanToFen(tMoney) + "",
                  "Cash",
                  businessid,selfCardPayLogVO.getChargeLogOID(), CommonUtil.yuanToFen(presentFee));
        }
        else
        {
            //modufy begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            resultMap = feeChargeBean.chargeCommit("",termInfo,
            		curMenuId,
                servnumber,
                CommonUtil.yuanToFen(tMoney) + "",
                "Cash", selfCardPayLogVO.getChargeLogOID(), CommonUtil.yuanToFen(presentFee));
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
        }
        // Change by hWX5316476  OR_huawei_201305_1248 20131011 NG4.0社会渠道风险管控个性需求_社会渠道自助终端现金缴费资金鉴权 End
        
        // 缴费成功
        if (resultMap != null && resultMap.size() > 0)
        {
            Object obj = resultMap.get("returnObj");
            if (obj instanceof CTagSet)
            {
                // 对湖北缴费接口的返回值进行解析
                CTagSet chargeInfo = (CTagSet)obj;
                
                dealNum = (String)chargeInfo.GetValue("dealNum");// 交易流水
                dealTime = (String)chargeInfo.GetValue("dealTime");// 交易时间
                
                //赠送话费流水号
                presentOid = (String)chargeInfo.GetValue("presentOid");
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
            
            //根据流水号判断交费状态，
            selfCardPayLogVO.setStatus(StringUtils.isBlank(dealNum) ? "10" : "11");
            selfCardPayLogVO.setDescription("话费充值现金缴费成功！");
            selfCardPayLogVO.setDealnum(dealNum);
            
            //add by sWX219697 2014-12-26 11:14:04 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            //赠送状态，1：赠送成功，0：赠送失败，由于赠送金额为0时赠送流水号为空，则赠送金额为0时状态置为1
            selfCardPayLogVO.setPresentStatus("0".equals(presentFee) ? "1" : (StringUtils.isBlank(presentOid) ? "0" : "1"));
            
            //赠送流水号
            selfCardPayLogVO.setPresentOid(presentOid);
            //add by sWX219697 2014-12-26 11:14:10 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            
         // add begin yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 tMoney
            String canRegardFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_AWARDSWITCH_HUB);
            if("1".equals(canRegardFlag)) actId=feeChargeService.getActId(payType, tMoney);
           
         
         // add end yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 

            forward = "success";
            
            // 取BOSS系统参数_是否发送短信验证码 1:发送 0:不发送
            String paramid = "sh_prtinvoicechk";
            Map retMap = feeChargeBean.getParamValue(termInfo, curMenuId, paramid);
            
            if (retMap != null && retMap.size() > 0)
            {
                Object object = retMap.get("returnObj");
                if (object instanceof CTagSet)
                {
                    // 对湖北缴费接口的返回值进行解析
                    CTagSet ctagset = (CTagSet)object;
                    needRandomPwd = (String)ctagset.GetValue("paramvalue");// 参数值
                }
            }
            else
            {
                needRandomPwd = "1";
            }
            
            // 记录缴费成功日志
            // Change by LiFeng 修改RECFORMNUM值，关联 sh_charge_log表
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(tMoney), 
            		"0", "缴费:自助终端缴费成功!");
            
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(presentFee), 
            		"0", "缴费:自助终端赠送金额成功!");

            //查询4G卡及剩余积分
            getCardTypeAndAviIntegral(termInfo);
        }
        // 缴费失败
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("话费充值现金缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            setErrormessage("话费充值现金缴费失败！");
            
            // 记录缴费失败日志
            // Change by LiFeng 修改RECFORMNUM值，关联 sh_charge_log表
            this.createRecLog(servnumber,
                    Constants.PAY_BYCASH,
                    logOID,
                    CommonUtil.yuanToFen(tMoney),
                    "1",
                    "缴费:自助终端缴费失败!");
            
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(presentFee), 
            		"1", "缴费:自助终端赠送金额失败!");
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cashChargeCommit end");
        
        return forward;
    }

    private void getCardTypeAndAviIntegral(TerminalInfoPO termInfo)
    {
        //add begin liutao00194861 2016-8-16 OR_HUB_201603_1191 【BOSS常规需求】自助终端显示内容优化需求（张德伟）
        //缴费成功调用积分查询和是否4G卡接口
        isNot4GCard = feeChargeBean.qryIs4GCard(termInfo, servnumber, curMenuId);
        //availIntegral=feeChargeBean.qryAvilInteral(termInfo,servnumber,curMenuId);
        //add end liutao00194861 2016-8-16 OR_HUB_201603_1191 【BOSS常规需求】自助终端显示内容优化需求（张德伟）

        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        // 调用接口查询积分信息
        Map result = qryScoreBean.queryScoreValue(termInfo, customer, curMenuId);
        if (result != null && result.size() > 1)
        {
            String scoreStr = (String)result.get("returnObj");
            String[] scores = scoreStr.split("~");
            //可用积分
            availIntegral = scores[1];
        }
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
        
        //add begin sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        //赠送金额
        selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
        
        //赠送状态，0：赠送失败
        selfCardPayLogVO.setPresentStatus("0");
        //add end sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        
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
        selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费 favourable：优惠缴费）
        
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
     * 选择交费金额
     * 
     * @return
     * @see
     */
    public String selectOtherFee()
    {
        return "selectOtherFee";
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
        
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
        try
        {
            writer = response.getWriter();
            // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907
            
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
            /*
             * String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
             * if (CommonUtilHub.strIsEmpty(chargeOperID)) { xml = "0";
             * 
             * logger.error("获取银联卡缴费工号异常,chargeOperID:[" + chargeOperID + "]."); } else {
             */
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
            
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
            
            //add begin sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            //赠送金额
            cardChargeLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
            
            //赠送状态，0：赠送失败
            cardChargeLogVO.setPresentStatus("0");
            //add end sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
            
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
            
            cardChargeLogVO.setRecType("phone");// 业务类型（phone：话费缴费
                                                // favourable：优惠缴费）
            
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
        // Chagne by LiFeng 解决异常不能退卡问题 20110909 Begin
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
            // String xml = "";
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
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        tMoney = Integer.parseInt(tMoney) + "";
        
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
        
        // String tempTermOperid = termInfo.getOperid();
        
        try
        {
            //add by sWX219697 2014-12-26 11:31:25 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        	//计算用户的赠送金额。元
        	presentFee = calculateFee(CommonUtil.fenToYuan(tMoney));
        	
            //赠送话费流水号
            String presentOid = "";
            //add end sWX219697 2014-12-26 11:31:30 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
        	
            // 根据地市获取虚拟工号
            // String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
            
            // 设置各地市特殊工号
            // termInfo.setOperid(chargeOperID);
            
            // session.setAttribute(Constants.TERMINAL_INFO, termInfo);
            // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
            
            //modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            // 银行卡缴费
            Map resultMap = feeChargeBean.chargeCommit("",termInfo, curMenuId, servnumber, tMoney, 
            		"Card", chargeLogOID, CommonUtil.yuanToFen(presentFee));
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            
            // 缴费成功
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // 对湖北缴费接口的返回值进行解析
                    CTagSet chargeInfo = (CTagSet)obj;
                    
                    dealNum = (String)chargeInfo.GetValue("dealNum");// 受理编号
                    dealTime = (String)chargeInfo.GetValue("dealTime");// 受理编号
                    
                    //赠送话费流水号
                    presentOid = (String)chargeInfo.GetValue("presentOid");
                }
                
                selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                
                //根据流水号判断交费状态，11：交费成功，10：扣款成功，交费失败
                selfCardPayLogVO.setStatus(StringUtils.isBlank(dealNum) ? "10" : "11");
                selfCardPayLogVO.setDescription("银联卡缴费成功！");
                selfCardPayLogVO.setDealnum(dealNum);
                
                //add by sWX219697 2014-12-26 11:11:31 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                //根据流水号判断赠送状态，1：赠送成功，0：赠送失败，由于赠送金额为0时赠送流水号为空，则赠送金额为0时状态置为1
                selfCardPayLogVO.setPresentStatus("0".equals(presentFee) ? "1" : (StringUtils.isBlank(presentOid) ? "0" : "1"));
                
                //赠送流水号
                selfCardPayLogVO.setPresentOid(presentOid);
                //add end sWX219697 2014-12-26 11:11:55 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                
                tMoney = CommonUtil.fenToYuan(tMoney);
                // add begin yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 tMoney
                String canRegardFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_AWARDSWITCH_HUB);
                if("1".equals(canRegardFlag))  actId=feeChargeService.getActId(payType, tMoney);
               
              
              // add end yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
                
                forward = "success";
                
                // 取BOSS系统参数_是否发送短信验证码 1:发送 0:不发送
                String paramid = "sh_prtinvoicechk";
                Map retMap = feeChargeBean.getParamValue(termInfo, curMenuId, paramid);
                if (retMap != null && retMap.size() > 0)
                {
                    Object object = retMap.get("returnObj");
                    if (object instanceof CTagSet)
                    {
                        // 对湖北缴费接口的返回值进行解析
                        CTagSet ctagset = (CTagSet)object;
                        
                        needRandomPwd = (String)ctagset.GetValue("paramvalue");// 参数值
                    }
                }
                else
                {
                    needRandomPwd = "1";
                }
                
                // 记录缴费成功日志
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "0",
                        "缴费:自助终端银联卡缴费成功!");
                
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(presentFee),
                        "0",
                        "缴费:自助终端银联卡缴费赠送金额成功!");

                //查询4G卡及剩余积分
                getCardTypeAndAviIntegral(termInfo);
            }
            // 缴费失败
            else
            {
                tMoney = CommonUtil.fenToYuan(Integer.parseInt(tMoney) + "");
                
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setDescription("话费充值银联卡缴费失败！");
                selfCardPayLogVO.setDealnum("");
                
                forward = "error";
                setErrormessage("话费充值银联卡缴费失败！");
                
                // 记录缴费失败日志
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "1",
                        "缴费:自助终端银联卡缴费失败!");
                
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(presentFee),
                        "0",
                        "缴费:自助终端银联卡缴费赠送金额失败!");
            }
            
            //add by xkf57421 for XQ[2011]_10_082 begin 
            selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
            //add by xkf57421 for XQ[2011]_10_082 end
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        catch (Exception e)
        {
            logger.error(e);
            e.printStackTrace();
            errormessage = "对不起,提交缴费时发生异常,请凭小票到营业厅查询是否缴费成功,谢谢使用.";
            forward = "cardErrorPage";
        }
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        // finally
        // {
        // 将session里的终端信息改回
        // termInfo.setOperid(tempTermOperid);
        // session.setAttribute(Constants.TERMINAL_INFO, termInfo);
        // }
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 End
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 End
        
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
        
        // Change by LiFeng 修改银联卡缴费使用特殊工号 20110907 Begin
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // Change by LiFeng OR_HUB_201110_147 电子渠道-自助终端-银联缴费相关整改需求 自助终端改回终端机对应工号缴费 20111108 Begin
        // String tempTermOperid = termInfo.getOperid();
        
        try
        {
            /*
             * if (Constants.PAY_BYCARD.equals(payType)) { //String chargeOperID =
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
                
                //add begin sWX219697 2015-1-6 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                //赠送金额
                selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
                
                //赠送状态，默认为0：赠送失败。赠送成功：1
                selfCardPayLogVO.setPresentStatus("0");
                //add begin sWX219697 2015-1-6 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
                
                selfCardPayLogVO.setTerminalSeq("");
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("00");
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                selfCardPayLogVO.setAcceptType("");
                selfCardPayLogVO.setServRegion(servRegion);
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());
                selfCardPayLogVO.setRecType("phone");// 业务类型（phone：话费缴费 favourable：优惠缴费）
                
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
     * 湖北打印发票需要短信随机码认证
     * 
     * @return
     * @see
     */
    public String validateByRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd Starting ...");
        }
        
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        request.setAttribute("invoiceType", invoiceType);
        request.setAttribute("dealNum", dealNum);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 生成随机密码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // 发送随机密码短信
        String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD_CONTENT");
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        if (!feeChargeBean.sendRandomPwd(servnumber, termInfo, shortMessage, curMenuId))
        {
            logger.error("向用户" + servnumber + "发送随机密码短信失败");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", "发票打印功能，随机密码短信发送失败。");
            
            this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，不能打印发票。");
        }
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
            }
            
            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 随机密码验证，验证通过后，打印发票
     * 
     * @return
     * @see
     */
    public String printInvoiceWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            
            // 调用接口打印发票
            request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
            
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
                // modify begin g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
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
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        // 调用接口打印发票
        request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
        
        return "print";
    }
    
    /**
     * 调用接口打印发票
     */
    private List printInvoice(String invoiceType, String dealNum)
    {
        HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 提供发票打印功能时，获取发票信息
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = feeChargeBean.getInvoiceData(termInfo, curMenuId, servnumber, dealNum);
            
            if (invoiceData != null && invoiceData.size() > 0)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof Vector)
                {
                    Vector invoiceVector = (Vector)invoiceObj;
                    
                    CTagSet tagSet = (CTagSet)invoiceVector.get(0);
                    String cycleCount = (String)tagSet.GetValue("invoice_cnt");
                    int rowNum = Integer.parseInt(cycleCount);
                    
                    CRSet crset = (CRSet)invoiceVector.get(1);
                    
                    List invoicesList = new ArrayList();
                    
                    // 打印全部发票，有多张发票信息打印多张
                    if ("ALL".equals(invoiceType))
                    {
                        Map invoiceMap = null;
                        for (int i = 0; i < rowNum; i++)
                        {
                            invoiceMap = new HashMap();
                            
                            // 将返回的CRSet转换成字符串
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (tmpArray.length == 2)
                                {
                                    invoiceMap.put(tmpArray[0], tmpArray[1]);
                                }
                            }
                            invoicesList.add(invoiceMap);
                        }
                    }
                    // 打印当前发票，有多张发票打印最后一张
                    else if ("Last".equals(invoiceType))
                    {
                        Map invoiceMap = new HashMap();// 键值对形式存储一张发票数据各个子数据
                        
                        // 将返回的CRSet转换成字符串
                        String invoice = crset.GetValue(rowNum - 1, 0) + "," + crset.GetValue(rowNum - 1, 1);
                        String[] invoiceArray = invoice.split(",");
                        for (int j = 0; j < invoiceArray.length; j++)
                        {
                            String tmpData = invoiceArray[j];
                            String[] tmpArray = tmpData.split("@~@");
                            if (tmpArray.length == 2)
                            {
                                invoiceMap.put(tmpArray[0], tmpArray[1]);
                            }
                        }
                        invoicesList.add(invoiceMap);
                    }
                    // 打印单张发票，有多张发票需要显示的月份为某月~某月，金额为多张发票的总金额，其他字段显示最后一张发票的信息
                    else if ("Single".equals(invoiceType))
                    {
                        float total = 0; // 所销帐的金额总和
                        String tmpStar = ""; // 开始日期
                        HashMap subMap = new HashMap();// 键值对形式存储一张发票数据各个子数据
                        for (int i = 0; i < rowNum; i++)
                        {
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (rowNum - 1 == 0)
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        subMap.put(tmpArray[0], tmpArray[1]);
                                        // System.out.println("发票的信息："+tmpArray[0]+":"+tmpArray[1]);
                                    }
                                }
                                else
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        if ("invoicefee".equals(tmpArray[0]))
                                        {
                                            total += Float.parseFloat((tmpArray[1].substring(0,
                                                    tmpArray[1].length() - 1)));
                                        }
                                        if (i == 0 && "callfeeCreateTime".equals(tmpArray[0]))
                                        {
                                            tmpStar = tmpArray[1];
                                        }
                                        if (i == rowNum - 1)
                                        {
                                            if ("invoicefee".equals(tmpArray[0]) || "invoicefeehj".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = CommonUtil.round(String.valueOf(total)) + "元";
                                            }
                                            if ("callfeeCreateTime".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = tmpStar + "——" + tmpArray[1];
                                            }
                                            subMap.put(tmpArray[0], tmpArray[1]);
                                            // System.out.println("发票的信息："+tmpArray[0]+":"+tmpArray[1]);
                                        }
                                    }
                                }
                            }
                        }
                        invoicesList.add(subMap);
                    }
                    return invoicesList;
                }
            }
        }
        return null;
    }
    
    // add by LiFeng [XQ[2011]_11_062]自助终端-重复缴费控制 20111124 Begin
    /**
     * 缴费异常，可查看缴费历史，需输入密码验证
     */
    public String goQryFeeHistory()
    {
        //清除用户信息
        getRequest().getSession().removeAttribute(Constants.USER_INFO);
        
        return SUCCESS;
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
            
            String tmpErrorMsg = "重复缴费:手机号[".concat(servnumber)
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
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
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
    
    // add by LiFeng [XQ[2011]_11_062]自助终端-重复缴费控制 20111124 End
//
    /**
     *免费抽奖
     * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
     */
    public void  mianFeiChouJiang() throws IOException
    {
       
        StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
        resBuff.append("<info>");
        Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            { HttpServletResponse response = getResponse();
              response.setContentType("text/xml;charset=GBK");
              request.setCharacterEncoding("GBK");
              writer = response.getWriter();
                 
             
                CTagSet res = feeChargeBean.mianFeiChouJiang(customerSimp, terminalInfo,actId,curMenuId);
               
              
                if(res!=null){
                    String CJ_RESULT=res.GetValue("CJ_RESULT");
                    String logInfo="";
                    if(CJ_RESULT==null||CJ_RESULT.equals("")){
                        throw new Exception();
                        
                    }else if(CJ_RESULT.equals("910002")){
                        logInfo="抱歉: 不是指定的营销客户群！";
                        
                       
                    }else if(CJ_RESULT.equals("910003")){
                        logInfo="抱歉: 您的当前积分为："+(res.GetValue("CJUSERJF")==null?"":res.GetValue("CJUSERJF"))+"，您的积分不足，或不满足条件！";
                      
                      
                       
                    }else if(CJ_RESULT.equals("910004")){
                        logInfo="抱歉:抽奖总数已达到最大数量限制！";
                       
                       
                    }else if(CJ_RESULT.equals("-2")){
                        logInfo="没有查到此抽奖活动的数据(此抽奖活动不存在)!";
                       
                        
                    }else{
                        logInfo=res.GetValue("PROMPTING")==null?"":res.GetValue("PROMPTING");
                       
                        
                    }
                  
                    resBuff.append(logInfo);
                    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "0", logInfo);
                    
                }else{
                    throw new Exception();
                    
                }
                
               }
            catch(Exception e)
            {    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "1", "免费抽奖出现异常！");
                   resBuff.append("免费抽奖出现异常！");
                   
              
                 
                
            }finally{
                
                if(null != writer)
                    {   
                    resBuff.append("</info>");
                    writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
    
    //
    /**
     *免费抽奖
     * @remark create yKF73963   2013-10-15 自助终端-积分专区   
     */
    public void  mianFeiChouJiangNew() throws IOException
    {
       
        StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
        resBuff.append("<info>");
        Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            { HttpServletResponse response = getResponse();
              response.setContentType("text/xml;charset=GBK");
              request.setCharacterEncoding("GBK");
              writer = response.getWriter();
              String logInfo="";
              ReturnWrap rw = feeChargeBean.mianFeiChouJiangNew(customerSimp, terminalInfo,actId,curMenuId);
            if(rw.getStatus()==0){
                
                logInfo=rw.getReturnMsg();
                resBuff.append(logInfo);
                
            }else{
                
                
          
                CTagSet res = (CTagSet)rw.getReturnObject();
               
              
                if(res!=null){
                    String CJ_RESULT=res.GetValue("CJ_RESULT");
                   
                    if(CJ_RESULT==null||CJ_RESULT.equals("")){
                        throw new Exception();
                        
                    }else if(CJ_RESULT.equals("910002")){
                        logInfo="抱歉: 不是指定的营销客户群！";
                        
                       
                    }else if(CJ_RESULT.equals("910003")){
                        logInfo="抱歉: 您的当前积分为："+(res.GetValue("CJUSERJF")==null?"":res.GetValue("CJUSERJF"))+"，您的积分不足，或不满足条件！";
                      
                      
                       
                    }else if(CJ_RESULT.equals("910004")){
                        logInfo="抱歉:抽奖总数已达到最大数量限制！";
                       
                       
                    }else if(CJ_RESULT.equals("-2")){
                        logInfo="没有查到此抽奖活动的数据(此抽奖活动不存在)!";
                       
                        
                    }else{
                        logInfo=res.GetValue("PROMPTING")==null?"":res.GetValue("PROMPTING");
                       
                        
                    }
                  
                    resBuff.append(logInfo);
                    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "0", logInfo);
                    
                }else{
                    throw new Exception();
                    
                }
                
               }
            }
            catch(Exception e)
            {    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "1", "抽奖出现异常！");
                   resBuff.append("抽奖出现异常！");
                   
              
                 
                
            }finally{
                
                if(null != writer)
                    {   
                    resBuff.append("</info>");
                    writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
    
    /**
     * <根据用户的充值金额计算赠送费用>
     * <功能详细描述>
     * @param chargeFee 用户充值金额，单位：元
     * @return 赠送费用，单位：元
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-12-26 10:57:23 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
     */
    private String calculateFee(String chargeFee)
    {
    	//若充值金额为空，则直接返回
    	if(StringUtils.isBlank(chargeFee))
    	{
    		return "0";
    	}
    	
        //充值金额，用于计算赠送金额，单位，元
        double chargeFeeUnit = 100;
        
        //赠送金额单位，如，每充值100元赠送1.5元，默认为0，即不赠送
        double presentFeeUnit = 0;
        
        try 
        {
        	chargeFeeUnit = Double.parseDouble(CommonUtil.getParamValue("SH_PAY_FEE"));
			
        	presentFeeUnit = Double.parseDouble(CommonUtil.getParamValue("SH_PRESENT_FEE"));
		} 
        catch (NumberFormatException e) 
        {
			logger.error("话费赠送SH_PAY_FEE，SH_PRESENT_FEE配置错误，不赠送用户话费：", e);
		}
        
        //计算赠送金额 元
        double presentFee = Math.floor(Double.parseDouble(chargeFee)/chargeFeeUnit) * presentFeeUnit;
        
        BigDecimal zero = new BigDecimal("0");
        
        //费用为0时，返回0，单位，元
        return 0 == zero.compareTo(new BigDecimal(String.valueOf(presentFee))) ? "0" : String.valueOf(presentFee);
    }
    
    //
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
    
    public FeeChargeHubBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeHubBean feeChargeBean)
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
    
    public FeeChargeHubService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeHubService feeChargeService)
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
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getErrPosResCode()
	{
		return errPosResCode;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setErrPosResCode(String errPosResCode)
	{
		this.errPosResCode = errPosResCode;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getActId()
    {
        return actId;
    }
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setActId(String actId)
    {
        this.actId = actId;
    }

	public String getPresentFee() {
		return presentFee;
	}

	public void setPresentFee(String presentFee) {
		this.presentFee = presentFee;
	}

    public String getBusiType()
    {
        return busiType;
    }

    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }

    public String getAvailIntegral()
    {
        return availIntegral;
    }

    public void setAvailIntegral(String availIntegral)
    {
        this.availIntegral = availIntegral;
    }

    public String getIsNot4GCard()
    {
        return isNot4GCard;
    }

    public void setIsNot4GCard(String isNot4GCard)
    {
        this.isNot4GCard = isNot4GCard;
    }

    public ScoreBean getQryScoreBean()
    {
        return qryScoreBean;
    }

    public void setQryScoreBean(ScoreBean qryScoreBean)
    {
        this.qryScoreBean = qryScoreBean;
    }

    public boolean isAgentParam()
    {
        return agentParam;
    }

    public void setAgentParam(boolean agentParam)
    {
        this.agentParam = agentParam;
    }

    public String getTermParam()
    {
        return termParam;
    }

    public void setTermParam(String termParam)
    {
        this.termParam = termParam;
    }

    public String getPayCerntWay()
    {
        return payCerntWay;
    }

    public void setPayCerntWay(String payCerntWay)
    {
        this.payCerntWay = payCerntWay;
    }

    public String getChargeMoney()
    {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney)
    {
        this.chargeMoney = chargeMoney;
    }

    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public String getErrorPayCenterMsg()
    {
        return errorPayCenterMsg;
    }

    public void setErrorPayCenterMsg(String errorPayCenterMsg)
    {
        this.errorPayCenterMsg = errorPayCenterMsg;
    }

    public String getCuNumbers()
    {
        return cuNumbers;
    }

    public void setCuNumbers(String cuNumbers)
    {
        this.cuNumbers = cuNumbers;
    }
  
}