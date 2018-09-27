/*
 * �ļ�����ReceptionAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-21
 * �޸����ݣ��������ƶ�ҵ�����
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
 * �ƶ�ҵ�����
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-21
 * @see
 * @since
 */
public class ReceptionAction extends BaseAction
{
    private static final long serialVersionUID = 1L;

    private String curMenuId = "";
    
    // ҳ��
    private int pageCount;
    
    // ��ҳ��־
    private String firstFlag;
    
    // �����˵�
    private List<MenuInfoPO> menuHubList2;
    
    // ��ѯ���ݿ�
    private MenuService menuService;
    
    // ҳ��չ����ʽ (1:ͳһͼƬ 0:�Զ���ͼƬ)
    private String style;
    
    // URL
    private String url;
    
    // ���ҷ�ʽ
    private String searchType;
    
    // Ʒ��
    private String searchCards;
    
    // ȫ��ͨƷ������
    private String BrandGotone_hub;
    
    // ���еش�Ʒ������
    private String BrandMzone_hub;
    
    // ������Ʒ������
    private String BrandSzx_hub;
    
    // ����ĸ
    private String searchLetter;
    
    // �ײ�˵�
    private List<MenuInfoPO> menuMinChildNode = new ArrayList<MenuInfoPO>();
    
    // ÿҳ��ʾ����
    private int pageSize;
    
    // �ڼ�ҳ
    private int page = 0;
    
    // ��Ʒ��ѯservice
    private QuickPubService quickPubService;
    
    //�������ײ����������
    private String comboMsg_sd;
    
    // ��ֵҵ��������
    private String VASMsg_sd;
    
    // ����ҵ��������
    private String interBusMsg_sd;    
    
    // ȫ��ͨƷ������
    private String BrandGotone_sd;
    
    // ���еش�Ʒ������
    private String BrandMzone_sd;
    
    // ������Ʒ������
    private String BrandSzx_sd;
    
    // ����
    private String searchGroups;
    
