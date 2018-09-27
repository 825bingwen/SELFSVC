package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.customize.nx.selfsvc.prodInstall.model.IdCardPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


/**
 * 
 * 宁夏在线开户
 * <功能详细描述>
 * 
 * @author  zKF66389
 * @version  [版本号, Jul 25, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class ProdInstallNxBean extends BaseBeanNXImpl
{
    
    /**
     * 开户时证件号码校验
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param certtype 证件类型
     * @param certid 证件号码
     * @return
     * @see [类、类#方法、类#成员]
     */
	public Map chkCertSubs(TerminalInfoPO termInfo, String curMenuId, String certtype, String certid)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuID", curMenuId);

        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 证件类型
        paramMap.put("certtype", certtype);
        
        // 证件号码
        paramMap.put("certid", certid);

        // 当前入网的数量，默认传1
        paramMap.put("choicetelnum", "1");
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().chkCertSubs(paramMap);
        
        Map map = new HashMap();
        
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            String  ifValid = v.GetValue("ifValid");

            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置 校验通过 1；不通过，0
            map.put("ifValid",ifValid);
            
            return map;
        }
        else if (rw != null)
        {
        	CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
             
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        // 返回
        return null;
    }
	
    /**
     * 查询手机号码列表
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param orgid 机构
     * @param sele_rule 查询类型 2：按前缀查询 3：按后缀查询 4:随机
     * @param tel_prefix 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
     * @param tel_suffix 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
     
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map qryNumberByProdid(TerminalInfoPO termInfo, String curMenuId, String orgid, String sele_rule, String tel_prefix, String tel_suffix)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 机构
        paramMap.put("orgid", orgid);
        
        // 查询类型 2：按前缀查询 3：按后缀查询
        paramMap.put("sele_rule", sele_rule);
        
        // 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
        paramMap.put("tel_prefix", tel_prefix);
        
        // 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
        paramMap.put("tel_suffix", tel_suffix);
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().qryNumberByProdid(paramMap);
        
        Map map = new HashMap();
        
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            String returnMsg = rw.getReturnMsg();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());

            return map;
        }
        
        // 返回
        return null;
    }
    
    /**
     * 号码资源暂选接口
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param telnum 手机号码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map telNumTmpPick(TerminalInfoPO termInfo, String curMenuId, String telnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 公共参数
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 参数
        
        // 手机号码
        paramMap.put("telnum", telnum);
        
        // 资源类型 rsclTgsm
        paramMap.put("restype", "rsclTgsm");
        
        // 操作标志 0为锁定，1为解锁
        paramMap.put("operflag", "0");
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().telNumTmpPick(paramMap);
        Map map = new HashMap();
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 返回
            return map; 
        }
        
        // 返回
        return null;
    }
    
    /**
     * 校验空白卡资源是否可用
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankno 空白卡序列号
     * @param prodid 产品编码
     * @param orgid 组织机构
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map chkBlankNo(TerminalInfoPO termInfo, String curMenuId, String blankno, String orgid)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 空白卡序列号
        paramMap.put("blankno", blankno);
        
        // 组织单位
        paramMap.put("orgid", orgid);
        
        // 资源大类
        paramMap.put("res_kind_id", "rsclW");
        
        // 资源类型 传：""
        paramMap.put("res_type_id", "");
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().chkBlankNo(paramMap);
        Map map = new HashMap();
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 返回
            return map; 
        }
        
        // 返回
        return null;
    }
    
    /**
     * 空白卡资源暂选
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankno 空白卡序列号
     * @param telnum 手机号码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map blankCardTmpPick(TerminalInfoPO termInfo, String curMenuId, String blankno, String telnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 空白卡序列号
        paramMap.put("blankno", blankno);
        
        // 服务号码
        paramMap.put("telnum", telnum);
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().blankCardTmpPick(paramMap);
        
        Map map = new HashMap();
        
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 返回
            return map; 
        }
        
        // 返回
        return null;
    }
    
    /**
     * 号卡校验
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param telnum 手机号码
     * @param iccid ICCID
     * @param porductid 产品编码
     * @param orgid 单位
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map chkTelSimcard(TerminalInfoPO termInfo, String curMenuId, String telnum, String iccid, String porductid, String orgid)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 公共参数
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 参数
        
        // 手机号码
        paramMap.put("telnum", telnum);
        
        // SIM卡ICCID
        paramMap.put("iccid", iccid);
        
        // 产品编码
        paramMap.put("porductid", porductid);
        
        // 业务类型（传Install）
        paramMap.put("rectype", "Install");
        
        // 是否已经锁定  传1
        paramMap.put("islocked", "1");
        
        // 是否是空白卡 传0
        paramMap.put("isblankcard", "0");
        
        // 代理商ID orgid
        paramMap.put("agentid", orgid);
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().chkTelSimcard(paramMap);
        Map map = new HashMap();
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 返回
            return map; 
        }
        
        // 返回
        return null;
    }
    
    /**
     * 计算费用
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param telnum 手机号码
     * @param mainprodid 主体产品编码
     * @param prodtempletid 产品模板编码
     * @param simnum ICCID SIM卡号
     * @param blankcardno 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map reckonRecFee(TerminalInfoPO termInfo, String curMenuId, String telnum, String mainprodid, String prodtempletid, String simnum, String blankcardno)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 公共参数
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
        
        // 参数
        
        // 手机号码
        paramMap.put("telnum", telnum);
        
        // 主体产品编码
        paramMap.put("mainprodid", mainprodid);
        
        // 产品模板编码
        paramMap.put("prodtempletid", prodtempletid);
        
        // SIM卡号
        paramMap.put("simnum", simnum);
        
        // 空白卡卡号
        paramMap.put("blankcardno", blankcardno);

        // 是否返回 减免费用 传1
        paramMap.put("retdiscountfee", "1");

        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().reckonRecFee(paramMap);
        Map map = new HashMap();

        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 返回
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        // 返回
        return null;
    }
    
    /**
     * 开户
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param idCardPO 身份证信息PO
     * @param tpltTempletPO 主体产品PO
     * @param telnum 手机号码
     * @param totalfee 用户缴纳费用
     * @param password 密码
     * @param imsi imsi
     * @param simnum 卡号
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map terminalInstall(TerminalInfoPO termInfo, String curMenuId, IdCardPO idCardPO, TpltTempletPO tpltTempletPO, 
    		String telnum, String totalfee, String password, String imsi, String simnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
 
        // 公共参数
        
        // 菜单
        paramMap.put("menuID", curMenuId);
        
        // 操作员ID
        paramMap.put("operID", termInfo.getOperid());
        
        // 终端ID
        paramMap.put("termID", termInfo.getTermid());
        
        // 地区
        paramMap.put("region", termInfo.getRegion());
        
        // 统一接触流水
        paramMap.put("touchOID", "");
    
        // 参数
        
        // 渠道bsacAtsv
        paramMap.put("accesstype", "bsacAtsv");
        
        // 手机号码
        paramMap.put("installtelnum", telnum);
        
        // 卡号
        paramMap.put("simnum", simnum);

        // imsi
        paramMap.put("imsi", imsi);
        
        // 主体产品编码
        paramMap.put("mainprodid", tpltTempletPO.getMainProdId());
        
        // 产品模板编码
        paramMap.put("prodtempletid", tpltTempletPO.getTempletId());
        
        // 选号费(可选)，这里0即可
        paramMap.put("telprice", "0");
        
        // 业务类型（传Install）
        paramMap.put("rectype", "Install");
        
        // 证件类型
        paramMap.put("certtype", "IdCard");
        
        // 证件号
        paramMap.put("certid", idCardPO.getIdCardNo());
 
        // 客户名称
        paramMap.put("custname", idCardPO.getIdCardName());
        
        // 用户缴纳费用
        paramMap.put("totalfee", totalfee);
        
        // 密码
        paramMap.put("password", password);
        
        // 客户联系电话
        paramMap.put("linkphone", "");
        
        // 联系地址
        paramMap.put("linkaddr", idCardPO.getIdCardAddr());
        
        // 默认传0
        paramMap.put("existdetail", "0");
        
        // 客户地址
        paramMap.put("custaddr", idCardPO.getIdCardAddr());
        
        // 联系人姓名
        paramMap.put("linkname", idCardPO.getIdCardName());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallNX().terminalInstall(paramMap);
        
        Map map = new HashMap();
        
        // 处理返回结果
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 返回
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 返回
            return map;
        }
        
        // 返回
        return null;
    }
	

}
