/*
 * 文件名：TerminalInfoService.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：与终端有关的数据库操作
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.terminfo.service;

import java.util.List;

import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 与终端相关的操作
 * 
 * @author g00140516
 * @version 1.0，2010-11-30
 * @see 
 * @since 
 */
public interface TerminalInfoService
{
    /**
     * 根据ip获取终端信息
     * 
     * @param IP
     * @return
     * @see 
     */
    public List getTerminalInfoByIP(String IP);
    
    /**
     * 根据厂商和浏览器类型获取插件信息 
     * 
     * @param infoPO
     * @return
     * @see 
     */
    public List getPluginInfo(TerminalInfoPO infoPO);
    
    /**
     * 根据termid获取可用菜单ID列表 
     * 
     * @param termid
     * @return
     * @see 
     */
    public List getMenuIDList(String termid);
    
    // add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
    /**
     * 更新SH_TERM_CONFIG表IP
     * 
     * @param termInfoPO
     * @see
     */
    public void updateTermConfigIp(TerminalInfoPO termInfoPO);
    
    /**
     * 判断终端机IP是否已经存在
     * 
     * @param termInfoPO
     * @see
     */
    public boolean isTerminalExisted(TerminalInfoPO termInfoPO);
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
    public List getTerminalInfoByMAC(String mac);
    
    // add begion yKF28472 OR_huawei_201305_474
    /**
     * 取市级orgId 如:比如HB.JH.01.03，高级单位为HB.JH
     * <功能详细描述>
     * @param orgId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getCityOrgId(String orgId);
    // add end yKF28472 OR_huawei_201305_474
    
    // add begin hWX5316476 OR_huawei_201305_1248 
    /**
     * 根据终端机的orgid获取组织机构的orgtype <功能详细描述>
     * @param termInfoPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getOrgtype(TerminalInfoPO termInfoPO);
    // add end hWX5316476 OR_huawei_201305_1248
}
