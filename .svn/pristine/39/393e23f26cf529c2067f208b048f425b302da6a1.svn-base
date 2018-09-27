package com.customize.hub.selfsvc.recommendProduct.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.customize.hub.selfsvc.bean.RecommendProductBean;
import com.customize.hub.selfsvc.recommendProduct.model.FeedBackDefPO;
import com.customize.hub.selfsvc.recommendProduct.model.RecommendProductPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 湖北营销推荐活动
 * @author cKF76106
 * @version [版本号, Aug 21, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RecommendProductAction extends BaseAction
{
    private RecommendProductBean recommendProductBean;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 错误信息
     */
    private String errormessage = "";
    
    /**
     * 成功信息
     */
    private String successMessage = "";
    
    /**
     * 推荐产品列表
     */
    private List<RecommendProductPO> recmdProductList = new ArrayList<RecommendProductPO>();
    
    /**
     * 用户手机号码
     */
    private String servnumber = "";
    
    /**
     * 业务推荐唯一流水号
     */
    private String commendOID = "";
    
    /**
     * 用户序列号
     */
    private String userSeq = "";
    
    /**
     * 页面缴费标志
     */
    private String feeChargeFlag = "";
    
    /**
     * 状态
     */
    private String status = "";

    // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
    /**
     * 推荐产品唯一流水号集合
     */
    private String userSeqs = "";
    // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
    
    /**
     * 营销活动编码
     */
    private String actId = "";
    
    /**
     * 营销活动编码集合,以逗号分隔
     */
    private String actIds = "";
    
    /**
     * 推荐事件类型集合,以逗号分隔
     */
    private String eventTypes = "";
    
    /**
     * 回馈内容
     */
    private String moContent;
    
    /**
     * 推荐类型
     */
    private String recmdType;
    
    /**
     * 回馈定义信息列表
     */
    private List<FeedBackDefPO> feedBackDefList;
    
    /**
     * 查询用户可推荐的业务列表
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public String getRecommendProductList()
    {
        
        String forward = "continue";
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // 调用接口查询用户可推荐的业务列表
        Map mapResult = recommendProductBean.qryRecommendProductList(terminalInfoPO, touchOID, servnumber, curMenuId);
        
        recmdProductList.clear();
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            CRSet crset = (CRSet)(mapResult.get("returnObj"));
            
            if (crset != null)
            { 
                // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                // 推荐产品的唯一流水号集合 
                StringBuilder userSeqsBuilder = new StringBuilder();
                // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                
                // 推荐活动集合
                StringBuilder actIdsBuilder = new StringBuilder();
                
                // 事件类型集合
                StringBuilder eventTypesBuilder = new StringBuilder();
                
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    RecommendProductPO productRecPO = new RecommendProductPO();
                    productRecPO.setActID(crset.GetValue(i, 10));
                    productRecPO.setActName(crset.GetValue(i, 11));// 营销活动名称
                    productRecPO.setActDict(crset.GetValue(i, 2));// 营销用语
                    productRecPO.setActContent(crset.GetValue(i, 13));// 活动内容
                    productRecPO.setUserSeq(crset.GetValue(i, 6));// 用户序列号
                    productRecPO.setCommendOID(crset.GetValue(i, 0));// 业务推荐唯一流水号
                    productRecPO.setCommendType(crset.GetValue(i, 16));// 推荐类型
                    productRecPO.setIsFeedBackDef(crset.GetValue(i, 17));// 是否有回复信息定义
                    productRecPO.setEventType(crset.GetValue(i, 18));
                    
                    // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                    // 将推荐产品的唯一流水号合并  并且以逗号隔开
                    userSeqsBuilder.append(productRecPO.getUserSeq());
                    userSeqsBuilder.append(",");
                    // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                    
                    // 将推荐产品的[营销活动编码]以逗号连接
                    actIdsBuilder.append(productRecPO.getActID());
                    actIdsBuilder.append(",");
                    
                    // 将推荐产品的[推荐事件类型]以逗号连接
                    eventTypesBuilder.append(productRecPO.getEventType());
                    eventTypesBuilder.append(",");
                    
                    recmdProductList.add(productRecPO);
                }
                
                // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                // 将推荐产品的唯一流水号集合转为String类型并去掉最后一个逗号
                this.userSeqs = userSeqsBuilder.toString();
                this.userSeqs = this.userSeqs.substring(0, this.userSeqs.length()-1);
                // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
                
                // 将推荐产品的[营销活动编码]去掉最后一个逗号
                this.actIds = actIdsBuilder.toString();
                this.actIds = this.actIds.substring(0, this.actIds.length()-1);
                
                // 将推荐产品的[推荐事件类型]去掉最后一个逗号
                this.eventTypes = eventTypesBuilder.toString();
                this.eventTypes = this.eventTypes.substring(0, this.eventTypes.length()-1);
            }
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "0", "营销推荐活动:查询用户可推荐的业务列表成功!");
        }
        // 用户没有可推荐的活动，继续原业务操作流程
        else if(null != mapResult && null == mapResult.get("returnObj") && "1".equals(mapResult.get("successFlag")))
        {
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "0", "营销推荐活动:查询用户可推荐的业务列表成功!");

            return  forward;
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000001", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);

            return forward;
            
        }
        else
        {
            String resultMsg = "查询用户可推荐的业务列表失败";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000001", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
            return forward;
        }
        
        // 是否调用新修改的页面
        String recommendProductKey = (String)PublicCache.getInstance().getCachedData(Constants.RECOMMENDPRODUCTKEY);
        
        // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        // 如果调用新页面
        if("1".equals(recommendProductKey))
        {
            return "recommendProductListNew";
        }
        // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        
        return "recommendProductList";
    }
    
    /**
     * 记录业务推荐结果
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public String recommendFeedback()
    {
        
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        
        // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        // 是否调用新修改的接口
        // String recommendProductKey = (String)PublicCache.getInstance().getCachedData(Constants.RECOMMENDPRODUCTKEY);
        // add end create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        // 如果启用新代码
        /*if("1".equals(recommendProductKey))
        {
            this.userSeq = this.userSeqs;
        }*/
        // add begin create by zWX176560 OR_HUB_201303_200 自助终端一体化营销功能优化
        
        // 调用推荐业务记录接口
        ReturnWrap rw = recommendProductBean.recommendFeedback(terminalInfoPO, touchOID, servnumber, 
        		curMenuId, this.getUserSeqs(), status, this.getActIds(), this.getEventTypes());
        // begin zKF66389 2015-09-15 9月份findbugs修改
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACK, "0", "0", "0", "营销推荐活动:记录业务推荐结果成功!");
            
            forward = "continue";
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000002", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACK, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
        }
        
        return forward;
    }
    
    /**
     * 推荐业务受理
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public String recommendProduct()
    {
        
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // 调用推荐业务受理
        ReturnWrap rw = recommendProductBean.recommendProduct(terminalInfoPO,
            touchOID, servnumber, curMenuId, this.getUserSeq(), this.getActId(), this.getRecmdType());
        // begin zKF66389 2015-09-15 9月份findbugs修改
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog(Constants.BUSITYPE_RECPROD, "0", "0", "0", "营销推荐活动:推荐业务受理成功!");
            
            // 更新业务推荐结果接口
            forward = doRecommentSuccess();
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000003", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECPROD, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
                        
        }
        
        return forward;
    }
    
    /**
     * 查询推荐反馈定义列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public String qryFeedBackDefList()
    {
        // 查询回馈定义信息列表
        ReturnWrap rw = recommendProductBean.qryFeedBackDefList(getTerminalInfoPO(),
            getCustomerSimp(), getCurMenuId(), getServnumber(), getActId());
        
        // 页面导向设定
        String forward = "failed";
        // begin zKF66389 2015-09-15 9月份findbugs修改
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet crset = (CRSet)rw.getReturnObject();
            
            if (null != crset && crset.GetRowCount() > 0)
            {
                feedBackDefList = new ArrayList<FeedBackDefPO>();
                
                // 循环crset结果集，放入feedBackDefList中
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    FeedBackDefPO feedBackDefPO = new FeedBackDefPO();
                    feedBackDefPO.setRecmdName(crset.GetValue(i, 0)); // 推荐名称
                    feedBackDefPO.setNcode(crset.GetValue(i, 1));// 推荐ncode
                    feedBackDefPO.setActInfo(crset.GetValue(i, 2));// 推荐活动信息
                    feedBackDefPO.setMoContent(crset.GetValue(i, 3));// 回复内容
                    
                    feedBackDefList.add(feedBackDefPO);
                }
            }
            
            // 记录日志
            this.createRecLog(Constants.BUSITYPE_QRYFEEDBACKDEFLIST, "0", "0", "0", "营销推荐活动:查询用户回馈定义信息列表成功!");
            
            // 导向查询页面
            forward = "feedBackDefList";
        }
        else
        {
            // 错误信息
            String resultMsg = "查询用户回馈定义信息列表失败:" + rw.getReturnMsg();
            
            // 记录日志
            this.createRecLog(Constants.BUSITYPE_QRYFEEDBACKDEFLIST, "0", "0", "1", resultMsg);
            
            // 设置错误信息
            setErrormessage(resultMsg);
        }
        
        return forward;
    }
    
    /** 
     * 用户活动回馈定义受理
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public String doFeedBackDef()
    {
        // 调用用户活动回馈定义受理接口
        ReturnWrap rw = recommendProductBean.doFeedBackDef(getTerminalInfoPO(),
            getCustomerSimp(), getCurMenuId(), getActId(), getMoContent(), getRecmdType());
        
        // 页面导向设定
        String forward = "failed";

        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // begin zKF66389 2015-09-15 9月份findbugs修改
        {
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACKDEF, "0", "0", "0", "营销推荐活动:用户活动回馈定义受理成功!");
            
            // 更新业务推荐结果接口
            forward = doRecommentSuccess();
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000002", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACKDEF, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
        }
        
        return forward;
    }

    /** 
     * 更新业务推荐结果接口
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    private String doRecommentSuccess()
    {
        // 页面导向设定
        String forward = "failed";
        
        // 取得当前登录用户
        NserCustomerSimp customerSimp = getCustomerSimp();
        
        // 调用更新业务推荐结果接口
        ReturnWrap rw = recommendProductBean.setRecommendSuccess(getTerminalInfoPO(),
            customerSimp.getContactId(), customerSimp.getServNumber(),
            getCurMenuId(), getCommendOID());
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // begin zKF66389 2015-09-15 9月份findbugs修改
        {
            this.createRecLog(Constants.BUSITYPE_SETRECSUCCESS, "0", "0", "0", "营销推荐活动:更新业务推荐结果成功!");
            
            setSuccessMessage("业务受理成功!");
            
            forward = "success";
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000004", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_SETRECSUCCESS, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
        }
        
        return forward;
    }
    
    /**
     * 推荐业务受理成功或者失败，继续办理原业务
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String contineRec()
    {
        return "continue";
    }
    
    /**
     * 推荐业务受理,服务密码认证（缴费支持活动推荐）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkPassword()
    {
        return "checkPassword";
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getUserSeq()
    {
        return userSeq;
    }
    
    public void setUserSeq(String userSeq)
    {
        this.userSeq = userSeq;
    }
    
    public String getFeeChargeFlag()
    {
        return feeChargeFlag;
    }
    
    public void setFeeChargeFlag(String feeChargeFlag)
    {
        this.feeChargeFlag = feeChargeFlag;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public RecommendProductBean getRecommendProductBean()
    {
        return recommendProductBean;
    }
    
    public void setRecommendProductBean(RecommendProductBean recommendProductBean)
    {
        this.recommendProductBean = recommendProductBean;
    }
    
    public List<RecommendProductPO> getRecmdProductList()
    {
        return recmdProductList;
    }
    
    public void setRecmdProductList(List<RecommendProductPO> recmdProductList)
    {
        this.recmdProductList = recmdProductList;
    }

    public String getCommendOID()
    {
        return commendOID;
    }

    public void setCommendOID(String commendOID)
    {
        this.commendOID = commendOID;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getUserSeqs()
    {
        return userSeqs;
    }

    public void setUserSeqs(String userSeqs)
    {
        this.userSeqs = userSeqs;
    }

    /**
     * @return 返回 feedBackDefList
     */
    public List<FeedBackDefPO> getFeedBackDefList()
    {
        return feedBackDefList;
    }

    /**
     * @param 对feedBackDefList进行赋值
     */
    public void setFeedBackDefList(List<FeedBackDefPO> feedBackDefList)
    {
        this.feedBackDefList = feedBackDefList;
    }

    /**
     * @return 返回 actIds
     */
    public String getActIds()
    {
        return actIds;
    }

    /**
     * @param 对actIds进行赋值
     */
    public void setActIds(String actIds)
    {
        this.actIds = actIds;
    }

    /**
     * @return 返回 eventTypes
     */
    public String getEventTypes()
    {
        return eventTypes;
    }

    /**
     * @param 对eventTypes进行赋值
     */
    public void setEventTypes(String eventTypes)
    {
        this.eventTypes = eventTypes;
    }

    /**
     * @return 返回 actId
     */
    public String getActId()
    {
        return actId;
    }

    /**
     * @param 对actId进行赋值
     */
    public void setActId(String actId)
    {
        this.actId = actId;
    }

    /**
     * @return 返回 moContent
     */
    public String getMoContent()
    {
        return moContent;
    }

    /**
     * @param 对moContent进行赋值
     */
    public void setMoContent(String moContent)
    {
        this.moContent = moContent;
    }

    /**
     * @return 返回 recmdType
     */
    public String getRecmdType()
    {
        return recmdType;
    }

    /**
     * @param 对recmdType进行赋值
     */
    public void setRecmdType(String recmdType)
    {
        this.recmdType = recmdType;
    }
}
