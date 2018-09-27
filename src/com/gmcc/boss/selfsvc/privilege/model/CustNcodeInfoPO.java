/*
 * �� �� ��:  CustNcodeInfoPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  hWX5316476
 * �޸�ʱ��:  May 22, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.gmcc.boss.selfsvc.privilege.model;

import java.util.List;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  hWX5316476
 * @version  [�汾��, May 22, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CustNcodeInfoPO
{
    /**
     * ͬ��ҵ���б�
     */
    private List<GroupNcodePO> groupNcodeList;
    
    /**
     * �����ײ�ncode
     */
    private String currNcode;
    
    /**
     * �����ײ�ncode
     */
    private String nextNcode;
    
    /**
     * �����ײͷ���
     */
    private String currNcodeFee;
    
    /**
     * �����ײͷ���
     */
    private String nextNcodeFee;
    
    /**
     * �����ײ�����
     */
    private String currNcodeName;
    
    /**
     * �����ײ�����
     */
    private String nextNcodeName;
    
    
    /**
     * ��ҵ������
     */
    private String groupDesc;

    public List<GroupNcodePO> getGroupNcodeList()
    {
        return groupNcodeList;
    }

    public String getNextNcodeFee()
    {
        return nextNcodeFee;
    }

    public String getCurrNcodeFee()
    {
        return currNcodeFee;
    }

    public void setCurrNcodeFee(String currNcodeFee)
    {
        this.currNcodeFee = currNcodeFee;
    }

    public void setNextNcodeFee(String nextNcodeFee)
    {
        this.nextNcodeFee = nextNcodeFee;
    }

    public void setGroupNcodeList(List<GroupNcodePO> groupNcodeList)
    {
        this.groupNcodeList = groupNcodeList;
    }

    public String getCurrNcode()
    {
        return currNcode;
    }

    public void setCurrNcode(String currNcode)
    {
        this.currNcode = currNcode;
    }

    public String getNextNcode()
    {
        return nextNcode;
    }

    public void setNextNcode(String nextNcode)
    {
        this.nextNcode = nextNcode;
    }

    public String getCurrNcodeName()
    {
        return currNcodeName;
    }

    public void setCurrNcodeName(String currNcodeName)
    {
        this.currNcodeName = currNcodeName;
    }

    public String getNextNcodeName()
    {
        return nextNcodeName;
    }

    public void setNextNcodeName(String nextNcodeName)
    {
        this.nextNcodeName = nextNcodeName;
    }

    public String getGroupDesc()
    {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc)
    {
        this.groupDesc = groupDesc;
    }
    
}
