package toy.project.delivery.userservice.user.adapter.in.web;

import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
class GetAllUsersMapper {

    public GetAllUsersDto mapToWebModel(List<User> userList) {
        return GetAllUsersDto.of(
            userList.stream()
                    .map(user -> UserDto.of(
                            user.getId(),
                            user.getEmail(),
                            user.getName(),
                            user.getEncryptedPwd()
                    ))
                    .collect(Collectors.toList())
        );
    }
}
