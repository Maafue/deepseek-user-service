package by.morozmaksim.deepseekuserservice.web.controller;

import by.morozmaksim.deepseekuserservice.domain.entity.User;
import by.morozmaksim.deepseekuserservice.service.UserService;
import by.morozmaksim.deepseekuserservice.web.dto.RequestUserDto;
import by.morozmaksim.deepseekuserservice.web.dto.ResponseUserDto;
import by.morozmaksim.deepseekuserservice.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Управление пользователями")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @Operation(summary = "Создать пользователя", description = "Создаёт нового пользователя.")
    @ApiResponse(responseCode = "201", description = "Пользователь создан")
    public ResponseEntity<ResponseUserDto> create(@Valid @RequestBody RequestUserDto requestUserDto) {
        User user = userMapper.requestUserDtoToUser(requestUserDto);
        User created = userService.create(user);
        ResponseUserDto responseUserDto = userMapper.userToResponseUserDto(user);
        URI location = URI.create("users/" + created.getId());
        return ResponseEntity.created(location).body(responseUserDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя", description = "Обновляет существующего пользователя.")
    @ApiResponse(responseCode = "200", description = "Пользователь обновлен")
    public ResponseUserDto update(@PathVariable Long id, @RequestBody RequestUserDto requestUserDto){
        User user = userMapper.requestUserDtoToUser(requestUserDto);
        User updated = userService.update(id, user);
        return userMapper.userToResponseUserDto(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id", description = "Получает пользователя по id.")
    @ApiResponse(responseCode = "200", description = "Пользователь получен")
    public ResponseUserDto getById(@PathVariable Long id) {
        return userMapper.userToResponseUserDto(userService.getById(id));
    }

    @GetMapping("/username")
    @Operation(summary = "Получение пользователя по username", description = "Получает пользователя по username.")
    @ApiResponse(responseCode = "200", description = "Пользователь получен")
    public ResponseUserDto getByUsername(@RequestParam String username) {
        return userMapper.userToResponseUserDto(userService.getByUsername(username));
    }

    @GetMapping
    @Operation(summary = "Получить всех пользователей", description = "Получает список всех пользователей.")
    @ApiResponse(responseCode = "200", description = "Пользователь получен")
    public List<ResponseUserDto> getAll() {
        return userMapper.usersToResponseUserDto(userService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя", description = "удаляет пользователя по id.")
    @ApiResponse(responseCode = "204", description = "Пользователь удален")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
