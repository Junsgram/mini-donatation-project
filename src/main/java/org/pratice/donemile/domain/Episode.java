package org.pratice.donemile.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.pratice.donemile.constant.BoardStatus;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString
@Builder
@Entity
//@SequenceGenerator(name="myEpisodeSeq", sequenceName = "epi_seq", initialValue = 1, allocationSize = 1)
public class Episode extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="episode_id")
    private Long id;

    @Column(length = 100, nullable = false) private String title;
    @Column(length = 500, nullable = false) private String summary;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false) @ColumnDefault("0") private int goalAccount;
    @Column(nullable = false) @ColumnDefault("0") private int currentAccount;
    @Enumerated(EnumType.STRING) private BoardStatus boardStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="img_id")
    private List<EpisodeImg> episodeImg;


}
