package org.example.goaltrack.controller.request;

import lombok.Getter;
import lombok.ToString;
import org.example.goaltrack.common.Gender;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String username;
    private String phone;
    private String email;
}
