package com.gmcc.boss.selfsvc.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.SelfSvcCall;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.dao.SelfSvcDaoImpl;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.common.model.UserSatfyVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 公用serviceImpl
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 10, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SelfSvcServiceImpl implements SelfSvcService
{
    private SelfSvcDaoImpl selfSvcDaoImpl;
    
    /**
     * 业务日志
     * <功能详细描述>
     * @param selfSvcLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createRecLog(SelfSvcLogVO selfSvcLogVO)
    {
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * 通过groupid获取字典表数据
     * @param groupid
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid)
    {
    	return selfSvcDaoImpl.getDictItemByGrp(groupid);
    }
    
    /**
     * 将用户满意度调查情况存入数据库
     * @param userSatfyVO：从jsp页面传入的用户满意度调查的属性及值
     * @see
     * @return 无
     * @remark create m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
     */
    public void insertUserSatfy(UserSatfyVO userSatfyVO )
    {
    	selfSvcDaoImpl.insertUserSatfy(userSatfyVO);
    }
    
    public void setSelfSvcDaoImpl(SelfSvcDaoImpl selfSvcDaoImpl)
    {
        this.selfSvcDaoImpl = selfSvcDaoImpl;
    }

    /**
     * 查询一段时间内想一个手机号发送的短信数量
     * <功能详细描述>
     * @param recordSmsCodePO
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
     */
    @Override
    public int qrySmsCodeNum(RecordSmsCodePO recordSmsCodePO)
    {
        return selfSvcDaoImpl.qrySmsCodeNum(recordSmsCodePO);
    }

    /**
     * 短信下发成功记录到SH_SMS_HISTORY表中
     * <功能详细描述>
     * @param recordSmsCodePO
     * @see [类、类#方法、类#成员]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
     */
    @Override
    public void insertSmsCode(RecordSmsCodePO recordSmsCodePO)
    {
        selfSvcDaoImpl.insertSmsCode(recordSmsCodePO);
    }

}
