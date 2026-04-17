package by.morozmaksim.deepseekuserservice.service;

import by.morozmaksim.deepseekuserservice.domain.entity.User;
import by.morozmaksim.deepseekuserservice.exception.ResourceNotFoundException;
import by.morozmaksim.deepseekuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;


    @Override
    @CachePut(value = "users", key = "#result.id")
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
    @CachePut(value = "users", key = "#id")
    public User update(Long id, User user) {
        User existUser = findById(id);
        if (user.getEmail() != null ) existUser.setEmail(user.getEmail());
        if (user.getUsername() != null) existUser.setUsername(user.getUsername());
        return userRepository.save(existUser);
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("User with username=" + username
                + " not found.");
        return user;
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User with id=" + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User getById(Long id) {
        System.out.println("sd");
        return findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    private User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id=" + id + " not found."));
    }
}
