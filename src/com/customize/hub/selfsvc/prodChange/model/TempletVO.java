/**
 * 
 */
package com.customize.hub.selfsvc.prodChange.model;

/**
 * @author Administrator
 * 
 */
public class TempletVO
{
    // ģ�����
    private String templetID;
    
    // ģ������
    private String templetName;
    
    public TempletVO()
    {
        
    }
    
    public TempletVO(String templetID, String templetName)
    {
        this.templetID = templetID;
        this.templetName = templetName;
    }
    
    /**
     * @return the templetID
     */
    public String getTempletID()
    {
        return templetID;
    }
    
    /**
     * @param templetID the templetID to set
     */
    public void setTempletID(String templetID)
    {
        this.templetID = templetID;
    }
    
    /**
     * @return the templetName
     */
    public String getTempletName()
    {
        return templetName;
    }
    
    /**
     * @param templetName the templetName to set
     */
    public void setTempletName(String templetName)
    {
        this.templetName = templetName;
    }
    
}
