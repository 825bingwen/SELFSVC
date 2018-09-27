package com.customize.nx.selfsvc.invoice.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.MonInvoicePrintBean;
import com.customize.nx.selfsvc.invoice.model.CyclePO;
import com.customize.nx.selfsvc.invoice.model.MonInvoicePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 月结发票
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-6-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonInvoicePrintNXAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//private static Logger logger = Logger.getLogger(MonInvoicePrintNXAction.class);
	private static Log logger = LogFactory.getLog(MonInvoicePrintNXAction.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	
	/**
	 * 调用接口bean
	 */
	private MonInvoicePrintBean monInvoPrintBean;
	
	/**
	 * 月结发票po
	 */
	private MonInvoicePO monInvoicePO;
	
	/**
	 * 账期
	 */
	private String cycle;
	
	/**
	 * 当前菜单
	 */
	private String curMenuId;
	
	/**
	 * 账期
	 */
	private CyclePO cyclepo = new CyclePO();
	
	/**
	 * 地区名称
	 */
	private String regionName;
	
	/**
	 * 操作员id
	 */
	private String operid;
	
	/**
     * 查询近六个月月份
     * 
     * @return
     * @remark add by jWX216858 on 20140616 for OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
    public String qryCurrentMonth()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();

        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        
        for(int i = 0; i < 6; i++)
        {
        	request.setAttribute("month" + i, CommonUtil.getLastMonth(pattern, i));
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonth End");
        }
        
        return "qryCurrentMonth";
    }

    /**
     * 查询账期功能
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     * @remark add by jWX216858 on 20140616 for OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
	public String qryBillCycle() throws Exception
    {
    	if(logger.isDebugEnabled())
    	{
    		logger.debug("qryBillCycle Starting ...");
    	}
    	
    	HttpServletRequest request = this.getRequest();
    	
    	// 获取session信息
    	HttpSession session = request.getSession();
    	
    	// 获取客户信息
    	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    	
    	// 获取终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	//调用查询账期接口返回的数据
    	ReturnWrap rw = monInvoPrintBean.qryBillCycle(customer.getServNumber(), termInfo, curMenuId, cycle);
    	
    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 取返回对象
            Vector vector = (Vector)(rw.getReturnObject());
            
            // 账期信息
            CRSet crset = (CRSet)(vector.get(1));
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                cyclepo.setCycle(crset.GetValue(i, 0));// 帐期
                cyclepo.setStartdate(crset.GetValue(i, 1));// 开始时间
                cyclepo.setEnddate(crset.GetValue(i, 2));// 结束时间
                cyclepo.setAcctid(crset.GetValue(i, 3));// 主账号
                cyclepo.setUnionacct(crset.GetValue(i, 4));// 是否合户用户。1，是；0，不是
            }
            
            // 返回
            return "qryBillCycle";
        }
        else
        {
        	request.setAttribute("errormessage", "账期查询失败！");
        	
        	request.setAttribute("backStep", "-1");
        	
            // 返回
            return "error";
        }
    }

    /**
     * 获取月结发票数据
     * 
     * @return
     * @remark add by jWX216858 on 20140616 for OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
    public String printMonInvoice()
    {
    	if (logger.isDebugEnabled())
    	{
    		logger.info("invoiceList start");
    	}
    	HttpServletRequest request = this.getRequest();
    	
    	// 获取session
    	HttpSession session = request.getSession();
    	
    	// 获取客户信息
    	NserCustomerSimp customer = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
    	
    	// 获取终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
    	// 调用月结发票数据查询接口，获取月结发票数据信息
    	ReturnWrap rw = monInvoPrintBean.invoiceData(termInfo, customer, curMenuId, cyclepo);
    	
    	// 获取地区名称
    	regionName = customer.getRegionName();
    	
    	// 获取操作员id
    	operid = termInfo.getOperid();
    	
    	// 接口调用成功，进行数据封装
    	if (null != rw && rw.getStatus() == SSReturnCode.SUCCESS)
    	{
    		// 发票流详细信息
    		CRSet crset = (CRSet)rw.getReturnObject();
    		
    		if(null != crset)
    		{
    			monInvoicePO = getInvoiceInfo(crset);
    			
    			// 设置打印发票手机号码
    			monInvoicePO.setTelnum(customer.getServNumber());
    		}
    	}
    	else if(rw != null && rw.getStatus() == SSReturnCode.ERROR)
    	{
    		request.setAttribute("errormessage", rw.getReturnMsg());
    		
    		// 记录查询月结发票信息失败日志
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_NX, "0", "0", "1", rw.getReturnMsg());
            return "error";
    	}
    	else
    	{
    		request.setAttribute("errormessage", "获取发票信息失败!");
    		
    		// 记录查询月结发票信息失败日志
            this.createRecLog(customer.getServNumber(), Constants.MONTHINVOICE_PRINT_NX, "0", "0", "1", "获取月结发票信息失败!");
            return "error";
    	}
    	
    	if (logger.isDebugEnabled())
    	{
    		logger.info("invoiceList End");
    	}
    	return "printMonInvoice";
    }
    
    /**
     * 对客服返回的打印发票内容进行处理(把返回的CRSet转换成对象)
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add by jWX216858 on 20140616 for OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
    public MonInvoicePO getInvoiceInfo(CRSet crset)
    {
    	// 从接口返回值中获取发票信息
    	Map<String, String> invoiceMaps = new HashMap<String, String>();
        
        // 遍历crset的数据
        for(int i = 0; i < crset.GetRowCount(); i++)
        {
        	String name = crset.GetValue(i, 0);
        	String value = crset.GetValue(i, 1);
        	
    		invoiceMaps.put(name, value);
        }
        
        MonInvoicePO monInvo = new MonInvoicePO();
        
        // 客户全称
        monInvo.setCustomerName(invoiceMaps.get("username"));
        
        String feeTime = invoiceMaps.get("callfeeCreateTime");
        
        // 款项性质
        monInvo.setFundType(feeTime.substring(0, 4)+ "年" + feeTime.substring(5, 7) + "月发票");
        
        // 通信服务费名称
        monInvo.setCommServFeeName(invoiceMaps.get("ContentItem1Name"));
        
        // 通信服务费金额
        monInvo.setCommServFee(invoiceMaps.get("ContentItem1"));
        
        // 销售折扣
        monInvo.setSellDiscount(invoiceMaps.get("presentused"));
        
        // 本次发票金额
        monInvo.setInvoiceFee(invoiceMaps.get("thisinvamt"));
        
        // 合同号
        monInvo.setContractNum(invoiceMaps.get("paynumno"));
        
        // 金额
        monInvo.setFee(invoiceMaps.get("totalmoney"));
        
        // 流水号
        monInvo.setFormNum(invoiceMaps.get("formnum"));
        
        // 打印时间
        monInvo.setPrintTime(invoiceMaps.get("time"));
        
        return monInvo;
    }
    
	public MonInvoicePrintBean getMonInvoPrintBean() {
		return monInvoPrintBean;
	}

	public void setMonInvoPrintBean(MonInvoicePrintBean monInvoPrintBean) {
		this.monInvoPrintBean = monInvoPrintBean;
	}

	public MonInvoicePO getMonInvoicePo() {
		return monInvoicePO;
	}

	public void setMonInvoicePo(MonInvoicePO monInvoicePO) {
		this.monInvoicePO = monInvoicePO;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CyclePO getCyclepo() {
		return cyclepo;
	}

	public void setCyclepo(CyclePO cyclepo) {
		this.cyclepo = cyclepo;
	}

	public MonInvoicePO getMonInvoicePO() {
		return monInvoicePO;
	}

	public void setMonInvoicePO(MonInvoicePO monInvoicePO) {
		this.monInvoicePO = monInvoicePO;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

}
