package com.customize.cq.selfsvc.prodOrder.action;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.ProdOrderBean;
import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.customize.cq.selfsvc.prodOrder.service.ProdOrderService;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


/**
 * ��Ʒ��ѯ����
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderAction extends BaseAction
{
//    
//    private static Log logger = LogFactory.getLog(ProdOrderAction.class);
//    
//    // ���л�
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus����
//    // ��ǰ�˵�
//    private String curMenuId;
//    // end zKF66389 findbus����
//    
//    // ҳ��
//    private String pageCount;
//    
//    // begin zKF66389 findbus����
//    // ������Ϣ
//    private String errorMessage;
//    // end zKF66389 findbus����
//    
//    //�ɹ���Ϣ
//    private String successMessage;
//    
//    // �û��Ѷ�����Ʒ�����
//    private Vector orderedProdList;
//    
//    // �û��ɶ�������ֵ��Ʒ����
//    private List<ProdOrderPO> availProdList;
//    
//    // �ӿڵ���
//    private ProdOrderBean prodOrderBean;
//    
//    // �ӿڵ���
//    private ReceptionBean receptionBean;
//    
//    // ���ݿ����service
//    ProdOrderService prodOrderService;
//    
//    /**
//     * ѡ���ѿ�ͨ��ɿ�ͨҵ��
//     * @return
//     */
//    public String showType()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("showType Starting ...");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("showType End");
//        }
//        
//        return "success";
//    }
//    
//    //��ѯ����
//    private String type;
//    
//	/**
//     * ��Ʒ��ѯ
//     * 
//     * @return
//     */
//    public String queryProdList()
//    {
//        logger.debug("queryProdList start!");
//        
//        String forward = null;
//        
//        // ��ȡsession
//        HttpSession session = getRequest().getSession();
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        if(pageCount != null)
//        {
//            this.getRequest().setAttribute("pagecount", pageCount.split(",")[0]);
//        }
//        
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        Map result = prodOrderBean.queryService(terminalInfoPO, customer, curMenuId, "0");
//        if (result != null && result.size() > 1)
//        {
//            CRSet crset = (CRSet)result.get("returnObj");
//            
//            if (crset != null && crset.GetRowCount() > 0)
//            {
//            	forward = "prodOrdered";
//                if("1".equals(type))forward = "prodOrdering";
//                // ���õ��Ľ�����ݷŵ�ҳ����ʾ��ǰ��
//                Vector v = new Vector();
//                v.add(new CEntityString("��Ʒ����,����,��ͨʱ��,����"));
//                v.add(crset);
//                
//                // ���ý����
//                setOrderedProdList(v);
//            }
//            else
//            {
//                // ����������־
//                this.createRecLog(Constants.BUSITYPE_PRODORDER, "0", "0", "0", "��ѯ�ɹ��������û�û�ж����κ���ֵ��Ʒ��");
//            }
//            
//        	// �������ն����ݿ��в�ѯ�û��ɶ�������ֵ��Ʒ
//        	availProdList = prodOrderService.qryProdOrderList(customer);
//        	
//        	// ���˵��û��Ѿ���������ֵ��Ʒ,��������Ϊnid��ncode��ͬ��
//        	CRSet orderedProdData = (CRSet)orderedProdList.get(1);
//        	if(availProdList != null && orderedProdData != null && availProdList.size() >0 && orderedProdData.GetRowCount() >0)
//        	{
//        		//�Ѷ�����Ʒ����
//        		String orderedGroup = ";";
//        		StringBuffer sbuf = new StringBuffer(orderedGroup);
//        		for(int i=0;i<orderedProdData.GetRowCount();i++)
//        		{
//        			String nid = orderedProdData.GetValue(i, 3);
//        			for(int j=0;j<availProdList.size();j++)
//        			{
//        				ProdOrderPO prodOrderPO = availProdList.get(j);
//        				String ncode = prodOrderPO.getNcode();
//        				String groupId = ";";
//        				if(prodOrderPO.getGroupcolor()!=null)groupId = prodOrderPO.getGroupcolor()+";";
//        				if(!";".equals(groupId)&&orderedGroup.indexOf(";"+groupId)!=-1){
//        					availProdList.remove(j);
//        				}else{
//        					if(nid.equals(ncode))
//            				{
//        						if(!";".equals(groupId))
//        						sbuf.append(groupId);
//            					availProdList.remove(j);
//            				}
//        				}
//        			}
//        		}
//        		orderedGroup = sbuf.toString();
//        	}
//        }
//        else
//        {
//            // ���ô�����Ϣ
//            setErrorMessage((String) result.get("returnMsg"));
//            forward = "error";
//            
//            // ����������־
//            this.createRecLog(Constants.BUSITYPE_PRODORDER, "0", "0", "0", "��ѯ�Ѷ�����Ʒʧ�ܣ����Ժ����ԡ��������㣬����ԭ�¡�");
//        }
//        
//        logger.debug("queryProdList end!");
//        return forward;
//        
//    }
//    
//    //ҵ�����
//    private String ncode;
//    //��Ч��ʽ
//    private String effType;
//    //������ʽ
//    private String operType;
//    
//    /**
//     * �����˶���ֵ��Ʒ
//     * 
//     * @return
//     */
//    public String orderProd()
//    {
//    	logger.debug("orderProd start"); 
//        
//        String forward = "error";
//        
//        // ��ȡsession
//        HttpSession session = getRequest().getSession();
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        //�жϵ�ǰ�˵��Ƿ�Ϊ��
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        if (effType == null)
//        {
//        	effType = "";
//        }
//        
//        ReturnWrap result = receptionBean.recCommonServ(customer, terminalInfoPO, ncode, operType, effType, "", curMenuId);
//        
//        if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
//        {
//            forward = "success";
//            
//            this.createRecLog(curMenuId, "0", "0", "0", "ҵ������ɹ�");
//            
//            this.getRequest().setAttribute("errormessage", "ҵ������ɹ�");
//            
//            if (logger.isInfoEnabled())
//            {
//                logger.info("ҵ��(" + ncode + ")����ɹ�");
//            }
//        }
//        else
//        {
//            this.createRecLog(curMenuId, "0", "0", "1", "ҵ������ʧ��");
//            
//            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
//            
//            logger.error("ҵ��(" + ncode + ")����ʧ��");
//        }
//        
//        logger.debug("orderProd end!");
//        return forward;
//        
//    }
//    
//    // begin zKF66389 findbus����
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus����
//
//	public String getPageCount()
//    {
//        return pageCount;
//    }
//    
//    public void setPageCount(String pageCount)
//    {
//        this.pageCount = pageCount;
//    }
//    
//    public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//	public Vector getOrderedProdList()
//    {
//        return orderedProdList;
//    }
//    
//    public void setOrderedProdList(Vector orderedProdList)
//    {
//        this.orderedProdList = orderedProdList;
//    }
//    
//    public ProdOrderBean getProdOrderBean()
//    {
//        return prodOrderBean;
//    }
//    
//    public void setProdOrderBean(ProdOrderBean prodOrderBean)
//    {
//        this.prodOrderBean = prodOrderBean;
//    }
//    
//    public ReceptionBean getReceptionBean()
//    {
//        return receptionBean;
//    }
//    
//    public void setReceptionBean(ReceptionBean receptionBean)
//    {
//        this.receptionBean = receptionBean;
//    }
//
//    public String getNcode()
//    {
//        return ncode;
//    }
//
//    public void setNcode(String ncode)
//    {
//        this.ncode = ncode;
//    }
//
//    public String getEffType()
//    {
//        return effType;
//    }
//
//    public void setEffType(String effType)
//    {
//        this.effType = effType;
//    }
//    
//    public String getOperType()
//    {
//        return operType;
//    }
//
//    public void setOperType(String operType)
//    {
//        this.operType = operType;
//    }
//
//    public String getSuccessMessage()
//    {
//        return successMessage;
//    }
//
//    public void setSuccessMessage(String successMessage)
//    {
//        this.successMessage = successMessage;
//    }
//
//    public ProdOrderService getProdOrderService() 
//    {
//		return prodOrderService;
//	}
//
//	public void setProdOrderService(ProdOrderService prodOrderService) 
//	{
//		this.prodOrderService = prodOrderService;
//	}
//
//	public List getAvailProdList() 
//	{
//		return availProdList;
//	}
//
//	public void setAvailProdList(List availProdList) 
//	{
//		this.availProdList = availProdList;
//	}
//	
//	public String getType()
//    {
//        return type;
//    }
//
//    public void setType(String type)
//    {
//        this.type = type;
//    }
}
