package StartApp.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RefrigeratorTest {

    Refrigerator refrigerator;

    @BeforeEach
    void setUp() {
        refrigerator = new Refrigerator();
        refrigerator.setId(1);
        refrigerator.setType("refrigerators");
        refrigerator.setMaker("Bosch");
        refrigerator.setCounter(10);
        refrigerator.setPrice(10000);
    }

    @Test
    void testEquals() {
        Refrigerator clone = new Refrigerator().createClone(refrigerator);
        boolean answer = clone.equals(refrigerator);
        assertTrue(answer);
        assertEquals(clone,refrigerator);
    }

    @Test
    void testHashCode() {
        Refrigerator clone = new Refrigerator().createClone(refrigerator);
        int hashFirst = clone.hashCode();
        int hashSecond = refrigerator.hashCode();
        assertTrue(hashFirst==hashSecond);
        assertEquals(hashFirst,hashSecond);
    }
}