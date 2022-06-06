package toy.project.delivery.userservice.user.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserUseCase;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegisterUserController.class)
class RegisterUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegisterUserUseCase registerUserUseCase;
    @MockBean
    private RegisterUserMapper registerUserMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUser() throws Exception {
        String email = "email@gmail.com";
        String password = "password";
        String name = "name";

        RegisterUserDto registerUserDto = RegisterUserDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(email, password, name);

        given(registerUserMapper.mapToCommand(registerUserDto)).willReturn(registerUserCommand);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserDto))
                ).andExpect(status().isOk())
                .andExpect(content().string("success"));

        then(registerUserMapper).should()
                .mapToCommand(eq(registerUserDto));

        then(registerUserUseCase).should()
                .registerUser(eq(registerUserCommand));
    }
}