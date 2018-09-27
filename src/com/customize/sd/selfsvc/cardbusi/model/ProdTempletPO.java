/*
 * 文 件 名:  ProdTempletPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  开户产品模板
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.model;

/**
 * 开户产品模板
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdTempletPO
{
    private String region = "";// 创建地区
    private String orgId = "";// 创建单位
    private String operId = "";// 创建人
    private String mainProdId = "";// 产品编码
    private String mainProdName = "";// 产品名称
    private String templetId = "";// 模板编号，对应营业PC_TPLT_TEMPLET.TEMPLETID
    private String templetName = "";// 模板名称，对应营业PC_TPLT_TEMPLET.TEMPLETNAME
    private String brand = "";// 品牌
    private String monthFee = "";// 套餐月费
    private String description = "";// 套餐详情
    private String sortOrder = "";// 顺序号
    private String minFee = "";// 预存费用
    private String notes = "";// 备注信息
    private String createDate = "";// 创建时间
    private String statusDate = "";// 状态时间
    
    /**
     * @return 返回 region
     */
    public String getRegion()
    {
        return region;
    }
    /**
     * @param 对region进行赋值
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    /**
     * @return 返回 orgId
     */
    public String getOrgId()
    {
        return orgId;
    }
    /**
     * @param 对orgId进行赋值
     */
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }
    /**
     * @return 返回 operId
     */
    public String getOperId()
    {
        return operId;
    }
    /**
     * @param 对operId进行赋值
     */
    public void setOperId(String operId)
    {
        this.operId = operId;
    }
    /**
     * @return 返回 mainProdId
     */
    public String getMainProdId()
    {
        return mainProdId;
    }
    /**
     * @param 对mainProdId进行赋值
     */
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    /**
     * @return 返回 mainProdName
     */
    public String getMainProdName()
    {
        return mainProdName;
    }
    /**
     * @param 对mainProdName进行赋值
     */
    public void setMainProdName(String mainProdName)
    {
        this.mainProdName = mainProdName;
    }
    /**
     * @return 返回 templetId
     */
    public String getTempletId()
    {
        return templetId;
    }
    /**
     * @param 对templetId进行赋值
     */
    public void setTempletId(String templetId)
    {
        this.templetId = templetId;
    }
    /**
     * @return 返回 templetName
     */
    public String getTempletName()
    {
        return templetName;
    }
    /**
     * @param 对templetName进行赋值
     */
    public void setTempletName(String templetName)
    {
        this.templetName = templetName;
    }
    /**
     * @return 返回 brand
     */
    public String getBrand()
    {
        return brand;
    }
    /**
     * @param 对brand进行赋值
     */
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    /**
     * @return 返回 monthFee
     */
    public String getMonthFee()
    {
        return monthFee;
    }
    /**
     * @param 对monthFee进行赋值
     */
    public void setMonthFee(String monthFee)
    {
        this.monthFee = monthFee;
    }
    /**
     * @return 返回 description
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    /**
     * @return 返回 sortOrder
     */
    public String getSortOrder()
    {
        return sortOrder;
    }
    /**
     * @param 对sortOrder进行赋值
     */
    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    /**
     * @return 返回 minFee
     */
    public String getMinFee()
    {
        return minFee;
    }
    /**
     * @param 对minFee进行赋值
     */
    public void setMinFee(String minFee)
    {
        this.minFee = minFee;
    }
    /**
     * @return 返回 notes
     */
    public String getNotes()
    {
        return notes;
    }
    /**
     * @param 对notes进行赋值
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    /**
     * @return 返回 createDate
     */
    public String getCreateDate()
    {
        return createDate;
    }
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    /**
     * @return 返回 statusDate
     */
    public String getStatusDate()
    {
        return statusDate;
    }
    /**
     * @param 对statusDate进行赋值
     */
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
}
