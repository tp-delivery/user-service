package toy.project.delivery.userservice.auth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import toy.project.delivery.userservice.auth.exception.AlreadyExistsEmailException;
import toy.project.delivery.userservice.user.domain.User;
import toy.project.delivery.userservice.user.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class SignUpServiceTest {
    private SignUpService signUpService;
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    @BeforeEach
    void setup() {
        this.signUpService = new SignUpService(userRepository, passwordEncoder);
    }

    @Test
    void signUp() {
        String email = "email";
        String password = "password";
        String encodedPassword = "encodedPassword";
        given(userRepository.existsByEmail(email)).willReturn(false);
        given(passwordEncoder.encode(password)).willReturn(encodedPassword);
        signUpService.signUp(email, password);
        then(userRepository).should().save(any(User.class));
    }

    @Test
    void signUp_AlreadyExistsEmailException() {
        String email = "email";
        String password = "password";
        given(userRepository.existsByEmail(email))
                .willReturn(true);
        assertThatThrownBy(() -> signUpService.signUp(email, password))
                .isInstanceOf(AlreadyExistsEmailException.class);

        then(userRepository).should().existsByEmail(email);
    }
}