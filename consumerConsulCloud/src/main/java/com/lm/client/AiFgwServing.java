package com.lm.client;



import com.alibaba.fastjson.JSONPObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author ming.li
 * @date 2023/9/1 11:30
 */
@FeignClient(name = "ai-fgw-serving")
public interface AiFgwServing {
    /**
     * 导出
     */
    @PostMapping(value = "hex/ocrapi/recvfrocr")
    JSONPObject recvfrocr(@RequestBody Map<String, String> paramMap) ;
}
