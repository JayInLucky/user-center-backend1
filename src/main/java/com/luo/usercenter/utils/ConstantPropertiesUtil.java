package com.luo.usercenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author jj
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = this.keyid;
        KEY_SECRET = this.keysecret;
        END_POINT = this.endpoint;
        BUCKET_NAME = this.bucketname;
    }
}
