/*
 * 文 件 名:  CardInstallAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  湖北在线开户(空白卡开户)
 * 修 改 人: zKF69263
 * 修改时间:  2014-10-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.hub.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * 湖北在线开户(空白卡开户)
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_HUB_201405_478 关于自助终端支持现场写卡的需求之在线开户
 */
public class CardInstallAction extends CardBusiBaseAction {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8336831060603314515L;

    /** 
     * 日志对象
     */
    private static Log logger = LogFactory.getLog(CardInstallAction.class);
    
    /** 
     * 产品列表
     */
    private List<ProdTempletPO> tpltTempletList;
    
    /**  ---------------- 机构 ---------------------
     * 
     * 地区
     */
    private String region;
    
    /** 
     * 地区名称
     */
    private String regionName;
    
    /**  ---------------- 选号 ---------------------
     * 
     * 查询类型
     */
    private String selTelRule;
    
    /** 
     * 号码前缀
     */
    private String telPrefix;
    
    /** 
     * 号码后缀
     */
    private String telSuffix;
    
    /** 
     * 每页号码列表
     */
    private List<ServerNumPO> serverNumList = null;
    
    /** 
     * 存放全部手机号码数据
     */
    private List<ServerNumPO> tmpList = null;
    
    /** ---------------- 产品 -------------------------
     * 
     * 产品ID
     */
    private String prodid;
    
    /** 
     * 空白卡卡号
     */
    private String blankno;
    
    /** 
     * 开户类型
     */
    private String rectype;
    
    /** 
     * 开户号码
     */
    private String telnum; 
    
    /** 
     * 卡类型
     */
    private String cardtype;
    
    /** 
     * 模板ID
     */
    private String prodTempletId;
    
    /** 
     * 预存款
     */
    private String minfee;
    
    /** 
     * 产品模板
     */
    private ProdTempletPO tpltTempletPO;

    /**---------------- 菜单相关 -------------------------
     * 
     * 菜单相关
     */
    private List usableTypes = null;
    
    /** ---------------- 投币相关 -------------------------
     * 
     * 投币金额
     */
    private String tMoney;
    
    /** 
     * 投币器流水号
     */
    private String terminalSeq;
    
    /** ---------------- 发票相关 -------------------------
     * 
     * 发票打印标志，0＝不打印 1＝打印
     */
    private String payType;
    
    /**
     * 用户投币面额信息
     */
    private String cashDetail = "";
    
    /**
     * 凭条交易时间
     */
    private String pDealTime = "";
    
    /**
     * 是否退还银联卡
     */
    private String needReturnCard = "";
    
    /**
     * 默认2：初始状态 0：写卡成功 1：写卡失败
     */
    private String writeCardStatus = "";
    
    /**
     * 默认2：初始状态 0：缴费成功 1：缴费失败
     */
    private String payStatus = "";  
    
    /**
     * 默认2：初始状态 0：开户成功 1：开户失败
     */
    private String installStatus = ""; 
    
    /**
     * 缴费流水号，与SH_CHARGE_lOG关联 缴费前此列数据为空
     */
    private String chargeId = "";

    /**
     * 开户流水号
     */
    private String installId = "";
    
    /**
     * 实收费用
     */
    private String toFee = "";
    
    /**
     * 开户受理流水
     */
    private String formnum = "";
    
    /**
     * 开户备注
     */
    private String notes = "";
    
    /**---------日志处理--------------
     * 缴费日志
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
     * 银行卡号
     */
    private String cardnumber;
    
    /**
     * 写卡所需信息串
     * iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2;
     */
    private String cardInfoStr;
    

    /**
     * 入网协议
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String agreement()
    {
        return "agreement";
    }
    
    /** 
     * 取得开户协议列表
     * 
     * @return List<DictItemPO>
     * @see [类、类#方法、类#成员]
     */
    public List<DictItemPO> getAgreementList()
    {
        return getDictItemByGrp("CardInstallAgreement");
    }
    
