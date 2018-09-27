package com.gmcc.boss.selfsvc.common.service;

import java.util.List;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.common.model.UserSatfyVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 
 * 公共服类service
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 10, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SelfSvcService
{
    /**
     * 业务日志
     * <功能详细描述>
     * @param selfSvcLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createRecLog(SelfSvcLogVO selfSvcLogVO);
    
    /**
     * 通过groupid获取字典表数据
     * @param groupid
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid);
    
    /**
     * 将用户满意度调查情况存入数据库
     * @param userSatfyVO：从jsp页面传入的用户满意度调查的属性及值
     * @see
     * @return 无
     * @remark create m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
     */
    public void insertUserSatfy(UserSatfyVO userSatfyVO );
    
	/**
     * 查询一段时间内像一个手机号发送的短信数量
     * <功能详细描述>
     * @param recordSmsCodePO
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
     */
    public int qrySmsCodeNum (RecordSmsCodePO recordSmsCodePO);
    
    /**
     * 短信下发成功记录到SH_SMS_HISTORY表中
     * <功能详细描述>
     * @param recordSmsCodePO
     * @see [类、类#方法、类#成员]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
     */
    public void insertSmsCode(RecordSmsCodePO recordSmsCodePO);
    
}
