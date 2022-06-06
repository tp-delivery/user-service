package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
class GetAllUsersDto {
    List<UserDto> users;
}
