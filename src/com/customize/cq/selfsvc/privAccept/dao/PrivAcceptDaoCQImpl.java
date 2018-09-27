package com.customize.cq.selfsvc.privAccept.dao;

import com.customize.cq.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class PrivAcceptDaoCQImpl extends BaseDaoImpl{
	
	public void createPrivLog(PrivLogVO privLogVO){
		
		sqlMapClientTemplate.insert("privAccept.insertPrivLog",privLogVO);
		
	}
}
