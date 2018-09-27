/*
 * �� �� ��:  GroupCodePO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  hWX5316476
 * �޸�ʱ��:  Apr 29, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.gmcc.boss.selfsvc.privilege.model;

/**
 * ͬ��ҵ������
 * 
 * @author  hWX5316476
 * @version  [�汾��, Apr 29, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class GroupNcodePO
{   
    /**
     * ��ID
     */
    private String groupId;
    
    /**
     * NCODE
     */
    private String ncode;
    
    /**
     * ҵ������
     */
    private String ncodeName;
    
    /**
     * ���ܷ�
     */
    private String fee;
    
    /**
     * ������
     */
    private String groupDesc;
    
    /**
     * ��ܰ��ʾ
     */
    private String tips;
    
    /**
     * ҵ�����
     */
    private String ncodeDesc;
    
    /**
     * <Ĭ���޲ι��캯��>
     */
    public GroupNcodePO(){}

    /**
     * <Ĭ�Ϲ��캯��>
     * @param groupId ��ID
     */
    public GroupNcodePO(String groupId)
    {
        this.groupId = groupId;
    }

    /**
     * <Ĭ�Ϲ��캯��>
     * @param groupId ��ID
     * @param ncode ncode
     */
    public GroupNcodePO(String groupId, String ncode)
    {
        this.groupId = groupId;
        this.ncode = ncode;
    }
    
    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getNcodeName()
    {
        return ncodeName;
    }

    public void setNcodeName(String ncodeName)
    {
        this.ncodeName = ncodeName;
    }

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public String getGroupDesc()
    {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc)
    {
        this.groupDesc = groupDesc;
    }

    public String getNcodeDesc()
    {
        return ncodeDesc;
    }

    public void setNcodeDesc(String ncodeDesc)
    {
        this.ncodeDesc = ncodeDesc;
    }

    public String getTips()
    {
        return tips;
    }

    public void setTips(String tips)
    {
        this.tips = tips;
    }
   
}
