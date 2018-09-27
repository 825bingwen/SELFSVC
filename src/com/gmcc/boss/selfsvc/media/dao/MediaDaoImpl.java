package com.gmcc.boss.selfsvc.media.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.media.model.SelfMediaQO;
import com.gmcc.boss.selfsvc.media.model.SelfMediaVO;

/**
 * ��ý�岥�Ų�����
 * <������ϸ����>
 * @author  yKF28472
 * @version  [�汾��, 2010-10-06]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MediaDaoImpl extends BaseDaoImpl
{

    /** �����ն˻�����ȡ��沥���б�
     * <������ϸ����>
     * @param termid �ն˻����
     * @see [�ࡢ��#��������#��Ա]
     * @return ��沥���б�
     */
    public String getAdvList(String termId)
    {
        return (String)sqlMapClientTemplate.queryForObject("media.getAdvList", termId);
    }
    
    /** �����ն˻�����ȡ���������б�
     * <������ϸ����>
     * @param termid �ն˻����
     * @see [�ࡢ��#��������#��Ա]
     * @return ���������б�
     */
    public String getScList(String termId)
    {
    	return (String)sqlMapClientTemplate.queryForObject("media.getScList", termId);
    }
    
    /** ȡ�ն˻������б����
     * <������ϸ����>
     * @param resids ��Դ���
     * @param restype ��Դ����
     * @param regions ����
     * @see [�ࡢ��#��������#��Ա]
     * @return �����б����
     */
    public Map getMediaResList(String resids,String restype, String regions)
    {

        String residsTmp[] = resids.split(",");
        
        // TODO �˴�regions����Ϊ�գ�������
        String regionsTmp[] = regions.split(",");
        resids = "";
        regions = "";
        StringBuffer residsBuffer = new StringBuffer("");
        StringBuffer regionsBuffer = new StringBuffer("");
        
        // ������ԴID
        // TODO ƴ�Ӳ�ѯ����Ҫ����ԴID����
        for(int i=0;i<residsTmp.length;i++)
        {
            if(i == residsTmp.length-1){
                residsBuffer.append("'").append(residsTmp[i]).append("'");
            }else{
                residsBuffer.append("'").append(residsTmp[i]).append("'").append(",");
            }
        }
        
        // ��������ID
        for(int i=0;i<regionsTmp.length;i++)
        {
            if(i == regionsTmp.length-1){
                regionsBuffer.append("'").append(regionsTmp[i]).append("'");
            }else{
                regionsBuffer.append("'").append(regionsTmp[i]).append("'").append(",");
            }
        }
        
        // ƴװ��ѯ����
        SelfMediaQO selfMediaQO = new SelfMediaQO();
        selfMediaQO.setRegions(regionsBuffer.toString());
        selfMediaQO.setResids(residsBuffer.toString());
        selfMediaQO.setRestype(restype); // sc:���� adv:���
        
        //  ִ�в�ѯ
        List list = sqlMapClientTemplate.queryForList("media.getMediaResList", selfMediaQO);
        Map map = new HashMap();
        for(int i=0;i<list.size();i++){
            map.put(((SelfMediaVO)list.get(i)).getResID(), (SelfMediaVO)list.get(i));
        }
        
        // ����
        return map;
    	
    }
    
    /**
     * ��ȡȫ��������������ļ��б�
     * @return
     */
    public List getAllMediaFiles()
	{
		return sqlMapClientTemplate.queryForList("media.getAllMediaFiles", null);
	}
    
    /**
     * ��ȡ��ʡ�����õ�������Ϣ�б�
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<String> getProvinceScList()
    {
    	return sqlMapClientTemplate.queryForList("media.getProvinceScList", "screenGroup");
    }
}
