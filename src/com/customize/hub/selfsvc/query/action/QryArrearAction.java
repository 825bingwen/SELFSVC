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

	// 结果标题
	private String[] servicetitle;

	// 设置结果
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

			// 定义结果标题名称
			String[] titles = { "帐期", "欠费金额(元)" };

			// 设置标题名称
			setServicetitle(titles);

			result = new ArrayList();
			// 拼装数据
			for (int i = 0; i < cr.GetRowCount(); i++) {
				List listOuter = new ArrayList();

				listOuter.add(cr.GetValue(i, 0));

				listOuter.add(cr.GetValue(i, 1));

				result.add(listOuter);

			}

			// 创建成功日志
			this.createRecLog("QryArrearHub", "0", "0", "0",
							"业务信息查询:欠费历史查询成功!");
		} else {
			this.getRequest().setAttribute("errormessage", "欠费历史查询失败!");

			// 创建错误日志
			this.createRecLog("QryArrearHub", "0", "0", "1", "欠费历史查询失败!");
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
