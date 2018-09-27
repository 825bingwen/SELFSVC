package com.gmcc.boss.selfsvc.privilege.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.privilege.model.CustNcodeInfoPO;
import com.gmcc.boss.selfsvc.privilege.model.GroupNcodePO;
import com.gmcc.boss.selfsvc.privilege.service.PrivilegeService;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ҵ����ģ��
 * 
 * @author yKF28472
 * @version [�汾��, Apr 23, 2011]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class PrivilegeAction extends BaseAction
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    private static Log logger = LogFactory.getLog(PrivilegeAction.class);
    
    private String curMenuId = "";
    
    // ҵ�����ͱ���
    private String nCode = "";
    
    // ��ʾ��Ϣ
    private String feeMessage = "";
    
    // �������� 1:��ͨ 0:ȡ��
    private String operType = "";
    
    // ������������
    private String operTypeName = "";
    
    // �˵�����
    private String menuName = "";
    
    // ������Ϣ
    private String busidetail = "";
    
    // ҵ��������ʷ���Ϣ
    private ReceptionBean receptionBean = null;
    
    //add begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150
    /**
     * ��ǰʹ��״̬
     */
    private String currStatus = "";
    //add end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150
    
    private String statusDesp = "";
    
    /**
     * �ر���ʾ��Ϣ
     */
    private String tip = "";
    
    /**
     * �ɹ���ʾ��Ϣ
     */
    private String successMessage = "";
    
    /**
     * parentMenuId ���˵�
     */
    private String parentMenuId = "";
    
    // add begin wWX217192 2015-04-02 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
    
    /**
     * ��������ʱ���ֻ�����
     */
    private String telnum = "";

    /**
     * ����ID
     */
    private String spid = "";
    
    /**
     * spҵ�����
     */
    private String bizid = "";
    
    // add begin hWX5316476 2015-4-28 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
    /**
     * ��ID
     */
    private String groupid;
    
    /**
     * ��Ч��ʽ��1���� 3������
     */
    private String effectType;
    
    /**
     * �ͻ�����ͬ��ҵ����Ϣ
     */
    private CustNcodeInfoPO custNcodeInfo;
    
    /**
     * ͬ��ҵ���б�
     */
    private GroupNcodePO groupNcodePO;

    // add end hWX5316476 2015-4-28 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���  
    
    /**
     * ����Service
     */
    private PrivilegeService privilegeService;
    
    private String spReceptionFlag = "";
    
	/**
     * ���ú�̨�ӿڣ���ѯ��ҵ���Ӧ���ʷ���Ϣ
     * 
     * @return
     * @throws IOException
     * @see
     */
    public String qryFeeMessage() throws IOException
    {
        logger.debug("qryFeeMessage Starting ...");
        
        this.getRequest().setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equals(province))
        {
            // ����Ŀǰû�д˽ӿڣ����������˲�
            feeMessage = "";
        }
        else
        {
            feeMessage = receptionBean.qryFeeMessage(customerSimp, termInfo, nCode, curMenuId, operType);
        }
        
        logger.info("����ҵ��" + nCode + "ʱ���Ժ�̨��ȡ����ʾ��ϢΪ��" + feeMessage);
        
        if (feeMessage == null || feeMessage.trim().length() == 0)
        {
            feeMessage = "��";
        }
        else
        {
            feeMessage = "��" + feeMessage.trim() + "��";
        }
        
        logger.debug("qryFeeMessage End");
        
        // ����������������
        if (Constants.SERVICE_APPLY.equals(operType))
        {
            operTypeName = "��ͨ";
        }
        else if (Constants.SERVICE_CANCEL.equals(operType))
        {
            operTypeName = "�˶�";
        }
        
        // �����˵�����
        List totalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        MenuInfoPO menu = null;
        for (int i = 0; i < totalMenus.size(); i++)
        {
            menu = (MenuInfoPO)totalMenus.get(i);
            if (this.curMenuId.equals(menu.getMenuid()))
            {
                break;
            }
            else
            {
                menu = null;
            }
        }
        
        if (menu != null)
        {
            busidetail = menu.getBusidetail();
            menuName = menu.getMenuname();
        }
        PrintWriter out = this.getResponse().getWriter();
        out.write(operTypeName + "," + menuName + "," + feeMessage);
        out.flush();
        
        return null;
    }
    
    /**
     * ҵ������
     * 
     * @return
     * @see
     */
    public String commitReception()
    {
        logger.debug("commitReception Starting ...");
        
        String forward = "failed";
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String sOperType = "";
        if (Constants.SERVICE_APPLY.equals(operType))
        {
        	operTypeName = "��ͨ";
        	sOperType = Constants.SERVICE_APPLY_STR;
        }
        else if (Constants.SERVICE_CANCEL.equals(operType))
        {
        	operTypeName = "�˶�";
        	
        	//��Ϊ��������ҵ����sOperTypeΪADD������ΪDEL
        	sOperType = "recInternationalRoaming".equalsIgnoreCase(curMenuId) ? 
        			Constants.SERVICE_APPLY_STR : Constants.SERVICE_CANCEL_STR;
        }
        
        // ��ȡҵ������
        menuName = CommonUtil.getMenuName(curMenuId);
        
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, nCode, sOperType, "", "", curMenuId);
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            forward = "success";
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.getReturnObject();
            // ҵ��������ˮ��
            String recFormnum = "0";
            
            if(null != tagSet && null != tagSet.get("formnum"))
            {
                recFormnum = (String)tagSet.get("formnum");
            }
            
            this.createRecLog(curMenuId, recFormnum, "0", "0", "ҵ�������ɹ�");
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            
            successMessage = menuName + operTypeName + "�ɹ�";
            
            logger.info("ҵ��(" + nCode + ")�����ɹ�");
        }
        else
        {
            // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            String resultMsg = menuName + operTypeName + "ʧ��";
            
            // modify begin yKF28472 ����ȡCRM���صĴ�����Ϣ
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
            	// begin zKF66389 2015-09-15 9�·�findbugs�޸�
                //if (result != null && result.getReturnMsg() != null && !"".equals(result.getReturnMsg()))
                if (result.getReturnMsg() != null && !"".equals(result.getReturnMsg()))
                {
                    resultMsg = result.getReturnMsg();
                }
            }
            // modify end yKF28472 ����ȡCRM���صĴ�����Ϣ
            
            resultMsg = getConvertMsg(resultMsg, "zz_04_20_000001", String.valueOf(result.getErrcode()), 
                    formatParams(sOperType, curMenuId));          
            
            this.createRecLog(curMenuId, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            
            logger.error("ҵ��(" + nCode + ")����ʧ��");
        }
        
        logger.debug("commitReception End");
        
        return forward;
    }
    
    public String selectOperationType()
    {
        //modify begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);        
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ����������Ϣ
        List totalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        MenuInfoPO menuInfo = null;
        if (totalMenus != null && totalMenus.size() > 0)
        {
            for (int i = 0; i < totalMenus.size(); i++)
            {
                menuInfo = (MenuInfoPO)totalMenus.get(i);
                if (this.curMenuId.equals(menuInfo.getMenuid()))
                {
                    busidetail = menuInfo.getBusidetail();
                    
                    // ���˵�
                    parentMenuId = menuInfo.getParentid();
                    
                    //this.getRequest().
                    break;
                }
            }
        }
        
        // add begin hWX5316476 2015-4-28 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
        // ����ͬ��ҵ����ID������в�ѯͬ��ҵ�񶩹���Ϣ������ͬ��ҵ������˵�
        if(StringUtils.isNotBlank(groupid))
        {
           // ��ѯ�û�������Ϣ
            return qryCustNcodeInfo();
        }
        // add end hWX5316476 2015-4-28 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���

        if("privNewBusRec".equals(parentMenuId))
        {
        	return "operationType";
        }
        
        // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryNcodeTips(customerSimp, termInfo, nCode, "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_HUB.equals(province))
        {
            String hotspotBz = (String)this.getRequest().getSession().getAttribute(termInfo.getTermid()+"_recHotspot");
            
            if ("1".equals(hotspotBz) )
            {
                
                ReturnWrap result = receptionBean.qryRecStatusHub(customerSimp, termInfo, nCode, "QRY", "", "", curMenuId);
                
                this.parseResult(result);
                
                logger.info("�û�" + customerSimp.getServNumber() + "��ҵ��(" + nCode + ")�ĵ�ǰ״̬��" + statusDesp);
                
                return "newOperationType"; 
            }
        }
		
        String newFlowFlag = (String) PublicCache.getInstance().getCachedData("SH_REC_NEWFLOW");
        if ("1".equals(newFlowFlag))
		{
        	// �����Ĳ�ѯʹ�õ����Ľӿ�
            if (Constants.PROOPERORGID_HUB.equals(province))
            {
            	ReturnWrap result = receptionBean.qryRecStatusHub(customerSimp, termInfo, nCode, "QRY", "", "", curMenuId);
                
                this.parseResult(result);
            }
            else
            {
            	ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, nCode, "QRY", "", "", curMenuId);
            	
            	//modify begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            	this.parseResultByRecCommServ(result);
            	//modify end jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            }

            logger.info("�û�" + customerSimp.getServNumber() + "��ҵ��(" + nCode + ")�ĵ�ǰ״̬��" + statusDesp);
            
            return "newOperationType2";
        }        
        //modify end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150        
        
        return "operationType";
    }
    
    /**
     * <��ѯͬ��ҵ���б����û��Ѱ���ҵ����Ϣ>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-5-27 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    private String qryCustNcodeInfo()
    {
        try
        {
            // ��ѯͬ��ҵ���б����û��Ѱ���ҵ����Ϣ
            custNcodeInfo = privilegeService.qryCustNcodeInfo(groupid, curMenuId);
        }
        catch (ReceptionException e)
        {
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", e.getMessage()); 
            return ERROR;
        }
        
        return "qryCustNcodeInfo";
    }
    /**
     * ȷ��ѡ��ҵ����Ϣ
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-5-27 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String confirmPrivInfo()
    {
        try
        {
            // ȷ��
            groupNcodePO = privilegeService.qryGroupNcodeInfo(groupid,custNcodeInfo.getNextNcode());
        }
        catch (ReceptionException e)
        {
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", e.getMessage()); 
            return ERROR;
        }
        
        return SUCCESS;
    }
    
    /**
     * <�ύ����ͬ��ҵ��>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-5-29 OR_SD_201503_945_ɽ��_�����ն�֧�š�ͬ�顯(��������)��Ʒ�Ķ���
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String commitPrivNcode()
    {
        try
        {
            // ������ѡ�Ż�ҵ��
            successMessage = privilegeService.commitPrivNcode(custNcodeInfo,groupNcodePO, operType, effectType, curMenuId);
        }
        catch (ReceptionException e)
        {
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", e.getMessage()); 
            return ERROR;
        }
        
        return SUCCESS;
    }

    /**
     * ��������ҵ�������ӿڷ��ص�����
     * @param result
     * @remark create by jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	private void parseResultByRecCommServ(ReturnWrap result)
	{
		// ����
		if (result != null && ((result.getStatus() == SSReturnCode.SUCCESS)
		        || (result.getErrcode() >= 104 && result.getErrcode() <= 107)))
		{
		    CTagSet tagSet = (CTagSet) result.getReturnObject();
		    
		    String currNCode = "";                
		    String nextNCode = "";
		    
		    if (tagSet != null)
		    {
		    	currNCode = tagSet.GetValue("curncode");
		    	nextNCode = tagSet.GetValue("nextncode");
		    }
		    
		    // ���¡����¾��ѿ�ͨ
		    if (StringUtils.isNotEmpty(currNCode) && StringUtils.isNotEmpty(nextNCode))
		    {
		        statusDesp = "�ѿ�ͨ";
		        
		        currStatus = "1";
		    }
		    // ���¡����¾�δ��ͨ
		    else if (StringUtils.isEmpty(currNCode) && StringUtils.isEmpty(nextNCode))
		    {
		        statusDesp = "δ��ͨ";
		        
		        currStatus = "0";
		    }
		    // �����ѿ�ͨ������ʧЧ
		    else if (StringUtils.isNotEmpty(currNCode) && StringUtils.isEmpty(nextNCode))
		    {
		        statusDesp = "�����ѿ�ͨ������ʧЧ";
		        
		        currStatus = "0";
		    }
		    // ����δ��ͨ�����¿�ͨ
		    else if (StringUtils.isEmpty(currNCode) && StringUtils.isNotEmpty(nextNCode))
		    {
		        statusDesp = "����δ��ͨ�����¿�ͨ";
		        
		        currStatus = "1";
		    }                                      
		}
		 // �쳣������δ��ͨ����
        else
        {
            statusDesp = "δ��ͨ";
            
            currStatus = "0";
        }
	}

    /**
     * �������õķ��ص�����
     * @param result
     * @remark create by jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	public void parseResult(ReturnWrap result)
	{
		// ����
		if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
		{
			CRSet crset = (CRSet) result.getReturnObject();
		    
			// �з���ֵ
			if (null != crset && 0 < crset.GetRowCount())
			{
				currStatus = crset.GetValue(0, 1);
				
				// 1���ѿ�ͨ
				if ("1".equals(currStatus))
				{
					statusDesp = "�ѿ�ͨ";
				}
				// ����δ��ͨ
				else
				{
					statusDesp = "δ��ͨ";
				}
			}
			// �޷���ֵ������δ��ͨ���д���
			else
			{
				statusDesp = "δ��ͨ";
		        
		        currStatus = "0";
			}                                      
		}
		// �쳣������δ��ͨ����
		else
		{
		    statusDesp = "δ��ͨ";
		    
		    currStatus = "0";
		}
	}
    
    /**
     * ��ʾ��Ϣ���죬��ʽ������
     * 
     * @param operType ��������
     * @param menuID ҵ��ID
     * @return
     * @see 
     * @remark create g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
     */
    private String[] formatParams(String operType, String menuID)
    {
        String[] params = new String[]{"", ""};
        
        if (Constants.SERVICE_APPLY_STR.equalsIgnoreCase(operType))
        {
            params[0] = "��ͨ";
        }
        else if (Constants.SERVICE_CANCEL_STR.equalsIgnoreCase(operType))
        {
            params[0] = "ȡ��";
        }
        
        params[1] = CommonUtil.getMenuName(menuID);
        
        return params;
    }
    
    public String jumpToSPRec()
    {
    	spReceptionFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_SPRECEPTION_FLAG);
    	
    	return SUCCESS;
    }
    
    /**
     * ɽ����������ҵ��
     * @return
     * @remark create by wWX217192 2015-03-30 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
     */
    public String orderSP()
    {
    	logger.debug("orderSP Start...");
    	
    	HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);        
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	
        String forward = "error";
        try
        {
    	// ����ɽ������ҵ�񶩹��ӿ�
    	String flag = privilegeService.addSPReception(termInfo, customer, curMenuId, telnum, spid, bizid);
    	
    	if(SSReturnCode.SUCCESS == Integer.valueOf(flag))
    	{
    		setSuccessMessage("ҵ������ɹ���");
    		
    		forward = "success";
    	}
        }
        catch(ReceptionException re)
        {
        	// ���ô�����Ϣ
            getRequest().setAttribute("errormessage", re.getMessage()); 
        }
    	
    	logger.debug("orderSP End...");
    	
    	return forward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getNCode()
    {
        return nCode;
    }
    
    public void setNCode(String code)
    {
        nCode = code;
    }
    
    public String getFeeMessage()
    {
        return feeMessage;
    }
    
    public String getGroupid()
    {
        return groupid;
    }

    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }

    public CustNcodeInfoPO getCustNcodeInfo()
    {
        return custNcodeInfo;
    }

    public void setCustNcodeInfo(CustNcodeInfoPO custNcodeInfo)
    {
        this.custNcodeInfo = custNcodeInfo;
    }

    public void setFeeMessage(String feeMessage)
    {
        this.feeMessage = feeMessage;
    }
    
    public String getOperType()
    {
        return operType;
    }
    
    public void setOperType(String operType)
    {
        this.operType = operType;
    }
    
    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }
    
    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }
    
    public String getOperTypeName()
    {
        return operTypeName;
    }
    
    public void setOperTypeName(String operTypeName)
    {
        this.operTypeName = operTypeName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMenuName()
    {
        return menuName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    //add begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150
    public String getCurrStatus()
    {
        return currStatus;
    }

    public void setCurrStatus(String currStatus)
    {
        this.currStatus = currStatus;
    }
    //add end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_150
    
    public String getBusidetail()
    {
        return busidetail;
    }
    
    public void setBusidetail(String busidetail)
    {
        this.busidetail = busidetail;
    }

    public String getStatusDesp()
    {
        return statusDesp;
    }

    public void setStatusDesp(String statusDesp)
    {
        this.statusDesp = statusDesp;
    }

    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }
        
    public String getSuccessMessage()
    {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public String getSpReceptionFlag() {
		return spReceptionFlag;
	}

	public void setSpReceptionFlag(String spReceptionFlag) {
		this.spReceptionFlag = spReceptionFlag;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getEffectType()
    {
        return effectType;
    }
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public GroupNcodePO getGroupNcodePO()
    {
        return groupNcodePO;
    }

    public void setGroupNcodePO(GroupNcodePO groupNcodePO)
    {
        this.groupNcodePO = groupNcodePO;
    }
}