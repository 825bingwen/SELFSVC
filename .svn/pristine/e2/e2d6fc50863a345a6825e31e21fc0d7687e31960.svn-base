package com.customize.sd.selfsvc.feeService.model;

import java.util.ArrayList;
import java.util.List;

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
@edu.umd.cs.findbugs.annotations.SuppressWarnings( {"NM_CONFUSING", "EC_UNRELATED_TYPES"})
public class PkgPO
{
    private String pkgid;// 套餐ID
    private String pkgname;// 套餐名称
    private String pkgdesc;// 套餐描述
    private List<PrivPO> privlist = new ArrayList<PrivPO>();// 字符列表
    private String privs;
    public String getPkgid()
    {
        return pkgid;
    }
    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }
    public String getPkgname()
    {
        return pkgname;
    }
    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }
    public String getPkgdesc()
    {
        return pkgdesc;
    }
    public void setPkgdesc(String pkgdesc)
    {
        this.pkgdesc = pkgdesc;
    }
    public List<PrivPO> getPrivlist()
    {
        return privlist;
    }
    public void setPrivlist(List<PrivPO> privlist)
    {
        this.privlist = privlist;
    }
    public String getPrivs()
    {
        privs = "";
        for(PrivPO privPO:privlist)
        {
            privs = privs + privPO.getFreetype() + ":" + privPO.getUsed() + ";";
        }
        return privs;
    }
    public void setPrivs(String privs)
    {
        this.privs = privs;
    }
    
    
}
