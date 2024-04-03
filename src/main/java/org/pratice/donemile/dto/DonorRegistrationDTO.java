package org.pratice.donemile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pratice.donemile.constant.Role;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonorRegistrationDTO {
    private String email;
    private String password;
    private String donorName;
    private String nickName;
    private String address;
    private LocalDate birth;
    private String phoneNum;
    private Role role;

}
