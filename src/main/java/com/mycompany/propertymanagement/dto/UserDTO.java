package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private long id;
    private String ownerName;
    @NotNull(message = "Email cannot be null")
    @Size(min = 1, max = 50, message = "Owner Email should be 1 to 50 char")
    @NotEmpty(message = "Email cannot be empty")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Passoword cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
