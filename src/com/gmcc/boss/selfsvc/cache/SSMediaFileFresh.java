/*
 * 文件名：SSMediaFileFresh.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：广告、屏保、自助售货图片资源、优惠打折图片资源的同步
 * 修改人：g00140516
 * 修改时间：2010-12-1
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.cache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.media.service.MediaService;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;

/**
 * 文件同步
 * 
 * @author g00140516
 * @version 1.0 2010.12.1
 *
 */
public class SSMediaFileFresh
{
    private static Log logger = LogFactory.getLog(SSMediaFileFresh.class);
    
    /**
     * 资源同步
     * @param rootPath
     */
    public static void synchronizeFiles(String rootPath)
    {
        // 屏保、广告同步
        if (logger.isInfoEnabled())
        {
            logger.info("屏保、广告的资源文件开始同步。。。");
        }
        
        // 屏保、广告的资源文件在主机的存放目录
        String localMediaSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.MEDIA_FILE_PATH);
        if (localMediaSourceFileDir == null || localMediaSourceFileDir.trim().length() == 0)
        {
            logger.error("屏保、广告的资源文件在主机的存放目录维护错误，请检查selfsvc.properties文件");
        }
        else
        {
            List mediaFiles = getMediaFileList();
            if (mediaFiles == null || mediaFiles.size() == 0)
            {
                logger.error("未从数据库中取到屏保、广告的资源文件列表");
            }
            else
            {
                // 屏保、广告的资源文件在应用内的存放目录
                String mediaTargetFileDIr = rootPath + Constants.MEDIA_FILE_RELATIVE_PATH;
                copyDir(localMediaSourceFileDir, mediaTargetFileDIr, mediaFiles);
            }
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("屏保、广告的资源文件同步结束");
        }
        
        /*
         * //自助售货图片同步 if (logger.isInfoEnabled()) { logger.info("自助售货图片资源同步开始。。。"); }
         *  // 自助售货图片资源在本机的存放目录 String sellgoodsSourceFileDir = (String)
         * PublicCache.getInstance().getCachedData(Constants.GOODS_FILE_PATH); if (sellgoodsSourceFileDir == null ||
         * sellgoodsSourceFileDir.trim().length() == 0) { logger.error("自助售货图片资源在主机的存放目录维护错误，请检查selfsvc.properties文件"); }
         * else { // 自助售货图片资源在应用内的存放目录 String sellgoodsTargetFileDIr = rootPath +
         * Constants.SELLGOODS_FILE_RELATIVELY_PATH; copyDir(sellgoodsSourceFileDir, sellgoodsTargetFileDIr); }
         * 
         * if (logger.isInfoEnabled()) { logger.info("自助售货图片资源同步结束"); }
         * 
         * //优惠打折图片同步 if (logger.isInfoEnabled()) { logger.info("优惠打折图片资源同步开始。。。"); }
         *  // 优惠打折图片资源在本机的存放目录 String yhdzSourceFileDir = (String) PublicCache.getInstance().getCachedData(
         * Constants.YHDZ_FILE_PATH); if (yhdzSourceFileDir == null || yhdzSourceFileDir.trim().length() == 0) {
         * logger.error("优惠打折图片资源在主机的存放目录维护错误，请检查selfsvc.properties文件"); } else { // 优惠打折图片资源在应用内的存放目录 String
         * yhdzTargetFileDIr = rootPath + Constants.ABATE_FILE_RELATIVELY_PATH; copyDir(yhdzSourceFileDir,
         * yhdzTargetFileDIr); }
         * 
         * if (logger.isInfoEnabled()) { logger.info("优惠打折图片资源同步结束"); }
         */

