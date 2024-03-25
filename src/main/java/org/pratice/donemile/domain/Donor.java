package org.pratice.donemile.domain;


import jakarta.persistence.*;
import lombok.*;
import org.pratice.donemile.constant.Role;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "donor")
@Entity
//@SequenceGenerator(name = "donor_seq_generator", sequenceName = "donor_seq", initialValue = 1, allocationSize = 1)
public class Donor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="donor_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String donorName;

    @Column(length = 30, nullable = false)
    private String nickName;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 30, nullable = false)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mile_id")
    private MileageAccount miles;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="recommend_id")
    private List<DonationRecord> donationRecord;

}
