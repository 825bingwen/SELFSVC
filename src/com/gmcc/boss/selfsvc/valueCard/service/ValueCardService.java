package com.gmcc.boss.selfsvc.valueCard.service;


import java.util.List;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.valueCard.model.ValueCardVO;

public interface ValueCardService 
{
	/**
     * <验证购买有价卡的号码是否省内用户>
     * <功能详细描述>
     * @param termInfo
     * @param servnumber
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-06 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	void qryUserStatus(String servnumber, String curMenuId);
	
	/**
     * <有价卡充值>
     * <功能详细描述>
     * @param servnumber 充值手机号码
     * @param curMenuId 菜单
     * @param valueCardPwd 有价卡卡号
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	public void valueCardCharge(String servnumber, String curMenuId, String valueCardPwd);
	
	/**
     * <获取有价卡面值信息>
     * <功能详细描述>
     * @param groupId
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-06 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	List<DictItemVO> getDictItemByGroup(String groupId, String curMenuId, String telnum);
	
	/**
     * <获取有价卡购买时的支付方式>
     * <功能详细描述>
     * @param telnum
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-08 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
	List<MenuInfoPO> getPayType(String curMenuId, String telnum);
	
	/**
	 * 有价卡购买
	 * @param terminalSeq
	 * @param valueCardVO
	 * @param curMenuId
	 * @return
	 */
	List<ValueCardVO> addValueCards(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO, String curMenuId);
	
	/**
     * 现金交费异常处理
     * @param selfCardPayLogVO
     * @param errormessage
     * @param tMoney
     * @see
     */
	void updateCashError(CardChargeLogVO selfCardPayLogVO, String errormessage, String tMoney);
	
	/**
	 * 银联卡交费异常处理
	 * @param telnum
	 * @param payType
	 * @param errormessage
	 * @param tMoney
	 * @param errorType
	 * @param chargeLogOID
	 * @param errPosResCode
	 * @see
	 */
	void addCardError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney, String errPosResCode, String errorType);
	
	/**
     * 扣款之前增加银联卡缴费日志
     * @param selfCardPayLogVO
     * @param recType
     * @param posNum
     * @param batchNumBeforeKoukuan
     * @see
     */
    String addCardChargeLog(CardChargeLogVO selfCardPayLogVO, String recType);
    
    /**
     * 扣款成功之后，更新交费日志
     * @param selfCardPayLogVO
     * 
     * @see
     */
    String updateCardChargeLog(CardChargeLogVO selfCardPayLogVO);
    
    /**
     * <无密码状态下获取用户信息>
     * <功能详细描述>
     * @param telnum 手机号码
     * @param curMenuId 菜单
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-06-13 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    void qryUserStatusSD(String telnum, String curMenuId);
    
    /**
     * <获取电子充值卡的面值信息>
     * <功能详细描述>
     * @param curMenuId
     * @param telnum
     * @return
     * @remark create by wWX217192 2015-06-13 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    List<DictItemVO> getDictItemSD(String curMenuId, String telnum);
    
    /**
     * 山东购买有价卡信息
     * @param selfCardPayLogVO
     * @param valueCardVO
     * @param menuid
     * @param unionRetCode
     * @return
     * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
     */
    List<ValueCardVO> addValueCards_sd(CardChargeLogVO selfCardPayLogVO, ValueCardVO valueCardVO, String menuid, String unionRetCode);
}
