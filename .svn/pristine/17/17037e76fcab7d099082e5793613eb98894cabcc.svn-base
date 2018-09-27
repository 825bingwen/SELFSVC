package com.customize.cq.selfsvc.reception.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.ScoreExchangeBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 实现积分兑换话费功能
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  l00190940
 * @version  [版本号, 2011/11/03]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ScoreExchangeAction extends BaseAction
{
//	// 记录日志 
//    private static Log logger = LogFactory.getLog(ScoreExchangeAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单
//    private String curMenuId;
//    // end zKF66389 findbus清零
//    
//    // 用户积分
//    private String leftScore;
//    
//    // 用户积分兑换信息
//    private List scoreExchangeInfo;
//    
//    // 活动编号
//    private String activeno;
//    
//    // 活动级别
//    private String nlevel;
//    
//    // 奖品编码
//    private String serviceid;
//    
//    // 接口调用
//    private ScoreExchangeBean scoreExchangeBean;
//    
//    // 成功信息
//    private String successMessage;
//    
//    /**
//     * 用户积分显示、用户积分兑换信息显示
//     * @return
//     */
//    public String scoreExchangePage()
//    {
//    	// 记录日志
//        logger.debug("scoreExchangePage starting");
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        curMenuId = (String) getRequest().getAttribute(Constants.CUR_MENUID);
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        //调用接口查询积分信息
//        Map result = scoreExchangeBean.queryScoreValue(terminalInfoPO, customer, curMenuId);
//        
//        if (result != null && result.size() > 1)
//        {
//            String scoreResult = (String)result.get("returnObj");
//            
//            //设置积分
//            setLeftScore(scoreResult);
//            
//            //创建成功日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "积分查询成功!");
//        }
//        else if(result != null)
//        {
//            this.getRequest().setAttribute("errormessage", "积分查询失败！");
//            
//            //创建错误日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "积分查询失败!");
//            
//            return "error";
//        }
//        
//        /*
//         *  exchangeMoneyD 动感地带M值兑换话费
//         *  gotoneExchangeFee 全球通积分换话费
//         *  ZgxYch_1	积分兑换纵贯线演唱会门票（网龄5年以下）
//		 *  ZgxYch_2	积分兑换纵贯线演唱会门票（网龄5年以上）
//		 *  MExchangeDZP 动感地带M值兑电子票
//		 *  JQHK_ZS2010神州行金秋回馈（话费促销）
//		 *  BrandMzonePrepay2011动感地带渠道存话费送话费短期促销活动
//		 *  gdd_dlb_1：欢乐暑假大礼包
//         **/
//        String acttype = "gotoneExchangeFee";
//        
//        //调用接口查询积分兑换信息
//        Map scoreExchangeInfoResult = scoreExchangeBean.queryScoreExchangeInfo(terminalInfoPO, customer, curMenuId, acttype);
//        
//        if (scoreExchangeInfoResult != null && scoreExchangeInfoResult.size() > 1)
//        {
//            List scoreResult = (List)scoreExchangeInfoResult.get("returnObj");
//            
//            //设置积分
//            setScoreExchangeInfo(scoreResult);
//            
//            //创建成功日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCOREEXCHAANGE, "0", "0", "0", "积分兑换信息查询成功!");
//        }
//        else if(scoreExchangeInfoResult != null)
//        {
//            this.getRequest().setAttribute("errormessage", "积分兑换信息查询失败！");
//            
//            //创建错误日志
//            this.createRecLog(Constants.BUSITYPE_WBQRYSCOREEXCHAANGE, "0", "0", "1", "积分兑换信息查询失败!");
//            
//            return "error";
//        }
//        
//        // 记录日志
//        logger.debug("scoreExchangePage ending");
//        
//    	return "selectScoreExchange";
//    }
//    
//    /**
//     * 将积分兑换为话费
//     * @return
//     */
//    public String scoreToBalance()
//    {
//    	// 记录日志
//        logger.debug("scoreToBalance starting");
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
//        //调用接口用积分兑换话费
//        Map result = scoreExchangeBean.exchangeBalance(terminalInfoPO, customer, curMenuId, activeno, nlevel, serviceid);
//        
//        if (result != null && result.get("successFlag") != null)
//        {
//            setSuccessMessage("积分兑换话费成功！");
//
//            // 记录日志
//            logger.debug("scoreToBalance ending");
//            
//            return "exchangeSucceed";	
//        }
//        
//        else if (result != null)
//        {
//            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
//        	
//        	// 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "积分兑换话费失败！");
//
//            // 记录日志
//            logger.debug("scoreToBalance ending");
//        	
//        	return "error";
//        }
//        
//        else
//        {
//        	this.getRequest().setAttribute("errormessage", "积分兑换话费失败！");
//        	
//        	// 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "积分兑换话费失败！");
//        	
//            // 记录日志
//            logger.debug("scoreToBalance ending");
//            
//        	return "error";
//        }
//    }
//
//	public ScoreExchangeBean getScoreExchangeBean() 
//	{
//		return scoreExchangeBean;
//	}
//
//	public void setScoreExchangeBean(ScoreExchangeBean scoreExchangeBean) 
//	{
//		this.scoreExchangeBean = scoreExchangeBean;
//	}
//
//	public String getLeftScore() 
//	{
//		return leftScore;
//	}
//
//	public void setLeftScore(String leftScore) 
//	{
//		this.leftScore = leftScore;
//	}
//
//	public List getScoreExchangeInfo() 
//	{
//		return scoreExchangeInfo;
//	}
//
//	public void setScoreExchangeInfo(List scoreExchangeInfo) 
//	{
//		this.scoreExchangeInfo = scoreExchangeInfo;
//	}
//
//	public String getActiveno() 
//	{
//		return activeno;
//	}
//
//	public void setActiveno(String activeno) 
//	{
//		this.activeno = activeno;
//	}
//
//	public String getNlevel() 
//	{
//		return nlevel;
//	}
//
//	public void setNlevel(String nlevel) {
//		this.nlevel = nlevel;
//	}
//
//	public String getServiceid() {
//		return serviceid;
//	}
//
//	public void setServiceid(String serviceid) 
//	{
//		this.serviceid = serviceid;
//	}
//
//	public String getSuccessMessage() 
//	{
//		return successMessage;
//	}
//
//	public void setSuccessMessage(String successMessage)
//	{
//		this.successMessage = successMessage;
//	}
//
//	public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}


}
