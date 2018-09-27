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
     * 获取字典表信息
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
    
    // add by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户 begion
    /**
     * 湖北判断是否为拆分地市 <功能详细描述>
     * 
     * @param region
     * 
     * @see [类、类#方法、类#成员]
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
        
        // 未从字典表中查询到，表示非拆分地市
        return false;
    }
    // add by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户 end
    
}
