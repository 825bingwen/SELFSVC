package com.gmcc.boss.selfsvc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;

/**
 * spring������
 * web��������ʱ����ApplicationContext,�ڶ�̬���Ҷ���ʱ�õ�
 * @author  yKF28472
 * @version  [�汾��, 2010-12-08]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ShContextLoaderListener extends ContextLoaderListener {   

	@Override
    /** 
     * web����ʱ����spring�����ļ�
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
		
		ServletContext servletContext = event.getServletContext();
		
		ApplicationContextUtil.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
		
	}

 
}  

