package ru.snowreplicator.order_approving.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.snowreplicator.order_approving.DTO.AddOrderDTO;
import ru.snowreplicator.order_approving.Entity.Order;
import ru.snowreplicator.order_approving.Repository.OrderRepository;
import ru.snowreplicator.order_approving.Service.Common.States;
import ru.snowreplicator.order_approving.Service.Exception.ErrorAddNewOrderException;
import ru.snowreplicator.order_approving.Service.Interface.OrderService;
import ru.snowreplicator.order_approving.ViewModel.OrderViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderViewModel add(AddOrderDTO addOrderDTO) {
        Order order = new Order();
        order.setState(States.DRAFT);
        order.setState(null);
        order.setStateDate(LocalDateTime.now());
        order.setDescription(addOrderDTO.getDescription());

        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new ErrorAddNewOrderException(e.getMessage());
        }

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
