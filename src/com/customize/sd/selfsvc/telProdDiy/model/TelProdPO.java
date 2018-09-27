/*
 * 文 件 名:  TelProdPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.model;

/**
 * 自选套餐PO
 * 
 * @author jWX216858
 * @version [版本号, 2014-10-10]
 * @see
 * @since
 */
public class TelProdPO
{
    // ncode
    private String nCode;
    
    // 语音通话套餐优惠id
    private String voicePrivId;
    
    // 上网流量套餐优惠id
    private String netPrivId;
    
    // 语音通话套餐名称
    private String voiceName;
    
    // 上网流量套餐名称
    private String netName;
    
    // 语音通话资费标准
    private String voiceFeeStan;
    
    // 上网流量资费标准
    private String netFeeStan;
    
    // 语音通话套餐价格
    private Integer voicePrice;
    
    // 上网流量套餐价格
    private Integer netPrice;
    
    // 所属品牌
    private String belongBrand;
    
    // 所属地市
    private String belongRegion;
    
    // 查询类型
    private String qryType;
    
    // 语音通话套餐描述
    private String voiceDesc;
    
    // 上网流量套餐描述
    private String netDesc;
    
    // 语音通话套餐适合人群
    private String voiceSuitPer;
    
    // 上网流量套餐适合人群
    private String netSuitPer;
    
    // 语音通话套餐业务说明
    private String voiceBusDesc;
    
    // 上网流量套餐业务说明
    private String netBusDesc;
    
    //add begin sWX219697 2015-4-29 14:55:50 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
    /**
     * 主体产品编码
     */
    private String prodId;
    
    /**
     * 主体产品名称
     */
    private String prodName;
    
    /**
     * 主体产品简介
     */
    private String prodDesc;
    //add end sWX219697 2015-4-29 14:55:50 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
    
    public String getNCode()
    {
        return nCode;
    }
    
    public void setNCode(String code)
    {
        nCode = code;
    }
    
    public String getVoicePrivId()
    {
        return voicePrivId;
    }
    
    public void setVoicePrivId(String voicePrivId)
    {
        this.voicePrivId = voicePrivId;
    }
    
    public String getNetPrivId()
    {
        return netPrivId;
    }
    
    public void setNetPrivId(String netPrivId)
    {
        this.netPrivId = netPrivId;
    }
    
    public String getBelongBrand()
    {
        return belongBrand;
    }
    
    public void setBelongBrand(String belongBrand)
    {
        this.belongBrand = belongBrand;
    }
    
    public String getBelongRegion()
    {
        return belongRegion;
    }
    
    public void setBelongRegion(String belongRegion)
    {
        this.belongRegion = belongRegion;
    }
    
    public String getQryType()
    {
        return qryType;
    }
    
    public void setQryType(String qryType)
    {
        this.qryType = qryType;
    }
    
    public String getVoiceFeeStan()
    {
        return voiceFeeStan;
    }
    
    public void setVoiceFeeStan(String voiceFeeStan)
    {
        this.voiceFeeStan = voiceFeeStan;
    }
    
    public String getNetFeeStan()
    {
        return netFeeStan;
    }
    
    public void setNetFeeStan(String netFeeStan)
    {
        this.netFeeStan = netFeeStan;
    }
    
    public Integer getVoicePrice()
    {
        return voicePrice;
    }
    
    public void setVoicePrice(Integer voicePrice)
    {
        this.voicePrice = voicePrice;
    }
    
    public Integer getNetPrice()
    {
        return netPrice;
    }
    
    public void setNetPrice(Integer netPrice)
    {
        this.netPrice = netPrice;
    }
    
    public String getVoiceName()
    {
        return voiceName;
    }
    
    public void setVoiceName(String voiceName)
    {
        this.voiceName = voiceName;
    }
    
    public String getNetName()
    {
        return netName;
    }
    
    public void setNetName(String netName)
    {
        this.netName = netName;
    }
    
    public String getVoiceDesc()
    {
        return voiceDesc;
    }
    
    public void setVoiceDesc(String voiceDesc)
    {
        this.voiceDesc = voiceDesc;
    }
    
    public String getNetDesc()
    {
        return netDesc;
    }
    
    public void setNetDesc(String netDesc)
    {
        this.netDesc = netDesc;
    }
    
    public String getVoiceSuitPer()
    {
        return voiceSuitPer;
    }
    
    public void setVoiceSuitPer(String voiceSuitPer)
    {
        this.voiceSuitPer = voiceSuitPer;
    }
    
    public String getNetSuitPer()
    {
        return netSuitPer;
    }
    
    public void setNetSuitPer(String netSuitPer)
    {
        this.netSuitPer = netSuitPer;
    }
    
    public String getVoiceBusDesc()
    {
        return voiceBusDesc;
    }
    
    public void setVoiceBusDesc(String voiceBusDesc)
    {
        this.voiceBusDesc = voiceBusDesc;
    }
    
    public String getNetBusDesc()
    {
        return netBusDesc;
    }
    
    public void setNetBusDesc(String netBusDesc)
    {
        this.netBusDesc = netBusDesc;
    }
    
    public String getProdId()
    {
        return prodId;
    }
    
    public void setProdId(String prodId)
    {
        this.prodId = prodId;
    }
    
    public String getProdName()
    {
        return prodName;
    }
    
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdDesc()
    {
        return prodDesc;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdDesc(String prodDesc)
    {
        this.prodDesc = prodDesc;
    }
    
}
