package com.gmcc.boss.selfsvc.quickpublish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 多个产品受理提交对象
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Jul 20, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MultiProdCommitPO
{
    // 手机号码
    private String telnum;
    
    // 标识该接口是否只进行校验，不进行办理
    private String ischeck;
    
    // 赠送号码，用于赠送业务。传“”
    private String doneenum;
    
    // 算费标识，预留字段，暂不使用。传“”
    private String iscalcfee;
    
    // 短信发送标识
    private String issendsms;
    
    // 传“”
    private String opersource;
    
    // 产品提交对象
    private List<ProdCommitPO> prodCommitList = new ArrayList<ProdCommitPO>();

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getIscheck()
    {
        return ischeck;
    }

    public void setIscheck(String ischeck)
    {
        this.ischeck = ischeck;
    }

    public String getDoneenum()
    {
        return doneenum;
    }

    public void setDoneenum(String doneenum)
    {
        this.doneenum = doneenum;
    }

    public String getIscalcfee()
    {
        return iscalcfee;
    }

    public void setIscalcfee(String iscalcfee)
    {
        this.iscalcfee = iscalcfee;
    }

    public String getIssendsms()
    {
        return issendsms;
    }

    public void setIssendsms(String issendsms)
    {
        this.issendsms = issendsms;
    }

    public String getOpersource()
    {
        return opersource;
    }

    public void setOpersource(String opersource)
    {
        this.opersource = opersource;
    }

    public List<ProdCommitPO> getProdCommitList()
    {
        return prodCommitList;
    }

    public void setProdCommitList(List<ProdCommitPO> prodCommitList)
    {
        this.prodCommitList = prodCommitList;
    }
    
    
}
