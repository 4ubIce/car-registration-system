package by.kirill.controller.handler.exceptions;

public class StatusNotUpdatebleException extends Throwable {
    public StatusNotUpdatebleException() {
        super("The status with id: 1 and name: available cannot be changed");
    }
}
