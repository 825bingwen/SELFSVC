/*
 * 文件名：LoginServiceImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：维护用户登录时的错误记录，对服务密码、随机密码的输入错误次数进行限制
 * 修改人：g00140516
 * 修改时间：2010-12-20
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.dao.LoginDaoImpl;
import com.gmcc.boss.selfsvc.login.model.BlackListPO;
import com.gmcc.boss.selfsvc.login.model.LoginCheckPO;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

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
public class LoginServiceImpl implements LoginService
{
    private LoginDaoImpl loginDaoImpl = null;
    
    public LoginDaoImpl getLoginDaoImpl()
    {
        return loginDaoImpl;
    }
    
    public void setLoginDaoImpl(LoginDaoImpl loginDaoImpl)
    {
        this.loginDaoImpl = loginDaoImpl;
    }
    
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
        return loginDaoImpl.qryErrorRecords(servNumber, authType);
    }
    
    /**
     * 更新错误登录记录
     * 
     * @param loginErrorPO
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @see
     */
    public void updateErrorRecords(LoginErrorPO loginErrorPO, String servNumber, String authType)
    {
        // 第一次登录失败
        if (loginErrorPO == null)
        {
            // insert错误记录
            loginErrorPO = new LoginErrorPO();
            loginErrorPO.setServNumber(servNumber);
            loginErrorPO.setAuthType(authType);
            loginErrorPO.setErrorTimes(1);
            
            loginDaoImpl.insertErrorRecords(loginErrorPO);
        }
        // 之前有过登录失败的记录
        else
        {
            int maxTimes = Integer.parseInt((String)PublicCache.getInstance()
                    .getCachedData(Constants.SH_LOGIN_MAXTIMES));
            
            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
            // 错误记录最后登录时间
            String lastLoginTime = loginErrorPO.getLastTime();
            
            // 号码锁定时间(分钟)
            String validTimeMin = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
          
            // 号码锁定时间(毫秒)
            long validTimeCompare = 1000L * 60 * Integer.parseInt(validTimeMin);
            
            // 超过了系统限制，但是仍然可以登录，只有一种可能，就是之前的错误记录已经失效，此时，需要重置错误记录
            if (((CommonUtil.compareCurrTime(lastLoginTime)) >= validTimeCompare)  && loginErrorPO.getErrorTimes() >= maxTimes)
            {
                loginDaoImpl.resetErrorRecords(loginErrorPO);
            }
            // modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
            
            // 未超过系统限制，有两种可能，一种是之前的错误记录仍然有效，一种是之前的错误记录失效。失效时，重置错误记录；未失效，更新错误记录
            else
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                // 第一次错误的时间
                String firstTime = loginErrorPO.getFirstTime();
                
                try
                {
                    // 错误记录有效时长
                    String validTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_TIMESCOPE);
                    
                    Calendar c = Calendar.getInstance();
                    
                    // 当前时间
                    String currTime = sdf.format(c.getTime());
                    
                    // 解锁时间
                    c.setTime(sdf.parse(firstTime));
                    c.add(Calendar.MINUTE, Integer.parseInt(validTime));
                    
                    String timeoutTime = sdf.format(c.getTime());
                    
                    if (timeoutTime.compareTo(currTime) > 0)
                    {
                        loginDaoImpl.updateErrorRecords(loginErrorPO);
                    }
                    else
                    {
                        loginDaoImpl.resetErrorRecords(loginErrorPO);
                    }
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        }
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
        loginDaoImpl.deleteErrorRecords(servNumber, authType);
    }

    /**
     * 
     * 根据手机号去查询该用户是否在黑名单中
     * @create zKF77391
     * @param servNumber 手机号码
     */
    public BlackListPO qryBlackListByServNumber(String servNumber)
    {
        return loginDaoImpl.qryBlackListByServNumber(servNumber);
    }
    /**
     * 
     * 根据手机号去查询该用户是否在黑名单中
     * @create zKF77391
     * @param servNumber
     * @return
     */
    public boolean checkBlackListByServNumber(String servNumber)
    {
        BlackListPO blackListPO = loginDaoImpl.qryBlackListByServNumber(servNumber);
        if (blackListPO == null)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    /**
     * 同一号码同一渠道多次登录验证
     * 
     * @param servNumber 手机号码
     * @return
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public boolean checkLoginByServNumber(String servNumber, String termId)
    {
        LoginCheckPO loginCheckPO = loginDaoImpl.qryLoginCheckByServNum(servNumber);
        
        // 如果该手机号码在登录表中有记录
        if(null != loginCheckPO)
        {
            // 同一机器多次办理业务
            if(termId.equals(loginCheckPO.getTermId()))
            {
                loginDaoImpl.updateLoginCheckByTermId(termId);
            }
            // 其他机器登录该手机号
            else
            {
                // 查询记录与当前时间的间隔
                double timeInterval = Double.valueOf(loginDaoImpl.getDateInterval(loginCheckPO.getStatusDate()));
                
                // 如果间隔时间小于间隔时间
                if(timeInterval < Double.valueOf((String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_MAX_TIME_INTERVAL")))
                {
                    return false;
                }
                // 间隔时间大于最大间隔时间
                else
                {
                    // 删除手机号在登录表中信息
                    loginDaoImpl.deleteLoginCheckByServNum(servNumber);
                    
                    // 更改终端ID
                    loginCheckPO.setTermId(termId);
                    
                    // 插入新记录
                    loginDaoImpl.insertLoginCheck(loginCheckPO);
                }
            }
             
        }
        // 不存在记录，插入登录表记录
        else
        {
            LoginCheckPO loginCheckPO2 = new LoginCheckPO();
            loginCheckPO2.setServNumber(servNumber);
            loginCheckPO2.setTermId(termId);
            
            // 插入新记录
            loginDaoImpl.insertLoginCheck(loginCheckPO2);
        }
        
        return true;
    }
    
    /**
     * 根据终端ID删除登录信息
     * 
     * @param termId 终端ID
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void deleteLoginCheckByTermId(String termId)
    {
        
        loginDaoImpl.deleteLoginCheckByTermId(termId);
    }
    
    /**
     * 根据参数Id查询参数值
     * @param paramId
     * @return
     * @remark create begin hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public String qryParamValueById(String paramId)
    {
        return loginDaoImpl.qryParamValueById(paramId);
    }
}
