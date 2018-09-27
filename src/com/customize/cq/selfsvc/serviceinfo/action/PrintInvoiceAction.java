package com.customize.cq.selfsvc.serviceinfo.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.FeeChargeBean;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ��Ʊ�ش�
 * @author z90080209
 * @date   Nov 16, 2011
 */
public class PrintInvoiceAction extends BaseAction
{
    
//    private static Log logger = LogFactory.getLog(PrintInvoiceAction.class);
//    
//    // ���л�
//    private static final long serialVersionUID = 1L;
//    
//    // ��ǰ�˵�
//    private String curMenuId;
//    
//    // ҳ��
//    private String pagecount;
//    
//    // ������Ϣ
//    private String errorMessage;
//    
//    //�ɹ���Ϣ
//    private String successMessage;
//    
//    // �ɴ�ӡ��Ʊ�����
//    private Vector invoiceList;
//    
//    // ���ýӿ�Bean
//    private FeeChargeBean feeChargeBean;
//    
//    /**
//     * ��ѯ�ɴ�ӡ��Ʊ
//     * @return
//     */
//    public String queryInvoice()
//    {
//        logger.debug("queryInvoice start!");
//        
//        String forward = null;
//        
//        // ��ȡsession
//        HttpSession session = getRequest().getSession();
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        if(pagecount != null)
//        {
//            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
//        }
//        
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        // �ṩ��Ʊ��ӡ����
//        String canPrintInvoice = terminalInfoPO.getTermspecial().substring(1, 2);
//        if (!"1".equals(canPrintInvoice))
//        {
//        	// ���ô�����Ϣ
//        	setErrorMessage("���ն˲��ṩ��Ʊ��ӡ���ܣ�");
//            return "error";
//        }
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        Map resultMap = feeChargeBean.chargeCommit(terminalInfoPO,
//        		curMenuId,
//                customer.getServNumber(),
//                "20",
//                payDate,
//                "",
//                "",
//                "",
//                "1",
//                "",
//                "");
//        if (resultMap != null && resultMap.size() > 1)
//        {
//        	Object obj = resultMap.get("returnObj");
//        	if (obj instanceof Vector)
//            {
//        		// �Է�Ʊ��ѯ�ӿڵķ���ֵ���н���
//                Vector chargeInfo = (Vector)obj;
//                CRSet crset = (CRSet)chargeInfo.get(1);
//                if (crset != null && crset.GetRowCount() > 0)
//                {
//                    forward = "invoiceList";
//                    // ���õ��Ľ�����ݷŵ�ҳ����ʾ��ǰ��
//                    Vector v = new Vector();
//                    v.add(new CEntityString("ҵ������,��Ʊ���,ҵ��ʱ��,����"));
//                    v.add(crset);
//                    
//                    // ���ý����
//                    setInvoiceList(v);
//                }
//                else
//                {
//                    // ���ô�����Ϣ
//                	setErrorMessage("��û�пɴ�ӡ�ķ�Ʊ��");
//                    forward = "error";
//                    
//                    // ����������־
//                    this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "��ѯ�ɹ��������û�û�пɴ�ӡ�ķ�Ʊ��");
//                }
//            }
//        }
//        else
//        {
//        	// ���ô�����Ϣ
//        	setErrorMessage("��ѯ�ɴ�ӡ��Ʊʧ�ܣ�");
//            forward = "error";
//            
//            // ����������־
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "��ѯ�ɴ�ӡ��Ʊʧ�ܣ����Ժ����ԡ��������㣬����ԭ�¡�");
//        }
//        
//        logger.debug("queryInvoice end!");
//        return forward;
//    }
//    
//    private String spName;
//    
//    private String spBizName;
//    
//    private String price;
//    
//    private String billFlagName;
//    
//    private String status;
//    
//    private String spId;
//    
//    private String spBizCode;
//    
//    private String bizType;
//    
//    private String domain;
//    
//    private String dealType;
//    
//    private String startTime;
//
//    /**
//     * ��Ʊ��ӡ�ύ
//     * @return
//     */
//    public String invoicePrint()
//    {
//        logger.debug("cancelSP start"); 
//       
//        String forward = "error";
//        
//        // ��ȡsession
//        HttpSession session = getRequest().getSession();
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        //�жϵ�ǰ�˵��Ƿ�Ϊ��
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        //�����������
//        String operType = "Cancel";
//        
//        /*
//         * 1-����SPID�����˶�;
//         * 2-�˶��û�����������SPҵ��; 
//         * 3-����SPID��SPBIZID�˶�; 
//         * 4-����spbiztype�����˶�; 
//         * 5-����DOMAIN�����˶�
//         */
//        String cancelType = "3";
//        
//        String effectType = "";
//        
//        Map result = new HashMap();//spBean.cancelService(terminalInfoPO, customer, CurMenuid,operType, cancelType, dealType, domain, spId, spBizCode, bizType, effectType);
//        result.put("1111", "2222");
//        if (result != null && result.size() > 1)
//        {
//            forward = "success";
//            
//            //���óɹ���Ϣ
//            setSuccessMessage("�ɹ��˶�" + spBizName + "ҵ��");
//            
//            //��¼�ɹ���Ϣ
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0","ҵ������ɹ�!");
//        }
////        else if(result != null)
////        {
////            //����ʧ����Ϣ
////            setErrormessage((String)result.get("returnMsg"));
////            
////            //��¼ʧ����Ϣ
////            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1",(String)result.get("returnMsg"));
////        }
//        else
//        {
//        	//����ʧ����Ϣ
//            setErrorMessage("�˶�" + spBizName + "ҵ��ʧ��");           
//            
//            //��¼ʧ����Ϣ
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", "�˶�" + spBizName + "ҵ��ʧ��");
//        }
//        
//        logger.debug("cancelSP end!");
//        return forward;
//        
//    }
//    
//
//    
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//
//
//	public String getPagecount()
//    {
//        return pagecount;
//    }
//    
//    public void setPagecount(String pagecount)
//    {
//        this.pagecount = pagecount;
//    }
//    
//    public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//	public Vector getInvoiceList()
//    {
//        return invoiceList;
//    }
//    
//    public void setInvoiceList(Vector invoiceList)
//    {
//        this.invoiceList = invoiceList;
//    }
//    
//    public FeeChargeBean getFeeChargeBean()
//    {
//        return feeChargeBean;
//    }
//    
//    public void setFeeChargeBean(FeeChargeBean feeChargeBean)
//    {
//        this.feeChargeBean = feeChargeBean;
//    }
//
//    public String getSpName()
//    {
//        return spName;
//    }
//
//    public void setSpName(String spName)
//    {
//        this.spName = spName;
//    }
//
//    public String getSpBizName()
//    {
//        return spBizName;
//    }
//
//    public void setSpBizName(String spBizName)
//    {
//        this.spBizName = spBizName;
//    }
//
//    public String getPrice()
//    {
//        return price;
//    }
//
//    public void setPrice(String price)
//    {
//        this.price = price;
//    }
//
//    public String getBillFlagName()
//    {
//        return billFlagName;
//    }
//
//    public void setBillFlagName(String billFlagName)
//    {
//        this.billFlagName = billFlagName;
//    }
//
//    public String getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
//
//    public String getSpId()
//    {
//        return spId;
//    }
//
//    public void setSpId(String spId)
//    {
//        this.spId = spId;
//    }
//
//    public String getSpBizCode()
//    {
//        return spBizCode;
//    }
//
//    public void setSpBizCode(String spBizCode)
//    {
//        this.spBizCode = spBizCode;
//    }
//
//    public String getBizType()
//    {
//        return bizType;
//    }
//
//    public void setBizType(String bizType)
//    {
//        this.bizType = bizType;
//    }
//
//    public String getDomain()
//    {
//        return domain;
//    }
//
//    public void setDomain(String domain)
//    {
//        this.domain = domain;
//    }
//
//    public String getDealType()
//    {
//        return dealType;
//    }
//
//    public void setDealType(String dealType)
//    {
//        this.dealType = dealType;
//    }
//
//    public String getStartTime()
//    {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime)
//    {
//        this.startTime = startTime;
//    }
//
//    public String getSuccessMessage()
//    {
//        return successMessage;
//    }
//
//    public void setSuccessMessage(String successMessage)
//    {
//        this.successMessage = successMessage;
//    }
}
