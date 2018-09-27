/*
 * �ļ�����BaseAction.java
 * ��Ȩ��CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g69866
 * �޸�ʱ�䣺2009-8-5
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.common.base.CommandContext;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.common.model.UserSatfyVO;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.huawei.alert.business.IAlertMsgService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * 
 * BaseAction <������ϸ����>
 * 
 * @author �����ն���Ŀ��
 * @version [�汾��, Dec 10, 2010]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    // HttpServletRequest
    private HttpServletRequest request;
    
    // HttpServletResponse
    private HttpServletResponse response;
    
    // selfSvcService
    private transient SelfSvcService selfSvcService;
    
    // add begin g00140516 2012/08/07 R003C12L08n01 OR_HUB_201203_367
    private IAlertMsgService alertMsgService = null;
    // add end g00140516 2012/08/07 R003C12L08n01 OR_HUB_201203_367
    
    // add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 
    //�����û�����ȵ���֮�û��ֻ���
    private String servnumber = "";
    
    //�����û�����ȵ���֮��������
	private String totScore = "";
	
	//�����û�����ȵ���֮�������֣��ַ���
	private String speciSatfyScore = "";
	
	//�����û�����ȵ���֮��õ�������
	private String favorEC = "";
	// add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
	
	// add begin qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM
	/**
	 * USIM����ʾ��־
	 */
	private String uSIMflag = "0";
	// add end qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM

	// add begin jWX216858 2015-5-28 OR_SD_201503_949_�����ն�������ʡ�ɷѹ��ܵ�֧��
	/**
     * ��ǰ�˵�
     */
    private String curMenuId;
    // add end jWX216858 2015-5-28 OR_SD_201503_949_�����ն�������ʡ�ɷѹ��ܵ�֧��
    
	// add begin jWX216858 2015-5-13
    // js�汾�ţ�����20150513 ���IE�ͻ��˻���δ��������JSδ����
	public String getJsVersion()
	{
		return CommonUtil.getParamValue(Constants.SH_JSVERSION);
	}
	// add end jWX216858 2015-5-13
	
    /**
     * ��ȡfield error��ʶ <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getFieldErrFlag()
    {
        if (hasFieldErrors() || hasActionErrors())
        {
            return "true";
        }
        else
        {
            return "";
        }
    }
    
    /**
     * IoC��ʽע��HttpServletRequest
     * 
     * @param request
     */
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    /**
     * IoC��ʽע��HttpServletResponse
     * 
     * @param response
     */
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    /**
     * ��ȡHttpServletRequest <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public HttpServletRequest getRequest()
    {
        return this.request;
    }
    
    /**
     * ��ȡHttpServletResponse <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public HttpServletResponse getResponse()
    {
        return this.response;
    }
    
    /**
     * ҵ����־���� <������ϸ����>
     * 
     * @param busiType ҵ������
     * @param recFormnum ҵ����ˮ��
     * @param recFee ҵ���������
     * @param recStatus ҵ������״̬ 0���ɹ���1��ʧ��
     * @param recStatusDesc ҵ����������
     * @see [�ࡢ��#��������#��Ա]
     */
    protected void createRecLog(String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        // ҵ���Ƽ�ӪҵԱ���ֻ�����
        String rectel = "";
        
        // �ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// ����
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }
        }
        else
        {
            selfSvcLogVO.setRegion("");// ����
        }
       
        if (customer != null)
        {
            selfSvcLogVO.setServnumber(customer.getServNumber());// �ֻ�����
        }
        else
        {
            selfSvcLogVO.setServnumber("");// �ֻ�����
        }
        
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// �ն˱��
        selfSvcLogVO.setTourchoid(customer == null ? "" : customer.getContactId());// ͳһ�Ӵ���ˮ
        
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// ҵ��������λ����
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// ����Ա����
        
        selfSvcLogVO.setBusitype(busiType);// ҵ������
        selfSvcLogVO.setRecformnum(recFormnum);// ҵ����ˮ��
        selfSvcLogVO.setRecfee(recFee);// ҵ���������
        selfSvcLogVO.setRecstatus(recStatus);// ҵ������״̬
        if(recStatusDesc.length()>256)
        {
            recStatusDesc = recStatusDesc.substring(0,256);
        }
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// ҵ����������
        selfSvcLogVO.setBrandID(customer == null ? "" : customer.getBrandID());  //�û�Ʒ��
        selfSvcLogVO.setRectel(rectel);
        //modify end g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        
        selfSvcService.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <��¼ҵ����־>
     * <������ϸ����>
     * @param servNumber �ֻ�����
     * @param busiType ҵ������
     * @param recFormnum ҵ����ˮ��
     * @param recFee ҵ���������ã��޷��õ�Ϊ0
     * @param recStatus 0:�ɹ���1:ʧ��
     * @param recStatusDesc ������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
    protected void createRecLog(String servNumber, String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        // ҵ���Ƽ�ӪҵԱ���ֻ�����
        String rectel = "";
        
        SelfSvcLogVO selfSvcLogVO = new SelfSvcLogVO();
        if (termInfo != null)
        {
            selfSvcLogVO.setRegion(termInfo.getRegion());// ����
            
            Map<String, String> rectelInfo = (Map<String, String>) PublicCache.getInstance().getCachedData(Constants.SH_INFO_RECTEL);
            if (null != rectelInfo && rectelInfo.containsKey(termInfo.getTermid()))
            {
                rectel = rectelInfo.get(termInfo.getTermid());
            }            
        }
        else
        {
            selfSvcLogVO.setRegion("");// ����
        }
        
        selfSvcLogVO.setServnumber(servNumber);// �ֻ�����
        selfSvcLogVO.setTermid(termInfo == null ? "" : termInfo.getTermid());// �ն˱��
        selfSvcLogVO.setTourchoid("");// ͳһ�Ӵ���ˮ
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// ҵ��������λ����
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// ����Ա����
        
        selfSvcLogVO.setBusitype(busiType);// ҵ������
        selfSvcLogVO.setRecformnum(recFormnum);// ҵ����ˮ��
        selfSvcLogVO.setRecfee(recFee);// ҵ���������
        selfSvcLogVO.setRecstatus(recStatus);// ҵ������״̬
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// ҵ����������
        selfSvcLogVO.setRectel(rectel);
        //modify end g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
        
        selfSvcService.createRecLog(selfSvcLogVO);
    }
    
    /**
     * ���ɶ����������
     * <������ϸ����>
     * @param servNumber �ֻ���
     * @param sendTime �����������
     * @see [�ࡢ��#��������#��Ա]
     */
    protected String createRandomPassword(String servNumber,String sendTime)
    {
        // ������볤��
        int len = 0;
        String strLen = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_LEN);
        if (strLen == null || "".equals(strLen.trim()))
        {
            len = 6;
        }
        else
        {
            len = Integer.parseInt(strLen);
        }
        
        // �����������
        String randomPwd = CommonUtil.getRandomNum(len);
        
        // д��session
        this.getRequest().getSession().setAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD, 
                randomPwd + "_" + sendTime);// randomPass�����������
        
        // ����
        return randomPwd;
    }
    
    /**
     * ���������֤
     * 
     * @param servNumber �������
     * @param randomPwd �������
     * @return 1���ɹ���0���������-1����ʱ
     * @see 
     */
    protected String loginWithRandomPwd(String servNumber, String randomPwd)
    {
        // ���session���Ƿ���� �ֻ���+Constants.AUTHTYPE_RANDOMPWD key 
        Object obj = this.getRequest().getSession().getAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
        if (obj == null)
        {
            return "0";
        }
        
        String[] info = ((String) obj).split("_");
        
        //��������������
        if (!info[0].equalsIgnoreCase(randomPwd))
        {
            return "0";
        }
        
        // modify begin g00140516 2013/02/07 R003C13L01n01 OR_NX_201302_600
        //�������������ȷ��������Ч����֤
        if (info.length > 1)
        {         
            int periodOfValidity = 0;
            
            // ���ģ������������Ч�ڵ�λ��Ϊ��
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
            	String strValidity = (String) PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_VALIDITY);
                if (strValidity == null || "".equals(strValidity.trim()))
                {
                    periodOfValidity = 30;
                }
                else
                {
                    periodOfValidity = Integer.parseInt(strValidity);
                }
            }
            // ����ʡ��:��Ϊ����
            else
            {
            	String strValidity = (String) PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_VALIDITY);
                if (strValidity == null || "".equals(strValidity.trim()))
                {
                    periodOfValidity = 5 * 60;
                }
                else
                {
                    periodOfValidity = Integer.parseInt(strValidity) * 60;
                }
            }
            
            try
            {               
                String limitTime = CommonUtil.addDate(info[1], periodOfValidity);
                
                String currTime = CommonUtil.getCurrentTime();// ȡ�õ�ǰʱ��
                
                if (limitTime.compareTo(currTime) >= 0)
                {                   
                    //��֤ͨ��ʱ��ɾ��                    
                    this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
                    
                    return "1";
                }
                else
                {
                    //��֤��ʱʱ��ɾ��
                    this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
                    
                    return "-1";
                }
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                
                return "0";
            }
        }
        // modify end g00140516 2013/02/07 R003C13L01n01 OR_NX_201302_600
        
        //��֤ͨ��ʱ��ɾ�� 
        this.getRequest().getSession().removeAttribute(servNumber + Constants.AUTHTYPE_RANDOMPWD);
        
        return "1";     
    }
    
    /**
     * ��ʾ��Ϣת��
     * 
     * @param resultMsg ��̨�ӿڷ�����ʾ
     * @param orimsgid ԭ��ʾ��Ϣ����
     * @param errcode ��̨���صĴ������
     * @param params ����
     * @return
     * @see 
     */
    protected String getConvertMsg(String resultMsg, String orimsgid, String errcode, String[] params)
    {
        // modify begin g00140516 2012/11/29 R003C12L11n01 CRM���صĴ����벻����ת��
        // CRM���صĴ����벻����ת��
        if (errcode != null && !"".equals(errcode.trim()))
        {
            return resultMsg;
        }
        
        // ��ʾ��Ϣת����1��ת����������ת
        String convertFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ALERTMSG_CONVERTFLAG);
        if (!"1".equals(convertFlag))
        {
            return resultMsg;
        }
        
        // �û���Ϣ
        NserCustomerSimp customerInfor = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ����
        String channel = "bsacAtsv";
        
        // ����
        String region = termInfo.getRegion();
        
        // Ʒ��
        String brand = customerInfor == null ? "" : customerInfor.getBrandID();
        
        // Ĭ����ʾ��Ϣ
        String defaultInfo = (String) PublicCache.getInstance().getCachedData(Constants.SH_ALERTMSG_DEFAULTINFO);
        
        // ��������ն��ڲ��Ĵ��������ת������ת�������ʾ��Ϣ��Ĭ����ʾ��Ϣ����ʹ��ԭ�е���ʾ��Ϣ
        String newMsg = getAlertMsgService().getAlertMsg(orimsgid, channel, region, brand, params).getMsgText();
        if (defaultInfo.equalsIgnoreCase(newMsg))
        {
            newMsg = resultMsg;
        }
        // modify end g00140516 2012/11/29 R003C12L11n01 CRM���صĴ����벻����ת��      
        
        return newMsg;
    }
    
    /**
     * ͨ��groupid��ȡ�ֵ������
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid)
    {
    	return selfSvcService.getDictItemByGrp(groupid);
    }
    
    // add begion yKF28472 OR_huawei_201305_474
    /**
     * ����ȡС����REGION
     * <������ϸ����>
     * @param orgId
     * @param region
     * @return С����REGION
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getSmallregion(String cityOrgId, String region)
    {
        if (cityOrgId != null && !"".equals(cityOrgId))
        {
            List<DictItemPO> smallregionList = getDictItemByGrp("SMALLREGION");
            for(DictItemPO po : smallregionList)
            {
                if (po.getDictid().equals(cityOrgId))
                {
                    // ����С����region
                    return po.getDictname();
                }
            }
        }
        
        // δ���ֵ���в�ѯ������ʾ��728���е�
        return region;
    }
    // add end yKF28472 OR_huawei_201305_474
    
	/**
	 * �û�����ȵ���������ݿ�
	 * ��jspҳ������ش������ݣ����������ݿ�
	 * @see
	 * @return ��
	 * @remark create m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
	 */
	public void surveyUserSatfy()
	{
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
		// ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ͻ��ֻ���
        servnumber = customer.getServNumber();
        
        UserSatfyVO userSatfyVO = new UserSatfyVO();
        userSatfyVO.setUserNo(servnumber);
        userSatfyVO.setUserTotScore(totScore);
        userSatfyVO.setUserSpeciScore(speciSatfyScore);
        userSatfyVO.setUserFavorEC(favorEC);
        
        if (servnumber != null && totScore != null && speciSatfyScore != null && favorEC != null)
        {    //�������ݿ�
            selfSvcService.insertUserSatfy(userSatfyVO);
        }
	}
	
	/**
	 * ��ȡ�ն˻���Ϣ
	 * @return
	 */
	public  TerminalInfoPO getTerminalInfoPO()
	{
	    // �ӻ�����ȡ���ն˻���Ϣ
	    TerminalInfoPO termInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
	    return termInfoPO;
	}
	
    /** 
     * ȡ�õ�ǰ��¼�û�
     * 
     * @return NserCustomerSimp
     * @see [�ࡢ��#��������#��Ա]
     */
    public NserCustomerSimp getCustomerSimp()
    {
        return (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
    }
    
    /**
     * У�鵱ǰʱ����������ֵ�Ƿ���á�1�����ã�0��������
     * 
     * @throws Exception
     * @see 
     * @see [�ࡢ��#��������#��Ա]
     */
    public void checkTime() throws Exception
    {
        String xml = "0";
        
        // 2320-0025
        String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
        
        if (time != null && !"".equals(time.trim()))
        {
            // ��ǰʱ��
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");        
            String currTime = sdf.format(new Date());
            
            // ��ǰʱ����0025��2320֮��ʱ����
            String[] times = time.split("-");
            if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
            {
                xml = "1";
            }
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
    }
    
    /**
     * AJAX���÷�����Ϣ
     * 
     * @return void [��������˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    protected void writeXmlMsg(String msg)
    {
        PrintWriter writer = null;
        
        try
        {
            this.getResponse().setContentType("text/xml;charset=GBK");
            writer = this.getResponse().getWriter();
            
            if (null != writer)
            {
                writer.write(msg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // �����client
            if (writer != null)
            {
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
	
    public void setSelfSvcService(SelfSvcService selfSvcService)
    {
        this.selfSvcService = selfSvcService;
    }

    public IAlertMsgService getAlertMsgService()
    {
        return alertMsgService;
    }

    public void setAlertMsgService(IAlertMsgService alertMsgService)
    {
        this.alertMsgService = alertMsgService;
    }
    
	public String getTotScore() {
		return totScore;
	}

	public void setTotScore(String totScore) {
		this.totScore = totScore;
	}

	public String getSpeciSatfyScore() {
		return speciSatfyScore;
	}

	public void setSpeciSatfyScore(String speciSatfyScore) {
		this.speciSatfyScore = speciSatfyScore;
	}

	public String getFavorEC() {
		return favorEC;
	}

	public void setFavorEC(String favorEC) {
		this.favorEC = favorEC;
	}
	
	// add begin qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM
	/**
	 * @return the uSIMflag
	 */
    public String getuSIMflag()
    {
        return uSIMflag;
    }

    public void setuSIMflag(String uSIMflag)
    {
        this.uSIMflag = uSIMflag;
    }

	// add end qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM
	
	/**
	 * ����ͼƬ��֤��
	 * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 �����ն��û���¼��֤��ʽ
	 */
	public void createRandomCodeImage()
	{
		String randomCode = CommonUtil.createRandomCode(4);
		
		CommandContext.getLog().debug("ͼƬ��֤�룺" + randomCode);
		this.getRequest().getSession().setAttribute("randomCode", randomCode);
		
		try
		{
			 OutputStream out = this.getResponse().getOutputStream();
	         JPEGImageEncoder encode = JPEGCodec.createJPEGEncoder(out);
	         BufferedImage bi = CommonUtil.createBufferedImage(randomCode);
	         encode.encode(bi);
	         out.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * �ж��Ƿ���Է��Ͷ�����֤��
	 * ����ֵerrorMsgΪ������Է��ͣ���Ϊ�ղ���������
	 * @param servNum �ֻ���
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
	 */
	public String canSmsCode(String servNum)
	{
	    HttpSession session = this.getRequest().getSession();
	    String errorMsg = "";
	    // ��ȡ���ŷ���Ƶ�ʣ���λ����
        String sendCodeInterval = CommonUtil.getParamValue(ConstantsBase.SH_SENDCODEINTERVAL, "60");
        // �ӻ�����ȡ��һ�η��Ͷ��ŵ�ʱ��
        Long sendCodeTime = (Long)session.getAttribute(servNum);
        // ȡ��ǰʱ��
        long currentTime = new Date().getTime();
        // ��������е�ʱ�䲻Ϊ�գ��жϷ��Ͷ��ż�������
        if (null != sendCodeTime)
        {
            // ��ǰʱ�� - ��һ�η���ʱ�� <= ���ŷ���Ƶ��ʱ�� ���ȡ������֤�����Ƶ��
            if (currentTime - sendCodeTime <= Long.parseLong(sendCodeInterval) * 1000)
            {
                errorMsg = "����ȡ������֤�����Ƶ�������Ժ����ԣ�";
                return errorMsg;
            }
            
            // ��ȡ�೤ʱ����ֻ�����·����ٴε�ʱ��,��λ��Сʱ
            String smsNumTime = CommonUtil.getParamValue(ConstantsBase.SH_SMSNUMTIME);
            // ��ȡ�೤ʱ����ֻ�����·����ٴεĴ���
            String smsNum = CommonUtil.getParamValue(ConstantsBase.SH_SMSNUM);
            // ��װ��ѯ����
            RecordSmsCodePO recordSmsCodePO = new RecordSmsCodePO();
            // һ��ʱ��
            recordSmsCodePO.setSmsNumTime(Double.parseDouble(smsNumTime) / 24);
            // �ֻ���
            recordSmsCodePO.setServNum(servNum);
            // ִ�в�ѯ��䣬��ȡһ��ʱ���ڴ��ֻ����·����ż�¼�Ĵ���
            int sendNum = selfSvcService.qrySmsCodeNum(recordSmsCodePO);
            // ������ݿ������õĴ������ڵ��ڴ�SH_SMS_HISTORY��ȡ��������Ŀ�����ȡƵ��
            if (sendNum >= Integer.parseInt(smsNum))
            {
                errorMsg = "����ȡ������֤�����Ƶ�������Ժ����ԣ�";
                return errorMsg;
            }
        }
        return errorMsg;
	}
	
	/**
	 * ������֤�뷢�ͳɹ����¼��SH_SMS_HISTORY����
	 * <������ϸ����>
	 * @param servNum �ֻ���
	 * @param termInfo �ն���Ϣ
	 * @param randomPwd ��֤��
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
	 */
	public void insertSmsCode(String servNum, TerminalInfoPO termInfo, String randomPwd)
	{
	    // ��װ������Ϣ
        RecordSmsCodePO recordSmsCode = new RecordSmsCodePO();
        // idΪ������ʱ�������
        recordSmsCode.setId(DateUtil.curOnlyTime());
        // �ֻ���
        recordSmsCode.setServNum(servNum);
        // �ն���Ϣ
        recordSmsCode.setTermId(termInfo.getTermid());
        // ��������
        recordSmsCode.setSmsContent("���û�" + servNum + "�������������ųɹ��������" + randomPwd);
        // ִ�в�����佫��Ϣ��¼��SH_SMS_HISTORY����
        selfSvcService.insertSmsCode(recordSmsCode);
	}

	/**
	 * <��ȡʡ��id>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by hWX5316476 2015-6-15 OR_SD_201505_1022_ɽ�����ӳ�ֵ����������_�����ն˸���
	 */
    public String getProvinceId()
    {
        return CommonUtil.getParamValue("ProvinceID");
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
}