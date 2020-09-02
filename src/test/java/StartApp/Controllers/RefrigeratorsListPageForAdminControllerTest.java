package StartApp.Controllers;

import StartApp.Entities.Refrigerator;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.RefrigeratorsRepo;
import StartApp.Repositories.WashMachinesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RefrigeratorsListPageForAdminControllerTest {

    @InjectMocks
    RefrigeratorsListPageForAdminController controller;

    @Mock
    RefrigeratorsRepo refrigeratorsRepo;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void toAdminPageRefrigerators() throws Exception {
        given(refrigeratorsRepo.findAll()).willReturn(new Iterable<Refrigerator>() {
            @Override
            public Iterator<Refrigerator> iterator() {
                List<Refrigerator> list = new ArrayList<>();

                return list.iterator();
            }
        });
        mockMvc.perform(get("/admin/listproducts/refrigerators"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("refrigerators"))
                .andExpect(view().name("RefrigeratorsPageForAdmin"));
    }

    @Test
    void addMethod() throws Exception {

        mockMvc.perform(post("/admin/listproducts/refrigerators")
                .param("type","refrigerators")
                .param("maker","Bosch")
                .param("counter","10")
                .param("price","1000")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/listproducts/refrigerators"));

    }
}

