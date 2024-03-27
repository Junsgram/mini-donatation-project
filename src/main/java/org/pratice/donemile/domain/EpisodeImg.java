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

    @Setter private String uuid;
    @Setter private String imgName;
    @Setter private String path;
    @Setter private String repimgYn;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id")
    private Episode episode;
}
