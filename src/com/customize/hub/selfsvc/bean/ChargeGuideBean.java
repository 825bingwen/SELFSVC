package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class ChargeGuideBean extends BaseBeanHubImpl{
	
	/**
	 * 湖北资费问题查询
	 */
	 public Map qryChargeGuide(TerminalInfoPO terminalInfoPO,String curMenuId,String streamNo,
			 String questionCode,String answerCode){
		 Map paramMap = new HashMap();
   	  // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid()); 
        // 设置终端机id
       
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);   
        // 设置参数
        paramMap.put("region", terminalInfoPO.getRegion());
        paramMap.put("streamno",streamNo);
        paramMap.put("questioncode",questionCode);
        paramMap.put("answercode",answerCode);
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryChargeGuide(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
       	 	CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            // 设置返回结果
            map.put("returnObj", v);    
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
	 }
}
