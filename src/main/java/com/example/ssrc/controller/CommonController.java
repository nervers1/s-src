package com.example.ssrc.controller;

import com.example.ssrc.service.CommonService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
    public CommonController(CommonService common) {this.common = common;}
    private final CommonService common;

    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public Map<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        LOGGER.info("----------------- upload ");
        Map<String, Object> jsonObject = new HashMap<>();
        // /opt/code/projects/s-src/src/main/resources/templates/test.html
        // /opt/code/projects/s-src/src/main/resources/templates/test.html
        String fileRoot = "/upload/";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.put("url", "/summernoteImage/"+savedFileName);
            jsonObject.put("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.put("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }

    @RequestMapping("test")
    public String page() {
        return "template2/index";
    }

}
