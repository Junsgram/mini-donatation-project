package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.EpisodeImg;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeImgServiceImpl implements EpisodeImgService{
    // 이미지 등록 시 필요 의존 주입 : 경로, 파일서비스(외부 폴더 파일 값 설정), 저장소(레퍼지토리)

    @Override
    public void Imgsave(EpisodeImg episodeImg, List<MultipartFile> episodeImgFile) {
        // 이미지 등록
        //
    }
}
