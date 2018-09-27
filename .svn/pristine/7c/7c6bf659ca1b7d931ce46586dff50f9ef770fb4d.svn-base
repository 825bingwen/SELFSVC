/*
 * 文件名：NserCustomerSimp.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：客户资料简化类Action
 * 修改人：H60010815
 * 修改时间：2006-4-28
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.gmcc.boss.selfsvc.common.BasePO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 客户资料简化类Action
 * </p>
 * 
 * @author H60010815
 * @version 1.0 2006.4.28
 */
/**
 * add prdId : 具体的产品iD
 * 
 * @author x60003349 <br>
 *         创建时间：Jun 5, 2006 9:04:51 AM
 */
public class NserCustomerSimp extends BasePO
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4799465955832157278L;

    private String servNumber; // 服务号码
    
    private String customerName; // 客户姓名
    
    private String regionID; // 地市ID
    
    private String regionName; // 所属地市名称
    
    private String productID; // 具体的产品ID
    
    private String brandName; // 产品名称
    
    private String brandID; // 产品iD
    
    private String vipType; // VIP类型
    
    private String loginType; // 接口返回的登录方式
    
    private String feeFlag; // 是否签署缴费协议，1 已签署；0 未签署
    
    private String netType; // 网络类型
    
    private String loginMark; // 客户的登陆方式
    
    // zKF28472 findbugs
    private String contactId = "";// 统一接触流水
    
    // zKF28472 findbugs
    
    private String status = ""; // 用户状态
    
    // add begin l00190940 2011/10/18 OR_HUB_201108_1001
    private String subage = ""; // 用户网龄
    
    private String subscore = ""; // 用户积分
    // add end l00190940 2011/10/18 OR_HUB_201108_1001
    
    // add begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
    // 验证码
    private String verifyCode = "";
    
    private String onlineTime; // 用户入网时间，格式为YYYY-MM-DD
    
    // add begin g00140516 2012/08/06 R003C12L08n01 OR_NX_201206_794
    private String subsID = "";
    // add end g00140516 2012/08/06 R003C12L08n01 OR_NX_201206_794
    
    // add begion yKF28472 OR_huawei_201305_474
    private String smallregion = "";
    // add end yKF28472 OR_huawei_201305_474
    
    //add begin sWX219697 2014-6-30 14:48:04 OR_HUB_201406_1115_湖北跨运营商携号转网
    /**
     * 入网类型 sbsnTransTelOut：跨运营商携出；sbsnTransTelIn：跨运营商携入 
     */
    private String signType;
    //add end sWX219697 2014-6-30 14:48:04 OR_HUB_201406_1115_湖北跨运营商携号转网
    
    public String getVerifyCode()
    {
        return verifyCode;
    }
    
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }
    
    // add end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public NserCustomerSimp()
    {
    }
    
    public NserCustomerSimp(String servNumber, String username, String regionID, String regionName, String brandID,
            String brandName)
    {
        this.servNumber = servNumber;
        this.customerName = username;
        this.regionID = regionID;
        this.regionName = regionName;
        this.brandID = brandID;
        this.brandName = brandName;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
    
    public String getProductID()
    {
        return productID;
    }
    
    public void setProductID(String productID)
    {
        this.productID = productID;
    }
    
    public String getRegionID()
    {
        return regionID;
    }
    
    public void setRegionID(String regionID)
    {
        this.regionID = regionID;
    }
    
    public String getRegionName()
    {
        return regionName;
    }
    
    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }
    
    public String getServNumber()
    {
        return servNumber;
    }
    
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }
    
    public String toString()
    {
        return "NserCustomerSimp类信息: 姓名：" + this.customerName + ", 电话号码：" + this.getServNumber();
    }
    
    /**
     * @return Returns the loginMark.
     */
    public String getLoginMark()
    {
        return loginMark;
    }
    
    /**
     * @param loginType The loginMark to set.
     */
    public void setLoginMark(String loginMark)
    {
        this.loginMark = loginMark;
    }

    public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getBrandID()
    {
        return brandID;
    }
    
    public void setBrandID(String brandID)
    {
        this.brandID = brandID;
    }
    
    public String getVipType()
    {
        return vipType;
    }
    
    public void setVipType(String vipType)
    {
        this.vipType = vipType;
    }
    
    public String getLoginType()
    {
        return loginType;
    }
    
    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }
    
    public String getFeeFlag()
    {
        return feeFlag;
    }
    
    public void setFeeFlag(String feeFlag)
    {
        this.feeFlag = feeFlag;
    }
    
    public String getNetType()
    {
        return netType;
    }
    
    public void setNetType(String netType)
    {
        this.netType = netType;
    }
    
    public String getSubage()
    {
        return subage;
    }
    
    public void setSubage(String subage)
    {
        this.subage = subage;
    }
    
    public String getSubscore()
    {
        return subscore;
    }
    
    public void setSubscore(String subscore)
    {
        this.subscore = subscore;
    }
    
    public String getOnlineTime()
    {
        return onlineTime;
    }
    
    public void setOnlineTime(String onlineTime)
    {
        this.onlineTime = onlineTime;
    }

    public String getSubsID()
    {
        return subsID;
    }

    public void setSubsID(String subsID)
    {
        this.subsID = subsID;
    }

    public String getSmallregion()
    {
        return smallregion;
    }

    public void setSmallregion(String smallregion)
    {
        this.smallregion = smallregion;
    }

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
    
    
}
