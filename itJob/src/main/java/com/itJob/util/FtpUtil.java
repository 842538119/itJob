package com.itJob.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;
import java.util.Random;


/**
 * @Description: FTP文件服务器 类
 * @Author: LRJ
 * @Date: 2019/12/30 17:25
 */
@Component
public class FtpUtil {
    //ftp服务器地址
    @Value("${ftp.server}")
    public String hostname;

    //ftp服务器端口
    @Value("${ftp.port}")
    private int port;

    //ftp登录账号
    @Value("${ftp.username}")
    private String username;

    //ftp登录密码
    @Value("${ftp.password}")
    private String password;

    //ftp保存目录
    @Value("${ftp.basePath}")
    private String basePath;

    public boolean fileUpload(String filename,MultipartFile multipartFile) throws IOException {
//      添加 ip、端口、用户名、密码
        FTPClient ftp = this.getConnectionFTP();
//      调用上传接口 传入ftp对象、ftp存放的位置、文件名、文件流
       boolean flag = this.uploadFile(ftp,filename, multipartFile.getInputStream());
//      断开ftp服务器
        this.closeFTP(ftp);
        return flag;
    }
    /**
     * 获得FTP连接方式
     */
    public FTPClient getConnectionFTP() {
        //创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try {
            //连接FTP服务器
            ftp.connect(hostname, port);

            //下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            //登录ftp
            ftp.login(username, password);

            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 关闭连接FTP方式
     * @param ftp FTPClient对象
     * @return boolean
     */
    public boolean closeFTP(FTPClient ftp) {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 上传文件到FTP的方式
     * @param ftp FTPClient对象
     * @param filename 文件名
     * @param inputStream 文件流
     * @return boolean
     */
    public boolean uploadFile(FTPClient ftp,String filename, InputStream inputStream) {

        try {
//          让客户端告诉服务端开通一个端口用来数据传输（必须要 不然会一直卡死）
            ftp.enterLocalPassiveMode();
                //获取文件存放的目录
            ftp.changeWorkingDirectory(basePath);
//          获取文件目录所有的文件
            FTPFile[] fs = ftp.listFiles();
//          如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
//          开始上传
            boolean boo  = ftp.storeFile(filename, inputStream);
            if (boo){
                return true;
            }
            //关闭输入流
            inputStream.close();
            //退出ftp
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件FTP方式
     * @param ftp FTPClient对象
     * @param fileName FTP服务器上要删除的文件名
     * @return
     */
    public boolean deleteFile(FTPClient ftp, String fileName) {
        boolean success = false;
        try {
//          让客户端告诉服务端开通一个端口用来数据传输（必须要 不然会一直卡死）
            ftp.enterLocalPassiveMode();
//          获取文件存放的目录
            ftp.changeWorkingDirectory(basePath);
//          开始删除
            success = ftp.deleteFile(fileName);
//          退出登录
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    /**
     * 根据文件名获取文件流
     * @param ftp
     * @param filename
     * @return
     */
    public InputStream downFile(FTPClient ftp,String filename) {
//      让客户端告诉服务端开通一个端口用来数据传输（必须要 不然会一直卡死）
        ftp.enterLocalPassiveMode();
        try {
//          获取文件存放的目录
            ftp.changeWorkingDirectory(basePath);
//          获取指定目录下的所有文件
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(filename)) {
                    return ftp.retrieveFileStream(ff.getName());
                }
            }
//          退出登录
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 创建目录(有则切换目录，没有则创建目录)
     * @param dir
     * @return
     */
    public boolean createDir(FTPClient ftp,String dir) {
        String d;
        try {
            //目录编码，解决中文路径问题
            d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
            //尝试切入目录
            if (ftp.changeWorkingDirectory(d))
                return true;
            String[] arr = dir.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录
            for (String s : arr) {
                sbfDir.append("/");
                sbfDir.append(s);
                //目录编码，解决中文路径问题
                d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                //尝试切入目录
                if (ftp.changeWorkingDirectory(d))
                    continue;
                if (!ftp.makeDirectory(d)) {
                    System.out.println("[失败]ftp创建目录：" + sbfDir.toString());
                    return false;
                }
                System.out.println("[成功]创建ftp目录：" + sbfDir.toString());
            }
            //将目录切换至指定路径
            return ftp.changeWorkingDirectory(d);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }
}
