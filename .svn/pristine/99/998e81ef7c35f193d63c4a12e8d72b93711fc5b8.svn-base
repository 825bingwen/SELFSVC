package com.customize.hub.selfsvc.call;

import java.util.Map;

import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface SelfSvcCallHub
{
    
    /**
     * 湖北欠费历史查询
     * 
     * @param map
     * @return
     */
    ReturnWrap qryArrear(Map map);
    
    /**
     * 湖北账户缴费方式查询（校验是否为托收账户）
     * @param map
     * @return
     * @remark create hWX5316476 2014-05-22 V200R003C10LG0501 OR_huawei_201405_234  自助终端接入EBUS一阶段_缴费
     */
    public ReturnWrap getAccSettleTypeByPayType(Map<String, String> map);
    
    /**
     * 湖北缴费查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * 湖北缴费接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map);
    
    /**
     * 创建支付任务
     * create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap createPayCenterTrans(NserCustomerSimp customerSimp,String curMenuId,TerminalInfoPO termInfo, String isTelNum, String currNumber,Map<String,String> selfPayLog);
    
    /**
     * 更新表 ar_bank_task 的 recoid
     * create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap updateBankRecoid(TerminalInfoPO termInfo,String currNumber,String curMenuId,String taskoid,String recoid,String status);
    
    /**
     * 支付交易查询
     *create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap qryPayChargeInfo(TerminalInfoPO termInfo,String taskoid,String currNumber,String curMenuId);
    
    /**
     * 更新银行支付状态
     * <功能详细描述>
     * @param payCenterParamInfo
     * @param respMsg
     * @param payId
     * @param payTransMsg
     * @param termInfo
     * @param curMenuId
     * @param currNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap updateBankPaymentStatus(String payCenterParamInfo,String respMsg,String payTransMsg,TerminalInfoPO termInfo,String curMenuId,String currNumber);
    
    // add begin hWX5316476 2013-10-11 OR_huawei_201305_1248
    /**
     * 湖北代理商自助终端现金缴费接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommitByAgent(Map map);
    
    // add end hWX5316476 2013-10-11 OR_huawei_201305_1248
    
    /**
     * 湖北取系统参数接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap getParamValue(Map map);
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap sendSMS(Map map);
    
    /**
     * 获取发票信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getInvoiceData(Map map);
    
    /**
     * 月账单查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * 账户余额查询
     * 
     * @param paramMap
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * 湖北套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map);
    
    /**
     * 本机品牌资费及已开通功能（产品展示）
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryFavourable(MsgHeaderPO msgHeaderPO);
    
    /**
     * 查询预约号码_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap phoneNumQuery(Map map);
    
    /**
     * 通过验证码预约号码_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap bespeakPhone(Map map);
    
    /**
     * 查询网号列表_湖北
     */
    public ReturnWrap netNbrQuery(Map map);
    
    /**
     * 查询网段列表_湖北
     */
    public ReturnWrap netValueQuery(Map map);
    
    /**
     * 身份证入网预约_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap idCardBook(Map map);
    
    /**
     * 湖北优惠业务查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map);
    
    /**
     * 湖北优惠业务受理
     */
    public ReturnWrap privAcceptCommit(Map map);
    
    /**
     * 湖北账单分析查询
     */
    public ReturnWrap qryBillAanlysis(Map map);
    
    /**
     * 湖北资费推荐
     */
    public ReturnWrap qryChargeGuide(Map map);
    
    /**
     * 产品变更，查询用户可转换套餐清单
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
    
    /**
     * 根据GROUPID获取字典表数据
     * 
     * @param map
     * @return
     */
    public ReturnWrap getDictItemByGroup(Map map);
    
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 Begin
    /**
     * 新增分月返还到账情况查询功能 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthlyReturnInfo(Map map);
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 End
    
    /**
     * 业务统一查询接口
     * 
     * @param msgHead
     * @return
     * @remark modify by sWX219697 2014-9-11 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public ReturnWrap queryService(MsgHeaderPO msgHead);
    
    /**
     * <账单寄送方式提交>
     * <功能详细描述>
     * @param msgHead 消息头
     * @param billSendType 发送方式
     * @param mailAddr 邮寄地址
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2014-9-9 09:31:28 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public ReturnWrap billSendCommit(MsgHeaderPO msgHead, String billSendType, String mailAddr);

    //add begin l00190940 2011-12-7 OR_HUB_201112_16
    /** 
     * 查询亲情号码
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryFamilyNumber(MsgHeaderPO msgHeader);
    
    /**
     * 设置、修改或取消亲情号码
     * 
     * @param map
     * @return
     */
    public ReturnWrap setFamilyNumber(MsgHeaderPO msgHeader, String sn, String sregion, String style);
    //add end l00190940 2011-12-7 OR_HUB_201112_16
    
    /**
     * 查询用户已存在的档次_湖北促销活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap qrySubsDangcisList(Map map);
    
    /**
     * 查询档次描述_湖北促销活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryDangciDesc(Map map);
    
    /**
     * 查询奖品列表_湖北促销活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRewardList(Map map);
    
    /**
     * 查询营销方案费用和用户预存费用
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivFee(Map map);
    
    /**
     * 促销活动受理_湖北促销活动
     * 
     * @param map
     * @return
     */
    public ReturnWrap recRewardCommit(Map map);
    
    /**
     * 促销活动受理_获取发票信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap invoiceDataByActivity(Map map);
    
    
    //新版账单查询add by xkf57421 begin
    /**
     * 查询账单客户信息
     * @param map
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryBillCustInfo(Map map);
    
    /**
     * 查询账单信息
     * @param map
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryCurBillInfo(Map map);
    
    public String arQryBillCommuHuBExcelEbus(Map map) throws Exception;
    //新版账单查询add by xkf57421 end
    
    // add begin ykf38827 2012/03/20 R003C12L03n01   OR_HUB_201202_800
    /**
     * 查询要打印的发票记录
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap invoiceList(Map map);
    
    /**
     * 获取要打印的发票数据
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap invoiceData(Map map);
    // add end ykf38827 2012/03/20 R003C12L03n01   OR_HUB_201202_800
    
    //add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     *  主体产品变更确认信息查询
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap mainProductRecInfo(MsgHeaderPO msgHeader, String ncode, String inttime);
    
    /**
     *  主体产品变更确认信息查询
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap mainProductChangeSubmit(MsgHeaderPO msgHeader, String ncode);

    //add end yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    
    
    //add by yedengchu begin
    /**
     *  返还信息查询
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap qryAllBackInfo(Map map);
    
    //add by xkf57421 for OR_HUB_201202_1192 begin
    /**
     *  校验开户的卡号
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap validateTelSim(Map<String, String> paramMap);
    
    /**
     * 查询费用明细列表
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryFeeItemList(Map<String, String> paramMap);
    /**
     * 查询销售条件
     * @param paramMap
     * @return
     * author wWX191797
     * time 2014-04-14
     * for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造    
     */
    public ReturnWrap querySaleCond(Map<String, String> paramMap);
    /**
     * 开户缴费
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap commitInstallProd(Map<String, String> paramMap);
    //add by xkf57421 for OR_HUB_201202_1192 end
     /**
        * 证件校验
        * 证件校验
        * @param 证件类型
        * @param startTime 证件号码
        * @param endTime 相关号码
        * @param logLevel 操作员Id
        * @param userName 终端机ID
        * @param bufferNum 当前菜单Id
        * @return tagset 结果集，
        * @since CommonLog1.0
        * @remark create yKF73963 2012-07-12 OR_HUB_201202_1192
       */
      public ReturnWrap certCardInfo(Map paramMap);
      /**
         * 获取SIM卡信息
         * 
         * @param 
         * @param iccid sim卡_iccid
        * @param region 地区
        * @param operid 操作员Id
        * @param atsvNum 终端机ID
        * @param menuid 当前菜单Id
         * @return tagset 结果集，
         * @since CommonLog1.0
         * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
        */
        public ReturnWrap getSimInfo(Map paramMap);
        /**
         * 获取号码
         * 
         * @param
         * @param fitmod 号码符合条件
         * @param hlrid 归属HLR
         * @param groupid HLR分组号
         * @param istoretype 查询库标志
         * @param teltype 产品品牌
         * @param prdtype 号码类型
         * @param maxcount 返回记录的最大数量
         * @param region 地区
         * @param operid 操作员Id
         * @param atsvNum 终端机ID
         * @param menuid 当前菜单Id
         * @return tagset 结果集，
         * @since CommonLog1.0
         * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
         */
        public ReturnWrap getTelnumbs(Map paramMap);
        /**
         * 暂选号码
         * 
         * @param telnum 电话号码
         * @param region 地区
         * @param operid 操作员Id
         * @param atsvNum 终端机ID
         * @param menuid 当前菜单Id
         * @return tagset 结果集，
         * @since CommonLog1.0
         * @remark create yKF73963 2012-07-17 OR_HUB_201202_1192
         */
        public ReturnWrap chooseTheTelnum(Map paramMap);
        
        
        /**
         * 查询用户可推荐的业务列表_湖北营销推荐活动
         * 
         * @param map
         * @return
         */
        public ReturnWrap qryRecommendProductList(MsgHeaderPO msgHeader);
        
        /**
         * 推荐业务受理接口_湖北营销推荐活动
         * 
         * @param map
         * @return
         */
        public ReturnWrap recommendFeedback(MsgHeaderPO msgHeader, String userSeqs, String status, String actIds, String eventTypes);
        
        /**
         * 推荐业务受理_湖北营销推荐活动
         * 
         * @param map
         * @return
         */
        public ReturnWrap recommendProduct(MsgHeaderPO msgHeader, String userSeq, String actId, String recmdType);
        
        
        /**
         * 推荐业务办理成功，更新业务推荐结果_湖北营销推荐活动
         * 
         * @param map
         * @return
         */
        public ReturnWrap setRecommendSuccess(MsgHeaderPO msgHeader, String commendOID);
        
    /** 
     * 查询回馈定义信息列表
     * 
     * @param msgHeader 报文头信息
     * @param actId 业务推荐活动编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap qryFeedBackDefList(MsgHeaderPO msgHeader, String actId);
        
    /** 
     * 回馈定义受理
     * 
     * @param telNum 用户手机号码
     * @param operId 终端机操作员
     * @param termId 终端机编号
     * @param touchOID
     * @param menuId 菜单Id
     * @param actId 业务推荐活动编码
     * @param contents 回复内容
     * @param recmdType 推荐类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap doFeedBackDef(MsgHeaderPO msgHeader, String actId, String contents, String recmdType);
        
    /**
     * 铁通宽带交费
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697  2014-9-22 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
     */
    public ReturnWrap wbankpay(Map<String, String> map, MsgHeaderPO msgHead);
    
        /**
         * 资助终端账单协同查询之139email
         * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
         */
         public ReturnWrap billColQuery139Email(Map map);
         /**
          * 资助终端账单协同查询之短信
          * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
          */
          public ReturnWrap billColQueryMessage(Map map);
          /**
           * 资助终端账单协同查询之彩信
           * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
           */
           public ReturnWrap billColQueryColorMessage(Map map);
           /**
            *免费抽奖
            * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
            */
            public ReturnWrap mianFeiChouJiang(Map map);
            /**
             * 电子券返还信息查询
             * 
             * @param [参数1] [参数1说明]
             * @param [参数2] [参数2说明]
             * @return [返回类型说明]
             * @exception/throws [违例类型] [违例说明]
             * @see [类、类#方法、类#成员]
             * @depreced
             * @remark create yKF73963（2013-03-18） OR_HUB_201301_780  关于BOSS触发手机支付电子券的分月赠送的需求 
             * 
             */
            public ReturnWrap qryEcashReturnInfo(Map map);

    /** 
     * 上网流量受理
     * 
     * @param msgHeader 报文头信息
     * @param productset 开通增值产品串
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
     */
    public ReturnWrap gprsWlanRec(MsgHeaderPO msgHeader, String productset);
    
    /**
     * 数据业务受理
     * 
     * @param msgHeader 报文头信息
     * @param spbizid sp业务编码
     * @param spid 企业编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
     */
    public ReturnWrap spRec(MsgHeaderPO msgHeader, String spbizid, String spid);
    
    /**
     * 向用户发送随机密码短信(湖北)
     * 
     * @param map
     * @return
     * @see
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    public ReturnWrap sendSmsHub(Map<String, String> map);
    
    //add begin jWX216858 2014/6/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
    /**
     * 获取月结发票信息（湖北）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
    public ReturnWrap getMonInvoiceData(MsgHeaderPO msgHeader, CyclePO cycle);
    
    /** 
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param selTelRule 选号规则
     * @param telPrefix 号码前缀
     * @param telSuffix 号码后缀
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String selTelRule,
        String telPrefix, String telSuffix, String mainProdid);
    
    /** 
     * 开户时证件号码校验
     * 
     * @param msgHeader 报文头信息
     * @param certType 证件类型
     * @param certId 证件号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId);
    
    /** 
     * 号码资源暂选接口
     * 
     * @param msgHeader 
     * @param inParamMap 入参
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, Map<String,String> inParamMap);
    
    /** 
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 报文头信息
     * @param inParamMap 入参
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String,String> inParamMap);
    
    /** 
     * 空白卡资源暂选
     * 
     * @param msgHeader 报文头信息
     * @param blankNo 空白卡卡号
     * @param telNum 手机号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum);
    
    /** 
     * 校验空白卡是否是预置空卡
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param telNum 手机号码
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum);
    
    /**
     * 查询账期（湖北）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
    public ReturnWrap qryBillCycle(MsgHeaderPO msgHeader, String billCycle);
    //add end jWX216858 2014/6/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印  
    
    /**
     * <校验用户身份证信息与手机号是否相符>
     * <功能详细描述>
     * @param msgHeader
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-25 11:26:25 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo);
    
    /**
     * <校验用户补卡次数>
     * <功能详细描述>
     * @param msgHeader
     * @param subscriber 用户信息     
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-25 14:05:18 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader, String subscriber);
    
    /**
     * 获取写卡信息加密数据
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-28 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap getEncryptedData(MsgHeaderPO msgHead,Map<String, Object> map);
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-28 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum);

    /** 
     * 查询手机号码是否有备卡
     * 
     * @param msgHeader 
     * @param telnum 手机号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryStoreCard(MsgHeaderPO msgHeader, String subsID);
    
    /**
     * <备卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)
     */
    public ReturnWrap reckonrecfeeByStore(MsgHeaderPO msgHeader, String servnum, String iccid);
    
    /**
     * <备卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)
     */
    public ReturnWrap prepareCashCommit(MsgHeaderPO msgHeader, String servnum, String iccid, String tMoney, String payType);
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param recFee 应缴费用
     * @param payType 支付方式
     * @param simInfo sim卡信息
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-30 13:57:27 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, String blankno, SimInfoPO simInfo);
    
    /**
     * 空白卡开户
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHead,Map<String, String> inParamMap);
    
    /**
     * 写卡失败 作废卡接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHead,Map<String, String> inParamMap);
    
    /**
     * 校验写卡结果接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap checkWriteCardInfo(MsgHeaderPO msgHead,Map<String, String> inParamMap);
    
    /**
     * 计算开户费用
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-31 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap reckonRecFee(MsgHeaderPO msgHead,Map<String, String> inParamMap);
    
    /**
     * <查询用户信息>
     * <功能详细描述>
     * @param msgHeader
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-11-6 18:08:12 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region);
    
    /** 对手机号进行实名制验证
     * <功能详细描述>
     * @return String true:验证证成功 其他：验证失败
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public ReturnWrap isRealName(MsgHeaderPO msgHeader);
    
    /** 宽带预约提交
     * <功能详细描述>
     * @param telNum 预约手机号码
     * @param currArea 预约地点
     * @param currProd 预约产品
     * @param iDcard   身份证号
     * @param installDate 预约安装时间
     * @param band  带宽
     * @return String true:成功, 其他：失败
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     * modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   
     */
    public ReturnWrap broadBandAppoint(String currArea,String installDate, String cardIdNum, String currProd, String band, MsgHeaderPO msgHeader);
    
    /**
     * <查询余额明细接口>
     * <功能详细描述>
     * @param msgHeader
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2016-03-31 OR_HUB_201602_493
     */
    public ReturnWrap showBalanceDetail(MsgHeaderPO msgHeader);
}
