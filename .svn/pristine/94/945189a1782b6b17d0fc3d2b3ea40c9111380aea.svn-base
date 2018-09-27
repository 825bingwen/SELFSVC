package com.gmcc.boss.selfsvc.reception.service;

import java.util.List;

import com.gmcc.boss.selfsvc.reception.dao.MenuDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 湖北菜单展现impl
 * 
 * 
 * @author  user
 * @version  [版本号, Apr 6, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuServiceImpl implements MenuService 
{
	private MenuDaoImpl menuDaoImpl;
	
	/**
     * 查询一级菜单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<MenuInfoPO> qryRecHotspot(TerminalInfoPO termInfo, String parentid)
	{
		return menuDaoImpl.qryRecHotspot(termInfo, parentid);
	}
	
    /**
     * 热门业务推荐下一级功能菜单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MenuInfoPO> qryRecHotspotNext(TerminalInfoPO termInfo, String menuid)
    {
        return menuDaoImpl.qryRecHotspotNext(termInfo, menuid);
    }

    /**
     * 查询菜单中最底层子节点
     * @param termgrpid 终端组ID
     * @return
     */
    @Override
    public List<MenuInfoPO> qryMinChildNode(MenuInfoPO menuInfo)
    {
        return menuDaoImpl.qryMinChildNode(menuInfo);
    }
    
    public MenuDaoImpl getMenuDaoImpl()
    {
        return menuDaoImpl;
    }

    public void setMenuDaoImpl(MenuDaoImpl menuDaoImpl)
    {
        this.menuDaoImpl = menuDaoImpl;
    }

    /**
     * 按分类查询菜单中最底层子节点
     * @param MenuInfoPO 终端组ID
     * @param MenuInfoPO
     * @return 按分类查询菜单列表
     * @remark create by wWX217192 2014-10-20 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
	@Override
	public List<MenuInfoPO> qryGroupsChildNode(MenuInfoPO menuInfo) 
	{
		return menuDaoImpl.qryGroupsChildNode(menuInfo);
	}

    
	
	
}
