package com.gmcc.boss.selfsvc.util;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * 邮件发送工具类 <功能详细描述>
 * 
 * @author cKF48754
 * @version [版本号, Sep 13, 2011]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SendMailUtil extends TimerTask
{
    private static Log logger = LogFactory.getLog(SendMailUtil.class); // 日志类
    
    private String host; // 邮件服务器地址
    
    private String port; // 邮件服务器端口
    
    private String username; // 发件人用户名
    
    private String pwd; // 发件人密码
    
    private String from; // 发件人邮箱地址
    
    private String to; // 收件人地址
    
    private String subject; // 邮件标题
    
    private String txt; // 邮件内容
    
    private String hasProxy;// 是否使用代理标识
    
    private String proxyType;// 代理类型
    
    private String proxyHost;// 代理主机
    
    private String proxyHostPort;// 代理主机端口
    
    /**
     * 构造方法，调用SendMailUtil定时发送邮件时传入收件人地址（to）、邮件标题（subject）、邮件内容（txt）
     */
    public SendMailUtil(String to, String subject, String txt)
    {
        // modify begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
        this.host = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_HOST);
        this.port = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_PORT);
        this.username = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_USERNAME);
        
        //modify begin m00227318 2012/11/16 V200R003C12L11 OR_huawei_201211_242
        //将参数表中的发件人密码解密
        String mailPwd = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_PWD);
        this.pwd = EncryptDecryptUtil.decryptAesPwd(mailPwd);
        //modify end m00227318 2012/11/16 V200R003C12L11 OR_huawei_201211_242

        this.from = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_FROM);
        // modify end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
        this.to = to;
        this.subject = subject;
        this.txt = txt;
        
        if (null == host || null == username || null == pwd || null == port || "".equals(host) || "".equals(username)
                || "".equals(pwd) || "".equals(port))
        {
            logger.error("初始化邮箱信息失败,请检查参数【SH_MAIL_HOST】【SH_MAIL_USERNAME】【SH_MAIL_PWD】【SH_MAIL_PORT】的配置是否正确");
        }
    }
    
    public void run()
    {
        
        Properties props = new Properties();
        // modify begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
        hasProxy = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_HASPROXY);
        
        proxyType = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_PROXYTYPE);
        
        proxyHost = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_PROXYHOST);
        
        proxyHostPort = (String)PublicCache.getInstance().getCachedData(Constants.SH_MAIL_PROXYHOSTPORT);
        // modify end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
        
        // 设置邮件代理
        if (null != hasProxy && "" != hasProxy && "1".equals(hasProxy))
        {
            if ("http".equals(proxyType))
            {
                props.put("http.proxySet", "true");
                props.put("http.proxyHost", proxyHost);
                props.put("http.proxyPort", proxyHostPort);
            }
            else if ("socks".equals(proxyType))
            {
                props.put("proxySet", "true");
                props.put("socksProxyHost", proxyHost);
                props.put("socksProxyPort", proxyHostPort);
            }
            else
            {
                props.put("proxySet", "true");
                props.put("ProxyHost", proxyHost);
                props.put("ProxyPort", proxyHostPort);
            }
        }
        
        // 设置发送邮件的邮件服务器的属性（这里使用smtp服务器）
        props.put("mail.smtp.host", host);
        
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");
        
        // 不是默认端口25 需要设置端口
        props.put("mail.smtp.port", port);
        
        // modify begin g00140516 2012/01/09 R003C12L01n01 将getDefaultInstance改为getInstance
        // 原因：getDefaultInstance首先是从缓存中查找是否有properties存在。如果存在，则加载默认的properties；如果不存在，才加载用户自己定义的properties。
        // 在首次调用时，会创建一个properties。不管之后邮箱的配置是否改变，使用getDefaultInstance的话，加载的都是首次创建的properties，就会导致新的配置不生效
        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getInstance(props);
        // modify end g00140516 2012/01/09 R003C12L01n01 将getDefaultInstance改为getInstance
        
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        
        try
        {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            // 加载标题
            message.setSubject(subject);
            
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 设置邮件的文本内容
            // 给消息对象设置内容
            BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
            
            mdp.setContent(txt, "text/html;charset=gb2312");// 给BodyPart对象设置内容和格式/编码方式
            
            multipart.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
            
            message.setContent(multipart);// 把multipart作为消息对象的内容
            
            message.saveChanges();
            
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            
            // 连接服务器的邮箱
            transport.connect(host, username, pwd);
            
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            
            transport.close();
        }
        catch (Exception e)
        {
            logger.error("邮件发送失败:", e);
        }
    }
}
