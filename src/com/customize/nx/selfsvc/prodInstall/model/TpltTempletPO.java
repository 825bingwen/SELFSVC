package com.customize.nx.selfsvc.prodInstall.model;

/**
 * 
 * 产品模板
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Jul 29, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class TpltTempletPO
{
    private String templetId = "";// 模板编号，对应营业PC_TPLT_TEMPLET.TEMPLETID
    private String templetName = "";// 模板名称，对应营业PC_TPLT_TEMPLET.TEMPLETNAME
    private String mainProdId = "";// 产品编码
    private String mainProdName = "";// 产品名称
    private String brand = "";// 品牌
    private String monthFee = "";// 套餐月费
    private String description = "";// 套餐详情
    private String region = "";// 创建地区
    private String orgId = "";// 创建单位
    private String createDate = "";// 创建时间
    private String statusDate = "";// 状态时间
    private String sortOrder = "";// 顺序号
    private String minFee = "";// 预存费用
    private String operId = "";// 创建人
    private String notes = "";// 备注信息
    public String getTempletId()
    {
        return templetId;
    }
    public void setTempletId(String templetId)
    {
        this.templetId = templetId;
    }
    public String getTempletName()
    {
        return templetName;
    }
    public void setTempletName(String templetName)
    {
        this.templetName = templetName;
    }
    public String getMainProdId()
    {
        return mainProdId;
    }
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    public String getMainProdName()
    {
        return mainProdName;
    }
    public void setMainProdName(String mainProdName)
    {
        this.mainProdName = mainProdName;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    public String getMonthFee()
    {
        return monthFee;
    }
    public void setMonthFee(String monthFee)
    {
        this.monthFee = monthFee;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getRegion()
    {
        return region;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getOrgId()
    {
        return orgId;
    }
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getStatusDate()
    {
        return statusDate;
    }
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    public String getSortOrder()
    {
        return sortOrder;
    }
    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    public String getMinFee()
    {
        return minFee;
    }
    public void setMinFee(String minFee)
    {
        this.minFee = minFee;
    }
    public String getOperId()
    {
        return operId;
    }
    public void setOperId(String operId)
    {
        this.operId = operId;
    }
    public String getNotes()
    {
        return notes;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
}
