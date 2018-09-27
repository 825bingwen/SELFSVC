/*
 * �ļ�����MenuInfoPO.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.resdata.model;

import java.math.BigDecimal;
import java.util.Date;

import com.gmcc.boss.selfsvc.common.BasePO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;

/**
 * �˵�
 * 
 * @author g00140516
 * 
 */
public class MenuInfoPO extends BasePO
{
    private static final long serialVersionUID = 1L;
    
    private String termgrpid;
    
    private String menuid = "";
    
    private String menuname = "";
    
    private String shortMenuname = ""; // �̲˵��������˵����ȴ���6λʱ����ȡǰ6λ��ʡ�Ժ�
    
    private BigDecimal menulevel;
    
    private String parentid = "";
    
    private String guiobj = "";
    
    private String tiptext = "";
    
    private String busidetail = "";
    
    private BigDecimal sortorder;
    
    private String imgpath = "";
    
    private String region = "";
    
    // begin zKF66389 findbus����
    private String brandID = "";
    // end zKF66389 findbus����
    
    private String authcode = "";
    
    private Date createdate = new Date();
    
    private BigDecimal status;
    
    private Date statusdate = new Date();
    
    private String position = "";
    
    private String imgpath2 = "";
    
    //add begin g00140516 2011/10/25 R003C11L10n01 ����������ܰ��ʾ    
    private String additionalInfo = "";   
    //add end g00140516 2011/10/25 R003C11L10n01 ����������ܰ��ʾ
    
    private String iscall = "";
    
    // add begin g00140516 2012/07/10 R003C12L07n01 OR_NX_201205_649
    /**
     * ��Ч��ʽ
     */
    private String effectType = "";
    // add end g00140516 2012/07/10 R003C12L07n01 OR_NX_201205_649
    
    // �Ƿ��ǲ�Ʒ���ñ���Ĳ�Ʒ 1����
    private String isProd = "";
    
    // ��Ʒ���ñ�PO
    private ProdConfigPO prodConfigPO = new ProdConfigPO();
    
    // add begin m00227318 2013/02/06 R003C13L01n01 ���ģ��޸ģ����ӿ�ѡ��֤��ʽ
    // �������ӿ�ѡ��֤��ʽ
    private String availableAuthCode = "";
    // add end m00227318 2013/02/06 R003C13L01n01 ���ģ��޸ģ����ӿ�ѡ��֤��ʽ
    
    //add begin sWX219697 2014-9-15 11:26:30 OR_SD_201409_556_�����ն�Ӫ�������Ż�
    //��ӦISSS����Ӫ��ƽ̨��offercode
    private String iSSSCode;
    //add begin sWX219697 2014-9-15 11:26:30 OR_SD_201409_556_�����ն�Ӫ�������Ż�
    
    // add begin wWX217192 2014-10-20 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ���������Ż�������
    // ɽ��ҵ������˵����շ����ѯ�ı�־ 1:�������ײ��� 2:��ֵҵ���� 3:����ҵ����
    private String type;
    // add begin wWX217192 2014-10-20 OR_SD_201402_795_ɽ��_���������ն˹���ƽ̨��Ʒ���������Ż�������
    
    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public Date getCreatedate()
    {
        return (Date)createdate.clone();
    }
    
    public void setCreatedate(Date createdate)
    {
        this.createdate = (Date)createdate.clone();
    }
    
    public String getMenuid()
    {
        return menuid;
    }
    
    public void setMenuid(String menuid)
    {
        this.menuid = menuid;
    }
    
    public String getMenuname()
    {
        return menuname;
    }
    
    public void setMenuname(String menuname)
    {
        this.menuname = menuname;
    }
    
    public String getParentid()
    {
        return parentid;
    }
    
