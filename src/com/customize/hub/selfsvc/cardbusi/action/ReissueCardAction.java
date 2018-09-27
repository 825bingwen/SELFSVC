
package com.customize.hub.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ReissueCardBean;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * <����>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Oct 23, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
 */
public class ReissueCardAction extends CardBusiBaseAction
{
	/**
     * ���л�
     */
    private static final long serialVersionUID = 3129620728444184038L;
	
	private static Log logger = LogFactory.getLog(ReissueCardAction.class);
	
	/**
	 * �����ӿڵ���
	 */
	private transient ReissueCardBean reissueCardBean;
	
	/**
	 * �û���¼
	 */
	private transient UserLoginBean userLoginBean;
	
    /**---------��־����--------------
     * �ɷ���־
     */
    private transient FeeChargeHubService feeChargeService;

	/**
	 * �û���Ϣ��
	 */
	private transient NserCustomerSimp customer;
	
	/**
	 * ����������vo
	 */
	private transient CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
	
	/**
	 * �û��ֻ�����
	 */
	private String servnumber;
	
	/**
	 * ����
	 */
	private String password;
	
    /**-----------------------�ɷ����-----------------------------
     * ���ý��ѷ�ʽ
     */
    private List usableTypes;
    
    /**
     * �û�Ͷ�ҽ��/�����ۿ���
     */
    private String tMoney;
    
    /**
     * ֧����ʽ 1����������4���ֽ�
     */
    private String payType;
    
    /**
     * �Ƿ���Ҫ�³������� 1����Ҫ
     */
    private String needReturnCard;
    
    /**
     * ������ӡ��Ϣ
     */
    private String printcontext;
    
    /**
     * ������־��ʽ��add/update
     */
    private String errorType;
	
