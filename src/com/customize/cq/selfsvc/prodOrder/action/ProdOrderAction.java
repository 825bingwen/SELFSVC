package com.customize.cq.selfsvc.prodOrder.action;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.ProdOrderBean;
import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.customize.cq.selfsvc.prodOrder.service.ProdOrderService;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


/**
 * 产品查询受理
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderAction extends BaseAction
{
//    
//    private static Log logger = LogFactory.getLog(ProdOrderAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单
//    private String curMenuId;
//    // end zKF66389 findbus清零
//    
//    // 页号
//    private String pageCount;
//    
//    // begin zKF66389 findbus清零
//    // 错误信息
//    private String errorMessage;
//    // end zKF66389 findbus清零
//    
//    //成功信息
//    private String successMessage;
//    
//    // 用户已订购产品结果集
//    private Vector orderedProdList;
//    
//    // 用户可订购的增值产品集合
//    private List<ProdOrderPO> availProdList;
//    
//    // 接口调用
//    private ProdOrderBean prodOrderBean;
//    
//    // 接口调用
//    private ReceptionBean receptionBean;
//    
//    // 数据库调用service
//    ProdOrderService prodOrderService;
//    
//    /**
//     * 选择已开通或可开通业务
//     * @return
//     */
//    public String showType()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("showType Starting ...");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("showType End");
//        }
//        
//        return "success";
//    }
//    
//    //查询类型
//    private String type;
//    
//	/**
//     * 产品查询
//     * 
//     * @return
//     */
//    public String queryProdList()
//    {
//        logger.debug("queryProdList start!");
//        
//        String forward = null;
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        if(pageCount != null)
//        {
//            this.getRequest().setAttribute("pagecount", pageCount.split(",")[0]);
//        }
//        
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        Map result = prodOrderBean.queryService(terminalInfoPO, customer, curMenuId, "0");
//        if (result != null && result.size() > 1)
//        {
//            CRSet crset = (CRSet)result.get("returnObj");
//            
//            if (crset != null && crset.GetRowCount() > 0)
//            {
//            	forward = "prodOrdered";
//                if("1".equals(type))forward = "prodOrdering";
//                // 将得到的结果数据放到页面显示的前提
//                Vector v = new Vector();
//                v.add(new CEntityString("产品名称,描述,开通时间,操作"));
//                v.add(crset);
//                
//                // 设置结果集
//                setOrderedProdList(v);
//            }
//            else
//            {
//                // 创建错误日志
//                this.createRecLog(Constants.BUSITYPE_PRODORDER, "0", "0", "0", "查询成功，但是用户没有订购任何增值产品。");
//            }
//            
//        	// 从自助终端数据库中查询用户可订购的增值产品
//        	availProdList = prodOrderService.qryProdOrderList(customer);
//        	
//        	// 过滤掉用户已经订购的增值产品,过滤条件为nid与ncode相同。
//        	CRSet orderedProdData = (CRSet)orderedProdList.get(1);
//        	if(availProdList != null && orderedProdData != null && availProdList.size() >0 && orderedProdData.GetRowCount() >0)
//        	{
//        		//已订购产品分组
//        		String orderedGroup = ";";
//        		StringBuffer sbuf = new StringBuffer(orderedGroup);
//        		for(int i=0;i<orderedProdData.GetRowCount();i++)
//        		{
//        			String nid = orderedProdData.GetValue(i, 3);
//        			for(int j=0;j<availProdList.size();j++)
//        			{
//        				ProdOrderPO prodOrderPO = availProdList.get(j);
//        				String ncode = prodOrderPO.getNcode();
//        				String groupId = ";";
//        				if(prodOrderPO.getGroupcolor()!=null)groupId = prodOrderPO.getGroupcolor()+";";
//        				if(!";".equals(groupId)&&orderedGroup.indexOf(";"+groupId)!=-1){
//        					availProdList.remove(j);
//        				}else{
//        					if(nid.equals(ncode))
//            				{
//        						if(!";".equals(groupId))
//        						sbuf.append(groupId);
//            					availProdList.remove(j);
//            				}
//        				}
//        			}
//        		}
//        		orderedGroup = sbuf.toString();
//        	}
//        }
//        else
//        {
//            // 设置错误信息
//            setErrorMessage((String) result.get("returnMsg"));
//            forward = "error";
//            
//            // 创建错误日志
//            this.createRecLog(Constants.BUSITYPE_PRODORDER, "0", "0", "0", "查询已订购产品失败，请稍后再试。带来不便，敬请原谅。");
//        }
//        
//        logger.debug("queryProdList end!");
//        return forward;
//        
//    }
//    
//    //业务代码
//    private String ncode;
//    //生效方式
//    private String effType;
//    //操作方式
//    private String operType;
//    
//    /**
//     * 订购退订增值产品
//     * 
//     * @return
//     */
//    public String orderProd()
//    {
//    	logger.debug("orderProd start"); 
//        
//        String forward = "error";
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        //判断当前菜单是否为空
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        if (effType == null)
//        {
//        	effType = "";
//        }
//        
//        ReturnWrap result = receptionBean.recCommonServ(customer, terminalInfoPO, ncode, operType, effType, "", curMenuId);
//        
//        if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
//        {
//            forward = "success";
//            
//            this.createRecLog(curMenuId, "0", "0", "0", "业务受理成功");
//            
//            this.getRequest().setAttribute("errormessage", "业务受理成功");
//            
//            if (logger.isInfoEnabled())
//            {
//                logger.info("业务(" + ncode + ")受理成功");
//            }
//        }
//        else
//        {
//            this.createRecLog(curMenuId, "0", "0", "1", "业务受理失败");
//            
//            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
//            
//            logger.error("业务(" + ncode + ")受理失败");
//        }
//        
//        logger.debug("orderProd end!");
//        return forward;
//        
//    }
//    
//    // begin zKF66389 findbus清零
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus清零
//
//	public String getPageCount()
//    {
//        return pageCount;
//    }
//    
//    public void setPageCount(String pageCount)
//    {
//        this.pageCount = pageCount;
//    }
//    
//    public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//	public Vector getOrderedProdList()
//    {
//        return orderedProdList;
//    }
//    
//    public void setOrderedProdList(Vector orderedProdList)
//    {
//        this.orderedProdList = orderedProdList;
//    }
//    
//    public ProdOrderBean getProdOrderBean()
//    {
//        return prodOrderBean;
//    }
//    
//    public void setProdOrderBean(ProdOrderBean prodOrderBean)
//    {
//        this.prodOrderBean = prodOrderBean;
//    }
//    
//    public ReceptionBean getReceptionBean()
//    {
//        return receptionBean;
//    }
//    
//    public void setReceptionBean(ReceptionBean receptionBean)
//    {
//        this.receptionBean = receptionBean;
//    }
//
//    public String getNcode()
//    {
//        return ncode;
//    }
//
//    public void setNcode(String ncode)
//    {
//        this.ncode = ncode;
//    }
//
//    public String getEffType()
//    {
//        return effType;
//    }
//
//    public void setEffType(String effType)
//    {
//        this.effType = effType;
//    }
//    
//    public String getOperType()
//    {
//        return operType;
//    }
//
//    public void setOperType(String operType)
//    {
//        this.operType = operType;
//    }
//
//    public String getSuccessMessage()
//    {
//        return successMessage;
//    }
//
//    public void setSuccessMessage(String successMessage)
//    {
//        this.successMessage = successMessage;
//    }
//
//    public ProdOrderService getProdOrderService() 
//    {
//		return prodOrderService;
//	}
//
//	public void setProdOrderService(ProdOrderService prodOrderService) 
//	{
//		this.prodOrderService = prodOrderService;
//	}
//
//	public List getAvailProdList() 
//	{
//		return availProdList;
//	}
//
//	public void setAvailProdList(List availProdList) 
//	{
//		this.availProdList = availProdList;
//	}
//	
//	public String getType()
//    {
//        return type;
//    }
//
//    public void setType(String type)
//    {
//        this.type = type;
//    }
}
