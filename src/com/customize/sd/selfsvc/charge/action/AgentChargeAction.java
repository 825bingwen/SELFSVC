package com.customize.sd.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.AgentChargeBean;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * <�����̿��г�ֵ�˻�����>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Jun 5, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by sWX219697 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ����
 */
public class AgentChargeAction extends BaseAction
{
	/**
	 * ���л�id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��־
	 */
	private static final Log logger = LogFactory.getLog(AgentChargeAction.class);
	
    /**
     * ҵ���
     */
    private FeeChargeService feeChargeService;
    
    /**
     * �ӿڵ���
     */
    private transient AgentChargeBean agentChargeBean;
	
	/**
	 * �˵�
	 */
    private String curMenuId = "";
    
    /**
     * �û��ֻ���
     */
    private String servnumber;
    
    /**
     * �����̳�ֵ�Ľ��
     */
    private String yingjiaoFee;
    
    /**
     * ����ʵ�ʿۿ���
     */
    private String tMoney;
    
    /**
     * �ն˻���ˮ��
     */
    private String terminalSeq;
    
    /**
     * ������Ϣ
     */
    private String errormessage;
    
    /**
     * �������ֻ�������������
     */
    private String servRegion;
    
    /**
     * ���п���
     */
    private String cardnumber;
    
    /**
     * ����Ϊˢ�����ն˷����Ψһ���
     */
    private String unionpaycode;
    
    /**
     * �����̻��ţ��ڿ�����ʶ��
     */
    private String unionpayuser;
    
    /**
     * ҵ������
     */
    private String busitype;
    
    /**
     * �ն����κ�
     */
    private String batchnum;
    
    /**
     * ����ʵ�ʿۿ����λ���֣�
     */
    private String unionpayfee;
    
    /**
     * ����ʵ�ʿۿ�ʱ��
     */
    private String unionpaytime;
    
    /**
     * ��Ȩ�루ɽ���ã�
     */
    private String authorizationcode;
    
    /**
     * ���ײο��ţ�ɽ���ã�
     */
    private String businessreferencenum;
    
    /**
     * ��֤�ţ�ɽ���ã�
     */
    private String vouchernum;
    
    /**
     * BOSS��ˮ
     */
    private String dealNum;
    
    /**
     * �ɷ���־��Ӧ��oid
     */
    private String chargeLogOID;
    
    /**
     * �쳣���ͣ�add�������쳣��־�����������쳣��־
     */
    private String errorType;
    
    /**
     * �Ƿ���Ҫ�˿�
     */
    private String needReturnCard;
    
    /**
     * ƾ������ʱ��
     */
    private String pDealTime;
    
    /**
     * ����������
     */
    private String agentName;
    
    /**
     * �������˻�
     */
    private String agentAccount;
    
    /**
     * �˻����
     */
    private String balance;
    
    /**
     * ��������֯��������
     */
    private String orgId;
    
    /**
     * �����̽ɷ�ǰ��ˮ��
     */
    private String beforeChargeNo;
    
    /**
     * �����̿�Ŀ����
     */
    private String subjectId;
    /**
     * �����ۿ�ӿڵķ�����
     */
    private String unionRetCode;
    
    /**
     * ������ӡ��Ϣ
     */
    private String printcontext;
    
    /**
     * �����̳�ֵʱ��ѡ��Ľ��
     */
    private List<DictItemPO> selectMoneyList;

