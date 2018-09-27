package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 活动受理
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Feb 1, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivitiesRecBean extends BaseBeanHubImpl
{
    /**
     * 查询用户已存在的档次
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param curMenuId 当前菜单
     * @param servnum 手机号码
     * @return
     * @see [类、类#方法、类#成员]
     */
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public Map qrySubsDangcisList(TerminalInfoPO terminalInfoPO,NserCustomerSimp customerSimp,String curMenuId,String servnum )
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        paramMap.put("touchOID", customerSimp.getContactId()); 
        
        //设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //手机号码
        paramMap.put("telnum", servnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().qrySubsDangcisList(paramMap);
        
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
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
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
     * 查询档次描述
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param curMenuId 当前菜单
     * @param dangciId 档次编码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map queryDangciDesc(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,String dangciId)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        //手机号码
        paramMap.put("telnum", customer.getServNumber());
        
        //设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //手机号码
        paramMap.put("privid", dangciId);
        
        ReturnWrap rw = super.getSelfSvcCallHub().queryDangciDesc(paramMap);
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
     * 查询赠品列表
     * <功能详细描述>
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param activityId
     * @param dangciId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map qryPresentList(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String activityId,String dangciId)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 活动编码
        paramMap.put("activityId", activityId);
        
        // 档次编码
        paramMap.put("dangciId", dangciId);
        
        ReturnWrap rw = getSelfSvcCallHub().qryRewardList(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }

        return null;
    } 
    
    /**
     * 活动受理
     * <功能详细描述>
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param activityId
     * @param dangciId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map recRewardCommit(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, 
            String activityId,String dangciId,
            String actreward,String imeiid,
            String onlycheck,int quantity,String accesstype,
            String password,String totalfee,String paytype)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 活动编码
        paramMap.put("actno", activityId);
        
        // 档次编码
        paramMap.put("actlevel", dangciId);
        
        // 活动赠品编号串
        paramMap.put("actrewaed", actreward);
        
        // 手机imeiid号
        paramMap.put("imeiid", imeiid);
        
        // 1是预受理；0是受理
        paramMap.put("onlycheck", onlycheck);
        
        // 赠品数量
        paramMap.put("quantity", quantity+"");
        
        // 受理渠道
        paramMap.put("accesstype", accesstype);
        
        // 密码 只有河北和宁夏不需要校验密码
        paramMap.put("password", password);
        
        // 用户投入的费用金额 预受理时可以不传
        paramMap.put("totalfee", totalfee);
        
        // 支付类型
        paramMap.put("paytype", paytype);
        
        ReturnWrap rw = getSelfSvcCallHub().recRewardCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }

        return null;
    }
    
    /**
     * 查询营销方案费用和用户预存费用
     * <功能详细描述>
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param activityId
     * @param dangciId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map qryPrivFee(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String recoid,String totalFee)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 营销方案受理流水
        paramMap.put("recoid", recoid);
        
        // 用户总费用
        paramMap.put("totalfee", totalFee);
        
        ReturnWrap rw = getSelfSvcCallHub().qryPrivFee(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }

        return null;
    } 
    
    /**
     * 查询活动受理发票打印数据
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单
     * @param servnumber 手机号码
     * @param recoid 受理流水
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map getInvoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, String recoid)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("telnumber", servnumber);// 号码
        paramMap.put("formnums", "");// 要打印发票的日志流水号，如果有多个则以“|”分割
        paramMap.put("recoid", recoid);// 活动受理编号，仅支持单个
        
        
        ReturnWrap rw = this.getSelfSvcCallHub().invoiceDataByActivity(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
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
