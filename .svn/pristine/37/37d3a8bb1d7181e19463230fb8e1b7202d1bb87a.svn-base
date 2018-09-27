package com.gmcc.boss.selfsvc.media.model;

import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;

/**
 * 多媒体结果对象
 * <功能详细描述>
 * @author  yKF28472
 * @version  [版本号, 2010-12-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SelfMediaVO
{
    private String region;// 地市
    private String resID;// 资源文件编号
    private String resName;// 保存到服务器上的媒体资源名称,加后缀名
    private String resFormat;// 媒体资源格式
    private String resLink;// 资源文件在web主机的相对路径
    private String status;// 状态
    private String statusDate;// 状态时间
    private String createDate;// 创建时间
    private String createOper;// 创建人工号
//    private String downLoadPath;// 下载路径
    private String resPlayTime;// 文件播放时长
    
    public String getCreateDate(){
        return createDate;
    }

    public void setCreateDate(String createDate){
        this.createDate = createDate;
    }

    public String getCreateOper(){
        return createOper;
    }

    public void setCreateOper(String createOper){
        this.createOper = createOper;
    }

    public String getDownLoadPath(){
    	if(Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
    		return System.getProperty("file.separator") + Constants.MEDIA_FILE_RELATIVE_PATH + System.getProperty("file.separator") + resName;
        }
    	else
    	{
    		return Constants.MEDIA_FILE_RELATIVE_PATH + System.getProperty("file.separator") + resName;
    	}
    }

//    public void setDownLoadPath(String downLoadPath){
//        this.downLoadPath = downLoadPath;
//    }

    public String getRegion(){
        return region;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public String getResFormat(){
        return resFormat;
    }

    public void setResFormat(String resFormat){
        this.resFormat = resFormat;
    }

    public String getResID(){
        return resID;
    }

    public void setResID(String resID){
        this.resID = resID;
    }

    public String getResLink(){
        return resLink;
    }

    public void setResLink(String resLink){
        this.resLink = resLink;
    }

    public String getResName(){
        return resName;
    }

    public void setResName(String resName){
        this.resName = resName;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatusDate(){
        return statusDate;
    }

    public void setStatusDate(String statusDate){
        this.statusDate = statusDate;
    }

    public String getResPlayTime(){
        if (StringUtils.isEmpty(resPlayTime)){
            resPlayTime = "60";
        }
        return resPlayTime;
    }

    public void setResPlayTime(String resPlayTime){
        this.resPlayTime = resPlayTime;
    }
    
}
