/*
 * 文 件 名:  QryUserScoreInfoByTypeAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  根据类型查询积分信息
 * 修 改 人:  zWX176560
 * 修改时间:  Aug 23, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.nx.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.customize.nx.selfsvc.bean.QryUserScoreInfoByTypeBean;
import com.customize.nx.selfsvc.serviceinfo.model.UserScorePO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 根据类型查询用户积分
 * 
 * @author zWX176560
 * @version R003C13L09n01 OR_NX_201308_595_宁夏_新开发积分查询接口
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QryUserScoreInfoByTypeAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(QryUserScoreInfoByTypeAction.class);
    
    /**
     * 根据类型查询积分接口
     */
    private QryUserScoreInfoByTypeBean qryUserScoreInfoByTypeBean;
    
    /**
     * 用户积分类型集合
     */
    private List<UserScorePO> userScoreTypeList;
    
    /**
     * 当前菜单编码
     */
    private String curMenuId = "";
    
    /**
     * 用户积分PO
     */
    private UserScorePO userScorePO = new UserScorePO();
    
    /**
     * 查询积分类型 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryUserScoreType()
    {
        // 查询日志开始
        logger.debug("qryUserScoreType Starting...");
        
        // 根据groupId 获取积分类型列表
        List<DictItemPO> list = this.getDictItemByGrp(Constants.SCORE_TYPE);
        userScoreTypeList = new ArrayList<UserScorePO>();
        
        for (int i = 0; i < list.size(); i++)
        {
            DictItemPO dictItemPO = list.get(i);
            UserScorePO userScorePO = new UserScorePO();
            userScorePO.setScoreType(dictItemPO.getDictname());
            userScorePO.setScoreId(dictItemPO.getDictid());
            userScoreTypeList.add(userScorePO);
        }
        
        // 创建成功日志
        this.createRecLog(Constants.QRYUSERSCOREINFOBYTYPE, "0", "0", "0", "根据类型查询积分查询成功!");
        
        // 日志结束
        logger.debug("qryUserScoreType Starting...");
        return "scoreTypePage";
    }
    
    /**
     * 根据类型查询积分信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryUserScoreInfoByType()
    {
        // 查询日志开始
        logger.debug("qryUserScoreInfoByType start...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用接口查询积分信息
        Map result = qryUserScoreInfoByTypeBean.qryUserScoreInfoByType(terminalInfoPO, customer, curMenuId);
        
        if (result != null && result.size() == 2)
        {
            // 获取相应的积分结果
            Map scoreMap = (Map)result.get("returnObj");
            String scoreId = userScorePO.getScoreId();
            String scoreValue = (String)scoreMap.get(scoreId);
            
            // 判断相应积分类型是否存在
            if (null == scoreValue)
            {
                userScorePO.setScoreValue("0");
            }
            else
            {
                userScorePO.setScoreValue(scoreValue);
            }
            
            // 创建成功日志
            this.createRecLog(Constants.QRYUSERSCOREINFOBYTYPE, "0", "0", "0", "根据类型查询积分查询成功!");
        }
        else
        {   
            // 创建错误日志
            this.createRecLog(Constants.QRYUSERSCOREINFOBYTYPE, "0", "0", "1", "根据类型查询积分查询失败!");
            
            getRequest().setAttribute("errormessage", "查询积分失败！");
            
            return "error";
        }
        
        // 查询日志结束
        logger.debug("qryUserScoreInfoByType end...");
        
        return "userScoreInfoPage";
    }
    
    public List<UserScorePO> getUserScoreTypeList()
    {
        return userScoreTypeList;
    }
    
    public void setUserScoreTypeList(List<UserScorePO> userScoreTypeList)
    {
        this.userScoreTypeList = userScoreTypeList;
    }
    
    public QryUserScoreInfoByTypeBean getQryUserScoreInfoByTypeBean()
    {
        return qryUserScoreInfoByTypeBean;
    }
    
    public void setQryUserScoreInfoByTypeBean(QryUserScoreInfoByTypeBean qryUserScoreInfoByTypeBean)
    {
        this.qryUserScoreInfoByTypeBean = qryUserScoreInfoByTypeBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public UserScorePO getUserScorePO()
    {
        return userScorePO;
    }
    
    public void setUserScorePO(UserScorePO userScorePO)
    {
        this.userScorePO = userScorePO;
    }
    
}
