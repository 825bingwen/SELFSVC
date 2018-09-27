package com.gmcc.boss.selfsvc.common;

public interface SSReturnCode
{
    /** 成功 */
    int SUCCESS = 1;
    
     int SUCCESS_CODE = 1;
    
    int SUCCESS_CODE_100 = 100;
    
    /** 失败 */
    int ERROR = 0;
    
    int LOST = 0;
    
    /** webservice返回成功代码 */
    String WEBSUCCESS = "100";
    
    /** webservice返回失败代码 */
    String WEBLOST = "1111";
    
    String WEBERROR = "";
    
    /** 取消业务 */
    String DEL_REC = "0";
    
    /** 新增业务 */
    String ADD_REC = "1";
    
    /** 查询业务 */
    String QRY_REC = "2";
    
    // added by g00140516 on 2010-05-21 begin
    /** 取消业务2 */
    String DEL2_REC = "3";
    
    /** 取消业务2 */
    String WS_DEL2_REC = "DEL2";
    
    // added by g00140516 on 2010-05-21 end
    
    /** 取消业务 */
    String WS_DEL_REC = "DEL";
    
    /** 新增业务 */
    String WS_ADD_REC = "ADD";
    
    /** 查询业务 */
    String WS_QRY_REC = "QRY";
    
    /** 开机 */
    String OPENSUBS = "OpenSubs";
    
    /** 停机 */
    String STOPSUBS = "StopSubs";
    
    /**
     * 一级boss接口调用成功返回码
     */
    String INTERBOSS_SUCCESS = "0000";
    
}
