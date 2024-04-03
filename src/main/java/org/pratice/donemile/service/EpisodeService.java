package org.pratice.donemile.service;

import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.dto.EpisodeDTO;
import org.pratice.donemile.dto.PageRequestDTO;
import org.pratice.donemile.dto.PageResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EpisodeService {
    // 게시글 등록
    Long  saveEpisode(EpisodeDTO episodeDTO, List<MultipartFile> itemImgFile) throws Exception;
    // 게시글 리스트 출력
    PageResultDTO<EpisodeDTO, Episode> getList(PageRequestDTO requestDTO);
    // 게시글 상세보기
    Episode findByEpisode(Long episodeId);
    // 게시글 수정
    // 게시글 삭제

    // dto -> entity 변환
    default Episode dtoToEntity(EpisodeDTO episodeDTO) {
        Episode epi1 = Episode.builder()
                .title(episodeDTO.getTitle())
                .summary(episodeDTO.getSummary())
                .content(episodeDTO.getContent())
                .goalAccount(episodeDTO.getGoalAccount())
                .currentAccount(episodeDTO.getCurrentAccount())
                .donor(episodeDTO.getDonor())
                .boardStatus(episodeDTO.getBoardStatus())
                .build();
        return epi1;
    }

    default Episode entityToDto(Episode episode) {
        Episode epiDto = Episode.builder()
                .id(episode.getId())
                .title(episode.getTitle())
                .summary(episode.getSummary())
                .content(episode.getContent())
                .goalAccount(episode.getGoalAccount())
                .currentAccount(episode.getCurrentAccount())
                .boardStatus(episode.getBoardStatus())
                .build();
        return epiDto;
    }
}
