package com.customize.cq.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.PersionInfoBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.bean.FavourableBean;
import com.gmcc.boss.selfsvc.bean.ScoreBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.serviceinfo.action.FavourableAction;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 我的个人信息查询
 * @author z90080209
 * @date   Nov 10, 2011
 */
public class PersionInfoAction extends BaseAction
{
    
//    private static Log logger = LogFactory.getLog(FavourableAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单
//    private String curMenuId = "";
//    // end zKF66389 findbus清零
//    
//    // 结果套餐信息标题
//    private String[] servicetitle;
//
//    // 结果已开通服务标题
//    private String[] favourableServicetitle;
//    
//    // 积分结果列表标题
//    private String[] scoreServicetitle;
//    
//    // 设置套餐信息结果
//    private List result;
//    
//    // 设置已开通服务结果
//    private List favourableResult;
//    
//    //设置积分内容
//    private String[] score;
//    
//    //客户信息
//    private NserCustomerSimp customer;
//    
//    // 调用接口bean
//    private PersionInfoBean qryPersionInfoBean;
//    
//    // 调用接口bean
//    private FavourableBean favourableBean;
//    
//    // 调用接口bean
//    private ScoreBean qryScoreBean;
//    
//    /**
//     * 套餐信息查询
//     * 
//     * @return
//     */
//    public String qryPersionInfo()
//    {
//    	String forward = "qryPersionInfo";
//    	
//        logger.debug("qryPersionInfo Starting...");
//        
//        // 当前月份
//        String month = CommonUtil.getLastMonth("yyyyMM", 0);
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp nserCustomerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // 页面展现品牌信息
//        customer = nserCustomerSimp;
//        
//        // 查询套餐信息
//        
//        // 执行查询
//        Map mapResult = qryPersionInfoBean.qryComboInfo(terminalInfoPO, nserCustomerSimp, curMenuId, month);
//        
//        // 处理返回
//        
//        // 定义结果标题名称
//        String[] titles = {"优惠编码", "累计量名称", "业务类型", "赠送总量", "已经使用", "剩余量", "优惠名称"};
//        
//        // 设置标题名称
//        setServicetitle(titles);
//        
//        // 结果
//        result = new ArrayList();
//        
//        if (mapResult.get("returnObj") != null && mapResult.size() > 0)
//        {
//        	CRSet cr = (CRSet)(mapResult.get("returnObj"));
//
//            // 构造[[val,val,val,..],[],[]...]形式的二维ArrayList存放返回的数据
//            //CRSet cr = (CRSet)(retData.get(1));
//            List listInner = null;
//            for (int i = 0; i < cr.GetRowCount(); i++)
//            {
//                listInner = new ArrayList();
//                listInner.add(cr.GetValue(i, 0));
//                for (int j = 1; j <= 6; j++)
//                {
//                    listInner.add(cr.GetValue(i, j));
//                }
//                result.add(listInner);
//            }
//            
//            // 创建成功日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "0", "业务信息查询:套餐信息查询成功!");
//        }
//        else if (mapResult.get("returnObj") == null)
//        {
//            this.getRequest().setAttribute("errormessage", (String)mapResult.get("returnMsg"));
//            
//            // 创建错误日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYPACKAGE, "0", "0", "1", "业务信息查询:套餐信息查询失败!");
//        }
//        
//        // 查询产品展示
//        //add by z90080209 查询开通业务与积分 20111107
//        // 结果
//        favourableResult = new ArrayList();
//        
//        // 查询类型 1：品牌资费 2：已开通服务、优惠
//        String queryType = "2";
//        
//        // 查询本机品牌资费及已开通功能（产品展示）
//        Map result = favourableBean.qryFavourable(terminalInfoPO, customer, curMenuId, queryType);
//        
//        if (result.get("returnObj") != null && result.size() > 0)
//        {
//            Vector vec = (Vector)(result.get("returnObj"));
//            
//            // 返回的字符串为已开通业务=资费描述=启用日期=结束日期
//            String servicetitle = (String)vec.get(0);
//            
//            // 对标题字符串进行分解后放入数组返回页面
//            if (servicetitle != null && !"".equals(servicetitle))
//            {
//                String[] titles1 = servicetitle.split("=");
//                
//             // 设置标题名称
//                setFavourableServicetitle(titles1);
//            }
//            
//            CRSet cr = (CRSet)vec.get(1);
//            // 拼装数据
//            for (int i = 0; i < cr.GetRowCount(); i++)
//            {
//            	List listOuter = new ArrayList();
//                listOuter.add(cr.GetValue(i, 1));
//                listOuter.add(cr.GetValue(i, 2));
//                listOuter.add(CommonUtil.formatTime(cr.GetValue(i, 3)));
//                listOuter.add(CommonUtil.formatTime(cr.GetValue(i, 4)));
//                favourableResult.add(listOuter);
//            }
//            
//            // 创建成功日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYFAVOURABLE, "0", "0", "0", "业务信息查询:已开通优惠查询成功!");
//        }
//        else if (result.get("returnObj") == null)
//        {
//        	// 记录错误消息
//            this.getRequest().setAttribute("errormessage", (String)mapResult.get("returnMsg"));
//            
//            // 创建错误日志
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYFAVOURABLE, "0", "0", "1", "业务信息查询:已开通优惠查询失败!");
//        }
//        
//        //调用接口查询积分信息
//        Map scoreResult = qryScoreBean.queryScoreValue(terminalInfoPO, customer, curMenuId);
//        
//        if (scoreResult != null && scoreResult.size() > 1)
//        {
//            String scoreStr = (String)scoreResult.get("returnObj");
//            
//            //设置标题
//            setScoreServicetitle(Constants.SCORE_TITLE);
//            String[] scores = scoreStr.split("~");
//            
//            //设置积分
//            setScore(scores);
//            
//            //创建成功日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "积分查询成功!");
//        }
//        else if(result != null)
//        {
//            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
//            
//            //创建错误日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "积分查询失败!");
//        }
//        
//        logger.debug("qryPersionInfo Starting...");
//
//        // 返回
//        return forward;
//        
//    }
//
//	public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//
//	public String[] getServicetitle() {
//		return servicetitle;
//	}
//	
//	public void setServicetitle(String[] servicetitle) {
//		this.servicetitle = servicetitle;
//	}
//
//	public String[] getFavourableServicetitle() {
//		return favourableServicetitle;
//	}
//
//	public void setFavourableServicetitle(String[] favourableServicetitle) {
//		this.favourableServicetitle = favourableServicetitle;
//	}
//
//	public List getResult() {
//		return result;
//	}
//
//	public void setResult(List result) {
//		this.result = result;
//	}
//
//	public List getFavourableResult() {
//		return favourableResult;
//	}
//
//	public void setFavourableResult(List favourableResult) {
//		this.favourableResult = favourableResult;
//	}
//	
//	public String[] getScore()
//    {
//        return score;
//    }
//
//    public void setScore(String[] score)
//    {
//        this.score = score;
//    }
//
//	public NserCustomerSimp getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(NserCustomerSimp customer) {
//		this.customer = customer;
//	}
//
//	public PersionInfoBean getQryPersionInfoBean() {
//		return qryPersionInfoBean;
//	}
//	
//	public void setQryPersionInfoBean(PersionInfoBean qryPersionInfoBean) {
//		this.qryPersionInfoBean = qryPersionInfoBean;
//	}
//	
//	public FavourableBean getFavourableBean() {
//		return favourableBean;
//	}
//	
//	public void setFavourableBean(FavourableBean favourableBean) {
//		this.favourableBean = favourableBean;
//	}
//	
//	public ScoreBean getQryScoreBean()
//    {
//        return qryScoreBean;
//    }
//    
//    public void setQryScoreBean(ScoreBean qryScoreBean)
//    {
//        this.qryScoreBean = qryScoreBean;
//    }
//    
//    public String[] getScoreServicetitle()
//    {
//        return scoreServicetitle;
//    }
//
//    public void setScoreServicetitle(String[] scoreServicetitle)
//    {
//        this.scoreServicetitle = scoreServicetitle;
//    }
//
//	/**
//     * 获取查询单页数据
//     * 
//     * @param request
//     * @param dataM
//     * @param listType
//     * @return
//     */
//    public Vector getQueryPageData(HttpServletRequest request, Map dataM, String listType)
//    {
//        
//        Vector dataV = (Vector)dataM.get(listType);
//        request.setAttribute("total", String.valueOf(dataV.size()));
//        return dataV;
//    }
//    
//    /**
//     * 计算索进空格
//     * 
//     * @param level
//     * @return
//     */
//    public String getBlack(int num)
//    {
//    	String str = "";
//    	StringBuffer sbuf = new StringBuffer(str);
//    	for(int i=0;i<num-1;i++)
//    	{
//    		sbuf.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//    	}
//    	
//    	return sbuf.toString();
//    }
    
}
