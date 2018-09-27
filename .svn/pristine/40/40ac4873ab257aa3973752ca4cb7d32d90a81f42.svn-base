/*
 * 文件名：TerminalInfoDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：与终端机有关的数据库操作
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.terminfo.dao;

//delete begin g00140516 2011/11/08 R003C11L11n01 去掉无用类
//delete end g00140516 2011/11/08 R003C11L11n01 去掉无用类

import java.util.List;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 与终端机有关的数据库操作
 * 
 * @author g00140516
 * @version 1.0，2010-12-16
 * @see 
 * @since 
 */
public class TerminalInfoDaoImpl extends BaseDaoImpl
{
    public TerminalInfoDaoImpl()
    {
        super();
    }
    
    /**
     * 根据ip获取终端信息
     * 
     */
    public List getTerminalInfoByIP(String IP)
    {
        return sqlMapClientTemplate.queryForList("TerminalInfo.getTerminalInfoByIP", IP);
    }
    
    /**
     * 根据厂商和浏览器类型获取插件信息
     * 
     */
    public List getPluginInfo(TerminalInfoPO infoPO)
    {
        return sqlMapClientTemplate.queryForList("TerminalInfo.getPluginInfo", infoPO);
    }
    
    /**
     * 根据termid获取可用菜单ID列表
     * 
     * @param termid
     * @return
     * @see 
     */
    public List getMenuIDList(String termid)
    {
        return sqlMapClientTemplate.queryForList("TerminalInfo.getMenuIDList", termid);
    }
    
    // add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
    /**
     * 更新SH_TERM_CONFIG表IP
     * 
     * @param termInfoPO
     * @see
     */
    public void updateTermConfigIp(TerminalInfoPO termInfoPO)
    {
        sqlMapClientTemplate.update("TerminalInfo.updateTermConfigIp", termInfoPO);
    }
    
    /**
     * 判断终端机IP是否已经存在
     * 
     * @param termInfoPO
     * @see
     */
    public String isTerminalExisted(TerminalInfoPO termInfoPO)
    {
        return (String)sqlMapClientTemplate.queryForObject("TerminalInfo.isIPAddrExisted", termInfoPO);
    }
    // add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
    
    /**
    * 根据MAC地址获取终端机信息
    * 
    * @param mac 终端机mac地址
    * @return 终端机信息列表
    * @exception/throws 
    * @see 
    * @depreced
    * @remark create g00140516 2011、11、11 R003C11L11n01 OR_huawei_201111_149
    */
    public List getTerminalInfoByMAC(String mac)
    {
        return sqlMapClientTemplate.queryForList("TerminalInfo.getTerminalInfoByMAC", mac);
    }
    
    // add begion yKF28472 OR_huawei_201305_474
    /**
     * 取市级orgId 如:比如HB.JH.01.03，高级单位为HB.JH
     * <功能详细描述>
     * @param orgId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getCityOrgId(String orgId)
    {
        return (String)sqlMapClientTemplate.queryForObject("TerminalInfo.getCityOrgId", orgId);
    }
    // add end yKF28472 OR_huawei_201305_474
    
    
    // add begin hWX5316476 OR_huawei_201305_1248 
    /**
     * 根据终端机的orgid获取组织机构的orgtype <功能详细描述>
     * @param termInfoPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getOrgtype(TerminalInfoPO termInfoPO)
    {
        return (String)sqlMapClientTemplate.queryForObject("TerminalInfo.getOrgtype", termInfoPO);
    }
    // add end hWX5316476 OR_huawei_201305_1248
}
