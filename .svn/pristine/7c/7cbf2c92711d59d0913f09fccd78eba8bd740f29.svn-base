/*
 * 文件名：LoginService.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：维护用户登录时的错误记录，对服务密码、随机密码的输入错误次数进行限制
 * 修改人：g00140516
 * 修改时间：2010-12-20
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.service;

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
public interface LoginService
{
    /**
     * 根据手机号码、认证方式查询错误登录记录
     * 
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @return
     * @see
     */
    public LoginErrorPO qryErrorRecords(String servNumber, String authType);
    
    /**
     * 更新错误登录记录
     * 
     * @param loginErrorPO
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @see
     */
    public void updateErrorRecords(LoginErrorPO loginErrorPO, String servNumber, String authType);
    
    /**
     * 用户认证成功后，根据手机号码、认证方式删除之前的错误登录记录
     * 
     * @param servNumber，手机号码
     * @param authType，认证方式
     * @see
     */
    public void deleteErrorRecords(String servNumber, String authType);
    /**
     * 
     * 根据手机号去查询该用户是否在黑名单中
     * @create zKF77391
     * @param servNumber 手机号码
     * @return true 在 <br >false 不在
     */
    public boolean checkBlackListByServNumber(String servNumber);
    
    /**
     * 同一号码同一渠道多次登录验证
     * 
     * @param servNumber 手机号码
     * @param termId 终端编号
     * @return
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public boolean checkLoginByServNumber(String servNumber, String termId);
    
    /**
     * 根据终端ID删除登录信息
     * 
     * @param termId 终端ID
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void deleteLoginCheckByTermId(String termId);
    
    /**
     * 根据参数Id查询参数值
     * @param paramId
     * @return
     * @remark create begin hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public String qryParamValueById(String paramId);
    
}
