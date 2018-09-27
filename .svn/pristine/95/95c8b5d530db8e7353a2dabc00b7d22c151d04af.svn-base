/*
 * 文 件 名:  RecommendActAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright 2015-2018,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  hWX5316476
 * 修改时间:  Jan 29, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.recommendActivity.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.activitiesrec.model.ActivityVO;
import com.customize.hub.selfsvc.activitiesrec.service.ActivitiesRecHubService;
import com.customize.hub.selfsvc.bean.ActivitiesRecBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * <新营销推荐活动>
 * <功能详细描述>
 * 
 * @author  hWX5316476
 * @version  [版本号, Jan 29, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RecommendActivityAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(RecommendActivityAction.class);
    
    /**
     * 当前菜单
     */
    private String curMenuId;
    
    /**
     * 配置的所有档次
     */
    private List<ActivityVO> dangciAllList = new ArrayList<ActivityVO>();
    
    /**
     * 页面缴费标志，只适用于充值缴费跳转
     * 
     */
    private String feeChargeFlag = "";
    
    /**
     * 活动编码
     */
    private String activityId = "";
    
    /**
     * 活动名称
     */
    private String activityName = "";
    
    /**
     * 活动组ID
     */
    private String groupId = "";
    
    /**
     * 活动组名称
     */
    private String groupName = "";
    
    /**
     * 档次编码
     */
    private String dangciId = "";
    
    /**
     * 档次名称
     */
    private String dangciName = "";
    
    /**
     * 档次描述信息
     */
    private String dangciDesc = "";
    
    /**
     * 自助终端定义的受理金额
     */
    private String prepayFee;
    
    /**
     * 手机号
     */
    private String servnumber;
    
    /**
     * 新主动营销推荐标识
     */
    private String recommendActivityFlag = "1";
    
    /**
     * 促销活动_所有记录
     */
    private List<ActivityVO> activityAllList = new ArrayList<ActivityVO>();
    
    /**
     * 接口操作bean
     */
    private transient ActivitiesRecBean activitiesRecBean;

    /**
     * service
     */
    private transient ActivitiesRecHubService activitiesRecHubService;
    
    /**
     * <获取可推荐的营销活动列表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryRecommendActList()
    {
        logger.debug("qryRecommendActList start");
        
        // 用户信息
        NserCustomerSimp customerSimp = getCustomerSimp();
    
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getTerminalInfoPO();
    
        // 暂时认为用户归属地与终端机归属地市一致
        String regionByTem = termInfo.getRegion();
        String regionByCustomer = termInfo.getRegion();
    
        // 获取用户手机号
        if(null != customerSimp && StringUtils.isNotEmpty(customerSimp.getServNumber()))
        {
            servnumber = customerSimp.getServNumber();
        }
        else
        {
            customerSimp = new NserCustomerSimp();
        }
    
        // 没有手机号则不进行推荐
        if(StringUtils.isEmpty(servnumber))
        {
            return "continueHandle";
        }
  
        if (StringUtils.isNotEmpty(customerSimp.getSmallregion()))
        {
            regionByTem = getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion());
            regionByCustomer = customerSimp.getSmallregion();
        }
        else if(StringUtils.isNotEmpty(customerSimp.getRegionID()))
        {
            regionByCustomer = customerSimp.getRegionID();
        }
        
        // 手机号码不在当前地市，不能办理活动,则不进行推荐
        if (!regionByTem.equals(regionByCustomer))
        {
            this.createRecLog(Constants.BUSITYPE_QRYRECACTLIST, "0", "0", "1", "手机号码不在当前地市，不能办理活动,则不进行推荐");
            return "continueHandle";
        }
        
        ActivityVO vo = new ActivityVO();
        
        try
        {
            // 查询已办理档次
            vo = qrySelfDangci(termInfo,customerSimp);
        }
        catch (Exception e)
        {
            this.createRecLog(Constants.BUSITYPE_QRYRECACTLIST, "0", "0", "1", e.getMessage());
            return "continueHandle";
        }
        
        // 查询活动列表
        activityAllList = activitiesRecHubService.getActivitieGroups(vo);
        
        // 当前没有可办理的活动，则不进行推荐
        if (activityAllList == null || activityAllList.size() == 0 )
        {
            this.createRecLog(Constants.BUSITYPE_QRYRECACTLIST, "0", "0", "1", "当前没有可办理的活动，不进行推荐");
            return "continueHandle";
        }
        
        // 参数对象
        vo = new ActivityVO();
        vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        
        // 查询数据
        dangciAllList = activitiesRecHubService.getDangciByGroupId(vo);
        
        for(ActivityVO activityVO:dangciAllList)
        {
            activityVO.setPrepayFee(Integer.parseInt(activityVO.getPrepayFee())/100+"");
            activityVO.setPresentValue(Integer.parseInt(activityVO.getPresentValue())/100+"");
        }
        
        // 活动列表加入档次
        List<ActivityVO> activityAllListByTmp = new ArrayList<ActivityVO>();
        ActivityVO activity = null;
        for (ActivityVO activityVO:activityAllList)
        {
            for(ActivityVO dangciVO:dangciAllList)
            {
                if (activityVO.getGroupId().equals(dangciVO.getGroupId()))
                {
                    activity = new ActivityVO();
                    activity.setGroupId(activityVO.getGroupId());
                    activity.setGroupName(activityVO.getGroupName());
                    activity.setActivityId(dangciVO.getActivityId());
                    activity.setDangciId(dangciVO.getDangciId());
                    activity.setDangciName(dangciVO.getDangciName());
                    activity.setRegion(dangciVO.getRegion());
                    activity.setPrepayFee(dangciVO.getPrepayFee());
                    activity.setStatus(dangciVO.getStatus());
                    activity.setPresentValue(dangciVO.getPresentValue());
                    activity.setStatusDate(dangciVO.getStatusDate());
                    
                    // 添加到活动列表
                    activityAllListByTmp.add(activity);
                }
            }
        }
        
        activityAllList = activityAllListByTmp;
        
        this.createRecLog(Constants.BUSITYPE_QRYRECACTLIST, "0", "0", "0", "新营销推荐活动:查询可办理的活动档次列表成功!");
        
        logger.debug("qryRecommendActList end");
        
        // 返回
        return "qryRecommendActList";
    }
    
    /**
     * <查询用户已存在的档次>
     * <功能详细描述>
     * @param termInfo
     * @param customerSimp
     * @param vo
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public ActivityVO qrySelfDangci(TerminalInfoPO termInfo,NserCustomerSimp customerSimp)throws Exception
    {
        // 查询用户已存在的档次
        Map mapResult = this.activitiesRecBean.qrySubsDangcisList(termInfo, customerSimp,curMenuId,servnumber);
        
        // 组装已存在的档次编码列表
        String dangciIds = "";
        StringBuffer sbuf = new StringBuffer(dangciIds);
        
        if (mapResult == null)
        {
            throw new Exception("查询用户已存在的档次异常！");
        }
        else if(mapResult.size() == 1)
        {
            throw new Exception((String)mapResult.get("returnMsg"));
        }
          
        CRSet crset = (CRSet) mapResult.get("returnObj");
        if (crset != null)
        {
            for (int i=0;i<crset.GetRowCount();i++)
            {
                if ((i+1) == crset.GetRowCount())
                {
                    sbuf.append("'").append(crset.GetValue(i, 0)).append("'");
                }
                else
                {
                    sbuf.append("'").append(crset.GetValue(i, 0)).append("'").append(",");
                }
            }
            dangciIds = sbuf.toString();
        }
        
        if ("".equals(dangciIds))
        {
            dangciIds = "''";
        }
        
        ActivityVO vo = new ActivityVO();
        
        // 设置地区
        if (StringUtils.isNotEmpty(termInfo.getRegion()))
        {
            vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        }
        
        // 用户已存在的档次
        vo.setDangciIds(dangciIds);
        
        this.createRecLog(Constants.BUSITYPE_QRYRECACTLIST, "0", "0", "0", "新营销推荐活动:查询用户已存在的档次成功!");
        
        return vo;
    }
    
    /**
     * <进入密码校验页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String chkServPwdPage()
    {
        return "chkServPwdPage";
    }
    
    /**
     * <进入短信验证码校验页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String chkRamdomPwdPage()
    {
        return "chkRamdomPwdPage";
    }
    
    /**
     * <暂不办理，继续原有业务>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String continueHandle()
    {
        return "continueHandle";
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public ActivitiesRecBean getActivitiesRecBean()
    {
        return activitiesRecBean;
    }

    public void setActivitiesRecBean(ActivitiesRecBean activitiesRecBean)
    {
        this.activitiesRecBean = activitiesRecBean;
    }

    public List<ActivityVO> getActivityAllList()
    {
        return activityAllList;
    }

    public void setActivityAllList(List<ActivityVO> activityAllList)
    {
        this.activityAllList = activityAllList;
    }

    public ActivitiesRecHubService getActivitiesRecHubService()
    {
        return activitiesRecHubService;
    }

    public void setActivitiesRecHubService(ActivitiesRecHubService activitiesRecHubService)
    {
        this.activitiesRecHubService = activitiesRecHubService;
    }

    public List<ActivityVO> getDangciAllList()
    {
        return dangciAllList;
    }

    public void setDangciAllList(List<ActivityVO> dangciAllList)
    {
        this.dangciAllList = dangciAllList;
    }

    public String getFeeChargeFlag()
    {
        return feeChargeFlag;
    }

    public void setFeeChargeFlag(String feeChargeFlag)
    {
        this.feeChargeFlag = feeChargeFlag;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getDangciId()
    {
        return dangciId;
    }

    public void setDangciId(String dangciId)
    {
        this.dangciId = dangciId;
    }

    public String getDangciName()
    {
        return dangciName;
    }

    public void setDangciName(String dangciName)
    {
        this.dangciName = dangciName;
    }

    public String getDangciDesc()
    {
        return dangciDesc;
    }

    public void setDangciDesc(String dangciDesc)
    {
        this.dangciDesc = dangciDesc;
    }

    public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getServnumber()
    {
        return servnumber;
    }

    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
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
