package com.gmcc.boss.selfsvc.quickpublish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ��Ʒ������ģ�����Ӳ�Ʒ��ѯ�ӿڷ�������ģ�� 
 * <������ϸ����>
 * 
 * @author  cKF76106
 * @version  [�汾��, Jul 9, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ChildProdPO implements Serializable
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = -6709326670062855076L;

    // ��ѡ��Ʒ��С��
    private String minprod = "";
    
    // ��ѡ��Ʒ�����
    private String maxprod = "";
    
    //��Ʒ�����Ӳ�Ʒ�б�
    private List<SubsChildProdPO> subsChildProdPOList = new ArrayList<SubsChildProdPO>();

    public String getMinprod()
    {
        return minprod;
    }

    public void setMinprod(String minprod)
    {
        this.minprod = minprod;
    }

    public String getMaxprod()
    {
        return maxprod;
    }

    public void setMaxprod(String maxprod)
    {
        this.maxprod = maxprod;
    }

    public List<SubsChildProdPO> getSubsChildProdPOList()
    {
        return subsChildProdPOList;
    }

    public void setSubsChildProdPOList(List<SubsChildProdPO> subsChildProdPOList)
    {
        this.subsChildProdPOList = subsChildProdPOList;
    }

    
}
