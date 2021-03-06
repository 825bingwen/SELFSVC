/*
 * 文 件 名:  CardBusiBaseAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  办卡Action基类
 * 修 改 人:  zKF69263
 * 修改时间:  2014-10-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.customize.hub.selfsvc.bean.CardInstallBean;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.IdCardPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.cardbusi.service.CardBusiService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 办卡Action基类
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CardBusiBaseAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2091574547747628710L;
    
    /** ---------------- 分页 ---------------------
     * 
     * 页数
     */
    protected int pageCount = 0;
    
    /** 
     * 每页显示容量
     */
    protected int pageSize = 9;
    
    /** 
     * 当前页
     */
    protected int page = 1;
    
    /** ---------------- 菜单相关 -------------------------
     * 
     * 当前菜单
     */
    protected String curMenuId = "";
    
    /**
     * sim卡信息类
     */
    protected transient SimInfoPO simInfoPO;
    
    /** 
     * 身份证信息
     */
    protected transient IdCardPO idCardPO;
    
    /**
     * 在线开户
     */
    protected transient CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();
    
    /** 
     * BEAN
     */
    protected transient CardInstallBean cardInstallBean;
    
    /** 
     * SERVICE
     */
    protected transient CardBusiService cardBusiService;
    
    /**---------------- 费用明细 -------------------------
     * 
     * 存放全部缴费信息
     */
    protected List<FeeConfirmPO> feeList;
    
    /** 
     * 应收费用
     */
    protected String recFee;
    
    /**
     * 是否打印小票  1-不打印，0-打印
     */
    protected String canNotPrint;
    
    /** 
     * 错误信息
     */
    protected String errormessage;
    
    /**
     * 终端机现金识币器和银联卡支付标识:0代表不可用,1代表可用,例如:11代表两设备都可用
     */
    protected String payTypeFlag;
    
    /**
     * 银联打印信息
     */
    protected String printcontext;
    
    /**
     * 是否将卡移动到回收箱 1：回收 0：不回收 默认不回收
     */
    protected String callBackCard;
    
    /**
     * 主动营销推荐标识
     */
    protected String recommendActivityFlag;
    
    /** 
     * 取得终端机支持空白卡类写卡型提示信息
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String getBlankCardTypeAlertMsg()
    {
        // 取出终端机空白卡写卡类型进行提示
        DictItemPO dictItemPO = getCardBusiService().qryTermBlankCardType(getTerminalInfoPO().getTermid());
        
        if (null != dictItemPO)
        {
            return dictItemPO.getDescription();
        }
        
        // 如果终端机没有配置支持空白卡类型时，提示默认信息
        return (String)PublicCache.getInstance().getCachedData("SH_BLANKCARDTYPE_ALERTMSG");
    }
    
    /** 
     * 取得分页数据
     * 
     * @param <E>
     * @param resultList
     * @return List<E>
     * @see [类、类#方法、类#成员]
     */
    protected <E> List<E> getPageList(List<E> resultList, int perPageSize)
    {
        int resultSize = 0;
        int startSeq = 0;
        int endSeq = 0;
        pageSize = perPageSize;
        
        // 获取总页数
        if (!resultList.isEmpty())
        {
            resultSize = resultList.size();
            
            pageCount = resultSize / pageSize;
            
            if (resultSize % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        
        // 计算起始和结束位置
        startSeq = (page - 1) * pageSize;
        endSeq = (page >= pageCount) ? resultSize : page * pageSize;
        
        // 记录总条数
        this.getRequest().setAttribute("recordCount", resultList.size());
        
        // 取得分页数据
        return resultList.subList(startSeq, endSeq);
    }
    
    /**
     * AJAX调用返回消息
     * 
     * @return void [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    protected void writeXmlMsg(String msg)
    {
        PrintWriter writer = null;
        
        try
        {
            this.getResponse().setContentType("text/xml;charset=GBK");
            writer = this.getResponse().getWriter();
            
            if (null != writer)
            {
                writer.write(msg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取可用缴费方式
     * @param groupID 终端组
     * @return
     */
    protected List getPayType(String groupID)
    {
        List<MenuInfoPO> menus = null;
        
        if (groupID != null && !"".equals(groupID))
        {
            menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
        }
        
        // 获取可选缴费方式
        List usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");

        return usableTypes;
    }
    /**
     * @return 返回 pageCount
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * @param 对pageCount进行赋值
     */
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    /**
     * @return 返回 pageSize
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * @param 对pageSize进行赋值
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * @return 返回 page
     */
    public int getPage()
    {
        return page;
    }

    /**
     * @param 对page进行赋值
     */
    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * @return 返回 idCardPO
     */
    public IdCardPO getIdCardPO()
    {
        return idCardPO;
    }

    /**
     * @param 对idCardPO进行赋值
     */
    public void setIdCardPO(IdCardPO idCardPO)
    {
        this.idCardPO = idCardPO;
    }

    /**
     * @return 返回 cardBusiLogPO
     */
    public CardBusiLogPO getCardBusiLogPO()
    {
        return cardBusiLogPO;
    }

    /**
     * @param 对cardBusiLogPO进行赋值
     */
    public void setCardBusiLogPO(CardBusiLogPO cardBusiLogPO)
    {
        this.cardBusiLogPO = cardBusiLogPO;
    }

    /**
     * @return 返回 cardInstallBean
     */
    public CardInstallBean getCardInstallBean()
    {
        return cardInstallBean;
    }

    /**
     * @param 对cardInstallBean进行赋值
     */
    public void setCardInstallBean(CardInstallBean cardInstallBean)
    {
        this.cardInstallBean = cardInstallBean;
    }

    /**
     * @return 返回 cardBusiService
     */
    public CardBusiService getCardBusiService()
    {
        return cardBusiService;
    }

    /**
     * @param 对cardBusiService进行赋值
     */
    public void setCardBusiService(CardBusiService cardBusiService)
    {
        this.cardBusiService = cardBusiService;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public SimInfoPO getSimInfoPO() 
	{
		return simInfoPO;
	}

	public void setSimInfoPO(SimInfoPO simInfoPO) 
	{
		this.simInfoPO = simInfoPO;
	}

	public List<FeeConfirmPO> getFeeList() 
	{
		return feeList;
	}

	public void setFeeList(List<FeeConfirmPO> feeList) 
	{
		this.feeList = feeList;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getRecFee() 
	{
		return recFee;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setRecFee(String recFee) 
	{
		this.recFee = recFee;
	}

	/**
	 * @return 返回 canNotPrint
	 */
	public String getCanNotPrint() 
	{
		return canNotPrint;
	}

	/**
	 * @param 对canNotPrint进行赋值
	 */
	public void setCanNotPrint(String canNotPrint) 
	{
		this.canNotPrint = canNotPrint;
	}

	/**
	 * @return 返回 errormessage
	 */
	public String getErrormessage() {
		return errormessage;
	}

	/**
	 * @param 对errormessage进行赋值
	 */
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

    /**
     * @return 返回 payTypeFlag
     */
    public String getPayTypeFlag()
    {
        return payTypeFlag;
    }

    /**
     * @param 对payTypeFlag进行赋值
     */
    public void setPayTypeFlag(String payTypeFlag)
    {
        this.payTypeFlag = payTypeFlag;
    }

    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public String getCallBackCard()
    {
        return callBackCard;
    }

    public void setCallBackCard(String callBackCard)
    {
        this.callBackCard = callBackCard;
    }

    public String getRecommendActivityFlag()
    {
        return recommendActivityFlag;
    }

    public void setRecommendActivityFlag(String recommendActivityFlag)
    {
        this.recommendActivityFlag = recommendActivityFlag;
    }
}
