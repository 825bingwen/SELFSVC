package com.gmcc.boss.selfsvc.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.model.OfferInfoVO;


/**
 * 
 * <山东智能营销平台(ISSS)业务营销与办理结果反馈>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Sep 18, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by sWX219697 2014-9-18 12:02:12 OR_SD_201409_556_自助终端营销功能优化
 */
public class ISSSAction extends BaseAction
{

	/**
	 *序列化
	 */
	private static final long serialVersionUID = 1L;
	
    public static final Log logger = LogFactory.getLog(ISSSAction.class);
	
	/**
	 * 对应的菜单id
	 */
	private String offerMenu;
	
	/**
	 * 产品id
	 */
	private String offerId;

	/**
	 * 战役ID，反馈时使用
	 */
	private String treatment_id;
	
	/**
	 * 手机号码
	 */
	private String servnumber;
	
	/**
	 * 办理结果
	 */
	private int status;
	
    /**
     * 用户订购状态 订购成功
     */
    private static final String ORDERED = "ordered";
    
    /**
     * 用户订购状态 订购失败
     */
    private static final String FAILED = "failed";
    
    /**
     * 4主体产品变更时为主体产品编码
     */
    private String offerCode;
	
    /**
     * 营销接口返回的业务列表
     */
    private List<OfferInfoVO> offerInfoList = new ArrayList<OfferInfoVO>();
    
    /**
     * <设置ISSS对应的session，用于向智能营销平台反馈办理结果 山东>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void setISSSSession()
    {
		//字符格式
		this.getResponse().setContentType("text/xml;charset=GBK");
        
		PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        	//设置session，反馈办理结果
        	ISSSInvokeUtil.setISSSSession(servnumber, offerMenu, offerId, treatment_id, offerCode);
        	
        	writer.write("1");
        }
        catch (Exception e)
        {
           logger.error("设置session失败：", e);
           writer.write("0");
        }
        
        writer.flush();
    	
    }
    
    /**
     * 查询推荐业务列表
     */
    public void qryOfferInfoList()
    {
        //调用营销事件webservice接口，用于充值结束后的业务展示
        offerInfoList = ISSSInvokeUtil.eventInvoke(servnumber);

        // 转换成JSON数组
        JSONArray jsonArray = JSONArray.fromObject(offerInfoList);
        
        this.getResponse().setContentType("text/xml;charset=GBK");
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
        	if (e != null)
        	{
        		e.printStackTrace();
        	}
        }
        
        // 返回结果
        if (jsonArray != null)
        {
        	System.out.println(jsonArray.toString());
        	writer.write(jsonArray.toString());
        }
    }
    
    /**
     * <向智能营销平台反馈办理结果 山东>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void feedbackByStatus()
    {
		//字符格式
		this.getResponse().setContentType("text/xml;charset=GBK");
		
		PrintWriter writer = null;
		
		try 
		{
			writer = this.getResponse().getWriter();
			
	    	String orderStatus = (SSReturnCode.SUCCESS == status ? ORDERED : FAILED);
	    	
	    	ISSSInvokeUtil.feedbackInvoke(servnumber, offerMenu, orderStatus);
			
			writer.write("1");
		} 
		catch (Exception e) 
		{
			logger.error("向ISSS平台反馈办理结果失败：", e);
			writer.write("0");
		}
		
		writer.flush();
    }
    
    /**
     * <智能营销平台考虑一下>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-2-11 17:00:47 OR_SD_201502_198_ISSS自助终端、便利店支持无产品营销活动推荐
     */
    public void consider()
    {
		try 
		{
			//推荐信息
			OfferInfoVO offer = new OfferInfoVO();
			
			//考虑一下
			offer.setStatus("toLeaded");
			
			//战役编码
			offer.setTreatment_id(treatment_id);
			
			//考虑一下，offerid固定为1
			offer.setOfferId(offerId);
	    	
	    	ISSSInvokeUtil.doFeedbackInvoke(servnumber, offer);
		} 
		catch (Exception e) 
		{
			logger.error("考虑一下办理结果失败：", e);
		}
    }


	public String getOfferMenu() {
		return offerMenu;
	}


	public void setOfferMenu(String offerMenu) {
		this.offerMenu = offerMenu;
	}


	public String getOfferId() {
		return offerId;
	}


	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}


	public String getTreatment_id() {
		return treatment_id;
	}


	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	}


	public String getServnumber() {
		return servnumber;
	}


	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
}
