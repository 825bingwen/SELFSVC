/*
 * 文 件 名:  NonlocalChargeBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  hWX5316476
 * 修改时间:  Mar 23, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.bean;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 跨省异地交费bean
 * 
 * @author  hWX5316476
 * @version  [版本号, Mar 23, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NonlocalChargeBean extends BaseBeanImpl
{
    /**
     * 查询客户应缴总金额信息
     * <功能详细描述>
     * @param curMenuId
     * @param termInfo
     * @param servnumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String,String> qryPayAmount(String curMenuId,TerminalInfoPO termInfo,String servnumber)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);

        // 调用接口查询客户应缴总金额
        ReturnWrap rw = selfSvcCall.qryPayAmount(msgHeader, termInfo.getOrgid());
        
        // 调用接口失败返回错误信息
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            String retMsg = rw.getReturnMsg();
            if(StringUtils.isEmpty(retMsg))
            {
                retMsg = "对不起，查询客户账户信息失败！";
            }
            throw new ReceptionException(retMsg);
        }
        
        Map<String, String> outParmMap = new HashMap<String, String>();
        
        CTagSet ctagSet = (CTagSet)rw.getReturnObject();
        
        if(StringUtils.isEmpty(ctagSet.GetValue("ProvinceCode")))
        {
            throw new ReceptionException("对不起，没有查询到对应的账户信息！");
        }
        
        // 手机号码归属省份编码
        String provinceCode = ctagSet.GetValue("ProvinceCode").substring(0,3);
        
        // 当前省份编码
        String currProvinceCode = CommonUtil.getParamValue(Constants.SH_CURRPROVINCE_CODE);
        
        if(currProvinceCode.equals(provinceCode))
        {
            throw new ReceptionException("对不起，该菜单属于跨省异地交费，您的手机号码属于本省，请到其它对应菜单进行交费。");
        }
        
        // 省份编码
        outParmMap.put("ProvinceCode", provinceCode);
           
        // 姓名
        outParmMap.put("CustName", ctagSet.GetValue("CustName"));
            
        // 应交总金额
        outParmMap.put("PayAmount", CommonUtil.liToFen(ctagSet.GetValue("PayAmount"), BigDecimal.ROUND_UP));
        
        // 预存总余额
        outParmMap.put("Balance", CommonUtil.liToFen(ctagSet.GetValue("Balance"), BigDecimal.ROUND_DOWN));

        return outParmMap;
    }
    
    /**
     * 异地缴费
     * <功能详细描述>
     * @param curMenuId
     * @param termInfo
     * @param servnumber
     * @param seq
     * @param actualPayAmount 实际缴费金额（分）
     * @see [类、类#方法、类#成员]
     */
    public void nonlocalCharge(String curMenuId,TerminalInfoPO termInfo,String servnumber, String seq, String actualPayAmount)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);

        // 调用接口查询客户应缴总金额
        ReturnWrap rw = selfSvcCall.nonlocalCharge(msgHeader, seq, CommonUtil.fenToLi(actualPayAmount), termInfo.getOrgid());
       
        // 调用接口失败返回错误信息
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            String retMsg = rw.getReturnMsg();
            if(StringUtils.isEmpty(retMsg))
            {
                retMsg = "对不起，跨省异地缴费操作失败！";
            }
            throw new ReceptionException(retMsg);
        }
    }
}
