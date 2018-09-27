package com.customize.hub.selfsvc.prodInstall.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.InstallFeeHubBean;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.prodInstall.model.IdCardVO;
import com.customize.hub.selfsvc.prodInstall.model.InstallLogVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;
import com.customize.hub.selfsvc.prodInstall.model.SimVO;
import com.customize.hub.selfsvc.prodInstall.model.TelnumbeVO;
import com.customize.hub.selfsvc.prodInstall.service.ProdInstallHubService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

@SuppressWarnings("unchecked")
public class InstallFeeHubAction extends BaseAction
{
	private static final long serialVersionUID = 65452125225l;
	
	 // 日志
    private static Log logger = LogFactory.getLog(InstallFeeHubAction.class);
	
    //add begin by wWX191797 at 20140414 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造
    private String lowFee;//最低消费
    private String installFee;//装机预存
    private String sallBrand;//销售品牌
    private String haveFee;//可用资费
    //add begin by wWX191797 at 20140414 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造
    private String telnum = "";
	
	private String servnumber = "";
	
	private String simiccid = "";
	
	private String imsi = "";
	
	private String mainprodid = "";
	
	private String prodtempletid = "";
	
	private String blankcardno = "";
	
	private String mainprodname = "";
	
	private String brand = "";
	
	private String telprice = "";
	
	private int page = 1;
	
	private int pagesize = 5;
	
	private int totalsize = 0;
	
	private int pagenum = 0;
	
    private String curMenuId;
 
    private String errormessage = "";
    
    
    private String custname = "";
    
    private String certid = "";
    
    private String certtype = "";
    
    private String linkaddr = "";
    
    private String sex = "";
    
    private String hlrid = "";
    
    private String groupid = "";
    
    /**************************************************************/
    //开户业务费用
    private String receptionFee = "";
    
    private List usableTypes = null;
    
    private String terminalSeq = "";
    
    //投币金额
    private String tMoney;
    
    //相差金额
    private String tmpMoney;
    
    private String formnum = "";
    
    private String subsid = "";
    
    private String printFlag = "";
    
    private String payType = "";
    
    private String needReturnCard = "";
    
    private String needRandomPwd = "";
    
    private String cardFlag = "";
    /**************************************************************/
    
    
    
    
    List<ItemFee> itemlist = new ArrayList<ItemFee>();
    List<ItemFee> allitemlist = new ArrayList<ItemFee>();
	
	class ItemFee
	{
		private String itemname = "";
		private String itemfee = "";
		private String itemnum = "";
		private String itemtype = "";
		public String getItemname()
		{
			return itemname;
		}
		public void setItemname(String itemname)
		{
			this.itemname = itemname;
		}
		public String getItemfee()
		{
			return itemfee;
		}
		public void setItemfee(String itemfee)
		{
			this.itemfee = itemfee;
		}
		public String getItemnum()
		{
			return itemnum;
		}
		public void setItemnum(String itemnum)
		{
			this.itemnum = itemnum;
		}
		public String getItemtype()
		{
			return itemtype;
		}
		public void setItemtype(String itemtype)
		{
			this.itemtype = itemtype;
		}
	}
	
	private InstallFeeHubBean installFeeHubBean;
	
	private ProdInstallHubService prodInstallHubService;
	
	private FeeChargeHubService feeChargeService;
	
	
	public void initProperties()
	{
		HttpSession session = getRequest().getSession();
		
		ShTpltTempletVO productInfo = (ShTpltTempletVO)session.getAttribute("productInfo");
		List<ShTpltTempletVO> tpltList = prodInstallHubService.queryTpltTemplet(productInfo);
		productInfo = tpltList.get(0);
		
		SimVO simInfo = (SimVO)session.getAttribute("SimCardInfo");
		TelnumbeVO telInfo = (TelnumbeVO)session.getAttribute("telnumInfo");
		IdCardVO idCardInfo = (IdCardVO)session.getAttribute("IdCardInfo");
		
		telnum = telInfo.getTelnum();
		simiccid = simInfo.getIccid();
		imsi = simInfo.getImsi();
		mainprodid = productInfo.getMainprodid();
		prodtempletid = productInfo.getTempletid();
		blankcardno = "";//这个接口没有返回值
		custname = idCardInfo.getIdCardName();
		certid = idCardInfo.getIdCardNo();
		certtype = "IdCard";
		linkaddr = idCardInfo.getIdCardAddr();
		sex = "男".equals(idCardInfo.getIdCardSex()) ? "0" 
			  : ("女".equals(idCardInfo.getIdCardSex()) ? "1" : "9");
	    
		telprice = telInfo.getFee();
		
		//页面上需要
		mainprodname = productInfo.getMainprodname();
		brand = productInfo.getBrand();
	}
	
