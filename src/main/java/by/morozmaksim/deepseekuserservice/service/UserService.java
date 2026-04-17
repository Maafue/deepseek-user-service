package by.morozmaksim.deepseekuserservice.service;

import by.morozmaksim.deepseekuserservice.domain.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
    User getById(Long id);
    User getByUsername(String username);
    List<User> getAll();
}
