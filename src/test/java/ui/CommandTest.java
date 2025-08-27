package ui;

import exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ui.Command.validateAndFormatModifier;

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
        String [] acceptedModifiers = new String[]{"/from","/to"};
        
        assertThrowsExactly(
                InvalidFormatException.class,
                () -> validateAndFormatModifier(modifierString, acceptedModifiers)
        );
    }
}
