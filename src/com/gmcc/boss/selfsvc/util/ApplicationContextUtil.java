package com.gmcc.boss.selfsvc.util;

import org.springframework.context.ApplicationContext;

/**
 * 动态查找取得spring容器里面的bean对象
 * @author  yKF28472
 * @version  [版本号, 2010-12-08]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext = null;     
    
    /**
     * 动态查找bean对象
     * @author  yKF28472
     * @version  [版本号, 2010-12-08]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static Object getBean(String beanName)
    {
        return applicationContext.getBean(beanName);
    }
    
    /**
     * 设置应用上下文
     * @author  yKF28472
     * @version  [版本号, 2010-12-08]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
}
