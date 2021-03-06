package com.example.ssrc.controller;

import com.example.ssrc.domain.Post;
import com.example.ssrc.service.BoardService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class BoardRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService board;
    private final String uploadImagePath;

    public BoardRestController(BoardService board, @Value("${custom.path.upload-images}") String uploadImagePath) {
        this.board = board;
        this.uploadImagePath = uploadImagePath;
    }

    @GetMapping(path = "/posts")
    public List<Post> list(Post post) {
        return board.list(post);
    }

    @GetMapping(path = "/post/{postNo}")
    public Post get(@PathVariable String postNo, Post post) {
        LOGGER.info("postNo ----{}", postNo);
        post.setPostSeriNo(postNo);
        return board.getPost(post);
    }

    @PostMapping(path = "/post")
    public Map<String, Object> create(Post post) {
        Map<String, Object> resultMap = new HashMap<>();

        int result = board.createPost(post);
        LOGGER.info("result : {}", result);

        if (result > 0) {
            resultMap.put("message", "Posting success...");
        } else {
            resultMap.put("message", "Error...");
        }
        return resultMap;
    }


    @PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
    public ResponseEntity<Map<String, Object>> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        LOGGER.info("----------------- upload ");
        Map<String, Object> jsonObject = new HashMap<>();
        String originalFileName = multipartFile.getOriginalFilename();    //???????????? ?????????
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //?????? ?????????
        String savedFileName = UUID.randomUUID() + extension;    //????????? ?????? ???


        File targetFile = new File(uploadImagePath + savedFileName);
        try (InputStream fileStream = multipartFile.getInputStream()) {
            // ?????? ????????? ???????????? Database??? ????????? ??????.
            LOGGER.info("getOriginalFilename : {}", originalFileName);
            LOGGER.info("extension : {}", extension);
            LOGGER.info("targetFile : {}", targetFile);
            LOGGER.info("savedFileName : {}", savedFileName);

            FileUtils.copyInputStreamToFile(fileStream, targetFile);    //?????? ??????
            jsonObject.put("url", "/upload/" + savedFileName);
            jsonObject.put("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);    //????????? ?????? ??????
            jsonObject.put("responseCode", "error");
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(jsonObject);
    }

}
