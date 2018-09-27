package com.customize.hub.selfsvc.chooseTel.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ChooseTelBean;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
import com.customize.hub.selfsvc.chooseTel.model.NetNbrPO;
import com.customize.hub.selfsvc.chooseTel.model.NetValuePO;
import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.service.ChooseTelService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ԤԼѡ��_����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Apr 19, 2011]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ChooseTelAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(ChooseTelAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�id
    private String curMenuId = "";
    
    //����ģʽ=����+�Ŷ�+β��(11λ�»���)
    private String phoneMode;
    
    // ������;(Ĭ�ϴ�"rsupSal")
    private String purpose;
    
    // ����ҳ��
    private int page = 0;
    
    // ÿҳ��ʾ������
    private int pageviewnum = 0;
    
    // ��ҳ��
    private int pageCount = 0;
    
    // ԤԼ�ֻ�����
    private String telnum;
    
    // Ԥ�����
    private String payfee;
    
    // Ԥ�����
    private String payfeeY;
    
    // ����֤����
    private String idCard;
    
    // ����
    private String netNbr;
    
    // �Ŷ�
    private String netValue;
    
    //  β��
    String finalNbr;
    
    // ��ҳʱ�õ���1:�ӵ�һҳ��ʼ��
    String bz;
    
    // ���ȫ���ֻ���������
    private List<ServerNumPO> tmpList = null;
    
    // ÿҳ�����б�
    private List<ServerNumPO> serverNumList = null;
    
    // ���ȫ����������
    private List<NetNbrPO> netNbrTmpList = null;
    
    // ÿҳ�����б�
    private List<NetNbrPO> netNbrList = null;
    
    // ���ȫ����������
    private List<NetValuePO> netValueTmpList = null;
    
    // ÿҳ������Ϣ
    private List<NetValuePO> netValueList = null;
    
    // success 0:�ɹ� 1:ʧ��
    private String successBz; 
    
    // ��֤��
    private String coid;
    
    // ʧЧʱ��
    private String ctime;
    
    // ��ʾ��Ϣ
    private String cremind = "";
    
    // ��ӡ�ص�
    private String locus;
    
    // bean
    private ChooseTelBean chooseTelBean;
    
    // service
    private ChooseTelService chooseTelService;
    
    //add by xkf57421 for ZG[2011]_11_006 begin
    private String initBz;
    //add by xkf57421 for ZG[2011]_11_006 end
    
    /**
     * �Ƿ���Ҫ��ҳ
     */
    private String pageFlag = "false";
    
    /**
     * �������ALL�����к��룻GOOD��������룻LOVE�����º��룻AABB��ABAB��ABBB
     */
    private String telNumType = "ALL";
    
    /**
     * �����������б�
     */
    private List<DictItemPO> luckyNumRules = null; 
    
    /**
     * ����������
     */
    private String luckyNumRule = "";
    
    /**
     * ���º����б�
     */
    private List<LoverNumPO> loverNums = null;
    
    /**
     * ÿҳ��ʾ�����º���
     */
    private List<LoverNumPO> pageLoverNums = null;
    
    /**
     * ��ѡ�����Ƿ������º��롣1���ǣ�����������
     */
    private String loverNumFlag = "";
    
    /**
	 * ��ʾ��Ϣ
	 */	
    private String alertMsg = "";

    /**
     * ����ѡ��ҳ�����ͣ�ͣ�����һloadingҳ�棬����ֹ�ظ��ύ
     * @author xkf57421
     */
    public String loadingMain()
    {
    	return "loadingPhoneList";
    }
    
    /**
     * �����б���ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String phoneListMain()
    {
        // �°�����ѡ�ű�ʶ
        String newVersion = (String) PublicCache.getInstance().getCachedData(Constants.SH_SELECTTEL_NEW);
                
        try
        {
            // ��ȡsession
            HttpSession session = getRequest().getSession(true);
            
            // ��ȡ�ն˻���Ϣ
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // ����ģʽ=����+�Ŷ�+β��(��ѡ�κι�������´�11λ�»���)
            phoneMode = "___________";
            
            // ������;(Ĭ�ϴ�"rsupSal")
            purpose = "rsupSal";
            
            // ����ҳ��(�ڼ�ҳ)
            if ("1".equals(bz) || page == 0)
            {
                page = 1;
            }
            
            // ��ѯԤԼ�����б�
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            // ��¼��־
            if (tmpList != null && tmpList.size() > 0)
            {
	            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "����ѡ�Ų�ѯ�ɹ�.");
            }
            else
            {
                String resultMsg = getConvertMsg("����ѡ�Ų�ѯʧ�ܡ�", "zz_04_18_000001", null, new String[]{""});
                
                this.createRecLog("SHChooseTelNumQry", "0", "0", "1", resultMsg);
            }
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // �°棬ÿҳ����ʾ20����¼
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            pageviewnum = "1".equals(newVersion) ? 20 : 25;
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // ��������
            int endRowNum = pageviewnum * page;
            
            // ��ʼ����
            int startRowNum = endRowNum - pageviewnum;
            
            // ��ҳ��
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            pageCount = (pageCount == 0) ? 1 : pageCount;
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�

            // ���ڴ���ȡ��ÿҳ����
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // ��岻��һҳ���������󣬷�ֹҳ�����
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    // д��ն���
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            logger.error("����ѡ�ţ���ѯ�����б�ʧ�ܣ�", e);
        }
        
        // �°棬������ҳ�档����ԭ��ҳ�治��
        if ("1".equals(newVersion))
        {
            return "phoneListMainNew";
        }
        
        // ����
        return "phoneListMain";
    }
    
    /**
     * ��������֤����ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputIdCard()
    {
        logger.debug("inputIdCard Starting...");
        
        System.out.println("telnum:"+this.telnum);
        System.out.println("payfee:"+this.payfee);
        
        logger.debug("inputIdCard end!");
        
        // ����
        return "inputIdCard";
    }
    
    /**
     * �������֤����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String checkIdCard()
    {
        logger.debug("checkIdCard Starting ...");
        
        try
        {
            getRequest().setCharacterEncoding("GBK");
            getResponse().setContentType("text/html;charset=GBK");
            String data = "";
            
            // ���ԤԼ����
            // ��װ��ѯ����
            ChooseTelNumPO chooseTelNumPO = new ChooseTelNumPO();
            chooseTelNumPO.setIdCard(this.getRequest().getParameter("idCard"));
            chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            // ִ�в�ѯ
            List<ChooseTelNumPO> chooseTelNumList = this.chooseTelService.getChooseTelNum(chooseTelNumPO);
            if(chooseTelNumList.size() != 0)// Ԥ����
            {
                // ȡ�ò�ѯ����
                chooseTelNumPO = (ChooseTelNumPO)chooseTelNumList.get(0);
                int time = Integer.parseInt(chooseTelNumPO.getChooseTelTime());
                int sh_choosetel_idcard_time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_CHOOSETEL_IDCARD_TIME"));
                if (time >= sh_choosetel_idcard_time)
                {
                    data = "һ������֤����ÿ�����ԤԼ"+sh_choosetel_idcard_time+"��!";
                }
            }

            PrintWriter out = getResponse().getWriter();
            out.print(data);
            out.flush();
            logger.debug("checkIdCard End!");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ����ԤԼ������֤��ʽ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String idCardFinish()
    {
        logger.debug("inputIdCard Starting...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        if (terminalInfoPO == null)
        {
            // ʧ��
            this.successBz = "1";
            
            alertMsg = getConvertMsg("����֤ԤԼ����ʧ�ܡ�", "zz_04_18_000003", null, new String[]{telnum});
            
            return "idCardFinish";
        }
        
        // ����
        String telnum = this.telnum;// �ֻ�����
        String seltelprepay = payfee;// Ԥ�����
        String channelid = "bsacAtsv";// ��������, Ĭ�ϣ�"bsacAtsv"
        String credentFlag = "1";// 0 ��֤�룻1 ����֤
        String certtype = "IdCard";// ֤������
        String certid = idCard;// ֤������
        
        // ԤԼ����
        Map resultMap = this.chooseTelBean.bespeakPhone(terminalInfoPO, curMenuId,telnum,seltelprepay,channelid,credentFlag,certtype,certid);
        
        ChooseTelLogPO chooseTelLogPO = new ChooseTelLogPO();
        
        chooseTelLogPO.setRegion(terminalInfoPO.getRegion());
        chooseTelLogPO.setTermId(terminalInfoPO.getTermid());//�ն˱��
        chooseTelLogPO.setOrgId(terminalInfoPO.getOrgid());//ѡ��Ӫҵ��
        chooseTelLogPO.setOperId(terminalInfoPO.getOperid());//ѡ�Ź���
        
        chooseTelLogPO.setIdCard(certid);
        chooseTelLogPO.setTelNum(telnum);
        chooseTelLogPO.setPayfee(seltelprepay);
        
        if (resultMap != null && resultMap.size() > 0)
        {
            CTagSet tagSet = (CTagSet)resultMap.get("returnObj");
            
            // ��֤ID
            this.coid = tagSet.GetValue("oid");
            
            // ʧЧʱ��
            this.ctime = tagSet.GetValue("vidatetime");
            
            // ������ʾ
            this.cremind = tagSet.GetValue("remind");
            
            // add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            if (cremind == null || "".equals(cremind.trim()))
            {
            	cremind = "���������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
            }
            else
            {
            	cremind = cremind.trim();
            	
            	if (cremind.endsWith(".") || cremind.endsWith("��") || cremind.endsWith("!") || cremind.endsWith("��"))
                {
                	cremind += "���������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
                }
                else
                {
                	cremind += "�����������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
                }
            }
            // add end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            
            // ��ӡ�ص�
            this.locus = terminalInfoPO.getOrgname();
            
            // �ɹ�
            this.successBz = "0";
            
            // ��������º��룬�����sh_tel_lover�е�״̬��ʧЧʱ��
            if ("1".equals(loverNumFlag))
            {
                LoverNumPO po = new LoverNumPO();
                po.setMainNum(telnum);
                po.setExpiredDate(ctime);
                
                chooseTelService.updateLoverNumInfo(po);
            }
            
            // ���ԤԼ����
            // ��װ��ѯ����
            ChooseTelNumPO chooseTelNumPO = new ChooseTelNumPO();
            chooseTelNumPO.setIdCard(this.getRequest().getParameter("idCard"));
            chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            // ִ�в�ѯ
            List<ChooseTelNumPO> chooseTelNumList = this.chooseTelService.getChooseTelNum(chooseTelNumPO);
            if(chooseTelNumList.size() == 0)// δԤ����
            {
                // ��װ��������
                chooseTelNumPO = new ChooseTelNumPO();
                chooseTelNumPO.setIdCard(idCard);
                chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                chooseTelNumPO.setChooseTelTime("1");
                
                // ִ������
                this.chooseTelService.insertChooseTelNum(chooseTelNumPO);
            }
            else
            {
                // ȡ�ò�ѯ����
                chooseTelNumPO = (ChooseTelNumPO)chooseTelNumList.get(0);
                int time = Integer.parseInt(chooseTelNumPO.getChooseTelTime());
                
                // ��װ���¶���
                chooseTelNumPO = new ChooseTelNumPO();
                chooseTelNumPO.setIdCard(idCard);
                chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                chooseTelNumPO.setChooseTelTime(String.valueOf(time + 1));
                
                // ִ�и���
                this.chooseTelService.updateChooseTelNum(chooseTelNumPO);
            }
            
            // ��¼��־
            this.createRecLog("SHChooseTelNum", "0", "0", "0", "����֤ԤԼ����ɹ�.");
            
            //��¼ѡ��ԤԼ��־
            String vidateTime = null;
            if (StringUtils.isNotBlank(this.ctime))
            {
                if (this.ctime.contains("0~"))
                {
                    vidateTime = this.ctime.substring(2, this.ctime.length());
                }
                else
                {
                    vidateTime = this.ctime;
                }
                chooseTelLogPO.setVidateTime(CommonUtil.formatDate(vidateTime, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
            }            
            chooseTelLogPO.setSubResult("0");
            chooseTelLogPO.setSubResultDesc("����֤ԤԼ����ɹ�");
            chooseTelService.createTelLog(chooseTelLogPO);
        }
        else
        {
            // ʧ��
            this.successBz = "1";
            
            alertMsg = getConvertMsg("����֤ԤԼ����ʧ�ܡ�", "zz_04_18_000003", null, new String[]{telnum});
            
            // ��¼��־
            this.createRecLog("SHChooseTelNum", "0", "0", "1", alertMsg);
            
            //��¼ѡ��ԤԼ��־
            chooseTelLogPO.setSubResult("1");
            
            if (alertMsg.length() > 256)
            {
                alertMsg = alertMsg.substring(0, 256);
            }
            
            chooseTelLogPO.setSubResultDesc(alertMsg);
            chooseTelService.createTelLog(chooseTelLogPO);
        }
        
        logger.debug("idCardFinish end!");
        
        return "idCardFinish";
    }
    
    /**
     * ����ԤԼ����֤�뷽ʽ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String validateFinish()
    {
        logger.debug("validateFinish Starting...");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ����
        String telnum = this.telnum;// �ֻ�����
        String seltelprepay = payfee;// Ԥ�����
        String channelid = "bsacAtsv";// ��������, Ĭ�ϣ�"bsacAtsv"
        String credentFlag = "0";// 0 ��֤�룻1 ����֤
        String certtype = "";// ֤������
        String certid = "";// ֤������
        
        // ԤԼ����
        Map resultMap = this.chooseTelBean.bespeakPhone(terminalInfoPO, curMenuId,telnum,seltelprepay,channelid,credentFlag,certtype,certid);
        
        if (resultMap != null && resultMap.size() > 0)
        {
            CTagSet tagSet = (CTagSet)resultMap.get("returnObj");
            
            // ��֤ID
            this.coid = tagSet.GetValue("oid");
            
            // ʧЧʱ��
            this.ctime = tagSet.GetValue("vidatetime");
            
            // ������ʾ
            this.cremind = tagSet.GetValue("remind");
            
            // add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            if (cremind == null || "".equals(cremind.trim()))
            {
            	cremind = "���������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
            }
            else
            {
            	cremind = cremind.trim();
            	
            	if (cremind.endsWith(".") || cremind.endsWith("��") || cremind.endsWith("!") || cremind.endsWith("��"))
                {
                	cremind += "���������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
                }
                else
                {
                	cremind += "�����������Ӧ�ײ�ѡ��Ԥ�滰�Ѽ��������ѵ������Ե���Ӫҵ��Ϊ׼��";
                }
            }
            // add end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            
            // ��ӡ�ص�
            this.locus = terminalInfoPO.getOrgname();
            
            // ԤԼ����
            this.payfeeY = new DecimalFormat("0.00").format(Double.parseDouble(payfee)/100);   
            
            // �ɹ�
            this.successBz = "0";
            
            // ��������º��룬�����sh_tel_lover�е�״̬��ʧЧʱ��
            if ("1".equals(loverNumFlag))
            {
                LoverNumPO po = new LoverNumPO();
                po.setMainNum(telnum);
                po.setExpiredDate(ctime);
                
                chooseTelService.updateLoverNumInfo(po);
            }
            
            // ��¼��־
            this.createRecLog("SHChooseTelNum", "0", "0", "0", "��֤��ԤԼ����ɹ�.");
        }
        else
        {
            // ʧ��
            this.successBz = "1";
            
            alertMsg = getConvertMsg("��֤��ԤԼ����ʧ�ܡ�", "zz_04_18_000002", null, new String[]{telnum});
            
            // ��¼��־
            this.createRecLog("SHChooseTelNum", "0", "0", "1", alertMsg);
        }
        
        logger.debug("validateFinish end!");
        
        return "validateFinish";
    }
    
    /**
     * ��β�Ž�������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String phoneSearchByFinalNbr()
    {
        logger.debug("phoneSearchByFinalNbr Starting...");
        
            
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �״ε�¼��־
        if ("1".equals(bz) || page == 0)
        {
            page = 1;
        }
        
        // ����
        netNbr = "___";
        
        // �Ŷ�
        netValue = "____";
        
        // β��
        if (finalNbr == null)
        {
            finalNbr = "";
        }
        String tmpFinalNbr = finalNbr;
        
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        finalNbr = this.evalUnderline(finalNbr);
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        
        // ����ģʽ=����+�Ŷ�+β��
        String phoneMode = netNbr + netValue + finalNbr;

        // ������;(Ĭ�ϴ�"rsupSal")
        String purpose = "rsupSal";
        
        // ��ѯԤԼ�����б�
        tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
        
        this.getRequest().setAttribute("recordCount", tmpList.size());
        
        // ÿҳ��ʾ������
        pageviewnum = 12;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
        
        // ��������
        int endRowNum = pageviewnum * page;
        
        // ��ʼ����
        int startRowNum = endRowNum - pageviewnum;
        
        // ��ҳ��
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
       		pageCount = 1;
        }
        
        // ÿҳ����
        serverNumList = new ArrayList<ServerNumPO>();
        for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-tmpList.size();i++)
            {
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        finalNbr = tmpFinalNbr;
        
        logger.debug("phoneSearchByFinalNbr end!");
        
        // ����
        return "phoneSearch";
    }

    /**
     * Ȧ���Ӷ�
     * @param finalNbr
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	private String evalUnderline(String str) 
	{
		if ("".equals(str) || str == null || "null".equals(str)) {
			str = "____";
        } else if (str.length() == 1) {
        	str = "___" + str;
        } else if (str.length() == 2) {
        	str = "__" + str;
        } else if (str.length() == 3) {
        	str = "_" + str;
        }
		return str;
	}
    
    /**
     * �����Ž�������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String phoneSearchByNetNbr()
    {
        logger.debug("phoneSearchByNetNbr Starting...");
        
        try{
            
            // ��ȡsession
            HttpSession session = getRequest().getSession(true);
            
            // ��ȡ�ն˻���Ϣ
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if ("1".equals(bz) || page == 0)
            {
                page = 1;
            }
            
            // ��������(Ĭ�ϴ�"GSM")
            String netType = "GSM";
    
            // ������;(Ĭ�ϴ�"rsupSal")
            String pur = "rsupSal";
            
            // ��ѯԤԼ�����б�
            Map mapResult = this.chooseTelBean.netNbrQuery(terminalInfoPO, curMenuId, netType, pur);
            
            // ȫ������ȡ����ŵ���ʱ������
            netNbrTmpList = new ArrayList<NetNbrPO>();
            if (mapResult != null && mapResult.size() > 0)
            {   
                NetNbrPO netNbrPO = null;
                CRSet crset = (CRSet) mapResult.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    netNbrPO = new NetNbrPO();
                    netNbrPO.setNetNbr(crset.GetValue(i, 0));// ����
                    netNbrTmpList.add(netNbrPO);
                }
            }
            
            this.getRequest().setAttribute("recordCount", netNbrTmpList.size());
            
            // ÿҳ��ʾ������
            pageviewnum = 30;
            
            if (pageviewnum < netNbrTmpList.size())
            {
                pageFlag = "true";
            }
            
            // ��ʼ��
            int endRowNum = pageviewnum * page;
            
            // ������
            int startRowNum = endRowNum - pageviewnum;
            
            // ��ҳ��
            if (netNbrTmpList.size() % pageviewnum != 0)
            {
                pageCount = netNbrTmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = netNbrTmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // ȡ��ÿҳ��ֵ
            netNbrList = new ArrayList<NetNbrPO>();
            for(int i=startRowNum;i<endRowNum && i<netNbrTmpList.size();i++)
            {
                netNbrList.add((NetNbrPO)netNbrTmpList.get(i));
            }
            
            // ����һҳ���ÿն��������
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-netNbrTmpList.size();i++)
                {
                    NetNbrPO po = new NetNbrPO();
                    po.setNetNbr("");
                    netNbrList.add(po);
                }
            }
            
            for(NetNbrPO a:netNbrList)
            {
                System.out.println("------"+a.getNetNbr());
            }
            
            logger.debug("phoneSearchByNetNbr end!");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "phoneSearchByNetNbr";
        
    }
    
    /**
     * �����ν�������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String phoneSearchByNetValue()
    {
        logger.debug("phoneSearchByNetValue Starting...");
            
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if ("1".equals(bz) || page == 0)
        {
            page = 1;
        }
        // ���ţ�û����___����
        String netnbr = "___";
        
        // ��������(Ĭ�ϴ�"GSM")
        String nettype = "GSM";

        // ������;(Ĭ�ϴ�"rsupSal")
        String pur = "rsupSal";
        
        // ��ѯ�����б�
        Map mapResult = this.chooseTelBean.netValueQuery(terminalInfoPO, curMenuId, netnbr, nettype, pur);

        // ������ GetTelnumsForSelfHelp 
        
        netValueTmpList = new ArrayList<NetValuePO>();
        if (mapResult != null && mapResult.size() > 0)
        {   
            NetValuePO netValuePO = null;
            CRSet crset = (CRSet) mapResult.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                netValuePO = new NetValuePO();
                netValuePO.setNetValue(crset.GetValue(i, 0));// ����
                netValueTmpList.add(netValuePO);
            }
        }
        
        this.getRequest().setAttribute("recordCount", netValueTmpList.size());
        
        // ÿҳ��ʾ������
        pageviewnum = 30;
        
        if (pageviewnum < netValueTmpList.size())
        {
            pageFlag = "true";
        }
        
        // �������
        int endRowNum = pageviewnum * page;
        
        // ��ʼ����
        int startRowNum = endRowNum - pageviewnum;
        
        // ��ҳ��
        if (netValueTmpList.size() % pageviewnum != 0)
        {
            pageCount = netValueTmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = netValueTmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
        
        // ��ѯÿҳ��Ϣ
        netValueList = new ArrayList<NetValuePO>();
        for(int i=startRowNum;i<endRowNum && i<netValueTmpList.size();i++)
        {
            netValueList.add((NetValuePO)netValueTmpList.get(i));
        }
        
        // ������ֵ
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-netValueTmpList.size();i++)
            {
                NetValuePO po = new NetValuePO();
                po.setNetValue("");
                netValueList.add(po);
            }
        }
        
        logger.debug("phoneSearchByNetValue end!");
            
        return "phoneSearchByNetValue";
    }
    
    /**
     * �����������ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String netValueSearchResult()
    {
        logger.debug("netValueSearchResult Starting...");
        
        try{
            
        
            // ��ȡsession
            HttpSession session = getRequest().getSession(true);
            
            // ��ȡ�ն˻���Ϣ
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ� 
            netValue = this.evalUnderline(netValue);
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            
            // ����ģʽ=����+�Ŷ�+β��(11λ�»���)
            phoneMode = "___"+netValue+"____";
            
            // ������;(Ĭ�ϴ�"rsupSal")
            purpose = "rsupSal";
            
            // ����ҳ��(�ڼ�ҳ)
            if ("1".equals(bz))
            {
                page = 1;
            }
            else if (page == 0)
            {
                page = 1;
            }
            
            // ��ѯԤԼ�����б�
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // ÿҳ��ʾ������
            pageviewnum = 25;
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // ��������
            int endRowNum = pageviewnum * page;
            
            // ��ʼ����
            int startRowNum = endRowNum - pageviewnum;
            
            // ��ҳ��
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // ��ѯÿҳ��Ϣ
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // ����ֵ
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        logger.debug("netValueSearchResult end!");
        
        return "netValueSearchResult";
    }
    
    /**
     * �����������ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String netNbrSearchResult()
    {
        logger.debug("netNbrSearchResult Starting...");
        
        try
        {
            
            // ��ȡsession
            HttpSession session = getRequest().getSession(true);
            
            // ��ȡ�ն˻���Ϣ
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // ����ģʽ=����+�Ŷ�+β��(11λ�»���)
            phoneMode = this.netNbr + "________";
            
            // ������;(Ĭ�ϴ�"rsupSal")
            purpose = "rsupSal";
            
            // ����ҳ��(�ڼ�ҳ)
            if ("1".equals(bz))
            {
                page = 1;
            }
            else if (page == 0)
            {
                page = 1;
            }
            
            // ��ѯԤԼ�����б�
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // ÿҳ��ʾ������
            pageviewnum = 25;
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // ��������
            int endRowNum = pageviewnum * page;
            
            // ��ʼ����
            int startRowNum = endRowNum - pageviewnum;
            
            // ��ҳ��
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // ��ѯÿҳ��Ϣ
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // ������ֵ
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        logger.debug("netNbrSearchResult end!");
        
        return "netNbrSearchResult";
    }
    
    /**
     * ��ָ�����Ͳ�ѯ��ѡ�����б�
     * 
     * @return
     * @see 
     */
    public String qryTelnumsWithType()
    {
        // ���к���
        if ("ALL".equalsIgnoreCase(telNumType))
        {
            return phoneListMain();
        }
        // �������
        else if ("GOOD".equalsIgnoreCase(telNumType))
        {
            // �����������б�
            luckyNumRules = chooseTelService.qryLuckyNumRules();
            
            if (luckyNumRules == null)
            {
                luckyNumRules = new ArrayList<DictItemPO>();
            }
            
            int size = luckyNumRules.size();

            // ÿҳ�����ʾ20�ֹ���
            if (size < 20)
            {
                DictItemPO po = null;
                for (int i = size; i < 20; i++)
                {
                    po = new DictItemPO();
                    
                    luckyNumRules.add(po);
                }
            }
            
            return "goodTypePage";
        }
        // ���º���
        else if ("LOVE".equalsIgnoreCase(telNumType))
        {
            return qryLoverNums();
        }
        // ABAB��AABB��ABBB
        else
        {
            return qrySpecifiedPatternNums();
        }
    }
    
    /**
     * ���ݼ����������ѯ���������ļ������
     * 
     * @return
     * @see 
     */
    public String qryLuckyNums()
    {
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
            
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
        // ����ģʽ
        phoneMode = "%" + luckyNumRule + "%";

        // ���ϼ���������ĺ����б�
        tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, "", "");
     
        // ��ѯ��������Ӧ�ķ���
//        Map<String, ServerNumPO> luckyNumInfo = null;
        
        // ��¼��־
        if (tmpList != null && tmpList.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "����ѡ�ż������(" + phoneMode + ")��ѯ�ɹ�.");

//            luckyNumInfo = this.chooseTelService.qryLuckyNumInfo();
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "����ѡ�ż������(" + phoneMode + ")��ѯʧ��.");
        }
            
        this.getRequest().setAttribute("recordCount", tmpList.size());
            
        pageviewnum = 20;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
            
        // ��������
        if (page == 0)
        {
            page = 1;
        }
        int endRowNum = pageviewnum * page;
            
        // ��ʼ����
        int startRowNum = endRowNum - pageviewnum;
            
        // ��ҳ��
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
            
        // ���ڴ���ȡ��ÿҳ����
        serverNumList = new ArrayList<ServerNumPO>();
        ServerNumPO luckyNumPO = null;
        
        for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
        {
            luckyNumPO = (ServerNumPO) tmpList.get(i);
            
//            if (null != luckyNumInfo && luckyNumInfo.containsKey(luckyNumPO.getTelnum()))
//            {
//                luckyNumPO.setSeltel_prepayfee(luckyNumInfo.get(luckyNumPO.getTelnum()).getSeltel_prepayfee());
//            }
            
            serverNumList.add(luckyNumPO);
        }
            
        // ��岻��һҳ���������󣬷�ֹҳ�����
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-tmpList.size();i++)
            {
                // д��ն���
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        // ����
        return "resultPage";
    }
    
    /**
     * ��ѯ���º����б�
     * 
     * @return
     * @see 
     */
    public String qryLoverNums()
    {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ����region��ѯ�����б�
        LoverNumPO po = new LoverNumPO();
        po.setCityID(terminalInfoPO.getRegion());
        
        loverNums = this.chooseTelService.qryLoverNum(po);
        
        // ��¼��־
        if (loverNums != null && loverNums.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "����ѡ�����º����ѯ�ɹ�.");
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "����ѡ�����º����ѯʧ��.");
        }
            
        this.getRequest().setAttribute("recordCount", loverNums.size());
            
        pageviewnum = 20;
        
        if (pageviewnum < loverNums.size())
        {
            pageFlag = "true";
        }
            
        // ��������
        if (page == 0)
        {
            page = 1;
        }
        int endRowNum = pageviewnum * page;
            
        // ��ʼ����
        int startRowNum = endRowNum - pageviewnum;
            
        // ��ҳ��
        if (loverNums.size() % pageviewnum != 0)
        {
            pageCount = loverNums.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = loverNums.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
            
        // ���ڴ���ȡ��ÿҳ����
        pageLoverNums = new ArrayList<LoverNumPO>();
        
        for (int i = startRowNum; i < endRowNum && i < loverNums.size(); i++)
        {
            pageLoverNums.add(loverNums.get(i));
        }
            
        // ��岻��һҳ���������󣬷�ֹҳ�����
        if (page == pageCount)
        {
            for(int i = 0; i < endRowNum - loverNums.size(); i++)
            {
                // д��ն���
                LoverNumPO po1 = new LoverNumPO();
                po1.setMainNum("");
                po1.setSubsNum("");
                po1.setCost("");
                
                pageLoverNums.add(po1);
            }
        }
        
        return "loveTypePage";
    }
    
    /**
     * ABAB��AABB��ABBBģʽ�����б���ѯ
     * 
     * @return
     * @see 
     */
    public String qrySpecifiedPatternNums()
    {
        // ��ȡsession
        HttpSession session = getRequest().getSession(true);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ����ģʽ=����+�Ŷ�+β��(��ѡ�κι�������´�11λ�»���)
        phoneMode = "___________";
                
        // ����ҳ��(�ڼ�ҳ)
        if (page == 0)
        {
            page = 1;
        }
        
        // ��ѯ�����б�
        tmpList = this.chooseTelBean.querySpecifiedPatternNums(terminalInfoPO, curMenuId, phoneMode, telNumType);
        
        // ��¼��־
        if (tmpList != null && tmpList.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "����ѡ��(" + telNumType + ")��ѯ�ɹ�");
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "����ѡ��(" + telNumType + ")��ѯʧ��");
        }
        
        this.getRequest().setAttribute("recordCount", tmpList.size());
        
        pageviewnum = 20;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
        
        // ��������
        int endRowNum = pageviewnum * page;
        
        // ��ʼ����
        int startRowNum = endRowNum - pageviewnum;
        
        // ��ҳ��        
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
        
        // ���ڴ���ȡ��ÿҳ����
        serverNumList = new ArrayList<ServerNumPO>();
        for (int i = startRowNum; i < endRowNum && i < tmpList.size(); i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        // ��岻��һҳ���������󣬷�ֹҳ�����
        if (page == pageCount)
        {
            for (int i = 0; i < endRowNum - tmpList.size(); i++)
            {
                // д��ն���
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        return "specifiedPatternNumPage";
    }
    

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPhoneMode()
    {
        return phoneMode;
    }

    public void setPhoneMode(String phoneMode)
    {
        this.phoneMode = phoneMode;
    }

    public String getPurpose()
    {
        return purpose;
    }

    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPageviewnum()
    {
        return pageviewnum;
    }

    public void setPageviewnum(int pageviewnum)
    {
        this.pageviewnum = pageviewnum;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getPayfee()
    {
        return payfee;
    }

    public void setPayfee(String payfee)
    {
        this.payfee = payfee;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getIdCard()
    {
        return idCard;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getNetNbr()
    {
        return netNbr;
    }

    public void setNetNbr(String netNbr)
    {
        this.netNbr = netNbr;
    }

    public String getNetValue()
    {
        return netValue;
    }

    public void setNetValue(String netValue)
    {
        this.netValue = netValue;
    }

    public String getFinalNbr()
    {
        return finalNbr;
    }

    public void setFinalNbr(String finalNbr)
    {
        this.finalNbr = finalNbr;
    }

    public String getBz()
    {
        return bz;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }

    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    public List<NetNbrPO> getNetNbrTmpList()
    {
        return netNbrTmpList;
    }

    public void setNetNbrTmpList(List<NetNbrPO> netNbrTmpList)
    {
        this.netNbrTmpList = netNbrTmpList;
    }

    public List<NetNbrPO> getNetNbrList()
    {
        return netNbrList;
    }

    public void setNetNbrList(List<NetNbrPO> netNbrList)
    {
        this.netNbrList = netNbrList;
    }

    public List<NetValuePO> getNetValueTmpList()
    {
        return netValueTmpList;
    }

    public void setNetValueTmpList(List<NetValuePO> netValueTmpList)
    {
        this.netValueTmpList = netValueTmpList;
    }

    public List<NetValuePO> getNetValueList()
    {
        return netValueList;
    }

    public void setNetValueList(List<NetValuePO> netValueList)
    {
        this.netValueList = netValueList;
    }

    public ChooseTelBean getChooseTelBean()
    {
        return chooseTelBean;
    }

    public void setChooseTelBean(ChooseTelBean chooseTelBean)
    {
        this.chooseTelBean = chooseTelBean;
    }

    public ChooseTelService getChooseTelService()
    {
        return chooseTelService;
    }

    public void setChooseTelService(ChooseTelService chooseTelService)
    {
        this.chooseTelService = chooseTelService;
    }

    public String getSuccessBz()
    {
        return successBz;
    }

    public void setSuccessBz(String successBz)
    {
        this.successBz = successBz;
    }

    public String getCtime()
    {
        return ctime;
    }

    public void setCtime(String ctime)
    {
        this.ctime = ctime;
    }

    public String getCoid()
    {
        return coid;
    }

    public void setCoid(String coid)
    {
        this.coid = coid;
    }

    public String getCremind()
    {
        return cremind;
    }

    public void setCremind(String cremind)
    {
        this.cremind = cremind;
    }

    public String getLocus()
    {
        return locus;
    }

    public void setLocus(String locus)
    {
        this.locus = locus;
    }

    public String getPayfeeY()
    {
        return payfeeY;
    }

    public void setPayfeeY(String payfeeY)
    {
        this.payfeeY = payfeeY;
    }

	public String getInitBz()
	{
		return initBz;
	}

	public void setInitBz(String initBz)
	{
		this.initBz = initBz;
	}

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public String getTelNumType()
    {
        return telNumType;
    }

    public void setTelNumType(String telNumType)
    {
        this.telNumType = telNumType;
    }

    public List<DictItemPO> getLuckyNumRules()
    {
        return luckyNumRules;
    }

    public void setLuckyNumRules(List<DictItemPO> luckyNumRules)
    {
        this.luckyNumRules = luckyNumRules;
    }

    public String getLuckyNumRule()
    {
        return luckyNumRule;
    }

    public void setLuckyNumRule(String luckyNumRule)
    {
        this.luckyNumRule = luckyNumRule;
    }

    public List<LoverNumPO> getPageLoverNums()
    {
        return pageLoverNums;
    }

    public void setPageLoverNums(List<LoverNumPO> pageLoverNums)
    {
        this.pageLoverNums = pageLoverNums;
    }
    
    public List<LoverNumPO> getLoverNums()
    {
        return loverNums;
    }

    public void setLoverNums(List<LoverNumPO> loverNums)
    {
        this.loverNums = loverNums;
    }

    public String getLoverNumFlag()
    {
        return loverNumFlag;
    }

    public void setLoverNumFlag(String loverNumFlag)
    {
        this.loverNumFlag = loverNumFlag;
    }

    public String getAlertMsg()
    {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg)
    {
        this.alertMsg = alertMsg;
    }
}