    /**
     * 读取身份证
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String readCert()
    {
        return "readCert";
    }
    
    /**
     * 身份证信息展现
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String certShow()
    {
        return "certShow";
    }
    
    /** 
     * 选择产品页面
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String selectProd()
    {
        try
        {
            // 获取终端机信息
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // 证件类型
            String certtype = "IdCard";
            
            // 证件号码
            String certid = getIdCardPO().getIdCardNo();
            
            // 调用接口校验证件号码下的用户数量
            this.getCardInstallBean().chkCertSubs(termInfo, getCurMenuId(), certtype, certid);
            
            // 设置产品模板Region
            ProdTempletPO prodTempletPO = new ProdTempletPO();
            prodTempletPO.setRegion(termInfo.getRegion());
            
            // 查询产品列表
            tpltTempletList = getCardBusiService().qryProdTempletList(prodTempletPO);

            if (null != tpltTempletList && tpltTempletList.size() >  0)
            {
                tpltTempletList = super.getPageList(tpltTempletList, 5);
            }
        }
        catch (ReceptionException re)
        {
            // 设置错误信息
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            return "installError";
        }
        
        // 转向产品列表页面
        return "selectProd";
    }
    
    /**
     * 选择选号规则页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectRule()
    {
        // 获取终端机信息
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // 获取缓存中的地区列表
        List<RegionInfoPO> regionListTmp = (List<RegionInfoPO>)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        
        // 循环查找终端机所在地区名称和组织机构
        for (RegionInfoPO regionInfoPO : regionListTmp)
        {
            if (termInfo.getRegion().equals(regionInfoPO.getRegion()))
            {
                region = regionInfoPO.getRegion();
                regionName = regionInfoPO.getRegionname();
            }
        }
        
        // 返回
        return "selectRule";
    }
    /**
     * 号码前缀后缀输入页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String inputTelnumByRule()
    {
        return "inputTelnumByRule";
    }
    /** 
     * 依据选号规则查询号码列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String telnumResult()
    {
        // 开始记录日志
        logger.debug("telNumResult start...");
        
        // 超时不自动跳转首页
        this.getRequest().setAttribute("telProdFlag", "1");
        
        try
        {
            // 设置产品ID
            this.setProdid(this.getTpltTempletPO().getMainProdId());
                   
            // 调用接口查询号码信息列表
            serverNumList = this.getCardInstallBean().qryTelNumberListByRule(getTerminalInfoPO(),getCurMenuId(), selTelRule, telPrefix, telSuffix,tpltTempletPO.getMainProdId());

            if (null != serverNumList && serverNumList.size() >  0)
            {
                serverNumList = super.getPageList(serverNumList, 18);
            }
        }
        catch (ReceptionException re)
        {
            // 设置错误信息
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            return "installError";
        }
        
        logger.debug("telNumResult End ...");

        return "telnumResult";
    }

    /**
     * 号码资源占选
     * 
     * @see [类、类#方法、类#成员]
     */
    public void telnumTmpPick()
    {
        logger.debug("telnumTmpPick Starting...");
        
        String xml = "";
        
        try
        {
            // 调用号码资源占选接口
            this.getCardInstallBean().telNumTmpPick(getTerminalInfoPO(), getCurMenuId(), telnum);
            
            // 号码资源占选成功
            xml = "0";
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error("号码资源占选失败：", re);
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
        }
        
        // 打印信息
        writeXmlMsg(xml);

        logger.debug("telnumTmpPick end!");
    }
    
