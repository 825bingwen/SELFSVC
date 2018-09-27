package com.gmcc.boss.selfsvc.media.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.prodInstall.action.ChoSerNOUserAction;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.media.service.MediaService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ��ý�岥�Ų���Action
 * �ն˻����ɹ�沥�������ļ�adv.wpl
 * �ն˻������������������ļ�sc.wpl
 * @author  yKF28472
 * @version  [�汾��, 2010-12-06]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MediaAction extends BaseAction
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//private static final Logger log = Logger.getLogger(MediaAction.class);
	private static Log log = LogFactory.getLog(MediaAction.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	
	// ͨ��springע������service����
	private MediaService mediaService;
	
    /** 
     * ȡ������Դ�����ļ�����
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
	public String getMediaScList() 
	{
		// �ն���Ϣ
		TerminalInfoPO terminalInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��������Ϣ
		this.getResponse().setContentType("text/xml;charset=UTF-8");

        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
        	// begin zKF66389 9�·�findbugs�޸�
            //e.printStackTrace();
        	log.error(e.toString());
        	// end zKF66389 9�·�findbugs�޸�
        }
        
        // ����
        String termid = terminalInfo.getTermid();
        String region = terminalInfo.getRegion();
        
        // ��ȡ�������·�� modify begin wWX217192 2014-12-18 for ɽ���ֳ���������
        String servletContext = "";
        if(Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
        	servletContext = this.getRequest().getSession().getServletContext().getRealPath("/");
        }
        else
        {
        	servletContext = getClass().getClassLoader().getResource("/").getPath();
        }
        // ��ȡ�������·�� modify end wWX217192 2014-12-18 for ɽ���ֳ���������
        
        int index = servletContext.indexOf("WEB-INF");
        if (index != -1)
        {
            servletContext = servletContext.substring(0, index);
        }
        
        // ִ��
        String xml = "0";
        
        // modify begin wWX217192 2014-09-19 OR_HUB_201403_1773 �����ն�LOGO����������һ�����¹���
        if(useProvinceSet())
        {
        	xml = mediaService.getProvinceScList(Constants.SC_TYPE, servletContext);
        }
        else
        {
        	// ���
        	xml = mediaService.getMediaWpl(termid, region, Constants.SC_TYPE, servletContext);
        }
        // modify end wWX217192 2014-09-19 OR_HUB_201403_1773    
        
        // ���ؽ��
        // begin zKF66389 9�·�findbugs�޸�
        if (xml != null)
        {
        	writer.println(xml);
        }
        // end zKF66389 9�·�findbugs�޸�
        
		return null;
	}

    /** 
     * �жϵ�ǰʡ���Ƿ�ʹ��ʡͳһ����
     * �����ǰʡ��Ϊ������ɽ�������������ش���ôʹ��ʡͳһ��������
     * @return true������ȫʡͳһ�������ã�false��δ����ȫʡͳһ��������
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by l00263786 2015-06-02 OR_SD_201504_102 �����ն�����ʵ��ȫʡͳһ����
     */
    private boolean useProvinceSet()
    {
        return (Constants.PROOPERORGID_HUB.equals(CommonUtil.getParamValue(Constants.PROVINCE_ID))
                ||Constants.PROOPERORGID_SD.equals(CommonUtil.getParamValue(Constants.PROVINCE_ID)))
        		&& "1".equals(CommonUtil.getParamValue(Constants.SH_SCREEN_FLAG));
    }
    
    /** 
     * ȡ�����Դ�����ļ�����
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
	public String getMediaAdvList() 
	{
		// �ն���Ϣ
		TerminalInfoPO terminalInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// ��������Ϣ
		this.getResponse().setContentType("text/xml;charset=UTF-8");

        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        // ����
        String termid = terminalInfo.getTermid();
        String region = terminalInfo.getRegion();
        String realPath = this.getRequest().getSession().getServletContext().getRealPath("/");
        
        // ִ��
        String xml = "0"; // �淵�ؽ��
        
        xml = mediaService.getMediaWpl(termid, region, Constants.ADV_TYPE, realPath);
        
        // begin zKF66389 9�·�findbugs�޸�
        if (xml != null)
        {
        	writer.println(xml);
        }
        // end zKF66389 9�·�findbugs�޸�
        
		return null;
	}
	
	/**
	 * ��沥����ҳ��
	 * <������ϸ����>
	 * @return
	 * @throws Exception
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public String advIndex() 
    {
	    return SUCCESS;
    }
  	
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}