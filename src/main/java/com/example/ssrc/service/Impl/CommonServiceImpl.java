package com.example.ssrc.service.Impl;

import com.example.ssrc.mapper.CommonMapper;
import com.example.ssrc.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

    private final CommonMapper mapper;

    public CommonServiceImpl(CommonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LocalDateTime getTime() {
        LocalDateTime time = mapper.getTime();
        LOGGER.info("시간이 찍히는지 봅시다 --> {}", time);
        return time;
    }

    @Override
    public void copyFile(String source, String target) {

        int i;
        Date d = new Date();
        long start = d.getTime();

        try (
                // BufferedInputStream 객체를 생성한다.
                FileInputStream fis = new FileInputStream(source);
                BufferedInputStream bis = new BufferedInputStream(fis);

                // BufferedOutputStream 객체를 생성한다.
                FileOutputStream fos = new FileOutputStream(target);
                BufferedOutputStream bos = new BufferedOutputStream(fos)

        ) {
            // 1바이트씩 읽어서 버퍼에 담는다.
            while ((i = bis.read()) != -1) {

                // 1바이트씩 출력버퍼에 담는다.
                bos.write(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        d = new Date();
        long end = d.getTime();
        System.out.println("복사 시간 : " + (end - start));
    }


}
