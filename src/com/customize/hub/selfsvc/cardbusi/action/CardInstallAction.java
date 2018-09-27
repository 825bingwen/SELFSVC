/*
 * �� �� ��:  CardInstallAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  �������߿���(�հ׿�����)
 * �� �� ��: zKF69263
 * �޸�ʱ��:  2014-10-22
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.hub.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * �������߿���(�հ׿�����)
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-10-22]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_HUB_201405_478 ���������ն�֧���ֳ�д��������֮���߿���
 */
public class CardInstallAction extends CardBusiBaseAction {

    /**
     * ע������
     */
    private static final long serialVersionUID = 8336831060603314515L;

    /** 
     * ��־����
     */
    private static Log logger = LogFactory.getLog(CardInstallAction.class);
    
    /** 
     * ��Ʒ�б�
     */
    private List<ProdTempletPO> tpltTempletList;
    
    /**  ---------------- ���� ---------------------
     * 
     * ����
     */
    private String region;
    
    /** 
     * ��������
     */
    private String regionName;
    
    /**  ---------------- ѡ�� ---------------------
     * 
     * ��ѯ����
     */
    private String selTelRule;
    
    /** 
     * ����ǰ׺
     */
    private String telPrefix;
    
    /** 
     * �����׺
     */
    private String telSuffix;
    
    /** 
     * ÿҳ�����б�
     */
    private List<ServerNumPO> serverNumList = null;
    
    /** 
     * ���ȫ���ֻ���������
     */
    private List<ServerNumPO> tmpList = null;
    
    /** ---------------- ��Ʒ -------------------------
     * 
     * ��ƷID
     */
    private String prodid;
    
    /** 
     * �հ׿�����
     */
    private String blankno;
    
    /** 
     * ��������
     */
    private String rectype;
    
    /** 
     * ��������
     */
    private String telnum; 
    
    /** 
     * ������
     */
    private String cardtype;
    
    /** 
     * ģ��ID
     */
    private String prodTempletId;
    
    /** 
     * Ԥ���
     */
    private String minfee;
    
    /** 
     * ��Ʒģ��
     */
    private ProdTempletPO tpltTempletPO;

    /**---------------- �˵���� -------------------------
     * 
     * �˵����
     */
    private List usableTypes = null;
    
    /** ---------------- Ͷ����� -------------------------
     * 
     * Ͷ�ҽ��
     */
    private String tMoney;
    
    /** 
     * Ͷ������ˮ��
     */
    private String terminalSeq;
    
    /** ---------------- ��Ʊ��� -------------------------
     * 
     * ��Ʊ��ӡ��־��0������ӡ 1����ӡ
     */
    private String payType;
    
    /**
     * �û�Ͷ�������Ϣ
     */
    private String cashDetail = "";
    
    /**
     * ƾ������ʱ��
     */
    private String pDealTime = "";
    
    /**
     * �Ƿ��˻�������
     */
    private String needReturnCard = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��
     */
    private String writeCardStatus = "";
    
    /**
     * Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��
     */
    private String payStatus = "";  
    
    /**
     * Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
     */
    private String installStatus = ""; 
    
    /**
     * �ɷ���ˮ�ţ���SH_CHARGE_lOG���� �ɷ�ǰ��������Ϊ��
     */
    private String chargeId = "";

    /**
     * ������ˮ��
     */
    private String installId = "";
    
    /**
     * ʵ�շ���
     */
    private String toFee = "";
    
    /**
     * ����������ˮ
     */
    private String formnum = "";
    
    /**
     * ������ע
     */
    private String notes = "";
    
    /**---------��־����--------------
     * �ɷ���־
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
     * ���п���
     */
    private String cardnumber;
    
    /**
     * д��������Ϣ��
     * iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2;
     */
    private String cardInfoStr;
    

