/*
 * �ļ����� ProdChangeAction.java
 * ������ �ʷ��ײͱ��action��
 * �����ˣ�jWX216858
 */
package com.customize.sd.selfsvc.prodChange.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.ProdChangeBean;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdInfoPO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.customize.sd.selfsvc.prodChange.service.ProdChangeService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.OfferInfoVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


public class ProdChangeSDAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//private static final Logger log = Logger.getLogger(ProdChangeSDAction.class);
	private static Log log = LogFactory.getLog(ProdChangeSDAction.class);
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	
	/**
	 * ����
	 */
	private String region = "";
	
	/**
	 * ��ת�������Ʒ�б�
	 */
	private List<ProdChangePO> prodChangeList = new ArrayList<ProdChangePO>();
	
	/**
	 * ��Ʒģ���б�
	 */
	private List<ProdTemplatePO> prodTemplateList = new ArrayList<ProdTemplatePO>();

	/**
	 * �迪ͨ��ҵ���б�
	 */
	private List<ProdInfoPO> openProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * ��ȡ����ҵ���б�
	 */
	private List<ProdInfoPO> cancelProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * �豣����ҵ���б�
	 */
	private List<ProdInfoPO> reserveProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * ��ǰ�˵�Id
	 */
	private String curMenuId = "";
	
	/**
	 * �µĲ�Ʒ����
	 */
	private String newProdId = "";
	
	/**
	 * �ӿ�bean
	 */
	private ProdChangeBean prodChangeBean;
	
	/**
	 * ���ײ�����
	 */
	private String newProdName = "";
	
	/**
	 * ģ�����
	 */
	private String templateId;
	
	/**
	 * ģ������
	 */
	private String templateName;
	
	/**
	 * �ı������ײ͵ĵ��� 
	 * 1���ǣ��ɹ�ҳ��չʾ���ײ�Ϊ�������ƣ� �������� 
	 */
	private String chgSelfLevel;
	
	/**
	 * �ͻ���Ϣ
	 */
	private NserCustomerSimp customer;
	
	/**
	 * ת���������ƷID
	 */
	private String oldProdId;
	
	/**
	 * �ײ��ʷѱ��service
	 */
	private ProdChangeService prodChangeService;
	
	// add begin jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
	/**
	 * MO�ײ��˶���ʾ
	 */
	private String retMOPrivTips;
	// add end jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
	
	/**
     * ��ѯ��ת���������Ʒ��Ϣ�б�
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_���������ն˲�Ʒ�������Ż�������
     */
	public String qryMainProdChangeInfoList()
	{
		log.debug("qryMainProdChangeInfoList start!");
		
		String forward = "error";
		
		HttpServletRequest request = getRequest();
		
		// ��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��ȡ�ͻ���Ϣ
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// modify begin wWX217192 2014-10-16 OR_SD_201407_1310 ISSS:ISSSƽ̨Ӫ�����Ʒ���ù�����չ
		
		// ����Ӫ��ƽ̨���صĲ�Ʒ��Ϣ
    	OfferInfoVO offerInfo = (OfferInfoVO) request.getSession().getAttribute("ISSS_" + customer.getServNumber() + "_" + curMenuId);
    	
    	// ������Ӫ��ƽ̨�е�session�а�����Ʒ������Ϣ�����û�ֱ�ӽ���ѡ��ģ��ҳ��
		if(null != offerInfo)
		{
			// ���ò�Ʒ����Ĳ���
			newProdId = offerInfo.getOfferCode();
			return "mainProdTemplate";
		}
    	
		// modify end wWX217192 2014-10-16 OR_SD_201407_1310 ISSS:ISSSƽ̨Ӫ�����Ʒ���ù�����չ
		
		try
        {
		    // ���ýӿڲ�ѯ��ת�������Ʒ��Ϣ�б�
	        prodChangeList = prodChangeBean.qryMainProdInfo(termInfoPO, customer, curMenuId);
	        
	        // ��ѯ�û��Ѱ���������Ʒ��Ϣ
            ProdChangePO prodChangeSelfPO = prodChangeBean.qryProdInfoById(termInfoPO, customer, curMenuId);
            
            if(null != prodChangeSelfPO)
            {
                prodChangeList.add(prodChangeSelfPO);
            }
            
            if(prodChangeList.size() == 0)
            {
                // ��¼ʧ����־
                this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "û�п�ת���������Ʒ");
                request.setAttribute("errormessage", "�Բ���û�п�ת���������Ʒ");
            }
            else
            {
                // ��¼�ɹ���־
                this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "��ѯ��ת���������Ʒ��Ϣ�б�ɹ�");
                forward = "qryProdChangeInfoList"; 
            }
        }
        catch (Exception e)
        {
            // ��¼ʧ����־
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
        }
		
		log.debug("qryMainProdChangeInfoList End!");
		
		return forward;
	}
	
	/**
	 * �����Ʒģ����Ϣ�б�
	 * @return
	 * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_���������ն˲�Ʒ�������Ż�������
	 */
	public String mainProdTemplateList()
	{
		log.debug("mainProdTemplateList start!");
		
		String forward = "error";
		
		HttpServletRequest request = getRequest();
		
		// ��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��ȡ�ͻ���Ϣ
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		try
        {
		    oldProdId = customer.getProductID();
		    
		    // ���ѡ��������ƷΪ�Ѱ���������Ʒ����ѯ���ڿ�ת���ĵ����б�
		    if(oldProdId.equals(newProdId))
	        {
	            // ��ѯ���ڿ�ת���ĵ����б�
		        prodTemplateList = prodChangeService.qryLevelByProdId(customer,newProdName);
	        }
	        else
	        {
	            // ���ýӿڲ�ѯ�����Ʒģ����Ϣ�б�
	            prodTemplateList = prodChangeBean.mainProdTemplateInfo(termInfoPO, customer, curMenuId, newProdId);
	        }
		    
		    // ��¼�ɹ���־
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "��ѯ�����Ʒģ����Ϣ�б�ɹ�");
		    
		    if(null == prodTemplateList || 0 == prodTemplateList.size())
		    {
	            request.setAttribute("errormessage", "�Բ���û�ж�Ӧ�������Ʒģ����Ϣ"); 
		    }
		    else
		    {
		        forward = "mainProdTemplate";
		    }
        }
        catch (Exception e)
        {
            // ��¼ʧ����־
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            forward = ERROR;
        }
		
		log.debug("mainProdTemplateList end!");
		
		return forward;
	}
	
	/**
	 * ��ѯ��Ʒ���ȷ����Ϣ���г���ͨ��ҵ��ȡ����ҵ�񡢱�����ҵ��
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String prodChangeValidateInfo()
	{
		log.debug("prodChangeValidateInfo start!");
		
		HttpServletRequest request = getRequest();
		
		// ��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��ȡ�ͻ���Ϣ
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// �ж��ǹ�Ϊ�����ƷԤ����
		boolean pretreatment = true;
		
		String forward = "error";
		
		// ���ýӿڲ�ѯ��Ʒ���ȷ����Ϣ
		Map<String, Object> mapResult = prodChangeBean.mainProdChangeRec(termInfoPO, customer, curMenuId, pretreatment, templateId, newProdId);
		
		// ȡ�����ýӿڷ��ص���Ϣ
		if (null != mapResult && 1 < mapResult.size())
		{
			Vector<Object> retData = (Vector<Object>) mapResult.get("returnObj"); 
			
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
			CTagSet ctagSet = (CTagSet) retData.get(0);
			
			// ��ȡMO�ײ��˶���ʾ��Ϣ
			retMOPrivTips = ctagSet.GetValue("RetMOPrivTips");
			// add end jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
			
			CRSet crset = (CRSet) retData.get(1);
			
			// ����CRSet
			for (int i = 0; i < crset.GetRowCount(); i++)
			{
				ProdInfoPO prodInfoPO = new ProdInfoPO();
				
				// �ײ�����
				prodInfoPO.setProdname(crset.GetValue(i, 2));
				
				// �Ż�����
				prodInfoPO.setPrivname(crset.GetValue(i, 7));
				
				// �༭״̬  A ������ O ������ D ɾ����
				String status = crset.GetValue(i, 0);
				
				// ������ҵ��
				if ("A".equals(status))
				{
					openProdList.add(prodInfoPO);
				}
				else if ("O".equals(status))// ������ҵ��
				{
					reserveProdList.add(prodInfoPO);
				}
				else if ("D".equals(status))// ȡ����ҵ��
				{
					cancelProdList.add(prodInfoPO);
				}
			}
			// ��¼�ɹ���־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "��ѯ��Ʒ���ȷ����Ϣ�ɹ�");
			forward = "prodChangeValidateInfo";
		}
		else if (null != mapResult)
		{
			// ��¼ʧ����־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", (String)mapResult.get("returnMsg"));
			request.setAttribute("errormessage", (String)mapResult.get("returnMsg"));
		}
		else
		{
			// ��¼ʧ����־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", "��ѯ��Ʒ���ȷ����Ϣʧ��");
			request.setAttribute("errormessage", "��ѯ��Ʒ���ȷ����Ϣʧ��");
		}
		
		log.debug("prodChangeValidateInfo end!");
		
		return forward;
	}
	
	 /**
     * ���ýӿ�ִ�������Ʒת��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String mainProdChangeSubmit()
    {
		log.debug("mainProdChangeSubmit start!");
		
    	HttpServletRequest request = getRequest();
		
		// ��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��ȡ�ͻ���Ϣ
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// ��ʾִ�������Ʒ���
		boolean pretreatment = false;
		
		String forward = "error";
		
		// ���ýӿ�ִ�������Ʒת��
		Map<String, Object> mapResult = prodChangeBean.mainProdChangeRec(termInfoPO, customer, curMenuId, pretreatment, templateId, newProdId);
		
		// ȡ�����ýӿڷ��ص���Ϣ
		if (null != mapResult &&  mapResult.size() > 1)
		{
			// ��¼�ɹ���־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "�����Ʒת���ɹ�");
			forward = "mainProdChangeSubmit";
		}
		else if (null != mapResult)
		{
			// ��¼ʧ����־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", (String)mapResult.get("returnMsg"));
			request.setAttribute("errormessage", (String)mapResult.get("returnMsg"));
		}
		else
		{
			// ��¼ʧ����־
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", "�����Ʒת��ʧ��");
			request.setAttribute("errormessage", "ִ�������Ʒת��ʧ��");
		}
		
		log.debug("mainProdChangeSubmit end!");
		return forward;
    }

    /**
     * ���ڵ���ת��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ�������Ż�������
     */
    public String chgLevelInGroup()
    {
        try
        {
            customer = this.getCustomerSimp();
            
            // ���ýӿڽ������ڵ���ת��
            prodChangeBean.chgLevelInGroup(this.getTerminalInfoPO(),  customer, curMenuId, templateId);
            
            // ���óɹ�ҳ��չʾ��������
            chgSelfLevel = "1";
        }
        catch (Exception e)
        {
            // ��¼ʧ����־
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
            return ERROR;
        }
        
        return "chgLevelInGroup";
    }

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public List<ProdChangePO> getProdChangeList()
	{
		return prodChangeList;
	}

	public void setProdChangeList(List<ProdChangePO> prodChangeList)
	{
		this.prodChangeList = prodChangeList;
	}

	public List<ProdTemplatePO> getProdTemplateList()
	{
		return prodTemplateList;
	}

	public void setProdTemplateList(List<ProdTemplatePO> prodTemplateList) 
	{
		this.prodTemplateList = prodTemplateList;
	}

	public List<ProdInfoPO> getOpenProdList() 
	{
		return openProdList;
	}

	public void setOpenProdList(List<ProdInfoPO> openProdList) 
	{
		this.openProdList = openProdList;
	}

	public List<ProdInfoPO> getCancelProdList()
	{
		return cancelProdList;
	}

	public void setCancelProdList(List<ProdInfoPO> cancelProdList)
	{
		this.cancelProdList = cancelProdList;
	}

	public List<ProdInfoPO> getReserveProdList() 
	{
		return reserveProdList;
	}

	public void setReserveProdList(List<ProdInfoPO> reserveProdList)
	{
		this.reserveProdList = reserveProdList;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getNewProdId()
	{
		return newProdId;
	}

	public void setNewProdId(String newProdId)
	{
		this.newProdId = newProdId;
	}

	public ProdChangeBean getProdChangeBean()
	{
		return prodChangeBean;
	}

	public void setProdChangeBean(ProdChangeBean prodChangeBean) 
	{
		this.prodChangeBean = prodChangeBean;
	}

	public String getNewProdName() 
	{
		return newProdName;
	}

	public void setNewProdName(String newProdName)
	{
		this.newProdName = newProdName;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public NserCustomerSimp getCustomer()
	{
		return customer;
	}

	public void setCustomer(NserCustomerSimp customer)
	{
		this.customer = customer;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

    public ProdChangeService getProdChangeService()
    {
        return prodChangeService;
    }

    public void setProdChangeService(ProdChangeService prodChangeService)
    {
        this.prodChangeService = prodChangeService;
    }

    public String getOldProdId()
    {
        return oldProdId;
    }

    public void setOldProdId(String oldProdId)
    {
        this.oldProdId = oldProdId;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getChgSelfLevel()
    {
        return chgSelfLevel;
    }

    public void setChgSelfLevel(String chgSelfLevel)
    {
        this.chgSelfLevel = chgSelfLevel;
    }

	public String getRetMOPrivTips() {
		return retMOPrivTips;
	}

	public void setRetMOPrivTips(String retMOPrivTips) {
		this.retMOPrivTips = retMOPrivTips;
	}

}
