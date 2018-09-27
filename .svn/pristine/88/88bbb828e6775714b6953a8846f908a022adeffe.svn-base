package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class FeeBalanceBean extends BaseBeanSDImpl
{
    
    /**
     * 账户余额查询
     * @param terminalInfoPO  终端机信息
     * @param customer 客户信息
     * @param menuid 当前菜单id
     * @return
     */
    
    // modify begin cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
    @SuppressWarnings("unchecked")
    public Map queryBalance(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        //设置操作员id
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单id
        paramMap.put("menuid", menuid);
        
        //是否模拟销账1：不做模拟销账 0：做模拟销账扣减后的结果
        paramMap.put("isoffset", "1");

        // 获取结果
        ReturnWrap rw = this.getSelfSvcCallSD().queryBalance(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 begin
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_BALANCE))
            {
                // 出参统一转换
                String[] openEbusRtn = {"prepaytype", "contract_balance", "hisbillfee", "currbillfee", "contract_canuse", "contract_thisused"};
                String[] destRtn = {"prepayType", "contractBalance", "hisBillFee", "currBillFee", "contractCanUse", "contractThisUsed"};
                v = CommonUtil.rtnConvert(v, BusinessIdConstants.CLI_QRY_BALANCE, openEbusRtn, destRtn);
            }
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 end
            
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
    // modify end cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
}
