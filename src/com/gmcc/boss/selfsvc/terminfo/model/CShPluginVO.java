/*
 * 文件名：CShPluginVO.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：插件类
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.terminfo.model;

import java.lang.reflect.Field;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 插件
 * 
 * @author g00140516
 * 
 */
public class CShPluginVO extends BasePO
{
    
    private static final long serialVersionUID = 2938586700549329636L;
    
    private String providerid;
    
    private String browserType;
    
    private String printerVer;
    
    private String prtftpaddr;
    
    private String prtpluginflag;
    
    private String invprinterVer;
    
    private String invprtftpaddr;
    
    private String invprtpluginflag;
    
    private String keyboardVer;
    
    private String keybrdftpaddr;
    
    private String keybrdpluginflag;
    
    private String cashVer;
    
    private String cashftpaddr;
    
    private String cashpluginflag;
    
    private String cardVer;
    
    private String cardftpaddr;
    
    private String cardpluginflag;
    
    private String managerVer;
    
    private String mgrftpaddr;
    
    private String mgrpluginflag;
    
    private String servipaddr;
    
    private String ftpusername;
    
    private String ftppwd;
    
    /**
     * 二代身份证读卡器控件版本
     */
    private String idcardver;
    
    /**
     * 二代身份证读卡器控件下载目录
     */
    private String idcardaddr;
    
    /**
     * 二代身份证读卡器控件标识
     */
    private String idcardpluginflag;
    
    /**
     * 银联接口控件版本
     */
    private String unionVer;
    
    /**
     * 银联接口控件下载目录
     */
    private String unionftpaddr;
    
    /**
     * 银联接口控件标识
     */
    private String unionpluginflag;
    /**
     * sim卡读卡器
     */
    private String simcardpluginidflag;
    
    //add begin sWX219697 2014-11-25 20:29:10 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
    /**
     * 湖北写卡器
     */
    private String writecardpluginflag;
    //add end sWX219697 2014-11-25 20:29:10 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
    
    // begin zKF28472 OR_NX_201303_280
    
    private String movecardver;
    
    private String movecardaddr;
    
    private String movecardflag;
    
    private String writecardver;
    
    private String writecardaddr;
    
    private String writecardflag;
    
    // end zKF28472 OR_NX_201303_280
    
    private String pursever;
    
    private String purseftpaddr;
    
    private String pursepluginflag;
    
    private String sellgoodsver;
    
    private String sellgoodsftpaddr;
    
    private String sellgoodspluginflag;
    
    /**
     * @return the browserType
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBrowserType()
    {
        return browserType;
    }
    
    /**
     * @param browserType the browserType to set
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBrowserType(String browserType)
    {
        this.browserType = browserType;
    }
    
    /**
     * @return the cardftpaddr
     */
    public String getCardftpaddr()
    {
        return cardftpaddr;
    }
    
    /**
     * @param cardftpaddr the cardftpaddr to set
     */
    public void setCardftpaddr(String cardftpaddr)
    {
        this.cardftpaddr = cardftpaddr;
    }
    
    /**
     * @return the cardpluginflag
     */
    public String getCardpluginflag()
    {
        return cardpluginflag;
    }
    
    /**
     * @param cardpluginflag the cardpluginflag to set
     */
    public void setCardpluginflag(String cardpluginflag)
    {
        this.cardpluginflag = cardpluginflag;
    }
    
    /**
     * @return the cardVer
     */
    public String getCardVer()
    {
        return cardVer;
    }
    
    /**
     * @param cardVer the cardVer to set
     */
    public void setCardVer(String cardVer)
    {
        this.cardVer = cardVer;
    }
    
    /**
     * @return the cashftpaddr
     */
    public String getCashftpaddr()
    {
        return cashftpaddr;
    }
    
    /**
     * @param cashftpaddr the cashftpaddr to set
     */
    public void setCashftpaddr(String cashftpaddr)
    {
        this.cashftpaddr = cashftpaddr;
    }
    
    /**
     * @return the cashpluginflag
     */
    public String getCashpluginflag()
    {
        return cashpluginflag;
    }
    
    /**
     * @param cashpluginflag the cashpluginflag to set
     */
    public void setCashpluginflag(String cashpluginflag)
    {
        this.cashpluginflag = cashpluginflag;
    }
    
    /**
     * @return the cashVer
     */
    public String getCashVer()
    {
        return cashVer;
    }
    
    /**
     * @param cashVer the cashVer to set
     */
    public void setCashVer(String cashVer)
    {
        this.cashVer = cashVer;
    }
    
    /**
     * @return the invprinterVer
     */
    public String getInvprinterVer()
    {
        return invprinterVer;
    }
    
    /**
     * @param invprinterVer the invprinterVer to set
     */
    public void setInvprinterVer(String invprinterVer)
    {
        this.invprinterVer = invprinterVer;
    }
    
