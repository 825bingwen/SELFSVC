package com.gmcc.boss.selfsvc.common.service;

import java.util.List;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.common.model.SelfSvcLogVO;
import com.gmcc.boss.selfsvc.common.model.UserSatfyVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 
 * ��������service
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Dec 10, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface SelfSvcService
{
    /**
     * ҵ����־
     * <������ϸ����>
     * @param selfSvcLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void createRecLog(SelfSvcLogVO selfSvcLogVO);
    
    /**
     * ͨ��groupid��ȡ�ֵ������
     * @param groupid
     * @return
     */
    public List<DictItemPO> getDictItemByGrp(String groupid);
    
    /**
     * ���û�����ȵ�������������ݿ�
     * @param userSatfyVO����jspҳ�洫����û�����ȵ�������Լ�ֵ
     * @see
     * @return ��
     * @remark create m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032
     */
    public void insertUserSatfy(UserSatfyVO userSatfyVO );
    
	/**
     * ��ѯһ��ʱ������һ���ֻ��ŷ��͵Ķ�������
     * <������ϸ����>
     * @param recordSmsCodePO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
     */
    public int qrySmsCodeNum (RecordSmsCodePO recordSmsCodePO);
    
    /**
     * �����·��ɹ���¼��SH_SMS_HISTORY����
     * <������ϸ����>
     * @param recordSmsCodePO
     * @see [�ࡢ��#��������#��Ա]
     * @remark create lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_�����ն˽ɷѻ���ȫ��������
     */
    public void insertSmsCode(RecordSmsCodePO recordSmsCodePO);
    
}