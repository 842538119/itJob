package com.itJob.controller;

import com.itJob.bean.Vo.Result;
import com.itJob.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 文件上传controller 类
 * @Author: LRJ
 * @Date: 2019/12/30 18:09
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private FtpUtil ftpUtil;
    Result result=new Result();
    @RequestMapping("/faceImg")
    public String uploadFaceImg(@RequestParam("file") MultipartFile[] files){
        //获取原始文件名
        MultipartFile uploadFile=files[0];
        String oldName = uploadFile.getOriginalFilename();
        //使用FTPUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
        String newName=ftpUtil.getImageName();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        boolean flag=false;
        //获取上传的io流
        try {
            InputStream input = uploadFile.getInputStream();
             flag = ftpUtil.fileUpload(newName,uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(flag){
            //设置服务器头像url路径
            String url="http://"+ftpUtil.hostname+"/"+newName;
            return url;
        }
        return null;
    }
}
