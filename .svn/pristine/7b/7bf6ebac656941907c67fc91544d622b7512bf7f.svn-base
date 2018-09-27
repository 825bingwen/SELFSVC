package com.gmcc.boss.selfsvc.valueCard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.hub.selfsvc.call.SelfSvcCallHub;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.customize.hub.selfsvc.common.service.FeeService;
import com.customize.sd.selfsvc.call.SelfSvcCallSD;
import com.customize.sd.selfsvc.common.service.IFeeServiceSD;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.common.service.BaseServiceImpl;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;
import com.gmcc.boss.selfsvc.valueCard.dao.ValueCardDaoImpl;
import com.gmcc.boss.selfsvc.valueCard.model.ValueCardVO;

public class ValueCardServiceImpl extends BaseServiceImpl implements ValueCardService 
{
	/**
	 * 日志
	 */ 
    private static Log logger = LogFactory.getLog(ValueCardServiceImpl.class);
    
    /**
     * 有价卡Dao层
     */
    private ValueCardDaoImpl valueCardDao;
    
    /**
     * 湖北专用的调用接口的call层
     */
    private SelfSvcCallHub selfSvcCallHub;
    
    /**
     * 湖北交费service
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
     * 湖北交费日志记录service
     */
    private transient FeeService feeService;
    
    /**
     * 山东专用的调用接口的call层
     */
    private SelfSvcCallSD selfSvcCallSD;
    
    /**
     * 山东交费日志记录service
     */
    private IFeeServiceSD feeServiceSDImpl;
	
    /**
     * <验证购买有价卡的号码是否省内用户>
     * <功能详细描述>
     * @param termInfo
     * @param servnumber
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-06 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	public void qryUserStatus(String servnumber, String curMenuId)
	{
		logger.debug("qryUserStatus start....");
		
		TerminalInfoPO termInfo = this.getTermInfo();
		
		Map<String,String> paramMap = new HashMap<String,String>();
    	
    	// 手机号
        paramMap.put("telnumber", servnumber);
        
        // 操作员id
        paramMap.put("curOper", termInfo.getOperid());
        
        // 终端机id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        // 触摸流水
        paramMap.put("touchoid", "");
        
        // 菜单id
        paramMap.put("menuid", curMenuId);
        
        // 调用查询用户信息接口
        ReturnWrap retUserInfo = selfSvcCall.getUserInfoHub(paramMap);
        
        if(retUserInfo.getStatus() == SSReturnCode.ERROR)
        {
        	throw new ReceptionException(retUserInfo.getReturnMsg());
        }
        
        // 账户信息
        CTagSet ctsUserInfo = (CTagSet)retUserInfo.getReturnObject();
        
        // 将用户信息封装至session中
        getCustomer(ctsUserInfo, servnumber);
        
        // 判断账户信息是否为本省用户
        if(valueCardDao.anthTelnum(ctsUserInfo.GetValue("region")) <= 0)
        {
        	throw new ReceptionException("您输入的号码不是本省号码，请核对您的号码信息！");
        }
        
        // 判断用户是否为停机用户
        if("US30".equals(ctsUserInfo.GetValue("status")))
        {
        	throw new ReceptionException("您输入的号码已停机，无法进行有价卡购买！");
        }
        
        logger.debug("qryUserStatus  end...");
	}
	
	/**
	 * 将用户信息封装至session中，以便后续使用
	 * @param ctsUserInfo
	 * @param servnumber
	 */
	private void getCustomer(CTagSet ctsUserInfo, String servnumber)
	{
		// ----------------------------当前用户信息-------------------------------------------------
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        
        // 区域
        customerSimp.setRegionID(ctsUserInfo.GetValue("region"));
        
        // 手机号码
        customerSimp.setServNumber(servnumber);
        
        // 产品ID
        customerSimp.setBrandID(ctsUserInfo.GetValue("productid"));
        
        // 产品名称
        customerSimp.setBrandName(ctsUserInfo.GetValue("productname"));
        
        // 客户名称
        customerSimp.setCustomerName(ctsUserInfo.GetValue("subname"));
        
        // 放入SESSION
        this.getSession().setAttribute("valueCardUserInfo", customerSimp);
	}
	