    /**
     * @return the invprtftpaddr
     */
    public String getInvprtftpaddr()
    {
        return invprtftpaddr;
    }
    
    /**
     * @param invprtftpaddr the invprtftpaddr to set
     */
    public void setInvprtftpaddr(String invprtftpaddr)
    {
        this.invprtftpaddr = invprtftpaddr;
    }
    
    /**
     * @return the invprtpluginflag
     */
    public String getInvprtpluginflag()
    {
        return invprtpluginflag;
    }
    
    /**
     * @param invprtpluginflag the invprtpluginflag to set
     */
    public void setInvprtpluginflag(String invprtpluginflag)
    {
        this.invprtpluginflag = invprtpluginflag;
    }
    
    /**
     * @return the keyboardVer
     */
    public String getKeyboardVer()
    {
        return keyboardVer;
    }
    
    /**
     * @param keyboardVer the keyboardVer to set
     */
    public void setKeyboardVer(String keyboardVer)
    {
        this.keyboardVer = keyboardVer;
    }
    
    /**
     * @return the keybrdftpaddr
     */
    public String getKeybrdftpaddr()
    {
        return keybrdftpaddr;
    }
    
    /**
     * @param keybrdftpaddr the keybrdftpaddr to set
     */
    public void setKeybrdftpaddr(String keybrdftpaddr)
    {
        this.keybrdftpaddr = keybrdftpaddr;
    }
    
    /**
     * @return the keybrdpluginflag
     */
    public String getKeybrdpluginflag()
    {
        return keybrdpluginflag;
    }
    
    /**
     * @param keybrdpluginflag the keybrdpluginflag to set
     */
    public void setKeybrdpluginflag(String keybrdpluginflag)
    {
        this.keybrdpluginflag = keybrdpluginflag;
    }
    
    /**
     * @return the managerVer
     */
    public String getManagerVer()
    {
        return managerVer;
    }
    
    /**
     * @param managerVer the managerVer to set
     */
    public void setManagerVer(String managerVer)
    {
        this.managerVer = managerVer;
    }
    
    /**
     * @return the mgrftpaddr
     */
    public String getMgrftpaddr()
    {
        return mgrftpaddr;
    }
    
    /**
     * @param mgrftpaddr the mgrftpaddr to set
     */
    public void setMgrftpaddr(String mgrftpaddr)
    {
        this.mgrftpaddr = mgrftpaddr;
    }
    
    /**
     * @return the mgrpluginflag
     */
    public String getMgrpluginflag()
    {
        return mgrpluginflag;
    }
    
    /**
     * @param mgrpluginflag the mgrpluginflag to set
     */
    public void setMgrpluginflag(String mgrpluginflag)
    {
        this.mgrpluginflag = mgrpluginflag;
    }
    
    /**
     * @return the printerVer
     */
    public String getPrinterVer()
    {
        return printerVer;
    }
    
    /**
     * @param printerVer the printerVer to set
     */
    public void setPrinterVer(String printerVer)
    {
        this.printerVer = printerVer;
    }
    
    /**
     * @return the providerid
     */
    public String getProviderid()
    {
        return providerid;
    }
    
    /**
     * @param providerid the providerid to set
     */
    public void setProviderid(String providerid)
    {
        this.providerid = providerid;
    }
    
    /**
     * @return the prtftpaddr
     */
    public String getPrtftpaddr()
    {
        return prtftpaddr;
    }
    
    /**
     * @param prtftpaddr the prtftpaddr to set
     */
    public void setPrtftpaddr(String prtftpaddr)
    {
        this.prtftpaddr = prtftpaddr;
    }
    
    /**
     * @return the prtpluginflag
     */
    public String getPrtpluginflag()
    {
        return prtpluginflag;
    }
    
    /**
     * @param prtpluginflag the prtpluginflag to set
     */
    public void setPrtpluginflag(String prtpluginflag)
    {
        this.prtpluginflag = prtpluginflag;
    }
    
    /**
     * @return the ftppwd
     */
    public String getFtppwd()
    {
        return ftppwd;
    }
    
    /**
     * @param ftppwd the ftppwd to set
     */
    public void setFtppwd(String ftppwd)
    {
        this.ftppwd = ftppwd;
    }
    
    /**
     * @return the ftpusername
     */
    public String getFtpusername()
    {
        return ftpusername;
    }
    
    /**
     * @param ftpusername the ftpusername to set
     */
    public void setFtpusername(String ftpusername)
    {
        this.ftpusername = ftpusername;
    }
    
    /**
     * @return the servipaddr
     */
    public String getServipaddr()
    {
        return servipaddr;
    }
    
    /**
     * @param servipaddr the servipaddr to set
     */
    public void setServipaddr(String servipaddr)
    {
        this.servipaddr = servipaddr;
    }
    
