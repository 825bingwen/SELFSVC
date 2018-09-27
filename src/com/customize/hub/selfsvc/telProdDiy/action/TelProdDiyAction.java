/*
 * �� �� ��:  MainProdChangeHubAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: yKF70747
 * �޸�ʱ��:  Apr 12, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.telProdDiy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.TelProdDiyBean;
import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;
import com.customize.hub.selfsvc.telProdDiy.service.TelProdDiyService;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class TelProdDiyAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    //private static final Logger log = Logger.getLogger(TelProdDiyAction.class);
    private static Log log = LogFactory.getLog(TelProdDiyAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    // ������Ϣ
    private String errormessage = "";
    
    // �ɹ���ʾ��Ϣ
    private String successMessage = "";
    
    // ��ѯservice
    private TelProdDiyService telProdDiyService;
    
    // �ӿ�bean
    private TelProdDiyBean telProdDiyBean;
    
    // ��ѡҵ���б�
    private List<TelProdPO> telProdList1 = new ArrayList<TelProdPO>();
    
    // ��ѡҵ���б�
    private List<TelProdPO> telProdList2 = new ArrayList<TelProdPO>();
    
    // ��ǰ�˵�
    private String curMenuId = "";
    
    // ��ѯ���� MAINPROD, ����ͨ��; GPRSWLAN, ����������SP, ����ҵ��
    private String qryType = "MAINPROD";
    
    // ҳ��
    private int pageCount;
    
    // ÿҳ��ʾ����
    private int pageSize;
    
    // �ڼ�ҳ
    private int page = 0;
    
    // nocode(����ͨ��)
    private String ncode = "";
    
    // ��ͨ��ֵ��Ʒ��(��Ʒ��,��ֵ��Ʒ,�Ż�;��Ʒ��,��ֵ��Ʒ,�Ż�;)
    private String productset = "";
    
    // ����ҵ��spid,spbizid;spid,spbizid;��
    private String spStr = "";
    
    // �Ҳ����div����
    private String rightDivHtml = "";
    
    // �ܷ���
    private String totalHidden = "0.00";
    
    List<TelProdPO> telProdList = new ArrayList<TelProdPO>();

            
    /**
     * �����û��Ĺ���������Ʒ�Ʋ�ѯ����ҵ���б�
     * 
     * @return
     * @see
     */
    public String qryTelProdList()
    {
        String forward = "qryTelProdList";
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���������û��������������ѡ�ײ�
        if (!(customer.getBrandID()).equals("BrandSzx"))
        {
            setErrormessage("�������������û������ܰ�����ѡ�ײ�ҵ��");
            forward = "error";
            
            return forward;
        }
        
        TelProdPO telProdPO = new TelProdPO();
        telProdPO.setBelongBrands(customer.getBrandID());
        //telProdPO.setBelong_region(customer.getRegionID());

        if ("".equals(customer.getSmallregion()) || null == customer.getSmallregion())
        {
            telProdPO.setBelong_region(customer.getRegionID());
        }
        else
        {
            telProdPO.setBelong_region(customer.getSmallregion());
        }
        telProdPO.setQryType(qryType);
        
        telProdList = telProdDiyService.qryTelProdList(telProdPO);
        
//        if (null != telProdList && 0 < telProdList.size())
//        {
//            forward = "qryTelProdList";
//        }
//        else
//        {
//            setErrormessage("��ʱû������ѡ���ײ�");
//            forward = "error";
//        }
       
        this.getRequest().setAttribute("recordCount", telProdList.size());
        
        if("SP".equals(qryType))
        {
            pageSize = 10;
        }else
        {
            pageSize = 7;
        }
        
        telProdList = this.getPageList(telProdList);
        
        for(TelProdPO po:telProdList)
        {
            if(po.getGoods_type().equals("1") || po.getGoods_type().equals("3")  || po.getGoods_type().equals("5"))
            {
                telProdList1.add(po);
            }
            else
            {
                telProdList2.add(po);

            }
        }
        
        this.getRequest().setAttribute("telProdFlag", "1");

        return forward;
    }
    
    /**
     * �����ύ
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String recSubmit()
    {
        String forward = "error";
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��������ͨ������ӿ�
        ReturnWrap result = telProdDiyBean.mainProductChangeSubmit(terminalInfoPO, customer, curMenuId, ncode);
        
        // ����ͨ������ɹ�
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog("SH_mainProductRec", "0", "0", "0", "����ͨ����" + ncode + "����ɹ���");
            
            // ��������������ҵ������ʧ����Ϣ
            String errorMessage = "";
            
            // ѡ������������
            //if (null != productset && !"".equals(productset))
            if (isNotEmpty(productset))
            {
                // ����������������ӿ�
                ReturnWrap wlanGprsResult = telProdDiyBean.gprsWlanRec(terminalInfoPO, customer, curMenuId, productset);
                // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                if (wlanGprsResult.getStatus() == SSReturnCode.SUCCESS)
                {
                    this.createRecLog("SH_wlanGprsRec", "0", "0", "0", "����������" + productset + "����ɹ���");
                }
                else
                {
                    String resultMsg = wlanGprsResult.getReturnMsg();
                    
                    resultMsg = super.getConvertMsg(resultMsg,
                            "zz_04_24_000004",
                            String.valueOf(wlanGprsResult.getErrcode()),
                            null);
                    
                    this.createRecLog("SH_wlanGprsRec", "0", "0", "1", resultMsg);
                    
                    errorMessage = errorMessage + resultMsg;
                }
            }
            
            // ѡ��������ҵ��
            //if (null != spStr && !"".equals(spStr))
            if (isNotEmpty(spStr))
            {
                String[] spArray = spStr.split(";");
                StringBuffer sbuf = new StringBuffer(errorMessage);
                for (int i = 0; i < spArray.length; i++)
                {
                    
                    String spid = spArray[i].split(",")[0];
                    
                    String spbizid = spArray[i].split(",")[1];
                    
                    // ��������ҵ������ӿ�
                    ReturnWrap spResult = telProdDiyBean.spRec(terminalInfoPO, customer, curMenuId, spbizid, spid);
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    if (spResult.getStatus() == SSReturnCode.SUCCESS)
                    {
                        this.createRecLog("SH_spRec", "0", "0", "0", "����ҵ��(sp���룬spҵ�����)��" + spArray[i] + "����ɹ���");
                    }
                    else
                    {
                        String resultMsg = spResult.getReturnMsg();
                        
                        resultMsg = super.getConvertMsg(resultMsg,
                                "zz_04_24_000003",
                                String.valueOf(spResult.getErrcode()),
                                null);
                        
                        this.createRecLog("SH_spRec", "0", "0", "1", resultMsg);
                        
                        sbuf.append(",").append(resultMsg);
                        
                    }
                }
                errorMessage = sbuf.toString();
            }
            
            if ("".equals(errorMessage))
            {
                setSuccessMessage("��ѡ�ײ�����ɹ���");
                
                forward = "success";
            }
            else
            {
                setErrormessage(errorMessage);
                
                forward = "error";
            }
        }
        else
        {
            String resultMsg = result.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_24_000001", String.valueOf(result.getErrcode()), null);
            
            this.createRecLog("SH_mainProductRec", "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
            forward = "error";
        }
        
        return forward;
    }
    
    /**
     * �ж��Ƿ��ÿ�
     * <������ϸ����>
     * @param str
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public boolean isNotEmpty(String str)
    {
        return null != str && !"".equals(str);
    }
    
    /**
     * ��ҳ
     * 
     * @param list
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<TelProdPO> getPageList(List<TelProdPO> list)
    {
        int sum = 0;
        int start = 0;
        int end = 0;
        
        // ��ȡ��ǰҳ
        if (page == 0)
        {
            page = 1;
        }
        
        // ��ȡ��ҳ��
        if (!list.isEmpty())
        {
            sum = list.size();
            pageCount = list.size() / pageSize;
            if (list.size() % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        else
        {
            pageCount = 0;
        }
        
        start = (page - 1) * pageSize;
        if (page >= pageCount)
        {
            end = sum;
        }
        else
        {
            end = page * pageSize;
        }
        
        List<TelProdPO> telProdList = new ArrayList<TelProdPO>();
        
        for (int i = start; i < end; i++)
        {
            telProdList.add(list.get(i));
        }
        return telProdList;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public TelProdDiyService getTelProdDiyService()
    {
        return telProdDiyService;
    }
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public void setTelProdDiyService(TelProdDiyService telProdDiyService)
    {
        this.telProdDiyService = telProdDiyService;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public TelProdDiyBean getTelProdDiyBean()
    {
        return telProdDiyBean;
    }
    
    public void setTelProdDiyBean(TelProdDiyBean telProdDiyBean)
    {
        this.telProdDiyBean = telProdDiyBean;
    }
    
    public String getQryType()
    {
        return qryType;
    }
    
    public void setQryType(String qryType)
    {
        this.qryType = qryType;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public int getPageCount()
    {
        return pageCount;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public String getProductset()
    {
        return productset;
    }
    
    public String getSpStr()
    {
        return spStr;
    }
    
    public void setProductset(String productset)
    {
        this.productset = productset;
    }
    
    public void setSpStr(String spStr)
    {
        this.spStr = spStr;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public List<TelProdPO> getTelProdList1()
    {
        return telProdList1;
    }

    public List<TelProdPO> getTelProdList2()
    {
        return telProdList2;
    }

    public void setTelProdList1(List<TelProdPO> telProdList1)
    {
        this.telProdList1 = telProdList1;
    }

    public void setTelProdList2(List<TelProdPO> telProdList2)
    {
        this.telProdList2 = telProdList2;
    }

    public String getRightDivHtml()
    {
        return rightDivHtml;
    }

    public void setRightDivHtml(String rightDivHtml)
    {
        this.rightDivHtml = rightDivHtml;
    }

    public String getTotalHidden()
    {
        return totalHidden;
    }

    public void setTotalHidden(String totalHidden)
    {
        this.totalHidden = totalHidden;
    }

    public List<TelProdPO> getTelProdList()
    {
        return telProdList;
    }

    public void setTelProdList(List<TelProdPO> telProdList)
    {
        this.telProdList = telProdList;
    }
    
}
