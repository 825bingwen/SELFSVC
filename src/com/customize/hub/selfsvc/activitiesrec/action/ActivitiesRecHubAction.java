package com.customize.hub.selfsvc.activitiesrec.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.activitiesrec.model.ActivityVO;
import com.customize.hub.selfsvc.activitiesrec.model.AwardVO;
import com.customize.hub.selfsvc.activitiesrec.service.ActivitiesRecHubService;
import com.customize.hub.selfsvc.bean.ActivitiesRecBean;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;


/**
 * 
 * ���������
 * 
 * @author  yKF28472
 * @version  [�汾��, Jan 31, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ActivitiesRecHubAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(ActivitiesRecHubAction.class);
    
    /**
     * service
     */
    private ActivitiesRecHubService activitiesRecHubService;
    
    /**
     * �����_��ҳ��¼
     */
    private List<ActivityVO> activityList = new ArrayList<ActivityVO>();
    
    /**
     * �����_���м�¼
     */
    private List<ActivityVO> activityAllList = new ArrayList<ActivityVO>();
    
    /**
     * ���õ����е���
     */
    private List<ActivityVO> dangciAllList = new ArrayList<ActivityVO>();
    
    /**
     * ��Ʒ�б�_��ҳ��¼
     */
    private List<AwardVO> awardList = new ArrayList<AwardVO>();
    
    /**
     * ��Ʒ�б�_���м�¼
     */
    private List<AwardVO> awardAllList = new ArrayList<AwardVO>();
    
    /**
     * ��ǰ�˵�
     */
    private String curMenuId = "";
    
    /**
     * �����
     */
    private String activityId = "";

    /**
     * �����
     */
    private String activityName = "";
    
    /**
     * ���ID
     */
    private String groupId = "";
    
    /**
     * �������
     */
    private String groupName = "";
    
    /**
     * ���α���
     */
    private String dangciId = "";
    
    /**
     * ��������
     */
    private String dangciName = "";
    
    /**
     * ����������Ϣ
     */
    private String dangciDesc = "";
    
    /**
     * �ɷѷ�ʽ
     */
    private List usableTypes = null;
    
    /**
     * �ֻ�����
     */
    private String servnumber = "";
    
    /**
     * ������Ϣ
     */
    private String errormessage = "";
    
    /**
     * ��Ʒ��Ŵ�
     */
    private String actreward = "";
    
    /**
     * �ֻ���IMEI�Ŵ�
     */
    private String imeiid = "";
    
    /**
     * ��Ʒ����
     */
    private int quantity = 0;
    
    /**
     * ��Ʒ�ܼ�
     */
    private int rewardAccount = 0;
    
    /**
     * �û�Ͷ����_��
     */
    //modify by sWX219697 2015-7-16 15:47:46 ��totalfee��ΪtotalFee��findbugs�޸�
    private String totalFee = "0";
    
    /**
     * �û�Ͷ����_Ԫ
     */
    private String totalfee_yuan = "0";
    
    /**
     * �ն���ˮ
     */
    private String terminalSeq = "";
    
    /**
     * ������ˮ
     */
    private String recoid = "";
    
    /**
     * �����ն˶����������
     */
    private String prepayFee;
    
    /**
     * ����
     */
    private String region;
    
    /**
     * �ն�ID
     */
    //modify by sWX219697 2015-7-16 11:28:57 ��termId��Ϊtermid��findbugs�޸�
    private String termid;
    
    /**
     * �ն�����
     */
    private String termName;
    
    /**
     * ����ԱID
     */
    private String operId;
    
    /**
     * �ص�
     */
    //modify by sWX219697 2015-7-16 11:28:57 ��orgName��Ϊorgname��findbugs�޸�
    private String orgname;
    
    /**
     * �����Ƿ�ɹ� 0:�ɹ� 1:ʧ��
     */
    private String successBz;
    
    /**
     * Ӫ����������
     */
    private String yxfaFee;
    
    /**
     * Ԥ��
     */
    private String ycFee;
    
    /**
     * Ӫ����������
     */
    private String yxfaFee_yuan;
    
    /**
     * Ԥ��
     */
    private String ycFee_yuan;
    
    /**
     * ���ѷ�ʽ 1��������  4���ֽ�
     */
    private String payType;
    
    /**
     * �Ƿ���Ҫ�˿� 0:����Ҫ 1:��Ҫ
     */
    private String needReturnCard;
    
    /**
     * ��������
     */
    private String cardnumber;
    
    /**
     * �����ն˷�����������ˮ��
     */
    private String chargeLogOID;
    
    /**
     * �̻���ţ��ڿ�����ʶ��
     */
    private String unionpayuser;
    
    /**
     * �ն˱��
     */
    private String unionpaycode;
    
    /**
     * ��������
     */
    //modify by sWX219697 2015-7-18 10:53:54 ��busitype��ΪbusiType��findbugs�޸�
    private String busiType;
    
    /**
     * �ն����κ�
     */
    private String batchnum;
    
    /**
     * ��Ȩ�루ɽ���ã�
     */
    private String authorizationcode;
    
    /**
     * ���ײο��ţ�ɽ���ã�
     */
    private String businessreferencenum;
    
    /**
     * ����ʵ�ʿۿ�ʱ��
     */
    private String unionpaytime;
    
    /**
     * ƾ֤�ţ�ɽ���ã�
     */
    private String vouchernum;
    
    /**
     * ����ʵ�ʿۿ����λ���֣�
     */
    private String unionpayfee;
    
    /**
     * �쳣����  add:���� update:�޸�
     */
    private String errorType;
    
    /**
     * ������ӡ��Ϣ
     */
    private String printcontext;
    
    /**
     * POS������
     */
    private String initPosResCode = "";
    private String cmtPosResCode = "";
    private String errPosResCode = "";
    
    /**
     * �ӿڲ���
     */
    private ActivitiesRecBean activitiesRecBean;
    
    private ReceptionBean receptionBean = null;    

    private String tip = "";

    /**
     * ��ѯ�������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryActivities()
    {
        String forward = "success";
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // modify begion yKF28472 OR_huawei_201305_474
        /**
        String regionByTem = termInfo.getRegion();
        String regionByCustomer = customerSimp.getRegionID();
        **/
        String regionByTem = "";
        String regionByCustomer = "";
        if (customerSimp.getSmallregion() != null && !"".equals(customerSimp.getSmallregion()))
        {
            regionByTem = getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion());
            regionByCustomer = customerSimp.getSmallregion();
        }
        else
        {
            regionByTem = termInfo.getRegion();
            regionByCustomer = customerSimp.getRegionID();
        }
        if (!regionByTem.equals(regionByCustomer))
        {
            errormessage = "���ֻ����벻�ڵ�ǰ���У����ܰ�����";
            return "error";
        }
        // modify end yKF28472 OR_huawei_201305_474
        
        // �ֻ�����
        servnumber = customerSimp.getServNumber();
        
        // ��ѯ�û��Ѵ��ڵĵ���
        Map mapResult = this.activitiesRecBean.qrySubsDangcisList(termInfo, customerSimp,curMenuId, customerSimp.getServNumber());
        
        // ��װ�Ѵ��ڵĵ��α����б�
        String dangciIds = "";
        StringBuffer sbuf = new StringBuffer(dangciIds);
        if (mapResult != null && mapResult.size() == 2)
        {   
            CRSet crset = (CRSet) mapResult.get("returnObj");
            if (crset != null)
            {
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    if ((i+1) == crset.GetRowCount())
                    {
                        sbuf.append("'").append(crset.GetValue(i, 0)).append("'");
                    }
                    else
                    {
                        sbuf.append("'").append(crset.GetValue(i, 0)).append("'").append(",");
                    }
                }
                dangciIds = sbuf.toString();
            }
        }
        else if (mapResult != null && mapResult.size() == 1)
        {
            errormessage = (String)mapResult.get("returnMsg");
            return "error";
        }
        else
        {
            errormessage = "��ѯ�û��Ѵ��ڵĵ����쳣��";
            return "error";
        }
        if ("".equals(dangciIds))
        {
            dangciIds = "''";
        }
        
        // ��������
        ActivityVO vo = new ActivityVO();
        
        // ����
        if (!"".equals(termInfo.getRegion()))
        {
            // modify begion yKF28472 OR_huawei_201305_474
            //vo.setRegion(termInfo.getRegion());	
            vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
            // modify end yKF28472 OR_huawei_201305_474
        }
        
        // �û��Ѵ��ڵĵ���
        if (!"".equals(dangciIds))
        {
            vo.setDangciIds(dangciIds);
        }
        
        // ��ѯ��б�----------------------------------------------------
        activityAllList = activitiesRecHubService.getActivitieGroups(vo);
        
        if (activityAllList == null || activityAllList.size() == 0 )
        {
            errormessage = "��ǰû�пɰ���Ļ��";
        	
            return "error";
        }
        
        // ��ѯ�����б�----------------------------------------------------------
        
        // ��������
        vo = new ActivityVO();
        //vo.setRegion(termInfo.getRegion());
        // modify begion yKF28472 OR_huawei_201305_474
        //vo.setRegion(termInfo.getRegion());
        vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        // modify end yKF28472 OR_huawei_201305_474
        
        // ��ѯ����
        dangciAllList = activitiesRecHubService.getDangciByGroupId(vo);
        
        for(ActivityVO activityVO:dangciAllList)
        {
            activityVO.setPrepayFee(Integer.parseInt(activityVO.getPrepayFee())/100+"");
            activityVO.setPresentValue(Integer.parseInt(activityVO.getPresentValue())/100+"");
        }
        
        // ��б���뵵��---------------------------------------------------------
        List<ActivityVO> activityAllListByTmp = new ArrayList<ActivityVO>();
        ActivityVO activity = null;
        for (ActivityVO activityVO:activityAllList)
        {
            for(ActivityVO dangciVO:dangciAllList)
            {
                if (activityVO.getGroupId().equals(dangciVO.getGroupId()))
                {
                    activity = new ActivityVO();
                    activity.setGroupId(activityVO.getGroupId());
                    activity.setGroupName(activityVO.getGroupName());
                    // ����
                    activity.setRegion(dangciVO.getRegion());
                    activity.setActivityId(dangciVO.getActivityId());
                    activity.setDangciId(dangciVO.getDangciId());
                    activity.setDangciName(dangciVO.getDangciName());
                    activity.setPrepayFee(dangciVO.getPrepayFee());
                    activity.setPresentValue(dangciVO.getPresentValue());
                    activity.setStatusDate(dangciVO.getStatusDate());
                    activity.setStatus(dangciVO.getStatus());
                    activityAllListByTmp.add(activity);
                }
            }
        }
        activityAllList = activityAllListByTmp;
        
        // ------------------------------------------------------------
        
        this.getRequest().setAttribute("recordCount", activityAllList.size());
        
        // ���÷�ҳ
        displayPage();
        
        // ����
        return forward;
    }
    
    /**
     * ��ѯ�����б�
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryDangCiList()
    {
        String forward = "success";
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ��������
        ActivityVO vo = new ActivityVO();
        vo.setGroupId(groupId);
        //vo.setRegion(termInfo.getRegion());
        // modify begion yKF28472 OR_huawei_201305_474
        //vo.setRegion(termInfo.getRegion());
        vo.setRegion(getSmallregion(termInfo.getCityOrgid(), termInfo.getRegion()));
        // modify end yKF28472 OR_huawei_201305_474
        
        // ��ѯ����
        activityAllList = activitiesRecHubService.getDangciByGroupId(vo);
        
        for(ActivityVO activityVO:activityAllList)
        {
        	activityVO.setPrepayFee(Integer.parseInt(activityVO.getPrepayFee())/100+"");
        	activityVO.setPresentValue(Integer.parseInt(activityVO.getPresentValue())/100+"");
        }
        
        this.getRequest().setAttribute("recordCount", activityAllList.size());
        
        // ���÷�ҳ
        displayPage_danci();
        
        // ����
        return forward;
    }
    
    /**
     * ��ѯ��������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryDangCiDesc()
    {
        String forward = "success";
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        // ��ѯ��������
        /**
        Map mapResult = this.activitiesRecBean.queryDangciDesc(termInfo, customerSimp, curMenuId, dangciId);

        // ȡ������Ϣ
        if (mapResult != null && mapResult.size() > 0)
        {   
            CRSet crset = (CRSet) mapResult.get("returnObj");
            if (crset != null)
            {
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    // ��������
                    this.dangciName = crset.GetValue(i, 0);
                    
                    // ��������
                    this.dangciDesc  = crset.GetValue(i, 1);
                }
            }
            else
            {
                errormessage = (String)mapResult.get("returnMsg");
                // ����ʱע��
                return "error";
            }
        }
        else
        {
            errormessage = "��ѯ���������쳣��";
            return "error";
        }
        **/
        
        // ���ݻ�����뵵�α����ѯ
        ActivityVO vo = new ActivityVO();
        vo.setActivityId(activityId);
        vo.setDangciId(dangciId);
        List<ActivityVO> list = this.activitiesRecHubService.getDangciById(vo);
        
        // ��������
        this.dangciName = "";
        
        // ��������
        this.dangciDesc  = "";
        
        if (list != null && list.size() > 0)
        {
            vo = list.get(0);
            this.dangciName = vo.getDangciName();
            this.dangciDesc = vo.getActivityDesc();
        }
        
        
        
        // ���ýӿڲ�ѯ��Ʒ�б�
        Map result = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        if (result != null && result.size() > 0)
        {   
            CRSet crset = (CRSet) result.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                if ( i+1 == crset.GetRowCount() )
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0);
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
                else
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // �ֻ�imeiid��
                    imeiid = imeiid + "|";
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            errormessage = "��ѯ��Ʒ�б��쳣��";
            return "error";
        }
        
        // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        // ����
        return forward;
    }
    
    /**
     * ��ѯ�����µ���Ʒ�б�
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String queryPresentsList()
    {
        String forward = "success";

        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        // ���ýӿڲ�ѯ��Ʒ�б�
        Map mapResult = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        AwardVO vo = null;
        if (mapResult != null && mapResult.size() > 0)
        {   
            CRSet crset = (CRSet) mapResult.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                vo = new AwardVO();
                vo.setRewardId(crset.GetValue(i, 0));// ��Ʒ����
                vo.setRewardName(crset.GetValue(i, 1));// ��Ʒ����
                vo.setRewardType(crset.GetValue(i, 2));// ��Ʒ����
                vo.setRewardTypeName(this.getDictName(crset.GetValue(i, 2),"RwdGiftType"));// ��Ʒ��������
                vo.setRewardValue(Integer.parseInt(crset.GetValue(i, 3))/100+"");// ��Ʒ��ֵ
                vo.setScoreDealType(crset.GetValue(i, 4));// ���ֿۼ�����
                if ("0".equals(crset.GetValue(i, 4)))
                {
                    vo.setScoreDealTypeName("��ͨ�ۼ�");
                } 
                else if ("1".equals(crset.GetValue(i, 4)))
                {
                    vo.setScoreDealTypeName("����");
                }
                else
                {
                    vo.setScoreDealTypeName(crset.GetValue(i, 4));
                }
                
                vo.setUserScore(crset.GetValue(i, 5));// �ۼ���������
                vo.setRewardNote(crset.GetValue(i, 6));// ��Ʒ˵��
                awardAllList.add(vo);// ���α���
                
                if ( i+1 == crset.GetRowCount() )
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0);
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
                else
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // �ֻ�imeiid��
                    imeiid = imeiid + "|";
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            errormessage = "��ѯ��Ʒ�б��쳣��";
            return "error";
        }

        this.getRequest().setAttribute("recordCount", awardAllList.size());
        
        // ��ҳ
        displayPage_present();
        
        // ����
        return forward;
    }
    
    /**
     * Э������ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String recDutyInfo()
    {
        String forward = "success";
        
        // ����
        return forward;
    }
    
    /** 
     * ȡ������Э���б�
     * 
     * @return List<DictItemPO>
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-2-9 OR_HUB_201501_167_HUB_���������ն˲˵��㼶�Ż�����
     */
    public List<DictItemPO> getAgreementList()
    {
        return getDictItemByGrp("ActivityAgreement");
    }
    
    /**
     * ѡ��ɷ�����ҳ�棨ִ��Ԥ����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String selectType()
    {
        String forward = "success";
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // ִ�лԤ����
        String onlycheck = "1";// 1��Ԥ����0������
        String accesstype = "bsacAtsv";// ��������
        String password = "";// ȡ������
        String totalfee = "";// �ܽ�� 
        String paytype = "Cash";// ֧����ʽcash:�ֽ�  card:������ 
        quantity = 1;// �̶�д��
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, totalfee, paytype);
        if (mapResult != null && mapResult.size() == 2)
        {   
            // �����ն����Ի����л�ȡ�˵��б�
            String groupID = termInfo.getTermgrpid();
            
            List<MenuInfoPO> menus = null;
            
            if (groupID != null && !"".equals(groupID))
            {
                menus = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(groupID);
            }
            
            // ��ҳ�˵��б�
            usableTypes = CommonUtil.getChildrenMenuInfo(menus, "activitiesRec", "");
            
            // findbugs�޸� 2015-09-02 zKF66389
//            if (logger.isInfoEnabled())
//            {
//                logger.info("��ǰ�����ѳ�ֵ�Ŀ�ѡ��ʽ��" + (usableTypes == null ? 0 : usableTypes.size()) + "��");
//            }
            // findbugs�޸� 2015-09-02 zKF66389
        }
        else if (mapResult != null && mapResult.size() == 1)
        {
            errormessage = (String)mapResult.get("returnMsg");
            return "error";
        }
        else
        {
            errormessage = "ִ�лԤ����ʧ�ܣ�";
            return "error";
        }
        
        // ����
        return forward;
    }
    
    /**
     * ת��Ͷ��ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cashActivities()
    {
        String forward = "success";
        
        // ����
        return forward;
    }
    
    /**
     * �����������ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String finish()
    {
        String forward = "success";
        
        DecimalFormat dformat = new DecimalFormat("#.##"); 
        
        this.payType = "4";
        
        // ��ʾ���
        totalfee_yuan = "0".equals(totalFee) ? "0" : dformat.format(Double.parseDouble(totalFee)/100.00) + "" ;
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ֻ�����
        servnumber = customerSimp.getServNumber();
        
        // ����ɷ�����֮ǰ���ȼ�¼Ͷ����־
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        //--------�ɷѼ�¼��־sh_charge_log(Ͷ��֮��ҵ������֮ǰִ�м�¼)----------------------------------------------------------------
        
        // ����Ͷ����־
        String logOID = activitiesRecHubService.getChargeLogOID();
        
        // ��װ����
        selfCardPayLogVO.setChargeLogOID(logOID);// id
        selfCardPayLogVO.setRegion(termInfo.getRegion());// ����
        selfCardPayLogVO.setTermID(termInfo.getTermid());// �ն�ID
        selfCardPayLogVO.setOperID(termInfo.getOperid());// ����ԱID
        selfCardPayLogVO.setServnumber(servnumber);// �ֻ���
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);// �ֽ�Ͷ����־
        selfCardPayLogVO.setFee(this.totalFee);// �û�Ͷ����
        
        // �ն���ˮ(�ն�id+�ֽ�ɷ���ˮ ��ȡ��20λ)
        terminalSeq = termInfo.getTermid() + terminalSeq;
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);// �ն���ˮ
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        selfCardPayLogVO.setRecdate(payDate);// ����ʱ��
        selfCardPayLogVO.setAcceptType("");// �������ͣ��գ�
        selfCardPayLogVO.setServRegion(customerSimp.getRegionID());// �ֻ���������
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setStatus("01");
        selfCardPayLogVO.setDescription("�ɷ�֮ǰ��¼Ͷ����־");
        selfCardPayLogVO.setDealnum(""); // boss�ɷ���ˮ �ɷѳɹ��󷵻�
        selfCardPayLogVO.setRecType("activity");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷѣ�
        
        // ִ��������¼
        activitiesRecHubService.addChargeLog(selfCardPayLogVO);
        
        //----------������־sh_log_priv-------------------------------------------------------------------------------------------------
        
        PrivLogVO privLogVO = new PrivLogVO();
        privLogVO.setChargeID(logOID); // �ɷѵ���ˮ
        privLogVO.setRegion(termInfo.getRegion()); // ����
        privLogVO.setServnumber(servnumber);
        privLogVO.setPrivId(this.activityId);// �����
        privLogVO.setPrivNcode(this.dangciId);// ���α���
        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        privLogVO.setChargeType("4"); // �ֽ�ɷ�  �ɷѷ�ʽ��1����������4���ֽ�
        privLogVO.setPrivFee(String.valueOf(Integer.parseInt(this.prepayFee)*100));// �������
        privLogVO.setToFee(this.totalFee);// �û�ʵ�ɽ���
        privLogVO.setChargeFee("0");// ����ɷѽ���
        privLogVO.setRecType("activity");//favourable -- �Żݰ���activity -- �������
        
        //----------ִ��ҵ������---------------------------------------------------------------------------------------------------
        
        // ִ�л����
        String onlycheck = "0";// 1��Ԥ����0������
        String accesstype = "bsacAtsv";// ��������
        String password = "";// ȡ������
        String paytype = "Cash";// ֧����ʽcash:�ֽ�  card:������ 
        
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, totalFee, paytype);
        
        // ����ɹ�
        if (mapResult != null && mapResult.size() > 1)
        {   
            Object obj = mapResult.get("returnObj");
            if (obj instanceof CTagSet)
            {
                CTagSet chargeInfo = (CTagSet)obj;
                recoid = (String)chargeInfo.GetValue("recoid");// ������ˮ
                
                selfCardPayLogVO.setRecdate(sdf1.format(new Date()));
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("����������ֽ�ɷѳɹ���");
                selfCardPayLogVO.setDealnum(recoid);
                
                // ��¼�ɷѳɹ���־
                this.createRecLog(servnumber,Constants.PAY_BYCASH,logOID,totalFee,"0","���������:�����ն˽ɷѳɹ�!");
                
                // ��¼��������־
                privLogVO.setRecStatus("05"); // ����ɹ�
                privLogVO.setRecStatusDesc("���������ɹ���"); // ����
                
                // �ɹ���־
                successBz = "0";
                
                // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
                if ("1".equals(tipFlag))
                {
                    tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCNOINPUTTIP", curMenuId);
                }
                // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                
                forward = "success";
            }
        }
        // ����ʧ��
        else
        {
            selfCardPayLogVO.setRecdate(payDate);
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("����������ֽ�ɷ�ʧ�ܣ�");
            selfCardPayLogVO.setDealnum("");
            
            // ��¼�ɷ�ʧ����־
            this.createRecLog(servnumber,Constants.PAY_BYCASH,logOID,totalFee,"1","���������:�����ն˽ɷ�ʧ��!");
            
            // ��¼�����Żݵ���־
            privLogVO.setRecStatus("01"); // ����ʧ��
            privLogVO.setRecStatusDesc("�����ʧ��,��ƾ����ƾ����Ӫҵ�������˿�!"); // ����
            
            // �ɹ���־
            successBz = "1";
            
            forward = "error";
        }
        
        
        // ����ɹ�_ȡ����
        if (mapResult != null && mapResult.size() > 1)
        {   
            // ���ýӿڲ�ѯ��Ʒ�б�
            Map map = this.activitiesRecBean.qryPrivFee(termInfo, customerSimp, curMenuId, recoid, totalFee);
            if (map != null && map.size() > 0)
            {   
                CRSet crset = (CRSet) map.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    yxfaFee = crset.GetValue(i, 0);// Ӫ����������
                    ycFee = crset.GetValue(i, 1);// �û�Ԥ��
                    yxfaFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 0))/100.00) + "";// Ӫ����������
                    ycFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 1))/100.00) + "";// �û�Ԥ��
                    privLogVO.setPrivFee(crset.GetValue(i, 0));// �������
                    privLogVO.setChargeFee(crset.GetValue(i, 1));// ����ɷѽ���
                }
            }
            else
            {
                yxfaFee_yuan = "0";// Ӫ����������
                ycFee_yuan = "0";// �û�Ԥ��
                privLogVO.setChargeFee("0");// ����ɷѽ���
                errormessage = "��ѯ��������쳣��";
                return "error";
            }
        
        }
        
        //-------------���½ɷѼ�¼--------------------------------------------------------------------------------------------
        activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
        
        //-------------���������¼--------------------------------------------------------------------------------------------
        activitiesRecHubService.createPrivLog(privLogVO);
        
        //-------------��ӡ��Ϣ------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        
        // ����
        return forward;
    }
    
    /**
     * ��Ʊ��ӡ��־
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String goPrintInvoice()
    {
        // ȡ��Ʊ����
        List list = printInvoice(recoid);
        
        // �����쳣
        if (null == list)
        {
            getRequest().setAttribute("errormessage", "��ȡ��Ʊ����Ϊ��");
            return "error";
        }
        
        // ��װ��XML����
        String invoice = getXmlStr(list);

        // ����request
        this.getRequest().setAttribute("invoice", invoice);
        
        // ����
        return "printInvoice";
    }
    
    /**
     * �ֽ𽻷��쳣����
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String goCashError()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError Starting ...");
        }
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ֻ�����
        servnumber = customerSimp.getServNumber();
        
        this.createRecLog(servnumber, Constants.PAY_BYCASH, "0", "0", "1", errormessage);
        
        HttpSession session = getRequest().getSession();
        
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(date);
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(activitiesRecHubService.getChargeLogOID());
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        selfCardPayLogVO.setServnumber(servnumber);
        selfCardPayLogVO.setPayType(Constants.PAYBYMONEY);
        selfCardPayLogVO.setFee(totalFee);
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
        selfCardPayLogVO.setServRegion(customerSimp.getRegionID());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        selfCardPayLogVO.setRecType("activity");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷ� activity:�����)
        
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if ( totalFee == null || "".equals(totalFee.trim()) || "0".equals(totalFee.trim()))
        if ("".equals(totalFee.trim()) || "0".equals(totalFee.trim()))
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
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
        
        //-------------���½ɷѼ�¼--------------------------------------------------------------------------------------------
        activitiesRecHubService.addChargeLog(selfCardPayLogVO);
        
        //-------------��ӡ��Ϣ------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        totalfee_yuan = Integer.parseInt(totalFee)/100 + "";
        
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCashError End");
        }
        
        return "cashErrorPage";
    }
    
    /**
     * ת����������ҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String readCard()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("readCard Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("readCard End");
        }
        
        // ����
        return "success";
    }
    
    /**
     * ת����������������ҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPassword Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPassword End");
        }
        
        // ����
        return "success";
    }
    
    /**
     * ת��ȷ�����п��ɷѽ��ҳ��
     * 
     * @return
     */
    public String makeSure()
    {
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ֻ�����
        servnumber = customerSimp.getServNumber();
        
        // ����
        return "makeSure";
    }
    
    /**
     * �ۿ�֮ǰ�����������ɷ���־
     * 
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    public void addChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addCardPayLog Starting...");
        }
        
        // request
        HttpServletRequest request = this.getRequest();
        
        // response
        HttpServletResponse response = this.getResponse();
        
        // ajax
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        String xml = "";
        
        try
        {
            writer = response.getWriter();
            
            // session
            HttpSession session = request.getSession();
            
            // �ն˻���Ϣ
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // �û���Ϣ
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
            // ״̬
            String status = (String)request.getParameter("status");
            
            // ������Ϣ
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // pos����
            String posNum = (String)request.getParameter("posnum");
            
            // �ۿ�֮ǰ���κ�
            String batchNumBeforeKoukuan = (String)request.getParameter("batchnumbeforekoukuan");
            
            // ��ˮ��
            String logOID = activitiesRecHubService.getChargeLogOID();
            
            // ��װ��־����
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(logOID);// ��ˮ��
            cardChargeLogVO.setRegion(termInfo.getRegion());// ����
            cardChargeLogVO.setTermID(termInfo.getTermid());// �ն�ID
            cardChargeLogVO.setOperID(termInfo.getOperid());// ����ԱID
            cardChargeLogVO.setServnumber(servnumber);// �ֻ���
            cardChargeLogVO.setPayType("1");// ��������
            cardChargeLogVO.setFee(String.valueOf(Integer.parseInt(this.prepayFee) * 100));// �ɷѽ��
            cardChargeLogVO.setUnionpayuser("");// �̻����
            cardChargeLogVO.setUnionpaycode("");// �ն˱�ţ�pos����ţ�
            cardChargeLogVO.setBusiType("");// �������� �磺���ѽ���
            
            //modify begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            //�����ݿ��������ܺ����������
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardnumber));
            //modify end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
            
            cardChargeLogVO.setBatchnum("");// �ն����κ�
            cardChargeLogVO.setAuthorizationcode("");// ��Ȩ�루ɽ���ã�
            cardChargeLogVO.setBusinessreferencenum("");// ���ײο��ţ�ɽ���ã�
            cardChargeLogVO.setUnionpaytime("");// ����ʵ�ʿۿ�ʱ��
            cardChargeLogVO.setVouchernum("");// ƾ֤�ţ�ɽ���ã�
            cardChargeLogVO.setUnionpayfee("");// ����ʵ�ʿۿ����λ���֣�
            cardChargeLogVO.setTerminalSeq(terminalSeq);// �ն���ˮ
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());
            cardChargeLogVO.setRecdate(payDate);// ����ʱ��
            cardChargeLogVO.setStatus(status);// ״̬
            cardChargeLogVO.setDescription(description);// ������Ϣ
            cardChargeLogVO.setDealnum("");// BOSS������
            cardChargeLogVO.setAcceptType("");// BOSS��������
            cardChargeLogVO.setServRegion(customerSimp.getRegionID());// ����
            cardChargeLogVO.setOrgID(termInfo.getOrgid());// ������λ
            cardChargeLogVO.setPosNum(posNum);// pos���
            cardChargeLogVO.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);// �ۿ�ǰ���κ�
            cardChargeLogVO.setRecType("activity");// ҵ�����ͣ�phone:���ѽɷ� favourable:�Żݽɷ� activity:�����
            
            // ����ɷ���־
            activitiesRecHubService.addChargeLog(cardChargeLogVO);
            
            // ������ˮ
            xml = "1~~" + logOID;
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
            logger.debug("addCardPayLog end!");
        }
        
        logger.debug("addCardPayLog End!");
    }
    
    /**
     * �ۿ�ɹ�֮�󣬸��½�����־
     * 
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updateCardChargeLog Starting...");
        }
        
        // request
        HttpServletRequest request = this.getRequest();
        
        // response
        HttpServletResponse response = this.getResponse();
        
        // ajax
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;

        String xml = "";
        try
        {
            writer = response.getWriter();
            
            // ״̬
            String status = (String)request.getParameter("status");
            
            // ������Ϣ
            String description = (String)request.getParameter("description");
            description = java.net.URLDecoder.decode(description, "UTF-8");
            
            // ��װ��־����
            CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
            cardChargeLogVO.setChargeLogOID(chargeLogOID);// ��ˮ��
            cardChargeLogVO.setUnionpayuser(unionpayuser);// �̻����
            cardChargeLogVO.setUnionpaycode(unionpaycode);// �ն˱��
            busiType = java.net.URLDecoder.decode(busiType, "UTF-8");
            cardChargeLogVO.setBusiType(busiType);// ��������
            cardChargeLogVO.setBatchnum(batchnum);// �ն����κ�
            cardChargeLogVO.setAuthorizationcode(authorizationcode);// ��Ȩ��(ɽ����)
            cardChargeLogVO.setBusinessreferencenum(businessreferencenum);// ���ײο���(ɽ����)
            cardChargeLogVO.setUnionpaytime(unionpaytime);// ����ʵ�ʿۿ�ʱ��
            cardChargeLogVO.setVouchernum(vouchernum);// ƾ֤��(ɽ����)
            cardChargeLogVO.setUnionpayfee(unionpayfee);// ����ʵ�ʿۿ���
            cardChargeLogVO.setStatus(status);// ״̬
            cardChargeLogVO.setDescription(description);// ����
            cardChargeLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// ����ʱ��
            
            cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);

            // ����ɷ���־
            activitiesRecHubService.updateCardChargeLog(cardChargeLogVO);
            
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
     * �����������ҳ��
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String cardFinish()
    {
        String forward = "success";
        
        DecimalFormat dformat = new DecimalFormat("#.##"); 
        
        // ��ʾ���
        totalfee_yuan = dformat.format(Double.parseDouble(unionpayfee)/100.00) + "";
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ֻ�����
        servnumber = customerSimp.getServNumber();
        
        this.payType = "1";
        
        //----------�ɷѳɹ���־sh_charge_log-------------------------------------------------------------------------------------------------
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(this.chargeLogOID);
        selfCardPayLogVO = activitiesRecHubService.getChargeLogById(selfCardPayLogVO).get(0);
        
        //----------������־sh_log_priv-------------------------------------------------------------------------------------------------
        PrivLogVO privLogVO = new PrivLogVO();
        privLogVO.setPrivLogOID(activitiesRecHubService.getChargeLogOID());// ID
        privLogVO.setChargeID(this.chargeLogOID);// �ɷѵ���ˮ
        privLogVO.setRegion(termInfo.getRegion()); // ����
        privLogVO.setServnumber(servnumber);
        privLogVO.setPrivId(this.activityId);// �����
        privLogVO.setPrivNcode(this.dangciId);// ���α���
        privLogVO.setRecDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        privLogVO.setChargeType("1"); // �ֽ�ɷ�  �ɷѷ�ʽ��1����������4���ֽ�
        privLogVO.setPrivFee(String.valueOf(Integer.parseInt(this.prepayFee)*100));// �������
        privLogVO.setToFee(this.unionpayfee);// �û�ʵ�ɽ���
        privLogVO.setChargeFee("0");// ����ɷѽ���
        privLogVO.setRecType("activity");//favourable -- �Żݰ���activity -- �������
        
        //----------ִ��ҵ������---------------------------------------------------------------------------------------------------
        
        // ִ�л����
        String onlycheck = "0";// 1��Ԥ����0������
        String accesstype = "bsacAtsv";// ��������
        String password = "";// ȡ������
        String paytype = "Card";// ֧����ʽcash:�ֽ�  card:������ 
        
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, accesstype, password, unionpayfee, paytype);
        
        // ����ɹ�
        if (mapResult != null && mapResult.size() > 1)
        {   
            Object obj = mapResult.get("returnObj");
            if (obj instanceof CTagSet)
            {
                CTagSet chargeInfo = (CTagSet)obj;
                recoid = (String)chargeInfo.GetValue("recoid");// ������ˮ
                
                selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                selfCardPayLogVO.setStatus("11");
                selfCardPayLogVO.setDescription("����������������ɷѳɹ���");
                selfCardPayLogVO.setDealnum(recoid);
                
                // ��¼�ɷѳɹ���־
                this.createRecLog(servnumber,Constants.PAY_BYCARD,this.chargeLogOID,this.unionpayfee,"0","���������:�����ն˽ɷѳɹ�!");
                
                // ��¼��������־
                privLogVO.setRecStatus("05"); // ����ɹ�
                privLogVO.setRecStatusDesc("���������ɹ���"); // ����
                
                // �ɹ���־
                successBz = "0";
                
                // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
                if ("1".equals(tipFlag))
                {
                    tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCNOINPUTTIP", curMenuId);
                }
                // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
                
                forward = "success";
            }
        }
        // ����ʧ��
        else
        {
            selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription("��������������ɷ�ʧ�ܣ�");
            selfCardPayLogVO.setDealnum("");
            
            // ��¼�ɷ�ʧ����־
            this.createRecLog(servnumber,Constants.PAY_BYCASH,this.chargeLogOID,this.unionpayfee,"1","���������:�����ն˽ɷ�ʧ��!");
            
            // ��¼�����Żݵ���־
            privLogVO.setRecStatus("01"); // ����ʧ��
            privLogVO.setRecStatusDesc("�����ʧ��,��ƾ����ƾ����Ӫҵ�������˿�!"); // ����
            
            // �ɹ���־
            successBz = "1";
            
            forward = "error";
        }
        
        // ����ɹ�_ȡ����
        if (mapResult != null && mapResult.size() > 1)
        {   
            // ���ýӿڲ�ѯ��Ʒ�б�
            Map map = this.activitiesRecBean.qryPrivFee(termInfo, customerSimp, curMenuId, recoid, this.unionpayfee);
            if (map != null && map.size() > 0)
            {   
                CRSet crset = (CRSet) map.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    yxfaFee = crset.GetValue(i, 0);// Ӫ����������
                    ycFee = crset.GetValue(i, 1);// �û�Ԥ��
                    yxfaFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 0))/100.00) + "";// Ӫ����������
                    ycFee_yuan = dformat.format(Double.parseDouble(crset.GetValue(i, 1))/100.00) + "";// �û�Ԥ��
                    privLogVO.setPrivFee(crset.GetValue(i, 0));// �������
                    privLogVO.setChargeFee(crset.GetValue(i, 1));// ����ɷѽ���
                }
            }
            else
            {
                yxfaFee_yuan = "0";// Ӫ����������
                ycFee_yuan = "0";// �û�Ԥ��
                privLogVO.setChargeFee("0");// ����ɷѽ���
                errormessage = "��ѯ��������쳣��";
                return "error";
            }
        
        }
        
        //-------------���½ɷѼ�¼--------------------------------------------------------------------------------------------
        selfCardPayLogVO.setPosResCode(null == cmtPosResCode ? "" : cmtPosResCode);
        activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
        
        //-------------���������¼--------------------------------------------------------------------------------------------
        activitiesRecHubService.createPrivLog(privLogVO);
        
        //-------------��ӡ��Ϣ------------------------------------------------------------------------------------------------
        region = termInfo.getRegion();
        termid = termInfo.getTermid();
        orgname = termInfo.getOrgname();
        termName = termInfo.getTermname();
        
        // ����
        return forward;
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
        
        // session
        HttpSession session = getRequest().getSession();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
            // �û���Ϣ
            NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
            
            // �ֻ�����
            servnumber = customerSimp.getServNumber();
            
            // ������־
            this.createRecLog(servnumber, Constants.PAY_BYCARD, "0", "0", "1", errormessage);
            
            // ����
            CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
            
            // add:�ۿ�֮ǰ�쳣 
            // update:�ۿ�֮���쳣
            if (errorType == null || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
            {
                selfCardPayLogVO.setChargeLogOID(activitiesRecHubService.getChargeLogOID());// id
                selfCardPayLogVO.setRegion(termInfo.getRegion());// ����
                selfCardPayLogVO.setTermID(termInfo.getTermid());// �ն�ID
                selfCardPayLogVO.setOperID(termInfo.getOperid());// ����Ա
                selfCardPayLogVO.setServnumber(servnumber);// �ֻ���
                selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);// ���׷�ʽ
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(this.prepayFee));// ������
                selfCardPayLogVO.setTerminalSeq("");// �ն���ˮ
                selfCardPayLogVO.setRecdate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// ����ʱ��
                selfCardPayLogVO.setStatus("00");// ��ʼ״̬
                selfCardPayLogVO.setDescription(errormessage);// �쳣��Ϣ
                selfCardPayLogVO.setDealnum("");// BOSS������
                selfCardPayLogVO.setAcceptType("");// ��������
                selfCardPayLogVO.setServRegion(customerSimp.getRegionID());// �ֻ�����REGION
                selfCardPayLogVO.setOrgID(termInfo.getOrgid());// �ն�������λ
                selfCardPayLogVO.setRecType("activity");// ҵ�����ͣ�phone�����ѽɷ� favourable���Żݽɷ� activity:�����)
                
                // ���ӽɷѼ�¼
                activitiesRecHubService.addChargeLog(selfCardPayLogVO);
            }
            // ������־
            else
            {
                selfCardPayLogVO.setChargeLogOID(this.chargeLogOID);
                
                if (this.unionpayfee == null || "".equals(this.unionpayfee.trim()))
                {
                    selfCardPayLogVO.setStatus("00");
                }
                else
                {
                    selfCardPayLogVO.setStatus("10");
                }
                selfCardPayLogVO.setDescription(errormessage);
                selfCardPayLogVO.setDealnum("");
                
                // �����ն˺�
                selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
                
                // �����̻���
                selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
                
                // �����ۿ����
                selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
                
                // ���´�����Ϣ
                activitiesRecHubService.updateChargeLog(selfCardPayLogVO);
            }
            
            //-------------��ӡ��Ϣ------------------------------------------------------------------------------------------------
            region = termInfo.getRegion();
            termid = termInfo.getTermid();
            orgname = termInfo.getOrgname();
            termName = termInfo.getTermname();
        }
        catch (Exception e)
        {
            // ����һ���쳣,ʹҳ��������˿�ҳ��
            logger.error(e);
            e.printStackTrace();
            errormessage = errormessage + e.getMessage();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("goCardError End");
        }
        
        // ת���������˿�ҳ��
        return "cardErrorPage";
    }
    
    /**
     * ���ýӿڴ�ӡ��Ʊ
     * 
     * @param invoiceType
     * @param dealNum
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, String>> printInvoice(String dealNum)
    {
        // modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089        
        HttpServletRequest request = this.getRequest();
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPINVOICE", curMenuId);
        }

        // �ṩ��Ʊ��ӡ����ʱ����ȡ��Ʊ��Ϣ
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = this.activitiesRecBean.getInvoiceData(termInfo, curMenuId, servnumber, dealNum);
            
            if (invoiceData != null && invoiceData.size() > 1)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof Vector)
                {
                    Vector invoiceVector = (Vector)invoiceObj;
                    
                    CTagSet tagSet = (CTagSet)invoiceVector.get(0);
                    String cycleCount = (String)tagSet.GetValue("invoice_cnt");
                    int rowNum = Integer.parseInt(cycleCount);
                    
                    CRSet crset = (CRSet)invoiceVector.get(1);
                    
                    List<Map<String, String>> invoicesList = new ArrayList<Map<String, String>>();
                    
                    for (int i = 0; i < rowNum; i++)
                    {
                        invoicesList.add(getInvoiceInfo(i, crset, tip));
                    }
                    
                    return invoicesList;
                }
            }
        }
        // modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        return null;
    }
    
    /**
     * �Կͷ����صĴ�ӡ��Ʊ���ݽ��д��� <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    
    public Map<String, String> getInvoiceInfo(int inde, CRSet cRSet, String tipText)
    {
        Map<String, String> invoiceMaps = new HashMap<String, String>();
        invoiceMaps.put("tipText", tipText);
        
        String[] invoiceArray = cRSet.GetValue(inde, 1).split("\\|\\|");
        // �ɷ�ʱ��
        invoiceMaps.put("chargeDate", cRSet.GetValue(inde, 0));
        
        // �ͻ�����
        invoiceMaps.put("username", getInvoiceItem(invoiceArray, "username"));
        
        // ��������
        invoiceMaps.put("callfeeCreateTime", getInvoiceItem(invoiceArray, "feetype"));
        
        // �绰����
        invoiceMaps.put("telnumber", getInvoiceItem(invoiceArray, "telnumber"));
        
        // �绰����
        invoiceMaps.put("formnum", getInvoiceItem(invoiceArray, "formnum"));
        
        // ��д���
        invoiceMaps.put("invoicefeedx", getInvoiceItem(invoiceArray, "invoicefeedx"));
        
        // Сд���
        invoiceMaps.put("invoicefee", getInvoiceItem(invoiceArray, "invoicefee"));
        
        // ��ͬ��
        invoiceMaps.put("paynumno", getInvoiceItem(invoiceArray, "paynumno"));
        
        // ��ͬ��
        invoiceMaps.put("paynumno", getInvoiceItem(invoiceArray, "paynumno"));
        
        // ���úϼ�
        invoiceMaps.put("invoicefeehj", getInvoiceItem(invoiceArray, "invoicefeehj"));
        
        // ����
        invoiceMaps.put("Score", getInvoiceItem(invoiceArray, "Score"));
        
        invoiceMaps.put("agreementleftbal", getInvoiceItem(invoiceArray, "hz_agreementleftbal") + "@"
                + getInvoiceItem(invoiceArray, "agreementleftbal"));
        
        invoiceMaps.put("agreementleftbal_Z", getInvoiceItem(invoiceArray, "hz_agreementleftbal_Z") + "@"
                + getInvoiceItem(invoiceArray, "agreementleftbal_Z"));
        
        invoiceMaps.put("InvoiceNote", getInvoiceItem(invoiceArray, "note"));
        
        // ��ӡʱ��
        invoiceMaps.put("printtime", getInvoiceItem(invoiceArray, "hz_printtime") + "@"
                + getInvoiceItem(invoiceArray, "printtime"));
        
        // ��ӡʱ��
        invoiceMaps.put("totalmoney", getInvoiceItem(invoiceArray, "hz_totalmoney") + "@"
                + getInvoiceItem(invoiceArray, "totalmoney"));
        
        // �ۼƽ���
        invoiceMaps.put("leftmoney", getInvoiceItem(invoiceArray, "hz_leftmoney") + "@"
                + getInvoiceItem(invoiceArray, "leftmoney"));
        
        //
        invoiceMaps.put("overdraft", getInvoiceItem(invoiceArray, "hz_overdraft") + "@"
                + getInvoiceItem(invoiceArray, "overdraft"));
        
        return invoiceMaps;
        
    }
    
    /**
     * ��ȡ��Ʊ��ֵ <������ϸ����>
     * 
     * @param invoiceArray
     * @param itemName
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getInvoiceItem(String[] invoiceArray, String itemName)
    {
        String printItem = "";
        for (int i = 0; i < invoiceArray.length; i++)
        {   
            String[] invoArr = invoiceArray[i].split("@~@");
            
                if (invoArr.length > 1 && invoArr[0].equals(itemName) )
                {
                    printItem = invoArr[1];
                    
                    break;
                }
               
        }
        return printItem;
    }
    
    /**
     * ����Ʊ������֯��xml
     * 
     * @param list ��Ʊ����
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
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
     * ��¼��Ʊ��ӡ��־
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String recoid = request.getParameter("recoid");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintPO record = new InvoicePrintPO();
        record.setServNumber(servNumber);
        record.setFormnum(recoid);
        record.setTermID(termInfo.getTermid());
        
        this.activitiesRecHubService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
    /**
     * ȡ�ֵ���
     * <������ϸ����>
     * @param dictID
     * @param groupID
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String getDictName(String dictID, String groupID)
    {
        if (null == dictID || null == groupID)
        {
            return dictID;
        }
        if (!activitiesRecBean.pubMap.containsKey(groupID))
        {
            // ��ҪȥCRMϵͳ���ȡ�ֵ������
            Map<String, String> inMap = new HashMap<String, String>();
            getInMap(inMap);
            inMap.put("groupid", groupID);
            activitiesRecBean.getDictItemByGroup(inMap);
        }
        Vector<DictItemVO> v = (Vector<DictItemVO>)activitiesRecBean.pubMap.get(groupID);
        for (DictItemVO dictItem : v)
        {
            if (dictID.equals(dictItem.getDictid()))
            {
                return dictItem.getDictname();
            }
        }
        
        return dictID;
    }
    
    /**
     * <һ�仰���ܼ���>
     * <������ϸ����>
     * @param inMap
     * @see [�ࡢ��#��������#��Ա]
     */
    private void getInMap(Map<String, String> inMap)
    {
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���ò���Աid
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��Ӵ�id
        inMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // ���ÿͻ��ֻ���
        inMap.put("telnumber", customer.getServNumber());
        inMap.put("telnum", customer.getServNumber());
        
        // ����
        inMap.put("region", terminalInfoPO.getRegion());
    }


    //------------------���ҳ-------------------------------------------------------------------------------------------
    /**
     * ��ǰҳ
     */
    private int page = 1;
    
    /**
     * ������
     */
    private int countNum = 0;
    
    /**
     * ÿҳ����
     */
    private int pageNum = 6;
    
    /**
     * ��ҳ��
     */
    private int countPageNum = 0;
    
    /**
     * ȡ��ǰҳ������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void displayPage()
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // �������
        activityList.clear();
        
        // ����������
        countNum = activityAllList.size();
        
        // ������ҳ��
        if (countNum % pageNum > 0)
        {
            countPageNum = countNum / pageNum + 1;
        }
        else
        {
            countPageNum = countNum / pageNum;
        }
        
        // ��ʼ����
        int startNum = pageNum * (page - 1) + 1;
        
        // ��������
        int endNum = pageNum * page;
        
        // ��ʼ����
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum)
            {
                this.activityList.add(activityAllList.get(i-1));
            }
        }
        
        // ҳ��18����¼ List<ActivityVO>
        /**
        if (this.activityList.size() < pageNum)
        {
            int size = activityList.size();
            ActivityVO activityVO = new ActivityVO();
            activityVO.setGroupId("");
            activityVO.setGroupName("");
            for(int i=size;i<pageNum;i++)
            {
                this.activityList.add(activityVO);
            }
        }
        */
        
    }
    
    //------------------���η�ҳ-------------------------------------------------------------------------------------------
    /**
     * ������
     */
    private int countNum_danci = 0;
    
    /**
     * ÿҳ����
     */
    private int pageNum_danci = 4;
    
    /**
     * ��ҳ��
     */
    private int countPageNum_danci = 0;
    
    /**
     * ȡ��ǰҳ������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void displayPage_danci()
    {
        // �������
        activityList.clear();
        
        // ����������
        countNum_danci = activityAllList.size();
        
        // ������ҳ��
        if (countNum_danci % pageNum_danci > 0)
        {
            countPageNum_danci = countNum_danci / pageNum_danci + 1;
        }
        else
        {
            countPageNum_danci = countNum_danci / pageNum_danci;
        }
        
        if (page == 0)
        {
            page = 1;
        }
        
        // ��ʼ����
        int startNum = pageNum_danci * (page - 1) + 1;
        
        // ��������
        int endNum = pageNum_danci * page;
        
        // ��ʼ����
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum_danci)
            {
                this.activityList.add(activityAllList.get(i-1));
            }
        }
    }
    
    //------------------��Ʒ��ҳ-------------------------------------------------------------------------------------------
    /**
     * ������
     */
    private int countNum_present = 0;
    
    /**
     * ÿҳ����
     */
    private int pageNum_present = 4;
    
    /**
     * ��ҳ��
     */
    private int countPageNum_present = 0;
    
    /**
     * ȡ��ǰҳ������
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void displayPage_present()
    {
        // �������
        awardList.clear();
        
        // ����������
        countNum_present = awardAllList.size();
        
        // ������ҳ��
        if (countNum_present % pageNum_present > 0)
        {
            countPageNum_present = countNum_present / pageNum_present + 1;
        }
        else
        {
            countPageNum_present = countNum_present / pageNum_present;
        }
        
        if (page == 0)
        {
            page = 1;
        }
        
        // ��ʼ����
        int startNum = pageNum_present * (page - 1) + 1;
        
        // ��������
        int endNum = pageNum_present * page;
        
        // ��ʼ����
        for(int i=startNum;i<=endNum;i++)
        {
            if (i <= countNum_present)
            {
                this.awardList.add(awardAllList.get(i-1));
            }
        }
    }

    // add begin hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
    /**
     * ��¼bean
     */
    private transient UserLoginBean userLoginBean;
    
    /**
     * ��¼service
     */
    private transient LoginService loginService;
    
    /**
     * ��������
     */
    private String password;
    
    /**
     * ������֤��
     */
    private String randomPwd;
    
    /**
     * ����Ӫ���Ƽ���ʶ
     */
    private String recommendActivityFlag;

    /**
     * <����������֤�����лԤ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
     */
    public void chkServPwd()throws Exception
    {
        logger.info("chkServPwd start");
        
        // У���ʶ �ɹ���0��ʧ�ܣ�1~~������Ϣ
        String xml = "0";
        
        HttpSession session = this.getRequest().getSession();
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
       
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
        if (null != loginErrorPO && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // ������
            logger.error("���ڷ����������������������ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ������");
            
            xml = "11~~���ڷ����������������������ﵽ��ϵͳ���ƣ����ĺ�����ʱ����������" + lockedTime + "���Ӻ�����";
            writeXmlMsg(xml);
            return;
        }
        
        // ����������֤����ȡ�û���Ϣ
        Map customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
        
        // �����û���Ϣ
        xml = handleUserInfo(xml,customerSimpMap,session);
        
        // ��������У��ʧ��
        if(!"0".equals(xml))
        {
            // ��¼ʧ��
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("ʹ�÷���������������֤ʧ�ܣ��ֻ����룺" + servnumber);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ����������������֤ʧ�ܡ�");
            
            writeXmlMsg(xml);
            return;
        }
        
        // ��¼�ɹ���ɾ����¼
        loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
        
        // ��ѯ��Ʒ�����лԤ����
        preAccept();
    }
    
    /**
     * <�����û���Ϣ>
     * <������ϸ����>
     * @param xml �����ʶ
     * @param customerSimpMap �û���Ϣmap
     * @param session
     * @param orimsgid  ����ת����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
     */
    public String handleUserInfo(String xml,Map customerSimpMap,HttpSession session)
    {
        logger.debug("handleUserInfo start");
        
        // �û���Ϣmap�����û���Ϣ��У��ʧ��
        if (customerSimpMap.get("customerSimp") == null)
        {
            logger.error("��ȡ�û���Ϣʧ�ܣ��ֻ����룺" + servnumber);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "ʹ����������������֤ʧ�ܡ�");
            
            String returnMsg = (String)customerSimpMap.get("returnMsp");
            xml = "11~~" + returnMsg;
        }
        else
        {   
            // �û���Ϣ
            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
            
            // ԭ�û���Ϣ
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            // ʹ���µ��û���Ϣ�滻session��ԭ�û���Ϣ
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                // ���ֻ����뼰����֤��ʽ
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                // ����굥����
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // ����˵�����
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                
                // ����û���Ϣ
                session.removeAttribute(Constants.USER_INFO);
                
                // ���µ��û���Ϣ�����session��
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                // �����û���loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "ʹ�÷���������������֤�ɹ�");
        }
        
        logger.debug("handleUserInfo end");
        return xml;
    }
    
    /**
     * <У�������֤��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
     */
    public void chkRandomPwd()throws Exception
    {
        logger.debug("chkRandomPwd start");

        // У���ʶ �ɹ���0����Ȩʧ�ܣ�11~~������Ϣ  Ԥ����ʧ�ܣ�10~~������Ϣ 
        String xml = "0";
        
        // ��ѯ�����Ƿ�����
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // ������
            logger.error("���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����" + servnumber + "��ʱ����ʹ�ö�����֤����֤");
            
            xml = "11~~���ڶ�����֤����֤ʧ�ܴ����ﵽ��ϵͳ���ƣ�����ʱ����ʹ�ö�����֤����֤����" + lockedTime + "���Ӻ�����";
            writeXmlMsg(xml);
            return;
        }
        
        // У�������֤��
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        
        // ������֤��У��ʧ��
        if (!"1".equals(result))
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "�Բ���������Ķ�����֤���Ѿ���ʱ��";
            }
            else
            {
                errorMsg = "�Բ������Ķ�����֤���������";
            }

            // ��֤ʧ�ܼ�¼
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            xml = "11~~" + errorMsg;
            writeXmlMsg(xml);
            return;
        }
        
        // ��֤�ɹ���ɾ����¼
        loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        logger.info("�û�" + servnumber + "ʹ�ö�����֤����������֤�ɹ�");
        
        // ���е��ýӿڵ�¼
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        
        xml = handleUserInfo(xml,customerSimpMap,session);
        
        if(!"0".equals(xml))
        {
            writeXmlMsg(xml);
            return;
        }
        
        preAccept();
    }
    
    /**
     * <�Ԥ����>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
     */
    public void preAccept()throws Exception
    {
        logger.debug("preAccept start");
        
        // У���ʶ �ɹ���0����Ȩʧ�ܣ�11~~������Ϣ  Ԥ����ʧ�ܣ�10~~������Ϣ 
        String xml = "0";
        
        // �û���Ϣ
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // �ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // ����
        dangciId = java.net.URLDecoder.decode(dangciId, "UTF-8");
        activityId = java.net.URLDecoder.decode(activityId, "UTF-8");
        
        // ���ݻ�����뵵�α����ѯ
        ActivityVO vo = new ActivityVO();
        vo.setActivityId(activityId);
        vo.setDangciId(dangciId);
        List<ActivityVO> list = this.activitiesRecHubService.getDangciById(vo);
        
        // ��������
        this.dangciName = "";
        
        // ��������
        this.dangciDesc  = "";
        
        if (list != null && list.size() > 0)
        {
            vo = list.get(0);
            this.dangciName = vo.getDangciName();
            this.dangciDesc = vo.getActivityDesc();
        }

        // ���ýӿڲ�ѯ��Ʒ�б�
        Map result = this.activitiesRecBean.qryPresentList(termInfo, customerSimp, curMenuId, activityId, dangciId);
        if (result != null && result.size() > 0)
        {   
            CRSet crset = (CRSet) result.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                if ( i+1 == crset.GetRowCount() )
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0);
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
                else
                {
                    // ��Ʒ���봮
                    actreward = actreward + crset.GetValue(i, 0)+"|";
                    // �ֻ�imeiid��
                    imeiid = imeiid + "|";
                    // ��Ʒ�ܼ�
                    rewardAccount = rewardAccount + Integer.parseInt(crset.GetValue(i, 3));
                    // ��Ʒ����
                    quantity = quantity + 1;
                }
            }
        }
        else
        {
            xml = "10~~��ѯ��Ʒ�б��쳣!";
            writeXmlMsg(xml);
            return;
        }
        
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
        if ("1".equals(tipFlag))
        {
            tip = receptionBean.qryObjectTips(customerSimp, termInfo, "RewardActivity", dangciId, "RewardLevel", "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        
        // 1��Ԥ����0������
        String onlycheck = "1";
        
        // ��Ʒ����
        quantity = 1;
       
        // ִ�лԤ����
        Map mapResult = this.activitiesRecBean.recRewardCommit(termInfo, customerSimp, curMenuId, activityId, dangciId, 
                actreward, imeiid, onlycheck, quantity, "bsacAtsv", "", "", "Cash");

        if (mapResult == null)
        {
            xml = "10~~�Բ���ִ�лԤ����ʧ�ܣ�";
            writeXmlMsg(xml);
            return;
        }
        else if (mapResult.size() == 1)
        {
            String returnMsg = (String)mapResult.get("returnMsg");
            xml = "10~~" + returnMsg;
            writeXmlMsg(xml);
            return;
        }
        
        writeXmlMsg(xml);
    }
    
    /**
     * AJAX���÷�����Ϣ
     * @return void [��������˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
     */
    protected void writeXmlMsg(String msg)
    {
        PrintWriter writer = null;
        
        try
        {
            this.getResponse().setContentType("text/xml;charset=GBK");
            writer = this.getResponse().getWriter();
            
            if (null != writer)
            {
                writer.write(msg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // �����client
            if (writer != null)
            {
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * У���Ƿ�����
     * <������ϸ����>
     * @param loginErrorPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // ��������ﵽ��ϵͳ����
        if (maxTimes <= loginErrorPO.getErrorTimes())
        {
            String lastTime = loginErrorPO.getLastTime();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            try
            {
                // ����ʱ��
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // ��ǰʱ��
                String currTime = sdf.format(c.getTime());
                
                // ����ʱ��
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // ����ʱ��Ҫ���ڵ�ǰʱ�䣬����������������ڼ���
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("�жϺ����Ƿ�����ʱ�����쳣��", e);
            }
        }
        
        // ���������δ�ﵽϵͳ����
        return false;
    }
    // add end hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
    
    public String getRecommendActivityFlag()
    {
        return recommendActivityFlag;
    }

    public void setRecommendActivityFlag(String recommendActivityFlag)
    {
        this.recommendActivityFlag = recommendActivityFlag;
    }

    public String getRandomPwd()
    {
        return randomPwd;
    }

    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public int getCountNum()
    {
        return countNum;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setCountNum(int countNum)
    {
        this.countNum = countNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public int getPageNum()
    {
        return pageNum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getCountPageNum()
    {
        return countPageNum;
    }

    public void setCountPageNum(int countPageNum)
    {
        this.countPageNum = countPageNum;
    }

    public ActivitiesRecHubService getActivitiesRecHubService()
    {
        return activitiesRecHubService;
    }

    public void setActivitiesRecHubService(ActivitiesRecHubService activitiesRecHubService)
    {
        this.activitiesRecHubService = activitiesRecHubService;
    }

    public List<ActivityVO> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityVO> activityList)
    {
        this.activityList = activityList;
    }

    public List<ActivityVO> getActivityAllList()
    {
        return activityAllList;
    }

    public void setActivityAllList(List<ActivityVO> activityAllList)
    {
        this.activityAllList = activityAllList;
    }

    public List<AwardVO> getAwardList()
    {
        return awardList;
    }

    public void setAwardList(List<AwardVO> awardList)
    {
        this.awardList = awardList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCurMenuId() {
		return curMenuId;
	}
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getDangciId()
    {
        return dangciId;
    }

    public void setDangciId(String dangciId)
    {
        this.dangciId = dangciId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getGroupId()
    {
        return groupId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getDangciName()
    {
        return dangciName;
    }

    public void setDangciName(String dangciName)
    {
        this.dangciName = dangciName;
    }

    public String getDangciDesc()
    {
        return dangciDesc;
    }

    public void setDangciDesc(String dangciDesc)
    {
        this.dangciDesc = dangciDesc;
    }

    public List getUsableTypes()
    {
        return usableTypes;
    }

    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getServnumber()
    {
        return servnumber;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getErrormessage()
    {
        return errormessage;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getActreward()
    {
        return actreward;
    }

    public void setActreward(String actreward)
    {
        this.actreward = actreward;
    }

    public String getImeiid()
    {
        return imeiid;
    }

    public void setImeiid(String imeiid)
    {
        this.imeiid = imeiid;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getRewardAccount()
    {
        return rewardAccount;
    }

    public void setRewardAccount(int rewardAccount)
    {
        this.rewardAccount = rewardAccount;
    }


    public String getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(String totalFee)
    {
        this.totalFee = totalFee;
    }

    public String getTotalfee_yuan()
    {
        return totalfee_yuan;
    }

    public void setTotalfee_yuan(String totalfee_yuan)
    {
        this.totalfee_yuan = totalfee_yuan;
    }

    public String getTerminalSeq()
    {
        return terminalSeq;
    }

    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }

    public String getRecoid()
    {
        return recoid;
    }

    public void setRecoid(String recoid)
    {
        this.recoid = recoid;
    }

    public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTermid()
    {
        return termid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTermid(String termid)
    {
        this.termid = termid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getTermName()
    {
        return termName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setTermName(String termName)
    {
        this.termName = termName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOperId()
    {
        return operId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOperId(String operId)
    {
        this.operId = operId;
    }

    public String getSuccessBz()
    {
        return successBz;
    }

    public void setSuccessBz(String successBz)
    {
        this.successBz = successBz;
    }

    public ActivitiesRecBean getActivitiesRecBean()
    {
        return activitiesRecBean;
    }

    public void setActivitiesRecBean(ActivitiesRecBean activitiesRecBean)
    {
        this.activitiesRecBean = activitiesRecBean;
    }

    public String getYxfaFee()
    {
        return yxfaFee;
    }

    public void setYxfaFee(String yxfaFee)
    {
        this.yxfaFee = yxfaFee;
    }

    public String getYcFee()
    {
        return ycFee;
    }

    public void setYcFee(String ycFee)
    {
        this.ycFee = ycFee;
    }

    public String getYxfaFee_yuan()
    {
        return yxfaFee_yuan;
    }

    public void setYxfaFee_yuan(String yxfaFee_yuan)
    {
        this.yxfaFee_yuan = yxfaFee_yuan;
    }

    public String getYcFee_yuan()
    {
        return ycFee_yuan;
    }

    public void setYcFee_yuan(String ycFee_yuan)
    {
        this.ycFee_yuan = ycFee_yuan;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getActivityName()
    {
        return activityName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public int getCountNum_danci()
    {
        return countNum_danci;
    }

    public void setCountNum_danci(int countNum_danci)
    {
        this.countNum_danci = countNum_danci;
    }

    public int getPageNum_danci()
    {
        return pageNum_danci;
    }

    public void setPageNum_danci(int pageNum_danci)
    {
        this.pageNum_danci = pageNum_danci;
    }

    public int getCountPageNum_danci()
    {
        return countPageNum_danci;
    }

    public void setCountPageNum_danci(int countPageNum_danci)
    {
        this.countPageNum_danci = countPageNum_danci;
    }

    public List<AwardVO> getAwardAllList()
    {
        return awardAllList;
    }

    public void setAwardAllList(List<AwardVO> awardAllList)
    {
        this.awardAllList = awardAllList;
    }

    public int getCountNum_present()
    {
        return countNum_present;
    }

    public void setCountNum_present(int countNum_present)
    {
        this.countNum_present = countNum_present;
    }

    public int getPageNum_present()
    {
        return pageNum_present;
    }

    public void setPageNum_present(int pageNum_present)
    {
        this.pageNum_present = pageNum_present;
    }

    public int getCountPageNum_present()
    {
        return countPageNum_present;
    }

    public void setCountPageNum_present(int countPageNum_present)
    {
        this.countPageNum_present = countPageNum_present;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPayType()
    {
        return payType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getNeedReturnCard()
    {
        return needReturnCard;
    }

    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }

    public String getCardnumber()
    {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }

    public String getChargeLogOID()
    {
        return chargeLogOID;
    }

    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }

    public String getUnionpayuser()
    {
        return unionpayuser;
    }

    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }

    public String getUnionpaycode()
    {
        return unionpaycode;
    }

    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBusiType()
    {
        return busiType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }

    public String getBatchnum()
    {
        return batchnum;
    }

    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
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

    public String getUnionpaytime()
    {
        return unionpaytime;
    }

    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }

    public String getVouchernum()
    {
        return vouchernum;
    }

    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }

    public String getUnionpayfee()
    {
        return unionpayfee;
    }

    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
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

    public String getErrPosResCode()
    {
        return errPosResCode;
    }

    public void setErrPosResCode(String errPosResCode)
    {
        this.errPosResCode = errPosResCode;
    }

    public String getErrorType()
    {
        return errorType;
    }

    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    public String getPrintcontext()
    {
        return printcontext;
    }

    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }

    public List<ActivityVO> getDangciAllList()
    {
        return dangciAllList;
    }

    public void setDangciAllList(List<ActivityVO> dangciAllList)
    {
        this.dangciAllList = dangciAllList;
    }

    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }

    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }
    
    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

    public String getOrgname()
    {
        return orgname;
    }

    public void setOrgname(String orgname)
    {
        this.orgname = orgname;
    }
    
}
