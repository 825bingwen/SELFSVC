package com.gmcc.boss.selfsvc.quickpublish.model;

import java.io.Serializable;

/**
 * 
 * 产品大类PO
 * 
 * @author  cKF76106
 * @version  [版本号, Oct 11, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdTypePO implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 304980199259504569L;

    private String typeID = "";
    
    private String typeName = "";

    private String typeDesc = "";

    public String getTypeID()
    {
        return typeID;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public String getTypeDesc()
    {
        return typeDesc;
    }

    public void setTypeID(String typeID)
    {
        this.typeID = typeID;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public void setTypeDesc(String typeDesc)
    {
        this.typeDesc = typeDesc;
    }

}
