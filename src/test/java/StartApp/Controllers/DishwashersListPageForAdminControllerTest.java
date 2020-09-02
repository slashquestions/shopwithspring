package StartApp.Controllers;

import StartApp.Entities.Dishwasher;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.DishwashersRepo;
import StartApp.Repositories.WashMachinesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class DishwashersListPageForAdminControllerTest {

    @InjectMocks
    DishwashersListPageForAdminController controller;

    @Mock
    DishwashersRepo dishwashersRepo;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void toAdminPageWashMachines() throws Exception {
        given(dishwashersRepo.findAll()).willReturn(new Iterable<Dishwasher>() {
            @Override
            public Iterator<Dishwasher> iterator() {
                List<Dishwasher> list = new ArrayList<>();

                return list.iterator();
            }
        });
        mockMvc.perform(get("/admin/listproducts/dishwashers"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dishwashers"))
                .andExpect(view().name("DishwashersPageForAdmin"));
    }

    @Test
    void addMethod() throws Exception {

        mockMvc.perform(post("/admin/listproducts/dishwashers")
                .param("type","dishwashers")
                .param("maker","Bosch")
                .param("counter","10")
                .param("price","1000")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/listproducts/dishwashers"));

    }
}
