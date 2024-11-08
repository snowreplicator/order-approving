package ru.snowreplicator.order_approving.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.snowreplicator.order_approving.Entity.Order;
import ru.snowreplicator.order_approving.Service.Common.States;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewModel {

    private long id;
    private States state;
    private LocalDateTime stateDate;
    private String description;

    public static OrderViewModel fromDomain(Order printHistory) {
        return new OrderViewModel(
                printHistory.getId(),
                printHistory.getState(),
                printHistory.getStateDate(),
                printHistory.getDescription()
        );
    }

}
