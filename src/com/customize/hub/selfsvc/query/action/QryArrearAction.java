/**
 * 
 */
package com.customize.hub.selfsvc.query.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.customize.hub.selfsvc.bean.QueryBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * @author user
 * 
 */
public class QryArrearAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private QueryBean queryBean;

	private String curMenuId;

	// �������
	private String[] servicetitle;

	// ���ý��
	private List result;

	public String qryArrear() {
		HttpSession session = getRequest().getSession();

		TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session
				.getAttribute(Constants.TERMINAL_INFO);

		NserCustomerSimp customer = (NserCustomerSimp) session
				.getAttribute(Constants.USER_INFO);

		Map mapResult = queryBean
				.qryArrear(terminalInfoPO, customer, curMenuId);
		if (mapResult != null && mapResult.size() > 0) {
			CRSet cr = (CRSet) (mapResult.get("returnObj"));

			// ��������������
			String[] titles = { "����", "Ƿ�ѽ��(Ԫ)" };

			// ���ñ�������
			setServicetitle(titles);

			result = new ArrayList();
			// ƴװ����
			for (int i = 0; i < cr.GetRowCount(); i++) {
				List listOuter = new ArrayList();

				listOuter.add(cr.GetValue(i, 0));

				listOuter.add(cr.GetValue(i, 1));

				result.add(listOuter);

			}

			// �����ɹ���־
			this.createRecLog("QryArrearHub", "0", "0", "0",
							"ҵ����Ϣ��ѯ:Ƿ����ʷ��ѯ�ɹ�!");
		} else {
			this.getRequest().setAttribute("errormessage", "Ƿ����ʷ��ѯʧ��!");

			// ����������־
			this.createRecLog("QryArrearHub", "0", "0", "1", "Ƿ����ʷ��ѯʧ��!");
		}
		return SUCCESS;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public QueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(QueryBean queryBean) {
		this.queryBean = queryBean;
	}

	public String[] getServicetitle() {
		return servicetitle;
	}

	public void setServicetitle(String[] servicetitle) {
		this.servicetitle = servicetitle;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

}
