package com.customize.hub.selfsvc.scorexecoup.model;

import java.io.Serializable;

/**
 * 
 * ����ʵ����
 * 
 * @author user
 * @version [�汾��, Mar 29, 2013]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ScorePojo implements Serializable
{
    /**
     * ע������
     */
    private static final long serialVersionUID = -9066580054490068166L;

    private String type;
    
    private String score;
    
    private String description;
    
    private String status;
    
    private String operid;
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getScore()
    {
        return score;
    }
    
    public void setScore(String score)
    {
        this.score = score;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getOperid()
    {
        return operid;
    }
    
    public void setOperid(String operid)
    {
        this.operid = operid;
    }
    
}