    /**
     * 校验卡资源是否可用
     * 
     * @see [类、类#方法、类#成员]
     */
    public void chkBlankNo()
    {
        logger.debug("chkBlankNo Starting...");
        
        String xml = "";
        
        try
        {
            TerminalInfoPO terminalInfo = getTerminalInfoPO();
            
            // 调用接口校验卡资源是否可用
            this.getCardInstallBean().chkBlankNo(terminalInfo, getCurMenuId(), blankno);
            
            // 校验空白卡是否是预置空卡
            this.getCardInstallBean().chkPreSetBlankCard(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // 校验卡资源可用
            xml = "0";
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error(re.getMessage(), re);
        }
        
        // 打印信息
        writeXmlMsg(xml);
        
        logger.debug("chkBlankNo End!");
    }
    
    /**
     * 空白卡资源暂选
     * 
     * @see [类、类#方法、类#成员]
     */
    public void blankCardTmpPick() throws Exception
    {
        logger.debug("blankCardTmpPick Starting...");
        
        String xml = "";
        
        try
        {
            // 调用接口空白卡资源暂选
            simInfoPO = this.getCardInstallBean().blankCardTmpPick(getTerminalInfoPO(), getCurMenuId(), blankno, telnum);
            
            // 空白卡资源暂选成功
            xml = "0~~" + simInfoPO.toString();
            cardInfoStr = simInfoPO.toString();
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error("空白卡资源暂选失败：", re);
        }
        
        // 打印信息
        writeXmlMsg(xml);
        
        logger.debug("blankCardTmpPick End!");
    }
    
    /** 
     * 校验空白卡信息
     * 1. 号码暂选
     * 2. 校验空白卡序号是否可用
     * 3. 校验空白卡是否是预置空卡
     * 4. 空白卡资源暂选
     * 
     * @see [类、类#方法、类#成员]
     */
    public String chkBlankCardInfo()
    {
        logger.debug("chkBlankCardInfo Starting...");
        
        try
        {
            TerminalInfoPO terminalInfo = getTerminalInfoPO();
            
            // 调用号码资源暂选接口
            this.getCardInstallBean().telNumTmpPick(terminalInfo, getCurMenuId(), telnum);
            
            // 调用接口校验卡资源是否可用
            this.getCardInstallBean().chkBlankNo(terminalInfo, getCurMenuId(), blankno);
            
            // 校验空白卡是否是预置空卡
            this.getCardInstallBean().chkPreSetBlankCard(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // 调用接口空白卡资源暂选
            simInfoPO = this.getCardInstallBean().blankCardTmpPick(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // 空白卡资源暂选成功
            cardInfoStr = simInfoPO.toString().replace("+", "^^");
            
            // 计算费用
            return feeConfirm();
        }
        catch (ReceptionException re)
        {
            logger.error("校验空白卡资源失败：", re);
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            // 设置错误信息
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            return "installError";
        }
        // logger.debug("chkBlankCardInfo End!");
    }
    
    /**
     * 费用确认页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String feeConfirm()
    {
        // 开始记录日志
        logger.debug("feeConfirm start...");
        
        try
        {
            // 调用算费接口
            CRSet crset = this.getCardInstallBean().reckonRecFee(this.getTerminalInfoPO(), curMenuId, telnum, tpltTempletPO, simInfoPO, blankno);
            
            // 总费用
            BigDecimal allFee = new BigDecimal("0");
        
            this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
            feeList = new ArrayList<FeeConfirmPO>();
            
            FeeConfirmPO feeConfirmPO = null;        
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                feeConfirmPO = new FeeConfirmPO();
                
                // 费用名称
                feeConfirmPO.setFeeName((crset.GetValue(i, 0)));
                
                // 应交费用
                feeConfirmPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 5)));
                
                // 数量
                feeConfirmPO.setNum(crset.GetValue(i, 2));
                
                // 费用类型
                feeConfirmPO.setFeeType(crset.GetValue(i, 3));
                
                allFee = allFee.add(new BigDecimal(crset.GetValue(i, 5)));
                feeList.add(feeConfirmPO);
            }       
        
            // 添加预存款项
            String preMinFee=this.tpltTempletPO.getMinFee();
            feeConfirmPO = new FeeConfirmPO();
            feeConfirmPO.setFeeName("套餐产品预存款");
            feeConfirmPO.setFee(CommonUtil.fenToYuan(preMinFee));
            feeConfirmPO.setNum("1");
            feeConfirmPO.setFeeType("预存款项");
            allFee = allFee.add(new BigDecimal(preMinFee));
            feeList.add(feeConfirmPO);
        
            // 添加费用合计
            feeConfirmPO = new FeeConfirmPO();
            feeConfirmPO.setFeeName("费用合计");
            recFee = allFee.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            feeConfirmPO.setFee(recFee);
            feeConfirmPO.setNum("1");
            feeConfirmPO.setFeeType("");
            feeList.add(feeConfirmPO);
        }
        catch (ReceptionException e)
        {
            errormessage = e.getMessage();
            return installError();
        }
        return "feeConfirm";
    }
    
    /**
     * 选择缴费类型
     * @return String
     */
    public String selectPayType()
    {
        logger.debug("selectPayType start!");
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    
        // 费用确认后记录开户信息
        addLogInstall();
    
        // 充值缴费方式列表
        usableTypes = getPayType(termInfo.getTermgrpid());
        
        // findbugs修改 2015-09-02 zKF66389
//        if (logger.isInfoEnabled())
//        {
//            logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//        }
        // findbugs修改 2015-09-02 zKF66389
        
        // findbugs修改 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
        if (usableTypes.size() == 0)
        // findbugs修改 2015-09-02 zKF66389
        {
            // 没有可用的充值方式
            setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
            
            // 记录日志
            this.createRecLog(telnum, "feeCharge", "0", "0", "1", "暂时没有可用的充值方式");
            
            return ERROR;
        }
        
        logger.debug("selectPayType end!");
        
        return "selectPayType";
    }
    
    /**
     * 转向投币页面
     * @return String
     */
    public String cashChargeInstall()
    {
        // 超时不自动跳转首页
        this.getRequest().setAttribute("telProdFlag", "1");

        // 转向投币页面
        return "cashCharge";
    }
    
    /**
     * 转向读银行卡页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toReadCard()
    {
        return "toReadCard";
    }
    
    /**
     * 进入银联卡密码输入页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputCardPwd()
    {
        return "cardPassword";
    }
    
    /**
     * 转向确认银行卡缴费金额页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toMakeSure()
    {
        // 超时不自动跳转首页
        this.getRequest().setAttribute("telProdFlag", "1");
        
        return "toMakeSure";
    }
    
    /**
     * 现金缴费开户处理
     * @return String
     */
    public String cashCommitInstall()
    {
        logger.debug("cashCommitInstall start");
        
        logger.info("用户" + telnum + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney + "；流水：" + terminalSeq);
        
        // 防止用户不投币，直接从浏览器中模拟交费请求
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            return installError();
        }    
     
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 重复交费问题
        if (!checkCashFee(termInfo))
        {
            String tipMsg = "非常抱歉，您本次缴费可能有误，请持小票凭证至营业前台查询核实，由此给您带来的不便，敬请谅解！";
            setErrormessage(tipMsg);
            return installError();
        }
        
        // 增加缴费日志  缴费成功10 
        addCashChargeLog();

        // 空白卡开户
        return installCardCommit();
    }
    
    /**
     * 空白卡开户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installCardCommit()
    {
        logger.debug("installCardCommit start");
        
        CardChargeLogVO cardChargeLogPO = new CardChargeLogVO();
        cardChargeLogPO.setChargeLogOID(chargeId);
        
        // 更新开户日志  缴费成功0 写卡成功0
        updateLogInstall();
        
        try
        {
            // 开户需要空白卡号
            simInfoPO.setBlankno(blankno);
            
            // 调用开户接口接口获取开户流水
            formnum = cardInstallBean.terminalInstall(this.getTerminalInfoPO(), curMenuId, idCardPO, tpltTempletPO, telnum,CommonUtil.yuanToFen(toFee),simInfoPO);
        }
        catch (ReceptionException e)
        {
            // 开户状态 1：失败 0：成功
            installStatus = "1";  
            notes = "开户失败！";
            
            // 更新开户日志
            updateLogInstall();
            
            // 描述
            cardChargeLogPO.setDescription("开户时缴费成功，开户失败！");
            
            // 更新缴费日志
            cardBusiService.updateCardChargeLog(cardChargeLogPO);
            
            // 写卡失败
            updateWriteCardResult();
            
            errormessage = e.getMessage();
            
            return installError();
        }
        
        // 开户状态 1：失败 0：成功
        installStatus = "0";
        notes = "开户成功！";
        
        // 更新开户日志  开户成功0  开户流水
        updateLogInstall();
        
        // 11：扣款成功，缴费成功：开户成功
        cardChargeLogPO.setStatus("11");
        cardChargeLogPO.setDescription("开户成功！");
        
        // 更新缴费日志
        cardBusiService.updateCardChargeLog(cardChargeLogPO);
        
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "0", notes);
        
        return SUCCESS;
    }
    
    /**
     * 扣款之前增加银联卡缴费日志
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        logger.debug("addCardPayLog Starting...");
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("UTF-8");

        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        String posNum = (String)request.getParameter("posnum");
        String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        chargeId = feeChargeService.getChargeLogOID();
        
        cardChargeLogVO.setChargeLogOID(chargeId);
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setTermID(termInfo.getTermid());
        cardChargeLogVO.setOperID(termInfo.getOperid());
        cardChargeLogVO.setServnumber(telnum);
        cardChargeLogVO.setPayType(payType);
        
        // 扣款金额 分
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        // 往数据库里存入加密后的银联卡号
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(request.getParameter("cardnumber")));
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        // 扣款时间
        String payDate =  CommonUtil.getCurrentTime();
        cardChargeLogVO.setRecdate(payDate);
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        cardChargeLogVO.setServRegion(termInfo.getRegion());
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setPosNum(posNum);
        cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
        
        // 业务类型（presetinstall: 预置空白卡开户缴费）
        cardChargeLogVO.setRecType("presetinstall");
        
        String xml = "";
        try
        {
            // 插入缴费日志
            feeChargeService.addChargeLog(cardChargeLogVO);
            xml = "1~~" + chargeId;
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        
        // 打印信息
        writeXmlMsg(xml);
        
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
        logger.debug("updateCardChargeLog Starting...");
        
        HttpServletRequest request = this.getRequest();

        // 状态
        String status = (String)request.getParameter("status");
        
        // 描述
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        // id
        cardChargeLogVO.setChargeLogOID(chargeId);
        
        // 商户编号
        cardChargeLogVO.setUnionpayuser(request.getParameter("unionpayuser"));
       
        // 终端编号
        cardChargeLogVO.setUnionpaycode(request.getParameter("unionpaycode"));
        
        // 交易类型
        cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(request.getParameter("busitype"), "UTF-8"));

        //往数据库里存入加密后的银联卡号
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(request.getParameter("cardnumber")));

        // 批次号
        cardChargeLogVO.setBatchnum(request.getParameter("batchnum"));
        
        // 授权码
        cardChargeLogVO.setAuthorizationcode(request.getParameter("authorizationcode"));
        
        // 交易参考号
        cardChargeLogVO.setBusinessreferencenum(request.getParameter("businessreferencenum"));
        
        // 银联扣款时间
        cardChargeLogVO.setUnionpaytime(request.getParameter("unionpaytime"));
        
        // 凭证号
        cardChargeLogVO.setVouchernum(request.getParameter("vouchernum"));
        
        // 终端流水
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        // 状态
        cardChargeLogVO.setStatus(status);
        
        // 描述
        cardChargeLogVO.setDescription(description);
        
        // 交易时间
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // 银联返回字段
        cardChargeLogVO.setPosResCode(request.getParameter("posResCode"));
        
        // 扣款金额
        String unionpayfee = request.getParameter("unionpayfee");
        
        // modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
        unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
        // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
        
        cardChargeLogVO.setUnionpayfee(unionpayfee);
 
        String xml = "";
        try
        {
            // 更新缴费日志
            cardBusiService.updateCardChargeLog(cardChargeLogVO);
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }

        // 打印信息
        writeXmlMsg(xml);
        
        logger.debug("updateCardChargeLog end!");
    }
    
    /**
     * 处理异常
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installError()
    {
        // 记录错误日志到sh_rec_log
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", errormessage);
        
        return "installError";
    }
    
    /**
     * 记录异常日志
     * @return
     * @see
     */
    public String makeErrLog()
    {
        // 现金缴费
        if("4".equals(payType))
        {
            // 记录缴费日志
            addCashChargeLog();
        }
        
        notes = errormessage.length()< 256 ? errormessage : errormessage.substring(0,256);
        
        // 记录开户日志
        updateLogInstall();
        
        
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", notes);
        
        return "installError";
    }
    
    /**
     * 记录开户日志
     */
    public void addLogInstall()
    {
        //获取终端机信息
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
       
        CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();

        // 流水号
        installId = this.getCardBusiService().getInstallId();
        cardBusiLogPO.setOid(installId);
        
        // 终端号
        cardBusiLogPO.setTermId(termInfo.getTermid());
        
        // 地区
        cardBusiLogPO.setRegion(termInfo.getRegion());
        
        // 手机号
        cardBusiLogPO.setServnumber(telnum);
        
        // 办理类型 presetinstall 预置空白卡开户
        cardBusiLogPO.setRectype("presetinstall");
        
        // 主体产品编码
        cardBusiLogPO.setMainProdId(tpltTempletPO.getMainProdId());
        
        // 产品模板编码
        cardBusiLogPO.setProdTempletId(tpltTempletPO.getTempletId());
        
        // 开户应收费用
        cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(recFee));
        
        // 客户名称
        cardBusiLogPO.setCustName(this.getIdCardPO().getIdCardName());
        
        // 身份证号
        cardBusiLogPO.setCertId(this.getIdCardPO().getIdCardNo());
        
        // 身份证上地址
        cardBusiLogPO.setLinkAddr(this.getIdCardPO().getIdCardAddr());
        
        // 性别
        cardBusiLogPO.setSex(this.getIdCardPO().getIdCardSex());
        
        // 空白卡号
        cardBusiLogPO.setBlankCard(blankno);
        
        // ICCID
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // IMSI
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // SMSP
        cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
       
        // 默认2：初始状态 0：写卡成功 1：写卡失败 
        cardBusiLogPO.setWriteCardStatus("2");
        
        // 默认2：初始状态 0：缴费成功 1：缴费失败 
        cardBusiLogPO.setPayStatus("2");
        
        // 开户状态 默认2：初始状态 0：开户成功 1：开户失败
        cardBusiLogPO.setInstallStatus("2");
        
        cardBusiLogPO.setWriteCardStatus("2");
        
        // 付款状态 默认2： 0：缴费成功 1：缴费失败
        cardBusiLogPO.setPayStatus("2");
        
        // 默认2：初始状态 0：退款成功 1：退款失败
        cardBusiLogPO.setRefundment("2");
        
        // 备注
        cardBusiLogPO.setNotes(tpltTempletPO.getNotes());
        
        // 记录日志
        this.getCardBusiService().addLogInstall(cardBusiLogPO);
    }
    
    /**
     * 增加缴费日志
     */
    public void addCashChargeLog()
    {
        // 终端信息
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // 缴费日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 终端流水(终端id+现金缴费流水 并取后20位)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        
        // 缴费流水
        String logOID = feeChargeService.getChargeLogOID();
        
        // 缴费时间
        String payDate = CommonUtil.getCurrentTime();
        
        // 组装数据
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(telnum);
        selfCardPayLogVO.setPayType(payType);
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setServRegion(region);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // 10：投币或扣款成功
        selfCardPayLogVO.setStatus("10");
        
        // 描述
        selfCardPayLogVO.setDescription("写卡开户之前的缴费");
        
        // 业务类型(presetinstall:预置空白卡开户缴费)
        selfCardPayLogVO.setRecType("presetinstall");
        
        // 实际缴费金额（数据库单位 分）
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(toFee));
        
        // 添加缴费日志
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        // 缴费流水号，与SH_CHARGE_lOG关联ChargeLogOID
        chargeId = logOID;
    }
    
