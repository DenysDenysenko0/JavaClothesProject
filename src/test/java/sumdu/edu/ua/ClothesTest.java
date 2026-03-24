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
}