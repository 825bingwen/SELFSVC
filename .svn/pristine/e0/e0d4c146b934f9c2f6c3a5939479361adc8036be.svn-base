package com.customize.hub.selfsvc.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.call.SelfSvcCallHub;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.SelfSvcCall;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.common.service.BaseServiceImpl;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * <湖北基础的Service调用类>
 * <功能详细描述>
 * 
 * @author  wWX217192
 * @version  [版本号, Apr 22, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by wWX219697 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
 */
public class BaseServiceHubImpl extends BaseServiceImpl
{
	//用户产品品牌
    public final String PRODUCTBRAND = "ProductBrand";

	private SelfSvcCallHub selfSvcCallHub;
	
	private SelfSvcCall selfSvcCall;
	
	public static final Map<String,Object> pubMap = new HashMap<String, Object>();
	
    /** 调用crm获取字典组
     * <功能详细描述>
     * @param inMap void
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 20, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1>
     */
    public void getDictItemByGroupCRM(Map<String, String> inMap){
        String groupID = inMap.get("groupid");
        if(!pubMap.containsKey(groupID))
        {
            ReturnWrap rw = selfSvcCallHub.getDictItemByGroup(inMap);
            
            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
            {
                CRSet crset = (CRSet)rw.getReturnObject();
                Vector<DictItemVO> v = new Vector<DictItemVO>();
                int num = crset.GetRowCount();
                DictItemVO dictItem = null;
                for (int i = 0; i < num; i++)
                {
                    String dictID = crset.GetValue(i, 0);
                    String dictName = crset.GetValue(i, 1);
                    
                    String description = crset.GetValue(i, 2);
                    dictItem = new DictItemVO(dictID, dictName, groupID);
                    dictItem.setDescription(description);
                    
                    v.add(dictItem);
                }
                pubMap.put(groupID, v);
            }
        }
    }
    
    /** 根据groupid获取crm字典组接口(封装Map对象)
     * <功能详细描述>
     * @param groupid 
     * @param curMenuId 当前菜单
     * @param servNumber 服务号码
     * @param termInfo 终端信息
     * @return List<DictItemVO>
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 20, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1>
     */
    public List<DictItemVO> getDictItems(String groupid, String curMenuId)
    {
        if (!pubMap.containsKey(groupid))
        {
            // 需要去CRM系统里获取字典表数据
            Map<String, String> inMap = new HashMap<String, String>();
            inMap.put("groupid", groupid);
            
            inMap.put("curMenuId", curMenuId);
            
            // 设置客户手机号
            inMap.put("telnumber", getCustomer().getServNumber());
            
            // 设置操作员id
            inMap.put("curOper", getTermInfo().getOperid());
            
            // 设置终端机id
            inMap.put("atsvNum", getTermInfo().getTermid());
            
            inMap.put("touchoid", "");
            
            getDictItemByGroupCRM(inMap);
        }
        
        Vector<DictItemVO> v = (Vector<DictItemVO>)pubMap.get(groupid);
        List<DictItemVO> dictItemList = new ArrayList<DictItemVO>();
        for (DictItemVO dictItem : v)
        {
            dictItemList.add(dictItem);
        }
        
        return dictItemList;
    }
    
	
	
	public SelfSvcCallHub getSelfSvcCallHub() {
		return selfSvcCallHub;
	}

	public void setSelfSvcCallHub(SelfSvcCallHub selfSvcCallHub) {
		this.selfSvcCallHub = selfSvcCallHub;
	}
	
    public SelfSvcCall getSelfSvcCall() {
		return selfSvcCall;
	}

	public void setSelfSvcCall(SelfSvcCall selfSvcCall) {
		this.selfSvcCall = selfSvcCall;
	}
}