    /**
     * 更新开户日志
     */
    public void updateLogInstall()
    {
        logger.debug("updateLogInstall Starting...");
        
        CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();
        
        // 流水号
        cardBusiLogPO.setOid(installId);
        
        // 缴费流水号，与SH_CHARGE_lOG关联 缴费前此列数据为空
        cardBusiLogPO.setChargeId(chargeId);
        
        // 缴费方式，1：银联卡；4：现金
        cardBusiLogPO.setChargeType(payType);
        
        // 实收费用
        cardBusiLogPO.setToFee("".equals(toFee)?"":CommonUtil.yuanToFen(toFee));
        
        // 默认2：初始状态 0：缴费成功 1：缴费失败 
        cardBusiLogPO.setPayStatus(payStatus);
        
        // 默认2：初始状态 0：写卡成功 1：写卡失败 
        cardBusiLogPO.setWriteCardStatus(writeCardStatus);
        
        // 默认2：初始状态 0：开户成功 1：开户失败
        cardBusiLogPO.setInstallStatus(installStatus);
        
        // 开户受理流水
        cardBusiLogPO.setFormnum(formnum);
        
        cardBusiLogPO.setNotes(notes);
        
        // 空白卡号
        cardBusiLogPO.setBlankCard(blankno);
        
        // ICCID
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // IMSI
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // SMSP
        cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
        
        // 更新开户日志
        cardBusiService.updateInstallLog(cardBusiLogPO);
        
        logger.debug("updateLogInstall end!");
    }
    
