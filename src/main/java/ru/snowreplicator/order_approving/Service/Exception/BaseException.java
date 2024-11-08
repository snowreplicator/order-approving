package ru.snowreplicator.order_approving.Service.Exception;

public abstract class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

}
