package com.customize.cq.selfsvc.call;

import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;

public interface SelfSvcCallCQ
{    
    /**
     * 山东套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map);
    
    /**
     * 山东月账单查询
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * 山东余额查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * 话费充值账户信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * 话费充值
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map);
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap sendSMS(Map map);
    
    /**
     * 不校验密码，直接获取用户信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getUserStatus(Map map);
    
    
    /**
     * 获取账单寄送查询的返回信息
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getMailBillSendInfo(Map map);	
    
    /**
     * 获取账单寄送查询的返回信息
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getBillSendInfo(Map map);
	
    /**
     * 撤销原邮箱寄送地址
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getCancelCaseInfo(Map map);
    
    /**
     * 开通邮箱寄送
     * 
     * @param map
     * @return 
     * @see
     */
	public ReturnWrap getOpenBillMailInfo(Map map);
	
	/**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map);
    
    /**
     * 开通139免费邮箱
     */
    public ReturnWrap add139Mail(Map map);
    
    /**
     * 业务统一查询接口
     * 
     * @param map
     * @return
     */
    
    public ReturnWrap queryService(Map map);
    
    /**
     * 账单寄送方式提交
     * 
     * @param map
     * @return
     */
    public ReturnWrap billSendCommit(Map map);
    
    /**
     * 本机品牌资费及已开通功能（产品展示）
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryFavourable(Map map);
    
    /**
     * 重庆优惠业务查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map);
    
    /**
     * 重庆优惠业务受理
     */
    public ReturnWrap privAcceptCommit(Map map);
    
    /**
     * 重庆产品变更，查询用户可转换套餐清单
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryChangeList(Map map);
    
    /**
     * 验用户选择的新主体产品是否具备转换条件
     * 
     * @param inMap
     * @return
     */
    public ReturnWrap prodChgCheck(Map inMap);
    
    /**
     * 根据用户选择的新产品，查询出该产品的模板列表
     * 
     * @param inMap
     * @return
     */
    public ReturnWrap getProdTmpList(Map inMap);
    
    /**
     * 获取优惠/服务/产品变更清单
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryChgContent(Map map);
    
    /**
     * 套餐转换的受理
     */
    public ReturnWrap prodChgCommit(Map map);
    
  //add start l00190940 2011-11-03 积分兑换中的积分查询
    /**
     * 积分查询
     * @param paramMap
     * @return
     */
	public ReturnWrap queryScoreValue(Map paramMap);
	//add end l00190940 2011-11-03 积分兑换中的积分查询
    
	//add start l00190940 2011-11-03 积分兑换中的积分兑换信息查询
	/**
     * 积分兑换信息查询
     * @param paramMap
     * @return
     */
	public ReturnWrap queryScoreExchangeInfo(Map paramMap);
	//add end l00190940 2011-11-03 积分兑换中的积分兑换信息查询

	//add start l00190940 2011-11-04 积分兑换中的积分兑换话费
	/**
     * 积分兑换话费
     * @param paramMap
     * @return
     */
	public ReturnWrap exchangeBalance(Map paramMap);
	//add end l00190940 2011-11-04 积分兑换中的积分兑换话费
}
