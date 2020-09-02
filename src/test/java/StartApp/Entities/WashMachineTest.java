package StartApp.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WashMachineTest {

    WashMachine washMachine;

    @BeforeEach
    void setUp() {
        washMachine = new WashMachine();
        washMachine.setId(1);
        washMachine.setType("washmachines");
        washMachine.setMaker("Bosch");
        washMachine.setCounter(10);
        washMachine.setPrice(10000);
    }

    @Test
    void testEquals() {
        WashMachine clone = new WashMachine().createClone(washMachine);
        boolean answer = clone.equals(washMachine);
        assertTrue(answer);
        assertEquals(clone,washMachine);
    }

    @Test
    void testHashCode() {
        WashMachine clone = new WashMachine().createClone(washMachine);
        int hashFirst = clone.hashCode();
        int hashSecond = washMachine.hashCode();
        assertTrue(hashFirst==hashSecond);
        assertEquals(hashFirst,hashSecond);
    }
}