package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 本机品牌资费及已开通功能
 * @author xkf29026
 *
 */
public class FavourableBean extends BaseBeanImpl
{
    /**
     * 查询本机品牌资费及已开通功能
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @param queryType 查询类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryFavourable(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String queryType)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //设置查询类型
        paramMap.put("queryType", queryType);
        
        ReturnWrap rw = selfSvcCall.qryFavourable(paramMap);
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            if ("2".equals(queryType))// 1：品牌资费 2：已开通服务、优惠
            {
                String title = "已开通业务=资费描述=启用日期=结束日期";
                Vector vector = new Vector();
                vector.add(title);
                vector.add(rw.getReturnObject());
                rw.setReturnObject(vector);
            }
            
            List v = (List)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            return map;
            
        }
    }
}
