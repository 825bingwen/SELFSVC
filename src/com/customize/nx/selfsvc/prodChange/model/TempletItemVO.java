package com.customize.nx.selfsvc.prodChange.model;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  qWX279398
 * @version  [版本号, 2015-7-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark modify begin qWX279398 2015年7月20日16:42:21 从湖北同名类中copy，findbugs修改
 */
public class TempletItemVO
{
    // 必选产品
    public final static String SELETYPE_MUST = "SeleType_Must";
    
    // 产品类型，ProdCataType
    private String cataType;
    
    // 产品编码
    private String entityID;
    
    // 产品名称
    private String entityName;
    
    private String minCount;
    
    // 选中方式,SelectType:[SeleType_Choice-可选,SeleType_Default-默认选中, SeleType_Must-必选]
    private String selectType;
    
    private String notes;
    
    private String maxCount;
    
    private String havePrivAttr;
    
    private String haveServAttr;
    
    private String oprType;
    
    private String haveSP;

    public String getEntityID()
    {
        return entityID;
    }

    public void setEntityID(String entityID)
    {
        this.entityID = entityID;
    }

    public String getCataType()
    {
        return cataType;
    }

    public void setCataType(String cataType)
    {
        this.cataType = cataType;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    public String getMinCount()
    {
        return minCount;
    }

    public void setMinCount(String minCount)
    {
        this.minCount = minCount;
    }

    public String getSelectType()
    {
        return selectType;
    }

    public void setSelectType(String selectType)
    {
        this.selectType = selectType;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getMaxCount()
    {
        return maxCount;
    }

    public void setMaxCount(String maxCount)
    {
        this.maxCount = maxCount;
    }

    public String getHavePrivAttr()
    {
        return havePrivAttr;
    }

    public void setHavePrivAttr(String havePrivAttr)
    {
        this.havePrivAttr = havePrivAttr;
    }

    public String getHaveServAttr()
    {
        return haveServAttr;
    }

    public void setHaveServAttr(String haveServAttr)
    {
        this.haveServAttr = haveServAttr;
    }

    public String getOprType()
    {
        return oprType;
    }

    public void setOprType(String oprType)
    {
        this.oprType = oprType;
    }

    public String getHaveSP()
    {
        return haveSP;
    }

    public void setHaveSP(String haveSP)
    {
        this.haveSP = haveSP;
    }
    
}
