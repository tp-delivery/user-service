package toy.project.delivery.userservice.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.delivery.userservice.user.application.port.in.GetUserByIdUseCase;
import toy.project.delivery.userservice.user.application.port.out.LoadUserByIdPort;
import toy.project.delivery.userservice.user.domain.User;

@Service
@RequiredArgsConstructor
public class GetUserByIdService implements GetUserByIdUseCase {

    private final LoadUserByIdPort loadUserByIdPort;

    @Override
    public User getUserById(Long id) {
        return loadUserByIdPort.loadUserById(id);
    }
}
