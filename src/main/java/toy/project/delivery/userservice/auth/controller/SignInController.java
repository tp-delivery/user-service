package toy.project.delivery.userservice.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.project.delivery.userservice.auth.dto.SignInDto;
import toy.project.delivery.userservice.auth.service.SignInService;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(
            @RequestBody SignInDto.Request request
    ) {
        String email = request.getEmail();
        String password = request.getPassword();
        String accessToken = signInService.signIn(email, password);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-AUTH-TOKEN", accessToken);

        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }
}
