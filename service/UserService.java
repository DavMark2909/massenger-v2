package application.service;

import application.entity.User;
import application.exception.type.NotFoundException;
import application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserByFullname(String fullname) throws NotFoundException {
        return userRepository.findByFullname(fullname).orElseThrow(() -> new NotFoundException(String.format("User %s wasn't found", fullname)));
    }

    public User findUserByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(String.format("User with username %s doesn't exist", username)));
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
