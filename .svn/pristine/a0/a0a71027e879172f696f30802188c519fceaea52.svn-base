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
 * 宽带预约 <功能详细描述>
 * 
 * @author gwx223032
 * @version [版本号, 2015-04-30]
 * @see [相关类/方法]--
 * @since [产品/模块版本]
 */
public class BroadBandAppointAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(BroadBandAppointAction.class);
    
    private transient IBroadBandAppointService broadBandAppointService;
    
    /**
     * 区县列表 (查分地市)
     */
    private List<DictItemVO> areaList;
    
    /**
     * 当前选择装机地址
     */
    private String currArea;
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 当前菜单
     */
    private String curMenuId;
    
    // add begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
    /**
     * 基本资费产品列表
     */
    private List<DictItemPO> appontProdList;
    
    /**
     * 当前选择资费产品名称
     */
    private String currProd;
    
    /**
     * 身份证号 IDcard
     */
    private String cardIdNum;
    
    /**
     * 预约安装时间
     */
    private String installDate;
    
    /**
     * 意向带宽
     */
    private String bandNum;
    
    // add end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
    
    /**
     * 宽带预约2.实名制验证初始化 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String initRealName()
    {
        try
        {
            // 验证是否是实名制用户
            broadBandAppointService.isRealName(curMenuId);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            errormessage = e.getMessage();
            return ERROR;
        }
        
        // 实名制用户进入选择装机地址界面
        return qryArea();
    }
    
    /**
     * 宽带预约3：装机地址选择 <功能详细描述>
     * 
     * @return String
     * @see [类、类#方法、类#成员] Create Author:<gWX223032> Time:<May 5, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public String qryArea()
    {
        try
        {
            // 获取装机地址
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
     * 初始化宽带介绍 <功能详细描述>
     * 
     * @return String
     * @see [类、类#方法、类#成员] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public String qryAppointMsg()
    {
        return "qryAppointMsg";
    }
    
    /**
     * 填写宽带预约客户信息页面
     * 
     * @return String
     * @remark create by fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
     */
    public String customerInfo()
    {
        
        return "customerInfo";
        
    }
    
    /**
     * 宽带预约4：预约 <功能详细描述>
     * 
     * @return String
     * @see [类、类#方法、类#成员] Create Author:<gWX223032> Time:<May 5, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public String broadBandAppoint()
    {
        try
        {
            // modify begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
            // 对日期进行再次校验
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            
            if (!format.parse(installDate).after(new Date()))
            {
                errormessage = "只能输入当前时间之后日期";
                logger.error("########" + errormessage);
                return ERROR;
            }
            broadBandAppointService.broadBandAppoint(currArea, curMenuId, installDate, cardIdNum, currProd, bandNum);
            // modify end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
            
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
     * 获取宽带介绍 <功能详细描述>
     * 
     * @return List<DictItemPO>
     * @see [类、类#方法、类#成员] Create Author:<gWX223032> Time:<May 6, 2015> Ver:<OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1 >
     */
    public List<DictItemPO> getAppointMsg()
    {
        return getDictItemByGrp("broadbandReserveGroup");
    }
    
    /**
     * 初始化选择基本产品页面 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_【BOSS常规需求】宽带业务预约营销项目需求（熊鹰飞）_需求分析说明书
     */
    public String selectBandProd()
    {
        // broadBandAppointProd 字典表中 基本资费产品》》groupid
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
