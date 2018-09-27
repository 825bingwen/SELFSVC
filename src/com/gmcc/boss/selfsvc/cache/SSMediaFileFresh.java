/*
 * �ļ�����SSMediaFileFresh.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ��������桢�����������ۻ�ͼƬ��Դ���Żݴ���ͼƬ��Դ��ͬ��
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-1
 * �޸����ݣ�����
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
 * �ļ�ͬ��
 * 
 * @author g00140516
 * @version 1.0 2010.12.1
 *
 */
public class SSMediaFileFresh
{
    private static Log logger = LogFactory.getLog(SSMediaFileFresh.class);
    
    /**
     * ��Դͬ��
     * @param rootPath
     */
    public static void synchronizeFiles(String rootPath)
    {
        // ���������ͬ��
        if (logger.isInfoEnabled())
        {
            logger.info("������������Դ�ļ���ʼͬ��������");
        }
        
        // ������������Դ�ļ��������Ĵ��Ŀ¼
        String localMediaSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.MEDIA_FILE_PATH);
        if (localMediaSourceFileDir == null || localMediaSourceFileDir.trim().length() == 0)
        {
            logger.error("������������Դ�ļ��������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�");
        }
        else
        {
            List mediaFiles = getMediaFileList();
            if (mediaFiles == null || mediaFiles.size() == 0)
            {
                logger.error("δ�����ݿ���ȡ��������������Դ�ļ��б�");
            }
            else
            {
                // ������������Դ�ļ���Ӧ���ڵĴ��Ŀ¼
                String mediaTargetFileDIr = rootPath + Constants.MEDIA_FILE_RELATIVE_PATH;
                copyDir(localMediaSourceFileDir, mediaTargetFileDIr, mediaFiles);
            }
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("������������Դ�ļ�ͬ������");
        }
        
        /*
         * //�����ۻ�ͼƬͬ�� if (logger.isInfoEnabled()) { logger.info("�����ۻ�ͼƬ��Դͬ����ʼ������"); }
         *  // �����ۻ�ͼƬ��Դ�ڱ����Ĵ��Ŀ¼ String sellgoodsSourceFileDir = (String)
         * PublicCache.getInstance().getCachedData(Constants.GOODS_FILE_PATH); if (sellgoodsSourceFileDir == null ||
         * sellgoodsSourceFileDir.trim().length() == 0) { logger.error("�����ۻ�ͼƬ��Դ�������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�"); }
         * else { // �����ۻ�ͼƬ��Դ��Ӧ���ڵĴ��Ŀ¼ String sellgoodsTargetFileDIr = rootPath +
         * Constants.SELLGOODS_FILE_RELATIVELY_PATH; copyDir(sellgoodsSourceFileDir, sellgoodsTargetFileDIr); }
         * 
         * if (logger.isInfoEnabled()) { logger.info("�����ۻ�ͼƬ��Դͬ������"); }
         * 
         * //�Żݴ���ͼƬͬ�� if (logger.isInfoEnabled()) { logger.info("�Żݴ���ͼƬ��Դͬ����ʼ������"); }
         *  // �Żݴ���ͼƬ��Դ�ڱ����Ĵ��Ŀ¼ String yhdzSourceFileDir = (String) PublicCache.getInstance().getCachedData(
         * Constants.YHDZ_FILE_PATH); if (yhdzSourceFileDir == null || yhdzSourceFileDir.trim().length() == 0) {
         * logger.error("�Żݴ���ͼƬ��Դ�������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�"); } else { // �Żݴ���ͼƬ��Դ��Ӧ���ڵĴ��Ŀ¼ String
         * yhdzTargetFileDIr = rootPath + Constants.ABATE_FILE_RELATIVELY_PATH; copyDir(yhdzSourceFileDir,
         * yhdzTargetFileDIr); }
         * 
         * if (logger.isInfoEnabled()) { logger.info("�Żݴ���ͼƬ��Դͬ������"); }
         */

