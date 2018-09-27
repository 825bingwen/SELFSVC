package com.gmcc.boss.selfsvc.media.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.cache.SSMediaFileFresh;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.media.dao.MediaDaoImpl;
import com.gmcc.boss.selfsvc.media.model.SelfMediaVO;

/**
 * 多媒体播放播放ServiceImpl
 * 终端机生成广告播放配置文件adv.wpl
 * 终端机生成屏保播放配置文件sc.wpl
 * @author  yKF28472
 * @version  [版本号, 2010-12-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MediaServiceImpl implements MediaService 
{
	static DocumentFactory documentFactory = DocumentFactory.getInstance();
	
	private static Log logger = LogFactory.getLog(MediaServiceImpl.class);
	
	private MediaDaoImpl mediaDaoImpl;
	
	/**
     * 获取资源列表对象
     * @param termId 终端ID
     * @param region 区域
     * @param mediaType 媒体类型
     * @param path 服务器存放的文件的路径
     * @return
     */
	public String getMediaWpl(String termId, String region, String mediaType, String path)
	{
        // 取媒体播放文件列表
        String resids = "";
        if (Constants.SC_TYPE.equals(mediaType)){
        	resids = mediaDaoImpl.getScList(termId);
        }else{
        	resids = mediaDaoImpl.getAdvList(termId);
        }
        
        if (resids == null || "".equals(resids.trim()))
        {
        	return "";
        }
        // 组装播放文件内容
        String regions = "";
        if (!Constants.PROVINCE_REGION_999.equals(region))
        {
        	regions = region + "," + Constants.PROVINCE_REGION_999;
        }
        Map map = mediaDaoImpl.getMediaResList(resids, mediaType, regions);
        
        String mediaArray[] = resids.split(Constants.STR_SPLIT_TRANS);
        
		return getWplXml(mediaArray, map, path);
	}
	
	
    /**
     * 生成播放列表内容
     * @param advArr 媒体资源文件ID 
     * @param map 媒体资源文件对象
     * @param path 服务器存放的文件的路径
     * @return
     */
    private String getWplXml(String[] advArr, Map map, String path)
    {
    	Document doc = documentFactory.createDocument();
		doc.setXMLEncoding("GBK");
		
		//根节点
		Element root = doc.addElement("root");
		
        int size = advArr.length;
        for (int i = 0; i < size; i++)
        {
            SelfMediaVO smv = (SelfMediaVO)map.get(advArr[i]);
            if (null == smv)
            {
                logger.error("获取媒体文件:" + advArr[i] + "失败,请检查数据!");
                continue;
            }
            // 该文件存在直接将信息传送到客户端,如果不存在则到主机媒体文件目录中去复制
            java.io.File f = new java.io.File(path + smv.getDownLoadPath());
            if (!f.exists())
            {
                logger.error("服务器目录" + smv.getResLink() + "不存在文件:" + smv.getResName() + "(资源编号:" + smv.getResID() + ")");
                String sourcePath = (String)PublicCache.getInstance().getCachedData("mediaFilePath") + System.getProperty("file.separator") + smv.getResName();
                java.io.File sourceFile = new java.io.File(sourcePath);
                if (!sourceFile.exists())
                {
                    logger.error("主机媒体文件目录找不到文件:" + sourcePath + ",请检查!");
                    continue;
                }
                else
                {
                    logger.info("开始复制文件:" + sourcePath);
					SSMediaFileFresh.copyFile(sourceFile, f);
					logger.info("复制文件:" + sourcePath + "完成.");
                }
            }
            else
            {
                logger.info("检查" + smv.getDownLoadPath() + "文件存在.");
            }
            
            //消息体
    		Element media = root.addElement("media");
    		
    		// 播放顺序
    		Element sortID = media.addElement("sortID");
    		sortID.addText(String.valueOf(i+1));
    		
    		// 资源ID
    		Element resID = media.addElement("resID");
    		resID.addText(smv.getResID());
    		
    		// 资源名称
    		Element resName = media.addElement("resName");
    		resName.addText(smv.getResName());
    		
    		// 文件格式
    		Element resFormat = media.addElement("resFormat");
    		resFormat.addText(smv.getResFormat());
    		
    		// 状态时间
    		Element statusDate = media.addElement("statusDate");
    		statusDate.addText(smv.getStatusDate());
    		
    		// 下载地址
    		Element downLoadPath = media.addElement("downLoadPath");
    		downLoadPath.addText(smv.getDownLoadPath());
    		
    		// 播放时间
    		Element resPlayTime = media.addElement("resPlayTime");
    		resPlayTime.addText(smv.getResPlayTime());

        }
        return doc.asXML();
    }
    
    public List getAllMediaFiles()
	{
		return mediaDaoImpl.getAllMediaFiles();
	}
    
    /**
     * 获取按省份设置的屏保信息列表
     * @return XML
     * @remark create by wWX217192 2014-09-19 OR_HUB_201403_1773 自助终端LOGO更换及屏保一键更新功能
     */
    public String getProvinceScList(String mediaType, String path)
    {
    	// 取媒体播放文件列表
    	List<String> residList = new ArrayList<String>();
    	
    	if(Constants.SC_TYPE.equals(mediaType))
    	{
    		residList = mediaDaoImpl.getProvinceScList();
    	}
    	
    	if(null == residList || residList.size() == 0)
    	{
    		return "";
    	}
    	
    	// 取屏保Id
    	StringBuffer resids = new StringBuffer("");
    	
    	for(int i = 0; i < residList.size(); i++)
    	{
    		if(i == residList.size() - 1)
    		{
    			resids.append(residList.get(i));
    		}
    		else
    		{
    			resids.append(residList.get(i)).append(",");
    		}
    	}
    	
    	// 获取播放文件信息
    	Map map = mediaDaoImpl.getMediaResList(resids.toString(), mediaType, Constants.PROVINCE_REGION_999);
    	
    	String mediaArray[] = resids.toString().split(Constants.STR_SPLIT_TRANS);
        
		return getWplXml(mediaArray, map, path);
    }
	
	public MediaDaoImpl getMediaDaoImpl() {
		return mediaDaoImpl;
	}
	public void setMediaDaoImpl(MediaDaoImpl mediaDaoImpl) {
		this.mediaDaoImpl = mediaDaoImpl;
	}
}
