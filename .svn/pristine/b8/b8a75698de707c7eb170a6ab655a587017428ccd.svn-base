/*
 * @filename: SocketUtil.java
 * @ All Right Reserved (C), 2006-2106, HUAWEI TECO CO.
 * @brif:  socket协议通讯
 * @author: g00140516
 * @de:  2011/12/09 
 * @description: 新增
 * @remark: create g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
 */
package com.gmcc.boss.selfsvc.call;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.CharsetConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.LengthConstants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * socket协议通讯
 * 
 * @author g00140516
 * @version 1.0, 2011/12/09
 * @see
 * @since
 */
public class SocketUtil
{
    private static Log logger = LogFactory.getLog(SocketUtil.class);
    
    private Socket socket = null;
    
    private InputStream is = null;
    
    private OutputStream os = null;
    
    /**
     * 流水号
     */
    private int transID = 0;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * socket调用入口
     * 
     * @param menuid 菜单ID
     * @param businessCode 业务代码
     * @param operID 操作员工号
     * @param pkgBody 报文包体
     * @return
     * @see
     */
    public ReturnWrap invoke(String menuid, String businessCode, String operID, String pkgBody)
    {
        initSocket();
        writeSocketPackage(menuid, businessCode, operID, pkgBody);
        
        return readSocketPackage();
    }
    
    /**
     * 创建socket连接
     * 
     * @see
     */
    private void initSocket()
    {
        // 从缓存中获取服务端的IP、port
        String ip = (String) PublicCache.getInstance().getCachedData(Constants.INT_SOCKET_IP);
        String port = (String) PublicCache.getInstance().getCachedData(Constants.INT_SOCKET_PORT);
        
        if (null != ip && !"".equals(ip.trim()) && null != port && !"".equals(port.trim()))
        {
            try
            {
                int nPort = Integer.parseInt(port);
                
                socket = new Socket(ip, nPort);
                is = socket.getInputStream();
                os = socket.getOutputStream();
            }
            catch (Exception e)
            {
                logger.error("创建socket链接失败：", e);
                
                if (null != os)
                {
                    try
                    {
                        os.close();
                        os = null;
                    }
                    catch (IOException e1)
                    {
                        logger.error("关闭socket链接失败：", e1);
                    }
                }
                
                if (null != is)
                {
                    try
                    {
                        is.close();
                        is = null;
                    }
                    catch (IOException e1)
                    {
                        logger.error("关闭socket链接失败：", e1);
                    }
                }
                
                if (socket != null)
                {
                    try
                    {
                        socket.close();
                        socket = null;
                    }
                    catch (IOException e1)
                    {
                        logger.error("关闭socket链接失败：", e1);
                    }
                }
            }
        }
    }
    
