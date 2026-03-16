package sumdu.edu.ua;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import sumdu.edu.ua.enums.Category;
import sumdu.edu.ua.enums.Size;


class ClothesTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Clothes clothes = new Clothes("T-Shirt", Size.M, 500.0, "Nike", 10, Category.SHIRT);
        assertThrows(IllegalArgumentException.class, () -> clothes.setPrice(-1));
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> new Clothes("", Size.M, -10.0, "", -5, Category.SHIRT));
    }

    @Test
    void shouldCreateEqualButDifferentObjectUsingCopyConstructor() {
        Clothes original = new Clothes("T-Shirt", Size.M, 500, "Nike", 10, Category.SHIRT);
        Clothes copy = new Clothes(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    @Test
    void shouldIncreaseStaticCounterWhenObjectsAreCreated() {
        int before = Clothes.getCreatedCount();
        new Clothes("T-Shirt", Size.M, 500, "Nike", 10, Category.SHIRT);
        int after = Clothes.getCreatedCount();

        assertEquals(before + 1, after);
    }
}