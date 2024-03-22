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
@SequenceGenerator(name = "myRecommendationSeq", sequenceName = "recommend_seq", initialValue = 1, allocationSize = 1)
public class Recommendation extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "myRecommendationSeq")
    @Column(name="recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id")
    private Episode episode;
}