    /**
     * 重复交费判断
     * 
     * @param termInfo 终端机信息
     * @return true，未重复；false：重复
     * @since 
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {   
        logger.debug("checkCashFee start!");
        
        String seq = termInfo.getTermid().concat(terminalSeq);
        
        String tmpSeq = seq.concat(telnum);
        
        // 如果有相同的串，则是重复缴费
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            pDealTime = DateUtil.getCurrentDateTime();
            
            String tmpErrorMsg = "重复缴费:手机号[".concat(telnum).concat("],投币金额[").concat(tMoney).concat("]元,归属营业厅[")
                    .concat(termInfo.getOrgname()).concat("],流水号[").concat(seq).concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(telnum);
            
            // 现金投币
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            
            // 现金缴费流水,终端id+厂商生成流水
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(pDealTime);
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // 记录重复缴费日志
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // 记录到SH_REC_LOG,交易流水号这里记录现金缴费流水号
            this.createRecLog(telnum, Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
    }
    
    /**
     * 异步获取写卡加密数据
     */
    public void asynGetEncryptedData()
    {
        String xml = "";
        try
        {
            String cardformnum = this.getRequest().getParameter("cardformnum");
            // 卡信息
            simInfoPO = new SimInfoPO(cardInfoStr);
            simInfoPO.setBlankno(blankno);
            
            // 获取开卡信息加密数据
            String encryptedData = getCardInstallBean().getEncryptedData(getTerminalInfoPO(),curMenuId,telnum,simInfoPO,cardformnum);
            xml = "0~~"+encryptedData;
        }
        catch (ReceptionException e)
        {
            xml = "1~~校验卡资源是否可用失败";
            logger.error("获取写卡加密数据失败：", e);
        }
        
        // 打印信息
        writeXmlMsg(xml);
       
    }
    
