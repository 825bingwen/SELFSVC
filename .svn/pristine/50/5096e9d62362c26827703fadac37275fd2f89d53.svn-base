package com.customize.cq.selfsvc.bean;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;

public class ProdChangeCQBean extends BaseBeanCQImpl
{
    
//    private static final Logger log = Logger.getLogger(ProdChangeCQBean.class);
//    
//    /**
//     * 根据用户当前主体产品查询可以转换的产品
//     */
//    public Map qryChangeList(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfoPO, String curMenuId)
//    {
//        Map paramMap = new HashMap();
//        // 设置操作员id
//        paramMap.put("curOper", terminalInfoPO.getOperid());
//        // 设置终端机id
//        paramMap.put("atsvNum", terminalInfoPO.getTermid());
//        // 设置客户接触id
//        paramMap.put("touchoid", customerSimp.getContactID());
//        // 设置当前菜单
//        paramMap.put("curMenuId", curMenuId);
//        // 设置客户手机号
//        paramMap.put("telnumber", customerSimp.getServNumber());
//        
//        // 修改主体产品变更流程
//        paramMap.put("rectype", "ChangeProduct");
//        
//        paramMap.put("region", terminalInfoPO.getRegion());
//        
//        paramMap.put("mainprodid", customerSimp.getProductID());
//        
//        ReturnWrap rw = this.getSelfSvcCallCQ().qryChangeList(paramMap);
//        
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CRSet v = (CRSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            Map map = new HashMap();
//            
//            // 设置返回结果
//            map.put("returnObj", v);
//            
//            // 设置返回信息
//            map.put("returnMsg", returnMsg);
//            
//            return map;
//        }
//        return null;
//    }
//    
//    /**
//     * 验用户选择的新主体产品是否具备转换条件
//     * 
//     * @param inMap
//     * @return
//     */
//    public Map prodChgCheck(Map inMap)
//    {
//        Map outMap = new HashMap();
//        
//        ReturnWrap rw = this.getSelfSvcCallCQ().prodChgCheck(inMap);
//        
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CTagSet cts = (CTagSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            
//            // 设置返回结果
//            outMap.put("retcode", cts.get("retcode"));
//            
//            // 设置返回信息
//            outMap.put("returnMsg", returnMsg);
//            
//            // 设置返回状态
//            outMap.put("status", "1");
//        }
//        else
//        {
//            outMap.put("status", "0");
//            if (rw != null)
//            {
//                outMap.put("returnMsg", rw.getReturnMsg());
//                log.error(rw.getReturnMsg());
//            }
//            else
//            {
//                outMap.put("returnMsg", "对不起系统发生异常,请稍后再试.");
//            }
//        }
//        
//        return outMap;
//    }
//    
//    /**
//     * 根据用户选择的新产品，查询出该产品的模板列表
//     * 
//     * @param inMap
//     * @return
//     */
//    public Map getProdTmpList(Map inMap)
//    {
//        ReturnWrap rw = this.getSelfSvcCallCQ().getProdTmpList(inMap);
//        
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CRSet v = (CRSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            Map map = new HashMap();
//            
//            // 设置返回结果
//            map.put("returnObj", v);
//            // 设置返回信息
//            map.put("returnMsg", returnMsg);
//            
//            return map;
//            
//        }
//        return null;
//    }
//    
//    /**
//     * 产品变更，获取优惠/服务/产品变更清单
//     */
//    public Map quryChgContent(Map<String, String> inMap)
//    {
//        
//        Map outMap = new HashMap();
//        ReturnWrap rw = this.getSelfSvcCallCQ().qryChgContent(inMap);
//        
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            String retInfo = (String)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            
//            try
//            {
//                Vector<TempletItemVO> templetItem = parseXml(retInfo);
//                
//                // 设置返回结果
//                outMap.put("returnObj", templetItem);
//            }
//            catch (DocumentException e)
//            {
//                outMap.put("status", "0");
//                outMap.put("returnMsg", "解析报文出现错误.");
//                log.error(e);
//                e.printStackTrace();
//            }
//            
//            // 设置返回信息
//            outMap.put("returnMsg", returnMsg);
//            
//            outMap.put("status", "1");
//            
//        }
//        else
//        {
//            String returnMsg = rw.getReturnMsg();
//            outMap.put("status", "0");
//            outMap.put("returnMsg", returnMsg);
//            log.error(returnMsg);
//        }
//        return outMap;
//    }
//    
//    /**
//     * 产品变更受理
//     */
//    public Map quryChgCommit(Map<String, String> inMap)
//    {
//        ReturnWrap rw = this.getSelfSvcCallCQ().prodChgCommit(inMap);
//        
//        Map outMap = new HashMap();
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CTagSet ctagset = (CTagSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            
//            outMap.put("status", "1");
//            // 设置返回信息
//            outMap.put("retcode", (String)ctagset.get("ctagset"));
//            
//            return outMap;
//            
//        }
//        else
//        {
//            String returnMsg = rw.getReturnMsg();
//            
//            outMap.put("status", "0");
//            outMap.put("returnMsg", returnMsg);
//            return outMap;
//        }
//    }
//    
//    private Vector<TempletItemVO> parseXml(String resultXml) throws DocumentException
//    {
//        Vector<TempletItemVO> list = new Vector<TempletItemVO>();
//        if (null != resultXml)
//        {
//            Document doc = DocumentHelper.parseText(resultXml);
//            Element root = doc.getRootElement();
//            Element eSvcCont = root.element("detailinfo").element("IntMsg").element("SvcCont");
//            
//            if (eSvcCont != null)
//            {
//                String rspSvc = eSvcCont.getText();
//                if (rspSvc != null)
//                {
//                    Document d = DocumentHelper.parseText(rspSvc);
//                    getItems(list, d.getRootElement().elementIterator(), null);
//                }
//            }
//        }
//        return list;
//    }
//    
//    private void getItems(Vector<TempletItemVO> list, Iterator it, TempletItemVO templetItem)
//    {
//        // 已添加必选明细的个数
//        int mustCount = 0;
//        // 父节点的最小选中数量
//        int minCount = 0;
//        if (null != templetItem && null != templetItem.getMinCount() && !"".equals(templetItem.getMinCount()))
//        {
//            minCount = new Integer(templetItem.getMinCount());
//        }
//        TempletItemVO item;
//        while (it.hasNext())
//        {
//            Element child = (Element)it.next();
//            if (null != child && child.elements().size() > 0)
//            {
//                item = null;
//                // SelectType 值为SeleType_Must是必选项直接添加
//                if (null != child.element("SelectType")
//                        && TempletItemVO.SELETYPE_MUST.equals(child.element("SelectType").getText()))
//                {
//                    item = createItem(child, templetItem);
//                    if (null != item)
//                    {
//                        list.add(item);
//                        mustCount++;
//                    }
//                }
//                getItems(list, child.elementIterator("Object"), item);
//            }
//        }
//        
//        // 当父节点的SelectType 值为SeleType_Must时，根据 父节点的最小选中数量添加非必选明细节点的个数
//        if (mustCount < minCount)
//        {
//            while (it.hasNext())
//            {
//                Element child = (Element)it.next();
//                if (null != child && child.elements().size() > 0)
//                {
//                    if ((null != templetItem && TempletItemVO.SELETYPE_MUST.equals(templetItem.getSelectType()))
//                            && (null != child.element("SelectType") && !TempletItemVO.SELETYPE_MUST.equals(child.element("SelectType")
//                                    .getText())) && (0 < minCount && mustCount < minCount))
//                    {
//                        item = createItem(child, templetItem);
//                        if (null != item)
//                        {
//                            list.add(item);
//                        }
//                        mustCount++;
//                    }
//                    // getItems(list, child.elementIterator("Object"), item);
//                }
//            }
//        }
//        
//        // 如果当前节点的EntityID中包含子节点的数量小于3个时，添加","完善格式
//        if (null != templetItem)
//        {
//            int len = templetItem.getEntityID().split(",").length;
//            switch (len)
//            {
//                case 1:
//                    templetItem.setEntityID(templetItem.getEntityID() + ",,");
//                    break;
//                case 2:
//                    templetItem.setEntityID(templetItem.getEntityID() + ",");
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//    
//    private TempletItemVO createItem(Element child, TempletItemVO templetItem)
//    {
//        TempletItemVO item = null;
//        if (0 < child.elements().size())
//        {
//            item = new TempletItemVO();
//            if (null != child.element("EntityID"))
//            {
//                if (null != templetItem)
//                {
//                    item.setEntityID(templetItem.getEntityID() + "," + child.element("EntityID").getText());
//                }
//                else
//                {
//                    item.setEntityID(child.element("EntityID").getText());
//                }
//            }
//            if (null != child.element("EntityName"))
//            {
//                item.setEntityName(child.element("EntityName").getText());
//            }
//            if (null != child.element("CataType"))
//            {
//                item.setCataType(child.element("CataType").getText());
//            }
//            if (null != child.element("Notes"))
//            {
//                item.setNotes(child.element("Notes").getText());
//            }
//            if (null != child.element("MinCount"))
//            {
//                item.setMinCount(child.element("MinCount").getText());
//            }
//            if (null != child.element("SelectType"))
//            {
//                item.setSelectType(child.element("SelectType").getText());
//            }
//            
//            if (null != child.element("OprType"))
//            {
//                item.setOprType(child.element("OprType").getText());
//            }
//            
//            if (null != child.element("HaveSP"))
//            {
//                item.setHaveSP(child.element("HaveSP").getText());
//            }
//        }
//        return item;
//    }
}
