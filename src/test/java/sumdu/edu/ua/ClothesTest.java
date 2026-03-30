package sumdu.edu.ua;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import sumdu.edu.ua.enums.Size;


class ClothesTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Clothes clothes = new Clothes("T-Shirt", Size.M, 500.0, "Nike", 10);
        assertThrows(IllegalArgumentException.class, () -> clothes.setPrice(-1));
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> new Clothes("", Size.M, -10.0, "", -5));
    }

    @Test
    void shouldCreateEqualButDifferentObjectUsingCopyConstructor() {
        Clothes original = new Clothes("T-Shirt", Size.M, 500, "Nike", 10);
        Clothes copy = new Clothes(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    @Test
    void shouldThrowExceptionWhenSleeveLengthInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Shirts("Vyshyvanka", Size.L, 500, "Merezhka", 3, -5));
    }

    @Test
    void shouldThrowExceptionWhenWaistSizeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Pants("Sport Pants", Size.M, 1000, "Nike", 2, 0));
    }

    @Test
    void shouldThrowExceptionWhenSoleMaterialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Shoes("Flipers", Size.M, 300, "Puma", 2, ""));
    }

    @Test
    void shouldSetHasHoodCorrectly() {
        Jackets jacket = new Jackets("Winter Jacket", Size.S, 3000, "Columbia", 10, true);
        assertEquals(true, jacket.isHasHood());
    }
}