    /**
     * 验证写卡结果
     */
    public void checkWriteCardInfo()
    {   
        String  writeCardInfo = this.getRequest().getParameter("writeCardInfo");
        
        // 写卡流水
        String cardformnum = this.getRequest().getParameter("cardformnum");
        
        // 卡信息
        simInfoPO = new SimInfoPO(cardInfoStr);
        
        String result = "";
        
        try
        {
            // 校验写卡结果 0：成功  1：失败
            result = cardInstallBean.checkWriteCardInfo(this.getTerminalInfoPO(), curMenuId, cardformnum, blankno, writeCardInfo);
            
            // 没有成功，调用写卡失败
            if(!"0".equals(result))
            {
                // 写卡失败
                updateWriteCardResult();
            }
        }
        catch (ReceptionException re)
        {
            result = "1~"+re.getMessage();
            
            // 写卡失败
            updateWriteCardResult();
         
            logger.error("验证写卡结果报文失败!", re);
        }
 
        // 打印信息
        writeXmlMsg(result);
    }
    
    /**
     * 异步调用卡作废接口
     */
    public void asynUpdateWriteCardResult()
    {
        String xml = "";
        
        try
        {
            // 写卡失败
            updateWriteCardResult();
            
            xml = "0";    
        }
        catch (ReceptionException re)
        {
            xml = "1~"+re.getMessage();
         
            logger.error("开户失败调用卡作废接口失败!", re);
        }
 
        // 打印信息
        writeXmlMsg(xml);
    }
    
