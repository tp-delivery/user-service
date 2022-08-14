package toy.project.delivery.userservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.project.delivery.userservice.auth.exception.AlreadyExistsEmailException;
import toy.project.delivery.userservice.user.domain.User;
import toy.project.delivery.userservice.user.repository.UserRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsEmailException("already exist email");
        }
        User user = User.of(email, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
