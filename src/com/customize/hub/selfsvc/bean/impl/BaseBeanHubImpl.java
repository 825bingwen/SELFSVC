package com.customize.hub.selfsvc.bean.impl;

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
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

public class BaseBeanHubImpl {
    
    //用户产品品牌
    public final String PRODUCTBRAND = "ProductBrand";

	private SelfSvcCallHub selfSvcCallHub;
	
	// add begin hWX5316476 2014-05-14 OR_huawei_201405_234  自助终端接入EBUS一阶段_缴费
	private SelfSvcCall selfSvcCall;
	// add end hWX5316476 2014-04-14 OR_huawei_201405_234  自助终端接入EBUS一阶段_缴费
	
	// modify begin by xkf29026 2011/10/6  添加final
	public static final Map<String,Object> pubMap = new HashMap<String, Object>();
	// modify end by xkf29026 2011/10/6 添加final
	
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

	public void getDictItemByGroup(Map<String, String> inMap)
    {
        String groupID = inMap.get("groupid");
        if(!pubMap.containsKey(groupID))
        {
            ReturnWrap rw = this.getSelfSvcCallHub().getDictItemByGroup(inMap);
            
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

}
