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
 * ����serviceImpl
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Dec 10, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SelfSvcServiceImpl implements SelfSvcService
{
    private SelfSvcDaoImpl selfSvcDaoImpl;
    
    /**
     * ҵ����־
     * <������ϸ����>
     * @param selfSvcLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void createRecLog(SelfSvcLogVO selfSvcLogVO)
    {
        selfSvcDaoImpl.createRecLog(selfSvcLogVO);
    }
    
    /**
     * ͨ��groupid��ȡ�ֵ������
     * @param groupid
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid)
    {
    	return selfSvcDaoImpl.getDictItemByGrp(groupid);
    }
    
    /**
     * ���û�����ȵ�������������ݿ�
     * @param userSatfyVO����jspҳ�洫����û�����ȵ�������Լ�ֵ
     * @see
     * @return ��
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
     * ��ѯһ��ʱ������һ���ֻ��ŷ��͵Ķ�������
     * <������ϸ����>
     * @param recordSmsCodePO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
     */
    @Override
    public int qrySmsCodeNum(RecordSmsCodePO recordSmsCodePO)
    {
        return selfSvcDaoImpl.qrySmsCodeNum(recordSmsCodePO);
    }

    /**
     * �����·��ɹ���¼��SH_SMS_HISTORY����
     * <������ϸ����>
     * @param recordSmsCodePO
     * @see [�ࡢ��#��������#��Ա]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
     */
    @Override
    public void insertSmsCode(RecordSmsCodePO recordSmsCodePO)
    {
        selfSvcDaoImpl.insertSmsCode(recordSmsCodePO);
    }

}