package com.customize.cq.selfsvc.bean.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.call.SelfSvcCallCQ;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.SelfSvcCall;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

public class BaseBeanCQImpl {
	
	//用户产品品牌
    public final String PRODUCTBRAND = "ProductBrand";
    
    public static final Map<String,Object> pubMap = new HashMap<String, Object>();

	private SelfSvcCallCQ selfSvcCallCQ;
	
    public SelfSvcCallCQ getSelfSvcCallCQ()
    {
        return selfSvcCallCQ;
    }

    public void setSelfSvcCallCQ(SelfSvcCallCQ selfSvcCallCQ)
    {
        this.selfSvcCallCQ = selfSvcCallCQ;
    }
    
    protected SelfSvcCall selfSvcCall;
	
    public SelfSvcCall getSelfSvcCall()
    {
        return selfSvcCall;
    }

    public void setSelfSvcCall(SelfSvcCall selfSvcCall)
    {
        this.selfSvcCall = selfSvcCall;
    }
    
    /**
     * 判断用户是否开通139邮箱
     * @return
     */
    public String emailService(ReturnWrap rw, List<DictItemPO> itemList)
    {
        CRSet result = (CRSet)rw.getReturnObject();
        if(result != null && result.GetRowCount() > 0)
        {
            String dictItem_spbizid = "";
            String dictItem_spid = "";
            for(int i = 0;i < itemList.size();i++)
            {
                if(itemList.get(i).getDictid().equals("139spbizid"))
                {
                    dictItem_spbizid = itemList.get(i).getDictname();
                }
                if(itemList.get(i).getDictid().equals("139spid"))
                {
                    dictItem_spid = itemList.get(i).getDictname();
                }
            }
            for(int i = 0;i < result.GetRowCount();i++)
            {
                String returned_spbizid = result.GetValue(i, 3);
                String returned_spid = result.GetValue(i, 1);
                if (dictItem_spbizid.indexOf(returned_spbizid) >= 0 && dictItem_spid.equals(returned_spid))
                {
                    return "1";
                }
            }
        }
        else
        {
            return "0";
        }
        return "0";
    }
    
    public void getDictItemByGroup(Map<String, String> inMap)
    {
        String groupID = inMap.get("groupid");
        if(!pubMap.containsKey(groupID))
        {
            ReturnWrap rw = this.getSelfSvcCall().getDictItem(inMap);
            
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
                    dictItem = new DictItemVO(dictID, dictName, groupID);
                    v.add(dictItem);
                }
                pubMap.put(groupID, v);
            }
        }

    }
}
