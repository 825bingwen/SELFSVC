package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 身份证入网预约
 * <功能详细描述>
 * 
 * @author  cKF48754
 * @version  [版本号, Jun 24, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class IdCardBookBean extends BaseBeanHubImpl
{
    
    /**
     * 身份证入网预约
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param curMenuId 当前菜单
     * @param idCardContent 0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map idCardBook(TerminalInfoPO terminalInfoPO,String curMenuId,String idCardContent)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //区域
        paramMap.put("region", terminalInfoPO.getRegion());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        String[] idCards = idCardContent.substring(1, idCardContent.length()).split("~");
        
        // 姓名
        paramMap.put("name", idCards[0]);
        
        // 性别
        paramMap.put("sex", idCards[1]);
        
        // 民族
        paramMap.put("nation", idCards[2]);
        
        // 出生
        paramMap.put("birthday", idCards[3]);
        
        // 地址
        paramMap.put("address", idCards[4]);
        
        // 公民身份号码
        paramMap.put("idCard", idCards[5]);
        
        // 签发机关
        paramMap.put("idiograph", idCards[6]);
        
        // 有效期起始日期
        paramMap.put("startDate", CommonUtil.formatDate(idCards[7],"yyyyMMdd","yyyy-MM-dd"));
        
        // 有效期截止日期
        paramMap.put("endDate", CommonUtil.formatDate(idCards[8],"yyyyMMdd","yyyy-MM-dd"));
        
        // 最新住址
        paramMap.put("newAddress", idCards[9]);
        
        ReturnWrap rw = super.getSelfSvcCallHub().idCardBook(paramMap);
        
        Map map = new HashMap();
        map.put("rtStatus", SSReturnCode.ERROR);
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // end zKF66389 2015-09-15 9月份findbugs修改
        {
            String returnMsg = rw.getReturnMsg();
            map.put("rtStatus", SSReturnCode.SUCCESS);
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        
    }
    
}
