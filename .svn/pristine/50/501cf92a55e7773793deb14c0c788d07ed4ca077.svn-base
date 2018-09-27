/*
 * 文件名：MenuInfoPO.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.resdata.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 资源数据
 * 
 * @author g00140516
 * 
 */
public class ResDataPO extends BasePO
{
    private static final long serialVersionUID = 1L;
    
    private String restype; // 资源所属分类 productid,menutype,region
    
    private String reskey; // 资源数据key
    
    private String resval;// 资源数据值
    
    public String getReskey()
    {
        return reskey;
    }
    
    public String getRestype()
    {
        return restype;
    }
    
    public String getResval()
    {
        return resval;
    }
    
    public void setReskey(String reskey)
    {
        this.reskey = reskey;
    }
    
    public void setRestype(String restype)
    {
        this.restype = restype;
    }
    
    public void setResval(String resval)
    {
        this.resval = resval;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[" + this.getClass() + "[");
        sb.append(", restype:" + this.getRestype());
        sb.append(", reskey:" + this.getReskey());
        sb.append(", resval:" + this.getResval());
        sb.append("]]");
        return super.toString();
    }
    
}
