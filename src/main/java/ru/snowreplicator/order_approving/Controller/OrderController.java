package ru.snowreplicator.order_approving.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.snowreplicator.order_approving.DTO.AddOrderDTO;
import ru.snowreplicator.order_approving.DTO.ProcessEventDTO;
import ru.snowreplicator.order_approving.Service.Interface.OrderService;
import ru.snowreplicator.order_approving.ViewModel.OrderViewModel;

import java.util.List;

@Tag(name = "OrderController", description = "Operations with orders")
@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all orders", description = "Info about all orders")
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<OrderViewModel>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Add new order", description = "Add new order to order table")
    @PostMapping(path = "/add")
    public ResponseEntity<OrderViewModel> add(@RequestBody AddOrderDTO addOrderDTO) {
        OrderViewModel orderViewModel = orderService.add(addOrderDTO);
        return new ResponseEntity<>(orderViewModel, HttpStatus.OK);
    }

    @Operation(summary = "Process event", description = "Process event on selected order")
    @PutMapping(path = "/processEvent")
    public ResponseEntity<OrderViewModel> processEvent(@RequestBody ProcessEventDTO processEventDTO) {
        OrderViewModel orderViewModel = orderService.processEvent(processEventDTO);
        return new ResponseEntity<>(orderViewModel, HttpStatus.OK);
    }

}
