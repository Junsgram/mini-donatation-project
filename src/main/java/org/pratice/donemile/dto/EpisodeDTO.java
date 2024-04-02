package org.pratice.donemile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pratice.donemile.constant.BoardStatus;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.domain.EpisodeImg;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class EpisodeDTO {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private int goalAccount;
    private int currentAccount;
    private BoardStatus boardStatus;
    private Donor donor;
    private List<EpisodeImg> episodeImg = new ArrayList<>();
    private LocalDateTime createdAt;
    private String createdBy;


    @Builder
    public EpisodeDTO(Long id, String title, String summary, String content, int goalAccount,
                      int currentAccount, BoardStatus boardStatus, Donor donor, List<EpisodeImg> episodeImg,
                      LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.goalAccount = goalAccount;
        this.currentAccount = currentAccount;
        this.boardStatus = boardStatus;
        this.donor = donor;
        this.episodeImg = episodeImg;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

}