        if (Constants.PROOPERORGID_NX.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
                && "1".equals((String)PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
        {
            // 语音提示文件同步
            if (logger.isInfoEnabled())
            {
                logger.info("语音提示文件同步开始。。。");
            }
            
            // 语音提示文件资源在本机的存放目录
            List promptFileList = new ArrayList();
            promptFileList.add("Charge-01.wav");
            promptFileList.add("Charge-02.wav");
            promptFileList.add("Charge-03.wav");
            promptFileList.add("Charge-04.wav");
            promptFileList.add("Charge-05.wav");
            promptFileList.add("Charge-06.wav");
            String promptSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.MEDIA_FILE_PATH);
            if (promptSourceFileDir == null || promptSourceFileDir.trim().length() == 0)
            {
                logger.error("语音提示文件资源在主机的存放目录维护错误，请检查selfsvc.properties文件");
            }
            else
            {
                // 语音提示文件资源在应用内的存放目录
                String promptTargetFileDIr = rootPath + Constants.PROMPT_FILE_RELATIVELY_PATH;
                copyDir(promptSourceFileDir, promptTargetFileDIr, promptFileList);
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("语音提示文件同步结束");
            }
        }
        
        // add begin g00150516 2012/08/23 R003C12L08n01 产品快速发布，图片同步
        if (logger.isInfoEnabled())
        {
            logger.info("产品快速发布，图片同步开始。。。");
        }
        
        String prodImgSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.SH_PATH_PRODIMG);
        if (prodImgSourceFileDir == null || "".equals(prodImgSourceFileDir.trim()))
        {
            logger.error("产品快速发布，图片资源的存放目录维护错误，请检查sh_param、sh_param_value表中SH_PATH_PRODIMG的配置");
        }
        else
        {
            String prodImgTargetFileDIr = rootPath + Constants.PRODIMG_FILE_RELATIVELY_PATH;
            copyDir(prodImgSourceFileDir, prodImgTargetFileDIr);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("产品快速发布，图片同步结束");
        }
        // add end g00150516 2012/08/23 R003C12L08n01 产品快速发布，图片同步
        
        // add begin zWX176560 2013/08/15 OR_NX_201307_930
        // 菜单图片同步
        
        if (logger.isInfoEnabled())
        {
            logger.info("菜单的资源文件开始同步。。。");
        }
        
