package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * ��Ʒ��������
 * <������ϸ����>
 * 
 * @author  cKF76106
 * @version  [�汾��, Jul 9, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class AddAttrPO
{
    // �������Ա���
    private String attrid = "";
    
    // ������������
    private String attrname = "";
    
    // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname ... 
    private String dictinfo = "";
    
    // �û�����ֵ������û�û�ж����������Ĭ��ֵ��
    private String attrvalue = "";
    
    // �Ƿ���� 0������ 1����
    private String ismandatory = "";
    
    // �Ƿ����չ�� 0������ 1����
    // ���ǽ���չ�ֵģ�����Ҫ��ҳ������ʾ���û����ܱ༭����Ʒ����ʱֱ��ʹ��Ĭ��ֵ���ɡ�
    private String isshow = "";
    
    // ��������ֵ��󳤶�
    private String maxlength = "";
    
    // ������������ 
    // MEMO�����б༭��
    // SINGLE�����б༭��
    // DATE��ʱ��༭��(��ʽ��yyyy-mm-dd hh24:mi:ss)
    // SELECT�������б�
    private String attrtype = "";
    
    // ֵ���� NUMBER������ STRING���ַ���
    private String valuetype = "";
    
    // ��������ֵ��С����
    private String minlength = "";
    
    // �������
    private String objid = "";
    
    // ����ֵ����
    private String attrnum = "";
    
    // �������Էָ���
    private String attrsplit = "";
    
    // ��������
    private String objType = "";
    
    public String getAttrid()
    {
        return attrid;
    }

    public void setAttrid(String attrid)
    {
        this.attrid = attrid;
    }

    public String getAttrname()
    {
        return attrname;
    }

    public void setAttrname(String attrname)
    {
        this.attrname = attrname;
    }

    public String getDictinfo()
    {
        return dictinfo;
    }

    public void setDictinfo(String dictinfo)
    {
        this.dictinfo = dictinfo;
    }

    public String getAttrvalue()
    {
        return attrvalue;
    }

    public void setAttrvalue(String attrvalue)
    {
        this.attrvalue = attrvalue;
    }

    public String getIsmandatory()
    {
        return ismandatory;
    }

    public void setIsmandatory(String ismandatory)
    {
        this.ismandatory = ismandatory;
    }

    public String getIsshow()
    {
        return isshow;
    }

    public void setIsshow(String isshow)
    {
        this.isshow = isshow;
    }

    public String getMaxlength()
    {
        return maxlength;
    }

    public void setMaxlength(String maxlength)
    {
        this.maxlength = maxlength;
    }

    public String getAttrtype()
    {
        return attrtype;
    }

    public void setAttrtype(String attrtype)
    {
        this.attrtype = attrtype;
    }

    public String getValuetype()
    {
        return valuetype;
    }

    public void setValuetype(String valuetype)
    {
        this.valuetype = valuetype;
    }

    public String getMinlength()
    {
        return minlength;
    }

    public void setMinlength(String minlength)
    {
        this.minlength = minlength;
    }

    public String getObjid()
    {
        return objid;
    }

    public void setObjid(String objid)
    {
        this.objid = objid;
    }

    public String getAttrnum()
    {
        return attrnum;
    }

    public void setAttrnum(String attrnum)
    {
        this.attrnum = attrnum;
    }

    public String getAttrsplit()
    {
        return attrsplit;
    }

    public void setAttrsplit(String attrsplit)
    {
        this.attrsplit = attrsplit;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getObjType()
    {
        return objType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setObjType(String objType)
    {
        this.objType = objType;
    }
    
    

    
    
    
    
}
