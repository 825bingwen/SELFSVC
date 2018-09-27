/*
 * 文件名：CmpayAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：手机支付主账户充值
 * 修改人：g00140516
 * 修改时间：2010-12-24
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.charge.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.CmpayBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.CmpayService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESEncrypt;

/**
 * 
 * 手机支付主账户充值
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-24
 * @see
 * @since
 */
public class CmpayAction extends BaseAction
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    private static Log logger = LogFactory.getLog(CmpayAction.class);
    
    private transient CmpayBean cmpayBean = null;
    
    private transient CmpayService cmpayService = null;
    
    private String servnumber = "";
    
    private String cashBalance = "";
    
    private String cardBalance = "";
    
    /**
     * 待充值金额，单位 元
     */
    private String shouldPay = "";
    
    private String curMenuId = "";
    
    private String terminalSeq = "";
    
    private String bossSeq = "";
    
    private String dealTime = "";
    
    private String cmpaySeq = "";
    
    private String newCashBalance = "";
    
    private String tMoney = "";
    
    private String errormessage;
    
    private String payType = "";
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
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

    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public CmpayBean getCmpayBean()
    {
        return cmpayBean;
    }
    
    public void setCmpayBean(CmpayBean cmpayBean)
    {
        this.cmpayBean = cmpayBean;
    }
    
    /**
     * 手机支付主账户充值，不需要身份认证，但是需要输入两遍号码，以保证充值号码正确
     * 
     * @return
     * @see
     */
    public String inputNumber()
    {
        return "inputNumber";
    }
    
    /**
     * 手机支付主账户信息查询
     * 
     * @return
     * @see
     */
    public String qryCmpayAccount()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCmpayAccount Starting ...");
        }
        
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            String actionTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            
            StringBuffer buffer = new StringBuffer(1024);
            buffer.append(servnumber)
                    .append(";")
                    .append(termInfo.getOrgid())
                    .append(";")
                    .append(termInfo.getOperid())
                    .append(";")
                    .append(actionTime);
            
            // DES加密
            String securePwd = new DESEncrypt().encrypt(buffer.toString());
            
            Vector result = cmpayBean.qryCmpayAccount(termInfo, servnumber, securePwd, actionTime, curMenuId);
            if (result != null && result.size() > 0)
            {
                if (logger.isInfoEnabled())
                {
                    logger.info(servnumber + "手机支付主账户信息查询成功");
                }
                
                cashBalance = CommonUtil.liToYuan((String)result.get(10));
                cardBalance = CommonUtil.liToYuan((String)result.get(11));
                shouldPay = CommonUtil.liToYuan((String) result.get(12));
                
                //根据终端组自缓存中获取可用充值方式
                String groupID = termInfo.getTermgrpid();
                
                List<MenuInfoPO> menus = null;
                
                if (groupID != null && !"".equals(groupID))
                {                    
                    menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(groupID);
                }
                
                // 当页菜单列表
                usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
                
            	// findbugs修改 2015-09-02 zKF66389
//                if (logger.isInfoEnabled())
//                {
//                    logger.info("当前，主账户充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
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
                    this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", "暂时没有可用的充值方式");
                }
                else
                {
                    forward = "success";  
                }
            }
            else
            {
                request.setAttribute("errormessage", "手机支付主账户信息查询失败，请稍后再试。");
                
                logger.error(servnumber + "手机支付主账户信息查询失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", "手机支付主账户信息查询失败");
            }
        }
        catch (Exception e)
        {
            logger.error(servnumber + "手机支付主账户信息查询失败", e);
            
            request.setAttribute("errormessage", "手机支付主账户信息查询失败，请稍后再试。");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", e.getMessage());
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCmpayAccount End");
        }
        
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
     * 充值
     * 
     * @return
     * @throws Exception
     * @see
     */
    public String commitCharge() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("commitCharge Starting ...");
        }
        
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();     
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 生成投币日志
        String logOID = cmpayService.getChargeLogOID();
        
        // 组装数据
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);           
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);//现金投币日志                   
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney)); 
        
        // 终端流水(终端id+现金缴费流水 并取后20位)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }            
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date date = new Date();
        
        String actionTime = sdf1.format(date);
        dealTime = sdf3.format(date);
        
        selfCardPayLogVO.setRecdate(actionTime);        
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion("");
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("缴费之前记录投币日志");
        selfCardPayLogVO.setDealnum(""); // boss缴费流水 缴费成功后返回
        selfCardPayLogVO.setRecType("mpay");// 业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值）
        selfCardPayLogVO.setMpayseq("");//手机支付系统流水号
        
        cmpayService.addCmpayLog(selfCardPayLogVO);

        try
        {
            StringBuffer secure_pwd_temp = new StringBuffer(1024);
            secure_pwd_temp.append(servnumber).append(";").append(termInfo.getOrgid())
                    .append(";").append(termInfo.getOperid()).append(";").append(actionTime);
                
            String securePwd = new DESEncrypt().encrypt(secure_pwd_temp.toString());
                
            Vector result = cmpayBean.recMainFee(securePwd, servnumber, termInfo, actionTime, tMoney, curMenuId);
            if (result != null && result.size() > 0)
            {
                bossSeq = (String)result.get(0);// BOSS流水号
                cmpaySeq = (String)result.get(1);// 手机支付系统流水号
                newCashBalance = CommonUtil.liToYuan((String)result.get(2));// 主账户可提现余额
                    
                selfCardPayLogVO.setRecdate(actionTime);
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("主账户充值成功！");
                selfCardPayLogVO.setDealnum(bossSeq);
                selfCardPayLogVO.setMpayseq(cmpaySeq);
                    
                forward = "success";
                    
                if (logger.isInfoEnabled())
                {
                    logger.info(servnumber + "手机支付主账户充值成功");
                }
                    
                this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "0", "手机支付主账户充值成功");
            }
            else
            {
                selfCardPayLogVO.setRecdate(actionTime);            
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setDescription("主账户充值失败！");
                selfCardPayLogVO.setDealnum("");
                selfCardPayLogVO.setMpayseq("");

                request.setAttribute("errormessage", "手机支付主账户充值失败");
                
                logger.error(servnumber + "手机支付主账户充值失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", "手机支付主账户充值失败");
            }            
        }
        catch (Exception e)
        {
            selfCardPayLogVO.setRecdate(actionTime);            
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("主账户充值失败！");
            selfCardPayLogVO.setDealnum("");
            selfCardPayLogVO.setMpayseq("");
            
            request.setAttribute("errormessage", "手机支付主账户充值失败");
            
            logger.error(servnumber + "手机支付主账户充值失败", e);           
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", e.getMessage());
        }
        
        cmpayService.updateCmpayLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("commitCharge End");
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
        
        this.createRecLog(servnumber, Constants.BUSITYPE_RECMAINFEE, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(cmpayService.getChargeLogOID());
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
        selfCardPayLogVO.setServRegion("");
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("mpay");// 业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值）
             
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
        
        cmpayService.addCmpayLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getCashBalance()
    {
        return cashBalance;
    }
    
    public void setCashBalance(String cashBalance)
    {
        this.cashBalance = cashBalance;
    }
    
    public String getCardBalance()
    {
        return cardBalance;
    }
    
    public void setCardBalance(String cardBalance)
    {
        this.cardBalance = cardBalance;
    }
    
    public String getShouldPay()
    {
        return shouldPay;
    }
    
    public void setShouldPay(String shouldPay)
    {
        this.shouldPay = shouldPay;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getBossSeq()
    {
        return bossSeq;
    }

    public void setBossSeq(String bossSeq)
    {
        this.bossSeq = bossSeq;
    }

    public String getDealTime()
    {
        return dealTime;
    }
    
    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }
    
    public String getCmpaySeq()
    {
        return cmpaySeq;
    }
    
    public void setCmpaySeq(String cmpaySeq)
    {
        this.cmpaySeq = cmpaySeq;
    }
    
    public String getNewCashBalance()
    {
        return newCashBalance;
    }
    
    public void setNewCashBalance(String newCashBalance)
    {
        this.newCashBalance = newCashBalance;
    }
    
    public String getTMoney()
    {
        return tMoney;
    }
    
    public void setTMoney(String money)
    {
        tMoney = money;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public CmpayService getCmpayService()
    {
        return cmpayService;
    }
    
    public void setCmpayService(CmpayService cmpayService)
    {
        this.cmpayService = cmpayService;
    }
}
