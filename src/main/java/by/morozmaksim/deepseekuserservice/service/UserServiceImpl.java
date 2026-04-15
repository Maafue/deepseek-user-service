package by.morozmaksim.deepseekuserservice.service;

import by.morozmaksim.deepseekuserservice.domain.User;
import by.morozmaksim.deepseekuserservice.exception.ResourceNotFoundException;
import by.morozmaksim.deepseekuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("User already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("Email already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("User with username=" + username
                + " not found.");
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id=" + id + " not found."));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
