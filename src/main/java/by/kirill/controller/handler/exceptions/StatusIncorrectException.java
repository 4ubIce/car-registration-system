package by.kirill.controller.handler.exceptions;

public class StatusIncorrectException extends Throwable {
    public StatusIncorrectException() {
        super("Status incorrect. The status can only be changed for a car with the available status");
    }
}
