package com.customize.hub.selfsvc.broadBandAppoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.hub.selfsvc.common.service.BaseServiceHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;

/**
 * ���ԤԼ <������ϸ����>
 * 
 * @author gWX223032
 * @version [�汾��, May 19, 2015]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class BroadBandAppointServiceImpl extends BaseServiceHubImpl implements IBroadBandAppointService
{
    
    /**
     * ʵ����֤ <������ϸ����>
     * 
     * @param telNum �ֻ�����
     * @return String true:��ʵ���� ������δʵ����
     * @see [�ࡢ��#��������#��Ա] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    @Override
    public void isRealName(String curMenuId)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, getTermInfo().getOperid(), getTermInfo().getTermid(), "",
                MsgHeaderPO.ROUTETYPE_TELNUM, getCustomer().getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().isRealName(msgHeader);
        
        // ���óɹ�
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            String msg = "";
            CTagSet tagSet = null;
            if (rw.getReturnObject() instanceof Vector)
            {
                // ������ {"isrealName":"0","outparamlist":[{"checkInfo":"���֤��Ϣ���һλУ����Ϣ����"}]}
                Vector v = (Vector)rw.getReturnObject();
                CRSet crset = (CRSet)v.get(1);
                msg = crset.GetValue(0, 0);
                tagSet = (CTagSet)v.get(0);
            }
            else
            {
                // ������ {"isrealName":"1","outparamlist":""}
                tagSet = (CTagSet)rw.getReturnObject();
            }
            
            // ���ز�ѯ���û���Ϣ��
            if (!"1".equals(tagSet.GetValue("isrealName")))
            {
                this.insertRecLog(Constants.BroadBandAppointbusiType,
                        "",
                        "",
                        Constants.RECSTATUS_FALID,
                        "�Բ���ʵ������֤У��ʧ�ܣ�" + msg);
                throw new ReceptionException("�Բ���ʵ������֤У��ʧ�ܣ�" + msg);
            }
        }
        else
        {
            this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "����ʵ������֤�ӿ�ʧ��!");
            throw new ReceptionException("���ýӿ�ʧ�ܣ�" + rw.getReturnMsg());
        }
    }
    
    /**
     * ��ȡװ����ַ
     * 
     * @param currMenuId ��ǰ�˵�id
     * @param smallRegionList ��ֵ����б�
     * @return List<DictItemVO> װ����ַ�б�
     */
    @Override
    public List<DictItemVO> qryAreaList(String currMenuId, List<DictItemPO> smallRegionList)
    {
        // ��ȡdict_item��descriptionֵ(��ֵ������⴦��)
        String regionDictDesc = qryRegionId(getTermInfo().getCityOrgid(), getTermInfo().getRegion(), smallRegionList);
        
        // ����dict_item��descriptionֵ��ȡ������Ϣ
        DictItemVO dictItemVO = getCurrRegionDictId(regionDictDesc, getDictItems(Constants.CITY420000, currMenuId));
        
        // ���ݵ��б�Ż�ȡ���Ӧ��������Ϣ�б�(����CRM�ӿ�)
        List<DictItemVO> areaList = getDictItems(Constants.DISTRICT + dictItemVO.getDictid(), currMenuId);
        
        // ������Ϣ�б�Ϊ��ʱ��ֱ����ӵ�ǰ������Ϣ���б�
        if (null == areaList || areaList.size() < 1)
        {
            areaList = new ArrayList<DictItemVO>();
            areaList.add(dictItemVO);
        }
        return areaList;
    }
    
    /**
     * ��ȡdict_item��descriptionֵ(��ֵ������⴦��)
     * 
     * @param cityOrgId �ն˻����ڵ�ַ����
     * @param region
     * @param smallregionList ��ֵ����б�
     * @return dict_item��descriptionֵ
     */
    @Override
    public String qryRegionId(String cityOrgId, String region, List<DictItemPO> smallregionList)
    {
        if (StringUtils.isNotBlank(cityOrgId))
        {
            for (DictItemPO po : smallregionList)
            {
                if (cityOrgId.equals(po.getDictid()))
                {
                    // ����С����region ��Ӧdict_item��description�ֶ�ֵ
                    return po.getDictid();
                }
            }
        }
        
        // δ�Ӳ�ֵ����б��в�ѯ������ʾ��728��ֵ��еģ�ֱ�ӷ���
        return region;
    }
    
    /**
     * ����dict_item��descriptionֵ ��ȡ��ǰ������Ϣ
     * 
     * @param descroption ����
     * @param regionInfoList �����б���CRM�ӿڷ��أ�
     * @return DictItemVO ��ǰ������ϢPO
     */
    @Override
    public DictItemVO getCurrRegionDictId(String description, List<DictItemVO> regionInfoList)
    {
        if (null != regionInfoList && regionInfoList.size() > 0)
        {
            for (DictItemVO po : regionInfoList)
            {
                if (description.equals(po.getDescription()))
                {
                    return po;
                }
            }
        }
        this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "δȡ��������Ϣ!");
        throw new ReceptionException("δȡ��������Ϣ!");
    }
    
    /**
     * ���ԤԼ�ύ
     * 
     * @param telNum ԤԼ�ֻ���
     * @param currArea ԤԼ��ַ
     * @param curMenuId ���ڲ˵�id
     * @param currProd ԤԼ��Ʒ
     * @param iDcard   ���֤��
     * @param installDate ԤԼ��װʱ��
     * @param band  ����
     * @param termInfo �ն���Ϣ
     * modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����   
     */
    @SuppressWarnings("unchecked")
    @Override
    public void  broadBandAppoint(String currArea,String curMenuId, String installDate, String cardIdNum, String currProd, String band)
    {
        // ���װ����ַΪ�գ���ֱ�Ӵ��ն˻���ǰ���ڵ���(����)
        if (StringUtils.isEmpty(currArea))
        {
            currArea = "δ֪����";
            List<RegionInfoPO> regionList = (List<RegionInfoPO>)PublicCache.getInstance()
                    .getCachedData(Constants.REGION_INFO);
            for (RegionInfoPO regionInfoPO : regionList)
            {
                if (getTermInfo().getRegion().equals(regionInfoPO.getRegion()))
                {
                    currArea = regionInfoPO.getRegionname();
                }
            }
        }
        
        //modify begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����   
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, getTermInfo().getOperid(), getTermInfo().getTermid(), "",
                MsgHeaderPO.ROUTETYPE_TELNUM, getCustomer().getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().broadBandAppoint(currArea,installDate, cardIdNum,currProd,band,msgHeader);
        //modify end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����   

        // ����ʧ��
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "���ԤԼʧ��");
            throw new ReceptionException("���ԤԼ�ӿڵ���ʧ�ܣ�" + rw.getReturnMsg());
        }
        this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_SUCCESS, "���ԤԼ�ɹ�");
    }

}
