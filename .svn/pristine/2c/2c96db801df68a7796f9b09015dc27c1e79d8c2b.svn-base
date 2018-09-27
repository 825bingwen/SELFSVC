package com.gmcc.boss.selfsvc.reception.dao;

import java.util.ArrayList;
import java.util.List;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 湖北菜单展现DaoImpl
 * 
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 6, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuDaoImpl extends BaseDaoImpl
{
	/**
	 * 查询一级菜单
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<MenuInfoPO> qryRecHotspot(TerminalInfoPO termInfo, String parentid)
	{
	    // 参数
	    MenuInfoPO menuPO = new MenuInfoPO();
	    menuPO.setTermgrpid(termInfo.getTermgrpid());
	    menuPO.setParentid(parentid);
	    
	    // 执行查询
		List<MenuInfoPO> menuInfoList = super.sqlMapClientTemplate.queryForList("menuHub.qryRecHotspot",menuPO);
		
		// 返回
		return menuInfoList;
	}
	
	/**
     * 热门业务推荐下一级功能菜单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MenuInfoPO> qryRecHotspotNext(TerminalInfoPO termInfo, String menuid)
    {
        // 参数
        MenuInfoPO menuPO = new MenuInfoPO();
        
        // 所属终端组
        menuPO.setTermgrpid(termInfo.getTermgrpid());
        
        // 父菜单
        menuPO.setParentid(menuid);
        
        // 执行查询
        List<MenuInfoPO> menuInfoList = super.sqlMapClientTemplate.queryForList("menuHub.qryRecHotspotNext",menuPO);
        
        // 返回
        return menuInfoList;
    }
    
    /**
     * 查询菜单中最底层子节点
     * @param termgrpid 终端组ID
     * @return
     * @see
     */
    public List<MenuInfoPO> qryMinChildNode(MenuInfoPO menuInfo)
    {
    	// modify begin wWX217192 2014-10-13 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
    	List<MenuInfoPO> menuInfoList = new ArrayList<MenuInfoPO>();
        
    	if(Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
        	// 执行查询
        	menuInfoList = super.sqlMapClientTemplate.queryForList("menuHub.qryMinChildNode",menuInfo);
        }
        else
        {
        	menuInfoList = super.sqlMapClientTemplate.queryForList("ResData.qryMinChildNode",menuInfo);
        }
    	// modify end wWX217192 2014-10-13 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
        return menuInfoList;
    }
    
    /**
     * 查询菜单中最底层子节点按分类搜索（山东）
     * @param MenuInfoPO 终端组ID
     * @param MenuInfoPO 
     * @return 按分类查询显示的菜单列表信息
     * @remark create by wWX217192 2014-10-20 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
    public List<MenuInfoPO> qryGroupsChildNode(MenuInfoPO menuInfo)
    {
    	// 执行查询
    	return super.sqlMapClientTemplate.queryForList("ResData.qryGroupsChildNode", menuInfo);
    }
}
