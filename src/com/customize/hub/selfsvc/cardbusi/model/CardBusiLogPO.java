package com.customize.hub.selfsvc.cardbusi.model;

/**
 * 
 * ���߿���
 * <������ϸ����>
 * 
 * @author  user
 * @version  [�汾��, Jul 29, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_NX_201303_280  ���������ն��Ż�����֮���߿���
 */
public class CardBusiLogPO
{
    /**
     * ��ˮ��
     */
    private String oid = "";
    
    /**
     * �ɷ���ˮ�ţ���SH_CHARGE_lOG���� �ɷ�ǰ��������Ϊ��
     */
    private String chargeId = "";
    
    /**
     * �ն˺�
     */
    private String termId = "";
    
    /**
     * ����
     */
    private String region = "";
    
    /**
     * �����ֻ���
     */
    private String servnumber = "";
    
    /**
     * �������� presetinstall Ԥ�ÿհ׿�����
     */
    private String rectype = "";
    
    /**
     * �ɷѷ�ʽ��1����������4���ֽ�
     */
    private String chargeType = "";
    
    /**
     * ����Ӧ�շ���
     */
    private String recFee = "";
    
    /**
     * ʵ�շ���
     */
    private String toFee = "";
    
    /**
     * �����Ʒ����
     */
    private String mainProdId = "";
    
    /**
     * ��Ʒģ�����
     */
    private String prodTempletId = ""; 
    
    /**
     * �ͻ�����
     */
    private String custName = "";
    
    /**
     * ֤����
     */
    private String certId = "";
    
    /**
     * �Ա�
     */
    private String sex = "";
    
    /**
     * ��ַ
     */
    private String linkAddr = "";

    /**
     * �հ׿���
     */
    private String blankCard = "";
    
    /**
     * ICCID
     */
    private String iccid = "";
    
    /**
     * IMSI
     */
    private String imsi = "";
    
    /**
     * ����Ϣ���ĺ���
     */
    private String smsp = "";
    
    /**
     * �ͻ���ϵ�绰
     */
    private String linkPhone = "";
    
    /**
     *  ѡ��Ĳ�Ʒ�б�
     */
    private String productList = "";
    
    /**
     * �û��ʼ���ַ
     */
    private String submailAddr = "";
    
    /**
     * ����ʱ��
     */
    private String createDate = "";
    
    /**
     * ״̬ʱ��
     */
    private String statusDate = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
     */
    private String payStatus = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
     */
    private String writeCardStatus = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
     */
    private String installStatus = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0���˿�ɹ� 1���˿�ʧ��
     */
    private String refundment = "";
    
    /**
     * Ӫҵ����������ˮ
     */
    private String formnum = "";
    
    /**
     * �û�SUBSID
     */
    private String subsId = "";
    
    /**
     * ��ע
     */
    private String notes = ""; 
    
    public String getOid()
    {
        return oid;
    }
    public void setOid(String oid)
    {
        this.oid = oid;
    }
    public String getTermId()
    {
        return termId;
    }
    public void setTermId(String termId)
    {
        this.termId = termId;
    }
    public String getChargeId()
    {
        return chargeId;
    }
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }
    public String getRegion()
    {
        return region;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getServnumber()
    {
        return servnumber;
    }
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    public String getMainProdId()
    {
        return mainProdId;
    }
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    public String getProdTempletId()
    {
        return prodTempletId;
    }
    public void setProdTempletId(String prodTempletId)
    {
        this.prodTempletId = prodTempletId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getChargeType()
    {
        return chargeType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setChargeType(String chargeType)
    {
        this.chargeType = chargeType;
    }
    public String getRecFee()
    {
        return recFee;
    }
    public void setRecFee(String recFee)
    {
        this.recFee = recFee;
    }
    public String getToFee()
    {
        return toFee;
    }
    public void setToFee(String toFee)
    {
        this.toFee = toFee;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCustName()
    {
        return custName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCustName(String custName)
    {
        this.custName = custName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCertId()
    {
        return certId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCertId(String certId)
    {
        this.certId = certId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getLinkAddr()
    {
        return linkAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setLinkAddr(String linkAddr)
    {
        this.linkAddr = linkAddr;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBlankCard()
    {
        return blankCard;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBlankCard(String blankCard)
    {
        this.blankCard = blankCard;
    }
    public String getIccid()
    {
        return iccid;
    }
    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }
    public String getImsi()
    {
        return imsi;
    }
    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSmsp()
    {
        return smsp;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSmsp(String smsp)
    {
        this.smsp = smsp;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getLinkPhone()
    {
        return linkPhone;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setLinkPhone(String linkPhone)
    {
        this.linkPhone = linkPhone;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProductList()
    {
        return productList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProductList(String productList)
    {
        this.productList = productList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSubmailAddr()
    {
        return submailAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSubmailAddr(String submailAddr)
    {
        this.submailAddr = submailAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCreateDate()
    {
        return createDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getStatusDate()
    {
        return statusDate;
    }
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    public String getWriteCardStatus()
    {
        return writeCardStatus;
    }
    public void setWriteCardStatus(String writeCardStatus)
    {
        this.writeCardStatus = writeCardStatus;
    }
    public String getPayStatus()
    {
        return payStatus;
    }
    public void setPayStatus(String payStatus)
    {
        this.payStatus = payStatus;
    }
    public String getInstallStatus()
    {
        return installStatus;
    }
    public void setInstallStatus(String installStatus)
    {
        this.installStatus = installStatus;
    }
    public String getRefundment()
    {
        return refundment;
    }
    public void setRefundment(String refundment)
    {
        this.refundment = refundment;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getFormnum()
    {
        return formnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSubsId()
    {
        return subsId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSubsId(String subsId)
    {
        this.subsId = subsId;
    }
    public String getNotes()
    {
        return notes;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    public String getRectype()
    {
        return rectype;
    }
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    
}
