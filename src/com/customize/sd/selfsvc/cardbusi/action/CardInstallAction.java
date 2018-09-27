/*
 * 文 件 名:  CardInstallAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  湖北在线开户(空白卡开户)
 * 修 改 人: zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.sd.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 山东在线开户(空白卡开户)
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求]
 */
public class CardInstallAction extends CardBusiBaseAction
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8336831060603314515L;

    /** 
     * 日志对象
     */
    private static Log logger = LogFactory.getLog(CardInstallAction.class);
    
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
    
    /** ---------------- 产品 -------------------------
     * 产品列表
     */
    private List<ProdTempletPO> tpltTempletList;
    
    /** 
     * 产品模板
     */
    private ProdTempletPO tpltTempletPO;
    
    /** 
     * 开户号码
     */
    private String telnum; 
    
    /** 
     * 空白卡卡号
     */
    private String blankno;
    
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
     * 缴费方式，1：银联卡；4：现金
     */
    private String payType;
    
    /**
     * 用户投币面额信息
     */
    private String cashDetail = "";
    
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
     * subsid
     */
    private String subsid;
    
    /**
     * 开户受理流水
     */
    private String formnum = "";
    
    /**
     * 开户备注
     */
    private String notes = "";
    
    /**
     * 银行卡号
     */
    private String cardnumber;
    
    /**---------日志处理--------------
     * 缴费日志
     */
    private transient FeeChargeService feeChargeService;

    /**
     * chargelog表里字段
     */
    private String bankno;
    
    /**
     * 银联扣款接口的返回码
     */
    private String unionRetCode = "";
    
    /**
     * 服务密码
     */
    private String passwd;
    
    /**
     * 写卡所需信息串
     * iccid~~imsi~~issueData~~formNum;
     */
    private String cardInfoStr;
    
    /**
     * 受理流水
     */
    private String dealNum;
    
    /**
     * 预定号码列表
     */
    private List<ServerNumPO> bookedTelnumList; 

    
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
     * @remark modify by sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
     */
    public String inputTelnumByRule()
    {
        //使用预约号码进行开户
        if(Constants.BOOKED_TELNUM.equals(selTelRule))
        {
            return qryBookedTelNum();
        }
        
        return "inputTelnumByRule";
    }
    
    /**
     * <查询用户已预约的号码>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
     */
    private String qryBookedTelNum()
    {
        try
        {
            //查询已预约号码
            bookedTelnumList = getCardBusiService().qryBookedTelnum(idCardPO.getIdCardNo(), getCurMenuId());
            
            //分页,每页5条
            bookedTelnumList = super.getPageList(bookedTelnumList, 5);
        }
        
        //查询失败，或用户未预约过号码，提示用户返回上一页采用其他方式选号，不跳转错误页面
        catch (ReceptionException e)
        {
            logger.error("查询用户已预约号码失败：", e);
            setErrormessage(e.getMessage());
        }
        
        return "bookedTelnum";
    }
    
    /** 
     * 依据选号规则查询号码列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String telnumResult()
    {
        // 开始记录日志
        logger.debug("telNumResult start...");
        
        try
        {
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
     * 设置服务密码页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String setPasswd()
    {
        return "setPasswd";
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
     * 校验空白卡信息
     * 1. 校验空白卡序号是否可用
     * 2. 校验空白卡是否是预置空卡
     * 3. 空白卡资源预暂、获取加密数据
     * @see [类、类#方法、类#成员]
     */
    public void chkBlankCardInfo()
    {
        logger.debug("chkBlankCardInfo Starting...");
        
        // 超时不自动跳转首页
        this.getRequest().setAttribute("sdCashFlag", "1");
        String xml = "";
        try
        {
        	String prodid = getRequest().getParameter("prodid");
        	
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // 受理类型，开户Install，补卡ChangeEnum
            String recType = "Install";
            
            // 调用接口校验卡资源是否可用
            this.getCardInstallBean().chkBlankNo(termInfo, getCurMenuId(), blankno, prodid, recType);
            
            // 校验空白卡是否是预置空卡
            this.getCardInstallBean().chkPreSetBlankCard(termInfo, getCurMenuId(), blankno, telnum, recType);
            
            // 空白卡资源预占
            SimInfoPO simInfo = cardInstallBean.getEncryptedData(termInfo, getCurMenuId(),blankno, telnum, recType);

            // 空白卡资源校验成功
			xml = "0~~" + simInfo.getWriteCardData();
        }
        catch (ReceptionException re)
        {
            logger.error("校验空白卡资源失败：", re);
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            xml = "1~~" + re.getMessage();
        }
        // 打印信息
        writeXmlMsg(xml);
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
        this.getRequest().setAttribute("sdCashFlag", "1");

        bankno = this.getChargeType("Cash") + getTerminalInfoPO().getBankno();
        
     	// 增加缴费日志  缴费成功10 
        addChargeLog();
        
        // 转向投币页面
        return "cashCharge";
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
        
        // 银联卡返回的tMoney单位为分
        if (Constants.PAYBYUNIONCARD.equals(payType))
        {
            tMoney = CommonUtil.fenToYuan(tMoney);
        }
        
        // 更新开户日志  缴费成功0 写卡成功0
        updateLogInstall();
        
        try
        {
            // add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
            // 业务办理前记录业务费用信息
            cardInstallBean.writeNetFeeInfo(getTerminalInfoPO(),
                getCustomerSimp(), getCurMenuId(), telnum, 
                this.getChargeType(Constants.PAYBYMONEY.equals(payType) ? "Cash" : "Card"),
                buildTerminalSeq(terminalSeq, payType), CommonUtil.yuanToFen(tMoney), 
                Constants.ACCEPTTYPE_CARDINSTALL);
            // add end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
            
        	// 写卡成功接口
            simInfoPO.setWriteResult("1");
            simInfoPO.setErrCode("0");
            simInfoPO.setErrMsg("写卡成功");
            
            // 写卡成功
            cardInstallBean.updateWriteCardResult(getTerminalInfoPO(), curMenuId, blankno, simInfoPO);
        	
            // 开户需要空白卡号
            simInfoPO.setBlankno(blankno);
            
            // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
            // 调用开户接口接口获取开户流水
            Map<String, String> map = cardInstallBean.terminalInstall(this.getTerminalInfoPO(),
            		curMenuId, idCardPO, tpltTempletPO, telnum, CommonUtil.yuanToFen(tMoney),
                simInfoPO, passwd, this.getChargeType(Constants.PAYBYMONEY.equals(payType) ? "Cash" : "Card"), 
                buildTerminalSeq(terminalSeq, payType));
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

            // 开户流水
            formnum = map.get("formnum");
            
            // subsid
            subsid = map.get("subsid");
            
            // 受理流水
            dealNum = map.get("dealnum");
            
            // 开户状态 1：失败 0：成功
            installStatus = "0";
            notes = "开户成功！";
            
            // 更新开户日志  开户成功0  开户流水
            updateLogInstall();
            
            // 更新缴费日志
            this.updateChargeLog("11","开户成功！");
            
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "0", notes);
            
            return SUCCESS;
        }
        catch (ReceptionException e)
        {
            // 开户状态 1：失败 0：成功
            installStatus = "1";  
            notes = "开户失败！";
            
            // 更新开户日志
            updateLogInstall();
            
            // 更新缴费日志
            this.updateChargeLog("10", "开户时缴费成功，开户失败！");
            
            // 写卡失败
            // updateWriteCardResult();
            
            errormessage = e.getMessage();
            
            return installError();
        }
    }

    /**
     * 转向读银行卡页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toReadCard()
    {
    	logger.debug("toReadCard Starting...");
    	// 超时不自动跳转首页
        this.getRequest().setAttribute("sdCashFlag", "1");
    	bankno = this.getChargeType("Card") + getTerminalInfoPO().getBankno();
    	
    	// 记录扣款前日志
    	addChargeLog();
    	
    	logger.debug("toReadCard end!");
        return "toReadCard";
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
        
        // 组织机构id
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        // region
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        
        // 商户编号
        cardChargeLogVO.setUnionpayuser(request.getParameter("unionpayuser"));
       
        // 终端编号
        cardChargeLogVO.setUnionpaycode(request.getParameter("unionpaycode"));
        
        // 交易类型
        cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(request.getParameter("busitype"), "UTF-8"));

        //往数据库里存入加密后的银联卡号
        cardChargeLogVO.setCardnumber(request.getParameter("cardnumber"));
        
        // 批次号
        cardChargeLogVO.setPosNum(request.getParameter("batchnum"));
        
        // POS流水号
        cardChargeLogVO.setBatchnum(request.getParameter("posnum"));
        
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
        if (unionpayfee.startsWith("0"))
        {
            unionpayfee = unionpayfee.substring(1);
        }
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
            
            logger.error("扣款成功后记录日志异常：", e);
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
     * 现金、银联卡缴费异常
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installFeeError()
    {
    	try
    	{
    		// 银联卡返回的金额单位是分，转换为元
            if (Constants.PAYBYUNIONCARD.equals(payType))
            {
                tMoney = CommonUtil.fenToYuan(tMoney);
            }
            
    		this.updateChargeLog();
	        
	        // 记录错误日志到sh_rec_log
	        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", errormessage);
	    }
	    catch (Exception e)
	    {
	        logger.error("记录扣款错误日志异常：", e);
	        errormessage = errormessage + e.getMessage();
	    }
    	return "installError";
    }
    
    /**
     * 记录写卡异常日志
     * @return
     * @see
     */
    public String makeErrLog()
    {
        errormessage = errormessage.length()< 256 ? errormessage : errormessage.substring(0,256);
        
        // 记录开户日志
        updateLogInstall();
        
        return installFeeError();
    }
    
    /**
     * 记录开户日志
     */
    public void addLogInstall()
    {
    	logger.debug("addLogInstall Starting...");
    	
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
        
        logger.debug("addLogInstall end!");
    }
    
    /**
     * 增加缴费日志
     */
    public void addChargeLog()
    {
    	logger.debug("addChargeLog Starting...");
        // 终端信息
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // 缴费日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
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
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setServRegion(region);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setBankno(bankno);
        
        if (Constants.PAYBYUNIONCARD.equals(payType))
        {
        	// 缴费金额
        	selfCardPayLogVO.setFee(CommonUtil.yuanToFen(recFee));
        }
        
    	// 10：投币或扣款成功
    	selfCardPayLogVO.setStatus("00");
        
        // 描述
        selfCardPayLogVO.setDescription("写卡开户之前的缴费");
        
        // 业务类型(presetinstall:预置空白卡开户缴费)
        selfCardPayLogVO.setRecType("presetinstall");
        
        // modify begin zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        //业务类型：补卡：ZZKH
        selfCardPayLogVO.setAcceptType(Constants.ACCEPTTYPE_CARDINSTALL);
        // modify end zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        
        // 添加缴费日志
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        // 缴费流水号，与SH_CHARGE_lOG关联ChargeLogOID
        chargeId = logOID;
        
        logger.debug("updateLogInstall end!");
    }
    
    /** 
     * <更新交费日志>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    private void updateChargeLog()
    {
        updateChargeLog(null, null);
    }
    
    /**
     * 更新缴费日志
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateChargeLog(String status, String description)
    {
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
    	selfCardPayLogVO.setChargeLogOID(chargeId);
    	
    	// 组织机构id
    	selfCardPayLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        // region
    	selfCardPayLogVO.setRegion(getTerminalInfoPO().getRegion());
    	
    	// 状态
        if (tMoney == null || "".equals(tMoney.trim()))
        {
            selfCardPayLogVO.setStatus("00");
        }
        else
        {
            selfCardPayLogVO.setStatus(CommonUtil.isEmpty(status) ? "10" : status);
            selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        }
        
        if (Constants.PAYBYMONEY.equals(payType))
        {
        	// 现金缴费终端流水(终端id+现金缴费流水 并取后20位)
            terminalSeq = getTerminalInfoPO().getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        
        // 错误信息
        selfCardPayLogVO.setDescription(CommonUtil.isEmpty(description) ? errormessage : description);
        selfCardPayLogVO.setDealnum(dealNum);
        
        // 银联返回错误码
        selfCardPayLogVO.setPosResCode(unionRetCode);
        
        cardBusiService.updateCardChargeLog(selfCardPayLogVO);
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
        cardBusiLogPO.setToFee("".equals(tMoney)?"":CommonUtil.yuanToFen(tMoney));
        
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
        
        // subsid
        cardBusiLogPO.setSubsId(subsid);
        
        // 更新开户日志
        cardBusiService.updateInstallLog(cardBusiLogPO);
        
        logger.debug("addChargeLog end!");
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
            
            String errCode = getRequest().getParameter("errCode");
            String errMsg = getRequest().getParameter("errMsg");
            String writeResult = getRequest().getParameter("writeResult");
            
            // 设置写卡结果信息
            simInfoPO.setWriteResult(writeResult);
            simInfoPO.setErrMsg(errCode);
            simInfoPO.setErrCode(errMsg);
            
            // 开户失败调用卡作废接口
            cardInstallBean.updateWriteCardResult(this.getTerminalInfoPO(), curMenuId, blankno, simInfoPO);
        }
        catch (ReceptionException re)
        {
            logger.error("开户失败调用卡作废接口失败!", re);
        }
    }
    
    /** 
     * 重新生成终端缴费流水号
     * 
     * @param terminalSeq 终端缴费流水号
     * @param payType 支付类型
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2015-05-08 OR_SD_201503_333_SD_自助终端活动受理预存赠送
     */
    private String buildTerminalSeq(String terminalSeq, String payType)
    {
        if (Constants.PAYBYMONEY.equals(payType))
        {
            // 现金缴费终端流水(终端id+现金缴费流水 并取后20位)
            terminalSeq = getTerminalInfoPO().getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
        }
        
        return terminalSeq;
    }

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getSelTelRule() {
		return selTelRule;
	}

	public void setSelTelRule(String selTelRule) {
		this.selTelRule = selTelRule;
	}

	public String getTelPrefix() {
		return telPrefix;
	}

	public void setTelPrefix(String telPrefix) {
		this.telPrefix = telPrefix;
	}

	public String getTelSuffix() {
		return telSuffix;
	}

	public void setTelSuffix(String telSuffix) {
		this.telSuffix = telSuffix;
	}

	public List<ServerNumPO> getServerNumList() {
		return serverNumList;
	}

	public void setServerNumList(List<ServerNumPO> serverNumList) {
		this.serverNumList = serverNumList;
	}

	public List<ProdTempletPO> getTpltTempletList() {
		return tpltTempletList;
	}

	public void setTpltTempletList(List<ProdTempletPO> tpltTempletList) {
		this.tpltTempletList = tpltTempletList;
	}

	public ProdTempletPO getTpltTempletPO() {
		return tpltTempletPO;
	}

	public void setTpltTempletPO(ProdTempletPO tpltTempletPO) {
		this.tpltTempletPO = tpltTempletPO;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getBlankno() {
		return blankno;
	}

	public void setBlankno(String blankno) {
		this.blankno = blankno;
	}

	public List getUsableTypes() {
		return usableTypes;
	}

	public void setUsableTypes(List usableTypes) {
		this.usableTypes = usableTypes;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCashDetail() {
		return cashDetail;
	}

	public void setCashDetail(String cashDetail) {
		this.cashDetail = cashDetail;
	}

	public String getWriteCardStatus() {
		return writeCardStatus;
	}

	public void setWriteCardStatus(String writeCardStatus) {
		this.writeCardStatus = writeCardStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getInstallId() {
		return installId;
	}

	public void setInstallId(String installId) {
		this.installId = installId;
	}

	public String getFormnum() {
		return formnum;
	}

	public void setFormnum(String formnum) {
		this.formnum = formnum;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public FeeChargeService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getUnionRetCode() {
		return unionRetCode;
	}

	public void setUnionRetCode(String unionRetCode) {
		this.unionRetCode = unionRetCode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCardInfoStr() {
		return cardInfoStr;
	}

	public void setCardInfoStr(String cardInfoStr) {
		this.cardInfoStr = cardInfoStr;
	}

	public String getSubsid() {
		return subsid;
	}

	public void setSubsid(String subsid) {
		this.subsid = subsid;
	}

	public String getDealNum() {
		return dealNum;
	}

	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

    public List<ServerNumPO> getBookedTelnumList()
    {
        return bookedTelnumList;
    }

    public void setBookedTelnumList(List<ServerNumPO> bookedTelnumList)
    {
        this.bookedTelnumList = bookedTelnumList;
    }
	
}