    /**
     * �����̵��ʳ�ֵ����ͽ��
     */
    private String minMoney;
    /**
     * <��ת������������ҳ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
	public String agentChargePage()
    {
        logger.debug("agentChargePage start");

        //ʧ��ҳ��
        String forward = "error";
		HttpSession session = this.getRequest().getSession();
		
		//�ն˻���Ϣ
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
		
		//�������ֻ�����Ϣ
		NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

		//�ֻ�����
		servnumber = customerSimp.getServNumber();
		
		//������
		servRegion = customerSimp.getRegionID();
		
		//��⵱ǰʱ����������ֵ�Ƿ����
		String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
		String errorMsg = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG);
		
		//�ж����õ�ʱ���Ƿ�Ϊ�գ���Ϊ�գ�����ʱ�������
		if (null != time && !"".equals(time.trim()))
		{
		    // ��ǰʱ��
		    String currTime = CommonUtil.dateToString(new Date(), "HHmm");
		    
		    // ��ǰʱ����0025��2320֮��ʱ����
		    String[] times = time.split("-");
		    
		    //��ǰʱ��ο��Գ�ֵ
		    if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
		    {
		    	logger.debug("��ǰʱ��:"+time+"�����̿��Գ�ֵ");
		    }
		    
		    //�������ڲ���ʹ������������
		    else
		    {
		    	setErrormessage(errorMsg);
		    	this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "��ǰʱ�����������ֵ������");
		    	return forward;
		    }
		}
		
		//��ѯ�ֻ��Ŷ�Ӧ�Ĵ�������Ϣ
		Map<String,String> resultMap = agentChargeBean.qryAgentInfo(termInfo, servnumber, curMenuId);
		
		//��ѯ�ɹ�
		if (String.valueOf(SSReturnCode.SUCCESS).equals(resultMap.get("retcode")))
		{
		    //��������֯��������
		    orgId = resultMap.get("orgId");
		    
		    //����������
		    agentName = resultMap.get("agentName");
		    
		    //�������ʽ��˻�����
			agentAccount = resultMap.get("agentAccount");
			
			//�����̿�Ŀ����
			subjectId = resultMap.get("subjectId");
			
			//�˻����
			balance = resultMap.get("balance");
			
			//���ֵ��в�ѯ�����̿�ѡ��Ľ��
			selectMoneyList = (List<DictItemPO>)PublicCache.getInstance()
				.getCachedData(Constants.AGENT_SELECT_MONEY);
			
			//��ѯ���ʳ�ֵ����ͽ�����ƣ���û�����ã���Ĭ��Ϊ200Ԫ
			String lowMoney = (String)PublicCache.getInstance().getCachedData(Constants.AGENT_MIN_MONEY);
			minMoney = ((null == lowMoney || "".equals(lowMoney)) ? "200" : lowMoney);
			
			forward = "otherFee";
		}
		
		//��ѯ��������Ϣʧ��
		else
		{
			logger.error("��ѯ�������˻���Ϣʧ�ܣ�");
		    String errMsg = CommonUtil.getParamValue(Constants.AGENT_QRY_INFO_ERR_MSG);
		    
		    //����ʾ��δ���ã���ʹ��Ĭ�ϵ�
		    errMsg = StringUtils.isEmpty(errMsg) ? "��ѯ�������˻���Ϣʧ�ܣ��޷�Ϊ�ʽ��˻����ѣ�" : errMsg;
		    
		    //��װ������Ϣ
		    String showErrMsg = StringUtils.isEmpty(resultMap.get("returnMsg")) ? errMsg : resultMap.get("returnMsg");
		    setErrormessage(showErrMsg);
		    
		    // ��¼�쳣��־
		    this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "��ѯ�������˻���Ϣʧ��");
		}
    	
    	logger.debug("agentChargePage end");
    	return forward;
    }
    
    /**
     * <�����̳�ֵ�������ҳ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputMoney()
    {
    	return "inputMoney";
    }
    
    /**
     * <��ת��������ҳ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String dutyInfo()
    {
    	return "dutyInfo";
    }
    
    /**
     * <�����̳�ֵ�忨ҳ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String readAgentCard()
    {
        logger.debug("readAgentCard begin");
        
    	String forward = "readAgentCard";
    	
    	try 
    	{
			HttpSession session = this.getRequest().getSession();
			TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
			
			//��ȡ������־id����¼�ɷ�ǰ��־
			chargeLogOID = feeChargeService.getChargeLogOID();
			CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
			
			//������־Ψһ��ʶ
			cardChargeLogVO.setChargeLogOID(chargeLogOID);
			
			//�ն˻�����
			cardChargeLogVO.setRegion(termInfo.getRegion());
			
			//�ն˱��
			cardChargeLogVO.setTermID(termInfo.getTermid());
			
			//����Ա���
			cardChargeLogVO.setOperID(termInfo.getOperid());
			
			//�ֻ�����
			cardChargeLogVO.setServnumber(servnumber);
			
			//�������ͣ������� 1���ֽ�ɷ� 4
			cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
			
			//���ѽ�� ��λ ��
			cardChargeLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
			
			//��¼ʱ��
			cardChargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
			
			//�ۿ�״̬ 11�����ѳɹ���10�������ۿ�ɹ���������ʧ�ܣ�01�������ۿ�ɹ�����ʱ״̬ 00��
			cardChargeLogVO.setStatus("00");
			cardChargeLogVO.setDescription("�ۿ�֮ǰ��¼��־");

			//�ֻ��Ź�����
			cardChargeLogVO.setServRegion(servRegion);
			
			//�ն˵���֯��������
			cardChargeLogVO.setOrgID(termInfo.getOrgid());
			
			//ҵ�����ͣ�phone�����ѽɷ�  favourable���Żݽɷ� agentpay�������̽��ѣ�
			cardChargeLogVO.setRecType("agentpay");
			
			//�ն˵����к�
			cardChargeLogVO.setBankno(getChargeType("Card") + termInfo.getBankno());
      
			// ����ɷ���־
			feeChargeService.addChargeLog(cardChargeLogVO);
			
			// ���γ�ʱ������ҳ�Ĺ���
			this.getRequest().setAttribute("sdCashFlag", "1");
		} 
    	catch (Exception e) 
    	{
            logger.error("�忨֮ǰ��¼��־ʧ�ܣ�", e);
            
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "�忨֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            setErrormessage("�忨֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            forward = "cardErrorPage";
		}
        
        logger.debug("readAgentCard end");
    	
    	return forward;
    }
    
    /**
     * <У���û��ĳ�ֵ���>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-8-23 11:09:10 OR_huawei_201408_657_�����ն˴������ʽ��˻���ֵ�����Ż�
     */
    public void checkBeforeCharge() throws Exception
    {
        logger.debug("checkBeforeCharge Starting ...");
        
    	HttpSession session = this.getRequest().getSession();
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
		
		//�ַ���ʽ
		this.getResponse().setContentType("text/xml;charset=GBK");
        
		PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("�����̽ɷ�ǰ��ֵ���У��ʧ�ܣ�");
        }
        
