package StartApp.Controllers;

import StartApp.Entities.Refrigerator;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.WashMachinesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class WashMachinesListPageForAdminControllerTest {

    @InjectMocks
    WashMachinesListPageForAdminController controller;

    @Mock
    WashMachinesRepo  washMachinesRepo;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void toAdminPageWashMachines() throws Exception {
        given(washMachinesRepo.findAll()).willReturn(new Iterable<WashMachine>() {
            @Override
            public Iterator<WashMachine> iterator() {
                List<WashMachine> list = new ArrayList<>();

                return list.iterator();
            }
        });
        mockMvc.perform(get("/admin/listproducts/washmachines"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("washmachines"))
                .andExpect(view().name("WashMachinesPageForAdmin"));
    }

    @Test
    void addMethod() throws Exception {

        mockMvc.perform(post("/admin/listproducts/washmachines")
                .param("type","refrigerators")
                .param("maker","Bosch")
                .param("counter","10")
                .param("price","1000")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/listproducts/washmachines"));

    }
}