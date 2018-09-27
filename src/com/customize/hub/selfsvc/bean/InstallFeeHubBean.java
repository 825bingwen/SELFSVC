package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class InstallFeeHubBean extends BaseBeanHubImpl
{

	//add by xkf57421 for OR_HUB_201202_1192 begin
	@SuppressWarnings("unchecked")
	public Map checkTelnumSim(TerminalInfoPO terminalInfo, String telnum,
			String simiccid, String imsi, String mainprodid, String curMenuId)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
        paramMap.put("operid", terminalInfo.getOperid());
        paramMap.put("termid", terminalInfo.getTermid());
        
        paramMap.put("telnum", telnum);
        paramMap.put("iccid", simiccid);
        paramMap.put("imsi", imsi);
        paramMap.put("porductid", mainprodid);
        paramMap.put("rtRegion", terminalInfo.getRegion());
        
        paramMap.put("touchoid", "");
        paramMap.put("menuid", curMenuId);
		
        
        ReturnWrap rw = this.getSelfSvcCallHub().validateTelSim(paramMap);
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	map.put("returnMsg", rw.getReturnMsg());
        	return map;
        }
        else
        {
            map.put("returnMsg", "账户信息查询失败");
            return map;
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public Map qryFeeItemInfo(TerminalInfoPO terminalInfo, String telnum,
			String mainprodid, String prodtempletid, String simiccid,
			String blankcardno, String curMenuId)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
        paramMap.put("operid", terminalInfo.getOperid());
        paramMap.put("termid", terminalInfo.getTermid());
        
        paramMap.put("telnum", telnum);
        paramMap.put("mainprodid", mainprodid);
        paramMap.put("prodtempletid", prodtempletid);
        paramMap.put("simnum", simiccid);
        paramMap.put("blankcardno", blankcardno);
        
        paramMap.put("rtRegion", terminalInfo.getRegion());
        
        paramMap.put("touchoid", "");
        paramMap.put("menuid", curMenuId);
		
        
        ReturnWrap rw = this.getSelfSvcCallHub().queryFeeItemList(paramMap);
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	//--------ebus接口改造---------------
			CRSet v = new CRSet();
			if(rw.getReturnObject() instanceof Vector)
			{
				Vector vc=(Vector)rw.getReturnObject();
				if(vc!=null && vc.size()>1)
				{
					v = (CRSet)vc.get(1);
				}
			}
			if(rw.getReturnObject() instanceof CRSet)
			{
				v =(CRSet)rw.getReturnObject();
			}
        	//----------------------------
			
//        	CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	map.put("returnMsg", rw.getReturnMsg());
        	return map;
        }
        else
        {
            map.put("returnMsg", "费用信息列表查询失败");
            return map;
        }
	}

	@SuppressWarnings("unchecked")
	public Map querySaleCond(TerminalInfoPO terminalInfo, String telnum,String curMenuId)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put("telnum", telnum);
		
        paramMap.put("operid", terminalInfo.getOperid());
        paramMap.put("termid", terminalInfo.getTermid());
        paramMap.put("rtRegion", terminalInfo.getRegion());
        paramMap.put("touchoid", "");
        paramMap.put("menuid", curMenuId);
		
        
        ReturnWrap rw = this.getSelfSvcCallHub().querySaleCond(paramMap);
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	map.put("returnMsg", rw.getReturnMsg());
        	return map;
        }
        else
        {
            map.put("returnMsg", "费用信息列表查询失败");
            return map;
        }
	}

	
	@SuppressWarnings("unchecked")
	public Map prodInstallCommit(TerminalInfoPO termInfo,
			Map<String, String> paramMap, String curMenuId)
	{
		paramMap.put("rtRegion", termInfo.getRegion());
		
		paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("touchoid", "");
        paramMap.put("menuid", curMenuId);
        
        ReturnWrap rw = this.getSelfSvcCallHub().commitInstallProd(paramMap);
        
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	map.put("returnMsg", rw.getReturnMsg());
        	return map;
        }
        else
        {
            map.put("returnMsg", "开户缴费接口调用失败");
            return map;
        }
	}
	//add by xkf57421 for OR_HUB_201202_1192 end
	//add by xkf57421 for 20130702 end
	public Map getInvoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, String formnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        paramMap.put("formnum", formnum);
        paramMap.put("touchOID", "");
        
        ReturnWrap rw = this.getSelfSvcCallHub().getInvoiceData(paramMap);
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
	
	/**
     * 向用户发送随机密码短信
     * 
     * @param servNumber，手机号码
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param curMenuId，当前菜单
     * @return
     * @see
     */
    public boolean sendRandomPwd(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallHub().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
	//add by lWX162765 for 20130702 end
}