    /**
     * 调用写卡失败接口
     */
    public void updateWriteCardResult()
    {
        try
        {
            simInfoPO = new SimInfoPO(cardInfoStr);
            
            // 设置写卡结果信息
            simInfoPO.setWriteResult("-1");
            simInfoPO.setErrMsg("写卡失败");
            simInfoPO.setErrCode("-1");
            
            // 开户失败调用卡作废接口
            cardInstallBean.updateWriteCardResult(this.getTerminalInfoPO(), curMenuId, blankno, simInfoPO);
        }
        catch (ReceptionException re)
        {
            logger.error("开户失败调用卡作废接口失败!", re);
        }
        
    }
    
    /**
     * @return 返回 tpltTempletList
     */
    public List<ProdTempletPO> getTpltTempletList()
    {
        return tpltTempletList;
    }

    /**
     * @param 对tpltTempletList进行赋值
     */
    public void setTpltTempletList(List<ProdTempletPO> tpltTempletList)
    {
        this.tpltTempletList = tpltTempletList;
    }

    /**
     * @return 返回 region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param 对region进行赋值
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    /**
     * @return 返回 regionName
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRegionName()
    {
        return regionName;
    }
    /**
     * @param 对regionName进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    /**
     * @return 返回 selTelRule
     */
    public String getSelTelRule()
    {
        return selTelRule;
    }

    /**
     * @param 对selTelRule进行赋值
     */
    public void setSelTelRule(String selTelRule)
    {
        this.selTelRule = selTelRule;
    }

    /**
     * @return 返回 telPrefix
     */
    public String getTelPrefix()
    {
        return telPrefix;
    }

    /**
     * @param 对telPrefix进行赋值
     */
    public void setTelPrefix(String telPrefix)
    {
        this.telPrefix = telPrefix;
    }

    /**
     * @return 返回 telSuffix
     */
    public String getTelSuffix()
    {
        return telSuffix;
    }

    /**
     * @param 对telSuffix进行赋值
     */
    public void setTelSuffix(String telSuffix)
    {
        this.telSuffix = telSuffix;
    }

