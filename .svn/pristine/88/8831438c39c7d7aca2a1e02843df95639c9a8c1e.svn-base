package com.gmcc.boss.selfsvc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;

/**
 * spring监听器
 * web服务启动时设置ApplicationContext,在动态查找对象时用到
 * @author  yKF28472
 * @version  [版本号, 2010-12-08]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShContextLoaderListener extends ContextLoaderListener {   

	@Override
    /** 
     * web启动时加载spring配置文件
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
		
		ServletContext servletContext = event.getServletContext();
		
		ApplicationContextUtil.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
		
	}

 
}  

