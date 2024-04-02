package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.EpisodeImg;
import org.pratice.donemile.repository.EpisodeImgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


@Service
@RequiredArgsConstructor
public class EpisodeImgServiceImpl implements EpisodeImgService{
    // 이미지 등록 시 필요 의존 주입 : 경로, 파일서비스(외부 폴더 파일 값 설정), 저장소(레퍼지토리)
    @Value("${episodeImgLocation}")
    private String episodeImgLocation;
    private final EpisodeImgRepository episodeImgRepository;
    private final FileService fileService;
    @Override

    public void imgSave(EpisodeImg episodeImg, MultipartFile episodeImgFile) throws Exception {
        // uploadFile(경로, 원본이미지 이름, 파일)
        String originImgName = episodeImgFile.getOriginalFilename();
        if(!StringUtils.isEmpty(originImgName)) {
            System.out.println("===========" +episodeImgLocation);
            System.out.println("===========" +episodeImg);
            System.out.println("===========" +episodeImgFile);
            String imgName = fileService.uploadFile(episodeImgLocation, originImgName,episodeImgFile.getBytes());
            String path = "/images/episode/" + imgName;
            episodeImg.updateImg(imgName, originImgName, path);
            episodeImgRepository.save(episodeImg);
        }
    }
}

