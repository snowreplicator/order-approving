package ru.snowreplicator.order_approving.Service.Interface;

import ru.snowreplicator.order_approving.DTO.AddOrderDTO;
import ru.snowreplicator.order_approving.DTO.ProcessEventDTO;
import ru.snowreplicator.order_approving.ViewModel.OrderViewModel;

import java.util.List;

public interface OrderService {

    OrderViewModel add(AddOrderDTO addOrderDTO);

    OrderViewModel processEvent(ProcessEventDTO processEventDTO);

    List<OrderViewModel> getAll();

}
