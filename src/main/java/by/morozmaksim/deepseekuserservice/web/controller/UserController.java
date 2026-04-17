package by.morozmaksim.deepseekuserservice.web.controller;

import by.morozmaksim.deepseekuserservice.domain.entity.User;
import by.morozmaksim.deepseekuserservice.service.UserService;
import by.morozmaksim.deepseekuserservice.web.dto.RequestUserDto;
import by.morozmaksim.deepseekuserservice.web.dto.ResponseUserDto;
import by.morozmaksim.deepseekuserservice.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<ResponseUserDto> create(@Valid @RequestBody RequestUserDto requestUserDto) {
        User user = userMapper.requestUserDtoToUser(requestUserDto);
        User created = userService.create(user);
        ResponseUserDto responseUserDto = userMapper.userToResponseUserDto(user);
        URI location = URI.create("users/" + created.getId());
        return ResponseEntity.created(location).body(responseUserDto);
    }

    @PutMapping("/{id}")
    public ResponseUserDto update(@PathVariable Long id, @RequestBody RequestUserDto requestUserDto){
        User user = userMapper.requestUserDtoToUser(requestUserDto);
        User updated = userService.update(id, user);
        return userMapper.userToResponseUserDto(updated);
    }

    @GetMapping("/{id}")
    public ResponseUserDto getById(@PathVariable Long id) {
        return userMapper.userToResponseUserDto(userService.getById(id));
    }

    @GetMapping("/username")
    public ResponseUserDto getByUsername(@RequestParam String username) {
        return userMapper.userToResponseUserDto(userService.getByUsername(username));
    }

    @GetMapping
    public List<ResponseUserDto> getAll() {
        return userMapper.usersToResponseUserDto(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
