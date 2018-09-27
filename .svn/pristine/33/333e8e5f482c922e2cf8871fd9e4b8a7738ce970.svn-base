package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 预约选号
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 19, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChooseTelBean extends BaseBeanImpl
{
    /**
     * 查询号码
     * <一句话功能简述>
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息 
     * @param curMenuId 当前菜单
     * @param county_id 地区，如SD.LA
     * @param sele_rule 查询类型 2：按前缀查询 3：按后缀查询
     * @param tel_prefix 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
     * @param tel_suffix 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map qryChooseTel(TerminalInfoPO terminalInfoPO,String curMenuId,String county_id,String sele_rule,
    		String tel_prefix,String tel_suffix, String region)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 地区
        paramMap.put("county_id", county_id);
        
        // sele_rule 查询类型 2：按前缀查询 3：按后缀查询
        paramMap.put("sele_rule", sele_rule);
        
        // tel_prefix 号码前缀
        paramMap.put("tel_prefix", tel_prefix);
        
        // tel_suffix 号码后缀
        paramMap.put("tel_suffix", tel_suffix);
        
        paramMap.put("region", region);
        
        ReturnWrap rw = selfSvcCall.qryChooseTel(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 预约号码
     * <一句话功能简述>
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param curMenuId 当前菜单
     * @param telnum 被预定号码
     * @param region 地市（查询时返回的信息）
     * @param org_id 单位（查询时返回的信息）
     * @param certtype 默认：IdCard
     * @param certid 身份证号
     * @param name 用户姓名
     * @param contacttel 联系号码，可为“”
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map chooseTel(TerminalInfoPO terminalInfoPO,String curMenuId,String telnum,String region,String org_id,String certtype,String certid,String name,String contacttel)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //被预定号码
        paramMap.put("telnum", telnum);
        
        //地市（查询时返回的信息）
        paramMap.put("region", region);
        
        //单位（查询时返回的信息）
        paramMap.put("org_id", org_id);
        
        //默认：IdCard
        paramMap.put("certtype", certtype);
        
        //身份证号
        paramMap.put("certid", certid);
        
        //用户姓名
        paramMap.put("name", name);
        
        //联系号码，可为“”
        paramMap.put("contacttel", contacttel);
        
        //预约时长 单位天
        String validday = (String)PublicCache.getInstance().getCachedData("SH_VALIDDAY");
        if (validday == null)
        {
            paramMap.put("validday", "");
        }
        else
        {
            paramMap.put("validday", validday);
        }
        
        
        ReturnWrap rw = selfSvcCall.chooseTel(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagset = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", tagset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    
    /**
     * 向用户发送订购凭证短信
     * 
     * @param servNumber，手机号码
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param curMenuId，当前菜单
     * @return
     * @see
     */
    public boolean sendMsg(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
}
