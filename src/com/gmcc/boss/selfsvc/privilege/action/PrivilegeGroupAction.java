/*
* @filename: RectelInfoAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  ͬ��ҵ�����������и�������
* @author: g00140516
* @de:  2012/07/10 
* @description: 
* @remark: create g00140516 2012/07/10 R003C12L07n01 OR_NX_201205_649
*/
package com.gmcc.boss.selfsvc.privilege.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * ͬ��ҵ�����������и�������
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/10
 * @see  
 * @since  
 */
public class PrivilegeGroupAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static final Log logger = LogFactory.getLog(PrivilegeGroupAction.class);
    
    /**
     * �˵�ID
     */
    private String curMenuId = "";
    
    /**
     * �ײͶ�Ӧ���ֵ���е�groupid
     */  
    private String groupid = "";
    
    /**
     * ����NCode
     */
    private String currNCode = "";
    
    /**
     * ����NCode
     */
    private String nextNCode = "";
    
    /**
     * �����ײ�����
     */
    private String currDesp = "δ��ͨ";
    
    /**
     * �����ײ�����
     */
    private String nextDesp = "δ��ͨ";
    
    /**
     * �û�ѡ���ncode
     */
    private String newNCode = "";
    
    /**
     * �û�ѡ����ײ�����
     */
    private String newPrivName = "";
    
    /**
     * �û�ѡ����ײ�����
     */
    private String newPrivDesp = "";
    
    /**
     * ��Ч��ʽ
     */
    private String effectType = "";
    
    /**
     * �ɹ���ʾ��Ϣ
     */
    private String successMessage = "";
    
    /**
     * �ײ��б�
     */
    private List<DictItemPO> privilegeList = null;
    
    /**
     * ��Ч��ʽ
     */
    private List<DictItemPO> effectTypeList = null;
    
    private ReceptionBean receptionBean = null;
    
    /**
     * չʾͬ��ҵ���б�
     * 
     * @return
     * @see 
     */
    public String qryPrivilegeGroup()
    {
        String forward = "error";
        
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // ����groupid��ѯ�ײ��б�
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "û�в�ѯ����ص��ײ���Ϣ");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "û�в�ѯ����ص��ײ���Ϣ");
            
            logger.error("û�в�ѯ����ص��ײ���Ϣ");
            
            return forward;
        }
        
        // ��ѯ�û���ǰ�������
        DictItemPO dictItemPO = privilegeList.get(0);
        String nCode = dictItemPO.getDictid();
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, nCode, "QRY", "", "", curMenuId);
        
        // ����
        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        if (checkResult(result))
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        {
            CTagSet tagSet = (CTagSet) result.getReturnObject();
            
            if (null != tagSet)
            {
                // ����ncode
                currNCode = tagSet.GetValue("curncode");
                
                // ����ncode
                nextNCode = tagSet.GetValue("nextncode");               
            }
            
            if ((null != currNCode && !"".equals(currNCode.trim())) 
                    || (null != nextNCode && !"".equals(nextNCode.trim())))
            {
                for (int i = 0; i < privilegeList.size(); i++)
                {
                    dictItemPO = privilegeList.get(i);
                    
                    if (dictItemPO.getDictid().equalsIgnoreCase(currNCode))
                    {
                        // ����ҵ������
                        currDesp = dictItemPO.getDictname();
                    }
                    
                    if (dictItemPO.getDictid().equalsIgnoreCase(nextNCode))
                    {
                        // ����ҵ������
                        nextDesp = dictItemPO.getDictname();
                    }
                }
            }
            
            forward = "qrySuccess";
        }
        else
        {
            request.setAttribute("errormessage", "��ѯ�ײ͵�ǰ�������ʧ��");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "��ѯ�ײ͵�ǰ�������ʧ��");
            
            logger.error("��ѯ�ײ͵�ǰ�������ʧ��");
        }
        
        return forward;
    }
    
    /**
     * У�鷵��ֵ
     * <������ϸ����>
     * @param result
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public boolean checkResult(ReturnWrap result)
    {
        return (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        || (result != null && result.getErrcode() >= 104 && result.getErrcode() <= 107);
    }
    
    /**
     * ����ȷ�ϣ�ͬʱѡ����Ч��ʽ
     * 
     * @return
     * @see 
     */
    public String confirmPrivilege()
    {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        // ��������ʱ��������һҳ
        request.setAttribute("backStep", "-1");
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // ����groupid��ѯͬ��ҵ���б�
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "û�в�ѯ����ص��ײ���Ϣ");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "û�в�ѯ����ص��ײ���Ϣ");
            
            logger.error("û�в�ѯ����ص��ײ���Ϣ");
            
            return "error";
        }
        
        // �����û�ѡ��ncode��ȡҵ�����ơ�ҵ������
        DictItemPO dictItemPO = null;
        for (int i = 0; i < privilegeList.size(); i++)
        {
            dictItemPO = privilegeList.get(i);
            
            if (dictItemPO.getDictid().equalsIgnoreCase(newNCode))
            {
                newPrivName = dictItemPO.getDictname();
                newPrivDesp = dictItemPO.getDescription();
                
                newPrivDesp = newPrivDesp.replaceAll("<br/>", "");

                break;
            }            
        }
        
        // ҵ���Ӧ����Ч��ʽ����ͨ,ȡ��,���
        String menuEffectType = "";
        
        List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        if (titleTotalMenus != null && titleTotalMenus.size() > 0)
        {
            MenuInfoPO menu = null;
            
            for (int i = 0; i < titleTotalMenus.size(); i++)
            {
                menu = (MenuInfoPO) titleTotalMenus.get(i);                
                
                if (curMenuId.equalsIgnoreCase(menu.getMenuid()))
                {
                    menuEffectType = menu.getEffectType();
                    break;
                }
            }
        }
        
        if (null != menuEffectType && !"".equals(menuEffectType.trim()))
        {
            String[] effectTypeArray = menuEffectType.split(",");
            
            // ���ʱ����Ч��ʽ
            if (null != currNCode && !"".equals(currNCode.trim()) && effectTypeArray.length >= 3)
            {
                menuEffectType = effectTypeArray[2];
            }
            // ��ͨʱ����Ч��ʽ
            else if (null == currNCode || "".equals(currNCode.trim()))
            {
                menuEffectType = effectTypeArray[0];
            }           
        }
        
        // ��Ч��ʽ��Ϊ��(������Ч��ʽ��|�ָ�)���ȴ��ֵ���л�ȡ��Ч��ʽ�б��ٴ��и�����Ч��ʽ���й���
        if (null != menuEffectType && !"".equals(menuEffectType.trim()))
        {
            String[] effectTypeArray = menuEffectType.split("\\|");

            effectTypeList = this.getDictItemByGrp(Constants.EFFECT_TYPE);
            
            if (null != effectTypeList && effectTypeList.size() > 0)
            {                
                for (int i = effectTypeList.size() - 1; i >= 0; i--)
                {
                    dictItemPO = effectTypeList.get(i);
                    
                    boolean flag = false;
                    
                    for (int j = 0; j < effectTypeArray.length; j++)
                    {
                        if (dictItemPO.getDictid().equals(effectTypeArray[j]))
                        {
                            flag = true;
                            
                            break;
                        }
                    }
                    
                    if (!flag)
                    {
                        effectTypeList.remove(i);
                    }
                }
            }
        }
        
        return "confirmPage";
    }
    
    /**
     * ͬ��ҵ�����
     * 
     * @return
     * @see 
     */
    public String commitPrivilege()
    {
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        String sOperType = "ADD";
        
        // ��ȡҵ������
        String menuName = CommonUtil.getMenuName(curMenuId);
        
        // ����groupid��ѯͬ��ҵ���б�
        privilegeList = getDictItemByGrp(groupid);
        
        if (null == privilegeList || privilegeList.size() == 0)
        {
            request.setAttribute("errormessage", "û�в�ѯ����ص��ײ���Ϣ");
            
            this.createRecLog(customerSimp.getServNumber(), curMenuId, "", "", "1", "û�в�ѯ����ص��ײ���Ϣ");
            
            logger.error("û�в�ѯ����ص��ײ���Ϣ");
            
            return "error";
        }
        
        // �����û�ѡ��ncode��ȡҵ�����ơ�ҵ������
        DictItemPO dictItemPO = null;
        for (int i = 0; i < privilegeList.size(); i++)
        {
            dictItemPO = privilegeList.get(i);
            
            if (dictItemPO.getDictid().equalsIgnoreCase(newNCode))
            {
                newPrivName = dictItemPO.getDictname();
                
                break;
            }            
        }        
        
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, newNCode, sOperType, effectType, "", curMenuId);
        if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        {
            forward = "success";
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.getReturnObject();
            // ҵ��������ˮ��
            String recFormnum = "0";
            
            if(null != tagSet && null != tagSet.get("formnum"))
            {
                recFormnum = (String)tagSet.get("formnum");
            }
            
            this.createRecLog(curMenuId, recFormnum, "0", "0", "ҵ������ɹ�");
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            
            successMessage = menuName + "��" + newPrivName + "����ɹ�";
        }
        else
        {
            String resultMsg = menuName + "��" + newPrivName + "����ʧ��";
            
            // modify begin yKF28472 ����ȡCRM���صĴ�����Ϣ
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                if (result != null && result.getReturnMsg() != null && !"".equals(result.getReturnMsg()))
                {
                    resultMsg = result.getReturnMsg();
                }
            }
            // modify end yKF28472 ����ȡCRM���صĴ�����Ϣ
            
            this.createRecLog(curMenuId, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            logger.error("ҵ��(" + newNCode + ")����ʧ��");
        }
        
        return forward;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getGroupid()
    {
        return groupid;
    }

    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCurrNCode()
    {
        return currNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCurrNCode(String currNCode)
    {
        this.currNCode = currNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getNextNCode()
    {
        return nextNCode;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setNextNCode(String nextNCode)
    {
        this.nextNCode = nextNCode;
    }

    public String getCurrDesp()
    {
        return currDesp;
    }

    public void setCurrDesp(String currDesp)
    {
        this.currDesp = currDesp;
    }

    public String getNextDesp()
    {
        return nextDesp;
    }

    public void setNextDesp(String nextDesp)
    {
        this.nextDesp = nextDesp;
    }

    public List<DictItemPO> getPrivilegeList()
    {
        return privilegeList;
    }

    public void setPrivilegeList(List<DictItemPO> privilegeList)
    {
        this.privilegeList = privilegeList;
    }

    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }

    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }

    public String getNewNCode()
    {
        return newNCode;
    }

    public void setNewNCode(String newNCode)
    {
        this.newNCode = newNCode;
    }

    public String getNewPrivName()
    {
        return newPrivName;
    }

    public void setNewPrivName(String newPrivName)
    {
        this.newPrivName = newPrivName;
    }

    public String getNewPrivDesp()
    {
        return newPrivDesp;
    }

    public void setNewPrivDesp(String newPrivDesp)
    {
        this.newPrivDesp = newPrivDesp;
    }

    public List<DictItemPO> getEffectTypeList()
    {
        return effectTypeList;
    }

    public void setEffectTypeList(List<DictItemPO> effectTypeList)
    {
        this.effectTypeList = effectTypeList;
    }

    public String getEffectType()
    {
        return effectType;
    }

    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
