/*
 * 文 件 名:  AcrTheYearActFuncAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼Action类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.PrestoredRewardBean;
import com.customize.sd.selfsvc.prestoredReward.model.ActivityLogPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.prestoredReward.service.IPrestoredRewardService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 预存有礼Action类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求 
 */
public class PrestoredRewardAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Log logger = LogFactory.getLog(PrestoredRewardAction.class);
	
	/**
	 * service
	 */
	private transient IPrestoredRewardService prestoredRewardService;
	
	/**
	 * bean
	 */
	private transient PrestoredRewardBean prestoredRewardBean;
	
	/**
	 * 活动列表
	 */
	private List<PrestoredRewardPO> prestoredRewardList = new ArrayList<PrestoredRewardPO>();
	
	/**
	 * 错误信息
	 */
	private String errormessage;

	/**
	 * 当前菜单
	 */
	private String curMenuId;;
	
	/**
	 * 当前页 
	 */
	private int page;
	
	/**
	 * 总页数
	 */
	private int totalPages;
	
	/**
	 * 页容量
	 */
	private int pageSize = 5;
	
	/**
	 * 活动描述
	 */
	private String activityDesc;
	
	/**
	 * 活动编码
	 */
	private String activityId;
	
	/**
	 * 档次编码
	 */
	private String actLevelId;
	
	/**
	 * 档次名称
	 */
	private String actLevelName;
	
	/**
	 * 受理金额
	 */
	//findbugs修改
	private String prepayFee;
	
	/**
	 * 活动组id
	 */
	private String groupId;
	
	/**
	 * 活动组名称
	 */
	private String groupName;
	
	/**
	 * 奖品编码串
	 */
	private String actreward = "";
	
	/**
	 * 小票奖品信息
	 */
	private String awardDesc;
	
	/**
	 * 交费方式菜单
	 */
	private List<MenuInfoPO> payTypeFunc;
	
	/**
	 * 手机号
	 */
	private String telNum;
	
	/**
	 * 交费类型
	 */
	private String payType;
	
	/**
	 * 终端机流水
	 */
	private String terminalSeq;
	
	/**
	 * 交费金额
	 */
	private String tMoney;
	
	/**
	 * 交费金额
	 */
	private String tMoney_fen;
	
	/**
	 * 应交费用
	 */
	private String recFee = "0";
	
	/**
	 * 预存费用
	 */
	private String preFee;
	
	/**
	 * 交易流水
	 */
	private String recoid;
	
	/**
     * 银联扣款接口的返回码
     */
    private String unionRetCode = "";
	
	/**
	 * 银联卡小票打印信息
	 */
	private String printcontext = "";
	
	/**
	 * 是否为实物 1:实物，0：非实物
	 */
	private String isGoods = "0"; 
	
	/**
	 * 缴费流水
	 */
	private String chargeLogOID;
	
	// ------------------------------银联卡缴费相关字段--------------------------------------
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
	
	/** 
     * 获取用户可用活动
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public String qryActivitiesList()
	{
		logger.debug("qryActivitiesList Starting...");
		
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		
		// 地区
		prestoredRewardPO.setRegion(getTerminalInfoPO().getRegion());

		try
		{
			// 业务有效性校验
			prestoredRewardBean.checkRecValid(getTerminalInfoPO(), getCustomerSimp(), curMenuId);
			
			// 获取用户已存在的档次
			actLevelId = prestoredRewardBean.qrySubsActLevelList(getTerminalInfoPO(), getCustomerSimp(), curMenuId);
			if ("".equals(actLevelId))
			{
				actLevelId = "''";
			}
			
			// 档次编码
			prestoredRewardPO.setActLevelId(actLevelId);
			
			prestoredRewardPO.setRegion(getTerminalInfoPO().getRegion());
			
			// 查询用户可用活动
			prestoredRewardList = prestoredRewardService.getActivitiesList(prestoredRewardPO);
			
			// 绑定可用活动数量，分页时使用
			this.getRequest().setAttribute("recordCount", prestoredRewardList.size());
			
			// 对获取的活动进行分页
			prestoredRewardList = this.getPageList(prestoredRewardList);
			
			logger.debug("qryActivitiesList end!");
			return "qryActivitiesList";
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
	}

	/** 
     * 查询档次描述
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public String queryActLevelDesc()
	{
		logger.debug("queryActLevelDesc Starting...");
		try
		{
			// 调用接口查询奖品列表
			PrestoredRewardPO prestoredRewardPO = prestoredRewardBean.qryRewardList(getTerminalInfoPO(), getCustomerSimp(), curMenuId, actLevelId, activityId);
			
			// 是否为实物
			isGoods = prestoredRewardPO.getIsGoods();
			
			// 奖品编码串
			actreward = prestoredRewardPO.getActreward();
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
		logger.debug("queryActLevelDesc end!");
		
		return "queryActLevelDesc";
	}
	
	/** 
     * 选择支付方式
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public String selectPayType()
	{
		logger.debug("selectPayType Starting...");
		
		// 手机号
		telNum = getCustomerSimp().getServNumber();
		
		// 执行活动预处理
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("1"); // 1是预受理；0是受理
		prestoredRewardPO.setPaytype("cash");// 支付方式cash:现金  card:银联卡
		prestoredRewardPO.setActivityId(activityId);// 活动编码
		prestoredRewardPO.setActLevelId(actLevelId);// 档次编码
		prestoredRewardPO.setActreward(actreward);// 奖品编码串
		prestoredRewardPO.setTotalFee("");// 用户投入的费用
		
		try 
		{
			// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
			// 调用bean，执行活动预受理
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, "", "");
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

			// 获取活动费用，即应交金额
			recFee = prestoredRewardBean.qryActivityFee(getTerminalInfoPO(), getCustomerSimp(), curMenuId, activityId, actLevelId, actreward);
			
			recFee = CommonUtil.fenToYuan(recFee);
			List<MenuInfoPO> menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(getTerminalInfoPO().getTermgrpid());
			
			// 获取交费方式菜单
			payTypeFunc = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
			
			logger.debug("selectPayType end!");
			return "selectPayType";
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
	}
	
	/**
     * 转到投钞页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashCharge()
    {
        return SUCCESS;
    }
	
    /**
     * 现金交费完成
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashFinish()
    {
    	logger.debug("cashFinish Starting...");
    	
    	String forward = "success";
    	
    	// 防止用户不投币，直接从浏览器中模拟交费请求
    	String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            
            return "error";
        }   
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 获取交费日志流水
        chargeLogOID = prestoredRewardService.getChargeLogOID();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        
        String status = "01"; // 缴费状态
        payType = "Cash"; // 现金缴费
        String description = "交费之前记录投币日志"; // 交易结果描述
        
    	// 增加缴费前日志
    	this.addChargeLog(status, payType, description, Constants.PAYBYMONEY);

    	//============================预存有礼受理日志=================================
    	ActivityLogPO activityLogPO = new ActivityLogPO();
    	activityLogPO.setChargeId(chargeLogOID); // 缴费日志流水
    	activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // 地区
    	activityLogPO.setTelNum(telNum); // 用户手机号码
    	activityLogPO.setActivityId(activityId); // 活动编码
    	activityLogPO.setActLevelId(actLevelId); // 档次编码
    	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 预存有礼受理费用
    	activityLogPO.setTotalFee(CommonUtil.yuanToFen(tMoney)); // 用户实缴金额
    	activityLogPO.setPreFee("0"); // 用户预存金额
    	activityLogPO.setPayType("4"); // 支付方式 缴费方式，1：银联卡；4：现金
    	
		// 执行活动受理
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("0"); // 1是预受理；0是受理
		prestoredRewardPO.setPaytype("cash");// 支付方式cash:现金  card:银联卡
		prestoredRewardPO.setActivityId(activityId);// 活动编码
		prestoredRewardPO.setActLevelId(actLevelId);// 档次编码
		prestoredRewardPO.setActreward(actreward);// 奖品编码串
		prestoredRewardPO.setTotalFee(CommonUtil.yuanToFen(tMoney));// 用户投入的费用
		
		try 
		{
		    // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
		    // 业务办理前记录业务费用信息
		    prestoredRewardBean.writeNetFeeInfo(getTerminalInfoPO(), 
		        getCustomerSimp(), curMenuId, telNum, this.getChargeType("Cash"), terminalSeq, prestoredRewardPO);
		    
			// 调用bean，执行活动预受理
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, this.getChargeType("Cash"), terminalSeq);
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

			selfCardPayLogVO.setStatus("11"); // 缴费状态
			selfCardPayLogVO.setDescription("预存有礼受理成功"); // 描述
			selfCardPayLogVO.setDealnum(recoid); // BOSS交易流水
			
			// 记录业务成功日志
            this.createRecLog(telNum,Constants.PAY_BYCASH,chargeLogOID,tMoney,"0","预存有礼受理:自助终端缴费成功!");
            
            // 预存有礼受理日志
            activityLogPO.setRecStatus("02"); // 受理状态
            activityLogPO.setRedStatusDesc("预存有礼受理成功"); // 受理状态描述
            
            //modify begin lWX431760  2017-06-08 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            recFee = prepayFee; // 营销方案费用
            preFee = String.valueOf(new BigDecimal(tMoney).subtract(new BigDecimal(prepayFee)));
            //modify end lWX431760  2017-06-08 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            
            // 预存有礼受理日志
        	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 预存有礼受理费用
        	activityLogPO.setPreFee(CommonUtil.yuanToFen(preFee)); // 用户预存费用 
		}
		catch (ReceptionException e)
		{
			selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("预存有礼受理现金缴费失败！");

			// 记录业务成功日志
            this.createRecLog(telNum,Constants.PAY_BYCASH,chargeLogOID,tMoney,"1","预存有礼受理:自助终端缴费失败!");

            // 预存有礼受理日志
            activityLogPO.setRecStatus("01"); // 受理状态
            activityLogPO.setRedStatusDesc("预存有礼受理失败,凭交易凭条到营业厅办理退款!"); // 受理状态描述

			setErrormessage(e.getMessage());
			forward = "error";
		}
		tMoney = CommonUtil.fenToYuan(CommonUtil.yuanToFen(tMoney));
		// 更新缴费日志
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
		// 增加预存有礼受理日志
		prestoredRewardService.createActivityLog(activityLogPO);
		
		logger.debug("cashFinish end!");
		
    	return forward;
    }
    
    /**
     * 现金交费异常
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashError()
    {
    	logger.debug("cashError Starting...");
    	
    	// 获取交费日志流水
        chargeLogOID = prestoredRewardService.getChargeLogOID();
    	
    	// 交易状态
    	String status = "10";
    	
    	// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    	// 判断用户是否投币
    	if (CommonUtil.isEmpty(tMoney))
    	{
    		status = "00";
    	}
    	else
    	{
    	    ActivityLogPO activityLogPO = new ActivityLogPO();
            activityLogPO.setChargeId(chargeLogOID); // 缴费日志流水
            activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // 地区
            activityLogPO.setTelNum(telNum); // 用户手机号码
            activityLogPO.setActivityId(activityId); // 活动编码
            activityLogPO.setActLevelId(actLevelId); // 档次编码
            activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 活动受理金额
            activityLogPO.setTotalFee(CommonUtil.yuanToFen(tMoney)); // 用户实缴金额
            activityLogPO.setPreFee("0"); // 用户预存金额
            activityLogPO.setPayType(Constants.PAYBYMONEY); // 支付方式 缴费方式，1：银联卡；4：现金
            activityLogPO.setRecStatus("01"); // 受理状态
            activityLogPO.setRedStatusDesc(errormessage); // 受理状态描述
            
            // 增加预存有礼受理日志
            prestoredRewardService.createActivityLog(activityLogPO);
    	}
    	// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    	
    	tMoney = CommonUtil.fenToYuan(CommonUtil.yuanToFen(tMoney));
    	
    	// 支付方式
    	payType = "Cash";
    	
    	// 记录业务失败日志
    	this.createRecLog(telNum, Constants.PAY_BYCASH, "0", "0", "1", errormessage);
    	
    	// 增加缴费日志
    	this.addChargeLog(status, payType, errormessage, Constants.PAYBYMONEY);
    	
    	logger.debug("cashError end!");
    	
    	return "cashError";
    }
    
    /**
     * 银联卡交费，转到读银联卡页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardCharge()
    {
    	logger.debug("cardCharge Starting...");
    	
    	// 交易状态
    	String status = "00";
    	payType = "Card";
    	String description = "银联卡扣款之前记录日志";
    	tMoney = recFee;
    	
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
    	
    	// 获取交费日志流水
        chargeLogOID = prestoredRewardService.getChargeLogOID();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
    	
    	// 扣款之前增加缴费日志
    	this.addChargeLog(status, payType, description, "1");
    	
    	// 屏蔽超时返回首页的功能
        getRequest().setAttribute("sdCashFlag", "1");
        
        logger.debug("cardCharge end!");
        return SUCCESS;
    }
    
    /**
     * 银联卡交费完成
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardFinish()
    {
    	logger.debug("cardFinish Starting...");
    	
    	String forward = "success";
    	
    	// 防止用户不投币，直接从浏览器中模拟交费请求
    	String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            
            return "error";
        }   
        
        // 银联卡扣款金额
    	while (tMoney.startsWith("0"))
        {
            tMoney = tMoney.substring(1);
        }
    	
    	//============================预存有礼受理日志=================================
    	ActivityLogPO activityLogPO = new ActivityLogPO();
    	activityLogPO.setChargeId(chargeLogOID); // 缴费日志流水
    	activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // 地区
    	activityLogPO.setTelNum(telNum); // 用户手机号码
    	activityLogPO.setActivityId(activityId); // 活动编码
    	activityLogPO.setActLevelId(actLevelId); // 档次编码
    	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 预存有礼受理费用
    	activityLogPO.setTotalFee(tMoney); // 用户实缴金额
    	activityLogPO.setPreFee("0"); // 用户预存金额
    	activityLogPO.setPayType("1"); // 支付方式 缴费方式，1：银联卡；4：现金
    	
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 缴费日志流水
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        
		// 执行活动受理
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("0"); // 1是预受理；0是受理
		prestoredRewardPO.setPaytype("card");// 支付方式cash:现金  card:银联卡
		prestoredRewardPO.setActivityId(activityId);// 活动编码
		prestoredRewardPO.setActLevelId(actLevelId);// 档次编码
		prestoredRewardPO.setActreward(actreward);// 奖品编码串
		prestoredRewardPO.setTotalFee(tMoney);// 用户投入的费用
		
		try 
		{
		    // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
		    // 业务办理前记录业务费用信息
            prestoredRewardBean.writeNetFeeInfo(getTerminalInfoPO(), 
                getCustomerSimp(), curMenuId, telNum, this.getChargeType("Card"), terminalSeq, prestoredRewardPO);
		    
			// 调用bean，执行活动预受理
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, this.getChargeType("Card"), terminalSeq);
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

			selfCardPayLogVO.setStatus("11"); // 缴费状态
			selfCardPayLogVO.setDescription("预存有礼受理成功"); // 描述
			selfCardPayLogVO.setDealnum(recoid); // BOSS交易流水
			
			// 记录业务成功日志
            this.createRecLog(telNum,Constants.PAY_BYCARD,chargeLogOID,tMoney,"0","预存有礼受理:自助终端缴费成功!");
            
            // 预存有礼受理日志
            activityLogPO.setRecStatus("02"); // 受理状态
            activityLogPO.setRedStatusDesc("预存有礼受理成功"); // 受理状态描述

            //modify begin lWX431760  2017-06-08 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4           
            recFee = prepayFee; // 营销方案费用
            preFee = String.valueOf(new BigDecimal(tMoney).subtract(new BigDecimal(prepayFee)));
           
            // 预存有礼受理日志
        	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 预存有礼受理费用
        	activityLogPO.setPreFee(CommonUtil.yuanToFen(preFee)); // 用户预存费用 
        	//modify end lWX431760  2017-06-08 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
        	
            tMoney = CommonUtil.fenToYuan(tMoney);
		}
		catch (ReceptionException e)
		{
			selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("预存有礼受理银联卡缴费失败！");

			// 记录业务成功日志
            this.createRecLog(telNum,Constants.PAY_BYCARD,chargeLogOID,tMoney,"1","预存有礼受理:自助终端缴费失败!");

        	// 预存有礼受理日志
            activityLogPO.setRecStatus("01"); // 受理状态
            activityLogPO.setRedStatusDesc("预存有礼受理失败,凭交易凭条到营业厅办理退款!"); // 受理状态描述

            
			setErrormessage(e.getMessage());
			forward = "error";
		}
		
		// 更新缴费日志
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
		// 增加预存有礼受理日志
		prestoredRewardService.createActivityLog(activityLogPO);
		
		logger.debug("cashFinish end!");
		
    	return forward;
    }
    
    /**
     * 银联卡交费异常
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardError()
    {
    	logger.debug("cardError Starting...");
    	
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
         
        // 缴费日志流水
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
    	
    	// 交易状态
    	String status = "10";
    	
    	// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    	// 判断用户是否投币
    	if (CommonUtil.isEmpty(tMoney))
    	{
    		status = "00";
    	}
    	else
        {
            ActivityLogPO activityLogPO = new ActivityLogPO();
            activityLogPO.setChargeId(chargeLogOID); // 缴费日志流水
            activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // 地区
            activityLogPO.setTelNum(telNum); // 用户手机号码
            activityLogPO.setActivityId(activityId); // 活动编码
            activityLogPO.setActLevelId(actLevelId); // 档次编码
            activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // 活动受理金额
            activityLogPO.setTotalFee(tMoney); // 用户实缴金额
            activityLogPO.setPreFee("0"); // 用户预存金额
            activityLogPO.setPayType(Constants.PAYBYUNIONCARD); // 支付方式 缴费方式，1：银联卡；4：现金
            activityLogPO.setRecStatus("01"); // 受理状态
            activityLogPO.setRedStatusDesc(errormessage); // 受理状态描述
            
            // 增加预存有礼受理日志
            prestoredRewardService.createActivityLog(activityLogPO);
        }
        // modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    	
    	// 交易状态
    	selfCardPayLogVO.setStatus(status); 
    	
    	selfCardPayLogVO.setDescription(errormessage);
    	
    	// 银联卡返回码
    	selfCardPayLogVO.setPosResCode(unionRetCode);
    	
    	// 支付方式
    	payType = "Card";
    	
    	// 记录业务失败日志
    	this.createRecLog(telNum, Constants.PAY_BYCARD, "0", "0", "1", errormessage);
    	
    	// 更新缴费日志
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
    	logger.debug("cardError end!");
    	return SUCCESS;
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
        getResponse().setContentType("text/xml;charset=GBK");
        getRequest().setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("扣款之前记录日志失败");
        }
        
        String status = (String)getRequest().getParameter("status"); // 交易状态
        String description = (String)getRequest().getParameter("description"); // 
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        // 终端机归属地市
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        
        // 终端机归属组织机构
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        // 缴费流水
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        
        // 银联商户号（授卡方标识）
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        
        // 银联为刷卡的终端分配的唯一编号
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        // 业务类型
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        // 用户刷卡卡号
        cardChargeLogVO.setCardnumber(cardnumber);
        
        // 终端批次号
        cardChargeLogVO.setBatchnum(batchnum);
        
        // 授权码（山东用）
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        
        // 交易参考号（山东用）
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        
        // 银联实际扣款时间
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        
        // 评证号（山东用）
        cardChargeLogVO.setVouchernum(vouchernum);
        
        // 银联实际扣款金额，单位（分）
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
        
        // 终端机流水
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
            prestoredRewardService.updateChargeLog(cardChargeLogVO);
            
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
     * 增加缴费日志
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void addChargeLog(String status, String payType, String description, String payTypeNum)
	{
		logger.debug("addChargeLog Starting...");
		
		// 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // 组装日志数据
        selfCardPayLogVO.setChargeLogOID(chargeLogOID); // 日志流水
        selfCardPayLogVO.setRegion(getTerminalInfoPO().getRegion()); // region
        selfCardPayLogVO.setTermID(getTerminalInfoPO().getTermid()); // 终端机id
        selfCardPayLogVO.setOperID(getTerminalInfoPO().getOperid()); // 操作员id
        selfCardPayLogVO.setServnumber(telNum); // 手机号码
        selfCardPayLogVO.setPayType(payTypeNum); // 支付方式 现金"4"
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney)); // 缴费金额
        
        // 终端机流水
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = getTerminalInfoPO().getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        selfCardPayLogVO.setRecdate(payDate); // 受理时间
        selfCardPayLogVO.setAcceptType(""); // BOSS受理类型
        selfCardPayLogVO.setServRegion(getCustomerSimp().getRegionID()); // 手机号码归属地市
        selfCardPayLogVO.setOrgID(getTerminalInfoPO().getOrgid()); // 组织结构id
        selfCardPayLogVO.setStatus(status); // 投币成功的临时状态
        selfCardPayLogVO.setDescription(description);
        selfCardPayLogVO.setDealnum(""); // BOSS受理编号
        selfCardPayLogVO.setRecType("activity"); // 受理类型
        
        String chargeType = this.getChargeType(payType);
        selfCardPayLogVO.setBankno(chargeType + getTerminalInfoPO().getBankno());
        
        // modify begin zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        // 业务类型：补卡：ZZACT
        selfCardPayLogVO.setAcceptType(Constants.ACCEPTTYPE_PRESTORED_REWARD);
        // modify end zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        
        // 增加缴费前日志
        prestoredRewardService.addChargeLog(selfCardPayLogVO);
        
        logger.debug("addChargeLog end!");
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
        
        // 获取所有的缴费类型
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ChargeType);
        
        if (chargeTypeList != null)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                
                // 取当前支付类型的缴费类型
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        return chargeType;
    }
    
	/**
     * 取当前页面数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<PrestoredRewardPO> getPageList(List<PrestoredRewardPO> list)
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // 计算总条数
        int countNum = prestoredRewardList.size();
        
        // 计算总页数
        if (countNum % pageSize > 0)
        {
        	totalPages = countNum / pageSize + 1;
        }
        else
        {
        	totalPages = countNum / pageSize;
        }
        
        // 开始条数
        int startNum = pageSize * (page - 1) + 1;
        
        // 结束条数
        int endNum = pageSize * page;
        
        List<PrestoredRewardPO> preRewardList = new ArrayList<PrestoredRewardPO>();
        
        // 开始条数
        for(int i = startNum; i <= endNum; i++)
        {
            if (i <= countNum)
            {
                preRewardList.add(list.get(i-1));
            }
        }
        return preRewardList;
    }

	public List<PrestoredRewardPO> getPrestoredRewardList() {
		return prestoredRewardList;
	}

	public void setPrestoredRewardList(List<PrestoredRewardPO> prestoredRewardList) {
		this.prestoredRewardList = prestoredRewardList;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PrestoredRewardBean getPrestoredRewardBean() {
		return prestoredRewardBean;
	}

	public void setPrestoredRewardBean(PrestoredRewardBean prestoredRewardBean) {
		this.prestoredRewardBean = prestoredRewardBean;
	}

	public IPrestoredRewardService getPrestoredRewardService() {
		return prestoredRewardService;
	}

	public void setPrestoredRewardService(
			IPrestoredRewardService prestoredRewardService) {
		this.prestoredRewardService = prestoredRewardService;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActLevelId() {
		return actLevelId;
	}

	public void setActLevelId(String actLevelId) {
		this.actLevelId = actLevelId;
	}

	public String getActLevelName() {
		return actLevelName;
	}

	public void setActLevelName(String actLevelName) {
		this.actLevelName = actLevelName;
	}


	public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getActreward() {
		return actreward;
	}

	public void setActreward(String actreward) {
		this.actreward = actreward;
	}

	public List<MenuInfoPO> getPayTypeFunc() {
		return payTypeFunc;
	}

	public void setPayTypeFunc(List<MenuInfoPO> payTypeFunc) {
		this.payTypeFunc = payTypeFunc;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getRecFee() {
		return recFee;
	}

	public void setRecFee(String recFee) {
		this.recFee = recFee;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public String getPrintcontext() {
		return printcontext;
	}

	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}

	public String getTMoney_fen() {
		return tMoney_fen;
	}

	public void setTMoney_fen(String money_fen) {
		tMoney_fen = money_fen;
	}

	public String getIsGoods() {
		return isGoods;
	}

	public void setIsGoods(String isGoods) {
		this.isGoods = isGoods;
	}

	public String getChargeLogOID() {
		return chargeLogOID;
	}

	public void setChargeLogOID(String chargeLogOID) {
		this.chargeLogOID = chargeLogOID;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getUnionpaycode() {
		return unionpaycode;
	}

	public void setUnionpaycode(String unionpaycode) {
		this.unionpaycode = unionpaycode;
	}

	public String getUnionpayuser() {
		return unionpayuser;
	}

	public void setUnionpayuser(String unionpayuser) {
		this.unionpayuser = unionpayuser;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public String getUnionpayfee() {
		return unionpayfee;
	}

	public void setUnionpayfee(String unionpayfee) {
		this.unionpayfee = unionpayfee;
	}

	public String getUnionpaytime() {
		return unionpaytime;
	}

	public void setUnionpaytime(String unionpaytime) {
		this.unionpaytime = unionpaytime;
	}

	public String getAuthorizationcode() {
		return authorizationcode;
	}

	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	public String getBusinessreferencenum() {
		return businessreferencenum;
	}

	public void setBusinessreferencenum(String businessreferencenum) {
		this.businessreferencenum = businessreferencenum;
	}

	public String getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}

	public String getAwardDesc() {
		return awardDesc;
	}

	public void setAwardDesc(String awardDesc) {
		this.awardDesc = awardDesc;
	}

	public String getUnionRetCode() {
		return unionRetCode;
	}

	public void setUnionRetCode(String unionRetCode) {
		this.unionRetCode = unionRetCode;
	}

}
