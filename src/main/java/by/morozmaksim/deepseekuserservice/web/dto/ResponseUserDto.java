package by.morozmaksim.deepseekuserservice.web.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResponseUserDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
