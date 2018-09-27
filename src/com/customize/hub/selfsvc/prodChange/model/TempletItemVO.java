/**
 * 
 */
package com.customize.hub.selfsvc.prodChange.model;

/**
 * @author Administrator
 * 
 */
public class TempletItemVO
{
    // 必选产品
    public final static String SELETYPE_MUST = "SeleType_Must";
    
    // 产品编码
    private String entityID;
    
    // 产品名称
    private String entityName;
    
    // 产品类型，ProdCataType
    private String cataType;
    
    // 选中方式,SelectType:[SeleType_Choice-可选,SeleType_Default-默认选中, SeleType_Must-必选]
    private String selectType;
    
    private String minCount;
    
    private String maxCount;
    
    private String notes;
    
    private String haveServAttr;
    
    private String havePrivAttr;
    
    private String haveSP;
    
    private String oprType;
    
    /**
     * @return the entityID
     */
    public String getEntityID()
    {
        return entityID;
    }
    
    /**
     * @param entityID the entityID to set
     */
    public void setEntityID(String entityID)
    {
        this.entityID = entityID;
    }
    
    /**
     * @return the entityName
     */
    public String getEntityName()
    {
        return entityName;
    }
    
    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }
    
    /**
     * @return the cataType
     */
    public String getCataType()
    {
        return cataType;
    }
    
    /**
     * @param cataType the cataType to set
     */
    public void setCataType(String cataType)
    {
        this.cataType = cataType;
    }
    
    /**
     * @return the selectType
     */
    public String getSelectType()
    {
        return selectType;
    }
    
    /**
     * @param selectType the selectType to set
     */
    public void setSelectType(String selectType)
    {
        this.selectType = selectType;
    }
    
    /**
     * @return the minCount
     */
    public String getMinCount()
    {
        return minCount;
    }
    
    /**
     * @param minCount the minCount to set
     */
    public void setMinCount(String minCount)
    {
        this.minCount = minCount;
    }
    
    /**
     * @return the maxCount
     */
    public String getMaxCount()
    {
        return maxCount;
    }
    
    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(String maxCount)
    {
        this.maxCount = maxCount;
    }
    
    /**
     * @return the notes
     */
    public String getNotes()
    {
        return notes;
    }
    
    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
    /**
     * @return the haveServAttr
     */
    public String getHaveServAttr()
    {
        return haveServAttr;
    }
    
    /**
     * @param haveServAttr the haveServAttr to set
     */
    public void setHaveServAttr(String haveServAttr)
    {
        this.haveServAttr = haveServAttr;
    }
    
    /**
     * @return the havePrivAttr
     */
    public String getHavePrivAttr()
    {
        return havePrivAttr;
    }
    
    /**
     * @param havePrivAttr the havePrivAttr to set
     */
    public void setHavePrivAttr(String havePrivAttr)
    {
        this.havePrivAttr = havePrivAttr;
    }
    
    /**
     * @return the haveSP
     */
    public String getHaveSP()
    {
        return haveSP;
    }
    
    /**
     * @param haveSP the haveSP to set
     */
    public void setHaveSP(String haveSP)
    {
        this.haveSP = haveSP;
    }
    
    /**
     * @return the oprType
     */
    public String getOprType()
    {
        return oprType;
    }
    
    /**
     * @param oprType the oprType to set
     */
    public void setOprType(String oprType)
    {
        this.oprType = oprType;
    }
    
}
