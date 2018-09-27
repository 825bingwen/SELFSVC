package com.gmcc.boss.selfsvc.login.model;

import com.gmcc.boss.selfsvc.common.BasePO;

public class LoginCheckPO extends BasePO
{
    private static final long serialVersionUID = 83018655731228383L;

    /**
     * �ն�ID
     */
    private String termId;
    
    /**
     * �ֻ�����
     */
    private String servNumber;
    
    /**
     * ����ʱ��
     */
    private String statusDate;

    public String getTermId()
    {
        return termId;
    }

    public void setTermId(String termId)
    {
        this.termId = termId;
    }

    public String getServNumber()
    {
        return servNumber;
    }

    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }

    public String getStatusDate()
    {
        return statusDate;
    }

    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    
}
