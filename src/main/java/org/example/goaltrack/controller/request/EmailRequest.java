package org.example.goaltrack.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequest {
    @NotBlank
    private String to;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;
}