	@SuppressWarnings("unchecked")
	public String ckTelSimCard()
	{
		String forward = "error";
		logger.debug("ckTelSimCard start!");
		try
		{
			initProperties();
			
			TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().
															getAttribute(Constants.TERMINAL_INFO);
			
			Map result = installFeeHubBean.checkTelnumSim(terminalInfo,telnum,simiccid,imsi,mainprodid,curMenuId);
			
			if (result != null && result.size() > 1)
			{
				CTagSet tagSet = (CTagSet) result.get("returnObj");
				
				if(null != tagSet && "0".equals(tagSet.GetValue("restatus")))
				{
					forward = queryFeeItems(terminalInfo);
					querySaleCond(terminalInfo,telnum);
				}
				else
				{
					forward = "error";
					logger.debug("开户时号卡检验返回码错误！");
					if(null != tagSet && !"".equals(tagSet.GetValue("info")))
					{
						setErrormessage(tagSet.GetValue("info"));
					}
					else
					{
						setErrormessage("开户时号卡检验失败");
					}
		            this.createRecLog(telnum, "ckTelSimCard", "0", "0", "0", "开户时号卡检验失败");
				}
			}
			else
	        {
				forward = "error";
				logger.debug("开户时号卡检验失败！");
	            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
	            this.createRecLog(telnum, "ckTelSimCard", "0", "0", "0", "开户时号卡检验失败");
	        }
		}
		catch(Exception e)
		{
			forward = "error";
			logger.error("开户时号卡检验失败！");
            setErrormessage("开户时号卡检验出现异常，请联系系统管理员。给您带来的不便，敬请原谅。");
            // 记录异常日志
            this.createRecLog(telnum, "ckTelSimCard", "0", "0", "0", "开户时号卡检验失败");
		}
		
		return forward;
	}

	public String queryFeeItems(TerminalInfoPO terminalInfo)
	{
		String forward = "error";
		
		try
		{
			initProperties();
			
			Map itemMap = installFeeHubBean.qryFeeItemInfo(terminalInfo,telnum,mainprodid,prodtempletid,simiccid,blankcardno,curMenuId);
			
			//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			if( itemMap.size() > 1)
			//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			{
				CRSet crset = (CRSet)itemMap.get("returnObj");
				
				transItemList(crset,allitemlist);
					
				ShTpltTempletVO shTpltTempletVO = new ShTpltTempletVO();
				shTpltTempletVO.setTempletid(prodtempletid);
				shTpltTempletVO.setRegion(terminalInfo.getRegion());
				List<ShTpltTempletVO> tpltList = prodInstallHubService.queryTpltTemplet(shTpltTempletVO);
				
				transItemList(tpltList,allitemlist);
				
				tripTotalFee(allitemlist);
				
				if(null == allitemlist || allitemlist.isEmpty())
				{
					forward = "error";
					setErrormessage("号卡校验成功，但费用明细列表为空！");
					this.createRecLog(telnum, "queryFeeItems", "0", "0", "0", "费用明细列表为空");
				}
				else
				{
					this.getRequest().setAttribute("totalsize", allitemlist.size());
				    // 调用分页
				    displayPage();
				    forward = "success";
				}
			}
			else
			{
				forward = "error";
				
				//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
				if(!"".equals(itemMap.get("returnMsg")))
				//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
				{
					this.getRequest().setAttribute("errormessage", itemMap.get("returnMsg"));
				}
				else
				{
					setErrormessage("号卡校验成功，但费用明细列表为空！");
				}
				this.createRecLog(telnum, "queryFeeItems", "0", "0", "0", "费用明细列表为空");
			}
		}
		catch(Exception e)
		{
			forward = "error";
			logger.error("开户时调用算费接口失败！");
            setErrormessage("开户时调用算费接口出现异常，请联系系统管理员。给您带来的不便，敬请原谅。");
            // 记录异常日志
            this.createRecLog(telnum, "queryFeeItems", "0", "0", "0", "开户时调用算费接口失败");
		}
		
