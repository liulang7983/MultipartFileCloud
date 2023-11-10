package com.zuul.faillback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author ming.li
 * @date 2023/11/9 22:12
 */
@Component
public class MyfallBack  implements FallbackProvider {
    /*返回fallback处理哪一个服务，返回的是服务名
    为指定的服务定义特性化的fallback逻辑
    如果服务指定了就走指定的，没指定就走默认的
    * */
    @Override
    public String getRoute() {
        return "conaumer-serving";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return  new ClientHttpResponse() {
            //响应码
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            //int类型响应码
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }
            //string类型响应码
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }
            //需要关闭的资源
            @Override
            public void close() {

            }
            //响应体
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("{\"mesg\":\"哈哈\"}".getBytes(StandardCharsets.UTF_8));
            }
            //响应头
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=new HttpHeaders();
                headers.setContentType(new MediaType("application","json", Charset.forName("utf-8")));
                return headers;
            }
        };
    }
}
