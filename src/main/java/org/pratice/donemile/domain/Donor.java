package org.pratice.donemile.domain;


import jakarta.persistence.*;
import lombok.*;
import org.pratice.donemile.constant.Role;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = true)
    private String donorName;

    @Column(length = 30, nullable = true)
    private String nickName;

    @Column(length = 100)
    private String address;

    @Column(nullable = true)
    private LocalDate birth;

    @Column(length = 30, nullable = true)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean fromSocial;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mile_id")
    private MileageAccount miles;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="recommend_id")
    private List<DonationRecord> donationRecord;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Role> roleSet = new HashSet<>();

    public void addRole(Role role){
        roleSet.add(role);
    }
}
