package com.lm.config;

import static feign.form.ContentType.MULTIPART;
import static java.util.Collections.singletonMap;

import java.lang.reflect.Type;
import java.util.HashMap;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;

import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author 李明
 * @date Created in 2023/9/1 0:05
 */
public class FeignMultipartSupportConfig {
    @Bean
    public Encoder springFormEncoder(ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory) {
        // 避免实体作为参数无法接收
        SpringEncoder springEncoder = new SpringEncoder(messageConvertersObjectFactory);
        return new SpringMultipartEncoder(springEncoder);
    }
}
