package ru.snowreplicator.order_approving.Service.Exception;

public class ErrorAddNewOrderException extends BaseException {

    public ErrorAddNewOrderException(String message) {
        super("Cannot add new order" + (message != null && !message.isEmpty() ? ". Error: " + message : ""));
    }

}

