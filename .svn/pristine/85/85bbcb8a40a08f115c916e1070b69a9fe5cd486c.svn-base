/*
 * 文 件 名:  CardBusiService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  办卡业务Service
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.service;

import java.util.List;

import com.customize.sd.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.ServerNumPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 办卡业务Service
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
 */
public interface CardBusiService
{
    /**
     * 查询开户产品模板
     * 
     * @param prodTempletPO
     * @return List<ProdTempletPO>
     * @see [类、类#方法、类#成员]
     */
    public List<ProdTempletPO> qryProdTempletList(ProdTempletPO prodTempletPO);
    
    /** 
     * 查询终端支持空白卡写卡支持的卡类型
     * 
     * @param termId 终端机编号
     * @return DictItemPO
     * @see [类、类#方法、类#成员]
     */
    public DictItemPO qryTermBlankCardType(String termId);
    
    /**
     * 保存写卡记录
     * <功能详细描述>
     * @param CardBusiLogPO
     * @return 失败0；
     * @see [类、类#方法、类#成员]
     */
    public String addLogInstall(CardBusiLogPO cardBusiLogPO);
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId();
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(CardBusiLogPO cardBusiLogPO);
    
    /**
     * 更新缴费日志状态
     * @param CardChargeLogVO
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /** 
     * <校验用户补卡次数>
     * <功能详细描述>
     * @param customerSimp
     * @see [类、类#方法、类#成员]
     */
    public void checkReissueNum(NserCustomerSimp customerSimp);
    
    /**
     * <查询用户已预约的号码>
     * <功能详细描述>
     * @param idCardNo
     * @param curMenuId
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 17:42:51 OR_SD_201505_489_开户中增加预约选号功能
     */
    public List<ServerNumPO> qryBookedTelnum(String idCardNo, String curMenuId);
}
