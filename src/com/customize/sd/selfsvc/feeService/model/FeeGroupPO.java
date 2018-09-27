package com.customize.sd.selfsvc.feeService.model;

import java.util.List;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Feb 15, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FeeGroupPO
{
    private String name;
    private String sortorder;
    private List<FeePO> subfee;
    private FeePO fee;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public List<FeePO> getSubfee()
    {
        return subfee;
    }
    public void setSubfee(List<FeePO> subfee)
    {
        this.subfee = subfee;
    }
    public String getSortorder()
    {
        return sortorder;
    }
    public void setSortorder(String sortorder)
    {
        this.sortorder = sortorder;
    }
    public FeePO getFee()
    {
        return fee;
    }
    public void setFee(FeePO fee)
    {
        this.fee = fee;
    }
    
    
}
