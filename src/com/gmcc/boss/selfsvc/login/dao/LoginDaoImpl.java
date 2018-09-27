/*
 * 文件名：LoginDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：维护用户登录时的错误记录，对服务密码、随机密码的输入错误次数进行限制
 * 修改人：g00140516
 * 修改时间：2010-12-20
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.dao;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.login.model.BlackListPO;
import com.gmcc.boss.selfsvc.login.model.LoginCheckPO;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;

/**
 * 
 * 维护用户登录时的错误记录，对服务密码、随机密码的输入错误次数进行限制
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-20
 * @see
 * @since
 */
public class LoginDaoImpl extends BaseDaoImpl
{
    /**
     * 根据手机号码、认证方式查询错误登录记录
     * 
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @return
     * @see
     */
    public LoginErrorPO qryErrorRecords(String servNumber, String authType)
    {
        LoginErrorPO obj = new LoginErrorPO();
        obj.setServNumber(servNumber);
        obj.setAuthType(authType);
        
        return (LoginErrorPO)sqlMapClientTemplate.queryForObject("login.qryErrorRecords", obj);
    }
    
    /**
     * 登录失败时，记录错误记录
     * 
     * @param loginErrorPO
     * @see
     */
    public void insertErrorRecords(LoginErrorPO loginErrorPO)
    {
        sqlMapClientTemplate.insert("login.insertErrorRecords", loginErrorPO);
    }
    
    /**
     * 用户认证成功后，根据手机号码、认证方式删除之前的错误登录记录
     * 
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @see
     */
    public void deleteErrorRecords(String servNumber, String authType)
    {
        LoginErrorPO obj = new LoginErrorPO();
        obj.setServNumber(servNumber);
        obj.setAuthType(authType);
        
        sqlMapClientTemplate.delete("login.deleteErrorRecords", obj);
    }
    
    /**
     * 重置错误登录记录，将错误次数重置为1，修改登录时间
     * 
     * @param loginErrorPO
     * @see
     */
    public void resetErrorRecords(LoginErrorPO loginErrorPO)
    {
        sqlMapClientTemplate.update("login.resetErrorRecords", loginErrorPO);
    }
    
    /**
     * 更新错误登录记录
     * 
     * @param loginErrorPO
     * @see
     */
    public void updateErrorRecords(LoginErrorPO loginErrorPO)
    {
        sqlMapClientTemplate.update("login.updateErrorRecords", loginErrorPO);
    }
    /**
     * 
     * 根据手机号查询黑名单
     * @create zKF77391
     * @param servNumber 
     * @return
     */
    public BlackListPO qryBlackListByServNumber(String servNumber)
    {
        BlackListPO blackListPO = new BlackListPO();
        blackListPO.setServNumber(servNumber);
        return (BlackListPO)sqlMapClientTemplate.queryForObject("login.qryBlackListByServNumber", blackListPO);
    }
    
    /**
     * 根据手机号码查询登录信息
     * 
     * @param servNumber 手机号码
     * @return
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public LoginCheckPO qryLoginCheckByServNum(String servNum)
    {
        return (LoginCheckPO)sqlMapClientTemplate.queryForObject("login.qryLoginCheckByServNum", servNum);
    }
    
    /**
     * 查询记录时间与当前时间的间隔
     * 
     * @param date 记录时间
     * @return
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public String getDateInterval(String date)
    {
        return (String) sqlMapClientTemplate.queryForObject("login.getDateInterval", date);
    }
    
    /**
     * 插入手机号登录记录
     * 
     * @param loginCheckPO 手机号登录PO
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void insertLoginCheck(LoginCheckPO loginCheckPO)
    {
        sqlMapClientTemplate.insert("login.insertLoginCheck", loginCheckPO);
    }
    
    /**
     * 删除手机号对应的记录
     * 
     * @param servNum 手机号
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void deleteLoginCheckByServNum(String servNum)
    {
        sqlMapClientTemplate.delete("login.deleteLoginCheckByServNum", servNum);
    }
    
    /**
     * 删除终端对应记录
     * 
     * @param termId
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void deleteLoginCheckByTermId(String termId)
    {
        sqlMapClientTemplate.delete("login.deleteLoginCheckByTermId", termId);
    }
    
    /**
     * 根据termId更新终端信息
     * 
     * @param termId 终端编号
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void updateLoginCheckByTermId(String termId)
    {
        sqlMapClientTemplate.update("login.updateLoginCheckByTermId", termId);
    }
    
    /**
     * 根据参数Id查询参数值
     * @param paramId
     * @return
     * @remark create by hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public String qryParamValueById(String paramId)
    {
        return (String) sqlMapClientTemplate.queryForObject("login.qryParamValueById", paramId);
    }
    
}
