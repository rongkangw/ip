package thecoolerduke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static thecoolerduke.main.Command.validateAndFormatInput;

import org.junit.jupiter.api.Test;

import thecoolerduke.exceptions.InvalidFormatException;

public class CommandTest {
    @Test
    public void testValidCommand() {
        assertEquals(Command.LIST_TASK, Command.validateCommand("list"));
    }

    @Test
    public void testInvalidCommand() {
        assertNull(Command.validateCommand("ls"));
    }

    @Test
    public void testThrowInvalidFormatExceptionOnInvalidFormat() {
        String modifierString = "test event /by 12/2/2025 18:00";
        String [] acceptedModifiers = new String[]{"/from", "/to"};
        assertThrowsExactly(
                InvalidFormatException.class, () -> validateAndFormatInput(modifierString, acceptedModifiers));
    }
}
