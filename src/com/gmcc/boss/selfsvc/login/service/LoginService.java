/*
 * �ļ�����LoginService.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������ά���û���¼ʱ�Ĵ����¼���Է������롢��������������������������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-20
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.login.service;

import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;

/**
 * 
 * ά���û���¼ʱ�Ĵ����¼���Է������롢��������������������������
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-20
 * @see
 * @since
 */
public interface LoginService
{
    /**
     * �����ֻ����롢��֤��ʽ��ѯ�����¼��¼
     * 
     * @param servNumber���ֻ�����
     * @param authType����֤��ʽ
     * @return
     * @see
     */
    public LoginErrorPO qryErrorRecords(String servNumber, String authType);
    
    /**
     * ���´����¼��¼
     * 
     * @param loginErrorPO
     * @param servNumber���ֻ�����
     * @param authType����֤��ʽ
     * @see
     */
    public void updateErrorRecords(LoginErrorPO loginErrorPO, String servNumber, String authType);
    
    /**
     * �û���֤�ɹ��󣬸����ֻ����롢��֤��ʽɾ��֮ǰ�Ĵ����¼��¼
     * 
     * @param servNumber���ֻ�����
     * @param authType����֤��ʽ
     * @see
     */
    public void deleteErrorRecords(String servNumber, String authType);
    /**
     * 
     * �����ֻ���ȥ��ѯ���û��Ƿ��ں�������
     * @create zKF77391
     * @param servNumber �ֻ�����
     * @return true �� <br >false ����
     */
    public boolean checkBlackListByServNumber(String servNumber);
    
    /**
     * ͬһ����ͬһ������ε�¼��֤
     * 
     * @param servNumber �ֻ�����
     * @param termId �ն˱��
     * @return
     * @author lWX5316086
     * @see [�ࡢ��#��������#��Ա]
     */
    public boolean checkLoginByServNumber(String servNumber, String termId);
    
    /**
     * �����ն�IDɾ����¼��Ϣ
     * 
     * @param termId �ն�ID
     * @author lWX5316086
     * @see [�ࡢ��#��������#��Ա]
     */
    public void deleteLoginCheckByTermId(String termId);
    
    /**
     * ���ݲ���Id��ѯ����ֵ
     * @param paramId
     * @return
     * @remark create begin hWX5316476 2014-12-5 OR_HUB_201408_628_����_���������ն���������ҳ��
     */
    public String qryParamValueById(String paramId);
    
}
