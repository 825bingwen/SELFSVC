package com.gmcc.boss.selfsvc.serviceinfo.action;

import com.customize.hub.selfsvc.common.ConstantsHub;
import com.gmcc.boss.selfsvc.bean.ScoreBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 积分查询 <功能详细描述>
 * 
 * @author yKF28472
 * @version [版本号, May 28, 2011]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ScoreAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(ScoreAction.class);
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 错误消息
    // private String error;
    
    // 结果列表标题
    private String[] servicetitle;
    
    // 设置积分内容
    private String[] score;
    
    // 调用接口bean
    private ScoreBean qryScoreBean;
    
    /**
     * 开始时间
     */
    private String startDate;
    
    /**
     * 结束时间
     */
    private String endDate;
    
    /**
     * 积分查询类别
     */
    private String queryType;
    
    /**
     * 积分明细查询返回结果
     */
    private List<Map<String, String>> scoreDetail;
    
    /**
     * 积分兑换明细查询
     */
    private List<Map<String, String>> scoreChangesd;
    
    /**
     * 积分兑换明细查询
     */
    private List<Map<String, String>> scoreChangehub;
    
    /**
     * 请求的地市编号
     */
    private String requestRegion;
    
    /**
     * 返回的信息提示
     */
    private String retMessage;
    
    // add begin jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
    /**
     * 用户可用积分（山东）
     */
    private String pointBalance = "";
    
    /**
     * 积分发放查询结果(山东)
     */
    private List<Map<String, String>> scorePayment;
    
    /**
     * 积分查询结果（山东）
     */
    private List<Map<String, String>> scoreSD;
    // add end jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
    
    /**
     * 积分查询    
     * 
     * @return
     */
    public String qryScore()
    {
        // 查询日志开始
        logger.debug("qryScore Starting...");
        
        // 定位到错误页面
        String froward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //获取所属省份
        String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        //add begin sWX219697 2014-5-13 OR_SD_201404_777
        //用户积分计划查询接口开关
        String scorePlanSwitch = (String)PublicCache.getInstance().getCachedData(Constants.SCORE_PLAN_SWITCH);
      
//        if (provinceID.equals(Constants.PROOPERORGID_SD) //山东
//        		&& !"全球通".equals(customer.getBrandName())//非全球通用户
//        		&& "1".equals(scorePlanSwitch)//开关开启
//        		&& !qryScoreBean.qryIsScoreOpenForSd(terminalInfoPO, customer, curMenuId))//未开通积分计划
//        {
//        	//没有开通积分计划的提示信息
//        	String msg = (String)PublicCache.getInstance().getCachedData(Constants.SCORE_NOT_OPEN_MSG);
//        	this.getRequest().setAttribute("errormessage", msg);
//        	
//        	return froward;
//        }
        //add end sWX219697 2014-5-13 OR_SD_201404_777
        
        // 调用接口查询积分信息
        Map result = qryScoreBean.queryScoreValue(terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            String scoreStr = (String)result.get("returnObj");
            // add by longchengcheng 20130702 end
            String scoreHub = (String) PublicCache.getInstance().getCachedData(ConstantsHub.SH_QRY_SCOREHUB_FLAG);
            if (provinceID.equals(Constants.PROOPERORGID_HUB)&&"1".equals(scoreHub)) {
                // 湖北积分查询
                // 设置标题
                setServicetitle(ConstantsHub.getQryScore());
                String[] scores = scoreStr.split("~");
                String[] scores_hb = new String[scores.length-1];
                for(int i=0;i<scores.length-1;i++){
                    scores_hb[i] = scores[i];
                }
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                scores_hb[0] = CommonUtil.getNameByBrandLetter(scores_hb[0]);
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                
                // 设置积分
                setScore(scores_hb);
                // add by longchengcheng 20130702 end
            } 
            else if (provinceID.equals(Constants.PROOPERORGID_SD) && "1".equals(CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH)))
            {
            	// 山东积分查询新标题
            	// begin zKF66389 2015-09-10 9月份findbugs修改
        		//setServicetitle(Constants.SCORE_NEWTITLE_SD);
        		setServicetitle(Constants.getScoreNewtitleSd());
        		// end zKF66389 2015-09-10 9月份findbugs修改
        		
        		this.qryScoreSD(result);
            }
            else 
            {
                String[] scores = scoreStr.split("~");
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                if(!provinceID.equals(Constants.PROOPERORGID_NX)){
                    scores[0] = CommonUtil.getNameByBrandLetter(scores[0]);
                }

                // modify end hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                
                //modify begin sWX219697 2014-5-13 OR_SD_201404_777
                //若为山东用户且接口开关开启
                if (provinceID.equals(Constants.PROOPERORGID_SD) && "1".equals(scorePlanSwitch))
                {
                    //设置新标题：本年度累计积分，当前可用积分
                	// begin zKF66389 2015-09-10 9月份findbugs修改
                	//setServicetitle(Constants.SCORE_TITLE_NEW_SD);
                	setServicetitle(Constants.getScoreTitleNewSd());
                	// end zKF66389 2015-09-10 9月份findbugs修改
                	
                	//山东积分展示修改：改为本年度累计积分、当前可用积分
                	String[] scores_sd = new String[2];
                	scores_sd[0] = scores[0];
                	scores_sd[1] = scores[3];
                	setScore(scores_sd);
                }
                else
                {
                	// 设置标题
                	// begin zKF66389 2015-09-10 9月份findbugs修改
                    //setServicetitle(Constants.SCORE_TITLE);
                	setServicetitle(Constants.getScoreTitle());
                    // end zKF66389 2015-09-10 9月份findbugs修改
                    
                    // 设置积分
                    setScore(scores);
                }
                //modify end sWX219697 2014-5-13 OR_SD_201404_777
                
            }
            froward = "qryScore";
            
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "积分查询成功!");
        }
        else if (result != null)
        {
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "积分查询失败!");
        }
        logger.debug("qryScore End!");
        return froward;
    }
    
    /**
     * 积分查询（山东）
     * @param map
     * @remark create by jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
     */
    private void qryScoreSD(Map map)
    {
    	if (null != map)
    	{
    		// 用户可用积分
    		pointBalance = (String) map.get("pointBalance");
    		if ("false".equals(map.get("IsInfoNull")))
    		{
    			// 积分明细
    			scoreSD = (List<Map<String, String>>) map.get("SUCCESSINFO");
    		}
    	}
    	
    }
    
    /**
     * 转向开始结束时间界面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String forStartAndEnd()
    {
        startDate = qryScoreBean.getLastMonthBeginDate(5);
        endDate = qryScoreBean.getLastMonthEndDate(0);
        return SUCCESS;
    }
    
    /**
     * 查询积分明细信息(山东和湖北)
     * 
     * @return
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    public String qryScoreDetailHisForSd() throws DocumentException
    {
        
        // 查询日志开始
        logger.debug("qryScoreDetailHisForSd Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // 调用接口查询积分信息
        Map result = qryScoreBean.qryScoreDetailHisForSd(startDate, endDate, terminalInfoPO, customer, curMenuId);
        if (result != null && result.size() > 1)
        {
            // 避免缓存
            clearSession();
            retMessage = null;
            // 返回的信息不空
            if ("false".equals(result.get("IsInfoNull")))
            {
                // 设置标题
            	// begin zKF66389 2015-09-10 9月份findbugs修改
                //setServicetitle(Constants.SCOREDETIAL_TITLE);
            	setServicetitle(Constants.getScoredetialTitle());
                // end zKF66389 2015-09-10 9月份findbugs修改
                // 设置积分
                scoreDetail = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "对不起！没有查询到相应的积分明细信息。";
            }
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "积分明细查询成功!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage = "对不起！没有查询到相应的积分明细信息。";
            // retMessage=(String)result.get("returnMsg");
            
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "积分明细查询失败!");
        }
        logger.debug("qryScoreDetailHisForSd End!");
        return "showdetail";
    }
    
    /**
     * 查询积分兑换历史信息(山东)
     * 
     * @return
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    public String queryScoreChangeHisForsd() throws DocumentException
    {
        
        // 查询日志开始
        logger.debug("queryScoreChangeHisForsd Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // 调用接口查询积分信息
        Map result = qryScoreBean.queryScoreChangeHisForsd(startDate, endDate, terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // 避免缓存
            clearSession();
            retMessage = null;
            // 返回的信息不空
            if ("false".equals(result.get("IsInfoNull")))
            {
                
                // 设置标题
            	// begin zKF66389 2015-09-10 9月份findbugs修改
                //setServicetitle(Constants.SCORECHANGE_TITLE);
            	setServicetitle(Constants.getScorechangeTitle());
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                // 设置积分
                scoreChangesd = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "对不起！没有查询到相应的积分兑换历史信息。";
            }
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "山东积分兑换历史查询成功!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage =  "对不起！没有查询到相应的积分兑换历史信息。";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "山东积分兑换历史查询失败!");
        }
        logger.debug("queryScoreChangeHisForsd End!");
        return "showScoreChange";
    }
    
    /**
     * 查询积分兑换历史信息(湖北)
     * 
     * @return
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    public String queryScorePrizeHisForhub() throws DocumentException
    {
        
        // 查询日志开始
        logger.debug("queryScorePrizeHisForhub Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        // 调用接口查询积分信息
        Map result = qryScoreBean.queryScorePrizeHisForhub(startDate, endDate, terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // 避免缓存
            clearSession();
            retMessage = null;
            // 返回的信息不空
            if ("false".equals(result.get("IsInfoNull")))
            {
                
                // 设置标题
            	// begin zKF66389 2015-09-10 9月份findbugs修改
                //setServicetitle(Constants.SCOREPRIZE_TITLE);
                setServicetitle(Constants.getScoreprizeTitle());
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                // 设置积分
                scoreChangehub = (List<Map<String, String>>)result.get("SUCCESSINFO");
                
            }
            else
            {
                retMessage = "对不起！没有查询到相应的积分兑换历史信息。";
            }
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "湖北积分兑换历史查询成功!");
        }
        else if (result != null)
        {
            clearSession();
            retMessage = "对不起！没有查询到相应的积分兑换历史信息。";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "湖北积分兑换历史查询失败!");
        }
        logger.debug("queryScorePrizeHisForhub End!");
        return "showScoreChange";
    }
    
    /**
     * 积分发放查询（山东）
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
     */
    public String qryScorePaymentSD()
    {
    	// 查询日志开始
        logger.debug("queryScorePrizeHisForhub Starting...");
        
        // 获取终端机信息
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // 获取用户信息
        NserCustomerSimp  customer = getCustomerSimp();
        
        Map<String, Object> result = qryScoreBean.qryScorePaymentSD(startDate, endDate, customer, termInfo, curMenuId);
        
        if (result.size() > 1)
        {
            // 避免缓存
            clearSession();
            retMessage = null;
            
            // 返回的信息不空
            if ("false".equals(result.get("IsInfoNull")))
            {
                // 设置标题
            	// begin zKF66389 2015-09-10 9月份findbugs修改
                //setServicetitle(Constants.PAYMENTSCORE_TITLE);
                setServicetitle(Constants.getPaymentscoreTitle());
                // end zKF66389 2015-09-10 9月份findbugs修改
                
                // 设置积分
                scorePayment = (List<Map<String, String>>)result.get("SUCCESSINFO");
            }
            else
            {
                retMessage = "对不起！没有查询到相应的积分发放信息。";
            }
            // 创建成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "0", "山东积分发放信息查询成功!");
        }
        else
        {
            clearSession();
            retMessage = "对不起！没有查询到相应的积分发放信息。";
            this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYSCORE, "0", "0", "1", "山东积分发放查询失败!");
        }
        logger.debug("queryScorePrizeHisForhub End!");
    	return "qryScorePaymentSD";
    }
    
    /**
     * 清除部分缓存(避免不必要的缓存)
     * 
     * @see [类、类#方法、类#成员]
     */
    public void clearSession()
    {
        // 避免缓存
        setServicetitle(null);
        scoreDetail = null;
        scoreChangehub = null;
        scoreChangesd = null;
        scorePayment = null;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public ScoreBean getQryScoreBean()
    {
        return qryScoreBean;
    }
    
    public void setQryScoreBean(ScoreBean qryScoreBean)
    {
        this.qryScoreBean = qryScoreBean;
    }
    
    public String[] getServicetitle()
    {
        return servicetitle;
    }
    
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }
    
    public String[] getScore()
    {
        return score;
    }
    
    public void setScore(String[] score)
    {
        this.score = score;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getQueryType()
    {
        return queryType;
    }
    
    public void setQueryType(String queryType)
    {
        this.queryType = queryType;
    }
    
    public List<Map<String, String>> getScoreDetail()
    {
        return scoreDetail;
    }
    
    public void setScoreDetail(List<Map<String, String>> scoreDetail)
    {
        this.scoreDetail = scoreDetail;
    }
    
    public String getRequestRegion()
    {
        return requestRegion;
    }
    
    public void setRequestRegion(String requestRegion)
    {
        this.requestRegion = requestRegion;
    }
    
    public List<Map<String, String>> getScoreChangesd()
    {
        return scoreChangesd;
    }
    
    public void setScoreChangesd(List<Map<String, String>> scoreChangesd)
    {
        this.scoreChangesd = scoreChangesd;
    }
    
    public List<Map<String, String>> getScoreChangehub()
    {
        return scoreChangehub;
    }
    
    public void setScoreChangehub(List<Map<String, String>> scoreChangehub)
    {
        this.scoreChangehub = scoreChangehub;
    }
    
    public String getRetMessage()
    {
        return retMessage;
    }
    
    public void setRetMessage(String retMessage)
    {
        this.retMessage = retMessage;
    }

	public String getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(String pointBalance) {
		this.pointBalance = pointBalance;
	}

	public List<Map<String, String>> getScorePayment() {
		return scorePayment;
	}

	public void setScorePayment(List<Map<String, String>> scorePayment) {
		this.scorePayment = scorePayment;
	}

	public List<Map<String, String>> getScoreSD() {
		return scoreSD;
	}

	public void setScoreSD(List<Map<String, String>> scoreSD) {
		this.scoreSD = scoreSD;
	}
    
}
