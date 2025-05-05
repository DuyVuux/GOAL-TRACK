package org.example.goaltrack.controller.response;

import lombok.*;
import org.example.goaltrack.common.Gender;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String username;
    private String phone;
    private String email;
}
