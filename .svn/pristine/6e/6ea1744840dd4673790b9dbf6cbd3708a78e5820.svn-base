/*
 * 文件名：ReceptionBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：业务受理
 * 修改人：g00140516
 * 修改时间：2010-12-21
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.privilege.dao.PrivilegeDaoImpl;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 业务受理，不带有任何附加属性
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-21
 * @see
 * @since
 */
public class ReceptionBean extends BaseBeanImpl
{
    protected PrivilegeDaoImpl privilegeDaoImpl; 
    
    public void setPrivilegeDaoImpl(PrivilegeDaoImpl privilegeDaoImpl)
    {
        this.privilegeDaoImpl = privilegeDaoImpl;
    }

    /**
     * 调用后台接口，查询该业务对应的资费信息
     * 
     * @param customerSimp，用户信息
     * @param termInfo，终端信息
     * @param nCode
     * @param menuID
     * @return
     * @see
     */
    public String qryFeeMessage(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String nCode, String menuID, String operType)
    {
        String message = "";
        
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("nCode", nCode);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        //modify begin lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
        ReturnWrap rw = selfSvcCall.queryFeeMessage(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {          
            CTagSet tagset = (CTagSet)rw.getReturnObject();
            message = tagset.GetValue("desc");            
        }
        //modify end lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
        
        return message;
    }
    
    /**
     * 业务受理
     * 
     * @param customerSimp，用户信息
     * @param termInfo，终端信息
     * @param nCode
     * @param operType，操作类型，开通或者取消
     * @param effectType
     * @param param
     * @param menuID
     * @return
     * @see
     * @Remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
     */
    public ReturnWrap recCommonServ(NserCustomerSimp customer, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String menuID)
    {
        // 组装消息头
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, termInfo.getOperid(), 
        		termInfo.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODUCTREC))
        {            
            if ("ADD".equals(operType))
            {
                operType = "PCOpRec";
            }
            else if ("DEL".equals(operType))
            {
                operType = "PCOpDel";
            }
            else if ("QRY".equals(operType))
            {
                operType = "PCOpQry";
            }
            else if ("ALL".equals(operType))
            {
                operType = "PCOpALL";
            }
            return selfSvcCall.recCommonServNK(msgHeader, nCode, operType, effectType, param);
        }
        else
        {
            return selfSvcCall.recCommonServ(msgHeader, nCode, operType, effectType, param);
        }
        
    }
    
    /**
     * 湖北专用，查询业务受理状态
     * 
     * @param customerSimp，用户信息
     * @param termInfo，终端信息
     * @param nCode
     * @param operType，操作类型，开通或者取消
     * @param effectType
     * @param param
     * @param menuID
     * @return
     * @se
     * @remark modify by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_开机早知道流水线部分EBUS改造
     */
    public ReturnWrap qryRecStatusHub(NserCustomerSimp customer, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String menuID)
    {
        // 组装消息头
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, termInfo.getOperid(), 
        		termInfo.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = selfSvcCall.qryRecStatusHub(msgHeader, nCode, operType);
        
        // 湖北转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
    	{
			// 解析接口返回的数据重新封装
			if (SSReturnCode.SUCCESS == rw.getStatus())
			{
				CRSet crset = new CRSet(2);
    			crset.AddRow();
    			if (rw.getReturnObject() instanceof Vector)
    			{
    				Vector v = (Vector) rw.getReturnObject();
    				CTagSet ctag = (CTagSet) v.get(0);
    				
    				// ncode
    				crset.SetValue(0, 0, ctag.GetValue("BOSSCODE"));
    				
    				// 1，开通；其它，未开通
    				crset.SetValue(0, 1, ctag.GetValue("CURSTATUS"));
    				
    			}
    			if (rw.getReturnObject() instanceof CTagSet)
    			{
    				CTagSet ctag = (CTagSet)rw.getReturnObject();
    				
    				// ncode
    				crset.SetValue(0, 0, ctag.GetValue("BOSSCODE"));
    				
    				// 1，开通；其它，未开通
    				crset.SetValue(0, 1, ctag.GetValue("CURSTATUS"));
    				
    			}
    			rw.setReturnObject(crset);
			}
    	}
        return rw;
    }
    
    /**
     * 根据ncode查询特别提示信息
     * 
     * @param customerSimp
     * @param termInfo
     * @param ncode
     * @param operType
     * @param tipType
     * @param menuID
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public String qryNcodeTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String ncode, 
            String operType, String tipType, String menuID)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuid", menuID);
        
        // 业务唯一标识
        paramMap.put("process_code", "cli_qry_ncodetips");
        
        //认证码
        paramMap.put("verify_code", "");
        
        // 测试标记 可以为空；0表示交易为测试用；1表示正式交易
        paramMap.put("testflag", "");
        
        // 设置客户接触id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // 路由 0：按照region 1：按照手机号码
        paramMap.put("route_type", "1");
        
        // 路由值，按手机号路由传手机号码，按地区路由传region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // 设置操作员id
        paramMap.put("operatorid", termInfo.getOperid());
        
        // 渠道下级单位信息
        paramMap.put("unitid", "");
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        // 手机号码
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // 受理类型
        paramMap.put("rectype", "ChangeProduct");
        
        // ncode
        paramMap.put("ncode", ncode);
        
        // 操作类型
        paramMap.put("operType", operType);
        
        // 提示类型
        paramMap.put("tipType", tipType);
        
        ReturnWrap rw = selfSvcCall.qryNcodeTips(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    return crset.GetValue(0, 3).trim();
                }                
            }
        }
        
        return "";
    }
        
    /**
     * 根据对象编码查询特别提示信息
     * 
     * @param customerSimp
     * @param termInfo
     * @param recType
     * @param objectID
     * @param objectType
     * @param operType
     * @param tipType
     * @param menuID
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public String qryObjectTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String recType, 
            String objectID, String objectType, String operType, String tipType, String menuID)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuid", menuID);
        
        // 业务唯一标识
        paramMap.put("process_code", "cli_qry_objecttips");
        
        //认证码
        paramMap.put("verify_code", "");
        
        // 测试标记 可以为空；0表示交易为测试用；1表示正式交易
        paramMap.put("testflag", "");
        
        // 设置客户接触id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // 路由 0：按照region 1：按照手机号码
        paramMap.put("route_type", "1");
        
        // 路由值，按手机号路由传手机号码，按地区路由传region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // 设置操作员id
        paramMap.put("operatorid", termInfo.getOperid());
        
        // 渠道下级单位信息
        paramMap.put("unitid", "");
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        // 手机号码
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // 受理类型
        paramMap.put("recType", recType);
        
        List<Map<String, String>> prods = new ArrayList<Map<String, String>>();
        
        Map<String, String> prodMap = new HashMap<String, String>();
        
        // 对象编码
        prodMap.put("objectID", objectID);
        
        // 对象类型
        prodMap.put("objectType", objectType);
        
        // 提示类型
        prodMap.put("tipType", tipType);
        
        // 操作类型
        prodMap.put("operType", operType);
        
        prods.add(prodMap);
        
        ReturnWrap rw = selfSvcCall.qryObjectTips(paramMap, prods);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    return crset.GetValue(0, 4).trim();
                }                
            }
        }
        
        return "";
    }
    
    /**
     * 根据对象编码查询特别提示信息
     * 
     * @param customerSimp
     * @param termInfo
     * @param recType
     * @param menuID
     * @param prods 产品列表
     * @return
     * @see 
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public Map<String, String> qryObjectTips(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String recType, 
            String menuID, List<Map<String, String>> prods)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuid", menuID);
        
        // 业务唯一标识
        paramMap.put("process_code", "cli_qry_objecttips");
        
        //认证码
        paramMap.put("verify_code", "");
        
        // 测试标记 可以为空；0表示交易为测试用；1表示正式交易
        paramMap.put("testflag", "");
        
        // 设置客户接触id
        paramMap.put("unicontact", customerSimp.getContactId());
        
        // 路由 0：按照region 1：按照手机号码
        paramMap.put("route_type", "1");
        
        // 路由值，按手机号路由传手机号码，按地区路由传region
        paramMap.put("route_value", customerSimp.getServNumber());
        
        // 设置操作员id
        paramMap.put("operatorid", termInfo.getOperid());
        
        // 渠道下级单位信息
        paramMap.put("unitid", "");
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        // 手机号码
        paramMap.put("telnum", customerSimp.getServNumber());
        
        // 受理类型
        paramMap.put("recType", recType);
        
        ReturnWrap rw = selfSvcCall.qryObjectTips(paramMap, prods);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector vector = (Vector) rw.getReturnObject();            
            if (vector != null && vector.size() > 1)
            {
                CRSet crset = (CRSet) vector.get(1);
                if (crset != null && crset.GetRowCount() > 0)
                {
                    Map<String, String> tipMap = new HashMap<String, String>();
                    
                    // 提示信息
                    String tip = "";
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        tip = crset.GetValue(i, 4);
                        
                        if (tip != null && !"".equals(tip.trim()))
                        {
                            tipMap.put(crset.GetValue(i, 0).trim() + "_" + crset.GetValue(i, 1).trim(), tip.trim());
                        }                        
                    }
                    
                    return tipMap;
                }                
            }
        }
        
        return null;
    }
    
}