    /**
     * @return 返回 serverNumList
     */
    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    /**
     * @param 对serverNumList进行赋值
     */
    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    /**
     * @return 返回 tmpList
     */
    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    /**
     * @param 对tmpList进行赋值
     */
    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }
    /**
     * @return 返回 prodid
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdid()
    {
        return prodid;
    }
    /**
     * @param 对prodid进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    /**
     * @return 返回 blankno
     */
    public String getBlankno()
    {
        return blankno;
    }

    /**
     * @param 对blankno进行赋值
     */
    public void setBlankno(String blankno)
    {
        this.blankno = blankno;
    }
    /**
     * @return 返回 rectype
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRectype()
    {
        return rectype;
    }
    /**
     * @param 对rectype进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    /**
     * @return 返回 telnum
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTelnum()
    {
        return telnum;
    }
    /**
     * @param 对telnum进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    /**
     * @return 返回 cardtype
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCardtype()
    {
        return cardtype;
    }
    /**
     * @param 对cardtype进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCardtype(String cardtype)
    {
        this.cardtype = cardtype;
    }

    /**
     * @return 返回 prodTempletId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdTempletId()
    {
        return prodTempletId;
    }

    /**
     * @param 对prodTempletId进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdTempletId(String prodTempletId)
    {
        this.prodTempletId = prodTempletId;
    }
    /**
     * @return 返回 minfee
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMinfee()
    {
        return minfee;
    }
    /**
     * @param 对minfee进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMinfee(String minfee)
    {
        this.minfee = minfee;
    }

    /**
     * @return 返回 tpltTempletPO
     */
    public ProdTempletPO getTpltTempletPO()
    {
        return tpltTempletPO;
    }

    /**
     * @param 对tpltTempletPO进行赋值
     */
    public void setTpltTempletPO(ProdTempletPO tpltTempletPO)
    {
        this.tpltTempletPO = tpltTempletPO;
    }

    /**
     * @return 返回 usableTypes
     */
    public List getUsableTypes()
    {
        return usableTypes;
    }

    /**
     * @param 对usableTypes进行赋值
     */
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
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
     * @return 返回 terminalSeq
     */
    public String getTerminalSeq()
    {
        return terminalSeq;
    }

    /**
     * @param 对terminalSeq进行赋值
     */
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }

    /**
     * @return 返回 payType
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * @param 对payType进行赋值
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
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
    /**
     * @return 返回 pDealTime
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getpDealTime()
    {
        return pDealTime;
    }
    /**
     * @param 对pDealTime进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setpDealTime(String pDealTime)
    {
        this.pDealTime = pDealTime;
    }

    /**
     * @return 返回 needReturnCard
     */
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }

    /**
     * @param 对needReturnCard进行赋值
     */
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }

    /**
     * @return 返回 writeCardStatus
     */
    public String getWriteCardStatus()
    {
        return writeCardStatus;
    }

    /**
     * @param 对writeCardStatus进行赋值
     */
    public void setWriteCardStatus(String writeCardStatus)
    {
        this.writeCardStatus = writeCardStatus;
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
     * @return 返回 installStatus
     */
    public String getInstallStatus()
    {
        return installStatus;
    }

    /**
     * @param 对installStatus进行赋值
     */
    public void setInstallStatus(String installStatus)
    {
        this.installStatus = installStatus;
    }

    /**
     * @return 返回 chargeId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getChargeId()
    {
        return chargeId;
    }

    /**
     * @param 对chargeId进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }

    /**
     * @return 返回 installId
     */
    public String getInstallId()
    {
        return installId;
    }

    /**
     * @param 对installId进行赋值
     */
    public void setInstallId(String installId)
    {
        this.installId = installId;
    }

    /**
     * @return 返回 toFee
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getToFee()
    {
        return toFee;
    }

    /**
     * @param 对toFee进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setToFee(String toFee)
    {
        this.toFee = toFee;
    }
    /**
     * @return 返回 formnum
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getFormnum()
    {
        return formnum;
    }
    /**
     * @param 对formnum进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }

    /**
     * @return 返回 notes
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * @param 对notes进行赋值
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getTMoney()
    {
        return tMoney;
    }

    public void setTMoney(String money)
    {
        tMoney = money;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPDealTime()
    {
        return pDealTime;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPDealTime(String dealTime)
    {
        pDealTime = dealTime;
    }

    public FeeChargeHubService getFeeChargeService()
    {
        return feeChargeService;
    }

    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }

    public String getCardnumber()
    {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }

    public String getCardInfoStr()
    {
        return cardInfoStr;
    }

    public void setCardInfoStr(String cardInfoStr)
    {
        this.cardInfoStr = cardInfoStr;
    }
    
   
}
