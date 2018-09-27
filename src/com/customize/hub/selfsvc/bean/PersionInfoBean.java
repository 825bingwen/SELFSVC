package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 湖北个人信息查询
 * 
 * @author yKF28472
 * 
 */
public class PersionInfoBean extends BaseBeanHubImpl
{
    @SuppressWarnings("unchecked")
    public Map qryComboInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String month)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置账期为当前月份
        paramMap.put("billcycle", month);
           
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallHub().qryComboInfo(paramMap);
        if (rw != null)
        {
            Vector v = (Vector)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            return map;
        }

        return null;
    }
    
    /**
     * 查询本机品牌资费及已开通功能（产品展示）
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryFavourable(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        MsgHeaderPO msgHeaderPO = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().qryFavourable(msgHeaderPO);
        if (rw != null)
        {
        	CRSet crset = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", crset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            return map;
        }
        
        return null;
    }
}
