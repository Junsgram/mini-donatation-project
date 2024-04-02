package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.domain.EpisodeImg;
import org.pratice.donemile.dto.EpisodeDTO;
import org.pratice.donemile.repository.EpisodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final EpisodeImgService episodeImgService;
    @Override
    public Long saveEpisode(EpisodeDTO episodeDTO, List<MultipartFile> itemImgFile) throws Exception {
        Episode episode = dtoToEntity(episodeDTO);
        System.out.println("에피소드 : " + episodeDTO.getEpisodeImg().toString());
        episodeRepository.save(episode);

        // 이미지 donor 등록
        for (int i=0; i<itemImgFile.size(); i++) {
            EpisodeImg episodeImg = new EpisodeImg();
            episodeImg.setEpisode(episode);
            if(i==0) {
                episodeImg.setRepimgYn("Y");
            }else {
                episodeImg.setRepimgYn("N");
            }
            episodeImgService.imgSave(episodeImg,itemImgFile.get(i));
        }
        return episode.getId();
    }
    // 에피소드 리스트 출력
    @Override
    public PageResultDTO<EpisodeDTO, Episode> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Episode>  result = episodeRepository.findAll(pageable);
        // Function<Episode, EpisodeDTO> fn = (entity -> entityToDto(entity));
        return null;
    }

    // 상세보기
    @Override
    public Episode findByEpisode(Long episodeId) {
        Episode episode = episodeRepository.findById(episodeId).get();
        return episode;
    }
}