        if (Constants.PROOPERORGID_NX.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
                && "1".equals((String)PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
        {
            // ������ʾ�ļ�ͬ��
            if (logger.isInfoEnabled())
            {
                logger.info("������ʾ�ļ�ͬ����ʼ������");
            }
            
            // ������ʾ�ļ���Դ�ڱ����Ĵ��Ŀ¼
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
                logger.error("������ʾ�ļ���Դ�������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�");
            }
            else
            {
                // ������ʾ�ļ���Դ��Ӧ���ڵĴ��Ŀ¼
                String promptTargetFileDIr = rootPath + Constants.PROMPT_FILE_RELATIVELY_PATH;
                copyDir(promptSourceFileDir, promptTargetFileDIr, promptFileList);
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("������ʾ�ļ�ͬ������");
            }
        }
        
        // add begin g00150516 2012/08/23 R003C12L08n01 ��Ʒ���ٷ�����ͼƬͬ��
        if (logger.isInfoEnabled())
        {
            logger.info("��Ʒ���ٷ�����ͼƬͬ����ʼ������");
        }
        
        String prodImgSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.SH_PATH_PRODIMG);
        if (prodImgSourceFileDir == null || "".equals(prodImgSourceFileDir.trim()))
        {
            logger.error("��Ʒ���ٷ�����ͼƬ��Դ�Ĵ��Ŀ¼ά����������sh_param��sh_param_value����SH_PATH_PRODIMG������");
        }
        else
        {
            String prodImgTargetFileDIr = rootPath + Constants.PRODIMG_FILE_RELATIVELY_PATH;
            copyDir(prodImgSourceFileDir, prodImgTargetFileDIr);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��Ʒ���ٷ�����ͼƬͬ������");
        }
        // add end g00150516 2012/08/23 R003C12L08n01 ��Ʒ���ٷ�����ͼƬͬ��
        
        // add begin zWX176560 2013/08/15 OR_NX_201307_930
        // �˵�ͼƬͬ��
        
        if (logger.isInfoEnabled())
        {
            logger.info("�˵�����Դ�ļ���ʼͬ��������");
        }
        
