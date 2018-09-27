package com.customize.sd.selfsvc.chooseTel.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.bean.ChooseTelBean;
import com.customize.sd.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.sd.selfsvc.chooseTel.service.ChooseTelService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ԤԼѡ��
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Apr 19, 2011]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ChooseTelAction extends PagedAction
{
    
    private static Log logger = LogFactory.getLog(ChooseTelAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    // ������Ϣ
    private String error;
    
    // ����б����
    private String[] servicetitle;
    
    // ��ѯ���
    private List result;
    
    // �������
    private String region;
    
    // ��������
    private String regionname;
    
    // ��λ
    private String orgid;
    
    // ��ѯ����
    private String sele_rule;
    
    // ����ǰ׺
    private String tel_prefix;
    
    // �����׺
    private String tel_suffix;
    
    // ��λ
    private String org_id;
    
    // ֤������
    private String certtype;
    
    // ֤������
    private String certid;
    
    // �ͻ�����
    private String name;
    
    // ��ϵ��ʽ
    private String contacttel;
    
    // ԤԼ�ֻ�����
    private String telnum;
    
    // �ն���Ϣ
    private String termname;
    
    // ��ӡ�ص�
    private String addr;
    
    private String orderID;
    
    //add begin cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ
    // ��ܰ��ʾ��Ϣ
    private String additionalInfo;
    //add end cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ
    
    // ���ȫ���ֻ���������
    private List<ServerNumPO> tmpList = null;
    
    // ÿҳ�����б�
    private List<ServerNumPO> serverNumList = null;
    
    // ���ýӿ�bean
    private ChooseTelBean chooseTelBean;
    
    // �������ݿ�Service
    private ChooseTelService chooseTelService;
    
    /**
     * ��ҳʱÿҳ��ʾ15����¼
     */
    private final int pageSize = 15;
    
    /**
     * ��ҳ��ʶ��trueʱ���ҳ
     */
    private String pageFlag = "false";
    
    /**
     * ���Ž��պ���
     */
    private String sendTelNum = "";
    
    /**
     * Ԥ���
     */
    private String preFee = "";
    
    /**
     * ѡ�Žӿ��л���webservice�ӿڵĿ���
     * webservice�ӿڵĿ��� 1�������� 0���ر�
     */
    private String webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
    
    /**
     * �Զ������ʱ������
     */
    private String condition = "";
    
    /**
     * �����Ʒ���� vprodId
     * �̳ǲ����ɵ������Ʒ���룬���к���ԤԼʱ����Ҫ���˱��봫�ݸ��̳�
     */
    private String vprodId = "";
    
    /**
     * 
     */
    private String manuTelnumFlag = "";
    
    /**
     * ѡ���ѯ����
     * <������ϸ����>
     * @param request
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String selectRule()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectRule Starting...");
        }
        
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        List<RegionInfoPO> regionListTmp = (List)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        
        // ѡ�Žӿ��л���webservice�ӿڵĿ���
        webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
        
        manuTelnumFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_QRYMANUNUM_FLAG");
        
        for (RegionInfoPO regionInfoPO:regionListTmp)
        {
            if (terminalInfoPO.getRegion().equals(regionInfoPO.getRegion()))
            {
            	region = regionInfoPO.getRegion();
            	regionname = regionInfoPO.getRegionname();
            	orgid = regionInfoPO.getOrgid();
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectRule End");
        }
        
        return "selectRule";
    }
    
    /**
     * ѡ���ѯ����
     * <������ϸ����>
     * @param request
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputTelNumByRule()
    {
        logger.debug("inputTelNumByRule Starting...");
        
        webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
        
        logger.debug("inputTelNumByRule End");
        
        if("4".equals(sele_rule))
        {
        	return "manuNumber";
        }
        
        return "inputTelNumByRule";       
    }
    
    /**
     * ����webservice�ӿڲ�ѯ����
     * @return
     * @remark create by wWX217192 2015-03-12 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    public String queryNum()
    {
    	// ��ӡ��־
    	logger.debug("queryNum start...");
    	
    	String responseMsg = "";
    	
    	if (tel_prefix == null)
        {
            tel_prefix = "";
        }
        
        if (tel_suffix == null)
        {
            tel_suffix = "";
        }
        
        // ����ǰ׺
        while (tel_prefix.length() < 7)
        {
            tel_prefix = tel_prefix + "*";
        }
    	
        while (tel_suffix.length() < 4)
        {
            tel_suffix = tel_suffix + "*";
        }
        
        responseMsg = chooseTelBean.getQueryNumReq(region, preFee, tel_prefix, 
        		tel_suffix, CommonUtil.getParamValue(Constants.SH_CHOOSETEL_QUERYNUM_ROWNUM), condition);
        
        // ������Ϣ
        String errorMsg = "";
        
        try 
        {
			
        	List<ServerNumPO> list = new ArrayList<ServerNumPO>();
        	
        	tmpList = new ArrayList<ServerNumPO>();
			
			responseMsg = getResponseMsg(responseMsg);
			
			Document body = DocumentHelper.parseText(responseMsg);
    		
    		// ��ȡ���ر��ĵ�return���ڵ�
    		Element root = body.getRootElement();
    		
    		// �ɹ�����100��ʧ�ܷ��ظ�ֵ
    		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
    		{
    			// ������ѯ����webservice�ӿڵķ��ر�����Ϣ
    			list = parseQueryNumResMsg(root);
    			
    			tmpList = getLimitFee(list);
    		}
    		else
    		{
    			// ��ȡ������Ϣ
    			errorMsg = root.element("errorMsg").getText();
    			getRequest().setAttribute("errormessage", errorMsg);
    			return ERROR;
    		}
			
			this.getRequest().setAttribute("recordCount", tmpList.size());
			
			// ���÷�ҳ������ʾ
			serverNumList = this.getPageList(tmpList, pageSize);
			
			return SUCCESS;
			
		}
        catch (Exception e) 
		{
			logger.info("�����ѯʧ�ܣ�", e);
			getRequest().setAttribute("errormessage", "�����ѯʧ�ܣ�");
			return ERROR;
		}
    }
    
    // ����������Ѻ�Ԥ����ֵ
    private List<ServerNumPO> getLimitFee(List<ServerNumPO> list)
    {
    	tmpList = new ArrayList<ServerNumPO>();
    	
    	String lowFeeLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWFEE_LIMIT");
        
        String lowPreLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWPRE_LIMIT");

        ServerNumPO serverNumPO = null;
        
        for (int i = 0; i < list.size(); i++)
        {
            // չʾ������������������ֵ
            if (null != lowFeeLimit)
            {
                if (!"".equals(list.get(i).getLow_consum_fee()) && !"".equals(list.get(i).getLow_consum_pre())
                        && (Double.valueOf(list.get(i).getLow_consum_fee()) <= Double.valueOf(lowFeeLimit))
                        && (Double.valueOf(list.get(i).getLow_consum_pre()) >= Double.valueOf(lowPreLimit))
                        )
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(list.get(i).getTelnum());// �ֻ�����
                    
                    serverNumPO.setLow_consum_fee(list.get(i).getLow_consum_fee());
                    serverNumPO.setLow_consum_pre(list.get(i).getLow_consum_pre());
                    serverNumPO.setLow_inservice_time(list.get(i).getLow_inservice_time());
                    
                    tmpList.add(serverNumPO);
                }
            }
            else
            {                    
                if(!"".equals(list.get(i).getLow_consum_fee()) && !"".equals(list.get(i).getLow_consum_pre()))
                {
                	serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(list.get(i).getTelnum());// �ֻ�����
                    
                    serverNumPO.setLow_consum_fee(list.get(i).getLow_consum_fee());
                    serverNumPO.setLow_consum_pre(list.get(i).getLow_consum_pre());
                    serverNumPO.setLow_inservice_time(list.get(i).getLow_inservice_time());
                    
                    tmpList.add(serverNumPO);
                }
            }
        }
        
        return tmpList;
    }
    
    /**
     * ��ѯ��ҪԤ���ĺ���
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String telNumResult()
    {
        // ��ʼ��¼��־
        logger.debug("telNumResult start...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        getRequest().setAttribute("backStep", "-1");
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        this.linkTelNum();
        
        // ��ѯ������Ϣ�б�
        Map mapResult = this.chooseTelBean.qryChooseTel(terminalInfoPO, curMenuId, orgid, sele_rule, tel_prefix, tel_suffix, region);
        
        //δ��ѯ�����ݣ��������ҳ��
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
            String errMsg = "";
            
            if ("".equals(tel_prefix.trim()) && "".equals(tel_suffix.trim()))
            {
                errMsg = "δ��ѯ�����������ļ�¼";
            }
            else if ("2".equals(sele_rule))// ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
            {
                errMsg = "δ��ѯ�����������ļ�¼(ǰ׺��" + tel_prefix + ")";               
            }
            else if("3".equals(sele_rule))
            {
                errMsg = "δ��ѯ�����������ļ�¼(��׺��" + tel_suffix + ")";
            }
            
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
            
            return "error";
        }
        
        // ���ݴ�ŵ���ʱ�����У�Ϊ���ڴ��ҳ׼����
        CRSet crset = (CRSet) mapResult.get("returnObj");
        
        this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
        tmpList = new ArrayList<ServerNumPO>();
        ServerNumPO serverNumPO = null;  
        
        // modify begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
        // OR_SD_201301_296 ���߱�־
        String upFlag = (String)PublicCache.getInstance().getCachedData("SH_TELCHOOSE_UPFLAG");
        
        if("1".equals(upFlag))
        {
        	limitLowFee(crset);
        }
        else
        {
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                serverNumPO = new ServerNumPO();
                serverNumPO.setTelnum(crset.GetValue(i, 0));// �ֻ�����
                serverNumPO.setFee(crset.GetValue(i, 2));
                serverNumPO.setOrg_id(crset.GetValue(i, 3));
                
                //add by fKF59607 OR_SD_201203_1057 ѡ������  2012-05-10 begin
                serverNumPO.setTellevel(crset.GetValue(i, 5));
                //add by fKF59607 OR_SD_201203_1057 ѡ������  2012-05-10 end
                
                tmpList.add(serverNumPO);
            }       
        }
        
        serverNumList = this.getPageList(tmpList, pageSize);
        
        logger.debug("telNumResult End ...");
        
        String forward = "telNumResult";

        if("1".equals(upFlag))
        {
            forward = "telNumResultNew";
        }
        return forward;
    }
    
    /**
     * �������֤����ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputCertid()
    {
        // ��ʼ��¼��־
        logger.debug("inputCertid start...");

        logger.debug("inputCertid End ...");

        return "inputCertid";
    }
    
    /**
     * Ԥ������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String makeSureTel()
    {
        // ��ʼ��¼��־
        logger.debug("makeSureTel start...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // add begin hWX5316476 2014-3-1 OR_SD_201312_300 ������������ԤԼ����թ��ʩ�Ż�
        // �����ݿ��л�ȡtrunc(sysdate+4)~trunc(sysdate+5)֮���һ�����ʱ��
        String enddate = chooseTelService.qryEnddate().substring(0,10).replaceAll("-","");
        
        Random r=new Random();
        StringBuffer b = new StringBuffer();
        
        // 0(����)-24(������)֮��������
		int h = r.nextInt(24);
		
		// 0(����)-60(������)֮��������
		int m = r.nextInt(60);

		String hh = "";
		String mm = "";
		if(h < 10)
		{
			hh = "0"+h;
		}
		else
		{
			hh = ""+h;
		}
		
		if(m < 10)
		{
			mm = "0"+m;
		}
		else
		{
			mm = ""+m;
		}
		
		StringBuffer ramdomTime = b.append(hh).append(mm);
       
		// ��ȡԤԼ�����ͷ�ʱ�䣬��ʽ�ο���201401141555����ʾ2014��1��14��15��55�ֵ����ͷţ���ȷ����
        String enddateRandom = enddate.concat(ramdomTime.toString());
        // add end hWX5316476 2014-3-1 OR_SD_201312_300 ������������ԤԼ����թ��ʩ�Ż�

        // ִ��Ԥ��
        Map mapResult = this.chooseTelBean.chooseTel(terminalInfoPO, curMenuId, telnum, region, org_id, certtype, certid, name, contacttel,enddateRandom);
       
        if (mapResult != null && mapResult.size() > 1)
        {  
            CTagSet tagSet = (CTagSet) mapResult.get("returnObj");
            orderID = tagSet.GetValue("orderid");
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "0", "����Ԥ���ɹ�");
            
            //add begin cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ
            // ȡ�˵�����ܰ��ʾ��Ϣ
            List totalMenus = (List)PublicCache.getInstance().getCachedData(terminalInfoPO.getTermgrpid());
            
            MenuInfoPO menuInfoPO = null;
            for (int i = 0; i < totalMenus.size(); i++)
            {
                menuInfoPO = (MenuInfoPO)totalMenus.get(i);
                if (this.curMenuId.equals(menuInfoPO.getMenuid()))
                {
                    break;
                }
                else
                {
                    menuInfoPO = null;
                }
            }
            
            if (menuInfoPO != null)
            {
                additionalInfo = menuInfoPO.getAdditionalInfo();
            }
            //add end cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ
            return "makeSureTel";
        }
        else
        {
            String errMsg = "";
            
            if (mapResult != null)
            {
                errMsg = (String) mapResult.get("returnMsg");
            }
            
            if (errMsg == null || "".equals(errMsg.trim()))
            {
                errMsg = "����" + telnum + "Ԥ��ʧ��";
            }
            
            getRequest().setAttribute("errormessage", errMsg);
            
            getRequest().setAttribute("backStep", "-1");
            
            // ��¼������־
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
        }
        
        logger.debug("makeSureTel End ...");

        return "error";
    }
    
    
    /**
     * ת�����뷢���ֻ�����ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputSendNum()
    {
        logger.debug("inputSendNum start...");
        
        logger.debug("inputSendNum end!");
        
        return "inputSendNum";
    }
    
    /**
     * ���Ͷ��ź�ת�����ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String sendNumFinish()
    {
        logger.debug("sendNumFinish start...");
        
        // ���Ͷ���
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        String shortMessage = "�ֻ����룺"+telnum+"��Ԥ���ɹ�����Я����Ч֤�������֤��ʱǰ��Ӫҵ��������!";
        this.chooseTelBean.sendMsg(sendTelNum, terminalInfoPO, shortMessage, "");
        
        logger.debug("sendNumFinish end!");
        
        return "sendNumFinish";
    }
    
    /**
     * ��ѡ���� ���������� 
     * ԭ����Ϊ����ѯ����->ԤԼ����
     * ��Ϊ
     * ������Ϊ����ѯ����->��ѡ����->ԤԼ����
     * @return
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    public String pickNum() throws Exception
    {
    	logger.debug("pickNum start...");
    	
    	String responseMsg = chooseTelBean.getPickNumReq(telnum);
    	
    	responseMsg = getResponseMsg(responseMsg);
    	
		// �������ر�����Ϣ
		Document body = DocumentHelper.parseText(responseMsg);
		
		// ��ȡ���ر��ĵĸ��ڵ�
		Element root = body.getRootElement();
		
		// �ɹ�����100��ʧ�ܷ��ظ�ֵ
		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
		{
			// �����Ʒ����
			vprodId = root.element("vprodId").getText();
		}
		else
		{
			// ��ȡ������Ϣ
			String errorMsg = root.element("errorMsg").getText();
			
			if(errorMsg.isEmpty())
			{
				errorMsg = "����" + telnum + "��ѡʧ�ܣ�";
			}
			// ���ú�����ѡʧ�ܵķ�����Ϣ
			getRequest().setAttribute("errormessage", errorMsg);
			return ERROR;
		}
    		
    	logger.debug("pickNum end...");
    	
    	return SUCCESS;
    }
    
    /**
     * Ԥ������ ����webservice�ӿ�
     * @return
     * @throws AxisFault 
     * @throws DocumentException 
     * @remark create by wWX217192 2015-03-16 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    public String bookNum() throws Exception
    {
    	logger.debug("bookNum start...");
    	
    	// ��ȡ��������Ϣ
    	String responseMsg = chooseTelBean.getBookNumReq(name, telnum, certid, contacttel, vprodId);
    	
    	responseMsg = getResponseMsg(responseMsg);
    	
		// �������ر�����Ϣ
		Document body = DocumentHelper.parseText(responseMsg);
		
		// ��ȡ���ر��ĵĸ��ڵ�
		Element root = body.getRootElement();
		
		// returnCode Ϊ100�ɹ���Ϊ��ֵʱʧ��
		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
		{
			return SUCCESS;
		}
		else
		{
			// ��ȡ������Ϣ
			String errorMsg = root.element("errorMsg").getText();
			
			if(errorMsg.isEmpty())
			{
				errorMsg = "����" + telnum + "Ԥ��ʧ�ܣ�";
			}
			
			// ���ú�����ѡʧ�ܵķ�����Ϣ
			getRequest().setAttribute("errormessage", errorMsg);
			
			return ERROR;
		}
    	
    }
    
    /**
     * ����webservice��ѯ����ӿڵķ��ر���
     * @param resMsg
     * @return ������ĺ���List
     * @remark create by wWX217192 2015-03-12 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    private List<ServerNumPO> parseQueryNumResMsg(Element root)
    {
    	List<ServerNumPO> resultList = new ArrayList<ServerNumPO>();
    	
    	try
    	{
			ServerNumPO numPO = null;
			// �������ر����е�numList�ڵ�
			Iterator numList = root.element("numList").elementIterator("numInfo");
			
			// ����numList�ڵ��µ�����
			while(numList.hasNext())
			{
				Element eChild = (Element) numList.next();
				
				numPO = new ServerNumPO();
				
				// �������
				numPO.setLow_consum_fee(eChild.element("minCost").getText());
				
				// ����
				numPO.setTelnum(eChild.element("telnum").getText());
				
				// Ԥ���
				numPO.setLow_consum_pre(eChild.element("preFee").getText());
				
				// ǩԼʱ��
				numPO.setLow_inservice_time(eChild.element("signTime").getText());
				
				resultList.add(numPO);
			}
    	}
    	catch (Exception e) 
		{
			logger.error("���������ѯ�ӿڵķ��ر���ʧ�ܣ�", e);
		}
		
    	return resultList;
    }
    
    /**
     * ��չʾ�����������ѵ����ֵ��������
     * @param crset
     * @return
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    private List<ServerNumPO> limitLowFee(CRSet crset)
    {
    	tmpList = new ArrayList<ServerNumPO>();
    	ServerNumPO serverNumPO = null;  
    	
    	// modify begin cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_1010
        String lowFeeLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWFEE_LIMIT");
        
        String lowPreLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWPRE_LIMIT");

        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            // չʾ������������������ֵ
            if (null != lowFeeLimit)
            {
                if (!"".equals(crset.GetValue(i, 6)) && !"".equals(crset.GetValue(i, 7))
                        && (Double.valueOf(CommonUtil.fenToYuan(crset.GetValue(i, 6))) <= Double.valueOf(lowFeeLimit))
                        && (Double.valueOf(CommonUtil.fenToYuan(crset.GetValue(i, 7))) >= Double.valueOf(lowPreLimit))
                        )
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(crset.GetValue(i, 0));// �ֻ�����
                    serverNumPO.setFee(crset.GetValue(i, 2));
                    serverNumPO.setOrg_id(crset.GetValue(i, 3));
                    
                    // add by fKF59607 OR_SD_201203_1057 ѡ������ 2012-05-10 begin
                    serverNumPO.setTellevel(crset.GetValue(i, 5));
                    // add by fKF59607 OR_SD_201203_1057 ѡ������ 2012-05-10 end
                    
                    // add begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    serverNumPO.setLow_consum_fee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
                    serverNumPO.setLow_consum_pre(CommonUtil.fenToYuan(crset.GetValue(i, 7)));
                    serverNumPO.setLow_inservice_time(crset.GetValue(i, 8));
                    // add end cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    
                    tmpList.add(serverNumPO);
                }
            }
            else
            {                    
                if(!"".equals(crset.GetValue(i, 6)) && !"".equals(crset.GetValue(i, 7)))
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(crset.GetValue(i, 0));// �ֻ�����
                    serverNumPO.setFee(crset.GetValue(i, 2));
                    serverNumPO.setOrg_id(crset.GetValue(i, 3));
                    
                    // add by fKF59607 OR_SD_201203_1057 ѡ������ 2012-05-10 begin
                    serverNumPO.setTellevel(crset.GetValue(i, 5));
                    // add by fKF59607 OR_SD_201203_1057 ѡ������ 2012-05-10 end
                    
                    // add begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    serverNumPO.setLow_consum_fee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
                    serverNumPO.setLow_consum_pre(CommonUtil.fenToYuan(crset.GetValue(i, 7)));
                    serverNumPO.setLow_inservice_time(crset.GetValue(i, 8));
                    // add end cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    tmpList.add(serverNumPO);

                }
            }
        }
        
        return tmpList;
    }
    
    /**
     * ƴ�Ӻ����ǰ׺�ͺ�׺
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
     */
    private void linkTelNum()
    {
    	if (tel_prefix == null)
        {
            tel_prefix = "";
        }
        
        if (tel_suffix == null)
        {
            tel_suffix = "";
        }
        
        if ("2".equals(sele_rule))// ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
        {
            while (tel_prefix.length() < 7)
            {
                tel_prefix = tel_prefix + "_";
            }        
        }
        else if("3".equals(sele_rule))
        {
            while (tel_suffix.length() < 4)
            {
                tel_suffix = tel_suffix + "_";
            }
        }
    }
    
    // ��ȡ���ر�����Ϣ
    private String getResponseMsg(String respMsg)
    {
    	String responseMsg = "";
    	
    	// ��ǩ��౻ת��
    	if(respMsg.contains("&lt;"))
    	{
    		respMsg = respMsg.replace("&lt;", "<");
    	}
    	
    	// ��ǩ�Ҳ౻ת��
    	if(respMsg.contains("&gt;"))
    	{
    		respMsg = respMsg.replace("&gt;", ">");
    	}
    	
    	int startNum = respMsg.indexOf("<ns:return>");
    	
    	int endNum = respMsg.indexOf("</ns:return>");
    	
    	responseMsg = respMsg.substring(startNum, endNum);
    	
    	responseMsg = responseMsg.replace("<ns:return>", "");
    	
    	responseMsg = responseMsg.replace("</ns:return>", "");
    	
    	return responseMsg;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String[] getServicetitle()
    {
        return servicetitle;
    }

    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }

    public List getResult()
    {
        return result;
    }

    public void setResult(List result)
    {
        this.result = result;
    }

    public ChooseTelBean getChooseTelBean()
    {
        return chooseTelBean;
    }

    public void setChooseTelBean(ChooseTelBean chooseTelBean)
    {
        this.chooseTelBean = chooseTelBean;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegionname()
    {
        return regionname;
    }

    public void setRegionname(String regionname)
    {
        this.regionname = regionname;
    }

    public String getOrgid()
    {
        return orgid;
    }

    public void setOrgid(String orgid)
    {
        this.orgid = orgid;
    }

    public String getSele_rule()
    {
        return sele_rule;
    }

    public void setSele_rule(String sele_rule)
    {
        this.sele_rule = sele_rule;
    }

    public String getTel_prefix()
    {
        return tel_prefix;
    }

    public void setTel_prefix(String tel_prefix)
    {
        this.tel_prefix = tel_prefix;
    }

    public String getTel_suffix()
    {
        return tel_suffix;
    }

    public void setTel_suffix(String tel_suffix)
    {
        this.tel_suffix = tel_suffix;
    }

    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    public String getOrg_id()
    {
        return org_id;
    }

    public void setOrg_id(String org_id)
    {
        this.org_id = org_id;
    }

    public String getCerttype()
    {
        return certtype;
    }

    public void setCerttype(String certtype)
    {
        this.certtype = certtype;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContacttel()
    {
        return contacttel;
    }

    public void setContacttel(String contacttel)
    {
        this.contacttel = contacttel;
    }

    public String getCertid()
    {
        return certid;
    }

    public void setCertid(String certid)
    {
        this.certid = certid;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getTermname()
    {
        return termname;
    }

    public void setTermname(String termname)
    {
        this.termname = termname;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
    
    //add begin cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ
    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
    //add end cKF48754 2011/10/25 L10 ������ܰ��ʾ��Ϣ

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public String getSendTelNum()
    {
        return sendTelNum;
    }

    public void setSendTelNum(String sendTelNum)
    {
        this.sendTelNum = sendTelNum;
    }

	public ChooseTelService getChooseTelService()
	{
		return chooseTelService;
	}

	public void setChooseTelService(ChooseTelService chooseTelService)
	{
		this.chooseTelService = chooseTelService;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

	public String getWebserviceFlag() {
		return webserviceFlag;
	}

	public void setWebserviceFlag(String webserviceFlag) {
		this.webserviceFlag = webserviceFlag;
	}

	public String getVprodId() {
		return vprodId;
	}

	public void setVprodId(String vprodId) {
		this.vprodId = vprodId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getManuTelnumFlag() {
		return manuTelnumFlag;
	}

	public void setManuTelnumFlag(String manuTelnumFlag) {
		this.manuTelnumFlag = manuTelnumFlag;
	}

}
