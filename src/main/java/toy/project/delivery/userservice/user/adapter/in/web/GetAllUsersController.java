package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.project.delivery.userservice.user.application.port.in.GetAllUsersUseCase;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetAllUsersController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UserWebModelMapper userWebModelMapper;

    @GetMapping("/users")
    public ResponseEntity<AllUsersDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "10") int max
    ) {
        List<User> users = getAllUsersUseCase.getAllUsers(offset, max);
        AllUsersDto allUsersDto = userWebModelMapper.mapToWebModel(users);

        return ResponseEntity.ok(allUsersDto);
    }
}
