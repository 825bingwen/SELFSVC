package com.customize.hub.selfsvc.activitiesrec.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.activitiesrec.model.ActivityVO;
import com.customize.hub.selfsvc.activitiesrec.model.AwardVO;
import com.customize.hub.selfsvc.activitiesrec.service.ActivitiesRecHubService;
import com.customize.hub.selfsvc.bean.ActivitiesRecBean;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;


/**
 * 
 * 促销活动受理
 * 
 * @author  yKF28472
 * @version  [版本号, Jan 31, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivitiesRecHubAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(ActivitiesRecHubAction.class);
    
    /**
     * service
     */
    private ActivitiesRecHubService activitiesRecHubService;
    
    /**
     * 促销活动_单页记录
     */
    private List<ActivityVO> activityList = new ArrayList<ActivityVO>();
    
    /**
     * 促销活动_所有记录
     */
    private List<ActivityVO> activityAllList = new ArrayList<ActivityVO>();
    
    /**
     * 配置的所有档次
     */
    private List<ActivityVO> dangciAllList = new ArrayList<ActivityVO>();
    
    /**
     * 奖品列表_单页记录
     */
    private List<AwardVO> awardList = new ArrayList<AwardVO>();
    
    /**
     * 奖品列表_所有记录
     */
    private List<AwardVO> awardAllList = new ArrayList<AwardVO>();
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
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
     * 缴费方式
     */
    private List usableTypes = null;
    
    /**
     * 手机号码
     */
    private String servnumber = "";
    
    /**
     * 错误信息
     */
    private String errormessage = "";
    
    /**
     * 赠品编号串
     */
    private String actreward = "";
    
    /**
     * 手机的IMEI号串
     */
    private String imeiid = "";
    
    /**
     * 赠品数量
     */
    private int quantity = 0;
    
    /**
     * 赠品总价
     */
    private int rewardAccount = 0;
    
    /**
     * 用户投入金额_分
     */
    //modify by sWX219697 2015-7-16 15:47:46 由totalfee改为totalFee，findbugs修改
    private String totalFee = "0";
    
    /**
     * 用户投入金额_元
     */
    private String totalfee_yuan = "0";
    
    /**
     * 终端流水
     */
    private String terminalSeq = "";
    
    /**
     * 交易流水
     */
    private String recoid = "";
    
    /**
     * 自助终端定义的受理金额
     */
    private String prepayFee;
    
    /**
     * 区域
     */
    private String region;
    
    /**
     * 终端ID
     */
    //modify by sWX219697 2015-7-16 11:28:57 由termId改为termid，findbugs修改
    private String termid;
    
    /**
     * 终端名称
     */
    private String termName;
    
    /**
     * 操作员ID
     */
    private String operId;
    
    /**
     * 地点
     */
    //modify by sWX219697 2015-7-16 11:28:57 由orgName改为orgname，findbugs修改
    private String orgname;
    
    /**
     * 受理是否成功 0:成功 1:失败
     */
    private String successBz;
    
    /**
     * 营销方案费用
     */
    private String yxfaFee;
    
    /**
     * 预存
     */
    private String ycFee;
    
    /**
     * 营销方案费用
     */
    private String yxfaFee_yuan;
    
    /**
     * 预存
     */
    private String ycFee_yuan;
    
    /**
     * 交费方式 1：银联卡  4：现金
     */
    private String payType;
    
    /**
     * 是否需要退卡 0:不需要 1:需要
     */
    private String needReturnCard;
    
    /**
     * 银联卡号
     */
    private String cardnumber;
    
    /**
     * 自助终端服务器生成流水号
     */
    private String chargeLogOID;
    
    /**
     * 商户编号（授卡方标识）
     */
    private String unionpayuser;
    
    /**
     * 终端编号
     */
    private String unionpaycode;
    
    /**
     * 交易类型
     */
    //modify by sWX219697 2015-7-18 10:53:54 由busitype改为busiType，findbugs修改
    private String busiType;
    
    /**
     * 终端批次号
     */
    private String batchnum;
    
    /**
     * 授权码（山东用）
     */
    private String authorizationcode;
    
    /**
     * 交易参考号（山东用）
     */
    private String businessreferencenum;
    
    /**
     * 银联实际扣款时间
     */
    private String unionpaytime;
    
    /**
     * 凭证号（山东用）
     */
    private String vouchernum;
    
    /**
     * 银联实际扣款金额，单位（分）
     */
    private String unionpayfee;
    
    /**
     * 异常类型  add:新增 update:修改
     */
    private String errorType;
    
    /**
     * 银联打印信息
     */
    private String printcontext;
    
    /**
     * POS返回码
     */
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    
    /**
     * 接口操作
     */
    private ActivitiesRecBean activitiesRecBean;
    
    private ReceptionBean receptionBean = null;    

    private String tip = "";

    /**
     * 查询促销活动组
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryActivities()
    {
        String forward = "success";
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // modify begion yKF28472 OR_huawei_201305_474
        /**
        String regionByTem = termInfo.getRegion();
        String regionByCustomer = customerSimp.getRegionID();
        **/
        String regionByTem = "";
        String regionByCustomer = "";
        if (customerSimp.getSmallregion() != null && !"".equals(customerSimp.getSmallregion()))
        {
            regionByTem = getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion());
            regionByCustomer = customerSimp.getSmallregion();
        }
        else
        {
            regionByTem = termInfo.getRegion();
            regionByCustomer = customerSimp.getRegionID();
        }
        if (!regionByTem.equals(regionByCustomer))
        {
            errormessage = "此手机号码不在当前地市，不能办理活动！";
            return "error";
        }
        // modify end yKF28472 OR_huawei_201305_474
        
        // 手机号码
        servnumber = customerSimp.getServNumber();
        
        // 查询用户已存在的档次
        Map mapResult = this.activitiesRecBean.qrySubsDangcisList(termInfo, customerSimp,curMenuId, customerSimp.getServNumber());
        
        // 组装已存在的档次编码列表
        String dangciIds = "";
        StringBuffer sbuf = new StringBuffer(dangciIds);
        if (mapResult != null && mapResult.size() == 2)
        {   
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
        }
        else if (mapResult != null && mapResult.size() == 1)
        {
            errormessage = (String)mapResult.get("returnMsg");
            return "error";
        }
        else
        {
            errormessage = "查询用户已存在的档次异常！";
            return "error";
        }
        if ("".equals(dangciIds))
        {
            dangciIds = "''";
        }
        
        // 参数对象
        ActivityVO vo = new ActivityVO();
        
        // 区域
        if (!"".equals(termInfo.getRegion()))
        {
            // modify begion yKF28472 OR_huawei_201305_474
            //vo.setRegion(termInfo.getRegion());	
            vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
            // modify end yKF28472 OR_huawei_201305_474
        }
        
        // 用户已存在的档次
        if (!"".equals(dangciIds))
        {
            vo.setDangciIds(dangciIds);
        }
        
        // 查询活动列表----------------------------------------------------
        activityAllList = activitiesRecHubService.getActivitieGroups(vo);
        
        if (activityAllList == null || activityAllList.size() == 0 )
        {
            errormessage = "当前没有可办理的活动！";
        	
            return "error";
        }
        
        // 查询档次列表----------------------------------------------------------
        
        // 参数对象
        vo = new ActivityVO();
        //vo.setRegion(termInfo.getRegion());
        // modify begion yKF28472 OR_huawei_201305_474
        //vo.setRegion(termInfo.getRegion());
        vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        // modify end yKF28472 OR_huawei_201305_474
        
        // 查询数据
        dangciAllList = activitiesRecHubService.getDangciByGroupId(vo);
        
        for(ActivityVO activityVO:dangciAllList)
        {
            activityVO.setPrepayFee(Integer.parseInt(activityVO.getPrepayFee())/100+"");
            activityVO.setPresentValue(Integer.parseInt(activityVO.getPresentValue())/100+"");
        }
        
        // 活动列表加入档次---------------------------------------------------------
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
                    // 档次
                    activity.setRegion(dangciVO.getRegion());
                    activity.setActivityId(dangciVO.getActivityId());
                    activity.setDangciId(dangciVO.getDangciId());
                    activity.setDangciName(dangciVO.getDangciName());
                    activity.setPrepayFee(dangciVO.getPrepayFee());
                    activity.setPresentValue(dangciVO.getPresentValue());
                    activity.setStatusDate(dangciVO.getStatusDate());
                    activity.setStatus(dangciVO.getStatus());
                    activityAllListByTmp.add(activity);
                }
            }
        }
        activityAllList = activityAllListByTmp;
        
        // ------------------------------------------------------------
        
        this.getRequest().setAttribute("recordCount", activityAllList.size());
        
        // 调用分页
        displayPage();
        
        // 返回
        return forward;
    }
    
    /**
     * 查询档次列表
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryDangCiList()
    {
        String forward = "success";
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 参数对象
        ActivityVO vo = new ActivityVO();
        vo.setGroupId(groupId);
        //vo.setRegion(termInfo.getRegion());
        // modify begion yKF28472 OR_huawei_201305_474
        //vo.setRegion(termInfo.getRegion());
        vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        // modify end yKF28472 OR_huawei_201305_474
        
        // 查询数据
        activityAllList = activitiesRecHubService.getDangciByGroupId(vo);
        
        for(ActivityVO activityVO:activityAllList)
        {
        	activityVO.setPrepayFee(Integer.parseInt(activityVO.getPrepayFee())/100+"");
        	activityVO.setPresentValue(Integer.parseInt(activityVO.getPresentValue())/100+"");
        }
        
        this.getRequest().setAttribute("recordCount", activityAllList.size());
        
        // 调用分页
        displayPage_danci();
        
        // 返回
        return forward;
    }
    
    /**
     * 查询档次描述
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryDangCiDesc()
    {
        String forward = "success";
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 查询档次描述
        /**
        Map mapResult = this.activitiesRecBean.queryDangciDesc(termInfo, customerSimp, curMenuId, dangciId);

        // 取返回信息
        if (mapResult != null && mapResult.size() > 0)
        {   
            CRSet crset = (CRSet) mapResult.get("returnObj");
            if (crset != null)
            {
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    // 档次名称
                    this.dangciName = crset.GetValue(i, 0);
                    
                    // 档次描述
                    this.dangciDesc  = crset.GetValue(i, 1);
                }
            }
            else
            {
                errormessage = (String)mapResult.get("returnMsg");
                // 测试时注掉
                return "error";
            }
        }
        else
        {
            errormessage = "查询档次描述异常！";
            return "error";
        }
        **/
        
        // 根据活动编码与档次编码查询
        ActivityVO vo = new ActivityVO();
        vo.setActivityId(activityId);
        vo.setDangciId(dangciId);
        List<ActivityVO> list = this.activitiesRecHubService.getDangciById(vo);
        
        // 档次名称
        this.dangciName = "";
        
        // 档次描述
        this.dangciDesc  = "";
        
        if (list != null && list.size() > 0)
        {
            vo = list.get(0);
            this.dangciName = vo.getDangciName();
            this.dangciDesc = vo.getActivityDesc();
        }
        
        
        
        // 调用接口查询奖品列表
        Map result = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        if (result != null && result.size() > 0)
        {   
            CRSet crset = (CRSet) result.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                if ( i+1 == crset.GetRowCount() )
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0);
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
                else
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // 手机imeiid号
                    imeiid = imeiid + "|";
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            errormessage = "查询赠品列表异常！";
            return "error";
        }
        
        // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        // 返回
        return forward;
    }
    
    /**
     * 查询档次下的赠品列表
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryPresentsList()
    {
        String forward = "success";

        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 调用接口查询奖品列表
        Map mapResult = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        AwardVO vo = null;
        if (mapResult != null && mapResult.size() > 0)
        {   
            CRSet crset = (CRSet) mapResult.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                vo = new AwardVO();
                vo.setRewardId(crset.GetValue(i, 0));// 奖品编码
                vo.setRewardName(crset.GetValue(i, 1));// 奖品名称
                vo.setRewardType(crset.GetValue(i, 2));// 奖品类型
                vo.setRewardTypeName(this.getDictName(crset.GetValue(i, 2),"RwdGiftType"));// 奖品类型名称
                vo.setRewardValue(Integer.parseInt(crset.GetValue(i, 3))/100+"");// 奖品价值
                vo.setScoreDealType(crset.GetValue(i, 4));// 积分扣减类型
                if ("0".equals(crset.GetValue(i, 4)))
                {
                    vo.setScoreDealTypeName("普通扣减");
                } 
                else if ("1".equals(crset.GetValue(i, 4)))
                {
                    vo.setScoreDealTypeName("扣完");
                }
                else
                {
                    vo.setScoreDealTypeName(crset.GetValue(i, 4));
                }
                
                vo.setUserScore(crset.GetValue(i, 5));// 扣减积分数额
                vo.setRewardNote(crset.GetValue(i, 6));// 奖品说明
                awardAllList.add(vo);// 档次编码
                
                if ( i+1 == crset.GetRowCount() )
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0);
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
                else
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // 手机imeiid号
                    imeiid = imeiid + "|";
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            errormessage = "查询赠品列表异常！";
            return "error";
        }

        this.getRequest().setAttribute("recordCount", awardAllList.size());
        
        // 分页
        displayPage_present();
        
        // 返回
        return forward;
    }
    
    /**
     * 协议描述页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String recDutyInfo()
    {
        String forward = "success";
        
        // 返回
        return forward;
    }
    
    /** 
     * 取得受理协议列表
     * 
     * @return List<DictItemPO>
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-2-9 OR_HUB_201501_167_HUB_关于自助终端菜单层级优化需求
     */
    public List<DictItemPO> getAgreementList()
    {
        return getDictItemByGrp("ActivityAgreement");
    }
    
    /**
     * 选择缴费类型页面（执行预处理）
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectType()
    {
        String forward = "success";
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 执行活动预处理
        String onlycheck = "1";// 1是预受理；0是受理
        String accesstype = "bsacAtsv";// 受理渠道
        String password = "";// 取货密码
        String totalfee = "";// 总金额 
        String paytype = "Cash";// 支付方式cash:现金  card:银联卡 
        quantity = 1;// 固定写死
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, totalfee, paytype);
        if (mapResult != null && mapResult.size() == 2)
        {   
            // 根据终端组自缓存中获取菜单列表
            String groupID = termInfo.getTermgrpid();
            
            List<MenuInfoPO> menus = null;
            
            if (groupID != null && !"".equals(groupID))
            {
                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
            }
            
            // 当页菜单列表
            usableTypes = CommonUtil.getChildrenMenuInfo(menus, "activitiesRec", "");
            
            // findbugs修改 2015-09-02 zKF66389
//            if (logger.isInfoEnabled())
//            {
//                logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
//            }
            // findbugs修改 2015-09-02 zKF66389
        }
        else if (mapResult != null && mapResult.size() == 1)
        {
            errormessage = (String)mapResult.get("returnMsg");
            return "error";
        }
        else
        {
            errormessage = "执行活动预处理失败！";
            return "error";
        }
        
        // 返回
        return forward;
    }
    
    /**
     * 转到投钞页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cashActivities()
    {
        String forward = "success";
        
        // 返回
        return forward;
    }
    
    /**
     * 交易受理并完成页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String finish()
    {
        String forward = "success";
        
        DecimalFormat dformat = new DecimalFormat("#.##"); 
        
        this.payType = "4";
        
        // 显示金额
        totalfee_yuan = "0".equals(totalFee) ? "0" : dformat.format(Double.parseDouble(totalFee)/100.00) + "" ;
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 手机号码
        servnumber = customerSimp.getServNumber();
        
        // 发起缴费请求之前首先记录投币日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        //--------缴费记录日志sh_charge_log(投币之后、业务受理之前执行记录)----------------------------------------------------------------
        
        // 生成投币日志
        String logOID = activitiesRecHubService.getChargeLogOID();
        
        // 组装数据
        selfCardPayLogVO.setChargeLogOID(logOID);// id
        selfCardPayLogVO.setRegion(termInfo.getRegion());// 区域
        selfCardPayLogVO.setTermID(termInfo.getTermid());// 终端ID
        selfCardPayLogVO.setOperID(termInfo.getOperid());// 操作员ID
        selfCardPayLogVO.setServnumber(servnumber);// 手机号
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// 现金投币日志
        selfCardPayLogVO.setFee(this.totalFee);// 用户投入金额
        
        // 终端流水(终端id+现金缴费流水 并取后20位)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);// 终端流水
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);// 交易时间
        selfCardPayLogVO.setAcceptType("");// 受理类型（空）
        selfCardPayLogVO.setServRegion(customerSimp.getRegionID());// 手机所属区域
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("缴费之前记录投币日志");
        selfCardPayLogVO.setDealnum(""); // boss缴费流水 缴费成功后返回
        selfCardPayLogVO.setRecType("activity");// 业务类型（phone：话费缴费 favourable：优惠缴费）
        
        // 执行新增记录
        activitiesRecHubService.addChargeLog(selfCardPayLogVO);
        
        //----------受理活动日志sh_log_priv-------------------------------------------------------------------------------------------------
        
        PrivLogVO privLogVO = new PrivLogVO();
        privLogVO.setChargeID(logOID); // 缴费的流水
        privLogVO.setRegion(termInfo.getRegion()); // 地区
        privLogVO.setServnumber(servnumber);
        privLogVO.setPrivId(this.activityId);// 活动编码
        privLogVO.setPrivNcode(this.dangciId);// 档次编码
        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        privLogVO.setChargeType("4"); // 现金缴费  缴费方式，1：银联卡；4：现金
        privLogVO.setPrivFee(String.valueOf(Integer.parseInt(this.prepayFee)*100));// 受理金额（）
        privLogVO.setToFee(this.totalFee);// 用户实缴金额（）
        privLogVO.setChargeFee("0");// 多余缴费金额（）
        privLogVO.setRecType("activity");//favourable -- 优惠办理；activity -- 促销活动。
        
        //----------执行业务受理---------------------------------------------------------------------------------------------------
        
        // 执行活动受理
        String onlycheck = "0";// 1是预受理；0是受理
        String accesstype = "bsacAtsv";// 受理渠道
        String password = "";// 取货密码
        String paytype = "Cash";// 支付方式cash:现金  card:银联卡 
        
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, totalFee, paytype);
        
        // 受理成功
        if (mapResult != null && mapResult.size() > 1)
        {   
            Object obj = mapResult.get("returnObj");
            if (obj instanceof CTagSet)
            {
                CTagSet chargeInfo = (CTagSet)obj;
                recoid = (String)chargeInfo.GetValue("recoid");// 交易流水
                
                selfCardPayLogVO.setRecdate(sdf1.format(new Date()));
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("促销活动受理现金缴费成功！");
                selfCardPayLogVO.setDealnum(recoid);
                
                // 记录缴费成功日志
                this.createRecLog(servnumber,Constants.PAY_BYCASH,logOID,totalFee,"0","促销活动受理:自助终端缴费成功!");
                
                // 记录受理活动的日志
                privLogVO.setRecStatus("05"); // 受理成功
                privLogVO.setRecStatusDesc("促销活动受理成功！"); // 描述
                
                // 成功标志
                successBz = "0";
                
                // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
                if ("1".equals(tipFlag))
                {
                    tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCNOINPUTTIP", curMenuId);
                }
                // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                
                forward = "success";
            }
        }
        // 受理失败
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("促销活动受理现金缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber,Constants.PAY_BYCASH,logOID,totalFee,"1","促销活动受理:自助终端缴费失败!");
            
            // 记录受理优惠的日志
            privLogVO.setRecStatus("01"); // 受理失败
            privLogVO.setRecStatusDesc("活动受理失败,请凭交易凭条到营业厅办理退款!"); // 描述
            
            // 成功标志
            successBz = "1";
            
            forward = "error";
        }
        
        
        // 受理成功_取费用
        if (mapResult != null && mapResult.size() > 1)
        {   
            // 调用接口查询奖品列表
            Map map = this.activitiesRecBean.qryPrivFee(termInfo, customerSimp, curMenuId, recoid, totalFee);
            if (map != null && map.size() > 0)
            {   
                CRSet crset = (CRSet) map.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    yxfaFee = crset.GetValue(i, 0);// 营销方案费用
                    ycFee = crset.GetValue(i, 1);// 用户预存
                    yxfaFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 0))/100.00) + "";// 营销方案费用
                    ycFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 1))/100.00) + "";// 用户预存
                    privLogVO.setPrivFee(crset.GetValue(i, 0));// 受理费用
                    privLogVO.setChargeFee(crset.GetValue(i, 1));// 多余缴费金额（）
                }
            }
            else
            {
                yxfaFee_yuan = "0";// 营销方案费用
                ycFee_yuan = "0";// 用户预存
                privLogVO.setChargeFee("0");// 多余缴费金额（）
                errormessage = "查询受理费用异常！";
                return "error";
            }
        
        }
        
        //-------------更新缴费记录--------------------------------------------------------------------------------------------
        activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
        
        //-------------增加受理记录--------------------------------------------------------------------------------------------
        activitiesRecHubService.createPrivLog(privLogVO);
        
        //-------------打印信息------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        
        // 返回
        return forward;
    }
    
    /**
     * 发票打印日志
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goPrintInvoice()
    {
        // 取发票更表
        List list = printInvoice(recoid);
        
        // 处理异常
        if (null == list)
        {
            getRequest().setAttribute("errormessage", "获取发票内容为空");
            return "error";
        }
        
        // 组装成XML对象
        String invoice = getXmlStr(list);

        // 放入request
        this.getRequest().setAttribute("invoice", invoice);
        
        // 返回
        return "printInvoice";
    }
    
    /**
     * 现金交费异常处理
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goCashError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError Starting ...");
        }
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 手机号码
        servnumber = customerSimp.getServNumber();
        
        this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(activitiesRecHubService.getChargeLogOID());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
        selfCardPayLogVO.setFee(totalFee);
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(customerSimp.getRegionID());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("activity");// 业务类型（phone：话费缴费 favourable：优惠缴费 activity:促销活动)
        
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if ( totalFee == null || "".equals(totalFee.trim()) || "0".equals(totalFee.trim()))
        if ("".equals(totalFee.trim()) || "0".equals(totalFee.trim()))
        // begin zKF66389 2015-09-15 9月份findbugs修改
        {
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        else
        {
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        
        //-------------更新缴费记录--------------------------------------------------------------------------------------------
        activitiesRecHubService.addChargeLog(selfCardPayLogVO);
        
        //-------------打印信息------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        totalfee_yuan = Integer.parseInt(totalFee)/100 + "";
        
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    /**
     * 转到读银联卡页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String readCard()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("readCard Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("readCard End");
        }
        
        // 返回
        return "success";
    }
    
    /**
     * 转到银联卡密码输入页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPassword Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPassword End");
        }
        
        // 返回
        return "success";
    }
    
    /**
     * 转向确认银行卡缴费金额页面
     * 
     * @return
     */
    public String makeSure()
    {
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 手机号码
        servnumber = customerSimp.getServNumber();
        
        // 返回
        return "makeSure";
    }
    
    /**
     * 扣款之前增加银联卡缴费日志
     * 
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog Starting...");
        }
        
        // request
        HttpServletRequest request = this.getRequest();
        
        // response
        HttpServletResponse response = this.getResponse();
        
        // ajax
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        String xml = "";
        
        try
        {
            writer = response.getWriter();
            
            // session
            HttpSession session = request.getSession();
            
            // 终端机信息
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 用户信息
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
            // 状态
            String status = (String)request.getParameter("status");
            
            // 描述信息
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // pos机号
            String posNum = (String)request.getParameter("posnum");
            
            // 扣款之前批次号
            String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
            
            // 流水号
            String logOID = activitiesRecHubService.getChargeLogOID();
            
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(logOID);// 流水号
            cardChargeLogVO.setRegion(termInfo.getRegion());// 区域
            cardChargeLogVO.setTermID(termInfo.getTermid());// 终端ID
            cardChargeLogVO.setOperID(termInfo.getOperid());// 操作员ID
            cardChargeLogVO.setServnumber(servnumber);// 手机号
            cardChargeLogVO.setPayType("1");// 交费类型
            cardChargeLogVO.setFee(String.valueOf(Integer.parseInt(this.prepayFee) * 100));// 缴费金额
            cardChargeLogVO.setUnionpayuser("");// 商户编号
            cardChargeLogVO.setUnionpaycode("");// 终端编号（pos机编号）
            cardChargeLogVO.setBusiType("");// 交易类型 如：消费交易
            
            //modify begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            //往数据库里存入加密后的银联卡号
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardnumber));
            //modify end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            
            cardChargeLogVO.setBatchnum("");// 终端批次号
            cardChargeLogVO.setAuthorizationcode("");// 授权码（山东用）
            cardChargeLogVO.setBusinessreferencenum("");// 交易参考号（山东用）
            cardChargeLogVO.setUnionpaytime("");// 银联实际扣款时间
            cardChargeLogVO.setVouchernum("");// 凭证号（山东用）
            cardChargeLogVO.setUnionpayfee("");// 银联实际扣款金额，单位（分）
            cardChargeLogVO.setTerminalSeq(terminalSeq);// 终端流水
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);// 受理时间
            cardChargeLogVO.setStatus(status);// 状态
            cardChargeLogVO.setDescription(description);// 描述信息
            cardChargeLogVO.setDealnum("");// BOSS受理编号
            cardChargeLogVO.setAcceptType("");// BOSS受理类型
            cardChargeLogVO.setServRegion(customerSimp.getRegionID());// 区域
            cardChargeLogVO.setOrgID(termInfo.getOrgid());// 所属单位
            cardChargeLogVO.setPosNum(posNum);// pos编号
            cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);// 扣款前批次号
            cardChargeLogVO.setRecType("activity");// 业务类型（phone:话费缴费 favourable:优惠缴费 activity:活动受理）
            
            // 插入缴费日志
            activitiesRecHubService.addChargeLog(cardChargeLogVO);
            
            // 返回流水
            xml = "1~~" + logOID;
        }
        catch (Exception e)
        {
            xml = "0";
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }

        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog end!");
        }
        
        logger.debug("addCardPayLog End!");
    }
    
    /**
     * 扣款成功之后，更新交费日志
     * 
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog Starting...");
        }
        
        // request
        HttpServletRequest request = this.getRequest();
        
        // response
        HttpServletResponse response = this.getResponse();
        
        // ajax
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;

        String xml = "";
        try
        {
            writer = response.getWriter();
            
            // 状态
            String status = (String)request.getParameter("status");
            
            // 描述信息
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // 组装日志对象
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);// 流水号
            cardChargeLogVO.setUnionpayuser(unionpayuser);// 商户编号
            cardChargeLogVO.setUnionpaycode(unionpaycode);// 终端编号
            busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
            cardChargeLogVO.setBusiType(busiType);// 交易类型
            cardChargeLogVO.setBatchnum(batchnum);// 终端批次号
            cardChargeLogVO.setAuthorizationcode(authorizationcode);// 授权码(山东用)
            cardChargeLogVO.setBusinessreferencenum(businessreferencenum);// 交易参考号(山东用)
            cardChargeLogVO.setUnionpaytime(unionpaytime);// 银联实际扣款时间
            cardChargeLogVO.setVouchernum(vouchernum);// 凭证号(山东用)
            cardChargeLogVO.setUnionpayfee(unionpayfee);// 银联实际扣款金额
            cardChargeLogVO.setStatus(status);// 状态
            cardChargeLogVO.setDescription(description);// 描述
            cardChargeLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 交易时间
            
            cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);

            // 插入缴费日志
            activitiesRecHubService.updateCardChargeLog(cardChargeLogVO);
            
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之前记录日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之前记录日志失败");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog end!");
        }
    }
    
    /**
     * 交易受理并完成页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String cardFinish()
    {
        String forward = "success";
        
        DecimalFormat dformat = new DecimalFormat("#.##"); 
        
        // 显示金额
        totalfee_yuan = dformat.format(Double.parseDouble(unionpayfee)/100.00) + "";
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 手机号码
        servnumber = customerSimp.getServNumber();
        
        this.payType = "1";
        
        //----------缴费成功日志sh_charge_log-------------------------------------------------------------------------------------------------
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(this.chargeLogOID);
        selfCardPayLogVO = activitiesRecHubService.getChargeLogById(selfCardPayLogVO).get(0);
        
        //----------受理活动日志sh_log_priv-------------------------------------------------------------------------------------------------
        PrivLogVO privLogVO = new PrivLogVO();
        privLogVO.setPrivLogOID(activitiesRecHubService.getChargeLogOID());// ID
        privLogVO.setChargeID(this.chargeLogOID);// 缴费的流水
        privLogVO.setRegion(termInfo.getRegion()); // 地区
        privLogVO.setServnumber(servnumber);
        privLogVO.setPrivId(this.activityId);// 活动编码
        privLogVO.setPrivNcode(this.dangciId);// 档次编码
        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        privLogVO.setChargeType("1"); // 现金缴费  缴费方式，1：银联卡；4：现金
        privLogVO.setPrivFee(String.valueOf(Integer.parseInt(this.prepayFee)*100));// 受理金额（）
        privLogVO.setToFee(this.unionpayfee);// 用户实缴金额（）
        privLogVO.setChargeFee("0");// 多余缴费金额（）
        privLogVO.setRecType("activity");//favourable -- 优惠办理；activity -- 促销活动。
        
        //----------执行业务受理---------------------------------------------------------------------------------------------------
        
        // 执行活动受理
        String onlycheck = "0";// 1是预受理；0是受理
        String accesstype = "bsacAtsv";// 受理渠道
        String password = "";// 取货密码
        String paytype = "Card";// 支付方式cash:现金  card:银联卡 
        
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, unionpayfee, paytype);
        
        // 受理成功
        if (mapResult != null && mapResult.size() > 1)
        {   
            Object obj = mapResult.get("returnObj");
            if (obj instanceof CTagSet)
            {
                CTagSet chargeInfo = (CTagSet)obj;
                recoid = (String)chargeInfo.GetValue("recoid");// 交易流水
                
                selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("促销活动受理银联卡缴费成功！");
                selfCardPayLogVO.setDealnum(recoid);
                
                // 记录缴费成功日志
                this.createRecLog(servnumber,Constants.PAY_BYCARD,this.chargeLogOID,this.unionpayfee,"0","促销活动受理:自助终端缴费成功!");
                
                // 记录受理活动的日志
                privLogVO.setRecStatus("05"); // 受理成功
                privLogVO.setRecStatusDesc("促销活动受理成功！"); // 描述
                
                // 成功标志
                successBz = "0";
                
                // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
                if ("1".equals(tipFlag))
                {
                    tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCNOINPUTTIP", curMenuId);
                }
                // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                
                forward = "success";
            }
        }
        // 受理失败
        else
        {
            selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("促销活动受理银联缴费失败！");
            selfCardPayLogVO.setDealnum("");
            
            // 记录缴费失败日志
            this.createRecLog(servnumber,Constants.PAY_BYCASH,this.chargeLogOID,this.unionpayfee,"1","促销活动受理:自助终端缴费失败!");
            
            // 记录受理优惠的日志
            privLogVO.setRecStatus("01"); // 受理失败
            privLogVO.setRecStatusDesc("活动受理失败,请凭交易凭条到营业厅办理退款!"); // 描述
            
            // 成功标志
            successBz = "1";
            
            forward = "error";
        }
        
        // 受理成功_取费用
        if (mapResult != null && mapResult.size() > 1)
        {   
            // 调用接口查询奖品列表
            Map map = this.activitiesRecBean.qryPrivFee(termInfo, customerSimp, curMenuId, recoid, this.unionpayfee);
            if (map != null && map.size() > 0)
            {   
                CRSet crset = (CRSet) map.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    yxfaFee = crset.GetValue(i, 0);// 营销方案费用
                    ycFee = crset.GetValue(i, 1);// 用户预存
                    yxfaFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 0))/100.00) + "";// 营销方案费用
                    ycFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 1))/100.00) + "";// 用户预存
                    privLogVO.setPrivFee(crset.GetValue(i, 0));// 受理费用
                    privLogVO.setChargeFee(crset.GetValue(i, 1));// 多余缴费金额（）
                }
            }
            else
            {
                yxfaFee_yuan = "0";// 营销方案费用
                ycFee_yuan = "0";// 用户预存
                privLogVO.setChargeFee("0");// 多余缴费金额（）
                errormessage = "查询受理费用异常！";
                return "error";
            }
        
        }
        
        //-------------更新缴费记录--------------------------------------------------------------------------------------------
        selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
        activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
        
        //-------------增加受理记录--------------------------------------------------------------------------------------------
        activitiesRecHubService.createPrivLog(privLogVO);
        
        //-------------打印信息------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        
        // 返回
        return forward;
    }
    
    /**
     * 银联卡交费异常处理
     * 
     * @return
     * @see
     */
    public String goCardError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError Starting ...");
        }
        
        // session
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
            // 用户信息
            NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
            
            // 手机号码
            servnumber = customerSimp.getServNumber();
            
            // 增加日志
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "1", errormessage);
            
            // 参数
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            
            // add:扣款之前异常 
            // update:扣款之后异常
            if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
            {
                selfCardPayLogVO.setChargeLogOID(activitiesRecHubService.getChargeLogOID());// id
                selfCardPayLogVO.setRegion(termInfo.getRegion());// 区域
                selfCardPayLogVO.setTermID(termInfo.getTermid());// 终端ID
                selfCardPayLogVO.setOperID(termInfo.getOperid());// 操作员
                selfCardPayLogVO.setServnumber(servnumber);// 手机号
                selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);// 交易方式
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(this.prepayFee));// 受理金额
                selfCardPayLogVO.setTerminalSeq("");// 终端流水
                selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 受理时间
                selfCardPayLogVO.setStatus("00");// 初始状态
                selfCardPayLogVO.setDescription(errormessage);// 异常信息
                selfCardPayLogVO.setDealnum("");// BOSS受理编号
                selfCardPayLogVO.setAcceptType("");// 受理类型
                selfCardPayLogVO.setServRegion(customerSimp.getRegionID());// 手机所属REGION
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());// 终端所属单位
                selfCardPayLogVO.setRecType("activity");// 业务类型（phone：话费缴费 favourable：优惠缴费 activity:促销活动)
                
                // 增加缴费记录
                activitiesRecHubService.addChargeLog(selfCardPayLogVO);
            }
            // 更新日志
            else
            {
                selfCardPayLogVO.setChargeLogOID(this.chargeLogOID);
                
                if (this.unionpayfee == null || "".equals(this.unionpayfee.trim()))
                {
                    selfCardPayLogVO.setStatus("00");
                }
                else
                {
                    selfCardPayLogVO.setStatus("10");
                }
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                
                // 银联终端号
                selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
                
                // 银联商户号
                selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
                
                // 银联扣款返回码
                selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
                
                // 列新错误信息
                activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
            }
            
            //-------------打印信息------------------------------------------------------------------------------------------------
            region = termInfo.getRegion();
            termid = termInfo.getTermid();
            orgname = termInfo.getOrgname();
            termName = termInfo.getTermname();
        }
        catch (Exception e)
        {
            // 捕获一切异常,使页面必须走退卡页面
            logger.error(e);
            e.printStackTrace();
            errormessage = errormessage + e.getMessage();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        // 转到银联卡退卡页面
        return "cardErrorPage";
    }
    
    /**
     * 调用接口打印发票
     * 
     * @param invoiceType
     * @param dealNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, String>> printInvoice(String dealNum)
    {
        // modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089        
        HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPINVOICE", curMenuId);
        }

        // 提供发票打印功能时，获取发票信息
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = this.activitiesRecBean.getInvoiceData(termInfo, curMenuId, servnumber, dealNum);
            
            if (invoiceData != null && invoiceData.size() > 1)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof Vector)
                {
                    Vector invoiceVector = (Vector)invoiceObj;
                    
                    CTagSet tagSet = (CTagSet)invoiceVector.get(0);
                    String cycleCount = (String)tagSet.GetValue("invoice_cnt");
                    int rowNum = Integer.parseInt(cycleCount);
                    
                    CRSet crset = (CRSet)invoiceVector.get(1);
                    
                    List<Map<String, String>> invoicesList = new ArrayList<Map<String, String>>();
                    
                    for (int i = 0; i < rowNum; i++)
                    {
                        invoicesList.add(getInvoiceInfo(i, crset, tip));
                    }
                    
                    return invoicesList;
                }
            }
        }
        // modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        return null;
    }
    
    /**
     * 对客服返回的打印发票内容进行处理 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    
    public Map<String, String> getInvoiceInfo(int inde, CRSet cRSet, String tipText)
    {
        Map<String, String> invoiceMaps = new HashMap<String, String>();
        invoiceMaps.put("tipText", tipText);
        
        String[] invoiceArray = cRSet.GetValue(inde, 1).split("\\|\\|");
        // 缴费时间
        invoiceMaps.put("chargeDate", cRSet.GetValue(inde, 0));
        
        // 客户名称
        invoiceMaps.put("username", getInvoiceItem(invoiceArray, "username"));
        
        // 款项性质
        invoiceMaps.put("callfeeCreateTime", getInvoiceItem(invoiceArray, "feetype"));
        
        // 电话号码
        invoiceMaps.put("telnumber", getInvoiceItem(invoiceArray, "telnumber"));
        
        // 电话号码
        invoiceMaps.put("formnum", getInvoiceItem(invoiceArray, "formnum"));
        
        // 大写金额
        invoiceMaps.put("invoicefeedx", getInvoiceItem(invoiceArray, "invoicefeedx"));
        
        // 小写金额
        invoiceMaps.put("invoicefee", getInvoiceItem(invoiceArray, "invoicefee"));
        
        // 合同号
        invoiceMaps.put("paynumno", getInvoiceItem(invoiceArray, "paynumno"));
        
        // 合同号
        invoiceMaps.put("paynumno", getInvoiceItem(invoiceArray, "paynumno"));
        
        // 费用合计
        invoiceMaps.put("invoicefeehj", getInvoiceItem(invoiceArray, "invoicefeehj"));
        
        // 积分
        invoiceMaps.put("Score", getInvoiceItem(invoiceArray, "Score"));
        
        invoiceMaps.put("agreementleftbal", getInvoiceItem(invoiceArray, "hz_agreementleftbal") + "@"
                + getInvoiceItem(invoiceArray, "agreementleftbal"));
        
        invoiceMaps.put("agreementleftbal_Z", getInvoiceItem(invoiceArray, "hz_agreementleftbal_Z") + "@"
                + getInvoiceItem(invoiceArray, "agreementleftbal_Z"));
        
        invoiceMaps.put("InvoiceNote", getInvoiceItem(invoiceArray, "note"));
        
        // 打印时间
        invoiceMaps.put("printtime", getInvoiceItem(invoiceArray, "hz_printtime") + "@"
                + getInvoiceItem(invoiceArray, "printtime"));
        
        // 打印时间
        invoiceMaps.put("totalmoney", getInvoiceItem(invoiceArray, "hz_totalmoney") + "@"
                + getInvoiceItem(invoiceArray, "totalmoney"));
        
        // 累计结余
        invoiceMaps.put("leftmoney", getInvoiceItem(invoiceArray, "hz_leftmoney") + "@"
                + getInvoiceItem(invoiceArray, "leftmoney"));
        
        //
        invoiceMaps.put("overdraft", getInvoiceItem(invoiceArray, "hz_overdraft") + "@"
                + getInvoiceItem(invoiceArray, "overdraft"));
        
        return invoiceMaps;
        
    }
    
    /**
     * 获取发票键值 <功能详细描述>
     * 
     * @param invoiceArray
     * @param itemName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getInvoiceItem(String[] invoiceArray, String itemName)
    {
        String printItem = "";
        for (int i = 0; i < invoiceArray.length; i++)
        {   
            String[] invoArr = invoiceArray[i].split("@~@");
            
                if (invoArr.length > 1 && invoArr[0].equals(itemName) )
                {
                    printItem = invoArr[1];
                    
                    break;
                }
               
        }
        return printItem;
    }
    
    /**
     * 将发票数据组织成xml
     * 
     * @param list 发票数据
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                        .append(subItemKey)
                        .append("><![CDATA[")
                        .append(subItemValue)
                        .append("]]></")
                        .append(subItemKey)
                        .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        
        return xmlStr.toString();
    }
    
    /**
     * 记录发票打印日志
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String recoid = request.getParameter("recoid");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintPO record = new InvoicePrintPO();
        record.setServNumber(servNumber);
        record.setFormnum(recoid);
        record.setTermID(termInfo.getTermid());
        
        this.activitiesRecHubService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
    /**
     * 取字典项
     * <功能详细描述>
     * @param dictID
     * @param groupID
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getDictName(String dictID, String groupID)
    {
        if (null == dictID || null == groupID)
        {
            return dictID;
        }
        if (!activitiesRecBean.pubMap.containsKey(groupID))
        {
            // 需要去CRM系统里获取字典表数据
            Map<String, String> inMap = new HashMap<String, String>();
            getInMap(inMap);
            inMap.put("groupid", groupID);
            activitiesRecBean.getDictItemByGroup(inMap);
        }
        Vector<DictItemVO> v = (Vector<DictItemVO>)activitiesRecBean.pubMap.get(groupID);
        for (DictItemVO dictItem : v)
        {
            if (dictID.equals(dictItem.getDictid()))
            {
                return dictItem.getDictname();
            }
        }
        
        return dictID;
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param inMap
     * @see [类、类#方法、类#成员]
     */
    private void getInMap(Map<String, String> inMap)
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 设置操作员id
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        inMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // 设置客户手机号
        inMap.put("telnumber", customer.getServNumber());
        inMap.put("telnum", customer.getServNumber());
        
        // 地区
        inMap.put("region", terminalInfoPO.getRegion());
    }


    //------------------活动分页-------------------------------------------------------------------------------------------
    /**
     * 当前页
     */
    private int page = 1;
    
    /**
     * 总条数
     */
    private int countNum = 0;
    
    /**
     * 每页条数
     */
    private int pageNum = 6;
    
    /**
     * 总页数
     */
    private int countPageNum = 0;
    
    /**
     * 取当前页面数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void displayPage()
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // 清空数据
        activityList.clear();
        
        // 计算总条数
        countNum = activityAllList.size();
        
        // 计算总页数
        if (countNum % pageNum > 0)
        {
            countPageNum = countNum / pageNum + 1;
        }
        else
        {
            countPageNum = countNum / pageNum;
        }
        
        // 开始条数
        int startNum = pageNum * (page - 1) + 1;
        
        // 结束条数
        int endNum = pageNum * page;
        
        // 开始条数
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum)
            {
                this.activityList.add(activityAllList.get(i-1));
            }
        }
        
        // 页面18条记录 List<ActivityVO>
        /**
        if (this.activityList.size() < pageNum)
        {
            int size = activityList.size();
            ActivityVO activityVO = new ActivityVO();
            activityVO.setGroupId("");
            activityVO.setGroupName("");
            for(int i=size;i<pageNum;i++)
            {
                this.activityList.add(activityVO);
            }
        }
        */
        
    }
    
    //------------------档次分页-------------------------------------------------------------------------------------------
    /**
     * 总条数
     */
    private int countNum_danci = 0;
    
    /**
     * 每页条数
     */
    private int pageNum_danci = 4;
    
    /**
     * 总页数
     */
    private int countPageNum_danci = 0;
    
    /**
     * 取当前页面数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void displayPage_danci()
    {
        // 清空数据
        activityList.clear();
        
        // 计算总条数
        countNum_danci = activityAllList.size();
        
        // 计算总页数
        if (countNum_danci % pageNum_danci > 0)
        {
            countPageNum_danci = countNum_danci / pageNum_danci + 1;
        }
        else
        {
            countPageNum_danci = countNum_danci / pageNum_danci;
        }
        
        if (page == 0)
        {
            page = 1;
        }
        
        // 开始条数
        int startNum = pageNum_danci * (page - 1) + 1;
        
        // 结束条数
        int endNum = pageNum_danci * page;
        
        // 开始条数
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum_danci)
            {
                this.activityList.add(activityAllList.get(i-1));
            }
        }
    }
    
    //------------------奖品分页-------------------------------------------------------------------------------------------
    /**
     * 总条数
     */
    private int countNum_present = 0;
    
    /**
     * 每页条数
     */
    private int pageNum_present = 4;
    
    /**
     * 总页数
     */
    private int countPageNum_present = 0;
    
    /**
     * 取当前页面数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void displayPage_present()
    {
        // 清空数据
        awardList.clear();
        
        // 计算总条数
        countNum_present = awardAllList.size();
        
        // 计算总页数
        if (countNum_present % pageNum_present > 0)
        {
            countPageNum_present = countNum_present / pageNum_present + 1;
        }
        else
        {
            countPageNum_present = countNum_present / pageNum_present;
        }
        
        if (page == 0)
        {
            page = 1;
        }
        
        // 开始条数
        int startNum = pageNum_present * (page - 1) + 1;
        
        // 结束条数
        int endNum = pageNum_present * page;
        
        // 开始条数
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum_present)
            {
                this.awardList.add(awardAllList.get(i-1));
            }
        }
    }

    // add begin hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
    /**
     * 登录bean
     */
    private transient UserLoginBean userLoginBean;
    
    /**
     * 登录service
     */
    private transient LoginService loginService;
    
    /**
     * 服务密码
     */
    private String password;
    
    /**
     * 短信验证码
     */
    private String randomPwd;
    
    /**
     * 主动营销推荐标识
     */
    private String recommendActivityFlag;

    /**
     * <服务密码认证并进行活动预受理>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
     */
    public void chkServPwd()throws Exception
    {
        logger.info("chkServPwd start");
        
        // 校验标识 成功：0，失败：1~~错误信息
        String xml = "0";
        
        HttpSession session = this.getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
       
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
        if (null != loginErrorPO && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // 被锁定
            logger.error("由于服务密码连续输入错误次数达到了系统限制，号码" + servnumber + "暂时被锁定");
            
            xml = "11~~由于服务密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试";
            writeXmlMsg(xml);
            return;
        }
        
        // 服务密码验证，获取用户信息
        Map customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
        
        // 解析用户信息
        xml = handleUserInfo(xml,customerSimpMap,session);
        
        // 服务密码校验失败
        if(!"0".equals(xml))
        {
            // 登录失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("使用服务密码进行身份认证失败，手机号码：" + servnumber);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用无密码进行身份认证失败。");
            
            writeXmlMsg(xml);
            return;
        }
        
        // 登录成功，删除记录
        loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
        
        // 查询奖品并进行活动预受理
        preAccept();
    }
    
    /**
     * <解析用户信息>
     * <功能详细描述>
     * @param xml 结果标识
     * @param customerSimpMap 用户信息map
     * @param session
     * @param orimsgid  错误转换码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
     */
    public String handleUserInfo(String xml,Map customerSimpMap,HttpSession session)
    {
        logger.debug("handleUserInfo start");
        
        // 用户信息map中无用户信息则校验失败
        if (customerSimpMap.get("customerSimp") == null)
        {
            logger.error("获取用户信息失败，手机号码：" + servnumber);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用无密码进行身份认证失败。");
            
            String returnMsg = (String)customerSimpMap.get("returnMsp");
            xml = "11~~" + returnMsg;
        }
        else
        {   
            // 用户信息
            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
            
            // 原用户信息
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            // 使用新的用户信息替换session中原用户信息
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                // 老手机号码及其验证方式
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                // 清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // 清除账单数据
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                
                // 清除用户信息
                session.removeAttribute(Constants.USER_INFO);
                
                // 将新的用户信息存放在session中
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                // 设置用户的loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
        }
        
        logger.debug("handleUserInfo end");
        return xml;
    }
    
    /**
     * <校验短信验证码>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
     */
    public void chkRandomPwd()throws Exception
    {
        logger.debug("chkRandomPwd start");

        // 校验标识 成功：0，鉴权失败：11~~错误信息  预受理失败：10~~错误信息 
        String xml = "0";
        
        // 查询号码是否锁定
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // 被锁定
            logger.error("由于短信验证码认证失败次数达到了系统限制，号码" + servnumber + "暂时不能使用短信验证码认证");
            
            xml = "11~~由于短信验证码认证失败次数达到了系统限制，您暂时不能使用短信验证码认证，请" + lockedTime + "分钟后再试";
            writeXmlMsg(xml);
            return;
        }
        
        // 校验短信验证码
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        
        // 短信验证码校验失败
        if (!"1".equals(result))
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "对不起，您输入的短信验证码已经超时。";
            }
            else
            {
                errorMsg = "对不起，您的短信验证码输入错误。";
            }

            // 认证失败记录
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            xml = "11~~" + errorMsg;
            writeXmlMsg(xml);
            return;
        }
        
        // 认证成功，删除记录
        loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        logger.info("用户" + servnumber + "使用短信验证码进行身份认证成功");
        
        // 进行调用接口登录
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        
        xml = handleUserInfo(xml,customerSimpMap,session);
        
        if(!"0".equals(xml))
        {
            writeXmlMsg(xml);
            return;
        }
        
        preAccept();
    }
    
    /**
     * <活动预受理>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
     */
    public void preAccept()throws Exception
    {
        logger.debug("preAccept start");
        
        // 校验标识 成功：0，鉴权失败：11~~错误信息  预受理失败：10~~错误信息 
        String xml = "0";
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 解析
        dangciId = java.net.URLDecoder.decode(dangciId, "UTF-8");
        activityId = java.net.URLDecoder.decode(activityId, "UTF-8");
        
        // 根据活动编码与档次编码查询
        ActivityVO vo = new ActivityVO();
        vo.setActivityId(activityId);
        vo.setDangciId(dangciId);
        List<ActivityVO> list = this.activitiesRecHubService.getDangciById(vo);
        
        // 档次名称
        this.dangciName = "";
        
        // 档次描述
        this.dangciDesc  = "";
        
        if (list != null && list.size() > 0)
        {
            vo = list.get(0);
            this.dangciName = vo.getDangciName();
            this.dangciDesc = vo.getActivityDesc();
        }

        // 调用接口查询奖品列表
        Map result = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        if (result != null && result.size() > 0)
        {   
            CRSet crset = (CRSet) result.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                if ( i+1 == crset.GetRowCount() )
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0);
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
                else
                {
                    // 奖品编码串
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // 手机imeiid号
                    imeiid = imeiid + "|";
                    // 赠品总价
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // 赠品数量
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            xml = "10~~查询赠品列表异常!";
            writeXmlMsg(xml);
            return;
        }
        
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        
        // 1是预受理；0是受理
        String onlycheck = "1";
        
        // 赠品数量
        quantity = 1;
       
        // 执行活动预处理
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, "bsacAtsv", "", "", "Cash");

        if (mapResult == null)
        {
            xml = "10~~对不起，执行活动预处理失败！";
            writeXmlMsg(xml);
            return;
        }
        else if (mapResult.size() == 1)
        {
            String returnMsg = (String)mapResult.get("returnMsg");
            xml = "10~~" + returnMsg;
            writeXmlMsg(xml);
            return;
        }
        
        writeXmlMsg(xml);
    }
    
    /**
     * AJAX调用返回消息
     * @return void [返回类型说明]
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
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
            // 输出到client
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
     * 校验是否锁定
     * <功能详细描述>
     * @param loginErrorPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // 错误次数达到了系统限制
        if (maxTimes <= loginErrorPO.getErrorTimes())
        {
            String lastTime = loginErrorPO.getLastTime();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            try
            {
                // 锁定时长
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // 当前时间
                String currTime = sdf.format(c.getTime());
                
                // 解锁时间
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // 解锁时间要晚于当前时间，即服务号码在锁定期间内
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("判断号码是否被锁定时发生异常：", e);
            }
        }
        
        // 错误次数尚未达到系统限制
        return false;
    }
    // add end hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
    
    public String getRecommendActivityFlag()
    {
        return recommendActivityFlag;
    }

    public void setRecommendActivityFlag(String recommendActivityFlag)
    {
        this.recommendActivityFlag = recommendActivityFlag;
    }

    public String getRandomPwd()
    {
        return randomPwd;
    }

    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public int getCountNum()
    {
        return countNum;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setCountNum(int countNum)
    {
        this.countNum = countNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public int getPageNum()
    {
        return pageNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getCountPageNum()
    {
        return countPageNum;
    }

    public void setCountPageNum(int countPageNum)
    {
        this.countPageNum = countPageNum;
    }

    public ActivitiesRecHubService getActivitiesRecHubService()
    {
        return activitiesRecHubService;
    }

    public void setActivitiesRecHubService(ActivitiesRecHubService activitiesRecHubService)
    {
        this.activitiesRecHubService = activitiesRecHubService;
    }

    public List<ActivityVO> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityVO> activityList)
    {
        this.activityList = activityList;
    }

    public List<ActivityVO> getActivityAllList()
    {
        return activityAllList;
    }

    public void setActivityAllList(List<ActivityVO> activityAllList)
    {
        this.activityAllList = activityAllList;
    }

    public List<AwardVO> getAwardList()
    {
        return awardList;
    }

    public void setAwardList(List<AwardVO> awardList)
    {
        this.awardList = awardList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCurMenuId() {
		return curMenuId;
	}
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getDangciId()
    {
        return dangciId;
    }

    public void setDangciId(String dangciId)
    {
        this.dangciId = dangciId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getGroupId()
    {
        return groupId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
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

    public List getUsableTypes()
    {
        return usableTypes;
    }

    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getServnumber()
    {
        return servnumber;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getErrormessage()
    {
        return errormessage;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getActreward()
    {
        return actreward;
    }

    public void setActreward(String actreward)
    {
        this.actreward = actreward;
    }

    public String getImeiid()
    {
        return imeiid;
    }

    public void setImeiid(String imeiid)
    {
        this.imeiid = imeiid;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getRewardAccount()
    {
        return rewardAccount;
    }

    public void setRewardAccount(int rewardAccount)
    {
        this.rewardAccount = rewardAccount;
    }


    public String getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(String totalFee)
    {
        this.totalFee = totalFee;
    }

    public String getTotalfee_yuan()
    {
        return totalfee_yuan;
    }

    public void setTotalfee_yuan(String totalfee_yuan)
    {
        this.totalfee_yuan = totalfee_yuan;
    }

    public String getTerminalSeq()
    {
        return terminalSeq;
    }

    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }

    public String getRecoid()
    {
        return recoid;
    }

    public void setRecoid(String recoid)
    {
        this.recoid = recoid;
    }

    public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTermid()
    {
        return termid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTermid(String termid)
    {
        this.termid = termid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTermName()
    {
        return termName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTermName(String termName)
    {
        this.termName = termName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOperId()
    {
        return operId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOperId(String operId)
    {
        this.operId = operId;
    }

    public String getSuccessBz()
    {
        return successBz;
    }

    public void setSuccessBz(String successBz)
    {
        this.successBz = successBz;
    }

    public ActivitiesRecBean getActivitiesRecBean()
    {
        return activitiesRecBean;
    }

    public void setActivitiesRecBean(ActivitiesRecBean activitiesRecBean)
    {
        this.activitiesRecBean = activitiesRecBean;
    }

    public String getYxfaFee()
    {
        return yxfaFee;
    }

    public void setYxfaFee(String yxfaFee)
    {
        this.yxfaFee = yxfaFee;
    }

    public String getYcFee()
    {
        return ycFee;
    }

    public void setYcFee(String ycFee)
    {
        this.ycFee = ycFee;
    }

    public String getYxfaFee_yuan()
    {
        return yxfaFee_yuan;
    }

    public void setYxfaFee_yuan(String yxfaFee_yuan)
    {
        this.yxfaFee_yuan = yxfaFee_yuan;
    }

    public String getYcFee_yuan()
    {
        return ycFee_yuan;
    }

    public void setYcFee_yuan(String ycFee_yuan)
    {
        this.ycFee_yuan = ycFee_yuan;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getActivityName()
    {
        return activityName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public int getCountNum_danci()
    {
        return countNum_danci;
    }

    public void setCountNum_danci(int countNum_danci)
    {
        this.countNum_danci = countNum_danci;
    }

    public int getPageNum_danci()
    {
        return pageNum_danci;
    }

    public void setPageNum_danci(int pageNum_danci)
    {
        this.pageNum_danci = pageNum_danci;
    }

    public int getCountPageNum_danci()
    {
        return countPageNum_danci;
    }

    public void setCountPageNum_danci(int countPageNum_danci)
    {
        this.countPageNum_danci = countPageNum_danci;
    }

    public List<AwardVO> getAwardAllList()
    {
        return awardAllList;
    }

    public void setAwardAllList(List<AwardVO> awardAllList)
    {
        this.awardAllList = awardAllList;
    }

    public int getCountNum_present()
    {
        return countNum_present;
    }

    public void setCountNum_present(int countNum_present)
    {
        this.countNum_present = countNum_present;
    }

    public int getPageNum_present()
    {
        return pageNum_present;
    }

    public void setPageNum_present(int pageNum_present)
    {
        this.pageNum_present = pageNum_present;
    }

    public int getCountPageNum_present()
    {
        return countPageNum_present;
    }

    public void setCountPageNum_present(int countPageNum_present)
    {
        this.countPageNum_present = countPageNum_present;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPayType()
    {
        return payType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getNeedReturnCard()
    {
        return needReturnCard;
    }

    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }

    public String getCardnumber()
    {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }

    public String getChargeLogOID()
    {
        return chargeLogOID;
    }

    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }

    public String getUnionpayuser()
    {
        return unionpayuser;
    }

    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }

    public String getUnionpaycode()
    {
        return unionpaycode;
    }

    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBusiType()
    {
        return busiType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }

    public String getBatchnum()
    {
        return batchnum;
    }

    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
    }

    public String getAuthorizationcode()
    {
        return authorizationcode;
    }

    public void setAuthorizationcode(String authorizationcode)
    {
        this.authorizationcode = authorizationcode;
    }

    public String getBusinessreferencenum()
    {
        return businessreferencenum;
    }

    public void setBusinessreferencenum(String businessreferencenum)
    {
        this.businessreferencenum = businessreferencenum;
    }

    public String getUnionpaytime()
    {
        return unionpaytime;
    }

    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }

    public String getVouchernum()
    {
        return vouchernum;
    }

    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }

    public String getUnionpayfee()
    {
        return unionpayfee;
    }

    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
    }

    public String getInitPosResCode()
    {
        return initPosResCode;
    }

    public void setInitPosResCode(String initPosResCode)
    {
        this.initPosResCode = initPosResCode;
    }

    public String getCmtPosResCode()
    {
        return cmtPosResCode;
    }

    public void setCmtPosResCode(String cmtPosResCode)
    {
        this.cmtPosResCode = cmtPosResCode;
    }

    public String getErrPosResCode()
    {
        return errPosResCode;
    }

    public void setErrPosResCode(String errPosResCode)
    {
        this.errPosResCode = errPosResCode;
    }

    public String getErrorType()
    {
        return errorType;
    }

    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public List<ActivityVO> getDangciAllList()
    {
        return dangciAllList;
    }

    public void setDangciAllList(List<ActivityVO> dangciAllList)
    {
        this.dangciAllList = dangciAllList;
    }

    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }

    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }
    
    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

    public String getOrgname()
    {
        return orgname;
    }

    public void setOrgname(String orgname)
    {
        this.orgname = orgname;
    }
    
}
