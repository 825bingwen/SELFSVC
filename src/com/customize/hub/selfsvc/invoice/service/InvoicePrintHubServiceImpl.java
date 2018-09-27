/*
 * �� �� ��:  InvoicePrintHubServiceImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: YKF38827
 * �޸�ʱ��:  Mar 13, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.invoice.service;

import com.customize.hub.selfsvc.invoice.dao.InvoicePrintDaoHubImpl;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;

/**
 * <��Ʊ��ӡ> <������ϸ����>
 * 
 * @author YKF38827
 * @version [Mar 13, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class InvoicePrintHubServiceImpl implements InvoicePrintHubService
{
    private InvoicePrintDaoHubImpl invoicePrintDaoImpl;
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintPO record)
    {
        invoicePrintDaoImpl.insertInvoiceLog(record);
    }
    
    public InvoicePrintDaoHubImpl getInvoicePrintDaoImpl()
    {
        return invoicePrintDaoImpl;
    }
    
    public void setInvoicePrintDaoImpl(InvoicePrintDaoHubImpl invoicePrintDaoImpl)
    {
        this.invoicePrintDaoImpl = invoicePrintDaoImpl;
    }
}
