/*
 * �ļ�����ChargeHistoryBean.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �������ɷ���ʷ��¼��ѯBean
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-17
 * �޸����ݣ�����
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * �������ڵĽɷ���ʷ��¼��ѯ
 * 
 * @author g00140516
 * @version 1.0��2010-12-17
 * @see
 * @since
 */
public class ChargeHistoryBean extends BaseBeanImpl
{
    /**
     * �������ڵĽɷѼ�¼��ѯ
     * 
     * @param customerSimp���û���Ϣ
     * @param termInfo���ն���Ϣ
     * @param startDate����ʼ����
     * @param endDate����������
     * @param menuID���˵�
     * @return
     * @see
     */
    public Vector qryChargeHistory(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String startDate,
            String endDate, String menuID)
    {
        Map paramMap = new HashMap();
        paramMap.put("servNumber", customerSimp.getServNumber());
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("menuID", menuID);
        paramMap.put("contactID", customerSimp.getContactId());
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.qryChargeHistory(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet results = (CRSet)rw.getReturnObject();
            
            return formatQueryDataList(results);
        }
        
        return null;
    }
    
    /**
     * �����ɷ���ʷ����ֵ
     * 
     * @param crSet
     * @return
     */
    public Vector formatQueryDataList(CRSet crSet)
    {
        Vector resvec = new Vector();
        
        if (crSet != null && crSet.GetRowCount() > 0)
        {
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
            {
                String titleconst = "�ɷ�����,�ɷѽ��(Ԫ),�ɷ�����,�Ƿ����";
                resvec.add(titleconst);
                
                int row = crSet.GetRowCount();
                String[][] formatDataList = new String[row][4];
                List enlist = new ArrayList();
                
                for (int i = 0; i < row; i++)
                {
                    formatDataList[i][0] = crSet.GetValue(i, 2);
                    formatDataList[i][1] = crSet.GetValue(i, 4);
                    formatDataList[i][2] = crSet.GetValue(i, 7);
                    formatDataList[i][3] = crSet.GetValue(i, 5);
                    
                    enlist.add(formatDataList[i][0]
                            + Constants.STR_SPLIT_TRANS + CommonUtil.fenToYuan(formatDataList[i][1])
                            + Constants.STR_SPLIT_TRANS + formatDataList[i][2] + Constants.STR_SPLIT_TRANS
                            + formatDataList[i][3]);
                }
                
                resvec.add(enlist);
            }
            else
            {
            	// modify begin l00190940 2011-11-22 ɽ���ɷ���ʷ��¼��ѯ��ȥ��״̬��
                String titleconst = "�ɷ�����,�ɷѽ��(Ԫ),�ɷѷ�ʽ,״̬";
                
                int row = crSet.GetRowCount();
                String[][] formatDataList = new String[row][5];
                List enlist = new ArrayList();
                
                for (int i = 0; i < row; i++)
                {
                	//modify begin fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�
                	//���ܿ�  ��ȡ����˳��
                	if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHARGEHISTORY))
                	{
                		formatDataList[i][0] = crSet.GetValue(i, 3);
                		formatDataList[i][1] = crSet.GetValue(i, 0);
                		formatDataList[i][2] = crSet.GetValue(i, 2);
                		formatDataList[i][3] = crSet.GetValue(i, 1);
                	}
                	else
                	{
                		formatDataList[i][0] = crSet.GetValue(i, 1);
                		formatDataList[i][1] = crSet.GetValue(i, 2);
                		formatDataList[i][2] = crSet.GetValue(i, 4);
                		formatDataList[i][3] = crSet.GetValue(i, 6);
                	}
                	//modify end fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�
                	
                    if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
                    {
                    //formatDataList[i][4] = crSet.GetValue(i, 5);
                    titleconst = "��������,���ѽ��(Ԫ),���ѷ�ʽ";
                    
                    String date = formatDataList[i][1];
                    if(!CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHARGEHISTORY))
                    {
                        date = CommonUtil.formatDate(formatDataList[i][1], "yyyyMMddhhmmss", "yyyy-MM-dd hh:mm:ss");
                    }
                    	
                    enlist.add(date
                            + Constants.STR_SPLIT_TRANS + CommonUtil.fenToYuan(formatDataList[i][2])
                            + Constants.STR_SPLIT_TRANS + formatDataList[i][3]);// + Constants.STR_SPLIT_TRANS
                           // + formatDataList[i][4]);
                    }
                    else
                    {
                    	formatDataList[i][4] = crSet.GetValue(i, 5);
                        
                        enlist.add(formatDataList[i][1]+ Constants.STR_SPLIT_TRANS + CommonUtil.fenToYuan(formatDataList[i][2])
                                + Constants.STR_SPLIT_TRANS + formatDataList[i][3] + Constants.STR_SPLIT_TRANS
                                + formatDataList[i][4]);
                    }
                }
                resvec.add(titleconst);                
                resvec.add(enlist);
                // modify end l00190940 2011-11-22 ɽ���ɷ���ʷ��¼��ѯ��ȥ��״̬��
            }           
        }
        
        return resvec;
    }
}
