public class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("that command isn't right: %s", super.getMessage());
    }
}
