/*
 * 文 件 名:  QryUserScoreInfoByTypeBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  根据类型查询用户积分信息
 * 修 改 人:  zWX176560
 * 修改时间:  Aug 23, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.nx.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 根据类型查询用户积分 <功能详细描述>
 * 
 * @author zWX176560
 * @version R003C13L09n01 OR_NX_201308_595_宁夏_新开发积分查询接口
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QryUserScoreInfoByTypeBean extends BaseBeanNXImpl
{
    /**
     * 积分查询
     * 
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return map
     */
    public Map qryUserScoreInfoByType(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        // 封装参数
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 根据类型查询积分
        ReturnWrap rw = this.getSelfSvcCallNX().qryUserScoreInfoByType(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 获取查询积分信息
            CRSet crset = (CRSet)rw.getReturnObject();
            
            // 设置返回积分信息
            Map<String, String> scoreMap = new HashMap<String, String>();
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                scoreMap.put(crset.GetValue(i, 0), crset.GetValue(i, 1));
            }
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", scoreMap);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
}
