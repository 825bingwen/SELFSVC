/*
 * �ļ�����AuthorityFilter.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�������������
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
 * ������
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
     * ����ip��mac��ѯ�ն˻���Ϣ���������null��˵�����ն˻���Ȩ���������ն�ƽ̨
     */    
    protected TerminalInfoPO getTerminalInfoByIPorMAC(String ip, String mac)
    {
        TerminalInfoService service = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");
        
        List info = null;
        
        if (mac == null)
        {
            // ����ip��ȡ�ն˻���Ϣ
            info = service.getTerminalInfoByIP(ip); 
        }
        else if (ip == null)
        {
            // ����mac��ȡ�ն˻���Ϣ
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
        
        // ��ȡ�ն˻��Ŀؼ���Ϣ
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

            // ��ȡ���
            for (int i = 0; i < strTermSpecial.length(); i++)
            {
                char pluginFlag = strTermSpecial.charAt(i);
                if (pluginFlag == '1')
                {
                	
                	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                    String pluginId = Constants.pluginIdArray[i];// id
                    String pluginId = Constants.getPluginidarray()[i];// id
//                    String pluginCode = (String)pluginSetNew.getAttributeValue(Constants.pluginKeyArray[i]);// classid
                    String pluginCode = (String)pluginSetNew.getAttributeValue(Constants.getPluginkeyarray()[i]);// classid
                    // begin zKF66389 2015-09-10 9�·�findbugs�޸�

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
        
        // ��ȡ�ն˻��Ŀ��ò˵�ID�б�
//        List menuIDList = service.getMenuIDList(termInfo.getTermid());
//        termInfo.setMenuList(menuIDList);
        
        return termInfo;
    }
    
    /**
     * ��ȡ��ʵ��IP
     * ֱ��ʹ��request.getRemoteAddr()�Ļ���ʹ��apache�ȸ��ؾ����ȡ������ʵ��IP
     * @param request
     * @return
     */
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
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
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
    /**
     * ���еķ��ʣ������뾭���˹�����
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
    
            // ���ҳ���ʼ��ʱ�ѷ�����Ŀ¼�ŵ�session��
            request.getSession().setAttribute("basePath",basePath);
        }

    	// ���Ϸ���IP������Ҫ�ٻ�ȡ�ն���Ϣ
        if (requestPath.indexOf("invalidIP.jsp") == -1)
        {
            // ��֤�ն˻���Ϣ
        	validateTerminalInfo(request, response, session);
        }
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
               
        //��ȡ��ǰ�˵�
        String currMenuID = request.getParameter(Constants.CUR_MENUID);
        if (currMenuID == null || "".equals(currMenuID.trim()))
        {
            currMenuID = "root";
        }
        
        // modify begin wWX217192 2015-06-05 for 91777 ���ڡ���վ��ű����ơ���ȫ����
        try
        {
            // ��ȡ�����б�
            Map<String, String[]> map = request.getParameterMap();
            
            // ��������
            for (Entry<String, String[]> entry : map.entrySet())
            {
                // ��ȡ������ֵ
                String[] parameter = entry.getValue();
                
                for (int i = 0; i < parameter.length; i++)
                {
                    String p = parameter[i];
                    String error = checkInput(p.trim());// �ж��Ƿ�����Ƿ��ַ�
                    if (!"".equals(error))
                    {
                    	// �����ַ���մ������ҵ��������ַ���KEY�������
                    	request.setAttribute(entry.getKey(), "");
                    	
                        // �������ֵ��Ϊ�գ�������Ƿ��ַ�
                    	request.setAttribute("errormessage", "���������Ϣ�����Ƿ��ַ� �����������룡");
                    	
                    	request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                    	
                        return;
                    }
                }
            }
        }
        catch (Exception e)
        {
        	// �������ֵ��Ϊ�գ�������Ƿ��ַ�
        	request.setAttribute("errormessage", "filter parameters error!");
        	
        	request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
            
        	return;
        }
        // modify end wWX217192 2015-06-05 for 91777 ���ڡ���վ��ű����ơ���ȫ����
        
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
                logger.error("�޷��ӻ����л�ȡ���˵���Ϣ");
                
                //modify begin l00190940 2011-12-9 �������ת��ҳ��
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                //modify end l00190940 2011-12-9 �������ת��ҳ��
                
                return;
            }
            
            if (menu == null)
            {
                logger.error("���ݲ˵�ID(" + currMenuID + ")ȡ������Ӧ�Ĳ˵���Ϣ");
                
                //modify begin l00190940 2011-12-9 �������ת��ҳ��
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                //modify end l00190940 2011-12-9 �������ת��ҳ��
                
                return;
            }
            
            // �ж��û��Ƿ����ʹ�ô˹��ܣ�����ʹ�ã���ʾͬ���˵�
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            // begin zKF66389 findbus����
            if (customerSimp != null && !"ALL".equalsIgnoreCase(menu.getBrandID().trim())
                    && !menu.getBrandID().trim().equalsIgnoreCase(customerSimp.getBrandID().trim()))
            // end zKF66389 findbus����
            {
                logger.warn("�û�" + customerSimp.getServNumber() + "����ʹ�ù���" + menu.getMenuname());
                
                request.setAttribute("errormessage", customerSimp.getBrandName() + "�û�����ʹ�ô˹���");
                
                request.getRequestDispatcher("/authorityError.jsp").forward(request, response);
                
                return;
            }
            
        	// ͣ���û����Ʋ���
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
            
            // ����Ҫ�����֤��ʽ
            String authCode = menu.getAuthcode();
            
            //add begin CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
            // �������˵���Ӧ��authcode��1000
            if (Constants.PROOPERORGID_HUB.equals(province) && "1000".equals(authCode))
            {
                // �û�δ��¼
                if (customerSimp == null)
                {
                    request.getRequestDispatcher("/jsp/login/servicepassword_hub.jsp").forward(request, response);
                    return;
                }
                // �ѵ�¼
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
             * ��ҳ���0000������Ҫ���û���¼��ʽ������֤
             */
            if (!"root".equals(currMenuID) && !"0000".equals(authCode))
            {
                // ����Ҫ���е�������֤��ʽ
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
                
                // �����Ҫ���з���������֤���������������֤ҳ��
                if (resultCode.charAt(0) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/servicepassword.jsp").forward(request, response);
                    
                    return;
                }
                // ���������֤ҳ��
                else if (resultCode.charAt(1) == '1')
                {
                	//modify begin m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
                    //Ҫ�������������֤�Ĳ˵�id���Ͳ�Ʒid��
                    String menuId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_MENU);
                    String ProId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_PRO);
                    
                    //��ȡ��ǰ�û��Ĳ�Ʒid
                    String usrProId = "";
                    if (customerSimp != null)
                    {
                    	usrProId = (String) customerSimp.getProductID();
                    }                   
                    
                    //���������㣬ִ�����������֤
                	if (!CommonUtil.filterMenu(menuId, currMenuID) || !CommonUtil.filterProd(ProId, usrProId))
                	{
                		response.sendRedirect(path + "/login/randomPwd.action?" + Constants.CUR_MENUID + "=" + currMenuID);
                	    return;
                	}
                	//modify end m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
                }
                // ����֤��֤ҳ��
                else if (resultCode.charAt(2) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/id.jsp").forward(request, response);
                    
                    return;
                }
                // �ֻ�Ǯ����֤ҳ��
                else if (resultCode.charAt(3) == '1')
                {
                    request.getRequestDispatcher("/jsp/login/rfid.jsp").forward(request, response);
                    
                    return;
                }                
            }
            // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
            if (customerSimp != null)
            {
                // �˵�֧��Ӫ���Ƽ����־
                String actFlag = "";
                
                // �����û��Ƽ����־
                String alreadyRecFlag = (String)session.getAttribute(Constants.ALREADY_REC_FLAG);
                
                // modify begin hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
                
                // ֧��Ӫ���Ƽ���Ĳ˵�
                List<DictItemPO> actMenuList = null;
                
                // ԭӪ���Ƽ������ 1����  0���� 
                String recommendSwitchOld = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND);
                
                // ��Ӫ���Ƽ������1����  0����
                String recommendSwitchNew = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND_NEW);
                
                // ��Ӫ���Ƽ��
                if("1".equals(recommendSwitchNew))
                {
                    // ��ȡ֧����Ӫ���Ƽ���Ĳ˵�
                    actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENUNEW);
                }
                // ԭӪ���Ƽ��
                else if("1".equals(recommendSwitchOld))
                {
                    // ��ȡ֧�־�Ӫ���Ƽ���Ĳ˵�
                    actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENU);
                }
                    
                // ֧��Ӫ���Ƽ���Ĳ˵�
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
            
                // ֧��Ӫ���Ƽ������ε�¼û���Ƽ���
                if("1".equals(actFlag) && !"1".equals(alreadyRecFlag))
                {
                    session.setAttribute(Constants.ALREADY_REC_FLAG, "1");
       
                    // �µ�����Ӫ���
                    if("1".equals(recommendSwitchNew))
                    {
                        response.sendRedirect(request.getContextPath()+"/recommendActivity/qryRecommendActList.action?curMenuId=" + currMenuID);
                    }
                    // ԭӪ���Ƽ��
                    else if("1".equals(recommendSwitchOld))
                    {
                        response.sendRedirect(request.getContextPath()+"/recommendProduct/getRecommendProductList.action?curMenuId=" + currMenuID);
                    }
                    
                    return;
                }
                // modify end hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
            }  
            // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
        }
        // ������ȵ��������ӱ�־
        else if (Constants.PROOPERORGID_HUB.equals(province))
        {
            // �ն���Ϣ
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
            
            // ����session
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
     * ��֤�������Ϣ
     * 
     * @param input ������Ϣ
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    private String checkInput(String input)
    {
        StringBuffer buff = new StringBuffer();
        if (null == input || "".equals(input))
        {
            return buff.toString();
        }
        
        // �����е����ת��ΪСд
        input = input.toLowerCase(Locale.US);
        
        // ȡ����Ҫ���˵��������
        String unusedSign = getPrefixDictItem("UnusedSign");
        
        // ���������ַ�
        if (StringUtils.isNotEmpty(unusedSign))
        {
            char[] unusedSigns = unusedSign.toCharArray();
            for (int i = 0; i < unusedSigns.length; i++)
            {
                if ("".equals(String.valueOf(unusedSigns[i])))
                {
                    continue;
                }
                
                // �ж��Ƿ���������ַ�
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
        
        // ȡ����Ҫ���˵Ĺؼ���(JS�����ݿ�)
        String keyWord = getPrefixDictItem("KeyWord");
        
        // ���˹ؼ���(JS�����ݿ�)
        if (StringUtils.isNotEmpty(keyWord))
        {
            String[] keyWords = keyWord.split(",");
            
            for (int i = 0; i < keyWords.length; i++)
            {
                if ("".equals(keyWords[i]))
                {
                    continue;
                }
                
                // �ж��Ƿ�����ؼ���
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
     * ȡ��GroupId='SpecialChar'�����ֵ�DictIdΪ����ǰ׺��������Ϣ���
     * 
     * @param prefixDictId DictIdǰ׺
     * @return ����������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
	private String getPrefixDictItem(String prefixDictId)
    {
        // ȡ�������ַ��ֵ���
        List<DictItemPO> specialChars = (List<DictItemPO>)PublicCache.getInstance().getCachedData("SpecialChar");
        // ���巵���ֵ������������ִ�
        StringBuffer joinValue = new StringBuffer();
        
        if (specialChars != null)
        {
            for (DictItemPO specialChar : specialChars)
            {
                // �ж��Ƿ��Ǵ������prefixDictIdΪǰ׺���ֵ���
                if (StringUtils.contains(specialChar.getDictid(), prefixDictId))
                {
                    joinValue.append(specialChar.getDescription()).append(",");
                }
            }
        }
        
        if(joinValue.length() > 0)
        {
            
            // modify begin qWX279398 2015��7��20��17:16:37 ȥ�����������toString()������findbugs�޸� 
        	return joinValue.substring(0, joinValue.length()-1);
        	// modify end qWX279398 2015��7��20��17:16:37 ȥ�����������toString()������findbugs�޸� 
        	
        }
        else
        {
        	return joinValue.toString();
        }
    }
    
    /**
     * �滻�����ַ�
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
     * ��֤�ն˻���Ϣ
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param session HttpSession
     * @throws ServletException
     * @throws IOException
     * @see [�ࡢ��#��������#��Ա]
     */
    protected void validateTerminalInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session)
        throws ServletException, IOException
    {
        // ��session�л�ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String lockTerm = request.getParameter("lockTerm");
        
        /**
         * ��ԭ�е�ҵ���߼������Ӻ�̨��������ͽ������ܣ�
         * 1.�ж�lockTerm�����Ƿ���ڣ�������������»�ȡ�ն˻���Ϣ���ܻ�ȡ������������ҵ�����̣���ȡ������ת������ҳ�档
         * 2.����ҳ�涨ʱ�ж��ն˻�״̬������ն˻�״̬Ϊ���ã���ת���ն���ҳ�档��������������ת������ҳ�档
         */ 
        if (lockTerm != null || terminalInfo == null)
        {
            // modify begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
            String flagMsg = "";
            
            // ��ȡ�ն˻����ֱ�־��IP����MAC��
            String machineFlag = (String) PublicCache.getInstance().getCachedData("machineFlag");                
            if ("IP".equalsIgnoreCase(machineFlag))
            {
                String ip = (String) request.getSession().getAttribute("termIP");
                
                if (null == ip || "".equals(ip))
                {
                    ip = request.getParameter("termIP");
                    
                    request.getSession().setAttribute("termIP", ip);
                }
                
                logger.info("��ǰIP=[" + ip + "].");
                
                // �ж��Ƿ��ȡ�ն˻���Ϣ
                if (null == ip || "".equals(ip))
                {
                    logger.error("��ǰ�Ự[" + request.getSession().getId() + "]�Ѷ�ʧ,��ȡ�����ն�IP!");
                    request.setAttribute("errormessage", "ϵͳ����,��ǰ�Ự�Ѷ�ʧ,ϵͳ���»�ȡ�Ự, ���Ժ�...");
                    request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                    return;
                }
                
                flagMsg = "IP(" + ip + ")";
                
                // ͨ���ն�IP��ȡ�ն˻���Ϣ
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
                
                logger.info("��ǰMAC=[" + mac + "].");
                
                // �ж��Ƿ��ȡ�ն˻���Ϣ
                if (null == mac || "".equals(mac))
                {
                    logger.error("��ǰ�Ự�Ѷ�ʧ,��ȡ�����ն�MAC!");
                    request.setAttribute("errormessage", "ϵͳ����,��ǰ�Ự�Ѷ�ʧ,ϵͳ���»�ȡ�Ự, ���Ժ�...");
                    request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                    return;
                }
                
                flagMsg = "MAC(" + mac + ")";
                
                // ͨ���ն�MAC��ȡ�ն˻���Ϣ
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
                    
                    //�����IP��Ϊ�գ�������ԭIP��һ�£����ж���IP�Ƿ��ظ���IP�ظ���������IP���ظ��������ն˻���Ϣ
                    if (!"".equals(newIPAddr) && !newIPAddr.trim().equals(oldIPAddr.trim()))
                    {
                        terminalInfo.setIpaddr(newIPAddr);
                        
                        TerminalInfoService service = (TerminalInfoService) ApplicationContextUtil.getBean("terminalInfoService");
                        
                        // �ж��ն˻�IP�Ƿ�Ψһ
                        if (service.isTerminalExisted(terminalInfo))
                        {
                            String errMsg = "IP��ַ" + newIPAddr + "�Ѵ��ڣ�ϵͳ�������ظ��������������á�";
                            logger.error(errMsg);
                            request.setAttribute("errormessage", errMsg);
                            request.getRequestDispatcher("/errorIP.jsp").forward(request, response);
                            return;
                        }
                        
                        // ����mac����SH_TERM_CONFIG��IP
                        service.updateTermConfigIp(terminalInfo);
                    }
                }
            }
            
            String lockedFlag = (String) PublicCache.getInstance().getCachedData("SH_LOCKED_FLAG");
            
            // �ж��Ƿ��ȡ�ն˻���Ϣ
        	if (terminalInfo == null)
        	{
            	logger.error("����" + flagMsg + "�����ն���֤��ʧ�ܡ�");
            	request.setAttribute("errormessage", "�ն˻���Ϣ��ȡʧ�ܣ��޷��ṩ����");
                request.getRequestDispatcher("/invalidIP.jsp").forward(request, response);
                return;
        	}
        	// add begin g00140516 2013/02/16 R003C13L02n01 �ն˻�����ʱ����ʾ����ͣ�ṩ����
        	else if ("1".equals(lockedFlag) && "1".equals(terminalInfo.getIsLocked()))
        	{
        		logger.error("�ն˻�" + terminalInfo.getIpaddr() + "����������ͣ�ṩ����");
            	request.setAttribute("errormessage", "��Ǹ�����ն���ͣ�ṩ����");
                request.getRequestDispatcher("/locked.jsp").forward(request, response);
                return;
        	}
        	// add end g00140516 2013/02/16 R003C13L02n01 �ն˻�����ʱ����ʾ����ͣ�ṩ����
        	// �ж��ն˻��Ƿ��п��õĲ˵��б���Ϣ
        	else
        	{
        	    //�����ն���
        	    String termGroupID = terminalInfo.getTermgrpid();
        	    
        	    List<MenuInfoPO> menus = null;
        	    
        	    if (termGroupID != null && !"".equals(termGroupID))
        	    {
        	        //�����ն����Ի����л�ȡ�˵��б�
        	        menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(termGroupID);
        	    }
        	    
        	    if (menus == null || menus.size() == 0)
        	    {
        	        logger.error("����" + flagMsg + "�����ն���֤���ɹ��������޷���ȡ�ն˿��õĲ˵��б����������ݿ����á�");
        	        request.setAttribute("errormessage", "�ն˻���Ϣ��ȡʧ�ܣ��޷��ṩ����");
        	        request.getRequestDispatcher("/invalidIP.jsp").forward(request, response);
                    return;
        	    }
        	    else
        	    {
        	        session.setAttribute(Constants.TERMINAL_INFO, terminalInfo);
        	        
        	        if (logger.isWarnEnabled())
        	        {
        	            logger.warn("�ն˻�IP=[" + terminalInfo.getIpaddr() + "]; SID=[" + session.getId() + "]");
        	        }
        	    }
        	}
        	// modify end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149
        }
    }
    
    /**
     * ����ҳ�桢���ܲ���Ҫ����
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
    	// add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
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
    	// add begin jWX216858 2014/08/08 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
    	return true;
    }
    
    /**
     * �жϵ�ǰ�˵��費��Ҫ����
     * @param str �����������õ���Ҫ���˵Ĳ˵���
     * @param menuid ��ǰ�˵�
     * @return true����Ҫ����
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
     * �жϵ�ǰ��Ʒ�費��Ҫ����
     * @param str ����������Ҫ���˵Ĳ�Ʒid
     * @param prodid ��ǰ��Ʒid
     * @return true����ǰ��Ʒ��Ҫ���� false����ǰ��Ʒ����Ҫ����
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