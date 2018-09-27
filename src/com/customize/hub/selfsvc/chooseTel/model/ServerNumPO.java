package com.customize.hub.selfsvc.chooseTel.model;
/**
 * 
 * 手机号码PO
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 20, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServerNumPO
{
    private String telnum;// 手机号码
    private String seltel_prepayfee;// 号码级别，即预存费，单位：分
    // add begin liutao 2016-07-30 OR_HUB_201603_1191  自助终端显示内容优化需求（张德伟）
    private String minimumCharge;//最低消费金额

    public String getTelnum()
    {
        return telnum;
    }
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    public String getSeltel_prepayfee()
    {
        return seltel_prepayfee;
    }
    public void setSeltel_prepayfee(String seltel_prepayfee)
    {
        this.seltel_prepayfee = seltel_prepayfee;
    }

    public String getMinimumCharge()
    {
        return minimumCharge;
    }

    public void setMinimumCharge(String minimumCharge)
    {
        this.minimumCharge = minimumCharge;
    }
}
