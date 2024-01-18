package org.example.task7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest {

    @Test
    void testAssignIfOneOfObjectsIsNullThrowIllegalArgumentException() {
        PersonFrom from = new PersonFrom("Lena", 30);
        PersonTo to = null;
        assertThrows(IllegalArgumentException.class, () -> {
            BeanUtils.assign(to, from);
        });
    }

    @Test
    void testAssignIfAllFieldsOfObjectsAreEqualAfterAssignAssertTrue() {
        PersonFrom from = new PersonFrom("Vova", 28);
        PersonTo to = new PersonTo();

        BeanUtils.assign(to, from);

        assertEquals(to.getFullName(), from.getFullName());
        assertEquals(to.getAge(), from.getAge());
    }
}