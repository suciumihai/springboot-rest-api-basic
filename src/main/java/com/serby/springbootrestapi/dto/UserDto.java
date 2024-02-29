package com.serby.springbootrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//scehma pt swagger
@Schema(
        description = "user dto model infrmation"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @Schema(description = "user firstname")
    @NotEmpty(message = "first name nu merge")
    private String firstName;

    @Schema(description = "user lastname")
    @NotEmpty(message = "last  name nu e niull sau empty")
    private String lastName;

    @Schema(description = "user email")
    @NotEmpty(message = "email nu e niull sau empty")
    @Email(message = "email tre sa fie email, ce , esti bou?")
    private String email;
}
