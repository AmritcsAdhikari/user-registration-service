package com.app.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("firstname")
    @NotBlank(message = "First cannot be Empty.")
    @NotNull(message = "First cannot be Null.")
    private String firstName;

    @JsonProperty("lastname")
    @NotBlank(message = "Last cannot be Empty.")
    @NotNull(message = "Last cannot be Null.")
    private String lastName;

    @NotBlank(message = "Email cannot be Empty.")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email doesn't meet pattern requirement.")
    private String email;

    @NotBlank(message = "Phone cannot be Empty.")
    @NotNull(message = "Phone cannot be Null.")
    @Min(value = 9, message = "Phone Number cannot be less than 10 digits.")
    private String phone;
}
