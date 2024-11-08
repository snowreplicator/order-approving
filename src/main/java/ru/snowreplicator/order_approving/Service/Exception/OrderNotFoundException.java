package ru.snowreplicator.order_approving.Service.Exception;

public class OrderNotFoundException extends BaseException {

    public OrderNotFoundException(Long id) {
        super("Order not found (id: " + id + ")");
    }

}

