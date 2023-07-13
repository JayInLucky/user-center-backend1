package com.luo.usercenter.service.impl;



//public class OssServiceImpl implements OssService {
//    @Override
//    public String uploadFileAvatar(MultipartFile file) {
//
//        //工具类获取值
//        String endpoint = ConstantPropertiesUtil.END_POINT;
//        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
//        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
//        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
//
//        InputStream inputStream=null;
//
//        try {
//            //创建OSS实例
//            OSS ossClient= new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, bucketName);
//
//            //获取上传文件的输入流
//            inputStream = file.getInputStream();
//
//            //获取文件名称
//            String fileName = file.getOriginalFilename();
//            //添加随机值
//            String uuid= UUID.randomUUID().toString().replaceAll("-", "");
//            fileName = uuid + fileName;
//
//            //把文件按照日期分类
//            //获取当前日期
//            String datePath = new DateTime().toString("yyyy-MM-dd");
//            //拼接日期
//            fileName=datePath+"/"+fileName;
//
//            //调用oss实例中的方法实现上传
//            //参数1： Bucket 名称
//            //参数2： 上传到oss文件路径和文件名称  /aa/bb/1.jpg
//            ossClient.putObject(bucketName,fileName,inputStream);
//            //关闭ossClient
//            ossClient.shutdown();
//
//            //把上传后的文件路径返回
//            //需要把上传到阿里云OSS 路径手动拼接出来
//            //https://achang-edu.oss-cn-hangzhou.aliyuncs.com/default.gif
//            String url = "http://" + bucketName + "." + endpoint + "/" + fileName;
//
//            return url;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return  null;
//        }
//
//
//    }
//}
