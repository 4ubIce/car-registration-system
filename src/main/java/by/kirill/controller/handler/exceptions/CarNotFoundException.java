package by.kirill.controller.handler.exceptions;

public class CarNotFoundException extends Throwable {
    public CarNotFoundException() {
        super("Car not found");
    }
}
