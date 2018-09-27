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
 * 宽带预约 <功能详细描述>
 * 
 * @author gWX223032
 * @version [版本号, May 19, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BroadBandAppointServiceImpl extends BaseServiceHubImpl implements IBroadBandAppointService
{
    
    /**
     * 实名验证 <功能详细描述>
     * 
     * @param telNum 手机号码
     * @return String true:已实名制 其他：未实名制
     * @see [类、类#方法、类#成员] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    @Override
    public void isRealName(String curMenuId)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, getTermInfo().getOperid(), getTermInfo().getTermid(), "",
                MsgHeaderPO.ROUTETYPE_TELNUM, getCustomer().getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().isRealName(msgHeader);
        
        // 调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            String msg = "";
            CTagSet tagSet = null;
            if (rw.getReturnObject() instanceof Vector)
            {
                // 解析： {"isrealName":"0","outparamlist":[{"checkInfo":"身份证信息最后一位校验信息不对"}]}
                Vector v = (Vector)rw.getReturnObject();
                CRSet crset = (CRSet)v.get(1);
                msg = crset.GetValue(0, 0);
                tagSet = (CTagSet)v.get(0);
            }
            else
            {
                // 解析： {"isrealName":"1","outparamlist":""}
                tagSet = (CTagSet)rw.getReturnObject();
            }
            
            // 返回查询的用户信息串
            if (!"1".equals(tagSet.GetValue("isrealName")))
            {
                this.insertRecLog(Constants.BroadBandAppointbusiType,
                        "",
                        "",
                        Constants.RECSTATUS_FALID,
                        "对不起实名制认证校验失败！" + msg);
                throw new ReceptionException("对不起实名制认证校验失败！" + msg);
            }
        }
        else
        {
            this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "调用实名制认证接口失败!");
            throw new ReceptionException("调用接口失败：" + rw.getReturnMsg());
        }
    }
    
    /**
     * 获取装机地址
     * 
     * @param currMenuId 当前菜单id
     * @param smallRegionList 拆分地市列表
     * @return List<DictItemVO> 装机地址列表
     */
    @Override
    public List<DictItemVO> qryAreaList(String currMenuId, List<DictItemPO> smallRegionList)
    {
        // 获取dict_item表description值(拆分地市特殊处理)
        String regionDictDesc = qryRegionId(getTermInfo().getCityOrgid(), getTermInfo().getRegion(), smallRegionList);
        
        // 根据dict_item表description值获取地市信息
        DictItemVO dictItemVO = getCurrRegionDictId(regionDictDesc, getDictItems(Constants.CITY420000, currMenuId));
        
        // 根据地市编号获取其对应的区县信息列表(调用CRM接口)
        List<DictItemVO> areaList = getDictItems(Constants.DISTRICT + dictItemVO.getDictid(), currMenuId);
        
        // 区县信息列表为空时，直接添加当前地市信息到列表
        if (null == areaList || areaList.size() < 1)
        {
            areaList = new ArrayList<DictItemVO>();
            areaList.add(dictItemVO);
        }
        return areaList;
    }
    
    /**
     * 获取dict_item表description值(拆分地市特殊处理)
     * 
     * @param cityOrgId 终端机所在地址编码
     * @param region
     * @param smallregionList 拆分地市列表
     * @return dict_item表description值
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
                    // 返回小地市region 对应dict_item表description字段值
                    return po.getDictid();
                }
            }
        }
        
        // 未从查分地市列表中查询到，表示非728拆分地市的，直接返回
        return region;
    }
    
    /**
     * 根据dict_item表description值 获取当前地市信息
     * 
     * @param descroption 描述
     * @param regionInfoList 地市列表（调CRM接口返回）
     * @return DictItemVO 当前地市信息PO
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
        this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "未取到地市信息!");
        throw new ReceptionException("未取到地市信息!");
    }
    
    /**
     * 宽带预约提交
     * 
     * @param telNum 预约手机号
     * @param currArea 预约地址
     * @param curMenuId 当期菜单id
     * @param currProd 预约产品
     * @param iDcard   身份证号
     * @param installDate 预约安装时间
     * @param band  带宽
     * @param termInfo 终端信息
     * modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   
     */
    @SuppressWarnings("unchecked")
    @Override
    public void  broadBandAppoint(String currArea,String curMenuId, String installDate, String cardIdNum, String currProd, String band)
    {
        // 如果装机地址为空，则直接传终端机当前所在地市(中文)
        if (StringUtils.isEmpty(currArea))
        {
            currArea = "未知地市";
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
        
        //modify begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, getTermInfo().getOperid(), getTermInfo().getTermid(), "",
                MsgHeaderPO.ROUTETYPE_TELNUM, getCustomer().getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().broadBandAppoint(currArea,installDate, cardIdNum,currProd,band,msgHeader);
        //modify end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   

        // 调用失败
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_FALID, "宽带预约失败");
            throw new ReceptionException("宽带预约接口调用失败：" + rw.getReturnMsg());
        }
        this.insertRecLog(Constants.BroadBandAppointbusiType, "", "", Constants.RECSTATUS_SUCCESS, "宽带预约成功");
    }

}
