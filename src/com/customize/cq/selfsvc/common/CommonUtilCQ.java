/**
 * 
 */
package com.customize.cq.selfsvc.common;

import java.util.List;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;

/**
 * 
 * @author Add by LiFeng
 * 
 *         20110907
 * 
 */
public class CommonUtilCQ
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
        for (DictItemPO dictItem : itemList)
        {
            if (dictID.equals(dictItem.getDictid()))
            {
                return dictItem.getDictname();
            }
        }
        return null;
    }
    
    /**
     * 根据当前菜单ID获取其busidetail字段
     * 2011-11-16 z90080209
     * @param currMenuID
     * @return
     * @see 
     */
    public static String getMenuBusiDetail(String currMenuID)
    {
        List allMenu = (List) PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
        
        if (allMenu == null || allMenu.size() == 0 
                || currMenuID == null || "".equals(currMenuID.trim()))
        {
            return "";
        }
        
        MenuInfoPO menu = null;
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO) allMenu.get(i);
            if (currMenuID.equals(menu.getMenuid()))
            {
                return menu.getBusidetail();
            }
        }
        
        return "";
    }
    
    public static boolean strIsEmpty(String iStr)
    {
        return iStr == null || "".equals(iStr.trim()) ? true : false;
    }
    
    public static boolean objIsNull(Object obj)
    {
        return obj == null ? true : false;
    }
    
}
