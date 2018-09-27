/*
 * 文 件 名:  PagedAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自助终端系统分页Action>
 * 修 改 人:  hWX5316476
 * 修改时间:  Dec 24, 2014
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.common;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  hWX5316476
 * @version  [版本号, Dec 24, 2014]
 * @see  [相关类/方法]
 * @since  [产品/C10LG1201]
 */
public class PagedAction extends BaseAction
{
    /**
     * 序列化
     */ 
    private static final long serialVersionUID = 1L;
    
    /** 
     * 页数
     */
    protected int pageCount = 0;
    
    /** 
     * 每页显示容量
     */
    protected int pageSize = 9;
    
    /** 
     * 当前页
     */
    protected int page = 1;
    
    /** 
     * 取得分页数据
     * 
     * @param <E>
     * @param resultList
     * @return List<E>
     * @see [类、类#方法、类#成员]
     */
    protected <E> List<E> getPageList(List<E> resultList, int perPageSize)
    {
        int resultSize = 0;
        int startSeq = 0;
        int endSeq = 0;
        pageSize = perPageSize;
        
        // 获取总页数
        if (!resultList.isEmpty())
        {
            resultSize = resultList.size();
            
            pageCount = resultSize / pageSize;
            
            if (resultSize % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        
        // 计算起始和结束位置
        startSeq = (page - 1) * pageSize;
        endSeq = (page >= pageCount) ? resultSize : page * pageSize;
        
        // 记录总条数
        this.getRequest().setAttribute("recordCount", resultList.size());
        
        // 取得分页数据
        return resultList.subList(startSeq, endSeq);
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
}
