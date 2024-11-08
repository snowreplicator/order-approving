package ru.snowreplicator.order_approving.Service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.snowreplicator.order_approving.DTO.AddOrderDTO;
import ru.snowreplicator.order_approving.DTO.ProcessEventDTO;
import ru.snowreplicator.order_approving.Entity.Order;
import ru.snowreplicator.order_approving.Repository.OrderRepository;
import ru.snowreplicator.order_approving.Service.Common.Events;
import ru.snowreplicator.order_approving.Service.Common.States;
import ru.snowreplicator.order_approving.Service.Exception.ErrorAddNewOrderException;
import ru.snowreplicator.order_approving.Service.Exception.OrderNotFoundException;
import ru.snowreplicator.order_approving.Service.Interface.OrderService;
import ru.snowreplicator.order_approving.ViewModel.OrderViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Autowired
    private final StateMachineFactory<States, Events> stateMachineFactory;

    @Transactional
    @Override
    public OrderViewModel add(AddOrderDTO addOrderDTO) {
        Order order = new Order();
        order.setState(States.DRAFT);
        order.setStateDate(LocalDateTime.now());
        order.setDescription(addOrderDTO.getDescription());

        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new ErrorAddNewOrderException(e.getMessage());
        }

        return OrderViewModel.fromDomain(order);
    }

    @Transactional
    @Override
    public OrderViewModel processEvent(ProcessEventDTO processEventDTO) {
        long orderId = processEventDTO.getOrderId() == null ? 0L : processEventDTO.getOrderId();

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null)
            throw new OrderNotFoundException(processEventDTO.getOrderId());

        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine(Long.toString(orderId));
        stateMachine.start();

        Message<Events> message = MessageBuilder
                .withPayload(processEventDTO.getEvent())
                .setHeader("orderId", orderId)
                .build();

        var temp = stateMachineFactory.getStateMachine(Long.toString(orderId));
        logger.info("Sending event {} to state machine for order {}", processEventDTO.getEvent(), orderId);

        stateMachine.sendEvent(message);

        orderRepository.save(order);
        return OrderViewModel.fromDomain(order);
    }

    @Override
    public List<OrderViewModel> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderViewModel::fromDomain)
                .collect(Collectors.toList());
    }

}
