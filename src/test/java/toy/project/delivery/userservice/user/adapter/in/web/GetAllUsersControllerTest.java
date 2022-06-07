package toy.project.delivery.userservice.user.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import toy.project.delivery.userservice.security.SecurityConfiguration;
import toy.project.delivery.userservice.user.application.port.in.GetAllUsersUseCase;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(controllers = GetAllUsersController.class)
class GetAllUsersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAllUsersUseCase getAllUsersUseCase;
    @MockBean
    private UserWebModelMapper userWebModelMapper;

    @Test
    void getAllUsers_without_offset_max() throws Exception {
        int defaultOffset = 0;
        int defaultMax = 10;
        List<User> users = givenUsers(defaultOffset, defaultMax);

        mockMvc.perform(get("/users")
                .header("Content-Type", "application/json")
        ).andExpect(status().isOk());

        then(getAllUsersUseCase).should()
                .getAllUsers(eq(defaultOffset), eq(defaultMax));

        then(userWebModelMapper).should()
                .mapToWebModel(eq(users));
    }


    @Test
    void getAllUsers_with_offset_max() throws Exception {
        int offset = 0;
        int max = 10;
        List<User> users = givenUsers(offset, max);

        mockMvc.perform(get("/users")
                .header("Content-Type", "application/json")
                .param("offset", Integer.toString(offset))
                .param("max", Integer.toString(max))
        ).andExpect(status().isOk());

        then(getAllUsersUseCase).should()
                .getAllUsers(eq(offset), eq(max));

        then(userWebModelMapper).should()
                .mapToWebModel(eq(users));
    }


    private List<User> givenUsers(int offset, int max) {
        List<User> userList = List.of(givenUser(1L), givenUser(2L));
        given(getAllUsersUseCase.getAllUsers(offset, max))
                .willReturn(userList);
        return userList;
    }

    private User givenUser(Long id) {
        User user = Mockito.mock(User.class);
        given(user.getId()).willReturn(id);
        return user;
    }
}