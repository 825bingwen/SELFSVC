/*
 * �ļ�����TerminalInfoPO.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �������K����Ϣ��
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.terminfo.model;

import java.util.List;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * �ն���Ϣ
 * @author g00140516
 *
 */
public class TerminalInfoPO extends BasePO
{
    private static final long serialVersionUID = 1L;

    private String termgrpid = "";
    private String termid = "";
    private String termname = "";
    private String ipaddr = "";
    private String mac = "";
    private String operid = "";
    private String unionuserid = "";
    private String unionpaycode = "";
    private String browsertype = "";
    private String termspecial = "";
    private String sockaddr = "";
    private String region = "";
    private String orgid = "";
    // add begion yKF28472 OR_huawei_201305_474
    private String cityOrgid = "";
    // add end yKF28472 OR_huawei_201305_474
    private String orgname = "";
    private String providercode = "";
    private String machinemodel = "";
    private String trmcode = "";
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    private String bankno = "";
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    // add begin YKF70747 2012/04/28 R003C12L04n01 OR_SD_201203_818 
    /**
     * �Ƿ�֧�ֽ�������ѡ���ܣ�
     * false���ն˻�֧�ִ�������ʽ����ԭ�����̣��û���ͨ�����������в�����
     * true���ն˻�֧�ֽ������̣��������̣��û���ͨ����������ѡ����
     */
    private boolean isSuppKey = false;    
    // add end YKF70747 2012/04/28  R003C12L04n01 OR_SD_201203_818 
    
    /**
     * �ն˿��õĲ˵�ID�б�
     */
//    private List menuList = null;
    
    /**
     * �����Ϣ
     */
    private String plugin = "";
    
    // add begin g00140516 2013/02/16 R003C13L02n01 �ն˻�����ʱ����ʾ����ͣ�ṩ����
    private String isLocked = "";
    // add end g00140516 2013/02/16 R003C13L02n01 �ն˻�����ʱ����ʾ����ͣ�ṩ����
    
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOperid() {
		return operid;
	}
	public void setOperid(String operid) {
		this.operid = operid;
	}
	public String getBrowsertype() {
		return browsertype;
	}
	public void setBrowsertype(String browsertype) {
		this.browsertype = browsertype;
	}
	public String getTermspecial() {
		return termspecial;
	}
	public void setTermspecial(String termspecial) {
		this.termspecial = termspecial;
	}
	public String getSockaddr() {
		return sockaddr;
	}
	public void setSockaddr(String sockaddr) {
		this.sockaddr = sockaddr;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getProvidercode() {
		return providercode;
	}
	public void setProvidercode(String providercode) {
		this.providercode = providercode;
	}
	public String getMachinemode() {
		return machinemodel;
	}
	public void setMachinemode(String machinemodel) {
		this.machinemodel = machinemodel;
	}
	public String getTrmcode() {
		return trmcode;
	}
	public void setTrmcode(String trmcode) {
		this.trmcode = trmcode;
	}
//	public List getMenuList() {
//		return menuList;
//	}
//	public void setMenuList(List menuList) {
//		this.menuList = menuList;
//	}
	public String getPlugin() {
		return plugin;
	}
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}
    public String getUnionuserid()
    {
        return unionuserid;
    }
    public void setUnionuserid(String unionuserid)
    {
        this.unionuserid = unionuserid;
    }
    public String getUnionpaycode()
    {
        return unionpaycode;
    }
    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    public String getMachinemodel()
    {
        return machinemodel;
    }
    public void setMachinemodel(String machinemodel)
    {
        this.machinemodel = machinemodel;
    }
	public String getTermgrpid() 
	{
		return termgrpid;
	}
	public void setTermgrpid(String termgrpid) 
	{
		this.termgrpid = termgrpid;
	}
	
	// add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    public String getBankno()
    {
        return bankno;
    }
    public void setBankno(String bankno)
    {
        this.bankno = bankno;
    }
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
   
    public boolean isSuppKey()
    {
        return isSuppKey;
    }
    public void setSuppKey(boolean isSuppKey)
    {
        this.isSuppKey = isSuppKey;
    }
    
	public String getIsLocked()
	{
		return isLocked;
	}
	
	public void setIsLocked(String isLocked)
	{
		this.isLocked = isLocked;
	}
    public String getCityOrgid()
    {
        return cityOrgid;
    }
    public void setCityOrgid(String cityOrgid)
    {
        this.cityOrgid = cityOrgid;
    }
}
