package com.gmcc.boss.selfsvc.serviceinfo.action;

import com.customize.hub.selfsvc.common.ConstantsHub;
import com.gmcc.boss.selfsvc.bean.ScoreBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * ���ֲ�ѯ <������ϸ����>
 * 
 * @author yKF28472
 * @version [�汾��, May 28, 2011]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ScoreAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(ScoreAction.class);
    
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    // ������Ϣ
    // private String error;
    
    // ����б����
    private String[] servicetitle;
    
    // ���û�������
    private String[] score;
    
    // ���ýӿ�bean
    private ScoreBean qryScoreBean;
    
    /**
     * ��ʼʱ��
     */
    private String startDate;
    
    /**
     * ����ʱ��
     */
    private String endDate;
    
    /**
     * ���ֲ�ѯ���
     */
    private String queryType;
    
    /**
     * ������ϸ��ѯ���ؽ��
     */
    private List<Map<String, String>> scoreDetail;
    
    /**
     * ���ֶһ���ϸ��ѯ
     */
    private List<Map<String, String>> scoreChangesd;
    
    /**
     * ���ֶһ���ϸ��ѯ
     */
    private List<Map<String, String>> scoreChangehub;
    
    /**
     * ����ĵ��б��
     */
    private String requestRegion;
    
    /**
     * ���ص���Ϣ��ʾ
     */
    private String retMessage;
    
    // add begin jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
    /**
     * �û����û��֣�ɽ����
     */
    private String pointBalance = "";
    
    /**
     * ���ַ��Ų�ѯ���(ɽ��)
     */
    private List<Map<String, String>> scorePayment;
    
    /**
     * ���ֲ�ѯ�����ɽ����
     */
    private List<Map<String, String>> scoreSD;
    // add end jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
    
    /**
     * ���ֲ�ѯ    
     * 
     * @return
     */
    public String qryScore()
    {
        // ��ѯ��־��ʼ
        logger.debug("qryScore Starting...");
        
        // ��λ������ҳ��
        String froward = "error";
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //��ȡ����ʡ��
        String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        //add begin sWX219697 2014-5-13 OR_SD_201404_777
        //�û����ּƻ���ѯ�ӿڿ���
        String scorePlanSwitch = (String)PublicCache.getInstance().getCachedData(Constants.SCORE_PLAN_SWITCH);
      
//        if (provinceID.equals(Constants.PROOPERORGID_SD) //ɽ��
//        		&& !"ȫ��ͨ".equals(customer.getBrandName())//��ȫ��ͨ�û�
//        		&& "1".equals(scorePlanSwitch)//���ؿ���
//        		&& !qryScoreBean.qryIsScoreOpenForSd(terminalInfoPO, customer, curMenuId))//δ��ͨ���ּƻ�
//        {
//        	//û�п�ͨ���ּƻ�����ʾ��Ϣ
//        	String msg = (String)PublicCache.getInstance().getCachedData(Constants.SCORE_NOT_OPEN_MSG);
//        	this.getRequest().setAttribute("errormessage", msg);
//        	
//        	return froward;
//        }
        //add end sWX219697 2014-5-13 OR_SD_201404_777
        
        // ���ýӿڲ�ѯ������Ϣ
        Map result = qryScoreBean.queryScoreValue(terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            String scoreStr = (String)result.get("returnObj");
            // add by longchengcheng 20130702 end
            String scoreHub = (String) PublicCache.getInstance().getCachedData(ConstantsHub.SH_QRY_SCOREHUB_FLAG);
            if (provinceID.equals(Constants.PROOPERORGID_HUB)&&"1".equals(scoreHub)) {
                // �������ֲ�ѯ
                // ���ñ���
                setServicetitle(ConstantsHub.getQryScore());
                String[] scores = scoreStr.split("~");
                String[] scores_hb = new String[scores.length-1];
                for(int i=0;i<scores.length-1;i++){
                    scores_hb[i] = scores[i];
                }
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_Ȧ���Ӷ�_�����ն�
                scores_hb[0] = CommonUtil.getNameByBrandLetter(scores_hb[0]);
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_Ȧ���Ӷ�_�����ն�
                
                // ���û���
                setScore(scores_hb);
                // add by longchengcheng 20130702 end
            } 
            else if (provinceID.equals(Constants.PROOPERORGID_SD) && "1".equals(CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH)))
            {
            	// ɽ�����ֲ�ѯ�±���
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
        		//setServicetitle(Constants.SCORE_NEWTITLE_SD);
        		setServicetitle(Constants.getScoreNewtitleSd());
        		// end zKF66389 2015-09-10 9�·�findbugs�޸�
        		
        		this.qryScoreSD(result);
            }
            else 
            {
                String[] scores = scoreStr.split("~");
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_Ȧ���Ӷ�_�����ն�
                if(!provinceID.equals(Constants.PROOPERORGID_NX)){
                    scores[0] = CommonUtil.getNameByBrandLetter(scores[0]);
                }

                // modify end hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_Ȧ���Ӷ�_�����ն�
                
                //modify begin sWX219697 2014-5-13 OR_SD_201404_777
                //��Ϊɽ���û��ҽӿڿ��ؿ���
                if (provinceID.equals(Constants.PROOPERORGID_SD) && "1".equals(scorePlanSwitch))
                {
                    //�����±��⣺������ۼƻ��֣���ǰ���û���
                	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                	//setServicetitle(Constants.SCORE_TITLE_NEW_SD);
                	setServicetitle(Constants.getScoreTitleNewSd());
                	// end zKF66389 2015-09-10 9�·�findbugs�޸�
                	
                	//ɽ������չʾ�޸ģ���Ϊ������ۼƻ��֡���ǰ���û���
                	String[] scores_sd = new String[2];
                	scores_sd[0] = scores[0];
                	scores_sd[1] = scores[3];
                	setScore(scores_sd);
                }
                else
                {
                	// ���ñ���
                	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                    //setServicetitle(Constants.SCORE_TITLE);
                	setServicetitle(Constants.getScoreTitle());
                    // end zKF66389 2015-09-10 9�·�findbugs�޸�
                    
                    // ���û���
                    setScore(scores);
                }
                //modify end sWX219697 2014-5-13 OR_SD_201404_777
                
            }
            froward = "qryScore";
            
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "���ֲ�ѯ�ɹ�!");
        }
        else if (result != null)
        {
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // ����������־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "���ֲ�ѯʧ��!");
        }
        logger.debug("qryScore End!");
        return froward;
    }
    
    /**
     * ���ֲ�ѯ��ɽ����
     * @param map
     * @remark create by jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
     */
    private void qryScoreSD(Map map)
    {
    	if (null != map)
    	{
    		// �û����û���
    		pointBalance = (String) map.get("pointBalance");
    		if ("false".equals(map.get("IsInfoNull")))
    		{
    			// ������ϸ
    			scoreSD = (List<Map<String, String>>) map.get("SUCCESSINFO");
    		}
    	}
    	
    }
    
    /**
     * ת��ʼ����ʱ�����
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String forStartAndEnd()
    {
        startDate = qryScoreBean.getLastMonthBeginDate(5);
        endDate = qryScoreBean.getLastMonthEndDate(0);
        return SUCCESS;
    }
    
    /**
     * ��ѯ������ϸ��Ϣ(ɽ���ͺ���)
     * 
     * @return
     * @throws DocumentException
     * @see [�ࡢ��#��������#��Ա]
     */
    public String qryScoreDetailHisForSd() throws DocumentException
    {
        
        // ��ѯ��־��ʼ
        logger.debug("qryScoreDetailHisForSd Starting...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // ���ýӿڲ�ѯ������Ϣ
        Map result = qryScoreBean.qryScoreDetailHisForSd(startDate, endDate, terminalInfoPO, customer, curMenuId);
        if (result != null && result.size() > 1)
        {
            // ���⻺��
            clearSession();
            retMessage = null;
            // ���ص���Ϣ����
            if ("false".equals(result.get("IsInfoNull")))
            {
                // ���ñ���
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //setServicetitle(Constants.SCOREDETIAL_TITLE);
            	setServicetitle(Constants.getScoredetialTitle());
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                // ���û���
                scoreDetail = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ�����ϸ��Ϣ��";
            }
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "������ϸ��ѯ�ɹ�!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ�����ϸ��Ϣ��";
            // retMessage=(String)result.get("returnMsg");
            
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // ����������־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "������ϸ��ѯʧ��!");
        }
        logger.debug("qryScoreDetailHisForSd End!");
        return "showdetail";
    }
    
    /**
     * ��ѯ���ֶһ���ʷ��Ϣ(ɽ��)
     * 
     * @return
     * @throws DocumentException
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryScoreChangeHisForsd() throws DocumentException
    {
        
        // ��ѯ��־��ʼ
        logger.debug("queryScoreChangeHisForsd Starting...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // ���ýӿڲ�ѯ������Ϣ
        Map result = qryScoreBean.queryScoreChangeHisForsd(startDate, endDate, terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // ���⻺��
            clearSession();
            retMessage = null;
            // ���ص���Ϣ����
            if ("false".equals(result.get("IsInfoNull")))
            {
                
                // ���ñ���
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //setServicetitle(Constants.SCORECHANGE_TITLE);
            	setServicetitle(Constants.getScorechangeTitle());
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                // ���û���
                scoreChangesd = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ��ֶһ���ʷ��Ϣ��";
            }
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "ɽ�����ֶһ���ʷ��ѯ�ɹ�!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage =  "�Բ���û�в�ѯ����Ӧ�Ļ��ֶһ���ʷ��Ϣ��";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // ����������־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "ɽ�����ֶһ���ʷ��ѯʧ��!");
        }
        logger.debug("queryScoreChangeHisForsd End!");
        return "showScoreChange";
    }
    
    /**
     * ��ѯ���ֶһ���ʷ��Ϣ(����)
     * 
     * @return
     * @throws DocumentException
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryScorePrizeHisForhub() throws DocumentException
    {
        
        // ��ѯ��־��ʼ
        logger.debug("queryScorePrizeHisForhub Starting...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // ���ýӿڲ�ѯ������Ϣ
        Map result = qryScoreBean.queryScorePrizeHisForhub(startDate, endDate, terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // ���⻺��
            clearSession();
            retMessage = null;
            // ���ص���Ϣ����
            if ("false".equals(result.get("IsInfoNull")))
            {
                
                // ���ñ���
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //setServicetitle(Constants.SCOREPRIZE_TITLE);
                setServicetitle(Constants.getScoreprizeTitle());
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                // ���û���
                scoreChangehub = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ��ֶһ���ʷ��Ϣ��";
            }
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "�������ֶһ���ʷ��ѯ�ɹ�!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ��ֶһ���ʷ��Ϣ��";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // ����������־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "�������ֶһ���ʷ��ѯʧ��!");
        }
        logger.debug("queryScorePrizeHisForhub End!");
        return "showScoreChange";
    }
    
    /**
     * ���ַ��Ų�ѯ��ɽ����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
     */
    public String qryScorePaymentSD()
    {
    	// ��ѯ��־��ʼ
        logger.debug("queryScorePrizeHisForhub Starting...");
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // ��ȡ�û���Ϣ
        NserCustomerSimp  customer = getCustomerSimp();
        
        Map<String, Object> result = qryScoreBean.qryScorePaymentSD(startDate, endDate, customer, termInfo, curMenuId);
        
        if (result.size() > 1)
        {
            // ���⻺��
            clearSession();
            retMessage = null;
            
            // ���ص���Ϣ����
            if ("false".equals(result.get("IsInfoNull")))
            {
                // ���ñ���
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //setServicetitle(Constants.PAYMENTSCORE_TITLE);
                setServicetitle(Constants.getPaymentscoreTitle());
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                // ���û���
                scorePayment = (List<Map<String, String>>)result.get("SUCCESSINFO");
            }
            else
            {
                retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ��ַ�����Ϣ��";
            }
            // �����ɹ���־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "ɽ�����ַ�����Ϣ��ѯ�ɹ�!");
        }
        else
        {
            clearSession();
            retMessage = "�Բ���û�в�ѯ����Ӧ�Ļ��ַ�����Ϣ��";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // ����������־
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "ɽ�����ַ��Ų�ѯʧ��!");
        }
        logger.debug("queryScorePrizeHisForhub End!");
    	return "qryScorePaymentSD";
    }
    
    /**
     * ������ֻ���(���ⲻ��Ҫ�Ļ���)
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public void clearSession()
    {
        // ���⻺��
        setServicetitle(null);
        scoreDetail = null;
        scoreChangehub = null;
        scoreChangesd = null;
        scorePayment = null;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public ScoreBean getQryScoreBean()
    {
        return qryScoreBean;
    }
    
    public void setQryScoreBean(ScoreBean qryScoreBean)
    {
        this.qryScoreBean = qryScoreBean;
    }
    
    public String[] getServicetitle()
    {
        return servicetitle;
    }
    
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }
    
    public String[] getScore()
    {
        return score;
    }
    
    public void setScore(String[] score)
    {
        this.score = score;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getQueryType()
    {
        return queryType;
    }
    
    public void setQueryType(String queryType)
    {
        this.queryType = queryType;
    }
    
    public List<Map<String, String>> getScoreDetail()
    {
        return scoreDetail;
    }
    
    public void setScoreDetail(List<Map<String, String>> scoreDetail)
    {
        this.scoreDetail = scoreDetail;
    }
    
    public String getRequestRegion()
    {
        return requestRegion;
    }
    
    public void setRequestRegion(String requestRegion)
    {
        this.requestRegion = requestRegion;
    }
    
    public List<Map<String, String>> getScoreChangesd()
    {
        return scoreChangesd;
    }
    
    public void setScoreChangesd(List<Map<String, String>> scoreChangesd)
    {
        this.scoreChangesd = scoreChangesd;
    }
    
    public List<Map<String, String>> getScoreChangehub()
    {
        return scoreChangehub;
    }
    
    public void setScoreChangehub(List<Map<String, String>> scoreChangehub)
    {
        this.scoreChangehub = scoreChangehub;
    }
    
    public String getRetMessage()
    {
        return retMessage;
    }
    
    public void setRetMessage(String retMessage)
    {
        this.retMessage = retMessage;
    }

	public String getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(String pointBalance) {
		this.pointBalance = pointBalance;
	}

	public List<Map<String, String>> getScorePayment() {
		return scorePayment;
	}

	public void setScorePayment(List<Map<String, String>> scorePayment) {
		this.scorePayment = scorePayment;
	}

	public List<Map<String, String>> getScoreSD() {
		return scoreSD;
	}

	public void setScoreSD(List<Map<String, String>> scoreSD) {
		this.scoreSD = scoreSD;
	}
    
}
