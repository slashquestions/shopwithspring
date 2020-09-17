package StartApp.Controllers;

import StartApp.Entities.OrderItem;
import StartApp.Entities.WashMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)


class BasketProductsPageControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    BasketProductsPageController controller;

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
            return "xxx";
        }
    };

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    void toBasketProductsPage() throws Exception {
        WashMachine refForSearch = new WashMachine();
        refForSearch.setId(1);
        refForSearch.setType("refrigerators");
        refForSearch.setMaker("Bosch");
        refForSearch.setCounter(10);
        refForSearch.setPrice(10000);

        List<OrderItem> listProducts = new ArrayList<>();
        OrderItem one = new OrderItem(1,refForSearch);

        listProducts.add(one);

        mockMvc.perform(get("/basketproducts")
                .principal(authentication)
        .sessionAttr("basketProducts",listProducts))
                .andExpect(status().isOk())
                .andExpect(view().name("BasketProducts"));
    }
}