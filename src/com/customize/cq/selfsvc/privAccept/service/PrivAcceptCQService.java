package com.customize.cq.selfsvc.privAccept.service;

import com.customize.cq.selfsvc.privAccept.model.PrivLogVO;


public interface PrivAcceptCQService {
    /**
     * 业务日志
     * 优惠受理记录日志
     * @param privLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createPrivLog(PrivLogVO privLogVO);
}
