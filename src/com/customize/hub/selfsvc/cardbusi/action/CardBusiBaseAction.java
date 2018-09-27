/*
 * �� �� ��:  CardBusiBaseAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  �쿨Action����
 * �� �� ��:  zKF69263
 * �޸�ʱ��:  2014-10-24
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.cardbusi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.customize.hub.selfsvc.bean.CardInstallBean;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.IdCardPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.cardbusi.service.CardBusiService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �쿨Action����
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-10-24]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CardBusiBaseAction extends BaseAction
{
    /**
     * ע������
     */
    private static final long serialVersionUID = 2091574547747628710L;
    
    /** ---------------- ��ҳ ---------------------
     * 
     * ҳ��
     */
    protected int pageCount = 0;
    
    /** 
     * ÿҳ��ʾ����
     */
    protected int pageSize = 9;
    
    /** 
     * ��ǰҳ
     */
    protected int page = 1;
    
    /** ---------------- �˵���� -------------------------
     * 
     * ��ǰ�˵�
     */
    protected String curMenuId = "";
    
    /**
     * sim����Ϣ��
     */
    protected transient SimInfoPO simInfoPO;
    
    /** 
     * ���֤��Ϣ
     */
    protected transient IdCardPO idCardPO;
    
    /**
     * ���߿���
     */
    protected transient CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();
    
    /** 
     * BEAN
     */
    protected transient CardInstallBean cardInstallBean;
    
    /** 
     * SERVICE
     */
    protected transient CardBusiService cardBusiService;
    
    /**---------------- ������ϸ -------------------------
     * 
     * ���ȫ���ɷ���Ϣ
     */
    protected List<FeeConfirmPO> feeList;
    
    /** 
     * Ӧ�շ���
     */
    protected String recFee;
    
    /**
     * �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ
     */
    protected String canNotPrint;
    
    /** 
     * ������Ϣ
     */
    protected String errormessage;
    
    /**
     * �ն˻��ֽ�ʶ������������֧����ʶ:0��������,1�������,����:11�������豸������
     */
    protected String payTypeFlag;
    
    /**
     * ������ӡ��Ϣ
     */
    protected String printcontext;
    
    /**
     * �Ƿ񽫿��ƶ��������� 1������ 0�������� Ĭ�ϲ�����
     */
    protected String callBackCard;
    
    /**
     * ����Ӫ���Ƽ���ʶ
     */
    protected String recommendActivityFlag;
    
    /** 
     * ȡ���ն˻�֧�ֿհ׿���д������ʾ��Ϣ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getBlankCardTypeAlertMsg()
    {
        // ȡ���ն˻��հ׿�д�����ͽ�����ʾ
        DictItemPO dictItemPO = getCardBusiService().qryTermBlankCardType(getTerminalInfoPO().getTermid());
        
        if (null != dictItemPO)
        {
            return dictItemPO.getDescription();
        }
        
        // ����ն˻�û������֧�ֿհ׿�����ʱ����ʾĬ����Ϣ
        return (String)PublicCache.getInstance().getCachedData("SH_BLANKCARDTYPE_ALERTMSG");
    }
    
    /** 
     * ȡ�÷�ҳ����
     * 
     * @param <E>
     * @param resultList
     * @return List<E>
     * @see [�ࡢ��#��������#��Ա]
     */
    protected <E> List<E> getPageList(List<E> resultList, int perPageSize)
    {
        int resultSize = 0;
        int startSeq = 0;
        int endSeq = 0;
        pageSize = perPageSize;
        
        // ��ȡ��ҳ��
        if (!resultList.isEmpty())
        {
            resultSize = resultList.size();
            
            pageCount = resultSize / pageSize;
            
            if (resultSize % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        
        // ������ʼ�ͽ���λ��
        startSeq = (page - 1) * pageSize;
        endSeq = (page >= pageCount) ? resultSize : page * pageSize;
        
        // ��¼������
        this.getRequest().setAttribute("recordCount", resultList.size());
        
        // ȡ�÷�ҳ����
        return resultList.subList(startSeq, endSeq);
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

    /**
     * ��ȡ���ýɷѷ�ʽ
     * @param groupID �ն���
     * @return
     */
    protected List getPayType(String groupID)
    {
        List<MenuInfoPO> menus = null;
        
        if (groupID != null && !"".equals(groupID))
        {
            menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
        }
        
        // ��ȡ��ѡ�ɷѷ�ʽ
        List usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");

        return usableTypes;
    }
    /**
     * @return ���� pageCount
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * @param ��pageCount���и�ֵ
     */
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    /**
     * @return ���� pageSize
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * @param ��pageSize���и�ֵ
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * @return ���� page
     */
    public int getPage()
    {
        return page;
    }

    /**
     * @param ��page���и�ֵ
     */
    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * @return ���� idCardPO
     */
    public IdCardPO getIdCardPO()
    {
        return idCardPO;
    }

    /**
     * @param ��idCardPO���и�ֵ
     */
    public void setIdCardPO(IdCardPO idCardPO)
    {
        this.idCardPO = idCardPO;
    }

    /**
     * @return ���� cardBusiLogPO
     */
    public CardBusiLogPO getCardBusiLogPO()
    {
        return cardBusiLogPO;
    }

    /**
     * @param ��cardBusiLogPO���и�ֵ
     */
    public void setCardBusiLogPO(CardBusiLogPO cardBusiLogPO)
    {
        this.cardBusiLogPO = cardBusiLogPO;
    }

    /**
     * @return ���� cardInstallBean
     */
    public CardInstallBean getCardInstallBean()
    {
        return cardInstallBean;
    }

    /**
     * @param ��cardInstallBean���и�ֵ
     */
    public void setCardInstallBean(CardInstallBean cardInstallBean)
    {
        this.cardInstallBean = cardInstallBean;
    }

    /**
     * @return ���� cardBusiService
     */
    public CardBusiService getCardBusiService()
    {
        return cardBusiService;
    }

    /**
     * @param ��cardBusiService���и�ֵ
     */
    public void setCardBusiService(CardBusiService cardBusiService)
    {
        this.cardBusiService = cardBusiService;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public SimInfoPO getSimInfoPO() 
	{
		return simInfoPO;
	}

	public void setSimInfoPO(SimInfoPO simInfoPO) 
	{
		this.simInfoPO = simInfoPO;
	}

	public List<FeeConfirmPO> getFeeList() 
	{
		return feeList;
	}

	public void setFeeList(List<FeeConfirmPO> feeList) 
	{
		this.feeList = feeList;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getRecFee() 
	{
		return recFee;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setRecFee(String recFee) 
	{
		this.recFee = recFee;
	}

	/**
	 * @return ���� canNotPrint
	 */
	public String getCanNotPrint() 
	{
		return canNotPrint;
	}

	/**
	 * @param ��canNotPrint���и�ֵ
	 */
	public void setCanNotPrint(String canNotPrint) 
	{
		this.canNotPrint = canNotPrint;
	}

	/**
	 * @return ���� errormessage
	 */
	public String getErrormessage() {
		return errormessage;
	}

	/**
	 * @param ��errormessage���и�ֵ
	 */
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

    /**
     * @return ���� payTypeFlag
     */
    public String getPayTypeFlag()
    {
        return payTypeFlag;
    }

    /**
     * @param ��payTypeFlag���и�ֵ
     */
    public void setPayTypeFlag(String payTypeFlag)
    {
        this.payTypeFlag = payTypeFlag;
    }

    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public String getCallBackCard()
    {
        return callBackCard;
    }

    public void setCallBackCard(String callBackCard)
    {
        this.callBackCard = callBackCard;
    }

    public String getRecommendActivityFlag()
    {
        return recommendActivityFlag;
    }

    public void setRecommendActivityFlag(String recommendActivityFlag)
    {
        this.recommendActivityFlag = recommendActivityFlag;
    }
}
