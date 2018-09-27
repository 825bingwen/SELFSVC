package com.gmcc.boss.selfsvc.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.SelfSvcCall;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.dao.SelfSvcDaoImpl;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * <公共基础的Service调用类>
 * <功能详细描述>
 * 
 * @author  wWX217192
 * @version  [版本号, Apr 22, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by wWX219697 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
 */
public class BaseServiceImpl 
{
	protected SelfSvcCall selfSvcCall;
	
	private SelfSvcDaoImpl selfSvcDaoImpl;
	
	/**
	 * 获取request的值
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        
        return request;
	}
	
	public HttpServletResponse getResponse()
	{
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		return response;
	}
	
	/**
	 * 获取session的值
	 * @return
	 */
	public HttpSession getSession()
	{
		HttpSession session = this.getRequest().getSession();
		
		return session;
	}

    /**
     * <记录业务日志>
     * <功能详细描述>
     * @param busiType 业务类型
     * @param recFormnum 业务流水号
     * @param recFee 业务受理费用，无费用的为0
     * @param recStatus 0:成功。1:失败
     * @param recStatusDesc 描述信息
     * @see [类、类#方法、类#成员]
     */
    public void insertRecLog(String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
    	// 终端机信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 业务推荐营业员的手机号码
        String rectel = "";
        
        // 客户信息
        NserCustomerSimp customer = this.getCustomer();
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// 区域
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }
        }
        else
        {
            selfSvcLogVO.setRegion("");// 区域
        }
       
        if (customer != null)
        {
            selfSvcLogVO.setServnumber(customer.getServNumber());// 手机号码
        }
        else
        {
            selfSvcLogVO.setServnumber("");// 手机号码
        }
        
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// 终端编号
        selfSvcLogVO.setTourchoid(customer == null ? "" : customer.getContactId());// 统一接触流水
        
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// 业务受理单位编码
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// 操作员工号
        
        selfSvcLogVO.setBusitype(busiType);// 业务类型
        selfSvcLogVO.setRecformnum(recFormnum);// 业务流水号
        selfSvcLogVO.setRecfee(recFee);// 业务受理金额
        selfSvcLogVO.setRecstatus(recStatus);// 业务受理状态
        if(recStatusDesc.length()>256)
        {
            recStatusDesc = recStatusDesc.substring(0,256);
        }
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// 业务受理描述
        selfSvcLogVO.setBrandID(customer == null ? "" : customer.getBrandID());  //用户品牌
        selfSvcLogVO.setRectel(rectel);
        
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <记录业务日志>
     * <功能详细描述>
     * @param servNumber 手机号码
     * @param busiType 业务类型
     * @param recFormnum 业务流水号
     * @param recFee 业务受理费用，无费用的为0
     * @param recStatus 0:成功。1:失败
     * @param recStatusDesc 描述信息
     * @see [类、类#方法、类#成员]
     */
    public void insertRecLogTelnum(String servNumber, String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
    	// 终端机信息
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // 业务推荐营业员的手机号码
        String rectel = "";
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// 区域
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }            
        }
        else
        {
            selfSvcLogVO.setRegion("");// 区域
        }
        
        selfSvcLogVO.setServnumber(servNumber);// 手机号码
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// 终端编号
        selfSvcLogVO.setTourchoid("");// 统一接触流水
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// 业务受理单位编码
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// 操作员工号
        
        selfSvcLogVO.setBusitype(busiType);// 业务类型
        selfSvcLogVO.setRecformnum(recFormnum);// 业务流水号
        selfSvcLogVO.setRecfee(recFee);// 业务受理金额
        selfSvcLogVO.setRecstatus(recStatus);// 业务受理状态
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// 业务受理描述
        selfSvcLogVO.setRectel(rectel);
        
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <获取终端机信息>
     * <功能详细描述>
     * @param recStatusDesc 描述信息
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public TerminalInfoPO getTermInfo()
    {
    	// 终端机信息
       return (TerminalInfoPO)this.getSession().getAttribute(Constants.TERMINAL_INFO);
    }
    
    /**
     * <获取登录的用户信息>
     * <功能详细描述>
     * @param recStatusDesc 描述信息
     * @see [类、类#方法、类#成员]
     * @remark create by wWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public NserCustomerSimp getCustomer()
    {
    	return (NserCustomerSimp)this.getSession().getAttribute(Constants.USER_INFO);
    }
    
    @Autowired
    public void setSelfSvcCall(SelfSvcCall selfSvcCall)
    {
        this.selfSvcCall = selfSvcCall;
    }

	public SelfSvcDaoImpl getSelfSvcDaoImpl() {
		return selfSvcDaoImpl;
	}

	@Autowired
	public void setSelfSvcDaoImpl(SelfSvcDaoImpl selfSvcDaoImpl) {
		this.selfSvcDaoImpl = selfSvcDaoImpl;
	}
}
