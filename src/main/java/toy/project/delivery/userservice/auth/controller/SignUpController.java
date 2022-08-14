package toy.project.delivery.userservice.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.project.delivery.userservice.auth.dto.SignUpDto;
import toy.project.delivery.userservice.auth.service.SignUpService;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(
            @RequestBody SignUpDto.Request request
    ) {
        String email = request.getEmail();
        String password = request.getPassword();
        signUpService.signUp(email, password);

        return ResponseEntity.ok("success");
    }
}
