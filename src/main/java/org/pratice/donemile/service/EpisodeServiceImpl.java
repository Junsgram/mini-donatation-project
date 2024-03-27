package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.domain.EpisodeImg;
import org.pratice.donemile.dto.EpisodeDTO;
import org.pratice.donemile.dto.PageRequestDTO;
import org.pratice.donemile.dto.PageResultDTO;
import org.pratice.donemile.repository.EpisodeImgRepository;
import org.pratice.donemile.repository.EpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final EpisodeImgService episodeImgService;
    @Override
    public Long saveEpisode(EpisodeDTO episodeDTO, List<MultipartFile> episodeImgList) {
        Episode episode = dtoToEntity(episodeDTO);
        episodeRepository.save(episode);
        // 이미지 donor 등록
        for (int i=0; i<episodeImgList.size(); i++) {
            EpisodeImg episodeImg = new EpisodeImg();
            episodeImg.setEpisode(episode);
            if(i==0) {
                episodeImg.setRepimgYn("Y");
            }else {
                episodeImg.setRepimgYn("n");
            }
        }
    }
}
