package com.gmcc.boss.selfsvc.quickpublish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 产品包或者模板下子产品查询接口返回数据模型 
 * <功能详细描述>
 * 
 * @author  cKF76106
 * @version  [版本号, Jul 9, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChildProdPO implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = -6709326670062855076L;

    // 可选产品最小数
    private String minprod = "";
    
    // 可选产品最大数
    private String maxprod = "";
    
    //产品包下子产品列表
    private List<SubsChildProdPO> subsChildProdPOList = new ArrayList<SubsChildProdPO>();

    public String getMinprod()
    {
        return minprod;
    }

    public void setMinprod(String minprod)
    {
        this.minprod = minprod;
    }

    public String getMaxprod()
    {
        return maxprod;
    }

    public void setMaxprod(String maxprod)
    {
        this.maxprod = maxprod;
    }

    public List<SubsChildProdPO> getSubsChildProdPOList()
    {
        return subsChildProdPOList;
    }

    public void setSubsChildProdPOList(List<SubsChildProdPO> subsChildProdPOList)
    {
        this.subsChildProdPOList = subsChildProdPOList;
    }

    
}
