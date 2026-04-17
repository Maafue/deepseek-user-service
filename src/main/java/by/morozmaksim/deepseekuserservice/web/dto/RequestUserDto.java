package by.morozmaksim.deepseekuserservice.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestUserDto {

    @NotBlank(message = "Username must be not blank.")
    private String username;

    @NotBlank(message = "Email must be not blank.")
    private String email;

}
