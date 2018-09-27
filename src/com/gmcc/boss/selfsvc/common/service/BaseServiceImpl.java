package com.gmcc.boss.selfsvc.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.SelfSvcCall;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.dao.SelfSvcDaoImpl;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * <����������Service������>
 * <������ϸ����>
 * 
 * @author  wWX217192
 * @version  [�汾��, Apr 22, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by wWX219697 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
 */
public class BaseServiceImpl 
{
	protected SelfSvcCall selfSvcCall;
	
	private SelfSvcDaoImpl selfSvcDaoImpl;
	
	/**
	 * ��ȡrequest��ֵ
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        
        return request;
	}
	
	public HttpServletResponse getResponse()
	{
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		return response;
	}
	
	/**
	 * ��ȡsession��ֵ
	 * @return
	 */
	public HttpSession getSession()
	{
		HttpSession session = this.getRequest().getSession();
		
		return session;
	}

    /**
     * <��¼ҵ����־>
     * <������ϸ����>
     * @param busiType ҵ������
     * @param recFormnum ҵ����ˮ��
     * @param recFee ҵ��������ã��޷��õ�Ϊ0
     * @param recStatus 0:�ɹ���1:ʧ��
     * @param recStatusDesc ������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertRecLog(String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
    	// �ն˻���Ϣ
        TerminalInfoPO termInfo = this.getTermInfo();
        
        // ҵ���Ƽ�ӪҵԱ���ֻ�����
        String rectel = "";
        
        // �ͻ���Ϣ
        NserCustomerSimp customer = this.getCustomer();
        
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
        
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// ҵ������λ����
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// ����Ա����
        
        selfSvcLogVO.setBusitype(busiType);// ҵ������
        selfSvcLogVO.setRecformnum(recFormnum);// ҵ����ˮ��
        selfSvcLogVO.setRecfee(recFee);// ҵ��������
        selfSvcLogVO.setRecstatus(recStatus);// ҵ������״̬
        if(recStatusDesc.length()>256)
        {
            recStatusDesc = recStatusDesc.substring(0,256);
        }
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// ҵ����������
        selfSvcLogVO.setBrandID(customer == null ? "" : customer.getBrandID());  //�û�Ʒ��
        selfSvcLogVO.setRectel(rectel);
        
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <��¼ҵ����־>
     * <������ϸ����>
     * @param servNumber �ֻ�����
     * @param busiType ҵ������
     * @param recFormnum ҵ����ˮ��
     * @param recFee ҵ��������ã��޷��õ�Ϊ0
     * @param recStatus 0:�ɹ���1:ʧ��
     * @param recStatusDesc ������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertRecLogTelnum(String servNumber, String busiType, String recFormnum, String recFee, String recStatus,
            String recStatusDesc)
    {
    	// �ն˻���Ϣ
        TerminalInfoPO termInfo = this.getTermInfo();
        
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
        selfSvcLogVO.setOrgid(termInfo == null ? "" : termInfo.getOrgid());// ҵ������λ����
        selfSvcLogVO.setOperid(termInfo == null ? "" : termInfo.getOperid());// ����Ա����
        
        selfSvcLogVO.setBusitype(busiType);// ҵ������
        selfSvcLogVO.setRecformnum(recFormnum);// ҵ����ˮ��
        selfSvcLogVO.setRecfee(recFee);// ҵ��������
        selfSvcLogVO.setRecstatus(recStatus);// ҵ������״̬
        selfSvcLogVO.setRecstatusdesc(recStatusDesc);// ҵ����������
        selfSvcLogVO.setRectel(rectel);
        
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * <��ȡ�ն˻���Ϣ>
     * <������ϸ����>
     * @param recStatusDesc ������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public TerminalInfoPO getTermInfo()
    {
    	// �ն˻���Ϣ
       return (TerminalInfoPO)this.getSession().getAttribute(Constants.TERMINAL_INFO);
    }
    
    /**
     * <��ȡ��¼���û���Ϣ>
     * <������ϸ����>
     * @param recStatusDesc ������Ϣ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX216858 2015-3-30 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż�
     */
    public NserCustomerSimp getCustomer()
    {
    	return (NserCustomerSimp)this.getSession().getAttribute(Constants.USER_INFO);
    }
    
    @Autowired
    public void setSelfSvcCall(SelfSvcCall selfSvcCall)
    {
        this.selfSvcCall = selfSvcCall;
    }

	public SelfSvcDaoImpl getSelfSvcDaoImpl() {
		return selfSvcDaoImpl;
	}

	@Autowired
	public void setSelfSvcDaoImpl(SelfSvcDaoImpl selfSvcDaoImpl) {
		this.selfSvcDaoImpl = selfSvcDaoImpl;
	}
}
