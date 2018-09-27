package com.customize.hub.selfsvc.recommendProduct.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.customize.hub.selfsvc.bean.RecommendProductBean;
import com.customize.hub.selfsvc.recommendProduct.model.FeedBackDefPO;
import com.customize.hub.selfsvc.recommendProduct.model.RecommendProductPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ����Ӫ���Ƽ��
 * @author cKF76106
 * @version [�汾��, Aug 21, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class RecommendProductAction extends BaseAction
{
    private RecommendProductBean recommendProductBean;
    
    /**
     * ��ǰ�˵�
     */
    private String curMenuId = "";
    
    /**
     * ������Ϣ
     */
    private String errormessage = "";
    
    /**
     * �ɹ���Ϣ
     */
    private String successMessage = "";
    
    /**
     * �Ƽ���Ʒ�б�
     */
    private List<RecommendProductPO> recmdProductList = new ArrayList<RecommendProductPO>();
    
    /**
     * �û��ֻ�����
     */
    private String servnumber = "";
    
    /**
     * ҵ���Ƽ�Ψһ��ˮ��
     */
    private String commendOID = "";
    
    /**
     * �û����к�
     */
    private String userSeq = "";
    
    /**
     * ҳ��ɷѱ�־
     */
    private String feeChargeFlag = "";
    
    /**
     * ״̬
     */
    private String status = "";

    // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
    /**
     * �Ƽ���ƷΨһ��ˮ�ż���
     */
    private String userSeqs = "";
    // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
    
    /**
     * Ӫ�������
     */
    private String actId = "";
    
    /**
     * Ӫ������뼯��,�Զ��ŷָ�
     */
    private String actIds = "";
    
    /**
     * �Ƽ��¼����ͼ���,�Զ��ŷָ�
     */
    private String eventTypes = "";
    
    /**
     * ��������
     */
    private String moContent;
    
    /**
     * �Ƽ�����
     */
    private String recmdType;
    
    /**
     * ����������Ϣ�б�
     */
    private List<FeedBackDefPO> feedBackDefList;
    
    /**
     * ��ѯ�û����Ƽ���ҵ���б�
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public String getRecommendProductList()
    {
        
        String forward = "continue";
        HttpSession session = this.getRequest().getSession();
        
        // ��ǰ�ն˻�
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ǰ�ͻ�
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // ���ýӿڲ�ѯ�û����Ƽ���ҵ���б�
        Map mapResult = recommendProductBean.qryRecommendProductList(terminalInfoPO, touchOID, servnumber, curMenuId);
        
        recmdProductList.clear();
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            CRSet crset = (CRSet)(mapResult.get("returnObj"));
            
            if (crset != null)
            { 
                // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                // �Ƽ���Ʒ��Ψһ��ˮ�ż��� 
                StringBuilder userSeqsBuilder = new StringBuilder();
                // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                
                // �Ƽ������
                StringBuilder actIdsBuilder = new StringBuilder();
                
                // �¼����ͼ���
                StringBuilder eventTypesBuilder = new StringBuilder();
                
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    RecommendProductPO productRecPO = new RecommendProductPO();
                    productRecPO.setActID(crset.GetValue(i, 10));
                    productRecPO.setActName(crset.GetValue(i, 11));// Ӫ�������
                    productRecPO.setActDict(crset.GetValue(i, 2));// Ӫ������
                    productRecPO.setActContent(crset.GetValue(i, 13));// �����
                    productRecPO.setUserSeq(crset.GetValue(i, 6));// �û����к�
                    productRecPO.setCommendOID(crset.GetValue(i, 0));// ҵ���Ƽ�Ψһ��ˮ��
                    productRecPO.setCommendType(crset.GetValue(i, 16));// �Ƽ�����
                    productRecPO.setIsFeedBackDef(crset.GetValue(i, 17));// �Ƿ��лظ���Ϣ����
                    productRecPO.setEventType(crset.GetValue(i, 18));
                    
                    // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                    // ���Ƽ���Ʒ��Ψһ��ˮ�źϲ�  �����Զ��Ÿ���
                    userSeqsBuilder.append(productRecPO.getUserSeq());
                    userSeqsBuilder.append(",");
                    // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                    
                    // ���Ƽ���Ʒ��[Ӫ�������]�Զ�������
                    actIdsBuilder.append(productRecPO.getActID());
                    actIdsBuilder.append(",");
                    
                    // ���Ƽ���Ʒ��[�Ƽ��¼�����]�Զ�������
                    eventTypesBuilder.append(productRecPO.getEventType());
                    eventTypesBuilder.append(",");
                    
                    recmdProductList.add(productRecPO);
                }
                
                // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                // ���Ƽ���Ʒ��Ψһ��ˮ�ż���תΪString���Ͳ�ȥ�����һ������
                this.userSeqs = userSeqsBuilder.toString();
                this.userSeqs = this.userSeqs.substring(0, this.userSeqs.length()-1);
                // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
                
                // ���Ƽ���Ʒ��[Ӫ�������]ȥ�����һ������
                this.actIds = actIdsBuilder.toString();
                this.actIds = this.actIds.substring(0, this.actIds.length()-1);
                
                // ���Ƽ���Ʒ��[�Ƽ��¼�����]ȥ�����һ������
                this.eventTypes = eventTypesBuilder.toString();
                this.eventTypes = this.eventTypes.substring(0, this.eventTypes.length()-1);
            }
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "0", "Ӫ���Ƽ��:��ѯ�û����Ƽ���ҵ���б�ɹ�!");
        }
        // �û�û�п��Ƽ��Ļ������ԭҵ���������
        else if(null != mapResult && null == mapResult.get("returnObj") && "1".equals(mapResult.get("successFlag")))
        {
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "0", "Ӫ���Ƽ��:��ѯ�û����Ƽ���ҵ���б�ɹ�!");

            return  forward;
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000001", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);

            return forward;
            
        }
        else
        {
            String resultMsg = "��ѯ�û����Ƽ���ҵ���б�ʧ��";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000001", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYRECPRODLIST, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
            return forward;
        }
        
        // �Ƿ�������޸ĵ�ҳ��
        String recommendProductKey = (String)PublicCache.getInstance().getCachedData(Constants.RECOMMENDPRODUCTKEY);
        
        // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        // ���������ҳ��
        if("1".equals(recommendProductKey))
        {
            return "recommendProductListNew";
        }
        // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        
        return "recommendProductList";
    }
    
    /**
     * ��¼ҵ���Ƽ����
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public String recommendFeedback()
    {
        
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        // ��ǰ�ն˻�
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ǰ�ͻ�
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        
        // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        // �Ƿ�������޸ĵĽӿ�
        // String recommendProductKey = (String)PublicCache.getInstance().getCachedData(Constants.RECOMMENDPRODUCTKEY);
        // add end create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        // ��������´���
        /*if("1".equals(recommendProductKey))
        {
            this.userSeq = this.userSeqs;
        }*/
        // add begin create by zWX176560 OR_HUB_201303_200 �����ն�һ�廯Ӫ�������Ż�
        
        // �����Ƽ�ҵ���¼�ӿ�
        ReturnWrap rw = recommendProductBean.recommendFeedback(terminalInfoPO, touchOID, servnumber, 
        		curMenuId, this.getUserSeqs(), status, this.getActIds(), this.getEventTypes());
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACK, "0", "0", "0", "Ӫ���Ƽ��:��¼ҵ���Ƽ�����ɹ�!");
            
            forward = "continue";
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000002", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACK, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
        }
        
        return forward;
    }
    
    /**
     * �Ƽ�ҵ������
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public String recommendProduct()
    {
        
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        // ��ǰ�ն˻�
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ǰ�ͻ�
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String touchOID = "";
        if (null != customerSimp)
        {
            servnumber = customerSimp.getServNumber();
            touchOID = customerSimp.getContactId();
        }
        
        // �����Ƽ�ҵ������
        ReturnWrap rw = recommendProductBean.recommendProduct(terminalInfoPO,
            touchOID, servnumber, curMenuId, this.getUserSeq(), this.getActId(), this.getRecmdType());
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog(Constants.BUSITYPE_RECPROD, "0", "0", "0", "Ӫ���Ƽ��:�Ƽ�ҵ������ɹ�!");
            
            // ����ҵ���Ƽ�����ӿ�
            forward = doRecommentSuccess();
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000003", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECPROD, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
                        
        }
        
        return forward;
    }
    
    /**
     * ��ѯ�Ƽ����������б�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public String qryFeedBackDefList()
    {
        // ��ѯ����������Ϣ�б�
        ReturnWrap rw = recommendProductBean.qryFeedBackDefList(getTerminalInfoPO(),
            getCustomerSimp(), getCurMenuId(), getServnumber(), getActId());
        
        // ҳ�浼���趨
        String forward = "failed";
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet crset = (CRSet)rw.getReturnObject();
            
            if (null != crset && crset.GetRowCount() > 0)
            {
                feedBackDefList = new ArrayList<FeedBackDefPO>();
                
                // ѭ��crset�����������feedBackDefList��
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    FeedBackDefPO feedBackDefPO = new FeedBackDefPO();
                    feedBackDefPO.setRecmdName(crset.GetValue(i, 0)); // �Ƽ�����
                    feedBackDefPO.setNcode(crset.GetValue(i, 1));// �Ƽ�ncode
                    feedBackDefPO.setActInfo(crset.GetValue(i, 2));// �Ƽ����Ϣ
                    feedBackDefPO.setMoContent(crset.GetValue(i, 3));// �ظ�����
                    
                    feedBackDefList.add(feedBackDefPO);
                }
            }
            
            // ��¼��־
            this.createRecLog(Constants.BUSITYPE_QRYFEEDBACKDEFLIST, "0", "0", "0", "Ӫ���Ƽ��:��ѯ�û�����������Ϣ�б�ɹ�!");
            
            // �����ѯҳ��
            forward = "feedBackDefList";
        }
        else
        {
            // ������Ϣ
            String resultMsg = "��ѯ�û�����������Ϣ�б�ʧ��:" + rw.getReturnMsg();
            
            // ��¼��־
            this.createRecLog(Constants.BUSITYPE_QRYFEEDBACKDEFLIST, "0", "0", "1", resultMsg);
            
            // ���ô�����Ϣ
            setErrormessage(resultMsg);
        }
        
        return forward;
    }
    
    /** 
     * �û��������������
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public String doFeedBackDef()
    {
        // �����û��������������ӿ�
        ReturnWrap rw = recommendProductBean.doFeedBackDef(getTerminalInfoPO(),
            getCustomerSimp(), getCurMenuId(), getActId(), getMoContent(), getRecmdType());
        
        // ҳ�浼���趨
        String forward = "failed";

        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        {
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACKDEF, "0", "0", "0", "Ӫ���Ƽ��:�û��������������ɹ�!");
            
            // ����ҵ���Ƽ�����ӿ�
            forward = doRecommentSuccess();
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000002", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_RECFEEDBACKDEF, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
        }
        
        return forward;
    }

    /** 
     * ����ҵ���Ƽ�����ӿ�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    private String doRecommentSuccess()
    {
        // ҳ�浼���趨
        String forward = "failed";
        
        // ȡ�õ�ǰ��¼�û�
        NserCustomerSimp customerSimp = getCustomerSimp();
        
        // ���ø���ҵ���Ƽ�����ӿ�
        ReturnWrap rw = recommendProductBean.setRecommendSuccess(getTerminalInfoPO(),
            customerSimp.getContactId(), customerSimp.getServNumber(),
            getCurMenuId(), getCommendOID());
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        {
            this.createRecLog(Constants.BUSITYPE_SETRECSUCCESS, "0", "0", "0", "Ӫ���Ƽ��:����ҵ���Ƽ�����ɹ�!");
            
            setSuccessMessage("ҵ������ɹ�!");
            
            forward = "success";
        }
        else
        {
            String resultMsg = rw.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_23_000004", rw.getErrcode()+"", null);
            
            this.createRecLog(Constants.BUSITYPE_SETRECSUCCESS, "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
        }
        
        return forward;
    }
    
    /**
     * �Ƽ�ҵ������ɹ�����ʧ�ܣ���������ԭҵ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String contineRec()
    {
        return "continue";
    }
    
    /**
     * �Ƽ�ҵ������,����������֤���ɷ�֧�ֻ�Ƽ���
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String checkPassword()
    {
        return "checkPassword";
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getUserSeq()
    {
        return userSeq;
    }
    
    public void setUserSeq(String userSeq)
    {
        this.userSeq = userSeq;
    }
    
    public String getFeeChargeFlag()
    {
        return feeChargeFlag;
    }
    
    public void setFeeChargeFlag(String feeChargeFlag)
    {
        this.feeChargeFlag = feeChargeFlag;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public RecommendProductBean getRecommendProductBean()
    {
        return recommendProductBean;
    }
    
    public void setRecommendProductBean(RecommendProductBean recommendProductBean)
    {
        this.recommendProductBean = recommendProductBean;
    }
    
    public List<RecommendProductPO> getRecmdProductList()
    {
        return recmdProductList;
    }
    
    public void setRecmdProductList(List<RecommendProductPO> recmdProductList)
    {
        this.recmdProductList = recmdProductList;
    }

    public String getCommendOID()
    {
        return commendOID;
    }

    public void setCommendOID(String commendOID)
    {
        this.commendOID = commendOID;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getUserSeqs()
    {
        return userSeqs;
    }

    public void setUserSeqs(String userSeqs)
    {
        this.userSeqs = userSeqs;
    }

    /**
     * @return ���� feedBackDefList
     */
    public List<FeedBackDefPO> getFeedBackDefList()
    {
        return feedBackDefList;
    }

    /**
     * @param ��feedBackDefList���и�ֵ
     */
    public void setFeedBackDefList(List<FeedBackDefPO> feedBackDefList)
    {
        this.feedBackDefList = feedBackDefList;
    }

    /**
     * @return ���� actIds
     */
    public String getActIds()
    {
        return actIds;
    }

    /**
     * @param ��actIds���и�ֵ
     */
    public void setActIds(String actIds)
    {
        this.actIds = actIds;
    }

    /**
     * @return ���� eventTypes
     */
    public String getEventTypes()
    {
        return eventTypes;
    }

    /**
     * @param ��eventTypes���и�ֵ
     */
    public void setEventTypes(String eventTypes)
    {
        this.eventTypes = eventTypes;
    }

    /**
     * @return ���� actId
     */
    public String getActId()
    {
        return actId;
    }

    /**
     * @param ��actId���и�ֵ
     */
    public void setActId(String actId)
    {
        this.actId = actId;
    }

    /**
     * @return ���� moContent
     */
    public String getMoContent()
    {
        return moContent;
    }

    /**
     * @param ��moContent���и�ֵ
     */
    public void setMoContent(String moContent)
    {
        this.moContent = moContent;
    }

    /**
     * @return ���� recmdType
     */
    public String getRecmdType()
    {
        return recmdType;
    }

    /**
     * @param ��recmdType���и�ֵ
     */
    public void setRecmdType(String recmdType)
    {
        this.recmdType = recmdType;
    }
}
