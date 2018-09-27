/*
 * 文 件 名:  PrivilegeDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  hWX5316476
 * 修改时间:  Apr 29, 2015
 * 跟踪单号:  OR_SD_201503_945_山东_自助终端支撑‘同组’(即：切替)产品的订购
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.privilege.dao;

import java.util.List;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.privilege.model.GroupNcodePO;

/**
 * 业务查询dao
 * 
 * @author  hWX5316476
 * @version  [版本号, Apr 29, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrivilegeDaoImpl extends BaseDaoImpl
{
    /**
     * 根据组ID查询业务编码Ncode
     * <功能详细描述>
     * @param groupId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<GroupNcodePO> qryGroupNcodeInfo(GroupNcodePO groupNcodePO)
    {
        return (List<GroupNcodePO>)sqlMapClientTemplate.queryForList("privilege.qryGroupNcodeInfo", groupNcodePO);
    }
    
    /**
     * 根据ncode查询产品、优惠信息
     * <功能详细描述>
     * @param ncodePo
     * @return
     * @see [类、类#方法、类#成员]
     * @Remark create by lWX431760 2017-07-19 OR_huawei_201706_780_【山东移动接口迁移专题】-自助终端新业务办理需求
     */
    public NcodePO qryNcodeMessage(NcodePO ncodePo)
    {
        // 使用临时变量,防止赋值对原数据产生影响
        NcodePO tmpPo = null;
        
        try
        {
            tmpPo = (NcodePO)ncodePo.clone();
            
            // 查询时，操作类型固定为受理
            if("PCOpQry".equals(tmpPo.getOptype()))
            {
                tmpPo.setOptype("PCOpRec");
            }
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        
        return (NcodePO) sqlMapClientTemplate.queryForObject("ncode.getObjType", tmpPo);
    }
}
