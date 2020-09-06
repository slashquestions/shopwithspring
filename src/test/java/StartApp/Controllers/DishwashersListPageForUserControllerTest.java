package StartApp.Controllers;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.Dishwasher;
import StartApp.Repositories.DishwashersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
class DishwashersListPageForUserControllerTest{

    @Mock
    HttpServletRequest request;

    @Mock
    DishwashersRepo dishwashersRepo;

    @InjectMocks
    DishwashersListPageForUserController controller;

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
    void toUserPageDishwasher() throws Exception {
        List<DefaultClassForMachine> list = new ArrayList<>();
        list.add(new Dishwasher());

        List<Dishwasher> ref = new ArrayList<>();
        ref.add(new Dishwasher());

        mockMvc.perform(get("/products/dishwashers")
                        .principal(authentication)
                        .sessionAttr("basketProducts",list)
                //.requestAttr("basketProducts",list)
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dishwashers"))
                .andExpect(view().name("DishwashersPageForUser"));

    }

    @Test
    void addDishwasherToBasketProductsProductFoundAndNoMore() throws Exception{
        Dishwasher refForSearch = new Dishwasher();
        refForSearch.setId(1);
        refForSearch.setType("dishwashers");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(0);
        refForSearch.setPrice(10000);

        List<DefaultClassForMachine> listProducts = new ArrayList<>();
        listProducts.add(refForSearch);
        given(dishwashersRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/dishwashers")
                .principal(authentication)
                .param("id","1"))
                .andExpect(status().is3xxRedirection())
                //.andExpect(model().attribute("messageAboutProduct","Product with id " + 1 + " ended"))
                .andExpect(view().name("redirect:/products/dishwashers"));
    }

    @Test
    void addDishwasherToBasketProductsProductFoundAndMoreZeroAndNotInBasket() throws Exception{
        Dishwasher refForSearch = new Dishwasher();
        refForSearch.setId(1);
        refForSearch.setType("dishwashers");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(10);
        refForSearch.setPrice(10000);

        List<DefaultClassForMachine> listProducts = new ArrayList<>();

        given(dishwashersRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/dishwashers")
                .principal(authentication)
                .param("id","1")
                .sessionAttr("basketProducts",listProducts))
                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("messageAboutProduct"))
                .andExpect(view().name("redirect:/products/dishwashers"));
    }

    @Test
    void addDishwasherToBasketProductsProductFoundAndMoreZeroAndHasInBasket() throws Exception{
        Dishwasher refForSearch = new Dishwasher();
        refForSearch.setId(1);
        refForSearch.setType("dishwashers");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(10);
        refForSearch.setPrice(10000);

        List<DefaultClassForMachine> listProducts = new ArrayList<>();
        listProducts.add(refForSearch);
        given(dishwashersRepo.findById(anyInt())).willReturn(Optional.of(refForSearch));


        mockMvc.perform(post("/products/dishwashers")
                .principal(authentication)
                .param("id","1")
                .sessionAttr("basketProducts",listProducts))
                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("messageAboutProduct"))
                .andExpect(view().name("redirect:/products/dishwashers"));
    }

    @Test
    void addDishwasherToBasketProductsProductNotExist() throws Exception {

        given(dishwashersRepo.findById(anyInt())).willReturn(Optional.empty());
        mockMvc.perform(post("/products/dishwashers")
                .param("id","1")
                .principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products/dishwashers"));
    }
}