    /**
     * ����Э��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String agreement()
    {
        return "agreement";
    }
    
    /** 
     * ȡ�ÿ���Э���б�
     * 
     * @return List<DictItemPO>
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<DictItemPO> getAgreementList()
    {
        return getDictItemByGrp("CardInstallAgreement");
    }
    
    /**
     * ��ȡ���֤
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String readCert()
    {
        return "readCert";
    }
    
    /**
     * ���֤��Ϣչ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String certShow()
    {
        return "certShow";
    }
    
    /** 
     * ѡ���Ʒҳ��
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    public String selectProd()
    {
        try
        {
            // ��ȡ�ն˻���Ϣ
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // ֤������
            String certtype = "IdCard";
            
            // ֤������
            String certid = getIdCardPO().getIdCardNo();
            
            // ���ýӿ�У��֤�������µ��û�����
            this.getCardInstallBean().chkCertSubs(termInfo, getCurMenuId(), certtype, certid);
            
            // ���ò�Ʒģ��Region
            ProdTempletPO prodTempletPO = new ProdTempletPO();
            prodTempletPO.setRegion(termInfo.getRegion());
            
            // ��ѯ��Ʒ�б�
            tpltTempletList = getCardBusiService().qryProdTempletList(prodTempletPO);

            if (null != tpltTempletList && tpltTempletList.size() >  0)
            {
                tpltTempletList = super.getPageList(tpltTempletList, 5);
            }
        }
        catch (ReceptionException re)
        {
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            // ��¼������־��Ϣ
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            return "installError";
        }
        
        // ת���Ʒ�б�ҳ��
        return "selectProd";
    }
    
    /**
     * ѡ��ѡ�Ź���ҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String selectRule()
    {
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // ��ȡ�����еĵ����б�
        List<RegionInfoPO> regionListTmp = (List<RegionInfoPO>)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        
        // ѭ�������ն˻����ڵ������ƺ���֯����
        for (RegionInfoPO regionInfoPO : regionListTmp)
        {
            if (termInfo.getRegion().equals(regionInfoPO.getRegion()))
            {
                region = regionInfoPO.getRegion();
                regionName = regionInfoPO.getRegionname();
            }
        }
        
        // ����
        return "selectRule";
    }
    /**
     * ����ǰ׺��׺����ҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String inputTelnumByRule()
    {
        return "inputTelnumByRule";
    }
    /** 
     * ����ѡ�Ź����ѯ�����б�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String telnumResult()
    {
        // ��ʼ��¼��־
        logger.debug("telNumResult start...");
        
        // ��ʱ���Զ���ת��ҳ
        this.getRequest().setAttribute("telProdFlag", "1");
        
        try
        {
            // ���ò�ƷID
            this.setProdid(this.getTpltTempletPO().getMainProdId());
                   
            // ���ýӿڲ�ѯ������Ϣ�б�
            serverNumList = this.getCardInstallBean().qryTelNumberListByRule(getTerminalInfoPO(),getCurMenuId(), selTelRule, telPrefix, telSuffix,tpltTempletPO.getMainProdId());

            if (null != serverNumList && serverNumList.size() >  0)
            {
                serverNumList = super.getPageList(serverNumList, 18);
            }
        }
        catch (ReceptionException re)
        {
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            // ��¼������־��Ϣ
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            return "installError";
        }
        
        logger.debug("telNumResult End ...");

        return "telnumResult";
    }

    /**
     * ������Դռѡ
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public void telnumTmpPick()
    {
        logger.debug("telnumTmpPick Starting...");
        
        String xml = "";
        
        try
        {
            // ���ú�����Դռѡ�ӿ�
            this.getCardInstallBean().telNumTmpPick(getTerminalInfoPO(), getCurMenuId(), telnum);
            
            // ������Դռѡ�ɹ�
            xml = "0";
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error("������Դռѡʧ�ܣ�", re);
            
            // ��¼������־��Ϣ
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);

        logger.debug("telnumTmpPick end!");
    }
    
    /**
     * У�鿨��Դ�Ƿ����
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public void chkBlankNo()
    {
        logger.debug("chkBlankNo Starting...");
        
        String xml = "";
        
        try
        {
            TerminalInfoPO terminalInfo = getTerminalInfoPO();
            
            // ���ýӿ�У�鿨��Դ�Ƿ����
            this.getCardInstallBean().chkBlankNo(terminalInfo, getCurMenuId(), blankno);
            
            // У��հ׿��Ƿ���Ԥ�ÿտ�
            this.getCardInstallBean().chkPreSetBlankCard(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // У�鿨��Դ����
            xml = "0";
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error(re.getMessage(), re);
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
        
        logger.debug("chkBlankNo End!");
    }
    
    /**
     * �հ׿���Դ��ѡ
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public void blankCardTmpPick() throws Exception
    {
        logger.debug("blankCardTmpPick Starting...");
        
        String xml = "";
        
        try
        {
            // ���ýӿڿհ׿���Դ��ѡ
            simInfoPO = this.getCardInstallBean().blankCardTmpPick(getTerminalInfoPO(), getCurMenuId(), blankno, telnum);
            
            // �հ׿���Դ��ѡ�ɹ�
            xml = "0~~" + simInfoPO.toString();
            cardInfoStr = simInfoPO.toString();
        }
        catch (ReceptionException re)
        {
            xml = "1~~" + re.getMessage();
            
            logger.error("�հ׿���Դ��ѡʧ�ܣ�", re);
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
        
        logger.debug("blankCardTmpPick End!");
    }
    
    /** 
     * У��հ׿���Ϣ
     * 1. ������ѡ
     * 2. У��հ׿�����Ƿ����
     * 3. У��հ׿��Ƿ���Ԥ�ÿտ�
     * 4. �հ׿���Դ��ѡ
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public String chkBlankCardInfo()
    {
        logger.debug("chkBlankCardInfo Starting...");
        
        try
        {
            TerminalInfoPO terminalInfo = getTerminalInfoPO();
            
            // ���ú�����Դ��ѡ�ӿ�
            this.getCardInstallBean().telNumTmpPick(terminalInfo, getCurMenuId(), telnum);
            
            // ���ýӿ�У�鿨��Դ�Ƿ����
            this.getCardInstallBean().chkBlankNo(terminalInfo, getCurMenuId(), blankno);
            
            // У��հ׿��Ƿ���Ԥ�ÿտ�
            this.getCardInstallBean().chkPreSetBlankCard(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // ���ýӿڿհ׿���Դ��ѡ
            simInfoPO = this.getCardInstallBean().blankCardTmpPick(terminalInfo, getCurMenuId(), blankno, telnum);
            
            // �հ׿���Դ��ѡ�ɹ�
            cardInfoStr = simInfoPO.toString().replace("+", "^^");
            
            // �������
            return feeConfirm();
        }
        catch (ReceptionException re)
        {
            logger.error("У��հ׿���Դʧ�ܣ�", re);
            
            // ��¼������־��Ϣ
            this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", re.getMessage());
            
            // ���ô�����Ϣ
            getRequest().setAttribute("errormessage", re.getMessage()); 
            
            return "installError";
        }
        // logger.debug("chkBlankCardInfo End!");
    }
    
    /**
     * ����ȷ��ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String feeConfirm()
    {
        // ��ʼ��¼��־
        logger.debug("feeConfirm start...");
        
        try
        {
            // ������ѽӿ�
            CRSet crset = this.getCardInstallBean().reckonRecFee(this.getTerminalInfoPO(), curMenuId, telnum, tpltTempletPO, simInfoPO, blankno);
            
            // �ܷ���
            BigDecimal allFee = new BigDecimal("0");
        
            this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
            feeList = new ArrayList<FeeConfirmPO>();
            
            FeeConfirmPO feeConfirmPO = null;        
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                feeConfirmPO = new FeeConfirmPO();
                
                // ��������
                feeConfirmPO.setFeeName((crset.GetValue(i, 0)));
                
                // Ӧ������
                feeConfirmPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 5)));
                
                // ����
                feeConfirmPO.setNum(crset.GetValue(i, 2));
                
                // ��������
                feeConfirmPO.setFeeType(crset.GetValue(i, 3));
                
                allFee = allFee.add(new BigDecimal(crset.GetValue(i, 5)));
                feeList.add(feeConfirmPO);
            }       
        
            // ���Ԥ�����
            String preMinFee=this.tpltTempletPO.getMinFee();
            feeConfirmPO = new FeeConfirmPO();
            feeConfirmPO.setFeeName("�ײͲ�ƷԤ���");
            feeConfirmPO.setFee(CommonUtil.fenToYuan(preMinFee));
            feeConfirmPO.setNum("1");
            feeConfirmPO.setFeeType("Ԥ�����");
            allFee = allFee.add(new BigDecimal(preMinFee));
            feeList.add(feeConfirmPO);
        
            // ��ӷ��úϼ�
            feeConfirmPO = new FeeConfirmPO();
            feeConfirmPO.setFeeName("���úϼ�");
            recFee = allFee.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            feeConfirmPO.setFee(recFee);
            feeConfirmPO.setNum("1");
            feeConfirmPO.setFeeType("");
            feeList.add(feeConfirmPO);
        }
        catch (ReceptionException e)
        {
            errormessage = e.getMessage();
            return installError();
        }
        return "feeConfirm";
    }
    
    /**
     * ѡ��ɷ�����
     * @return String
     */
    public String selectPayType()
    {
        logger.debug("selectPayType start!");
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO) getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    
        // ����ȷ�Ϻ��¼������Ϣ
        addLogInstall();
    
