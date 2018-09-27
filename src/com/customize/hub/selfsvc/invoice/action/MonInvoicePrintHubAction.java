package com.customize.hub.selfsvc.invoice.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.InvoicePrintBean;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �½ᷢƱ
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-6-16]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MonInvoicePrintHubAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//private static Logger logger = Logger.getLogger(MonInvoicePrintHubAction.class);
	private static Log logger = LogFactory.getLog(MonInvoicePrintHubAction.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	
	/**
	 * ���ýӿ�bean
	 */
	private InvoicePrintBean monInvoPrintBean;
	
	/**
	 * �½ᷢƱpo
	 */
	private InvoicePrintPO monInvoicePO;
	
	/**
	 * ����
	 */
	private String cycle;
	
	/**
	 * ��ǰ�˵�
	 */
	private String curMenuId;
	
	/**
	 * ����
	 */
	private CyclePO cyclepo = new CyclePO();
	
	/**
	 * ��������
	 */
	private String regionName;
	
	/**
	 * ����Աid
	 */
	private String operid;
	
	/**
     * ��ѯ���������·�
     * 
     * @return
     * @remark add by jWX216858 on 20140616 for OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    public String qryCurrentMonth()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();

        // �ɲ�ѯ��ǰ�º�ǰ����µ��˵���Ϣ
        String pattern = "yyyyMM";
        
        for(int i = 0; i < 6; i++)
        {
        	request.setAttribute("month" + i, CommonUtil.getLastMonth(pattern, i));
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth End");
        }
        
        return "qryCurrentMonth";
    }

    /**
     * ��ѯ���ڹ���
     * <������ϸ����>
     * @return
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by jWX216858 on 20140616 for OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
	public String qryBillCycle() throws Exception
    {
    	if(logger.isDebugEnabled())
    	{
    		logger.debug("qryBillCycle Starting ...");
    	}
    	
    	HttpServletRequest request = this.getRequest();
    	
    	// ��ȡsession��Ϣ
    	HttpSession session = this.getRequest().getSession();
    	
    	// ��ȡ�ͻ���Ϣ
    	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    	
    	// ��ȡ�ն˻���Ϣ
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	//���ò�ѯ���ڽӿڷ��ص�����
    	ReturnWrap rw = monInvoPrintBean.qryBillCycle(customer.getServNumber(), termInfo, curMenuId, cycle);
    	
    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // ȡ���ض���
            Vector vector = (Vector)(rw.getReturnObject());
            
            // ������Ϣ
            CRSet crset = (CRSet)(vector.get(1));
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                cyclepo.setCycle(crset.GetValue(i, 0));// ����
                cyclepo.setStartdate(crset.GetValue(i, 1));// ��ʼʱ��
                cyclepo.setEnddate(crset.GetValue(i, 2));// ����ʱ��
                cyclepo.setAcctid(crset.GetValue(i, 3));// ���˺�
                cyclepo.setUnionacct(crset.GetValue(i, 4));// �Ƿ�ϻ��û���1���ǣ�0������
            }
            
            // ����
            return "qryBillCycle";
        }
        else
        {
        	request.setAttribute("errormessage", "���ڲ�ѯʧ�ܣ�");
        	
        	request.setAttribute("backStep", "-1");
        	
            // ����
            return "error";
        }
    }

    /**
     * ��ȡ�½ᷢƱ����,��ӡ��Ʊ
     * 
     * @return
     * @remark add by jWX216858 on 20140616 for OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    public String printMonInvoice()
    {
    	if (logger.isDebugEnabled())
    	{
    		logger.info("invoiceList start");
    	}
    	HttpServletRequest request = this.getRequest();
    	
    	// ��ȡsession
    	HttpSession session = this.getRequest().getSession();
    	
    	// ��ȡ�ͻ���Ϣ
    	NserCustomerSimp customer = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
    	
    	// ��ȡ�ն˻���Ϣ
    	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
    	// �����½ᷢƱ���ݲ�ѯ�ӿڣ���ȡ�½ᷢƱ������Ϣ
    	ReturnWrap rw = monInvoPrintBean.invoiceData(termInfo, customer, curMenuId, cyclepo);
    	
    	// ��ȡ����Աid
    	operid = termInfo.getOperid();
    	
    	// �ӿڵ��óɹ����������ݷ�װ
    	if (null != rw && rw.getStatus() == SSReturnCode.SUCCESS)
    	{
    		// ��Ʊ����ϸ��Ϣ
    		CRSet crset = (CRSet)rw.getReturnObject();
    		
    		if(null != crset)
    		{
    			monInvoicePO = getInvoiceInfo(crset);
    		}
    	}	
		else if(rw != null && rw.getStatus() == SSReturnCode.ERROR)
    	{
			request.setAttribute("errormessage", rw.getReturnMsg());
    		
    		// ��¼��ѯ�½ᷢƱ��Ϣʧ����־
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_HUB, "0", "0", "1", rw.getReturnMsg());
            return "error";
    	}
    	else
    	{
    		request.setAttribute("errormessage", "��ȡ��Ʊ��Ϣʧ��!");
    		
    		// ��¼��ѯ�½ᷢƱ��Ϣʧ����־
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_HUB, "0", "0", "1", "��ȡ�½ᷢƱ��Ϣʧ��!");
            return "error";
    	}
    	
    	if (logger.isDebugEnabled())
    	{
    		logger.info("invoiceList End");
    	}
    	return "printMonInvoice";
    }
    
    /**
     * �Կͷ����صĴ�ӡ��Ʊ���ݽ��д���(�ѷ��ص�CRSetת���ɶ���)
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by jWX216858 on 20140616 for OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    public InvoicePrintPO getInvoiceInfo(CRSet crset)
    {
    	// �ӽӿڷ���ֵ�л�ȡ��Ʊ��Ϣ
    	Map<String, String> invoiceMaps = new HashMap<String, String>();
        
        // ����crset������
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	String name = crset.GetValue(i, 0);
        	String value = crset.GetValue(i, 1);
        	
    		invoiceMaps.put(name, value);
        }
        
        InvoicePrintPO monInvo = new InvoicePrintPO();
        
        // �ͻ�ȫ��
        monInvo.setCustomerName(invoiceMaps.get("username"));
        
        // ��������
        monInvo.setFundType(invoiceMaps.get("feetype").substring(0, 8) + "��Ʊ");
        
        // �ֻ�����
        monInvo.setServNumber(invoiceMaps.get("telnumber"));
        
        // ͨ�ŷ����
        monInvo.setCommServFee(invoiceMaps.get("invoicefeehj"));
        
        // �����ۿ�
        monInvo.setSellDiscount(invoiceMaps.get("presentused"));
        
        // ���η�Ʊ���
        monInvo.setInvoiceFee(invoiceMaps.get("thisinvamt"));
        
        // ���
        monInvo.setFee(invoiceMaps.get("invoicefee"));
        
        // ��ͬ��
        monInvo.setContractNum(invoiceMaps.get("paynumno"));
        
        // ��ˮ��
        monInvo.setFormnum(invoiceMaps.get("formnum"));
        
        // ��ӡ���
        monInvo.setPrintyear(invoiceMaps.get("printyear"));
        
        // ��ӡ�·�
        monInvo.setPrintmonth(invoiceMaps.get("printmonth"));
        
        // ��ӡ��
        monInvo.setPrintday(invoiceMaps.get("printday"));
        
        // add begin wWX217192 2016-01-27 OR_HUB_201512_256_����_������BOSSϵͳ�б����è���ѳ�ֵ�ѿ�Ʊ��������
        String thirdPaySwitch = (String) PublicCache.getInstance().getCachedData(Constants.SH_MONTHINVOICE_HUB_THIRDPAY);
        
        if (Constants.SH_MONTHINVOICE_HUB_OPEN.equals(thirdPaySwitch))
        {
        	monInvo.setThirdPay(invoiceMaps.get("thirdpayfee"));
        }
        // add end wWX217192 2016-01-27 OR_HUB_201512_256_����_������BOSSϵͳ�б����è���ѳ�ֵ�ѿ�Ʊ��������
        return monInvo;
    }
    
	public InvoicePrintBean getMonInvoPrintBean() {
		return monInvoPrintBean;
	}

	public void setMonInvoPrintBean(InvoicePrintBean monInvoPrintBean) {
		this.monInvoPrintBean = monInvoPrintBean;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CyclePO getCyclepo() {
		return cyclepo;
	}

	public void setCyclepo(CyclePO cyclepo) {
		this.cyclepo = cyclepo;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public InvoicePrintPO getMonInvoicePO() {
		return monInvoicePO;
	}

	public void setMonInvoicePO(InvoicePrintPO monInvoicePO) {
		this.monInvoicePO = monInvoicePO;
	}

}
