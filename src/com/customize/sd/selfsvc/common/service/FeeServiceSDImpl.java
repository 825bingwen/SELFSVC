/*
 * 文 件 名:  FeeChargeServiceSDImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <山东缴费日志记录service实现类>
 * 修 改 人:  jWX216858
 * 修改时间:  May 22, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.charge.dao.FeeChargeDaoImpl;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.dao.ChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * <山东缴费日志记录service实现类>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, May 22, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑]
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class FeeServiceSDImpl extends BaseServiceSDImpl implements IFeeServiceSD
{
    @Autowired
    private ChargeDaoImpl chargeDaoImpl;
    
    @Autowired
    private FeeChargeDaoImpl feeChargeDaoImpl;
    
    /**
	 * 增加缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @param chargeLogOID 缴费日志流水
	 * @param recType 交易类型
	 * @param servRegion 手机号码归属地
	 * @return
     * @see [类、类#方法、类#成员]
	 */
    @Override
	public CardChargeLogVO addChargeLog(CardChargeLogVO chargeLogVO, String chargeLogOID, String recType, String servRegion)
	{
		// 获取终端机信息
		TerminalInfoPO termInfo = this.getTermInfo();
		
		// 缴费日志流水
		chargeLogVO.setChargeLogOID(chargeLogOID);
		
		// 终端机region
		chargeLogVO.setRegion(termInfo.getRegion());
		
		// 终端机id
		chargeLogVO.setTermID(termInfo.getTermid());
		
		// 组织结构编码
		chargeLogVO.setOrgID(termInfo.getOrgid());
		
		// 操作员id
		chargeLogVO.setOperID(termInfo.getOperid());
		
		// 受理时间
		chargeLogVO.setRecdate(DateUtil._getCurrentTime());
		
		// 缴费类型
		String chargeType = "";
		
		// 现金缴费
		if (Constants.PAYBYMONEY.equals(chargeLogVO.getPayType()))
        {
			// 获取缴费类型
			chargeType = this.getChargeType("Cash");
        }
		else
		{
			// 获取缴费类型
			chargeType = this.getChargeType("Card");
		}
		
		// 银联缴费金额，实为用户选择的费用
		chargeLogVO.setFee(CommonUtil.yuanToFen(chargeLogVO.getYingjiaoFee()));
		
		// 合并缴费渠道和厂商对应的行号
		chargeLogVO.setBankno(chargeType + termInfo.getBankno());
		
		// 受理类型
		chargeLogVO.setRecType(recType);
		
		// 手机号码归属region
		chargeLogVO.setServRegion(servRegion);
		
		// 受理状态
		chargeLogVO.setStatus(Constants.CHARGE_ERROR);
		
		// 描述
		chargeLogVO.setDescription("交费之前记录投币日志");
		
		chargeDaoImpl.addChargeLog(chargeLogVO);
		
		return chargeLogVO;
	}
	
	/**
	 *  银联扣款成功更新缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void updateCardChargeLog(CardChargeLogVO chargeLogVO)
	{
		try
		{
			// 对缴费类型进行转码
			chargeLogVO.setBusiType(java.net.URLDecoder.decode(chargeLogVO.getBusiType(), "UTF-8"));
			
			// 描述
			chargeLogVO.setDescription(java.net.URLDecoder.decode(chargeLogVO.getDescription(), "UTF-8"));
			
			// 受理时间
			chargeLogVO.setRecdate(DateUtil._getCurrentTime());
			
			// 组织机构id
			chargeLogVO.setOrgID(getTermInfo().getOrgid());
			
			// region
			chargeLogVO.setRegion(getTermInfo().getRegion());
			
			feeChargeDaoImpl.updateCardChargeLog(chargeLogVO);
		}
		catch (Exception e)
		{
			// 记录错误日志
            this.insertRecLogTelnum(chargeLogVO.getServnumber(), "charge", "0", "0", Constants.RECSTATUS_FALID, "扣款成功后更新缴费日志失败");
			
			throw new ReceptionException("扣款成功后更新缴费日志失败。");
		}
	}
	
	/**
	 * 更新缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public void updateChargeLog(CardChargeLogVO chargeLogVO, String message, String unionRetCode, String status)
	{
		// 组织机构id
		chargeLogVO.setOrgID(getTermInfo().getOrgid());
		
		// region
		chargeLogVO.setRegion(getTermInfo().getRegion());
		
		// 受理时间
		chargeLogVO.setRecdate(DateUtil._getCurrentTime());
		
		// 描述信息
		chargeLogVO.setDescription(message);
		
		// 现金缴费终端流水(终端id+现金缴费流水 并取后20位)
		if (Constants.PAYBYMONEY.equals(chargeLogVO.getPayType()))
        {
			if (CommonUtil.isNotEmpty(chargeLogVO.getTerminalSeq()))
			{
				String terminalSeq = getTermInfo().getTermid() + chargeLogVO.getTerminalSeq();
				if (terminalSeq.length() > 20)
				{
					terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
				}
				chargeLogVO.setTerminalSeq(terminalSeq);
			}
        }
		
		// 银联返回错误码
		chargeLogVO.setPosResCode(unionRetCode);
		
		if (CommonUtil.isNotEmpty(chargeLogVO.gettMoney()) && !"0".equals(chargeLogVO.gettMoney().trim()))
		{
			// 缴费金额不为空，状态置为10：缴费成功，业务失败
			chargeLogVO.setStatus(CommonUtil.isEmpty(status) ? Constants.PAYSUCCESS_CHARGEERROR : status);
		}
		else
		{
			// 00 扣款或者投币失败或未交费
			chargeLogVO.setStatus(Constants.CHARGE_ERROR);
		}
		
		// 缴费金额
		chargeLogVO.setFee(CommonUtil.yuanToFen(chargeLogVO.gettMoney()));
		feeChargeDaoImpl.updateChargeLog(chargeLogVO);
	}
	
	/**
     * 取缴费类型
     * 
     * @param payType(Card或者Cash)
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getChargeType(String payType)
    {
        String chargeType = "";
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ChargeType);
        if (null != chargeTypeList)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        
        return chargeType;
    }
}
