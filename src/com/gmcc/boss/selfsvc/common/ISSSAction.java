package com.gmcc.boss.selfsvc.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.model.OfferInfoVO;


/**
 * 
 * <ɽ������Ӫ��ƽ̨(ISSS)ҵ��Ӫ�������������>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Sep 18, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by sWX219697 2014-9-18 12:02:12 OR_SD_201409_556_�����ն�Ӫ�������Ż�
 */
public class ISSSAction extends BaseAction
{

	/**
	 *���л�
	 */
	private static final long serialVersionUID = 1L;
	
    public static final Log logger = LogFactory.getLog(ISSSAction.class);
	
	/**
	 * ��Ӧ�Ĳ˵�id
	 */
	private String offerMenu;
	
	/**
	 * ��Ʒid
	 */
	private String offerId;

	/**
	 * ս��ID������ʱʹ��
	 */
	private String treatment_id;
	
	/**
	 * �ֻ�����
	 */
	private String servnumber;
	
	/**
	 * ������
	 */
	private int status;
	
    /**
     * �û�����״̬ �����ɹ�
     */
    private static final String ORDERED = "ordered";
    
    /**
     * �û�����״̬ ����ʧ��
     */
    private static final String FAILED = "failed";
    
    /**
     * 4�����Ʒ���ʱΪ�����Ʒ����
     */
    private String offerCode;
	
    /**
     * Ӫ���ӿڷ��ص�ҵ���б�
     */
    private List<OfferInfoVO> offerInfoList = new ArrayList<OfferInfoVO>();
    
    /**
     * <����ISSS��Ӧ��session������������Ӫ��ƽ̨���������� ɽ��>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
    public void setISSSSession()
    {
		//�ַ���ʽ
		this.getResponse().setContentType("text/xml;charset=GBK");
        
		PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        	//����session������������
        	ISSSInvokeUtil.setISSSSession(servnumber, offerMenu, offerId, treatment_id, offerCode);
        	
        	writer.write("1");
        }
        catch (Exception e)
        {
           logger.error("����sessionʧ�ܣ�", e);
           writer.write("0");
        }
        
        writer.flush();
    	
    }
    
    /**
     * ��ѯ�Ƽ�ҵ���б�
     */
    public void qryOfferInfoList()
    {
        //����Ӫ���¼�webservice�ӿڣ����ڳ�ֵ�������ҵ��չʾ
        offerInfoList = ISSSInvokeUtil.eventInvoke(servnumber);

        // ת����JSON����
        JSONArray jsonArray = JSONArray.fromObject(offerInfoList);
        
        this.getResponse().setContentType("text/xml;charset=GBK");
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
        	if (e != null)
        	{
        		e.printStackTrace();
        	}
        }
        
        // ���ؽ��
        if (jsonArray != null)
        {
        	System.out.println(jsonArray.toString());
        	writer.write(jsonArray.toString());
        }
    }
    
    /**
     * <������Ӫ��ƽ̨���������� ɽ��>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
    public void feedbackByStatus()
    {
		//�ַ���ʽ
		this.getResponse().setContentType("text/xml;charset=GBK");
		
		PrintWriter writer = null;
		
		try 
		{
			writer = this.getResponse().getWriter();
			
	    	String orderStatus = (SSReturnCode.SUCCESS == status ? ORDERED : FAILED);
	    	
	    	ISSSInvokeUtil.feedbackInvoke(servnumber, offerMenu, orderStatus);
			
			writer.write("1");
		} 
		catch (Exception e) 
		{
			logger.error("��ISSSƽ̨����������ʧ�ܣ�", e);
			writer.write("0");
		}
		
		writer.flush();
    }
    
    /**
     * <����Ӫ��ƽ̨����һ��>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-2-11 17:00:47 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
     */
    public void consider()
    {
		try 
		{
			//�Ƽ���Ϣ
			OfferInfoVO offer = new OfferInfoVO();
			
			//����һ��
			offer.setStatus("toLeaded");
			
			//ս�۱���
			offer.setTreatment_id(treatment_id);
			
			//����һ�£�offerid�̶�Ϊ1
			offer.setOfferId(offerId);
	    	
	    	ISSSInvokeUtil.doFeedbackInvoke(servnumber, offer);
		} 
		catch (Exception e) 
		{
			logger.error("����һ�°�����ʧ�ܣ�", e);
		}
    }


	public String getOfferMenu() {
		return offerMenu;
	}


	public void setOfferMenu(String offerMenu) {
		this.offerMenu = offerMenu;
	}


	public String getOfferId() {
		return offerId;
	}


	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}


	public String getTreatment_id() {
		return treatment_id;
	}


	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	}


	public String getServnumber() {
		return servnumber;
	}


	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
}
