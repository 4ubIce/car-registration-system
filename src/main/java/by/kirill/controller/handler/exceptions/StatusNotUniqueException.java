package by.kirill.controller.handler.exceptions;

public class StatusNotUniqueException extends Throwable {
    public StatusNotUniqueException() {
        super("The status has a non unique status name");
    }
}