    public String getIdcardaddr()
    {
        return idcardaddr;
    }
    
    public void setIdcardaddr(String idcardaddr)
    {
        this.idcardaddr = idcardaddr;
    }
    
    public String getIdcardpluginflag()
    {
        return idcardpluginflag;
    }
    
    public void setIdcardpluginflag(String idcardflag)
    {
        this.idcardpluginflag = idcardflag;
    }
    
    public String getIdcardver()
    {
        return idcardver;
    }
    
    public void setIdcardver(String idcardver)
    {
        this.idcardver = idcardver;
    }
    
    public String getUnionVer()
    {
        return unionVer;
    }
    
    public void setUnionVer(String unionVer)
    {
        this.unionVer = unionVer;
    }
    
    public String getUnionftpaddr()
    {
        return unionftpaddr;
    }
    
    public void setUnionftpaddr(String unionftpaddr)
    {
        this.unionftpaddr = unionftpaddr;
    }
    
    public String getUnionpluginflag()
    {
        return unionpluginflag;
    }
    
    public void setUnionpluginflag(String unionpluginflag)
    {
        this.unionpluginflag = unionpluginflag;
    }
    
    public String getPursever()
    {
        return pursever;
    }
    
    public void setPursever(String pursever)
    {
        this.pursever = pursever;
    }
    
    public String getPurseftpaddr()
    {
        return purseftpaddr;
    }
    
    public void setPurseftpaddr(String purseftpaddr)
    {
        this.purseftpaddr = purseftpaddr;
    }
    
    public String getSellgoodsftpaddr()
    {
        return sellgoodsftpaddr;
    }
    
    public void setSellgoodsftpaddr(String sellgoodsftpaddr)
    {
        this.sellgoodsftpaddr = sellgoodsftpaddr;
    }
    
    public String getPursepluginflag()
    {
        return pursepluginflag;
    }
    
    public void setPursepluginflag(String pursepluginflag)
    {
        this.pursepluginflag = pursepluginflag;
    }
    
    public String getSellgoodsver()
    {
        return sellgoodsver;
    }
    
    public void setSellgoodsver(String sellgoodsver)
    {
        this.sellgoodsver = sellgoodsver;
    }
    
    public String getSellgoodspluginflag()
    {
        return sellgoodspluginflag;
    }
    
    public void setSellgoodspluginflag(String sellgoodspluginflag)
    {
        this.sellgoodspluginflag = sellgoodspluginflag;
    }
    
    public String getSimcardpluginidflag()
    {
        return simcardpluginidflag;
    }

    public void setSimcardpluginidflag(String simcardpluginidflag)
    {
        this.simcardpluginidflag = simcardpluginidflag;
    }

    public String getMovecardver()
    {
        return movecardver;
    }

    public void setMovecardver(String movecardver)
    {
        this.movecardver = movecardver;
    }

    public String getMovecardaddr()
    {
        return movecardaddr;
    }

    public void setMovecardaddr(String movecardaddr)
    {
        this.movecardaddr = movecardaddr;
    }

    public String getMovecardflag()
    {
        return movecardflag;
    }

    public void setMovecardflag(String movecardflag)
    {
        this.movecardflag = movecardflag;
    }

    public String getWritecardver()
    {
        return writecardver;
    }

    public void setWritecardver(String writecardver)
    {
        this.writecardver = writecardver;
    }

    public String getWritecardaddr()
    {
        return writecardaddr;
    }

    public void setWritecardaddr(String writecardaddr)
    {
        this.writecardaddr = writecardaddr;
    }

    public String getWritecardflag()
    {
        return writecardflag;
    }

    public void setWritecardflag(String writecardflag)
    {
        this.writecardflag = writecardflag;
    }
    
    /**
	 * @return 返回 writecardpluginflag
	 */
	public String getWritecardpluginflag() 
	{
		return writecardpluginflag;
	}

	/**
	 * @param 对writecardpluginflag进行赋值
	 */
	public void setWritecardpluginflag(String writecardpluginflag) 
	{
		this.writecardpluginflag = writecardpluginflag;
	}

	public Object getAttributeValue(String key)
    {
    	Object obj = null;
    	
    	Field[] fields = this.getClass().getDeclaredFields();
    	if (fields != null && fields.length > 0)
    	{
    		for (int i = 0; i < fields.length; i++)
    		{
    			if (key.equals(fields[i].getName()))
    			{
    				try 
    				{
						obj = fields[i].get(this);						
					} 
    				catch (IllegalArgumentException e) 
					{						
						e.printStackTrace();
					} 
    				catch (IllegalAccessException e) 
    				{						
						e.printStackTrace();
					}
    				
    				break;
    			}
    		}
    	}
    	
    	return obj;
    }
}
