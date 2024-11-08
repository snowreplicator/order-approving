package ru.snowreplicator.order_approving.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.snowreplicator.order_approving.Service.Common.States;
import ru.snowreplicator.order_approving.Service.Converter.StatesConverter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "state", nullable = false)
    @NotNull
    @Convert(converter = StatesConverter.class)
    private States state;

    @Column(name = "state_date", nullable = false)
    @NotNull
    private LocalDateTime stateDate;

    @Column(name = "description", nullable = true)
    private String description;

}
