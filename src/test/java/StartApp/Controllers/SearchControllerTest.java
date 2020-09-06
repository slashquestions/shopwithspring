package StartApp.Controllers;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.Refrigerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    SearchController controller;

    MockMvc mockMvc;

    DefaultClassForMachine classForTest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        classForTest = new Refrigerator();
        classForTest.setId(1);
        classForTest.setType("refrigerators");
        classForTest.setMaker("Bosch");
        classForTest.setCounter(10);
        classForTest.setPrice(10000);
    }

    @Test
    void toSearchPage() throws Exception {
        given(entityManager.find(any(),any())).willReturn(classForTest);

        mockMvc.perform(get("/search/")
        .param("id","1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("ProductPage"));
    }
}