package com.gmcc.boss.selfsvc.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
 
/**
 * 
 * GZIP压缩
 * <功能详细描述>
 * 
 * @author  ZKF66389
 * @version  [版本号, Aug 22, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CompressedStream extends ServletOutputStream 
{ 
    
    private ServletOutputStream out; 
    private ByteArrayOutputStream bout; 
    private GZIPOutputStream gzip; 
    
    public CompressedStream(ServletOutputStream out) throws IOException 
    {
        this.out = out;
        reset();
    }
    
    /** @see ServletOutputStream * */ 
    public void close() throws IOException 
    { 
        gzip.close(); 
    }
     
    /** @see ServletOutputStream * */ 
    public void flush() throws IOException 
    { 
        gzip.flush(); 
    } 
    
    /** @see ServletOutputStream * */ 
    public void write(byte[] b) throws IOException 
    { 
        write(b, 0, b.length); 
    } 
    
    /** @see ServletOutputStream * */ 
    public void write(byte[] b, int off, int len) throws IOException 
    { 
        gzip.write(b, off, len); 
    } 
    
    /** @see ServletOutputStream * */ 
    
    public void write(int b) throws IOException 
    { 
        gzip.write(b); 
    } 
    
    /** 
    * Resets the stream. 
    * 
    * @throws IOException if an I/O error occurs. 
    */ 
    public void reset() throws IOException 
    {
        gzip = new GZIPOutputStream(out); 
    } 

} 
