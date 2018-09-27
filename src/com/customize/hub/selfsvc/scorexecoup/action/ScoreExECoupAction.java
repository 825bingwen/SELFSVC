/*
 * @filename: ScoreExECoupAction.java
 * @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
 * @brif:  积分兑换电子券
 * @author: m00227318
 * @de:  2012/10/18 
 * @description: 
 * @remark: create m00227318 2012/10/18 eCommerce V200R003C12L09 OR_huawei_2012_09_33
 */
package com.customize.hub.selfsvc.scorexecoup.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.customize.hub.selfsvc.bean.ScoreExECoupBean;
import com.customize.hub.selfsvc.scorexecoup.model.RewardAction;
import com.customize.hub.selfsvc.scorexecoup.model.ScorePojo;
import com.customize.hub.selfsvc.scorexecoup.service.ScoreExECoupService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


/**
* 湖北积分兑换电子券
* @author m00227318
* 
*/
public class ScoreExECoupAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	private ScoreExECoupBean qryScoreExCoupBean;
	
	private ScoreExECoupService scoreExECoupService;
	
	// 当前菜单，由jsp页面传入
    private String curMenuId = "";
    
    //活动编码
    private String activityId = "";
    
    //档次编码
    private String dangciId =  "";
	
	//jsp页面选择要兑换的积分数
	private String exchangeScore = "";
	
	//用户剩余积分数
	private String usableScore = "";
	
    //优惠类赠品的奖品编号
    private String prefRewardId = "";
    
    //受理编号，应答报文返回
    private String recoid = "";
    
    //业务办理成功信息
    private String successMessage = "";   
    
    //出错信息
    private String errormessage = "";
    
    //最少允许进行兑换的积分值
    private String score_value;
    
    //头部内容
    private String score_title;
    
    //允许兑换的积分列表
    private List<ScorePojo> scoreList; 
    
    //提示信息
    private String scoreWaring;
    
    // add begin cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
    //兑换类型， 0:积分兑换电子券 1:积分兑换话费
    private String changeType;
    // add end cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
   //add begin yKF73963 2013-09-09   OR_HUB_201304_824  自助终端-积分专区 
    private String dictId;
    List<RewardAction> canRewardList=new ArrayList<RewardAction>();
    List<RewardAction> canActiveList=new ArrayList<RewardAction>();
    
    //add end yKF73963 2013-09-09   OR_HUB_201304_824  自助终端-积分专区 
    /**
	 * 用户查询优惠类赠品列表
	 * @see
	 * @return
	 * @remark:
	 */	
	public String getPrefRewardList()
	{
		String forward = "error";
		
		HttpSession session = this.getRequest().getSession();
		
		// 当前终端机
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //若当前用户没有可用积分，转向错误页面
		if (customerSimp.getSubscore() == null || "".equals(customerSimp.getSubscore()) || "0".equals(customerSimp.getSubscore()))
		{
			errormessage = "您目前没有可用积分，不能办理积分兑换电子券业务。";
			
			this.createRecLog(curMenuId, "0", "0", "1", errormessage);
		}
		else
		{
		    // modify begin cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
		    // 根据兑换类型取出该兑换类型配置表全部数据
		    List<ScorePojo> allList = this.scoreExECoupService.getScorePojoListType(changeType);
		    		    
		    // 湖北优惠类的赠品类型，与CRM一致
            String prefRwdType = "";
            scoreList = new ArrayList<ScorePojo>();
            for (ScorePojo po : allList)
            {
                if (po.getType().equals(Constants.SH_SCOREEXECOUP_VALUE))
                {
                    // 最少允许进行兑换的积分
                    score_value = po.getScore();
                }
                
                if (po.getType().equals(Constants.SH_SCOREEXECOUP_TITLE))
                {
                    // 标题头
                    score_title = po.getDescription();
                }
                
                if (po.getType().equals(Constants.SH_SCOREEXECOUP_DATA))
                {
                    scoreList.add(po);
                }
                
                if (po.getType().equals(Constants.SCOREEXECOUP_PRODID))
                {
                    // 湖北积分兑换电子券，活动编码，与CRM一致
                    activityId = po.getScore();
                }
                
                if (po.getType().equals(Constants.SCOREEXECOUP_PRIVID))
                {
                    // 湖北积分兑换电子券，档次编码，与CRM一致
                    dangciId = po.getScore();
                }
                
                if (po.getType().equals("SH_PREFRWD_TYPE"))
                {
                    // 湖北优惠类的赠品类型，与CRM一致
                    prefRwdType = po.getScore();
                }
            }
            
            if(StringUtils.isEmpty(score_value))
            {
                errormessage = "最少兑换积分没有设置请先设置最少兑换积分!";
                this.createRecLog(curMenuId, "0", "0", "1", errormessage);
                
                return forward;
            }
		    
		    // 如果最小限制是100积分，但用户只有90积分，则只展现“全部兑换”，并提示“您当前可用积分值：90，
            // 您可以选择全部兑换。”)
            if (Integer.parseInt(customerSimp.getSubscore()) >= Integer.parseInt(score_value))
            {
                
                Iterator<ScorePojo> iterator = scoreList.iterator();
                
                while (iterator.hasNext())
                {
                    ScorePojo dict = iterator.next();
                    
                    // 对配置的结果进行筛选(该页面需做判断，如果用户积分不够300，则300、500、1000这几项不展现，
                    if (Integer.parseInt(customerSimp.getSubscore()) < Integer.parseInt(dict.getScore())
                            || Integer.parseInt(dict.getScore()) < Integer.parseInt(score_value))
                    {
                        iterator.remove();
                    }
                }
                
                // 如果用户配置的超值4个单独的积分兑换模块就默认取前四个为了保证样式
                if (scoreList.size() > 4)
                {
                    List<ScorePojo> tempList = new ArrayList<ScorePojo>();
                    for (int i = 0; i < 4; i++)
                    {
                        tempList.add(scoreList.get(i));
                    }
                    scoreList.clear();
                    scoreList.addAll(tempList);
                    tempList = null;
                }
            }
            else
            {
                scoreWaring = "您当前可用积分值：" + customerSimp.getSubscore() + "，您可以选择全部兑换。";
            }
			
			//调用接口查询当前用户的赠品
			Map<String,Object> mapResult = qryScoreExCoupBean.qryPrefRewardList(terminalInfoPO, customerSimp, curMenuId, activityId, dangciId);
			// modify end cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
			
			if (mapResult != null && "1".equals((String) mapResult.get("successFlag")))
			{
				CRSet crset = (CRSet) mapResult.get("returnObj");
				
			    //当前用户有赠品
				if (crset != null && crset.GetRowCount() > 0)
				{
					//取优惠类赠品的奖品编号
					for (int i = 0; i < crset.GetRowCount(); i++)
					{
						if (prefRwdType.equalsIgnoreCase(crset.GetValue(i, 2)))
						{
							prefRewardId = crset.GetValue(i, 0);
							break;
						}
					}
				}
				
				//有优惠类赠品
				if (prefRewardId != null && !"".equals(prefRewardId))
				{
					forward = "success";
				}
				//无优惠类赠品
				else
				{
					errormessage = "查询积分兑换电子券活动的优惠类赠品失败";
		            
		            this.createRecLog(curMenuId, "0", "0", "1", errormessage);
				}
			}
			else
			{
				errormessage = (String) mapResult.get("returnMsg");
	            
	            this.createRecLog(curMenuId, "0", "0", "1", errormessage);
			}		
		}
		
		return forward;
	}
	
	/**
	 * 湖北提交积分兑换电子券业务
	 * @return
	 * @remark
	 */
	public String commitPrefRewardList()
	{
	    String forward = "error";
	    
	    // modify begin cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
	    // 根据兑换类型取出该兑换类型配置表全部数据
        List<ScorePojo> allList = this.scoreExECoupService.getScorePojoListType(changeType);
        
        //湖北，积分兑换电子券提交的附加串中产品的附加属性，与CRM一致
        String elemoney = "";
        
        // begin BUG_HUB_201305_28 yKF28472
        //一积分兑换的钱数元单位
        double scoreexecoup_equals = 0.00;
        // end BUG_HUB_201305_28 yKF28472
        
	    for (ScorePojo po : allList)
        {
            if (po.getType().equals("SH_ELEMONEY"))
            {
                elemoney = po.getScore();
            }
            
            if (po.getType().equals("SCOREEXECOUP_EQUALS"))
            {
                scoreexecoup_equals = Double.parseDouble((String)po.getScore());
            }
        }
	    // modify end cKF76106 2013/07/10 R003C13L07n01 OR_HUB_201207_1089    
		//优惠类赠品的附加属性串，新增字段
		String addattrstr = "";
		
		// 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        //手机的IMEI号串，没用到
        String imeiid = "";
        
        // 1是预受理；0是受理
        String onlycheck = "0";
        
        // 受理渠道
        String accesstype = "bsacAtsv";
        
        // 取货密码
        String password = "";
        
        //用户投入的金额
        String totalfee = "";
        
        // 支付方式cash:现金  card:银联卡 
        String paytype = "";
        
        //赠品数量
        String quantity = exchangeScore;
        
        //自助终端在提交时需要指定此附加属性串：1000001,P,ELEMONEY=3;1000001为奖品编码;P表示优惠的附加属性;elemoney表示附加属性编码;3表示附加属性值。
        // begin BUG_HUB_201305_28 yKF28472
        //addattrstr = prefRewardId + "," + "P" + "," + elemoney + "=" + exchangeScore;
        double elemoneyValue = Math.ceil(Double.parseDouble(exchangeScore)/1.0*scoreexecoup_equals*100.00);
        int elemoneyValue_ = (int)elemoneyValue;
        addattrstr = prefRewardId + "," + "P" + "," + elemoney + "=" + elemoneyValue_;
        // end BUG_HUB_201305_28 yKF28472
        
        //提交积分兑换电子券业务
        Map<String,Object> mapResult = this.qryScoreExCoupBean.prefRewardCommit(termInfo, customerSimp, curMenuId, activityId, 
        		dangciId, prefRewardId, imeiid, onlycheck, quantity, accesstype, password, totalfee, paytype, addattrstr);

        // 受理成功
        if (mapResult != null && "1".equals((String) mapResult.get("successFlag")))
        {   
            Object obj = mapResult.get("returnObj");
            if (obj instanceof CTagSet)
            {
                CTagSet chargeInfo = (CTagSet)obj;
                recoid = (String)chargeInfo.GetValue("recoid");// 交易流水
                
                //用户剩余可用积分等于用户原本的积分减去消费的积分
                if (customerSimp.getSubscore() != null || !"".equals(customerSimp.getSubscore()))
                {
                    usableScore = String.valueOf(Integer.parseInt(customerSimp.getSubscore())- Integer.parseInt(exchangeScore));
                    customerSimp.setSubscore(usableScore);                                        
                }
                
                successMessage = "办理积分兑换电子券业务成功，请返回办理其他业务。";
                
                this.createRecLog(curMenuId, recoid, "0", "0", "湖北积分兑换电子券业务：办理成功");
                
                forward = "success";
            }
            else
            {
            	errormessage = "积分兑换电子券活动办理失败";
	            
	            this.createRecLog(curMenuId, "0", "0", "1", errormessage);
            }
        }
        else
        {
        	errormessage = (String) mapResult.get("returnMsg");       	
            
            this.createRecLog(curMenuId, "0", "0", "1", errormessage);
        }
        
		return forward;
	}
	
	/**
	 * 湖北积分兑换电子券，手工输入积分数
	 * @return
	 * 
	 */
	public String inputScore()
	{
        if(StringUtils.isEmpty(score_value))
        {
            errormessage = "最少兑换积分没有设置请先设置最少兑换积分!";
            this.createRecLog(curMenuId, "0", "0", "1", errormessage);
            
            return "error";
        }
        
		return "inputScore";
	}
	/**
	* 积分专区一级菜单初始化
	* 〈功能详细描述〉
	* @depreced
	* @remark create yKF73963 2013-09-09   OR_HUB_201304_824  自助终端-积分专区 
	*/
   public String initSocoreSpecial(){
       
       return "initScoreSpecial";
   }
   /**
    * 判断用户能抽那些奖
    * 〈功能详细描述〉
    * @param  用户积分 score
    * @param  金奖区，银奖区，铜奖区
    * @return HashMap<String,String>
    * @exception/throws [违例类型] [违例说明]
    * @see [类、类#方法、类#成员]
    * @depreced
    * @remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
    */
 public String canRewardList(){
       //查询用户可以抽奖的区域
     RewardAction rwAction=new RewardAction();
  
     NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
     rwAction.setRegion(customerSimp.getRegionID());
     rwAction.setStep(customerSimp.getSubscore());
     canRewardList=scoreExECoupService.qryAllRewardLevel(rwAction);
  
     return "canRewardList";
   }
 /**
  * 根据特定抽奖区展示可以抽奖的活动
  * 〈功能详细描述〉
  * @param  用户积分 score
  * @param  金奖区，银奖区，铜奖区
  * @return HashMap<String,String>
  * @exception/throws [违例类型] [违例说明]
  * @see [类、类#方法、类#成员]
  * @depreced
  * @remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
  */
 @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
 public String ShowActiveList(){
     //查询用户可以抽奖的区域

   NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
   RewardAction rwAction=new RewardAction();
   rwAction.setActid(dictId);
   rwAction.setRegion(customerSimp.getRegionID());
   rwAction.setStep(customerSimp.getSubscore());
 canActiveList=scoreExECoupService.qryActiveListByLevel(rwAction);

   return "ShowActiveList";
 }

	public String getCurMenuId() {
		return curMenuId;
	}
	
	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage() 
	{
		return errormessage;
	}

	public void setErrormessage(String errormessage) 
	{
		this.errormessage = errormessage;
	}

	public String getPrefRewardId() 
	{
		return prefRewardId;
	}

	public void setPrefRewardId(String prefRewardId)
	{
		this.prefRewardId = prefRewardId;
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

	public String getExchangeScore() 
	{
		return exchangeScore;
	}

	public void setExchangeScore(String exchangeScore) 
	{
		this. exchangeScore = exchangeScore;
	}

	public String getRecoid() 
	{
		return recoid;
	}

	public void setRecoid(String recoid) 
	{
		this.recoid = recoid;
	}

	public String getSuccessMessage() 
	{
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) 
	{
		this.successMessage = successMessage;
	}

	public String getUsableScore() 
	{
		return usableScore;
	}

	public void setUsableScore(String usableScore) 
	{
		this.usableScore = usableScore;
	}

	public ScoreExECoupBean getQryScoreExCoupBean() 
	{
		return qryScoreExCoupBean;
	}

	public void setQryScoreExCoupBean(ScoreExECoupBean qryScoreExCoupBean) 
	{
		this.qryScoreExCoupBean = qryScoreExCoupBean;
	}

    public String getScore_value()
    {
        return score_value;
    }

    public void setScore_value(String score_value)
    {
        this.score_value = score_value;
    }

    public String getScore_title()
    {
        return score_title;
    }

    public void setScore_title(String score_title)
    {
        this.score_title = score_title;
    }

    public String getScoreWaring()
    {
        return scoreWaring;
    }

    public void setScoreWaring(String scoreWaring)
    {
        this.scoreWaring = scoreWaring;
    }

    public ScoreExECoupService getScoreExECoupService()
    {
        return scoreExECoupService;
    }

    public void setScoreExECoupService(ScoreExECoupService scoreExECoupService)
    {
        this.scoreExECoupService = scoreExECoupService;
    }

    public List<ScorePojo> getScoreList()
    {
        return scoreList;
    }

    public void setScoreList(List<ScorePojo> scoreList)
    {
        this.scoreList = scoreList;
    }

    public String getChangeType()
    {
        return changeType;
    }

    public void setChangeType(String changeType)
    {
        this.changeType = changeType;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getDictId()
    {
        return dictId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setDictId(String dictId)
    {
        this.dictId = dictId;
    }

    public List<RewardAction> getCanRewardList()
    {
        return canRewardList;
    }

    public void setCanRewardList(List<RewardAction> canRewardList)
    {
        this.canRewardList = canRewardList;
    }

    public List<RewardAction> getCanActiveList()
    {
        return canActiveList;
    }

    public void setCanActiveList(List<RewardAction> canActiveList)
    {
        this.canActiveList = canActiveList;
    }
    
}