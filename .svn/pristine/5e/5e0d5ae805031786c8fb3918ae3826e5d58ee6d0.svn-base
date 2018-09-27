package com.gmcc.boss.selfsvc.privilege.service;

import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.privilege.model.CustNcodeInfoPO;
import com.gmcc.boss.selfsvc.privilege.model.GroupNcodePO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface PrivilegeService
{
	/**
     * 山东移动梦网业务订购
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param telnum
     * @param spid
     * @param bizid
     * @return
     * @remark create by wWX217192 2014-04-02 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
     */
    String addSPReception(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String telnum, String spid, String bizid);
    
    /**
     * 根据组ID查询同组业务列表
     * @param groupId
     * @param menuid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-4-29 OR_SD_201503_945_山东_自助终端支撑‘同组’(即：切替)产品的订购
     */
    CustNcodeInfoPO qryCustNcodeInfo(String groupId, String menuid);
    
    /**
     * 根据组ID和ncode查询业务信息
     * @param groupId
     * @param ncode
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-5-26 OR_SD_201503_945_山东_自助终端支撑‘同组’(即：切替)产品的订购
     */
    GroupNcodePO qryGroupNcodeInfo(String groupId,String ncode);
    
    /**
     * <办理同组业务产品>
     * @param custNcodeInfoPO 用户订购业务信息
     * @param groupNcodePO 要办理或变更业务信息
     * @param operType 操作类型 开通  取消
     * @param effectType 生效方式
     * @param curMenuId 菜单id
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-5-28 OR_SD_201503_945_山东_自助终端支撑‘同组’(即：切替)产品的订购
     */
    String commitPrivNcode(CustNcodeInfoPO custNcodeInfoPO,GroupNcodePO groupNcodePO, String operType, String effectType, String curMenuId);
}
