package ru.snowreplicator.order_approving.Service.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import ru.snowreplicator.order_approving.Entity.Order;
import ru.snowreplicator.order_approving.Repository.OrderRepository;
import ru.snowreplicator.order_approving.Service.Common.Events;
import ru.snowreplicator.order_approving.Service.Common.States;
import ru.snowreplicator.order_approving.Service.Exception.OrderNotFoundException;

@Component
public class OrderStateMachineListener extends StateMachineListenerAdapter<States, Events> {

    private static final Logger logger = LoggerFactory.getLogger(OrderStateMachineListener.class);

    private final OrderRepository orderRepository;

    public OrderStateMachineListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @OnTransition(target = "IN_PROGRESS")
    public void onOrderAccepted(StateMachine<States, Events> stateMachine) {
        Long orderId = Long.parseLong(stateMachine.getId());
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        order.setState(stateMachine.getState().getId());
        orderRepository.save(order);
    }

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        logger.info("State changed from {} to {}", from, to);

    }

    @Override
    public void transition(Transition<States, Events> transition) {
        logger.info("Transition: {}", transition);
    }

    @Override
    public void stateEntered(State<States, Events> state) {
        logger.info("State entered: {}", state);
    }

    @Override
    public void stateExited(State<States, Events> state) {
        logger.info("State exited: {}", state);
    }

    @Override
    public void eventNotAccepted(Message<Events> message) {
        logger.warn("Event not accepted: {}", message);
    }


    public void exceptionThrown(Throwable exception) {
        logger.error("Exception thrown: ", exception);
    }


}
