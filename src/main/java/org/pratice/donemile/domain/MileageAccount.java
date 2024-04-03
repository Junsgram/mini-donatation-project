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
//@SequenceGenerator(name = "myAccountSeq", sequenceName = "mile_seq", initialValue = 1, allocationSize = 1)
public class MileageAccount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mile_id")
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;

}
