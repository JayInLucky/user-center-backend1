package com.luo.usercenter.controller;


import com.aliyun.oss.OSS;
import com.luo.usercenter.common.BaseResponse;
import com.luo.usercenter.common.ResultUtils;
import com.luo.usercenter.service.OssService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author jj
 */
@RestController
@RequestMapping("/fileOss")
public class OssController {

    @Resource
    private OssService ossService;

    public BaseResponse<String> uploadOssFile(@RequestParam(required = false)MultipartFile file){
        //获取上传的文件
        if (file.isEmpty()){
            return null;
        }
        //返回上传到Oss的路径
        String url = ossService.uploadFileAvatar(file);
        //返回r对象
        return ResultUtils.success(url);
    }

}
