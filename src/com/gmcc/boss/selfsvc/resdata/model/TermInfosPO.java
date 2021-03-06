/*
 * 文件名：TermInfosPO
 * 版权：
 * 描述:终端设备属性表
 * 修改人：lwx439898
 * 修改时间：2017-10-13
 * 修改内容：新增
 */

package com.gmcc.boss.selfsvc.resdata.model;

import com.gmcc.boss.selfsvc.common.BasePO;


/**
 * 
 * 终端设备属性表
 * <功能详细描述>
 * 
 * @author  lWX439898
 * @version  [版本号, 2017-10-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TermInfosPO extends BasePO
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1455148316261033172L;
    //终端设备号
    private String terminalid;
    //属性编码
    private String attributeid;
    //属性值
    private String attributeval;
    public String getTerminalid()
    {
        return terminalid;
    }
    public void setTerminalid(String terminalid)
    {
        this.terminalid = terminalid;
    }
    public String getAttributeid()
    {
        return attributeid;
    }
    public void setAttributeid(String attributeid)
    {
        this.attributeid = attributeid;
    }
    public String getAttributeval()
    {
        return attributeval;
    }
    public void setAttributeval(String attributeval)
    {
        this.attributeval = attributeval;
    }


    
    
}
