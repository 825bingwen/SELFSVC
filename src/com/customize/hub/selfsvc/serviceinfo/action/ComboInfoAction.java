package com.customize.hub.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ComboInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 湖北套餐信息查询
 * 
 * @author xkf29026
 * 
 */
public class ComboInfoAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(ComboInfoAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId = "";
    
    // 结果标题
    private String[] servicetitle;
    
    // 设置结果
    private List result;
    
    // 月份
    private String month;
    
    // 调用接口bean
    private ComboInfoBean qryComboInfoBean;
    
    // add begin jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
    // 湖北套餐信息查询流程优化，改为只查当前月 1：开  0：关 
    private String cmoboInfoFlag;
    // add end jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
    
    /**
     * 转到选择账单月份页面
     * 
     * @return
     */
    public String selectTaoCanMonth()
    {
        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        this.setMonth(CommonUtil.getLastMonth(pattern, 0));
        String month1 = CommonUtil.getLastMonth(pattern, 1);
        String month2 = CommonUtil.getLastMonth(pattern, 2);
        String month3 = CommonUtil.getLastMonth(pattern, 3);
        String month4 = CommonUtil.getLastMonth(pattern, 4);
        String month5 = CommonUtil.getLastMonth(pattern, 5);
        
        HttpServletRequest request = this.getRequest();
        request.setAttribute("month", month);
        request.setAttribute("month1", month1);
        request.setAttribute("month2", month2);
        request.setAttribute("month3", month3);
        request.setAttribute("month4", month4);
        request.setAttribute("month5", month5);
        
        return "selectBillMonth";
    }
    
    /**
     * 套餐信息查询
     * 
     * @return
     */
    public String qryComboInfo()
    {
        // modify begin wangyunlong 2013-09-12 R003C13LG0801 OR_HUB_201303_624
    	String pattern = "yyyyMM";
        this.setMonth(CommonUtil.getLastMonth(pattern, 0));
        String month1 = CommonUtil.getLastMonth(pattern, 1);
        String month2 = CommonUtil.getLastMonth(pattern, 2);
        String month3 = CommonUtil.getLastMonth(pattern, 3);
        String month4 = CommonUtil.getLastMonth(pattern, 4);
        String month5 = CommonUtil.getLastMonth(pattern, 5);
        
        HttpServletRequest request = this.getRequest();
        request.setAttribute("month", month);
        request.setAttribute("month1", month1);
        request.setAttribute("month2", month2);
        request.setAttribute("month3", month3);
        request.setAttribute("month4", month4);
        request.setAttribute("month5", month5);
        // modify end wangyunlong 2013-09-12 R003C13LG0801 OR_HUB_201303_624

        logger.debug("qryComboInfo Starting...");
        
        String forward = "error";
       
        // add begin jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
        // 湖北套餐信息查询流程优化，改为只查当前月 1：开  0：关 
        cmoboInfoFlag = CommonUtil.getParamValue(Constants.SH_COMBOINFO_SWITCH);
        if ("1".equals(cmoboInfoFlag))
        {
        	month = CommonUtil.getLastMonth("yyyyMM", 0);
        }
        // add end jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 查询套餐信息
        Map mapResult = qryComboInfoBean.qryComboInfo(terminalInfoPO, customer, curMenuId, month);
        if (mapResult.get("returnObj") != null && mapResult.size() > 0)
        {
            
//            Vector retData = (Vector)(mapResult.get("returnObj"));
            
            // 定义结果标题名称
            String[] titles = {"业务类型", "套餐名称", "赠送类型", "赠送总量", "累计使用量", "单位"};
            
            // 设置标题名称
            setServicetitle(titles);
            result=(List)(mapResult.get("returnObj"));
//            // 构造[[val,val,val,..],[],[]...]形式的二维ArrayList存放返回的数据
//            CRSet cr = (CRSet)(retData.get(1));
//            result = new ArrayList();
//            List listInner = null;
//            for (int i = 0; i < cr.GetRowCount(); i++)
//            {
//                listInner = new ArrayList();
//                listInner.add(cr.GetValue(i, 0));
//                for (int j = 2; j <= 6; j++)
//                {
//                    listInner.add(cr.GetValue(i, j));
//                }
//                result.add(listInner);
//            }
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
            
            forward = "queryTcService";
        }
        else if (mapResult.get("returnObj") == null)
        {
            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            String resultMsg = getConvertMsg((String) mapResult.get("returnMsg"), "zz_04_15_000002", 
                    String.valueOf(mapResult.get("errcode")), null);

            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", resultMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            forward = "error";
        }
        
        logger.debug("qryComboInfo Starting...");
        return forward;
        
    }
    
    public ComboInfoBean getQryComboInfoBean()
    {
        return qryComboInfoBean;
    }
    
    public void setQryComboInfoBean(ComboInfoBean qryComboInfoBean)
    {
        this.qryComboInfoBean = qryComboInfoBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String[] getServicetitle()
    {
        return servicetitle;
    }
    
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }
    
    public List getResult()
    {
        return result;
    }
    
    public void setResult(List result)
    {
        this.result = result;
    }

	public String getMonth() 
	{
		return month;
	}

	public void setMonth(String month) 
	{
		this.month = month;
	}

	public String getCmoboInfoFlag() {
		return cmoboInfoFlag;
	}

	public void setCmoboInfoFlag(String cmoboInfoFlag) {
		this.cmoboInfoFlag = cmoboInfoFlag;
	}
}
