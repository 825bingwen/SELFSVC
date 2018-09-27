/*
 * 文件名：ReceptionAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-21
 * 修改内容：新增，移动业务办理
 */
package com.gmcc.boss.selfsvc.reception.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.customize.hub.selfsvc.common.ChineseSpelling;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;
import com.gmcc.boss.selfsvc.quickpublish.service.QuickPubService;
import com.gmcc.boss.selfsvc.reception.service.MenuService;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 移动业务办理
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-21
 * @see
 * @since
 */
public class ReceptionAction extends BaseAction
{
    private static final long serialVersionUID = 1L;

    private String curMenuId = "";
    
    // 页数
    private int pageCount;
    
    // 分页标志
    private String firstFlag;
    
    // 二级菜单
    private List<MenuInfoPO> menuHubList2;
    
    // 查询数据库
    private MenuService menuService;
    
    // 页面展现样式 (1:统一图片 0:自定义图片)
    private String style;
    
    // URL
    private String url;
    
    // 查找方式
    private String searchType;
    
    // 品牌
    private String searchCards;
    
    // 全球通品牌描述
    private String BrandGotone_hub;
    
    // 动感地带品牌描述
    private String BrandMzone_hub;
    
    // 神州行品牌描述
    private String BrandSzx_hub;
    
    // 首字母
    private String searchLetter;
    
    // 底层菜单
    private List<MenuInfoPO> menuMinChildNode = new ArrayList<MenuInfoPO>();
    
    // 每页显示容量
    private int pageSize;
    
    // 第几页
    private int page = 0;
    
    // 产品查询service
    private QuickPubService quickPubService;
    
    //语音与套餐类服务描述
    private String comboMsg_sd;
    
    // 增值业务类描述
    private String VASMsg_sd;
    
    // 国际业务类描述
    private String interBusMsg_sd;    
    
    // 全球通品牌描述
    private String BrandGotone_sd;
    
    // 动感地带品牌描述
    private String BrandMzone_sd;
    
    // 神州行品牌描述
    private String BrandSzx_sd;
    
    // 分类
    private String searchGroups;
    
