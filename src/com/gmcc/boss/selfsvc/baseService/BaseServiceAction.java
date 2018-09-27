package com.gmcc.boss.selfsvc.baseService;

import com.gmcc.boss.selfsvc.common.BaseAction;

public class BaseServiceAction extends BaseAction
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 显示功能列表
     * @return
     */
    public String initFunctionList()
    {
        return "funclist";
    }
}
