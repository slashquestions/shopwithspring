package StartApp.Controllers;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsersListPageForAdminControllerTest {

    @InjectMocks
    UsersListPageForAdminController controller;

    @Mock
    UserRepo userRepo;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void toListUsers() throws Exception {
        mockMvc.perform(get("/admin/listusers"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("ListUsersPageForAdmin"));
    }

    @Test
    void deleteUserFromDB() throws Exception {
        mockMvc.perform(post("/admin/listusers")
                .param("id","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/listusers"));
    }
}