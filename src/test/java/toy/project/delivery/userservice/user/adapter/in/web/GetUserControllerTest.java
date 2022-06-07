package toy.project.delivery.userservice.user.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import toy.project.delivery.userservice.security.SecurityConfiguration;
import toy.project.delivery.userservice.user.application.port.in.GetUserByIdUseCase;
import toy.project.delivery.userservice.user.domain.User;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(controllers = GetUserController.class)
class GetUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetUserByIdUseCase getUserByIdUseCase;
    @MockBean
    private UserWebModelMapper userWebModelMapper;

    @Test
    void getUserById() throws Exception {
        Long id = 1L;
        User user = givenUser(id);

        mockMvc.perform(get("/users/{id}", id)
                .header("Content-Type", "application/json")
        ).andExpect(status().isOk());

        then(getUserByIdUseCase).should()
                .getUserById(eq(id));

        then(userWebModelMapper).should()
                .mapToWebModel(eq(user));
    }

    private User givenUser(Long id) {
        User user = Mockito.mock(User.class);
        given(user.getId()).willReturn(id);
        given(getUserByIdUseCase.getUserById(id))
                .willReturn(user);
        return user;
    }
}