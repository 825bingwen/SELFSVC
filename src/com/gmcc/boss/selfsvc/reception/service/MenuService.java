package com.gmcc.boss.selfsvc.reception.service;

import java.util.List;

import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 湖北菜单展现service
 * 
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 6, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MenuService 
{
	/**
	 * 查询一级菜单
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<MenuInfoPO> qryRecHotspot(TerminalInfoPO termInfo, String parentid);
	
    /**
     * 热门业务推荐下一级功能菜单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MenuInfoPO> qryRecHotspotNext(TerminalInfoPO termInfo, String menuid);
    
    /**
     * 查询菜单中最底层子节点按品牌查询
     * @param termgrpid 终端组ID
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MenuInfoPO> qryMinChildNode(MenuInfoPO menuInfo);
    
    /**
     * 查询菜单中最底层子节点按分类查询
     * @param menuInfo 
     */
    public List<MenuInfoPO> qryGroupsChildNode(MenuInfoPO menuInfo);
    
}
