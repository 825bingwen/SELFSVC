package com.customize.sd.selfsvc.feeService.model;

import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Feb 16, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrivPO
{
    private String rateid;// 资费政策ID
    private String freetype;// 通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR
    private String total;// 赠送的总量
    private String used;// 已经使用的量
    private String attrtype;// 单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时)
    private String attrtypename;
    private String feename;// 资费政策名称
    private String attrtypeunit;// 单位名称
    private String surplusamount;// 剩余量
    private String privset;// 套餐信息查询中的名称
    public String getRateid()
    {
        return rateid;
    }
    public void setRateid(String rateid)
    {
        this.rateid = rateid;
    }
    public String getFreetype()
    {
        return freetype;
    }
    public void setFreetype(String freetype)
    {
        this.freetype = freetype;
    }
    public String getTotal()
    {
        return total;
    }
    public void setTotal(String total)
    {
        this.total = total;
    }
    public String getUsed()
    {
        return used;
    }
    public void setUsed(String used)
    {
        this.used = used;
    }
    public String getAttrtype()
    {
        return attrtype;
    }
    public void setAttrtype(String attrtype)
    {
        this.attrtype = attrtype;
    }
    public String getFeename()
    {
        return feename;
    }
    public void setFeename(String feename)
    {
        this.feename = feename;
    }
    public String getAttrtypename()
    {
        // 单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时)
        if ("1".equals(attrtype))
        {
            attrtypename = "次数";
        }
        else if ("2".equals(attrtype))
        {
            attrtypename = "时长(秒)";
        }
        else if ("3".equals(attrtype))
        {
            attrtypename = "费用(元)";
        }
        else if ("4".equals(attrtype))
        {
            attrtypename = "流量(KB)";
        }
        else if ("5".equals(attrtype))
        {
            attrtypename = "流量(M)";
        }
        else if ("6".equals(attrtype))
        {
            attrtypename = "时长(小时)";
        }
        else
        {
            attrtypename = attrtype;
        }
        return attrtypename;
    }
    public void setAttrtypename(String attrtypename)
    {
        this.attrtypename = attrtypename;
    }
	public String getAttrtypeunit() 
	{
        return attrtypeunit;
	}
	public void setAttrtypeunit(String attrtypeunit) 
	{
		this.attrtypeunit = attrtypeunit;
	}
	public String getSurplusamount() 
	{
		return surplusamount;
	}
	public void setSurplusamount(String surplusamount)
	{
		this.surplusamount = surplusamount;
	}
	public String getPrivset() {
		return privset;
	}
	public void setPrivset(String privset) {
		this.privset = privset;
	}
	
}
