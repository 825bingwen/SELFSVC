/*
 * 文件名：ChargeAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-23
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.charge.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.service.ChargeService;
import com.gmcc.boss.selfsvc.common.BaseAction;

/**
 * 
 * 移动充值缴费
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-23
 * @see
 * @since
 */
public class ChargeAction extends BaseAction
{
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	protected String curMenuId = "";
    
	/**
	 * 缴费日志信息类
	 */
	protected CardChargeLogVO cardChargeLogVO;
	
    /**
     * 充值日志记录
     */
    private ChargeService chargeService;

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * 显示功能列表
     * 
     * @return
     * @throws Exception
     */
    public String initFunctionList() throws Exception
    {
        return "funclist";
    }

	public void setChargeService(ChargeService chargeService) 
	{
		this.chargeService = chargeService;
	}

	public CardChargeLogVO getCardChargeLogVO() {
		return cardChargeLogVO;
	}

	public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO) {
		this.cardChargeLogVO = cardChargeLogVO;
	}

    public ChargeService getChargeService()
    {
        return chargeService;
    }
    
    
}