	/**
	 * <������¼ҳ��>
	 * <��ת������¼ҳ�棬Ҫ�����������ֻ�����ͷ�������>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String inputTelnum()
	{
		//�������session
		removeUserSession();
		
		return "inputTelnum";
	}
	
	/**
	 * <����û�session>
	 * <������ϸ����>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void removeUserSession()
	{
		if (getCustomerSimp() != null)
		{
			//���session
            this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
		}
	}
	
	/**
	 * <У���û���ݲ���ת����ȡ���֤ҳ��>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String readCert()
	{
		String forward = "readCert";
		
		//У���û�����
		Map authResult = userLoginBean.getUserInfoWithPwd(servnumber, password, getTerminalInfoPO());
		customer = (NserCustomerSimp)authResult.get("customerSimp");
		
		//У��ʧ��
		if(null == authResult.get("customerSimp"))
		{
			//������Ϣת��
            String resultMsg = getConvertMsg((String) authResult.get("returnMsp"), 
                    "zz_04_01_000001", String.valueOf(authResult.get("errcode")), 
                    null);
            
            if (StringUtils.isBlank(resultMsg))
            {
            	resultMsg = "��¼ʧ��";
            }
            
            // У��ʧ��
            setErrormessage(resultMsg);
            
            // ��¼��־:�ֻ����롢ҵ�����͡�ҵ����ˮ�š�ҵ����á�״̬��0���ɹ���1:��������
            this.createRecLog(servnumber, "reissueCard", "0", "0", "1", (String) authResult.get("returnMsp"));
			forward = ERROR;
		}
		else
		{
			//��������������ҵ��
			if (!getTerminalInfoPO().getRegion().equals(customer.getRegionID()))
			{
	            setErrormessage("�ݲ���������������ҵ��");
	            
	            // ��¼��־:�ֻ����롢ҵ�����͡�ҵ����ˮ�š�ҵ����á�״̬��0���ɹ���1:��������
	            this.createRecLog(servnumber, "reissueCard", "0", "0", "1", "�ݲ���������������ҵ��");
				forward = ERROR;
			}
			
			//���û���Ϣ������session
			else
			{
				//�������session
				removeUserSession();
				
                //���µ��û���Ϣ�����session��
                this.getRequest().getSession().setAttribute(Constants.USER_INFO, customer);
			}
		}
		
		
		return forward;
	}
	
	/**
	 * <����ʱ��¼sh_rec_log��־>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String addRecLog()
	{
		this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", errormessage);
		
		return ERROR;
	}
	
	/**
	 * <��ʾ��ȡ�����֤��Ϣ>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String certShow()
	{
		return "certShow";
	}
	
	/**
	 * <У���û����ֻ���������֤��Ϣ��У���û��Ĳ�������>
	 * <������ϸ����>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public void checkIdCardAndReissueNum()
	{
        try
        {
            //У���û������֤��Ϣ���ֻ������Ƿ����
            reissueCardBean.checkReissueIdCard(getIdCardPO().getIdCardNo(), getCustomerSimp().getServNumber(), 
            		curMenuId, getTerminalInfoPO());
            
            //��ѯ�û���Ϣ
            String subscriber = reissueCardBean.getSubscriberByTel(getCustomerSimp().getServNumber(), 
            		curMenuId, getTerminalInfoPO());
            
            //��������У��
        	reissueCardBean.checkReissueNum(getCustomerSimp().getServNumber(), 
        			curMenuId, getTerminalInfoPO(), subscriber);
        	
        	//У�鲹������
        	checkReissueNum();
        	
        	writeXmlMsg("0");
        }
        catch (ReceptionException e)
        {
           logger.error("У���û����ֻ���������֤��Ϣ����������ʧ��", e);
           writeXmlMsg("1~~"+e.getMessage());
        }
	}
	
	/**
	 * <У���û���������>
	 * <�����ݿ���û�����ò��������������õĲ������������֣��򲻽��д���У��>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void checkReissueNum()
	{
		//��ѯ���õ�ÿ��������ಹ������
		if (StringUtils.isBlank(CommonUtil.getParamValue("sh_reissueCard_num")))
		{
			return;
		}
		
		int limitNum;
		
		try 
		{
			limitNum = Integer.parseInt(CommonUtil.getParamValue("sh_reissueCard_num"));
		} 
		catch (NumberFormatException e) 
		{
			logger.error("�û�����������ѯ����", e);
			return;
		}
		
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//��ǰ�µĵ�һ��
		cardBusiLog.setCreateDate(getFirstDay("yyyyMMdd"));
		
		//��ǰ�µ����һ��
		cardBusiLog.setStatusDate(getLastDay("yyyyMMdd"));
		
		//�û�����
		cardBusiLog.setRegion(getCustomerSimp().getRegionID());
		
		//�ֻ�����
		cardBusiLog.setServnumber(getCustomerSimp().getServNumber());
		
		//�����Ѿ������Ĵ���
		int usedNum = getCardBusiService().getReissueCardNum(cardBusiLog);
		
		//�û����²�����������
		if (usedNum >= limitNum)
		{
			throw new ReceptionException("�û��ѳ������²����������ƣ���ʱ�޷�����");
		}
		
	}
	
	/**
	 * <��ȡ��ǰ�µĵ�һ��>
	 * <������ϸ����>
	 * @param dateFormat
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private String getFirstDay(String dateFormat)
	{
		Calendar cale = null;
	    SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
	    // ��ȡǰ�µĵ�һ�� 
	    cale = Calendar.getInstance(); 
	    cale.add(Calendar.MONTH, 0); 
	    cale.set(Calendar.DAY_OF_MONTH, 1); 
	    
	    return format.format(cale.getTime()); 
	}
	
	/**
	 * <һ�仰���ܼ���>
	 * <������ϸ����>
	 * @param dateFormat
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private String getLastDay(String dateFormat)
	{
		Calendar cale = null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
		
		// ��ȡǰ�µ����һ�� 
	    cale = Calendar.getInstance(); 
	    cale.add(Calendar.MONTH, 1); 
	    cale.set(Calendar.DAY_OF_MONTH, 0); 
	    return format.format(cale.getTime()); 
	}
	
	/**
	 * <�հ׿���Դ���У��>
	 * <����հ׿��Ƿ���ã������Ԥռ�ÿհ׿���Դ>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public void authBlankCard()
	{
		try 
		{
			//�ն˻���Ϣ
			TerminalInfoPO termInfo = getTerminalInfoPO();
			
			//�հ׿���ԴУ��
			cardInstallBean.chkBlankNo(termInfo, getCurMenuId(), 
					getCardBusiLogPO().getBlankCard());
			
			//У���Ƿ�ΪԤ�ƿտ�
			cardInstallBean.chkPreSetBlankCard(termInfo, curMenuId, cardBusiLogPO.getBlankCard(), 
					getCustomerSimp().getServNumber());
			
			//�հ׿���ԴԤռ
			SimInfoPO simInfo = cardInstallBean.blankCardTmpPick(termInfo, getCurMenuId(), 
					cardBusiLogPO.getBlankCard(), getCustomerSimp().getServNumber());
			
			writeXmlMsg("0~~"+simInfo);
		} 
        catch (ReceptionException e)
        {
           logger.error("�հ׿���ԴУ��ʧ�ܣ�", e);
           writeXmlMsg("1~~"+e.getMessage());
        }
	}
	
	/**
	 * <�������>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String feeConfirm()
	{
		String forward = "feeConfirm";
		
		try 
		{
			//�������
			Map<String,Object>  map = reissueCardBean.countReissueFee(getCustomerSimp().getServNumber(), 
					simInfoPO.getIccid(), cardBusiLogPO.getBlankCard(), curMenuId, getTerminalInfoPO());
			
			//������ϸ
			feeList = (List<FeeConfirmPO>)map.get("feeList");
			
			//�ܷ��� Ԫ
			recFee = (String)map.get("recFee");
			
			//��������Ϊ0������Ҫ���ѣ�ֱ��д��
			if(isZero(recFee))
			{
				//��������
				recFee = "0";
				
				//��Ӳ���ҵ����־
				addCardBusiLog();
				
				//ֱ�Ӽ�¼������־
				addFreeLog();
				
				//��Ҫд���������γ�ʱ������ҳ����
				this.getRequest().setAttribute("telProdFlag", "1");
			}
		} 
		catch (ReceptionException e) 
		{
            //������Ϣ
            setErrormessage(e.getMessage());
            
            // ��¼��־:�ֻ����롢ҵ�����͡�ҵ����ˮ�š�ҵ����á�״̬��0���ɹ���1:��������
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", e.getMessage());
            
			forward = ERROR;
		}
		
		return forward;
	}
	
	/**
	 * <���Ӳ���������־>
	 * <������ϸ����>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void addCardBusiLog()
	{
		//��־id
		cardBusiLogPO.setOid(this.getCardBusiService().getInstallId());
		
        // �ն˺�
        cardBusiLogPO.setTermId(getTerminalInfoPO().getTermid());
        
        // ��������reissueCard ����
        cardBusiLogPO.setRectype("reissueCard");
		
		//֤������
		cardBusiLogPO.setCertId(idCardPO.getIdCardNo());
		
		//�û�����
		cardBusiLogPO.setCustName(idCardPO.getIdCardName());
		
		//iccid
		cardBusiLogPO.setIccid(simInfoPO.getIccid());
		
		//imsi
		cardBusiLogPO.setImsi(simInfoPO.getImsi());
		
		//smsp
		cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
		
        //�Ա�
        cardBusiLogPO.setSex(idCardPO.getIdCardSex());
		
		//סַ
		cardBusiLogPO.setLinkAddr(idCardPO.getIdCardAddr());
		
		//�ֻ�����
		cardBusiLogPO.setServnumber(getCustomerSimp().getServNumber());
		
		//�ֻ�����subsid
		cardBusiLogPO.setSubsId(getCustomerSimp().getSubsID());
		
		//����
		cardBusiLogPO.setRegion(getTerminalInfoPO().getRegion());
		
		//��������
		cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(recFee));
		
		// Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
		cardBusiLogPO.setWriteCardStatus("2");
      
		// Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
		cardBusiLogPO.setPayStatus("2");
		
		// ����״̬ Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
		cardBusiLogPO.setInstallStatus("2");
		
		// Ĭ��2����ʼ״̬ 0���˿�ɹ� 1���˿�ʧ��
		cardBusiLogPO.setRefundment("2");
		
		// ��ע
		cardBusiLogPO.setNotes("����������ȷ�ϣ���ʼ�ɷ�");
		this.getCardBusiService().addLogInstall(cardBusiLogPO);
	}
	
	/**
	 * <��������Ϊ0ʱ��¼��־>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void addFreeLog()
	{
		String logOID = feeChargeService.getChargeLogOID();
		
		//������־id���벹����־chargeid����
		cardChargeLogVO.setChargeLogOID(logOID);
		
		//�ն˵���
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        
        //�ն�id
        cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
        
        //�ն˻�����Աid
        cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
        
        //�ֻ�����
        cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        
        //���������
        cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
        
        //��֯�ṹid
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        //���ѽ��
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(recFee));
        
        //����״̬ ��ʼ״̬:11
        cardChargeLogVO.setStatus("10");
        
        //���ѷ�ʽ������Ϊ0ʱ����Ϊ�ֽ�ɷ�4���������
        cardChargeLogVO.setPayType(Constants.PAYBYMONEY);
        
        //����ʱ��
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        //ҵ������
        cardChargeLogVO.setRecType("reissueCard");

        //����
        cardChargeLogVO.setDescription("��������Ϊ0�����轻��");
		
		// ����ɷ���־
		feeChargeService.addChargeLog(cardChargeLogVO);
		
		// ���²���������־
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//��־id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
		//����������־
		cardBusiLog.setChargeId(logOID);
		
		//��������
		cardBusiLog.setChargeType(Constants.PAYBYMONEY);
		
		// Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
		cardBusiLog.setPayStatus("0");
		
		//ʵ�ʽɷѽ��
		cardBusiLog.setToFee(CommonUtil.yuanToFen(recFee));
		
		this.getCardBusiService().updateInstallLog(cardBusiLog);
	}

	/**
	 * <��ȡsim���������ݲ�չʾ���ʽ>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String selectPayType()
	{
		String forward = "selectType";
		
		//����������־
		addCardBusiLog();
		
	    // ��ȡ���õĳ�ֵ��ʽ
	    usableTypes = getPayType(getTerminalInfoPO().getTermgrpid());
	    
	    // findbugs�޸� 2015-09-02 zKF66389
	    //logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
	    // findbugs�޸� 2015-09-02 zKF66389
	    
	    //���鲻�����õĳ�ֵ��ʽ���򱨴�
	    // findbugs�޸� 2015-09-02 zKF66389
	    //if (usableTypes == null || usableTypes.size() == 0)
	    if (usableTypes.size() == 0)
	    // findbugs�޸� 2015-09-02 zKF66389
	    {
	        // û�п��õĳ�ֵ��ʽ
	        setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
	        
	        // ��¼��־
	        this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", "��ʱû�п��õĳ�ֵ��ʽ");
	        
	        forward = ERROR;
	    }
		
		return forward;
	}
	
	/**
	 * <�ֽ�ɷ�>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String cashPay()
	{
		//���γ�ʱ������ҳ����
		this.getRequest().setAttribute("telProdFlag", "1");
		
		return "cashPay";
	}
	
	/**
	 * <�������ɷѶ���ҳ��>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String unionCardPay()
	{
		return "readCard";
	}
	
	/**
	 * <��������������>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String inputCardPwd()
	{
		return "inputCardPwd";
	}
	
	/**
	 * <��������ȷ��>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String makeSure()
	{
		//���γ�ʱ������ҳ����
		this.getRequest().setAttribute("telProdFlag", "1");
		
		return "makeSure";
	}
	
	/**
	 * <�����ۿ�֮ǰ���ӽ�����־>
	 * <������ϸ����>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public void addUnionCardLog()
	{
		try 
		{
			String logOID = feeChargeService.getChargeLogOID();
			
			//������־id���벹����־chargeid����
			cardChargeLogVO.setChargeLogOID(logOID);
			
			//�ն˵���
            cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            
            //�ն�id
            cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            
            //�ն˻�����Աid
            cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            
            //��֯��������
            cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
            
            //�ֻ�����
            cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            
            //����
            cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
            //֧����ʽ
            cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
            //���ѽ��
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(recFee));
            
            //���ܵ�����������
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardChargeLogVO.getCardnumber()));
            
            //����״̬ ��ʼ״̬:00
            cardChargeLogVO.setStatus("00");
            
            //����ʱ��
            cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            
            //ҵ������
            cardChargeLogVO.setRecType("reissueCard");

            //����
            cardChargeLogVO.setDescription("�����ۿ�֮ǰ���ӽ�����־");
			
			// ����ɷ���־
			feeChargeService.addChargeLog(cardChargeLogVO);
			
			// ���²���������־
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//��־id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
			//����������־
			cardBusiLog.setChargeId(logOID);
			
			//��������
			cardBusiLog.setChargeType(Constants.PAYBYUNIONCARD);
			
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			writeXmlMsg("0~~" + logOID);
		} 
		catch (Exception e) 
		{
			logger.error("�ۿ�Ǯ��ӽ�����־�͸��²�����־�쳣��", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <�����ۿ�ɹ�֮����½�����־>
	 * <������ϸ����>
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public void updateUnionCardLog()
	{
        try 
        {
        	//�������صĽ�������
			String busitype = cardChargeLogVO.getBusiType();
			
			cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(busitype, "UTF-8"));
			
			//�����ۿ����
			String unionpayfee = cardChargeLogVO.getUnionpayfee();
			
			// modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
            unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
            // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
			
			//�����ۿ���� ��λ ��
			cardChargeLogVO.setUnionpayfee(unionpayfee);
			
			//01 ����ɹ�
			cardChargeLogVO.setStatus("01");
			
			//������Ϣ
			cardChargeLogVO.setDescription("�����ۿ�ɹ�");
			
			//����
			cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
			
			//����������
			String initPosResCode = cardChargeLogVO.getPosResCode();
			cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);

			//��������������־
			feeChargeService.updateCardChargeLog(cardChargeLogVO);
			
			// ---------------���²���������־----------------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//��־id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
	        //ʵ�ʽɷѽ��
	        cardBusiLog.setToFee(unionpayfee);
			
	        // Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
			cardBusiLog.setPayStatus("0");
			
			//������Ϣ
			cardBusiLog.setNotes("�ɷѳɹ�");
	        
			//���²���������־
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			writeXmlMsg("0");
		} 
        catch (Exception e) 
        {
			logger.error("�ۿ�֮�������־ʧ��", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <����������Ϊ0ʱд��ʧ�ܵ����>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String goFreeWriteError()
	{
		// ---------------���²���������־----------------
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//��־id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
        // Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
		cardBusiLog.setPayStatus("0");
		
		//д��״̬
		cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
		
		//������Ϣ
		cardBusiLog.setNotes("��������Ϊ0��д��ʧ��");
        
		//���²���������־
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
        // ��¼��־
        this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", errormessage);
		
		return ERROR;
	}
	
	/**
	 * <�����ֽ�ɷ��쳣>
	 * <������ϸ����>
	 * @return	 
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String goCashError()
	{
        try
        {
        	//��¼sh_rec_log��־
            this.createRecLog(getCustomerSimp().getServNumber(), payType, "0", "0", "1", errormessage);
            
            //�ն˻�
            TerminalInfoPO termInfoPO = getTerminalInfoPO();
            
            //----------------���½�����־-----------------
            //֧������
            cardChargeLogVO.setPayType(payType);
            
            //�ֽ�ɷ���ˮ��
            if (StringUtils.isNotBlank(cardChargeLogVO.getTerminalSeq()))
            {
                String terminalSeq = getTerminalInfoPO().getTermid() + cardChargeLogVO.getTerminalSeq();
                if (terminalSeq.length() > 20)
                {
                    terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
                }
                
                cardChargeLogVO.setTerminalSeq(terminalSeq);
            }
            else
            {
            	cardChargeLogVO.setTerminalSeq("");
            }

            //�ж��û���Ͷ�ҽ��
            if (isZero(tMoney))
            {
            	//δͶ��
            	cardChargeLogVO.setStatus("00");
            }
            else
            {
            	//��Ͷ��
            	cardChargeLogVO.setStatus("10");
            }
            
            //�ɷ���־id
            String chargeLogId = feeChargeService.getChargeLogOID();
            
            //�ɷ���־id
        	cardChargeLogVO.setChargeLogOID(chargeLogId);
        	
        	//�ն˵���
        	cardChargeLogVO.setRegion(termInfoPO.getRegion());
        	
        	//�ն˱��
        	cardChargeLogVO.setTermID(termInfoPO.getTermid());
        	
        	//�ն˲���Ա
        	cardChargeLogVO.setOperID(termInfoPO.getOperid());
        	
        	//��֯��������
        	cardChargeLogVO.setOrgID(termInfoPO.getOrgid());
        	
        	//�ֻ�����
        	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        	
        	//�ֻ����������
        	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
        	//�ɷѽ�� ��
        	cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            
        	//����ʱ��
        	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        	
        	//������Ϣ
        	cardChargeLogVO.setDescription(errormessage);
        	
        	//ҵ������
        	cardChargeLogVO.setRecType("reissueCard");
            
        	//���ӽ�����־
            feeChargeService.addChargeLog(cardChargeLogVO);
            
			//--------���²���������־--------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//��־id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
			//����������־
			cardBusiLog.setChargeId(chargeLogId);
			
			//д��״̬
			cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
			
			//֧������
			cardBusiLog.setPayStatus(cardBusiLogPO.getPayStatus());
			
			//ʵ�շ���
			cardBusiLog.setToFee(CommonUtil.yuanToFen(tMoney));
			
			//��������
			cardBusiLog.setChargeType(payType);
			
			//������Ϣ
			cardBusiLog.setNotes(errormessage);
			
			this.getCardBusiService().updateInstallLog(cardBusiLog);

        }
        
        //�����쳣�������̿������
        catch (Exception e)
        {
            logger.error("��¼�ۿ������־�쳣��",e);
            errormessage = errormessage + e.getMessage();
        }
        
		return ERROR;
	}

	/**
	 * <�����Ѻ�д���쳣>
	 * <������ϸ����>
	 * @return	 
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String goCardError()
	{
        try
        {
        	//��¼������־
            this.createRecLog(getCustomerSimp().getServNumber(), payType, "0", "0", "1", errormessage);
            
            //�������Ѻ�д���쳣����
            if("1".equals(cardBusiLogPO.getWriteCardStatus()))
            {
    			//--------���²���������־--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			//��־id
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//д��״̬
    			cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
    			
    			//������Ϣ
    			cardBusiLog.setNotes(errormessage);
    			
    			//������־
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
    			
    			return ERROR;
    			
            }
            
            //�����������Ѻ��ֽ�ɷ���־
            if (StringUtils.isEmpty(errorType) || "add".equalsIgnoreCase(errorType))
            {
            	//������־id
            	String chargeLogId = feeChargeService.getChargeLogOID();
            	cardChargeLogVO.setChargeLogOID(chargeLogId);
            	
            	//��������
            	cardChargeLogVO.setPayType(payType);
            	
            	//������ˮ
                cardChargeLogVO.setTerminalSeq("");

                //������
                cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
                
                //�жϿۿ���
                if (isZero(tMoney))
                {
                	//δ����
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	//�ѽ���
                	cardChargeLogVO.setStatus("10");
                }
                
            	//���ѽ��
            	cardChargeLogVO.setFee(tMoney);
            	
            	//�ն˵���
            	cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            	
            	//�ն˱��
            	cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            	
            	//�ն˲���Ա
            	cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            	
            	//��֯��������
            	cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
            	
            	//�ֻ�����
            	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            	
            	//�û�������
            	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());

            	//����ʱ��
            	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            	
            	//������Ϣ
            	cardChargeLogVO.setDescription(errormessage);
            	
            	//ҵ������
            	cardChargeLogVO.setRecType("reissueCard");
                
            	//�����־
                feeChargeService.addChargeLog(cardChargeLogVO);
                
    			//--------�������²���������־--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			//��־id
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//ʵ�ʽɷѽ��
    			cardBusiLog.setToFee(tMoney);
    			
    			//����״̬
    			cardBusiLog.setPayStatus(cardBusiLogPO.getPayStatus());
    			
    			//����������־
    			cardBusiLog.setChargeId(chargeLogId);
    			
    			//��������
    			cardBusiLog.setChargeType(payType);
    			
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
            }
            
            // ���������ۿ���־��־
            else
            {
            	//���ѽ��Ϊ0
                if (isZero(tMoney))
                {
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	cardChargeLogVO.setStatus("10");
                }
                
                //������Ϣ
                cardChargeLogVO.setDescription(errormessage);
                
                //�����ն˺�
                cardChargeLogVO.setUnionpaycode(getTerminalInfoPO().getUnionpaycode());
                
                //�����̻���
                cardChargeLogVO.setUnionpayuser(getTerminalInfoPO().getUnionuserid());
                
                feeChargeService.updateChargeLog(cardChargeLogVO);
            }
        }
        
        //�����쳣�������̿������
        catch (Exception e)
        {
            logger.error("��¼�ۿ������־�쳣��",e);
            errormessage = errormessage + e.getMessage();
        }
        
		return ERROR;
	}
	
	/**
     * ��ֹ�û��ظ��ɷ�
     * 
     * @param termInfo
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private boolean checkRepeatCash()
    {
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
        String seq = termInfo.getTermid().concat(cardChargeLogVO.getTerminalSeq());
        
        String tmpSeq = seq.concat(getCustomerSimp().getServNumber());
        
        // �������ͬ�Ĵ��������ظ��ɷ�
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            String tmpErrorMsg = "�ظ��ɷ�:�ֻ���[".concat(getCustomerSimp().getServNumber())
                    .concat("],Ͷ�ҽ��[")
                    .concat(tMoney)
                    .concat("]Ԫ,����Ӫҵ��[")
                    .concat(termInfo.getOrgname())
                    .concat("],��ˮ��[")
                    .concat(seq)
                    .concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(getCustomerSimp().getServNumber());
            
            // �ֽ�Ͷ��
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            
            // �ֽ�ɷ���ˮ,�ն�id+����������ˮ
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(DateUtilHub.getCurrentDateTime());
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // ��¼�ظ��ɷ���־
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // ��¼��SH_REC_LOG,������ˮ�������¼�ֽ�ɷ���ˮ��
            this.createRecLog(getCustomerSimp().getServNumber(), Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
        
    }
	
	/**
	 * <�����ύ>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String reissueCommit()
	{
		String forward = SUCCESS;
		
		//���ѽ��ת�������ֽ�ɷѣ�tMoney��λΪԪ����ת���ɷ֣��������ѣ����ص�tMoney��λΪ�֣�����ת��
		String commitMoney = Constants.PAYBYMONEY.equals(payType) ? CommonUtil.yuanToFen(tMoney) : tMoney;
		
		//�ɷ���־id�����ڸ��½���״̬
		String chargeLogOID = Constants.PAYBYMONEY.equals(payType) ? 
				feeChargeService.getChargeLogOID() : cardChargeLogVO.getChargeLogOID();
		
		//�ֽ�ɷ��ύʱ�������ֽ𽻷���־
		if (Constants.PAYBYMONEY.equals(payType))
		{
	        //��ֹ�ֽ��ظ��ɷ�
	        if(!checkRepeatCash())
	        {
	            setErrormessage("������ˮ���ظ����ظ��ɷѣ�");
	            
	            return ERROR;
	        }

	        // �ն���ˮ(�ն�id+�ֽ�ɷ���ˮ ��ȡ��20λ)
	        String terminalSeq = "";
	        
	        if(StringUtils.isNotEmpty(cardChargeLogVO.getTerminalSeq()))
	        {
		        terminalSeq = getTerminalInfoPO().getTermid() + cardChargeLogVO.getTerminalSeq();
		        
		        if (terminalSeq.length() > 20)
		        {
		            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
		        }
	        }
	        
	        //�ֽ�ɷ���ˮ
	        cardChargeLogVO.setTerminalSeq(terminalSeq);
	        
	        // ��װ����
	        cardChargeLogVO.setChargeLogOID(chargeLogOID);
	        
	        //����
	        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
	        
	        //�ն˱��
	        cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
	        
	        //����Ա
	        cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
	        
	        //�ֻ�����
	        cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
	        
	        //�ɷѷ�ʽ
	        cardChargeLogVO.setPayType(payType);
	        
	        //�ɷѽ��
	        cardChargeLogVO.setFee(commitMoney);
	        
	        //����ʱ��
	        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
	        
	        //������
	        cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
	        
	        //��֯��������
	        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
	        
	        //�ɷ�״̬ �ɹ��ɷ�
	        cardChargeLogVO.setStatus("10");
	        
	        //������Ϣ
	        cardChargeLogVO.setDescription("�����ɷѳɹ�");
	        
	        //ҵ������
	        cardChargeLogVO.setRecType("reissueCard");
	        
	        // ��ӽɷ���־
	        feeChargeService.addChargeLog(cardChargeLogVO);
	        
	        //-------------------����ҵ��������־-----------------------
	        cardBusiLogPO.setChargeId(chargeLogOID);
	        
	        //�ɷ�����
	        cardBusiLogPO.setChargeType(payType);
	        
	        //ʵ�ʽɷѽ��
	        cardBusiLogPO.setToFee(commitMoney);
			
	        // Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
			cardBusiLogPO.setPayStatus("0");
			
	    	// Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
			cardBusiLogPO.setWriteCardStatus("0");

			//������Ϣ
			cardBusiLogPO.setNotes("�ɷѳɹ�");
	        
	    	this.getCardBusiService().updateInstallLog(cardBusiLogPO);
		}
		
		// �ύ�����ҵ��������־
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//��־id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
		//ʵ�ʽɷѽ�� ��λ����
		cardBusiLog.setToFee(commitMoney);
		
		// Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
		cardBusiLog.setWriteCardStatus("0");
		
		try
		{
			//д���ɹ��ӿ�
			simInfoPO.setWriteResult("1");
			simInfoPO.setErrCode("0");
			simInfoPO.setErrMsg("д���ɹ�");
			
			cardInstallBean.updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);

			//��������Ϊ0ʱ�����ѷ�ʽ���ֽ�ɷ� 4���������
			if(isZero(commitMoney))
			{
				payType = Constants.PAYBYMONEY;
			}
			
			//�����ύ
			String recOid = reissueCardBean.reissueCommit(getCustomerSimp().getServNumber(), curMenuId, commitMoney, 
					payType, cardBusiLogPO.getBlankCard(), simInfoPO, getTerminalInfoPO());

			//����ҳ����ʾ����λ Ԫ
			tMoney = CommonUtil.fenToYuan(commitMoney);
			
			//----------���½�����־״̬----------
			CardChargeLogVO cardChargeLog = new CardChargeLogVO();
			
			cardChargeLog.setChargeLogOID(chargeLogOID);
			
			cardChargeLog.setStatus("11");
			
			cardChargeLog.setDescription("���ѳɹ���ҵ�����ɹ�");
			
			this.getCardBusiService().updateCardChargeLog(cardChargeLog);
			//----------���½�����־����----------
			
			//������ˮ�ţ���ӡƾ��ʱ�õ�
			cardBusiLogPO.setFormnum(recOid);

			// ����״̬ Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
			cardBusiLog.setInstallStatus("0");
			
			//ҵ����ˮ��
			cardBusiLog.setFormnum(recOid);
			
			//������Ϣ
			cardBusiLog.setNotes("�����ɹ�");
			
            // ��¼��־
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", recOid, commitMoney, "0", "�����ɹ�");	
    	
		}
		catch(ReceptionException e)
		{
			logger.error("�����ύʧ�ܣ�", e);
			
			// ����״̬ Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
			cardBusiLog.setInstallStatus("1");
			
			//������Ϣ
			cardBusiLog.setNotes(e.getMessage());
			
			//������Ϣ
			setErrormessage(e.getMessage());
			
            // ��¼��־
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", commitMoney, "1", e.getMessage());
			
			try
			{
				//д��ʧ�ܽӿ�
				simInfoPO.setWriteResult("-1");
				simInfoPO.setErrCode("-1");
				simInfoPO.setErrMsg(e.getMessage());
				getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
			}
			catch(ReceptionException ex)
			{
				logger.error("д��ʧ�ܽӿ�ʧ�ܣ�", ex);
			}
            
            forward = ERROR;
		}
		
		//�����ύ�������־
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		return forward;
	}

	/**
	 * <�жϷ����Ƿ�Ϊ0>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private boolean isZero(String fee)
	{
		boolean res = false;
		
		//��Ϊ�գ��򷵻�true
		if (StringUtils.isBlank(fee))
		{
			res = true;
		}
		else
		{
			//�ж�Ӧ�շ����Ƿ�Ϊ0����Ϊ0����������ҳ��
			BigDecimal zero = new BigDecimal("0");
			res = (0 == zero.compareTo(new BigDecimal(fee)) ? true : false);
		}
		
		return res;
	}
	
	public String getServnumber() 
	{
		return servnumber;
	}

	public void setServnumber(String servnumber) 
	{
		this.servnumber = servnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public NserCustomerSimp getCustomer() {
		return customer;
	}

	public void setCustomer(NserCustomerSimp customer) {
		this.customer = customer;
	}

	public ReissueCardBean getReissueCardBean() {
		return reissueCardBean;
	}

	public void setReissueCardBean(ReissueCardBean reissueCardBean) {
		this.reissueCardBean = reissueCardBean;
	}

	public List<FeeConfirmPO> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<FeeConfirmPO> feeList) {
		this.feeList = feeList;
	}

	/**
	 * @return ���� usableTypes
	 */
	public List getUsableTypes() {
		return usableTypes;
	}

