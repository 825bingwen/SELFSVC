package com.customize.hub.selfsvc.privAccept.service;

import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;


public interface PrivAcceptHubService {
    /**
     * ҵ����־
     * �Ż������¼��־
     * @param privLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void createPrivLog(PrivLogVO privLogVO);
}
