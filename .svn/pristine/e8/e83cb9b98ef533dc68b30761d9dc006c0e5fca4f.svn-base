package com.gmcc.boss.selfsvc.media.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.media.model.SelfMediaQO;
import com.gmcc.boss.selfsvc.media.model.SelfMediaVO;

/**
 * 多媒体播放操作类
 * <功能详细描述>
 * @author  yKF28472
 * @version  [版本号, 2010-10-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MediaDaoImpl extends BaseDaoImpl
{

    /** 根据终端机编码取广告播放列表
     * <功能详细描述>
     * @param termid 终端机编号
     * @see [类、类#方法、类#成员]
     * @return 广告播放列表
     */
    public String getAdvList(String termId)
    {
        return (String)sqlMapClientTemplate.queryForObject("media.getAdvList", termId);
    }
    
    /** 根据终端机编码取屏保播放列表
     * <功能详细描述>
     * @param termid 终端机编号
     * @see [类、类#方法、类#成员]
     * @return 屏保播放列表
     */
    public String getScList(String termId)
    {
    	return (String)sqlMapClientTemplate.queryForObject("media.getScList", termId);
    }
    
    /** 取终端机播放列表对象
     * <功能详细描述>
     * @param resids 资源编号
     * @param restype 资源类型
     * @param regions 区域
     * @see [类、类#方法、类#成员]
     * @return 播放列表对象
     */
    public Map getMediaResList(String resids,String restype, String regions)
    {

        String residsTmp[] = resids.split(",");
        
        // TODO 此处regions可能为空？？？？
        String regionsTmp[] = regions.split(",");
        resids = "";
        regions = "";
        StringBuffer residsBuffer = new StringBuffer("");
        StringBuffer regionsBuffer = new StringBuffer("");
        
        // 处理资源ID
        // TODO 拼接查询所需要的资源ID条件
        for(int i=0;i<residsTmp.length;i++)
        {
            if(i == residsTmp.length-1){
                residsBuffer.append("'").append(residsTmp[i]).append("'");
            }else{
                residsBuffer.append("'").append(residsTmp[i]).append("'").append(",");
            }
        }
        
        // 处理区域ID
        for(int i=0;i<regionsTmp.length;i++)
        {
            if(i == regionsTmp.length-1){
                regionsBuffer.append("'").append(regionsTmp[i]).append("'");
            }else{
                regionsBuffer.append("'").append(regionsTmp[i]).append("'").append(",");
            }
        }
        
        // 拼装查询对象
        SelfMediaQO selfMediaQO = new SelfMediaQO();
        selfMediaQO.setRegions(regionsBuffer.toString());
        selfMediaQO.setResids(residsBuffer.toString());
        selfMediaQO.setRestype(restype); // sc:屏保 adv:广告
        
        //  执行查询
        List list = sqlMapClientTemplate.queryForList("media.getMediaResList", selfMediaQO);
        Map map = new HashMap();
        for(int i=0;i<list.size();i++){
            map.put(((SelfMediaVO)list.get(i)).getResID(), (SelfMediaVO)list.get(i));
        }
        
        // 返回
        return map;
    	
    }
    
    /**
     * 获取全部的屏保、广告文件列表
     * @return
     */
    public List getAllMediaFiles()
	{
		return sqlMapClientTemplate.queryForList("media.getAllMediaFiles", null);
	}
    
    /**
     * 获取按省份设置的屏保信息列表
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<String> getProvinceScList()
    {
    	return sqlMapClientTemplate.queryForList("media.getProvinceScList", "screenGroup");
    }
}
