/*
 * �� �� ��:  HotAPPDownloadServiceImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����APPӦ������serviceʵ����>
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  Jun 26, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.hotAPPDownload.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.customize.sd.selfsvc.hotAPPDownload.dao.HotAPPDownloadDaoImpl;
import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

/**
 * <����APPӦ������serviceʵ����>
 * <������ϸ����>
 * 
 * @author  jWX216858
 * @version  [�汾��, Jun 26, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾 OR_SD_201506_152_ɽ��_�������ն����ӡ�����APPӦ�á�����]
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class HotAPPDownloadServiceImpl extends BaseServiceSDImpl implements IHotAPPDownloadService 
{
	/**
     * ��־
     */
    private static Log logger = LogFactory.getLog(HotAPPDownloadServiceImpl.class);
	
	@Autowired
	private HotAPPDownloadDaoImpl appDownloadDao;
	
	/**
     * <����appӦ��id��ȡapp����>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
	public APPInfoPO getAppInfoById(String appId) 
	{
		return appDownloadDao.getAppInfoById(appId);
	}

	/**
     * <��ȡ���е�appӦ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
	public List<List<APPInfoPO>> getAppInfoList() 
	{
		List<APPInfoPO> appInfoList = appDownloadDao.getAppInfoList();
		// û�в�ѯ��App��Ϣ��ת������ҳ��
		if (null == appInfoList || appInfoList.size() == 0)
		{
			throw new ReceptionException("û�в�ѯ����Ӧ��APPӦ����Ϣ��");
		}
		
		String lastApptype = "";
        String nextApptype = "";
        String currApptype = "";
        APPInfoPO appInfo = null;
        List<List<APPInfoPO>> appInfoLists = new ArrayList<List<APPInfoPO>>();
        List<APPInfoPO> subAppInfoList = null;
        for (int i = 0; i < appInfoList.size(); i++)
        {
        	appInfo = appInfoList.get(i);
        	nextApptype = appInfo.getAppType();
        	if (!currApptype.equalsIgnoreCase(nextApptype))
            {
                if (subAppInfoList != null && subAppInfoList.size() > 0)
                {
                	appInfoLists.add(subAppInfoList);
                }
                
                currApptype = nextApptype;
            }
        	if (!lastApptype.equalsIgnoreCase(currApptype))
        	{
        		lastApptype = currApptype;
        		subAppInfoList = new ArrayList<APPInfoPO>();
        	}
        	subAppInfoList.add(appInfo);
        }
        if (null != subAppInfoList && subAppInfoList.size() > 0)
        {
        	appInfoLists.add(subAppInfoList);
        }
        return appInfoLists;
	}

	/**
     * <���ŷ���app���ص�ַ>
     * <������ϸ����>
     * @param menuId ��ǰ�˵�
	 * @param servNumber �ֻ�����
	 * @param appInfo app��Ϣ
	 * @throws UnsupportedEncodingException 
     * @see [�ࡢ��#��������#��Ա]
     */
	@Override
	public void sendAddress(String menuId, String servNumber, APPInfoPO appInfo) 
	{
		// ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomer();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = getTermInfo();
		
        // �ֻ�����
        String telnum = (null == customer) ? servNumber : customer.getServNumber();
        
        // �˵�id
        menuId = (null == menuId) ? "" : menuId;
        
		// ��װ����ͷ��Ϣ
        MsgHeaderPO header = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
		
		Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        // �ֻ�����
        DocumentUtil.addSubElementToEle(eBody, "telnum", telnum);

        // ����ģ����
        DocumentUtil.addSubElementToEle(eBody, "templateno", "Atsv0001");
        
        // Ӧ������
        String appName = "";
        try 
        {
        	appName = java.net.URLDecoder.decode(appInfo.getAppName(), "UTF-8");
		} 
        catch (UnsupportedEncodingException e)
        {
        	insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, "app�����·�ʧ�ܣ������ԣ�");
			throw new ReceptionException("app�����·�ʧ�ܣ������ԣ�");
		}
        
        // ���Ͷ�������
        String smsparam = CommonUtil.getParamValue(Constants.SH_APPDOWNLOAD_CONTENT);
        smsparam = "1" + smsparam.replace("[%1]", appName).replace("[%2]", appInfo.getShortAddress()); 
        
        // �����б�
        DocumentUtil.addSubElementToEle(eBody, "smsparam", smsparam);
        
        // �Ƿ��������,1:�ǣ�0����
        DocumentUtil.addSubElementToEle(eBody, "isrvcall", "1");
        
        // ���ŷ���app���ص�ַ
        ReturnWrap rw = selfSvcCall.sendAddress(header, doc);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	String msg = ("".equals(rw.getReturnMsg())) ? "app�������ӷ��Ͷ���ʧ��" : rw.getReturnMsg();
            insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, msg);
            throw new ReceptionException(msg);
        }
        
        // ����ɹ������ҵ����־
        insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_SUCCESS, "app�������ӷ��ͳɹ�!");
	}
	
	/**
	 * <�û���¼����¼�ɹ�����ŷ���app���ص�ַ>
     * <������ϸ����>
	 * @param menuId ��ǰ�˵�
	 * @param servNumber �ֻ�����
	 * @param appInfo app��Ϣ
	 * @param password ��������
	 */
	@SuppressWarnings("unchecked")
	public void customerLogin(String menuId, String servNumber, APPInfoPO appInfo, String password) 
	{
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = getTermInfo();
		
		try 
		{
			// ��������м���
			DESedeEncrypt encrypt = new DESedeEncrypt();
			password = encrypt.encrypt(null == password ? "" : password);
		} 
		catch (Exception e)
		{
			logger.error("ɽ��������֤ʱ�������������!", e);
		}
        
        // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 begin
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        
        // 0������У�� 1�������޸� 2���������ã���У��oldpwd
        paramMap.put("subcmdid", "0");
        paramMap.put("oldpwd", password);
        paramMap.put("menuID", menuId);
        paramMap.put("touchOID", "");
        paramMap.put("newpwd", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.resetPassword(paramMap);
        // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 end
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	String msg = ("".equals(rw.getReturnMsg())) ? "�û���¼��֤ʧ�ܣ�������" :rw.getReturnMsg();
            insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, msg);
            throw new ReceptionException(msg);
        }
        
		this.sendAddress(menuId, servNumber, appInfo);
	}
}
















