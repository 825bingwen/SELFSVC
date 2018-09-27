/*
 * 文件名：ChargeHistoryBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：手机支付主账户充值
 * 修改人：g00140516
 * 修改时间：2010-12-24
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 手机支付主账户充值
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-24
 * @see
 * @since
 */
public class CmpayBean extends BaseBeanImpl
{
    /**
     * 手机支付主账户信息查询
     * 
     * @param termInfo，终端机信息
     * @param servNumber，服务号码
     * @param securePwd，安全密码
     * @param actionTime，时间
     * @param menuID，菜单ID
     * @return
     * @see
     */
    public Vector qryCmpayAccount(TerminalInfoPO termInfo, String servNumber, String securePwd, String actionTime,
            String menuID)
    {
        Vector v = new Vector();
        
        Map map = new HashMap();
        map.put("securePwd", securePwd);
        map.put("servNumber", servNumber);
        map.put("orgID", termInfo.getOrgid());
        map.put("actionTime", actionTime);
        map.put("menuID", menuID);
        map.put("contactID", "");
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.selfSvcCall.qryCmpayAccount(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet)rw.getReturnObject();
            
            v.add(cout.GetValue("boss_seq"));
            v.add(cout.GetValue("telnum"));
            v.add(cout.GetValue("card_type"));
            v.add(cout.GetValue("card_num"));
            v.add(cout.GetValue("user_name"));
            v.add(cout.GetValue("other_name"));
            v.add(cout.GetValue("true_name"));
            v.add(cout.GetValue("sacc_status"));
            v.add(cout.GetValue("acc_mess"));
            v.add(cout.GetValue("sacc_mess"));
            v.add(cout.GetValue("cash"));
            v.add(cout.GetValue("card"));
            v.add(cout.GetValue("wait"));
        }
        
        return v;
    }
    
    /**
     * 充值
     * 
     * @param securePwd，安全密码
     * @param servNumber，服务号码
     * @param termInfo，终端机信息
     * @param actionTime，时间
     * @param money，充值金额
     * @param menuID，菜单ID
     * @return
     * @see
     */
    public Vector recMainFee(String securePwd, String servNumber, TerminalInfoPO termInfo, String actionTime,
            String money, String menuID)
    {
        Vector result = new Vector();
        
        Map map = new HashMap();
        map.put("securePwd", securePwd);
        map.put("servNumber", servNumber);
        map.put("orgID", termInfo.getOrgid());
        map.put("actionTime", actionTime);
        map.put("money", money);
        map.put("menuID", menuID);
        map.put("contactID", "");
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.selfSvcCall.recMainFee(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet)rw.getReturnObject();
            
            result.add(cout.GetValue("boss_seq"));
            result.add(cout.GetValue("mpay_seq"));
            result.add(cout.GetValue("cash"));
        }
        
        return result;
    }
}
