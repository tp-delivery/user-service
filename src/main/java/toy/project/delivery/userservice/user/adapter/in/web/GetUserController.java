package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toy.project.delivery.userservice.user.application.port.in.GetUserByIdUseCase;
import toy.project.delivery.userservice.user.domain.User;

@RestController
@RequiredArgsConstructor
public class GetUserController {
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UserWebModelMapper userWebModelMapper;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id
    ) {
        User user = getUserByIdUseCase.getUserById(id);
        UserDto userDto = userWebModelMapper.mapToWebModel(user);

        return ResponseEntity.ok(userDto);
    }
}
