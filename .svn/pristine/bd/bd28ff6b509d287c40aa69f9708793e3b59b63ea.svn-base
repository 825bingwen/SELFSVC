package com.customize.hub.selfsvc.charge.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.gmcc.boss.selfsvc.bean.NonlocalChargeBean;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.ChargeService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;
/**
 * 
 * <跨省异地缴费>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Mar 23, 2015]
 * @see  [相关类/方法]
 * @since  [OR_NX_201501_1030 跨省异地缴费]
 */
public class NonlocalChargeAction extends BaseAction
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(NonlocalChargeAction.class);
    
    /**
     * 投币金额
     */
    private String tMoney;
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 错误类型
     */
    private String errorType;
    
    /**
     * 是否需要退卡，1：需要
     */
    private String needReturnCard;
    
    /**
     * 应缴费用 元
     */
    private String yingjiaoFee;
    
    /**
     * 银联打印信息
     */
    private String printcontext;
    
    /**
     * 调用一级boss bean
     */
    private transient NonlocalChargeBean nonlocalChargeBean;
    
    /**
     * 记录重复缴费日志
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
     * 菜单id
     */
    private String curMenuId = "";
    
	/**
	 * 缴费日志信息类
	 */
    private transient CardChargeLogVO cardChargeLogVO;
	
    /**
     * 充值日志记录
     */
    private transient ChargeService chargeService;
    
    /**
     * 可用缴费方式列表
     */
    private List usableTypes;
    
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
     * 话费充值账户信息查询（账户信息+支付方式）
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {
        logger.debug("qryfeeChargeAccount start");
        
        // 如果账户信息查询失败转到缴费登录页面
        String forward = ERROR;
        
        try
        {
        	//终端机信息
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//调用一级boss接口，查询应缴话费
            Map<String,String> qryResult = nonlocalChargeBean.qryPayAmount(curMenuId, termInfo, 
            		cardChargeLogVO.getServnumber());
                
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // 手机号码
            customerSimp.setServNumber(cardChargeLogVO.getServnumber());
            
            // 客户名称 模糊化
            customerSimp.setCustomerName(CommonUtil.blurNameHUB(qryResult.get("CustName")));
            
            // 放入SESSION
            this.getRequest().getSession().setAttribute(Constants.USER_INFO, customerSimp);
            
            //应交费用 元
            cardChargeLogVO.setFee(CommonUtil.fenToYuan(qryResult.get("PayAmount")));
            
            //省份编码
            cardChargeLogVO.setProvinceCode(qryResult.get("ProvinceCode"));
            
            //充值方式
            usableTypes = CommonUtil.qryUsablePayTypes(termInfo.getTermgrpid(), curMenuId);
            
            //没有可用的充值方式
            if (null == usableTypes || usableTypes.size() == 0)
            {
                // 没有可用的充值方式
                setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
                
                // 记录日志
                this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_FALID, "暂时没有可用的充值方式");
            }
            else
            {
                // 记录成功日志
                this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_SUCCESS, "查询交费方式成功");
                forward = SUCCESS;
            }
        }
        catch (ReceptionException e)
        {
            logger.error("查询应缴费用失败！",e);
            setErrormessage(e.getMessage());
            
            // 记录异常日志
            this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_FALID, e.getMessage());
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
     * 话费充值执行现金缴费
     * 
     * @return
     */
    public String chargeCommit()
    {
        logger.debug("ChargeCommit start");
        
        String forward = ERROR;
        
    	//终端机信息
    	TerminalInfoPO termInfo = this.getTerminalInfoPO();
    	
    	//日志id
    	String logOID = "";
        
    	//若为现金缴费，记录现金缴费日志
    	if (Constants.PAY_BYCASH.equals(cardChargeLogVO.getPayType()))
    	{
    		// 设定缴费方式
    	    if(!feeChargeService.checkCashFee(termInfo, cardChargeLogVO.getServnumber(), 
    	    		CommonUtil.yuanToFen(tMoney), cardChargeLogVO.getTerminalSeq()))
    	    {
                //记录到SH_REC_LOG,交易流水号这里记录现金缴费流水号
    	    	this.createRecLog(cardChargeLogVO.getServnumber(), 
    	    			Constants.BUSITYPE_NONLOCAL_CHARGE, cardChargeLogVO.getTerminalSeq(), 
    	    			CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_FALID, "湖北跨省重复缴费！");
    	    	
    	        return "repeatError";
    	    }
            
            // 发起缴费请求之前首先记录投币日志
    	    logOID = chargeService.addCashLog(cardChargeLogVO.getServnumber(),CommonUtil.yuanToFen(tMoney) , 
    	    		cardChargeLogVO.getTerminalSeq(), cardChargeLogVO.getAcceptType(), 
    	    		Constants.PROVINCE_REGION_999, 
    	    		Constants.PAYSUCCESS_CHARGEERROR, "交费之前记录投币日志", Constants.BUSITYPE_NONLOCAL_CHARGE, "",
    	    		cardChargeLogVO.getProvinceCode(), termInfo);
    	}
    	else
    	{
    		//若为银联缴费，则需要先转为元，用于页面显示，与现金交费统一
    		tMoney = CommonUtil.fenToYuan(tMoney);
    		logOID = cardChargeLogVO.getChargeLogOID();
    	}
	    
	    //生成交费流水号
        String dealnum = chargeService.getNonlocalChargeSeq();
        cardChargeLogVO.setDealnum(dealnum);
        
        try 
        {
        	//直接调用一级BOSS接口进行交费
        	nonlocalChargeBean.nonlocalCharge(curMenuId, termInfo, cardChargeLogVO.getServnumber(),
        			dealnum, CommonUtil.yuanToFen(tMoney));
			
			//更新交费状态
        	chargeService.updateChargeStatus(logOID, Constants.CHARGE_SUCCESS, "交费成功", dealnum, "", "", "", termInfo);
			
			// 记录缴费成功日志
			this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, dealnum, 
					 CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_SUCCESS, "缴费:自助终端跨省缴费成功!");
			
			forward = SUCCESS;
		} 
        catch (ReceptionException e) 
        {
        	logger.error("跨省交费失败：", e);
        	setErrormessage(e.getMessage());
        	
        	//更新交费日志
        	chargeService.updateChargeStatus(logOID, Constants.PAYSUCCESS_CHARGEERROR, "话费跨省缴费失败！", dealnum, "", "", "", termInfo);
			
			// 记录缴费失败日志
			this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, dealnum, 
					CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_FALID, "缴费:自助终端跨省缴费失败!");
		}
        
        logger.debug("ChargeCommit end");
        
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
        logger.debug("goCashError Starting ...");
        
    	//终端机信息
    	TerminalInfoPO termInfo = this.getTerminalInfoPO();
    	
    	//创建业务日志
    	this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", 
    	        Constants.RECSTATUS_FALID, errormessage);
    	
        String status = "";
        
        //交费状态
        if (StringUtils.isBlank(tMoney) || "0".equals(tMoney.trim()))
        {
        	status = Constants.CHARGE_ERROR;
        }
        else
        {
        	status = Constants.PAYSUCCESS_CHARGEERROR;
        }
    	
        //记录交费日志
        chargeService.addCashLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), 
        		cardChargeLogVO.getTerminalSeq(), "", Constants.PROVINCE_REGION_999, status, errormessage, 
        		Constants.BUSITYPE_NONLOCAL_CHARGE, "", cardChargeLogVO.getProvinceCode(), termInfo);
        
        logger.debug("goCashError End");
        
        return "cashErrorPage";
    }
    
    /**
     * 转向银行卡缴费金额选择页面
     * 
     * @return
     */
    public String cardCharge()
    {
        return "selectFee";
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
        logger.debug("addCardPayLog Starting...");
        
        try
        {
        	//终端机信息
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
            //记录交费前日志
            String logOID = "";
            logOID = chargeService.addCardLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), 
	            		EncryptDecryptUtil.encryptAesPwd(cardChargeLogVO.getCardnumber()), 
	            		cardChargeLogVO.getTerminalSeq(), cardChargeLogVO.getStatus(), 
	            		cardChargeLogVO.getDescription(), Constants.PROVINCE_REGION_999, cardChargeLogVO.getPosNum(),
	            		cardChargeLogVO.getBatchNumBeforeKoukuan(), 
	            		cardChargeLogVO.getAcceptType(), Constants.BUSITYPE_NONLOCAL_CHARGE, 
	            		cardChargeLogVO.getProvinceCode(), termInfo);
            
            writeXmlMsg("1~~" + logOID);
        }
        catch (Exception e)
        {
        	logger.error("扣款之前记录日志异常：", e);
            writeXmlMsg("0");
            
        }
        
        logger.debug("addCardPayLog end!");
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
        
        try
        {
        	//终端机信息
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//银联扣款金额 分,去掉左侧多余的0
            String unionpayfee = CommonUtil.formatNumberStr(cardChargeLogVO.getUnionpayfee());
            
            //更新银联扣款日志
            chargeService.updateCardLog(cardChargeLogVO.getChargeLogOID(), cardChargeLogVO.getUnionpayuser(), 
            		cardChargeLogVO.getUnionpaycode(), cardChargeLogVO.getBatchnum(), 
            		cardChargeLogVO.getAuthorizationcode(), cardChargeLogVO.getBusinessreferencenum(), 
            		cardChargeLogVO.getUnionpaytime(), cardChargeLogVO.getVouchernum(), unionpayfee, 
            		cardChargeLogVO.getPosResCode(), "", "", "", 
            		java.net.URLDecoder.decode(cardChargeLogVO.getBusiType(), "UTF-8"), termInfo);
            writeXmlMsg("1");
        }
        catch (Exception e)
        {
        	logger.error("扣款之前记录日志异常：", e);
        	writeXmlMsg("0");
        }
        
        logger.debug("updateCardChargeLog end!");
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
        logger.debug("goCardError Starting ...");
        
        try
        {
        	//终端机信息
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//记录sh_rec_log日志
            this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, 
            		"0", "0", Constants.RECSTATUS_FALID, errormessage);
            
            //是新增日志还是更新已有的日志
            if (StringUtils.isBlank(errorType)|| "add".equalsIgnoreCase(errorType))
            {
            	//新增交费日志
                chargeService.addCardLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), "", "", Constants.CHARGE_ERROR, 
                		errormessage, Constants.PROVINCE_REGION_999, "", "", "", Constants.BUSITYPE_NONLOCAL_CHARGE, 
                		cardChargeLogVO.getProvinceCode(),termInfo);
            }
            
            // 更新日志
            else
            {
            	String status = (StringUtils.isBlank(tMoney) ? Constants.CHARGE_ERROR : Constants.PAYSUCCESS_CHARGEERROR);
                
                //银联返回码
                String errPosResCode = StringUtils.isBlank(cardChargeLogVO.getPosResCode()) ? "" : cardChargeLogVO.getPosResCode();
                
            	//更新银联交费日志
                chargeService.updateChargeStatus(cardChargeLogVO.getChargeLogOID(), status, errormessage, "", 
                		errPosResCode, termInfo.getUnionuserid(), termInfo.getUnionpaycode(), termInfo);
            }
        }
        catch (Exception e)
        {
            // 捕获一切异常,使页面必须走退卡页面
            logger.error("银联卡交费记录日志异常：",e);
            errormessage = errormessage + e.getMessage();
        }
        
        logger.debug("goCardError End");
        
        return "cardErrorPage";
    }
    
    /**
     * <充值交费单笔充值最低金额>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getMinMoney()
    {
    	return CommonUtil.getParamValue(Constants.NONLOCAL_CHARGE_MIN, "10");
    }
    
    /**
     * <充值交费单笔充值最高金额>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getMaxMoney()
    {
    	return CommonUtil.getParamValue(Constants.NONLOCAL_CHARGE_MAX, "2000");
    }
    

    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
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
    
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }
    
    public String getYingjiaoFee()
    {
        return yingjiaoFee;
    }
    
    public void setYingjiaoFee(String yingjiaoFee)
    {
        this.yingjiaoFee = yingjiaoFee;
    }
    
    public String getPrintcontext()
    {
        return printcontext;
    }
    
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }
    
    public void setNonlocalChargeBean(NonlocalChargeBean nonlocalChargeBean)
    {
        this.nonlocalChargeBean = nonlocalChargeBean;
    }
    
    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CardChargeLogVO getCardChargeLogVO()
    {
        return cardChargeLogVO;
    }
    
    public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO)
    {
        this.cardChargeLogVO = cardChargeLogVO;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public void setChargeService(ChargeService chargeService)
    {
        this.chargeService = chargeService;
    }
	
	
}