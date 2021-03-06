/*
 * 文 件 名:  InvoicePrintDaoHubImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: YKF38827
 * 修改时间:  Mar 13, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.invoice.dao;

import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * <发票打印>
 * <功能详细描述>
 * 
 * @author  YKF38827
 * @version  [Mar 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InvoicePrintDaoHubImpl  extends BaseDaoImpl
{
    /**
     * 记录打印发票日志
     * 
     * @param record
     * @see
     */
    public  void insertInvoiceLog(InvoicePrintPO invoicePrintPO)
    {
        sqlMapClientTemplate.insert("invoiceHub.insertInvoiceLog", invoicePrintPO);
    }

}