        //�����̽ɷ�ǰ��ֵ���У��
    	String resultMsg = agentChargeBean.checkBeforeCharge(termInfo, servRegion, 
    			curMenuId, agentAccount, CommonUtil.yuanToFen(yingjiaoFee), subjectId, orgId);
        
		writer.write(resultMsg);
		writer.flush();
    }

    /**
     * <�����̽���ǰ�ӿڵ���>
     * <������ȷ����������Ϣ���ȵ��ô˽ӿڼ�¼����ǰ��Ϣ����ý���ǰ��ˮ�ţ��ٽ��������ۿ����>
     * @see [�ࡢ��#��������#��Ա]
     */
    public void beforeAgentCharge() throws Exception
    {
        logger.debug("beforeAgentCharge Starting ...");
        
    	HttpSession session = this.getRequest().getSession();
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
        
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("��¼�ɷ�ǰ��Ϣ�ӿڵ���ʧ�ܣ�");
        }
        
        //�����̽ɷ�ǰ��Ϣ��¼�ӿ�
    	beforeChargeNo = agentChargeBean.beforeAgentCharge(termInfo, servnumber, curMenuId, 
    			orgId, agentAccount, yingjiaoFee, subjectId);
        
    	//���ɷ�ǰ��ˮ�Ų�Ϊ�գ��򷵻�1~~beforeChargeNo�����򷵻�0
    	if (null != beforeChargeNo && !"".equals(beforeChargeNo))
    	{
    		writer.write("1~~"+beforeChargeNo);
    		writer.flush();
    	}
    	else
    	{
    		writer.write("0");
    		writer.flush();
    	}
    	
    }
    
    /**
     * <�����̽����ύ>
     * <�����ۿ�ɹ��󣬵��ýӿڶԴ������˻���ֵ>
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardChargeCommit()
    {
        logger.debug("cardChargeCommit start");
        
        String forward = "cardErrorPage";
        
        HttpSession session = this.getRequest().getSession();
        
        //��ֹ�û����������ۿ�����
        String referer = this.getRequest().getHeader("Referer");
        
        //����һ������Ϊ��
        if (null == referer)
        {
            setErrormessage("��Ч����");
            
            return forward;
        }
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        pDealTime = CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        
        //����ת����Ԫ��ע�⣺�������صĽ���Է�Ϊ��λ����ֵ�ӿ�����ԪΪ��λ
        tMoney = CommonUtil.fenToYuan(tMoney);
        
        // ������־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // �����̳�ֵ
        Map<String,Object> resultMap = agentChargeBean.agentCharge(termInfo, servnumber, curMenuId, tMoney, beforeChargeNo);
        
        //��ֵ�ɹ�
        if (null != resultMap && SSReturnCode.SUCCESS == (Integer)resultMap.get("retcode"))
        {
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("���������������ѳɹ���");
            
            forward = "success";
            
            // ��¼�ɷѳɹ���־
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "0", "�����̽���:�����ն˽��ѳɹ�!");
        }
        
        //��ֵʧ�ܣ����������Ϣ
        else
        {
            String errMsg = "";
            if (null != resultMap)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            
            if (null == errMsg || "".equals(errMsg.trim()))
            {
                errMsg = "�������ۿ�ɹ������ǽ���ʧ�ܣ�";
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            
            forward = "cardErrorPage";
            
            String chargeErrMsg = (String) PublicCache.getInstance().getCachedData(Constants.AGENT_CHARGE_ERR_MSG);
            
            //����ʾ��δ���ã���ʹ��Ĭ�ϵ�
            if (null == chargeErrMsg || "".equals(chargeErrMsg))
            {
            	chargeErrMsg = "�������ۿ�ɹ������ǽ���ʧ�ܣ����ǽ����Ժ�Ϊ������������ϵӪҵԱȡ���������п���";
            }
            
            setErrormessage(chargeErrMsg);
            
            // ��¼�ɷ�ʧ����־
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
    }
    
    /**
     * <���������쳣����>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String goCardError()
    {
        logger.debug("goCardError Starting ...");
        
        //��¼�쳣��־
        this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", errormessage);
        
        // �ն���Ϣ
        HttpSession session = getRequest().getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        //�������ͣ�Ϊ�ջ�add����ʾ������־���������ԭ�е���־
        if (null == errorType || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
        {
            selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setTermID(termInfo.getTermid());
            selfCardPayLogVO.setOperID(termInfo.getOperid());
            selfCardPayLogVO.setServnumber(servnumber);
            
            //������֧��
            selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
            //tMoney:�����ۿ��yingjiaoFee������������Ľ��
            if (null == tMoney || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            }
            else
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setServRegion(servRegion);
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            selfCardPayLogVO.setRecType("agentpay");
            selfCardPayLogVO.setBankno(getChargeType("Card") + termInfo.getBankno());
            feeChargeService.addChargeLog(selfCardPayLogVO);
        }
        // ������־
        else
        {
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            
            //��tMoney��Ϊ�գ�˵�������ѳɹ��ۿ��Ҫ��status��Ϊ10:�����ۿ�ɹ���������ʧ��
            if (null == tMoney || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setStatus("00");
            }
            else
            {
                selfCardPayLogVO.setStatus("10");
            }
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setPosResCode(unionRetCode);
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        
        logger.debug("goCardError End");
        
        return "cardErrorPage";
    }
    
    /**
     * <�����ۿ�ɹ��������־��Ϣ>
     * <������ϸ����>
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog() throws Exception
    {
        logger.debug("updateCardChargeLog Starting...");
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        this.getResponse().setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("�����ۿ�֮���¼��־ʧ��");
        }
        
        // ��װ��־����
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        //�ն���������
        cardChargeLogVO.setRegion(termInfo.getRegion());
        
        //�ն���֯��������
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        
        //��־Ψһ��ʾ
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        
        //�����̻���
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        
        //�����ն˺�
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        //��������
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        //����
        cardChargeLogVO.setCardnumber(cardnumber);
        
        //���κ�
        cardChargeLogVO.setBatchnum(batchnum);
        
        //��Ȩ��
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        
        //���ײο���
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        
        //����ʵ�ʿۿ�ʱ��
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        
        //ƾ֤��
        cardChargeLogVO.setVouchernum(vouchernum);
        
        //�����ۿ���
        if (null != unionpayfee)
        {
            while (unionpayfee.startsWith("0"))
            {
                unionpayfee = unionpayfee.substring(1);
            }
        }
        else
        {
            unionpayfee = "";
        }
        
        //ʵ�ʿۿ���
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        
        //������ˮ��
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        //��ǰ����״̬
        String status = (String)request.getParameter("status");
        cardChargeLogVO.setStatus(status);
        
        //������Ϣ
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        cardChargeLogVO.setDescription(description);
        
        //��־��¼ʱ��
        cardChargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        String xml = "";
        try
        {
            // ����ɷ���־
            feeChargeService.updateCardChargeLog(cardChargeLogVO);
            
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("�ۿ�֮�������־�쳣��", e);
        }
        finally
        {
            // �����client
            if (null != writer)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("�ر�writer�쳣��", e);
                    
                    throw new Exception("�ۿ�֮�������־�쳣");
                }
            }
        }
        
        logger.debug("updateCardChargeLog end!");
    }

    /**
     * ȡ�ɷ�����
     * 
     * @param payType(Card����Cash) groupid = 'ChargeType'
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String getChargeType(String payType)
    {
        String chargeType = "";
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance()
                .getCachedData(Constants.ChargeType);

        if (null != chargeTypeList)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        
        return chargeType;
    }
    
	public FeeChargeService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public AgentChargeBean getAgentChargeBean() {
		return agentChargeBean;
	}

	public void setAgentChargeBean(AgentChargeBean agentChargeBean) {
		this.agentChargeBean = agentChargeBean;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getServRegion() {
		return servRegion;
	}

	public void setServRegion(String servRegion) {
		this.servRegion = servRegion;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getUnionpaycode() {
		return unionpaycode;
	}

	public void setUnionpaycode(String unionpaycode) {
		this.unionpaycode = unionpaycode;
	}

	public String getUnionpayuser() {
		return unionpayuser;
	}

	public void setUnionpayuser(String unionpayuser) {
		this.unionpayuser = unionpayuser;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public String getUnionpayfee() {
		return unionpayfee;
	}

	public void setUnionpayfee(String unionpayfee) {
		this.unionpayfee = unionpayfee;
	}

	public String getUnionpaytime() {
		return unionpaytime;
	}

	public void setUnionpaytime(String unionpaytime) {
		this.unionpaytime = unionpaytime;
	}

	public String getAuthorizationcode() {
		return authorizationcode;
	}

	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	public String getBusinessreferencenum() {
		return businessreferencenum;
	}

	public void setBusinessreferencenum(String businessreferencenum) {
		this.businessreferencenum = businessreferencenum;
	}

	public String getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}

	public String getDealNum() {
		return dealNum;
	}


	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

	public String getChargeLogOID() {
		return chargeLogOID;
	}


	public void setChargeLogOID(String chargeLogOID) {
		this.chargeLogOID = chargeLogOID;
	}


	public String getErrorType() {
		return errorType;
	}


	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}


	public String getNeedReturnCard() {
		return needReturnCard;
	}


	public void setNeedReturnCard(String needReturnCard) {
		this.needReturnCard = needReturnCard;
	}


	public String getPDealTime() {
		return pDealTime;
	}


	public void setPDealTime(String dealTime) {
		pDealTime = dealTime;
	}


	public String getAgentName() {
		return agentName;
	}


	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	public String getAgentAccount() {
		return agentAccount;
	}


	public void setAgentAccount(String agentAccount) {
		this.agentAccount = agentAccount;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getUnionRetCode() {
		return unionRetCode;
	}


	public void setUnionRetCode(String unionRetCode) {
		this.unionRetCode = unionRetCode;
	}


	public String getPrintcontext() {
		return printcontext;
	}


	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}


	public String getBeforeChargeNo() {
		return beforeChargeNo;
	}

	public void setBeforeChargeNo(String beforeChargeNo) {
		this.beforeChargeNo = beforeChargeNo;
	}

	public String getYingjiaoFee() {
		return yingjiaoFee;
	}

	public void setYingjiaoFee(String yingjiaoFee) {
		this.yingjiaoFee = yingjiaoFee;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public List<DictItemPO> getSelectMoneyList() {
		return selectMoneyList;
	}

	public void setSelectMoneyList(List<DictItemPO> selectMoneyList) {
		this.selectMoneyList = selectMoneyList;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}
    
}