        if (Constants.PROOPERORGID_NX.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            // 菜单的资源文件在主机的存放目录
            String localMenuSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.MENUIMGPATH);
            if (localMenuSourceFileDir == null || localMediaSourceFileDir.trim().length() == 0)
            {
                logger.error("菜单的资源文件在主机的存放目录维护错误，请检查selfsvc.properties文件");
            }
            else
            {
                // 屏保、广告的资源文件在应用内的存放目录
                String menuTargetFileDIr = rootPath + Constants.MENUIMG_FILE_RELATIVELY_PATH + "/";
                copyDirByCover(localMenuSourceFileDir, menuTargetFileDIr);
                
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("菜单的资源文件同步结束");
            }
        }
        // add end zWX176560 2013/08/15 OR_NX_201307_930
        
        // add begin jWX216858 2014/12/25 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
        synRocommend4GFiles(rootPath);
        // add end jWX216858 2014/12/25 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
    }
    
    /**
     * 4G终端推荐资源同步
     * @param rootPath
     * @remark create by jWX216858 2014/12/25 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     * 
     */
    private static void synRocommend4GFiles(String rootPath)
    {
    	// 同步"4G终端推荐"中所用到的图片
        if (Constants.PROOPERORGID_SD.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
        	// 菜单的资源文件在主机的存放目录
            String localPhoneSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.PHONEIMGPATH);
            if (localPhoneSourceFileDir == null || localPhoneSourceFileDir.trim().length() == 0)
            {
                logger.error("4G终端推荐的资源文件在主机的存放目录维护错误，请检查selfsvc.properties文件");
            }
            else
            {
                // 4G终端推荐的资源文件在应用内的存放目录
                String menuTargetFileDIr = rootPath + Constants.PHONEIMG_FILE_RELATIVELY_PATH + "/";
                copyDirByCover(localPhoneSourceFileDir, menuTargetFileDIr);
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("4G终端推荐的资源文件同步结束");
            }
        }
    }
    
    /**
     * 将指定文件从源目录复制到目的目录
     * 
     * @param sourceFileDir，源目录
     * @param targetFileDir，目的目录
     * @param files，文件列表
     */
    private static void copyDir(String sourceFileDir, String targetFileDir, List files)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "开始。。。");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("源目录--" + sourceFileDir + "--在本机不存在，请检查selfsvc.properties文件");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("目的目录--" + targetFileDir + "--不存在，且创建失败");
                return;
            }
        }
        
        String fileSeparator = System.getProperty("file.separator");
        
        int filesNum = files.size();
        int copies = 0;
        String fileName = "";
        File srcFile = null;
        File destFile = null;
        for (int i = 0; i < filesNum; i++)
        {
            fileName = (String)files.get(i);
            srcFile = new File(sourceFileDir + fileSeparator + fileName);
            if (!srcFile.isFile())
            {
                logger.error("文件" + sourceFileDir + fileSeparator + fileName + "不存在，请检查");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            if (destFile.exists())
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("文件" + fileName + "已经存在，不需要同步");
                }
                continue;
            }
            
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "结束，同步文件数：" + copies);
        }
    }
    
    /**
     * 将源目录下的所有文件同步至目的目录
     * 
     * @param sourceFileDir
     * @param targetFileDir
     */
    public static void copyDir(String sourceFileDir, String targetFileDir)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "开始。。。");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("源目录--" + sourceFileDir + "--在本机不存在，请检查selfsvc.properties文件");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("目的目录--" + targetFileDir + "--不存在，且创建失败");
                return;
            }
        }
        
        File[] files = srcDir.listFiles();
        String fileSeparator = System.getProperty("file.separator");
        
        int copies = 0;
        String fileName = "";
        File srcFile = null;
        File destFile = null;
        for (int i = 0; i < files.length; i++)
        {
            fileName = files[i].getName();
            srcFile = new File(sourceFileDir + fileSeparator + fileName);
            if (!srcFile.isFile())
            {
                logger.error("文件" + sourceFileDir + fileSeparator + fileName + "不存在，请检查");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            if (destFile.exists())
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("文件" + fileName + "已经存在，不需要同步");
                }
                continue;
            }
            
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "结束，同步文件数：" + copies);
        }
    }
    
    // add end zWX176560 2013/08/15 OR_NX_201307_930
    /**
     * 将源目录下的所有文件同步至目的目录
     * 
     * @param sourceFileDir
     * @param targetFileDir
     */
    public static void copyDirByCover(String sourceFileDir, String targetFileDir)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "开始。。。");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("源目录--" + sourceFileDir + "--在本机不存在，请检查selfsvc.properties文件");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("目的目录--" + targetFileDir + "--不存在，且创建失败");
                return;
            }
        }
        
        File[] files = srcDir.listFiles();
        String fileSeparator = System.getProperty("file.separator");
        
        int copies = 0;
        String fileName = "";
        File srcFile = null;
        File destFile = null;
        for (int i = 0; i < files.length; i++)
        {
            fileName = files[i].getName();
            srcFile = new File(sourceFileDir + fileSeparator + fileName);
            if (!srcFile.isFile())
            {
                logger.error("文件" + sourceFileDir + fileSeparator + fileName + "不存在，请检查");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("从源目录" + sourceFileDir + "同步文件至目的目录" + targetFileDir + "结束，同步文件数：" + copies);
        }
    }
    // add end zWX176560 2013/08/15 OR_NX_201307_930
    
    /**
     * 复制文件
     * 
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFile(File sourceFile, File targetFile)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("复制文件" + sourceFile + "至" + targetFile + "开始。。。");
        }
        
        FileInputStream input = null;
        BufferedInputStream inBuff = null;
        FileOutputStream output = null;
        BufferedOutputStream outBuff = null;
        
        try
        {
            input = new FileInputStream(sourceFile);
            inBuff = new BufferedInputStream(input);
            
            output = new FileOutputStream(targetFile);
            outBuff = new BufferedOutputStream(output);
            
            // 缓冲数组
            byte[] b = new byte[1024 * 10];
            int len;
            while ((len = inBuff.read(b)) != -1)
            {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
            
        }
        catch (FileNotFoundException e)
        {
            logger.error("复制文件" + sourceFile + "至" + targetFile + "异常：", e);
        }
        catch (IOException e)
        {
            logger.error("复制文件" + sourceFile + "至" + targetFile + "异常：", e);
        }
        finally
        {
            closeStream(input,inBuff,output,outBuff);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("复制文件" + sourceFile + "至" + targetFile + "结束");
        }
    }
    

    /**
     * 关闲流
     * <功能详细描述>
     * @param input
     * @param inBuff
     * @param output
     * @param outBuff
     * @see [类、类#方法、类#成员]
     */
    private static void closeStream(FileInputStream input,BufferedInputStream inBuff,FileOutputStream output,BufferedOutputStream outBuff)
    {
        try
        {
            if (inBuff != null)
            {
                inBuff.close();
            }
            
            if (input != null)
            {
                input.close();
            }
            
            if (outBuff != null)
            {
                outBuff.close();
            }
            
            if (output != null)
            {
                output.close();
            }
        }
        catch (Exception e)
        {
            logger.error("关闭文件流异常：", e);
        }
    }
    
    /**
     * 获取广告、屏保的所有资源文件
     * 
     * @return
     */
    private static List getMediaFileList()
    {
        if (logger.isInfoEnabled())
        {
            logger.info("获取全部的屏保、广告文件列表。。。");
        }
        List data = null;
        
        try
        {
            MediaService service = (MediaService)ApplicationContextUtil.getBean("mediaService");
            data = service.getAllMediaFiles();
        }
        catch (Exception e)
        {
            logger.error("获取全部的屏保、广告文件列表异常：", e);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("获取全部的屏保、广告文件列表结束");
        }
        
        return data;
    }
}
