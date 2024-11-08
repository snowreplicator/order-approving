package ru.snowreplicator.order_approving.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.snowreplicator.order_approving.Service.Common.Events;

@Data
public class ProcessEventDTO {

    @NotNull
    private Long orderId;
    private Events event;

}
