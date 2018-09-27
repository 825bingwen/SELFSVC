package com.customize.nx.selfsvc.call;

import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;

public interface SelfSvcCallNX
{
    
    /**
     * 账户余额查询
     * 
     * @param paramMap
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * 月账单查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap sendSMS(Map map);
    
    /**
     * 缴费查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * 缴费接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map);
    
    /**
     * 宁夏套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map);
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getCustinfo(Map map);
    
    /**
     * 查询月账信息_新版
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill_new(Map map);
    
    /**
     * 查询银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap);
    
    /**
     * 取消银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap cancelChargeType(Map<String, String> paramMap);
    
    /**
     * 新增银行划扣支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap addChargeType(Map<String, String> paramMap);
    
    /**
     * 发票查询
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map<String, String> map);
    
    /**
     *  主体产品变更确认信息查询
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map<String,String> map);
    
    /**
     *  主体产品变更受理
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap mainProductChangeSubmit(Map<String,String> map);
    
    /**
     *  查询可变更主体产品列表
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap qryChangeMainProduct(Map<String,String> map);
    
    /**
     * 根据类型查询积分值
     * 
     * @param map
     * @return returnWrap
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap);
    
    /**
     * 开户时证件号码校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkCertSubs(Map map);
    
    /**
     * 查询手机号码列表
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryNumberByProdid(Map map);
    
    /**
     * 号码资源暂选接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(Map map);
    
    /**
     * 校验空白卡资源是否可用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(Map map);
    
    /**
     * 空白卡资源暂选
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(Map map);
    
    /**
     * 号卡校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkTelSimcard(Map map);
    
    /**
     * 计算费用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap reckonRecFee(Map map);
    
    /**
     * 开户
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap terminalInstall(Map map);
    
    //add begin jWX216858 2014/6/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
    /**
     * 获取月结发票信息（宁夏）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
    public ReturnWrap getMonInvoiceData(Map<String, String> paramMap);
    
    /**
     * 查询账期（宁夏）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
    public ReturnWrap qryBillCycle(Map<String, String> paramMap);
    //add end jWX216858 2014/6/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印

    
}
