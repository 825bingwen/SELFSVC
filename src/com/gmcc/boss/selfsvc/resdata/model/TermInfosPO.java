/*
 * �ļ�����TermInfosPO
 * ��Ȩ��
 * ����:�ն��豸���Ա�
 * �޸��ˣ�lwx439898
 * �޸�ʱ�䣺2017-10-13
 * �޸����ݣ�����
 */

package com.gmcc.boss.selfsvc.resdata.model;

import com.gmcc.boss.selfsvc.common.BasePO;


/**
 * 
 * �ն��豸���Ա�
 * <������ϸ����>
 * 
 * @author  lWX439898
 * @version  [�汾��, 2017-10-13]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class TermInfosPO extends BasePO
{
    /**
     * ע������
     */
    private static final long serialVersionUID = -1455148316261033172L;
    //�ն��豸��
    private String terminalid;
    //���Ա���
    private String attributeid;
    //����ֵ
    private String attributeval;
    public String getTerminalid()
    {
        return terminalid;
    }
    public void setTerminalid(String terminalid)
    {
        this.terminalid = terminalid;
    }
    public String getAttributeid()
    {
        return attributeid;
    }
    public void setAttributeid(String attributeid)
    {
        this.attributeid = attributeid;
    }
    public String getAttributeval()
    {
        return attributeval;
    }
    public void setAttributeval(String attributeval)
    {
        this.attributeval = attributeval;
    }


    
    
}