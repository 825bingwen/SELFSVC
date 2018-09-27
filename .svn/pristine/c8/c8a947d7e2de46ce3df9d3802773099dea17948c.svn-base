package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 受理历史查询
 * @author xkf29026
 *
 */
public class ServiceHistoryBean extends BaseBeanImpl
{
    /**
     * 受理历史查询
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryAllServiceHistory(String startDate,String endDate,TerminalInfoPO terminalInfoPO,NserCustomerSimp customer, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //设置开始日期
        paramMap.put("startDate", startDate);
        
        //设置结束日期
        paramMap.put("endDate", endDate);
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("servnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.qryAllServiceHistory(paramMap);

        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String title = "";
            
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
            {
                title = "受理时间=受理渠道=业务名称";
            }
            else
            {
                title = "操作时间=受理地点=业务名称";
            }
            
            Vector v = new Vector();            
            v.add(title);
            v.add(rw.getReturnObject());
            
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
            String returnMsg = rw.getReturnMsg();            
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            return map;
        }
       
    }
}