    /**
     * 发送报文
     * 
     * @param menuid 菜单ID
     * @param businessCode 业务代码
     * @param operID 操作员工号
     * @param pkgBody 业务包体
     * @see
     */
    private void writeSocketPackage(String menuid, String businessCode, String operID, String pkgBody)
    {
        if (null == socket || null == is || null == os)
        {
            return;
        }
        
        try
        {
            // 计算分包数目
            int pkgNum = 1;
            if (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH < pkgBody.length())
            {
                pkgNum = pkgBody.length() / (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH);
                
                if (0 != pkgBody.length() % (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH))
                {
                    pkgNum += 1;
                }
            }
            
            StringBuffer buffer = null;
            
            // 依次发送多个包
            for (int i = 0; i < pkgNum; i++)
            {
                buffer = new StringBuffer(1024);
                
                // 不是最后一个包
                if (i != pkgNum - 1)
                {
                    // 包长LengthConstants.MAX_LENGTH
                    buffer.append(CommonUtil.formatStr("" + LengthConstants.MAX_LENGTH,
                            "right",
                            " ",
                            LengthConstants.PACKAGE_LENGTH));
                }
                else
                {
                    // 包长pkgBody.getBytes().length % (LengthConstants.MAX_LENGTH -
                    // LengthConstants.PACKAGE_HEADER_LENGTH)
                    buffer.append(CommonUtil.formatStr(""
                            + (LengthConstants.PACKAGE_HEADER_LENGTH + pkgBody.length()
                                    % (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH)),
                            "right",
                            " ",
                            LengthConstants.PACKAGE_LENGTH));
                }
                
                buffer.append(CommonUtil.formatStr(menuid, "right", " ", LengthConstants.MENUID_LENGTH));
                
                buffer.append(CommonUtil.formatStr(businessCode, "right", " ", LengthConstants.BUSINESSCODE_LENGTH));
                
                buffer.append(CommonUtil.formatStr("" + transID, "right", " ", LengthConstants.SEQNUM_LENGTH));
                
                buffer.append(CommonUtil.formatStr(Constants.CHANNEL_ID, "right", " ", LengthConstants.CHANNEL_LENGTH));
                
                buffer.append(CommonUtil.formatStr(operID, "right", " ", LengthConstants.OPERID_LENGTH));
                
                buffer.append(CommonUtil.formatStr(Constants.SUCCESS_RETCODE_HUNDRED,
                        "right",
                        " ",
                        LengthConstants.RETCODE_LENGTH));
                
                buffer.append(CommonUtil.formatStr(sdf.format(new Date()),
                        "right",
                        " ",
                        LengthConstants.HEADER_TIME_LENGTH));
                
                buffer.append(CommonUtil.formatStr("" + pkgNum, "right", " ", LengthConstants.PACKAGE_NUM_LENGTH));
                
                // 不是最后一个包
                if (i != pkgNum - 1)
                {
                    buffer.append(CommonUtil.formatStr(Constants.NEXTPKG_FLAG,
                            "right",
                            " ",
                            LengthConstants.NEXTPKG_FLAG_LENGTH));
                    
                    buffer.append(pkgBody.substring(i
                            * (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH), (i + 1)
                            * (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH)));
                }
                else
                {
                    buffer.append(CommonUtil.formatStr(Constants.NO_NEXTPKG_FLAG,
                            "right",
                            " ",
                            LengthConstants.NEXTPKG_FLAG_LENGTH));
                    
                    buffer.append(pkgBody.substring(i
                            * (LengthConstants.MAX_LENGTH - LengthConstants.PACKAGE_HEADER_LENGTH)));
                }
                
                if (logger.isInfoEnabled())
                {
                    logger.info("socket请求报文：" + buffer.toString());
                }
                
                os.write(buffer.toString().getBytes(CharsetConstants.GBK));
            }
        }
        catch (Exception e1)
        {
            logger.error("socket请求失败：", e1);
            
            this.closeConnection(os, is, socket);
        }
    }
    
