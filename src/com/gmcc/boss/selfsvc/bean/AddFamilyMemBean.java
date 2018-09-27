package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.baseService.familymem.model.FamilyMemPO;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 家庭网成员添加bean
 * 
 * @author kWX211786
 * @version [版本号, May 24, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AddFamilyMemBean extends BaseBeanImpl
{
    
    /**
     * 查询家庭网成员
     * @param terminalInfoPO 终端机信息
     * @param customer 主号信息
     * @param curMenuId 菜单
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryFamilyMem(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.queryFamilyMemService(paramMap);
        Map map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
            CRSet cs = new CRSet();
            if (v.size() > 1)
            {
                cs = (CRSet)v.get(1);
            }
            // 设置返回结果
            map.put("returnObj", cs);
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if(rw != null)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
         
            return map;
        }
        
        return null;
    }
    /**
     * 添加家庭网成员
     * 
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> addFamilyMem(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, FamilyMemPO familyMemPO)
    {
        Map<String, String> paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        
        // 设置业务发起号码，主主号
        paramMap.put("servNumber", customer.getServNumber());

        // 要添加成员电话号码
        paramMap.put("memTelnum", familyMemPO.getTelNum());
        
        // 短号
        paramMap.put("shortNum", familyMemPO.getShortNum());
        
        ReturnWrap rw = selfSvcCall.addFamilyMem(paramMap);
        
        Map<String, Object> map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet ts = (CTagSet)rw.getReturnObject();
            
            //设置是否办理成功标志
            map.put("result", ts.GetValue("result"));
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if(rw != null)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        return null;
    }
    
    /**
     * 家庭网取消接口
     * 
     * @param terminfo，终端机信息
     * @param customer，用户信息
     * @param curMenuId，当前菜单信息
     * @return 接口处理结果
     * @see
     * @remark add begin wWX217192 on 2014/06/03 for OR_huawei_201405_875
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap deleteFamilyMen(TerminalInfoPO terminfo, NserCustomerSimp customer, String curMenuId)
    {
    	Map map = new HashMap();
    	map.put("servnum", customer.getServNumber());
    	map.put("menuID", curMenuId);
    	map.put("touchOID", "");
    	map.put("operID", terminfo.getOperid());
    	map.put("termID", terminfo.getTermid());
    	
    	ReturnWrap rw = selfSvcCall.deleteFamilyMem(map);
    	
    	// 调用接口成功，无论接口返回的信息是否为正确的家庭网取消信息，都将其返回至action层进行进一步的处理
    	if(rw != null)
    	{
    		return rw;
    	}
    	
    	return null;
    }
    
    /**
     * <删除家庭网成员>
     * <功能详细描述>
     * @param terminalInfo 终端信息
     * @param customer  用户信息
     * @param curMenuId 菜单ID
     * @param memTelnum 要删除的成员手机号
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2015-2-4 10:45:04 OR_SD_201412_777 自助终端放开家庭网成员删除的功能
     */
    public void delMemByTelNum(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String memTelnum)
    {
        // 组装请求报文头
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // 调用删除家庭网成员接口
        ReturnWrap rw = selfSvcCall.delMemByTelnum(header, memTelnum);
        
        // 调用接口成功
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet ts = (CTagSet)rw.getReturnObject();
            
            // 删除失败
            if(!"1".equals(ts.GetValue("result")))
            {
                throw new ReceptionException("对不起，家庭网成员:"+memTelnum+"删除失败！");
            }
        }
        // 失败
        else
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
}
