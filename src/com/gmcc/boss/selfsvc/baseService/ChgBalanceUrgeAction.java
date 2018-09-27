package com.gmcc.boss.selfsvc.baseService;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ChgBalanceUrgeBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �������ѡ������ֵ�Ͷ�����ֵ��������
 * @author xkf29026
 *
 */
public class ChgBalanceUrgeAction extends BaseAction
{
    
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin by xkf29026 2011/10/6 ���final
    public static final Log logger = LogFactory.getLog(ChgBalanceUrgeAction.class);
    // modify end by xkf29026 2011/10/6  ���final
    
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    //��ǰҳ��
    private String pagecount;
    
    // ������Ϣ
    private String errormessage;
    
    //�ɹ���Ϣ
    private String successMessage;
    
    // �����������
    private String balanceAwake;
    
    //��������
    private String operType;
    
    //�������ֵ����
    private Vector balanceVector;
    
    // ���ýӿ�Bean
    private ChgBalanceUrgeBean chgBalanceUrgeBean;
    
    /**
     * �����������ֵҳ��
     * 
     * @return
     */
    public String balanceUrgePage()
    {
        // ��¼��־
        logger.debug("balanceUrgePage starting");
        
        if(pagecount != null)
        {
            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
        }
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���ýӿڻ�ȡ���������Ϣ
        Map result = chgBalanceUrgeBean.qryBalanceNotice(terminalInfoPO, customer, curMenuId);        
        
        if (result == null || result.size() <= 0)
        {
            // ���ô�����ʾ��Ϣ
            setErrormessage("��ѯ��ǰ�������ֵʧ�ܣ����Ժ����ԡ��������㣬����ԭ�¡�");
            
            String forward = "error";
            
            // ����ʧ����־
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "1", "��ѯ��ǰ�������ֵʧ�ܣ����Ժ����ԡ��������㣬����ԭ�¡�");
            
            return forward;
        }
        else
        {
            CTagSet tagset = (CTagSet)result.get("returnObj");
            String prepayType = tagset.GetValue("prepay_type");
            String credit = tagset.GetValue("credit");/////////////////////////modified by l00190940 2011-08-15
            
            //modify begin fwx439896 2017-05-22  OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3_����������˵���� 
            //�ܿ����صĸ��ѷ�ʽΪ����  �󸶷� ���ȸ���
            if ("0".equals(prepayType)||"�󸶷�".equals(prepayType))
            {
            //modify begin fwx439896 2017-05-22  OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3_����������˵���� 
              
            	// ���ô�����ʾ��Ϣ
            	setErrormessage("�󸶷��û����������������ֵ��");
                
                String forward = "error";
                
                // ����ʧ����־
                this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "0", "�󸶷��û����������������ֵ��");
                
                return forward;
            }
            else
            {
            	if ("0".equals(credit) || "".equals(credit))
            	{
            		// ���ô�����ʾ��Ϣ
                	setErrormessage("����δ��ͨ������ѷ���");
                	
                	String forward = "noBalanceUrge";
                	return forward;
            	}
            	else
            	{
            		setSuccessMessage(CommonUtil.fenToYuan(credit));
            		String forward = "changeOrCancel";
            		return forward;
            	}
            }

        }
    }
    
    /**
     * �����������ֵѡ�����
     */
    public String balanceUrgeSelect()
    {
    	logger.debug("balanceUrgeSelect Starting...");
    	
    	// ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        /**
         * �˴�getDictItem_chgBalanceUrge�е�getDictItemΪ����groupid��ȡ�ֵ���Ϣ�Ĺ��÷�������ʹ�õ��ǲ������ݣ����Ժ��������_chgBalanceUrge��
         * ��ʹ�������Ľӿڻ������ʱ��ʹ��getDictItem
         */
        Map result = chgBalanceUrgeBean.getDictItem_chgBalanceUrge(terminalInfoPO,
                customer,
                curMenuId,
                Constants.LEFT_MONEY_NOTIFS);
        
        if (result != null && result.size() > 0)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            if (crset != null)
            {
                Vector balanceVector = new Vector();
                DictItemVO dictitemVO = null;
                
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    dictitemVO = new DictItemVO(crset.GetValue(i, 0), crset.GetValue(i, 1), Constants.LEFT_MONEY_NOTIFS);
                    
                    balanceVector.add(dictitemVO);
                }
                //�����������ֵ
                setBalanceVector(balanceVector);
            }
        }
        else
        {
            // ���ô�����ʾ��Ϣ
        	setErrormessage("��ѯ��ѡ�������ֵʧ��!");
        	
            String forward = "error";
            
            // ����ʧ����־
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE, "0", "0", "1", "��ѯ��ѡ�������ֵʧ��!");
            
            return forward;
        }
    	logger.debug("balanceUrgeSelect end!");
    	
    	// ����ѡ���������ֵҳ��
        return "selectBalancePage";
    }
    
    /**
     * ������Ѻ������������ֵ
     */
    public String balanceUrgeDef()
    {
        logger.debug("balanceUrgeDef Starting...");
        
        String forward = null;
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ȡ���������
        if ("0".equals(operType))
        {
            balanceAwake = "0";
        }
        
        // ���ýӿ������������ֵ        
        String credit = balanceAwake;
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equals(province))
        {
        	credit = CommonUtil.yuanToFen(credit);
        }
        
        Map result = chgBalanceUrgeBean.setBalanceNotice(terminalInfoPO, customer, curMenuId, credit, operType);
        if (result != null && result.size() > 0)
        {
            String msg = "";
            if ("0".equals(operType))
            {
                msg = "ȡ��������ѳɹ�!";
            }
            else if ("1".equals(operType))
            {
                msg = "��ͨ������ѳɹ�!";
            }
            else if ("2".equals(operType))
            {
                msg = "����������ֵ�ɹ�!";
            }
            
            // ���óɹ���Ϣ
            setSuccessMessage(msg);
            
            forward = "success";
            
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE,
                    "0",
                    "0",
                    "0",
                    msg);
        }
        else
        {
            String msg = "";
            if ("0".equals(operType))
            {
                msg = "ȡ���������ʧ��!";
            }
            else if ("1".equals(operType))
            {
                msg = "��ͨ�������ʧ��!";
            }
            else if ("2".equals(operType))
            {
                msg = "����������ֵʧ��!";
            }
            
            // ���ô�����Ϣ
            setErrormessage(msg);
            
            // modify begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            getRequest().setAttribute("backStep", "-1");
            
            forward = "error";
            
            // ����ʧ����־
            this.createRecLog(Constants.BUSITYPE_CHGBALANCEURGE,
                    "0",
                    "0",
                    "1",
                    msg);
            // modify end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        }
        logger.debug("balanceUrgeDef End!");
        return forward;
    }
   
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public ChgBalanceUrgeBean getChgBalanceUrgeBean()
    {
        return chgBalanceUrgeBean;
    }
    
    public void setChgBalanceUrgeBean(ChgBalanceUrgeBean chgBalanceUrgeBean)
    {
        this.chgBalanceUrgeBean = chgBalanceUrgeBean;
    }
    
    public String getBalanceAwake()
    {
        return balanceAwake;
    }
    
    public void setBalanceAwake(String balanceAwake)
    {
        this.balanceAwake = balanceAwake;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public Vector getBalanceVector()
    {
        return balanceVector;
    }

    public void setBalanceVector(Vector balanceVector)
    {
        this.balanceVector = balanceVector;
    }

    public String getPagecount()
    {
        return pagecount;
    }

    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getOperType() {
		return operType;
	}

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setOperType(String operType) {
		this.operType = operType;
	}
}
