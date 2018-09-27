/*
 * 文 件 名:  NonlocalChargeAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <宁夏异地缴费>
 * 修 改 人:  hWX5316476
 * 修改时间:  Mar 20, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.nx.selfsvc.charge.action;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.NonlocalChargeBean;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.ChargeService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * <异地缴费Action>
 * <功能详细描述>
 * 
 * @author  hWX5316476
 * @version  [版本号, Mar 20, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
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
     * 错误信息
     */ 
    private String errormessage;
    
    /**
     * 当前菜单
     */
    private String curMenuId;
    
    /**
     * 可用缴费方式列表
     */
    private List usableTypes;
    
    /**
     *  应交金额
     */
    private String payAmount;
    
    /**
     * 客户姓名
     */
    private String custName;
    
    /**
     * 用户投币面额信息
     */
    private String cashDetail;

    /**
     * 交费金额
     */
    private String tMoney;
    
    /**
     * 话费余额
     */ 
    private String balance;
    
    /**
     * 是否需要退卡1：需要，0：不需要
     */
    private String needReturnCard;
    
    /**
     * 跨区交费的交易流水
     */
    private String dealNum;
    
    /**
     * 交易时间
     */
    private String dealTime;
    
    /**
     * 主动退出标识
     */
    private String quitFlag;
    
    /**
     * 缴费日志信息类
     */
    private transient CardChargeLogVO chargeLogVO;

    /**
     * 异地缴费bean
     */
    private transient NonlocalChargeBean nonlocalChargeBean;
    
    /**
     * 缴费Service
     */
    private transient ChargeService chargeService;
    
    /**
     * 手机号输入页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputNumberPage()
    {
        return SUCCESS;
    }
    
    /**
     * 查询用户应缴费用
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryPayAmount()
    {
        logger.debug("qryPayAmount start");
        
        // 获取终端信息
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        try
        {
            // 充值缴费方式列表
            usableTypes = CommonUtil.qryUsablePayTypes(termInfo.getTermgrpid(), curMenuId);
            
            // findbugs修改 2015-09-02 zKF66389
            //logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
            // findbugs修改 2015-09-02 zKF66389
            
            // findbugs修改 2015-09-02 zKF66389
            //if (null == usableTypes || 0 == usableTypes.size())
            if (0 == usableTypes.size())
            // findbugs修改 2015-09-02 zKF66389
            {
                logger.error("没有可用的充值方式");
                
                // 没有可用的充值方式
                errormessage = "对不起，暂时没有可用的充值方式，请返回执行其他操作。";
                
                // 记录日志
                this.createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", "1", "没有可用的充值方式");
                
                return ERROR;
            } 
          
            // 查询客户应缴费用总额
            Map<String,String> outParmMap = nonlocalChargeBean.qryPayAmount(curMenuId, termInfo, chargeLogVO.getServnumber());
        
            // 客户姓名
            custName = CommonUtil.blurName(outParmMap.get("CustName"));
            
            // 应缴费用总额
            payAmount = CommonUtil.fenToYuan(outParmMap.get("PayAmount"));
            
            // 手机号归属省份编码
            chargeLogVO.setProvinceCode(outParmMap.get("ProvinceCode"));
            
            // 预存总余额
            balance = CommonUtil.fenToYuan(outParmMap.get("Balance"));
            
            // 查询手机号归属地
            String servRegion = chargeService.qryServRegion(chargeLogVO.getServnumber(), chargeLogVO.getProvinceCode());
            
            chargeLogVO.setServRegion(servRegion);
        }
        catch (ReceptionException e)
        {
            errormessage = e.getMessage();
            
            // 记录错误日志
            this.createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, errormessage);
            
            return ERROR;
        }
        
        logger.debug("qryPayAmount end");
        
        return SUCCESS;
    }
    
    /**
     * 转向投币页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashCharge()
    {
        // 投币页面标志，投币页面钱箱打开不跳转到现金稽核
        this.getRequest().setAttribute("isCashCharge", "1");

        return SUCCESS;
    }

    /**
     * 现金提交
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashChargeCommit()
    {
        logger.debug("cashChargeCommit start");
        
        logger.info("用户" + chargeLogVO.getServnumber() + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney + "；流水：" + chargeLogVO.getTerminalSeq());
        
        String forward = ERROR;
        
        // 防止用户不投币，直接从浏览器中模拟交费请求
        String referer = getRequest().getHeader("Referer");
        
        if (null == referer)       
        {
            errormessage = "无效请求";
            
            return forward;
        }
        
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // 校验是否重复交费
        if (!CommonUtil.checkCashFeeNX(termInfo, chargeLogVO.getTerminalSeq(), chargeLogVO.getServnumber(),tMoney))
        {
            return "repeatError";
        }
        
        // 插入现金投币日志
        chargeService.insertCashDetailInfoNX(termInfo.getTermid(), chargeLogVO.getTerminalSeq(), chargeLogVO.getServnumber(), cashDetail);
        
        // 检查投币金额
        if (StringUtils.isBlank(tMoney) || "0".equals(tMoney.trim()))
        {
            tMoney = "0";
            errormessage = "对不起，您的投币金额为0元，无法为您完成缴费。";
            return forward;
        }
        
        // 设置交费状态
        chargeLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
        
        // 添加现金交费日志
        chargeLogVO = chargeService.addChargeLog(chargeLogVO, CommonUtil.yuanToFen(tMoney), Constants.BUSITYPE_NONLOCAL_CHARGE,termInfo);
        
        // 获取规则跨区交费的交易流水号
        dealNum = chargeService.getNonlocalChargeSeq();
        
        try
        {
            // 调用异地缴费接口
            nonlocalChargeBean.nonlocalCharge(curMenuId, termInfo, chargeLogVO.getServnumber(), dealNum,CommonUtil.yuanToFen(tMoney));
            
            // 更新缴费日志
            dealTime = chargeService.updateChargeStatus(chargeLogVO.getChargeLogOID(), Constants.CHARGE_SUCCESS, "自助终端异地缴费成功!",
                    dealNum, "", "", "", termInfo);
            
            // 记录业务日志
            createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_SUCCESS, "自助终端异地缴费成功!");
            
            // 查询最新余额
            qryNewBalance(termInfo);
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            errormessage = e.getMessage();
            
            // 更新缴费日志
            dealTime = chargeService.updateChargeStatus(chargeLogVO.getChargeLogOID(), Constants.PAYSUCCESS_CHARGEERROR, "自助终端异地缴费失败!",
                    dealNum, "", "", "", termInfo);
            
            // 记录错误日志
            createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, errormessage);
        }
        
        logger.debug("cashChargeCommit end");
        
        return forward;
    }
    

    /**
     * 现金交费跳转错误页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goCashError()
    {
        logger.debug("goCashError Starting ...");
        
        logger.info("用户" + chargeLogVO.getServnumber() + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney);
       
        TerminalInfoPO termInfoPO = this.getTerminalInfoPO();
        
        // 添加投币日志
        chargeService.insertCashDetailInfoNX(termInfoPO.getTermid(), chargeLogVO.getTerminalSeq(), chargeLogVO.getServnumber(), cashDetail);
        
        // 添加现金交费日志
        chargeLogVO = chargeService.addChargeLog(chargeLogVO, CommonUtil.yuanToFen(tMoney), Constants.BUSITYPE_NONLOCAL_CHARGE,termInfoPO);
        
        // 记录错误日志
        createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", Constants.RECSTATUS_FALID, errormessage);
        
        logger.debug("goCashError End");
        
        return "cashErrorPage";
    }
    
    /**
     * 转向银行卡缴费金额选择页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardCharge()
    {
        return "otherFee";
    }
    
    /**
     * 选择交费金额
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectOtherFee()
    {
        return SUCCESS;
    }
    
    /**
     * 手工输入缴费金额
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toInputMoney()
    {
        return "toInputMoney";
    }
    
    /**
     * 转向读银行卡读卡页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toReadCard()
    {
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        return "toReadCard";
    }
    
    /**
     * 转向银行卡缴费免责声明页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String dutyInfo()
    {
        return "dutyInfo";
    }
    
    /**
     * 进入银联卡密码输入页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputCardPwd()
    {
        return "inputPwd";
    }
    
    /**
     * 转向确认银行卡缴费金额页面
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toMakeSure()
    {
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        // 是否启动银联密码框 (0：银联密码框   1：华为密码框)
        int pwdBz = CommonUtil.getParamValue("SH_PAY_PWD_BZ") == null ? 0 : Integer.parseInt(CommonUtil.getParamValue("SH_PAY_PWD_BZ"));
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
     * @see [类、类#方法、类#成员]
     */
    public void addCardChargeLog()
    {
        logger.debug("addCardChargeLog Start!");
        
        String xml = "";
        try
        {
            // 支付方式
            chargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
            // 插入银联缴费日志
            chargeLogVO = chargeService.addChargeLog(chargeLogVO, tMoney, Constants.BUSITYPE_NONLOCAL_CHARGE,this.getTerminalInfoPO());
            
            xml = "1~~" + chargeLogVO.getChargeLogOID();
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        this.writeXmlMsg(xml);
        
        logger.debug("addCardChargeLog End!");
    }
    
    /**
     * 扣款成功之后，更新交费日志
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog()
    {
        logger.debug("updateCardChargeLog Start！");
        
        String xml = "";
        try
        {
            chargeLogVO.setRegion(this.getTerminalInfoPO().getRegion());
            
            // 描述
            chargeLogVO.setDescription(java.net.URLDecoder.decode(chargeLogVO.getDescription(), "UTF-8"));
            
            // 业务类型
            chargeLogVO.setBusiType(java.net.URLDecoder.decode(chargeLogVO.getBusiType(), "UTF-8"));
       
            // 更新缴费日志
            chargeService.updateCardLog(chargeLogVO, Constants.CHARGE_SUCCESS, "", this.getTerminalInfoPO());
            
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        
        this.writeXmlMsg(xml);
        
        logger.debug("updateCardChargeLog end!");
        
    }
    
    /**
     * 银行卡缴费完成之后更新日志
     * 获取小票信息
     * @return
     */
    public String cardChargeCommit()
    {
        logger.debug("cardChargeCommit start");
        
        String forward = ERROR;
        
        TerminalInfoPO termInfo = this.getTerminalInfoPO();

        tMoney = CommonUtil.fenToYuan(tMoney);
       
        // 更新缴费日志
        dealTime = chargeService.updateChargeStatus(chargeLogVO.getChargeLogOID(), Constants.CHARGE_SUCCESS, "自助终端异地缴费成功!", 
                dealNum, "", "", "", termInfo);
        
        // 记录错误日志
        this.createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", "0", errormessage);
        
        forward = SUCCESS;
        
        // 查询最新余额
        qryNewBalance(termInfo);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
    }
    
    /**
     * 银联卡交费异常处理
     * @return
     * @see
     */
    public String goCardError()
    {
        logger.debug("goCardError Starting ...");
        
        // 记录错误信息
        this.createRecLog(chargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "0", "0", "1", errormessage);
        
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // 费用
        String fee;
        
        // 状态
        String status;
        
        // 没有扣款记录选择金额
        if (StringUtils.isBlank(tMoney))
        {
            fee = CommonUtil.yuanToFen(payAmount);
            status = Constants.CHARGE_ERROR;
        }
        // 有扣款金额记录实际扣款金额
        else
        {
            fee = CommonUtil.yuanToFen(tMoney);
            status = Constants.PAYSUCCESS_CHARGEERROR;
        }

        // 没有交费日志新增交费日志
        if (StringUtils.isEmpty(chargeLogVO.getChargeLogOID()))
        {
            chargeLogVO.setStatus(Constants.CHARGE_ERROR);
            
            // 添加交费日志
            chargeLogVO = chargeService.addChargeLog(chargeLogVO, fee, Constants.BUSITYPE_NONLOCAL_CHARGE,termInfo);
            
            dealTime = chargeLogVO.getRecdate();
        }
        else
        {
            // 更新日志
            dealTime = chargeService.updateChargeStatus(chargeLogVO.getChargeLogOID(), status, errormessage, "", "", "", "",termInfo);
        }
            
        logger.debug("goCardError End");
        
        return "cardErrorPage";
    }
    
    /**
     * 交费成功后查询最新余额
     * @param termInfo
     * @see [类、类#方法、类#成员]
     */
    private void qryNewBalance( TerminalInfoPO termInfo)
    {
        try
        {
            // 查询客户应缴费用总额
            Map<String,String> outParmMap = nonlocalChargeBean.qryPayAmount(curMenuId, termInfo, chargeLogVO.getServnumber());
              
            // 客户姓名
            custName = outParmMap.get("CustName");
              
            // 预存总余额
            balance = CommonUtil.fenToYuan(outParmMap.get("Balance"));
        }
        // 缴费成功余额查询失败显示成功
        catch (ReceptionException e)
        {
            balance = "";
            custName = "";
        }
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

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPayAmount()
    {
        return payAmount;
    }

    public void setPayAmount(String payAmount)
    {
        this.payAmount = payAmount;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public NonlocalChargeBean getNonlocalChargeBean()
    {
        return nonlocalChargeBean;
    }

    public void setNonlocalChargeBean(NonlocalChargeBean nonlocalChargeBean)
    {
        this.nonlocalChargeBean = nonlocalChargeBean;
    }

    public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getCashDetail()
    {
        return cashDetail;
    }

    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
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

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
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

    public String getQuitFlag()
    {
        return quitFlag;
    }

    public void setQuitFlag(String quitFlag)
    {
        this.quitFlag = quitFlag;
    }

    public ChargeService getChargeService()
    {
        return chargeService;
    }

    public void setChargeService(ChargeService chargeService)
    {
        this.chargeService = chargeService;
    }

    public List getUsableTypes()
    {
        return usableTypes;
    }

    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }

    public CardChargeLogVO getChargeLogVO()
    {
        return chargeLogVO;
    }

    public void setChargeLogVO(CardChargeLogVO chargeLogVO)
    {
        this.chargeLogVO = chargeLogVO;
    }
}