		return forward;
	}
	/**
	 * 查询销售条件
	 * @param paramMap
	 * @return
	 * author wWX191797
	 * time 2014-04-14
	 * for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造	
	 */
	public void querySaleCond(TerminalInfoPO terminalInfo,String telnum)
	{	
		System.out.println("querySaleCond+++++++++++++1111111111111111");
		try
		{
			
			Map itemMap = installFeeHubBean.querySaleCond(terminalInfo,telnum,curMenuId);
			
			//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			if(itemMap.size() > 1)
			//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			{	
				System.out.println("yesyesyes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				CRSet crset = (CRSet)itemMap.get("returnObj");
				if(crset != null && crset.GetColumnCount() >=2)
				{		
					System.out.println("querySaleCond+++++++++++++2222222222222222");
						installFee=(crset.GetValue(0, 1));
						lowFee=(crset.GetValue(1, 1));
						sallBrand=(crset.GetValue(2, 1));
						haveFee=(crset.GetValue(3, 1));
						System.out.println("installFee:["+installFee+"],lowFee:["+lowFee+"]," +
								"sallBrand:["+sallBrand+"],haveFee:["+haveFee+"]");
				}
				
			}
			else
			{	
				System.out.println("nonono!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				
				//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
				if(!"".equals(itemMap.get("returnMsg")))
				//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
				{
					this.getRequest().setAttribute("errormessage", itemMap.get("returnMsg"));
				}
				else
				{
					setErrormessage("号卡校验成功，但销售条件列表为空！");
				}
				this.createRecLog(telnum, "queryFeeItems", "0", "0", "0", "销售条件列表为空");
			}
		}
		catch(Exception e)
		{
			logger.error("开户时调用销售条件失败！");
            setErrormessage("开户时调用销售条件出现异常，请联系系统管理员。给您带来的不便，敬请原谅。");
            e.printStackTrace();
            // 记录异常日志
            this.createRecLog(telnum, "queryFeeItems", "0", "0", "0", "开户时调用销售条件失败");
		}
		
	}	
	public void queryLowFee(){
		System.out.println("queryLowFee+++++++++++++++++++++++++++++++++++");
		TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().
		getAttribute(Constants.TERMINAL_INFO);
		querySaleCond(terminalInfo,telnum);
		getResponse().setContentType("text/html; charset=GBK");
		getResponse().setHeader("Content-Type", "text/html;charset=GBK");
		System.out.println("lowFee:["+lowFee+"]");
		try {
			getResponse().getWriter().write(lowFee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 产品入网交费类型：目前只支持现金支付
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String installChargeType()
	{
	        
	        logger.debug("installChargeType start");
	        
	        String forward = "";
	        
	        HttpServletRequest request = this.getRequest();
	        HttpSession session = request.getSession();
	        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
	        
	        String groupID = termInfo.getTermgrpid();
	        List<MenuInfoPO> menus = null;
	        if (groupID != null && !"".equals(groupID))
            {
                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
            }    
	        usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");   
	        if (null == usableTypes || usableTypes.isEmpty())
            {
                setErrormessage("对不起，暂时没有可用的缴费方式，请返回执行其他操作。");
                
                this.createRecLog(telnum, "SHInstall", "0", "0", "1", "暂时没有可用的缴费方式");
                
                forward = "error";
            }    
	        else
	        {
	            forward = "selectType";
	        }
	       
	        logger.debug("installChargeType end");
	        
	        return forward;
	}
	
	/**
     * 转向投币页面
     * @return
     */
    public String cashCharge()
    {
        return "cashChargePage";
    }
    
    /**
     * 缴费开户
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installCashCommit()
    {
    	
        initProperties();
    	
    	logger.debug("installCashCommit start");
    	
    	String forward = "error";
    	
    	HttpSession session = getRequest().getSession();
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	tmpMoney = CurrencyUtil.minus(tMoney, receptionFee);
    	
    	logger.info("投入币额与开户缴费金额差为：" + tmpMoney);
    	
    	forward = installCashLog(termInfo,terminalSeq, "Cash");
    	
    	logger.debug("installCashCommit end");
    	
    	return forward;
    }
    
    public boolean isShInstallDone(TerminalInfoPO termInfo, String type,String errMsg)
	{	
		boolean flag = false;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		initInstallMap(paramMap);
		
		Map result = installFeeHubBean.prodInstallCommit(termInfo,paramMap,curMenuId);
		
		if(null != result && result.size() > 1)
		{
			CTagSet tagSet = (CTagSet) result.get("returnObj");
			formnum = tagSet.GetValue("aiformnum");
			subsid = tagSet.GetValue("aisubsid");
			flag = true;
		}
		else
		{
			flag = false;
			
			errMsg = "调用开户接口受理业务失败！";
		}
		return flag;
	}
    
    public String installCashLog(TerminalInfoPO termInfo,String terminalNum, String type)
	{
    	String forward = "error";
    	boolean flag = false;
    	String errMsg = "";
        
    	//缴费日志序号
        String logOID = feeChargeService.getChargeLogOID();
        
        // 记录投币日志
        CardChargeLogVO selfCardPayLogVO = makeChargeLog(termInfo,terminalNum,logOID);
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        InstallLogVO installLogVO = makeInstallLog(termInfo, logOID);
        
        if (tmpMoney.indexOf("-") >= 0)
        {
        	tmpMoneyLower(selfCardPayLogVO,installLogVO);
        	forward = "error";
        }
        else
        {
        	flag = isShInstallDone(termInfo,type,errMsg);
        	
        	if(flag)
        	{
        		SimpleDateFormat temDate = new SimpleDateFormat("yyyyMMddHHmmss");
                String accpetDate = temDate.format(new Date());
                selfCardPayLogVO.setRecdate(accpetDate);
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("开户受理成功！");
                
                setErrormessage("开户受理成功！");
                
                installLogVO.setStatus("1"); // 受理成功
                installLogVO.setNotes("开户受理成功！");
                installLogVO.setStatusdate(accpetDate);
                installLogVO.setRecstatus("1");
                installLogVO.setSubsid(subsid);
                installLogVO.setFormnum(formnum);
                
                setPrintFlag("1");
                this.createRecLog(telnum, "INSTALL_PAYTYPE_CASH", "0", tMoney, "0", "开户受理成功！投币金额为：" + tMoney + "!");
                
                //设置取卡标志为YES
                setCardFlag("YES");
                forward = "success";
        	}
        	else
        	{
        		tmpMoneyEqual(selfCardPayLogVO,installLogVO,errMsg);
        		forward = "error";
        	}
        }
        
        prodInstallHubService.createInstallLog(installLogVO);
        feeChargeService.updateChargeLog(selfCardPayLogVO);
    	return forward;
	}
    
    /**
     * 缴费开户失败
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goCashError()
    {
    	return "cashErrorPage";
    }
    
    
    /**
     * 取消业务办理
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installCashCancel()
    {
    	//记录投币日志
    	HttpSession session = getRequest().getSession();
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	String logOID = feeChargeService.getChargeLogOID();
    	CardChargeLogVO selfCardPayLogVO = makeChargeLog(termInfo,terminalSeq,logOID);
    	tmpMoneyLower(selfCardPayLogVO, null);
    	feeChargeService.addChargeLog(selfCardPayLogVO);
    	
    	return "cashErrorPage";
    }
    
    public CardChargeLogVO makeChargeLog(TerminalInfoPO termInfo, String terminalNum,String logOID)
    {
    	CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO(); // 增加缴费日志
    	
    	selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
    	
        selfCardPayLogVO.setServnumber(telnum);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        terminalNum = termInfo.getTermid() + terminalNum;
        if (terminalNum.length() > 20)
        {
            terminalNum = terminalNum.substring(terminalNum.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalNum);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(termInfo.getRegion());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("卡号开户之前记录投币日志");
        selfCardPayLogVO.setDealnum("");
        selfCardPayLogVO.setRecType("install");
        
        return selfCardPayLogVO;
    }
    
    public InstallLogVO makeInstallLog(TerminalInfoPO termInfo,String logOID)
    {
    	 InstallLogVO installLogVO = new InstallLogVO(); 
    	 
    	 installLogVO.setTermid(termInfo.getTermid());
         installLogVO.setChargeid(logOID);
         installLogVO.setRegion(termInfo.getRegion());
         installLogVO.setServnumber(telnum);
         installLogVO.setHlrid(hlrid);
         installLogVO.setGroupid(groupid);
         installLogVO.setMainprodiid(mainprodid);
         installLogVO.setProdtempletid(prodtempletid);
         installLogVO.setChargetype(Constants.PAYBYMONEY);
         installLogVO.setRecfee(CommonUtil.yuanToFen(receptionFee));
         installLogVO.setTofee(CommonUtil.yuanToFen(tMoney));
         installLogVO.setCustname(custname);
         installLogVO.setCertid(certid);
         installLogVO.setLinkaddr(linkaddr);
         installLogVO.setSex(sex);
         installLogVO.setBlankcard(blankcardno);
         installLogVO.setImsi(imsi);
         installLogVO.setLinkphone("");
         installLogVO.setProductlist("");
         installLogVO.setSubmailaddr("");
         SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
         String payDate = sdf1.format(new Date());
         installLogVO.setCreatedate(payDate);
         installLogVO.setStatusdate(payDate);
         
         return installLogVO;
    }
    
    public void tmpMoneyLower(CardChargeLogVO selfCardPayLogVO,InstallLogVO installLogVO)
    {
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    	String payErrDate = sdf1.format(new Date());
        selfCardPayLogVO.setRecdate(payErrDate);
        selfCardPayLogVO.setStatus("10");
        
        selfCardPayLogVO.setDescription("开户受理费用不足，开户失败！");
        selfCardPayLogVO.setDealnum("");
        setErrormessage("开户受理费用不足,开户失败,请凭交易凭条到营业厅退款！");
        
        
        // 记录受理优惠的日志
        if(null != installLogVO)
        {
        	installLogVO.setStatus("0");
            installLogVO.setRecstatus("2");  // 开户失败
            installLogVO.setNotes("开户受理费用不足，开户失败！");
        }
       
        this.createRecLog(telnum, "INSTALL_PAYTYPE_CASH", selfCardPayLogVO.getChargeLogOID(), "0", "1", "开户受理费用不足,开户失败!费用为："
                + tMoney + "!");
    }
    
    public void tmpMoneyEqual(CardChargeLogVO selfCardPayLogVO,InstallLogVO installLogVO,String errMsg)
    {
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    	String payErrDate = sdf1.format(new Date());
        selfCardPayLogVO.setRecdate(payErrDate);
        selfCardPayLogVO.setStatus("10");
        
        if(StringUtils.isBlank(errMsg))
        {
        	errMsg = "调用开户接口受理业务失败,请凭交易凭条到营业厅退款！";
        }
        
        selfCardPayLogVO.setDescription("调用开户接口受理业务失败！");
        selfCardPayLogVO.setDealnum("");
        
        setErrormessage(errMsg);
        
        installLogVO.setStatus("0");
        installLogVO.setRecstatus("2");  // 开户失败
        installLogVO.setNotes("调用开户接口受理业务失败！");
        
        this.createRecLog(telnum, "INSTALL_PAYTYPE_CASH", selfCardPayLogVO.getChargeLogOID(), "0", "1", "调用开户接口受理业务失败！ 费用为："
                + tMoney + "!");
    }
    
    public void initInstallMap(Map<String, String> paramMap)
    {
    	paramMap.put("telnum", telnum);
		paramMap.put("simnum", simiccid);
		paramMap.put("mainprodiid", mainprodid);
		paramMap.put("prodtempletid", prodtempletid);
		//客户缴纳的费用
		paramMap.put("sumprice", CommonUtil.yuanToFen(tMoney));
		paramMap.put("custname", custname);
		paramMap.put("certid", certid);
		paramMap.put("certtype", certtype);
		paramMap.put("linkaddr", linkaddr);
		paramMap.put("sex", sex);
		
		String defPwd = CommonUtil.getRandomNum(6);
		if(StringUtils.isBlank(defPwd)) defPwd = "888888";
		paramMap.put("passwd", defPwd);//开户默认密码
		
		paramMap.put("blankcard", blankcardno);
		paramMap.put("imsi", imsi);
		paramMap.put("telprice", telprice);
		paramMap.put("linkphone", "");
		paramMap.put("productlist", "");
		paramMap.put("submailaddr", "");
    }

	//私有方法开始
	private String formatFee(String fee)
	{
		try
		{
			DecimalFormat df = new DecimalFormat("0.00");
			
			if(null == fee || "".equals(fee.trim()))
			{
				fee = "0";
			}
			
			double value = Double.parseDouble(fee);
			
			if(String.valueOf(fee).indexOf(".") == -1)
			{
				value = value/100;
			}
			
			return df.format(value);
		}
		catch(Exception e)
		{
			return "0";
		}
	}
	
	private void transItemList(CRSet crset, List<ItemFee> itemlist)
	{
		if(null == crset || crset.GetColumnCount() < 4)
		{
			return;
		}
		for(int i=0; i<crset.GetRowCount(); i++)
		{
			ItemFee item = new ItemFee();
			item.setItemname(crset.GetValue(i, 0));
			item.setItemfee(crset.GetValue(i, 1));
			item.setItemnum(crset.GetValue(i, 2));
			item.setItemtype(crset.GetValue(i, 3));
			
			itemlist.add(item);
		}
	}
	
	private void transItemList(List<ShTpltTempletVO> tpltList,
			List<ItemFee> itemlist)
	{
		if(null == tpltList || tpltList.isEmpty())
		{
			return;
		}
		for(ShTpltTempletVO tplt : tpltList)
		{
			ItemFee item = new ItemFee();
			itemlist.add(item);
			
			item.setItemname("预存款");
			item.setItemfee(tplt.getMinfee());
			item.setItemnum("1");
			item.setItemtype("预存款");
		}
	}
	
	private void tripTotalFee(List<ItemFee> itemlist)
	{
		if(null == itemlist || itemlist.isEmpty())
		{
			return;
		}
		
		double totalFee = 0d;
		
		for(ItemFee item : itemlist)
		{
			String value = formatFee(item.getItemfee());
			item.setItemfee(value);
			totalFee += Double.parseDouble(value);
		}
		
		receptionFee = formatFee(String.valueOf(totalFee));
		ItemFee item = new ItemFee();
		itemlist.add(item);
		item.setItemname("费用合计");
		item.setItemfee(receptionFee);
		item.setItemnum("");
		item.setItemtype("");
		
	}
	
	private void displayPage()
	{
		if (page == 0)
        {
			page = 1;
        }
        
        // 清空数据
        itemlist.clear();
        
        // 计算总条数
        totalsize = allitemlist.size();
        
        // 计算总页数
        if (totalsize % pagesize > 0)
        {
            pagenum = totalsize / pagesize + 1;
        }
        else
        {
        	pagenum = totalsize / pagesize;
        }
        
        // 开始条数
        int startNum = pagesize * (page - 1) + 1;
        
        // 结束条数
        int endNum = pagesize * page;
        
        // 开始条数
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= totalsize)
            {
                this.itemlist.add(allitemlist.get(i-1));
            }
        }
        
        if (this.itemlist.size() < pagesize)
        {
            int size = itemlist.size();
            ItemFee item = new ItemFee();
            for(int i=size;i<pagesize;i++)
            {
            	itemlist.add(item);
            }
        }
	}
	
	 /**
     * 将发票数据组织成xml
     * 
     * @param list 发票数据
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
     * 湖北打印发票需要短信随机码认证
     * 
     * @return
     * @see
     */
    public String validateByRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd Starting ...");
        }
        
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("formnum");
        
        request.setAttribute("invoiceType", invoiceType);
        request.setAttribute("dealNum", dealNum);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 生成随机密码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // 发送随机密码短信
        String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD_CONTENT");
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        if (!installFeeHubBean.sendRandomPwd(servnumber, termInfo, shortMessage, curMenuId))
        {
            logger.error("向用户" + servnumber + "发送随机密码短信失败");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", "发票打印功能，随机密码短信发送失败。");
            
            this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，不能打印发票。");
        }
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
            }
            
            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 随机密码验证，验证通过后，打印发票
     * 
     * @return
     * @see
     */
    public String printInvoiceWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            
            // 调用接口打印发票
            request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
            
            if (logger.isInfoEnabled())
            {
                logger.info("发票打印功能，用户" + servnumber + "使用随机密码进行认证成功");
            }
        }
        else
        {
            forward = "error";
            
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                // modify begin g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
            }
            
            logger.error("发票打印功能，用户" + servnumber + "输入的随机密码错误或超时，认证失败");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 无需随机密码验证，直接打印发票
     * 
     * @return
     * @see
     */
    public String printInvoiceWithoutPwd()
    {
        HttpServletRequest request = this.getRequest();
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        // 调用接口打印发票
        request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
        
        return "print";
    }
	
	/**
     * 调用接口打印发票
     */
    private List printInvoice(String invoiceType, String dealNum)
    {
        HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 提供发票打印功能时，获取发票信息
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = installFeeHubBean.getInvoiceData(termInfo, curMenuId, servnumber, dealNum);
            
            if (invoiceData != null && invoiceData.size() > 0)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof Vector)
                {
                    Vector invoiceVector = (Vector)invoiceObj;
                    
                    CTagSet tagSet = (CTagSet)invoiceVector.get(0);
                    String cycleCount = (String)tagSet.GetValue("invoice_cnt");
                    int rowNum = Integer.parseInt(cycleCount);
                    
                    CRSet crset = (CRSet)invoiceVector.get(1);
                    
                    List invoicesList = new ArrayList();
                    
                    // 打印全部发票，有多张发票信息打印多张
                    if ("ALL".equals(invoiceType))
                    {
                        Map invoiceMap = null;
                        for (int i = 0; i < rowNum; i++)
                        {
                            invoiceMap = new HashMap();
                            
                            // 将返回的CRSet转换成字符串
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (tmpArray.length == 2)
                                {
                                    invoiceMap.put(tmpArray[0], tmpArray[1]);
                                }
                            }
                            invoicesList.add(invoiceMap);
                        }
                    }
                    // 打印当前发票，有多张发票打印最后一张
                    else if ("Last".equals(invoiceType))
                    {
                        Map invoiceMap = new HashMap();// 键值对形式存储一张发票数据各个子数据
                        
                        // 将返回的CRSet转换成字符串
                        String invoice = crset.GetValue(rowNum - 1, 0) + "," + crset.GetValue(rowNum - 1, 1);
                        String[] invoiceArray = invoice.split(",");
                        for (int j = 0; j < invoiceArray.length; j++)
                        {
                            String tmpData = invoiceArray[j];
                            String[] tmpArray = tmpData.split("@~@");
                            if (tmpArray.length == 2)
                            {
                                invoiceMap.put(tmpArray[0], tmpArray[1]);
                            }
                        }
                        invoicesList.add(invoiceMap);
                    }
                    // 打印单张发票，有多张发票需要显示的月份为某月~某月，金额为多张发票的总金额，其他字段显示最后一张发票的信息
                    else if ("Single".equals(invoiceType))
                    {
                        float total = 0; // 所销帐的金额总和
                        String tmpStar = ""; // 开始日期
                        HashMap subMap = new HashMap();// 键值对形式存储一张发票数据各个子数据
                        for (int i = 0; i < rowNum; i++)
                        {
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (rowNum - 1 == 0)
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        subMap.put(tmpArray[0], tmpArray[1]);
                                    }
                                }
                                else
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        if ("invoicefee".equals(tmpArray[0]))
                                        {
                                            total += Float.parseFloat((tmpArray[1].substring(0,
                                                    tmpArray[1].length() - 1)));
                                        }
                                        if (i == 0 && "callfeeCreateTime".equals(tmpArray[0]))
                                        {
                                            tmpStar = tmpArray[1];
                                        }
                                        if (i == rowNum - 1)
                                        {
                                            if ("invoicefee".equals(tmpArray[0]) || "invoicefeehj".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = CommonUtil.round(String.valueOf(total)) + "元";
                                            }
                                            if ("callfeeCreateTime".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = tmpStar + "——" + tmpArray[1];
                                            }
                                            subMap.put(tmpArray[0], tmpArray[1]);
                                        }
                                    }
                                }
                            }
                        }
                        invoicesList.add(subMap);
                    }
                    return invoicesList;
                }
            }
        }
        return null;
    }
	
	public void setInstallFeeHubBean(InstallFeeHubBean installFeeHubBean)
	{
		this.installFeeHubBean = installFeeHubBean;
	}

	public void setProdInstallHubService(ProdInstallHubService prodInstallHubService)
	{
		this.prodInstallHubService = prodInstallHubService;
	}
	
	public String getTelnum()
	{
		return telnum;
	}

	public void setTelnum(String telnum)
	{
		this.telnum = telnum;
	}

	public String getSimiccid()
	{
		return simiccid;
	}

	public void setSimiccid(String simiccid)
	{
		this.simiccid = simiccid;
	}

	public String getImsi()
	{
		return imsi;
	}

	public void setImsi(String imsi)
	{
		this.imsi = imsi;
	}

	public String getMainprodid()
	{
		return mainprodid;
	}

	public void setMainprodid(String mainprodid)
	{
		this.mainprodid = mainprodid;
	}

	public String getProdtempletid()
	{
		return prodtempletid;
	}

	public void setProdtempletid(String prodtempletid)
	{
		this.prodtempletid = prodtempletid;
	}

	public String getBlankcardno()
	{
		return blankcardno;
	}

	public void setBlankcardno(String blankcardno)
	{
		this.blankcardno = blankcardno;
	}

	public InstallFeeHubBean getInstallFeeHubBean()
	{
		return installFeeHubBean;
	}

	public ProdInstallHubService getProdInstallHubService()
	{
		return prodInstallHubService;
	}
	
	public FeeChargeHubService getFeeChargeService()
	{
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeHubService feeChargeService)
	{
		this.feeChargeService = feeChargeService;
	}
	
	public String getTMoney()
	{
		return tMoney;
	}

	public void setTMoney(String money)
	{
		tMoney = money;
	}

	public String getTmpMoney()
	{
		return tmpMoney;
	}

	public void setTmpMoney(String tmpMoney)
	{
		this.tmpMoney = tmpMoney;
	}

	public String getMainprodname()
	{
		return mainprodname;
	}

	public void setMainprodname(String mainprodname)
	{
		try
	    {
	         this.mainprodname = new String(mainprodname.getBytes("ISO-8859-1"), "GKB");
	    }
	    catch (Exception e)
	    {
	         this.mainprodname = mainprodname;
	    }
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}

	public int getTotalsize()
	{
		return totalsize;
	}

	public void setTotalsize(int totalsize)
	{
		this.totalsize = totalsize;
	}

	public int getPagenum()
	{
		return pagenum;
	}

	public void setPagenum(int pagenum)
	{
		this.pagenum = pagenum;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage()
	{
	    return errormessage;
	}
	    
	public void setErrormessage(String errormessage)
	{
	    try
	    {
	         this.errormessage = new String(errormessage.getBytes("ISO-8859-1"), "GKB");
	    }
	    catch (Exception e)
	    {
	         this.errormessage = errormessage;
	    }
	}
	
	public List<ItemFee> getItemlist()
	{
		return itemlist;
	}

	public void setItemlist(List<ItemFee> itemlist)
	{
		this.itemlist = itemlist;
	}

	public List<ItemFee> getAllitemlist()
	{
		return allitemlist;
	}

	public void setAllitemlist(List<ItemFee> allitemlist)
	{
		this.allitemlist = allitemlist;
	}

	public String getCustname()
	{
		return custname;
	}

	public void setCustname(String custname)
	{
		this.custname = custname;
	}

	public String getCertid()
	{
		return certid;
	}

	public void setCertid(String certid)
	{
		this.certid = certid;
	}

	public String getCerttype()
	{
		return certtype;
	}

	public void setCerttype(String certtype)
	{
		this.certtype = certtype;
	}

	public String getLinkaddr()
	{
		return linkaddr;
	}

	public void setLinkaddr(String linkaddr)
	{
		this.linkaddr = linkaddr;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getReceptionFee()
	{
		return receptionFee;
	}

	public void setReceptionFee(String receptionFee)
	{
		this.receptionFee = receptionFee;
	}
	
	public List getUsableTypes()
	{
		return usableTypes;
	}

	public void setUsableTypes(List usableTypes)
	{
		this.usableTypes = usableTypes;
	}

	public String getTerminalSeq()
	{
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq)
	{
		this.terminalSeq = terminalSeq;
	}

	public String getFormnum()
	{
		return formnum;
	}

	public void setFormnum(String formnum)
	{
		this.formnum = formnum;
	}

	public String getSubsid()
	{
		return subsid;
	}

	public void setSubsid(String subsid)
	{
		this.subsid = subsid;
	}

	public String getPrintFlag()
	{
		return printFlag;
	}

	public void setPrintFlag(String printFlag)
	{
		this.printFlag = printFlag;
	}

	public String getHlrid()
	{
		return hlrid;
	}

	public void setHlrid(String hlrid)
	{
		this.hlrid = hlrid;
	}

	public String getGroupid()
	{
		return groupid;
	}

	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}

	public String getPayType()
	{
		return payType;
	}

	public void setPayType(String payType)
	{
		this.payType = payType;
	}

	public String getNeedReturnCard()
	{
		return needReturnCard;
	}

	public void setNeedReturnCard(String needReturnCard)
	{
		this.needReturnCard = needReturnCard;
	}

	public String getNeedRandomPwd()
	{
		return needRandomPwd;
	}

	public void setNeedRandomPwd(String needRandomPwd)
	{
		this.needRandomPwd = needRandomPwd;
	}

	public String getTelprice()
	{
		return telprice;
	}

	public void setTelprice(String telprice)
	{
		this.telprice = telprice;
	}

	public String getCardFlag()
	{
		return cardFlag;
	}

	public void setCardFlag(String cardFlag)
	{
		this.cardFlag = cardFlag;
	}

	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}
    //add begin by wWX191797 at 20140414 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造

	public String getLowFee() {
		return lowFee;
	}

	public void setLowFee(String lowFee) {
		this.lowFee = lowFee;
	}

	public String getInstallFee() {
		return installFee;
	}

	public void setInstallFee(String installFee) {
		this.installFee = installFee;
	}

	public String getSallBrand() {
		return sallBrand;
	}

	public void setSallBrand(String sallBrand) {
		this.sallBrand = sallBrand;
	}

	public String getHaveFee() {
		return haveFee;
	}

	public void setHaveFee(String haveFee) {
		this.haveFee = haveFee;
	}
    
	//add end by wWX191797 at 20140414 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造

}
