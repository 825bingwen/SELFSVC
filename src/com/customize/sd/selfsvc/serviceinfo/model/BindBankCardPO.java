/*
 * 文 件 名:  BindBankCardPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <绑定银行卡信息PO>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 13, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.customize.sd.selfsvc.serviceinfo.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * <绑定银行卡信息PO>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BindBankCardPO implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 手机号码
     */
    private String telNum = "";
    
    /**
     * 统一接触流水
     */
    private String contactId = "";
    
    /**
     * 用户真实姓名
     */
    private String userFactName = "";
    
    /**
     * 证件类型
     */
    private String idCardType = "";
    
    /**
     * 证件类型名称
     */
    private String idCardTypeText = "";
    
    /**
     * 证件类型_浪潮用
     */
    private String id_type = "";
    
    /**
     * 证件号码
     */
    private String idCardNum = "";
    
    /**
     * 银行卡类型
     */
    private String bankCardType = "";
    
    /**
     * 银行卡号码
     */
    private String bankCardNum = "";
    
    /**
     * 信用卡的有效期
     */
    private String expire = "";
    
    /**
     * 信用卡的CVN2
     */
    private String cvn2 = "";

    /**
     * 交易成功流水号
     */
    private String appFlowCode;
    
    // Add begin wWX217192 2014-11-26 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    /**
     * 快捷支付协议号 
     */
    private String AGRNO;
    
    /**
     * 订单金额
     */
    private String amount;
    
    /**
     * 银行代码
     */
    private String bankAbbr;
    // Add end wWX217192 2014-11-26 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    
    public String getTelNum()
    {
        return telNum;
    }

    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }

    public String getUserFactName()
    {
        return userFactName;
    }

    public void setUserFactName(String userFactName)
    {
        this.userFactName = userFactName;
    }

    public String getIdCardType()
    {
        return idCardType;
    }

    public void setIdCardType(String idCardType)
    {
        this.idCardType = idCardType;
    }

    public String getIdCardNum()
    {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum)
    {
        this.idCardNum = idCardNum;
    }

    public String getBankCardType()
    {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType)
    {
        this.bankCardType = bankCardType;
    }

    public String getBankCardNum()
    {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum)
    {
        this.bankCardNum = bankCardNum;
    }

    public String getExpire()
    {
        return expire;
    }

    public void setExpire(String expire)
    {
        this.expire = expire;
    }

    public String getCvn2()
    {
        return cvn2;
    }

    public void setCvn2(String cvn2)
    {
        this.cvn2 = cvn2;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getContactId()
    {
        return contactId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setContactId(String contactId)
    {
        this.contactId = contactId;
    }

    public String getAppFlowCode()
    {
        return appFlowCode;
    }

    public void setAppFlowCode(String appFlowCode)
    {
        this.appFlowCode = appFlowCode;
    }

    public String getIdCardTypeText()
    {
        return idCardTypeText;
    }

    public void setIdCardTypeText(String idCardTypeText)
    {
        this.idCardTypeText = idCardTypeText;
    }

    public String getId_type()
    {
        return id_type;
    }

    public void setId_type(String id_type)
    {
        this.id_type = id_type;
    }

    public String getAGRNO() {
		return AGRNO;
	}

	public void setAGRNO(String agrno) {
		AGRNO = agrno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBankAbbr() {
		return bankAbbr;
	}

	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

	/** 
     * 姓名除最后一个字显示其余均显示*
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     */
    public String getUserLastName()
    {
        // 当用户姓名为空时,返回"未知用户"
        if (StringUtils.isEmpty(getUserFactName()))
        {
            return "未知用户";
        }
        
        StringBuffer lastName = new StringBuffer();
        
        // "姓名"只展示最后一个字，其他的以* 代替
        for (int i = 0; i < (getUserFactName().length() - 1); i++)
        {
            lastName.append("*");
        }
        
        lastName.append(getUserFactName().substring(getUserFactName().length() - 1));
        
        return lastName.toString();
    }
}
