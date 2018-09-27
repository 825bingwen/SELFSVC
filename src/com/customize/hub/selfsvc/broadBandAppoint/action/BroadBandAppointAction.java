package com.customize.hub.selfsvc.broadBandAppoint.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.broadBandAppoint.service.IBroadBandAppointService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 
 * ���ԤԼ <������ϸ����>
 * 
 * @author gwx223032
 * @version [�汾��, 2015-04-30]
 * @see [�����/����]--
 * @since [��Ʒ/ģ��汾]
 */
public class BroadBandAppointAction extends BaseAction
{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(BroadBandAppointAction.class);
    
    private transient IBroadBandAppointService broadBandAppointService;
    
    /**
     * �����б� (��ֵ���)
     */
    private List<DictItemVO> areaList;
    
    /**
     * ��ǰѡ��װ����ַ
     */
    private String currArea;
    
    /**
     * ������Ϣ
     */
    private String errormessage;
    
    /**
     * ��ǰ�˵�
     */
    private String curMenuId;
    
    // add begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
    /**
     * �����ʷѲ�Ʒ�б�
     */
    private List<DictItemPO> appontProdList;
    
    /**
     * ��ǰѡ���ʷѲ�Ʒ����
     */
    private String currProd;
    
    /**
     * ���֤�� IDcard
     */
    private String cardIdNum;
    
    /**
     * ԤԼ��װʱ��
     */
    private String installDate;
    
    /**
     * �������
     */
    private String bandNum;
    
    // add end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
    
    /**
     * ���ԤԼ2.ʵ������֤��ʼ�� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String initRealName()
    {
        try
        {
            // ��֤�Ƿ���ʵ�����û�
            broadBandAppointService.isRealName(curMenuId);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            errormessage = e.getMessage();
            return ERROR;
        }
        
        // ʵ�����û�����ѡ��װ����ַ����
        return qryArea();
    }
    
    /**
     * ���ԤԼ3��װ����ַѡ�� <������ϸ����>
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա] Create Author:<gWX223032> Time:<May 5, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    public String qryArea()
    {
        try
        {
            // ��ȡװ����ַ
            areaList = broadBandAppointService.qryAreaList(curMenuId, getDictItemByGrp(Constants.SMALLREGION));
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            errormessage = e.getMessage();
            return ERROR;
        }
        
        return "qryArea";
    }
    
    /**
     * ��ʼ��������� <������ϸ����>
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    public String qryAppointMsg()
    {
        return "qryAppointMsg";
    }
    
    /**
     * ��д���ԤԼ�ͻ���Ϣҳ��
     * 
     * @return String
     * @remark create by fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
     */
    public String customerInfo()
    {
        
        return "customerInfo";
        
    }
    
    /**
     * ���ԤԼ4��ԤԼ <������ϸ����>
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա] Create Author:<gWX223032> Time:<May 5, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    public String broadBandAppoint()
    {
        try
        {
            // modify begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
            // �����ڽ����ٴ�У��
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            
            if (!format.parse(installDate).after(new Date()))
            {
                errormessage = "ֻ�����뵱ǰʱ��֮������";
                logger.error("########" + errormessage);
                return ERROR;
            }
            broadBandAppointService.broadBandAppoint(currArea, curMenuId, installDate, cardIdNum, currProd, bandNum);
            // modify end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            errormessage = e.getMessage();
            return ERROR;
        }
        
        return SUCCESS;
    }
    
    /**
     * ��ȡ������� <������ϸ����>
     * 
     * @return List<DictItemPO>
     * @see [�ࡢ��#��������#��Ա] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    public List<DictItemPO> getAppointMsg()
    {
        return getDictItemByGrp("broadbandReserveGroup");
    }
    
    /**
     * ��ʼ��ѡ�������Ʒҳ�� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
     */
    public String selectBandProd()
    {
        // broadBandAppointProd �ֵ���� �����ʷѲ�Ʒ����groupid
        appontProdList = this.getDictItemByGrp("broadBandAppointProd");
        return "selectBandProd";
    }
    
    public String getCurrArea()
    {
        return currArea;
    }
    
    public void setCurrArea(String currArea)
    {
        this.currArea = currArea;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public List<DictItemVO> getAreaList()
    {
        return areaList;
    }
    
    public void setAreaList(List<DictItemVO> areaList)
    {
        this.areaList = areaList;
    }
    
    public IBroadBandAppointService getBroadBandAppointService()
    {
        return broadBandAppointService;
    }
    
    public void setBroadBandAppointService(IBroadBandAppointService broadBandAppointService)
    {
        this.broadBandAppointService = broadBandAppointService;
    }
    
    public String getCurMenuId()
    {
        return curMenuId;
    }
    
    public void setCurMenuId(String curMenuId)
    {
        this.curMenuId = curMenuId;
    }
    
    public String getCurrProd()
    {
        return currProd;
    }
    
    public void setCurrProd(String currProd)
    {
        this.currProd = currProd;
    }
    
    public List<DictItemPO> getAppontProdList()
    {
        return appontProdList;
    }
    
    public void setAppontProdList(List<DictItemPO> appontProdList)
    {
        this.appontProdList = appontProdList;
    }
    
    public String getInstallDate()
    {
        return installDate;
    }
    
    public void setInstallDate(String installDate)
    {
        this.installDate = installDate;
    }
    
    public String getCardIdNum()
    {
        return cardIdNum;
    }
    
    public void setCardIdNum(String cardIdNum)
    {
        this.cardIdNum = cardIdNum;
    }
    
    public String getBandNum()
    {
        return bandNum;
    }
    
    public void setBandNum(String bandNum)
    {
        this.bandNum = bandNum;
    }
    
}