	/**
     * <获取有价卡面值信息>
     * <功能详细描述>
     * @param groupId
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-06 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	public List<DictItemVO> getDictItemByGroup(String groupId, String curMenuId, String telnum)
    {
		Map<String, String> inMap = new HashMap<String, String>();
		
		// 获取终端机信息
        TerminalInfoPO terminalInfoPO = this.getTermInfo();
        
        // 设置操作员id
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        inMap.put("touchoid", "");
        
        // 设置当前菜单
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // 设置客户手机号
        inMap.put("telnumber", telnum);
        
        // 地区
        inMap.put("region", terminalInfoPO.getRegion());
		
        // 字典组id
		inMap.put("groupid", groupId);
		
        ReturnWrap rw = selfSvcCallHub.getDictItemByGroup(inMap);
        
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	List<DictItemVO> dictList = new ArrayList<DictItemVO>();
        	
        	// 获取返回的字典项信息
            CRSet crset = (CRSet)rw.getReturnObject();
            
            DictItemVO dictItem = null;
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                dictItem = new DictItemVO(crset.GetValue(i, 0), crset.GetValue(i, 1), groupId);
                
                dictList.add(dictItem);
            }
            return dictList;
        }
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }

    }
	
	/**
     * <获取有价卡购买时的支付方式>
     * <功能详细描述>
     * @param telnum
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-08 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	@SuppressWarnings("unchecked")
	public List<MenuInfoPO> getPayType(String curMenuId, String telnum)
	{
		// 获取终端组信息
		TerminalInfoPO termInfo = this.getTermInfo();
		
		// 根据终端组自缓存中获取菜单列表
        String groupID = termInfo.getTermgrpid();
        
		List<MenuInfoPO> menus = null;
        
        if (!StringUtils.isEmpty(groupID))
        {
        	menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
        }
        
		// 当页菜单列表
        List<MenuInfoPO> usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
        
        logger.info("当前，有价卡购买的支付方式有" + (0 == usableTypes.size() ? 0 : usableTypes.size()) + "种");
        
        if (0 == usableTypes.size())
        {
            // 记录日志
            this.insertRecLogTelnum(telnum, Constants.VALUECARD_RECLOG, "0", "0", Constants.RECSTATUS_FALID, "暂时没有可用的充值方式");
        }
        return usableTypes;
	}
	
	/**
	 * 有价卡购买
	 * @param terminalSeq
	 * @param valueCardVO
	 * @param curMenuId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ValueCardVO> addValueCards(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO, String menuid)
	{
        // 终端信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 用户信息
        NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
        
        // 判断此处是否为重复交费
	    if (Constants.PAYBYMONEY.equals(selfCardPayLogVO.getPayType()) 
	    		&& !checkCashFee(selfCardPayLogVO))
	    {
	        throw new ReceptionException("1;,;重复缴费");
	    }
        
        MsgHeaderPO header = new MsgHeaderPO(menuid, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // 调用有价卡购买接口
        ReturnWrap rw  = selfSvcCall.getValueCards(header, getInParam(selfCardPayLogVO, valueCardVO));
        
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
        	Vector vector = (Vector) rw.getReturnObject();
        	
        	CTagSet tagSet = (CTagSet) vector.get(0);
        	
        	// 平台交易流水号
        	String transActionId = tagSet.GetValue("transActionID");
        	
        	CRSet rSet = (CRSet) vector.get(1);
        	
        	// 记录业务日志及更新交费日志
        	updateRecAndChargeLog(selfCardPayLogVO, Constants.RECSTATUS_SUCCESS);
			
        	// 获取购买的有价卡列表信息
        	return getValueCardList(rSet, transActionId);
        }
        else
        {
        	updateRecAndChargeLog(selfCardPayLogVO, Constants.RECSTATUS_FALID);
			
			throw new ReceptionException("2;,;" + rw.getReturnMsg());
        }
	}
	
	/**
	 * 封装个人有价卡购买的入参信息
	 * @param selfCardPayLogVO
	 * @param valueCardVO
	 * @return 封装到Map中的入参
	 * @see
	 */
	private Map<String, String> getInParam(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO)
	{
		// 获取用户信息
		NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
		
		// 封装有价卡购买接口的入参信息
        Map<String, String> inParam = new HashMap<String, String>();
        
        // 销售渠道01：实体营业厅；03：移动热线；04：短信营业厅；05：自助终端；08：网上营业厅； 09：赠送渠道；
        inParam.put("channelType", "05");
        
        // 支付方式（必传）
        inParam.put("elecCardPayType", selfCardPayLogVO.getPayType());
        
        // 卡数量（必传）
        inParam.put("elecCardNum", valueCardVO.getCardNum());
        
        // 卡面值（分，必传）
        inParam.put("elecCardCntTotal", CommonUtil.yuanToFen(valueCardVO.getCntTotal()));
        
        // 卡业务类型（必传）
        if(valueCardVO.getCardType().length() == 1)
        {
        	valueCardVO.setCardType("0" + valueCardVO.getCardType());
        }
        inParam.put("elecCardType", valueCardVO.getCardType());
        
        // TOD 实际支付金额（分，必传）
        inParam.put("elecCardPayMoney", CommonUtil.yuanToFen(selfCardPayLogVO.getFee()));
        
        // 发票付款人姓名（必传）
        inParam.put("elecCardCustName", customer.getCustomerName());
        
        // 购卡手机号码
        inParam.put("telNum", customer.getServNumber());
        
        // 卡业务属性值
        inParam.put("elecCardBusiPro", "");
        
        // 邮箱
        inParam.put("elecCardMailBox", "");
        
        // 证件编码
        inParam.put("elecCardCertID", "");
        
        // 是否发送短信可选，默认为1：发送 0：不发送
        inParam.put("isSendSms", "1");
        
        // 是否可补发可选，默认为1：允许 0不允许
        inParam.put("isReSend", "1");
        
        return inParam;
	}
	