    /**
     * ��ʾ�����б�
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
        
        // ����
        if ("1".equals(style))
        {
            // ͳһͼƬ
            return "funclist_unify";
        }
        else
        {
            // �Զ���ͼƬ
            return "funclist";
        }
    }
    
    /**
     * ��ʾ�����б�_����
     * 
     * չ��:����ҵ���Ƽ����û����񡢲�Ʒ��ͨ���ʷ��Ƽ�������ҵ�񶩹� ����������ҵ���Ƽ����û����񡢲�Ʒ��ͨ ���� �����Ӳ˵� �������ʷ��Ƽ�������ҵ�񶩹� ���빦��ҳ��
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
        
        // �ջ�1��ҵ����ࡢ2��Ʒ�Ʒ��ࡢ3������ĸ����
        if (null == searchType || "".equals(searchType) || "1".equals(searchType))
        {
            farwed = "funclist01";
        }
        else if ("2".equals(searchType))
        {
            // ��ȡƷ������
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
     * ��ʾ�����б�_����_�����˵�(�ȵ�ҵ���Ƽ�)
     * 
     * @return
     * @throws Exception
     */
    public String initRecHotspot_hub() throws Exception
    {
        HttpSession session = this.getRequest().getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ȡ������Ĳ˵�����
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
        
        // ��ѯ�˵���ӦURL
        url = menu.getGuiobj();
        
        // ��ѯ�ȵ�ҵ���Ƽ��µĲ˵��б�
        menuHubList2 = this.menuService.qryRecHotspot(termInfo, curMenuId);
        
        // ��������
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
        
        // ��Ʒ���ٷ������߱�ʶ
        String flag = (String) PublicCache.getInstance().getCachedData(Constants.SH_QUICKPUBLISH_FLAG);
        
        if("1".equals(flag))
        {
            NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

            List<ProdConfigPO> prods = new ArrayList<ProdConfigPO>();
            
            ProdConfigPO prodConfigPO = new ProdConfigPO();
            prodConfigPO.setAreaCode(termInfo.getRegion());
            prodConfigPO.setIsRec("1");
            
            // �ѵ�¼�û���ѯ
            if (null != customerInfor)
            {
                prodConfigPO.setBrand(customerInfor.getBrandID());
                prodConfigPO.setMainProd(customerInfor.getProductID());
                prodConfigPO.setAreaCode(customerInfor.getRegionID());
            }
            
            // ��Ʒ��ѯ:��ѯ��Ʒ���ñ�
            prods = quickPubService.getProdList(prodConfigPO);
                    
            // ȥ���ظ���NCODE
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
            // ���Ƽ���Ʒ��ӵ�����б���
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
        
        // ����
        if ("1".equals(style))
        {
            // ͳһͼƬ
            return "funclist_unify";
        }
        else
        {
            // �Զ���ͼƬ
            return "funclist";
        }
        
    }
    
    /**
     * ����ҵ���Ƽ���һ�����ܲ˵�
     * 
     * @return
     * @throws Exception
     */
    public String initRecHotspotNext_hub() throws Exception
    {
        HttpSession session = this.getRequest().getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ȡ������Ĳ˵�����
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
        
        // ��ѯ�˵���ӦURL
        url = menu.getGuiobj();
        
        // ����ҵ���Ƽ���һ���˵��Ĺ����б�
        menuHubList2 = this.menuService.qryRecHotspotNext(termInfo, curMenuId);
        
        // ��������
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
        
        // ��ֵ
        menuHubList2 = menuHubList_tmp;
        
        // ����
        if ("1".equals(style))
        {
            // ͳһͼƬ
            return "funclist_unify";
        }
        else
        {
            // �Զ���ͼƬ
            return "funclist";
        }
        
    }
    
    /**
     * ��Ʒ������ҵ������
     * 
     * @return
     * @see
     */
    public String searchCards_hub()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // ��ֵ�ն˻���ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // begin zKF66389 findbus����
        // Ʒ��
        menuInfo.setBrandID(searchCards);
        // end zKF66389 findbus����
        
        List<MenuInfoPO> menus = this.menuService.qryMinChildNode(menuInfo);
                
        // ��������
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
        
        // ��Ʒ���ٷ��������ߣ�ȡsh_prod_config���еĲ�Ʒ��Ϣ
        if ("1".equals(quickPublishFlag))
        {
            NserCustomerSimp customerInfo = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

            ProdConfigPO prodConfigPO = new ProdConfigPO();
            
            // �ն˻���������
            prodConfigPO.setAreaCode(termInfo.getRegion());
            
            // ��ָ��Ʒ������
            prodConfigPO.setBrand(searchCards);
            
            // �ѵ�¼�û���ѯʱ���谴���û��Ĺ��������������Ʒ���й���
            if (null != customerInfo)
            {
                prodConfigPO.setAreaCode(customerInfo.getRegionID());
                prodConfigPO.setMainProd(customerInfo.getProductID());
            }
            
            // ��Ʒ��ѯ:��ѯ��Ʒ���ñ�
            List<ProdConfigPO> prods = quickPubService.getProdList(prodConfigPO);
                    
            // ȥ���ظ���NCODE
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
     * ������ҵ���������ֵ�ƴ������ĸ����ҵ������
     * 
     * @return
     * @see
     */
    public String searchLetter_hub()
    {
        menuMinChildNode = new ArrayList<MenuInfoPO>();
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
                
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // ��ֵ�ն˻���ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // ��ȡ�˵�
        // begin zKF66389 findbus����
        menuInfo.setBrandID(null);
        // end zKF66389 findbus����
        
        List<MenuInfoPO> list = this.menuService.qryMinChildNode(menuInfo);
        
        // ��������
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
        
        // ��Ʒ���ٷ��������ߣ�ȡsh_prod_config���еĲ�Ʒ��Ϣ
        if ("1".equals(quickPublishFlag))
        {
            NserCustomerSimp customerInfo = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

            ProdConfigPO prodConfigPO = new ProdConfigPO();
            
            // �ն˻���������
            prodConfigPO.setAreaCode(termInfo.getRegion());
            
            // �ѵ�¼�û���ѯʱ���谴���û��Ĺ���������Ʒ�ƺ������Ʒ���й���
            if (null != customerInfo)
            {
                prodConfigPO.setAreaCode(customerInfo.getRegionID());
                prodConfigPO.setBrand(customerInfo.getBrandID());
                prodConfigPO.setMainProd(customerInfo.getProductID());
            }
            
            // ��Ʒ��ѯ:��ѯ��Ʒ���ñ�
            List<ProdConfigPO> prods = quickPubService.getProdList(prodConfigPO);
                    
            // ȥ���ظ���NCODE
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
            // ��ȡ��������ĸ
            String str = "";
            
            // ����ת��ƴ������ĸ����ʵ��
            ChineseSpelling chineseSpell = ChineseSpelling.getInstance();
            
            for (int i = 0, n = list.size(); i < n; i++)
            {
                menuInfo = list.get(i);
                
                // sh_prod_config���еĲ�Ʒ
             // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//                if ("1".equals(menuInfo.getIsProd()))
//                {
//                    str = chineseSpell.getSellingHead(menuInfo.getProdConfigPO().getProdName());
//                }
//                else
//                {
//                    str = chineseSpell.getSellingHead(menuInfo.getMenuname());
//                }
                str = ("1".equals(menuInfo.getIsProd())) ? chineseSpell.getSellingHead(menuInfo.getProdConfigPO().getProdName()) : chineseSpell.getSellingHead(menuInfo.getMenuname());
                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
                
                // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
                //if (!"".equals(str) && null != str)
                if (isNotNull(str))
                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
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
        
        // ��ҳ
        menuMinChildNode = getPageList(menuMinChildNode);
        return "searchLetter";
    }
    
    /**
     * �ж��Ƿ�Ϊ��
     * <������ϸ����>
     * @param str
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public boolean isNotNull(String str)
    {
        return !"".equals(str) && null != str;
    }
    
    /**
     * ��ҳ
     * 
     * @param list �˵�����
     * @return
     * @see
     */
    public List<MenuInfoPO> getPageList(List<MenuInfoPO> list)
    {
        int sum=0;
        int start=0;
        int end=0;
        
        // ��ȡ��ǰҳ
        if (page == 0)
        {
            page = 1;
        }
        
        // ��ȡ��ҳ��
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
     * ��ȡ�ն˻���Ϣ
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
     * ��ʾ�����б�_ɽ��
     * 
     * չ��:����ҵ���Ƽ����û����񡢲�Ʒ��ͨ���ʷ��Ƽ�������ҵ�񶩹� ����������ҵ���Ƽ����û����񡢲�Ʒ��ͨ ���� �����Ӳ˵� �������ʷ��Ƽ�������ҵ�񶩹� ���빦��ҳ��
     * 
     * @return
     * @throws Exception
     * @remark create by wWX217192 2014-10-09 V200R003C10LG1001 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ�������Ż�������
     */
    public String initFunctionList_sd() throws Exception
    {
        // modify begin g00140516 2012/06/08 R003C12L05n01 Bug 26719
    	curMenuId = "rec";
        // modify end g00140516 2012/06/08 R003C12L05n01 Bug 26719
        
        String farwed = "";
        
        // �ջ�1��ҵ����ࡢ2��Ʒ�Ʒ��ࡢ3������ĸ����
        if (null == searchType || "".equals(searchType) || "1".equals(searchType))
        {
        	// ��ȡ��������
        	comboMsg_sd = (String)PublicCache.getInstance().getCachedData("comboMsg_sd");
        	VASMsg_sd = (String)PublicCache.getInstance().getCachedData("VASMsg_sd");
        	interBusMsg_sd = (String)PublicCache.getInstance().getCachedData("interBusMsg_sd");
        	
            farwed = "funclist01";
        }
        else if ("2".equals(searchType))
        {
            // ��ȡƷ������
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
     * ������ҵ���������ֵ�ƴ������ĸ����ҵ������
     * 
     * @return
     * @see
     * @remark create by wWX217192 2014-10-10 V200R003C10LG1001 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ�������Ż�������
     */
    public String searchLetter_sd()
    {
        menuMinChildNode = new ArrayList<MenuInfoPO>();
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
                
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // ��ֵ�ն˻���ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // �˵���Ʒ��
        // begin zKF66389 findbus����
        menuInfo.setBrandID(null);
        // end zKF66389 findbus����
        
        // ��ѯ����ն˻��µ����в˵�
        List<MenuInfoPO> list = this.menuService.qryMinChildNode(menuInfo);
        
        // ��������
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
            // ��ȡ��������ĸ
            String str = "";
            
            // ����ת��ƴ������ĸ����ʵ��
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
        
        // ��ҳ
        menuMinChildNode = getPageList(menuMinChildNode);
        return "searchLetter";
    }
    
    /**
     * ��Ʒ������ҵ������
     * 
     * @return
     * @see
     * @remark create by wWX217192 2014-10-13 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ�������Ż�������
     */
    public String searchCards_sd()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // ��ֵ�ն˻���ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // Ʒ��
        // begin zKF66389 findbus����
        menuInfo.setBrandID(searchCards);
        // end zKF66389 findbus����
        
        List<MenuInfoPO> menus = this.menuService.qryMinChildNode(menuInfo);
                
        // ��������
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
     * ����������ҵ������
     * @return
     * @remark create by wWX217192 2014-10-17 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ�������Ż�������
     */
    public String searchGroups_sd()
    {
    	HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        MenuInfoPO menuInfo = new MenuInfoPO();
        
        // ��ֵ�ն˻���ID
        menuInfo.setTermgrpid(getTerminalInfo().getTermgrpid());
        
        // ����
        menuInfo.setType(searchGroups);
        
        // ���շ����ѯɽ��ҵ�����Ĳ˵�
        List<MenuInfoPO> menus = this.menuService.qryGroupsChildNode(menuInfo);
        
        // ��������
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