	/**
	 * @param ��usableTypes���и�ֵ
	 */
	public void setUsableTypes(List usableTypes) {
		this.usableTypes = usableTypes;
	}

	/**
	 * @return ���� tMoney
	 */
	public String getTMoney() {
		return tMoney;
	}

	/**
	 * @param ��tMoney���и�ֵ
	 */
	public void setTMoney(String money) {
		tMoney = money;
	}

	/**
	 * @return ���� payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param ��payType���и�ֵ
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return ���� feeChargeService
	 */
	public FeeChargeHubService getFeeChargeService() {
		return feeChargeService;
	}

	/**
	 * @param ��feeChargeService���и�ֵ
	 */
	public void setFeeChargeService(FeeChargeHubService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	/**
	 * @return ���� cardChargeLogVO
	 */
	public CardChargeLogVO getCardChargeLogVO() {
		return cardChargeLogVO;
	}

	/**
	 * @param ��cardChargeLogVO���и�ֵ
	 */
	public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO) {
		this.cardChargeLogVO = cardChargeLogVO;
	}

	/**
	 * @return ���� errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param ��errorType���и�ֵ
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return ���� needReturnCard
	 */
	public String getNeedReturnCard() {
		return needReturnCard;
	}

	/**
	 * @param ��needReturnCard���и�ֵ
	 */
	public void setNeedReturnCard(String needReturnCard) {
		this.needReturnCard = needReturnCard;
	}

	/**
	 * @return ���� printcontext
	 */
	public String getPrintcontext() {
		return printcontext;
	}

	/**
	 * @param ��printcontext���и�ֵ
	 */
	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}
	
	
}
