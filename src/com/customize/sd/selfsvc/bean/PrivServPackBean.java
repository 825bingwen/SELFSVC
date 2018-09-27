package com.customize.sd.selfsvc.bean;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 特惠业务包
 * @author hWX5316476
 *
 */
public class PrivServPackBean extends BaseBeanSDImpl
{
    /**
     * 特惠业务受理
     * @param termInfo 终端机信息
     * @param customer 客户信息
     * @param curMenuId 菜单id
     * @param nCode nCode
     * @return
     * @remark create by hWX5316476 2014-12-11 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
    public boolean privServPackRec(TerminalInfoPO termInfo, NserCustomerSimp customer,
            String curMenuId, PrivServPackPO privServPackPO)
    {
        // 组装报文头信息
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), "1", customer.getServNumber());
        
        // 生效时间
        String effDate = privServPackPO.getEffDate();
        
        // 当没有设置生效时间的时候，生效时间默认
        if(StringUtils.isEmpty(effDate))
        {
            // 生效方式 （0默认  2立即  3下周期 4指定时间生效）
            privServPackPO.setEffType("0");
        }
        else 
        {
            // 生效方式 （0默认  2立即  3下周期 4指定时间生效）
            privServPackPO.setEffType("4");
        }
        
        // 调用接口
        ReturnWrap rw = getSelfSvcCallSD().privServPackRec(header,privServPackPO);
        
        // 办理成功
        if(rw.getStatus()  == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return true;
    }
}
