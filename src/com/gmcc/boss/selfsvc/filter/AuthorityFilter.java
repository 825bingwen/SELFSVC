/*
 * 文件名：AuthorityFilter.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增，过滤器
 */
package com.gmcc.boss.selfsvc.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.CShPluginVO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.terminfo.service.TerminalInfoService;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 过滤器
 * 
 * @author g00140516
 * 
 */
public class AuthorityFilter implements Filter
{
    private static Log logger = LogFactory.getLog(AuthorityFilter.class);
    
    private FilterConfig filterConfig = null;
    
    // private String errmsg = null;
    
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }
    
    public void destroy()
    {
        this.filterConfig = null;
    }
    
    // private COperator getOperator(HttpServletRequest request)
    // {
    // COperator virtialoper = null;
    // if (request.getSession().getAttribute(Constants.CUR_OPER) != null)
    // {
    // virtialoper = (COperator)request.getSession().getAttribute(Constants.CUR_OPER);
    // }
    // else
    // {
    // PropFileUtil file = PropFileUtil.getInstance();
    // virtialoper = new COperator();
    // virtialoper.m_EntityID = file.getProperty("ProOper");
    // virtialoper.m_Region = file.getProperty("ProOperRegion");
    // virtialoper.m_OrgID = file.getProperty("ProOperOrgid");
    // virtialoper.m_RoamRegion = virtialoper.m_Region;
    // virtialoper.m_RoamOrgID = virtialoper.m_OrgID;
    // }
    // return virtialoper;
    // }
    
    // private boolean getSignInfo(HttpServletRequest request, String ip)
    // {
    // HttpSession session = request.getSession();
    // // COperator operator = getOperator(request);
    //        
    // SelfSvcServer selfSvcServer = ((SelfSvcServerImpl)SelfSvcContextUtils.getBean("selfSvcServer"));
    // ReturnWrap reswrap = selfSvcServer.getSignToInfo(ip);
    //        
    // if (reswrap.getStatus() == 0)
    // {
    // errmsg = reswrap.getReturnMsg();
    // return false;
    // }
    // else
    // {
    // Vector retVec = (Vector)reswrap.getReturnObject();
    // String needSignNew = (String)retVec.get(0);
    // String nextSignTime = (String)retVec.get(1);
    // String unionUserid = (String)retVec.get(2);
    // String unionPayCode = (String)retVec.get(3);
    // String needSign = (String)session.getAttribute(Constants.ISNEEDSIGN);
    // if (needSign == null)
    // {
    // if (needSignNew != null)
    // {
    // session.setAttribute(Constants.ISNEEDSIGN, needSignNew);
    // }
    // else
    // {
    // session.setAttribute(Constants.ISNEEDSIGN, "");
    // return false;
    // }
    // }
    // if (unionUserid != null)
    // {
    // session.setAttribute("unionUserid", unionUserid);
    // }
    // else
    // {
    // session.setAttribute("unionUserid", "");
    // return false;
    // }
    // if (unionPayCode != null)
    // {
    // session.setAttribute("unionPayCode", unionPayCode);
    // }
    // else
    // {
    // session.setAttribute("unionPayCode", "");
    // return false;
    // }
    // if (nextSignTime != null)
    // {
    // session.setAttribute("nextSignTime", nextSignTime);
    // }
    // else
    // {
    // session.setAttribute("nextSignTime", "");
    // return false;
    // }
    // }
    // return true;
    // }
    
    // modify begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
    /**
     * 根据ip或mac查询终端机信息，如果返回null，说明该终端机无权访问自助终端平台
     */    
    protected TerminalInfoPO getTerminalInfoByIPorMAC(String ip, String mac)
    {
        TerminalInfoService service = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");
        
        List info = null;
        
        if (mac == null)
        {
            // 根据ip获取终端机信息
            info = service.getTerminalInfoByIP(ip); 
        }
        else if (ip == null)
        {
            // 根据mac获取终端机信息
            info = service.getTerminalInfoByMAC(mac);
        }        
        // modify end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
        
        if (info == null || info.size() == 0)
        {
            return null;
        }
        
        TerminalInfoPO termInfo = (TerminalInfoPO)info.get(0);
        
        // add begion yKF28472 OR_huawei_201305_474
        if (Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            termInfo.setCityOrgid(service.getCityOrgId(termInfo.getOrgid()));
        }
        // add end yKF28472 OR_huawei_201305_474
        
        // 获取终端机的控件信息
        List pluginInfo = service.getPluginInfo(termInfo);
        if (pluginInfo == null || pluginInfo.size() == 0)
        {
            termInfo.setPlugin("");
        }
        else
        {
            CShPluginVO pluginSetNew = (CShPluginVO)pluginInfo.get(0);
            
            String pluginReg = "";
            String strTermSpecial = termInfo.getTermspecial();

            // 获取插件
            for (int i = 0; i < strTermSpecial.length(); i++)
            {
                char pluginFlag = strTermSpecial.charAt(i);
                if (pluginFlag == '1')
                {
                	
                	// begin zKF66389 2015-09-10 9月份findbugs修改
//                    String pluginId = Constants.pluginIdArray[i];// id
                    String pluginId = Constants.getPluginidarray()[i];// id
//                    String pluginCode = (String)pluginSetNew.getAttributeValue(Constants.pluginKeyArray[i]);// classid
                    String pluginCode = (String)pluginSetNew.getAttributeValue(Constants.getPluginkeyarray()[i]);// classid
                    // begin zKF66389 2015-09-10 9月份findbugs修改

                    if (pluginCode == null || pluginCode.trim().length() == 0)
                    {
                        continue;
                    }
                    
                    // FIREFOX
                    if (Constants.BROWSER_FIREFOX.equalsIgnoreCase(termInfo.getBrowsertype()))
                    {
                        pluginReg += "<object id=\'" + pluginId + "\' type=\'application/" + pluginCode + "\'></object>\n";
                    }
                    // IE
                    else
                    {
                    	//pluginReg += "<object id=\"" + pluginId + "\" classid=\"" + pluginCode + "\"></object>";
                    	
				        if (Constants.UNION_PLUGIN.equals(pluginId))
				        {
				            pluginReg += "<object id=\"" + pluginId + "\" classid=\"" + pluginCode + "\" codeBase=\"hfbmc.cab#1,0,0,5\">";
				            pluginReg += "<param name=\"_Version\" value=\"65536\">";
				            pluginReg += "<param name=\"_ExtentX\" value=\"635\">";
				            pluginReg += "<param name=\"_ExtentY\" value=\"582\">";
				            pluginReg += "<param name=\"_StockProps\" value=\"64\">";
				            pluginReg += "<param name=\"Enabled\" value=\"1\">";
				            pluginReg += "</object>";
				        }
				        else
				        {
				            pluginReg += "<object id=\"" + pluginId + "\" classid=\"" + pluginCode + "\"></object>";
				        }
                    }
                }
            }
            
            termInfo.setPlugin(pluginReg);
        }
        
         //add begin YKF70747 2012/04/28 R003C12L04n01 OR_SD_201203_818
         String termKeyInfo = (String) PublicCache.getInstance().getCachedData(Constants.SH_TERMI_KEY_INFO);
         String termProvMachinemodel = termInfo.getProvidercode() + "," + termInfo.getMachinemodel();
       
         if (null != termKeyInfo && 0 <= termKeyInfo.indexOf(termProvMachinemodel))
         {
             termInfo.setSuppKey(true);
         }           
         //add begin YKF70747 2012/04/28 R003C12L04n01 OR_SD_201203_818        
        
        // 获取终端机的可用菜单ID列表
//        List menuIDList = service.getMenuIDList(termInfo.getTermid());
//        termInfo.setMenuList(menuIDList);
        
        return termInfo;
    }
    
    /**
     * 获取真实的IP
     * 直接使用request.getRemoteAddr()的话，使用apache等负载均衡获取不到真实的IP
     * @param request
     * @return
     */
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /*
	private String getIpAddr(HttpServletRequest request) 
    {      
		String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
        {      
            ip = request.getHeader("Proxy-Client-IP");      
        }      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {      
        	ip = request.getHeader("WL-Proxy-Client-IP");      
        }      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
        {      
        	ip = request.getRemoteAddr();      
        }
        
        return ip;      
	}  
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /**
     * 所有的访问，都必须经过此过滤器
     */
    @SuppressWarnings("unchecked")
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        
        String requestPath = request.getRequestURI();
        
        if (logger.isWarnEnabled())
        {
            logger.warn("SID=[" + request.getSession().getId() + "]; requestPath=[" + requestPath + "].");
        }        
        
        if (requestPath.indexOf("initip.jsp") != -1)
        {
        	filterChain.doFilter(servletRequest, servletResponse);
        	return;
        }
        
        if (request.getSession().getAttribute("basePath") == null)
        {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    
            // 框架页面初始化时把服务器目录放到session中
            request.getSession().setAttribute("basePath",basePath);
        }

    	// 不合法的IP，不需要再获取终端信息
        if (requestPath.indexOf("invalidIP.jsp") == -1)
        {
            // 验证终端机信息
        	validateTerminalInfo(request, response, session);
        }
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
               
        //获取当前菜单
        String currMenuID = request.getParameter(Constants.CUR_MENUID);
        if (currMenuID == null || "".equals(currMenuID.trim()))
        {
            currMenuID = "root";
        }
        
        // modify begin wWX217192 2015-06-05 for 91777 存在“跨站点脚本编制”安全问题
        try
        {
            // 获取参数列表
            Map<String, String[]> map = request.getParameterMap();
            
            // 遍历参数
            for (Entry<String, String[]> entry : map.entrySet())
            {
                // 获取参数的值
                String[] parameter = entry.getValue();
                
                for (int i = 0; i < parameter.length; i++)
                {
                    String p = parameter[i];
                    String error = checkInput(p.trim());// 判断是否包含非法字符
                    if (!"".equals(error))
                    {
                    	// 特殊字符清空处理：找到有特殊字符的KEY进行清空
                    	request.setAttribute(entry.getKey(), "");
                    	
                        // 如果返回值不为空，则包含非法字符
                    	request.setAttribute("errormessage", "您输入的信息包含非法字符 ，请重新输入！");
                    	
                    	request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                    	
                        return;
                    }
                }
            }
        }
        catch (Exception e)
        {
        	// 如果返回值不为空，则包含非法字符
        	request.setAttribute("errormessage", "filter parameters error!");
        	
        	request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
            
        	return;
        }
        // modify end wWX217192 2015-06-05 for 91777 存在“跨站点脚本编制”安全问题
        
        if (needFilter(requestPath))
        {
            MenuInfoPO menu = null;
            
            List menuList = (List)PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
            if (menuList != null && menuList.size() > 0)
            {
                for (int i = 0; i < menuList.size(); i++)
                {
                    menu = (MenuInfoPO)menuList.get(i);
                    if (currMenuID.equals(menu.getMenuid()))
                    {
                        break;
                    }
                    else
                    {
                        menu = null;
                    }
                }
            }
            else
            {
                logger.error("无法从缓存中获取到菜单信息");
                
                //modify begin l00190940 2011-12-9 变更错误转向页面
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                //modify end l00190940 2011-12-9 变更错误转向页面
                
                return;
            }
            
            if (menu == null)
            {
                logger.error("根据菜单ID(" + currMenuID + ")取不到对应的菜单信息");
                
                //modify begin l00190940 2011-12-9 变更错误转向页面
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                //modify end l00190940 2011-12-9 变更错误转向页面
                
                return;
            }
            
            // 判断用户是否可以使用此功能，不能使用，显示同级菜单
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            // begin zKF66389 findbus清零
            if (customerSimp != null && !"ALL".equalsIgnoreCase(menu.getBrandID().trim())
                    && !menu.getBrandID().trim().equalsIgnoreCase(customerSimp.getBrandID().trim()))
            // end zKF66389 findbus清零
            {
                logger.warn("用户" + customerSimp.getServNumber() + "不能使用功能" + menu.getMenuname());
                
                request.setAttribute("errormessage", customerSimp.getBrandName() + "用户不能使用此功能");
                
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                
                return;
            }
            
        	// 停机用户限制操作
            if (Constants.PROOPERORGID_SD.equals(province))
            {
                int bz = 0;
	            if (customerSimp != null && !"root".equals(menu.getMenuid()))
	            {
	                bz = 0;
	            	SelfSvcService selfsvcService = (SelfSvcService)ApplicationContextUtil.getBean("selfSvcService");;
	            	List<DictItemPO> dictItemList = selfsvcService.getDictItemByGrp(customerSimp.getStatus()+"_IN");
	            	for(DictItemPO dictItemPO : dictItemList)
	            	{
	                	if(dictItemPO.getDictid().equals(menu.getMenuid()))
	                	{   
	                		bz = 1;
	                	}
	            	}
	            	if (bz == 0)
	            	{
	            	    List<DictItemPO> suggestiveInfoList = selfsvcService.getDictItemByGrp("SuggestiveInfo");
                        
                        for (DictItemPO suggestiveInfo : suggestiveInfoList)
                        {
                            if (suggestiveInfo.getDictid().equals(customerSimp.getStatus()))
                            {
                                logger.error(suggestiveInfo.getDictname());
                                
                                request.setAttribute("errormessage",suggestiveInfo.getDictname());
                                
                                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                                
                                return;
                            }
                        }
	            	}
	            }
            }
            
            // 功能要求的认证方式
            String authCode = menu.getAuthcode();
            
            //add begin CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
            // 湖北，菜单对应的authcode是1000
            if (Constants.PROOPERORGID_HUB.equals(province) && "1000".equals(authCode))
            {
                // 用户未登录
                if (customerSimp == null)
                {
                    request.getRequestDispatcher("/jsp/login/servicepassword_hub.jsp").forward(request, response);
                    return;
                }
                // 已登录
                else
                {
                    String loginMark = customerSimp.getLoginMark().substring(0, 2);
                    
                    if ("00".equals(loginMark))
                    {
                        request.getRequestDispatcher("/jsp/login/servicepassword_hub.jsp").forward(request, response);
                        return;
                    }
                }
            }
            //add end CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
            
            /*
             * 首页面和0000都不需要对用户登录方式进行验证
             */
            if (!"root".equals(currMenuID) && !"0000".equals(authCode))
            {
                // 还需要进行的身份认证方式
                String resultCode = "";
                
                if (customerSimp == null || "0000".equals(customerSimp.getLoginMark()))
                {
                    resultCode = authCode;
                }
                else
                {
                    resultCode = CommonUtil.getAuthorizationCode(customerSimp.getLoginMark(), authCode);
                }
                
                String path = request.getContextPath();
                
                // 如果需要进行服务密码认证，进入服务密码认证页面
                if (resultCode.charAt(0) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/servicepassword.jsp").forward(request, response);
                    
                    return;
                }
                // 随机密码认证页面
                else if (resultCode.charAt(1) == '1')
                {
                	//modify begin m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
                    //要过滤随机密码验证的菜单id串和产品id串
                    String menuId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_MENU);
                    String ProId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_PRO);
                    
                    //获取当前用户的产品id
                    String usrProId = "";
                    if (customerSimp != null)
                    {
                    	usrProId = (String) customerSimp.getProductID();
                    }                   
                    
                    //条件不满足，执行随机密码验证
                	if (!CommonUtil.filterMenu(menuId, currMenuID) || !CommonUtil.filterProd(ProId, usrProId))
                	{
                		response.sendRedirect(path + "/login/randomPwd.action?" + Constants.CUR_MENUID + "=" + currMenuID);
                	    return;
                	}
                	//modify end m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
                }
                // 身份证认证页面
                else if (resultCode.charAt(2) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/id.jsp").forward(request, response);
                    
                    return;
                }
                // 手机钱包认证页面
                else if (resultCode.charAt(3) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/rfid.jsp").forward(request, response);
                    
                    return;
                }                
            }
            // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
            if (customerSimp != null)
            {
                // 菜单支持营销推荐活动标志
                String actFlag = "";
                
                // 已向用户推荐活动标志
                String alreadyRecFlag = (String)session.getAttribute(Constants.ALREADY_REC_FLAG);
                
                // modify begin hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
                
                // 支持营销推荐活动的菜单
                List<DictItemPO> actMenuList = null;
                
                // 原营销推荐活动开关 1：开  0：关 
                String recommendSwitchOld = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND);
                
                // 新营销推荐活动开关1：开  0：关
                String recommendSwitchNew = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND_NEW);
                
                // 新营销推荐活动
                if("1".equals(recommendSwitchNew))
                {
                    // 获取支持新营销推荐活动的菜单
                    actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENUNEW);
                }
                // 原营销推荐活动
                else if("1".equals(recommendSwitchOld))
                {
                    // 获取支持旧营销推荐活动的菜单
                    actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENU);
                }
                    
                // 支持营销推荐活动的菜单
                if (actMenuList != null)
                {
                    for (int i = 0; i < actMenuList.size(); i++)
                    {
                        DictItemPO dictItemPO = actMenuList.get(i);
                        if (currMenuID.equals(dictItemPO.getDictid()))
                        {
                            actFlag = "1";
                            break;
                        }
                    }
                }
            
                // 支持营销推荐并本次登录没有推荐过
                if("1".equals(actFlag) && !"1".equals(alreadyRecFlag))
                {
                    session.setAttribute(Constants.ALREADY_REC_FLAG, "1");
       
                    // 新的主动营销活动
                    if("1".equals(recommendSwitchNew))
                    {
                        response.sendRedirect(request.getContextPath()+"/recommendActivity/qryRecommendActList.action?curMenuId=" + currMenuID);
                    }
                    // 原营销推荐活动
                    else if("1".equals(recommendSwitchOld))
                    {
                        response.sendRedirect(request.getContextPath()+"/recommendProduct/getRecommendProductList.action?curMenuId=" + currMenuID);
                    }
                    
                    return;
                }
                // modify end hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
            }  
            // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
        }
        // 如果从热点进入后增加标志
        else if (Constants.PROOPERORGID_HUB.equals(province))
        {
            // 终端信息
            TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            List menuList = (List)PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
            
            MenuInfoPO menuInfo = null;
            if (menuList != null && menuList.size() > 0)
            {
                for (int i = 0; i < menuList.size(); i++)
                {
                    menuInfo = (MenuInfoPO)menuList.get(i);
                    if (currMenuID.equals(menuInfo.getMenuid()))
                    {
                        break;
                    }
                }
            }
            
            // 加入session
            if ("recHotspot".equals(currMenuID))
            {
                if (session.getAttribute(terminalInfo.getTermid()+"_recHotspot") == null)
                {
                    session.setAttribute(terminalInfo.getTermid()+"_recHotspot", "1");
                }
            }
            else
            {
                
                if (menuInfo != null && ("root".equals(menuInfo.getParentid()) || "rec".equals(menuInfo.getParentid()) ))
                {
                    if (session.getAttribute(terminalInfo.getTermid()+"_recHotspot") != null)
                    {
                        session.removeAttribute(terminalInfo.getTermid()+"_recHotspot");
                    }
                }
            }
        }
               
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    /**
     * 验证输入的信息
     * 
     * @param input 输入信息
     * @return String
     * @see [类、类#方法、类#成员]
     */
    private String checkInput(String input)
    {
        StringBuffer buff = new StringBuffer();
        if (null == input || "".equals(input))
        {
            return buff.toString();
        }
        
        // 将所有的入参转换为小写
        input = input.toLowerCase(Locale.US);
        
        // 取得需要过滤的特殊符号
        String unusedSign = getPrefixDictItem("UnusedSign");
        
        // 过滤特殊字符
        if (StringUtils.isNotEmpty(unusedSign))
        {
            char[] unusedSigns = unusedSign.toCharArray();
            for (int i = 0; i < unusedSigns.length; i++)
            {
                if ("".equals(String.valueOf(unusedSigns[i])))
                {
                    continue;
                }
                
                // 判断是否包含特殊字符
                if (input.indexOf(unusedSigns[i]) > -1)
                {
                    buff.append(replaceChar(unusedSigns[i]));
                    break;
                }
            }
            
            if (!"".equals(buff.toString()))
            {
                return buff.toString();
            }
        }
        
        // 取得需要过滤的关键字(JS或数据库)
        String keyWord = getPrefixDictItem("KeyWord");
        
        // 过滤关键字(JS或数据库)
        if (StringUtils.isNotEmpty(keyWord))
        {
            String[] keyWords = keyWord.split(",");
            
            for (int i = 0; i < keyWords.length; i++)
            {
                if ("".equals(keyWords[i]))
                {
                    continue;
                }
                
                // 判断是否包含关键字
                if (input.indexOf(keyWords[i].toLowerCase(Locale.US)) > -1)
                {
                    buff.append(keyWords[i]);
                    break;
                }
            }
        }
        
        return buff.toString();
    }
    
    /**
     * 取得GroupId='SpecialChar'数据字典DictId为传入前缀的描述信息组合
     * 
     * @param prefixDictId DictId前缀
     * @return 返回描述信息
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
	private String getPrefixDictItem(String prefixDictId)
    {
        // 取得特殊字符字典项
        List<DictItemPO> specialChars = (List<DictItemPO>)PublicCache.getInstance().getCachedData("SpecialChar");
        // 定义返回字典项描述连接字串
        StringBuffer joinValue = new StringBuffer();
        
        if (specialChars != null)
        {
            for (DictItemPO specialChar : specialChars)
            {
                // 判断是否是传入的以prefixDictId为前缀的字典项
                if (StringUtils.contains(specialChar.getDictid(), prefixDictId))
                {
                    joinValue.append(specialChar.getDescription()).append(",");
                }
            }
        }
        
        if(joinValue.length() > 0)
        {
            
            // modify begin qWX279398 2015年7月20日17:16:37 去掉方法后面的toString()方法，findbugs修改 
        	return joinValue.substring(0, joinValue.length()-1);
        	// modify end qWX279398 2015年7月20日17:16:37 去掉方法后面的toString()方法，findbugs修改 
        	
        }
        else
        {
        	return joinValue.toString();
        }
    }
    
    /**
     * 替换特殊字符
     * 
     * @param c
     * @return
     */
    private String replaceChar(char c)
    {
        String string = "";
        
        switch (c)
        {
            case '<':
                string = "&lt;";
                break;
            case '>':
                string = "&gt;";
                break;
            case '&':
                string = "&amp;";
                break;
            case '"':
                string = "&quot;";
                break;
            default:
                string = String.valueOf(c);
                break;
        }
        return string;
    }
    
    /** 
     * 验证终端机信息
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param session HttpSession
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    protected void validateTerminalInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session)
        throws ServletException, IOException
    {
        // 从session中获取终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String lockTerm = request.getParameter("lockTerm");
        
        /**
         * 在原有的业务逻辑上增加后台监控锁定和解锁功能：
         * 1.判断lockTerm参数是否存在，如果存在则重新获取终端机信息，能获取到就走正常的业务流程，获取不到就转到锁定页面。
         * 2.锁定页面定时判断终端机状态，如果终端机状态为可用，则转到终端首页面。如果不可用则继续转到锁定页面。
         */ 
        if (lockTerm != null || terminalInfo == null)
        {
            // modify begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
            String flagMsg = "";
            
            // 获取终端机区分标志（IP或者MAC）
            String machineFlag = (String) PublicCache.getInstance().getCachedData("machineFlag");                
            if ("IP".equalsIgnoreCase(machineFlag))
            {
                String ip = (String) request.getSession().getAttribute("termIP");
                
                if (null == ip || "".equals(ip))
                {
                    ip = request.getParameter("termIP");
                    
                    request.getSession().setAttribute("termIP", ip);
                }
                
                logger.info("当前IP=[" + ip + "].");
                
                // 判断是否获取终端机信息
                if (null == ip || "".equals(ip))
                {
                    logger.error("当前会话[" + request.getSession().getId() + "]已丢失,获取不到终端IP!");
                    request.setAttribute("errormessage", "系统故障,当前会话已丢失,系统重新获取会话, 请稍候...");
                    request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                    return;
                }
                
                flagMsg = "IP(" + ip + ")";
                
                // 通过终端IP获取终端机信息
                terminalInfo = getTerminalInfoByIPorMAC(ip, null);
            }
            else
            {
                String mac = (String) request.getSession().getAttribute("termMac");
                
                if (null == mac || "".equals(mac))
                {
                    mac = request.getParameter("termMac");
                    
                    request.getSession().setAttribute("termMac", mac);
                }
                
                logger.info("当前MAC=[" + mac + "].");
                
                // 判断是否获取终端机信息
                if (null == mac || "".equals(mac))
                {
                    logger.error("当前会话已丢失,获取不到终端MAC!");
                    request.setAttribute("errormessage", "系统故障,当前会话已丢失,系统重新获取会话, 请稍候...");
                    request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                    return;
                }
                
                flagMsg = "MAC(" + mac + ")";
                
                // 通过终端MAC获取终端机信息
                terminalInfo = getTerminalInfoByIPorMAC(null, mac); 
                
                if (terminalInfo != null)
                {
                    String newIPAddr = (String) request.getParameter("termIP");
                    if (newIPAddr == null)
                    {
                        newIPAddr = "";
                    }
                    
                    String oldIPAddr = terminalInfo.getIpaddr();
                    if (oldIPAddr == null)
                    {
                        oldIPAddr = "";
                    }
                    
                    //如果新IP不为空，并且与原IP不一致，则判断新IP是否重复。IP重复，报错；IP不重复，更新终端机信息
                    if (!"".equals(newIPAddr) && !newIPAddr.trim().equals(oldIPAddr.trim()))
                    {
                        terminalInfo.setIpaddr(newIPAddr);
                        
                        TerminalInfoService service = (TerminalInfoService) ApplicationContextUtil.getBean("terminalInfoService");
                        
                        // 判断终端机IP是否唯一
                        if (service.isTerminalExisted(terminalInfo))
                        {
                            String errMsg = "IP地址" + newIPAddr + "已存在，系统不允许重复，请检查网络配置。";
                            logger.error(errMsg);
                            request.setAttribute("errormessage", errMsg);
                            request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                            return;
                        }
                        
                        // 根据mac更新SH_TERM_CONFIG表IP
                        service.updateTermConfigIp(terminalInfo);
                    }
                }
            }
            
            String lockedFlag = (String) PublicCache.getInstance().getCachedData("SH_LOCKED_FLAG");
            
            // 判断是否获取终端机信息
        	if (terminalInfo == null)
        	{
            	logger.error("根据" + flagMsg + "进行终端验证，失败。");
            	request.setAttribute("errormessage", "终端机信息获取失败，无法提供服务");
                request.getRequestDispatcher("/invalidIP.jsp").forward(request, response);
                return;
        	}
        	// add begin g00140516 2013/02/16 R003C13L02n01 终端机锁定时，提示“暂停提供服务”
        	else if ("1".equals(lockedFlag) && "1".equals(terminalInfo.getIsLocked()))
        	{
        		logger.error("终端机" + terminalInfo.getIpaddr() + "被锁定，暂停提供服务。");
            	request.setAttribute("errormessage", "抱歉，本终端暂停提供服务");
                request.getRequestDispatcher("/locked.jsp").forward(request, response);
                return;
        	}
        	// add end g00140516 2013/02/16 R003C13L02n01 终端机锁定时，提示“暂停提供服务”
        	// 判断终端机是否有可用的菜单列表信息
        	else
        	{
        	    //所属终端组
        	    String termGroupID = terminalInfo.getTermgrpid();
        	    
        	    List<MenuInfoPO> menus = null;
        	    
        	    if (termGroupID != null && !"".equals(termGroupID))
        	    {
        	        //根据终端组自缓存中获取菜单列表
        	        menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(termGroupID);
        	    }
        	    
        	    if (menus == null || menus.size() == 0)
        	    {
        	        logger.error("根据" + flagMsg + "进行终端验证，成功。但是无法获取终端可用的菜单列表，请检查数据库配置。");
        	        request.setAttribute("errormessage", "终端机信息获取失败，无法提供服务");
        	        request.getRequestDispatcher("/invalidIP.jsp").forward(request, response);
                    return;
        	    }
        	    else
        	    {
        	        session.setAttribute(Constants.TERMINAL_INFO, terminalInfo);
        	        
        	        if (logger.isWarnEnabled())
        	        {
        	            logger.warn("终端机IP=[" + terminalInfo.getIpaddr() + "]; SID=[" + session.getId() + "]");
        	        }
        	    }
        	}
        	// modify end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
        }
    }
    
    /**
     * 部分页面、功能不需要过滤
     * 
     * @param request
     * @return
     * @see
     */
    private boolean needFilter(String requestPath)
    { 
        /*if (requestPath.indexOf("invalidIP.jsp") != -1 
                || requestPath.indexOf("errorIP.jsp") != -1
        		|| requestPath.indexOf("commonError.jsp") != -1
                || requestPath.indexOf("servicepassword.jsp") != -1 
                || requestPath.indexOf("randomcode.jsp") != -1
                || requestPath.indexOf("editPassword.jsp") != -1
                || requestPath.indexOf("editPasswordByRandomPwd.action") != -1
                || requestPath.indexOf("editPassword.action") != -1
                || requestPath.indexOf("randomcode_cq.action") != -1
                || requestPath.indexOf("randomcode_cq.jsp") != -1
                || requestPath.indexOf("loginWithRandom_cq.action") != -1
                || requestPath.indexOf("id.jsp") != -1 
                || requestPath.indexOf("rfid.jsp") != -1
                || requestPath.indexOf("userLoginWithPwd.action") != -1
                || requestPath.indexOf("backForward.action") != -1 
                || requestPath.indexOf("randomPwd.action") != -1
                || requestPath.indexOf("userLoginWithRandomPwd.action") != -1
                || requestPath.indexOf("selfScreen.jsp") != -1
                || requestPath.indexOf("selfAdv.jsp") != -1
                || requestPath.indexOf("selfAdvUpdate.jsp") != -1
                || requestPath.indexOf("frame.jsp") != -1
                || requestPath.indexOf("getMediaScList.action") != -1
                || requestPath.indexOf("userLoginWithID.action") != -1
        	    || requestPath.indexOf("authorityError.jsp") != -1
                || requestPath.indexOf("recHotspot_hub.action") != -1
                || requestPath.indexOf("receptionFunc.action") != -1
                || requestPath.indexOf("receptionFunc_hub.action") != -1
                || requestPath.indexOf("recHotspotNext_hub.action") != -1
                || requestPath.indexOf("searchCards_hub.action") != -1
                || requestPath.indexOf("searchLetter_hub.action") != -1
                || requestPath.indexOf("valiPrint.action") != -1
                //add begin CKF76106 2012/07/03 R003C12L07n01 OR_HUB_201206_597    
                || requestPath.indexOf("getProdList.action") != -1
                || requestPath.indexOf("prodDetail.action") != -1
                //add end CKF76106 2012/07/03 R003C12L07n01 OR_HUB_201206_597
                //add begin CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
                || requestPath.indexOf("goRamdomPage_hub.action") != -1
                || requestPath.indexOf("goServicePwdPage_hub.action") != -1
                || requestPath.indexOf("loginWithRandomPwd_hub.action") != -1
                //add end CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875  
                //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                || requestPath.indexOf("recommendProduct.action") != -1  
                || requestPath.indexOf("contineRec.action") != -1 )
                //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
            
                
        {*/
    	// add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
    	List<String> pathList = new ArrayList<String>();
    	pathList.add("invalidIP.jsp");
    	pathList.add("errorIP.jsp");
    	pathList.add("commonError.jsp");
    	pathList.add("servicepassword.jsp");
    	pathList.add("randomcode.jsp");
    	pathList.add("editPassword.jsp");
    	pathList.add("editPasswordByRandomPwd.action");
    	pathList.add("editPassword.action");
    	pathList.add("randomcode_cq.action");
    	pathList.add("randomcode_cq.jsp");
    	pathList.add("loginWithRandom_cq.action");
    	pathList.add("id.jsp");
    	pathList.add("rfid.jsp");
    	pathList.add("userLoginWithPwd.action");
    	pathList.add("backForward.action");
    	pathList.add("randomPwd.action");
    	pathList.add("userLoginWithRandomPwd.action");
    	pathList.add("selfScreen.jsp");
    	pathList.add("selfAdv.jsp");
    	pathList.add("selfAdvUpdate.jsp");
    	pathList.add("frame.jsp");
    	pathList.add("getMediaScList.action");
    	pathList.add("userLoginWithID.action");
    	pathList.add("authorityError.jsp");
    	pathList.add("recHotspot_hub.action");
    	pathList.add("receptionFunc.action");
    	pathList.add("receptionFunc_hub.action");
    	pathList.add("recHotspotNext_hub.action");
    	pathList.add("searchCards_hub.action");
    	pathList.add("searchLetter_hub.action");
    	pathList.add("valiPrint.action");
    	pathList.add("getProdList.action");
    	pathList.add("prodDetail.action");
    	pathList.add("goRamdomPage_hub.action");
    	pathList.add("goServicePwdPage_hub.action");
    	pathList.add("loginWithRandomPwd_hub.action");
    	pathList.add("recommendProduct.action");
    	pathList.add("contineRec.action");
    	
    	for (String str : pathList)
    	{
    		if (requestPath.indexOf(str) != -1)
    		{
    			return false;
    		}
    	}
    	// add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
    	return true;
    }
    
    /**
     * 判断当前菜单需不需要过滤
     * @param str 参数表中配置的需要过滤的菜单串
     * @param menuid 当前菜单
     * @return true：需要过滤
     * @remark create m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
     */
    /*private boolean filterMenu(String str, String menuid)
    {
    	boolean flag = false;
    	
    	if (str == null || "".equals(str.trim()) || menuid == null || "".equals(menuid.trim()))
    	{
    		return flag;
    	}
    	
    	String[] menus = str.split(",");
    	for (int i = 0; i < menus.length; i++)
    	{
    		if (menus[i].equalsIgnoreCase(menuid))
    		{
    			flag = true;
    			break;
    		}
    	}
    	
    	return flag;
    }*/
    
    /**
     * 判断当前产品需不需要过滤
     * @param str 参数表中需要过滤的产品id
     * @param prodid 当前产品id
     * @return true：当前产品需要过滤 false：当前产品不需要过滤
     * @remark create m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
     */
    /*private boolean filterProd(String str, String prodid)
    {
    	boolean flag = false;
    	
    	if (str == null || "".equals(str.trim()) || prodid == null || "".equals(prodid.trim()))
    	{
    		return flag;
    	}
    	
    	String[] prods=str.split(",");
    	for (int i = 0; i < prods.length; i++)
    	{
    		if (prods[i].equalsIgnoreCase(prodid))
    		{
    			flag = true;
    			break;
    		}
    	}
    	
    	return flag;
    }*/
}
