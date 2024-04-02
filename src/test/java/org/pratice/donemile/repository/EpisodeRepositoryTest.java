package org.pratice.donemile.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pratice.donemile.constant.Role;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.domain.EpisodeImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DisplayName("에피소드 CRUD 테스트")
@SpringBootTest
public class EpisodeRepositoryTest {
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    EpisodeRepository episodeRepository;
    @Autowired
    EpisodeImgRepository episodeImgRepository;

    @DisplayName("등록 테스트 입니다.")
    @Test
    void GivenDonorDummy_whenInsertEpisode_thenRegisterEpisode() {
        // Given
        Donor donor = Donor.builder()
                .email("admin")
                .password("1234")
                .donorName("윤성준")
                .nickName("테스터")
                .address("우리집")
                .birth(LocalDate.now())
                .phoneNum("123")
                .role(Role.ADMIN)
                .build();
        System.out.println(donor);
        donorRepository.save(donor);

        // When & Then
        Episode episode = Episode.builder()
                .title("제목입니다")
                .summary("간략하게 설명한 글입니다")
                .content("본문 내용입니다.")
                .donor(donor)
                .episodeImg(new ArrayList<>())
                .build();
        System.out.println(episode.toString());
        episodeRepository.save(episode);
    }

    @DisplayName("게시글 리스트 테스트")
    @Transactional
    @Test
    void GivenNothing_whenEpisodesList_thenEpisodesList() {
        // Given
        // When & Then
        List<Episode> lists = episodeRepository.findAll();
        for (Episode e : lists) {
            System.out.println(e);
        }
    }

    @DisplayName("에피소드 제목 및 내용으로 찾기")
    @Transactional
    @Test
    void GivenEpisodeTitleOrContent_whenEpisodeFindbyId_thenReturnEpisode() {
        // Given
        String title = "제목";
        String content = "내용";
        // When & Then
        List<Episode> lists1 = episodeRepository.findByTitle(title);
        List<Episode> lists2 = episodeRepository.findByContent(content);

        for (Episode e : lists1) {
            System.out.println("제목으로 검색한 값입니다 : " + e.toString());
        }
        for (Episode e : lists2) {
            System.out.println("본문으로 검색한 값입니다 : " + e.toString());
        }
    }

    @DisplayName("에피소드 삭제 테스트")
    @Transactional
    @Test
    void GivenEpisodeId_whenFindByIdEpisode_thenDeleteEpisode() {
        Optional<Episode> result = episodeRepository.findById(3L);
        if (result.isPresent()) {
            Episode episode = result.get();
            episodeRepository.deleteById(episode.getId());
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }
    }

    @DisplayName("에피소드 내용 수정")
    @Transactional
    @Test
    void GivenEpisodeDummyData_whenUpdateEpisodeData_thenReturnEpisode() {
        // Given
        Optional<Episode> result = episodeRepository.findById(3L);
        if (result.isPresent()) {
            Episode episode = result.get();
            episode.setTitle("수정 게시글 제목");
            episode.setSummary("수정 간략설명");
            episode.setContent("수정 본문 내용");

            // When & Then
            episodeRepository.save(episode);
            System.out.println("수정 완료 : " + episode.toString());
        } else {
            System.out.println("수정 실패");
        }
    }
}
