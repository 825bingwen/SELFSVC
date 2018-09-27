package com.customize.hub.selfsvc.charge.action;

import com.customize.hub.selfsvc.bean.FeeChargeHubBean;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ScoreBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.terminfo.service.TerminalInfoService;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * ���ѳ�ֵ�ɷ�
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeHubAction extends BaseAction
{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��־
    private static Log logger = LogFactory.getLog(FeeChargeHubAction.class);
    
    // ��ǰ�˵�
    private String curMenuId = "";
    
    // �û��ֻ���
    private String servnumber;
    
    // ���ѽ��
    private String tMoney;
    
    // �ն˻���ˮ��
    private String terminalSeq;
    
    // ������Ϣ
    private String errormessage;
    
    // ���ýӿ�Bean
    private transient FeeChargeHubBean feeChargeBean;
    
    private String servRegion = "";
    
    // Ӧ�ɷ���
    private String yingjiaoFee;
    
    // �������
    private String balance;
    
    // �û�ˢ������
    private String cardnumber;
    
    // ����Ϊˢ�����ն˷����Ψһ���
    private String unionpaycode;
    
    // �����̻��ţ��ڿ�����ʶ��
    private String unionpayuser;
    
    // ҵ������
    //modify by sWX219697 2015-7-18 ��busitype��ΪbusiType��findbugs�޸�
    private String busiType;
    
    // �ն����κ�
    private String batchnum;
    
    // ����ʵ�ʿۿ����λ���֣�
    private String unionpayfee;
    
    // ����ʵ�ʿۿ�ʱ��
    private String unionpaytime;
    
    // ��Ȩ�루ɽ���ã�
    private String authorizationcode;
    
    // ���ײο��ţ�ɽ���ã�
    private String businessreferencenum;
    
    // ��֤�ţ�ɽ���ã�
    private String vouchernum;
    
    // �������ݿ�
    private transient FeeChargeHubService feeChargeService;
    
    // BOSS��ˮ
    private String dealNum = "";
    
    // ����ʱ��
    private String dealTime = "";
    
    private String hasMultiInvoices = "true";
    
    private String payType;
    
    private boolean canPayWithCash = true;
    
    private List usableTypes = null;
    
    // �ɷ���־��Ӧ��oid
    private String chargeLogOID = "";
    
    private String errorType = "";
    
    private String needReturnCard = "";
    
    private String needRandomPwd = "";// ȡBOSSϵͳ����_�Ƿ��Ͷ�����֤�� 1:���� 0:������
    
    private String printcontext = "";// ������ӡ��Ϣ
    
    //add by xkf57421 for XQ[2011]_10_082 begin
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    //add by xkf57421 for XQ[2011]_10_082 begin
    // �齱�����
    private String actId =null;
    
    //add begin sWX219697 2014-12-23 15:44:49 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
    /**
     * ���ͽ�� ��λ����
     */
    private String presentFee;
    //add end sWX219697	2014-12-23 15:46:51 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���

    //add begin liutao00194861 2016-8-16 OR_HUB_201603_1191 ��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��
    //ʣ�����
    private String availIntegral="";
    //�Ƿ�4G��ʾ
    private String isNot4GCard="";
    // ���ýӿ�bean
    private ScoreBean qryScoreBean;
    //add end liutao00194861 2016-8-16  ��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��
    
    //add begin lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
    //�����������ն��жϲ��� 
    private boolean agentParam = false;
    //֧������֧����ʽչʾ����
    private String termParam; 
    //֧������֧����ʶ
    private String payCerntWay;
    
    //�ɷѽ��
    private String chargeMoney;
    
    // �ض���url
    private String redirectUrl;
    
    //֧������֧��ʧ����Ϣ
    private String errorPayCenterMsg;
    
    //��ֵ�ɹ������ֻ�����
    private String cuNumbers = "";
    /**
     * �ɹ���־
     */
    public static final String G_SUCCESS_FLAG = "000000";
    //add ebnd lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
    

    /**
     * ���ѳ�ֵ������Ҫ������֤��������Ҫ����������룬�Ա�֤��ֵ������ȷ
     * 
     * @return
     */
    public String inputNumber()
    {
        // Change by LiFeng ����inputNumberҳ������������е��û���Ϣ 20110907
        this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
        return "inputNumber";
    }
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ���˻���Ϣ+֧����ʽ��
     * 
     * @return
     */
    public String qryfeeChargeAccount()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryfeeChargeAccount start");
        }
        
        // ����˻���Ϣ��ѯʧ��ת���ɷѵ�¼ҳ��
        String forward = "inputNumber";
        
        HttpServletRequest request = this.getRequest();
        
        try
        {
            TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
            
            Map result = feeChargeBean.qryfeeChargeAccount(termInfo, servnumber, curMenuId);
            if (result != null && result.size() > 1)
            {
                CTagSet tagSet = (CTagSet)result.get("returnObj");
                
                // ----------------------------�˻���Ϣ����-------------------------------------------------
                
                // ����
                servRegion = tagSet.GetValue("region");
                
                // ���û������
                setBalance(CommonUtil.fenToYuan(tagSet.GetValue("balance")));
                
                // ----------------------------��ǰ�û���Ϣ-------------------------------------------------
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                
                // �ֻ�����
                customerSimp.setServNumber(servnumber);
                
                // ��ƷID
                customerSimp.setBrandID(tagSet.GetValue("productid"));
                
                // ��Ʒ����
                customerSimp.setBrandName(tagSet.GetValue("productname"));
                
                // �ͻ�����
                customerSimp.setCustomerName(tagSet.GetValue("subname"));
                
                // ����SESSION
                request.getSession().setAttribute(Constants.USER_INFO, customerSimp);
                
                // ----------------------------��ֵ��ʽ-------------------------------------------------
                
                // �����ն����Ի����л�ȡ�˵��б�
                String groupID = termInfo.getTermgrpid();
                
                List<MenuInfoPO> menus = null;
                
                if (groupID != null && !"".equals(groupID))
                {
                    menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
                }
                
                // ��ҳ�˵��б�
                usableTypes = CommonUtil.getChildrenMenuInfo(menus, curMenuId, "");
                
                // findbugs�޸� 2015-09-02 zKF66389
//                if (logger.isInfoEnabled())
//                {
//                    logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
//                }
                // findbugs�޸� 2015-09-02 zKF66389
                
                // findbugs�޸� 2015-09-02 zKF66389
                //if (usableTypes == null || usableTypes.size() == 0)
                if (usableTypes.size() == 0)
                // findbugs�޸� 2015-09-02 zKF66389
                {
                    // û�п��õĳ�ֵ��ʽ
                    setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
                    
                    // ��¼��־
                    this.createRecLog(servnumber, "feeCharge", "0", "0", "1", "��ʱû�п��õĳ�ֵ��ʽ");
                    
                    forward = "failed";
                }
                
                forward = "success";
                
                // ��¼�ɹ���־
                this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "�ɷ�ʱ��ѯ�������ͳɹ�");
            }
            else
            {
                logger.debug("��ѯ��������ʧ�ܣ�");
                
                this.getRequest().setAttribute("errormessage", result.get("returnMsg"));
                
                // ��¼������־
                this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "�ɷ�ʱ��ѯ��������ʧ��");
            }
        }
        catch (Exception e)
        {
            logger.error("��ѯ��������ʧ�ܣ�");
            setErrormessage("��ѯ�������ͳ����쳣������ϵϵͳ����Ա�����������Ĳ��㣬����ԭ�¡�");
            
            // ��¼�쳣��־
            this.createRecLog(servnumber, "cashCharge", "0", "0", "0", "�ɷ�ʱ��ѯ��������ʧ��");
        }
        
        //add begin lwx439898 2017-10-12 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
        if("true".equals(CommonUtil.getParamValue("onlinePay_Switch", "false")))
        {           
            HttpSession session = getRequest().getSession();
            // �ն���Ϣ
            TerminalInfoPO termInfos = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            //��ȡ�ն��豸��
            String termids = termInfos.getTermgrpid();       
            //�ն����Լ���       
            List<TermInfosPO> termInfoList = (List<TermInfosPO>)PublicCache.getInstance().getCachedData(Constants.TERM_INFO);
            if(null != termInfoList)
            {              
                for(TermInfosPO terminal : termInfoList)
                {
                    //������ѯ�ն��豸���Ա����Ƿ���ڸ��ն˺�
                    if(termids.equals(terminal.getTerminalid()))
                    {
                        if("PayCenter".equals(terminal.getAttributeid()))
                        {
                            //����1��ʾ����չʾ֧������֧���ķ�ʽ
                            if( "1".equals(terminal.getAttributeval()))
                            {
                                termParam = "true";
                                break;
                            }
                        }
                    }
                }       
                //�ж������ն��Ƿ����ڴ����̵�orgtyAgentֵ
                String orgtyAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_ORGTYPE_AGENT);
                //��ȡTerminalInfoService����
                TerminalInfoService infoService = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");  
                logger.info("orgtyAgent="+orgtyAgent+" and infoService="+infoService+" and termInfos="+infoService.getOrgtype(termInfos));
                //�ж������ն��Ƿ����ڴ�����                
                if(null != orgtyAgent && orgtyAgent.equals(infoService.getOrgtype(termInfos)))
                {
                    agentParam = true;
                }      
            }
        }      
        //add end lwx439898 2017-10-12 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
        
        return forward;
    }
    
    /**
     * ת��Ͷ��ҳ��
     * 
     * @return
     */
    public String cashCharge()
    {
        return "cashChargePage";
    }
    
    /**
     * ֧������֧����ʽ
     * 
     * create by lwx439898 2017-10-12 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public String intPayCetCharge()
    {
        String forward = null;
        logger.debug("payCenterCharge start");
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        // ��ȡ��ǰ�û� ��Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        // �ֻ�����
        String currNumber = customerSimp.getServNumber();
        // ֧������ Ԫת��
        String totalChargeMoney = CommonUtil.yuanToFen(chargeMoney);
        // ��ǰҵ������
        String recType = CommonUtil.getParamValue("self_recType", "Voucher");
        // �������� Ĭ�� pay��ֵ
        String transType = CommonUtil.getParamValue("self_transType", "pay");
       
        String respMsg = "";
        String payCenterParamInfo = "";
        try
        {
            // ����֧�����񲢷��������
            String payTransMsg = createPayCenterTrans(customerSimp,
                    termInfo,
                    currNumber,
                    totalChargeMoney,
                    recType,
                    transType);
            if (StringUtils.isBlank(payTransMsg))
            {
                throw new Exception("����֧������ʧ�ܣ�");
            }
            
            // ֧�����Բ���
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {
                respMsg = "{a:'response1',b:'response2'}";
                payCenterParamInfo = "{a:'aa',b:'bb',c:'cc'}";
                redirectUrl = CommonUtil.getParamValue("SH_URL_TEST"); 
            }
            else
            {
                // ��װ�����������Ӧ��������
                payCenterParamInfo = assembledPackets(termInfo, payTransMsg, totalChargeMoney, currNumber);
                
                // ��ȡ֧����������url��������
                String reqUrl = CommonUtil.getParamValue("SH_PayCenterSendMsgUrl");
                
                if (StringUtils.isBlank(reqUrl))
                {
                    throw new Exception("����֧����������֧����URLΪ�գ�");
                }
                
                respMsg = sendPayCenterMessage(payCenterParamInfo, reqUrl);
                if (StringUtils.isBlank(respMsg))
                {
                    throw new Exception("����֧�������쳣");
                }
                
                org.json.JSONObject respObject = new org.json.JSONObject(respMsg);
                
                String respCode = respObject.getString("retCode");
                // �ɹ���ʶ
                if (G_SUCCESS_FLAG.equals(respCode))
                {
                    org.json.JSONObject resqData = respObject.getJSONObject("data");
                    if (null != resqData)
                    {
                        redirectUrl = resqData.getString("redirectUrl");
                    }
                }
                else
                {
                    throw new Exception(respObject.getString("retMsg"));
                }
            }
            // ��������֧��״̬
            feeChargeBean.updateBankPaymentStatus(payCenterParamInfo,
                    respMsg,
                    payTransMsg,
                    termInfo,
                    curMenuId,
                    currNumber);
            if (StringUtils.isBlank(redirectUrl))
            {
                throw new Exception("����֧�����Ļ�ȡ�ض���URLʧ�ܣ�");
            }
            //����ʹ��
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {              
                getRequest().getSession().setAttribute("taskoid",payTransMsg);
            }
            
            getRequest().setAttribute("taskoid", payTransMsg);           
            forward = "initPayCenter";
        }
        catch (Exception e)
        {
            logger.error("֧������֧���쳣��" + e); 
            errorPayCenterMsg = "֧������֧��ʧ�ܣ�"+e;
            forward = "error";
        }
        return forward;
    }
    
    /**
     * ֧������֧���ɹ�(֧�����Ļص�)
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * create by lwx439898 2017-10-12 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public String payCetChargeCommit()
    {  
        String forward = "success";
        logger.error("PayCetChargeCommit begin  ");
        try
        {
            // �ն���Ϣ
            TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
            // ��ȡ��ǰ�û� ��Ϣ
            NserCustomerSimp customerSimp = (NserCustomerSimp)getRequest().getSession()
                    .getAttribute(Constants.USER_INFO);
            String currNumber = customerSimp.getServNumber();
            String taskoid = "";
            String payId = "";
            HttpServletRequest request = this.getRequest();
            //����ʹ��
            if ("true".equals(CommonUtil.getParamValue("SH_PayCenterTEST")))
            {
                payId = request.getParameter("payId");
                taskoid = (String)getRequest().getSession().getAttribute("taskoid");
                chargeMoney = CommonUtil.yuanToFen(chargeMoney);
            }
            else
            {                
                payId = request.getParameter("payId");
                taskoid =  request.getParameter("partnerPayId");
                chargeMoney = request.getParameter("payFee");               
            }
            // ֧������֧��ģʽ
            String payModeId = "";
            // ֧��״̬
            String bankStatus = "";
            // ��ѯ֧������
            Map chargeinfos = feeChargeBean.qryPayChargeInfo(termInfo, taskoid, currNumber, curMenuId);
            logger.error("��ѯ֧�����׷���ֵ��"+chargeinfos);
            if (chargeinfos != null && chargeinfos.size() > 1)
            {
                Object obj = chargeinfos.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // �Է���ֵ���н���
                    CTagSet chargeInfo = (CTagSet)obj;
                    payModeId = (String)chargeInfo.GetValue("PAYTYPE");
                    bankStatus = (String)chargeInfo.GetValue("BANKSTATUS");
                }
            }
            logger.error("bankStatus��"+bankStatus);
            // �ж�֧��״̬�����Ƿ�֧���ɹ�,����1���ʾ֧��ʧ��
            if (!"1".equals(bankStatus))
            {
                forward = "error";
                errorPayCenterMsg = "֧��ʧ�ܣ������֧�����Ŀ۷��Ƿ�����";
                return forward;
            }
            
            // ��֧������
            String newPayType = payModeId;
            String recoid = "";
            String status = "";
            // modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            // �ɷ��ύ
            Map resultMap = feeChargeBean.chargeCommit(taskoid,termInfo,
                    curMenuId,
                    currNumber,
                    chargeMoney + "",
                    newPayType,
                    payId,
                    "0");
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            logger.error("FeechargeResponse 11��"+resultMap);
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // �Ժ����ɷѽӿڵķ���ֵ���н���
                    CTagSet chargeInfo = (CTagSet)obj;
                    
                    dealNum = (String)chargeInfo.GetValue("dealNum");// ������ˮ
                    dealTime = (String)chargeInfo.GetValue("dealTime");// ����ʱ��
                    recoid = (String)chargeInfo.GetValue("RECOID");
                    
                }
                status = "success";
            }
            else
            {
                status = "fail";
            }
            logger.error("updateBankRecoid beign   ");
            // ���±� ar_bank_task �� recoid
            feeChargeBean.updateBankRecoid(termInfo, currNumber, curMenuId, taskoid, recoid, status);
            logger.error("updateBankRecoid end    "+resultMap);
            if (resultMap == null)
            {
                throw new Exception("���ýɷѽӿ�ʧ�ܣ�");
            }
            cuNumbers = currNumber;
        }
        catch (Exception e)
        {
            forward = "error";
            errorPayCenterMsg = "PayCetChargeCommit exception"+e;
            logger.error("PayCetChargeCommit end error  ");
        }
     
        return forward;
    }

    /**
     * ����֧�����ķ�����Ϣ
     * <������ϸ����>
     * @param sendMsg
     * @param reqUrl
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    private String sendPayCenterMessage(String sendMsg, String reqUrl)
    {
        StringBuffer respMsg = new StringBuffer();
        BufferedReader in = null;
        try
        {
            // ��ȡ����Ա��Ϣ
            
            logger.debug("PayCenterAction.sendPayCenterMessage reqUrl:" + reqUrl);
            // 1����ʼ��POST������
            PostMethod httppost = new PostMethod(reqUrl);
            // ����POST����
            httppost.setRequestBody(sendMsg);
            logger.debug("PayCenterAction.sendPayCenterMessage sendMsg:" + sendMsg);
            // ���ñ���ͷ��Ϣ                                              
            httppost.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            // ���ӳ�ʱ
            String connectTimeout = CommonUtil.getParamValue("PayCenterConnectTimeout");
            connectTimeout = StringUtils.defaultIfEmpty(connectTimeout, "5000");
            // �ȴ���ʱ
            String waitTimeout = CommonUtil.getParamValue("PayCenterWaitTimeout");
            waitTimeout = StringUtils.defaultIfEmpty(waitTimeout, "5000");

            // 2�����ýӿڣ�
            HttpClient client = new HttpClient();
            // ��������ʱ��
            client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt(connectTimeout));
            // ���õȴ�ʱ��
            client.getHttpConnectionManager().getParams().setSoTimeout(Integer.parseInt(waitTimeout));
            client.executeMethod(httppost);
            
            // 3����ȡ�ӿڵ��ý��
            // ���ձ���
            in = new BufferedReader(new InputStreamReader(httppost.getResponseBodyAsStream(), "UTF-8"));
            char[] buffer = new char[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1)
            {
                respMsg.append(buffer, 0, len);
            }
          
            // 4���ͷ�����
            httppost.releaseConnection();

        }
        catch (Exception e)
        {
            logger.error("PayCenterAction.sendPayCenterMessage Exception:", e);
        }
        finally
        {
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch (IOException e)
            {
                logger.error("PayCenterAction.sendPayCenterMessage IOException:", e);
            }
            
        }
        logger.debug("PayCenterAction.sendPayCenterMessage respMsg:" + respMsg.toString());
        return respMsg.toString();
    }
    
    /**
     * ƴװ֧���������json����
     * <������ϸ����>
     * @param termInfo
     * @param payTransMsg
     * @param totalChargeMoney
     * @param currNumber
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lwx439898 2017-10-16 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    private String assembledPackets(TerminalInfoPO termInfo,String payTransMsg, String totalChargeMoney, String currNumber)
    {
        JSONObject payCenterParam = new JSONObject();
        JSONObject payCenterData = new JSONObject();       
        try
        {
            // �ͻ���ʾ
            payCenterData.put("customerId", currNumber);
            // �̻���ʶ
            payCenterData.put("partnerId", CommonUtil.getParamValue("self_selfMerchant", "SHSELF"));
            // ֧����λ��ʶ
            payCenterData.put("partnerPayId", payTransMsg);
            //ָ������̨code
            payCenterData.put("cashierCode", CommonUtil.getParamValue("self_cashierCode", ""));
            //��������
            payCenterData.put("orderDesc",  CommonUtil.getParamValue("self_orderDesc", "selfsvc_feecharge_"+totalChargeMoney));
            // ��������
            payCenterData.put("groupId", termInfo.getOrgid());
            // ����
            payCenterData.put("currency", "RMB");
            // ֧���ܽ��(��λ����)
            payCenterData.put("totalFee", totalChargeMoney);
            // ����ģʽ
            payCenterData.put("accessMode","PC");
            // ǰ̨֪ͨ
            String retUrl = CommonUtil.getParamValue("SH_PayCenterRetUrl");
            if (StringUtils.isBlank(retUrl))
            {
                logger.error("��ȡǰ̨֪ͨurl�쳣");
            }
            payCenterData.put("retUrl", retUrl);
            // ��̨֪ͨ
            String notifyUrl = CommonUtil.getParamValue("SH_PayCenterNotifyPayUrl");
            if (StringUtils.isBlank(notifyUrl))
            {
                logger.error("��ȡ��̨֪ͨurl�쳣");
            }
            payCenterData.put("notifyUrl", notifyUrl);
            // �û�IP
            payCenterData.put("clientIp", termInfo.getIpaddr());
            // �Ƿ���Ҫ��¼
            payCenterData.put("needLoginFlag", CommonUtil.getParamValue("self_needLoginFlag", "N"));
            // ��ʱʱ��
            payCenterData.put("timeout", CommonUtil.getParamValue("self_timeout", ""));
            // ��Ʒ���� �Ǳ����ʱΪ��
            List<String> list = new ArrayList<String>();
            payCenterData.put("goodsInfo", list);
            payCenterParam.put("data", payCenterData);
            payCenterParam.put("signType", "MD5");
            // ǩ��
            String eleSignKey = CommonUtil.getParamValue("SH_PayCenterElectronicSignKey");
            if (StringUtils.isBlank(eleSignKey))
            {
                logger.error("����֧�����ĵ���Կ�쳣");
            }
            
            payCenterParam.put("sign", md5HexSignStr(JSONObject.toJSONString(payCenterData, SerializerFeature.SortField), eleSignKey));
        }
        catch (Exception e)
        {
            logger.error("ƴװ���json�����쳣", e);
        }      
        return payCenterParam.toString();
    }
  
    /**
     * �½�֧������
     * 
     * @param currNumber
     * @param totalChargeMoney
     * @param recType
     * @param transType
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    private String createPayCenterTrans(NserCustomerSimp customerSimp,TerminalInfoPO termInfo,String currNumber,String totalChargeMoney,String recType,String transType)
    {
        String taskOid = "";
        String queryType = CommonUtil.getParamValue("self_qryChargeType", "servNumber");
        Map<String,String> selfPayLog = new HashMap<String,String>();
        selfPayLog.put("RecType", recType);
        selfPayLog.put("exChangeType", transType);
        selfPayLog.put("Payamount", totalChargeMoney);
        selfPayLog.put("AccessType","bsacAtsv");
        selfPayLog.put("Paytype", CommonUtil.getParamValue("self_payType", "PayCenter"));
        selfPayLog.put("Telnum", currNumber);
        selfPayLog.put("bankCode",CommonUtil.getParamValue("self_bankCode", "PayCenter"));
               
        //����֧�����񣬼�¼ar_bank_task��
        Map payTransMsg = feeChargeBean.createPayCenterTrans(customerSimp,curMenuId,termInfo,queryType,currNumber,selfPayLog);
        if (payTransMsg != null && payTransMsg.size() > 1)
        {
            Object obj = payTransMsg.get("returnObj");
            if (obj instanceof CTagSet)
            {
                // �Է���ֵ���н���
                CTagSet chargeInfo = (CTagSet)obj;
                taskOid = (String)chargeInfo.GetValue("TASKOID");
            }
        }
        
        return taskOid;
    }
    
    /**
     * MD5����
     * @param content
     * @param signKey
     * @return
     */
    private String md5HexSignStr(String content, String signKey)
    {
        String str = content + "&key=" + signKey;     
       
        return DigestUtils.md5Hex(str);
    }
    
      
    /**
     * ���ѳ�ִֵ���ֽ�ɷ�
     * 
     * @return
     */
    public String cashChargeCommit()
    {
        logger.debug("cashChargeCommit start");
        
        String forward = null;
        
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        //�������ͽ���λ��Ԫ
        presentFee = calculateFee(tMoney);
        
        //���ͻ�����ˮ��
        String presentOid = "";
        //add end sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        
        // �趨�ɷѷ�ʽ
        this.payType = Constants.PAY_BYCASH;
        
        HttpSession session = getRequest().getSession();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // add by LiFeng [XQ[2011]_11_062]�����ն�-�ظ��ɷѿ��� 20111125 Begin
        //���Կ�������
	    if (!checkCashFee(termInfo))
	    {
	         forward = "repeatError";
	         return forward;
	    }
        //Add by LiFeng [XQ[2011]_11_062]�����ն�-�ظ��ɷѿ��� 20111125 End
        
        // ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // ����Ͷ����־
        String logOID = feeChargeService.getChargeLogOID();
        
        // ��װ����
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// �ֽ�Ͷ����־
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        //���ͽ��
        selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(presentFee));
        
        //����״̬0����ʼ״̬������ʧ��
        selfCardPayLogVO.setPresentStatus("0");
        //add by sWX219697 2014-12-26 11:18:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        
        // �ն���ˮ(�ն�id+�ֽ�ɷ���ˮ ��ȡ��20λ)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");// �������� �ɷѳɹ��󷵻�
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("�ɷ�֮ǰ��¼Ͷ����־");
        selfCardPayLogVO.setDealnum(""); // boss�ɷ���ˮ �ɷѳɹ��󷵻�
        selfCardPayLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷѣ�
        
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        // Change by hWX5316476  OR_huawei_201305_1248 20131011 NG4.0����������չܿظ�������_������������ն��ֽ�ɷ��ʽ��Ȩ Begin
        
        // �����������ն�businessid��Ӧ�Ŀ�Ŀ
        String businessid = (String)PublicCache.getInstance().getCachedData(Constants.SH_SUBJECT_AGENT);
        
        // �ֽ�ɷѴ����̿ۿ��
        String cashChageAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_CASHCHARGE_AGENT);
        
        // �ж������ն��Ƿ����ڴ����̵�orgtypeֵ
        String orgtypeAgent = (String)PublicCache.getInstance().getCachedData(Constants.SH_ORGTYPE_AGENT);
        
        Map resultMap = null;
        
        // ��ȡTerminalInfoService����
        TerminalInfoService service = (TerminalInfoService)ApplicationContextUtil.getBean("terminalInfoService");

        // ��������������ն˵��ֽ�ɷѿ��ؿ��������Ҹ������ն����ڴ����̹����ģ����ô����̿ۿ�ӿ�ִ���ֽ�ɷѣ���������ֽ�ɷѽӿ�
        if("1".equals(cashChageAgent) && null != orgtypeAgent && orgtypeAgent.equals(service.getOrgtype(termInfo)))
        {
        	 resultMap = feeChargeBean.chargeCommitByAgent(termInfo,
        			 curMenuId,
                  servnumber,
                  CommonUtil.yuanToFen(tMoney) + "",
                  "Cash",
                  businessid,selfCardPayLogVO.getChargeLogOID(), CommonUtil.yuanToFen(presentFee));
        }
        else
        {
            //modufy begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            resultMap = feeChargeBean.chargeCommit("",termInfo,
            		curMenuId,
                servnumber,
                CommonUtil.yuanToFen(tMoney) + "",
                "Cash", selfCardPayLogVO.getChargeLogOID(), CommonUtil.yuanToFen(presentFee));
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
        }
        // Change by hWX5316476  OR_huawei_201305_1248 20131011 NG4.0����������չܿظ�������_������������ն��ֽ�ɷ��ʽ��Ȩ End
        
        // �ɷѳɹ�
        if (resultMap != null && resultMap.size() > 0)
        {
            Object obj = resultMap.get("returnObj");
            if (obj instanceof CTagSet)
            {
                // �Ժ����ɷѽӿڵķ���ֵ���н���
                CTagSet chargeInfo = (CTagSet)obj;
                
                dealNum = (String)chargeInfo.GetValue("dealNum");// ������ˮ
                dealTime = (String)chargeInfo.GetValue("dealTime");// ����ʱ��
                
                //���ͻ�����ˮ��
                presentOid = (String)chargeInfo.GetValue("presentOid");
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
            
            //������ˮ���жϽ���״̬��
            selfCardPayLogVO.setStatus(StringUtils.isBlank(dealNum) ? "10" : "11");
            selfCardPayLogVO.setDescription("���ѳ�ֵ�ֽ�ɷѳɹ���");
            selfCardPayLogVO.setDealnum(dealNum);
            
            //add by sWX219697 2014-12-26 11:14:04 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            //����״̬��1�����ͳɹ���0������ʧ�ܣ��������ͽ��Ϊ0ʱ������ˮ��Ϊ�գ������ͽ��Ϊ0ʱ״̬��Ϊ1
            selfCardPayLogVO.setPresentStatus("0".equals(presentFee) ? "1" : (StringUtils.isBlank(presentOid) ? "0" : "1"));
            
            //������ˮ��
            selfCardPayLogVO.setPresentOid(presentOid);
            //add by sWX219697 2014-12-26 11:14:10 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            
         // add begin yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� tMoney
            String canRegardFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_AWARDSWITCH_HUB);
            if("1".equals(canRegardFlag)) actId=feeChargeService.getActId(payType, tMoney);
           
         
         // add end yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 

            forward = "success";
            
            // ȡBOSSϵͳ����_�Ƿ��Ͷ�����֤�� 1:���� 0:������
            String paramid = "sh_prtinvoicechk";
            Map retMap = feeChargeBean.getParamValue(termInfo, curMenuId, paramid);
            
            if (retMap != null && retMap.size() > 0)
            {
                Object object = retMap.get("returnObj");
                if (object instanceof CTagSet)
                {
                    // �Ժ����ɷѽӿڵķ���ֵ���н���
                    CTagSet ctagset = (CTagSet)object;
                    needRandomPwd = (String)ctagset.GetValue("paramvalue");// ����ֵ
                }
            }
            else
            {
                needRandomPwd = "1";
            }
            
            // ��¼�ɷѳɹ���־
            // Change by LiFeng �޸�RECFORMNUMֵ������ sh_charge_log��
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(tMoney), 
            		"0", "�ɷ�:�����ն˽ɷѳɹ�!");
            
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(presentFee), 
            		"0", "�ɷ�:�����ն����ͽ��ɹ�!");

            //��ѯ4G����ʣ�����
            getCardTypeAndAviIntegral(termInfo);
        }
        // �ɷ�ʧ��
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("���ѳ�ֵ�ֽ�ɷ�ʧ�ܣ�");
            selfCardPayLogVO.setDealnum("");
            
            forward = "error";
            setErrormessage("���ѳ�ֵ�ֽ�ɷ�ʧ�ܣ�");
            
            // ��¼�ɷ�ʧ����־
            // Change by LiFeng �޸�RECFORMNUMֵ������ sh_charge_log��
            this.createRecLog(servnumber,
                    Constants.PAY_BYCASH,
                    logOID,
                    CommonUtil.yuanToFen(tMoney),
                    "1",
                    "�ɷ�:�����ն˽ɷ�ʧ��!");
            
            this.createRecLog(servnumber, Constants.PAY_BYCASH, logOID, CommonUtil.yuanToFen(presentFee), 
            		"1", "�ɷ�:�����ն����ͽ��ʧ��!");
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cashChargeCommit end");
        
        return forward;
    }

    private void getCardTypeAndAviIntegral(TerminalInfoPO termInfo)
    {
        //add begin liutao00194861 2016-8-16 OR_HUB_201603_1191 ��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��
        //�ɷѳɹ����û��ֲ�ѯ���Ƿ�4G���ӿ�
        isNot4GCard = feeChargeBean.qryIs4GCard(termInfo, servnumber, curMenuId);
        //availIntegral=feeChargeBean.qryAvilInteral(termInfo,servnumber,curMenuId);
        //add end liutao00194861 2016-8-16 OR_HUB_201603_1191 ��BOSS�������������ն���ʾ�����Ż������ŵ�ΰ��

        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        // ���ýӿڲ�ѯ������Ϣ
        Map result = qryScoreBean.queryScoreValue(termInfo, customer, curMenuId);
        if (result != null && result.size() > 1)
        {
            String scoreStr = (String)result.get("returnObj");
            String[] scores = scoreStr.split("~");
            //���û���
            availIntegral = scores[1];
        }
    }


    /**
     * �ֽ𽻷��쳣����
     * 
     * @return
     * @see
     */
    public String goCashError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError Starting ...");
        }
        
        this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        if (Constants.PAY_BYCASH.equals(payType))
        {
            selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
        }
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        //add begin sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        //���ͽ��
        selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
        
        //����״̬��0������ʧ��
        selfCardPayLogVO.setPresentStatus("0");
        //add end sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        
        if (terminalSeq == null || "".equals(terminalSeq.trim()))
        {
            selfCardPayLogVO.setTerminalSeq("");
        }
        else
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
            
            selfCardPayLogVO.setTerminalSeq(terminalSeq);
        }
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setAcceptType("");
        selfCardPayLogVO.setServRegion(servRegion);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷѣ�
        
        if (tMoney == null || "".equals(tMoney.trim()) || "0".equals(tMoney.trim()))
        {
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        else
        {
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setDealnum("");
        }
        
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    /**
     * ת�����п��ɷѽ��ѡ��ҳ��
     * 
     * @return
     */
    public String cardCharge()
    {
        return "otherFee";
    }
    
    /**
     * ѡ�񽻷ѽ��
     * 
     * @return
     * @see
     */
    public String selectOtherFee()
    {
        return "selectOtherFee";
    }
    
    /**
     * �ֹ�����ɷѽ��
     * 
     * @return
     */
    public String toInputMoney()
    {
        return "toInputMoney";
    }
    
    /**
     * ת�����п��ɷ���������ҳ��
     * 
     * @return
     */
    public String dutyInfo()
    {
        return "dutyInfo";
    }
    
    /**
     * ת������п�ҳ��
     * 
     * @return
     */
    public String toReadCard()
    {
        return "toReadCard";
    }
    
    /**
     * ������������������ҳ��
     * 
     * @return
     * @see
     */
    public String inputCardPwd()
    {
        return "inputPwd";
    }
    
    /**
     * ת��ȷ�����п��ɷѽ��ҳ��
     * 
     * @return
     */
    public String toMakeSure()
    {
        return "makeSure";
    }
    
    /**
     * �ۿ�֮ǰ�����������ɷ���־
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        String xml = "";
        
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 Begin
        try
        {
            writer = response.getWriter();
            // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907
            
            HttpSession session = request.getSession();
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            String payType = (String)request.getParameter("paytype");
            String status = (String)request.getParameter("status");
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            String posNum = (String)request.getParameter("posnum");
            String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
            
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
            // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907
            /*
             * String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
             * if (CommonUtilHub.strIsEmpty(chargeOperID)) { xml = "0";
             * 
             * logger.error("��ȡ�������ɷѹ����쳣,chargeOperID:[" + chargeOperID + "]."); } else {
             */
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
            
            // ��װ��־����
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            
            String logOID = feeChargeService.getChargeLogOID();
            cardChargeLogVO.setChargeLogOID(logOID);
            
            cardChargeLogVO.setRegion(termInfo.getRegion());
            cardChargeLogVO.setTermID(termInfo.getTermid());
            
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
            // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907
            // cardChargeLogVO.setOperID(chargeOperID);
            
            cardChargeLogVO.setOperID(termInfo.getOperid());
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
            
            cardChargeLogVO.setServnumber(servnumber);
            cardChargeLogVO.setPayType(payType);
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            
            //add begin sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            //���ͽ��
            cardChargeLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
            
            //����״̬��0������ʧ��
            cardChargeLogVO.setPresentStatus("0");
            //add end sWX219697 2015-1-6 16:54:56 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            
            cardChargeLogVO.setUnionpayuser("");
            cardChargeLogVO.setUnionpaycode("");
            cardChargeLogVO.setBusiType("");

            //modify begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            //�����ݿ��������ܺ����������
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardnumber));
            //modify end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242

            cardChargeLogVO.setBatchnum("");
            cardChargeLogVO.setAuthorizationcode("");
            cardChargeLogVO.setBusinessreferencenum("");
            cardChargeLogVO.setUnionpaytime("");
            cardChargeLogVO.setVouchernum("");
            cardChargeLogVO.setUnionpayfee("");
            cardChargeLogVO.setTerminalSeq(terminalSeq);
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            
            cardChargeLogVO.setStatus(status);
            cardChargeLogVO.setDescription(description);
            cardChargeLogVO.setDealnum("");
            cardChargeLogVO.setAcceptType("");
            cardChargeLogVO.setServRegion(servRegion);
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            cardChargeLogVO.setPosNum(posNum);
            cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
            
            cardChargeLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ�
                                                // favourable���Żݽɷѣ�
            
            // ����ɷ���־
            feeChargeService.addChargeLog(cardChargeLogVO);
            xml = "1~~" + logOID;
            // }
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
        }
        finally
        {
            // �����client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("�ر�writer�쳣��", e);
                    
                    throw new Exception("�ۿ�֮ǰ��¼��־ʧ��");
                }
            }
        }
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 End
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog end!");
        }
        
        logger.debug("addCardPayLog End!");
    }
    
    /**
     * �ۿ�ɹ�֮�󣬸��½�����־
     * 
     * @throws Exception
     * @see
     */
    public void updateCardChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        // Chagne by LiFeng ����쳣�����˿����� 20110909 Begin
        String xml = "";
        try
        {
            writer = response.getWriter();
            // }
            // catch (IOException e)
            // {
            // throw new IOException("�ۿ�֮ǰ��¼��־ʧ��");
            // }
            
            String status = (String)request.getParameter("status");
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // ��װ��־����
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);
            cardChargeLogVO.setUnionpayuser(unionpayuser);
            cardChargeLogVO.setUnionpaycode(unionpaycode);
            
            busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
            cardChargeLogVO.setBusiType(busiType);
            
            cardChargeLogVO.setBatchnum(batchnum);
            cardChargeLogVO.setAuthorizationcode(authorizationcode);
            cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
            cardChargeLogVO.setUnionpaytime(unionpaytime);
            cardChargeLogVO.setVouchernum(vouchernum);
            
            // modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
            unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
            // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
            
            cardChargeLogVO.setUnionpayfee(unionpayfee);
            
            cardChargeLogVO.setStatus(status);
            cardChargeLogVO.setDescription(description);
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);
            
            // add by xkf57421 for XQ[2011]_10_082 begin
            cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);
            // add by xkf57421 for XQ[2011]_10_082 end
            
            // Chagne by LiFeng ����쳣�����˿����� 20110909
            // String xml = "";
            // try
            // {
            // ����ɷ���־
            feeChargeService.updateCardChargeLog(cardChargeLogVO);
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
        }
        finally
        {
            // �����client
            if (writer != null)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("�ر�writer�쳣��", e);
                    
                    throw new Exception("�ۿ�֮ǰ��¼��־ʧ��");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog end!");
        }
    }
    
    /**
     * ���п��ɷ��ύ
     * 
     * @return
     */
    public String cardChargeCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit start");
        }
        
        String forward = null;
        
        // �趨�ɷѷ�ʽ
        this.payType = Constants.PAY_BYCARD;
        
        HttpSession session = getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        tMoney = Integer.parseInt(tMoney) + "";
        
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 Begin
        
        // String tempTermOperid = termInfo.getOperid();
        
        try
        {
            //add by sWX219697 2014-12-26 11:31:25 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        	//�����û������ͽ�Ԫ
        	presentFee = calculateFee(CommonUtil.fenToYuan(tMoney));
        	
            //���ͻ�����ˮ��
            String presentOid = "";
            //add end sWX219697 2014-12-26 11:31:30 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
        	
            // ���ݵ��л�ȡ���⹤��
            // String chargeOperID = CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER);
            
            // ���ø��������⹤��
            // termInfo.setOperid(chargeOperID);
            
            // session.setAttribute(Constants.TERMINAL_INFO, termInfo);
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
            
            //modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            // ���п��ɷ�
            Map resultMap = feeChargeBean.chargeCommit("",termInfo, curMenuId, servnumber, tMoney, 
            		"Card", chargeLogOID, CommonUtil.yuanToFen(presentFee));
            //modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            
            // �ɷѳɹ�
            if (resultMap != null && resultMap.size() > 0)
            {
                Object obj = resultMap.get("returnObj");
                if (obj instanceof CTagSet)
                {
                    // �Ժ����ɷѽӿڵķ���ֵ���н���
                    CTagSet chargeInfo = (CTagSet)obj;
                    
                    dealNum = (String)chargeInfo.GetValue("dealNum");// �������
                    dealTime = (String)chargeInfo.GetValue("dealTime");// �������
                    
                    //���ͻ�����ˮ��
                    presentOid = (String)chargeInfo.GetValue("presentOid");
                }
                
                selfCardPayLogVO.setRecdate(CommonUtil.formatDate(dealTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                
                //������ˮ���жϽ���״̬��11�����ѳɹ���10���ۿ�ɹ�������ʧ��
                selfCardPayLogVO.setStatus(StringUtils.isBlank(dealNum) ? "10" : "11");
                selfCardPayLogVO.setDescription("�������ɷѳɹ���");
                selfCardPayLogVO.setDealnum(dealNum);
                
                //add by sWX219697 2014-12-26 11:11:31 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                //������ˮ���ж�����״̬��1�����ͳɹ���0������ʧ�ܣ��������ͽ��Ϊ0ʱ������ˮ��Ϊ�գ������ͽ��Ϊ0ʱ״̬��Ϊ1
                selfCardPayLogVO.setPresentStatus("0".equals(presentFee) ? "1" : (StringUtils.isBlank(presentOid) ? "0" : "1"));
                
                //������ˮ��
                selfCardPayLogVO.setPresentOid(presentOid);
                //add end sWX219697 2014-12-26 11:11:55 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                
                tMoney = CommonUtil.fenToYuan(tMoney);
                // add begin yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� tMoney
                String canRegardFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_AWARDSWITCH_HUB);
                if("1".equals(canRegardFlag))  actId=feeChargeService.getActId(payType, tMoney);
               
              
              // add end yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 
                
                forward = "success";
                
                // ȡBOSSϵͳ����_�Ƿ��Ͷ�����֤�� 1:���� 0:������
                String paramid = "sh_prtinvoicechk";
                Map retMap = feeChargeBean.getParamValue(termInfo, curMenuId, paramid);
                if (retMap != null && retMap.size() > 0)
                {
                    Object object = retMap.get("returnObj");
                    if (object instanceof CTagSet)
                    {
                        // �Ժ����ɷѽӿڵķ���ֵ���н���
                        CTagSet ctagset = (CTagSet)object;
                        
                        needRandomPwd = (String)ctagset.GetValue("paramvalue");// ����ֵ
                    }
                }
                else
                {
                    needRandomPwd = "1";
                }
                
                // ��¼�ɷѳɹ���־
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "0",
                        "�ɷ�:�����ն��������ɷѳɹ�!");
                
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(presentFee),
                        "0",
                        "�ɷ�:�����ն��������ɷ����ͽ��ɹ�!");

                //��ѯ4G����ʣ�����
                getCardTypeAndAviIntegral(termInfo);
            }
            // �ɷ�ʧ��
            else
            {
                tMoney = CommonUtil.fenToYuan(Integer.parseInt(tMoney) + "");
                
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("10");
                selfCardPayLogVO.setDescription("���ѳ�ֵ�������ɷ�ʧ�ܣ�");
                selfCardPayLogVO.setDealnum("");
                
                forward = "error";
                setErrormessage("���ѳ�ֵ�������ɷ�ʧ�ܣ�");
                
                // ��¼�ɷ�ʧ����־
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(tMoney),
                        "1",
                        "�ɷ�:�����ն��������ɷ�ʧ��!");
                
                this.createRecLog(servnumber,
                        Constants.PAY_BYCARD,
                        chargeLogOID,
                        CommonUtil.yuanToFen(presentFee),
                        "0",
                        "�ɷ�:�����ն��������ɷ����ͽ��ʧ��!");
            }
            
            //add by xkf57421 for XQ[2011]_10_082 begin 
            selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
            //add by xkf57421 for XQ[2011]_10_082 end
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        catch (Exception e)
        {
            logger.error(e);
            e.printStackTrace();
            errormessage = "�Բ���,�ύ�ɷ�ʱ�����쳣,��ƾСƱ��Ӫҵ����ѯ�Ƿ�ɷѳɹ�,ллʹ��.";
            forward = "cardErrorPage";
        }
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
        // finally
        // {
        // ��session����ն���Ϣ�Ļ�
        // termInfo.setOperid(tempTermOperid);
        // session.setAttribute(Constants.TERMINAL_INFO, termInfo);
        // }
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 End
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardChargeCommit end");
        }
        
        return forward;
    }
    
    /**
     * ȡ�����п��ɷ�
     * 
     * @return
     */
    public String cancelCardCharge()
    {
        return "cancelCardCharge";
    }
    
    /**
     * �����������쳣����
     * 
     * @return
     * @see
     */
    public String goCardError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError Starting ...");
        }
        
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 Begin
        
        HttpSession session = getRequest().getSession();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
        // String tempTermOperid = termInfo.getOperid();
        
        try
        {
            /*
             * if (Constants.PAY_BYCARD.equals(payType)) { //String chargeOperID =
             * CommonUtilHub.getDictNameByID(termInfo.getRegion(), ConstantsHub.CARD_CHARGE_OPER); if
             * (CommonUtilHub.strIsEmpty(chargeOperID)) { errormessage = errormessage + "��ȡ�����������ɷ�ָ������"; } else { //
             * ����session����ն˶�Ӧ�Ĺ��� termInfo.setOperid(chargeOperID); session.setAttribute(Constants.TERMINAL_INFO,
             * termInfo); } }
             */
            // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
            
            this.createRecLog(servnumber, payType, "0", "0", "1", errormessage);
            
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(date);
            
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            
            if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
            {
                selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
                selfCardPayLogVO.setRegion(termInfo.getRegion());
                selfCardPayLogVO.setTermID(termInfo.getTermid());
                selfCardPayLogVO.setOperID(termInfo.getOperid());
                selfCardPayLogVO.setServnumber(servnumber);
                if (Constants.PAY_BYCASH.equals(payType))
                {
                    selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
                }
                else if (Constants.PAY_BYCARD.equals(payType))
                {
                    selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
                }
                
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
                
                //add begin sWX219697 2015-1-6 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                //���ͽ��
                selfCardPayLogVO.setPresentFee(CommonUtil.yuanToFen(calculateFee(tMoney)));
                
                //����״̬��Ĭ��Ϊ0������ʧ�ܡ����ͳɹ���1
                selfCardPayLogVO.setPresentStatus("0");
                //add begin sWX219697 2015-1-6 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                
                selfCardPayLogVO.setTerminalSeq("");
                selfCardPayLogVO.setRecdate(payDate);
                selfCardPayLogVO.setStatus("00");
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                selfCardPayLogVO.setAcceptType("");
                selfCardPayLogVO.setServRegion(servRegion);
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());
                selfCardPayLogVO.setRecType("phone");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷѣ�
                
                feeChargeService.addChargeLog(selfCardPayLogVO);
            }
            // ������־
            else
            {
                selfCardPayLogVO.setChargeLogOID(chargeLogOID);
                
                if (tMoney == null || "".equals(tMoney.trim()))
                {
                    selfCardPayLogVO.setStatus("00");
                }
                else
                {
                    selfCardPayLogVO.setStatus("10");
                }
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                
                //Change by LiFeng [OR_HUB_201112_1021]�����ն�-��������BUG[BUGת����] 20120104 Begin
                //�����ն˺�
                selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
                
                //�����̻���
                selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
                //Change by LiFeng [OR_HUB_201112_1021]�����ն�-��������BUG[BUGת����] 20120104 End
                
                // add by xkf57421 for XQ[2011]_10_082 begin
                selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
                // add by xkf57421 for XQ[2011]_10_082 end
                
                feeChargeService.updateChargeLog(selfCardPayLogVO);
            }
        }
        // Change by LiFeng �޸��쳣�����˿����� 20110909 Begin
        catch (Exception e)
        {
            // ����һ���쳣,ʹҳ��������˿�ҳ��
            logger.error(e);
            e.printStackTrace();
            errormessage = errormessage + e.getMessage();
        }
        // Change by LiFeng �޸��쳣�����˿����� 20110909 End
        
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 Begin
        /*
         * finally { // ��session����ն���Ϣ�Ļ� termInfo.setOperid(tempTermOperid);
         * session.setAttribute(Constants.TERMINAL_INFO, termInfo); }
         */
        // Change by LiFeng �޸��������ɷ�ʹ�����⹤�� 20110907 End
        // Change by LiFeng OR_HUB_201110_147 ��������-�����ն�-�����ɷ������������ �����ն˸Ļ��ն˻���Ӧ���Žɷ� 20111108 End
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        return "cardErrorPage";
    }
    
    /**
     * ��¼��Ʊ��ӡ��־
     * 
     * @see
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String cycle = request.getParameter("cycle");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintRecord record = new InvoicePrintRecord();
        record.setServNumber(servNumber);
        record.setCycle(cycle);
        record.setTermID(termInfo.getTermid());
        
        feeChargeService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
//    /**
//     * ������ϸ����Ʊע�ͽ���
//     * 
//     * @param consumeList ������ϸ
//     * @param comments ��Ʊע��
//     * @return
//     */
//    private String parseConsumeList(String consumeList, String comments)
//    {
//        String[] consumeArr = consumeList.replace("|", ",").split(";");
//        StringBuffer tmpConsumeList = new StringBuffer();
//        int rowNum = 13;
//        int row = 0;
//        for (int i = 0; i < consumeArr.length && row < rowNum; i++)
//        {
//            if (consumeArr[i].trim().length() <= 3)
//            {
//                continue;
//            }
//            
//            String tmpArr[] = consumeArr[i].split(",");
//            if (!CommonUtil.isEmpty(tmpArr[0]))
//            {
//                tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[0].trim(), 15));
//                
//                if (i % 2 == 0)
//                {
//                    if (tmpArr.length > 1)
//                    {
//                        tmpConsumeList.append(CommonUtil.fillRightBlank(tmpArr[1].trim(), 8));
//                    }
//                    else
//                    {
//                        tmpConsumeList.append(CommonUtil.fillRightBlank("", 8));
//                    }
//                }
//                else
//                {
//                    if (tmpArr.length > 1)
//                    {
//                        tmpConsumeList.append(tmpArr[1].trim()).append("\\n");
//                    }
//                    else
//                    {
//                        tmpConsumeList.append("\\n");
//                    }
//                }
//                
//                if (i % 2 == 0)
//                {
//                    row++;
//                }
//            }
//        }
//        
//        if (consumeArr.length % 2 != 0)
//        {
//            tmpConsumeList.append("\\n");
//        }
//        
//        if (comments != null && !"".equals(comments.trim()))
//        {
//            tmpConsumeList.append(comments.trim()).append("\\n");
//            
//            if (comments.trim().length() % 26 == 0)
//            {
//                row += (comments.trim().length() / 26);
//            }
//            else
//            {
//                row = row + (comments.trim().length() / 26) + 1;
//            }
//        }
//        
//        while (row < rowNum)
//        {
//            tmpConsumeList.append("\\n");
//            row++;
//        }
//        
//        return tmpConsumeList.toString();
//    }
    
    /**
     * ����Ʊ������֯��xml
     * 
     * @param list ��Ʊ����
     * @return
     * @see
     */
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                        .append(subItemKey)
                        .append("><![CDATA[")
                        .append(subItemValue)
                        .append("]]></")
                        .append(subItemKey)
                        .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        
        return xmlStr.toString();
    }
    
    /**
     * ������ӡ��Ʊ��Ҫ�����������֤
     * 
     * @return
     * @see
     */
    public String validateByRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd Starting ...");
        }
        
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        request.setAttribute("invoiceType", invoiceType);
        request.setAttribute("dealNum", dealNum);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �����������
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // ��������������
        String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD_CONTENT");
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        if (!feeChargeBean.sendRandomPwd(servnumber, termInfo, shortMessage, curMenuId))
        {
            logger.error("���û�" + servnumber + "��������������ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", "��Ʊ��ӡ���ܣ����������ŷ���ʧ�ܡ�");
            
            this.getRequest().setAttribute("errormessage", "���������ŷ���ʧ�ܣ����ܴ�ӡ��Ʊ��");
        }
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("���û�" + servnumber + "�������������ųɹ��������" + randomPwd);
            }
            
            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * ���������֤����֤ͨ���󣬴�ӡ��Ʊ
     * 
     * @return
     * @see
     */
    public String printInvoiceWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            
            // ���ýӿڴ�ӡ��Ʊ
            request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
            
            if (logger.isInfoEnabled())
            {
                logger.info("��Ʊ��ӡ���ܣ��û�" + servnumber + "ʹ��������������֤�ɹ�");
            }
        }
        else
        {
            forward = "error";
            
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "���������������Ѿ���ʱ���뷵�����Ի��߽�������������";
            }
            else
            {
                // modify begin g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
                errorMsg = getConvertMsg("�����������������������롣", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 ������������������ʾ��Ϣ����
            }
            
            logger.error("��Ʊ��ӡ���ܣ��û�" + servnumber + "����������������ʱ����֤ʧ��");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * �������������֤��ֱ�Ӵ�ӡ��Ʊ
     * 
     * @return
     * @see
     */
    public String printInvoiceWithoutPwd()
    {
        HttpServletRequest request = this.getRequest();
        
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        
        // ���ýӿڴ�ӡ��Ʊ
        request.setAttribute("invoice", getXmlStr(printInvoice(invoiceType, dealNum)));
        
        return "print";
    }
    
    /**
     * ���ýӿڴ�ӡ��Ʊ
     */
    private List printInvoice(String invoiceType, String dealNum)
    {
        HttpServletRequest request = this.getRequest();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �ṩ��Ʊ��ӡ����ʱ����ȡ��Ʊ��Ϣ
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = feeChargeBean.getInvoiceData(termInfo, curMenuId, servnumber, dealNum);
            
            if (invoiceData != null && invoiceData.size() > 0)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof Vector)
                {
                    Vector invoiceVector = (Vector)invoiceObj;
                    
                    CTagSet tagSet = (CTagSet)invoiceVector.get(0);
                    String cycleCount = (String)tagSet.GetValue("invoice_cnt");
                    int rowNum = Integer.parseInt(cycleCount);
                    
                    CRSet crset = (CRSet)invoiceVector.get(1);
                    
                    List invoicesList = new ArrayList();
                    
                    // ��ӡȫ����Ʊ���ж��ŷ�Ʊ��Ϣ��ӡ����
                    if ("ALL".equals(invoiceType))
                    {
                        Map invoiceMap = null;
                        for (int i = 0; i < rowNum; i++)
                        {
                            invoiceMap = new HashMap();
                            
                            // �����ص�CRSetת�����ַ���
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (tmpArray.length == 2)
                                {
                                    invoiceMap.put(tmpArray[0], tmpArray[1]);
                                }
                            }
                            invoicesList.add(invoiceMap);
                        }
                    }
                    // ��ӡ��ǰ��Ʊ���ж��ŷ�Ʊ��ӡ���һ��
                    else if ("Last".equals(invoiceType))
                    {
                        Map invoiceMap = new HashMap();// ��ֵ����ʽ�洢һ�ŷ�Ʊ���ݸ���������
                        
                        // �����ص�CRSetת�����ַ���
                        String invoice = crset.GetValue(rowNum - 1, 0) + "," + crset.GetValue(rowNum - 1, 1);
                        String[] invoiceArray = invoice.split(",");
                        for (int j = 0; j < invoiceArray.length; j++)
                        {
                            String tmpData = invoiceArray[j];
                            String[] tmpArray = tmpData.split("@~@");
                            if (tmpArray.length == 2)
                            {
                                invoiceMap.put(tmpArray[0], tmpArray[1]);
                            }
                        }
                        invoicesList.add(invoiceMap);
                    }
                    // ��ӡ���ŷ�Ʊ���ж��ŷ�Ʊ��Ҫ��ʾ���·�Ϊĳ��~ĳ�£����Ϊ���ŷ�Ʊ���ܽ������ֶ���ʾ���һ�ŷ�Ʊ����Ϣ
                    else if ("Single".equals(invoiceType))
                    {
                        float total = 0; // �����ʵĽ���ܺ�
                        String tmpStar = ""; // ��ʼ����
                        HashMap subMap = new HashMap();// ��ֵ����ʽ�洢һ�ŷ�Ʊ���ݸ���������
                        for (int i = 0; i < rowNum; i++)
                        {
                            String invoice = crset.GetValue(i, 0) + "," + crset.GetValue(i, 1);
                            String[] invoiceArray = invoice.split(",");
                            for (int j = 0; j < invoiceArray.length; j++)
                            {
                                String tmpData = invoiceArray[j];
                                String[] tmpArray = tmpData.split("@~@");
                                if (rowNum - 1 == 0)
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        subMap.put(tmpArray[0], tmpArray[1]);
                                        // System.out.println("��Ʊ����Ϣ��"+tmpArray[0]+":"+tmpArray[1]);
                                    }
                                }
                                else
                                {
                                    if (tmpArray.length == 2)
                                    {
                                        if ("invoicefee".equals(tmpArray[0]))
                                        {
                                            total += Float.parseFloat((tmpArray[1].substring(0,
                                                    tmpArray[1].length() - 1)));
                                        }
                                        if (i == 0 && "callfeeCreateTime".equals(tmpArray[0]))
                                        {
                                            tmpStar = tmpArray[1];
                                        }
                                        if (i == rowNum - 1)
                                        {
                                            if ("invoicefee".equals(tmpArray[0]) || "invoicefeehj".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = CommonUtil.round(String.valueOf(total)) + "Ԫ";
                                            }
                                            if ("callfeeCreateTime".equals(tmpArray[0]))
                                            {
                                                tmpArray[1] = tmpStar + "����" + tmpArray[1];
                                            }
                                            subMap.put(tmpArray[0], tmpArray[1]);
                                            // System.out.println("��Ʊ����Ϣ��"+tmpArray[0]+":"+tmpArray[1]);
                                        }
                                    }
                                }
                            }
                        }
                        invoicesList.add(subMap);
                    }
                    return invoicesList;
                }
            }
        }
        return null;
    }
    
    // add by LiFeng [XQ[2011]_11_062]�����ն�-�ظ��ɷѿ��� 20111124 Begin
    /**
     * �ɷ��쳣���ɲ鿴�ɷ���ʷ��������������֤
     */
    public String goQryFeeHistory()
    {
        //����û���Ϣ
        getRequest().getSession().removeAttribute(Constants.USER_INFO);
        
        return SUCCESS;
    }
    
    /**
     * ��֤��ˮ��
     * 
     * @param termInfo
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {
        
        String seq = termInfo.getTermid().concat(terminalSeq);
        
        String tmpSeq = seq.concat(servnumber);
        
        // �������ͬ�Ĵ��������ظ��ɷ�
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            String recDate = DateUtilHub.getCurrentDateTime();
            
            dealTime = recDate;
            
            String tmpErrorMsg = "�ظ��ɷ�:�ֻ���[".concat(servnumber)
                    .concat("],Ͷ�ҽ��[")
                    .concat(tMoney)
                    .concat("]Ԫ,����Ӫҵ��[")
                    .concat(termInfo.getOrgname())
                    .concat("],��ˮ��[")
                    .concat(seq)
                    .concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(servnumber);
            // �ֽ�Ͷ��
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            // �ֽ�ɷ���ˮ,�ն�id+����������ˮ
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(recDate);
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // ��¼�ظ��ɷ���־
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // ��¼��SH_REC_LOG,������ˮ�������¼�ֽ�ɷ���ˮ��
            this.createRecLog(servnumber, Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
        
    }
    
    // add by LiFeng [XQ[2011]_11_062]�����ն�-�ظ��ɷѿ��� 20111124 End
//
    /**
     *��ѳ齱
     * @remark create yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 
     */
    public void  mianFeiChouJiang() throws IOException
    {
       
        StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
        resBuff.append("<info>");
        Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // �ն˻���Ϣ
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            { HttpServletResponse response = getResponse();
              response.setContentType("text/xml;charset=GBK");
              request.setCharacterEncoding("GBK");
              writer = response.getWriter();
                 
             
                CTagSet res = feeChargeBean.mianFeiChouJiang(customerSimp, terminalInfo,actId,curMenuId);
               
              
                if(res!=null){
                    String CJ_RESULT=res.GetValue("CJ_RESULT");
                    String logInfo="";
                    if(CJ_RESULT==null||CJ_RESULT.equals("")){
                        throw new Exception();
                        
                    }else if(CJ_RESULT.equals("910002")){
                        logInfo="��Ǹ: ����ָ����Ӫ���ͻ�Ⱥ��";
                        
                       
                    }else if(CJ_RESULT.equals("910003")){
                        logInfo="��Ǹ: ���ĵ�ǰ����Ϊ��"+(res.GetValue("CJUSERJF")==null?"":res.GetValue("CJUSERJF"))+"�����Ļ��ֲ��㣬������������";
                      
                      
                       
                    }else if(CJ_RESULT.equals("910004")){
                        logInfo="��Ǹ:�齱�����Ѵﵽ����������ƣ�";
                       
                       
                    }else if(CJ_RESULT.equals("-2")){
                        logInfo="û�в鵽�˳齱�������(�˳齱�������)!";
                       
                        
                    }else{
                        logInfo=res.GetValue("PROMPTING")==null?"":res.GetValue("PROMPTING");
                       
                        
                    }
                  
                    resBuff.append(logInfo);
                    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "0", logInfo);
                    
                }else{
                    throw new Exception();
                    
                }
                
               }
            catch(Exception e)
            {    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "1", "��ѳ齱�����쳣��");
                   resBuff.append("��ѳ齱�����쳣��");
                   
              
                 
                
            }finally{
                
                if(null != writer)
                    {   
                    resBuff.append("</info>");
                    writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
    
    //
    /**
     *��ѳ齱
     * @remark create yKF73963   2013-10-15 �����ն�-����ר��   
     */
    public void  mianFeiChouJiangNew() throws IOException
    {
       
        StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
        resBuff.append("<info>");
        Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // �ն˻���Ϣ
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            { HttpServletResponse response = getResponse();
              response.setContentType("text/xml;charset=GBK");
              request.setCharacterEncoding("GBK");
              writer = response.getWriter();
              String logInfo="";
              ReturnWrap rw = feeChargeBean.mianFeiChouJiangNew(customerSimp, terminalInfo,actId,curMenuId);
            if(rw.getStatus()==0){
                
                logInfo=rw.getReturnMsg();
                resBuff.append(logInfo);
                
            }else{
                
                
          
                CTagSet res = (CTagSet)rw.getReturnObject();
               
              
                if(res!=null){
                    String CJ_RESULT=res.GetValue("CJ_RESULT");
                   
                    if(CJ_RESULT==null||CJ_RESULT.equals("")){
                        throw new Exception();
                        
                    }else if(CJ_RESULT.equals("910002")){
                        logInfo="��Ǹ: ����ָ����Ӫ���ͻ�Ⱥ��";
                        
                       
                    }else if(CJ_RESULT.equals("910003")){
                        logInfo="��Ǹ: ���ĵ�ǰ����Ϊ��"+(res.GetValue("CJUSERJF")==null?"":res.GetValue("CJUSERJF"))+"�����Ļ��ֲ��㣬������������";
                      
                      
                       
                    }else if(CJ_RESULT.equals("910004")){
                        logInfo="��Ǹ:�齱�����Ѵﵽ����������ƣ�";
                       
                       
                    }else if(CJ_RESULT.equals("-2")){
                        logInfo="û�в鵽�˳齱�������(�˳齱�������)!";
                       
                        
                    }else{
                        logInfo=res.GetValue("PROMPTING")==null?"":res.GetValue("PROMPTING");
                       
                        
                    }
                  
                    resBuff.append(logInfo);
                    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "0", logInfo);
                    
                }else{
                    throw new Exception();
                    
                }
                
               }
            }
            catch(Exception e)
            {    this.createRecLog(Constants.MIANFEICHOUJIANG, "0", "0", "1", "�齱�����쳣��");
                   resBuff.append("�齱�����쳣��");
                   
              
                 
                
            }finally{
                
                if(null != writer)
                    {   
                    resBuff.append("</info>");
                    writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
    
    /**
     * <�����û��ĳ�ֵ���������ͷ���>
     * <������ϸ����>
     * @param chargeFee �û���ֵ����λ��Ԫ
     * @return ���ͷ��ã���λ��Ԫ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-12-26 10:57:23 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
     */
    private String calculateFee(String chargeFee)
    {
    	//����ֵ���Ϊ�գ���ֱ�ӷ���
    	if(StringUtils.isBlank(chargeFee))
    	{
    		return "0";
    	}
    	
        //��ֵ�����ڼ������ͽ���λ��Ԫ
        double chargeFeeUnit = 100;
        
        //���ͽ�λ���磬ÿ��ֵ100Ԫ����1.5Ԫ��Ĭ��Ϊ0����������
        double presentFeeUnit = 0;
        
        try 
        {
        	chargeFeeUnit = Double.parseDouble(CommonUtil.getParamValue("SH_PAY_FEE"));
			
        	presentFeeUnit = Double.parseDouble(CommonUtil.getParamValue("SH_PRESENT_FEE"));
		} 
        catch (NumberFormatException e) 
        {
			logger.error("��������SH_PAY_FEE��SH_PRESENT_FEE���ô��󣬲������û����ѣ�", e);
		}
        
        //�������ͽ�� Ԫ
        double presentFee = Math.floor(Double.parseDouble(chargeFee)/chargeFeeUnit) * presentFeeUnit;
        
        BigDecimal zero = new BigDecimal("0");
        
        //����Ϊ0ʱ������0����λ��Ԫ
        return 0 == zero.compareTo(new BigDecimal(String.valueOf(presentFee))) ? "0" : String.valueOf(presentFee);
    }
    
    //
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }
    
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }
    
    public String getErrorType()
    {
        return errorType;
    }
    
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }
    
    public List getUsableTypes()
    {
        return usableTypes;
    }
    
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    
    public boolean isCanPayWithCash()
    {
        return canPayWithCash;
    }
    
    public void setCanPayWithCash(boolean canPayWithCash)
    {
        this.canPayWithCash = canPayWithCash;
    }
    
    public String getHasMultiInvoices()
    {
        return hasMultiInvoices;
    }
    
    public void setHasMultiInvoices(String hasMultiInvoices)
    {
        this.hasMultiInvoices = hasMultiInvoices;
    }
    
    public String getDealNum()
    {
        return dealNum;
    }
    
    public void setDealNum(String dealNum)
    {
        this.dealNum = dealNum;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public FeeChargeHubBean getFeeChargeBean()
    {
        return feeChargeBean;
    }
    
    public void setFeeChargeBean(FeeChargeHubBean feeChargeBean)
    {
        this.feeChargeBean = feeChargeBean;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String gettMoney()
    {
        return tMoney;
    }

    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
    }

    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getBalance()
    {
        return balance;
    }
    
    public void setBalance(String balance)
    {
        this.balance = balance;
    }
    
    public String getCardnumber()
    {
        return cardnumber;
    }
    
    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }
    
    public String getUnionpaycode()
    {
        return unionpaycode;
    }
    
    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    
    public String getUnionpayuser()
    {
        return unionpayuser;
    }
    
    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }

    
    public String getBatchnum()
    {
        return batchnum;
    }
    
    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
    }
    
    public String getUnionpayfee()
    {
        return unionpayfee;
    }
    
    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
    }
    
    public String getUnionpaytime()
    {
        return unionpaytime;
    }
    
    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }
    
    public String getAuthorizationcode()
    {
        return authorizationcode;
    }
    
    public void setAuthorizationcode(String authorizationcode)
    {
        this.authorizationcode = authorizationcode;
    }
    
    public String getBusinessreferencenum()
    {
        return businessreferencenum;
    }
    
    public void setBusinessreferencenum(String businessreferencenum)
    {
        this.businessreferencenum = businessreferencenum;
    }
    
    public String getVouchernum()
    {
        return vouchernum;
    }
    
    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }
    
    public FeeChargeHubService getFeeChargeService()
    {
        return feeChargeService;
    }
    
    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }
    
    public String getYingjiaoFee()
    {
        return yingjiaoFee;
    }
    
    public void setYingjiaoFee(String yingjiaoFee)
    {
        this.yingjiaoFee = yingjiaoFee;
    }
    
    public String getServRegion()
    {
        return servRegion;
    }
    
    public void setServRegion(String servRegion)
    {
        this.servRegion = servRegion;
    }
    
    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public String getChargeLogOID()
    {
        return chargeLogOID;
    }
    
    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }
    
    public String getDealTime()
    {
        return dealTime;
    }
    
    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }
    
    public String getNeedRandomPwd()
    {
        return needRandomPwd;
    }
    
    public void setNeedRandomPwd(String needRandomPwd)
    {
        this.needRandomPwd = needRandomPwd;
    }
    
    public String getPrintcontext()
    {
        return printcontext;
    }
    
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

	public String getInitPosResCode()
	{
		return initPosResCode;
	}

	public void setInitPosResCode(String initPosResCode)
	{
		this.initPosResCode = initPosResCode;
	}

	public String getCmtPosResCode()
	{
		return cmtPosResCode;
	}

	public void setCmtPosResCode(String cmtPosResCode)
	{
		this.cmtPosResCode = cmtPosResCode;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getErrPosResCode()
	{
		return errPosResCode;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setErrPosResCode(String errPosResCode)
	{
		this.errPosResCode = errPosResCode;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getActId()
    {
        return actId;
    }
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setActId(String actId)
    {
        this.actId = actId;
    }

	public String getPresentFee() {
		return presentFee;
	}

	public void setPresentFee(String presentFee) {
		this.presentFee = presentFee;
	}

    public String getBusiType()
    {
        return busiType;
    }

    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }

    public String getAvailIntegral()
    {
        return availIntegral;
    }

    public void setAvailIntegral(String availIntegral)
    {
        this.availIntegral = availIntegral;
    }

    public String getIsNot4GCard()
    {
        return isNot4GCard;
    }

    public void setIsNot4GCard(String isNot4GCard)
    {
        this.isNot4GCard = isNot4GCard;
    }

    public ScoreBean getQryScoreBean()
    {
        return qryScoreBean;
    }

    public void setQryScoreBean(ScoreBean qryScoreBean)
    {
        this.qryScoreBean = qryScoreBean;
    }

    public boolean isAgentParam()
    {
        return agentParam;
    }

    public void setAgentParam(boolean agentParam)
    {
        this.agentParam = agentParam;
    }

    public String getTermParam()
    {
        return termParam;
    }

    public void setTermParam(String termParam)
    {
        this.termParam = termParam;
    }

    public String getPayCerntWay()
    {
        return payCerntWay;
    }

    public void setPayCerntWay(String payCerntWay)
    {
        this.payCerntWay = payCerntWay;
    }

    public String getChargeMoney()
    {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney)
    {
        this.chargeMoney = chargeMoney;
    }

    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public String getErrorPayCenterMsg()
    {
        return errorPayCenterMsg;
    }

    public void setErrorPayCenterMsg(String errorPayCenterMsg)
    {
        this.errorPayCenterMsg = errorPayCenterMsg;
    }

    public String getCuNumbers()
    {
        return cuNumbers;
    }

    public void setCuNumbers(String cuNumbers)
    {
        this.cuNumbers = cuNumbers;
    }
  
}