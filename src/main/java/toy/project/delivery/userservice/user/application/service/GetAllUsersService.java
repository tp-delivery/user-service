package toy.project.delivery.userservice.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.delivery.userservice.user.application.port.in.GetAllUsersUseCase;
import toy.project.delivery.userservice.user.application.port.out.LoadAllUsersPort;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetAllUsersService implements GetAllUsersUseCase {

    private final LoadAllUsersPort loadAllUsersport;

    @Override
    public List<User> getAllUsers(int offset, int max) {
        return loadAllUsersport.loadAllUsers(offset, max);
    }
}