    /**
     * 显示功能列表
     * 
     * @return
     * @throws Exception
     */
    public String initFunctionList() throws Exception
    {
        menuMinChildNode.clear();
        
        List<MenuInfoPO> menus = new ArrayList<MenuInfoPO>();     
        
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(terminalInfo.getTermgrpid());
        
        if (titleTotalMenus != null && titleTotalMenus.size() > 0)
        {
            for (int i = 0; i < titleTotalMenus.size(); i++)
            {
                MenuInfoPO menu = (MenuInfoPO) titleTotalMenus.get(i);
                
                 if (curMenuId.equalsIgnoreCase(menu.getParentid()) 
                            && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
                                    || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))) 
                {
                     menus.add(menu);
                }
            }           
        }
        
        this.getRequest().setAttribute("recordCount", menus.size());
        
        pageSize = 12;
        
        // modify begin cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

        if(Constants.PROOPERORGID_NX.equals(province))
        {
            pageSize = 10;
        }
		// modify end cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
        
        menuMinChildNode = this.getPageList(menus);        
        
        // 返回
        if ("1".equals(style))
        {
            // 统一图片
            return "funclist_unify";
        }
        else
        {
            // 自定义图片
            return "funclist";
        }
    }
    
    /**
     * 显示功能列表_湖北
     * 
     * 展现:热门业务推荐、用户服务、产品开通、资费推荐、梦网业务订购 单击：热门业务推荐、用户服务、产品开通 进入 二级子菜单 单击：资费推荐、梦网业务订购 进入功能页面
     * 
     * @return
     * @throws Exception
     */
    public String initFunctionList_hub() throws Exception
    {
        // modify begin g00140516 2012/06/08 R003C12L05n01 Bug 26719
    	curMenuId = "rec";
        // modify end g00140516 2012/06/08 R003C12L05n01 Bug 26719
        
        String farwed = "";
        
        // 空或1按业务分类、2按品牌分类、3按首字母查找
        if (null == searchType || "".equals(searchType) || "1".equals(searchType))
        {
            farwed = "funclist01";
        }
        else if ("2".equals(searchType))
        {
            // 获取品牌描述
            BrandGotone_hub = (String)PublicCache.getInstance().getCachedData("BrandGotone_hub");
            BrandMzone_hub = (String)PublicCache.getInstance().getCachedData("BrandMzone_hub");
            BrandSzx_hub = (String)PublicCache.getInstance().getCachedData("BrandSzx_hub");
            farwed = "funclist02";
        }
        else
        {
            farwed = "funclist03";
        }
        return farwed;
    }
    
    /**
     * 显示功能列表_湖北_二级菜单(热点业务推荐)
     * 
     * @return
     * @throws Exception
     */
    public String initRecHotspot_hub() throws Exception
    {
        HttpSession session = this.getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 取缓存里的菜单对象
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        MenuInfoPO menu = null;
        for (int i = 0; i < titleTotalMenus.size(); i++)
        {
            menu = (MenuInfoPO)titleTotalMenus.get(i);
            if (curMenuId.equals(menu.getMenuid()))
            {
                break;
            }
        }
        
        // 查询菜单对应URL
        url = menu.getGuiobj();
        
        // 查询热点业务推荐下的菜单列表
        menuHubList2 = this.menuService.qryRecHotspot(termInfo, curMenuId);
        
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < menuHubList2.size(); i++)
        {
            MenuInfoPO menuInfo = (MenuInfoPO)menuHubList2.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || termInfo.getRegion()
                    .equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        //add begin CKF76106 2012/10/12 R003C12L09n01 OR_HUB_201209_884   
        
        // 产品快速发布上线标识
        String flag = (String) PublicCache.getInstance().getCachedData(Constants.SH_QUICKPUBLISH_FLAG);
        
        if("1".equals(flag))
        {
            NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

            List<ProdConfigPO> prods = new ArrayList<ProdConfigPO>();
            
            ProdConfigPO prodConfigPO = new ProdConfigPO();
            prodConfigPO.setAreaCode(termInfo.getRegion());
            prodConfigPO.setIsRec("1");
            
            // 已登录用户查询
            if (null != customerInfor)
            {
                prodConfigPO.setBrand(customerInfor.getBrandID());
                prodConfigPO.setMainProd(customerInfor.getProductID());
                prodConfigPO.setAreaCode(customerInfor.getRegionID());
            }
            
            // 产品查询:查询产品配置表
            prods = quickPubService.getProdList(prodConfigPO);
                    
            // 去掉重复的NCODE
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < prods.size(); i++) 
            {
                String nCode = prods.get(i).getNcode();
                if (map.get(nCode) != null) 
                {
                    prods.remove(i);
                    i--;
                } 
                else 
                {
                    map.put(nCode, "");
                }
            }
            // 将推荐产品添加到结果列表中
            for(ProdConfigPO po : prods)
            {
                MenuInfoPO menupo = new  MenuInfoPO();
                menupo.setIsProd("1");
                menupo.setProdConfigPO(po);
                menuHubList_tmp.add(menupo);
            }
        }
        
        //add end CKF76106 2012/10/12 R003C12L09n01 OR_HUB_201209_884 
        
        this.getRequest().setAttribute("recordCount", menuHubList_tmp.size());
        
        pageSize = 12;
        
        menuHubList2 = this.getPageList(menuHubList_tmp);
        
        // 返回
        if ("1".equals(style))
        {
            // 统一图片
            return "funclist_unify";
        }
        else
        {
            // 自定义图片
            return "funclist";
        }
        
    }
    
    /**
     * 热门业务推荐下一级功能菜单
     * 
     * @return
     * @throws Exception
     */
    public String initRecHotspotNext_hub() throws Exception
    {
        HttpSession session = this.getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 取缓存里的菜单对象
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        MenuInfoPO menu = null;
        for (int i = 0; i < titleTotalMenus.size(); i++)
        {
            menu = (MenuInfoPO)titleTotalMenus.get(i);
            if (curMenuId.equals(menu.getMenuid()))
            {
                break;
            }
        }
        
        // 查询菜单对应URL
        url = menu.getGuiobj();
        
        // 热门业务推荐下一级菜单的功能列表
        menuHubList2 = this.menuService.qryRecHotspotNext(termInfo, curMenuId);
        
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < menuHubList2.size(); i++)
        {
            MenuInfoPO menuInfo = (MenuInfoPO)menuHubList2.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || termInfo.getRegion()
                    .equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        
        // 赋值
        menuHubList2 = menuHubList_tmp;
        
        // 返回
        if ("1".equals(style))
        {
            // 统一图片
            return "funclist_unify";
        }
        else
        {
            // 自定义图片
            return "funclist";
        }
        
    }
    
    /**
     * 按品牌搜索业务名称
     * 
     * @return
     * @see
     */
    public String searchCards_hub()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // 赋值终端机组ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // begin zKF66389 findbus清零
        // 品牌
        menuInfo.setBrandID(searchCards);
        // end zKF66389 findbus清零
        
        List<MenuInfoPO> menus = this.menuService.qryMinChildNode(menuInfo);
                
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < menus.size(); i++)
        {
            menuInfo = (MenuInfoPO)menus.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || 
                    termInfo.getRegion().equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        
        String quickPublishFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_QUICKPUBLISH_FLAG);
        
        // 产品快速发布已上线，取sh_prod_config表中的产品信息
        if ("1".equals(quickPublishFlag))
        {
            NserCustomerSimp customerInfo = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

            ProdConfigPO prodConfigPO = new ProdConfigPO();
            
            // 终端机归属地区
            prodConfigPO.setAreaCode(termInfo.getRegion());
            
            // 按指定品牌搜索
            prodConfigPO.setBrand(searchCards);
            
            // 已登录用户查询时，需按照用户的归属地区和主体产品进行过滤
            if (null != customerInfo)
            {
                prodConfigPO.setAreaCode(customerInfo.getRegionID());
                prodConfigPO.setMainProd(customerInfo.getProductID());
            }
            
            // 产品查询:查询产品配置表
            List<ProdConfigPO> prods = quickPubService.getProdList(prodConfigPO);
                    
            // 去掉重复的NCODE
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < prods.size(); i++) 
            {
                String nCode = prods.get(i).getNcode();
                if (map.get(nCode) != null) 
                {
                    prods.remove(i);
                    i--;
                } 
                else 
                {
                    map.put(nCode, "");
                }
            }
            
            for(ProdConfigPO po : prods)
            {
                MenuInfoPO menupo = new  MenuInfoPO();
                menupo.setIsProd("1");
                menupo.setProdConfigPO(po);
                menuHubList_tmp.add(menupo);
            }
        }
               
        menus = menuHubList_tmp;
        
        this.getRequest().setAttribute("recordCount", menus.size());
        
        pageSize = 8;
        
        menuMinChildNode = this.getPageList(menus);        
        
        return "searchCards";
    }
    
    /**
     * 按名称业务名称首字的拼音首字母搜索业务名称
     * 
     * @return
     * @see
     */
    public String searchLetter_hub()
    {
        menuMinChildNode = new ArrayList<MenuInfoPO>();
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
                
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // 赋值终端机组ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // 获取菜单
        // begin zKF66389 findbus清零
        menuInfo.setBrandID(null);
        // end zKF66389 findbus清零
        
        List<MenuInfoPO> list = this.menuService.qryMinChildNode(menuInfo);
        
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < list.size(); i++)
        {
            menuInfo = (MenuInfoPO)list.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || termInfo.getRegion().equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        
        String quickPublishFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_QUICKPUBLISH_FLAG);
        
        // 产品快速发布已上线，取sh_prod_config表中的产品信息
        if ("1".equals(quickPublishFlag))
        {
            NserCustomerSimp customerInfo = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

            ProdConfigPO prodConfigPO = new ProdConfigPO();
            
            // 终端机归属地区
            prodConfigPO.setAreaCode(termInfo.getRegion());
            
            // 已登录用户查询时，需按照用户的归属地区、品牌和主体产品进行过滤
            if (null != customerInfo)
            {
                prodConfigPO.setAreaCode(customerInfo.getRegionID());
                prodConfigPO.setBrand(customerInfo.getBrandID());
                prodConfigPO.setMainProd(customerInfo.getProductID());
            }
            
            // 产品查询:查询产品配置表
            List<ProdConfigPO> prods = quickPubService.getProdList(prodConfigPO);
                    
            // 去掉重复的NCODE
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < prods.size(); i++) 
            {
                String nCode = prods.get(i).getNcode();
                if (map.get(nCode) != null) 
                {
                    prods.remove(i);
                    i--;
                } 
                else 
                {
                    map.put(nCode, "");
                }
            }
            
            for (ProdConfigPO po : prods)
            {
                MenuInfoPO menupo = new  MenuInfoPO();
                menupo.setIsProd("1");
                menupo.setProdConfigPO(po);
                menuHubList_tmp.add(menupo);
            }
        }
        list = menuHubList_tmp;
        
        if (!list.isEmpty() && !"".equals(searchLetter))
        {
            // 获取中文首字母
            String str = "";
            
            // 中文转化拼音首字母对象实例
            ChineseSpelling chineseSpell = ChineseSpelling.getInstance();
            
            for (int i = 0, n = list.size(); i < n; i++)
            {
                menuInfo = list.get(i);
                
                // sh_prod_config表中的产品
             // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//                if ("1".equals(menuInfo.getIsProd()))
//                {
//                    str = chineseSpell.getSellingHead(menuInfo.getProdConfigPO().getProdName());
//                }
//                else
//                {
//                    str = chineseSpell.getSellingHead(menuInfo.getMenuname());
//                }
                str = ("1".equals(menuInfo.getIsProd())) ? chineseSpell.getSellingHead(menuInfo.getProdConfigPO().getProdName()) : chineseSpell.getSellingHead(menuInfo.getMenuname());
                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                
                // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                //if (!"".equals(str) && null != str)
                if (isNotNull(str))
                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                {
                    if (str.substring(0, 1).equals(searchLetter))
                    {
                        menuMinChildNode.add(menuInfo);
                    }
                }
            }
        }
        
        this.getRequest().setAttribute("recordCount", menuMinChildNode.size());
        
        pageSize = 8;
        
        // 分页
        menuMinChildNode = getPageList(menuMinChildNode);
        return "searchLetter";
    }
    
    /**
     * 判断是否为空
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isNotNull(String str)
    {
        return !"".equals(str) && null != str;
    }
    
    /**
     * 分页
     * 
     * @param list 菜单集合
     * @return
     * @see
     */
    public List<MenuInfoPO> getPageList(List<MenuInfoPO> list)
    {
        int sum=0;
        int start=0;
        int end=0;
        
        // 获取当前页
        if (page == 0)
        {
            page = 1;
        }
        
        // 获取总页数
        if (!list.isEmpty())
        {
            sum=list.size();
            pageCount = list.size() / pageSize;
            if (list.size() % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        else
        {
            pageCount = 0;
        }
        
        start = (page - 1) * pageSize;
        if (page >= pageCount)
        {
            end = sum;
        }else{
            end = page * pageSize;
        }
        
        List<MenuInfoPO> menuInfoList = new ArrayList<MenuInfoPO>();
        
        for (int i = start; i < end; i++)
        {
            menuInfoList.add(list.get(i));
        }
        return menuInfoList;
    }
    
    /**
     * 获取终端机信息
     * 
     * @return
     * @see
     */
    public TerminalInfoPO getTerminalInfo()
    {
        HttpSession session = this.getRequest().getSession();
        return (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    }
    
    /**
     * 显示功能列表_山东
     * 
     * 展现:热门业务推荐、用户服务、产品开通、资费推荐、梦网业务订购 单击：热门业务推荐、用户服务、产品开通 进入 二级子菜单 单击：资费推荐、梦网业务订购 进入功能页面
     * 
     * @return
     * @throws Exception
     * @remark create by wWX217192 2014-10-09 V200R003C10LG1001 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
    public String initFunctionList_sd() throws Exception
    {
        // modify begin g00140516 2012/06/08 R003C12L05n01 Bug 26719
    	curMenuId = "rec";
        // modify end g00140516 2012/06/08 R003C12L05n01 Bug 26719
        
        String farwed = "";
        
        // 空或1按业务分类、2按品牌分类、3按首字母查找
        if (null == searchType || "".equals(searchType) || "1".equals(searchType))
        {
        	// 获取分类描述
        	comboMsg_sd = (String)PublicCache.getInstance().getCachedData("comboMsg_sd");
        	VASMsg_sd = (String)PublicCache.getInstance().getCachedData("VASMsg_sd");
        	interBusMsg_sd = (String)PublicCache.getInstance().getCachedData("interBusMsg_sd");
        	
            farwed = "funclist01";
        }
        else if ("2".equals(searchType))
        {
            // 获取品牌描述
            BrandGotone_sd = (String)PublicCache.getInstance().getCachedData("BrandGotone_sd");
            BrandMzone_sd = (String)PublicCache.getInstance().getCachedData("BrandMzone_sd");
            BrandSzx_sd = (String)PublicCache.getInstance().getCachedData("BrandSzx_sd");
            farwed = "funclist02";
        }
        else
        {
            farwed = "funclist03";
        }
        return farwed;
    }
    
    /**
     * 按名称业务名称首字的拼音首字母搜索业务名称
     * 
     * @return
     * @see
     * @remark create by wWX217192 2014-10-10 V200R003C10LG1001 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
    public String searchLetter_sd()
    {
        menuMinChildNode = new ArrayList<MenuInfoPO>();
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
                
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // 赋值终端机组ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // 菜单的品牌
        // begin zKF66389 findbus清零
        menuInfo.setBrandID(null);
        // end zKF66389 findbus清零
        
        // 查询相关终端机下的所有菜单
        List<MenuInfoPO> list = this.menuService.qryMinChildNode(menuInfo);
        
        // 过滤区域
        List<MenuInfoPO> menuSdList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < list.size(); i++)
        {
            menuInfo = (MenuInfoPO)list.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || termInfo.getRegion().equalsIgnoreCase(menuInfo.getRegion())))
            {
            	menuSdList_tmp.add(menuInfo);
            }
        }
        
        list = menuSdList_tmp;
        
        if (!list.isEmpty() && !"".equals(searchLetter))
        {
            // 获取中文首字母
            String str = "";
            
            // 中文转化拼音首字母对象实例
            ChineseSpelling chineseSpell = ChineseSpelling.getInstance();
            
            for (int i = 0, n = list.size(); i < n; i++)
            {
                menuInfo = list.get(i);
                
                str = ("1".equals(menuInfo.getIsProd())) ? chineseSpell.getSellingHead(menuInfo.getProdConfigPO().getProdName()) : chineseSpell.getSellingHead(menuInfo.getMenuname());
                if (isNotNull(str))
                {
                    if (str.substring(0, 1).equals(searchLetter))
                    {
                        menuMinChildNode.add(menuInfo);
                    }
                }
            }
        }
        
        this.getRequest().setAttribute("recordCount", menuMinChildNode.size());
        
        pageSize = 8;
        
        // 分页
        menuMinChildNode = getPageList(menuMinChildNode);
        return "searchLetter";
    }
    
    /**
     * 按品牌搜索业务名称
     * 
     * @return
     * @see
     * @remark create by wWX217192 2014-10-13 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
    public String searchCards_sd()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // 赋值终端机组ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // 品牌
        // begin zKF66389 findbus清零
        menuInfo.setBrandID(searchCards);
        // end zKF66389 findbus清零
        
        List<MenuInfoPO> menus = this.menuService.qryMinChildNode(menuInfo);
                
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < menus.size(); i++)
        {
            menuInfo = (MenuInfoPO)menus.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || 
                    termInfo.getRegion().equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        
        menus = menuHubList_tmp;
        
        this.getRequest().setAttribute("recordCount", menus.size());
        
        pageSize = 8;
        
        menuMinChildNode = this.getPageList(menus);        
        
        return "searchCards";
    }
    
    /**
     * 按分类搜索业务名称
     * @return
     * @remark create by wWX217192 2014-10-17 OR_SD_201402_795_山东_关于自助终端管理平台产品办理功能优化的需求
     */
    public String searchGroups_sd()
    {
    	HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // 赋值终端机组ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // 分类
        menuInfo.setType(searchGroups);
        
        // 按照分类查询山东业务办理的菜单
        List<MenuInfoPO> menus = this.menuService.qryGroupsChildNode(menuInfo);
        
        // 过滤区域
        List<MenuInfoPO> menuHubList_tmp = new ArrayList<MenuInfoPO>();
        for (int i = 0; i < menus.size(); i++)
        {
            menuInfo = (MenuInfoPO)menus.get(i);
            
            if ((Constants.PROVINCE_REGION_999.equalsIgnoreCase(menuInfo.getRegion()) || 
                    termInfo.getRegion().equalsIgnoreCase(menuInfo.getRegion())))
            {
                menuHubList_tmp.add(menuInfo);
            }
        }
        
        menus = menuHubList_tmp;
        
        this.getRequest().setAttribute("recordCount", menus.size());
        
        pageSize = 8;
        
        menuMinChildNode = this.getPageList(menus);        
        
    	return "searchGroups";
    }
    

    public String getFirstFlag()
    {
        return firstFlag;
    }
    
    public void setFirstFlag(String firstFlag)
    {
        this.firstFlag = firstFlag;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<MenuInfoPO> getMenuHubList2()
    {
        return menuHubList2;
    }
    
    public void setMenuHubList2(List<MenuInfoPO> menuHubList2)
    {
        this.menuHubList2 = menuHubList2;
    }
    
    public MenuService getMenuService()
    {
        return menuService;
    }
    
    public void setMenuService(MenuService menuService)
    {
        this.menuService = menuService;
    }
    
    public String getStyle()
    {
        return style;
    }
    
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getSearchType()
    {
        return searchType;
    }
    
    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }
    
    public String getSearchCards()
    {
        return searchCards;
    }
    
    public void setSearchCards(String searchCards)
    {
        this.searchCards = searchCards;
    }
    
    public String getBrandGotone_hub()
    {
        return BrandGotone_hub;
    }

    public void setBrandGotone_hub(String brandGotone_hub)
    {
        BrandGotone_hub = brandGotone_hub;
    }

    public String getBrandMzone_hub()
    {
        return BrandMzone_hub;
    }

    public void setBrandMzone_hub(String brandMzone_hub)
    {
        BrandMzone_hub = brandMzone_hub;
    }

    public String getBrandSzx_hub()
    {
        return BrandSzx_hub;
    }

    public void setBrandSzx_hub(String brandSzx_hub)
    {
        BrandSzx_hub = brandSzx_hub;
    }

    public String getSearchLetter()
    {
        return searchLetter;
    }
    
    public void setSearchLetter(String searchLetter)
    {
        this.searchLetter = searchLetter;
    }
    
    public List<MenuInfoPO> getMenuMinChildNode()
    {
        return menuMinChildNode;
    }
    
    public void setMenuMinChildNode(List<MenuInfoPO> menuMinChildNode)
    {
        this.menuMinChildNode = menuMinChildNode;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public QuickPubService getQuickPubService()
    {
        return quickPubService;
    }

    public void setQuickPubService(QuickPubService quickPubService)
    {
        this.quickPubService = quickPubService;
    }

	public String getComboMsg_sd() {
		return comboMsg_sd;
	}

	public void setComboMsg_sd(String comboMsg_sd) {
		this.comboMsg_sd = comboMsg_sd;
	}

	public String getVASMsg_sd() {
		return VASMsg_sd;
	}

	public void setVASMsg_sd(String msg_sd) {
		VASMsg_sd = msg_sd;
	}

	public String getInterBusMsg_sd() {
		return interBusMsg_sd;
	}

	public void setInterBusMsg_sd(String interBusMsg_sd) {
		this.interBusMsg_sd = interBusMsg_sd;
	}

	public String getBrandGotone_sd() {
		return BrandGotone_sd;
	}

	public void setBrandGotone_sd(String brandGotone_sd) {
		BrandGotone_sd = brandGotone_sd;
	}

	public String getBrandMzone_sd() {
		return BrandMzone_sd;
	}

	public void setBrandMzone_sd(String brandMzone_sd) {
		BrandMzone_sd = brandMzone_sd;
	}

	public String getBrandSzx_sd() {
		return BrandSzx_sd;
	}

	public void setBrandSzx_sd(String brandSzx_sd) {
		BrandSzx_sd = brandSzx_sd;
	}

	public String getSearchGroups() {
		return searchGroups;
	}

	public void setSearchGroups(String searchGroups) {
		this.searchGroups = searchGroups;
	}    
}
