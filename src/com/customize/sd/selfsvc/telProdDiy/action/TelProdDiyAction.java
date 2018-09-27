/*
 * �� �� ��:  TelProdDiyAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <��ѡ�ײ�>
 * �� �� ��: jWX216858
 * ����ʱ��: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;
import com.customize.sd.selfsvc.telProdDiy.service.TelProdDiyService;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.common.ReceptionException;

/**
 * ��ѡ�ײ�action
 * @author  jWX216858
 * @version  [�汾��, 2014-10-10]
 * @see  
 * @since 
 */
@Controller
@Scope("prototype")
public class TelProdDiyAction extends PagedAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//public static final Logger log = Logger.getLogger(TelProdDiyAction.class);
	private static Log log = LogFactory.getLog(TelProdDiyAction.class);
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	
	/**
	 * ��ѯservice
	 */
	@Autowired
	private transient TelProdDiyService telService;
	
	/**
	 * ��ѡҵ���б�
	 */
	private List<TelProdPO> voiceProdList;
	
	/**
	 * ��ѡҵ���б�
	 */
	private List<TelProdPO> netProdList;
	
	/**
	 * �����Ʒ�б�
	 */
	private List<TelProdPO> telProdList;
	
	/**
	 * ��ѡ�ײͶ���
	 */
	private transient TelProdPO telProdPo = new TelProdPO();
	
	/**
	 * ��ǰ�˵�
	 */
	private String curMenuId;
	
	/**
	 * �ܷ���
	 */
	private String totalHidden = "0.00";
	
    /**
     * ҳ���Ҳ�div�������
     */
    private String rightDivHtml;
    
    /**
     * ������Ϣ
     */
    private String errormessage;
    
    /**
     * �ɹ���Ϣ
     */
    private String successMessage;
    
    /**
     * ��ѯ��ѡ�ײͿ���ҵ���б�
     * @return
     * @remark modify by sWX219697 2015-5-5 17:37:11 OR_SD_201503_508_SD_�����ն����������Ʒ��ѡ�ײ͵İ���
     */
    public String qryTelProdList()
    {
        String forward = ERROR;
        
        try
        {
            //��ѯ�û����õ������Ʒ
            telProdList = telService.qryUsableProdList(curMenuId);
            forward = "qryTelProdList";
        }
        catch (ReceptionException e)
        {
            log.error("��ѯ��ѡ�ײͿ���ҵ���б�ʧ�ܣ�", e);
            setErrormessage(e.getMessage());    
        }
    	
    	return forward;
    }
    
    /**
     * <�����û�ѡ��������Ʒ�����Ҷ�Ӧ�����������������ײ�>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-5-6 09:45:30 OR_SD_201503_508_SD_�����ն����������Ʒ��ѡ�ײ͵İ���
     */
    public String qryProdListByProdId()
    {
        String forward = ERROR;
        
        try
        {
            // ��ѯ���ݿ⣬��ȡ��ѡ�ײ���Ϣ,Ĭ�ϲ�ѯ����
            if (null == telProdPo.getQryType() || "VOICECALL".equals(telProdPo.getQryType()))
            {
                // �Ի�ȡ���������ݽ��з�ҳ
                voiceProdList = getPageList(telService.qryVoiceProdList(telProdPo.getProdId()), 8);
            }
            else
            {
                // �Ի�ȡ���������������ݽ��з�ҳ
                netProdList = getPageList(telService.qryNetProdList(telProdPo.getProdId()), 8);
            }
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            log.error("���������Ʒ�����ѯ�����������ײ�ʧ�ܣ�", e);
            setErrormessage(e.getMessage());        
        }
        
        return forward;
    }
    
    /**
     * ��ѯ��ѡ�ײͿ���ҵ���б�
     * @return
     * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_ɽ��_���������ն˲�Ʒ����������4G��ѡ�ײ��Լ��޸�GPRS��4G����Ĺ��ܣ�ȫҵ�������Ż���
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_�����ն����������Ʒ��ѡ�ײ͵İ���
     */
    public String recSubmit()
    {
        String forward = ERROR;
        
        try
        {
            //��ѯ�û�ѡ���ײͶ�Ӧ��ncode����������ˮ�߽ӿ�����
            telService.recCommit(telProdPo, curMenuId);
            
            setSuccessMessage("��ѡ�ײ�����ɹ�");
            forward = "recSubmit";
        }
        catch (ReceptionException e)
        {
            log.error("��ѡ�ײ�����ʧ�ܣ�", e);
            setErrormessage(e.getMessage());   
        }
		
		return forward;
    }


    
    public String getTotalHidden()
    {
        return totalHidden;
    }
    
    public void setTotalHidden(String totalHidden)
    {
        this.totalHidden = totalHidden;
    }
    
    public String getRightDivHtml()
    {
        return rightDivHtml;
    }
    
    public void setRightDivHtml(String rightDivHtml)
    {
        this.rightDivHtml = rightDivHtml;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public List<TelProdPO> getVoiceProdList()
    {
        return voiceProdList;
    }
    
    public void setVoiceProdList(List<TelProdPO> voiceProdList)
    {
        this.voiceProdList = voiceProdList;
    }
    
    public List<TelProdPO> getNetProdList()
    {
        return netProdList;
    }
    
    public void setNetProdList(List<TelProdPO> netProdList)
    {
        this.netProdList = netProdList;
    }

    public List<TelProdPO> getTelProdList()
    {
        return telProdList;
    }

    public void setTelProdList(List<TelProdPO> telProdList)
    {
        this.telProdList = telProdList;
    }

    public TelProdPO getTelProdPo()
    {
        return telProdPo;
    }

    public void setTelProdPo(TelProdPO telProdPo)
    {
        this.telProdPo = telProdPo;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
    
}
