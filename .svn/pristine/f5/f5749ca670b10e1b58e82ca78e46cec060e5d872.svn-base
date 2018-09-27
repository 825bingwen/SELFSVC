package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 宽带交费
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Sep 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BroadBandPayBean extends BaseBeanHubImpl
{
    /**
     * 产品受理 <功能详细描述>
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param menuID 菜单
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697	2014-9-22 14:55:43 OR_huawei_201409_422_湖北_自助终端接入EBUS_宽带交费
     */
    public ReturnWrap wbandpay(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String menuID, String ncode, String fee)
    {
    	
    	//封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(menuID, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(), 
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
    	
    	//封装消息头
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置当前菜单
        paramMap.put("menuid", menuID);
        
        // 业务唯一标识
        paramMap.put("process_code", "cli_busi_wbandrec");
        
        //认证码
        paramMap.put("verify_code", "");
        
        // 测试标记 可以为空；0表示交易为测试用；1表示正式交易
        paramMap.put("testflag", "");
        
        // 设置客户接触id
        paramMap.put("unicontact", customer.getContactId());
        
        // 路由 0：按照region 1：按照手机号码
        paramMap.put("route_type", "1");
        
        // 路由值，按手机号路由传手机号码，按地区路由传region
        paramMap.put("route_value", customer.getServNumber());
        
        // 设置操作员id
        paramMap.put("operatorid", terminalInfoPO.getOperid());
        
        // 渠道下级单位信息
        paramMap.put("unitid", "");
        
        // 设置终端机id
        paramMap.put("termid", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // NCODE编码
        paramMap.put("ncode", ncode);
        
        // 受理渠道
        paramMap.put("accesstype", "bsacAtsv");
        
        // 操作类型
        paramMap.put("oprtype", "PCOpRec");
        
        // 费用(分)
        paramMap.put("contrastfee", fee);

        ReturnWrap rw = this.getSelfSvcCallHub().wbankpay(paramMap, msgHead);
                
        return rw;
    }
}
