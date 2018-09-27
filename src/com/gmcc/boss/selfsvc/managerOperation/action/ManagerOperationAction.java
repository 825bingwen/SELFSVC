package com.gmcc.boss.selfsvc.managerOperation.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.managerOperation.service.ManagerOperationService;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ά����Ա���� �����ֽ���˵�
 * 
 * @author xKF29026
 * 
 */
public class ManagerOperationAction extends BaseAction
{
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��־
    // modify begin by xkf29026 2011/10/6 ���final
    public static final Log logger = LogFactory.getLog(ManagerOperationAction.class);
    // modify end by xkf29026 2011/10/6  ���final
    
    // ����������
    private String auditPsw;

    // ����ʵ�ʽ��
    private String realMoney;
    
    // ������Ϣ
    private String errorMessage;
    
    // �ɹ���Ϣ
    private String successMessage;
    
    //ϵͳ������
    private String sysMoney;
       
    //���ú�̨����
    private ManagerOperationService managerOperationService;
    
    // add begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    /**
     * �ϴλ��˽���ʱ�䣬��ȷ����
     */
    private String lastAuditEndTime = "";
    
    /**
     * �ϴλ��˿�ʼʱ�䣬��ȷ����
     */
    private String lastAuditStartTimeFen = "";
    
    /**
     * �ϴλ��˽���ʱ�䣬��ȷ����
     */
    private String lastAuditEndTimeFen = "";
    
    /**
     * ������ֵ��ֽ����Ϣ
     */
    private List<CashDetailPO> cashes = null;
    
    /**
     * ��������
     */
    private String regionName = "";
    // add end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312    
    
    private String checkCashStatus ="";
    
    // add begin OR_NX_201207_777 yKF28472
    
    /**
     * ����ID
     */
    private String auditId = "";
    
    /**
     * printflag 0:���¼�¼ 1:�����¼�¼
     */
    private String updateFlag = "0";
    
    /**
     * ���λ��˽���ʱ��
     */
    private String auditEndTime = "";
    private String auditEndTimeFen = "";
    // end begin OR_NX_201207_777 yKF28472
    
    /**
     * ת��ά����Աѡ������˵�ҳ��
     * 
     * @return
     */
    public String selectOperationType()
    {
        return "selectOperationType";
    }
    
