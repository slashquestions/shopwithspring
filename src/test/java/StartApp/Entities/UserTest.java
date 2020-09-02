package StartApp.Entities;

import StartApp.Enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setActive(true);
        user.setUserOnSite(true);
        user.setRole(Collections.singleton(Role.USER));
    }

    @Test
    void testHashCode() {
        User userClone = new User();
        userClone.setId(user.getId());
        userClone.setUsername(user.getUsername());
        userClone.setPassword(user.getPassword());
        userClone.setActive(user.isActive());
        userClone.setUserOnSite(user.isUserOnSite());
        userClone.setRole(user.getRole());

        int hashFirst = user.hashCode();
        int hashSecond = userClone.hashCode();

        assertEquals(hashFirst,hashSecond);

    }

    @Test
    void testEqualsTrue() {
        User userClone = new User();
        userClone.setId(user.getId());
        userClone.setUsername(user.getUsername());
        userClone.setPassword(user.getPassword());
        userClone.setActive(user.isActive());
        userClone.setUserOnSite(user.isUserOnSite());
        userClone.setRole(user.getRole());

        assertEquals(user,userClone);
    }
    @Test
    void testEqualsFalse() {

        assertFalse(user.equals(new Object()));
    }

    @Test
    void testInterfaceFields(){
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());

    }
}