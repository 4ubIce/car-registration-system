package by.kirill.controller.handler.exceptions;

public class StatusNotFoundException extends Throwable {
    public StatusNotFoundException() {
        super("Status not found");
    }
}
