/**
 * @author lWX5316086
 */
package com.gmcc.boss.selfsvc.login.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**终端开关机信息实体类
 *
 * @author lWX5316086
 * @version 
 * @see 
 * @since 
 */
public class TerminalOnOffPO extends BasePO
{
	private static final long serialVersionUID = 1L;

	//编号
	private String id;
	
    //终端编号
    private String termId;
    
    //终端状态编号
    private String status;
    
    //状态描述
    private String detail;
    
    //开始时间
    private String startDate;
    
    //结束时间
    private String endDate;
    
    // add begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
    // 区域
    private int region;
    // add begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region

    public String getDetail()
    {
        return detail;
    }

	public String getEndDate()
    {
        return endDate;
    }

	public String getId() {
		return id;
	}

    public String getStartDate()
    {
        return startDate;
    }

    public String getStatus()
    {
        return status;
    }

    public String getTermId()
    {
        return termId;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public void setId(String id) {
		this.id = id;
	}

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTermId(String termId)
    {
        this.termId = termId;
    }

    public int getRegion()
    {
        return region;
    }

    public void setRegion(int region)
    {
        this.region = region;
    }
}
