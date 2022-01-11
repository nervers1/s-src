package com.example.ssrc.controller;

import com.example.ssrc.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
    public BoardController(BoardService common) {this.common = common;}
    private final BoardService common;

    @RequestMapping("time")
    public String getTime(Model model) {
        LocalDateTime time = common.getTime();
        LOGGER.info("time --> {}", time);
        model.addAttribute("time", time);
        return "time";
    }


    @RequestMapping("test")
    public String page() {
        return "template2/index";
    }

}
