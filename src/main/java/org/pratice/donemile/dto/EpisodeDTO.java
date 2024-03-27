package org.pratice.donemile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pratice.donemile.constant.BoardStatus;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.domain.EpisodeImg;

import java.util.List;

@Data
@AllArgsConstructor
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
    private List<EpisodeImg> episodeImg;
}
