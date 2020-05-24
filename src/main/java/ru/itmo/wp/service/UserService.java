package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Access;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.RegisterCredentials;
import ru.itmo.wp.repository.AccessRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    public UserService(UserRepository userRepository, AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }
    public User register(RegisterCredentials registerCredentials) {
        User user = new User();
        user.setLogin(registerCredentials.getLogin());
        user.setName(registerCredentials.getName());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerCredentials.getLogin(), registerCredentials.getPassword());
        return user;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
