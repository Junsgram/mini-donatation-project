package org.pratice.donemile.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
@Entity
//@SequenceGenerator(name = "myImgSeq", sequenceName = "mile_seq", initialValue = 1, allocationSize = 1)
public class EpisodeImg extends BaseEntity{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="img_id")
    private Long id;

    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id")
    private Episode episode;
}
