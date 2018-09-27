package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * 产品受理提交对象
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Jul 20, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdCommitPO
{
    // 对产品包下的子产品或者模板下的子产品，传产品编码；其它传NCODE
    private String ncode;
    
    // 生效方式
    // 2：立即
    // 3：次月
    // 4：次日
    // 5：指定生效时间
    // 默认是立即生效。需外围根据产品的实际情况传入
    private String effecttype;
    
    // 操作类型
    // PCOpRec:开通
    // PCOpMod:修改
    // PCOpDel:关闭
    // PCOpPau:暂停
    // PCOpRes:恢复
    private String opertype;
    
    // 附加属性，格式：
    // attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2…
    // 即附加属性编码=附加属性值=附加属性的操作类型，其中操作类型目前没用，直接使用
    // attrid1=attrvalue1= #attrid2=attrvalue2=#…即可
    private String attrparam;
    
    // 原增值产品编码，目前没用。直接传“”
    private String oldprodid;
    
    // 服务资源
    private String serviceres;
    
    // 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。
    // PCIntRelaNormal：普通业务
    // PCIntRelaRadio：业务切换
    private String inftype;
    
    // 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
    private String templetflag;
    
    // 具体生效时间
    private String startdate;
    
    // 具体失效时间
    private String enddate;
    
    // 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
    private String pkgid;
    
    // 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode
    private String objtype;
    
    // 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
    private String privid;
    
    // 模板编码。对产品包下子产品或者模板下子产品受理的时候使用
    private String templetid;

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getEffecttype()
    {
        return effecttype;
    }

    public void setEffecttype(String effecttype)
    {
        this.effecttype = effecttype;
    }

    public String getOpertype()
    {
        return opertype;
    }

    public void setOpertype(String opertype)
    {
        this.opertype = opertype;
    }

    public String getAttrparam()
    {
        return attrparam;
    }

    public void setAttrparam(String attrparam)
    {
        this.attrparam = attrparam;
    }

    public String getOldprodid()
    {
        return oldprodid;
    }

    public void setOldprodid(String oldprodid)
    {
        this.oldprodid = oldprodid;
    }

    public String getServiceres()
    {
        return serviceres;
    }

    public void setServiceres(String serviceres)
    {
        this.serviceres = serviceres;
    }

    public String getInftype()
    {
        return inftype;
    }

    public void setInftype(String inftype)
    {
        this.inftype = inftype;
    }

    public String getTempletflag()
    {
        return templetflag;
    }

    public void setTempletflag(String templetflag)
    {
        this.templetflag = templetflag;
    }

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getPkgid()
    {
        return pkgid;
    }

    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }

    public String getObjtype()
    {
        return objtype;
    }

    public void setObjtype(String objtype)
    {
        this.objtype = objtype;
    }

    public String getPrivid()
    {
        return privid;
    }

    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getTempletid()
    {
        return templetid;
    }

    public void setTempletid(String templetid)
    {
        this.templetid = templetid;
    }
    
    
}
