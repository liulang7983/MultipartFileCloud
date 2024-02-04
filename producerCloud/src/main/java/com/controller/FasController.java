package com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 李明
 * @date Created in 2023/8/31 23:58
 */
@RestController
@RequestMapping("fas")
public class FasController {
    @PostMapping(value = "/asyncUploadFiles",consumes = MediaType.MULTIPART_FORM_DATA_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    String asyncUploadFiles(@RequestPart("files") MultipartFile[] files, @RequestParam("fsUuid")String fsUuid){
        System.out.println(fsUuid);
        System.out.println(files.length);
        String path="C:\\Users\\14307\\Desktop\\日志\\MultipartFileCloud\\";
        for (int i = 0; i < files.length; i++) {
            try {
                MultipartFile file = files[i];
                String filename = file.getOriginalFilename();
                String filePath=path+filename;
                file.transferTo(new File(filePath));
                System.out.println(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "成功";
    }
}
