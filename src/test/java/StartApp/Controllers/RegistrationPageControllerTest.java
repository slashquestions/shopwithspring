package StartApp.Controllers;

import StartApp.Entities.User;
import StartApp.Repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class RegistrationPageControllerTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    RegistrationPageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("RegistrationPage"));
    }

    @Test
    void addUserNotFinded() throws Exception {
        given(userRepo.findByUsername(anyString())).willReturn(null);
        mockMvc.perform(post("/registration")
        .param("username","sss"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void addUserFinded() throws Exception {
        User user = new User();
        given(userRepo.findByUsername(anyString())).willReturn(new User());
        mockMvc.perform(post("/registration")
                .param("username","sss")
                .param("role","USER"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("RegistrationPage"));
    }
}