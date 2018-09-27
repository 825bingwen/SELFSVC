package com.gmcc.boss.selfsvc.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BasePO implements Serializable, Cloneable
{
    
    private String suffix;
    
    private static final long serialVersionUID = 1L;
    
    public BasePO()
    {
        suffix = null;
    }
    
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    public String getSuffix()
    {
        return suffix;
    }
    
    public Object clone() throws CloneNotSupportedException
    {
        BasePO clone = (BasePO)super.clone();
        clone.suffix = suffix;
        return clone;
    }
}
