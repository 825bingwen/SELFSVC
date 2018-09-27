package com.customize.hub.selfsvc.privAccept.dao;

import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class PrivAcceptDaoHubImpl extends BaseDaoImpl{
	
	public void createPrivLog(PrivLogVO privLogVO){
		
		sqlMapClientTemplate.insert("privAccept.insertPrivLog",privLogVO);
		
	}
}
