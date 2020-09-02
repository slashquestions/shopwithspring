package StartApp;

import com.mysql.cj.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginSuccessHandlerActiveTrueTest {


    @Captor
    ArgumentCaptor<String> captor;

    @InjectMocks
    LoginSuccessHandlerActiveTrue controller;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Spy
    Statement statement;

    Authentication authenticationAdmin = new Authentication() {
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
            return "admin";
        }
    };

    Authentication authenticationUser = new Authentication() {
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
            return "true";
        }
    };

    @Test
    void onAuthenticationSuccessUserAdmin() throws IOException, ServletException {
        given(request.getRequestURI()).willReturn("/login");
        given(request.getSession()).willReturn(session);

        controller.onAuthenticationSuccess(request,response,authenticationAdmin);
        then(request).should(times(2)).getSession();
        then(response).should().sendRedirect("/admin");

    }



    @Test
    void onAuthenticationSuccessUserNotAdmin() throws IOException, ServletException {
        given(request.getRequestURI()).willReturn("/login");
        given(request.getSession()).willReturn(session);

        controller.onAuthenticationSuccess(request,response,authenticationUser);
        then(request).should(times(2)).getSession();
        then(response).should().sendRedirect("/");

    }



}