package org.pratice.donemile.service;

import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.dto.EpisodeDTO;
import org.pratice.donemile.dto.PageRequestDTO;
import org.pratice.donemile.dto.PageResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EpisodeService {
    // 게시글 등록
    Long saveEpisode(EpisodeDTO episodeDTO, List<MultipartFile> episodeImgList);
    // 게시글 리스트 출력
    // PageResultDTO<EpisodeDTO, Episode> getList(PageRequestDTO requestDTO);
    // 게시글 상세보기
    // 게시글 수정
    // 게시글 삭제

    // dto -> entity 변환
    default Episode dtoToEntity(EpisodeDTO episodeDTO) {
        Episode epi = Episode.builder()
                .title(episodeDTO.getTitle())
                .summary(episodeDTO.getSummary())
                .content(episodeDTO.getContent())
                .goalAccount(episodeDTO.getGoalAccount())
                .currentAccount(episodeDTO.getCurrentAccount())
                .build();
        return epi;
    }
}
