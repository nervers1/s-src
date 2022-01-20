package com.example.ssrc.controller;

import com.example.ssrc.domain.Post;
import com.example.ssrc.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService board;

    public BoardRestController(BoardService board) {
        this.board = board;
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
}
