package com.example.ssrc.service;

import java.time.LocalDateTime;

public interface CommonService {
    LocalDateTime getTime();


    void copyFile(String source, String target);
}
