/*
 * �� �� ��:  InvoicePrintHubService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: YKF38827
 * �޸�ʱ��:  Mar 13, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.invoice.service;

import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;

/**
 * <��Ʊ��ӡ> <������ϸ����>
 * 
 * @author YKF38827
 * @version [Mar 13, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public interface InvoicePrintHubService
{
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintPO record);
}
