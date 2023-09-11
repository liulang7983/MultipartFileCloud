package com.lm.controller;

import com.lm.client.ProducerServing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 李明
 * @date Created in 2023/9/1 0:02
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private ProducerServing producerServing;
    @PostMapping(value = "/asyncUploadFiles",consumes = MediaType.MULTIPART_FORM_DATA_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    String asyncUploadFiles(@RequestPart("files") MultipartFile[] files, @RequestParam("fsUuid")String fsUuid){
        String s = producerServing.asyncUploadFiles(files, fsUuid);
        System.out.println(fsUuid);
        return "成功";
    }
}
