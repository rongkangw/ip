package main;

import static main.Command.validateAndFormatModifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import exceptions.InvalidFormatException;

public class CommandTest {
    @Test
    public void testValidCommand() {
        assertEquals(Command.LIST_TASK, Command.validateCommand("list"));
    }

    @Test
    public void testInvalidCommand() {
        assertNull(Command.validateCommand("ls"));
    }

    @SuppressWarnings({"checkstyle:Regexp", "checkstyle:SeparatorWrap"})
    @Test
    public void testThrowInvalidFormatExceptionOnInvalidFormat() {
        String modifierString = "test event /by 12/2/2025 18:00";
        String [] acceptedModifiers = new String[]{"/from", "/to"};
        assertThrowsExactly(
                InvalidFormatException.class, () -> validateAndFormatModifier(modifierString, acceptedModifiers));
    }
}
