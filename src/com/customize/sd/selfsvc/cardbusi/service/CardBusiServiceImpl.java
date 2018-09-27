/*
 * 文 件 名:  CardBusiServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  办卡业务Service实现
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.cardbusi.dao.CardBusiDaoImpl;
import com.customize.sd.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

/**
 * 办卡业务Service实现
 * 
 * @author zKF69263
 * @version [版本号, 2014-12-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
 */
public class CardBusiServiceImpl extends BaseServiceSDImpl implements CardBusiService 
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
     * 添加写卡记录写卡记录 <功能详细描述>
     * 
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
     * 
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        cardBusiDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * 校验用户补卡次数
     * 
     * @param customerSimp
     */
    public void checkReissueNum(NserCustomerSimp customerSimp)
    {
        // 获取补卡次数
        String reissueCardNum = CommonUtil.getParamValue("sh_reissueCard_num");
        
        // 默认每月补卡次数为3次
        if (StringUtils.isBlank(reissueCardNum))
        {
            reissueCardNum = "3";
        }
        
        CardBusiLogPO cardBusiLog = new CardBusiLogPO();
        
        // 当前月的第一天
        cardBusiLog.setCreateDate(DateUtil.getFirstDay("yyyyMMdd"));
        
        // 当前月的最后一天
        cardBusiLog.setStatusDate(DateUtil.getLastDay("yyyyMMdd"));
        
        // 用户地区
        cardBusiLog.setRegion(customerSimp.getRegionID());
        
        // 手机号码
        cardBusiLog.setServnumber(customerSimp.getServNumber());
        
        // 本月已经补卡的次数
        int usedNum = getCardBusiDaoImpl().getReissueCardNum(cardBusiLog);
        
        // 用户本月补卡次数用完
        if (usedNum >= Integer.parseInt(reissueCardNum))
        {
            throw new ReceptionException("用户已超出本月补卡次数限制，暂时无法补卡");
        }
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
     * <查询用户已预约的号码>
     * <功能详细描述>
     * @param idCardNo
     * @param curMenuId
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 17:42:51 OR_SD_201505_489_开户中增加预约选号功能
     */
    public List<ServerNumPO> qryBookedTelnum(String idCardNo, String curMenuId)
    {
        List<ServerNumPO> bookedTelnumList = new ArrayList<ServerNumPO>();
        
        //终端机信息
        TerminalInfoPO termInfo = getTermInfo();
        
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        Document doc = DocumentHelper.createDocument();
        
        Element eBody = doc.addElement("message_content");
        
        //证件号码
        DocumentUtil.addSubElementToEle(eBody, "CERTID", idCardNo);

        // 调用接口
        ReturnWrap rw = getSelfSvcCallSD().qryBookedTelnum(msgHeader, doc);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLog(Constants.SH_CARD_INSTALL, "", "", Constants.RECSTATUS_FALID, rw.getReturnMsg());
            throw new ReceptionException("查询预约号码失败，请返回采用其他方式选择号码");
        }
        
        //遍历解析已预约的号码
        if(rw.getReturnObject() instanceof CRSet)
        {
            CRSet crset = (CRSet)rw.getReturnObject();
            
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                Date endDate = DateUtil.toDate(crset.GetValue(i, 1), "yyyy-MM-dd HH:mm:ss");
                
                //过滤过期的号码
                if(null == endDate || new Date().compareTo(endDate) == 1)
                {
                    continue;
                }
                
                ServerNumPO bookedtelnumPO = new ServerNumPO();
                
                //手机号码
                bookedtelnumPO.setTelnum(crset.GetValue(i, 0));
                
                //过期时间
                bookedtelnumPO.setDeadline(crset.GetValue(i, 1));
                
                //证件类型
                bookedtelnumPO.setIdType(crset.GetValue(i, 2));
                
                //证件号码
                bookedtelnumPO.setIdCardNo(crset.GetValue(i, 3));
                
                bookedTelnumList.add(bookedtelnumPO);
            }
        }
        
        //如果用户之前未预约过号码，则提示
        if(0 == bookedTelnumList.size())
        {
            throw new ReceptionException("您之前未预约过号码，请返回采用其他方式选择号码");
        }
        
        return bookedTelnumList;
    }

}
