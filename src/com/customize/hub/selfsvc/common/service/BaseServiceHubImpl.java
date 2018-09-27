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
 * <����������Service������>
 * <������ϸ����>
 * 
 * @author  wWX217192
 * @version  [�汾��, Apr 22, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by wWX219697 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
 */
public class BaseServiceHubImpl extends BaseServiceImpl
{
	//�û���ƷƷ��
    public final String PRODUCTBRAND = "ProductBrand";

	private SelfSvcCallHub selfSvcCallHub;
	
	private SelfSvcCall selfSvcCall;
	
	public static final Map<String,Object> pubMap = new HashMap<String, Object>();
	
    /** ����crm��ȡ�ֵ���
     * <������ϸ����>
     * @param inMap void
     * @see [�ࡢ��#��������#��Ա]
     * Create Author:<gWX223032> Time:<May 20, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1>
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
    
    /** ����groupid��ȡcrm�ֵ���ӿ�(��װMap����)
     * <������ϸ����>
     * @param groupid 
     * @param curMenuId ��ǰ�˵�
     * @param servNumber �������
     * @param termInfo �ն���Ϣ
     * @return List<DictItemVO>
     * @see [�ࡢ��#��������#��Ա]
     * Create Author:<gWX223032> Time:<May 20, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1>
     */
    public List<DictItemVO> getDictItems(String groupid, String curMenuId)
    {
        if (!pubMap.containsKey(groupid))
        {
            // ��ҪȥCRMϵͳ���ȡ�ֵ������
            Map<String, String> inMap = new HashMap<String, String>();
            inMap.put("groupid", groupid);
            
            inMap.put("curMenuId", curMenuId);
            
            // ���ÿͻ��ֻ���
            inMap.put("telnumber", getCustomer().getServNumber());
            
            // ���ò���Աid
            inMap.put("curOper", getTermInfo().getOperid());
            
            // �����ն˻�id
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
