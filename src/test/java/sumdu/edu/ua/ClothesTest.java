package sumdu.edu.ua;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClothesTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Clothes clothes = new Clothes("T-Shirt", "M", 500.0, "Nike", 10);
        assertThrows(IllegalArgumentException.class, () -> clothes.setPrice(-1));
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> new Clothes("", "M", -10.0, "", -5));
    }
}