    /**
     * 关闭连接（圈复杂度）
     * @param os
     * @param is
     * @param socket
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private void closeConnection(OutputStream os, InputStream is, Socket socket)
	{
		if (null != os)
		{
		    try
		    {
		        os.close();
		    }
		    catch (IOException e)
		    {
		        logger.error("关闭socket连接失败：", e);
		    }
		}
		
		if (null != is)
		{
		    try
		    {
		        is.close();
		    }
		    catch (IOException e)
		    {
		        logger.error("关闭socket连接失败：", e);
		    }
		}
		
		if (socket != null)
		{
		    try
		    {
		        socket.close();
		    }
		    catch (IOException e)
		    {
		        logger.error("关闭socket连接失败：", e);
		    }
		}
	}
	
    /**
     * 接收报文
     * 
     * @see [类、类#方法、类#成员]
     */
    private ReturnWrap readSocketPackage()
    {
        // 返回信息
        ReturnWrap rw = new ReturnWrap();
        
        if (null == socket || null == is || null == os)
        {
            rw.setStatus(SSReturnCode.ERROR);
            rw.setReturnMsg("系统异常");
            
            return rw;
        }
        
        try
        {
            // 成功标识
        	boolean successFlag = true;
        	
            // 后续包标志
            boolean hasNextPackage = true;
            
            // 单个包的包头字节流
            byte[] headerBuf = null;
            
            // 单个包的包体字节流
            byte[] bodyBuf = null;
            
            // 单个包的包头
            StringBuffer headerBuffer = null;
            
            // 所有包的包体字节流
            List<byte[]> bodyList = new ArrayList<byte[]>();
            
            // 所有包的包体字节流的总长度
            int totalLen = 0;
            
            // 依次读取多个包
            while (hasNextPackage)
            {
                headerBuf = new byte[LengthConstants.PACKAGE_HEADER_LENGTH];
                
            	// 计算包长
            	headerBuffer = new StringBuffer(LengthConstants.PACKAGE_HEADER_LENGTH);
                for (int i = 0; i < LengthConstants.PACKAGE_LENGTH; i++)
                {
                	headerBuffer.append((char) is.read());
                }
                
                int packageLength = Integer.parseInt(headerBuffer.toString().trim());
                
                // 如果整个包的长度小于等于报文头的长度，调用失败
                if (LengthConstants.PACKAGE_HEADER_LENGTH >= packageLength)
                {
                	successFlag = false;
                	
                	rw.setStatus(SSReturnCode.ERROR);
                    rw.setReturnMsg("socket请求失败");
                    
                    logger.error("socket请求失败，整个包的长度为" + packageLength + "，小于等于报文头的长度");
                    
                    break;
                }
                // 如果整个包的长度大于系统允许的最大长度，调用失败
                else if (LengthConstants.MAX_LENGTH < packageLength)
                {
                	successFlag = false;
                	
                	rw.setStatus(SSReturnCode.ERROR);
                    rw.setReturnMsg("socket请求失败");
                    
                    logger.error("socket请求失败，整个包的长度为" + packageLength + "，大于系统要求的最大报文长度");
                    
                    break;
                }
                
                // 已读字节数
                int readLength = LengthConstants.PACKAGE_LENGTH;
                
                // 获取包头内容
                while (readLength < LengthConstants.PACKAGE_HEADER_LENGTH)
                {
                    int len = is.read(headerBuf, 0, LengthConstants.PACKAGE_HEADER_LENGTH - readLength);
                    
                    headerBuffer.append(new String(headerBuf, 0, len));
                    
                    readLength += len;
                }
                
                String packageHeaderContent = headerBuffer.toString();
                
                if (logger.isInfoEnabled())
                {
                    logger.info("socket应答报文头：" + packageHeaderContent);
                }
                              
                // 获取返回码
                int offset = LengthConstants.PACKAGE_LENGTH + LengthConstants.MENUID_LENGTH
                        + LengthConstants.BUSINESSCODE_LENGTH + LengthConstants.SEQNUM_LENGTH
                        + LengthConstants.CHANNEL_LENGTH + LengthConstants.OPERID_LENGTH;
                String retCode = packageHeaderContent.substring(offset, offset + LengthConstants.RETCODE_LENGTH);
               
                //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
                retCode = retCode.trim();
                //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端            
                
                // 已读字节数
                readLength = LengthConstants.PACKAGE_HEADER_LENGTH;
                
                bodyBuf = new byte[packageLength - readLength];
                
                int startPosition = 0;
                
                // 成功，将包体内容放到pkgBodyBuffer中
                if (Constants.SUCCESS_RETCODE_HUNDRED.equals(retCode)
                        || Constants.SUCCESS_RETCODE_ZERO.equals(retCode))
                {
                    String nextPkgFlag = packageHeaderContent.substring(LengthConstants.PACKAGE_HEADER_LENGTH - LengthConstants.NEXTPKG_FLAG_LENGTH,
                            LengthConstants.PACKAGE_HEADER_LENGTH);
                    
                    if (Constants.NO_NEXTPKG_FLAG.equals(nextPkgFlag))
                    {
                        hasNextPackage = false;
                    }                    
                    
                    // 获取单个包的包体内容，作为错误信息
                    while (readLength < packageLength)
                    {
                        int len = is.read(bodyBuf, startPosition, packageLength - readLength);
                        
                        readLength += len;
                        
                        startPosition += len;
                    }
                    
                    bodyList.add(bodyBuf);
                    totalLen += bodyBuf.length;           
                }
                // 失败，将包体内容放在字节流中，最后转换成String，要考虑汉字的情况
                else
                {
                    // 获取单个包的包体内容，作为错误信息
                    while (readLength < packageLength)
                    {
                        int len = is.read(bodyBuf, startPosition, packageLength - readLength);
                        
                        readLength += len;
                        
                        startPosition += len;
                    }
                    
                	successFlag = false;
                    
                    rw.setStatus(SSReturnCode.ERROR);
                    rw.setReturnMsg(new String(bodyBuf, CharsetConstants.GBK));
                    
                    logger.error("socket详单查询失败："
                            + new String(bodyBuf, CharsetConstants.GBK));
                    
                    break;
                }
            }
            
            if (successFlag)
            {
                byte[] totalBuffer = new byte[totalLen];
                int startPosition = 0;
                
                byte[] singlePkgBody = null;
                
                int pkgNum = bodyList.size();
                for (int i = 0; i < pkgNum; i++)
                {
                    singlePkgBody = (byte[]) bodyList.get(i);
                    
                    for (int j = 0; j < singlePkgBody.length; j++)
                    {
                        totalBuffer[startPosition + j] = singlePkgBody[j];                        
                    }
                    
                    startPosition += singlePkgBody.length;
                }
                
                rw.setStatus(SSReturnCode.SUCCESS);
                rw.setReturnObject(new String(totalBuffer, CharsetConstants.GBK));
            }
        }
        catch (Exception e)
        {
            logger.error("socket请求失败：", e);
            
            rw.setStatus(SSReturnCode.ERROR);
            rw.setReturnMsg(e.getMessage());
        }
        finally
        {
            this.closeConnection(os, is, socket);
        }
        
        return rw;
    }
}
