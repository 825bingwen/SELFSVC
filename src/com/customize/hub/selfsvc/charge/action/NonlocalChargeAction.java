package com.customize.hub.selfsvc.charge.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.gmcc.boss.selfsvc.bean.NonlocalChargeBean;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.ChargeService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;
/**
 * 
 * <��ʡ��ؽɷ�>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Mar 23, 2015]
 * @see  [�����/����]
 * @since  [OR_NX_201501_1030 ��ʡ��ؽɷ�]
 */
public class NonlocalChargeAction extends BaseAction
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��־
     */
    private static Log logger = LogFactory.getLog(NonlocalChargeAction.class);
    
    /**
     * Ͷ�ҽ��
     */
    private String tMoney;
    
    /**
     * ������Ϣ
     */
    private String errormessage;
    
    /**
     * ��������
     */
    private String errorType;
    
    /**
     * �Ƿ���Ҫ�˿���1����Ҫ
     */
    private String needReturnCard;
    
    /**
     * Ӧ�ɷ��� Ԫ
     */
    private String yingjiaoFee;
    
    /**
     * ������ӡ��Ϣ
     */
    private String printcontext;
    
    /**
     * ����һ��boss bean
     */
    private transient NonlocalChargeBean nonlocalChargeBean;
    
    /**
     * ��¼�ظ��ɷ���־
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
     * �˵�id
     */
    private String curMenuId = "";
    
	/**
	 * �ɷ���־��Ϣ��
	 */
    private transient CardChargeLogVO cardChargeLogVO;
	
    /**
     * ��ֵ��־��¼
     */
    private transient ChargeService chargeService;
    
    /**
     * ���ýɷѷ�ʽ�б�
     */
    private List usableTypes;
    
    /**
     * ���ѳ�ֵ������Ҫ�����֤��������Ҫ����������룬�Ա�֤��ֵ������ȷ
     * 
     * @return
     */
    public String inputNumber()
    {
        this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
        return "inputNumber";
    }
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ���˻���Ϣ+֧����ʽ��
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {
        logger.debug("qryfeeChargeAccount start");
        
        // ����˻���Ϣ��ѯʧ��ת���ɷѵ�¼ҳ��
        String forward = ERROR;
        
        try
        {
        	//�ն˻���Ϣ
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//����һ��boss�ӿڣ���ѯӦ�ɻ���
            Map<String,String> qryResult = nonlocalChargeBean.qryPayAmount(curMenuId, termInfo, 
            		cardChargeLogVO.getServnumber());
                
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // �ֻ�����
            customerSimp.setServNumber(cardChargeLogVO.getServnumber());
            
            // �ͻ����� ģ����
            customerSimp.setCustomerName(CommonUtil.blurNameHUB(qryResult.get("CustName")));
            
            // ����SESSION
            this.getRequest().getSession().setAttribute(Constants.USER_INFO, customerSimp);
            
            //Ӧ������ Ԫ
            cardChargeLogVO.setFee(CommonUtil.fenToYuan(qryResult.get("PayAmount")));
            
            //ʡ�ݱ���
            cardChargeLogVO.setProvinceCode(qryResult.get("ProvinceCode"));
            
            //��ֵ��ʽ
            usableTypes = CommonUtil.qryUsablePayTypes(termInfo.getTermgrpid(), curMenuId);
            
            //û�п��õĳ�ֵ��ʽ
            if (null == usableTypes || usableTypes.size() == 0)
            {
                // û�п��õĳ�ֵ��ʽ
                setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
                
                // ��¼��־
                this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_FALID, "��ʱû�п��õĳ�ֵ��ʽ");
            }
            else
            {
                // ��¼�ɹ���־
                this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_SUCCESS, "��ѯ���ѷ�ʽ�ɹ�");
                forward = SUCCESS;
            }
        }
        catch (ReceptionException e)
        {
            logger.error("��ѯӦ�ɷ���ʧ�ܣ�",e);
            setErrormessage(e.getMessage());
            
            // ��¼�쳣��־
            this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", Constants.RECSTATUS_FALID, e.getMessage());
        }
        
        return forward;
    }
    
    /**
     * ת��Ͷ��ҳ��
     * 
     * @return
     */
    public String cashCharge()
    {
        return "cashChargePage";
    }
    
    /**
     * ���ѳ�ִֵ���ֽ�ɷ�
     * 
     * @return
     */
    public String chargeCommit()
    {
        logger.debug("ChargeCommit start");
        
        String forward = ERROR;
        
    	//�ն˻���Ϣ
    	TerminalInfoPO termInfo = this.getTerminalInfoPO();
    	
    	//��־id
    	String logOID = "";
        
    	//��Ϊ�ֽ�ɷѣ���¼�ֽ�ɷ���־
    	if (Constants.PAY_BYCASH.equals(cardChargeLogVO.getPayType()))
    	{
    		// �趨�ɷѷ�ʽ
    	    if(!feeChargeService.checkCashFee(termInfo, cardChargeLogVO.getServnumber(), 
    	    		CommonUtil.yuanToFen(tMoney), cardChargeLogVO.getTerminalSeq()))
    	    {
                //��¼��SH_REC_LOG,������ˮ�������¼�ֽ�ɷ���ˮ��
    	    	this.createRecLog(cardChargeLogVO.getServnumber(), 
    	    			Constants.BUSITYPE_NONLOCAL_CHARGE, cardChargeLogVO.getTerminalSeq(), 
    	    			CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_FALID, "������ʡ�ظ��ɷѣ�");
    	    	
    	        return "repeatError";
    	    }
            
            // ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
    	    logOID = chargeService.addCashLog(cardChargeLogVO.getServnumber(),CommonUtil.yuanToFen(tMoney) , 
    	    		cardChargeLogVO.getTerminalSeq(), cardChargeLogVO.getAcceptType(), 
    	    		Constants.PROVINCE_REGION_999, 
    	    		Constants.PAYSUCCESS_CHARGEERROR, "����֮ǰ��¼Ͷ����־", Constants.BUSITYPE_NONLOCAL_CHARGE, "",
    	    		cardChargeLogVO.getProvinceCode(), termInfo);
    	}
    	else
    	{
    		//��Ϊ�����ɷѣ�����Ҫ��תΪԪ������ҳ����ʾ�����ֽ𽻷�ͳһ
    		tMoney = CommonUtil.fenToYuan(tMoney);
    		logOID = cardChargeLogVO.getChargeLogOID();
    	}
	    
	    //���ɽ�����ˮ��
        String dealnum = chargeService.getNonlocalChargeSeq();
        cardChargeLogVO.setDealnum(dealnum);
        
        try 
        {
        	//ֱ�ӵ���һ��BOSS�ӿڽ��н���
        	nonlocalChargeBean.nonlocalCharge(curMenuId, termInfo, cardChargeLogVO.getServnumber(),
        			dealnum, CommonUtil.yuanToFen(tMoney));
			
			//���½���״̬
        	chargeService.updateChargeStatus(logOID, Constants.CHARGE_SUCCESS, "���ѳɹ�", dealnum, "", "", "", termInfo);
			
			// ��¼�ɷѳɹ���־
			this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, dealnum, 
					 CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_SUCCESS, "�ɷ�:�����ն˿�ʡ�ɷѳɹ�!");
			
			forward = SUCCESS;
		} 
        catch (ReceptionException e) 
        {
        	logger.error("��ʡ����ʧ�ܣ�", e);
        	setErrormessage(e.getMessage());
        	
        	//���½�����־
        	chargeService.updateChargeStatus(logOID, Constants.PAYSUCCESS_CHARGEERROR, "���ѿ�ʡ�ɷ�ʧ�ܣ�", dealnum, "", "", "", termInfo);
			
			// ��¼�ɷ�ʧ����־
			this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, dealnum, 
					CommonUtil.yuanToFen(tMoney), Constants.RECSTATUS_FALID, "�ɷ�:�����ն˿�ʡ�ɷ�ʧ��!");
		}
        
        logger.debug("ChargeCommit end");
        
        return forward;
    }
    
    /**
     * �ֽ𽻷��쳣����
     * 
     * @return
     * @see
     */
    public String goCashError()
    {
        logger.debug("goCashError Starting ...");
        
    	//�ն˻���Ϣ
    	TerminalInfoPO termInfo = this.getTerminalInfoPO();
    	
    	//����ҵ����־
    	this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, "", "", 
    	        Constants.RECSTATUS_FALID, errormessage);
    	
        String status = "";
        
        //����״̬
        if (StringUtils.isBlank(tMoney) || "0".equals(tMoney.trim()))
        {
        	status = Constants.CHARGE_ERROR;
        }
        else
        {
        	status = Constants.PAYSUCCESS_CHARGEERROR;
        }
    	
        //��¼������־
        chargeService.addCashLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), 
        		cardChargeLogVO.getTerminalSeq(), "", Constants.PROVINCE_REGION_999, status, errormessage, 
        		Constants.BUSITYPE_NONLOCAL_CHARGE, "", cardChargeLogVO.getProvinceCode(), termInfo);
        
        logger.debug("goCashError End");
        
        return "cashErrorPage";
    }
    
    /**
     * ת�����п��ɷѽ��ѡ��ҳ��
     * 
     * @return
     */
    public String cardCharge()
    {
        return "selectFee";
    }
    
    /**
     * �ֹ�����ɷѽ��
     * 
     * @return
     */
    public String toInputMoney()
    {
        return "toInputMoney";
    }
    
    /**
     * ת�����п��ɷ���������ҳ��
     * 
     * @return
     */
    public String dutyInfo()
    {
        return "dutyInfo";
    }
    
    /**
     * ת������п�ҳ��
     * 
     * @return
     */
    public String toReadCard()
    {
        return "toReadCard";
    }
    
    /**
     * ������������������ҳ��
     * 
     * @return
     * @see
     */
    public String inputCardPwd()
    {
        return "inputPwd";
    }
    
    /**
     * ת��ȷ�����п��ɷѽ��ҳ��
     * 
     * @return
     */
    public String toMakeSure()
    {
        return "makeSure";
    }
    
    /**
     * �ۿ�֮ǰ�����������ɷ���־
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        logger.debug("addCardPayLog Starting...");
        
        try
        {
        	//�ն˻���Ϣ
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
            //��¼����ǰ��־
            String logOID = "";
            logOID = chargeService.addCardLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), 
	            		EncryptDecryptUtil.encryptAesPwd(cardChargeLogVO.getCardnumber()), 
	            		cardChargeLogVO.getTerminalSeq(), cardChargeLogVO.getStatus(), 
	            		cardChargeLogVO.getDescription(), Constants.PROVINCE_REGION_999, cardChargeLogVO.getPosNum(),
	            		cardChargeLogVO.getBatchNumBeforeKoukuan(), 
	            		cardChargeLogVO.getAcceptType(), Constants.BUSITYPE_NONLOCAL_CHARGE, 
	            		cardChargeLogVO.getProvinceCode(), termInfo);
            
            writeXmlMsg("1~~" + logOID);
        }
        catch (Exception e)
        {
        	logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
            writeXmlMsg("0");
            
        }
        
        logger.debug("addCardPayLog end!");
    }
    
    /**
     * �ۿ�ɹ�֮�󣬸��½�����־
     * 
     * @throws Exception
     * @see
     */
    public void updateCardChargeLog() throws Exception
    {
        logger.debug("updateCardChargeLog Starting...");
        
        try
        {
        	//�ն˻���Ϣ
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//�����ۿ��� ��,ȥ���������0
            String unionpayfee = CommonUtil.formatNumberStr(cardChargeLogVO.getUnionpayfee());
            
            //���������ۿ���־
            chargeService.updateCardLog(cardChargeLogVO.getChargeLogOID(), cardChargeLogVO.getUnionpayuser(), 
            		cardChargeLogVO.getUnionpaycode(), cardChargeLogVO.getBatchnum(), 
            		cardChargeLogVO.getAuthorizationcode(), cardChargeLogVO.getBusinessreferencenum(), 
            		cardChargeLogVO.getUnionpaytime(), cardChargeLogVO.getVouchernum(), unionpayfee, 
            		cardChargeLogVO.getPosResCode(), "", "", "", 
            		java.net.URLDecoder.decode(cardChargeLogVO.getBusiType(), "UTF-8"), termInfo);
            writeXmlMsg("1");
        }
        catch (Exception e)
        {
        	logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
        	writeXmlMsg("0");
        }
        
        logger.debug("updateCardChargeLog end!");
    }
    
    /**
     * ȡ�����п��ɷ�
     * 
     * @return
     */
    public String cancelCardCharge()
    {
        return "cancelCardCharge";
    }
    
    /**
     * �����������쳣����
     * 
     * @return
     * @see
     */
    public String goCardError()
    {
        logger.debug("goCardError Starting ...");
        
        try
        {
        	//�ն˻���Ϣ
        	TerminalInfoPO termInfo = this.getTerminalInfoPO();
        	
        	//��¼sh_rec_log��־
            this.createRecLog(cardChargeLogVO.getServnumber(), Constants.BUSITYPE_NONLOCAL_CHARGE, 
            		"0", "0", Constants.RECSTATUS_FALID, errormessage);
            
            //��������־���Ǹ������е���־
            if (StringUtils.isBlank(errorType)|| "add".equalsIgnoreCase(errorType))
            {
            	//����������־
                chargeService.addCardLog(cardChargeLogVO.getServnumber(), CommonUtil.yuanToFen(tMoney), "", "", Constants.CHARGE_ERROR, 
                		errormessage, Constants.PROVINCE_REGION_999, "", "", "", Constants.BUSITYPE_NONLOCAL_CHARGE, 
                		cardChargeLogVO.getProvinceCode(),termInfo);
            }
            
            // ������־
            else
            {
            	String status = (StringUtils.isBlank(tMoney) ? Constants.CHARGE_ERROR : Constants.PAYSUCCESS_CHARGEERROR);
                
                //����������
                String errPosResCode = StringUtils.isBlank(cardChargeLogVO.getPosResCode()) ? "" : cardChargeLogVO.getPosResCode();
                
            	//��������������־
                chargeService.updateChargeStatus(cardChargeLogVO.getChargeLogOID(), status, errormessage, "", 
                		errPosResCode, termInfo.getUnionuserid(), termInfo.getUnionpaycode(), termInfo);
            }
        }
        catch (Exception e)
        {
            // ����һ���쳣,ʹҳ��������˿�ҳ��
            logger.error("���������Ѽ�¼��־�쳣��",e);
            errormessage = errormessage + e.getMessage();
        }
        
        logger.debug("goCardError End");
        
        return "cardErrorPage";
    }
    
    /**
     * <��ֵ���ѵ��ʳ�ֵ��ͽ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getMinMoney()
    {
    	return CommonUtil.getParamValue(Constants.NONLOCAL_CHARGE_MIN, "10");
    }
    
    /**
     * <��ֵ���ѵ��ʳ�ֵ��߽��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getMaxMoney()
    {
    	return CommonUtil.getParamValue(Constants.NONLOCAL_CHARGE_MAX, "2000");
    }
    

    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

	public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getTMoney()
    {
        return tMoney;
    }
    
    public void setTMoney(String money)
    {
        tMoney = money;
    }
    
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }
    
    public String getYingjiaoFee()
    {
        return yingjiaoFee;
    }
    
    public void setYingjiaoFee(String yingjiaoFee)
    {
        this.yingjiaoFee = yingjiaoFee;
    }
    
    public String getPrintcontext()
    {
        return printcontext;
    }
    
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }
    
    public void setNonlocalChargeBean(NonlocalChargeBean nonlocalChargeBean)
    {
        this.nonlocalChargeBean = nonlocalChargeBean;
    }
    
    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CardChargeLogVO getCardChargeLogVO()
    {
        return cardChargeLogVO;
    }
    
    public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO)
    {
        this.cardChargeLogVO = cardChargeLogVO;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public void setChargeService(ChargeService chargeService)
    {
        this.chargeService = chargeService;
    }
	
	
}