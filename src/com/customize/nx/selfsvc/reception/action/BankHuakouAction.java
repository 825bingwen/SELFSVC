/*
* @filename: BankHuakouAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  �������л���ҵ��
* @author: g00140516
* @de:  2012/09/05 
* @description: 
* @remark: create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
*/
package com.customize.nx.selfsvc.reception.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.BankHuakouBean;


import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * �������л���ҵ��
 * 
 * @author  g00140516
 * @version  1.0, 2012/09/05
 * @see  
 * @since  
 */
public class BankHuakouAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    public static final Log logger = LogFactory.getLog(BankHuakouAction.class);
    
    /**
     * ��ǰ�˵�
     */
    private String curMenuId;
    
    /**
     * ��������
     */
    private String drawType = "";
    
    /**
     * ���۽��
     */
    private String drawAmt = "";
    
    /**
     * ��ͨʱ��
     */
    private String createTime = "";
    
    /**
     * �����˺�
     */
    private String pan = "";
    
    /**
     * ���֤��
     */
    private String IDCard = "";
    
    /**
     * ���������б�
     */
    public List<DictItemPO> drawTypes = null;
    
    /**
     * ���۽���б�
     */
    public List<DictItemPO> drawAmts = null;
    
    private BankHuakouBean huakouBean = null;
    
    /**
     * ��ѯ���л���֧����ʽ���ж��û��Ƿ��ѿ�ͨ
     * @return
     */
    public String qryChargeType()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        /**
         * ���л���֧����ʽ
         */
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        Map<String, Object> resultMap = huakouBean.qryChargeType(termInfo, customerSimp, curMenuId, paytype);
        
        // �ѿ�ͨ
        if ("1".equals((String) resultMap.get("flag")) && resultMap.get("resultObj") != null)
        {
            CTagSet tagSet = (CTagSet) resultMap.get("resultObj");
            
            // ����̨���صĻ������ͱ���תΪ��������
            drawType = tagSet.GetValue("drawtype");
            qryDrawType();           
            
            // ����̨���صĻ��۽�����תΪ�������
            drawAmt = tagSet.GetValue("drawamt");
            qryDrawAmt();
            
            createTime = tagSet.GetValue("createdate");
            
            // �̻�ID
            String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
            
            // ���������ӿڲ�ѯ�󶨹�ϵ
            Map<String, String> bankResultMap = huakouBean.qryBindInfo(merchantID, customerSimp.getSubsID());
            if (bankResultMap == null || !bankResultMap.containsKey("pan"))
            {
                String respDesc = "";
                if (bankResultMap != null)
                {
                    respDesc = (String) bankResultMap.get("respDesc");
                }
                
                if (respDesc == null || "".equals(respDesc.trim()))
                {
                    respDesc = "��ѯ�������л���ҵ�����״̬ʧ��";
                }
                
                logger.error(respDesc);
                
                request.setAttribute("errormessage", respDesc);
                
                this.createRecLog(curMenuId, "0", "0", "1", respDesc);
                
                return "error";
            }
            
            // �����˺�
            pan = bankResultMap.get("pan");
            
            return "delPage";
        }
        
        // δ��ͨ
        return "addPage";
    }

    /**
     * ����̨���صĻ��۽�����תΪ�������
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
     */
    private void qryDrawAmt()
    {
        drawAmts = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouMoney");
        if (drawAmts != null && drawAmts.size() > 0 || drawAmt != null && !"".equals(drawAmt.trim()))
        {
        	DictItemPO dictitemPO = null;
        	for (int i = 0; i < drawAmts.size(); i++)
        	{
        		dictitemPO = drawAmts.get(i);
        		if (drawAmt.equals(dictitemPO.getDictid()))
        		{
        			drawAmt = dictitemPO.getDictname();
        			break;
        		}
        	}
        }
    }

    /**
     * ����̨���صĻ������ͱ���תΪ��������
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
     */
    private void qryDrawType()
    {
        drawTypes = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouType");
        if (drawTypes != null && drawTypes.size() > 0 || drawType != null && !"".equals(drawType.trim()))
        {
        	DictItemPO dictitemPO = null;
        	for (int i = 0; i < drawTypes.size(); i++)
        	{
        		dictitemPO = drawTypes.get(i);
        		if (drawType.equals(dictitemPO.getDictid()))
        		{
        			drawType = dictitemPO.getDictname();
        			break;
        		}
        	}
        }
    }
    
    /**
     * ����󶨹�ϵ
     * 
     * @return
     * @see
     */
    public String cancelBindInfo()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // �̻�ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // ���������ӿڽ���󶨹�ϵ
        Map<String, Object> resultMap = huakouBean.cancelBindInfo(merchantID, pan, customerSimp.getSubsID());
        
        boolean delFlag = false;
        if (resultMap != null && resultMap.containsKey("delFlag"))
        {
            delFlag = (Boolean) resultMap.get("delFlag");
        }
        
        // ���ʧ��
        if (!delFlag)
        {
            String respDesc = "";
           
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "ȡ���������л���ҵ��ʧ��";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        // ���۷�ʽ
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        // ��CRM��ȡ�����л��۷�ʽ
        resultMap = huakouBean.cancelChargeType(termInfo, customerSimp, curMenuId, paytype);
        
        delFlag = false;
        if (resultMap != null && resultMap.containsKey("delFlag"))
        {
            delFlag = (Boolean) resultMap.get("delFlag");
        }
        
        // ȡ��ʧ��
        if (!delFlag)
        {
            logger.error("����󶨹�ϵʧ��");
            
            request.setAttribute("errormessage", "ȡ���������л���ҵ��ʧ��");
            
            this.createRecLog(curMenuId, "0", "0", "1", "ȡ���������л���ҵ��ʧ��");
            
            return "error";
        }
        
        this.createRecLog(curMenuId, "0", "0", "0", "ȡ���������л���ҵ��ɹ�");
        
        return "success";
    }

    /**
     * ���֤���������˺ŵ���֤
     * 
     * @return
     * @see
     */
    public String confirmBankInfo()
    {
        HttpServletRequest request = getRequest();

        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);        
    
        // �̻�ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // ��������������ʵ����֤�ӿڽ�����֤
        Map<String, Object> resultMap = huakouBean.confirmBankInfo(merchantID, pan, IDCard, "", customerSimp.getSubsID());
        
        boolean confirmFlag = false;
        if (resultMap != null && resultMap.containsKey("confirmFlag"))
        {
            confirmFlag = (Boolean) resultMap.get("confirmFlag");
        }
        
        if (!confirmFlag)
        {
            String respDesc = "";
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "�����˺���Ϣ��֤ʧ��";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        return "confirmPage";
    }
    
    /**
     * ת�ƻ������͡����۽��ѡ��ҳ��
     * 
     * @return
     * @see 
     */
    public String selectHuakouInfo()
    {
        // ���������б�
    	drawTypes = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouType");
    	
    	// ���۽���б�
    	drawAmts = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouMoney");
    	
    	return "huakouInfo";
    }
    
    /**
     * �������л��۷�ʽ
     * 
     * @return
     * @see 
     */
    public String addBindInfo()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // �̻�ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // ���������ӿڽ����󶨹�ϵ
        Map<String, Object> resultMap = huakouBean.addBindInfo(customerSimp, merchantID, pan, IDCard, "");
        
        boolean addFlag = false;
        if (resultMap != null && resultMap.containsKey("addFlag"))
        {
            addFlag = (Boolean) resultMap.get("addFlag");
        }
        
        // ��ʧ��
        if (!addFlag)
        {
            String respDesc = "";
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "��ͨ�������л���ҵ��ʧ��";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        // ���۷�ʽ
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        // �������
        String triggerAmt = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_TRIGGERMONEY);
        
        // ��CRM�п�ͨ���л��۷�ʽ
        resultMap = huakouBean.addChargeType(termInfo, customerSimp, curMenuId, paytype, pan, drawType, drawAmt, triggerAmt);
        
        addFlag = false;
        if (resultMap != null && resultMap.containsKey("addFlag"))
        {
            addFlag = (Boolean) resultMap.get("addFlag");
        }
        
        // ��ͨʧ��
        if (!addFlag)
        {
            logger.error("�����󶨹�ϵʧ��");
            
            request.setAttribute("errormessage", "��ͨ�������л���ҵ��ʧ��");
            
            this.createRecLog(curMenuId, "0", "0", "1", "��ͨ�������л���ҵ��ʧ��");
            
            return "error";
        }
        
        this.createRecLog(curMenuId, "0", "0", "0", "��ͨ�������л���ҵ��ɹ�");
        
        return "success";
    }
    
    public BankHuakouBean getHuakouBean()
    {
        return huakouBean;
    }

    public void setHuakouBean(BankHuakouBean huakouBean)
    {
        this.huakouBean = huakouBean;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getDrawType()
    {
        return drawType;
    }

    public void setDrawType(String drawType)
    {
        this.drawType = drawType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getDrawAmt()
    {
        return drawAmt;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setDrawAmt(String drawAmt)
    {
        this.drawAmt = drawAmt;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getPan()
    {
        return pan;
    }

    public void setPan(String pan)
    {
        this.pan = pan;
    }

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String card) {
		IDCard = card;
	}

	public List<DictItemPO> getDrawTypes() {
		return drawTypes;
	}

	public void setDrawTypes(List<DictItemPO> drawTypes) {
		this.drawTypes = drawTypes;
	}

	public List<DictItemPO> getDrawAmts() {
		return drawAmts;
	}

	public void setDrawAmts(List<DictItemPO> drawAmts) {
		this.drawAmts = drawAmts;
	}
}
