package com.customize.sd.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.FeeChargeBean;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.customize.sd.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * ���ѳ�ֵ�ɷ�
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeAction extends BaseAction
{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��־ 
    // modify begin by xkf29026  2011/10/6 ����final
    public static final Log logger = LogFactory.getLog(FeeChargeAction.class);
    // modify end by xkf29026 2011/10/6  ����final
    
    // ��ǰ�˵�
    private String curMenuId = "";
    
    // �û��ֻ���
    private String servnumber;
    
    // ���ѽ��
    private String tMoney;
    
    // �ն˻���ˮ��
    private String terminalSeq;
    
    // ������Ϣ
    private String errormessage;
    
    // ���ýӿ�Bean
    private FeeChargeBean feeChargeBean;
    
    private String servRegion = "";
    
    private String acceptType = "";
    
    // Ӧ�ɷ���
    private String yingjiaoFee;
    
    // �������
    private String balance;
    
    // �û�ˢ������
    private String cardnumber;
    
    // ����Ϊˢ�����ն˷����Ψһ���
    private String unionpaycode;
    
    // �����̻��ţ��ڿ�����ʶ��
    private String unionpayuser;
    
    // ҵ������
    private String busitype;
    
    // �ն����κ�
    private String batchnum;
    
    // ����ʵ�ʿۿ����λ���֣�
    private String unionpayfee;
    
    // ����ʵ�ʿۿ�ʱ��
    private String unionpaytime;
    
    // ��Ȩ�루ɽ���ã�
    private String authorizationcode;
    
    // ���ײο��ţ�ɽ���ã�
    private String businessreferencenum;
    
    // ��֤�ţ�ɽ���ã�
    private String vouchernum;
    
    // �������ݿ�
    private FeeChargeService feeChargeService;
    
    // BOSS��ˮ
    private String dealNum = "";
    
    private String hasMultiInvoices;
    
    private String payType;
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
    // �ɷ���־��Ӧ��oid
    private String chargeLogOID = "";
    
    private String errorType = "";
    
    private String needReturnCard = "";
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    private String chargeType = "";
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
    // ƾ������ʱ��
    private String pDealTime = "";
    // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
    
    // add begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
    /**
     * �ͻ�����
     */
    private String custName = "";
    // add end g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
    
    // add begin g00140516 2012/12/07 R003C12L11n01 OR_SD_201211_692
    /**
     * �����ۿ�ӿڵķ�����
     */
    private String unionRetCode = "";
    
    /**
     * ������ӡ��Ϣ
     */
    private String printcontext = "";
    // add end g00140516 2012/12/07 R003C12L11n01 OR_SD_201211_692
    
    /**
     * ��Ʊ��ӡPO
     */
    private InvoicePrintPO invoicePrint;
    
    private String recoid;
    
    //add begin sWX219697 2014-7-17 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
    /**
     * ��ؽɷѣ�1�����أ�0����ؽɷ�
     */
    private String regionFlag;
    
    /**
     * ��������
     */
    private String regionName;
    //add end sWX219697 2014-7-17 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
    
    // add begin zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
    /**
     * �ֽ���ϸ
     */
    private String cashDetail;
    // add end zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
    
    // add begin jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
    /**
     * �������ɱ�־
     */
    private String morePhoneFlag; 
    
    /**
     * ��������list
     */
    private transient List<CardChargeLogVO> morePhones = null;
    
    /**
     * ���������ֻ�������
     */
    private String[] servnumbers;
    
    /**
     * �û�ʵ�ɽ������
     */
    private String morePhoneFee;

    /**
     * ���������ܷ���
     */
    private String totalFee;
    
    /**
     * ��Ʊ����
     */
    private transient List<String> invoices = null;
    
    /**
     * ��ӡ�½ᷢƱ��־��1����ӡ�½ᷢƱ
     */
    private String monthInvoiceFlag;
    
    /**
     * �������ɷ�po
     */
    private transient CardChargeLogVO cardChargeVO;
    
    /**
     * ƾ����ӡ��־��1���Ѵ�ӡ
     */
    private String printPayProFlag;
    
    /**
     * ��ӡȫ����Ʊ��־��1���Ѵ�ӡ
     */
    private String printAllInvFlag;
    
    /**
     * �ֻ������ַ�������","�ָ�
     */
    private String telnums;
    
    /**
     * ���������û���Ϣ�ַ���
     * �û�1���û�2���û�3
     * �ã��ֻ�����~�ֻ�����region~ʵ�ɽ��~���~Ӧ�ɷ�~�ͻ�����~��������~�ɷ���־��ˮ~������ˮ~boss���~�ɷ�״̬
     */
    private String morePhoneInfo;
    
    /**
     * �Ѵ�ӡ��Ʊ���ֻ����봮
     */
    private String printInvTelnum = "";
    // add end jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
    
    
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
    /**
     * ��Ʊ��ӡʱ����
     */
    private String password = "";
    
    // �½��ʶ
    private String printFlag = "";
    
    private transient UserLoginBean userLoginBean = null;
    
    private transient LoginService loginService = null;
    
    // ��ӡȫ����Ʊ��ʶ
    private String printAllFlag = "";
    
    // �Ѵ�ӡ�������
    private int printInvTelnumLen = 0;
    
    // ���˽ɷѺ����ܸ���
    private int morePhonesLen = 0;
    
    // ���˽ɷѱ�ʶ
    private String morePhonesFlag = "";
    // modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
    
    /**
     * ���ѳ�ֵ������Ҫ������֤��������Ҫ����������룬�Ա�֤��ֵ������ȷ
     * 
     * @return
     */
    public String inputNumber()
    {      
    	// ��ʼ���ͻ���Ϣ��־
    	morePhoneInfo = "";
    	
    	// ��ʼ���������ɱ�־
    	morePhoneFlag = "";
    	
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
           return  "inputNumber2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "inputNumber";
    }
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    /**
     * �Ƿ�ֻ֧�ֽ�������
     * @remark create YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818 
     */
    private boolean getIsKey()
    {
        TerminalInfoPO termInfo = (TerminalInfoPO) (getRequest().getSession().getAttribute(Constants.TERMINAL_INFO));
        
        return termInfo.isSuppKey();
    }
        
    /**
     * ȡ�ɷ�����
     * 
     * @param payType(Card����Cash)
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String getChargeType(String payType)
    {
        String chargeType = "";
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance()
                .getCachedData(Constants.ChargeType);
        // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
        if (chargeTypeList != null)
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
        // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
        
        return chargeType;
    }
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371

    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {        
        logger.debug("qryfeeChargeAccount start");
        
        String forward = "failed";

        // �ж��û�״̬�Ƿ���Խɷѣ�����true���Խɷ�
        String checkResult = qryUserStatus();
        
        if (!"".equals(checkResult))
        {       	
        	return forward;
        }
        
        HttpServletRequest request = this.getRequest();
            
        try
        {
        	TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
                
        	// �ֽ�ɷ�
        	// modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
            String bankNo = termInfo.getBankno();
            String chargeType = getChargeType("Cash");
            
            if (StringUtils.isEmpty(bankNo) || StringUtils.isEmpty(chargeType))
            {
            	logger.error("�������������кŻ�ȡʧ�ܣ�");
                
                setErrormessage("�������������к���Ϣ��ȡʧ��");
                
                // ��¼�쳣��־
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "�������������к���Ϣ��ȡʧ��");
                
                return forward;
            }
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371
             
            // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            Map<String, Object> result = feeChargeBean.qryfeeChargeAccount(termInfo,
                    servnumber,
                    curMenuId,
                    bankNo,
                    CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"),
                    "ALL",
                    chargeType);
            // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
            if (result != null && result.size() > 0)
            {
                CTagSet tagSet = (CTagSet)result.get("returnObj");
                
                // modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
                String sumFee = "";
                String balance = "";
                
                servRegion = tagSet.GetValue("servregion");
                
                // modify begin sWX219697 2014-7-19 09:47:35 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
                // ������ʽ��regionFlagΪ0(��������)ʱ����������ΪZZYD��������أ�
                // modify begin zKF28472 2014-10-28 OR_SD_201410_324 ���������ն˿����ɷѶ����쳣������
                // acceptType = "0".equals(regionFlag) ? "ZZYD" : tagSet.GetValue("accept_type");
                acceptType = "0".equals(regionFlag) ? "Z" + tagSet.GetValue("accept_type")
                        : tagSet.GetValue("accept_type");
                // modify end zKF28472 2014-10-28 OR_SD_201410_324 ���������ն˿����ɷѶ����쳣������
                // modify end sWX219697 2014-7-19 09:47:41 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
                // add begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                custName = tagSet.GetValue("cust_name");
                // add begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                
                sumFee = tagSet.GetValue("sum_fee");
                balance = tagSet.GetValue("balance");
                
                // modify end by cwx456134 2017-05-13 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
                
                if (StringUtils.isEmpty(acceptType))
                {
                    logger.error("��ѯ��������ʧ�ܣ�");
                    
                    setErrormessage("��ѯ�û���Ϣʧ��");
                    
                    // ��¼�쳣��־
                    this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����ʱ��ѯ��������ʧ��");
                    
                    return forward;
                }
                
                // modify begin hWX5316476 2014-03-14 OR_SD_201312_90_ɽ��_�����ն˽���Ӧ��������ʾ���Ż�����
                // Ӧ�ɷ���
                if (StringUtils.isEmpty(sumFee) || "0.00".equals(sumFee))
                {
                    this.setYingjiaoFee(null);
                    
                    // ���û������
                    setBalance(balance);
                }
                else
                {
                	// ���ýӿڲ�ѯӦ�ɻ��ѽ��
                    Map resultfee = feeChargeBean.qryRetcharge(termInfo, servnumber, curMenuId);
                    
                    if(null == resultfee || null == resultfee.get("retcharge"))
                    {
                    	logger.error("��ѯ��ѯӦ�ɻ��ѽ��ʧ�ܣ�");
                        
                        setErrormessage("��ѯӦ�ɻ��ѽ��ʧ��");
                        
                        // ��¼�쳣��־
                        this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����ʱ��ѯӦ�ɻ��ѽ��ʧ��");
                        
                        return forward;
                    }
                    String retcharge = (String) resultfee.get("retcharge");
                	String shouldpay = CommonUtil.fenToYuan(retcharge);
                	
                	this.setYingjiaoFee(shouldpay);
                }
	            // modify end hWX5316476 2014-03-14 OR_SD_201312_90_ɽ��_�����ն˽���Ӧ��������ʾ���Ż�����
                // ----------------------------��ֵ��ʽ-------------------------------------------------
                    
                // �����ն����Ի����л�ȡ�˵��б�
                String groupID = termInfo.getTermgrpid();
                    
                List<MenuInfoPO> menus = null;
                    
                if (!StringUtils.isEmpty(groupID))
                {
                	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
                }
                    
                // ��ҳ�˵��б�
                usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
                
                // findbugs�޸� 2015-09-02 zKF66389
                //logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
                // findbugs�޸� 2015-09-02 zKF66389    
                // findbugs�޸� 2015-09-02 zKF66389 
                //if (usableTypes == null || usableTypes.size() == 0)
                if (usableTypes.size() == 0)
                // findbugs�޸� 2015-09-02 zKF66389 
                {
                    // û�п��õĳ�ֵ��ʽ
                    setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
                        
                    // ��¼��־
                    this.createRecLog(servnumber, "feeCharge", "0", "0", "1", "��ʱû�п��õĳ�ֵ��ʽ");
                }
                else
                {
                    // add begin YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
                    if (getIsKey())
                    {
                       return  "success2";
                    }
                    // add end YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
                    
                    forward = "success";
                }
            }
            else
            {
                logger.error("��ѯ��������ʧ�ܣ�");
                    
                setErrormessage("��ѯ��������ʧ��");
                    
                // ��¼������־
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����ʱ��ѯ��������ʧ��");
            }
        }
        catch (Exception e)
        {
            logger.error("��ѯ��������ʧ�ܣ�");
                
            setErrormessage("��ѯ��������ʧ��");
                
            // ��¼�쳣��־
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����ʱ��ѯ��������ʧ��");
        }
        
        return forward;
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
     * ת��Ͷ��ҳ��
     * 
     * @return
     */
    public String cashCharge()
    {
        this.getRequest().setAttribute("sdCashFlag", "1");
        
        // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        chargeType = getChargeType("Cash");
        // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // add begin zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        // �ն���Ϣ
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // ȡ�ýɷ���־oid
        chargeLogOID = feeChargeService.getChargeLogOID();
        
        // ��װ�ɷ���־��������
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// �ֽ�Ͷ����־
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        selfCardPayLogVO.setRecdate(DateUtil._getCurrentTime());
        selfCardPayLogVO.setAcceptType(acceptType);
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
        selfCardPayLogVO.setDescription("����֮ǰ��¼Ͷ����־");
        selfCardPayLogVO.setRecType("phone"); // ҵ�����ͣ�phone�����ѽɷ�  favourable���Żݽɷѣ�
        selfCardPayLogVO.setBankno(chargeType + termInfo.getBankno());
        
        // �����ɷ���־
        feeChargeService.addChargeLog(selfCardPayLogVO);
        // add end zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
           return  "cashChargePage2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "cashChargePage";
    }
    
    /**
     * ���ѳ�ֵ���ֽ�ɷ�
     * 
     * @return
     */
    public String cashChargeCommit()
    {
        logger.debug("cashChargeCommit start");
        logger.warn("�ύ�������󣬺��룺" + servnumber + "����" + tMoney + "����ˮ��" + terminalSeq);
          	
        // add begin g00140516 2012/11/09 R003C12L11n01 ��ֹ�û���Ͷ�ң�ֱ�Ӵ��������ģ�⽻������
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("��Ч����");
            
            return "error";
        }    
        // add end g00140516 2012/11/09 R003C12L11n01 ��ֹ�û���Ͷ�ң�ֱ�Ӵ��������ģ�⽻������        
        
        String forward = null;
        
        HttpSession session = getRequest().getSession();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        
        // �ֽ�ɷ�
        // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        String bankNo = termInfo.getBankno();
        // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // modify begin zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        // ��װ�ɷ���־��������
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        // modify end zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        
        // ɽ���ӿ� -- ��Ʊ��ӡ��־��0������ӡ 1����ӡ 2=����ӡ��Ʊ���Բ���
        String invoiceType = "2";
        
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        // �ֽ�ɷ�
        Map resultMap = feeChargeBean.chargeCommit(termInfo,
        		curMenuId,servnumber,bankNo,payDate,terminalSeq,tMoney,acceptType,invoiceType,"","",chargeType);
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        if (resultMap != null && resultMap.size() > 1)
        {
            CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            if (null != tagSet && !tagSet.isEmpty())
            {
                dealNum = (String)tagSet.GetValue("bill_nbr");// �������
                
                this.getRequest().setAttribute("dealNum", dealNum);
                
                // modify begin wWX217192 OR_huawei_201407_1042 �����նˣ�ɽ������ֵ��Ʊ��ӡ�Ż� 
                // ������ˮ�ţ���ӡ��Ʊʱʹ��
                recoid = (String)tagSet.GetValue("recoid");
                // modify end wWX217192 OR_huawei_201407_1042 �����նˣ�ɽ������ֵ��Ʊ��ӡ�Ż� 
                
                // add begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                custName = (String)tagSet.GetValue("cust_name");
                // add end g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                
            }
            
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("���ѳ�ֵ�ֽ𽻷ѳɹ���");
            selfCardPayLogVO.setDealnum(dealNum);
            
/*            forward = "success";
            
            // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
            if (getIsKey())
            {
            	 forward = "success2";
            }
            // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
*/            
            forward = getIsKey() ? "success2" : "success";
            
            // ��¼�ɷѳɹ���־
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "0", "����:�����ն˽��ѳɹ�!");
            
        }
        else
        {
            String errMsg = "";
            if (resultMap != null)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            
            if (errMsg == null || "".equals(errMsg.trim()))
            {
                errMsg = "���ѳ�ֵ�ֽ𽻷�ʧ�ܣ�";
            }
            
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            selfCardPayLogVO.setDealnum("");
            
            //forward = "error";
            
            // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
//            if (getIsKey())
//            {
//            	 forward = "error2";
//            }
            // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818
            forward = getIsKey() ? "error2" : "error";
            
            setErrormessage("���ѳ�ֵ�ֽ𽻷�ʧ�ܣ�");
            
            // ��¼�ɷ�ʧ����־
            this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);

        logger.debug("cashChargeCommit end");
   
        return forward;
    }
    
    /** 
     * �ֽ�Ͷ�Һ󣬸��½�����־
     * 
     * @see [�ࡢ��#��������#��Ա]
     * @remark: create zKF69263 2015/04/16 OR_SD_201502_169_SD_�����ն�ʵ���ֽ���˹���
     */
    public void updateCashChargeLog()
    {
        logger.debug("updateCashChargeLog Starting...");
        
        try
        {
            // ȡ���ն˻���Ϣ
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // ��װ��־����
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            
            cardChargeLogVO.setChargeLogOID(chargeLogOID);
            cardChargeLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            cardChargeLogVO.setDescription("���ѳ�ֵ�ֽ𽻷Ѽ�¼Ͷ����ϸ");
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setRecdate(DateUtil._getCurrentTime());
            cardChargeLogVO.setCashDetail(cashDetail);
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            cardChargeLogVO.setTerminalSeq(terminalSeq);
            
            // ����ɷ���־
            feeChargeService.updateCashDetail(cardChargeLogVO);
            
            // ���½ɷ���־�ɹ�
            writeXmlMsg("1");
        }
        catch (Exception e)
        {
            // ���½ɷ���־ʧ��
            writeXmlMsg("0");
            
            logger.error("�ֽ�Ͷ��֮�����Ͷ����ϸ��־�쳣��", e);
        }
        
        logger.debug("updateCashChargeLog end!");
    }
    
    /**
     * �ֽ𽻷��쳣����
     * 
     * @return
     * @see
     */
    public String goCashError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError Starting ...");
        }
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        // modify begin zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        // �ն���Ϣ
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // ���¼�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // ��װ�ɷ���־��������
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRecdate(DateUtil._getCurrentTime());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        // �ն���ˮ��
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        
        if (tMoney == null || "".equals(tMoney.trim()) || "0".equals(tMoney.trim()))
        {
            selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        else
        {
            selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        
        // ���½ɷ���־
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        // modify end zKF69263 2015-4-17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        if (getIsKey())
        {
            return "cashErrorPage2";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        
        return "cashErrorPage";
    }
    
    /**
     * У�鵱ǰʱ����������ֵ�Ƿ���á�1�����ã�0��������
     * 
     * @throws Exception
     * @see 
     * @remark create g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692
     */
    public void checkTime() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("�ж������������Ƿ����ʧ��");
        }
        
        String xml = "0";
        
        try
        {
            // 2320-0025
            String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
            
            if (time != null && !"".equals(time.trim()))
            {
                // ��ǰʱ��
                SimpleDateFormat sdf = new SimpleDateFormat("HHmm");        
                String currTime = sdf.format(new Date());
                
                // ��ǰʱ����0025��2320֮��ʱ����
                String[] times = time.split("-");
                if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
                {
                    xml = "1";
                }
            }
        }
        catch (Exception e)
        {
            logger.error("�ж������������Ƿ����ʧ�ܣ�", e);
        }
        finally
        {
            // �����client
            if (writer != null)
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
                    
                    throw new Exception("�ж������������Ƿ����ʧ��");
                }
            }
        }        
    }
    
    /**
     * ת�����п��ɷѽ��ѡ��ҳ��
     * 
     * @return
     */
    public String cardCharge()
    {
    	// add begin jWX216858 2015-4-9 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
    	// ���Ϊ���˽ɷѣ�ֱ�ӽ���Э��ҳ��
    	if ("1".equals(morePhoneFlag))
    	{
            // add begin sWX219697 2015-8-6 14:52:50
            //У��ǰ̨�ܽ�����̨�ܽ���Ƿ�һ��
            int userTotalFee = 0;
            
            //�����û���Ϣ�ַ����е��ܽ��
            for(CardChargeLogVO vo : this.getMorePhoneList())
            {
                userTotalFee = userTotalFee + Integer.parseInt(vo.gettMoney());
            }
            
            //������totalFee�������ܽ�һ�£���ת������ҳ�棬�������˽ɷ�
            if (userTotalFee != Integer.parseInt(totalFee))
            {
                logger.error("���˽ɷ��ܽ��У��ʧ�ܣ�");
           
                this.createRecLog(Constants.SH_MOREPHONE, "", "0", Constants.RECSTATUS_FALID, "���˽ɷ��ܽ��У��ʧ�ܣ�");
           
                setErrormessage("���˽ɷ��ܽ��У��ʧ�ܣ�");
           
                return ERROR;
            }
            // add end sWX219697 2015-8-6 14:52:50
    	    
    		return this.dutyInfo();
    	}
    	// add end jWX216858 2015-4-9 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
    	
		chargeType = getChargeType("Card");
		
		if (getIsKey())
		{
			return "otherFee2";
		}
		
		return "otherFee";
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
        String forward = "toReadCard";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            // ��װ��־����
            chargeLogOID = feeChargeService.getChargeLogOID();
            
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);        
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setTermID(termInfo.getTermid());
            cardChargeLogVO.setOperID(termInfo.getOperid());
            cardChargeLogVO.setServnumber(servnumber);
            cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            cardChargeLogVO.setUnionpayuser("");
            cardChargeLogVO.setUnionpaycode("");
            cardChargeLogVO.setBusiType("");
            cardChargeLogVO.setCardnumber("");
            cardChargeLogVO.setBatchnum("");
            cardChargeLogVO.setAuthorizationcode("");
            cardChargeLogVO.setBusinessreferencenum("");
            cardChargeLogVO.setUnionpaytime("");
            cardChargeLogVO.setVouchernum("");
            cardChargeLogVO.setUnionpayfee("");
            cardChargeLogVO.setTerminalSeq("");
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            
            cardChargeLogVO.setStatus("00");
            cardChargeLogVO.setDescription("�ۿ�֮ǰ��¼��־");
            cardChargeLogVO.setDealnum("");
            cardChargeLogVO.setAcceptType(acceptType);
            cardChargeLogVO.setServRegion(servRegion);
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setPosNum("");
            cardChargeLogVO.setBatchNumBeforeKoukuan("");
            cardChargeLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ�  favourable���Żݽɷѣ�
            cardChargeLogVO.setBankno(chargeType + termInfo.getBankno());
           
            // ����ɷ���־
            feeChargeService.addChargeLog(cardChargeLogVO);
            
            // ���γ�ʱ������ҳ�Ĺ���
            request.setAttribute("sdCashFlag", "1");
        }
        catch (Exception e)
        {
            logger.error("�ۿ�֮ǰ��¼��־ʧ�ܣ�", e);
            
            this.createRecLog(servnumber, payType, "0", "0", "1", "�ۿ�֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            
            setErrormessage("�ۿ�֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            
            forward = "error";
        }
        
        return forward;
    }

    /**
     * �ۿ�ɹ�֮�󣬸��½�����־
     * 
     * @throws Exception
     * @see
     */
    public void updateCardChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("�ۿ�֮ǰ��¼��־ʧ��");
        }
        
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // ��װ��־����
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        cardChargeLogVO.setCardnumber(cardnumber);
        cardChargeLogVO.setBatchnum(batchnum);
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        cardChargeLogVO.setVouchernum(vouchernum);
        
        if (unionpayfee != null)
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
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        cardChargeLogVO.setRecdate(payDate);
        
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
            if (writer != null)
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
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog end!");
        }
    }
    
    /**
     * ���п��ɷ��ύ
     * 
     * @return
     */
    public String cardChargeCommit()
    {
        logger.debug("cardChargeCommit start");

        String forward = null;
        
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        String referer = getRequest().getHeader("Referer");
        if (null == referer)
        {
            setErrormessage("��Ч����");
            
            return "error";
        }
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        // add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
        
        // ���п��ɷ�
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        String bankNo = termInfo.getBankno();
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // ɽ���ӿ� -- ��Ʊ��ӡ��־��0������ӡ 1����ӡ 2=����ӡ��Ʊ���Բ���
        String invoiceType = "2";
        
        while (tMoney.startsWith("0"))
        {
            tMoney = tMoney.substring(1);
        }
        tMoney = CommonUtil.fenToYuan(tMoney);
        
        // modify begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        // ���п��ɷ�
        Map resultMap = feeChargeBean.chargeCommit(termInfo,
        		curMenuId,servnumber,bankNo,payDate,
                terminalSeq,tMoney,acceptType,invoiceType,"","",chargeType);
        // modify end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
        
        // ������־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        if (resultMap != null && resultMap.size() > 1)
        {
        	CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            
        	if (null != tagSet && !tagSet.isEmpty())
            {
                dealNum = (String)tagSet.GetValue("bill_nbr");// �������
                
                this.getRequest().setAttribute("dealNum", dealNum);
                
                // modify begin wWX217192 OR_huawei_201407_1042 �����նˣ�ɽ������ֵ��Ʊ��ӡ�Ż�
                // ������ˮ��Ԥ�淢Ʊ��ӡʹ��
                recoid = (String)tagSet.GetValue("recoid");
            	// modify begin wWX217192 OR_huawei_201407_1042 �����նˣ�ɽ������ֵ��Ʊ��ӡ�Ż� 
                
                // add begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                custName = (String)tagSet.GetValue("cust_name");
                // add end g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
                
            }
                
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("���ѳ�ֵ���������ѳɹ���");
            selfCardPayLogVO.setDealnum(dealNum);
            
            forward = "success";
            
            if (getIsKey())
            {
               return  "success2";
            }
            
            // ��¼�ɷѳɹ���־
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "0", "����:�����ն˽��ѳɹ�!");
        }
        else
        {
            String errMsg = "";
            if (resultMap != null)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//            if (errMsg == null || "".equals(errMsg.trim()))
//            {
//                errMsg = "�������ۿ�ɹ������ǽ���ʧ�ܣ�";
//            }
            errMsg = (errMsg == null || "".equals(errMsg.trim())) ? "�������ۿ�ɹ������ǽ���ʧ�ܣ�" : errMsg;
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
            
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            
            setErrormessage("�������ۿ�ɹ������ǽ���ʧ�ܣ�");
            
            // ��¼�ɷ�ʧ����־
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
    }
    
    /**
     * �����������쳣����
     * 
     * @return
     * @see
     */
    public String goCardError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError Starting ...");
        }
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
        {
            selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setTermID(termInfo.getTermid());
            selfCardPayLogVO.setOperID(termInfo.getOperid());
            selfCardPayLogVO.setServnumber(servnumber);
            if (Constants.PAY_BYCASH.equals(payType))
            {
                selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
            }
            else if (Constants.PAY_BYCARD.equals(payType))
            {
                selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
            }
            
            if (tMoney == null || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            }
            else
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            }
            
            selfCardPayLogVO.setTerminalSeq("");
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
            selfCardPayLogVO.setAcceptType(acceptType);
            selfCardPayLogVO.setServRegion(servRegion);
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            selfCardPayLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ�  favourable���Żݽɷѣ�
            // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            selfCardPayLogVO.setBankno(chargeType + termInfo.getBankno());
            // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
            feeChargeService.addChargeLog(selfCardPayLogVO);
        }
        // ������־
        else
        {
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            
            if (tMoney == null || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setStatus("00");
            }
            else
            {
                selfCardPayLogVO.setStatus("10");
            }
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
            selfCardPayLogVO.setPosResCode(unionRetCode);
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        return "cardErrorPage";
    }
    
    /**
     * ��¼��Ʊ��ӡ��־
     * 
     * @see
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String cycle = request.getParameter("cycle");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintRecord record = new InvoicePrintRecord();
        record.setServNumber(servNumber);
        record.setCycle(cycle);
        record.setTermID(termInfo.getTermid());
        
        feeChargeService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
    /**
     * ������ϸ����Ʊע�ͽ���
     * 
     * @param consumeList ������ϸ
     * @param comments ��Ʊע��
     * @return
     */
    private String parseConsumeList(String consumeList, String comments)
    {
    	// modify begin g00140516 2012/08/14 R003C12L08n01 ��Ʊ��ӡ��ʽ�Ż�
        String[] consumeArr = consumeList.replace("|", ",").split(";");
        StringBuffer tmpConsumeList = new StringBuffer();
        int rowNum = 18;
        int row = 0;
        for (int i = 0; i < consumeArr.length && row < rowNum; i++)
        {
            if (consumeArr[i].trim().length() <= 3)
            {
                continue;
            }
            
            String tmpArr[] = consumeArr[i].split(",");
            if (!CommonUtil.isEmpty(tmpArr[0]))
            {
            	// modify begin wWX217192 2014-07-10 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ��Ϣ����
            	// Ϊ������Ʊ��ϸ����ǰ�Ŀո���Ϣ��trim()����ɾ��
            	tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[0], 13));
                
                if (tmpArr.length > 1)
                {
                	// Ϊ������Ʊ��ϸ����ǰ�Ŀո���Ϣ��trim()����ɾ��
                    tmpConsumeList.append(CommonUtil.fillLeftBlank(tmpArr[1], 8));
                }
                else
                {
                    tmpConsumeList.append(CommonUtil.fillLeftBlank("", 8));
                }
                // modify end wWX217192 2014-07-10 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ��Ϣ����
                tmpConsumeList.append("\\n");
                row++;
            }
        }
        
        // ��Ʊ��ע��ÿ�д�ӡ25������
        if (comments != null && !"".equals(comments.trim()))
        {
            tmpConsumeList.append(comments.trim()).append("\\n");
            
            if (comments.trim().length() % 25 == 0)
            {
                row += (comments.trim().length() / 25);
            }
            else
            {
                row = row + (comments.trim().length() / 25) + 1;
            }
        }
        
        tmpConsumeList.append("\\n");
        row++;
        tmpConsumeList.append("\\n");
        row++;
        
        return tmpConsumeList.toString();
        
    }
    
    /**
     * ����Ʊ������֯��xml
     * 
     * @param list ��Ʊ����
     * @return
     * @see
     */
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                        .append(subItemKey)
                        .append("><![CDATA[")
                        .append(subItemValue)
                        .append("]]></")
                        .append(subItemKey)
                        .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        
        return xmlStr.toString();
    }
    
    /**
     * ���������֤����֤ͨ���󣬴�ӡ��Ʊ
     * 
     * @return
     * @see
     */
    public String printInvoice()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            
            if (logger.isInfoEnabled())
            {
                logger.info("��Ʊ��ӡ���ܣ��û�" + servnumber + "ʹ��������������֤�ɹ�");
            }
        }
        else
        {
            forward = "error";
            
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                errorMsg = "�����������������������롣";
            }
            
            logger.error("��Ʊ��ӡ���ܣ��û�" + servnumber + "����������������ʱ����֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
    	list = getPrintInvoice(servnumber, recoid);
    	
    	//add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        //��ȡ�������ã��Ƿ����õ��ӷ�Ʊ,trueΪ����,��ӡ���ӷ�Ʊʱ���ӿڲ�����ֵ,����ֱ����������
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            String errormessage = (String)getRequest().getAttribute("telnum");
            if(CommonUtil.isNotEmpty(errormessage))
            {
                logger.info(errormessage);
                return "error";
            }
            else
            {
                logger.info("��ӡ���ӷ�Ʊ���û�" + servnumber + "��ӡ��Ʊ��Ϣ�ɹ�");
                request.setAttribute("isElectronInvoice", isElectronInvoice);
                request.setAttribute("servnumber", servnumber);
                return "success";
            }
        }
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        
        if(list.isEmpty())
        {
        	logger.info("��ѯ��Ʊ��Ϣʧ��!");
        	
        	forward = "error";
        }
        // String invoiceXML = (String)request.getParameter("invoiceXML");
        // String invoiceType = (String)request.getParameter("invoiceType");
        
        // ��װXML��Ϣ
        request.setAttribute("invoiceXML", getXmlStr(list));
    	
        // ���ô�ӡ����
    	request.setAttribute("invoiceType", "Last");
        
        return forward;
    }
    
    /**
     * �������������֤��ֱ�Ӵ�ӡ��Ʊ
     * 
     * @return
     * @see
     */
    public String printInvoiceWithoutPwd()
    {
    	// ҳ����ת��־��Ϣ
    	String forward = "success";
        // modify begin g00140516 2012/08/14 R003C12L08n01 ��Ʊ��ӡ��ʽ�Ż�
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
        list = getPrintInvoice(servnumber, recoid);

        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        //��ȡ�������ã��Ƿ����õ��ӷ�Ʊ,trueΪ����,��ӡ���ӷ�Ʊʱ���ӿڲ�����ֵ,����ֱ����������
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            String errormessage = (String)getRequest().getAttribute("telnum");
            if(CommonUtil.isNotEmpty(errormessage))
            {
                logger.info(errormessage);
                return "error";
            }
            else
            {
                logger.info("��ӡ���ӷ�Ʊ���û�" + servnumber + "��ӡ��Ʊ��Ϣ�ɹ�");
                request.setAttribute("isElectronInvoice", isElectronInvoice);
                request.setAttribute("servnumber", servnumber);
                if (getIsKey())
                {
                    return  "print2";
                }
                else
                {
                    return "print";
                }
            }
        }
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        
        if(list.isEmpty())
        {
        	logger.info("��ѯ��Ʊ��Ϣʧ��!");
        	
        	forward = "error";
        }
        else
        {
        	String printType = setPrintType();
			getRequest().setAttribute("printType", printType);

        	// modify end g00140516 2012/08/14 R003C12L08n01 ��Ʊ��ӡ��ʽ�Ż�
        	
        	// String invoiceXML= (String) request.getParameter("invoiceXML");
        	// String invoiceType = (String) request.getParameter("invoiceType");
        	
        	request.setAttribute("invoiceXML", getXmlStr(list));
        	
        	request.setAttribute("invoiceType", "Last");
        	
        	// add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        	if (getIsKey())
        	{
        		return  "print2";
        	}
        	// add end YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818 
        }
        
        return "print";
    }
    
    /**
     *  * ���ô�ӡ��Ʊ�ӿڲ�ѯ��Ʊ����
     * 
     * @return List<Map<String, String>>
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by wWX217192 
     */
    private List<Map<String, String>> getPrintInvoice(String telnum, String oid)
    {
    	HttpServletRequest request = this.getRequest();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ��ѯ��ӡ��Ʊ��Ϣ
        Map<String, Object> invoiceData = feeChargeBean.invoiceData(termInfo, curMenuId, telnum, oid);
        
        List<Map<String, String>> invoicesList = null;
        
        if (invoiceData != null && invoiceData.size() > 1)
        {
            Object invoiceObj = invoiceData.get("returnObj");
            
            if (invoiceObj instanceof CRSet)
            {
                invoicesList = new ArrayList<Map<String, String>>();
                
                // ȡ�ӿڷ��ض���
                CRSet crset  = (CRSet)invoiceObj;

                // crsetת��MAP
                Map<String, String> invoiceMaps = getInvoiceInfo(crset);
                
                // ����list
                invoicesList.add(invoiceMaps);
            }
            printInvTelnum = printInvTelnum + "," + telnum;
            
            // ��¼��ѯδ��ӡ��Ʊ��Ϣ�ɹ���־
            this.createRecLog(telnum, Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "0", "��ȡ����Ʊ��Ϣ�ɹ�!");
        }
        else if (invoiceData != null) {
            
            getRequest().setAttribute("telnum", invoiceData.get("returnMsg"));
            
            // ��¼��ѯδ��ӡ��Ʊ��Ϣʧ����־
            this.createRecLog(telnum, Constants.OPERTYPE_QRYPRINTINVOICE, "0", "0", "1", 
                "��ȡ����Ʊ��Ϣʧ�ܣ�" + String.valueOf(invoiceData.get("returnMsg")));
        }
        
        // ����
        return invoicesList;
    }
    
    
    /**
     * �Կͷ����صĴ�ӡ��Ʊ���ݽ��д���
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 OR_huawei_201404_1109 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_����ɷѷ�Ʊ
     */
    public Map<String, String> getInvoiceInfo(CRSet crset)
    {
    	// ��ȡsession��Ϣ
    	HttpServletRequest request = this.getRequest();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
    	// �ӽӿڷ���ֵ�л�ȡ��Ʊ��Ϣ
    	Map<String, String> invoiceMaps = new HashMap<String, String>();
        
    	// ��ϳɿ��õ�Map��������
    	Map<String, String> resultMaps = new HashMap<String, String>();
    	
        // ����Ʊ��ϸ��Ϣƴ�ӳ��ַ�����Ϣ
        StringBuffer contentItemStr = new StringBuffer("");
        
        // ��Ʊ������ʽ�ı�־λ, 0:�Ϸ�Ʊ��ʽ��1���·�Ʊ��ʽ
        String styleFlag = "0";
        
        // ��������
        StringBuffer feeTime = new StringBuffer("");
        
        // ��ȡContentItemX����ContentItemXName��ֵ
        String key = "";
        
        // ��ʼ�����ֵ��Ϣ
        int max = 0;
        
        // ContentItem���ݵ�����ֵ
        int seq = 0;
        
        // ������Ʊ��ϸ��Ϣ
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	seq = 0;
        	
        	key = crset.GetValue(i, 0);
        	
        	// ��keyֵ�г�����ContentItem��ͷ����Name��β����Ϣ����Ϊ�·�Ʊ��ʽ�������·�Ʊ�Ľ�������
        	if(key.startsWith("ContentItem") && !key.endsWith("Name"))
        	{
        		styleFlag = "1";
        	}
        	
        	max = max(key, max);
        	
        }
        
        // ����һ�����飬��װ��Ʊ��ϸ��Ϣ
        String[] contentItems = new String[max];
        
        // ����һ����ȡContentItem����ֵ�ı���
        int arrNums = 0;
        
        // ����crset������
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	String name = crset.GetValue(i, 0);
        	String value = crset.GetValue(i, 1);
        	
        	// name�а���ContentItem���ַ���������Ϣ��װ���ַ����ĸ�ʽ
        	if(name.startsWith("ContentItem"))
        	{
        		arrNums = getSeq(name);
        		
        		if("1".equals(styleFlag))
        		{
        			parseInvoiceDetail(name, value, contentItems, arrNums);
        		}
        		else
        		{
        			contentItems[arrNums - 1] = value.trim().replace("  ", "|");
        		}
        	}
        	// ��name�в�����ContentItem���ַ�����������������invoiceMaps��
        	else
        	{
        		invoiceMaps.put(name, value);
        	}
        }
        
        // ��ContentItemStr�ַ�����գ��洢��Ʊ��Ϣ
        contentItemStr = new StringBuffer("");
        
        for(int i = 0; i < contentItems.length; i++)
        {
        	if(StringUtils.isNotEmpty(contentItems[i]))
        	{
        		contentItemStr.append(contentItems[i]).append(";");
        	}
        }
        
        // �Ʒ��ڼ�(��ȡǰ8���ַ�����Ϊֵ����ʹ��)
        if(!CommonUtil.isEmpty(invoiceMaps.get("callfeeCreateTime")))
        {
        	feeTime = feeTime.append("��������:|").append(invoiceMaps.get("callfeeCreateTime").substring(0, 6) + "(" + invoiceMaps.get("callfeeCreateTime") + ")").append(";");
        }
        
        // �ڷ�Ʊ��ϸ�Ķ������ӻ������ڵ���ʾ��
        contentItemStr = feeTime.append(contentItemStr);
        
        // ������ʾ"����д"
        contentItemStr.append("��д���ϼ�:|").append(invoiceMaps.get("totalmoneydx")).append(";");
        
        // �Է�Ʊ��ϸ���н����������ɿɴ�ӡ�ڷ�Ʊ�ϵ��ַ���
        String consumeList = parseConsumeList(contentItemStr.toString(), invoiceMaps.get("note"));
        
        invoiceMaps.put("consumeList", consumeList);
        
        // ����Ϣ��װ�����ƽɷѽӿڵ���ʽ��ӡ
        // �ֻ�����
        resultMaps.put("servNumber", invoiceMaps.get("telnumber"));
        
        // ��ˮ��
        resultMaps.put("dealNum", invoiceMaps.get("formnum"));
        
        // ��Ʊ��ӡ
        resultMaps.put("acceptType", invoiceMaps.get("rectype"));
        
        // ��ͬ��
        resultMaps.put("acctID", 
        	StringUtils.isNotEmpty(invoiceMaps.get("paynumno")) ? invoiceMaps.get("paynumno") : "");
        
        // �ͻ�����
        resultMaps.put("subsName", invoiceMaps.get("username"));
        
        // ��ӡ����(YYYY.MM.DD)����ʽ�޸�ΪYYYYMMDD
        resultMaps.put("payDate", invoiceMaps.get("time").replace(".", ""));
        
        // �������
        resultMaps.put("bossFormnum", invoiceMaps.get("formnum"));
        
        // �ն���֯��������
        resultMaps.put("pOrgName", termInfo.getOrgname());
        
        // ��Ʊ��ϸ��Ϣ
        resultMaps.put("consumeList", invoiceMaps.get("consumeList"));
        
        return resultMaps;
        
    }
    
    /**
     * �õ�ContentItem���е����ֵ��Ϣ
     * @param key
     * @param max
     * @return ���ֵ
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ����
     */
    private int max(String key, int max)
    {
    	// ��ȡContentItem�����ֵ����
    	int seq = 0;
    	
    	// ��ȡkeyֵ�е�������Ϣ
    	if(key.startsWith("ContentItem") && key.endsWith("Name"))
    	{
    		seq = Integer.parseInt(key.replace("ContentItem", "").replace("Name", ""));
    	}
    	
    	// ��ȡ�����е����ֵ
    	if(seq > max)
    	{
    		max = seq;
    	}
    	
    	return max;
    }
    
    /**
     * �õ�ContentItem�����е���ֵ��Ϣ
     * @param name
     * @return �õ�ContentItem�������ֵ��Ϣ
     * @remark create wWX217192 2014-07-12 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ����
     */
    private int getSeq(String name)
    {
    	// ��ȡContentItem�����ֵ
    	int arrNums = 0;
    	
    	// �ַ�����Name��β����replace��ContentItem��Name�ַ���
    	if(name.startsWith("ContentItem") && name.endsWith("Name"))
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", "").replace("Name", ""));
		}
    	// �ַ�������Name��β�����滻��ContentItem
		else
		{
			arrNums = Integer.parseInt(name.replace("ContentItem", ""));
		}
    	return arrNums;
    }
    
    /**
     * ������Ʊ��ϸ
     * @param name
     * @param value
     * @param contentItemStr
     * @return ��Ʊ��ϸ�ַ���
     * @remark create wWX217192 2014-07-08 OR_huawei_201404_1108 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_�½ᷢƱ
     */
    private String[] parseInvoiceDetail(String name, String value, String[] contentItems, int seq)
    {
		StringBuffer tempSf = new StringBuffer("");
		
		// ��ContentItems[seq - 1] ��Ϊ�գ���name�滻Ϊvalueֵ
		if(StringUtils.isNotEmpty(contentItems[seq - 1]))
		{
			contentItems[seq - 1] = contentItems[seq - 1].replace(name, value);
		}
		// ContentItems[seq - 1] Ϊ��
		else
		{
			// name��"Name"��β��ƴ���ַ�������������
			if(name.endsWith("Name"))
			{
				tempSf.append(value).append("|").append(name.substring(0, name.length() - 4));
				contentItems[seq - 1] = tempSf.toString();
			}
			// name����"Name"��β�����������������
			else
			{
				tempSf.append(name + "Name").append("|").append(value);
				contentItems[seq - 1] = tempSf.toString();
			}
		}
		
		// modify begin wWX217192 2014-07-14 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ����
		// �ֳ�Ҫ���ںϼƺ�ӿ���
		if(value.equals("�ϼ�"))
		{
			contentItems[seq - 1] = contentItems[seq - 1] + "\\n";
		}
		// modify end wWX217192 2014-07-14 OR_huawei_201407_304 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_Ӫ������Ʊ����
		
		return contentItems;
    }
    
    /**
     * ��У�����룬ֱ�ӻ�ȡ�û���Ϣ
     * 
     * @return
     * @remark modify by jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    private String qryUserStatus()
    {
        logger.debug("getUserStatus Starting ...");
        
        HttpServletRequest request = this.getRequest();
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        HashMap<String,String> map = (HashMap<String,String>) feeChargeBean.getUserStatus(servnumber, "", termInfo);
        
        if (map == null)
        {
            logger.error("����ǰ��֤����" + servnumber + "��״̬ʧ��");
            
            setErrormessage("��֤����" + servnumber + "״̬ʧ��");
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����ǰ��֤����״̬ʧ��");
            
            logger.debug("getUserStatus End!");
            
            return "��֤����״̬ʧ��";
        }
        
        String status = map.get("status");
        
        Pattern p = Pattern.compile("US2[A-Z0-9]*");
        Matcher m = p.matcher(status);
        if (m.matches() && !"US28".equals(status))
        {
            logger.error("����" + servnumber + "�����������ܽ���");
            
            setErrormessage("����" + servnumber + "�����������ܽ���");
            this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "״̬��" + status + "�������������ܽ���");
            
            logger.debug("getUserStatus End!");
            
            return "����" + servnumber + "�����������ܽ���";
        }
        
        String region = map.get("servRegion");
        
        // add begin jWX216858 2015-4-23 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
        if ("1".equals(morePhoneFlag))
        {
        	if (!region.equals(termInfo.getRegion()))
        	{
        		logger.error("����" + servnumber + "������û������ڽɷѳ�ֵ�˵����нɷѣ�");
                
            	setErrormessage("����" + servnumber + "������û������ڽɷѳ�ֵ�˵����нɷѣ�");
            	
            	return "����" + servnumber + "������û������ڽɷѳ�ֵ�˵����нɷѣ�";
        	}
        	return "";
        }
        // add begin jWX216858 2015-4-23 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
        
        //�Ƿ���ؽɷ�
        if (!region.equals(termInfo.getRegion()))
        {
        	//modify begin sWX219697 2014-7-19 09:42:45 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
        	//��������
        	regionName = map.get("regionName");
        	
        	//��ؽɷѿ��أ�1��������ؽɷѣ���1����������ؽɷ�
        	//modify begin sWX219697 2014-9-3 08:40:20 ������ؽɷѿ���
        	String chargeSwitch = CommonUtil.getParamValue("SH_YD_CHARGE_SWITCH");
        	//modify begin sWX219697 2014-9-3 08:40:20 ������ؽɷѿ���
        	
        	//�����ɷѿ��عرգ���ú���Ϊʡ�����
        	if (!"1".equals(chargeSwitch) || feeChargeService.anthSdTelnum(region) <= 0)
        	{
            	logger.error("����" + servnumber + "������û������ܽ���");
                
            	setErrormessage("����" + servnumber + "������û������ܽ���");
                this.createRecLog(servnumber, "feeCharge", "0", "0", "0", "����" + servnumber + "������û������ܽ���");
                
                logger.debug("getUserStatus End!");
                
                return "����" + servnumber + "������û������ܽ���";
        	}

        	//��������
        	regionFlag = "0";
        	//modify end sWX219697 2014-7-19 09:43:17 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
        }
        
        logger.debug("getUserStatus End!");
        
        return "";
    }
    
    /**
     * �������ɿ��� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-10 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String getMorePhoneSwitch()
    {
		return CommonUtil.getParamValue(Constants.SH_MOREPHONE_SWITCH);
	}
    
    /**
     * �������ɿ��� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-21 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public List<DictItemPO> getAmountList()
    {
    	return this.getDictItemByGrp("MorePhoneAmount");
    }
    
    /**
     * �������������ֻ����� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-13 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String morePhone()
    {
    	morePhones = this.getMorePhoneList();
    	return "inputNumber";
    }
    
    /**
     * �������ɻ�ȡ�û���Ϣ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public void qryUserStatusMorePhone()
    {
    	logger.debug("qryUserStatusMorePhone start");
        
    	String xml = "";
    	try
    	{
    		// �ж��û�״̬�Ƿ���Խɷѣ�����true���Խɷ�
    		String checkResult = qryUserStatus();
    		
    		if (!"".equals(checkResult))
    		{       
    			xml = "0;,;" + getErrormessage();
    		}
    		else
    		{
    			String bankNo = getTerminalInfoPO().getBankno(); // �к�
    			chargeType = getChargeType("Card"); // �ɷ�����
    			
    			if (StringUtils.isEmpty(bankNo) || StringUtils.isEmpty(chargeType))
    			{
    				logger.error("�������������кŻ�ȡʧ�ܣ�");
    				
    				setErrormessage("�������������к���Ϣ��ȡʧ��");
    				xml = "0;,;"  + getErrormessage();
    			}
    			else
    			{
    				String money = getRequest().getParameter("money");
    				CardChargeLogVO morePhone = feeChargeBean.qryAcountMorePhone(
    						getTerminalInfoPO(),
    						servnumber,
    						curMenuId,bankNo,
    						CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"),
    						"ALL",chargeType,
    						feeChargeService.getChargeLogOID(),
    						money);
    				xml = "1;,;" + morePhone.getMorePhoneStr();
    			}
    		}
    	}
    	catch (ReceptionException e)
    	{
    		logger.error(e.getMessage());
    		xml = "0;,;" + e.getMessage();
    	}
    	logger.debug("qryUserStatusMorePhone end");
    	writeXmlMsg(xml);
    }
    
    /**
     * �������ɻ�ȡ�û���Ϣ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public List<CardChargeLogVO> getMorePhoneList()
    {
    	morePhones =  new ArrayList<CardChargeLogVO>();
    	
    	if (CommonUtil.isEmpty(morePhoneInfo))
    	{	
    		return morePhones;
    	}
    	String[] morePhoneInfos = morePhoneInfo.split(",");
    	
    	for (int i = 0; i < morePhoneInfos.length; i++)
    	{
    		String[] infos = morePhoneInfos[i].split("~",-1);
    		CardChargeLogVO cardCharge = new CardChargeLogVO();
    		cardCharge.setServnumber(infos[0]);
    		cardCharge.setServRegion(infos[1]);
    		cardCharge.settMoney(infos[2]);
    		cardCharge.setBalance(infos[3]);
    		cardCharge.setYingjiaoFee(infos[4]);
    		cardCharge.setCustName(infos[5]);
    		cardCharge.setAcceptType(infos[6]);
    		cardCharge.setChargeLogOID(infos[7]);
    		cardCharge.setRecoid(infos[8]);
    		cardCharge.setDealnum(infos[9]);
    		cardCharge.setChargeStatus(infos[10]);
    		morePhones.add(cardCharge);
    	}
    	return morePhones;
    }
    
    /**
     * �������ɳ�ֵ�˻���Ϣ��ѯ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String qryAcountMorePhone()
    {
    	logger.debug("qryAcountMorePhone begin");
    	chargeType = getChargeType("Card"); // �ɷ�����
    	
    	String forward = "qryAcountMorePhone";
    	usableTypes = getPayMenu();
        
    	// findbugs�޸� 2015-09-02 zKF66389
    	//logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
    	// findbugs�޸� 2015-09-02 zKF66389
        
    	// findbugs�޸� 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
    	if (usableTypes.size() == 0)
        // findbugs�޸� 2015-09-02 zKF66389
        {
            // û�п��õĳ�ֵ��ʽ
            setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
            
            forward = "failed";
            // ��¼��־
            this.createRecLog(Constants.SH_MOREPHONE, "", "0", "1", "��ʱû�п��õĳ�ֵ��ʽ");
        }
        morePhones = this.getMorePhoneList();
        
        if (morePhones.size() < 1)
        {
        	setErrormessage("û�л�ȡ���û���Ϣ");
        	forward = "failed";
        }
    	logger.debug("qryAcountMorePhone end");
    	return forward;
    }

    /**
     * ��ȡ��ֵ��ʽ�˵� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	private List getPayMenu() {
		// ��ȡ�ն˻�id
        String groupID = getTerminalInfoPO().getTermgrpid();
        
        List<MenuInfoPO> menus = null;
        
        if (!StringUtils.isEmpty(groupID))
        {
        	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
        }
        
        // ��ȡ֧����ʽ�˵�
        return CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
	}
    
    /**
     * ת������������ҳ�� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String readCardMorePhone()
    {
    	String forward = "readCardMorePhone";
        
    	try
    	{
        	morePhones = this.getMorePhoneList();
        	
            // ��ȡ����������ˮ
            chargeLogOID = feeChargeService.getChargeLogOID();
            
            feeChargeService.addCardLog(chargeLogOID, morePhones, getTerminalInfoPO(), chargeType);
            
            // ���γ�ʱ������ҳ�Ĺ���
            getRequest().setAttribute("sdCashFlag", "1");
        }
        catch (Exception e)
        {
            logger.error("�ۿ�֮ǰ��¼��־ʧ�ܣ�", e);
            
            this.createRecLog(Constants.SH_MOREPHONE, "", "0", "1", "�ۿ�֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            
            setErrormessage("�ۿ�֮ǰ��¼��־ʧ�ܣ��޷�ʹ�����������г�ֵ");
            
            forward = "error";
        }
        
        return forward;
    }
    
    /**
     * ���»���������־ <������ϸ����>
     * 
     * @return
     * @throws Exception 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public void updateMoreChargeLog() throws Exception
    {
        logger.debug("updateMoreChargeLog Starting...");
        
        getResponse().setContentType("text/xml;charset=GBK");
        getRequest().setCharacterEncoding("UTF-8");
        morePhoneInfo = java.net.URLDecoder.decode(getRequest().getParameter("morePhoneInfo"),"UTF-8");
        
    	morePhones = this.getMorePhoneList();
    	
    	String xml = "1";
    	for (int i = 0; i < morePhones.size(); i++)
    	{
    		try
    		{
    			// ���½ɷ���־
	    		feeChargeService.updateMorePhoneLog(cardChargeVO, morePhones.get(i), getTerminalInfoPO());
    		}
    		catch (Exception e)
    		{
    			xml = "0";
    			logger.error(morePhones.get(i).getServnumber() + " �û����½ɷ���־�쳣");
    			logger.error("�ۿ�֮�������־�쳣��", e);
    		}
    	}
        
        logger.debug("updateCardChargeLog end!");
        
        writeXmlMsg(xml);
    }
    
    /**
     * ���������ύ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String morePhoneCommit()
    {
    	logger.debug("morePhoneCommit start");

    	morePhones = this.getMorePhoneList();
        String forward = "success";
        
        String referer = getRequest().getHeader("Referer");
        if (null == referer)
        {
            setErrormessage("��Ч����");
            
            return "failed";
        }
        
        CardChargeLogVO morePhone = null;
        StringBuffer sb = new StringBuffer();
        
        // ѭ���ɷ�
        for (int i = 0; i < morePhones.size(); i++)
        {
        	// ���п��ɷ�
        	morePhone = feeChargeBean.chargeCommitMorePhone(getTerminalInfoPO(),
                     morePhones.get(i), curMenuId, chargeType);
           
        	// ������ˮ
            morePhones.get(i).setRecoid(morePhone.getRecoid());
            
            // boss��ˮ
            morePhones.get(i).setDealnum(morePhone.getDealnum());
            
            // �ͻ�����
            morePhones.get(i).setCustName(morePhone.getCustName());
            
            // �ɷ�״̬,0:�ɹ�
            morePhones.get(i).setChargeStatus(morePhone.getChargeStatus());
            
            // ����ʱ�䣬��ӡƾ����
            pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            sb.append(morePhones.get(i).getMorePhoneStr()).append(",");
            // ���½ɷ���־
            feeChargeService.updateChargeLog(morePhone);
            
            // ��¼ҵ����־
            this.createRecLog(morePhones.get(i).getServnumber(), Constants.SH_MOREPHONE, "0", "0", 
            		morePhone.getChargeStatus(), morePhone.getDescription());
        }
        morePhoneInfo = sb.toString();
        morePhoneInfo = morePhoneInfo.substring(0, morePhoneInfo.length() - 1);
        logger.debug("morePhoneCommit end");
        
        return forward;
    }
    
    /**
     * �������ɽ���ҳ�� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-15 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String finish()
    {
    	morePhones = this.getMorePhoneList();
    	return "finish";
    }
    
    /**
     * �������ɴ�ӡȫ����Ʊ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public String printAllInvoice()
    {
    	logger.debug("printAllInvoice begin");
    	String forward = "success";
    	morePhones = this.getMorePhoneList();
    	invoices = new ArrayList<String>();
    	CardChargeLogVO morePhone = null;
    	List<Map<String, String>> list = null;
    	StringBuffer telnum = new StringBuffer(); 
    	
    	int index = 0;

        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
    	//���ӷ�Ʊʱ�����ؽ���б����绰���룬���䣬�ɹ�ʧ��
        List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
        //��ȡ�������ã��Ƿ����õ��ӷ�Ʊ,trueΪ����,��ӡ���ӷ�Ʊʱ���ӿ�ֻ���ش�����Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
    	
    	// ����Ʊ���ݷ��뼯��
    	for (int i = 0; i < morePhones.size(); i++)
    	{
    		index += i;
    		morePhone = morePhones.get(i);
    		
    		// ƴ���ֻ���
    		telnum = telnum.append(morePhone.getServnumber()).append(",");
    		
    		// �ɷѳɹ����ſɴ�ӡ��Ʊ
    		if ("0".equals(morePhone.getChargeStatus()))
    		{
    			// ��ȡ��Ʊ��Ϣ
    			list = getPrintInvoice(morePhone.getServnumber(), morePhone.getRecoid());
    			
    	        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
    	        if("true".equals(isElectronInvoice))
    	        {
    	            Map<String,String> map = new HashMap<String,String>();
                    map.put("telNum", morePhone.getServnumber());
                    map.put("email", morePhone.getServnumber()+"@139.com");
    	            String errormessage = (String)getRequest().getAttribute("telnum");
    	            if(CommonUtil.isNotEmpty(errormessage))
    	            {
    	                map.put("flag", "ʧ��");
    	                logger.info(errormessage);
    	            }
    	            else
    	            {
                        map.put("flag", "�ɹ�");
    	                logger.info("��ӡ���ӷ�Ʊ���û�" + morePhone.getServnumber() + "��ӡ��Ʊ��Ϣ�ɹ�");
    	            }
    	            messages.add(map);
    	        }
    	        //add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
    			
    			// begin zKF66389 2015-09-18 9�·�findbugs�޸�
    			//if(null == list || list.isEmpty())
    			if(list.isEmpty())
    			// end zKF66389 2015-09-18 9�·�findbugs�޸�
    			{
    				logger.info("��ѯ��Ʊ��Ϣʧ��!");
    				
    				invoices.add("");
    			}
    			else
    			{
    				// ��Ʊ��ӡ��ʽ����ֻ����һ�ξ���
    				if (0 == index)
    				{
    					String printType = setPrintType();
    					getRequest().setAttribute("printType", printType);
    				}
    			}
    			invoices.add(getXmlStr(list));
    		}
    	}
    	
    	//add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        getRequest().setAttribute("isElectronInvoice", isElectronInvoice);
        getRequest().setAttribute("messages", messages);
    	//add end by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        
    	getRequest().setAttribute("invoice", invoices);
    	telnums = telnum.toString();
    	logger.debug("printAllInvoice end");
    	return forward;
    }

    /**
     * �������ɷ�Ʊ��ӡ��ʽ���� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	private String setPrintType() 
	{
		// ��Ʊ��ӡ��ʽ���á�1:���̱���A,�����ͺ�A|���̱���C,�����ͺ�C;2:���̱���B,�����ͺ�B|���̱���D,�����ͺ�D;3:���̱���E,�����ͺ�E|���̱���F,�����ͺ�F
		String printType = (String) PublicCache.getInstance().getCachedData("SH_INVOICE_PRINTTYPE");
		if (printType != null && !"".equals(printType.trim()))
		{
			// ���̱���B,�����ͺ�B
			String tmpStr = getTerminalInfoPO().getProvidercode() + "," + getTerminalInfoPO().getMachinemodel();
			int index1 = printType.indexOf(tmpStr);
			if (index1 != -1)
			{
				int index2 = printType.indexOf(";", index1);
				if (index2 != -1)
				{
					// 1:���̱���A,�����ͺ�A|���̱���C,�����ͺ�C;2:���̱���B,�����ͺ�B|���̱���D,�����ͺ�D
					printType = printType.substring(0, index2);
				}
				
				index2 = printType.lastIndexOf(":");
				
				// ��ӡ��ʽ��2
				printType = printType.substring(index2 - 1, index2);
				
			}
		}
		return printType;
	}
    
	/**
     * �������ɴ����� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-4-1 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
	public String goMorePhoneError()
	{
		morePhones = this.getMorePhoneList();
		
		// ѭ��������Ϣ
		for (int i = 0; i < morePhones.size(); i++)
		{
			// ���ô�����Ϣ
			morePhones.get(i).setDescription(errormessage);
			
			// ����������
			morePhones.get(i).setPosResCode(unionRetCode);
			
			if (CommonUtil.isEmpty(tMoney))
			{	
				// 00״̬
				morePhones.get(i).setStatus(Constants.CHARGE_ERROR);
			}
			else
			{
				// 10״̬���ۿ�ɹ����ɷ�ʧ��
				morePhones.get(i).setStatus(Constants.PAYSUCCESS_CHARGEERROR);
			}
			
			// ��֯��������
			morePhones.get(i).setOrgID(getTerminalInfoPO().getOrgid());
			
			// region
			morePhones.get(i).setRegion(getTerminalInfoPO().getRegion());
			
            feeChargeService.updateChargeLog(morePhones.get(i));
            
            this.createRecLog(morePhones.get(i).getServnumber(), Constants.SH_MOREPHONE, "0", "0", "1", errormessage);
		}
		return "goMorePhoneError";
	}
	
	/** 
     * ת������ҳ��
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    public String goToError()
    {
        return ERROR;
    }
    
    /** ��ȡȫ����ӡ��Ϣ
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
     */
    public void getAllPrintInfo()
    {
        // ȫ����ӡ
        if ("all".equals(this.printAllFlag))
        {
            // �Ѵ�ӡ����
            String printInvTelnumFormat = this.printInvTelnum;
            
            // �ǿ��ҵ�һ���ַ�Ϊ����
            if ( !"".equals(printInvTelnumFormat) && printInvTelnumFormat.charAt(0) == ',')
            {
                printInvTelnumFormat = printInvTelnumFormat.substring(1,printInvTelnumFormat.length());
            }
            
            // �Ѵ�ӡ�������
            String[] printInvTelnumStr = printInvTelnumFormat.split(",");
            
            // �ǵ�һ�δ�ӡ
            if (!"".equals(printInvTelnumFormat))
            {
                printInvTelnumLen = printInvTelnumStr.length;
            }
            
            // ������Ϣ�ܸ���
            morePhonesLen = this.getMorePhoneList().size();
            servnumber = morePhones.get(printInvTelnumLen).getServnumber();
            recoid = morePhones.get(printInvTelnumLen).getRecoid();
            tMoney = morePhones.get(printInvTelnumLen).gettMoney();
        }
    }
    
    /** ��ӡǰ���������½
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
     */
    public String goServicePwdPage()
    {
        getAllPrintInfo();
        return "goServicePwdPage";
    }
    
    /** ��ӡǰ��������½
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
     */
    public String goRandomPwdPage()
    {
        getAllPrintInfo();
        return "goRandomPwdPage";
    }
    
    /**
     * ͨ���ֻ����롢�����������������֤
     * 
     * @return
	 * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
     */
    public String loginWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd Starting ...");
        }
        
        String forward = "failed";
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        // ����������ܱ�־(����)��1:����
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
                
        LoginErrorPO loginErrorPO = null;
        if (!Constants.PROOPERORGID_SD.equals(province))
        {
            loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
            if (loginErrorPO != null && isLocked(loginErrorPO))
            {
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                // ������
                logger.error("���ڷ����������������������ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ������");
                
                if (Constants.PROOPERORGID_NX.equals(province))
                {
                    this.getRequest().setAttribute("errormessage", "���ڷ����������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������");
                }
                else
                {
                    this.getRequest().setAttribute("errormessage", "���ڷ����������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����");
                }
                
                return forward;
            }
        }

        if (Constants.PROOPERORGID_SD.equals(province))
        {
            //modify begin g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����
            String newpwd = password;
            
            try
            {
                DESedeEncrypt encrypt = new DESedeEncrypt();
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("ɽ��������֤ʱ�������������", e);
            }
            
            // ����CRMͳһ��֤�ӿڽ�����֤
            Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
            //modify end g00140516 2012/03/24 R003C12L02n01 ɽ������������3DES����

            // 100:�ɹ� 102:ȱʡ���� 119:��ʼ����
            if (returnMap != null && "100".equals(String.valueOf(returnMap.get("errcode"))))// 100:�ɹ� 
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("�û�" + servnumber + "ʹ�÷����������������֤�ɹ�");
                }
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                
                if (customerSimpMap.get("customerSimp") != null)
                {
                    // ȡ��¼�û���Ϣ
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                          
                    // ����session��Ϣ
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //���ֻ����뼰����֤��ʽ
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //����굥����
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                        //����˵�����
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��

                        //����û���Ϣ
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //���µ��û���Ϣ�����session��
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //�����û���loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷����������������֤�ɹ�");
                    
                    forward = "success";
                }
            }
            // ��ʼ�����ȱʡ����-->���������֤-->�����޸�ҳ��
            else if (returnMap != null && ("102".equals(String.valueOf(returnMap.get("errcode"))) || "119".equals(String.valueOf(returnMap.get("errcode")))))
            {
                // �����������
                String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
                
                // ��������������
                String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMessage = shortMessage.replace("[%1]", randomPwd);
                
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                customerSimp.setServNumber(servnumber);
                
                if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
                {
                    logger.error("���û�" + customerSimp.getServNumber() + "��������������ʧ��");
                    
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "���������ŷ���ʧ�ܡ�");
                    
                    this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����Ժ����ԡ�");
                    
                    forward = "failed";
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("���û�" + servnumber + "�������������ųɹ��������" + randomPwd);
                    }
                    
                    forward = "randomcode_editPwd";
                }
            }
            // ��¼ʧ��
            else
            {
                loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
                
                logger.error("ʹ�÷����������������֤ʧ�ܣ��ֻ����룺" + servnumber);
                
                if (returnMap != null)
                {
                    this.getRequest().setAttribute("errormessage", returnMap.get("returnMsg"));
                }
                else
                {
                    this.getRequest().setAttribute("errormessage", "ʹ�÷������������֤ʧ�ܡ�");
                }
                this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ�÷������������֤ʧ�ܡ�"); 
                
                forward = "failed";
            }
        }
        else
        {
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            Map customerSimpMap = null;
            if (Constants.PROOPERORGID_NX.equals(province) && "1".equals(encryptFlag))
            {
                String newpwd = password;
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                }
                catch (Exception e)
                {
                    logger.error("����������֤ʱ�������������", e);
                }
                
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, newpwd, termInfo);

            }
            else
            {   
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            if (customerSimpMap.get("customerSimp") == null)
            {
                // ��¼ʧ��
                loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
                
                logger.error("ʹ�÷����������������֤ʧ�ܣ��ֻ����룺" + servnumber);
                
                // modify begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
                String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
                        "zz_04_01_000001", String.valueOf(customerSimpMap.get("errcode")), 
                        null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // add begin cKF76106 2013/02/05  �����û���¼ʧ�ܸ��ݴ���ת����ʾ��Ϣ
                if (Constants.PROOPERORGID_NX.equals(province) && "713".equals(String.valueOf(customerSimpMap.get("errcode"))))
                {
                    // [CDATA[BUSINESSID:BegChargeFee_ZZZD,����[15138053999]��ѯ������ʧ��]]
                    this.getRequest().setAttribute("errormessage", "���û��������ƶ��û������������в���");
                }
                // add end cKF76106 2013/02/05  �����û���¼ʧ�ܸ��ݴ���ת����ʾ��Ϣ
                else if (Constants.PROOPERORGID_NX.equals(province))
                {
                    int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                    String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                    
                    if ((loginErrorPO == null && 1 < maxTimes) 
                            || (loginErrorPO != null && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                    {
                        this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ����˳�����������֤");
                    }
                    else
                    {
                        //this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ��������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����");
                        this.getRequest().setAttribute("errormessage", "����������֤ʧ�ܣ��������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������");
                    }
                }
                
                this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
                // modify end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            }
            else
            {   
                //add begin ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
                if (Constants.PROOPERORGID_NX.equals(province)
                        && userLoginBean.valiIsfirstpwd(termInfo, curMenuId, servnumber))
                {
                    // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    forward = "initPwdLogin";
                    // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    
                }
                //add end ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
             
                else
                { 
                    if (logger.isInfoEnabled())
                    {
                        logger.info("�û�" + servnumber + "ʹ�÷����������������֤�ɹ�");
                    }
                    
                    
                    // add begin lWX5316086 

                    // ���ͬһ����ͬһ������ε�¼
                    if(Constants.PROOPERORGID_NX.equals(province) && 
                            "1".equals((String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_SWITCH")) && 
                              !loginService.checkLoginByServNumber(servnumber,termInfo.getTermid()))
                    {
                        // ���ش�����ʾ
                        this.getRequest().setAttribute("errormessage", (String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_TIPMESSAGE"));
                        
                        return "failed";
                    }
               
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                    
                    // ��¼�ɹ���ɾ����¼
                    loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                    
                    //modify begin g00140516 2011/10/20 R003C11L10n01 �ع�            
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //���ֻ����뼰����֤��ʽ
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //����굥����
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                        //����˵�����
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                        
                        //����û���Ϣ
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //���µ��û���Ϣ�����session��
                        // session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //�����û���loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    //modify end g00140516 2011/10/20 R003C11L10n01 �ع� 
                    
                    // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    // ��������û��Ƽ����־
                    session.removeAttribute(Constants.ALREADY_REC_FLAG);
                    // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷����������������֤�ɹ�");
                    
                    forward = "success";  
                    
                    // add end lWX5316086 
                    
                }
           }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd End");
        }
        
        return forward;
    }
    
    /** �������Ƿ�����
     * <������ϸ����>
     * @param loginErrorPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
     */
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // ��������ﵽ��ϵͳ����
        if (loginErrorPO.getErrorTimes() >= maxTimes)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String lastTime = loginErrorPO.getLastTime();
            
            try
            {
                // ����ʱ��
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // ��ǰʱ��
                String currTime = sdf.format(c.getTime());
                
                // ����ʱ��
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // ����ʱ��Ҫ���ڵ�ǰʱ�䣬����������������ڼ���
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("�жϺ����Ƿ�����ʱ�����쳣��", e);
            }
        }
        
        // ���������δ�ﵽϵͳ����
        return false;
    }
    
    /**
     * ʹ��������������֤
     * 
     * @return
     * @see 
     */
    public String loginWithRandomPwdNew()
    {
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, password);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�û�" + servnumber + "ʹ������������������֤�ɹ�");
            }
            
            // ��֤�ɹ���ɾ����¼
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // �û���Ϣ
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // ȡ�ͻ���Ϣ
            if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                if (customerSimpMap.get("customerSimp") != null)
                {
                    customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                }
            }
            
            customerSimp.setServNumber(servnumber);
            customerSimp.setLoginMark("0100");
            
            // session�д�ŵ��û���Ϣ
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                // session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                //���ֻ����뼰����֤��ʽ
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��
                //����˵�����
                session.removeAttribute(oldServNumber + "_billhalfyeartrend");
                session.removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 ���˵���ѯΪ������������ͼ�ͷ��ýṹͼ���������˵���Ϣ�浽session��

                if (!servnumber.equals(oldCustomerSimp.getServNumber()))
                {
                    //����û���Ϣ
                    session.removeAttribute(Constants.USER_INFO);
                    
                    //���µ��û���Ϣ�����session��
                    // session.setAttribute(Constants.USER_INFO, customerSimp);
                }
                else
                {
                    oldCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0100"));   
                }
            }
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "���������֤�ɹ���");
            
            forward = "success";
        }
        // ��֤ʧ��
        else 
        {
            // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            // ��ȡ��֤ʧ�ܺ�Ĵ�����Ϣ
            String errorMsg = getErrMsg(loginErrorPO, result);
            // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            
            // ��֤ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("�û�" + servnumber + "����������������ʱ��������֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        return forward;
    }
    
    /**
     * ��ȡ��֤ʧ�ܺ�Ĵ�����Ϣ
     * @param loginErrorPO
     * @param result
     * @return ������Ϣ
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
    private String getErrMsg(LoginErrorPO loginErrorPO, String result)
    {
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        String errorMsg = "";
        
        if ("-1".equals(result))
        {
            errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                if ((null == loginErrorPO && 1 < maxTimes) 
                        || (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                {
                    errorMsg = "���������������Ѿ���ʱ�����˳�����������֤";
                }
                else
                {
                    errorMsg = "���������������Ѿ���ʱ���������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������";
                }
            }
        }
        else
        {
            errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
            
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                  
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                if ((null == loginErrorPO  && 1 < maxTimes) 
                        || (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                {
                    errorMsg = "�����������������˳�����������֤";
                }
                else
                {
                    errorMsg = "���������������������������֤ʧ�ܴ����ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + Integer.parseInt(lockedTime)/60 + "Сʱ������";
                }
            }
        }
        return errorMsg;
    }
	
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }
    
    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    // add end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public boolean isCanPayWithCash()
    {
        return canPayWithCash;
    }
    
    public void setCanPayWithCash(boolean canPayWithCash)
    {
        this.canPayWithCash = canPayWithCash;
    }
    
    public String getHasMultiInvoices()
    {
        return hasMultiInvoices;
    }
    
    public void setHasMultiInvoices(String hasMultiInvoices)
    {
        this.hasMultiInvoices = hasMultiInvoices;
    }
    
    public String getDealNum()
    {
        return dealNum;
    }
    
    public void setDealNum(String dealNum)
    {
        this.dealNum = dealNum;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public FeeChargeBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeBean feeChargeBean)
    {
        this.feeChargeBean = feeChargeBean;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String gettMoney()
    {
        return tMoney;
    }

    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
    }

    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getBalance()
    {
        return balance;
    }
    
    public void setBalance(String balance)
    {
        this.balance = balance;
    }
    
    public String getCardnumber()
    {
        return cardnumber;
    }
    
    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }
    
    public String getUnionpaycode()
    {
        return unionpaycode;
    }
    
    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    
    public String getUnionpayuser()
    {
        return unionpayuser;
    }
    
    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }
    
    public String getBusitype()
    {
        return busitype;
    }
    
    public void setBusitype(String busitype)
    {
        this.busitype = busitype;
    }
    
    public String getBatchnum()
    {
        return batchnum;
    }
    
    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
    }
    
    public String getUnionpayfee()
    {
        return unionpayfee;
    }
    
    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
    }
    
    public String getUnionpaytime()
    {
        return unionpaytime;
    }
    
    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }
    
    public String getAuthorizationcode()
    {
        return authorizationcode;
    }
    
    public void setAuthorizationcode(String authorizationcode)
    {
        this.authorizationcode = authorizationcode;
    }
    
    public String getBusinessreferencenum()
    {
        return businessreferencenum;
    }
    
    public void setBusinessreferencenum(String businessreferencenum)
    {
        this.businessreferencenum = businessreferencenum;
    }
    
    public String getVouchernum()
    {
        return vouchernum;
    }
    
    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }
    
    public FeeChargeService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }
    
    public String getYingjiaoFee()
    {
        return yingjiaoFee;
    }
    
    public void setYingjiaoFee(String yingjiaoFee)
    {
        this.yingjiaoFee = yingjiaoFee;
    }
    
    public String getServRegion()
    {
        return servRegion;
    }
    
    public void setServRegion(String servRegion)
    {
        this.servRegion = servRegion;
    }
    
    public String getAcceptType()
    {
        return acceptType;
    }
    
    public void setAcceptType(String acceptType)
    {
        this.acceptType = acceptType;
    }
    
    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public String getChargeLogOID()
    {
        return chargeLogOID;
    }
    
    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    public String getChargeType()
    {
        return chargeType;
    }
    
    public void setChargeType(String chargeType)
    {
        this.chargeType = chargeType;
    }
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
	// add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371   
    public String getpDealTime()
    {
        return pDealTime;
    }
    
    public void setpDealTime(String pDealTime)
    {
        this.pDealTime = pDealTime;
    }
    // add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getUnionRetCode()
    {
        return unionRetCode;
    }

    public void setUnionRetCode(String unionRetCode)
    {
        this.unionRetCode = unionRetCode;
    }
        
    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public InvoicePrintPO getInvoicePrint()
    {
        return invoicePrint;
    }

    public void setInvoicePrint(InvoicePrintPO invoicePrint)
    {
        this.invoicePrint = invoicePrint;
    }

	public String getRegionFlag() {
		return regionFlag;
	}

	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public List<CardChargeLogVO> getMorePhones() {
		return morePhones;
	}

	public void setMorePhones(List<CardChargeLogVO> morePhones) {
		this.morePhones = morePhones;
	}

	public String[] getServnumbers() {
		return servnumbers;
	}

	public void setServnumbers(String[] servnumbers) {
		this.servnumbers = servnumbers;
	}

	public String getMorePhoneFee() {
		return morePhoneFee;
	}

	public void setMorePhoneFee(String morePhoneFee) {
		this.morePhoneFee = morePhoneFee;
	}

	public List<String> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<String> invoices) {
		this.invoices = invoices;
	}

	public void setMorePhoneFlag(String morePhoneFlag) {
		this.morePhoneFlag = morePhoneFlag;
	}

	public String getMorePhoneFlag() {
		return morePhoneFlag;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getMonthInvoiceFlag() {
		return monthInvoiceFlag;
	}

	public void setMonthInvoiceFlag(String monthInvoiceFlag) {
		this.monthInvoiceFlag = monthInvoiceFlag;
	}

    /**
     * @return ���� cashDetail
     */
    public String getCashDetail()
    {
        return cashDetail;
    }

    /**
     * @param ��cashDetail���и�ֵ
     */
    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
    }

	public CardChargeLogVO getCardChargeVO() {
		return cardChargeVO;
	}

	public void setCardChargeVO(CardChargeLogVO cardChargeVO) {
		this.cardChargeVO = cardChargeVO;
	}

	public String getPrintPayProFlag() {
		return printPayProFlag;
	}

	public void setPrintPayProFlag(String printPayProFlag) {
		this.printPayProFlag = printPayProFlag;
	}

	public String getPrintAllInvFlag() {
		return printAllInvFlag;
	}

	public void setPrintAllInvFlag(String printAllInvFlag) {
		this.printAllInvFlag = printAllInvFlag;
	}

	public String getTelnums() {
		return telnums;
	}

	public void setTelnums(String telnums) {
		this.telnums = telnums;
	}

	public String getMorePhoneInfo() {
		return morePhoneInfo;
	}

	public void setMorePhoneInfo(String morePhoneInfo) {
		this.morePhoneInfo = morePhoneInfo;
	}

	public String getPrintInvTelnum() {
		return printInvTelnum;
	}

	public void setPrintInvTelnum(String printInvTelnum) {
		this.printInvTelnum = printInvTelnum;
	}
	
	// add begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrintFlag()
    {
        return printFlag;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }
    public String getPrintAllFlag()
    {
        return printAllFlag;
    }
    
    public void setPrintAllFlag(String printAllFlag)
    {
        this.printAllFlag = printAllFlag;
    }
	// add end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�

    public int getPrintInvTelnumLen()
    {
        return printInvTelnumLen;
    }

    public void setPrintInvTelnumLen(int printInvTelnumLen)
    {
        this.printInvTelnumLen = printInvTelnumLen;
    }

    public int getMorePhonesLen()
    {
        return morePhonesLen;
    }

    public void setMorePhonesLen(int morePhonesLen)
    {
        this.morePhonesLen = morePhonesLen;
    }

    public String getMorePhonesFlag()
    {
        return morePhonesFlag;
    }

    public void setMorePhonesFlag(String morePhonesFlag)
    {
        this.morePhonesFlag = morePhonesFlag;
    }


}