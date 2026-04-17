package by.morozmaksim.deepseekuserservice.web.mapper;

import by.morozmaksim.deepseekuserservice.domain.entity.User;
import by.morozmaksim.deepseekuserservice.web.dto.RequestUserDto;
import by.morozmaksim.deepseekuserservice.web.dto.ResponseUserDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestUserDtoToUser(RequestUserDto requestUserDto);
    ResponseUserDto userToResponseUserDto(User user);
    List<ResponseUserDto> usersToResponseUserDto(List<User> users);
}
