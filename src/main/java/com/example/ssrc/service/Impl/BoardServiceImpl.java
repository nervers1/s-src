package com.example.ssrc.service.Impl;

import com.example.ssrc.mapper.BoardMapper;
import com.example.ssrc.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardServiceImpl implements BoardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

    private BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LocalDateTime getTime() {
        LocalDateTime time = (LocalDateTime)mapper.getTime();
        LOGGER.info("시간이 찍히는지 봅시다 --> {}", time);
        return time;
    }
}
