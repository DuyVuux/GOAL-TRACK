package org.example.goaltrack.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.example.goaltrack.common.Gender;
import org.example.goaltrack.common.UserType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;

    @NotBlank(message = "lastName must be not blank")
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String username;
    private String phone;

    @Email(message = "Email invalid")
    private String email;
    private UserType type;
}
