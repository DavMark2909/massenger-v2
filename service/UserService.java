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
        return userRepository.findUserByFullname(fullname).orElseThrow(() -> new NotFoundException(String.format("User %s wasn't found")));
    }
}
