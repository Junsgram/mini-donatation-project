package org.pratice.donemile.service;

import org.pratice.donemile.domain.EpisodeImg;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EpisodeImgService {
    // 이미지 등록
    void imgSave(EpisodeImg episodeImg, MultipartFile episodeImgFile) throws Exception;
    // 이미지 리스트

}
