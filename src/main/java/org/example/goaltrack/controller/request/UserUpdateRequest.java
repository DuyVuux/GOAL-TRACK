package org.example.goaltrack.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.example.goaltrack.common.Gender;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    @NotNull(message = "id must be not blank")
    private Long id;

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
}
