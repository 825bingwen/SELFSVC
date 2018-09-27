package com.customize.nx.selfsvc.prodInstall.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.nx.selfsvc.bean.ProdInstallNxBean;
import com.customize.nx.selfsvc.cache.RefreshCacheNX;
import com.customize.nx.selfsvc.prodInstall.model.FeeConfirmPO;
import com.customize.nx.selfsvc.prodInstall.model.IdCardPO;
import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.customize.nx.selfsvc.prodInstall.service.ProdInstallNxService;
import com.customize.sd.selfsvc.chooseTel.model.ServerNumPO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * 宁夏在线开户(空白卡开户)
 * <功能详细描述>
 * 
 * @author  zKF66389
 * @version  [版本号, Jul 25, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class ProdInstallNxAction extends BaseAction
{
	private static final long serialVersionUID = 65452125225l;
	
	/** 
	 * LOGGER
	 */
	private static Log logger = LogFactory.getLog(ProdInstallNxAction.class);
	
	/** 
	 * BEAN
	 */
	private ProdInstallNxBean prodInstallNxBean;
	
	/** 
	 * SERVICE
	 */
	private ProdInstallNxService prodInstallNxService;
	
	/** 
	 * 身份证信息
	 */
	private IdCardPO idCardPO = new IdCardPO();
	
	/** 
	 * 错误信息
	 */
	private String errormessage;
	
	/**
	 *  是否打印小票  1-不打印，0-打印
	 */
	private String canNotPrint;
	
	/** 
	 * 产品列表
	 */
	private List<TpltTempletPO> tpltTempletList;
	
	/**  ---------------- 机构 ---------------------
	 * 
	 * 地区
	 */
	private String region;
	
	/** 
	 * 地区名称
	 */
	private String regionName;
	
	/** 
	 * 机构
	 */
	private String orgid;
	
	/**  ---------------- 选号 ---------------------
	 * 
	 * 查询类型
	 */
    private String sele_rule;
    
	/** 
	 * 号码前缀
	 */
    private String tel_prefix;
    
	/** 
	 * 号码后缀
	 */
    private String tel_suffix;
    
	/** 
	 * 每页号码列表
	 */
    private List<ServerNumPO> serverNumList = null;
    
	/** 
	 * 存放全部手机号码数据
	 */
    private List<ServerNumPO> tmpList = null;
    
	/** 
	 * 选号页面每页最多显示21条记录，如超过21条，需分页
	 */
    private int nMaxNum = 9;
    
	/** 
	 * 分页标识。true时需分页
	 */
    private String pageFlag = "false";
    
    /** ---------------- 分页 ---------------------
	 * 
	 * 页数
	 */
    private int pageCount;
    
	/** 
	 * 每页显示容量
	 */
    private int pageSize = 9;
    
	/** 
	 * 第几页
	 */
    private int page = 0;
    
    /** ---------------- 产品 -------------------------
	 * 
	 * 产品ID
	 */
    private String prodid;
    
	/** 
	 * 空白卡卡号
	 */
    private String blankno;
    
	/** 
	 * 开户类型
	 */
    private String rectype;
    
	/** 
	 * 开户号码
	 */
    private String telnum; 
    
	/** 
	 * 卡类型
	 */
    private String cardtype;
    
	/** 
	 * IMSI
	 */
    private String imsi;
    
	/** 
	 * ICCID
	 */
    private String iccid;
    
	/** 
	 * smsp
	 */
    private String smsp;
    
	/** 
	 * 服务密码
	 */
    private String pwd;
	
	/** 
	 * 模板ID
	 */
    private String prodTempletId;
    
	/** 
	 * 预存款
	 */
    private String minfee;
    
	/** 
	 * 产品模板
	 */
    private TpltTempletPO tpltTempletPO;
    
    /**---------------- 费用明细 -------------------------
	 * 
	 * 存放全部缴费信息
	 */
    private List<FeeConfirmPO> feeList = null;
    
	/** 
	 * 开户应收费用
	 */
    private String recFee;

    /**---------------- 菜单相关 -------------------------
	 * 
	 * 菜单相关
	 */
    private List usableTypes = null;
    
	/** 
	 * 当前菜单
	 */
    private String curMenuId = "";
    
    /** ---------------- 投币相关 -------------------------
	 * 
	 * 投币金额
	 */
    private String tMoney;
    
	/** 
	 * 投币器流水号
	 */
    private String terminalSeq;
    
    /** ---------------- 发票相关 -------------------------
     * 
	 * 发票打印标志，0＝不打印 1＝打印
	 */
    private String payType;
    
    /**
     * 用户投币面额信息
     */
    private String cashDetail = "";
    
    /**
     * 凭条交易时间
     */
    private String pDealTime = "";
    
    /**
     * 受理编号
     */
    private String dealNum = "";
    
    /**
     * 是否退还银联卡
     */
    private String needReturnCard = "";
    
    /**
     * 默认2：初始状态 0：写卡成功 1：写卡失败
     */
    private String writeCardStatus = "";// 
    
    /**
     * 默认2：初始状态 0：缴费成功 1：缴费失败
     */
    private String payStatus = "";  
    
    /**
     * 默认2：初始状态 0：开户成功 1：开户失败
     */
    private String installStatus = ""; 
    
    /**
     * 缴费流水号，与SH_CHARGE_lOG关联 缴费前此列数据为空
     */
    private String chargeId = "";

    /**
     * 开户流水号
     */
    private String installId = "";
    
    /**
     * 实收费用
     */
    private String toFee = "";
    
    /**
     * 开户受理流水
     */
    private String formnum = "";
    
    /**
     * 开户备注
     */
    private String notes = "";
    
	/**
     * 处理异常
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installError()
    {
    	// 记录错误日志到sh_rec_log
    	this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "1", errormessage);
    	
        return "installError";
    }
    
	/**
	 * 入网协议
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String agreement()
	{
		return "agreement";
	}
	
	/**
	 * 读取身份证
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String readCert()
    {
		// 设置菜单信息
		curMenuId = "openAccount";
		
        return "readCert";
    }
	
    /**
     * 身份证信息展现
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String certShow()
    {
        return "certShow";
    }
	
	/**
	 * 产品选择页面
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectProd()
	{
	    
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 证件类型
        String certtype = "IdCard";
        
        // 证件号码
        String certid = idCardPO.getIdCardNo();
        
        // 调用接口校验证件号码下的用户数量
        Map  map = this.prodInstallNxBean.chkCertSubs(termInfo, curMenuId, certtype, certid);
        
        // 如果调用接口成功，返回的参数ifValid校验通过 1；不通过，0
        if (null != map && map.size() > 2 && "0".equals((String)map.get("ifValid")))
        {
            errormessage = "户主证件号码下的有效用户数量超过用户上限";
            
            // 转向错误页面
            return "installError";
        }
        // 如果调用接口失败
        else if(null != map && map.size() == 2)
        {
        	errormessage = (String)map.get("returnMsg");
        	
        	// 转向错误页面
            return "installError";
        }
        // 调用接口异常
        else if(null == map)
        {
        	errormessage = "调用证件号码下的用户数量校验接口失败！请稍后再试！";
        	
        	// 转向错误页面
            return "installError";
        }
        
        // 参数
        TpltTempletPO tpltTempletPO = new TpltTempletPO();
        tpltTempletPO.setRegion(termInfo.getRegion());
        
        // 查询产品列表
        tpltTempletList = prodInstallNxService.qryTpltTempletList(tpltTempletPO);

        if (null != tpltTempletList && 0 < tpltTempletList.size())
        {
            this.getRequest().setAttribute("recordCount", tpltTempletList.size());

            pageSize = 5;
            
            tpltTempletList = getPageList(tpltTempletList);
        }
        
        // 转向产品列表页面
	    return "selectProd";
	}
	
    /**
     * 选择选号规则页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectRule()
    {
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 获取缓存中的地区列表
        List<RegionInfoPO> regionListTmp = (List<RegionInfoPO>)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        
        for (RegionInfoPO regionInfoPO:regionListTmp)
        {
            if (termInfo.getRegion().equals(regionInfoPO.getRegion()))
            {
                region = regionInfoPO.getRegion();
                regionName = regionInfoPO.getRegionname();
                orgid = regionInfoPO.getOrgid();
            }
        }
        
        // 返回
        return "selectRule";
    }
    
    /**
     * 号码前缀后缀输入页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputTelnumByRule()
    {
        return "inputTelnumByRule";
    }
    
    /**
     * 手机号码查询列表
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String telnumResult()
    {
        // 开始记录日志
        logger.debug("telNumResult start...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        tel_prefix = (null == tel_prefix) ? "" : tel_prefix;
        tel_suffix = (null == tel_suffix) ? "" : tel_suffix;
        // add end jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 处理前缀，长度不够在后面补_
        // 查询类型 2：按前缀查询 3：按后缀查询
        if ("2".equals(sele_rule))
        {
            while (tel_prefix.length() < 7)
            {
                tel_prefix = tel_prefix + "_";
            }        
        }
        // 处理后缀,长度不够在后面补_
        else if("3".equals(sele_rule))
        {
            while (tel_suffix.length() < 4)
            {
                tel_suffix = tel_suffix + "_";
            }
        }
        
        // 设置产品ID
        this.setProdid(this.getTpltTempletPO().getMainProdId());
               
        // 调用接口查询号码信息列表
        Map mapResult = this.prodInstallNxBean.qryNumberByProdid(terminalInfoPO, curMenuId, orgid, sele_rule, tel_prefix, tel_suffix);
        
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
            String errMsg = this.getErrMsg();
            
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
            
            return "installError";
        }
        
        // 数据存放到临时变量中（为做内存分页准备）
        CRSet crset = (CRSet) mapResult.get("returnObj");
        
        this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
        int pageviewnum = nMaxNum;
        
        if (crset.GetRowCount() > nMaxNum)
        {
            pageFlag = "true";
            pageviewnum = pageSize;
        }
        
        tmpList = new ArrayList<ServerNumPO>();
        ServerNumPO serverNumPO = null;        
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            serverNumPO = new ServerNumPO();
            
            // 手机号
            serverNumPO.setTelnum(crset.GetValue(i, 0));
            
            // 费用
            serverNumPO.setFee(crset.GetValue(i, 2));
            
            // 单位
            serverNumPO.setOrg_id(crset.GetValue(i, 3));
                
            tmpList.add(serverNumPO);
        }       
        
        // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        page = (page == 0) ? 1 : page;
        // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）

        
        // 结束行数
        int endRowNum = pageviewnum * page;
        
        // 开始条数
        int startRowNum = endRowNum - pageviewnum;
        
        // 总页数
        // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        pageCount = (tmpList.size() % pageviewnum != 0) ? (tmpList.size() / pageviewnum + 1) : (tmpList.size() / pageviewnum);
        // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 从内存中取得每页数据
        serverNumList = new ArrayList<ServerNumPO>();
        for (int i = startRowNum; i < endRowNum && i < tmpList.size(); i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        // 填冲不满一页的其它对象，防止页面变型
        if (page == pageCount)
        {
            for (int i = 0; i < endRowNum - tmpList.size(); i++)
            {
                // 写入空对象
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                
                serverNumList.add(po);
            }
        }
        
        logger.debug("telNumResult End ...");

        return "telnumResult";
    }

    /**
     * 根据查询类型获取错误信息
     * @return
     * @remark create by jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String getErrMsg()
	{
		String errMsg = "";
		
		// 随机查询
		if ("4".equals(sele_rule))
		{
		    errMsg = "未查询到符合条件的记录";
		    
		    getRequest().setAttribute("backStep", "-2");
		}
		// 按前缀查询
		else if ("2".equals(sele_rule))
		{
			// 查询类型 2：按前缀查询 3：按后缀查询
		    errMsg = "未查询到符合条件的记录(前缀：" + tel_prefix + ")";
		    
		    getRequest().setAttribute("backStep", "-1");
		}
		// 按后缀查询
		else if("3".equals(sele_rule))
		{
		    errMsg = "未查询到符合条件的记录(后缀：" + tel_suffix + ")";
		    
		    getRequest().setAttribute("backStep", "-1");
		}
		return errMsg;
	}
    
    /**
     * 号码资源占选
     * <功能详细描述>
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void telnumTmpPick() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("telnumTmpPick Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("号码资源占选失败！");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String xml = "";
        try
        {
            // 调用号码资源占选接口
            Map map = this.prodInstallNxBean.telNumTmpPick(termInfo, curMenuId, telnum);
            
            // 号码资源占选成功
            if (map != null && map.size() == 0)
            {
                xml = "0";
            }
            // 号码资源占选失败
            else if (map != null && map.size() == 1)
            {
                xml = "1~~" + map.get("returnMsg");
            }
            // 接口调用异常
            else
            {
                xml = "1~~号码资源占选失败";
            }
        }
        catch (Exception e)
        {
            xml = "1~~号码资源占选失败";
            
            logger.error("号码资源占选失败：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("号码资源占选失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("telnumTmpPick end!");
        }
        
        logger.debug("telnumTmpPick End!");
    }
    
    /**
     * 校验卡资源是否可用
     * <功能详细描述>
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void chkBlankNo() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("chkBlankNo Starting...");
        }
        
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
            throw new IOException("校验卡资源是否可用失败！");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);       
        
        String xml = "";
        try
        {
        	// 调用接口校验卡资源是否可用
            Map map = this.prodInstallNxBean.chkBlankNo(termInfo, curMenuId, blankno, orgid);
            
            if (map != null && map.size() == 0)// 成功
            {
                xml = "0";
            }
            else if (map != null && map.size() == 1)// 失败
            {
                xml = "1~~" + map.get("returnMsg");
            }
            else
            {
                xml = "1~~校验卡资源是否可用失败";
            }
        }
        catch (Exception e)
        {
            xml = "1~~校验卡资源是否可用失败";
            
            logger.error("校验卡资源是否可用失败：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("校验卡资源是否可用失败");
                }
            }
        }
        
        logger.debug("chkBlankNo End!");
    }
    
    /**
     * 空白卡资源暂选
     * <功能详细描述>
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void blankCardTmpPick() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("blankCardTmpPick Starting...");
        }
        
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
            throw new IOException("空白卡资源暂选失败！");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);       
        
        String xml = "";
        try
        {
        	// 调用接口空白卡资源暂选
            Map map = this.prodInstallNxBean.blankCardTmpPick(termInfo, curMenuId, blankno, telnum);
            
            if (map != null && map.size() == 2)// 成功
            {
                CTagSet ctagset = (CTagSet)map.get("returnObj");
                
                // SIM卡的相关信息，格式：ICCID,IMSI,EKI,SMSP,PIN1,PIN2,PUK1,PUK2
                String siminfo = ctagset.GetValue("siminfo");
                String[]  siminfovalue = siminfo.split(",");
                String imsi = siminfovalue[1];
                String iccid = siminfovalue[0];
                String smsp = siminfovalue[3];
                xml = "0~~"+imsi+"~~"+iccid+"~~"+smsp;
            }
            else if (map != null && map.size() == 1)// 失败
            {
                xml = "1";
            }
            else
            {
                xml = "2";
            }
        }
        catch (Exception e)
        {
            xml = "1";
            
            logger.error("空白卡资源暂选失败：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("空白卡资源暂选失败");
                }
            }
        }
        
        logger.debug("chkBlankNo End!");
    }
    
    /**
     * 号卡校验
     * <功能详细描述>
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void chkTelSimcard() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("chkTelSimcard Starting...");
        }
        
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
            throw new IOException("号卡校验失败！");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);       
        
        String xml = "";
        try
        {
        	// 调用接口号卡校验
            Map map = this.prodInstallNxBean.chkTelSimcard(termInfo, curMenuId, telnum, iccid, prodid ,orgid);
            
            // 号卡校验可用
            if (map != null && map.size() == 0)
            {
                xml = "0";
            }
            // 号卡校验不可用
            else if (map != null && map.size() == 1)
            {
                xml = "1~~" + map.get("returnMsg");
            }
            // 号卡校验失败
            else
            {
                xml = "1~~号卡校验失败";
            }
        }
        catch (Exception e)
        {
            xml = "1~~号卡校验失败";
            
            logger.error("号卡校验失败：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("号卡校验失败");
                }
            }
        }
        
        logger.debug("chkTelSimcard End!");
    }
    
    /**
     * 设置服务密码页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String setPasswd()
    {
        return "setPasswd";
    }

    /**
     * 记录异常日志
     * @return
     * @see
     */
    public String makeErrLog()
    {
    	// 记录日志
    	addLogInstall();
    	
    	errormessage = "写卡失败，请稍候再试！！！";
    	return "installError";
    }
    
    /**
     * 更新异常日志
     * @return
     * @see
     */
    public String updateErrLog() throws Exception
    {
    	updateLogInstall();
    	return "installError";
    }
    /**
     * 记录开户日志
     */
    public void addLogInstall()
    {
        //获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        installId = prodInstallNxService.getInstallId();
        LogInstallPO logInstallPO = new LogInstallPO();
        logInstallPO.setOid(installId);
        logInstallPO.setTermId(termInfo.getTermid());
        logInstallPO.setRegion(region);
        logInstallPO.setServnumber(telnum);
        logInstallPO.setMainProdId(tpltTempletPO.getMainProdId());
        logInstallPO.setProdTempletId(tpltTempletPO.getTempletId());
        logInstallPO.setRecFee(CommonUtil.yuanToFen(recFee));
        logInstallPO.setCustName(idCardPO.getIdCardName());
        logInstallPO.setCertId(idCardPO.getIdCardNo());
        logInstallPO.setLinkAddr(idCardPO.getIdCardAddr());
        logInstallPO.setSex(idCardPO.getIdCardSex());
        logInstallPO.setBlankCard(blankno);
        logInstallPO.setIccid(iccid);
        logInstallPO.setImsi(imsi);
        logInstallPO.setSmsp(smsp);
        logInstallPO.setWriteCardStatus(writeCardStatus);
        logInstallPO.setPayStatus(payStatus);
        logInstallPO.setInstallStatus(installStatus);
        logInstallPO.setRefundment("2");
        logInstallPO.setNotes(tpltTempletPO.getNotes());
        
        // 记录日志
        this.prodInstallNxService.addLogInstall(logInstallPO);
    }
    
    /**
     * 更新开户日志，同时返回信息
     */
    public void updateInstallLogNotes() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateInstallLogNotes Starting...");
        }
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
            throw new IOException("更新开户日志备注失败！");
        }
        LogInstallPO logInstallPO=new LogInstallPO();
        
        // 流水号
        logInstallPO.setOid(installId);
        
        if("0".equals(notes))
        {
        	notes = "开户吐卡成功！";
        }
        else if("1".equals(notes))
        {
        	notes = "开户吐卡失败！";
        }
        	
        // 备注
        logInstallPO.setNotes(notes);
        String xml = "";
        try
        {
            // 更新开户日志
        	this.prodInstallNxService.updateInstallLogNotes(logInstallPO);
        	xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("更新开户日志备注失败！", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("更新开户日志备注失败！");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateLogInstallForWriter end!");
        }
    }
    
    /**
     * 更新日志
     * 
     */
    public void updateLogInstall()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateLogInstall Starting...");
        }
        
        LogInstallPO logInstallPO = new LogInstallPO();
        
        // 流水号
        logInstallPO.setOid(installId);
        
        // 缴费流水号，与SH_CHARGE_lOG关联 缴费前此列数据为空
        logInstallPO.setChargeId(chargeId);
        
        // 缴费方式，1：银联卡；4：现金
        logInstallPO.setChargeType(payType);
        
        // 实收费用
        logInstallPO.setToFee(CommonUtil.yuanToFen(toFee));
        
        // 默认2：初始状态 0：写卡成功 1：写卡失败 
        logInstallPO.setWriteCardStatus(writeCardStatus);
        
        // 默认2：初始状态 0：缴费成功 1：缴费失败 
        logInstallPO.setPayStatus(payStatus);
        
        // 默认2：初始状态 0：开户成功 1：开户失败
        logInstallPO.setInstallStatus(installStatus);
        
        // 开户受理流水
        logInstallPO.setFormnum(formnum);
        
        // 更新开户日志
    	this.prodInstallNxService.updateInstallLog(logInstallPO);

        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateLogInstall end!");
        }
    }
    
    /**
     * 费用确认页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String feeConfirm()
    {
    	// 开始记录日志
        logger.debug("feeConfirm start...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        prodTempletId = tpltTempletPO.getTempletId();

        // 调用算费接口
        Map mapResult = this.prodInstallNxBean.reckonRecFee(terminalInfoPO, curMenuId, telnum, prodid, prodTempletId, iccid, blankno);
        
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
            String errMsg = "未查询到符合条件的记录";
            
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "1", errMsg);
            
            return "installError";
        }
        
        // 数据存放到临时变量中（为做内存分页准备）
        CRSet crset = (CRSet) mapResult.get("returnObj");
     
        // 总费用
        BigDecimal allFee = new BigDecimal("0");
        
        this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
        feeList = new ArrayList<FeeConfirmPO>();
        FeeConfirmPO feeConfirmPO = null;        
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
        	feeConfirmPO = new FeeConfirmPO();
        	
        	// 费用名称
        	feeConfirmPO.setFeeName((crset.GetValue(i, 0)));
        	
        	// 费用
        	feeConfirmPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 1)));
        	
        	// 数量
        	feeConfirmPO.setNum(crset.GetValue(i, 2));
        	
        	// 费用类型
        	feeConfirmPO.setFeeType(crset.GetValue(i, 3));
            
        	allFee = allFee.add(new BigDecimal(crset.GetValue(i, 1)));
            feeList.add(feeConfirmPO);
        }       
        
        // 添加预存款项
        String preMinFee=this.tpltTempletPO.getMinFee();
        feeConfirmPO = new FeeConfirmPO();
        feeConfirmPO.setFeeName("预存款");
    	feeConfirmPO.setFee(CommonUtil.fenToYuan(preMinFee));
    	feeConfirmPO.setNum("1");
    	feeConfirmPO.setFeeType("预存款项");
    	allFee = allFee.add(new BigDecimal(preMinFee));
    	feeList.add(feeConfirmPO);
    	
    	// 添加费用合计
    	feeConfirmPO = new FeeConfirmPO();
    	feeConfirmPO.setFeeName("费用合计");
    	this.recFee = allFee.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN).toString();
    	feeConfirmPO.setFee(recFee);
    	feeConfirmPO.setNum("1");
    	feeConfirmPO.setFeeType("");
    	feeList.add(feeConfirmPO);
        
        return "feeConfirm";
    }
    
    /**
     * 选择缴费类型
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectPayType()
    {
    	// 记录开户信息
    	addLogInstall();
    	
    	TerminalInfoPO termInfo = (TerminalInfoPO) getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    	
    	// 根据终端组自缓存中获取菜单列表
        String groupID = termInfo.getTermgrpid();
        
        List<MenuInfoPO> menus = null;
        
        if (groupID != null && !"".equals(groupID))
        {      
        	// 获取菜单列表
            menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(groupID);
        }
        
    	// 当页菜单列表
        usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
        
        // findbugs修改 2015-09-02 zKF66389
//        if (logger.isInfoEnabled())
//        {
//            logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//        }
        // findbugs修改 2015-09-02 zKF66389
        // findbugs修改 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
        if (usableTypes.size() == 0)
        // findbugs修改 2015-09-02 zKF66389
        {
            logger.error("没有可用的充值方式");
            
            // 没有可用的充值方式
            setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
            
            // 记录日志
            this.createRecLog(Constants.FEE_CHARGE, "0", "0", "1", "没有可用的充值方式");
        } 
    	
        return "selectPayType";
    }
    
    /**
     * 转向投币页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashCharge()
    {
        // 投币页面标志，投币页面钱箱打开不跳转到现金稽核
        this.getRequest().setAttribute("isCashCharge", "1");

        return "cashCharge";
    }
    
    /**
     * 现金缴费开户
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String installCashCommit()
    {
    	if (logger.isDebugEnabled())
        {
            logger.debug("cashChargeCommit start");
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("用户" + telnum + "投币面额为：" + cashDetail + "；总投币金额为：" + tMoney + "；流水：" + terminalSeq);
        }
        
        String forward = null;
        
        // 防止用户不投币，直接从浏览器中模拟交费请求
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("无效请求");
            
            return "installError";
        }    
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 重复交费问题
        if (!checkCashFee(termInfo))
        {
        	String tipMsg = "非常抱歉，您本次缴费可能有误。 请在自助终端(帐单详单查询->缴费历史查询)、宁夏移动网站或持小票凭证至营业前台查询核实。由此给您带来的不便，敬请谅解！";
        	setErrormessage(tipMsg);
        	return "installError";
        }
        
        // 终端流水号
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        
        // 记录用户缴费日志之前先记录用户的投币情况
        Map<String, String> params = new HashMap<String, String>();
        params.put("termID", termInfo.getTermid());
        params.put("telnum", telnum);
        params.put("terminalSeq", terminalSeq);
        params.put("cashDetail", cashDetail);
        this.prodInstallNxService.insertCashDetailInfo(params);
       
        // 发起开户请求之前首先记录缴费日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        this.setChargeId(prodInstallNxService.getChargeLogOID());
        selfCardPayLogVO.setChargeLogOID(chargeId);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(telnum);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);                 
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));                   
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        selfCardPayLogVO.setRecdate(payDate);        
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(region);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("开户之前记录缴费日志");
        selfCardPayLogVO.setDealnum("");
        
        // 业务类型（install：开户缴费）
        selfCardPayLogVO.setRecType("install");
        
        // 新增缴费日志
        prodInstallNxService.addChargeLog(selfCardPayLogVO);

        // 调用开户接口
        Map mapResult = this.prodInstallNxBean.terminalInstall(termInfo, curMenuId,this.idCardPO, this.tpltTempletPO, telnum, tMoney, this.pwd, imsi, iccid);
        
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
        	// 记录日志
        	String errMsg = "在线开户失败!";
        	
        	// 2：初始状态 0：开户成功 1：开户失败
        	this.setInstallStatus("1");

        	// 缴费开户完成后更新开户日志
        	this.updateLogInstall();
        	
        	// 10：投币成功，缴费失败
        	selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("开户时投币成功，开户失败！");
            
            // 更新缴费日志状态
            prodInstallNxService.updateCardChargeLogStatus(selfCardPayLogVO);
            
            if(mapResult != null)
            {
            	errMsg = (String)mapResult.get("returnMsg");
            	
            }
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "1", errMsg);
            
            return "installError";
        }
        
        // 数据存放到临时变量中
        CTagSet ctagset = (CTagSet) mapResult.get("returnObj");
        
        // 开户流水号码
        formnum = ctagset.GetValue("installformnum");
        
    	// 开户状态 2：初始状态 0：开户成功 1：开户失败
    	this.setInstallStatus("0");
    	
    	// 缴费状态 默认2：初始状态 0：缴费成功 1：缴费失败
    	this.setPayStatus("0");
    	
    	// 更新开户日志
        this.updateLogInstall();
        
    	// 11：投币成功，缴费开户成功
    	selfCardPayLogVO.setStatus("11");
    	selfCardPayLogVO.setDescription("开户时投币成功，开户成功！");
        	
        // 更新缴费日志状态
        prodInstallNxService.updateCardChargeLogStatus(selfCardPayLogVO);
        
        this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "0", "在线开户成功!");
        
        return "printInvoice";

    }
    
    /**
     * 转向读银行卡页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toReadCard()
    {
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        return "toReadCard";
    }
    
    /**
     * 进入银联卡密码输入页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputCardPwd()
    {
        return "inputPwd";
    }
    
    /**
     * 转向确认银行卡缴费金额页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toMakeSure()
    {
        // 取消自动转向首页
        this.getRequest().setAttribute("isCashCharge", "1");
        
        // 是否启动银联密码框 (0：银联密码框   1：华为密码框)
        int pwdBz = PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ") == null ? 0 : Integer.parseInt((String) PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ"));
        if (pwdBz == 0)
        {
            return "makeSure_ylpwd";
        }
        else
        {
            return "toMakeSure";
        }
    }
    
    /**
     * 银联卡缴费开户
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public String installCardCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit start");
        }
        
        HttpSession session = getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Date date = new Date();
        pDealTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        
        CardChargeLogVO cardChargeLogPO = new CardChargeLogVO();
        cardChargeLogPO.setChargeLogOID(chargeId);
        
        // 缴费成
        this.payStatus = "0";
        // 调用开户接口
        Map mapResult = this.prodInstallNxBean.terminalInstall(termInfo, curMenuId,this.idCardPO, this.tpltTempletPO, telnum, tMoney, this.pwd, imsi, iccid);
        
        toFee = CommonUtil.fenToYuan(toFee);
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
            String errMsg = "";
            
            errMsg = "在线开户失败";
            
            getRequest().setAttribute("errormessage", errMsg); 
        	this.installStatus = "1";
        	this.updateLogInstall();
        	
        	// 10：扣款成功，缴费失败：开户失败
        	cardChargeLogPO.setStatus("10");
        	cardChargeLogPO.setDescription("开户时扣款成功，开户失败！");
        	
        	// 更新缴费日志
        	prodInstallNxService.updateCardChargeLogStatus(cardChargeLogPO);
            
            this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "1", errMsg);
            
            return "installError";
        }
        
        // 数据存放到临时变量中
        CTagSet ctagset = (CTagSet) mapResult.get("returnObj");
        
        // 开户流水号码
        formnum = ctagset.GetValue("installformnum");
        
     	this.installStatus = "0";
     	this.updateLogInstall();
     	
     	// 11：扣款成功，缴费成功：开户成功
    	cardChargeLogPO.setStatus("11");
    	cardChargeLogPO.setDescription("开户时扣款成功，开户成功！");
    	
    	// 更新缴费日志
    	prodInstallNxService.updateCardChargeLogStatus(cardChargeLogPO);
    	
    	// 处理显示
        if (tMoney != null)
        {
            while (tMoney.startsWith("0"))
            {
                tMoney = tMoney.substring(1);
            }
        }
        else
        {
            tMoney = "";
        }
        tMoney = Integer.parseInt(tMoney) / 100 + "";
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit end");
        }
        
        this.createRecLog(Constants.SH_PROD_INSTALL_NX, "0", "0", "0", "在线开户成功!");
        
        return "installCardCommit";
    }
    
    /**
     * 分页
     * <功能详细描述>
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TpltTempletPO> getPageList(List<TpltTempletPO> list)
    {
        int sum=0;
        int start=0;
        int end=0;
        
        // 获取当前页
        if (page == 0)
        {
            page = 1;
        }
        
        // 获取总页数
        if (!list.isEmpty())
        {
            sum=list.size();
            pageCount = list.size() / pageSize;
            if (list.size() % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        else
        {
            pageCount = 0;
        }
        
        start = (page - 1) * pageSize;
        if (page >= pageCount)
        {
            end = sum;
        }else{
            end = page * pageSize;
        }
        
        List<TpltTempletPO> spList = new ArrayList<TpltTempletPO>();
        
        for (int i = start; i < end; i++)
        {
            spList.add(list.get(i));
        }
        return spList;
    }
    
    /**
     * 重复交费判断
     * 
     * @param termInfo 终端机信息
     * @return true，未重复；false：重复
     * @since 
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {    
    	// 现金交费流水日志记录标识，1记。
        String cashChargeFlag = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_FLAG");
        if ("1".equalsIgnoreCase(cashChargeFlag))
        {
            String seq = termInfo.getTermid().concat("_").concat(terminalSeq).concat("_").concat(telnum).concat("_").concat(tMoney);
                        
            synchronized(RefreshCacheNX.cashChargeRecords)
            {
                if (RefreshCacheNX.cashChargeRecords.containsKey(seq))
                {
                    String tmpErrorMsg = "重复缴费:手机号[".concat(telnum).concat("], 投币金额[").concat(tMoney)
                            .concat("]元, 操作员[").concat(termInfo.getOperid()).concat("], 流水号[").concat(terminalSeq).concat("]");
                    
                    logger.error(tmpErrorMsg);

                    return false;
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("向缓存中插入缴费信息：" + seq);
                    }
                    
                    RefreshCacheNX.cashChargeRecords.put(seq, DateUtilHub.curOnlyTime());
                }
            }
        }
        
        return true;
    }
    
    /**
     * 扣款之前增加银联卡缴费日志
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog Starting...");
        }
        
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
            throw new IOException("扣款之前记录日志失败");
        }
        
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String payType = (String)request.getParameter("paytype");
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        chargeId = prodInstallNxService.getChargeLogOID();
        cardChargeLogVO.setChargeLogOID(chargeId);
        
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setTermID(termInfo.getTermid());
        cardChargeLogVO.setOperID(termInfo.getOperid());
        cardChargeLogVO.setServnumber(telnum);
        cardChargeLogVO.setPayType(payType);
        cardChargeLogVO.setFee(tMoney);
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
        
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        cardChargeLogVO.setDealnum("");
        cardChargeLogVO.setAcceptType("");
        cardChargeLogVO.setServRegion(this.region);
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setPosNum("");
        cardChargeLogVO.setBatchNumBeforeKoukuan("");
        
        // 业务类型（phone：话费缴费  favourable：优惠缴费,install: 开户缴费）
        cardChargeLogVO.setRecType("install");
        cardChargeLogVO.setBankno("");
        
        String xml = "";
        try
        {
            // 插入缴费日志
            this.prodInstallNxService.addChargeLog(cardChargeLogVO);
            xml = "1~~" + chargeId;
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog end!");
        }
        
        logger.debug("addCardPayLog End!");
    }

    /**
     * 扣款成功之后，更新交费日志
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
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("扣款并缴费之后记录日志失败");
        }
        
        // 状态
        String status = (String)request.getParameter("status");
        
        // 描述
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        // id
        cardChargeLogVO.setChargeLogOID(request.getParameter("chargeId"));
        
        // 商户编号
        cardChargeLogVO.setUnionpayuser(request.getParameter("unionpayuser"));
        
        // 终端编号
        cardChargeLogVO.setUnionpaycode(request.getParameter("unionpaycode"));
        
        // 交易类型
        cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(request.getParameter("busitype"), "UTF-8"));

        //往数据库里存入加密后的银联卡号
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(request.getParameter("cardnumber")));

        // 批次号
        cardChargeLogVO.setBatchnum(request.getParameter("batchnum"));
        
        // 授权码
        cardChargeLogVO.setAuthorizationcode(request.getParameter("authorizationcode"));
        
        // 交易参考号
        cardChargeLogVO.setBusinessreferencenum(request.getParameter("businessreferencenum"));
        
        // 银联扣款时间
        cardChargeLogVO.setUnionpaytime(new SimpleDateFormat("yyyy").format(new Date()) + request.getParameter("unionpaytime"));
        
        // 凭证号
        cardChargeLogVO.setVouchernum(request.getParameter("vouchernum"));
        
        // 扣款金额
        String unionpayfee = request.getParameter("unionpayfee");
        
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
        
        // 扣款金额
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        
        // 终端流水
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        // 状态
        cardChargeLogVO.setStatus(status);
        
        // 描述
        cardChargeLogVO.setDescription(description);
        
        // 银行行号
        cardChargeLogVO.setBankno(request.getParameter("bankno"));
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        // 交易时间
        cardChargeLogVO.setRecdate(payDate);
        
        String xml = "";
        try
        {
            // 更新缴费日志
            this.prodInstallNxService.updateCardChargeLog(cardChargeLogVO);
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog end!");
        }
    }
    
    /**
     * 校验当前时间现金缴费是否可用。1，可用；0，不可用
     * 
     * @throws Exception
     * @see 
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
            throw new IOException("判断现金交费是否可用失败");
        }
        
        String xml = "0";
        
        try
        {
            // 2355-0005
            String time = (String) PublicCache.getInstance().getCachedData("SH_CHARGE_CASH_LIMIT");
            
            if (time != null && !"".equals(time.trim()))
            {
                // 当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
                String currTime = sdf.format(new Date());
                
                // 当前时间在0025至2320之间时可用
                String[] times = time.split("-");
                if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
                {
                    xml = "1";
                }
            }
        }
        catch (Exception e)
        {
            logger.error("判断现金交费是否可用失败：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.print(xml);
                
                try
                {
                	writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("判断现金交费是否可用失败");
                }
            }
        }        
    }
    
    /**
     * 注入BEAN
     * <功能详细描述>
     * @param prodInstallNxBean
     * @see [类、类#方法、类#成员]
     */
    public void setProdInstallNxBean(ProdInstallNxBean prodInstallNxBean)
    {
        this.prodInstallNxBean = prodInstallNxBean;
    }

    /**
     * 注入SERVICE
     * <功能详细描述>
     * @param prodInstallNxService
     * @see [类、类#方法、类#成员]
     */
    public void setProdInstallNxService(ProdInstallNxService prodInstallNxService)
    {
        this.prodInstallNxService = prodInstallNxService;
    }

    public IdCardPO getIdCardPO()
    {
        return idCardPO;
    }

    public void setIdCardPO(IdCardPO idCardPO)
    {
        this.idCardPO = idCardPO;
    }

    public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<TpltTempletPO> getTpltTempletList()
    {
        return tpltTempletList;
    }

    public void setTpltTempletList(List<TpltTempletPO> tpltTempletList)
    {
        this.tpltTempletList = tpltTempletList;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public String getOrgid()
    {
        return orgid;
    }

    public void setOrgid(String orgid)
    {
        this.orgid = orgid;
    }

    public String getSele_rule()
    {
        return sele_rule;
    }

    public void setSele_rule(String sele_rule)
    {
        this.sele_rule = sele_rule;
    }

    public String getTel_prefix()
    {
        return tel_prefix;
    }

    public void setTel_prefix(String tel_prefix)
    {
        this.tel_prefix = tel_prefix;
    }

    public String getTel_suffix()
    {
        return tel_suffix;
    }

    public void setTel_suffix(String tel_suffix)
    {
        this.tel_suffix = tel_suffix;
    }

    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }

    public int getNMaxNum()
    {
        return nMaxNum;
    }

    public void setNMaxNum(int maxNum)
    {
        nMaxNum = maxNum;
    }

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public String getProdid()
    {
        return prodid;
    }

    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    public ProdInstallNxBean getProdInstallNxBean()
    {
        return prodInstallNxBean;
    }

    public ProdInstallNxService getProdInstallNxService()
    {
        return prodInstallNxService;
    }

    public String getBlankno()
    {
        return blankno;
    }

    public void setBlankno(String blankno)
    {
        this.blankno = blankno;
    }

    public String getRectype()
    {
        return rectype;
    }

    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getCardtype()
    {
        return cardtype;
    }

    public void setCardtype(String cardtype)
    {
        this.cardtype = cardtype;
    }

    public String getImsi()
    {
        return imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public String getIccid()
    {
        return iccid;
    }

    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }

    public String getSmsp()
    {
        return smsp;
    }

    public void setSmsp(String smsp)
    {
        this.smsp = smsp;
    }
    
    public String getPwd() 
    {
		return pwd;
	}

	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}

	public String getProdTempletId() 
	{
		return prodTempletId;
	}

	public void setProdTempletId(String prodTempletId)
	{
		this.prodTempletId = prodTempletId;
	}

	public String getMinfee() 
	{
		return minfee;
	}

	public void setMinfee(String minfee) 
	{
		this.minfee = minfee;
	}

	public List<FeeConfirmPO> getFeeList()
	{
		return feeList;
	}

	public void setFeeList(List<FeeConfirmPO> feeList) 
	{
		this.feeList = feeList;
	}

	public TpltTempletPO getTpltTempletPO() 
	{
		return tpltTempletPO;
	}

	public void setTpltTempletPO(TpltTempletPO tpltTempletPO)
	{
		this.tpltTempletPO = tpltTempletPO;
	}
	
	public String getRecFee()
	{
		return recFee;
	}

	public void setRecFee(String recFee)
	{
		this.recFee = recFee;
	}

	public List getUsableTypes() 
	{
		return usableTypes;
	}

	public void setUsableTypes(List usableTypes) 
	{
		this.usableTypes = usableTypes;
	}

	public String getTMoney() 
	{
		return tMoney;
	}

	public void setTMoney(String money) 
	{
		tMoney = money;
	}

	public String getTerminalSeq() 
	{
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) 
	{
		this.terminalSeq = terminalSeq;
	}

	public String getCashDetail() 
	{
		return cashDetail;
	}

	public void setCashDetail(String cashDetail) 
	{
		this.cashDetail = cashDetail;
	}

	public String getPayType() 
	{
		return payType;
	}

	public void setPayType(String payType) 
	{
		this.payType = payType;
	}

	public String getPDealTime() 
	{
		return pDealTime;
	}

	public void setPDealTime(String dealTime)
	{
		pDealTime = dealTime;
	}

	public String getDealNum() 
	{
		return dealNum;
	}

	public void setDealNum(String dealNum)
	{
		this.dealNum = dealNum;
	}

	public String getNeedReturnCard() 
	{
		return needReturnCard;
	}

	public void setNeedReturnCard(String needReturnCard)
	{
		this.needReturnCard = needReturnCard;
	}
	
    
	public String getWriteCardStatus()
	{
		return writeCardStatus;
	}

	public void setWriteCardStatus(String writeCardStatus) 
	{
		this.writeCardStatus = writeCardStatus;
	}

	public String getPayStatus() 
	{
		return payStatus;
	}

	public void setPayStatus(String payStatus) 
	{
		this.payStatus = payStatus;
	}

	public String getInstallStatus() 
	{
		return installStatus;
	}

	public void setInstallStatus(String installStatus)
	{
		this.installStatus = installStatus;
	}

	public String getChargeId() 
	{
		return chargeId;
	}

	public void setChargeId(String chargeId) 
	{
		this.chargeId = chargeId;
	}

	public String getInstallId() 
	{
		return installId;
	}

	public void setInstallId(String installId) 
	{
		this.installId = installId;
	}

	public String getToFee() 
	{
		return toFee;
	}

	public void setToFee(String toFee) 
	{
		this.toFee = toFee;
	}

	public String getCanNotPrint()
	{
		return canNotPrint;
	}

	public void setCanNotPrint(String canNotPrint) 
	{
		this.canNotPrint = canNotPrint;
	}
	
	public String getFormnum() 
	{
		return formnum;
	}

	public void setFormnum(String formnum) 
	{
		this.formnum = formnum;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
}
