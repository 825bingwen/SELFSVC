package com.customize.hub.selfsvc.broadBandAppoint.service;

import java.util.List;

import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


public interface IBroadBandAppointService
{
    /** 实名验证
     * <功能详细描述>
     * @param telNum 手机号码
     * @param curMenuId 菜单id
     * @param TerminalInfoPO termInfo 终端信息
     * @return void
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public void isRealName(String curMenuId);
    
    
    /** 拆分地市特殊处理：根据终端cityOrgId和region获取地市region(对应dict_item表description字段值)
     * <功能详细描述>
     * @param cityOrgId 终端cityOrgId
     * @param region 终端所在地市
     * @param smallregionList 拆分地市字典列表
     * @return String  返回region 对应dict_item表description字段值
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public String qryRegionId(String cityOrgId, String region,List<DictItemPO> smallregionList);
    
    
    /** 根据地市region(descroption)获取地市的dictId
     * <功能详细描述>
     * @param descroption region
     * @param regionInfoList 拆分地市列表
     * @return String dictId
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public DictItemVO getCurrRegionDictId(String descroption, List<DictItemVO> regionInfoList);
    
    /** 根据groupid获取装机地址
     * <功能详细描述>
     * @param curMenuId curMenuId
     * @return String List<DictItemVO> 拆分地市列表
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public List<DictItemVO> qryAreaList(String currMenuId,List<DictItemPO> smallRegionList);
    
    /** 宽带预约
     * <功能详细描述>
     * @param telNum 手机号码
     * @param currArea 预约地址
     * @param currProd 预约产品
     * @param iDcard   身份证号
     * @param installDate 预约安装时间
     * @param band  带宽
     * @param TerminalInfoPO termInfo 终端信息
     * @return void
     * @see [类、类#方法、类#成员]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     * modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书   
     */
    public void  broadBandAppoint(String currArea,String curMenuId, String installDate, String cardIdNum, String currProd, String band);  
}
