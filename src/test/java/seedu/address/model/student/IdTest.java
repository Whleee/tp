package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void isValidId() {
        // null Id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid Id values
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("one")); // non-numeric
        assertFalse(Id.isValidId("1a2")); // alphabets within digits
        assertFalse(Id.isValidId("1 2")); // spaces within digits

        // valid Id values
        assertTrue(Id.isValidId("234"));
        assertTrue(Id.isValidId("209"));
        assertTrue(Id.isValidId("202"));

        // NOTE: Tests on uniqueness of Id values will be trickier due to mutability
        // unique values
        new Id("210");
        assertTrue(Id.isValidId("300"));

        // non-unique values
        assertFalse(Id.isValidId("210"));
    }

    @Test
    public void getLastUsedId() {
        new Id("300");
        assertEquals(Id.getLastUsedId(), 300);

        new Id("301");
        new Id("302");
        assertEquals(Id.getLastUsedId(), 302);
    }
}
