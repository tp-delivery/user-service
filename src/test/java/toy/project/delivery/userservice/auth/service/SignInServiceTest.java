package toy.project.delivery.userservice.auth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import toy.project.delivery.userservice.jwt.JwtTokenProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class SignInServiceTest {
    private SignInService signInService;
    private AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
    private JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);

    @BeforeEach
    void setup() {
        this.signInService = new SignInService(authenticationManager, jwtTokenProvider);
    }

    @Test
    void signIn() {
        String email = "email";
        String password = "password";
        String expectedToken = "expectedToken";
        Authentication authentication = givenAuthentication();
        given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .willReturn(authentication);
        given(jwtTokenProvider.generateToken(authentication))
                .willReturn(expectedToken);

        String token = signInService.signIn(email, password);
        assertThat(token).isEqualTo(expectedToken);

        then(authenticationManager).should().authenticate(new UsernamePasswordAuthenticationToken(email, password));
        then(jwtTokenProvider).should().generateToken(authentication);
    }

    private Authentication givenAuthentication() {
        return Mockito.mock(Authentication.class);
    }
}