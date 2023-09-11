package com.lm.client;

import com.lm.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 李明
 * @date Created in 2023/8/31 23:52
 */
@FeignClient(name = "producerCloud",configuration = FeignMultipartSupportConfig.class)
public interface ProducerServing {

    @PostMapping(value = "/fas/asyncUploadFiles",consumes = MediaType.MULTIPART_FORM_DATA_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    String asyncUploadFiles(@RequestPart("files") MultipartFile[] files, @RequestParam("fsUuid")String fsUuid);
}