        // ��ֵ�ɷѷ�ʽ�б�
        usableTypes = getPayType(termInfo.getTermgrpid());
        
        // findbugs�޸� 2015-09-02 zKF66389
//        if (logger.isInfoEnabled())
//        {
//            logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
//        }
        // findbugs�޸� 2015-09-02 zKF66389
        
        // findbugs�޸� 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
        if (usableTypes.size() == 0)
        // findbugs�޸� 2015-09-02 zKF66389
        {
            // û�п��õĳ�ֵ��ʽ
            setErrormessage("�Բ�����ʱû�п��õĳ�ֵ��ʽ���뷵��ִ������������");
            
            // ��¼��־
            this.createRecLog(telnum, "feeCharge", "0", "0", "1", "��ʱû�п��õĳ�ֵ��ʽ");
            
            return ERROR;
        }
        
        logger.debug("selectPayType end!");
        
        return "selectPayType";
    }
    
    /**
     * ת��Ͷ��ҳ��
     * @return String
     */
    public String cashChargeInstall()
    {
        // ��ʱ���Զ���ת��ҳ
        this.getRequest().setAttribute("telProdFlag", "1");

        // ת��Ͷ��ҳ��
        return "cashCharge";
    }
    
    /**
     * ת������п�ҳ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String toReadCard()
    {
        return "toReadCard";
    }
    
    /**
     * ������������������ҳ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String inputCardPwd()
    {
        return "cardPassword";
    }
    
    /**
     * ת��ȷ�����п��ɷѽ��ҳ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String toMakeSure()
    {
        // ��ʱ���Զ���ת��ҳ
        this.getRequest().setAttribute("telProdFlag", "1");
        
        return "toMakeSure";
    }
    
    /**
     * �ֽ�ɷѿ�������
     * @return String
     */
    public String cashCommitInstall()
    {
        logger.debug("cashCommitInstall start");
        
        logger.info("�û�" + telnum + "Ͷ�����Ϊ��" + cashDetail + "����Ͷ�ҽ��Ϊ��" + tMoney + "����ˮ��" + terminalSeq);
        
        // ��ֹ�û���Ͷ�ң�ֱ�Ӵ��������ģ�⽻������
        String referer = getRequest().getHeader("Referer");
        if (null == referer)       
        {
            setErrormessage("��Ч����");
            return installError();
        }    
     
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �ظ���������
        if (!checkCashFee(termInfo))
        {
            String tipMsg = "�ǳ���Ǹ�������νɷѿ����������СƱƾ֤��Ӫҵǰ̨��ѯ��ʵ���ɴ˸��������Ĳ��㣬�����½⣡";
            setErrormessage(tipMsg);
            return installError();
        }
        
        // ���ӽɷ���־  �ɷѳɹ�10 
        addCashChargeLog();

        // �հ׿�����
        return installCardCommit();
    }
    
    /**
     * �հ׿�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String installCardCommit()
    {
        logger.debug("installCardCommit start");
        
        CardChargeLogVO cardChargeLogPO = new CardChargeLogVO();
        cardChargeLogPO.setChargeLogOID(chargeId);
        
        // ���¿�����־  �ɷѳɹ�0 д���ɹ�0
        updateLogInstall();
        
        try
        {
            // ������Ҫ�հ׿���
            simInfoPO.setBlankno(blankno);
            
            // ���ÿ����ӿڽӿڻ�ȡ������ˮ
            formnum = cardInstallBean.terminalInstall(this.getTerminalInfoPO(), curMenuId, idCardPO, tpltTempletPO, telnum,CommonUtil.yuanToFen(toFee),simInfoPO);
        }
        catch (ReceptionException e)
        {
            // ����״̬ 1��ʧ�� 0���ɹ�
            installStatus = "1";  
            notes = "����ʧ�ܣ�";
            
            // ���¿�����־
            updateLogInstall();
            
            // ����
            cardChargeLogPO.setDescription("����ʱ�ɷѳɹ�������ʧ�ܣ�");
            
            // ���½ɷ���־
            cardBusiService.updateCardChargeLog(cardChargeLogPO);
            
            // д��ʧ��
            updateWriteCardResult();
            
            errormessage = e.getMessage();
            
            return installError();
        }
        
        // ����״̬ 1��ʧ�� 0���ɹ�
        installStatus = "0";
        notes = "�����ɹ���";
        
        // ���¿�����־  �����ɹ�0  ������ˮ
        updateLogInstall();
        
        // 11���ۿ�ɹ����ɷѳɹ��������ɹ�
        cardChargeLogPO.setStatus("11");
        cardChargeLogPO.setDescription("�����ɹ���");
        
        // ���½ɷ���־
        cardBusiService.updateCardChargeLog(cardChargeLogPO);
        
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "0", notes);
        
        return SUCCESS;
    }
    
    /**
     * �ۿ�֮ǰ�����������ɷ���־
     * 
     * @throws Exception
     */
    public void addChargeLog() throws Exception
    {
        logger.debug("addCardPayLog Starting...");
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("UTF-8");

        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        String status = (String)request.getParameter("status");
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        String posNum = (String)request.getParameter("posnum");
        String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
        
        // ��װ��־����
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        chargeId = feeChargeService.getChargeLogOID();
        
        cardChargeLogVO.setChargeLogOID(chargeId);
        cardChargeLogVO.setRegion(termInfo.getRegion());
        cardChargeLogVO.setTermID(termInfo.getTermid());
        cardChargeLogVO.setOperID(termInfo.getOperid());
        cardChargeLogVO.setServnumber(telnum);
        cardChargeLogVO.setPayType(payType);
        
        // �ۿ��� ��
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        
        // �����ݿ��������ܺ����������
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(request.getParameter("cardnumber")));
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        // �ۿ�ʱ��
        String payDate =  CommonUtil.getCurrentTime();
        cardChargeLogVO.setRecdate(payDate);
        cardChargeLogVO.setStatus(status);
        cardChargeLogVO.setDescription(description);
        cardChargeLogVO.setServRegion(termInfo.getRegion());
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        cardChargeLogVO.setPosNum(posNum);
        cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
        
        // ҵ�����ͣ�presetinstall: Ԥ�ÿհ׿������ɷѣ�
        cardChargeLogVO.setRecType("presetinstall");
        
        String xml = "";
        try
        {
            // ����ɷ���־
            feeChargeService.addChargeLog(cardChargeLogVO);
            xml = "1~~" + chargeId;
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
        
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
        logger.debug("updateCardChargeLog Starting...");
        
        HttpServletRequest request = this.getRequest();

        // ״̬
        String status = (String)request.getParameter("status");
        
        // ����
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        
        // ��װ��־����
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        // id
        cardChargeLogVO.setChargeLogOID(chargeId);
        
        // �̻����
        cardChargeLogVO.setUnionpayuser(request.getParameter("unionpayuser"));
       
        // �ն˱��
        cardChargeLogVO.setUnionpaycode(request.getParameter("unionpaycode"));
        
        // ��������
        cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(request.getParameter("busitype"), "UTF-8"));

        //�����ݿ��������ܺ����������
        cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(request.getParameter("cardnumber")));

        // ���κ�
        cardChargeLogVO.setBatchnum(request.getParameter("batchnum"));
        
        // ��Ȩ��
        cardChargeLogVO.setAuthorizationcode(request.getParameter("authorizationcode"));
        
        // ���ײο���
        cardChargeLogVO.setBusinessreferencenum(request.getParameter("businessreferencenum"));
        
        // �����ۿ�ʱ��
        cardChargeLogVO.setUnionpaytime(request.getParameter("unionpaytime"));
        
        // ƾ֤��
        cardChargeLogVO.setVouchernum(request.getParameter("vouchernum"));
        
        // �ն���ˮ
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        // ״̬
        cardChargeLogVO.setStatus(status);
        
        // ����
        cardChargeLogVO.setDescription(description);
        
        // ����ʱ��
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // ���������ֶ�
        cardChargeLogVO.setPosResCode(request.getParameter("posResCode"));
        
        // �ۿ���
        String unionpayfee = request.getParameter("unionpayfee");
        
        // modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
        unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
        // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_������ϼ��š������·����ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
        
        cardChargeLogVO.setUnionpayfee(unionpayfee);
 
        String xml = "";
        try
        {
            // ���½ɷ���־
            cardBusiService.updateCardChargeLog(cardChargeLogVO);
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("�ۿ�֮ǰ��¼��־�쳣��", e);
        }

        // ��ӡ��Ϣ
        writeXmlMsg(xml);
        
        logger.debug("updateCardChargeLog end!");
    }
    
    /**
     * �����쳣
     *
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String installError()
    {
        // ��¼������־��sh_rec_log
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", errormessage);
        
        return "installError";
    }
    
    /**
     * ��¼�쳣��־
     * @return
     * @see
     */
    public String makeErrLog()
    {
        // �ֽ�ɷ�
        if("4".equals(payType))
        {
            // ��¼�ɷ���־
            addCashChargeLog();
        }
        
        notes = errormessage.length()< 256 ? errormessage : errormessage.substring(0,256);
        
        // ��¼������־
        updateLogInstall();
        
        
        this.createRecLog(Constants.SH_CARD_INSTALL, "0", "0", "1", notes);
        
        return "installError";
    }
    
    /**
     * ��¼������־
     */
    public void addLogInstall()
    {
        //��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
       
        CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();

        // ��ˮ��
        installId = this.getCardBusiService().getInstallId();
        cardBusiLogPO.setOid(installId);
        
        // �ն˺�
        cardBusiLogPO.setTermId(termInfo.getTermid());
        
        // ����
        cardBusiLogPO.setRegion(termInfo.getRegion());
        
        // �ֻ���
        cardBusiLogPO.setServnumber(telnum);
        
        // �������� presetinstall Ԥ�ÿհ׿�����
        cardBusiLogPO.setRectype("presetinstall");
        
        // �����Ʒ����
        cardBusiLogPO.setMainProdId(tpltTempletPO.getMainProdId());
        
        // ��Ʒģ�����
        cardBusiLogPO.setProdTempletId(tpltTempletPO.getTempletId());
        
        // ����Ӧ�շ���
        cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(recFee));
        
        // �ͻ�����
        cardBusiLogPO.setCustName(this.getIdCardPO().getIdCardName());
        
        // ���֤��
        cardBusiLogPO.setCertId(this.getIdCardPO().getIdCardNo());
        
        // ���֤�ϵ�ַ
        cardBusiLogPO.setLinkAddr(this.getIdCardPO().getIdCardAddr());
        
        // �Ա�
        cardBusiLogPO.setSex(this.getIdCardPO().getIdCardSex());
        
        // �հ׿���
        cardBusiLogPO.setBlankCard(blankno);
        
        // ICCID
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // IMSI
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // SMSP
        cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
       
        // Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
        cardBusiLogPO.setWriteCardStatus("2");
        
        // Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
        cardBusiLogPO.setPayStatus("2");
        
        // ����״̬ Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
        cardBusiLogPO.setInstallStatus("2");
        
        cardBusiLogPO.setWriteCardStatus("2");
        
        // ����״̬ Ĭ��2�� 0���ɷѳɹ� 1���ɷ�ʧ��
        cardBusiLogPO.setPayStatus("2");
        
        // Ĭ��2����ʼ״̬ 0���˿�ɹ� 1���˿�ʧ��
        cardBusiLogPO.setRefundment("2");
        
        // ��ע
        cardBusiLogPO.setNotes(tpltTempletPO.getNotes());
        
        // ��¼��־
        this.getCardBusiService().addLogInstall(cardBusiLogPO);
    }
    
    /**
     * ���ӽɷ���־
     */
    public void addCashChargeLog()
    {
        // �ն���Ϣ
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // �ɷ���־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        // �ն���ˮ(�ն�id+�ֽ�ɷ���ˮ ��ȡ��20λ)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        
        // �ɷ���ˮ
        String logOID = feeChargeService.getChargeLogOID();
        
        // �ɷ�ʱ��
        String payDate = CommonUtil.getCurrentTime();
        
        // ��װ����
        selfCardPayLogVO.setChargeLogOID(logOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(telnum);
        selfCardPayLogVO.setPayType(payType);
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        selfCardPayLogVO.setRecdate(payDate);
        selfCardPayLogVO.setServRegion(region);
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // 10��Ͷ�һ�ۿ�ɹ�
        selfCardPayLogVO.setStatus("10");
        
        // ����
        selfCardPayLogVO.setDescription("д������֮ǰ�Ľɷ�");
        
        // ҵ������(presetinstall:Ԥ�ÿհ׿������ɷ�)
        selfCardPayLogVO.setRecType("presetinstall");
        
        // ʵ�ʽɷѽ����ݿⵥλ �֣�
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(toFee));
        
        // ��ӽɷ���־
        feeChargeService.addChargeLog(selfCardPayLogVO);
        
        // �ɷ���ˮ�ţ���SH_CHARGE_lOG����ChargeLogOID
        chargeId = logOID;
    }
    
    /**
     * ���¿�����־
     */
    public void updateLogInstall()
    {
        logger.debug("updateLogInstall Starting...");
        
        CardBusiLogPO cardBusiLogPO = new CardBusiLogPO();
        
        // ��ˮ��
        cardBusiLogPO.setOid(installId);
        
        // �ɷ���ˮ�ţ���SH_CHARGE_lOG���� �ɷ�ǰ��������Ϊ��
        cardBusiLogPO.setChargeId(chargeId);
        
        // �ɷѷ�ʽ��1����������4���ֽ�
        cardBusiLogPO.setChargeType(payType);
        
        // ʵ�շ���
        cardBusiLogPO.setToFee("".equals(toFee)?"":CommonUtil.yuanToFen(toFee));
        
        // Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� 
        cardBusiLogPO.setPayStatus(payStatus);
        
        // Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� 
        cardBusiLogPO.setWriteCardStatus(writeCardStatus);
        
        // Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ��
        cardBusiLogPO.setInstallStatus(installStatus);
        
        // ����������ˮ
        cardBusiLogPO.setFormnum(formnum);
        
        cardBusiLogPO.setNotes(notes);
        
        // �հ׿���
        cardBusiLogPO.setBlankCard(blankno);
        
        // ICCID
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // IMSI
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // SMSP
        cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
        
        // ���¿�����־
        cardBusiService.updateInstallLog(cardBusiLogPO);
        
        logger.debug("updateLogInstall end!");
    }
    
    /**
     * �ظ������ж�
     * 
     * @param termInfo �ն˻���Ϣ
     * @return true��δ�ظ���false���ظ�
     * @since 
     */
    private boolean checkCashFee(TerminalInfoPO termInfo)
    {   
        logger.debug("checkCashFee start!");
        
        String seq = termInfo.getTermid().concat(terminalSeq);
        
        String tmpSeq = seq.concat(telnum);
        
        // �������ͬ�Ĵ��������ظ��ɷ�
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            pDealTime = DateUtil.getCurrentDateTime();
            
            String tmpErrorMsg = "�ظ��ɷ�:�ֻ���[".concat(telnum).concat("],Ͷ�ҽ��[").concat(tMoney).concat("]Ԫ,����Ӫҵ��[")
                    .concat(termInfo.getOrgname()).concat("],��ˮ��[").concat(seq).concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(telnum);
            
            // �ֽ�Ͷ��
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            
            // �ֽ�ɷ���ˮ,�ն�id+����������ˮ
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(pDealTime);
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // ��¼�ظ��ɷ���־
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // ��¼��SH_REC_LOG,������ˮ�������¼�ֽ�ɷ���ˮ��
            this.createRecLog(telnum, Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
    }
    
    /**
     * �첽��ȡд����������
     */
    public void asynGetEncryptedData()
    {
        String xml = "";
        try
        {
            String cardformnum = this.getRequest().getParameter("cardformnum");
            // ����Ϣ
            simInfoPO = new SimInfoPO(cardInfoStr);
            simInfoPO.setBlankno(blankno);
            
            // ��ȡ������Ϣ��������
            String encryptedData = getCardInstallBean().getEncryptedData(getTerminalInfoPO(),curMenuId,telnum,simInfoPO,cardformnum);
            xml = "0~~"+encryptedData;
        }
        catch (ReceptionException e)
        {
            xml = "1~~У�鿨��Դ�Ƿ����ʧ��";
            logger.error("��ȡд����������ʧ�ܣ�", e);
        }
        
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
       
    }
    
    /**
     * ��֤д�����
     */
    public void checkWriteCardInfo()
    {   
        String  writeCardInfo = this.getRequest().getParameter("writeCardInfo");
        
        // д����ˮ
        String cardformnum = this.getRequest().getParameter("cardformnum");
        
        // ����Ϣ
        simInfoPO = new SimInfoPO(cardInfoStr);
        
        String result = "";
        
        try
        {
            // У��д����� 0���ɹ�  1��ʧ��
            result = cardInstallBean.checkWriteCardInfo(this.getTerminalInfoPO(), curMenuId, cardformnum, blankno, writeCardInfo);
            
            // û�гɹ�������д��ʧ��
            if(!"0".equals(result))
            {
                // д��ʧ��
                updateWriteCardResult();
            }
        }
        catch (ReceptionException re)
        {
            result = "1~"+re.getMessage();
            
            // д��ʧ��
            updateWriteCardResult();
         
            logger.error("��֤д���������ʧ��!", re);
        }
 
        // ��ӡ��Ϣ
        writeXmlMsg(result);
    }
    
    /**
     * �첽���ÿ����Ͻӿ�
     */
    public void asynUpdateWriteCardResult()
    {
        String xml = "";
        
        try
        {
            // д��ʧ��
            updateWriteCardResult();
            
            xml = "0";    
        }
        catch (ReceptionException re)
        {
            xml = "1~"+re.getMessage();
         
            logger.error("����ʧ�ܵ��ÿ����Ͻӿ�ʧ��!", re);
        }
 
        // ��ӡ��Ϣ
        writeXmlMsg(xml);
    }
    
    /**
     * ����д��ʧ�ܽӿ�
     */
    public void updateWriteCardResult()
    {
        try
        {
            simInfoPO = new SimInfoPO(cardInfoStr);
            
            // ����д�������Ϣ
            simInfoPO.setWriteResult("-1");
            simInfoPO.setErrMsg("д��ʧ��");
            simInfoPO.setErrCode("-1");
            
            // ����ʧ�ܵ��ÿ����Ͻӿ�
            cardInstallBean.updateWriteCardResult(this.getTerminalInfoPO(), curMenuId, blankno, simInfoPO);
        }
        catch (ReceptionException re)
        {
            logger.error("����ʧ�ܵ��ÿ����Ͻӿ�ʧ��!", re);
        }
        
    }
    
    /**
     * @return ���� tpltTempletList
     */
    public List<ProdTempletPO> getTpltTempletList()
    {
        return tpltTempletList;
    }

    /**
     * @param ��tpltTempletList���и�ֵ
     */
    public void setTpltTempletList(List<ProdTempletPO> tpltTempletList)
    {
        this.tpltTempletList = tpltTempletList;
    }

    /**
     * @return ���� region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param ��region���и�ֵ
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    /**
     * @return ���� regionName
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRegionName()
    {
        return regionName;
    }
    /**
     * @param ��regionName���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    /**
     * @return ���� selTelRule
     */
    public String getSelTelRule()
    {
        return selTelRule;
    }

    /**
     * @param ��selTelRule���и�ֵ
     */
    public void setSelTelRule(String selTelRule)
    {
        this.selTelRule = selTelRule;
    }

    /**
     * @return ���� telPrefix
     */
    public String getTelPrefix()
    {
        return telPrefix;
    }

    /**
     * @param ��telPrefix���и�ֵ
     */
    public void setTelPrefix(String telPrefix)
    {
        this.telPrefix = telPrefix;
    }

    /**
     * @return ���� telSuffix
     */
    public String getTelSuffix()
    {
        return telSuffix;
    }

    /**
     * @param ��telSuffix���и�ֵ
     */
    public void setTelSuffix(String telSuffix)
    {
        this.telSuffix = telSuffix;
    }

    /**
     * @return ���� serverNumList
     */
    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    /**
     * @param ��serverNumList���и�ֵ
     */
    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    /**
     * @return ���� tmpList
     */
    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    /**
     * @param ��tmpList���и�ֵ
     */
    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }
    /**
     * @return ���� prodid
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdid()
    {
        return prodid;
    }
    /**
     * @param ��prodid���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    /**
     * @return ���� blankno
     */
    public String getBlankno()
    {
        return blankno;
    }

    /**
     * @param ��blankno���и�ֵ
     */
    public void setBlankno(String blankno)
    {
        this.blankno = blankno;
    }
    /**
     * @return ���� rectype
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRectype()
    {
        return rectype;
    }
    /**
     * @param ��rectype���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    /**
     * @return ���� telnum
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTelnum()
    {
        return telnum;
    }
    /**
     * @param ��telnum���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    /**
     * @return ���� cardtype
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCardtype()
    {
        return cardtype;
    }
    /**
     * @param ��cardtype���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCardtype(String cardtype)
    {
        this.cardtype = cardtype;
    }

    /**
     * @return ���� prodTempletId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdTempletId()
    {
        return prodTempletId;
    }

    /**
     * @param ��prodTempletId���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdTempletId(String prodTempletId)
    {
        this.prodTempletId = prodTempletId;
    }
    /**
     * @return ���� minfee
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMinfee()
    {
        return minfee;
    }
    /**
     * @param ��minfee���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMinfee(String minfee)
    {
        this.minfee = minfee;
    }

    /**
     * @return ���� tpltTempletPO
     */
    public ProdTempletPO getTpltTempletPO()
    {
        return tpltTempletPO;
    }

    /**
     * @param ��tpltTempletPO���и�ֵ
     */
    public void setTpltTempletPO(ProdTempletPO tpltTempletPO)
    {
        this.tpltTempletPO = tpltTempletPO;
    }

    /**
     * @return ���� usableTypes
     */
    public List getUsableTypes()
    {
        return usableTypes;
    }

    /**
     * @param ��usableTypes���и�ֵ
     */
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }

    /**
     * @return ���� tMoney
     */
    public String gettMoney()
    {
        return tMoney;
    }

    /**
     * @param ��tMoney���и�ֵ
     */
    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
    }

    /**
     * @return ���� terminalSeq
     */
    public String getTerminalSeq()
    {
        return terminalSeq;
    }

    /**
     * @param ��terminalSeq���и�ֵ
     */
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }

    /**
     * @return ���� payType
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * @param ��payType���и�ֵ
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    /**
     * @return ���� cashDetail
     */
    public String getCashDetail()
    {
        return cashDetail;
    }

    /**
     * @param ��cashDetail���и�ֵ
     */
    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
    }
    /**
     * @return ���� pDealTime
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getpDealTime()
    {
        return pDealTime;
    }
    /**
     * @param ��pDealTime���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setpDealTime(String pDealTime)
    {
        this.pDealTime = pDealTime;
    }

    /**
     * @return ���� needReturnCard
     */
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }

    /**
     * @param ��needReturnCard���и�ֵ
     */
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }

    /**
     * @return ���� writeCardStatus
     */
    public String getWriteCardStatus()
    {
        return writeCardStatus;
    }

    /**
     * @param ��writeCardStatus���и�ֵ
     */
    public void setWriteCardStatus(String writeCardStatus)
    {
        this.writeCardStatus = writeCardStatus;
    }

    /**
     * @return ���� payStatus
     */
    public String getPayStatus()
    {
        return payStatus;
    }

    /**
     * @param ��payStatus���и�ֵ
     */
    public void setPayStatus(String payStatus)
    {
        this.payStatus = payStatus;
    }

    /**
     * @return ���� installStatus
     */
    public String getInstallStatus()
    {
        return installStatus;
    }

    /**
     * @param ��installStatus���и�ֵ
     */
    public void setInstallStatus(String installStatus)
    {
        this.installStatus = installStatus;
    }

    /**
     * @return ���� chargeId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getChargeId()
    {
        return chargeId;
    }

    /**
     * @param ��chargeId���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }

    /**
     * @return ���� installId
     */
    public String getInstallId()
    {
        return installId;
    }

    /**
     * @param ��installId���и�ֵ
     */
    public void setInstallId(String installId)
    {
        this.installId = installId;
    }

    /**
     * @return ���� toFee
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getToFee()
    {
        return toFee;
    }

    /**
     * @param ��toFee���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setToFee(String toFee)
    {
        this.toFee = toFee;
    }
    /**
     * @return ���� formnum
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getFormnum()
    {
        return formnum;
    }
    /**
     * @param ��formnum���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }

    /**
     * @return ���� notes
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * @param ��notes���и�ֵ
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getTMoney()
    {
        return tMoney;
    }

    public void setTMoney(String money)
    {
        tMoney = money;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPDealTime()
    {
        return pDealTime;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPDealTime(String dealTime)
    {
        pDealTime = dealTime;
    }

    public FeeChargeHubService getFeeChargeService()
    {
        return feeChargeService;
    }

    public void setFeeChargeService(FeeChargeHubService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }

    public String getCardnumber()
    {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }

    public String getCardInfoStr()
    {
        return cardInfoStr;
    }

    public void setCardInfoStr(String cardInfoStr)
    {
        this.cardInfoStr = cardInfoStr;
    }
    
   
}
