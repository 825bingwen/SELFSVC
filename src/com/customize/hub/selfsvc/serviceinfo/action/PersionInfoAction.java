package com.customize.hub.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.PersionInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.serviceinfo.action.FavourableAction;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 湖北我的个人信息查询
 * 
 * @author yKF28472
 * 
 */
public class PersionInfoAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(FavourableAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId = "";
    
    // 结果套餐信息标题
    private String[] servicetitle;

    // 结果已开通服务标题
    private String[] favourableServicetitle;
    
    // 设置套餐信息结果
    private List result;
    
    // 设置已开通服务结果
    private List favourableResult;
    
    //客户信息
    private NserCustomerSimp customer;
    
    // 调用接口bean
    private PersionInfoBean qryPersionInfoBean;

    /**
     * 套餐信息查询
     * 
     * @return
     */
    public String qryPersionInfo()
    {
    	String forward = "qryPersionInfo";
    	
        logger.debug("qryPersionInfo Starting...");
        
        // 当前月份
        String month = CommonUtil.getLastMonth("yyyyMM", 0);
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp nserCustomerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 页面展现品牌信息
        customer = nserCustomerSimp;
        
        // 查询套餐信息
        
        // 执行查询
        Map mapResult = qryPersionInfoBean.qryComboInfo(terminalInfoPO, nserCustomerSimp, curMenuId, month);
        
        // 处理返回
        
        // 定义结果标题名称
        String[] titles = {"业务类型", "套餐名称", "赠送类型", "赠送总量", "累计使用量", "单位"};
        
        // 设置标题名称
        setServicetitle(titles);
        
        // 结果
        result = new ArrayList();
        
        if (mapResult.get("returnObj") != null && mapResult.size() > 0)
        {
            Vector retData = (Vector)(mapResult.get("returnObj"));

            // 构造[[val,val,val,..],[],[]...]形式的二维ArrayList存放返回的数据
            CRSet cr = (CRSet)(retData.get(1));
            List listInner = null;
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                listInner = new ArrayList();
                listInner.add(cr.GetValue(i, 0));
                for (int j = 2; j <= 6; j++)
                {
                    listInner.add(cr.GetValue(i, j));
                }
                result.add(listInner);
            }
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
        }
        else if (mapResult.get("returnObj") == null)
        {
            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            String resultMsg = getConvertMsg((String) mapResult.get("returnMsg"), "zz_04_15_000002", 
                    String.valueOf(mapResult.get("errcode")), null);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", resultMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        }
        
        // 查询产品展示
        
        // 定义结果标题名称
        String[] titles1 = {"类型","业务名称","生效日期","失效日期"};
        
        // 设置标题名称
        setFavourableServicetitle(titles1);
        
        // 结果
        favourableResult = new ArrayList();
        
        // 查询本机品牌资费及已开通功能（产品展示）
        Map result = qryPersionInfoBean.qryFavourable(terminalInfoPO, customer, curMenuId);
        
        if (result.get("returnObj") != null && result.size() > 0)
        {   
            Vector servicecont = new Vector();
            CRSet cr = (CRSet)result.get("returnObj");
            
            // 拼装数据
            List listInner = null;
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
            	listInner = new ArrayList();
            	listInner.add(getBlack(cr.GetValue(i, 0).split("\\.").length) + cr.GetValue(i, 1));// 类型
            	listInner.add(cr.GetValue(i, 3));// 业务名称
            	listInner.add(cr.GetValue(i, 4));// 生效日期
            	listInner.add(cr.GetValue(i, 5));// 失效日期
            	favourableResult.add(listInner);
            }
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYFAVOURABLE, "0", "0", "0", "业务信息查询:已开通优惠查询成功!");
        }
        else if (result.get("returnObj") == null)
        {
            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            String resultMsg = getConvertMsg((String) mapResult.get("returnMsg"), "zz_04_15_000003", 
                    String.valueOf(result.get("errcode")), null);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYFAVOURABLE, "0", "0", "1", resultMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        }
        
        logger.debug("qryPersionInfo Starting...");

        // 返回
        return forward;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String[] getServicetitle() {
		return servicetitle;
	}
	
	public void setServicetitle(String[] servicetitle) {
		this.servicetitle = servicetitle;
	}

	public String[] getFavourableServicetitle() {
		return favourableServicetitle;
	}

	public void setFavourableServicetitle(String[] favourableServicetitle) {
		this.favourableServicetitle = favourableServicetitle;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public List getFavourableResult() {
		return favourableResult;
	}

	public void setFavourableResult(List favourableResult) {
		this.favourableResult = favourableResult;
	}

	public NserCustomerSimp getCustomer() {
		return customer;
	}

	public void setCustomer(NserCustomerSimp customer) {
		this.customer = customer;
	}

	public PersionInfoBean getQryPersionInfoBean() {
		return qryPersionInfoBean;
	}
	
	public void setQryPersionInfoBean(PersionInfoBean qryPersionInfoBean) {
		this.qryPersionInfoBean = qryPersionInfoBean;
	}

	/**
     * 获取查询单页数据
     * 
     * @param request
     * @param dataM
     * @param listType
     * @return
     */
    public Vector getQueryPageData(HttpServletRequest request, Map dataM, String listType)
    {
        
        Vector dataV = (Vector)dataM.get(listType);
        request.setAttribute("total", String.valueOf(dataV.size()));
        return dataV;
    }
    
    /**
     * 计算索进空格
     * 
     * @param level
     * @return
     */
    public String getBlack(int num)
    {
    	StringBuffer str = new StringBuffer(1024);
    	for(int i=0;i<num-1;i++)
    	{
    		str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	}
    	return str.toString();
    }
}
