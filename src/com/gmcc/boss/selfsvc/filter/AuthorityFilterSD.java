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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 过滤器
 * 
 * @author g00140516
 * 
 */
public class AuthorityFilterSD extends AuthorityFilter
{
    private static Log logger = LogFactory.getLog(AuthorityFilterSD.class);
    
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
        
        // modify begin wWX217192 2015-06-05 for bug 91777 存在“跨站点脚本编制”安全问题
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
        // modify end wWX217192 2015-06-05 for bug 91777 存在“跨站点脚本编制”安全问题
        
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
            // begin zKF66389 findbus清零
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
            
            // 增加可选认证方式
            // 功能要求的可选认证方式
            String availableAuthCode = menu.getAvailableAuthCode();
            
            // 功能要求的认证方式
            String authCode = menu.getAuthcode();
            
            /*
             * 首页面不需要对用户登录方式进行验证;
             * AVAILABLEAUTHCODE、AUTHCODE均为0000时，不需要对用户登录方式进行验证
             */
            if (!"root".equals(currMenuID) && !("0000".equals(availableAuthCode) && "0000".equals(authCode)))
            {
            	// 设置usimFlag标志位，判断此处是否需要进行usim卡更换的提示
            	if(customerSimp == null )
            	{
            		session.setAttribute("usimFlag", "1");
            	}
                /*
                 * 菜单对应的可选认证方式
                 */
                if (!"0000".equals(availableAuthCode))
                {
                    // 用户需要进行的认证方式
                    String resultAvaiCode = CommonUtil.getIntersectionCode(customerSimp, currMenuID, availableAuthCode);
                    if (!"0000".equals(resultAvaiCode))
                    {
                        // 如果还需要进行服务密码认证，进入服务密码认证页面
                        if (resultAvaiCode.charAt(0) == '1')
                        {
                            request.getRequestDispatcher("/jsp/login/servicepassword.jsp?authCodeType=optional&resultAvaiCode=" + resultAvaiCode).forward(request, response);
                            
                            return;
                        }
                        // 随机密码认证页面
                        else if (resultAvaiCode.charAt(1) == '1')
                        {
                            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
                            request.getRequestDispatcher("/jsp/login/optionalmode/randomPwd.jsp?authCodeType=optional&resultAvaiCode=" + resultAvaiCode).forward(request, response);
                            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
                            return; 
                        }
                        // 身份证认证页面
                        else if (resultAvaiCode.charAt(2) == '1')
                        {
                            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
                            request.getRequestDispatcher("/jsp/login/id.jsp?authCodeType=optional&resultAvaiCode=" + resultAvaiCode).forward(request, response);
                            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
                            
                            return;
                        } 
                    }
                }
                
                /*
                 * 用户进行必选认证方式认证
                 */
                if (!"0000".equals(authCode))
                {
                    // 还需要进行的身份认证方式
                    String resultCode = "";
                    
                    // 用户未认证，转到第一种必选认证方式页面
                    if (customerSimp == null || "0000".equals(customerSimp.getLoginMark()))
                    {
                        resultCode = authCode;
                    }
                    // 用户已认证
                    else
                    {
                        resultCode = CommonUtil.getAuthorizationCode(customerSimp.getLoginMark(), authCode);
                    }
                    
                    String path = request.getContextPath();
                    
                    // 如果还需要进行服务密码认证，进入服务密码认证页面
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
                }               
            }
            // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
            if (customerSimp != null)
            {
                // 菜单支持营销推荐活动标志
                String actFlag = "";
                // 已向用户推荐活动标志
                String alreadyRecFlag = (String)session.getAttribute(Constants.ALREADY_REC_FLAG);
       
                // 支持营销推荐活动的菜单
                List<DictItemPO> actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENU);
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
            
                if("1".equals(actFlag) && !"1".equals(alreadyRecFlag))
                {
                    session.setAttribute(Constants.ALREADY_REC_FLAG, "1");
       
                    response.sendRedirect(request.getContextPath()+"/recommendProduct/getRecommendProductList.action?curMenuId=" + currMenuID);
                    
                    return;
                }
            }  
            // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
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
            String[] unusedSigns = unusedSign.split("");
            
            for (int i = 0; i < unusedSigns.length; i++)
            {
                if ("".equals(unusedSigns[i]))
                {
                    continue;
                }
                
                // 判断是否包含特殊字符
                if (input.indexOf(unusedSigns[i]) > -1)
                {
                    buff.append(replaceChar(unusedSigns[i].charAt(0)));
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
                || requestPath.indexOf("contineRec.action") != -1 
                //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                
                // 增加可选认证方式
                || requestPath.indexOf("goServicePwdPage.action") != -1
                || requestPath.indexOf("goRandomPwdPage.action") != -1
                || requestPath.indexOf("goIDPage.action") != -1 
                || requestPath.indexOf("loginWithRandomPwdNew.action") != -1
                // 增加可选认证方式    
                
            )
                
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
    	
    	// 增加可选认证方式
    	pathList.add("goServicePwdPage.action");
    	pathList.add("goRandomPwdPage.action");
    	pathList.add("goIDPage.action");
    	pathList.add("loginWithRandomPwdNew.action");
    	
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
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /*
    private boolean filterMenu(String str, String menuid)
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
    }
    */
    
    /**
     * 判断当前产品需不需要过滤
     * @param str 参数表中需要过滤的产品id
     * @param prodid 当前产品id
     * @return true：当前产品需要过滤 false：当前产品不需要过滤
     * @remark create m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
     */
    /*
    private boolean filterProd(String str, String prodid)
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
    }
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
}
