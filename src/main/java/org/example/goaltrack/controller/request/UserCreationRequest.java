package org.example.goaltrack.controller.request;

import lombok.Getter;
import org.example.goaltrack.common.Gender;
import org.example.goaltrack.common.UserType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
public class UserCreationRequest implements Serializable {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String username;
    private String phone;
    private String email;
    private UserType type;
}
