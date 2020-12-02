public class UserNotFoundError extends Exception {
    public UserNotFoundError(String message) {
        super(message);
    }
    public UserNotFoundError() { }
}