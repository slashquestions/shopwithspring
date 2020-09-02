package StartApp.Controllers;

import StartApp.Entities.DefaultInterfaceForMachine;
import StartApp.Entities.Refrigerator;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.WashMachinesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
class WashMachinesListPageForUserControllerTest {

    @Mock
    HttpServletRequest request;

    @Mock
    WashMachinesRepo washMachinesRepo;

    @InjectMocks
    WashMachinesListPageForUserController controller;

    MockMvc mockMvc;


    Authentication authentication = new Authentication() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return "user";
        }
    };

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void toUserPageWashMachines() throws Exception {
        List<DefaultInterfaceForMachine> list = new ArrayList<>();
        list.add(new Refrigerator());

        List<Refrigerator> ref = new ArrayList<>();
        ref.add(new Refrigerator());

        mockMvc.perform(get("/products/washmachines")
                .principal(authentication)
                .sessionAttr("basketProducts",list)
                //.requestAttr("basketProducts",list)
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("washmachines"))
                .andExpect(view().name("WashMachinesPageForUser"));

    }

    @Test
    void addWashMachineToBasketProductsProductFoundAndNoMore() throws Exception{
        WashMachine refForSearch = new WashMachine();
        refForSearch.setId(1);
        refForSearch.setType("refrigerators");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(0);
        refForSearch.setPrice(10000);

        List<DefaultInterfaceForMachine> listProducts = new ArrayList<>();
        listProducts.add(refForSearch);
        given(washMachinesRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/washmachines")
                .principal(authentication)
                .param("id","1"))
                .andExpect(status().is3xxRedirection())
                //.andExpect(model().attribute("messageAboutProduct","Product with id " + 1 + " ended"))
                .andExpect(view().name("redirect:/products/washmachines"));
    }

    @Test
    void addWashMachineToBasketProductsProductFoundAndMoreZeroAndNotInBasket() throws Exception{
        WashMachine refForSearch = new WashMachine();
        refForSearch.setId(1);
        refForSearch.setType("refrigerators");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(10);
        refForSearch.setPrice(10000);

        List<DefaultInterfaceForMachine> listProducts = new ArrayList<>();

        given(washMachinesRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/washmachines")
                .principal(authentication)
                .param("id","1")
                .sessionAttr("basketProducts",listProducts))
                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("messageAboutProduct"))
                .andExpect(view().name("redirect:/products/washmachines"));
    }

    @Test
    void addWashMachineToBasketProductsProductFoundAndMoreZeroAndHasInBasket() throws Exception{
        WashMachine refForSearch = new WashMachine();
        refForSearch.setId(1);
        refForSearch.setType("refrigerators");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(10);
        refForSearch.setPrice(10000);

        List<DefaultInterfaceForMachine> listProducts = new ArrayList<>();
        listProducts.add(refForSearch);
        given(washMachinesRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/washmachines")
                .principal(authentication)
                .param("id","1")
                .sessionAttr("basketProducts",listProducts))
                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("messageAboutProduct"))
                .andExpect(view().name("redirect:/products/washmachines"));
    }

    @Test
    void addWashMachineToBasketProductsProductNotExist() throws Exception {

        given(washMachinesRepo.findById(anyInt())).willReturn(Optional.empty());
        mockMvc.perform(post("/products/washmachines")
                .param("id","1")
                .principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products/washmachines"));
    }
}