    public void setParentid(String parentid)
    {
        this.parentid = parentid;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public BigDecimal getSortorder()
    {
        return sortorder;
    }
    
    public void setSortorder(BigDecimal sortorder)
    {
        this.sortorder = sortorder;
    }
    
    public BigDecimal getStatus()
    {
        return status;
    }
    
    public void setStatus(BigDecimal status)
    {
        this.status = status;
    }
    
    public BigDecimal getMenulevel()
    {
        return menulevel;
    }
    
    public void setMenulevel(BigDecimal menulevel)
    {
        this.menulevel = menulevel;
    }
    
    public String getGuiobj()
    {
        return guiobj;
    }
    
    public void setGuiobj(String guiobj)
    {
        this.guiobj = guiobj;
    }
    
    public String getTiptext()
    {
        return tiptext;
    }
    
    public void setTiptext(String tiptext)
    {
        this.tiptext = tiptext;
    }
    
    public String getImgpath()
    {
        return imgpath;
    }
    
    public void setImgpath(String imgpath)
    {
        this.imgpath = imgpath;
    }
    
    public String getAuthcode()
    {
        return authcode;
    }
    
    public void setAuthcode(String authcode)
    {
        this.authcode = authcode;
    }
    
    public Date getStatusdate()
    {
        return (Date)statusdate.clone();
    }
    
    public void setStatusdate(Date statusdate)
    {
        this.statusdate = (Date)statusdate.clone();
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[" + this.getClass() + "[");
        sb.append(", menuid:" + this.getMenuid());
        sb.append(", menuname:" + this.getMenuname());
        sb.append(", level:" + this.getMenulevel());
        sb.append(", parentid:" + this.getParentid());
        sb.append(", guiobj:" + this.getGuiobj());
        sb.append(", tiptext:" + this.getTiptext());
        sb.append(", sortorder:" + this.getSortorder());
        sb.append(", imgpath:" + this.getImgpath());
        sb.append(", region:" + this.getRegion());
        sb.append(", brandid:" + this.getBrandID());
        sb.append(", authcode:" + this.getAuthcode());
        sb.append(", createdate:" + this.getCreatedate());
        sb.append(", status:" + this.getStatus());
        sb.append(", statusdate:" + this.getStatusdate());
        sb.append(", availableauthcode:" + this.getAvailableAuthCode());
        sb.append(", iSSSCode:" + this.getiSSSCode());
        sb.append("]]");
        
        return super.toString();
    }
    
    public String getShortMenuname()
    {
        return shortMenuname;
    }
    
    public void setShortMenuname(String shortMenuname)
    {
        this.shortMenuname = shortMenuname;
    }
    
    public String getBusidetail()
    {
        return busidetail;
    }
    
    public void setBusidetail(String busidetail)
    {
        this.busidetail = busidetail;
    }

    public String getImgpath2()
    {
        return imgpath2;
    }

    public void setImgpath2(String imgpath2)
    {
        this.imgpath2 = imgpath2;
    }

	public String getTermgrpid() 
	{
		return termgrpid;
	}

	public void setTermgrpid(String termgrpid) 
	{
		this.termgrpid = termgrpid;
	}

    public String getIscall()
    {
        return iscall;
    }

    public void setIscall(String iscall)
    {
        this.iscall = iscall;
    }

    public String getEffectType()
    {
        return effectType;
    }

    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public String getIsProd()
    {
        return isProd;
    }

    public ProdConfigPO getProdConfigPO()
    {
        return prodConfigPO;
    }

    public void setIsProd(String isProd)
    {
        this.isProd = isProd;
    }

    public void setProdConfigPO(ProdConfigPO prodConfigPO)
    {
        this.prodConfigPO = prodConfigPO;
    }

	public String getAvailableAuthCode()
	{
		return availableAuthCode;
	}

	public void setAvailableAuthCode(String availableAuthCode)
	{
		this.availableAuthCode = availableAuthCode;
	}

    public String getiSSSCode()
    {
        return iSSSCode;
    }

    public void setiSSSCode(String iSSSCode)
    {
        this.iSSSCode = iSSSCode;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

    
}