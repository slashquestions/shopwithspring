package StartApp.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DishwasherTest {

    Dishwasher dishwasher;

    @BeforeEach
    void setUp() {
        dishwasher = new Dishwasher();
        dishwasher.setId(1);
        dishwasher.setType("dishwashers");
        dishwasher.setMaker("Bosch");
        dishwasher.setCounter(10);
        dishwasher.setPrice(10000);
    }

    @Test
    void testEquals() {
        Dishwasher clone = new Dishwasher().createClone(dishwasher);
        boolean answer = clone.equals(dishwasher);
        assertTrue(answer);
        assertEquals(clone,dishwasher);
    }

    @Test
    void testHashCode() {
        Dishwasher clone = new Dishwasher().createClone(dishwasher);
        int hashFirst = clone.hashCode();
        int hashSecond = dishwasher.hashCode();
        assertTrue(hashFirst==hashSecond);

    }
}