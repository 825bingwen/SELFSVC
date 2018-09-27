/*
 * 文 件 名:  CardBusiServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  办卡业务Service实现
 * 修 改 人:  zKF69263
 * 修改时间:  2014-10-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.service;

import java.util.List;

import com.customize.hub.selfsvc.cardbusi.dao.CardBusiDaoImpl;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.ProdTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 办卡业务Service实现
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CardBusiServiceImpl implements CardBusiService
{
    private CardBusiDaoImpl cardBusiDaoImpl;
    
    /**
     * 查询开户产品模板
     * 
     * @param prodTempletPO
     * @return List<ProdTempletPO>
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ProdTempletPO> qryProdTempletList(ProdTempletPO prodTempletPO)
    {
        return cardBusiDaoImpl.qryProdTempletList(prodTempletPO);
    }
    
    /** 
     * 查询终端支持空白卡写卡支持的卡类型
     * 
     * @param termId 终端机编号
     * @return DictItemPO
     * @see [类、类#方法、类#成员]
     */
    @Override
    public DictItemPO qryTermBlankCardType(String termId)
    {
        return cardBusiDaoImpl.qryTermBlankCardType(termId);
    }

    /**
     * 添加写卡记录写卡记录
     * <功能详细描述>
     * @param CardBusiLogPO
     * @return 失败0；
     * @see [类、类#方法、类#成员]
     */
    public String addLogInstall(CardBusiLogPO cardBusiLogPO)
    {
        return cardBusiDaoImpl.addLogInstall(cardBusiLogPO);
    }
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId()
    {
        return cardBusiDaoImpl.getInstallId();
    }
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(CardBusiLogPO cardBusiLogPO)
    {
        cardBusiDaoImpl.updateInstallLog(cardBusiLogPO);
    }
    
    /**
     * 更新缴费日志状态
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        cardBusiDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * @return 返回 cardBusiDaoImpl
     */
    public CardBusiDaoImpl getCardBusiDaoImpl()
    {
        return cardBusiDaoImpl;
    }

    /**
     * @param 对cardBusiDaoImpl进行赋值
     */
    public void setCardBusiDaoImpl(CardBusiDaoImpl cardBusiDaoImpl)
    {
        this.cardBusiDaoImpl = cardBusiDaoImpl;
    }

    /**
     * 查询用户本月补卡次数
     * @param cardBusiLogPO
     * @return
     */
	public int getReissueCardNum(CardBusiLogPO cardBusiLogPO) 
	{
		return cardBusiDaoImpl.getReissueCardNum(cardBusiLogPO);
	}
}
