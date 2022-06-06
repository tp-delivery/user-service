package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserUseCase;

@RestController
@RequiredArgsConstructor
public class RegisterUserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final RegisterUserMapper registerUserMapper;

    @PostMapping("/users")
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterUserDto registerUserDto
    ) {
        RegisterUserCommand registerUserCommand = registerUserMapper.mapToCommand(registerUserDto);

        registerUserUseCase.registerUser(registerUserCommand);

        return ResponseEntity.ok("success");
    }
}
