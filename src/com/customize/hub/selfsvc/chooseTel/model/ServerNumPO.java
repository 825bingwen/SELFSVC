package com.customize.hub.selfsvc.chooseTel.model;
/**
 * 
 * �ֻ�����PO
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Apr 20, 2011]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ServerNumPO
{
    private String telnum;// �ֻ�����
    private String seltel_prepayfee;// ���뼶�𣬼�Ԥ��ѣ���λ����
    // add begin liutao 2016-07-30 OR_HUB_201603_1191  �����ն���ʾ�����Ż������ŵ�ΰ��
    private String minimumCharge;//������ѽ��

    public String getTelnum()
    {
        return telnum;
    }
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    public String getSeltel_prepayfee()
    {
        return seltel_prepayfee;
    }
    public void setSeltel_prepayfee(String seltel_prepayfee)
    {
        this.seltel_prepayfee = seltel_prepayfee;
    }

    public String getMinimumCharge()
    {
        return minimumCharge;
    }

    public void setMinimumCharge(String minimumCharge)
    {
        this.minimumCharge = minimumCharge;
    }
}
