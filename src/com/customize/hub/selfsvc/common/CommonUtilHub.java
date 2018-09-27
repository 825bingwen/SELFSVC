/**
 * 
 */
package com.customize.hub.selfsvc.common;

import java.util.List;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 
 * @author Add by LiFeng
 * 
 *         20110907
 * 
 */
public class CommonUtilHub
{
    /**
     * ��ȡ�ֵ����Ϣ
     * 
     * @param dictID
     * @param groupID
     * @return
     */
    public static String getDictNameByID(String dictID, String groupID)
    {
        if (null == dictID || null == groupID)
        {
            return null;
        }
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(groupID);
        if (null == itemList)
        {
            return null;
        }
        for (DictItemPO dictItem : itemList)
        {
            if (dictID.equals(dictItem.getDictid()))
            {
                return dictItem.getDictname();
            }
        }
        return null;
    }
    
    public static boolean strIsEmpty(String iStr)
    {
        return iStr == null || "".equals(iStr.trim()) ? true : false;
    }
    
    public static boolean objIsNull(Object obj)
    {
        return obj == null ? true : false;
    }
    
    // add by Lifeng 2013-05-18 [OR_HUB_201305_410][�������]�����ն˿��� begion
    /**
     * �����ж��Ƿ�Ϊ��ֵ��� <������ϸ����>
     * 
     * @param region
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public static boolean isSplitRegion(String region)
    {
        List<DictItemPO> smallregionList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("SPLITREGION");
        if (null == smallregionList)
        {
            return false;
        }
        for (DictItemPO po : smallregionList)
        {
            if (po.getDictid().equals(region))
            {
                return true;
            }
        }
        
        // δ���ֵ���в�ѯ������ʾ�ǲ�ֵ���
        return false;
    }
    // add by Lifeng 2013-05-18 [OR_HUB_201305_410][�������]�����ն˿��� end
    
}
