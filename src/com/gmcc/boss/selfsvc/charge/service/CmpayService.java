/*
 * 文件名：CmpayService.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：手机支付主账户充值记录日志
 * 修改人：g00140516
 * 修改时间：2010-12-24
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * 
 * 手机支付主账户充值记录日志
 * 
 * 
 * @author  g00140516
 * @version  1.0，2010-12-24
 * @see  
 * @since  
 */
public interface CmpayService
{
    /**
     * 日志记录
     * 
     * @param cmpayLogPO
     * @see 
     */
    public void addCmpayLog(CardChargeLogVO cmpayLogPO);
    
    /**
     * 更新日志记录
     * 
     * @param cmpayLogPO
     * @see 
     */
    public void updateCmpayLog(CardChargeLogVO cmpayLogPO);
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
}
