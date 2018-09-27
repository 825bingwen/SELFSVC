package com.gmcc.boss.selfsvc.baseService.spService.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;
import com.gmcc.boss.selfsvc.baseService.spService.service.SpService;
import com.gmcc.boss.selfsvc.bean.SPBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;


/**
 * ȫ������ҵ���ѯ�˶��򶩹�
 * @author xkf29026
 *
 */
public class SPAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(SPAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�
    private String curMenuId;
    
    // ҳ��
    private String pagecount;
    
    // ������Ϣ
    private String errormessage;
    
    //�ɹ���Ϣ
    private String successMessage;
    
    // �û��ѿ�ͨ����ҵ������
    private Vector spservice;
    
    // �û��ɶ�����ȫ������ҵ�񼯺�
    private List<SpAvailPO> availSPService;
    
    // ��������
    private String operType;
    
    // �Ƿ��Ѷ��� 0:δ���� 1:�Ѷ���
    private String dinggouStatus;
    
    // �ӿڵ���
    private SPBean spBean;
    
    // ���ݿ����service
    SpService spService;
    
    /**
     * ����spcode+operatorcode��ѯ����ҵ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String querySpByCode()
    {
        logger.debug("querySpByCode start!");
        
        String forward = null;
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0");        
        
        forward = this.parseResult(result);
        // modify end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        
        // ���Ӻ���ȫ������ҵ�񶩹�����
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if(Constants.PROOPERORGID_HUB.equals(province))
        {
            // �������ն����ݿ��в�ѯ�û��ɶ�����ȫ������ҵ��
            availSPService = spService.qryAvailSP();
            
            List<SpAvailPO> availSPServiceBak = new ArrayList<SpAvailPO>();
            for (SpAvailPO spAvailPO : availSPService)
            {
                if (curMenuId.split("-")[0].equals(spAvailPO.getSpcode()) && curMenuId.split("-")[1].equals(spAvailPO.getOperatorCode()))
                {
                    availSPServiceBak.add(spAvailPO);
                }
            }
            availSPService = availSPServiceBak;
            dinggouStatus = "0";
            
            // ���˵��û��Ѿ�������spҵ��,��������Ϊspid��spbizidͬʱ��ͬ��
            if (spservice != null)
            {
                CRSet spserviceData = (CRSet)spservice.get(1);
                if(availSPService != null && spserviceData != null && availSPService.size() >0 && spserviceData.GetRowCount() >0)
                {
                    for(int i=0;i<spserviceData.GetRowCount();i++)
                    {
                        String spid = spserviceData.GetValue(i, 1);  
                        String spBizid = spserviceData.GetValue(i, 3);
//                        for(int j=0;j<availSPService.size();j++)
//                        {
//                            SpAvailPO spAvailPO = availSPService.get(j);
//                            String spcode = spAvailPO.getSpcode();
//                            String spbizcode = spAvailPO.getOperatorCode();
//                            if(spid.equals(spcode) && spBizid.equals(spbizcode))
//                            {
//                                dinggouStatus = "1";
//                            }
//                        }
                        for(SpAvailPO sp : availSPService)
                        {
                          String spcode = sp.getSpcode();
                          String spbizcode = sp.getOperatorCode();
                          if(spid.equals(spcode) && spBizid.equals(spbizcode))
                          {
                              dinggouStatus = "1";
                              sp.setOfferMan(spserviceData.GetValue(i, 2));// �ṩ��
                              sp.setOperatorName(spserviceData.GetValue(i, 4));// ҵ������
                              sp.setFee(CurrencyUtil.divide(spserviceData.GetValue(i, 7),"1000"));// ����(Ԫ)
                              sp.setValiddate(spserviceData.GetValue(i, 9));// ��ͨ����
                          }
                        }
                    }
                }
            }
            
            // �����Ʒѷ�ʽ�����ú�ʱ�����ʾ��ʽ
            this.setBillFlag();
            return "querySpByCode";
        }
        
        
        logger.debug("querySpByCode end!");
        return forward;
    }

    /**
     * �����Ʒѷ�ʽ�����ú�ʱ�����ʾ��ʽ
     * @param availSPService
     * @remark create by jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	public void setBillFlag()
	{
		if(availSPService != null)
		{
		    for(int k=0;k<availSPService.size();k++)
		    {
		        SpAvailPO spAvailPO = availSPService.get(k);
		        if("0".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("���");
		        }
		        if("1".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("����");
		        }
		        if("2".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("����");
		        }
		        spAvailPO.setFee(CurrencyUtil.divide(spAvailPO.getFee(),"1000"));
		        spAvailPO.setExpireDate(CommonUtil.formatDate(spAvailPO.getExpireDate(),"yyyyMMdd","yyyy-MM-dd"));
		        availSPService.set(k, spAvailPO);
		    }
		}
	}

    /**
     * ����bean���ص�map
     * @param forward
     * @param result
     * @remark create by jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93 Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	public String parseResult(Map result)
	{
		String forward = null;
		if (result.size() > 2)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            
            if (crset != null && crset.GetRowCount() > 0)
            {
                forward = "qrySPinfo";
                // ���õ��Ľ�����ݷŵ�ҳ����ʾ��ǰ��
                Vector v = new Vector();
                v.add(new CEntityString("ҵ������,�ṩ��,�ʷ�,��ͨʱ��"));
                v.add(crset);
                
                // ���ý����
                setSpservice(v);
            }
            else
            {
                //add begin by cwx456134 2017-4-23 DTS2017041809030 ��ʷ����
                forward = "error";
                //add end by cwx456134 2017-4-23 DTS2017041809030 ��ʷ����
                
            	setErrormessage("����δ��ͨ�κ�����ҵ��");
                if ("Order".equals(operType))
                {
                    // ����������־
                    this.createRecLog(curMenuId, "0", "0", "0", "��ѯ�ɹ��������û�û�п�ͨ�κ�����ҵ��");
                }
                else
                {
                    // ����������־
                    this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "��ѯ�ɹ��������û�û�п�ͨ�κ�����ҵ��");
                }
            }
        }
        else
        {
            String resultMsg = getConvertMsg((String) result.get("returnMsg"), "zz_04_20_000001", 
                    String.valueOf(result.get("errcode")), new String[]{"��ѯ", "�ѿ�ͨSPҵ��"});
            
            // ���ô�����Ϣ
            setErrormessage(resultMsg);
            forward = "error";
            
            if ("Order".equals(operType))
            {
                // ����������־
                this.createRecLog(curMenuId, "0", "0", "1", resultMsg);
            }
            else
            {
                // ����������־
                this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", resultMsg);
            }
        }
		return forward;
	}
    
	/**
     * ����ҵ���ѯ
     * 
     * @return
     */
    public String querySP()
    {
        logger.debug("querySP start!");
        
        String forward = "error";
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if(pagecount != null)
        {
            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
        }
        
        // modify begin jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        /*if (CurMenuid == null)
        {
            CurMenuid = "";
        }*/
        curMenuId = curMenuId == null ? "" : curMenuId;
        // modify begin jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0");
        forward = this.parseResult(result);
        // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        
        // ���Ӻ���ȫ������ҵ�񶩹�����
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if(Constants.PROOPERORGID_HUB.equals(province) && "Order".equals(operType))
        {
        	// �������ն����ݿ��в�ѯ�û��ɶ�����ȫ������ҵ��
        	availSPService = spService.qryAvailSP();
        	
        	// ���˵��û��Ѿ�������spҵ��,��������Ϊspid��spbizidͬʱ��ͬ��
        	if (spservice != null)
        	{
            	CRSet spserviceData = (CRSet)spservice.get(1);
            	if(availSPService != null && spserviceData != null && availSPService.size() >0 && spserviceData.GetRowCount() >0)
            	{
            		for(int i=0;i<spserviceData.GetRowCount();i++)
            		{
            			String spid = spserviceData.GetValue(i, 1);  
            			String spBizid = spserviceData.GetValue(i, 3);
            			for(int j=0;j<availSPService.size();j++)
            			{
            				SpAvailPO spAvailPO = availSPService.get(j);
            				String spcode = spAvailPO.getSpcode();
            				String spbizcode = spAvailPO.getOperatorCode();
            				if(spid.equals(spcode) && spBizid.equals(spbizcode))
            				{
            					availSPService.remove(j);
            				}
            			}
            		}
            	}
        	}
        	
            // �����Ʒѷ�ʽ�����ú�ʱ�����ʾ��ʽ
        	this.setBillFlag();
        	return "spAvailList";
        }

        logger.debug("querySP end!");
        return forward;
        
    }
    
    private String spName;
    
    private String spBizName;
    
    private String price;
    
    private String billFlagName;
    
    private String status;
    
    private String spId;
    
    private String spBizCode;
    
    private String bizType;
    
    private String domain;
    
    private String dealType;
    
    private String startTime;

    /**
     * �˶�����ҵ��
     * 
     * @return
     */
    public String cancelSP()
    {
        logger.debug("cancelSP start"); 
       
        String forward = "error";
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //�жϵ�ǰ�˵��Ƿ�Ϊ��
        curMenuId = (curMenuId == null) ? "" : curMenuId;
        
        //modify begin g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
        /**
         * ҵ�����͸���operTypeֵ���жϣ����ֵΪOrder��Ϊ���������ֵΪCancel��Ϊ�˶���
         */
        Map result = null;
        String cancelType = "";
        String effectType = "";
        String operInfo = "";
        if("Cancel".equals(operType))
        {
        	cancelType = "3";
        	operInfo = "�˶�";
        	
        	result = spBean.cancelService(terminalInfoPO, customer, 
        			curMenuId,operType, cancelType, dealType, domain, spId, spBizCode, bizType, effectType);
        }
        else if("Order".equals(operType))
        {
        	// modify begin sWX219697 2014-6-30 16:36:11 OR_HUB_201406_1115_��������Ӫ��Я��ת��
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

        	//������ΪЯ��ת��Я���û������ж�Ҫ������ҵ���Ƿ��ڰ�������
        	if(Constants.PROOPERORGID_HUB.equals(province) 
        			&& "sbsnTransTelIn".equalsIgnoreCase(customer.getSignType()))
        	{
        		//��ѯspҵ�������
        		SpAvailPO spPO = new SpAvailPO();
        		
        		//sp��ҵ����
        		spPO.setSpcode(spId);
        		
        		//spҵ�����
        		spPO.setOperatorCode(spBizCode);
        		
        		//spҵ������
        		spPO.setServType(bizType);
        		
        		
        		//��ҵ���ڰ���������ʱ�޷�����
        		if (spService.authWhiteList(spPO) <= 0)
        		{
        			//��ȡ��ʾ��Ϣ����������û�У������Ĭ��
        			String msg = (String) PublicCache.getInstance().getCachedData(Constants.TRANSIN_MSG);
        			String showMsg = (null == msg || "".equals(msg)) ? 
        					"�ǳ���Ǹ����Ŀǰ���ܰ����ҵ��������ѯ10086��" : msg;
        			setSuccessMessage(showMsg);
        			this.createRecLog(curMenuId, "0", "0", "1", "Я���û������ĸ�ҵ���ڰ�������");
        			return "success";
        		}
        		
        		//Ҫ������ҵ���ڰ������У����Զ���
        		cancelType = "3";
                effectType = "0";
                operInfo = "����";
                
                result = spBean.orderSPService(terminalInfoPO, customer, 
                		curMenuId, operType, cancelType, domain, spId, spBizCode, bizType, effectType);
        		
        	}
        	
        	//�Ǻ����û����Я��ת���û������ֱ�Ӷ���
        	else
        	{
                cancelType = "3";
                effectType = "0";
                operInfo = "����";
                
                result = spBean.orderSPService(terminalInfoPO, customer, 
                		curMenuId, operType, cancelType, domain, spId, spBizCode, bizType, effectType);
            
        	}
        	// modify end sWX219697 2014-6-30 16:36:11 OR_HUB_201406_1115_��������Ӫ��Я��ת��
            
        }
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        if (result != null && result.size() > 2)
        {
            forward = "success";
            
            //���óɹ���Ϣ
            setSuccessMessage("�ɹ�"+ operInfo + spBizName + "ҵ��");
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.get("returnObj");
            
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_��������Ӫ��Я��ת��(����Ȧ���Ӷ�)
            // ҵ��������ˮ��
            boolean b = (null != tagSet && null != tagSet.get("formnum"));
            String recFormnum = b ? (String)tagSet.get("formnum") : "0";
            
            //��¼��־ҵ������
            String busiType = "Order".equals(operType) ? curMenuId : Constants.BUSITYPE_SUBSCANCELSP;
            
            //ҵ����ˮ��
            String formNum = "Order".equals(operType) ? "0" : recFormnum;
            
            //���涩����־
            this.createRecLog(busiType, formNum, "0", "0","ҵ������ɹ�!");
            
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_��������Ӫ��Я��ת��(����Ȧ���Ӷ�)

        }
        else
        {
            String[] params = new String[]{operInfo, spBizName + "ҵ��"};
                        
            String resultMsg = "";
            String errCode = "";
            
            if (result != null)
            {
                resultMsg = (String) result.get("returnMsg");
                errCode = String.valueOf(result.get("errcode"));
            }
            else
            {
                resultMsg = operInfo + spBizName + "ҵ��ʧ��";
            }
            
            resultMsg = getConvertMsg(resultMsg, "zz_04_20_000001", errCode, params);         
            
            //����ʧ����Ϣ
            setErrormessage(resultMsg);
            
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_��������Ӫ��Я��ת��(����Ȧ���Ӷ�)
            //��¼��־ҵ������
            String busiType = "Order".equals(operType) ? curMenuId : Constants.BUSITYPE_SUBSCANCELSP;
            
            //��¼ʧ����־
            this.createRecLog(busiType, "0", "0", "1", resultMsg);
            // modify end sWX219697 2014-6-30 OR_HUB_201406_1115_��������Ӫ��Я��ת��(����Ȧ���Ӷ�)
          
        }
        // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
        //modify end g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
        
        logger.debug("cancelSP end!");
        return forward;
        
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPagecount()
    {
        return pagecount;
    }
    
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String essageerrormessage)
    {
        this.errormessage = essageerrormessage;
    }
    
    public Vector getSpservice()
    {
        return spservice;
    }
    
    public void setSpservice(Vector spservice)
    {
        this.spservice = spservice;
    }
    
    public SPBean getSpBean()
    {
        return spBean;
    }
    
    public void setSpBean(SPBean spBean)
    {
        this.spBean = spBean;
    }

    public String getSpName()
    {
        return spName;
    }

    public void setSpName(String spName)
    {
        this.spName = spName;
    }

    public String getSpBizName()
    {
        return spBizName;
    }

    public void setSpBizName(String spBizName)
    {
        this.spBizName = spBizName;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getBillFlagName()
    {
        return billFlagName;
    }

    public void setBillFlagName(String billFlagName)
    {
        this.billFlagName = billFlagName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSpId()
    {
        return spId;
    }

    public void setSpId(String spId)
    {
        this.spId = spId;
    }

    public String getSpBizCode()
    {
        return spBizCode;
    }

    public void setSpBizCode(String spBizCode)
    {
        this.spBizCode = spBizCode;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getDealType()
    {
        return dealType;
    }

    public void setDealType(String dealType)
    {
        this.dealType = dealType;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

	public String getOperType() 
	{
		return operType;
	}

	public void setOperType(String operType) 
	{
		this.operType = operType;
	}
	
    public SpService getSpService() 
    {
		return spService;
	}

	public void setSpService(SpService spService) 
	{
		this.spService = spService;
	}

	public List getAvailSPService() 
	{
		return availSPService;
	}

	public void setAvailSPService(List availSPService) 
	{
		this.availSPService = availSPService;
	}

    public String getDinggouStatus()
    {
        return dinggouStatus;
    }

    public void setDinggouStatus(String dinggouStatus)
    {
        this.dinggouStatus = dinggouStatus;
    }
}