        if (Constants.PROOPERORGID_NX.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            // �˵�����Դ�ļ��������Ĵ��Ŀ¼
            String localMenuSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.MENUIMGPATH);
            if (localMenuSourceFileDir == null || localMediaSourceFileDir.trim().length() == 0)
            {
                logger.error("�˵�����Դ�ļ��������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�");
            }
            else
            {
                // ������������Դ�ļ���Ӧ���ڵĴ��Ŀ¼
                String menuTargetFileDIr = rootPath + Constants.MENUIMG_FILE_RELATIVELY_PATH + "/";
                copyDirByCover(localMenuSourceFileDir, menuTargetFileDIr);
                
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("�˵�����Դ�ļ�ͬ������");
            }
        }
        // add end zWX176560 2013/08/15 OR_NX_201307_930
        
        // add begin jWX216858 2014/12/25 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
        synRocommend4GFiles(rootPath);
        // add end jWX216858 2014/12/25 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
    }
    
    /**
     * 4G�ն��Ƽ���Դͬ��
     * @param rootPath
     * @remark create by jWX216858 2014/12/25 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
     * 
     */
    private static void synRocommend4GFiles(String rootPath)
    {
    	// ͬ��"4G�ն��Ƽ�"�����õ���ͼƬ
        if (Constants.PROOPERORGID_SD.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
        	// �˵�����Դ�ļ��������Ĵ��Ŀ¼
            String localPhoneSourceFileDir = (String)PublicCache.getInstance().getCachedData(Constants.PHONEIMGPATH);
            if (localPhoneSourceFileDir == null || localPhoneSourceFileDir.trim().length() == 0)
            {
                logger.error("4G�ն��Ƽ�����Դ�ļ��������Ĵ��Ŀ¼ά����������selfsvc.properties�ļ�");
            }
            else
            {
                // 4G�ն��Ƽ�����Դ�ļ���Ӧ���ڵĴ��Ŀ¼
                String menuTargetFileDIr = rootPath + Constants.PHONEIMG_FILE_RELATIVELY_PATH + "/";
                copyDirByCover(localPhoneSourceFileDir, menuTargetFileDIr);
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("4G�ն��Ƽ�����Դ�ļ�ͬ������");
            }
        }
    }
    
    /**
     * ��ָ���ļ���ԴĿ¼���Ƶ�Ŀ��Ŀ¼
     * 
     * @param sourceFileDir��ԴĿ¼
     * @param targetFileDir��Ŀ��Ŀ¼
     * @param files���ļ��б�
     */
    private static void copyDir(String sourceFileDir, String targetFileDir, List files)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "��ʼ������");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("ԴĿ¼--" + sourceFileDir + "--�ڱ��������ڣ�����selfsvc.properties�ļ�");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("Ŀ��Ŀ¼--" + targetFileDir + "--�����ڣ��Ҵ���ʧ��");
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
                logger.error("�ļ�" + sourceFileDir + fileSeparator + fileName + "�����ڣ�����");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            if (destFile.exists())
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("�ļ�" + fileName + "�Ѿ����ڣ�����Ҫͬ��");
                }
                continue;
            }
            
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "������ͬ���ļ�����" + copies);
        }
    }
    
    /**
     * ��ԴĿ¼�µ������ļ�ͬ����Ŀ��Ŀ¼
     * 
     * @param sourceFileDir
     * @param targetFileDir
     */
    public static void copyDir(String sourceFileDir, String targetFileDir)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "��ʼ������");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("ԴĿ¼--" + sourceFileDir + "--�ڱ��������ڣ�����selfsvc.properties�ļ�");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("Ŀ��Ŀ¼--" + targetFileDir + "--�����ڣ��Ҵ���ʧ��");
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
                logger.error("�ļ�" + sourceFileDir + fileSeparator + fileName + "�����ڣ�����");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            if (destFile.exists())
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("�ļ�" + fileName + "�Ѿ����ڣ�����Ҫͬ��");
                }
                continue;
            }
            
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "������ͬ���ļ�����" + copies);
        }
    }
    
    // add end zWX176560 2013/08/15 OR_NX_201307_930
    /**
     * ��ԴĿ¼�µ������ļ�ͬ����Ŀ��Ŀ¼
     * 
     * @param sourceFileDir
     * @param targetFileDir
     */
    public static void copyDirByCover(String sourceFileDir, String targetFileDir)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "��ʼ������");
        }
        
        File srcDir = new File(sourceFileDir);
        if (!srcDir.isDirectory())
        {
            logger.error("ԴĿ¼--" + sourceFileDir + "--�ڱ��������ڣ�����selfsvc.properties�ļ�");
            return;
        }
        
        File destDir = new File(targetFileDir);
        if (!destDir.isDirectory())
        {
            if (!destDir.mkdirs())
            {
                logger.error("Ŀ��Ŀ¼--" + targetFileDir + "--�����ڣ��Ҵ���ʧ��");
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
                logger.error("�ļ�" + sourceFileDir + fileSeparator + fileName + "�����ڣ�����");
                continue;
            }
            
            destFile = new File(targetFileDir + fileSeparator + fileName);
            copyFile(srcFile, destFile);
            copies++;
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��ԴĿ¼" + sourceFileDir + "ͬ���ļ���Ŀ��Ŀ¼" + targetFileDir + "������ͬ���ļ�����" + copies);
        }
    }
    // add end zWX176560 2013/08/15 OR_NX_201307_930
    
    /**
     * �����ļ�
     * 
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFile(File sourceFile, File targetFile)
    {
        if (logger.isInfoEnabled())
        {
            logger.info("�����ļ�" + sourceFile + "��" + targetFile + "��ʼ������");
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
            
            // ��������
            byte[] b = new byte[1024 * 10];
            int len;
            while ((len = inBuff.read(b)) != -1)
            {
                outBuff.write(b, 0, len);
            }
            // ˢ�´˻���������
            outBuff.flush();
            
        }
        catch (FileNotFoundException e)
        {
            logger.error("�����ļ�" + sourceFile + "��" + targetFile + "�쳣��", e);
        }
        catch (IOException e)
        {
            logger.error("�����ļ�" + sourceFile + "��" + targetFile + "�쳣��", e);
        }
        finally
        {
            closeStream(input,inBuff,output,outBuff);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("�����ļ�" + sourceFile + "��" + targetFile + "����");
        }
    }
    

    /**
     * ������
     * <������ϸ����>
     * @param input
     * @param inBuff
     * @param output
     * @param outBuff
     * @see [�ࡢ��#��������#��Ա]
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
            logger.error("�ر��ļ����쳣��", e);
        }
    }
    
    /**
     * ��ȡ��桢������������Դ�ļ�
     * 
     * @return
     */
    private static List getMediaFileList()
    {
        if (logger.isInfoEnabled())
        {
            logger.info("��ȡȫ��������������ļ��б�����");
        }
        List data = null;
        
        try
        {
            MediaService service = (MediaService)ApplicationContextUtil.getBean("mediaService");
            data = service.getAllMediaFiles();
        }
        catch (Exception e)
        {
            logger.error("��ȡȫ��������������ļ��б��쳣��", e);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��ȡȫ��������������ļ��б����");
        }
        
        return data;
    }
}
