package org.pratice.donemile.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
@Entity
//@SequenceGenerator(name = "myRecordSeq", sequenceName = "mile_seq", initialValue = 1, allocationSize = 1)
public class DonationRecord extends BaseEntity{
    @Id
    @Column(name="recom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("0")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id")
    private Episode episode;
}
