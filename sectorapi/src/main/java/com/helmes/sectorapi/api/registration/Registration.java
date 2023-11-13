package com.helmes.sectorapi.api.registration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration implements Serializable {
    @NotBlank(message = "User's name cannot be empty.")
    @Size(min = 1, max = 250)
    private String name;
    @NotEmpty
    private List<String> sectors;
    private boolean agreeToTerms;
}
