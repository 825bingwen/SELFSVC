package com.gmcc.boss.selfsvc.common.model;

import com.gmcc.boss.common.base.CPDateTime;

public class CShMenuItemVO 
{

	private static final long serialVersionUID = -1616747056817423801L;
	public String m_Menuid = null;
    public String m_MenuName = null;
    public int m_Lever = 0;
    public String m_Parentid = null;
    public String m_Guiobj = null;
    public String m_Tiptext = null;
    public int m_Sortorder = 0;
    public CPDateTime m_CreateDate = new CPDateTime();
    public int m_Status = 0;
    public CPDateTime m_StatusDate = new CPDateTime();
    
    public String imgPath;

    public String getM_Menuid()
    {
        return m_Menuid;
    }

    public void setM_Menuid(String menuid)
    {
        m_Menuid = menuid;
    }

    public String getM_MenuName()
    {
        return m_MenuName;
    }

    public void setM_MenuName(String menuName)
    {
        m_MenuName = menuName;
    }

    public int getM_Lever()
    {
        return m_Lever;
    }

    public void setM_Lever(int lever)
    {
        m_Lever = lever;
    }

    public String getM_Parentid()
    {
        return m_Parentid;
    }

    public void setM_Parentid(String parentid)
    {
        m_Parentid = parentid;
    }

    public String getM_Guiobj()
    {
        return m_Guiobj;
    }

    public void setM_Guiobj(String guiobj)
    {
        m_Guiobj = guiobj;
    }

    public String getM_Tiptext()
    {
        return m_Tiptext;
    }

    public void setM_Tiptext(String tiptext)
    {
        m_Tiptext = tiptext;
    }

    public int getM_Sortorder()
    {
        return m_Sortorder;
    }

    public void setM_Sortorder(int sortorder)
    {
        m_Sortorder = sortorder;
    }

    public CPDateTime getM_CreateDate()
    {
        return m_CreateDate;
    }

    public void setM_CreateDate(CPDateTime createDate)
    {
        m_CreateDate = createDate;
    }

    public int getM_Status()
    {
        return m_Status;
    }

    public void setM_Status(int status)
    {
        m_Status = status;
    }

    public CPDateTime getM_StatusDate()
    {
        return m_StatusDate;
    }

    public void setM_StatusDate(CPDateTime statusDate)
    {
        m_StatusDate = statusDate;
    }

    public CShMenuItemVO()
    {}

    /**
     * @return the imgPath
     */
    public String getImgPath()
    {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (m_Menuid == null) ? 0 : m_Menuid.hashCode());
        return result;
    }

    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final CShMenuItemVO other = (CShMenuItemVO)obj;
        if (m_Menuid == null)
        {
            if (other.m_Menuid != null) return false;
        }
        else if ( !m_Menuid.equals(other.m_Menuid)) return false;
        return true;
    }

    public String toString()
    {
        return "id:" + m_Menuid + " name:" + m_MenuName + " lever:" + m_Lever + " m_Parentid:"
               + m_Parentid + " order" + m_Sortorder;
    }
}
