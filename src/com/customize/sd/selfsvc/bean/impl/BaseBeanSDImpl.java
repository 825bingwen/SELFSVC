package com.customize.sd.selfsvc.bean.impl;

import com.customize.sd.selfsvc.call.SelfSvcCallSD;

public class BaseBeanSDImpl{

	private SelfSvcCallSD selfSvcCallSD;

    public SelfSvcCallSD getSelfSvcCallSD()
    {
        return selfSvcCallSD;
    }

    public void setSelfSvcCallSD(SelfSvcCallSD selfSvcCallSD)
    {
        this.selfSvcCallSD = selfSvcCallSD;
    }    
}
