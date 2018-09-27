package com.gmcc.boss.selfsvc.common.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.gmcc.boss.selfsvc.common.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * 运行时错误处理拦截器
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 13, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ErrorInterceptor implements Interceptor
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 初始化
     */
    public void init()
    {
        
    }

    /**
     * 处理action异常
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept(ActionInvocation invocation) throws Exception
    {
        // 取得action对象
        BaseAction action = (BaseAction)invocation.getAction();
        
        // 创建logger对象
        Log logger = LogFactory.getLog(BaseAction.class);
        
        // 执行action方法之前执行
        invocation.addPreResultListener(new PreResultListenerImpl());
        
        //modify begin g00140516 2011/11/05 R003C11L11n01 修改日志记录
        String result = "";
        try
        {
            result = invocation.invoke();
        }
        catch (DataAccessException ex)
        {
            logger.error(ex);
            throw new Exception("数据库操作失败！");
        }
        catch (NullPointerException ex)
        {
            logger.error(ex);
            throw new Exception("调用了未经初始化的对象或者是不存在的对象！");
        }
        catch (IOException ex)
        {
            logger.error(ex);
            throw new Exception("IO异常！");
        }
        catch (ClassNotFoundException ex)
        {
            logger.error(ex);
            throw new Exception("指定的类不存在！");
        }
        catch (ArithmeticException ex)
        {
            logger.error(ex);
            throw new Exception("数学运算异常！");
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            logger.error(ex);
            throw new Exception("数组下标越界!");
        }
        catch (IllegalArgumentException ex)
        {
            logger.error(ex);
            throw new Exception("方法的参数错误！");
        }
        catch (ClassCastException ex)
        {
            logger.error(ex);
            throw new Exception("类型强制转换错误！");
        }
        catch (SecurityException ex)
        {
            logger.error(ex);
            throw new Exception("违背安全原则异常！");
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            throw new Exception("操作数据库异常！");
        }
        catch (NoSuchMethodError ex)
        {
            logger.error(ex);
            throw new Exception("方法末找到异常！");
        }
        catch (InternalError ex)
        {
            logger.error(ex);
            throw new Exception("Java虚拟机发生了内部错误");
        }
        catch (Exception ex)
        {
            logger.error(ex);
            throw new Exception("程序内部错误，操作失败！");
        }
        //modify end g00140516 2011/11/05 R003C11L11n01 修改日志记录
        
        // after(invocation, result);
        return result;
    }
    
    /**
     * 销毁
     */
    public void destroy()
    {
        
    }
    
}
