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
 * ����ʱ������������
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Dec 13, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ErrorInterceptor implements Interceptor
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * ��ʼ��
     */
    public void init()
    {
        
    }

    /**
     * ����action�쳣
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept(ActionInvocation invocation) throws Exception
    {
        // ȡ��action����
        BaseAction action = (BaseAction)invocation.getAction();
        
        // ����logger����
        Log logger = LogFactory.getLog(BaseAction.class);
        
        // ִ��action����֮ǰִ��
        invocation.addPreResultListener(new PreResultListenerImpl());
        
        //modify begin g00140516 2011/11/05 R003C11L11n01 �޸���־��¼
        String result = "";
        try
        {
            result = invocation.invoke();
        }
        catch (DataAccessException ex)
        {
            logger.error(ex);
            throw new Exception("���ݿ����ʧ�ܣ�");
        }
        catch (NullPointerException ex)
        {
            logger.error(ex);
            throw new Exception("������δ����ʼ���Ķ�������ǲ����ڵĶ���");
        }
        catch (IOException ex)
        {
            logger.error(ex);
            throw new Exception("IO�쳣��");
        }
        catch (ClassNotFoundException ex)
        {
            logger.error(ex);
            throw new Exception("ָ�����಻���ڣ�");
        }
        catch (ArithmeticException ex)
        {
            logger.error(ex);
            throw new Exception("��ѧ�����쳣��");
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            logger.error(ex);
            throw new Exception("�����±�Խ��!");
        }
        catch (IllegalArgumentException ex)
        {
            logger.error(ex);
            throw new Exception("�����Ĳ�������");
        }
        catch (ClassCastException ex)
        {
            logger.error(ex);
            throw new Exception("����ǿ��ת������");
        }
        catch (SecurityException ex)
        {
            logger.error(ex);
            throw new Exception("Υ����ȫԭ���쳣��");
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            throw new Exception("�������ݿ��쳣��");
        }
        catch (NoSuchMethodError ex)
        {
            logger.error(ex);
            throw new Exception("����ĩ�ҵ��쳣��");
        }
        catch (InternalError ex)
        {
            logger.error(ex);
            throw new Exception("Java������������ڲ�����");
        }
        catch (Exception ex)
        {
            logger.error(ex);
            throw new Exception("�����ڲ����󣬲���ʧ�ܣ�");
        }
        //modify end g00140516 2011/11/05 R003C11L11n01 �޸���־��¼
        
        // after(invocation, result);
        return result;
    }
    
    /**
     * ����
     */
    public void destroy()
    {
        
    }
    
}
