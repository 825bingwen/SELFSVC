package com.gmcc.boss.selfsvc.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.gmcc.boss.selfsvc.common.model.CShMenuItemVO;


public class ShMenuNode implements Comparable
{
    public CShMenuItemVO menuItem;
    public ShMenuNode parent;
    public List submenus; //ShMenuNode

    public ShMenuNode(CShMenuItemVO menuItem, ShMenuNode parent, List submenus)
    {
        this.menuItem = menuItem;
        this.parent = parent;
        this.submenus = submenus;
    }

    public ShMenuNode(CShMenuItemVO menuItem)
    {
        this.menuItem = menuItem;
        this.parent = null;
        this.submenus = new ArrayList();
    }

    public CShMenuItemVO getMenuItem()
    {
        return menuItem;
    }

    public void setMenuItem(CShMenuItemVO menuItem)
    {
        this.menuItem = menuItem;
    }

    public ShMenuNode getParent()
    {
        return parent;
    }

    public void setParent(ShMenuNode parent)
    {
        this.parent = parent;
    }

    public List getSubmenus()
    {
        return submenus;
    }

    public void setSubmenus(List submenus)
    {
        this.submenus = submenus;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (menuItem == null) ? 0 : menuItem.hashCode());
        return result;
    }

    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ShMenuNode other = (ShMenuNode)obj;
        if (menuItem == null)
        {
            if (other.menuItem != null) return false;
        }
        else if ( !menuItem.equals(other.menuItem)) return false;
        return true;
    }

    public String toString()
    {
        String menustr = menuItem.m_Menuid;
        String parentstr = parent == null ? "null" : parent.menuItem.m_Menuid;
        //String substr = submenus == null ? "null" : "";
        String substr = (submenus == null ? "null" : "");
        StringBuffer sbuf = new StringBuffer(substr);
        if (submenus != null)
        {
	        for (Iterator ite = submenus.iterator(); ite.hasNext();)
	        {
	            sbuf.append("$").append(((ShMenuNode) (ite.next())).menuItem.m_Menuid);
	        }
        }
        substr = sbuf.toString();
        return "ShMenuNode[" + menustr + "    " + parentstr + "     " + substr + "]\n";
    }

    public int compareTo(Object obj)
    {
        if (this.menuItem.m_Sortorder > ((ShMenuNode)obj).menuItem.m_Sortorder)
        {
            return 1;
        }
        else if (this.menuItem.m_Sortorder < ((ShMenuNode)obj).menuItem.m_Sortorder)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}