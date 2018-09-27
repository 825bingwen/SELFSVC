package com.customize.nx.selfsvc.serviceinfo.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.PackageInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class PackageInfoAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(PackageInfoAction.class);
    
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
    private PackageInfoBean packageInfoBean;
    
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
    @SuppressWarnings("unchecked")
	public String qryPackageInfo()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("PackageInfoAction -- qryPackageInfo Starting ...");
        }
        
        String forward = "error";
       
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (month == null || "".equalsIgnoreCase(month))
        {
            month = CommonUtil.getLastMonth("yyyyMM", 0);
        }
        
        // 查询套餐信息
        Map mapResult = packageInfoBean.qryPackageInfo(terminalInfoPO, customer, curMenuId, month);
        if (mapResult.get("returnObj") != null && mapResult.size() > 0)
        {
            Vector retData = (Vector)(mapResult.get("returnObj"));
            
            // 定义结果标题名称
            String[] titles = {"套餐名称", "赠送量", "使用量", "剩余量"};
            
            // 设置标题名称
            setServicetitle(titles);
            
            // 构造[[val,val,val,..],[],[]...]形式的二维ArrayList存放返回的数据
            CRSet cr = (CRSet)(retData.get(1));
            result = new ArrayList();
            List listInner = null;
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                listInner = new ArrayList();
                
	        	// modify begin by wWX217192 20140417  OR_NX_201404_10  宁夏_【自助终端需求】关于修改电子渠道套餐剩余量查询内容展现的需求
	        	// 判断套餐中是否包含"单位：秒"的信息，以便将数据换算成分钟
	        	if(cr.GetValue(i, 0).contains("单位:秒"))
	        	{
	        		// 套餐名称
	        		listInner.add(cr.GetValue(i, 0).replace("单位:秒", "单位:分钟"));
	        		
	        		// 赠送量
        			listInner.add(Integer.valueOf(cr.GetValue(i, 1))/60);
	        		
        			// 使用量
        			int data = Integer.valueOf(cr.GetValue(i, 2))%60;
        			
        			// 判断使用量转换成分钟是否有超出部分，若无则正常显示
        			if(data == 0)
        			{
        				listInner.add(Integer.valueOf(cr.GetValue(i, 2))/60);
        			}
        			// 若有的话则进1
        			else
        			{
        				listInner.add(Integer.valueOf(cr.GetValue(i, 2))/60 + 1);
        			}
	        		
        			// 剩余量
	        		listInner.add(Integer.valueOf(cr.GetValue(i, 3))/60);
	        	}
	        	// 判断套餐中是否包含"单位：字节"的信息，以便将数据换算成兆
	        	else if(cr.GetValue(i, 0).contains("单位:字节"))
	        	{
	        		//套餐名称
        			listInner.add(cr.GetValue(i, 0).replace("单位:字节", "单位:兆"));

        			//赠送量
        			listInner.add(this.changeDataFormat(Float.valueOf(cr.GetValue(i, 1))));

        			//使用量
        			listInner.add(this.changeDataFormat(Float.valueOf(cr.GetValue(i, 2))));

        			//剩余量
        			listInner.add(this.changeDataFormat(Float.valueOf(cr.GetValue(i, 3))));
	        	}
	        	//如果套餐中不含以上需要转换的信息则直接将信息封装至List中返回前台进行遍历
	        	else {
	        		for (int j = 0; j <= 3; j++)
	                {
	        			listInner.add(cr.GetValue(i, j));
	                }
	        	}
	        	//modify end by wWX217192 20140417   OR_NX_201404_10  宁夏_【自助终端需求】关于修改电子渠道套餐剩余量查询内容展现的需求
	        
                result.add(listInner);
            }
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
            
            forward = "queryTcService";
        }
        else if (mapResult.get("returnObj") == null)
        {
            this.getRequest().setAttribute("errormessage", (String)mapResult.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", "业务信息查询:套餐信息查询失败!");
            
            forward = "error";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("PackageInfoAction -- qryPackageInfo End");
        }
        
        return forward;
        
    }
    
    //将数据进行四舍五入的运算 将字节数据转换成兆显示
    private String changeDataFormat(float data) {
    	String parten = "#.##";
		DecimalFormat decimal = new DecimalFormat(parten);
		String str= decimal.format(data/1024/1024);
		return str;
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

    public PackageInfoBean getPackageInfoBean()
    {
        return packageInfoBean;
    }

    public void setPackageInfoBean(PackageInfoBean packageInfoBean)
    {
        this.packageInfoBean = packageInfoBean;
    }
}

