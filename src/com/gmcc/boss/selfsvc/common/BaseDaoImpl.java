package com.gmcc.boss.selfsvc.common;

import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.gmcc.boss.selfsvc.call.SelfSvcCall;

/**
 * 
 * �������ݿ����
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Dec 10, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BaseDaoImpl
{
    
    protected SqlMapClientTemplate sqlMapClientTemplate;
    
    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate)
    {
        this.sqlMapClientTemplate = sqlMapClientTemplate;
    }
    
    
}
