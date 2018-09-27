/*
 * �� �� ��:  AcrTheYearActFuncAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  Ԥ������Action��
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-11-27
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.prestoredReward.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.PrestoredRewardBean;
import com.customize.sd.selfsvc.prestoredReward.model.ActivityLogPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.prestoredReward.service.IPrestoredRewardService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * Ԥ������Action��
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-11-27]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ����� 
 */
public class PrestoredRewardAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Log logger = LogFactory.getLog(PrestoredRewardAction.class);
	
	/**
	 * service
	 */
	private transient IPrestoredRewardService prestoredRewardService;
	
	/**
	 * bean
	 */
	private transient PrestoredRewardBean prestoredRewardBean;
	
	/**
	 * ��б�
	 */
	private List<PrestoredRewardPO> prestoredRewardList = new ArrayList<PrestoredRewardPO>();
	
	/**
	 * ������Ϣ
	 */
	private String errormessage;

	/**
	 * ��ǰ�˵�
	 */
	private String curMenuId;;
	
	/**
	 * ��ǰҳ 
	 */
	private int page;
	
	/**
	 * ��ҳ��
	 */
	private int totalPages;
	
	/**
	 * ҳ����
	 */
	private int pageSize = 5;
	
	/**
	 * �����
	 */
	private String activityDesc;
	
	/**
	 * �����
	 */
	private String activityId;
	
	/**
	 * ���α���
	 */
	private String actLevelId;
	
	/**
	 * ��������
	 */
	private String actLevelName;
	
	/**
	 * �������
	 */
	//findbugs�޸�
	private String prepayFee;
	
	/**
	 * ���id
	 */
	private String groupId;
	
	/**
	 * �������
	 */
	private String groupName;
	
	/**
	 * ��Ʒ���봮
	 */
	private String actreward = "";
	
	/**
	 * СƱ��Ʒ��Ϣ
	 */
	private String awardDesc;
	
	/**
	 * ���ѷ�ʽ�˵�
	 */
	private List<MenuInfoPO> payTypeFunc;
	
	/**
	 * �ֻ���
	 */
	private String telNum;
	
	/**
	 * ��������
	 */
	private String payType;
	
	/**
	 * �ն˻���ˮ
	 */
	private String terminalSeq;
	
	/**
	 * ���ѽ��
	 */
	private String tMoney;
	
	/**
	 * ���ѽ��
	 */
	private String tMoney_fen;
	
	/**
	 * Ӧ������
	 */
	private String recFee = "0";
	
	/**
	 * Ԥ�����
	 */
	private String preFee;
	
	/**
	 * ������ˮ
	 */
	private String recoid;
	
	/**
     * �����ۿ�ӿڵķ�����
     */
    private String unionRetCode = "";
	
	/**
	 * ������СƱ��ӡ��Ϣ
	 */
	private String printcontext = "";
	
	/**
	 * �Ƿ�Ϊʵ�� 1:ʵ�0����ʵ��
	 */
	private String isGoods = "0"; 
	
	/**
	 * �ɷ���ˮ
	 */
	private String chargeLogOID;
	
	// ------------------------------�������ɷ�����ֶ�--------------------------------------
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
	
	/** 
     * ��ȡ�û����û
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
     */
	public String qryActivitiesList()
	{
		logger.debug("qryActivitiesList Starting...");
		
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		
		// ����
		prestoredRewardPO.setRegion(getTerminalInfoPO().getRegion());

		try
		{
			// ҵ����Ч��У��
			prestoredRewardBean.checkRecValid(getTerminalInfoPO(), getCustomerSimp(), curMenuId);
			
			// ��ȡ�û��Ѵ��ڵĵ���
			actLevelId = prestoredRewardBean.qrySubsActLevelList(getTerminalInfoPO(), getCustomerSimp(), curMenuId);
			if ("".equals(actLevelId))
			{
				actLevelId = "''";
			}
			
			// ���α���
			prestoredRewardPO.setActLevelId(actLevelId);
			
			prestoredRewardPO.setRegion(getTerminalInfoPO().getRegion());
			
			// ��ѯ�û����û
			prestoredRewardList = prestoredRewardService.getActivitiesList(prestoredRewardPO);
			
			// �󶨿��û��������ҳʱʹ��
			this.getRequest().setAttribute("recordCount", prestoredRewardList.size());
			
			// �Ի�ȡ�Ļ���з�ҳ
			prestoredRewardList = this.getPageList(prestoredRewardList);
			
			logger.debug("qryActivitiesList end!");
			return "qryActivitiesList";
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
	}

	/** 
     * ��ѯ��������
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
     */
	public String queryActLevelDesc()
	{
		logger.debug("queryActLevelDesc Starting...");
		try
		{
			// ���ýӿڲ�ѯ��Ʒ�б�
			PrestoredRewardPO prestoredRewardPO = prestoredRewardBean.qryRewardList(getTerminalInfoPO(), getCustomerSimp(), curMenuId, actLevelId, activityId);
			
			// �Ƿ�Ϊʵ��
			isGoods = prestoredRewardPO.getIsGoods();
			
			// ��Ʒ���봮
			actreward = prestoredRewardPO.getActreward();
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
		logger.debug("queryActLevelDesc end!");
		
		return "queryActLevelDesc";
	}
	
	/** 
     * ѡ��֧����ʽ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
     */
	public String selectPayType()
	{
		logger.debug("selectPayType Starting...");
		
		// �ֻ���
		telNum = getCustomerSimp().getServNumber();
		
		// ִ�лԤ����
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("1"); // 1��Ԥ������0������
		prestoredRewardPO.setPaytype("cash");// ֧����ʽcash:�ֽ�  card:������
		prestoredRewardPO.setActivityId(activityId);// �����
		prestoredRewardPO.setActLevelId(actLevelId);// ���α���
		prestoredRewardPO.setActreward(actreward);// ��Ʒ���봮
		prestoredRewardPO.setTotalFee("");// �û�Ͷ��ķ���
		
		try 
		{
			// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
			// ����bean��ִ�лԤ����
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, "", "");
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

			// ��ȡ����ã���Ӧ�����
			recFee = prestoredRewardBean.qryActivityFee(getTerminalInfoPO(), getCustomerSimp(), curMenuId, activityId, actLevelId, actreward);
			
			recFee = CommonUtil.fenToYuan(recFee);
			List<MenuInfoPO> menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(getTerminalInfoPO().getTermgrpid());
			
			// ��ȡ���ѷ�ʽ�˵�
			payTypeFunc = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
			
			logger.debug("selectPayType end!");
			return "selectPayType";
		}
		catch (ReceptionException e)
		{
			setErrormessage(e.getMessage());
			return "error";
		}
	}
	
	/**
     * ת��Ͷ��ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cashCharge()
    {
        return SUCCESS;
    }
	
    /**
     * �ֽ𽻷����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cashFinish()
    {
    	logger.debug("cashFinish Starting...");
    	
    	String forward = "success";
    	
    	// ��ֹ�û���Ͷ�ң�ֱ�Ӵ��������ģ�⽻������
    	String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("��Ч����");
            
            return "error";
        }   
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // ��ȡ������־��ˮ
        chargeLogOID = prestoredRewardService.getChargeLogOID();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        
        String status = "01"; // �ɷ�״̬
        payType = "Cash"; // �ֽ�ɷ�
        String description = "����֮ǰ��¼Ͷ����־"; // ���׽������
        
    	// ���ӽɷ�ǰ��־
    	this.addChargeLog(status, payType, description, Constants.PAYBYMONEY);

    	//============================Ԥ������������־=================================
    	ActivityLogPO activityLogPO = new ActivityLogPO();
    	activityLogPO.setChargeId(chargeLogOID); // �ɷ���־��ˮ
    	activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // ����
    	activityLogPO.setTelNum(telNum); // �û��ֻ�����
    	activityLogPO.setActivityId(activityId); // �����
    	activityLogPO.setActLevelId(actLevelId); // ���α���
    	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // Ԥ��������������
    	activityLogPO.setTotalFee(CommonUtil.yuanToFen(tMoney)); // �û�ʵ�ɽ��
    	activityLogPO.setPreFee("0"); // �û�Ԥ����
    	activityLogPO.setPayType("4"); // ֧����ʽ �ɷѷ�ʽ��1����������4���ֽ�
    	
		// ִ�л����
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("0"); // 1��Ԥ������0������
		prestoredRewardPO.setPaytype("cash");// ֧����ʽcash:�ֽ�  card:������
		prestoredRewardPO.setActivityId(activityId);// �����
		prestoredRewardPO.setActLevelId(actLevelId);// ���α���
		prestoredRewardPO.setActreward(actreward);// ��Ʒ���봮
		prestoredRewardPO.setTotalFee(CommonUtil.yuanToFen(tMoney));// �û�Ͷ��ķ���
		
		try 
		{
		    // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
		    // ҵ�����ǰ��¼ҵ�������Ϣ
		    prestoredRewardBean.writeNetFeeInfo(getTerminalInfoPO(), 
		        getCustomerSimp(), curMenuId, telNum, this.getChargeType("Cash"), terminalSeq, prestoredRewardPO);
		    
			// ����bean��ִ�лԤ����
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, this.getChargeType("Cash"), terminalSeq);
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

			selfCardPayLogVO.setStatus("11"); // �ɷ�״̬
			selfCardPayLogVO.setDescription("Ԥ�����������ɹ�"); // ����
			selfCardPayLogVO.setDealnum(recoid); // BOSS������ˮ
			
			// ��¼ҵ��ɹ���־
            this.createRecLog(telNum,Constants.PAY_BYCASH,chargeLogOID,tMoney,"0","Ԥ����������:�����ն˽ɷѳɹ�!");
            
            // Ԥ������������־
            activityLogPO.setRecStatus("02"); // ����״̬
            activityLogPO.setRedStatusDesc("Ԥ�����������ɹ�"); // ����״̬����
            
            //modify begin lWX431760  2017-06-08 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            recFee = prepayFee; // Ӫ����������
            preFee = String.valueOf(new BigDecimal(tMoney).subtract(new BigDecimal(prepayFee)));
            //modify end lWX431760  2017-06-08 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            
            // Ԥ������������־
        	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // Ԥ��������������
        	activityLogPO.setPreFee(CommonUtil.yuanToFen(preFee)); // �û�Ԥ����� 
		}
		catch (ReceptionException e)
		{
			selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("Ԥ�����������ֽ�ɷ�ʧ�ܣ�");

			// ��¼ҵ��ɹ���־
            this.createRecLog(telNum,Constants.PAY_BYCASH,chargeLogOID,tMoney,"1","Ԥ����������:�����ն˽ɷ�ʧ��!");

            // Ԥ������������־
            activityLogPO.setRecStatus("01"); // ����״̬
            activityLogPO.setRedStatusDesc("Ԥ����������ʧ��,ƾ����ƾ����Ӫҵ�������˿�!"); // ����״̬����

			setErrormessage(e.getMessage());
			forward = "error";
		}
		tMoney = CommonUtil.fenToYuan(CommonUtil.yuanToFen(tMoney));
		// ���½ɷ���־
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
		// ����Ԥ������������־
		prestoredRewardService.createActivityLog(activityLogPO);
		
		logger.debug("cashFinish end!");
		
    	return forward;
    }
    
    /**
     * �ֽ𽻷��쳣
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cashError()
    {
    	logger.debug("cashError Starting...");
    	
    	// ��ȡ������־��ˮ
        chargeLogOID = prestoredRewardService.getChargeLogOID();
    	
    	// ����״̬
    	String status = "10";
    	
    	// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
    	// �ж��û��Ƿ�Ͷ��
    	if (CommonUtil.isEmpty(tMoney))
    	{
    		status = "00";
    	}
    	else
    	{
    	    ActivityLogPO activityLogPO = new ActivityLogPO();
            activityLogPO.setChargeId(chargeLogOID); // �ɷ���־��ˮ
            activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // ����
            activityLogPO.setTelNum(telNum); // �û��ֻ�����
            activityLogPO.setActivityId(activityId); // �����
            activityLogPO.setActLevelId(actLevelId); // ���α���
            activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // ��������
            activityLogPO.setTotalFee(CommonUtil.yuanToFen(tMoney)); // �û�ʵ�ɽ��
            activityLogPO.setPreFee("0"); // �û�Ԥ����
            activityLogPO.setPayType(Constants.PAYBYMONEY); // ֧����ʽ �ɷѷ�ʽ��1����������4���ֽ�
            activityLogPO.setRecStatus("01"); // ����״̬
            activityLogPO.setRedStatusDesc(errormessage); // ����״̬����
            
            // ����Ԥ������������־
            prestoredRewardService.createActivityLog(activityLogPO);
    	}
    	// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
    	
    	tMoney = CommonUtil.fenToYuan(CommonUtil.yuanToFen(tMoney));
    	
    	// ֧����ʽ
    	payType = "Cash";
    	
    	// ��¼ҵ��ʧ����־
    	this.createRecLog(telNum, Constants.PAY_BYCASH, "0", "0", "1", errormessage);
    	
    	// ���ӽɷ���־
    	this.addChargeLog(status, payType, errormessage, Constants.PAYBYMONEY);
    	
    	logger.debug("cashError end!");
    	
    	return "cashError";
    }
    
    /**
     * ���������ѣ�ת����������ҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardCharge()
    {
    	logger.debug("cardCharge Starting...");
    	
    	// ����״̬
    	String status = "00";
    	payType = "Card";
    	String description = "�������ۿ�֮ǰ��¼��־";
    	tMoney = recFee;
    	
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
    	
    	// ��ȡ������־��ˮ
        chargeLogOID = prestoredRewardService.getChargeLogOID();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
    	
    	// �ۿ�֮ǰ���ӽɷ���־
    	this.addChargeLog(status, payType, description, "1");
    	
    	// ���γ�ʱ������ҳ�Ĺ���
        getRequest().setAttribute("sdCashFlag", "1");
        
        logger.debug("cardCharge end!");
        return SUCCESS;
    }
    
    /**
     * �������������
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardFinish()
    {
    	logger.debug("cardFinish Starting...");
    	
    	String forward = "success";
    	
    	// ��ֹ�û���Ͷ�ң�ֱ�Ӵ��������ģ�⽻������
    	String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("��Ч����");
            
            return "error";
        }   
        
        // �������ۿ���
    	while (tMoney.startsWith("0"))
        {
            tMoney = tMoney.substring(1);
        }
    	
    	//============================Ԥ������������־=================================
    	ActivityLogPO activityLogPO = new ActivityLogPO();
    	activityLogPO.setChargeId(chargeLogOID); // �ɷ���־��ˮ
    	activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // ����
    	activityLogPO.setTelNum(telNum); // �û��ֻ�����
    	activityLogPO.setActivityId(activityId); // �����
    	activityLogPO.setActLevelId(actLevelId); // ���α���
    	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // Ԥ��������������
    	activityLogPO.setTotalFee(tMoney); // �û�ʵ�ɽ��
    	activityLogPO.setPreFee("0"); // �û�Ԥ����
    	activityLogPO.setPayType("1"); // ֧����ʽ �ɷѷ�ʽ��1����������4���ֽ�
    	
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // �ɷ���־��ˮ
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        
		// ִ�л����
		PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
		prestoredRewardPO.setOnlycheck("0"); // 1��Ԥ������0������
		prestoredRewardPO.setPaytype("card");// ֧����ʽcash:�ֽ�  card:������
		prestoredRewardPO.setActivityId(activityId);// �����
		prestoredRewardPO.setActLevelId(actLevelId);// ���α���
		prestoredRewardPO.setActreward(actreward);// ��Ʒ���봮
		prestoredRewardPO.setTotalFee(tMoney);// �û�Ͷ��ķ���
		
		try 
		{
		    // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
		    // ҵ�����ǰ��¼ҵ�������Ϣ
            prestoredRewardBean.writeNetFeeInfo(getTerminalInfoPO(), 
                getCustomerSimp(), curMenuId, telNum, this.getChargeType("Card"), terminalSeq, prestoredRewardPO);
		    
			// ����bean��ִ�лԤ����
            recoid = prestoredRewardBean.recRewardCommit(getTerminalInfoPO(),
                getCustomerSimp(), curMenuId, prestoredRewardPO, this.getChargeType("Card"), terminalSeq);
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������

			selfCardPayLogVO.setStatus("11"); // �ɷ�״̬
			selfCardPayLogVO.setDescription("Ԥ�����������ɹ�"); // ����
			selfCardPayLogVO.setDealnum(recoid); // BOSS������ˮ
			
			// ��¼ҵ��ɹ���־
            this.createRecLog(telNum,Constants.PAY_BYCARD,chargeLogOID,tMoney,"0","Ԥ����������:�����ն˽ɷѳɹ�!");
            
            // Ԥ������������־
            activityLogPO.setRecStatus("02"); // ����״̬
            activityLogPO.setRedStatusDesc("Ԥ�����������ɹ�"); // ����״̬����

            //modify begin lWX431760  2017-06-08 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4           
            recFee = prepayFee; // Ӫ����������
            preFee = String.valueOf(new BigDecimal(tMoney).subtract(new BigDecimal(prepayFee)));
           
            // Ԥ������������־
        	activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // Ԥ��������������
        	activityLogPO.setPreFee(CommonUtil.yuanToFen(preFee)); // �û�Ԥ����� 
        	//modify end lWX431760  2017-06-08 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
        	
            tMoney = CommonUtil.fenToYuan(tMoney);
		}
		catch (ReceptionException e)
		{
			selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("Ԥ�����������������ɷ�ʧ�ܣ�");

			// ��¼ҵ��ɹ���־
            this.createRecLog(telNum,Constants.PAY_BYCARD,chargeLogOID,tMoney,"1","Ԥ����������:�����ն˽ɷ�ʧ��!");

        	// Ԥ������������־
            activityLogPO.setRecStatus("01"); // ����״̬
            activityLogPO.setRedStatusDesc("Ԥ����������ʧ��,ƾ����ƾ����Ӫҵ�������˿�!"); // ����״̬����

            
			setErrormessage(e.getMessage());
			forward = "error";
		}
		
		// ���½ɷ���־
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
		// ����Ԥ������������־
		prestoredRewardService.createActivityLog(activityLogPO);
		
		logger.debug("cashFinish end!");
		
    	return forward;
    }
    
    /**
     * �����������쳣
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardError()
    {
    	logger.debug("cardError Starting...");
    	
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
         
        // �ɷ���־��ˮ
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
    	
    	// ����״̬
    	String status = "10";
    	
    	// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
    	// �ж��û��Ƿ�Ͷ��
    	if (CommonUtil.isEmpty(tMoney))
    	{
    		status = "00";
    	}
    	else
        {
            ActivityLogPO activityLogPO = new ActivityLogPO();
            activityLogPO.setChargeId(chargeLogOID); // �ɷ���־��ˮ
            activityLogPO.setRegion(getTerminalInfoPO().getRegion()); // ����
            activityLogPO.setTelNum(telNum); // �û��ֻ�����
            activityLogPO.setActivityId(activityId); // �����
            activityLogPO.setActLevelId(actLevelId); // ���α���
            activityLogPO.setRecFee(CommonUtil.yuanToFen(recFee)); // ��������
            activityLogPO.setTotalFee(tMoney); // �û�ʵ�ɽ��
            activityLogPO.setPreFee("0"); // �û�Ԥ����
            activityLogPO.setPayType(Constants.PAYBYUNIONCARD); // ֧����ʽ �ɷѷ�ʽ��1����������4���ֽ�
            activityLogPO.setRecStatus("01"); // ����״̬
            activityLogPO.setRedStatusDesc(errormessage); // ����״̬����
            
            // ����Ԥ������������־
            prestoredRewardService.createActivityLog(activityLogPO);
        }
        // modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
    	
    	// ����״̬
    	selfCardPayLogVO.setStatus(status); 
    	
    	selfCardPayLogVO.setDescription(errormessage);
    	
    	// ������������
    	selfCardPayLogVO.setPosResCode(unionRetCode);
    	
    	// ֧����ʽ
    	payType = "Card";
    	
    	// ��¼ҵ��ʧ����־
    	this.createRecLog(telNum, Constants.PAY_BYCARD, "0", "0", "1", errormessage);
    	
    	// ���½ɷ���־
		prestoredRewardService.updateChargeLog(selfCardPayLogVO);
    	
    	logger.debug("cardError end!");
    	return SUCCESS;
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
        getResponse().setContentType("text/xml;charset=GBK");
        getRequest().setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("�ۿ�֮ǰ��¼��־ʧ��");
        }
        
        String status = (String)getRequest().getParameter("status"); // ����״̬
        String description = (String)getRequest().getParameter("description"); // 
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // ��װ��־����
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        // �ն˻���������
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        
        // �ն˻�������֯����
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        // �ɷ���ˮ
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        
        // �����̻��ţ��ڿ�����ʶ��
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        
        // ����Ϊˢ�����ն˷����Ψһ���
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        // ҵ������
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        // �û�ˢ������
        cardChargeLogVO.setCardnumber(cardnumber);
        
        // �ն����κ�
        cardChargeLogVO.setBatchnum(batchnum);
        
        // ��Ȩ�루ɽ���ã�
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        
        // ���ײο��ţ�ɽ���ã�
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        
        // ����ʵ�ʿۿ�ʱ��
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        
        // ��֤�ţ�ɽ���ã�
        cardChargeLogVO.setVouchernum(vouchernum);
        
        // ����ʵ�ʿۿ����λ���֣�
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
        
        // �ն˻���ˮ
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
            prestoredRewardService.updateChargeLog(cardChargeLogVO);
            
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
     * ���ӽɷ���־
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public void addChargeLog(String status, String payType, String description, String payTypeNum)
	{
		logger.debug("addChargeLog Starting...");
		
		// ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // ��װ��־����
        selfCardPayLogVO.setChargeLogOID(chargeLogOID); // ��־��ˮ
        selfCardPayLogVO.setRegion(getTerminalInfoPO().getRegion()); // region
        selfCardPayLogVO.setTermID(getTerminalInfoPO().getTermid()); // �ն˻�id
        selfCardPayLogVO.setOperID(getTerminalInfoPO().getOperid()); // ����Աid
        selfCardPayLogVO.setServnumber(telNum); // �ֻ�����
        selfCardPayLogVO.setPayType(payTypeNum); // ֧����ʽ �ֽ�"4"
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney)); // �ɷѽ��
        
        // �ն˻���ˮ
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = getTerminalInfoPO().getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        selfCardPayLogVO.setRecdate(payDate); // ����ʱ��
        selfCardPayLogVO.setAcceptType(""); // BOSS��������
        selfCardPayLogVO.setServRegion(getCustomerSimp().getRegionID()); // �ֻ������������
        selfCardPayLogVO.setOrgID(getTerminalInfoPO().getOrgid()); // ��֯�ṹid
        selfCardPayLogVO.setStatus(status); // Ͷ�ҳɹ�����ʱ״̬
        selfCardPayLogVO.setDescription(description);
        selfCardPayLogVO.setDealnum(""); // BOSS�������
        selfCardPayLogVO.setRecType("activity"); // ��������
        
        String chargeType = this.getChargeType(payType);
        selfCardPayLogVO.setBankno(chargeType + getTerminalInfoPO().getBankno());
        
        // modify begin zKF69263 2015-5-11 OR_SD_201503_333_SD_�����ն˻����Ԥ������
        // ҵ�����ͣ�������ZZACT
        selfCardPayLogVO.setAcceptType(Constants.ACCEPTTYPE_PRESTORED_REWARD);
        // modify end zKF69263 2015-5-11 OR_SD_201503_333_SD_�����ն˻����Ԥ������
        
        // ���ӽɷ�ǰ��־
        prestoredRewardService.addChargeLog(selfCardPayLogVO);
        
        logger.debug("addChargeLog end!");
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
        
        // ��ȡ���еĽɷ�����
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ChargeType);
        
        if (chargeTypeList != null)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                
                // ȡ��ǰ֧�����͵Ľɷ�����
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        return chargeType;
    }
    
	/**
     * ȡ��ǰҳ������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<PrestoredRewardPO> getPageList(List<PrestoredRewardPO> list)
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // ����������
        int countNum = prestoredRewardList.size();
        
        // ������ҳ��
        if (countNum % pageSize > 0)
        {
        	totalPages = countNum / pageSize + 1;
        }
        else
        {
        	totalPages = countNum / pageSize;
        }
        
        // ��ʼ����
        int startNum = pageSize * (page - 1) + 1;
        
        // ��������
        int endNum = pageSize * page;
        
        List<PrestoredRewardPO> preRewardList = new ArrayList<PrestoredRewardPO>();
        
        // ��ʼ����
        for(int i = startNum; i <= endNum; i++)
        {
            if (i <= countNum)
            {
                preRewardList.add(list.get(i-1));
            }
        }
        return preRewardList;
    }

	public List<PrestoredRewardPO> getPrestoredRewardList() {
		return prestoredRewardList;
	}

	public void setPrestoredRewardList(List<PrestoredRewardPO> prestoredRewardList) {
		this.prestoredRewardList = prestoredRewardList;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PrestoredRewardBean getPrestoredRewardBean() {
		return prestoredRewardBean;
	}

	public void setPrestoredRewardBean(PrestoredRewardBean prestoredRewardBean) {
		this.prestoredRewardBean = prestoredRewardBean;
	}

	public IPrestoredRewardService getPrestoredRewardService() {
		return prestoredRewardService;
	}

	public void setPrestoredRewardService(
			IPrestoredRewardService prestoredRewardService) {
		this.prestoredRewardService = prestoredRewardService;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActLevelId() {
		return actLevelId;
	}

	public void setActLevelId(String actLevelId) {
		this.actLevelId = actLevelId;
	}

	public String getActLevelName() {
		return actLevelName;
	}

	public void setActLevelName(String actLevelName) {
		this.actLevelName = actLevelName;
	}


	public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getActreward() {
		return actreward;
	}

	public void setActreward(String actreward) {
		this.actreward = actreward;
	}

	public List<MenuInfoPO> getPayTypeFunc() {
		return payTypeFunc;
	}

	public void setPayTypeFunc(List<MenuInfoPO> payTypeFunc) {
		this.payTypeFunc = payTypeFunc;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getRecFee() {
		return recFee;
	}

	public void setRecFee(String recFee) {
		this.recFee = recFee;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public String getPrintcontext() {
		return printcontext;
	}

	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}

	public String getTMoney_fen() {
		return tMoney_fen;
	}

	public void setTMoney_fen(String money_fen) {
		tMoney_fen = money_fen;
	}

	public String getIsGoods() {
		return isGoods;
	}

	public void setIsGoods(String isGoods) {
		this.isGoods = isGoods;
	}

	public String getChargeLogOID() {
		return chargeLogOID;
	}

	public void setChargeLogOID(String chargeLogOID) {
		this.chargeLogOID = chargeLogOID;
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

	public String getAwardDesc() {
		return awardDesc;
	}

	public void setAwardDesc(String awardDesc) {
		this.awardDesc = awardDesc;
	}

	public String getUnionRetCode() {
		return unionRetCode;
	}

	public void setUnionRetCode(String unionRetCode) {
		this.unionRetCode = unionRetCode;
	}

}