	/**
	 * 更新交费日志信息，同时添加业务日志
	 * @param selfCardPayLogVO
	 * @param statusFlag
	 */ 
	private void updateRecAndChargeLog(CardChargeLogVO selfCardPayLogVO, String statusFlag)
	{
		if(Constants.RECSTATUS_SUCCESS.equals(statusFlag))
		{
			selfCardPayLogVO.setStatus(Constants.CHARGE_SUCCESS);
			selfCardPayLogVO.setDescription("有价卡购买交费成功！");
			
			// 添加业务日志
			this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.VALUECARD_RECLOG, selfCardPayLogVO.getChargeLogOID(),
					selfCardPayLogVO.getFee(), Constants.RECSTATUS_SUCCESS, "有价卡购买成功");
		}
		else
		{
			selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
			selfCardPayLogVO.setDescription("有价卡购买交费失败！");
			
			// 添加业务日志
			this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.VALUECARD_RECLOG, selfCardPayLogVO.getChargeLogOID(),
					selfCardPayLogVO.getFee(), Constants.RECSTATUS_FALID, "有价卡购买失败");
		}
		
		// 更新交费日志
		feeService.updateChargeResult(selfCardPayLogVO);
	}
	
	/**
	 * 遍历接口返回的cardList信息
	 * @param rSet
	 * @param transActionId
	 * @return
	 */
	private List<ValueCardVO> getValueCardList(CRSet rSet, String transActionId)
	{
		// 获取有价卡信息集合
        List<ValueCardVO> cardList = new ArrayList<ValueCardVO>();
        
		ValueCardVO vo = null;
    	
    	for(int i = 0; i < rSet.GetRowCount(); i++)
    	{
    		vo = new ValueCardVO();
    		
    		// 平台交易流水号
    		vo.setTransActionId(transActionId);
    		
    		// 卡号
    		vo.setCardNo(rSet.GetValue(i, 0));
    		
    		// 密码
    		vo.setCardPwd(rSet.GetValue(i, 1));
    		
    		// 卡面值
    		vo.setCntTotal(rSet.GetValue(i, 2));
    		
    		// 卡有效期
    		vo.setExpDate(rSet.GetValue(i, 3));
    		
    		// 卡业务类型
    		vo.setCardType(rSet.GetValue(i, 4));
    		
    		// 卡业务属性值
    		vo.setCardBusiPro(rSet.GetValue(i, 5));
    		
    		cardList.add(vo);
    	}
    	
    	return cardList;
	}
	
	/**
     * 验证流水号是否重复，用户是否在重复交费
     * 
     * @param terminalSeq
     * @param telnum
     * @param tMoney
     * @return
     * @see [类、类#方法、类#成员]
     */
	private boolean checkCashFee(CardChargeLogVO selfCardPayLogVO)
    {
    	// 获取终端机信息
    	TerminalInfoPO termInfo = this.getTermInfo();
        
        String seq = termInfo.getTermid().concat(selfCardPayLogVO.getTerminalSeq());
        
        String tmpSeq = seq.concat(selfCardPayLogVO.getServnumber());
        
        // 如果有相同的串，则是重复缴费
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            String recDate = DateUtilHub.getCurrentDateTime();
            
            String tmpErrorMsg = "重复缴费:手机号[".concat(selfCardPayLogVO.getServnumber())
                    .concat("],投币金额[")
                    .concat(selfCardPayLogVO.getFee())
                    .concat("]元,归属营业厅[")
                    .concat(termInfo.getOrgname())
                    .concat("],流水号[")
                    .concat(seq)
                    .concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(selfCardPayLogVO.getServnumber());
            // 现金投币
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(selfCardPayLogVO.getFee()));
            // 现金缴费流水,终端id+厂商生成流水
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(recDate);
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // 记录重复缴费日志
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // 记录到SH_REC_LOG,交易流水号这里记录现金缴费流水号
            this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(selfCardPayLogVO.getFee()), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
        
    }
	
	/**
     * 现金交费异常处理
     * @param telnum
     * @param payType
     * @param errormessage
     * @param tMoney
     * @param terminalSeq
     * @return
     * @see
     */
    public void updateCashError(CardChargeLogVO selfCardPayLogVO, String errormessage, String tMoney)
    {
        logger.debug("addCashError Starting ...");
        
        // 用户信息
        NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
        
        // 记录现金交费异常的业务日志
        this.insertRecLogTelnum(customer.getServNumber(), Constants.VALUECARD_RECLOG, "0", "0", Constants.RECSTATUS_FALID, errormessage);
        
        feeService.updateCashChargeError(selfCardPayLogVO, errormessage, Constants.VALUECARD_RECLOG, tMoney);
        
        logger.debug("addCashError End...");
    }
    
    /**
     * 扣款之前增加银联卡缴费日志
     * @param telnum
     * @param tMoney
     * @param cardnumber
     * @param terminalSeq
     * @throws Exception
     */
    public String addCardChargeLog(CardChargeLogVO selfCardPayLogVO, String recType)
    {
    	logger.debug("addChargeLog Starting...");
        
        selfCardPayLogVO = feeService.addChargeLog(selfCardPayLogVO, recType);
        
        String xml = "1~~" + selfCardPayLogVO.getChargeLogOID();
        
        logger.debug("addChargeLog End...");
        
        return xml;
    }
    
    /**
     * 扣款成功之后，更新交费日志
     * @param selfCardPayLogVO
     * 
     * @see
     */
    public String updateCardChargeLog(CardChargeLogVO selfCardPayLogVO)
    {
        logger.debug("updateCardChargeLog Starting...");
        
        return feeService.updateCardChargeLog(selfCardPayLogVO);
    }
    
    /**
     * 银联卡交费异常处理
     * @param telnum
     * @param payType
     * @param errormessage
     * @param tMoney
     * @param errorType
     * @param chargeLogOID
     * @param errPosResCode
     * @return
     */
    public void addCardError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney, String errPosResCode, String errorType)
    {
        logger.debug("addCardError Starting ...");
        
        this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.PAY_BYCARD, "0", "0", 
        		Constants.RECSTATUS_FALID, errormessage);
        
        // 添加银联扣款异常日志
        feeService.updateCardChargeError(selfCardPayLogVO, errormessage, recType, tMoney, errPosResCode, errorType);
        
        logger.debug("addCardError End");
    }
    
    /**
     * <有价卡校验>
     * <功能详细描述>
     * @param servnumber 充值手机号码
     * @param curMenuId 菜单
     * @param valueCardPwd 有价卡卡号
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    private void authValueCard(String servnumber, String curMenuId, String valueCardPwd)
    {
        // 获取终端组信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        Map<String, String> map = new HashMap<String, String>();
        
        //电子有价卡密码
        map.put("elecCardNum", valueCardPwd);
        
        //调用接口校验有价卡
        ReturnWrap rw = selfSvcCall.authValueCard(msgHeader, map);
        
        //接口调用失败
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLogTelnum(servnumber, Constants.VALUECARD_CHARGE, "", "", Constants.RECSTATUS_FALID, "有价卡校验失败");
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHKIFEVCCARD))
        {
            String[] openEbusRtn = new String[] {"iseleccard"};
            String[] destRtn = new String[] {"isElecCard"};
            cTagSet = CommonUtil.rtnConvert(cTagSet, BusinessIdConstants.CLI_QRY_CHKIFEVCCARD, openEbusRtn, destRtn);                      
        }
        // modify end lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        
        //不是电子有价卡
        if(!"1".equals(cTagSet.GetValue("isElecCard")))
        {
            insertRecLogTelnum(servnumber, Constants.VALUECARD_CHARGE, "", "", Constants.RECSTATUS_FALID, "该卡不是电子有价卡，无法充值");
            throw new ReceptionException("该卡不是电子有价卡，无法充值！","authError");
        }
    }
    
    /**
     * <有价卡充值>
     * <功能详细描述>
     * @param servnumber 充值手机号码
     * @param curMenuId 菜单
     * @param valueCardPwd 有价卡卡号
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     * @remark modify by hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
     */
    public void valueCardCharge(String servnumber, String curMenuId, String valueCardPwd)
    {
        //首先校验是否电子有价卡
        authValueCard(servnumber, curMenuId, valueCardPwd);
        
        // 获取终端组信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);

        //有价卡充值
        ReturnWrap rw = selfSvcCall.valueCardCharge(msgHeader, valueCardPwd);
        
        //接口调用失败
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLogTelnum(servnumber, Constants.VALUECARD_CHARGE, "", "", Constants.RECSTATUS_FALID, "有价卡充值失败");
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        //充值面额
        String countTotal = cTagSet.GetValue("countTotal");
        
        //add begin lwx439898 2017-05-19 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        //返回值 ：CHARGERESULT
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_ELECCARDCHARGE))
        {
            String[] openEbusRtn = new String[] {"chargeresult"};
            String[] destRtn = new String[] {"chargeResult"};
            cTagSet = CommonUtil.rtnConvert(cTagSet, BusinessIdConstants.CLI_BUSI_ELECCARDCHARGE, openEbusRtn, destRtn);
        }
        //add end lwx439898  2017-05-19 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        
        //充值成功，chargeResult：1
        if("1".equals(cTagSet.GetValue("chargeResult")))
        {
            insertRecLogTelnum(servnumber, Constants.VALUECARD_CHARGE, "", countTotal, Constants.RECSTATUS_SUCCESS, "有价卡充值成功");
        }
        
        //充值失败
        else
        {
            insertRecLogTelnum(servnumber, Constants.VALUECARD_CHARGE, "", "", Constants.RECSTATUS_FALID, "有价卡充值失败");
            throw new ReceptionException("对不起，有价卡充值失败！"); 
        }
    }
    
    /**------------------------- 山东有价卡购买 ------------------------**/
    
    /**
     * <无密码状态下获取用户信息>
     * <功能详细描述>
     * @param telnum 手机号码
     * @param curMenuId 菜单
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-06-13 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    public void qryUserStatusSD(String telnum, String curMenuId)
    {
    	logger.debug("qryUserStatusSD start....");
		
		TerminalInfoPO termInfo = this.getTermInfo();
		
		Map<String,String> paramMap = new HashMap<String,String>();
    	
    	// 手机号
        paramMap.put("telnum", telnum);
        
        // 操作员id
        paramMap.put("password", "");
        
        // 终端机id
        paramMap.put("termID", termInfo.getTermid());
        
        // 触摸流水
        paramMap.put("operID", termInfo.getOperid());
        
        // 调用查询用户信息接口
        ReturnWrap retUserInfo = selfSvcCallSD.getUserStatus(paramMap);
        
        if(retUserInfo.getStatus() == SSReturnCode.ERROR)
        {
        	throw new ReceptionException(retUserInfo.getReturnMsg());
        }
        
        // 账户信息
        CTagSet ctsUserInfo = (CTagSet)retUserInfo.getReturnObject();
        
        // 将用户信息封装至session中
        getCustomer(ctsUserInfo, telnum);
        
        // 判断账户信息是否为本省用户
        if(valueCardDao.anthTelnum(ctsUserInfo.GetValue("region")) <= 0)
        {
        	throw new ReceptionException("您输入的号码不是本省号码，请核对您的号码信息！");
        }
        
        // 判断用户是否为停机用户
        if("US30".equals(ctsUserInfo.GetValue("status")))
        {
        	throw new ReceptionException("您输入的号码已停机，无法进行有价卡购买！");
        }
        
        logger.debug("qryUserStatusSD  end...");
    }
    
    /**
     * <获取电子充值卡的面值信息>
     * <功能详细描述>
     * @param curMenuId
     * @param telnum
     * @return
     * @remark create by wWX217192 2015-06-13 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    public List<DictItemVO> getDictItemSD(String curMenuId, String telnum)
    {
    	// 终端机信息
    	TerminalInfoPO termInfo = this.getTermInfo();
    	
    	// 封装入参信息
    	Map<String, String> paramMap = new HashMap<String, String>();
    	
    	// 操作员id
    	paramMap.put("curOper", termInfo.getOperid());
    	
    	// 终端机id
    	paramMap.put("atsvNum", termInfo.getTermid());
    	
    	// 手机号码
    	paramMap.put("telnumber", telnum);
    	
    	// 客户接触id
    	paramMap.put("touchoid", "");
    	
    	// 当前菜单
    	paramMap.put("curMenuId", curMenuId);
    	
    	// 菜单表的groupid
    	paramMap.put("groupid", Constants.FEE_EVCARD);
    	
    	ReturnWrap rw = selfSvcCall.getDictItem(paramMap);
    	
    	if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	List<DictItemVO> dictList = new ArrayList<DictItemVO>();
        	
        	// 获取返回的字典项信息
            CRSet crset = (CRSet)rw.getReturnObject();
            
            DictItemVO dictItem = null;
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                dictItem = new DictItemVO(crset.GetValue(i, 0), crset.GetValue(i, 1), Constants.FEE_EVCARD);
                
                dictList.add(dictItem);
            }
            return dictList;
        }
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * 山东购买有价卡信息
     * @param selfCardPayLogVO
     * @param valueCardVO
     * @param menuid
     * @param unionRetCode
     * @return
     * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    public List<ValueCardVO> addValueCards_sd(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO, String menuid, String unionRetCode)
    {
    	// 终端信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 用户信息
        NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
        
        MsgHeaderPO header = new MsgHeaderPO(menuid, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = selfSvcCallSD.buyValueCard(header, joinParam(selfCardPayLogVO, valueCardVO, customer));
        
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
        	Vector vector = (Vector) rw.getReturnObject();
        	
        	CTagSet tagSet = (CTagSet) vector.get(0);
        	
        	// 平台交易流水号
        	String transActionId = tagSet.GetValue("transActionID");
        	
        	CRSet rSet = (CRSet) vector.get(1);
        	
        	// 记录业务日志及更新交费日志
        	updateRecAndChargeLog_sd(selfCardPayLogVO, Constants.RECSTATUS_SUCCESS, unionRetCode);
			
        	// 获取购买的有价卡列表信息
        	return getValueCardList(rSet, transActionId);
        }
        else
        {
        	updateRecAndChargeLog_sd(selfCardPayLogVO, Constants.RECSTATUS_FALID, unionRetCode);
			
			throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * 山东拼接接口入参信息
     * @param selfCardPayLogVO
     * @param valueCardVO
     * @param customer
     * @return
     * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    private Document joinParam(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO, NserCustomerSimp customer)
    {
    	Document doc = DocumentHelper.createDocument();
    	Element ebody = doc.addElement("message_content");
    	
    	//销售渠道 01：实体营业厅；03：移动热线；04：短信营业厅；05：自助终端；08：网上营业厅； 09：赠送渠道；
    	DocumentUtil.addSubElementToEle(ebody, "channelType", "05");
    	
    	// 支付方式（必传）
    	DocumentUtil.addSubElementToEle(ebody, "elecCardPayType", selfCardPayLogVO.getPayType());
    	
    	// 卡数量（必传）
    	DocumentUtil.addSubElementToEle(ebody, "elecCardNum", valueCardVO.getCardNum());
    	
    	// 卡面值（分，必传）
    	DocumentUtil.addSubElementToEle(ebody, "elecCardCntTotal", CommonUtil.yuanToFen(valueCardVO.getCntTotal()));
    	
    	// 卡业务类型（必传）
        if(valueCardVO.getCardType().length() == 1)
        {
        	valueCardVO.setCardType("0" + valueCardVO.getCardType());
        }
        DocumentUtil.addSubElementToEle(ebody, "elecCardType", valueCardVO.getCardType());
        
        // 实际支付金额（分，必传）
        DocumentUtil.addSubElementToEle(ebody, "elecCardPayMoney", CommonUtil.yuanToFen(selfCardPayLogVO.gettMoney()));
        
        // 发票付款人姓名
        DocumentUtil.addSubElementToEle(ebody, "elecCardCustName", customer.getCustomerName());
        
        // 购卡手机号码
        DocumentUtil.addSubElementToEle(ebody, "telNum", customer.getServNumber());
        
        // 卡业务属性值
        DocumentUtil.addSubElementToEle(ebody, "elecCardBusiPro", "");
        
        // 邮箱
        DocumentUtil.addSubElementToEle(ebody, "elecCardMailBox", "");
        
        // 证件编码
        DocumentUtil.addSubElementToEle(ebody, "elecCardCertID", "");
        
        // 是否发送短信可选，默认为1：发送 0：不发送
        DocumentUtil.addSubElementToEle(ebody, "isSendSms", "1");
        
        // 是否可补发可选，默认为1：允许 0不允许
        DocumentUtil.addSubElementToEle(ebody, "isReSend", "1");
    	
    	return doc;
    }
    
    /**
	 * 更新交费日志信息，同时添加业务日志
	 * @param selfCardPayLogVO
	 * @param statusFlag
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
	 */ 
	private void updateRecAndChargeLog_sd(CardChargeLogVO selfCardPayLogVO, String statusFlag, String unionRetCode)
	{
		if(Constants.RECSTATUS_SUCCESS.equals(statusFlag))
		{
			// 更新缴费日志
	        feeServiceSDImpl.updateChargeLog(selfCardPayLogVO, "有价卡购买交费成功！", unionRetCode, Constants.CHARGE_SUCCESS);

			// 添加业务日志
			this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.VALUECARD_RECLOG, selfCardPayLogVO.getChargeLogOID(),
					selfCardPayLogVO.gettMoney(), Constants.RECSTATUS_SUCCESS, "有价卡购买成功");
		}
		else
		{
			// 更新缴费日志
	        feeServiceSDImpl.updateChargeLog(selfCardPayLogVO, "有价卡购买交费失败！", unionRetCode, Constants.PAYSUCCESS_CHARGEERROR);

			
			// 添加业务日志
			this.insertRecLogTelnum(selfCardPayLogVO.getServnumber(), Constants.VALUECARD_RECLOG, selfCardPayLogVO.getChargeLogOID(),
					selfCardPayLogVO.gettMoney(), Constants.RECSTATUS_FALID, "有价卡购买失败");
		}
	}
	
	public ValueCardDaoImpl getValueCardDao() {
		return valueCardDao;
	}

	public void setValueCardDao(ValueCardDaoImpl valueCardDao) {
		this.valueCardDao = valueCardDao;
	}

	public SelfSvcCallHub getSelfSvcCallHub() {
		return selfSvcCallHub;
	}

	public void setSelfSvcCallHub(SelfSvcCallHub selfSvcCallHub) {
		this.selfSvcCallHub = selfSvcCallHub;
	}

	public FeeChargeHubService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeHubService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public FeeService getFeeService() {
		return feeService;
	}

	public void setFeeService(FeeService feeService) {
		this.feeService = feeService;
	}

	public SelfSvcCallSD getSelfSvcCallSD() {
		return selfSvcCallSD;
	}

	public void setSelfSvcCallSD(SelfSvcCallSD selfSvcCallSD) {
		this.selfSvcCallSD = selfSvcCallSD;
	}

	public IFeeServiceSD getFeeServiceSDImpl() {
		return feeServiceSDImpl;
	}

	public void setFeeServiceSDImpl(IFeeServiceSD feeServiceSDImpl) {
		this.feeServiceSDImpl = feeServiceSDImpl;
	}
}