    /**
     * ת������Ա��������ҳ��
     */
    public String doCashAudit()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        String from = request.getParameter("from");
        if (from != null && !"".equals(from.trim()))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("�����ֽ����ҳ�棬��Դ��" + from);
            }
        }
        
        checkCashStatus = "1";
        
        // ���˵���ӡ��ʶ��1����δ��ӡ�Ľ��˵�
        String unprintFlag = request.getParameter("unprintFlag");
        
        // add begin zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        // �ֽ���˿���
        String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
        // add end zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���

		// modify begin zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
	    // add begin OR_NX_201207_777 yKF28472
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if ((Constants.PROOPERORGID_NX.equalsIgnoreCase(province) 
            || "1".equals(cashAuditSwitch)) && !"1".equals(unprintFlag))
        {
            //��ȡ�ն˻���Ϣ
            TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            //�ն˻�id
            String termid = termInfo.getTermid();
            
            //��װ�����˶���
            ManagerOperationPO managerOperationPO = new ManagerOperationPO();
            managerOperationPO.setTermid(termid);
            
            // ��ѯ
            List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
            
            // ��δ��ӡ�Ľ��˵���¼
            if (unPrintRecords == null || unPrintRecords.size() == 0)
            {
                // �����¼�¼
                managerOperationService.insertLogByAuditBefore(managerOperationPO);
            }
        }
        // add begin OR_NX_201207_777 yKF28472
        // modify end zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        
        return "cashAuditPage";
    }
    
    /**
     * У�����������
     * 
     * @return
     */
    public String checkAuditPassword()
    {
    	//�������̨��ӡ��־����ʼ
		logger.debug("checkPassword start");
    	
		String forward = "";
		
		//��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		//�ն˻�id
		String termid = termInfo.getTermid();
		
    	//��װ�����˶���
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        // add begin zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        managerOperationPO.setRegion(termInfo.getRegion());
        // add end zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        managerOperationPO.setAuditPsw(CommonUtil.MD5Encode(auditPsw));
        
        //���ú�̨У�����������
        String checkStatus = managerOperationService.checkAuditPassword(managerOperationPO);
        if (checkStatus.equals("1"))
        {
            checkCashStatus = "1";
            
            // add begin zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
            // �ֽ���˿���
            String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
            // add end zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
            
            // modify begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) || "1".equals(cashAuditSwitch))
            {
                // add begin OR_NX_201207_777 yKF28472
                updateFlag = "0";
                
                // ���δ��ӡ�ļ�¼
                List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
                
                if (unPrintRecords != null && unPrintRecords.size()>0)
                {
                    auditId = unPrintRecords.get(0).getId();
                    
                    auditEndTime = unPrintRecords.get(0).getAuditEndTime();
                 // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//                    if (auditEndTime != null && !"".equals(auditEndTime.trim()))
//                    {
//                        auditEndTimeFen = auditEndTime.substring(0, 16);
//                    }
//                    else
//                    {
//                        auditEndTimeFen = "����";                       
//                    }
                    auditEndTimeFen = (auditEndTime != null && !"".equals(auditEndTime.trim())) ? auditEndTime.substring(0, 16) : "����";
                 // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
                    
                    realMoney = unPrintRecords.get(0).getRealMoney();
                    if (realMoney != null && !"".equals(realMoney.trim()))
                    {
                        updateFlag = "1";
                        
                        realMoney = String.valueOf(Integer.parseInt(realMoney) / 100);
                    }
                }
                
                // add end OR_NX_201207_777 yKF28472
                
                // ��ȡ�ϴλ���ʱ���
                ManagerOperationPO auditLog = managerOperationService.qryLastAuditLog(termid);
                if (null != auditLog)
                {
                    String lastAuditStartTime = auditLog.getAuditStartTime();
                    
                 // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
                    if (null == lastAuditStartTime)
                    {
                        lastAuditStartTimeFen = "";
                    }
                    else if (!"".equals(lastAuditStartTime))
                    {
                        lastAuditStartTimeFen = lastAuditStartTime.substring(0, 16);
                    }
                 // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
                    
                    lastAuditEndTime = auditLog.getAuditEndTime();
                    if (null == lastAuditEndTime)
                    {
                        lastAuditEndTime = "";
                        lastAuditEndTimeFen = "";
                    }
                    else if (!"".equals(lastAuditEndTime))
                    {
                        lastAuditEndTimeFen = lastAuditEndTime.substring(0, 16);
                    }
                }
                else
                {
                    lastAuditEndTime = "";
                    lastAuditEndTimeFen = "";
                }
                
                auditLog = new ManagerOperationPO();
                auditLog.setTermid(termid);
                // add begin zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
                auditLog.setRegion(termInfo.getRegion());
                // add end zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
                auditLog.setAuditStartTime(lastAuditEndTime);
                auditLog.setAuditEndTime(auditEndTime);
                
                //��ȡϵͳͳ�ƽ��
                sysMoney = CommonUtil.fenToYuan(managerOperationService.getSysMoney(auditLog));
                
                // add begin zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
                if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
                {
                    // ��ѯ�����Ϣ
                    cashes = managerOperationService.qryCashDetailInfo(auditLog, "&nbsp;&nbsp;");
                    
                    //ת��inputCheckMoney.jspҳ��
                    forward="nxInputCheckMoney";
                }
                else if ("1".equals(cashAuditSwitch))
                {
                    //ת��sdInputCheckMoney.jspҳ��
                    forward="sdInputCheckMoney";
                }
                // add end zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
            }
            else
            {
                //��ȡϵͳ�����ܽ��
                String sysMoney = managerOperationService.getSysMoney(termid);
                setSysMoney(sysMoney.substring(0, sysMoney.length() - 2));

                //ת��inputCheckMoney.jspҳ��
                forward="inputCheckMoney";
            }
            // modify end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
        }
        
    	else if(checkStatus.equals("2"))
		{
			//���ô�����Ϣ
			setErrorMessage("�ֽ������Ա����������������룡");
     
			//ת�����ҳ��
			forward="error";
		}
        
        //�������̨��ӡ��־������
        logger.debug("checkPassword End");
        
        return forward;
    }
    
    /**
     * �����ֽ������־
     * 
     * @return
     */
    public String insertAuditCashLog()
    {
    	//�������̨��ӡ��־����ʼ
		if (logger.isDebugEnabled())
		{
		    logger.debug("insertAuditCashLog start");
		}
    	
		String forward = "";
			
    	// �ն���Ϣ
    	TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    	
    	//��װ�ֽ����Ա����
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setRealMoney(realMoney+"00");
        managerOperationPO.setTermid(termInfo.getTermid());
        
        //���ú�̨�����ֽ������־
        String insertStatus = managerOperationService.insertAuditCashLog(managerOperationPO);
        
        if (insertStatus.equals("1"))
        {
        	//���óɹ���Ϣ
        	setSuccessMessage("�ֽ���˳ɹ�");
        	
        	//��¼�ֽ���˳ɹ���־
            this.createRecLog("", "cashAudit", "0", "0", "0", "�ֽ���˳ɹ�");
        	
            //ת��ɹ�ҳ��
            forward="success";
        }
        else
        {
        	//���ô�����Ϣ
        	setErrorMessage("�ֽ����ʧ��");
        	
        	//��¼�ֽ����ʧ����־
            this.createRecLog("", "cashAudit", "0", "0", "1", "�ֽ����ʧ��");
        	
            //ת�����ҳ��
            forward="error";
        }
        
        //�������̨��ӡ��־����ʼ
		if (logger.isDebugEnabled())
		{
		    logger.debug("insertAuditCashLog end");
		}
        
        return forward;
    }
    
    /**
     * ���������ֽ������־
     * 
     * @return
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String insertNXAuditLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertNXAuditLog start");
        }
        
        String forward = "finish";

        // add begin OR_NX_201207_777 yKF28472

        //���ú�̨�����ֽ������־
        String insertStatus = "1";
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        List data = (List) PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        if (null != data && data.size() > 0)
        {
            RegionInfoPO regionInfoPO = null;
            for (int i = 0; i < data.size(); i++)
            {
                regionInfoPO = (RegionInfoPO) data.get(i);
                
                if (termInfo.getRegion().equalsIgnoreCase(regionInfoPO.getRegion()))
                {
                    regionName = regionInfoPO.getRegionname();
                    break;
                }
            }
        }
        
        checkCashStatus = "1";
        
        if ("0".equals(updateFlag))
        {
            // ��װ�ֽ����Ա����
            ManagerOperationPO managerOperationPO = new ManagerOperationPO();
            managerOperationPO.setTermid(termInfo.getTermid());
            managerOperationPO.setAuditStartTime(lastAuditEndTime);
            managerOperationPO.setSysMoney(CommonUtil.yuanToFen(sysMoney));
            managerOperationPO.setRealMoney(realMoney + "00");
            managerOperationPO.setId(this.auditId);
                        
            insertStatus = managerOperationService.updateNXAuditLog(managerOperationPO);            

            if ("1".equals(insertStatus))
            {
                //���óɹ���Ϣ
                setSuccessMessage("�ֽ���˳ɹ�");
                
                //��¼�ֽ���˳ɹ���־
                this.createRecLog("", "cashAudit", "0", "0", "0", "�ֽ���˳ɹ�");
            }
            else
            {
                //���ô�����Ϣ
                setSuccessMessage("�ֽ����ʧ��");
                
                //��¼�ֽ����ʧ����־
                this.createRecLog("", "cashAudit", "0", "0", "1", "�ֽ����ʧ��");
            }
        }
        // begin begin OR_NX_201207_777 yKF28472
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertNXAuditLog end");
        }
        
        return forward;
    }
    
    /**
     * ��ȡ�����ֽ�ɷѶ��˵���ӡ��Ϣ
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void getPrintedData() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        StringBuffer buffer = new StringBuffer("");        

        buffer.append("  ���˷�Χ��\n\n  ��\n");
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ��װ�ֽ����Ա����
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termInfo.getTermid());
        // add begin zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        managerOperationPO.setRegion(termInfo.getRegion());
        // add end zKF69263 2015-4-10 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        managerOperationPO.setAuditStartTime(lastAuditEndTime);
        managerOperationPO.setAuditEndTime(auditEndTime);
        
        // ��ѯ����ʱ����ڵ�ǰ6���ɷѼ�¼
        List<CardChargeLogVO> records = managerOperationService.qrySixChargeRecords("first", managerOperationPO);
        if (null != records)
        {
            CardChargeLogVO record = null;
            for (int i = 0; i < records.size(); i++)
            {
                record = records.get(i);
                
                buffer.append("  ").append(record.getRecdate()).append(" ").append(record.getServnumber())
                        .append(" ").append(record.getFee()).append("\n");
            }
        }
        
        buffer.append("  ��\n");
        
        // ��ѯ����ʱ����ڵĺ�6���ɷѼ�¼
        records = managerOperationService.qrySixChargeRecords("", managerOperationPO);
        if (null != records)
        {
            CardChargeLogVO record = null;
            for (int i = records.size(); i > 0; i--)
            {
                record = records.get(i - 1);
                
                buffer.append("  ").append(record.getRecdate()).append(" ").append(record.getServnumber())
                        .append(" ").append(record.getFee()).append("\n");
            }
        }
        buffer.append("\n  �ܽ�����").append(sysMoney).append("\n  �ܱ�����");
        
        // ��ѯ����ʱ����ڵ��ܽɷѼ�¼��Ŀ
        String strNum = managerOperationService.qryChargeRecordsNum("selectChargeRecordsNum", managerOperationPO);
        
        buffer.append(strNum).append("\n\n  ��ֵ��ϸ��\n\n  ��ֵ������");
        
        int totalNum = 0;
        
        // ��ѯ����ʱ�������ֵ��ϸ
        StringBuffer subBuff = new StringBuffer(64);
        cashes = managerOperationService.qryCashDetailInfo(managerOperationPO, " ");
        if (null != cashes && cashes.size() > 0)
        {
            CashDetailPO cashDetail = null;
            for (int i = 0; i < cashes.size(); i++)
            {
                cashDetail = cashes.get(i);
                
                String cashNum = cashDetail.getCashNum();
                
                if (null == cashNum || "".equals(cashNum.trim()))
                {
                    continue;
                }
                
                totalNum += Integer.parseInt(cashDetail.getCashNum().trim());
                
                subBuff.append("  ").append(cashDetail.getCashFee()).append("Ԫ��")
                        .append(cashDetail.getCashNum()).append("��\n");
            }
        }
        
        buffer.append(totalNum).append("��\n").append(subBuff.toString())
                .append("\n\n  �ɷ�ʧ�ܼ�������ϸ��\n  ʧ�ܱ�����");
        
        // ��ѯ����ʱ����ڵ�ʧ�ܽɷѼ�¼��Ŀ
        strNum = managerOperationService.qryChargeRecordsNum("selectFailedRecordsNum", managerOperationPO);
        
        // add begin cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        // ʧ�ܽ��ױ���
        int failCnt = Integer.parseInt(strNum);
        // add end cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        
        buffer.append(strNum).append("   ����������");
        
        // ��ѯ����ʱ����ڵĳ����ɷѼ�¼��Ŀ
        strNum = managerOperationService.qryChargeRecordsNum("selectRollbackRecordsNum", managerOperationPO);
        
        buffer.append(strNum).append("\n\n  ����ʵ�ʽ�").append(realMoney).append("\n\n");
        
        // add begin cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780 
        // �нɷ�ʧ�ܼ�¼����ѯ�ɷ�ʧ�ܼ�¼
        if(failCnt > 0)
        {
            buffer.append("  �ɷ�ʧ����ϸ:\n");
            records = managerOperationService.qryFailedRecords(managerOperationPO);
            if (null != records)
            {
                CardChargeLogVO record = null;
                for (int i = 0; i < records.size(); i++)
                {
                    record = records.get(i);
                    
                    buffer.append("  ʱ��:").append(record.getRecdate()).append(" ����:").append(record.getServnumber())
                            .append("\n  ������ˮ:").append(record.getTerminalSeq()).append(" ���:").append(record.getFee())
                            .append("\n  ���׽��:").append(record.getDescription()).append("\n\n");
                }
            }
        }
        // add end cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        
        if (logger.isInfoEnabled())
        {
            logger.info("�ն˻�ID��" + termInfo.getTermid() + ";" + "���˵���Ϣ��" + buffer.toString());
        }
        
        out.write(buffer.toString());
        out.flush();
    }
    
    /**
     * ��ȡɽ���ֽ�ɷѶ��˵���ӡ��Ϣ
     * 
     * @remark: create zKF69263 2015/04/17 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
     */
    public void getCashPrintedData() throws Exception
    {
        StringBuffer cashData = new StringBuffer();
        
        // ��װ�ֽ�ɷѶ��˵���ӡ��Ϣ
        if (StringUtils.isNotEmpty(lastAuditStartTimeFen) || StringUtils.isNotEmpty(lastAuditEndTimeFen))
        {
            cashData.append("  �ϴλ���ʱ��Σ�")
                .append(StringUtils.isEmpty(lastAuditStartTimeFen) ? "��ʼ" : lastAuditStartTimeFen)
                .append("-").append(lastAuditEndTimeFen).append("\n\n");
        }
        
        cashData.append("  ���λ���ʱ��Σ�")
            .append(StringUtils.isEmpty(lastAuditEndTimeFen) ? "��ʼ" : lastAuditEndTimeFen)
            .append("-").append(java.net.URLDecoder.decode(auditEndTimeFen, "UTF-8")).append("\n\n");
        cashData.append("  ϵͳͳ�ƽ�").append(sysMoney).append("Ԫ").append("\n\n");
        cashData.append("  ����ʵ�ʽ�").append(realMoney).append(".00Ԫ").append("\n\n");
        
        // ���ɶ��˵���ӡ��Ϣ
        writeXmlMsg(cashData.toString());
        
        logger.debug("�ն˻�ID��" + getTerminalInfoPO().getTermid() + ";" + "���˵���Ϣ��" + cashData.toString());
    }
    
  	// add begin OR_NX_201207_777 yKF28472
    /**
     * ����״̬
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void updatePringFlag() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ��װ�ֽ����Ա����
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setId(this.auditId);
        
        // ����״̬
        managerOperationService.updatePringFlag(managerOperationPO);
        
        if (logger.isInfoEnabled())
        {
            logger.info("�ն˻�ID��" + termInfo.getTermid() + ";" + "����״̬�ɹ���");
        }
        
        out.write("");
        
        out.flush();
    }
    
    /**
     * ����״̬
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void getUnPrintRecord() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ��װ�ֽ����Ա����
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termInfo.getTermid());
        
        // ��ѯ
        List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
        
        // ��δ��ӡ�Ľ��˵���¼
        if (unPrintRecords != null && unPrintRecords.size() > 0)
        {
            out.write("1");
        }
        // û��δ��ӡ�Ľ��˵���¼
        else
        {
            out.write("0");
        }
        
        out.flush();
    }
    // end begin OR_NX_201207_777 yKF28472
    
    public String getAuditPsw()
    {
        return auditPsw;
    }
    
    public void setAuditPsw(String auditPsw)
    {
        this.auditPsw = auditPsw;
    }
    
    public ManagerOperationService getManagerOperationService()
    {
        return managerOperationService;
    }
    
    public void setManagerOperationService(ManagerOperationService managerOperationService)
    {
        this.managerOperationService = managerOperationService;
    }
    
    public String getRealMoney()
    {
        return realMoney;
    }
    
    public void setRealMoney(String realMoney)
    {
        this.realMoney = realMoney;
    }


	public String getSysMoney() {
		return sysMoney;
	}

	public void setSysMoney(String sysMoney) {
		this.sysMoney = sysMoney;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	// add begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    public String getLastAuditEndTime()
    {
        return lastAuditEndTime;
    }

    public void setLastAuditEndTime(String lastAuditEndTime)
    {
        this.lastAuditEndTime = lastAuditEndTime;
    }

    public List<CashDetailPO> getCashes()
    {
        return cashes;
    }

    public void setCashes(List<CashDetailPO> cashes)
    {
        this.cashes = cashes;
    }
    
    public String getLastAuditStartTimeFen()
    {
        return lastAuditStartTimeFen;
    }

    public void setLastAuditStartTimeFen(String lastAuditStartTimeFen)
    {
        this.lastAuditStartTimeFen = lastAuditStartTimeFen;
    }

    public String getLastAuditEndTimeFen()
    {
        return lastAuditEndTimeFen;
    }

    public void setLastAuditEndTimeFen(String lastAuditEndTimeFen)
    {
        this.lastAuditEndTimeFen = lastAuditEndTimeFen;
    }    

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }
    // add end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312

    public String getCheckCashStatus()
    {
        return checkCashStatus;
    }

    public void setCheckCashStatus(String checkCashStatus)
    {
        this.checkCashStatus = checkCashStatus;
    }

	// add begin OR_NX_201207_777 yKF28472
    public String getAuditId()
    {
        return auditId;
    }

    public void setAuditId(String auditId)
    {
        this.auditId = auditId;
    }


    // end begin OR_NX_201207_777 yKF28472

    public String getUpdateFlag()
    {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag)
    {
        this.updateFlag = updateFlag;
    }

    public String getAuditEndTimeFen()
    {
        return auditEndTimeFen;
    }

    public void setAuditEndTimeFen(String auditEndTimeFen)
    {
        this.auditEndTimeFen = auditEndTimeFen;
    }

    public String getAuditEndTime()
    {
        return auditEndTime;
    }

    public void setAuditEndTime(String auditEndTime)
    {
        this.auditEndTime = auditEndTime;
    }   
}
