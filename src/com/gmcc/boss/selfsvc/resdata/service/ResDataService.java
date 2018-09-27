/*
 * �ļ�����ResDataService.java
 * ��Ȩ��CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �����������ն���Դ���� Service
 * �޸��ˣ�z00107005
 * �޸�ʱ�䣺2008-1-17
 * �޸����ݣ�����
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ��޸ģ�����Ӧ�����ն˵���Ҫ
 */
package com.gmcc.boss.selfsvc.resdata.service;

import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;

/**
 * �����ݿ��л�ȡ�����ն����е��������ݡ��˵����ݵ�
 * 
 */
public interface ResDataService
{
    /**
     * ��SH_PARAM���ȡ���е�������Ϣ
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List getAllResDataList();
    
    /**
     * ��ȡ�˵�����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List getAllMenuInfoList();
    
    /**
     * ��ȡregion����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List getAllRegionInfoList();
    
    /**
     * ��ȡ�ֵ�����
     * 
     * @return
     * @see
     */
    public List<DictItemPO> getDictItems();
    
    /**
     * ��ȡsh_info_rectel��Ϣ
     * 
     * @return
     * @see
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    public Map<String, String> getRectelInfo();
    
    /**
     * ��ȡ Sh_Term_externattr��Ϣ:�ն��豸����
     * 
     * @return
     * @see
     * @remark create lwx439898 2017-10-12 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public List<TermInfosPO> getAllTermInfos();
    
}
